<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<table id="raffledata" class="zebra">
    <thead>
        <tr>
            <th>&nbsp;</th>
            <th>title</th>
            <th>description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td colspan="3"><a href="addraffle.view">Add Raffle</a></td>
        </tr>
        <c:forEach var="raffle" items="${raffleList}">
            <tr>
                <td><a href="editraffle.view?raffleId=${raffle.id}">edit</a></td>
                <td>${raffle.title}</td>
                <td>${raffle.description}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
