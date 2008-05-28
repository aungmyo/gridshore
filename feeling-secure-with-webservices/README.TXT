Import this project into your favorite IDE.

Make sure you check whether the paths of the truststores match the ones used in your environment.
Files you might want to check are:
\client\src\main\java\nl\gridshore\demo\webservice\WsClient.java
\provider\src\main\java\nl\gridshore\demo\webservice\WebServiceLauncher.java

That should be it... start the server by running the WebServiceLauncher file
Then, when the server is started, launch the client to send a message.

Make sure the port numbers of client and server match. You could use a tool like tcpmon to
see the contents of the messages sent. Could be nice.

Have fun!!

PS. If you have any questions, place a comment on one of my WSSecurity article:
http://www.gridshore.nl/2008/05/17/feeling-secure-with-web-services-introduction/
http://www.gridshore.nl/2008/05/20/feeling-secure-with-web-services-part-1-the-usernametoken/
http://www.gridshore.nl/2008/05/26/feeling-secure-with-web-services-part-2/
