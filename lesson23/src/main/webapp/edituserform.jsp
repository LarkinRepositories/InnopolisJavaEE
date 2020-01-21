<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="user" class="pojo.user.User.Builder" />
<c:set target="${user}" property="id" value='<%= request.getParameter("id")%>' />
<c:set target="${user}" property="login" value='<%= request.getParameter("login")%>' />
<c:set target="${user}" property="password" value='<%= request.getParameter("password")%>' />
<c:set target="${user}" property="phone" value='<%= request.getParameter("phone")%>' />
<c:set target="${user}" property="email" value='<%= request.getParameter("email")%>' />



<h1>Editing a new user</h1>
<form method="post" action="${pageContext.request.contextPath}/edituser" autocomplete="off">
    <div class="form-group">
        <label for="id">ID</label>
        <input name="id" type="text" class="form-control" id="id" value="<jsp:getProperty name="user" property="id" />">
    </div>
    <div class="form-group">
        <label for="user">Login</label>
        <input name="login" type="text" class="form-control" id="user" value="<jsp:getProperty name="user" property="login" />">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input name="password" type="text" class="form-control" id="password" value="<jsp:getProperty name="user" property="password" />">
    </div>
    <div class="form-group">
        <label for="phone">Phone</label>
        <input name="phone" type="text" class="form-control" id="phone" value="<jsp:getProperty name="user" property="phone" />">
    </div>
    <div class="form-group">
        <label for="email">Email</label>
        <input name="email" type="text" class="form-control" id="email" value="<jsp:getProperty name="user" property="email" />">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>