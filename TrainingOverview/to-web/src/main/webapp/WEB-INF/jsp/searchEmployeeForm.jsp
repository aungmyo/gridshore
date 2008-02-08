<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><fmt:message key="generic.application.title"/></title>

    <link rel="stylesheet" href="${ctx}/styles/to.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/styles/clickmenu.css" type="text/css"/>
    <script type="text/javascript" src="${ctx}/js/jquery-1.2.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.tablesorter.pack.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.clickmenu.pack.js"></script>
    <script type="text/javascript" src="${ctx}/js/employees.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#list').clickMenu();
            $("#employeesdata").tablesorter({headers:{0:{sorter:false}},sortList:[[2,0]], widgets: ['zebra']});

        });

    </script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/menu.jsp" %>
<div id="maincontentbox">
<form:form modelAttribute="employee">
<table id="employeedata">
    <tbody>
        <tr>
            <th><fmt:message key="employee.idnumber"/></th>
            <td><form:input path="employeeNumber"/></td>
        </tr>
        <tr>
            <th><fmt:message key="employee.name"/></th>
            <td><form:input path="longName"/></td>
        </tr>
        <tr>
            <th><fmt:message key="employee.cell"/></th>
            <td><form:input path="cell"/></td>
        </tr>
        <tr>
            <th><fmt:message key="employee.cluster"/></th>
            <td><form:input path="cluster"/></td>
        </tr>
        <tr>
            <th><fmt:message key="employee.level"/></th>
            <td><form:input path="level"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="<fmt:message key="generic.search"/>"/></td>
        </tr>
    </tbody>
</table>
</form:form>
</div>

<div id="detailscontentbox">
    <table id="employeesdata" class="tablesorter">
        <thead>
            <tr>
                <th>&nbsp;</th>
                <th><fmt:message key="employee.idnumber"/></th>
                <th><fmt:message key="employee.name"/></th>
                <th><fmt:message key="employee.level"/></th>
                <th><fmt:message key="employee.cluster"/></th>
                <th><fmt:message key="employee.cell"/></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="employee" items="${employeesList}">
                <tr>
                    <td><a href="#" onclick="fillEmployeeBox(${employee.id})">details</a></td>
                    <td>${employee.employeeNumber}</td>
                    <td>${employee.longName}</td>
                    <td>${employee.level}</td>
                    <td>${employee.cluster}</td>
                    <td>${employee.cell}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>

</html>
