<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="page-header">
    <div class="pull-left">
        <h1><fmt:message key="admins"/></h1>
    </div>
</div>
<div class="breadcrumbs">
    <ul>
        <li>
            <a href="<c:url value="${baseUrl}"/>"><fmt:message key="home"/></a>
            <i class="icon-angle-right"></i>
        </li>
        <li>
            <a href="<c:url value="${moduleBaseUrl}"/>"><fmt:message key="menu.employees"/></a>
            <i class="icon-angle-right"></i>
        </li>
        <li>
            <a href="<c:url value="${moduleBaseUrl}/form"/>"><fmt:message key="action.add"/></a>
        </li>
    </ul>
    <div class="close-bread">
        <a href="#"><i class="icon-remove"></i></a>
    </div>
</div>

</br>

<div class="row-fluid">
    <div class="span12">
        <div class="box box-bordered box-color">
            <div class="box-title">
                <h3><i class="icon-th-list"></i><fmt:message key="action.add"/></h3>
            </div>
            <div class="box-content nopadding">
                <ul class="tabs tabs-inline tabs-top">
                    <li class="active">
                        <a href="#" disabled="" data-toggle="tab"><i class="icon-th-list"></i> <fmt:message key="basic.info"/>
                        </a>
                    </li>
                </ul>
                <spring:url value="${moduleBaseUrl}/save" var="action"/>
                <form:form method="post" class="form-vertical form-bordered" enctype="multipart/form-data" modelAttribute="user"
                           action="${action}">
                <div class="tab-content padding tab-content-inline tab-content-bottom">
                    <form:hidden path="id"/>

                    <spring:bind path="email">
                        <div class="control-group">
                            <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="email"><fmt:message key="user.email"/></label>
                                    <form:input path="email" type="text" class="form-control"/>
                                    <span style="color: red"><form:errors path="email" class="control-label"/></span>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="username">
                        <div class="control-group">
                            <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="username"><fmt:message key="username"/></label>
                                    <form:input path="username" type="text" class="form-control"/>
                                    <span style="color: red"><form:errors path="username" class="control-label"/></span>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="firstName">
                        <div class="control-group ">
                            <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="firstName"><fmt:message key="user.firstName"/></label>
                                    <form:input path="firstName" type="text" class="form-control"/>
                                    <span style="color: red"><form:errors path="firstName" class="control-label"/></span>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="lastName">
                        <div class="control-group">
                            <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="lastName"><fmt:message key="user.lastName"/></label>
                                    <form:input path="lastName" type="text" class="form-control"/>
                                    <span style="color: red"><form:errors path="lastName" class="control-label"/></span>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="password">
                        <div class="control-group">
                            <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="password"><fmt:message key="password"/></label>
                                    <form:input path="password" type="password" class="form-control"/>
                                    <span style="color: red"><form:errors path="password" class="control-label"/></span>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="passwordConfirm">
                        <div class="control-group">
                            <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="passwordConfirm"><fmt:message key="password.repeat"/></label>
                                    <form:input path="passwordConfirm" type="password" class="form-control"/>
                                    <span style="color: red"><form:errors path="passwordConfirm" class="control-label"/></span>
                            </div>
                        </div>
                    </spring:bind>

                    <div class="form-actions" style="margin:1.5em">
                        <button class="btn btn-primary" type="submit" id="executeBtn"><fmt:message key="action.save"/></button>
                        <button class="btn" type="reset"><fmt:message key="action.cancel"/></button>
                    </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<style>
    .form-group-mx-3 {
        width: 30%;
    }
</style>
