
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<myTags:template>
    <jsp:attribute name="header">
        <h1>Mobiles</h1>
    </jsp:attribute>
    <jsp:body>
        <ul>
            <li><a href="${pageContext.request.contextPath}/allmobiles">List mobiles</a></li>
            <li><a href="${pageContext.request.contextPath}/addmobile">Add mobile</a></li>
            <li><a href="${pageContext.request.contextPath}/editmobile">Edit mobile</a></li>
            <li><a href="${pageContext.request.contextPath}/deletemobile">Delete mobile</a></li>
        </ul>
    </jsp:body>
</myTags:template>