<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<table id="prizedata" class="zebra">
    <thead>
        <tr>
            <th>&nbsp;</th>
            <th>title</th>
            <th>description</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="prize" items="${raffle.prizes}">
            <c:if test="${prize.winner == null}">
                <tr>
                    <td><a href="pickwinner.view?prizeId=${prize.id}">pick winner</a></td>
                    <td>${prize.title}</td>
                    <td>${prize.description}</td>
                </tr>
            </c:if>
        </c:forEach>
    </tbody>
</table>
