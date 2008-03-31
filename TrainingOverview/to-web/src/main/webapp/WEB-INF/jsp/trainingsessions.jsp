<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<h2>${training.name} - ${training.remark}</h2>
<table id="sessionsdata" class="zebra">
    <thead>
        <tr>
            <th colspan="2"><a href="addtrainingsession.view?trainingId=${training.id}"><fmt:message key="training.session.add"/></a></th>
            <th><fmt:message key="training.session.weeknr"/></th>
            <th><fmt:message key="training.session.year"/></th>
            <th><fmt:message key="training.session.status"/></th>
            <th><fmt:message key="training.session.remark"/></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="session" items="${training.sessions}">
            <tr>
                <td><a href="edittrainingsession.view?trainingsessionId=${session.id}"><fmt:message key="training.session.update"/></a></td>
                <td><a href="#" onclick="fillSessionsDetailsBox(${session.id})"><fmt:message key="training.session.registrants"/></a></td>
                <td>${session.weekNr}</td>
                <td>${session.year}</td>
                <td>${session.status}</td>
                <td>${session.remark}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>