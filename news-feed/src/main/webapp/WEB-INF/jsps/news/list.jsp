<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <%
        StringBuilder hostBuilder = new StringBuilder();
        hostBuilder.append("http://").append(request.getServerName());
        hostBuilder.append(":").append(request.getServerPort());
        hostBuilder.append(request.getContextPath());
        String host = hostBuilder.toString();
    %>
    
    <link rel="alternate" type="application/rss+xml" title="Gridshore RSS Feed" href="<%=host%>/news/feed.rss"/>
    <script type="text/javascript">
        $(document).ready(function() {
            $(".tablesorter").tablesorter({widgets: ['zebra','indexFirstColumn']});
        });
    </script>
</head>
<body>
<table class="tablesorter">
    <thead>
    <tr>
        <th>&nbsp;</th>
        <th>author</th>
        <th>title</th>
        <th>create date</th>
        <th>introduction</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${newsItems}" var="newsItem">
        <tr>
            <td><a href="/news/${newsItem.id}">view</a></td>
            <td>${newsItem.metaData.author}</td>
            <td>${newsItem.title}</td>
            <td>${newsItem.metaData.creationDate}</td>
            <td>${newsItem.introduction}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>