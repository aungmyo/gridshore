package nl.gridshore.samples.hippo.web;

import nl.gridshore.samples.hippo.RepoSessionTemplate;
import nl.gridshore.samples.hippo.SessionCallback;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.xpath.XPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.ws.server.endpoint.AbstractJDomPayloadEndpoint;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;


/**
 * Created by IntelliJ IDEA.
 * User: jettro coenradie
 * Date: Nov 28, 2008
 * Time: 6:11:38 PM
 * <p>Endpoint that makes use of JDom to obtain the request and create the response for a webservice call</p>
 * <p>Check the schema document.xsd in the webapp folder for expected input and output</p> 
 */
public class SearchNewsEndpoint extends AbstractJDomPayloadEndpoint {
    private static Logger logger = LoggerFactory.getLogger(SearchNewsEndpoint.class);

    public static final String HTTP_GRIDSHORE_NL_SCHEMAS = "http://gridshore.nl/schemas";

    private RepoSessionTemplate repoSessionTemplate;
    private XPath searchTextExpression;
    private Namespace namespace;

    public SearchNewsEndpoint() {
        namespace = Namespace.getNamespace("grid", HTTP_GRIDSHORE_NL_SCHEMAS);
        try {
            searchTextExpression = XPath.newInstance("//grid:SearchText");
            searchTextExpression.addNamespace(namespace);
        } catch (JDOMException e) {
            logger.error("Problem while creating an XPath for searching the input");
            throw new EndpointConfigurationException("Problem in the SearchNewsEndpoint", e);
        }
    }

    protected Element invokeInternal(Element requestElement) throws Exception {
        final String searchText = searchTextExpression.valueOf(requestElement);

        QueryResult queryResult = executeQuery(searchText);

        return createResponseFromQueryResult(queryResult);
    }

    private Element createResponseFromQueryResult(QueryResult queryResult) throws RepositoryException {
        Element searchResponse = new Element("SearchResponse", namespace);
        NodeIterator nodes = queryResult.getNodes();
        while (nodes.hasNext()) {
            Node node = nodes.nextNode();
            Property title = node.getProperty("defaultcontent:title");
            Property introduction = node.getProperty("defaultcontent:introduction");
            Property body = node.getProperty("defaultcontent:body");
            Property uuid = node.getProperty("jcr:uuid");

            Element articleElement = new Element("Article", namespace);
            articleElement.setAttribute("uuid", uuid.getString());
            Element titleElement = new Element("Title", namespace);
            Element introductionElement = new Element("Introduction", namespace);
            Element bodyElement = new Element("Body", namespace);

            titleElement.setText(title.getString());
            introductionElement.setText(introduction.getString());
            bodyElement.setText(body.getString());

            articleElement.addContent(titleElement);
            articleElement.addContent(introductionElement);
            articleElement.addContent(bodyElement);
            searchResponse.addContent(articleElement);
        }
        return searchResponse;
    }

    private QueryResult executeQuery(final String searchText) throws RepositoryException {
        return repoSessionTemplate.readFromSession(new SessionCallback() {
            public QueryResult readFromSession(QueryManager queryManager) throws RepositoryException {
                Query query = queryManager.createQuery("//element(*,defaultcontent:article)[jcr:like(@defaultcontent:title,'%"
                        + searchText + "%')]", Query.XPATH);
                return query.execute();
            }
        });
    }

    @Required
    public void setRepoSessionTemplate(RepoSessionTemplate repoSessionTemplate) {
        this.repoSessionTemplate = repoSessionTemplate;
    }
}
