<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>

</head>
<body>
<div id="form-story">
    <h2>Create/update story</h2>
    <form:form modelAttribute="newsItem" method="POST" action="/news/">
        <form:hidden path="id"/>
        <table>
            <tr>
                <td>Name:</td>
                <td><form:input path="name"/></td>
                <td><form:errors path="name"/></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><form:input path="description"/></td>
                <td><form:errors path="description"/></td>
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
