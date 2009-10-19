<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<p>Welcome <c:out value="${currentUser}"/>to <strong>Doing Java on Google App Engine</strong>. This site is a demonstration
    of some things you can do with google app engine. You can do the following with this page:</p>
<ul>
    <li><a href="/gs/news">Watch the news</a>, read the news items that are stored in the data store.</li>
    <li>Check <a href="/gs/messages">messages received</a> by the application through email and google talk.</li>
    <li><a href="/gs/news/form">Create news</a> (only if your are logged in using a google account).</li>
    <li>Edit news that you have written (only if you are logged in of course).</li>
    <li>Delete news (only if you are an administrator)</li>
</ul>

<p>There are some other features like using the contact form, sending email to the application and using google talk to
talk to the application.</p>

<p>If you want to learn more about the used technology, visit the <a href="/technology.html">technology</a> page.</p>

<p>If you have questions, please use the <a href="/gs/contact/form">contact form</a>.</p>
</body>
</html>