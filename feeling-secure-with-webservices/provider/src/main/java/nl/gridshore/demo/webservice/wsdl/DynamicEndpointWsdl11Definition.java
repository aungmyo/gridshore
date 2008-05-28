package nl.gridshore.demo.webservice.wsdl;

import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xpath.AbstractXPathTemplate;
import org.springframework.xml.transform.StringResult;
import org.w3c.dom.Node;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.StringWriter;

public class DynamicEndpointWsdl11Definition implements Wsdl11Definition {

    public Wsdl11Definition delegateWsdl;
    public AbstractXPathTemplate xpathTemplate;
    private Pattern pattern;

    @SuppressWarnings({"unchecked"})
    public Source getSource() {
        Source source = delegateWsdl.getSource();
        List<Node> locations = xpathTemplate.evaluateAsNodeList("//soap:address/@location", source);
        for (Node location : locations) {
            location.setTextContent("");
        }

        return source;
    }

    public void setPattern(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    public void setDelegateWsdl(final Wsdl11Definition delegateWsdl) {
        this.delegateWsdl = delegateWsdl;
    }

    public void setXpathTemplate(final AbstractXPathTemplate xpathTemplate) {
        this.xpathTemplate = xpathTemplate;
    }

}
