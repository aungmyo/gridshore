<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<table>
    <tr>
        <th>id</th>
        <th>amount</th>
        <th>type</th>
        <th>status</th>
    </tr>
<c:forEach items="${paymentList}" var="payment">
    <tr>
        <td><a href="editPaymentStatus.html?id=<c:out value='${payment.id}'/>"><c:out value='${payment.id}'/></a></td>
        <td><c:out value="${payment.amount}"/></td>
        <td><c:out value="${payment.type}"/></td>
        <td><c:out value="${payment.status}"/></td>
    </tr>
</c:forEach>
</table>
