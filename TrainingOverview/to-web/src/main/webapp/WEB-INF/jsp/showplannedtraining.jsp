<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<table id="employeedata" class="zebra">
    <tbody>
        <tr>
            <th><fmt:message key="employee.idnumber"/></th><td>${employee.employeeNumber}</td>
        </tr>
        <tr>
            <th><fmt:message key="employee.name"/></th><td>${employee.longName}</td>
        </tr>
        <tr>
            <th><fmt:message key="employee.level"/></th><td>${employee.level}</td>
        </tr>
        <tr>
            <th><fmt:message key="employee.cluster"/></th><td>${employee.cluster}</td>
        </tr>
        <tr>
            <th><fmt:message key="employee.cell"/></th><td>${employee.cell}</td>
        </tr>
    </tbody>
</table>

<table id="trainingplans" class="zebra">
    <thead>
        <tr>
            <th><fmt:message key="training.session.weeknr"/></th>
            <th><fmt:message key="training.session.status"/></th>
            <th><fmt:message key="training.session.remark"/></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="training" items="${trainingList}">
            <tr>
                <th></th>
                <th colspan="2">${training.name}</th>
            </tr>
            <c:forEach var="session" items="${training.sessions}">
                <tr>
                    <td><a href="#" onclick="addTrainingPlan(${employee.id},${session.id})"><fmt:message key="employee.trainingplan.enrol"/></a> </td>
                    <td>${session.weekNr}</td>
                    <td>${session.status}</td>
                    <td>${session.remark}</td>
                </tr>
            </c:forEach>
        </c:forEach>
    </tbody>
</table>
