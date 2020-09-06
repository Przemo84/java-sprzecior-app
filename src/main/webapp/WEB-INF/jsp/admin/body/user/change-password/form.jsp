<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="page-header">
    <div class="pull-left">
        <h1>Moje konto</h1>
    </div>
</div>

<div class="row-fluid">
    <div class="span12">
        <div class="box box-bordered box-color">
            <div class="box-title">
                <h3><i class="icon-th-list"></i> <fmt:message key="password.change"/></h3>
            </div>
            <div class="box-content nopadding">
                <spring:url value="${moduleBaseUrl}/save" var="action"/>
                <form:form method="post" class="form-vertical form-bordered" enctype="multipart/form-data"
                           modelAttribute="user"
                           action="${action}">


                    <spring:bind path="currentPassword">
                        <div class="control-group">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label"><fmt:message key="password.current"/></label>
                                <form:input path="currentPassword" type="password" class="form-control"
                                            autocomplete="false"/>
                                <span style="color: red"><form:errors path="currentPassword"
                                                                      class="control-label"/></span>
                            </div>
                        </div>
                    </spring:bind>


                    <spring:bind path="newPassword">
                        <div class="control-group">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label"><fmt:message key="password.new"/></label>
                                <form:input path="newPassword" type="password" class="form-control"
                                            autocomplete="false"/>
                                <span style="color: red"><form:errors path="newPassword" class="control-label"/></span>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="passwordConfirm">
                        <div class="control-group">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="passwordConfirm"><fmt:message
                                        key="password.repeat"/></label>
                                <form:input path="passwordConfirm" type="password" class="form-control"/>
                                <span style="color: red"><form:errors path="passwordConfirm"
                                                                      class="control-label"/></span>
                            </div>
                        </div>
                    </spring:bind>

                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit"><fmt:message key="action.save"/></button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<style>
    .form-group.mx-3 {
        width: 30%
    }

    .form-actions {
        border-top: 1px solid gray;
        padding: 10px;
    }
</style>