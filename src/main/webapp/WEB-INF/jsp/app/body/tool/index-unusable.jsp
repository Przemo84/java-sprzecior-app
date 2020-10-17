<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
            <a href="<c:url value="${moduleBaseUrl}/unusable"/>"><fmt:message key="menu.action.tools.unusable"/></a>
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
                                    ${(not empty param.size) ? param.size : 250}<span class="caret"></span>
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
                            <input class="form-control" id="filterInput" type="text" placeholder="Szukaj..">
                        </div>
                    </div>
                    <table class="table table-hover table-nomargin table-striped table-bordered"
                           style="clear: both;">
                        <thead>
                        <tr>
                            <th style="text-align: center;">
                                <tag:th param="id">
                                    <fmt:message key="tool.id"/>
                                </tag:th>
                            </th>
                            <th style="text-align: center;">
                                <tag:th param="companyId">
                                    <fmt:message key="tool.company.id"/>
                                </tag:th>
                            </th>
                            <th style="text-align: center;">
                                <tag:th param="unusableReason">
                                    <fmt:message key="tool.unusable.reason"/>
                                </tag:th>
                            </th>
                            <th style="text-align: center;">
                                <tag:th param="unusableDate">
                                    <fmt:message key="tool.unusable.date"/>
                                </tag:th>
                            </th>
                            <th style="text-align: center;">
                                <tag:th param="available">
                                    <fmt:message key="tool.is.available"/>
                                </tag:th>
                            </th>

                            <th style="text-align: center;">
                                <tag:th param="model">
                                    <fmt:message key="tool.model"/>
                                </tag:th>
                            </th>
                            <th style="text-align: center;">
                                <tag:th param="user">
                                    <fmt:message key="tool.user"/>
                                </tag:th>
                            </th>
                            <th style="text-align: center;">
                                <tag:th param="toolType">
                                    <fmt:message key="tool.type"/>
                                </tag:th>
                            </th>
                            <th style="text-align: center;">
                                <tag:th param="serialNo">
                                    <fmt:message key="tool.serial.number"/>
                                </tag:th>
                            </th>
                            <th style="text-align: center;">
                                <tag:th param="productionDate">
                                    <fmt:message key="tool.production.date"/>
                                </tag:th>
                            </th>
                            <th style="text-align: center;">
                                <tag:th param="calibrationDate">
                                    <fmt:message key="tool.calibration.date"/>
                                </tag:th>
                            </th>
                            <sec:authorize access="hasAnyAuthority('Editor')">
                                <th style="text-align: center;" colspan="2"><fmt:message key="options"/></th>
                            </sec:authorize>

                        </tr>
                        </thead>

                        <tbody id="toolsTable">
                        <c:forEach var="item" items="${page.iterator()}">
                            <tr>
                                <td style="text-align: center;">
                                    <a href="<c:url value="${moduleBaseUrl}/unusable/${item.id}"/>"
                                       rel="tooltip" title="Zobacz szczegółowe info">
                                            ${item.id}
                                    </a>
                                </td>
                                <td style="text-align: center;">
                                    <a href="<c:url value="${moduleBaseUrl}/unusable/${item.id}"/>"
                                       rel="tooltip" title="Zobacz szczegółowe info">
                                            ${item.companyId}
                                    </a>
                                </td>
                                <td style="text-align: center;" id="unusableReason">${item.unusableReason}</td>
                                <td style="text-align: center;">
                                    <fmt:formatDate value="${item.unusableDate}" pattern="YYYY-MM-dd HH:MM:ss"/>
                                </td>
                                <td style="text-align: center;">
                                    <c:if test="${item.available}">
                                        <i class="icon-ok" style="color: green"></i>
                                    </c:if>
                                    <c:if test="${item.available eq false}">
                                        <i class="icon-minus" style="color: red"></i>
                                    </c:if>
                                </td>

                                <td style="text-align: center;">
                                    <a href="<c:url value="${moduleBaseUrl}/status/${item.id}"/>"
                                       rel="tooltip" title="Zobacz historię sprzetu">
                                            ${item.model}
                                    </a>
                                </td>
                                <td style="text-align: center;">${item.user.fullName}</td>
                                <td style="text-align: center;">${item.toolType}</td>
                                <td style="text-align: center;">${item.serialNo}</td>
                                <td style="text-align: center;">
                                    <fmt:formatDate value="${item.productionDate}" pattern="YYYY-MM"/>
                                </td>
                                <td style="text-align: center;">
                                    <fmt:formatDate value="${item.calibrationDate}" pattern="YYYY-MM"/>
                                </td>

                                <sec:authorize access="hasAnyAuthority('Editor')">
                                    <td style="width: 32px; text-align: center;">
                                        <a href="javascript:void(0);"
                                           onclick="javascript:confirm_action('<c:url
                                                   value="${moduleBaseUrl}/make-usable/${item.id}"/>');"
                                           rel="tooltip" title="Przenieś do sprzętu użytkowanego"
                                           class="btn btn-satgreen">
                                            <i class="fas fa-cannabis"></i>
                                        </a>
                                    </td>
                                </sec:authorize>
                            </tr>
                        </c:forEach>
                        </tbody>

                        <tfoot>
                        <tr>
                            <th colspan="13" style="height:40px;"></th>
                        </tr>
                        </tfoot>
                    </table>
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
            $("#toolsTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });

</script>

<style>
    #unusableReason {
        max-width: 200px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
</style>