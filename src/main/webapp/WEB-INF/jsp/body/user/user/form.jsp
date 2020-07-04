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
        <c:forEach items="${crumbs}" var="item" varStatus="loop">
            <c:if test="${!loop.last}">
                <li>
                    <a href="<c:url value="${item.url}"/>">${item.title}</a>
                    <i class="icon-angle-right"></i>
                </li>
            </c:if>

            <c:if test="${loop.last}">
                <li class="active"><a href="${moduleBaseUrl}/form">${item.title}</a></li>
            </c:if>
        </c:forEach>
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
                <spring:url value="${moduleBaseUrl}/save" var="action"/>
                <form:form method="post" class="form-vertical form-bordered" enctype="multipart/form-data" modelAttribute="user"
                           action="${action}">
                    <form:hidden path="id"/>

                    <spring:bind path="email">
                        <div class="control-group">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="email"><fmt:message key="user.email"/></label>
                                <div class="controls">
                                    <form:input path="email" type="text" class="input-xlarge"/>
                                    <span style="color: red"><form:errors path="email" class="control-label"/></span>
                                </div>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="username">
                        <div class="control-group">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="username"><fmt:message key="username"/></label>
                                <div class="controls">
                                    <form:input path="username" type="text" class="input-xlarge"/>
                                    <span style="color: red"><form:errors path="username" class="control-label"/></span>
                                </div>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="firstName">
                        <div class="control-group ">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="firstName"><fmt:message key="user.firstName"/></label>
                                <div class="controls">
                                    <form:input path="firstName" type="text" class="input-xlarge"/>
                                    <span style="color: red"><form:errors path="firstName" class="control-label"/></span>
                                </div>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="lastName">
                        <div class="control-group">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="lastName"><fmt:message key="user.lastName"/></label>
                                <div class="controls">
                                    <form:input path="lastName" type="text" class="input-xlarge"/>
                                    <span style="color: red"><form:errors path="lastName" class="control-label"/></span>
                                </div>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="password">
                        <div class="control-group">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="password"><fmt:message key="password.description"/></label>
                                <div class="controls">
                                    <form:input path="password" type="password" class="input-xlarge"/>
                                    <span style="color: red"><form:errors path="password" class="control-label"/></span>
                                </div>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="passwordConfirm">
                        <div class="control-group">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="passwordConfirm"><fmt:message key="password.repeat"/></label>
                                <div class="controls">
                                    <form:input path="passwordConfirm" type="password" class="input-xlarge"/>
                                    <span style="color: red"><form:errors path="passwordConfirm" class="control-label"/></span>
                                </div>
                            </div>
                        </div>
                    </spring:bind>

                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit" id="executeBtn"><fmt:message key="action.save"/></button>
                        <button class="btn" type="reset"><fmt:message key="action.cancel"/></button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
