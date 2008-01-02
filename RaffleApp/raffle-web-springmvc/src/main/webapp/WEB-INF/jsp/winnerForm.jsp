<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<form action="" id="newWinnerForm">
    <select id="choosenRaffle">
        <option value="">Choose a raffle</option>
        <c:forEach var="availableRaffle" items="${availableRaffles}">
            <option value="${availableRaffle.id}"
                    <c:if test="${raffleId == availableRaffle.id}">selected="true"</c:if> >
                    ${availableRaffle.title}</option>
        </c:forEach>
    </select>

    <div id="choosenPrize">

    </div>
</form>