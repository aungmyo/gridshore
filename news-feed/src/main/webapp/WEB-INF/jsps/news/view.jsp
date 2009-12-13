<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Detail of news item</title>
    <%
        StringBuilder hostBuilder = new StringBuilder();
        hostBuilder.append("http://").append(request.getServerName());
        hostBuilder.append(":").append(request.getServerPort());
        hostBuilder.append(request.getContextPath());
        String host = hostBuilder.toString();
    %>

    <link rel="alternate" type="application/rss+xml" title="Gridshore RSS Feed" href="<%=host%>/news/${newsItem.id}/feed.rss"/>

    <script type="text/javascript">
        $(document).ready(function() {
            $(".tablesorter").tablesorter({widgets: ['zebra','indexFirstColumn']});
        });
    </script>
</head>
<body>
<div id="form-story">
    <h2>${newsItem.title}</h2>
    <p><em>${newsItem.introduction}</em></p>
    <div>${newsItem.item}</div>
    <table class="tablesorter">
        <thead>
        <tr>
            <th>commenter</th>
            <th>comment</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${newsItem.comments}" var="newsComment">
            <tr>
                <td>${newsComment.commenter}</td>
                <td>${newsComment.content}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form:form modelAttribute="comment" method="POST" action="/news/${newsItem.id}/comment">
        <form:hidden path="newsItemId"/>
        <form:hidden path="commentId"/>
        <table>
            <tr>
                <td>Commenter:</td>
                <td><form:input path="commenter"  size="50"/></td>
                <td><form:errors path="commenter"/></td>
            </tr>
            <tr>
                <td>Content:</td>
                <td><form:input path="content" size="100"/></td>
                <td><form:errors path="content"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Add Comment"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
