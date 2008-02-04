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

<h2><a href="#" onclick="showTrainingPlan(${employee.id})"><fmt:message key="employee.trainingplan.add"/></a></h2>
<table id="trainingplans" class="zebra">
    <thead>
        <tr>
            <th><fmt:message key="training.name"/> </th>
            <th><fmt:message key="training.session.weeknr"/></th>
            <th><fmt:message key="training.session.status"/></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="plan" items="${employee.trainingPlans}">
            <tr>
                <td>${plan.session.training.name}</td>
                <td>${plan.session.weekNr}</td>
                <td>${plan.session.status}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<h2><a href="#" onclick="showTrainingWishes(${employee.id})"><fmt:message key="employee.trainingwishes.addwish"/></a></h2>
<table id="trainingwishes" class="zebra">
    <thead>
        <tr>
            <th><fmt:message key="training.name"/> </th>
            <th><fmt:message key="training.code"/></th>
            <th><fmt:message key="training.remark"/></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="wish" items="${employee.trainingWishes}">
            <tr>
                <td>${wish.training.name}</td>
                <td>${wish.training.code}</td>
                <td>${wish.training.remark}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
