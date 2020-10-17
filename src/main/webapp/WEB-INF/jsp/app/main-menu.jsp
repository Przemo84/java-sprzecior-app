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
            <span>Sprzęt</span>
        </a>
    </div>
    <ul class="subnav-menu">
        <li class="<c:if test="${fn:contains(currentUrl, 'app/tools/available')}">active</c:if>">
            <a href="<c:url value="/app/tools/available"/>"><fmt:message key="menu.action.tools.available"/> </a>
        </li>
        <li class="<c:if test="${fn:contains(currentUrl, 'app/tools/unavailable')}">active</c:if>">
            <a href="<c:url value="/app/tools/unavailable"/>"><fmt:message key="menu.action.tools.unavailable"/> </a>
        </li>
        <ul class="subnav-menu">
            <li class="<c:if test="${fn:contains(currentUrl, 'app/tools/unusable')}">active</c:if>">
                <a href="<c:url value="/app/tools/unusable"/>"><fmt:message key="menu.action.tools.unusable"/> </a>
            </li>
        </ul>
        <li class="<c:if test="${fn:contains(currentUrl, 'app/tools/user')}">active</c:if>">
            <a href="<c:url value="/app/tools/user"/>"><fmt:message key="menu.action.tools.user"/> </a>
        </li>
    </ul>
</div>

