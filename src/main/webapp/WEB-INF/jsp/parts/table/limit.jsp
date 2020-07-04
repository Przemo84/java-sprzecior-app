<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="dataTables_length">
    <label>


        <div class="btn-group" style="float: left;">
            <a href="#" data-toggle="dropdown" class="btn dropdown-toggle">
                ${(not empty param.size) ? param.size : 10}<span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
                <c:if test="${param.size != 10}">
                    <li>
                        <a href="<c:url value="${moduleBaseUrl}?size=10&sort=${param.sort}&order=${param.order}&page=${param.page}"/> ">10</a>
                    </li>
                </c:if>
                <c:if test="${param.size != 25}">
                    <li>
                        <a href="<c:url value="${moduleBaseUrl}?size=25&sort=${param.sort}&order=${param.order}&page=${param.page}"/> ">25</a>
                    </li>
                </c:if>
                <c:if test="${param.size != 50}">
                    <li>
                        <a href="<c:url value="${moduleBaseUrl}?size=50&sort=${param.sort}&order=${param.order}&page=${param.page}"/> ">50</a>
                    </li>
                </c:if>
                <c:if test="${param.size != 100}">
                    <li>
                        <a href="<c:url value="${moduleBaseUrl}?size=100&sort=${param.sort}&order=${param.order}&page=${param.page}"/> ">100</a>
                    </li>
                </c:if>
            </ul>
        </div>
        <span><fmt:message key="elements.per.page"/> </span>
    </label>
</div>

<div style="float: right; margin: 10px 10px 5px 5px">
    <a href="<c:url value="${moduleBaseUrl}/form"/>" class="btn">
        <i class="icon-plus"></i>
        <fmt:message key="action.add"/>
    </a>
</div>