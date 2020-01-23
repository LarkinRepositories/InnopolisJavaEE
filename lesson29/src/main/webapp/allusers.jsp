<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; UTF-8" %>

<jsp:useBean id="users" scope="request" type="java.util.List"/>

<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>login</th>
        <th>password</th>
        <th>phone</th>
        <th>email</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td scope="row">${user.id}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>${user.phone}</td>
            <td>${user.email}</td>
            <td><a href="${pageContext.request.contextPath}/showuser?id=${user.id}">View details</a></td>
            <td><a href="${pageContext.request.contextPath}/edituser?id=${user.id}">Edit</a></td>
            <td><a href="${pageContext.request.contextPath}/deleteuser?id=${user.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<table class="table">
    <tr>
        <td><a href="${pageContext.request.contextPath}">Main page</a></td>
        <td><a href="${pageContext.request.contextPath}/adduser">Add new user</a></td>
        <td><a href="${pageContext.request.contextPath}/deleteuser">Delete user</a></td>
    </tr>
</table>