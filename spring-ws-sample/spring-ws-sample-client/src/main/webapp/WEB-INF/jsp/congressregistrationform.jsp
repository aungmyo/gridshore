<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <title>Voeg event toe</title>
        <link type="text/css" href="${ctx}/style/belastingdienst.css" rel="stylesheet"/>
    </head>
    <body>
        <form:form commandName="CongressRegistration">
        	congressId <form:input path="congressId"/><br/>
        	firstname <form:input path="registrant.firstname"/><br/>
        	middlename <form:input path="registrant.middlename"/><br/>
        	lastname <form:input path="registrant.lastname"/><br/>
        	emailAddress <form:input path="registrant.emailAddress"/><br/>
        	sessionIds<br/>
        	sessie 1 <form:checkbox path="sessionIds" value="1"/><br/>
        	sessie 2 <form:checkbox path="sessionIds" value="2"/><br/>
        	sessie 3 <form:checkbox path="sessionIds" value="3"/><br/>
        	
        	<input type="submit" name="Create">
        </form:form>
    </body>
</html>
