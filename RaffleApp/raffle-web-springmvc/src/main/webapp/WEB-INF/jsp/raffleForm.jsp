<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2>
    <c:choose>
        <c:when test="${raffle.new}">
            New Raffle
        </c:when>
        <c:otherwise>
            Update Raffle
        </c:otherwise>
    </c:choose>
</h2>
<form:form modelAttribute="raffle">
    <table>
        <tbody class="formentry">
            <tr>
                <th>title</th>
                <td><form:input path="title" size="50" maxlength="50"/></td>
                <td><form:errors path="title" cssClass="errors"/></td>
            </tr>
            <tr>
                <th>description</th>
                <td><form:input path="description" size="50" maxlength="255"/></td>
                <td><form:errors path="description" cssClass="errors"/></td>
            </tr>
            <tr>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${raffle.new}">
                            <input type="submit" value="Add Raffle"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Update Raffle"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>

        </tbody>
    </table>
</form:form>
<c:if test="${raffle.new == false}">
    <table class="zebra">
        <thead>
            <tr>
                <th>&nbsp;</th>
                <th>name</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td colspan="2"><a href="addparticipant.view?raffleId=${raffle.id}">Add Participant</a></td>
            </tr>
            <c:forEach var="participant" items="${raffle.participants}">
                <tr>
                    <td><a href="deleteparticipant.view?participantId=${participant.id}">delete</a></td>
                    <td>${participant.name}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <table class="zebra">
        <thead>
            <tr>
                <th>&nbsp;</th>
                <th>title</th>
                <th>description</th>
                <th>winner</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td colspan="4"><a href="addprize.view?raffleId=${raffle.id}">Add Prize</a></td>
            </tr>
            <c:forEach var="prize" items="${raffle.prizes}">
                <tr>
                    <td><a href="deleteprize.view?prizeId=${prize.id}">delete</a></td>
                    <td>${prize.title}</td>
                    <td>${prize.description}</td>
                    <td>
                        <c:choose>
                            <c:when test="${prize.winner == null}">
                                <a href="addwinner.view?prizeId=${prize.id}">Choose winner</a>
                            </c:when>
                            <c:otherwise>
                                ${prize.winner.participant.name}
                                <a href="deletewinner.view?prizeId=${prize.id}">remove</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
