<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<c:set var="currentUrl" value="${requestScope['javax.servlet.forward.request_uri']}"/>

<div class="subnav">
    <div class="subnav-title">
        <a href="#" class="toggle-subnav">
            <i class="icon-angle-down"></i>
            <span>Użytkownicy</span>
        </a>
    </div>
    <ul class="subnav-menu">
        <li class="<c:if test="${fn:contains(currentUrl, 'admin/admins')}">active</c:if>">
            <a href="<c:url value="/admin/admins/"/>"><fmt:message key="menu.admins"/> </a>
        </li>
        <li class="<c:if test="${fn:contains(currentUrl, 'admin/editors')}">active</c:if>">
            <a href="<c:url value="/admin/editors/"/>"><fmt:message key="menu.editors"/></a>
        </li>
        <li class="<c:if test="${fn:contains(currentUrl, 'admin/employees')}">active</c:if>">
            <a href="<c:url value="/admin/employees/"/>"><fmt:message key="menu.employees"/></a>
        </li>
        <li class="<c:if test="${fn:contains(currentUrl, 'admin/user/locked')}">active</c:if>">
            <a href="<c:url value="/admin/locked-users/"/>"><fmt:message key="menu.users.locked"/> </a>
        </li>
        <li class="<c:if test="${fn:contains(currentUrl, 'admin/roles')}">active</c:if>">
            <a href="<c:url value="/admin/roles/"/>"><fmt:message key="menu.roles"/> </a>
        </li>
    </ul>
</div>

<div class="subnav">
    <div class="subnav-title">
        <a href="#" class="toggle-subnav">
            <i class="icon-angle-down"></i>
            <span>Sprzęt</span>
        </a>
    </div>
    <ul class="subnav-menu">
        <li class="<c:if test="${fn:contains(currentUrl, 'admin/tools')}">active</c:if>">
            <a href="<c:url value="/admin/tools/"/>"><fmt:message key="menu.action.tools"/> </a>
        </li>
    </ul>
</div>

<div class="subnav">
    <div class="subnav-title">
        <a href="#" class="toggle-subnav">
            <i class="icon-angle-down"></i>
            <span>Historia</span>
        </a>
    </div>
    <ul class="subnav-menu">
        <li class="<c:if test="${fn:contains(currentUrl, 'admin/history')}">active</c:if>">
            <a href="<c:url value="/admin/history/"/>"><fmt:message key="menu.action.history"/> </a>
        </li>
    </ul>
</div>
