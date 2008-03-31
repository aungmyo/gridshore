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
            $("#projectsdata")
                    .tablesorter({headers:{0:{sorter:false},1:{sorter:false}},sortList:[[3,0],[2,0]], widgets: ['zebra']});
            $(".zebra tr:nth-child(even)").addClass("striped");
            $("#detailscontentbox").hide();
            $('#list').clickMenu();
        });

        function fillEmployeesBox(projectId) {
            $("#detailscontentbox").load("projectemployees.view",{projectId : projectId}, function() {
                $("#employeesdata").tablesorter({headers:{0:{sorter:false}},sortList:[[1,0]], widgets: ['zebra']});
                $("#detailscontentbox").show();
            });
        }

    </script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/menu.jsp" %>
<div id="maincontentbox">
<table id="projectsdata" class="tablesorter">
    <thead>
        <tr>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
            <th><fmt:message key="project.name"/></th>
            <th><fmt:message key="project.client"/></th>
            <th><fmt:message key="project.manager.name"/></th>
            <th><fmt:message key="project.manager.email"/></th>
            <th><fmt:message key="project.wbs"/></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="training" items="${projectsList}">
            <tr>
                <td><a href="#" onclick="fillEmployeesBox(${training.id})"><fmt:message key="project.employees"/></a></td>
                <td><a href="editproject.view?projectId=${training.id}"><fmt:message key="generic.edit"/></a></td>
                <td>${training.name}</td>
                <td>${training.client}</td>
                <td>${training.managerName}</td>
                <td>${training.managerEmail}</td>
                <td>${training.wbs}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>

<div id="detailscontentbox">empty</div>
</body>

</html>
    