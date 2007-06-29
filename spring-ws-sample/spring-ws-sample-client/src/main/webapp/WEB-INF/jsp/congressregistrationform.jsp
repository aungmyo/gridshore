<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <title><spring:message code="congress.registration.title"/></title>
        <link type="text/css" href="${ctx}/style/cr.css" rel="stylesheet"/>
    </head>
    <body>
        <form:form commandName="CongressRegistration">
    	<form:errors path="*" cssClass="error"/>
        	<table>
        		<tr>
        			<td><spring:message code="congress.id"/></td>
        			<td><form:input path="congressId"/></td>
        		</tr>
        		<tr>
        			<td><spring:message code="registrant.firstname"/></td>
        			<td><form:input path="registrant.firstname"/></td>
        		</tr>
        		<tr>
        			<td><spring:message code="registrant.middlename"/></td>
        			<td><form:input path="registrant.middlename"/></td>
        		</tr>
        		<tr>
        			<td><spring:message code="registrant.lastname"/></td>
        			<td><form:input path="registrant.lastname"/></td>
        		</tr>
        		<tr>
        			<td><spring:message code="registrant.emailAddress"/></td>
        			<td><form:input path="registrant.emailAddress"/></td>
        		</tr>
        		<tr>
        			<td><spring:message code="congress.sessions"/></td>
        			<td> 
        				Session 1<form:checkbox path="sessionIds" value="7"/><br/>
        				Session 2<form:checkbox path="sessionIds" value="8"/><br/>
        				Session 3<form:checkbox path="sessionIds" value="9"/><br/>
        			</td>
        		</tr>
        	</table>
        	
        	<input type="submit" name="<spring:message text="generic.create"/>">
        </form:form>
    </body>
</html>
