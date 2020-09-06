<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<div class="container-fluid">
    <a href="#" id="brand">v1.0.0</a>
    <a href="#" class="toggle-nav" rel="tooltip" data-placement="bottom" title="Toggle navigation">
        <i class="icon-reorder"></i>
    </a>
    <ul class="main-nav">
        <li>
            <a href="<c:url value="${baseUrl}"/> ">
                <i class="icon-home"></i>
                <span><fmt:message key="dashboard.title"></fmt:message> </span>
            </a>
        </li>
    </ul>
    <div class="user">
        <ul class="icon-nav">
            <li style="width: 300px">
                <h4 class="pull-left" style=" color: white; margin-right: 12px">CMS Sprzęcior</h4>
                <div style="float: left; margin-top: 2px">
                    <img src="<c:url value="/resources/img/nordgeo-logo-50.png"/> " alt="">
                </div>
            </li>

            <li class="auth-timer" style="color: white; margin-top: 8px; margin-right: 10px">
                <strong><fmt:message key="header.seconds.left.to.logout"/> </strong>
                <%--//TODO timer --%>
                <span class="timer">
                    ${pageContext.session.maxInactiveInterval}
                </span>
            </li>
        </ul>
        <div class="dropdown">
            <%--TODO--%>
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                ${authUser.firstName} ${authUser.lastName}
                <img src="<c:url value="/resources/img/user-avatar.jpg"/>" width=" 27" height="27" alt="">
            </a>
            <ul class="dropdown-menu pull-right">
                <li>
                    <a href="<c:url value="/admin/account/password"/>">
                        <fmt:message key="password.change"/> </a>
                </li>
                <li>

                    <form id="logout-form" action="<c:url value="/signout"/>" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>

                    <a href="#" onclick="document.getElementById('logout-form').submit();">
                        <fmt:message key="log-out"/> </a>
                </li>
            </ul>
        </div>
    </div>

</div>
<ul class="mobile-nav">
    <li><a href="#"><fmt:message key="dashboard.title"/> </a></li>
</ul>
