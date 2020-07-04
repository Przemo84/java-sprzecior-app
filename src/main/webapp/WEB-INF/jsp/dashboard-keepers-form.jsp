<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="page-header">
    <div class="pull-left">
        <h1>Administratorzy</h1>
    </div>
</div>

<div class="breadcrumbs">
    <ul>
        <li>
            <a href="<c:url value="${moduleBaseUrl}"/>"><fmt:message key="home"/> </a>
            <i class="icon-angle-right"></i>
        </li>
        <li>
            <a href="<c:url value="${moduleBaseUrl}"/>"><fmt:message key="admins"/> </a>
        </li>
    </ul>
    <div class="close-bread">
        <a href="#"><i class="icon-remove"></i></a>
    </div>
</div>

<div class="row-fluid">
    <div class="span12">
        <div class="box box-bordered box-color">
            <div class="box-title">
                <h3><i class="icon-th-list"></i> <fmt:message key="action.add"/> </h3>
            </div>
            <div class="box-content nopadding">
                <form autocomplete="off" class="form-vertical form-bordered" method="POST" action=""
                      enctype="multipart/form-data">
                    <div class="control-group ">
                        <label class="control-label" for="email"><fmt:message key="user.email"/> </label>
                        <div class="controls">
                            <input type="text" name="email" value="" class="input-xlarge" id="email">
                        </div>
                    </div>
                    <div class="control-group ">
                        <label class="control-label" for="username"><fmt:message key="username"/> </label>
                        <div class="controls">
                            <input type="text" name="username" value="" class="input-xlarge" id="username">
                        </div>
                    </div>
                    <div class="control-group ">
                        <label class="control-label" for="firstname"><fmt:message key="user.firstName"/> </label>
                        <div class="controls">
                            <input type="text" name="firstname" value="" class="input-xlarge" id="firstname">
                        </div>
                    </div>
                    <div class="control-group ">
                        <label class="control-label" for="lastname"><fmt:message key="user.lastName"/> </label>
                        <div class="controls">
                            <input type="text" name="lastname" value="" class="input-xlarge" id="lastname">
                        </div>
                    </div>
                    <div class="control-group ">
                        <label class="control-label" for="password"><fmt:message key="password.description"/> </label>
                        <div class="controls">
                            <input type="password" name="password" value="" class="input-xlarge" id="password">
                        </div>
                    </div>

                    <div class="control-group ">
                        <label class="control-label" for="password_confirm"><fmt:message key="password.repeat"/> </label>
                        <div class="controls">
                            <input type="password" name="password_confirm" value="" class="input-xlarge" id="password_confirm">
                        </div>
                    </div>


                    <div class="control-group ">
                        <label class="control-label" for="role"><fmt:message key="role"/> </label>
                        <div class="controls">
                            <select name="role">
                                <option value=""> <fmt:message key="select.list.select"/> </option>
                                <option value="2">
                                    <fmt:message key="role.administrator"/>
                                </option>
                                <option value="4">
                                    <fmt:message key="role.editor"/>
                                </option>
                            </select>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit"><fmt:message key="action.save"/> </button>
                        <button class="btn" type="button"><fmt:message key="action.reset"/> </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>