package nl.gridshore.newsfeed.integration.search;

/**
 * <p>Service that queries the web (using a specialised service) using the provided keywords.</p>
 * <p/>
 * <p>You can perform an AND search as well as an OR search. Both AND and OR are specified
 * using the {@see}</p>
 *
 * @author Jettro Coenradie
 */
public interface SearchService {
    static final String AND = "+";
    static final String OR = "-";

    /**
     * Use the the provided keywords to find a result with any or more of the provided keywords.
     *
     * @param keywords String array containing the keywords to search for
     * @return String containing the result of the search
     */
    String searchByAnyOf(String[] keywords);

    /**
     * Use the the provided keywords to find a result with all of the provided keywords.
     *
     * @param keywords String array containing the keywords to search for
     * @return String containing the result of the search
     */
    String searchByAllOf(String[] keywords);

    /**
     * Use the provided sentence to search for results, uses the any of implementation.
     *
     * @param sentence String containing the sentence to search with
     * @return String containing the result of the search
     */
    String searchBy(String sentence);
}
