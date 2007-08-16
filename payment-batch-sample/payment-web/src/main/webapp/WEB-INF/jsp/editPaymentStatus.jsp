<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: Jettro.Coenradie
  Date: 16-aug-2007
  Time: 16:39:34
  To change this template use File | Settings | File Templates.
--%>
<form:form commandName="command">
    <form:errors path="*"/>
    <table>
        <tr>
            <td>id</td>
            <td><c:out value="${command.id}"/></td>
        </tr>
        <tr>
            <td>amount</td>
            <td><c:out value="${command.amount}"/></td>
        </tr>
        <tr>
            <td>type</td>
            <td><c:out value="${command.type}"/></td>
        </tr>
        <tr>
            <td>status</td>
            <td><form:input path="status"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="submit"/></td>
        </tr>
    </table>
</form:form>