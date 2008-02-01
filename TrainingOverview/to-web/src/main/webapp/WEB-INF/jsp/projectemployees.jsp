<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<h2>${project.client} - ${project.name}</h2>
<table id="employeesdata" class="zebra">
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
        <c:forEach var="employee" items="${project.employees}">
            <tr>
                <td>${employee.employeeNumber}</td>
                <td>${employee.longName}</td>
                <td>${employee.level}</td>
                <td>${employee.cluster}</td>
                <td>${employee.cell}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>