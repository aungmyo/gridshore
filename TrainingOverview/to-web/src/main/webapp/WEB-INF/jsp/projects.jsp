<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Training Overview</title>

    <link rel="stylesheet" href="${ctx}/styles/to.css" type="text/css"/>
    <script type="text/javascript" src="${ctx}/js/jquery-1.2.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $(".zebra tr:nth-child(even)").addClass("striped");
        });
    </script>
</head>

<body>
<div id="screentitle">Training Overview Application</div>
<div id="maincontentbox">
<table id="projectsdata" class="zebra">
    <thead>
        <tr>
            <th>&nbsp;</th>
            <th>name</th>
            <th>client</th>
            <th>manager</th>
            <th>manager email</th>
            <th>wbs</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="project" items="${projectsList}">
            <tr>
                <td><a href="editproject.view?projectId=${project.id}">edit</a></td>
                <td>${project.name}</td>
                <td>${project.client}</td>
                <td>${project.managerName}</td>
                <td>${project.managerEmail}</td>
                <td>${project.wbs}</td>
            </tr>
        </c:forEach>
    </tbody>
</table></div>
</body>

</html>
    