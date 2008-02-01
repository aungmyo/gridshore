<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2>
    <c:choose>
        <c:when test="${training.new}"><fmt:message key="training.add"/></c:when>
        <c:otherwise><fmt:message key="training.update"/></c:otherwise>
    </c:choose>
</h2>
<form:form modelAttribute="training">
    <table>
        <tbody class="formentry">
            <tr>
                <th><fmt:message key="training.name"/></th>
                <td><form:input path="name" size="50" maxlength="50"/></td>
                <td><form:errors path="name" cssClass="errors"/></td>
            </tr>
            <tr>
                <th><fmt:message key="training.code"/></th>
                <td><form:input path="code" size="50" maxlength="250"/></td>
                <td><form:errors path="code" cssClass="errors"/></td>
            </tr>
            <tr>
                <th><fmt:message key="training.remark"/></th>
                <td><form:input path="remark" size="50" maxlength="250"/></td>
                <td><form:errors path="remark" cssClass="errors" /></td>
            </tr>
            <tr>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${training.new}">
                            <input type="submit" value="<fmt:message key="training.add"/>"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="<fmt:message key="training.update"/>"/>
                        </c:otherwise>
                    </c:choose>
                    &nbsp;
                    <input type="submit" value="<fmt:message key="generic.cancel"/>" name="_cancel"/>
                </td>
            </tr>

        </tbody>
    </table>
</form:form>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
