<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>

</head>
<body>
<a href="/news/form">New item</a><br/>
<table>
    <thead>
        <tr>
            <td>author</td>
            <td>title</td>
            <td>create date</td>
            <td>introduction</td>
        </tr>
    </thead>
    <c:forEach items="${newsItems}" var="newsItem">
        <tr>
            <td>${newsItem.metaData.author}</td>
            <td>${newsItem.title}</td>
            <td>${newsItem.metaData.creationDate}</td>
            <td>${newsItem.introduction}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>