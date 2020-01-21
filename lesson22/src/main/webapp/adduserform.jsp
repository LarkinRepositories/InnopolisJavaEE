<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="user" class="pojo.user.User" />
<c:set target="${user}" property="login" value="login" />
<c:set target="${user}" property="password" value="password" />
<c:set target="${user}" property="phone" value="+7-(913)-911-91-91" />
<c:set target="${user}" property="email" value="mail@mail.com" />
<%--<jsp:setProperty name="user" property="" value="" />--%>


<h1>Adding a new user</h1>
<form method="post" action="${pageContext.request.contextPath}/adduser" autocomplete="off">
    <div class="form-group">
        <label for="login">Login</label>
        <input name="login" type="text" class="form-control" id="login" value="<jsp:getProperty name="user" property="login" />">
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