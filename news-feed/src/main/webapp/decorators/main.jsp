<%--
 % This is the main decorator for all SOMECOMPANY INTRANET pages.
 % It includes standard caching, style sheet, header, footer and copyright notice.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
    <title><decorator:title default="News feed"/></title>
    <%
        StringBuilder hostBuilder = new StringBuilder();
        hostBuilder.append("http://").append(request.getServerName());
        hostBuilder.append(":").append(request.getServerPort());
        hostBuilder.append(request.getContextPath());
        String host = hostBuilder.toString();
    %>
    <link rel="alternate" type="application/rss+xml" title="Gridshore RSS Feed" href="<%=host%>/news/feed.rss"/>
    <%@ include file="/includes/style.jsp" %>
    <%@ include file="/includes/script.jsp" %>
    <decorator:head/>
    <style type="text/css">
        body {
            background-color: white;
            margin: 0;
            padding: 0;
        }

        #maincontent {
            position: absolute;
            top: 100px;
            left: 100px;
            width: 90%;
        }

        .kwicks {
            list-style-type: none;
            list-style-position: outside;
            position: relative;
            margin: 0;
            padding: 0;
        }

        .kwicks li {
            display: block;
            padding: 0;
            cursor: pointer;
            float: left;
            width: 125px;
            height: 40px;
            margin-right: 2px;
            background-color: #9999ff;
            text-align: center;
        }

        .kwicks a {
            display: block;
            height: 40px;
            outline: none;
            color: white;
            text-decoration:none;
        }
        #header {
            position: absolute;
            top: 10px;
            left: 100px;
            width: 90%;
        }
    </style>

    <script type="text/javascript">

        var newItem = function (event) {
            window.location = '/news/form';
            event.stopPropagation();
            event.preventDefault();
            return false;
        };

        var allItems = function (event) {
            window.location = '/news/';
            event.stopPropagation();
            event.preventDefault();
            return false;
        };

        $(document)
                .bind('keydown', 'ctrl+i', newItem)
                .bind('keydown', 'ctrl+a', allItems);

    </script>

</head>
<body>
<div id="header">
    <span id="maintitle">News feed</span>
    <ul class="kwicks">
        <li><a href="/news">List all items</a></li>
        <li><span class="newItem"><a href="/news/form">New item</a></span></li>
    </ul>
</div>
<div id="maincontent">
    <decorator:body/>
</div>
</body>
</html>