<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="security" uri="http://www.gridshore.nl/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>All news items</title>
</head>
<body>
<div>
    <div>
        <c:if test="${not empty newsItem.imageId}"><img src="/gs/image/${newsItem.imageId}" class="newsfoto"/></c:if>
        <h2>${newsItem.title}</h2>
        <span class="author">by ${newsItem.author.nickName}</span>

        <p class="intro">${newsItem.introduction}</p>

        <p>${newsItem.item}</p>
    </div>
</div>

</body>
</html>