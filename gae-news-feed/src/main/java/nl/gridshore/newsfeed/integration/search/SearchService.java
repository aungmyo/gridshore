package nl.gridshore.newsfeed.integration.search;

/**
 * @author Jettro Coenradie
 */
public interface SearchService {
    String searchByAnyOf(String[] keywords);

    String searchByAllOf(String[] keywords);

    String searchBy(String sentence);
}
