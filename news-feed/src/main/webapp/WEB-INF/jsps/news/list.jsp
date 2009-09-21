<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>

</head>
<body>
<ul id="sortable">
    <c:forEach items="${newsItems}" var="newsItem">
        <li id="newsItem_${newsItem.id}">${newsItem.metaData.author}</li>
    </c:forEach>
</ul>

</body>
</html>