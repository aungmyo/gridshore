package nl.gridshore.newsfeed.integration.search.impl;

import nl.gridshore.newsfeed.integration.search.SearchService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author Jettro Coenradie
 */
@Service
public class YahooSearchService implements SearchService {
    private static final String OR = "-";
    private static final String AND = "+";

    @Override
    public String searchByAnyOf(String[] keywords) {
        String keyWordsQuery = doCreateQueryKeyWords(keywords, OR);
        return doSearch(keyWordsQuery);
    }

    @Override
    public String searchByAllOf(String[] keywords) {
        String keyWordsQuery = doCreateQueryKeyWords(keywords, AND);
        return doSearch(keyWordsQuery);
    }

    @Override
    public String searchBy(String sentence) {
        String result;
        if (sentence != null) {
            String[] keywords = sentence.split("\\s");
            result = searchByAnyOf(keywords);
        } else {
            result = "Sorry cannot search for nothing";
        }
        return result;
    }

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
            e.printStackTrace();
            return "";
        }
    }

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
            return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
