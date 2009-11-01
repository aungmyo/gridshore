package nl.gridshore.newsfeed.integration.search.impl;

import nl.gridshore.newsfeed.integration.search.SearchService;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>Search service that makes use of yahoo to crawl the web. The different methods are used
 * to create a search string that is send to the yahoo boss search api.</p>
 * <p> see http://boss.yahooapis.com for more information.</p>
 *
 * @author Jettro Coenradie
 */
@Service
public class YahooSearchService implements SearchService {
    private static final Logger log = Logger.getLogger(YahooSearchService.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public String searchByAnyOf(String[] keywords) {
        String keyWordsQuery = doCreateQueryKeyWords(keywords, OR);
        return doSearch(keyWordsQuery);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String searchByAllOf(String[] keywords) {
        String keyWordsQuery = doCreateQueryKeyWords(keywords, AND);
        return doSearch(keyWordsQuery);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String searchBy(String sentence) {
        String result;
        if (sentence != null) {
            String[] keywords = sentence.split("\\s");
            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("The following key words ")
                    .append(sentence)
                    .append(" resulted in the yahoo results : \r\n\r\n\r\n")
                    .append(searchByAnyOf(keywords));
            result = resultBuilder.toString();
        } else {
            result = "Sorry cannot search for nothing";
        }
        log.debug(result);

        return result;
    }

    /**
     * Create a search query from the provided key words and the AND/OR operator.
     *
     * @param keywords String array containing the keywords
     * @param andOr    String containing + (AND) - (OR)
     * @return A string containing the created query.
     */
    private String doCreateQueryKeyWords(String[] keywords, String andOr) {
        StringBuilder queryKeywords = new StringBuilder();
        if (keywords.length > 0) {
            queryKeywords.append(encode(keywords[0]));
        }
        for (int i = 1; i < keywords.length; i++) {
            queryKeywords.append(andOr).append(encode(keywords[i]));
        }
        return queryKeywords.toString();
    }

    private String encode(String keyword) {
        try {
            return URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("The used encoding message is not supported : UTF - 8", e);
            return "";
        }
    }

    /**
     * Returns a string containing all the abstracts of search results as provided by yahoo in response
     * to the provided query string.
     *
     * @param query String containing the keywords to search for
     * @return String containing the complete result (abstracts) of a yahoo search
     */
    private String doSearch(String query) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("http://boss.yahooapis.com/ysearch/web/v1/")
                .append(query)
                .append("?appid=wGsFV_DV34EwXnC.2Bt_Ql8Kcir_HmrxMzWUF2fv64CA8ha7e4zgudqXFA8K_J4-&format=xml&filter=-porn");
        try {
            URL url = new URL(queryBuilder.toString());
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return safeParseXml(buffer.toString());
        } catch (MalformedURLException e) {
            log.error("The used url is not right : " + queryBuilder.toString(), e);
            return "The used url is not right.";
        } catch (IOException e) {
            log.error("Problem obtaining search results, connection maybe?", e);
            return "Problem obtaining search results, connection maybe?";
        }
    }

    /**
     * <p>Used to prevent the caller from having to call exceptions.</p>
     *
     * @param xml String containing an xml to be parsed.
     * @return String containg the non xml and html content or an empty string if there is a problem.
     */
    private String safeParseXml(String xml) {
        String result = "";
        try {
            result = parseXml(xml);
        } catch (JDOMException e) {
            log.error("problem in the dom tree that cannot be traversed by jdom", e);
        } catch (IOException e) {
            log.error("Problem reading the provided string containing an xml document", e);
        }

        return result;
    }


    /**
     * <p>Parses the xml as provided. The abstract is used for the return query and the bold html
     * tags removed to make a non html return result</p>
     *
     * @param xml String containing an xml to be parsed.
     * @return String containing a text without tags (xml and html)
     * @throws JDOMException Thrown if the dom tree cannot be parsed.
     * @throws IOException   Thrown if there are problems in the string.
     */
    private String parseXml(String xml) throws JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        Element rootElement = document.getRootElement();
        Namespace ns = Namespace.getNamespace("http://www.inktomi.com/");
        Element resultSetWeb = rootElement.getChild("resultset_web", ns);
        List resultElements = resultSetWeb.getChildren();

        StringBuilder resultBuilder = new StringBuilder();
        for (Object result : resultElements) {
            Element resultElement = (Element) result;
            String abstractFoundArticle = resultElement.getChildText("abstract", ns);
            String strippedAbstract = abstractFoundArticle.replaceAll("<b>", "").replaceAll("</b>", "");
            resultBuilder.append(strippedAbstract).append("\r\n \r\n");
        }
        return resultBuilder.toString();
    }

}
