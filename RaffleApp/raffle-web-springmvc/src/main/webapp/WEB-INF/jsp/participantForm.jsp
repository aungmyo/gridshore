<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2>Raffle : ${participant.raffle.title}</h2>
<form:form modelAttribute="participant">
    <table>
        <tbody class="formentry">
            <tr>
                <th>title</th>
                <td><form:input path="name" size="50" maxlength="50"/></td>
                <td><form:errors path="name" cssClass="errors"/></td>
            </tr>
            <tr>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${participant.new}">
                            <input type="submit" value="Add Participant"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Update Participant"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>

        </tbody>
    </table>
</form:form>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
