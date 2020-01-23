<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>

<body>
${message}
<br>
<c:forEach var="item" items="${strings}">
    ${item}<br>
</c:forEach>
</body>
</html>
