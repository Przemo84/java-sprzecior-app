<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="page-header">
    <div class="pull-left">
        <h1><fmt:message key="menu.action.tools"/></h1>
    </div>
</div>
<div class="breadcrumbs">
    <ul>
        <li>
            <a href="<c:url value="${baseUrl}"/>"><fmt:message key="home"/></a>
            <i class="icon-angle-right"></i>
        </li>
        <li>
            <a href="<c:url value="${moduleBaseUrl}"/>"><fmt:message key="menu.action.tools"/></a>
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
                <form:form method="post" class="form-vertical form-bordered" enctype="multipart/form-data" modelAttribute="tool"
                           action="${action}">
                <div class="tab-content padding tab-content-inline tab-content-bottom">
                    <form:hidden path="id"/>

                    <spring:bind path="companyId">
                        <div class="control-group">
                            <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="email"><fmt:message key="tool.company.id"/></label>
                                <form:input path="companyId" type="text" class="form-control"/>
                                <span style="color: red"><form:errors path="companyId" class="control-label"/></span>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="title">
                        <div class="control-group">
                            <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="title"><fmt:message key="tool.title"/></label>
                                <form:input path="title" type="text" class="form-control"/>
                                <span style="color: red"><form:errors path="title" class="control-label"/></span>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="toolType">
                        <div class="control-group ">
                            <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="toolType"><fmt:message key="tool.type"/></label>
                                <form:input path="toolType" type="text" class="form-control"/>
                                <span style="color: red"><form:errors path="toolType" class="control-label"/></span>
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
