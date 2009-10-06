<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>author</th>
        <th>title</th>
        <th>create date</th>
        <th>introduction</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${newsItems}" var="newsItem">
        <tr>
            <td>${newsItem.author}</td>
            <td>${newsItem.title}</td>
            <td>${newsItem.introduction}</td>
            <td>${newsItem.item}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>