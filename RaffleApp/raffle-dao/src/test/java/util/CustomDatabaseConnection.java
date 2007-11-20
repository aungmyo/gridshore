package util;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;

import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 18, 2007
 * Time: 10:57:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class CustomDatabaseConnection extends DatabaseConnection {
    public CustomDatabaseConnection(Connection connection, String schema) {
        super(connection, schema);
    }

    public CustomDatabaseConnection(Connection connection) {
        super(connection);
        this.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
    }
}
