<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="user" class="pojo.user.User" />
<c:set target="${user}" property="login" value="login" />
<c:set target ="${user}" property="password" value="password" />

<h1>Login form</h1>
<form method="post" action="${pageContext.request.contextPath}/login" autocomplete="off" >
    <div class="form-group">
        <label for="login">Login</label>
        <input name="login" type="text" class="form-control" id="login" value="<jsp:getProperty name="user" property="login"/>">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input name="password" type="text" class="form-control" id="password" value="<jsp:getProperty name="user" property="password" />">
    </div>
    <button type="submit"class="btn btn-primary">Login</button>
</form>