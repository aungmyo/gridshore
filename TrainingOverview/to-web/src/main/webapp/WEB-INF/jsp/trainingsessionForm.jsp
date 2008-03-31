<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><fmt:message key="generic.application.title"/></title>

    <link rel="stylesheet" href="${ctx}/styles/to.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/styles/clickmenu.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/styles/ui.datepicker.css" type="text/css"/>
    <script type="text/javascript" src="${ctx}/js/jquery-1.2.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.tablesorter.pack.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.clickmenu.pack.js"></script>
    <script type="text/javascript" src="${ctx}/js/ui.datepicker.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
//            $("#weekNr").attachDatepicker();
            $('#list').clickMenu();
        });
    </script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/menu.jsp" %>
<div id="maincontentbox">


<h2>
    <c:choose>
        <c:when test="${trainingSession.new}"><fmt:message key="training.session.add"/></c:when>
        <c:otherwise><fmt:message key="training.session.update"/></c:otherwise>
    </c:choose>
</h2>
<form:form modelAttribute="trainingSession">
    <table>
        <tbody class="formentry">
            <tr>
                <th><fmt:message key="training.session.weeknr"/></th>
                <td><form:input path="weekNr" size="2" maxlength="2"/></td>
                <td><form:errors path="weekNr" cssClass="errors"/></td>
            </tr>
            <tr>
                <th><fmt:message key="training.session.year"/></th>
                <td><form:input path="year" size="4" maxlength="4"/></td>
                <td><form:errors path="year" cssClass="errors"/></td>
            </tr>
            <tr>
                <th><fmt:message key="training.session.status"/></th>
                <td><form:select path="status" items="${sessionStatusses}" itemLabel="label" itemValue="value"/></td>
                <td><form:errors path="status" cssClass="errors" /></td>
            </tr>
            <tr>
                <th><fmt:message key="training.session.remark"/></th>
                <td><form:input path="remark" size="50" maxlength="100"/></td>
                <td><form:errors path="remark" cssClass="errors"/></td>
            </tr>
            <tr>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${trainingSession.new}">
                            <input type="submit" value="<fmt:message key="training.session.add"/>"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="<fmt:message key="training.session.update"/>"/>
                        </c:otherwise>
                    </c:choose>
                    &nbsp;
                    <input type="submit" value="<fmt:message key="generic.cancel"/>" name="_cancel"/>
                </td>
            </tr>

        </tbody>
    </table>
</form:form>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
