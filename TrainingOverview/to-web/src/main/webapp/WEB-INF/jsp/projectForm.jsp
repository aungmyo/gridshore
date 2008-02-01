<%@ include file="/WEB-INF/jsp/header.jsp" %>
<h2><fmt:message key="project.update"/></h2>
<form:form modelAttribute="project">
    <table>
        <tbody class="formentry">
            <tr>
                <th><fmt:message key="project.client"/> - <fmt:message key="project.name"/></th>
                <td>${project.name} - ${project.client}</td>
                <td><form:hidden path="name"/><form:hidden path="client"/></td>
            </tr>
            <tr>
                <th><fmt:message key="project.manager.name"/></th>
                <td><form:input path="managerName" size="50" maxlength="50"/></td>
                <td><form:errors path="managerName" cssClass="errors"/></td>
            </tr>
            <tr>
                <th><fmt:message key="project.manager.email"/></th>
                <td><form:input path="managerEmail" size="50" maxlength="50"/></td>
                <td><form:errors path="managerEmail" cssClass="errors"/></td>
            </tr>
            <tr>
                <th><fmt:message key="project.wbs"/></th>
                <td><form:input path="wbs" size="50" maxlength="255"/></td>
                <td><form:errors path="wbs" cssClass="errors"/></td>
            </tr>
            <tr>
                <td colspan="3">
                    <input type="submit" value="<fmt:message key="project.update"/>"/> &nbsp;
                    <input type="submit" value="<fmt:message key="generic.cancel"/>" name="_cancel"/>
                </td>
            </tr>
        </tbody>
    </table>
</form:form>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>