<%--
 % This is the main decorator for all pages.
 % It includes standard caching, style sheet, header, footer and copyright notice.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="security" uri="http://www.gridshore.nl/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
    <title><decorator:title default="News feed"/></title>
    <link type="text/css" href="/css/custom-theme/jquery-ui-1.7.2.custom.css" rel="stylesheet"/>
    <link type="text/css" href="/css/table/default.css" rel="stylesheet"/>
    <link type="text/css" href="/css/main.css" rel="stylesheet"/>

    <script type="text/javascript" src="/js/jquery-1.3.2.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.7.2.custom.min.js"></script>
    <script type="text/javascript" src="/js/jquery.tablesorter.min.js"></script>
    <script type="text/javascript" src="/js/jquery.bgiframe.js"></script>
    <script type="text/javascript" src="/js/jquery.dimensions.js"></script>
    <script type="text/javascript" src="/js/jquery.positionBy.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $(".tablesorter").tablesorter({widgets: ['zebra','indexFirstColumn']});
        });
    </script>
    <decorator:head/>

</head>
<body>
<div id="main">
    <div id="navigation">
        <span><a href="/gs/home">Home</a></span>
        <span><a href="/gs/news">All news</a></span>
        <span><a href="/gs/news/form">Create news</a></span>
        <span><a href="/gs/messages">Received messages</a></span>
        <span class="right"><a href="/gs/contact/form">Contact</a></span>
        <span class="right"><a href="/technology.html">Technology</a></span>
        <span class="right"><a href="/about.html">About</a></span>
        <span class="right">&nbsp;:&nbsp;</span>
        <span class="right"><security:loginUrl destination="/gs/home"/></span>
    </div>
    <div id="maincontent">
        <decorator:body/>
    </div>
</div>
<script type="text/javascript">
    var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
    document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
    try {
        var pageTracker = _gat._getTracker("UA-180286-10");
        pageTracker._trackPageview();
    } catch(err) {
    }</script>
</body>
</html>