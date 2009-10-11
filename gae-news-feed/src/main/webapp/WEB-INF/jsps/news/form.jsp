<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Create new item</title>
</head>
<body>
<div id="form-story">
    <h2>Create/update story</h2>
    <form:form modelAttribute="newsItem" method="POST" action="/spring/news">
        <form:hidden path="id"/>
        <table>
            <tr>
                <td>Name:</td>
                <td><form:input path="author"/></td>
                <td><form:errors path="author"/></td>
            </tr>
            <tr>
                <td>Title:</td>
                <td><form:input path="title" size="50"/></td>
                <td><form:errors path="title"/></td>
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
                <td colspan="2">
                    <input type="submit" value="Save Changes"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
