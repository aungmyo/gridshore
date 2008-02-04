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

<table id="trainingwishes" class="zebra">
    <thead>
        <tr>
            <th>&nbsp;</th>
            <th><fmt:message key="training.name"/></th>
            <th><fmt:message key="training.code"/></th>
            <th><fmt:message key="training.remark"/></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="training" items="${trainingList}">
            <tr>
                <td><a href="#" onclick="addTrainingWish(${employee.id},${training.id})"><fmt:message key="employee.trainingwishes.add"/></a> </td>
                <td>${training.name}</td>
                <td>${training.code}</td>
                <td>${training.remark}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>