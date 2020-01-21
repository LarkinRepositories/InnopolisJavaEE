<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="user" class="pojo.user.User" />
<c:set target="${user}" property="id" value= '<%= request.getParameter("id")%>' />

<h1>Remove user by id</h1>
<form method="post" action="${pageContext.request.contextPath}/deleteuser" autocomplete="off">
    <div class="form-group">
        <label for="id">Id</label>
        <input name="id" type="text" class="form-control" id="id" value="<jsp:getProperty name="user" property="id" />">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>