<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body>
<a href='allmobiles'><- Go back to mobiles list</a>
<h2>${model.model}</h2>
<h2>params</h2>
<ul>
    <li>Price - ${model.price}</li>
    <li>Manufacturer - ${model.manufacturer}</li>
</ul>
</body>
</html>