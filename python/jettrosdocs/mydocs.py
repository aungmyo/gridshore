import os
from google.appengine.ext.webapp import template
import cgi
import gdata.docs.service
import gdata.docs
import gdata.alt.appengine
import urllib

from google.appengine.api import users
from google.appengine.api import urlfetch
from google.appengine.ext import webapp
from google.appengine.ext.webapp.util import run_wsgi_app
from google.appengine.ext import db

HOST_NAME = 'docsjettro.appspot.com'

class StoredToken(db.Model):
  user_email = db.StringProperty(required=True)
  session_token = db.StringProperty(required=True)
  target_url = db.StringProperty(required=True)

class Document:
  pass
  
class MainPage(webapp.RequestHandler):
  # Initialize some global variables we will use
  def __init__(self):
    # Stores the page's current user
    self.current_user = None
    # Stores the Google Data Client
    self.client = None
    # The one time use token value from the URL after the AuthSub redirect.
    self.token = None
    # The url of the feed to obtain    
    self.feed_url = 'http://docs.google.com/feeds/documents/private/full'
    # Stores the token_scope information
    self.token_scope = self.feed_url
    
  def get(self):
    self.current_user = users.GetCurrentUser()

    # Allow the user to sign in or sign out
    if self.current_user:
      url = users.create_logout_url(self.request.uri)
      url_linktext = 'Logout'
    else:
      url = users.create_login_url(self.request.uri)
      url_linktext = 'Login'

    for param in self.request.query.split('&'):
      # Get the token scope variable we specified when generating the URL
      if param.startswith('token_scope'):
        self.token_scope = urllib.unquote_plus(param.split('=')[1])
      # Google Data will return a token, get that
      elif param.startswith('token'):
        self.token = param.split('=')[1]

    # Manage our Authentication for the user
    self.ManageAuth()
    self.LookupToken()
    
    documents=[]
    responseMessage = ''
    try:
      documents = self.FetchFeed()
    except Exception, strerror:
      responseMessage = strerror
            
    template_values = {
      'url': url,
      'url_linktext': url_linktext,
      'response_msg': responseMessage,
      'documents': documents,
      }

    path = os.path.join(os.path.dirname(__file__), 'index.html')
    self.response.out.write(template.render(path, template_values))


  def ManageAuth(self):
    self.client = gdata.docs.service.DocsService()
    gdata.alt.appengine.run_on_appengine(self.client)
    if self.token and self.current_user:
      self.UpgradeAndStoreToken()

  def UpgradeAndStoreToken(self):
    self.client.SetAuthSubToken(self.token)
    self.client.UpgradeToSessionToken()
    if self.current_user:
      # Create a new token object for the data store which associates the
      # session token with the requested URL and the current user.
      new_token = StoredToken(user_email=self.current_user.email(),
          session_token=self.client.GetAuthSubToken(), target_url=self.token_scope)
      new_token.put()

  def LookupToken(self):
    if self.feed_url and self.current_user:
      stored_tokens = StoredToken.gql('WHERE user_email = :1',self.current_user.email())
      for token in stored_tokens:
        if self.feed_url.startswith(token.target_url):
          self.client.SetAuthSubToken(token.session_token)
          return      

  def FetchFeed(self):
    # The following creation of the client is necessary for the case a client is not authenticated
    if not self.client:
      self.client = gdata.docs.service.DocsService()
      gdata.alt.appengine.run_on_appengine(self.client)

    try:
      return self.ListAllDocuments()
    except gdata.service.RequestError, request_error:
      # If fetching fails, then tell the user that they need to login to
      # authorize this app by logging in at the following URL.
      if request_error[0]['status'] == 401:
        # Get the URL of the current page so that our AuthSub request will
        # send the user back to here.
        next = self.request.uri
        auth_sub_url = self.client.GenerateAuthSubURL(next, self.feed_url,secure=False, session=True)
        raise Exception('<a href="%s">Click here to authorize this application to view the feed</a>' % (auth_sub_url))
      else:
        raise Exception( 'Something else went wrong, here is the error object: %s ' % (str(request_error[0])))
          
  def ListAllDocuments(self):
    feed = self.client.GetDocumentListFeed()
    documents = []
    for i, entry in enumerate(feed.entry):
      doc = Document()
      doc.title = entry.title.text.encode('UTF-8')
      doc.author = entry.author[0].name.text.encode('UTF-8') 
      documents.append(doc)
    return documents

application = webapp.WSGIApplication([('/docs', MainPage)],
                                     debug=True)

def main():
  run_wsgi_app(application)

if __name__ == "__main__":
  main()