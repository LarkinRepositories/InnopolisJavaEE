<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; UTF-8" %>

<jsp:useBean id="mobiles" scope="request" type="java.util.Collection"/>

<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>model</th>
        <th>price</th>
        <th>manufacturer</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="mobile" items="${mobiles}">
        <tr>
            <td scope="row">${mobile.id}</td>
            <td>${mobile.model}</td>
            <td>${mobile.price}</td>
            <td>${mobile.manufacturer}</td>
            <td><a href="${pageContext.request.contextPath}/showmobile?id=${mobile.id}">Link</a></td>
            <td><a href="${pageContext.request.contextPath}/delete?id=${mobile.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<table class="table"><tr><td><a href="${pageContext.request.contextPath}/mobiles/">Main page</a></td><td><a href="${pageContext.request.contextPath}/addmobile">Add new mobile</a></td></tr></table>

