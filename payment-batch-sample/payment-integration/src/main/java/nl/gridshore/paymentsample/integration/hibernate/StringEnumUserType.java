package nl.gridshore.paymentsample.integration.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.EnhancedUserType;
import org.hibernate.usertype.ParameterizedType;
import static org.hibernate.util.ReflectHelper.*;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 26-aug-2007
 * Time: 9:29:53
 * Special Hibernate UserType used to persist String enums
 */
public class StringEnumUserType implements EnhancedUserType, ParameterizedType {
    private Class<Enum> enumClass;
    
    public void setParameterValues(Properties parameters) {
        String enumClassName = parameters.getProperty("enumClassName");
        try {
            //noinspection unchecked
            enumClass = classForName(enumClassName);
        } catch (ClassNotFoundException e) {
            throw new HibernateException("Enum class is not found",e);
        }
    }

    public Class returnedClass() {
        return enumClass;
    }

    public int[] sqlTypes() {
        return new int[]{Hibernate.STRING.sqlType()};
    }

    public String objectToSQLString(Object value) {
        return '\'' + ( (Enum) value).name();
    }

    public String toXMLString(Object value) {
        return ((Enum)value).name();
    }

    public Object fromXMLString(String xmlValue) {
        return Enum.valueOf(enumClass,xmlValue);
    }

    public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner) throws HibernateException, SQLException {
        String name = resultSet.getString(names[0]);
        return resultSet.wasNull() ? null : Enum.valueOf(enumClass,name);
    }

    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index) throws HibernateException, SQLException {
        if (value == null) {
            preparedStatement.setNull(index, Hibernate.STRING.sqlType());
        } else {
            preparedStatement.setString(index, ((Enum)value).name());
        }
    }

    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y || !(x == null || y == null) && x.equals(y);
    }

    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    public Object deepCopy(Object x) throws HibernateException {
        return x;
    }

    public boolean isMutable() {
        return false;
    }

    public Serializable disassemble(Object x) throws HibernateException {
        return (Serializable)x;
    }

    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

}
