<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<h2>${trainingSession.training.name} - ${trainingSession.training.remark}</h2>
<h3>${trainingSession.year} (${trainingSession.weekNr})</h3>
<p>${trainingSession.remark} </p>
<table id="sessionsemployeesdata" class="tablesorter">
    <thead>
        <tr>
            <th><fmt:message key="employee.idnumber"/></th>
            <th><fmt:message key="employee.name"/></th>
            <th><fmt:message key="employee.level"/></th>
            <th><fmt:message key="employee.cluster"/></th>
            <th><fmt:message key="employee.cell"/></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="trainingPlanning" items="${trainingPlanningList}">
            <tr>
                <td>${trainingPlanning.employee.employeeNumber}</td>
                <td>${trainingPlanning.employee.longName}</td>
                <td>${trainingPlanning.employee.level}</td>
                <td>${trainingPlanning.employee.cluster}</td>
                <td>${trainingPlanning.employee.cell}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>