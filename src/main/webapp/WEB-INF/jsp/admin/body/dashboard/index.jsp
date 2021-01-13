<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="page-header">
    <div class="pull-left">
        <h1><fmt:message key="dashboard.title"/></h1>
    </div>


</div>

<div class="breadcrumbs">
    <ul>
        <li>
            <a href="<c:url value="${moduleBaseUrl}"/>"><fmt:message key="home"/></a>
        </li>
    </ul>
    <div class="close-bread">
        <a href="#"><i class="icon-remove"></i></a>
    </div>
</div>
<br>
<div class="row-fluid">

    <div class="alert alert-info">
        <fmt:message key="welcome.message.for.admin"/>
        <button data-dismiss="alert" class="close" type="button">×</button>
    </div>
</div>
