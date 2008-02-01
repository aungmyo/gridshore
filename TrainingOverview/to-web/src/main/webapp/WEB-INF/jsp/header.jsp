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
                    .tablesorter({sortList:[[3,0],[2,0]], widgets: ['zebra']});
            $(".zebra tr:nth-child(even)").addClass("striped");
            $("#employeesBox").hide();
            $('#list').clickMenu();
        });

        function fillEmployeesBox(projectId) {
            $("#employeesBox").load("projectemployees.view",{projectId : projectId}, function() {
                $(".zebra tr:nth-child(even)").addClass("striped");
                $("#employeesBox").show();
            });
        }

    </script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/menu.jsp" %>
<div id="maincontentbox">
