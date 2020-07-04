<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="page-header">
    <div class="pull-left">
        <h1><fmt:message key="menu.dealers"/></h1>
    </div>
</div>
<div class="breadcrumbs">
    <ul>
        <li>
            <a href="<c:url value="${baseUrl}"/>"><fmt:message key="home"/></a>
            <i class="icon-angle-right"></i>
        </li>
        <li>
            <a href="<c:url value="${moduleBaseUrl}"/>"><fmt:message key="menu.dealers"/></a>
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

                    <spring:bind path="firstName">
                        <div class="control-group ">
                            <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                                <label class="control-label"><fmt:message key="user.firstName"/></label>
                                <div class="controls">
                                    <form:input path="firstName" type="text" class="form-control"/>
                                    <span style="color: red"><form:errors path="firstName" class="control-label"/></span>
                                </div>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="lastName">
                        <div class="control-group ">
                            <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                                <label class="control-label"><fmt:message key="user.lastName"/></label>
                                <div class="controls">
                                    <form:input path="lastName" type="text" class="form-control"/>
                                    <span style="color: red"><form:errors path="lastName" class="control-label"/></span>
                                </div>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="email">
                        <div class="control-group">
                            <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="email"><fmt:message key="user.email"/></label>
                                <div class="controls">
                                    <form:input path="email" type="text" class="form-control"/>
                                    <span style="color: red"><form:errors path="email" class="control-label"/></span>
                                </div>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="username">
                        <div class="control-group">
                            <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="username"><fmt:message key="username"/></label>
                                <div class="controls">
                                    <form:input path="username" type="text" class="form-control"/>
                                    <span style="color: red"><form:errors path="username" class="control-label"/></span>
                                </div>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="password">
                        <div class="control-group">
                            <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="password"><fmt:message key="password.description"/></label>
                                <div class="controls">
                                    <form:input path="password" type="password" class="form-control"/>
                                    </br>
                                    <span style="color: red"><form:errors path="password" class="control-label"/></span>
                                </div>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="passwordConfirm">
                        <div class="control-group">
                            <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                                <label class="control-label" for="passwordConfirm"><fmt:message key="password.repeat"/></label>
                                <div class="controls">
                                    <form:input path="passwordConfirm" type="password" class="form-control"/>
                                    <span style="color: red"><form:errors path="passwordConfirm" class="control-label"/></span>
                                </div>
                            </div>
                        </div>
                    </spring:bind>

                    <spring:bind path="mobile">
                        <div class="control-group ">
                            <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                                <label class="control-label"><fmt:message key="mobile"/></label>
                                <div class="controls">
                                    <form:input path="mobile" type="text" class="form-control"/>
                                    <span style="color: red"><form:errors path="mobile" class="control-label"/></span>
                                </div>
                            </div>
                        </div>
                    </spring:bind>


                    <div class="control-group">
                        <label class="control-label">
                            <fmt:message key="image"></fmt:message>
                        </label>
                        <div class="show-img img-preview">
                            <img class="show-img img-preview" src="<c:url value="/files/${user.icon}"/> " id="image"/>
                        </div>
                        <div class="mt-5">
                    <span class="btn btn-default btn-file">
                        <fmt:message key="browse"></fmt:message>
                        <input id="imgInp" type="file" name="file" accept="image/png, image/jpeg"/>
                    </span>
                            <span class="btn btn-default" onclick="startCrop(1, this);">
                        <i class="fa fa-crop"></i>
                       <fmt:message key="crop"></fmt:message>
                    </span>
                        </div>
                        <div class="form-group has-error">
                            <form:errors path="icon" class="control-label"/>
                        </div>
                    </div>

                    <form:input path="imageParams.oldImageName"  type="hidden" id="oldImage"/>
                    <form:input path="imageParams.x"             type="hidden"  id="imgX"/>
                    <form:input path="imageParams.y"             type="hidden"  id="imgY"/>
                    <form:input path="imageParams.width"         type="hidden"  id="imgWidth"/>
                    <form:input path="imageParams.height"        type="hidden"  id="imgHeight"/>


                    <div class="form-actions" style="margin:1.5em">
                        <button class="btn btn-primary" type="submit"><fmt:message key="action.save"/></button>
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

    .btn-file {
        position: relative;
        overflow: hidden;
    }

    .btn-file input[type=file] {
        position: absolute;
        top: 0;
        right: 0;
        min-width: 100%;
        min-height: 100%;
        font-size: 100px;
        text-align: right;
        filter: alpha(opacity=0);
        opacity: 0;
        outline: none;
        background: white;
        cursor: inherit;
        display: block;
    }

    .img-preview {
        max-height: 100%;
        object-fit: cover;
        max-width: 100%;
    }

    img {
        max-width: 100%; /* This rule is very important, please do not ignore this! */
    }

</style>
<script>
    function readURL(input) {

        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#image').attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#imgInp").change(function () {
        readURL(this);
    });
</script>
