<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="page-header">
    <div class="pull-left">
        <h1><fmt:message key="menu.roles"/></h1>
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
                <ul class="tabs tabs-inline tabs-top">
                    <li class="active">
                        <a href="#" disabled="" data-toggle="tab"><i class="icon-th-list"></i> <fmt:message key="basic.info"/>
                        </a>
                    </li>
                </ul>
                <spring:url value="${moduleBaseUrl}/save" var="action"/>
                <form:form method="post" class="form-vertical form-bordered" enctype="multipart/form-data" modelAttribute="role"
                           action="${action}">
                    <form:hidden path="id"/>

                    <spring:bind path="name">
                        <div class="control-group">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label"><fmt:message key="role.name"/></label>
                                <div class="controls">
                                    <form:input path="name" type="text" class="input-xlarge"/>
                                    <span style="color: red"><form:errors path="name" class="control-label"/></span>
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
