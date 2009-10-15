<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Create new item</title>
</head>
<body>
<div id="form-story">
    <h2>Create/update story</h2>
    <form:form modelAttribute="newsItem" method="POST" action="/gs/news" enctype="multipart/form-data" >
        <form:hidden path="id"/>
        <table>
            <tr>
                <td>Name:</td>
                <td><form:input path="nickName" readonly="true"/></td>
                <td><form:errors path="nickName"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><form:input path="email" readonly="true"/></td>
                <td><form:errors path="email"/></td>
            </tr>
            <tr>
                <td>userId:</td>
                <td><form:input path="userId" readonly="true"/></td>
                <td><form:errors path="userId"/></td>
            </tr>
            <tr>
                <td>Title:</td>
                <td><form:input path="title" size="50"/></td>
                <td><form:errors path="title"/></td>
            </tr>
            <tr>
                <td>Image:</td>
                <td><input id="image" name="image" type="file"/> </td>
                <td><form:errors path="image"/></td>
            </tr>
            <tr>
                <td>Introduction:</td>
                <td><form:textarea path="introduction" cols="100" rows="3"/></td>
                <td><form:errors path="introduction"/></td>
            </tr>
            <tr>
                <td>text:</td>
                <td><form:textarea path="item" cols="100" rows="10"/></td>
                <td><form:errors path="item"/></td>
            </tr>
            <tr>
                <td colspan="3">
                    <input type="submit" value="Save Changes" tabindex="0"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
