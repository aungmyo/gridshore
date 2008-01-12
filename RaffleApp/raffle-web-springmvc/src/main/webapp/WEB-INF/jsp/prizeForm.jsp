<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2>Raffle : ${prize.raffle.title}</h2>
<form:form modelAttribute="prize">
    <table>
        <tbody class="formentry">
            <tr>
                <th>title</th>
                <td><form:input path="title" size="50" maxlength="50"/></td>
                <td><form:errors path="title" cssClass="errors"/></td>
            </tr>
            <tr>
                <th>description</th>
                <td><form:input path="description" size="50" maxlength="250"/></td>
                <td><form:errors path="description" cssClass="errors"/></td>
            </tr>
            <tr>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${prize.new}">
                            <input type="submit" value="Add Prize"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Update Prize"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>

        </tbody>
    </table>
</form:form>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
