<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Contact form</title>
</head>
<body>
<h2>Contact us</h2>
<div id="form-contact">
    <form:form modelAttribute="contactForm" method="POST" action="/gs/contact/form">
        <table>
            <tr>
                <td>Name:</td>
                <td><form:input path="name"/></td>
                <td><form:errors path="name"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><form:input path="email"/></td>
                <td><form:errors path="email"/></td>
            </tr>
            <tr>
                <td>Title:</td>
                <td><form:input path="title"/></td>
                <td><form:errors path="title"/></td>
            </tr>
            <tr>
                <td>Introduction:</td>
                <td><form:textarea path="message" cols="100" rows="10"/></td>
                <td><form:errors path="message"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Send message"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
