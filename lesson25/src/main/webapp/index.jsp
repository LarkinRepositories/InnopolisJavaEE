
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<myTags:template>
    <jsp:attribute name="header">
        <h1>Lesson 22 app</h1>
        <h2>Mobiles</h2>
    </jsp:attribute>
    <jsp:body>
        <ul>
            <li><a href="${pageContext.request.contextPath}/allmobiles">List mobiles</a></li>
            <li><a href="${pageContext.request.contextPath}/addmobile">Add mobile</a></li>
            <li><a href="${pageContext.request.contextPath}/editmobile">Edit mobile</a></li>
            <li><a href="${pageContext.request.contextPath}/deletemobile">Delete mobile</a></li>
        </ul>
    <h2>Users</h2>
        <ul>
            <li><a href="${pageContext.request.contextPath}/allusers">List users</a></li>
            <li><a href="${pageContext.request.contextPath}/adduser">Add new user</a></li>
            <li><a href="${pageContext.request.contextPath}/edituser">Edit user</a></li>
            <li><a href="${pageContext.request.contextPath}/deleteuser">Delete user</a></li>
        </ul>
    </jsp:body>
</myTags:template>