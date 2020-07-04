<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="pull-left">
    <img src="<c:url value="/resources/img/arrow_ltr.png"/>"/>
</div>
<div class="pull-left">
    <select class="form-control">
        <option>Action</option>
        <option>Delete</option>
        <option>Lock</option>
        <option>Archive</option>
    </select>
</div>
<div class="pull-left" style="margin-left: 5px">
    <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
            ${(not empty param.size) ? param.size : 10}<span class="caret"></span>
        </button>
        <ul class="dropdown-menu">


            <c:if test="${param.size != 10}">
                <li><a href="<c:url value="${moduleBaseUrl}?size=10"/> ">10</a></li>
            </c:if>
            <c:if test="${param.size != 25}">
                <li><a href="<c:url value="${moduleBaseUrl}?size=25"/> ">25</a></li>
            </c:if>
            <c:if test="${param.size != 50}">
                <li><a href="<c:url value="${moduleBaseUrl}?size=50"/> ">50</a></li>
            </c:if>
            <c:if test="${param.size != 100}">
                <li><a href="<c:url value="${moduleBaseUrl}?size=100"/> ">100</a></li>
            </c:if>
        </ul>
    </div>
</div>