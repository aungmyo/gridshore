<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<table>
    <thead>
        <tr>
            <th>&nbsp;</th>
            <th>title</th>
            <th>description</th>
        </tr>
    </thead>
    <tbody class="data">
        <c:forEach var="raffle" items="${raffleList}">
            <tr>
                <td><a href="raffe.view?raffleId=${raffle.id}">edit</a></td>
                <td>${raffle.title}</td>
                <td>${raffle.description}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
