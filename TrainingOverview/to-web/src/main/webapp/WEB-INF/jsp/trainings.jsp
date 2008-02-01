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
            $('#list').clickMenu();
            $("#trainingsdata").tablesorter({headers:{0:{sorter:false}},sortList:[[3,0],[2,0]], widgets: ['zebra']});
            $("#detailscontentbox").hide();
        });

        function fillSessionsBox(trainingId) {
            $("#detailscontentbox").load("trainingsessions.view",{trainingId : trainingId}, function() {
                $(".zebra tr:nth-child(even)").addClass("striped");
                $("#detailscontentbox").show();
            });
        }

    </script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/menu.jsp" %>
<div id="maincontentbox">

<table id="trainingsdata" class="tablesorter">
    <thead>
        <tr>
            <th colspan="2"><a href="addtraining.view"><fmt:message key="training.add"/> </a></th>
            <th><fmt:message key="training.name"/></th>
            <th><fmt:message key="training.code"/></th>
            <th><fmt:message key="training.remark"/></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="training" items="${trainingsList}">
            <tr>
                <td><a href="#" onclick="fillSessionsBox(${training.id})"><fmt:message key="training.sessions"/></a></td>
                <td><a href="edittraining.view?trainingId=${training.id}"><fmt:message key="generic.edit"/></a></td>
                <td>${training.name}</td>
                <td>${training.code}</td>
                <td>${training.remark}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</div>

<div id="detailscontentbox">empty</div>
</body>

</html>
