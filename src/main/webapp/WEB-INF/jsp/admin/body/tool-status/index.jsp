<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="page-header">
    <div class="pull-left">
        <h1><fmt:message key="menu.action.tools.history"/>: <i style="color:#942a25">${tool.title} , ID: ${tool.id} </i></h1>
    </div>
</div>

<div class="breadcrumbs">
    <ul>
        <li>
            <a href="<c:url value="${baseUrl}"/>"><fmt:message key="home"/></a>
            <i class="icon-angle-right"></i>
        </li>
        <li>
            <a href="<c:url value="${moduleBaseUrl}/${tool.id}"/>"><fmt:message key="menu.action.tools.history"/></a>
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
                    <div class="container-fluid">
                        <div class="form-group col-md-4" style="margin: 5px 5px 5px 5px">
                            <input class="form-control" id="myInput" type="text" placeholder="Szukaj..">
                        </div>
                    </div>
                    <form autocomplete="off" action="<c:url value="${moduleBaseUrl}"/>/checkbox"
                          method="post" id="executable-users-list-form">
                        <table class="table table-hover table-nomargin table-striped table-bordered"
                               style="clear: both;">
                            <thead>
                            <tr>
                                <th style="text-align: center;">
                                    <tag:th param="companyId">
                                        <fmt:message key="tool.company.id"/>
                                    </tag:th>
                                </th>
                                <th style="text-align: center;">
                                    <tag:th param="title">
                                        <fmt:message key="tool.title"/>
                                    </tag:th>
                                </th>
                                <th style="text-align: center;">
                                    <tag:th param="toolType">
                                        <fmt:message key="tool.type"/>
                                    </tag:th>
                                </th>
                                <th style="text-align: center;">
                                    <tag:th param="available">
                                        <fmt:message key="tool.is.available"/>
                                    </tag:th>
                                </th>

                                <th style="text-align: center;">
                                    <tag:th param="user">
                                        <fmt:message key="tool.user"/>
                                    </tag:th>
                                </th>
                            </tr>
                            </thead>

                            <tbody id="myTable">
                            <c:forEach var="item" items="${page.iterator()}">
                                <tr>
                                    <td style="text-align: center;">${item.action}</td>
                                    <td style="text-align: center;">${item.createDate}</td>
                                    <td style="text-align: center;">${item.user.fullName}</td>
                                    <td style="text-align: center;">${item.description}</td>
                                    <td style="text-align: center;">${item.needRepair}</td>
                                    <td style="text-align: center;">${item.shape}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
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
    var checkboxes = $('#executable-users-list-form td input[type="checkbox"]');
    var checkboxAll = $('#executable-users-list-form  th input[type="checkbox"]');

    checkboxes.change(function () {
        $('#executeBtn').prop("disabled", !this.checked);
    }).change();

    checkboxAll.change(function () {
        $('#executeBtn').prop("disabled", !this.checked);
    }).change();

    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });

</script>