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
        <th>author</th>
        <th>title</th>
        <th>create date</th>
        <th>introduction</th>
        <th>&nbsp;</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${newsItems}" var="newsItem">
        <tr>
            <td>${newsItem.author}</td>
            <td>${newsItem.title}</td>
            <td>${newsItem.introduction}</td>
            <td>${newsItem.item}</td>
            <td><security:canEdit newsItem="${newsItem}"><a href="/spring/news/form/${newsItem.id}">edit</a></security:canEdit>
                <security:canDelete newsItem="${newsItem}"> - <a href="/spring/news/delete/${newsItem.id}">delete</a></security:canDelete>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>