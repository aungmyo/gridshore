package nl.gridshore.samples.training.business.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 10:26:02 PM
 * base exceptions for all exceptions that can occur during the import of the file with employee data
 */
public class ImportEmployeeDataException extends BusinessException {
    public ImportEmployeeDataException(String s) {
        super(s);
    }

    public ImportEmployeeDataException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
