package nl.gridshore.samples.hippo.web;

import org.springframework.ws.server.endpoint.AbstractJDomPayloadEndpoint;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 28, 2008
 * Time: 6:11:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchNewsEndpoint extends AbstractJDomPayloadEndpoint {
    private static Logger logger = LoggerFactory.getLogger(SearchNewsEndpoint.class);

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
        String searchText = searchTextExpression.valueOf(requestElement);
        logger.info(searchText);

        Element searchResponse = new Element("SearchResponse",namespace);

        Element title = new Element("Title",namespace);
        title.setText("The title");

        Element messageContent = new Element("MessageContent",namespace);
        messageContent.setText("the message");

        Element publicationDate = new Element("PublicationDate",namespace);
        publicationDate.setText((new Date()).toString());

        searchResponse.addContent(title);
        searchResponse.addContent(messageContent);
        searchResponse.addContent(publicationDate);
        return searchResponse;
    }
}
