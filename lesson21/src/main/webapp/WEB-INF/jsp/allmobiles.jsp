<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body>
<h2>Mobiles list</h2>

<c:forEach items ="${model}" var="mobile">
    <a href='showmobile?id=${mobile.id}'>${mobile.model}</a><br>
</c:forEach>
</body>
</html>