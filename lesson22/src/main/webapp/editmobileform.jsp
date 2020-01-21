<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="mobile" class="pojo.mobile.Mobile" />
<c:set target="${mobile}" property="model" value='<%= request.getParameter("model")%>' />
<c:set target="${mobile}" property="price" value='<%= request.getParameter("price")%>' />
<c:set target="${mobile}" property="manufacturer" value='<%= request.getParameter("manufacturer")%>' />



<h1>Adding a new mobile</h1>
<form method="post" action="${pageContext.request.contextPath}/editmobile" autocomplete="off">
    <div class="form-group">
        <label for="model">Model</label>
        <input name="model" type="text" class="form-control" id="model" value="<jsp:getProperty name="mobile" property="model" />">
    </div>
    <div class="form-group">
        <label for="price">Price</label>
        <input name="price" type="text" class="form-control" id="price" value="<jsp:getProperty name="mobile" property="price" />">
    </div>
    <div class="form-group">
        <label for="manufacturer">Manufacturer</label>
        <input name="manufacturer" type="text" class="form-control" id="manufacturer" value="<jsp:getProperty name="mobile" property="manufacturer" />">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>