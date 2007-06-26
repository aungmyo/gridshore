<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <title>Voeg event toe</title>
        <link type="text/css" href="${ctx}/style/belastingdienst.css" rel="stylesheet"/>
    </head>
    <body>
        <form:form commandName="Event">
        	<table>
        	<tr>
        	<td>
            <fieldset>
            	<legend>Aanvrager</legend>
                <table>
                    <tr>
                        <td>Sofi nummer</td>
                        <td><form:input path="requestorSofiNumber"/></td>
                    </tr>
                    <tr>
                        <td>Voornaam</td>
                        <td><form:input path="requestorFirstname"/></td>
                    </tr>
                    <tr>
                        <td>Achternaam</td>
                        <td><form:input path="requestorLastname"/></td>
                    </tr>
                    <tr>
                        <td>geboortedatum (yyyymmdd)</td>
                        <td><form:input path="requestorDateOfBirth"/></td>
                    </tr>
                    <tr>
                        <td>Inkomen</td>
                        <td><form:input path="requestorIncome"/></td>
                    </tr>
                    <tr>
                        <td>Straat nummer</td>
                        <td><form:input path="requestorAddressNumber"/></td>
                    </tr>
                    <tr>
                        <td>Straat</td>
                        <td><form:input path="requestorAddressStreet"/></td>
                    </tr>
                    <tr>
                        <td>Plaats</td>
                        <td><form:input path="requestorAddressCity"/></td>
                    </tr>
                    <tr>
                        <td>Postcode</td>
                        <td><form:input path="requestorAddressPostalCode"/></td>
                    </tr>
                    <tr>
                        <td>Land</td>
                        <td><form:input path="requestorAddressCountry"/></td>
                    </tr>
                </table>
            </fieldset>
            </td>
            <td valign="top">
            <fieldset>
                <legend>Partner</legend>
                <table>
                    <tr>
                        <td>Sofi nummer</td>
                        <td><form:input path="partnerSofiNumber"/></td>
                    </tr>
                    <tr>
                        <td>Voornaam</td>
                        <td><form:input path="partnerFirstname"/></td>
                    </tr>
                    <tr>
                        <td>Achternaam</td>
                        <td><form:input path="partnerLastname"/></td>
                    </tr>
                    <tr>
                        <td>geboortedatum (yyyymmdd)</td>
                        <td><form:input path="partnerDateOfBirth"/></td>
                    </tr>
                    <tr>
                        <td>Inkomen</td>
                        <td><form:input path="requestorIncome"/></td>
                    </tr>
                </table>
            </fieldset>
            </td>
            </tr>
            <tr>
            <td colspan="2" valign="top">
            <fieldset>
                <legend>Aanvraag gegevens</legend>
                <table>
                    <tr>
                        <td>Rekening nummer</td>
                        <td><form:input path="accountNumber"/></td>
                    </tr>
                    <tr>
                        <td>Start datum (yyyymmdd)</td>
                        <td><form:input path="startDate"/></td>
                    </tr>
                    <tr>
                        <td>Eind datum (yyyymmdd)</td>
                        <td><form:input path="endDate"/></td>
                    </tr>
                </table>
            </fieldset>
            </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" name="Opslaan"></td>
            </tr>
            </table>
        </form:form>
    </body>
</html>