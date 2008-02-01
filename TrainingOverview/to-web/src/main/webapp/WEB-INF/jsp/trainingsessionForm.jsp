<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2>
    <c:choose>
        <c:when test="${trainingSession.new}"><fmt:message key="training.session.add"/></c:when>
        <c:otherwise><fmt:message key="training.session.update"/></c:otherwise>
    </c:choose>
</h2>
<form:form modelAttribute="trainingSession">
    <table>
        <tbody class="formentry">
            <tr>
                <th><fmt:message key="training.session.weeknr"/></th>
                <td><form:input path="weekNr" size="2" maxlength="2"/></td>
                <td><form:errors path="weekNr" cssClass="errors"/></td>
            </tr>
            <tr>
                <th><fmt:message key="training.session.status"/></th>
                <td><form:select path="status" items="${sessionStatusses}"/></td>
                <td><form:errors path="status" cssClass="errors" /></td>
            </tr>
            <tr>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${trainingSession.new}">
                            <input type="submit" value="<fmt:message key="training.session.add"/>"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="<fmt:message key="training.session.update"/>"/>
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
