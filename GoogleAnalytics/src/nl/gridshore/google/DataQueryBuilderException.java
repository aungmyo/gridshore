package nl.gridshore.google;

/**
 * RuntimeException used to give exceptions back to the client of the DataQueryBuilder class
 *
 * @author Jettro Coenradie
 */
public class DataQueryBuilderException extends RuntimeException {
    public DataQueryBuilderException(String s) {
        super(s);
    }

    public DataQueryBuilderException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
