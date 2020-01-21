<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<jsp:useBean id="User.Builder" class="pojo.user.User.Builder" />--%>
<%--<c:set target="${User.Builder}" property="login" value="login" />--%>
<%--<c:set target ="${User.Builder}" property="password" value="password" />--%>

<h1>Login form</h1>
<form method="post" action="${pageContext.request.contextPath}/login" autocomplete="off" >
    <div class="form-group">
        <label for="login">Login</label>
        <input name="login" type="text" class="form-control" id="login" >
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input name="password" type="text" class="form-control" id="password">
    </div>
    <button type="submit"class="btn btn-primary">Login</button>
</form>