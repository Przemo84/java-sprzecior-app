<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="page-header">
    <div class="pull-left">
        <h1><fmt:message key="menu.employees"/></h1>
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
        </li>
    </ul>
    <div class="close-bread">
        <a href="#"><i class="icon-remove"></i></a>
    </div>
</div>


<div class="row-fluid">
    <div class="span12">
        <div class="box">
            <div class="box-content nopadding">
                <div class="dataTables_wrapper">
                    <div class="dataTables_length">
                        <label>
                            <div class="btn-group" style="float: left;">
                                <a href="#" data-toggle="dropdown" class="btn dropdown-toggle">
                                    ${(not empty param.size) ? param.size : 200}<span class="caret"></span>
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
                        <a href="<c:url value="${moduleBaseUrl}/form"/>" class="btn btn-green">
                            <i class="icon-plus"></i>
                            <fmt:message key="action.add"/>
                        </a>
                    </div>
                    <div class="container-fluid">
                        <div class="form-group col-md-4" style="margin: 5px 5px 5px 5px">
                            <input class="form-control" id="filterInput" type="text" placeholder="Szukaj..">
                        </div>
                    </div>
                    <form autocomplete="off" action="<c:url value="${moduleBaseUrl}"/>/checkbox"
                          method="post" id="executable-users-list-form">
                        <table class="table table-hover table-nomargin table-striped table-bordered"
                               style="clear: both;">
                            <thead>
                            <tr>
                                <th style="text-align: center;">
                                    <tag:th param="firstName">
                                        <fmt:message key="user.firstName"/>
                                    </tag:th>
                                </th>
                                <th style="text-align: center;">
                                    <tag:th param="lastName">
                                        <fmt:message key="user.lastName"/>
                                    </tag:th>
                                </th>
                                <th style="text-align: center;">
                                    <tag:th param="email">
                                        <fmt:message key="user.email"/>
                                    </tag:th>
                                </th>
                                <th style="text-align: center;">
                                    <tag:th param="username">
                                        <fmt:message key="username"/>
                                    </tag:th>
                                </th>
                                <th style="text-align: center;">
                                    <tag:th param="lastLoginDate">
                                        <fmt:message key="user.lastLogin"/>
                                    </tag:th>
                                </th>
                                <th style="text-align: center;" colspan="3"><fmt:message key="options"/></th>
                            </tr>
                            </thead>

                            <tbody id="usersTable">
                            <c:forEach var="item" items="${page.iterator()}">
                                    <td style="text-align: center;">${item.firstName}</td>
                                    <td style="text-align: center;">${item.lastName}</td>
                                    <td style="text-align: center;">${item.email}</td>
                                    <td style="text-align: center;">${item.username}</td>
                                    <td style="text-align: center;">
                                        <fmt:formatDate value="${item.lastLoginDate}" pattern="dd.MM.yyyy"/>
                                    </td>

                                    <td style="width: 32px; text-align: center;">
                                        <a href="<c:url value="${moduleBaseUrl}/form/${item.id}"/>" rel="tooltip"
                                           title="Edytuj" class="btn">
                                            <i class="icon-pencil"></i>
                                        </a>
                                    </td>
                                    <td style="width: 32px; text-align: center;">
                                        <a href="javascript:void(0);"
                                           onclick="javascript:confirm_action('<c:url
                                                   value="${moduleBaseUrl}/lock/${item.id}"/>');"
                                           rel="tooltip" title="Zablokuj" class="btn btn-danger">
                                            <i class="icon-minus-sign"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>

                            <tfoot>
                            <tr>
                                <th colspan="8" style="height:40px;"></th>
                            </tr>
                            </tfoot>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <div class="pagination pagination-small">
            <tag:paginate pageData="${page}" next="&#62;" previous="&#60;"/>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $("#filterInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#usersTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>