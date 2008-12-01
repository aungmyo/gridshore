package nl.gridshore.samples.hippo.web;

import org.springframework.ws.server.endpoint.AbstractJDomPayloadEndpoint;
import org.springframework.beans.factory.annotation.Required;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import nl.gridshore.samples.hippo.RepoSessionTemplate;
import nl.gridshore.samples.hippo.SessionCallback;
import nl.gridshore.samples.hippo.impl.WrappedSession;

import javax.jcr.query.QueryResult;
import javax.jcr.query.QueryManager;
import javax.jcr.query.Query;
import javax.jcr.*;


/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 28, 2008
 * Time: 6:11:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchNewsEndpoint extends AbstractJDomPayloadEndpoint {
    private static Logger logger = LoggerFactory.getLogger(SearchNewsEndpoint.class);

    private RepoSessionTemplate repoSessionTemplate;

    private XPath searchTextExpression;
    private Namespace namespace;

    public SearchNewsEndpoint() {
        namespace = Namespace.getNamespace("ovh", "http://rijksoverheid.nl/schemas");
        try {
            searchTextExpression = XPath.newInstance("//ovh:SearchText");
            searchTextExpression.addNamespace(namespace);
        } catch (JDOMException e) {
            logger.error("Problem while creating an XPath");
            throw new EndpointConfigurationException("Problem in the SearchNewsEndpoint",e);
        }
    }

    protected Element invokeInternal(Element requestElement) throws Exception {
        final String searchText = searchTextExpression.valueOf(requestElement);

        Element searchResponse = new Element("SearchResponse",namespace);

        QueryResult queryResult = repoSessionTemplate.readFromSession(new SessionCallback() {
            public QueryResult readFromSession(WrappedSession session) throws RepositoryException {
                Workspace workspace = session.getWorkspace();
                QueryManager queryManager = workspace.getQueryManager();
                Query query = queryManager.createQuery("//element(*,poc:document)[jcr:like(@poc:introductie,'%"
                        + searchText + "%')]", Query.XPATH);
                return query.execute();
            }
        });
        NodeIterator nodes = queryResult.getNodes();
        while (nodes.hasNext()) {
            Node node = nodes.nextNode();
            Property introductie = node.getProperty("poc:introductie");
            Property titel = node.getProperty("poc:titel");
            Property documentdatum = node.getProperty("poc:documentdatum");

            Element document = new Element("NewsDocument",namespace);
            Element title = new Element("Title",namespace);
            Element messageContent = new Element("MessageContent",namespace);
            Element publicationDate = new Element("PublicationDate",namespace);

            title.setText(introductie.getString());
            messageContent.setText(titel.getString());
            publicationDate.setText(documentdatum.getString());

            document.addContent(title);
            document.addContent(messageContent);
            document.addContent(publicationDate);
            searchResponse.addContent(document);
        }

        return searchResponse;
    }

    @Required
    public void setRepoSessionTemplate(RepoSessionTemplate repoSessionTemplate) {
        this.repoSessionTemplate = repoSessionTemplate;
    }
}
