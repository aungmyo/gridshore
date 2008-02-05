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
    <script type="text/javascript">
        $(document).ready(function() {
            $("#employeesdata")
                    .tablesorter({headers:{0:{sorter:false}},sortList:[[2,0]], widgets: ['zebra']});
            $(".zebra tr:nth-child(even)").addClass("striped");
            $("#detailscontentbox").hide();
            $('#list').clickMenu();
        });

        function fillEmployeeBox(employeeId) {
            $("#detailscontentbox").load("employee.view",{employeeId : employeeId}, function() {
                $(".zebra tr:nth-child(even)").addClass("striped");
                $("#detailscontentbox").show();
            });
        }

        function showTrainingPlan(employeeId) {
            $("#detailscontentbox").load("showplannedtraining.view",{employeeId : employeeId}, function() {
                $(".zebra tr:nth-child(even)").addClass("striped");
                $("#detailscontentbox").show();
            });
        }

        function addTrainingPlan(employeeId,trainingsessionId) {
            $("#detailscontentbox").load("addplannedtraining.view",
                                        {employeeId : employeeId, trainingsessionId : trainingsessionId},
                                        function() {
                $(".zebra tr:nth-child(even)").addClass("striped");
                $("#detailscontentbox").show();
            });
        }

        function showTrainingWishes(employeeId) {
            $("#detailscontentbox").load("showwishedtraining.view",{employeeId : employeeId}, function() {
                $(".zebra tr:nth-child(even)").addClass("striped");
                $("#detailscontentbox").show();
            });
        }

        function addTrainingWish(employeeId,trainingId) {
            $("#detailscontentbox").load("addwishedtraining.view",
                                        {employeeId : employeeId, trainingId : trainingId},
                                        function() {
                $(".zebra tr:nth-child(even)").addClass("striped");
                $("#detailscontentbox").show();
            });
        }

    </script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/menu.jsp" %>
<div id="maincontentbox">
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

<div id="detailscontentbox">empty</div>
</body>

</html>
