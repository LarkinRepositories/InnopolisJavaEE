<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header id="header">
    <div class="container">
        <nav id="nav">
            <div class="opener-holder">
                <a href="#" class="nav-opener"><span></span></a>
            </div>
            <div class="nav-drop">
                <ul  class="nav navbar-nav navbar-right">
                    <c:choose>
                        <c:when test="${sessionScope.get('user')!= null}">
                                <li><a class="navbar-link" title="Выйти"
                                       href="${pageContext.request.contextPath}/perform_logout">
                                    <span class="glyphicon glyphicon-log-out"></span></a></li>
                        </c:when>
                        <c:otherwise>
                                <li><a class="navbar-link" href="${pageContext.request.contextPath}/login">Авторизоваться</a></li>
                                <li><a class="navbar-link" href="${pageContext.request.contextPath}/reg">Зарегистрироваться</a></li>
                        </c:otherwise>
                    </c:choose>
                    <ul>
                        <li>
                            <a href="?lang=ru">Русский</a>
                        </li>
                        |
                        <li>
                            <a href="?lang=en">English</a>
                        </li>
                </ul>
            </div>
        </nav>
    </div>
</header>

