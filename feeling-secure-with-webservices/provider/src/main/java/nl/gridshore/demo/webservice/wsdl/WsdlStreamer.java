package nl.gridshore.demo.webservice.wsdl;

import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.wsdl.WsdlDefinition;
import org.springframework.xml.xpath.XPathOperations;
import org.w3c.dom.Node;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import java.util.List;

public class WsdlStreamer extends AbstractController {

    private WsdlDefinition wsdlDefinition;
    private XPathOperations xpathTemplate;

    protected ModelAndView handleRequestInternal(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws Exception {

        Source source = wsdlDefinition.getSource();
        List<Node> locations = xpathTemplate.evaluateAsNodeList("//soap:address/@location", source);
        for (Node location : locations) {
            location.setTextContent(httpServletRequest.getRequestURL().toString().replaceAll(".wsdl", "Service"));
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, new StreamResult(httpServletResponse.getOutputStream()));

        return null;
    }

    public void setWsdlDefinition(final WsdlDefinition wsdlDefinition) {
        this.wsdlDefinition = wsdlDefinition;
    }

    public void setXpathTemplate(final XPathOperations xpathTemplate) {
        this.xpathTemplate = xpathTemplate;
    }
}
