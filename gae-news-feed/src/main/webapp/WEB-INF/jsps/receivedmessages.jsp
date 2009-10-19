<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="security" uri="http://www.gridshore.nl/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>All news items</title>
</head>
<body>
<table class="tablesorter">
    <thead>
    <tr>
        <th>sender</th>
        <th>content</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${receivedMessages}" var="receivedMessage">
        <tr>
            <td>${receivedMessage.sender}</td>
            <td>${receivedMessage.content}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>