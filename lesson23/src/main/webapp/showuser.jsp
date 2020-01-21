<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<ul class="list-group">
    <li class="list-group-item">${user.id}</li>
    <li class="list-group-item">${user.login}</li>
    <li class="list-group-item">${user.password}</li>
    <li class="list-group-item">${user.phone}</li>
    <li class="list-group-item">${user.email}</li>
</ul>

<br>
<a href="/">Main page</a>