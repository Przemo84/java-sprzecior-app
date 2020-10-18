<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="page-header">
    <div class="pull-left">
        <h1><fmt:message key="menu.action.tools.history"/>
        </h1>
        <div class="rating" data-rate-value=6></div>
    </div>
</div>

<div class="breadcrumbs" style="margin-bottom: 20px">
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
<div class="container-fluid" style="text-align: center">

    <p class="h4 dotted">
        ID: <strong>${tool.id}</strong>
    </p>
    <p class="h4 dotted">
        Nordgeo ID: <strong>${tool.companyId}</strong>
    </p>
    <p class="h4 dotted">
        Moodel: <strong>${tool.model}</strong>
    </p>
    <p class="h4 dotted">
        Serial No: <strong>${tool.serialNo}</strong>
    </p>
    <p class="h4" style="text-align: left">
        Średnia ocen:
        <strong>
            <div style="width:112px">
                <c:if test="${tool.averageRating >= 4.0}">
                    <h4 class="rating-style" style="background-color: green">
                        <strong><fmt:formatNumber type="number" maxFractionDigits="2"
                                                  value="${tool.averageRating}"/></strong>
                    </h4>
                </c:if>

                <c:if test="${tool.averageRating < 4.0 and tool.averageRating >= 3.0}">
                    <h4 class="rating-style" style="background-color: yellowgreen">
                        <strong><fmt:formatNumber type="number" maxFractionDigits="2"
                                                  value="${tool.averageRating}"/></strong>
                    </h4>
                </c:if>

                <c:if test="${tool.averageRating < 3.0 and tool.averageRating >= 2.0}">
                    <h4 class="rating-style" style="background-color: yellow">
                        <strong><fmt:formatNumber type="number" maxFractionDigits="2"
                                                  value="${tool.averageRating}"/></strong>
                    </h4>
                </c:if>

                <c:if test="${tool.averageRating < 2.0}">
                    <h4 class="rating-style" style="background-color: red">
                        <strong><fmt:formatNumber type="number" maxFractionDigits="2"
                                                  value="${tool.averageRating}"/></strong>
                    </h4>
                </c:if>

                <c:if test="${tool.averageRating eq null}">
                    <h4 style="width: 32px; text-align: center;">
                    </h4>
                </c:if>
            </div>
        </strong>
    </p>
</div>

<div class="row-fluid">
    <div class="span12">
        <div class="box">
            <div class="box-content nopadding">
                <div class="dataTables_wrapper">
                    <div class="dataTables_length">
                    </div>
                    <table class="table table-hover table-nomargin table-striped table-bordered"
                           style="clear: both;">
                        <thead>
                        <tr>
                            <th style="text-align: center;">
                                <tag:th param="action">
                                    <fmt:message key="tool-status.action"/>
                                </tag:th>
                            </th>
                            <th style="text-align: center;" colspan="2">
                                <tag:th param="createDate">
                                    <fmt:message key="tool-status.action.date"/>
                                </tag:th>
                            </th>
                            <th style="text-align: center;">
                                <tag:th param="user">
                                    <fmt:message key="tool-status.author"/>
                                </tag:th>
                            </th>
                            <th style="text-align: center;">
                                <tag:th param="description">
                                    <fmt:message key="tool-status.description"/>
                                </tag:th>
                            </th>

                            <th style="text-align: center;">
                                <tag:th param="needRepair">
                                    <fmt:message key="tool-status.need.repair"/>
                                </tag:th>
                            </th>

                            <th style="text-align: center;">
                                <tag:th param="rating">
                                    <fmt:message key="tool-status.rating"/>
                                </tag:th>
                            </th>
                        </tr>
                        </thead>

                        <tbody id="myTable">
                        <c:forEach var="item" items="${page.iterator()}">
                            <tr>
                                <td style="text-align: center;">
                                    <c:if test="${item.action eq 'TAKE_IN'}">
                                        <i style="color: red" class="fas fa-cloud-download-alt"></i><fmt:message
                                            key="${item.action}"/>
                                    </c:if>
                                    <c:if test="${item.action eq 'TAKE_OUT'}">
                                        <i style="color: green" class="fas fa-cloud-upload-alt"></i><fmt:message
                                            key="${item.action}"/>
                                    </c:if>
                                </td>
                                <td style="text-align: center;" colspan="2">
                                    <fmt:formatDate value="${item.createDate}" type="date"
                                                    pattern="YYYY-MM-dd HH:MM:ss"/>
                                </td>
                                <td style="text-align: center;">${item.user.fullName}</td>
                                <td style="text-align: center;">${item.description}</td>
                                <td style="text-align: center;">
                                    <c:if test="${item.needRepair eq false}">
                                        <i style="color: green" class="fas fa-thumbs-up"></i>
                                    </c:if>
                                    <c:if test="${item.needRepair eq true}">
                                        <i style="color: red" class="fas fa-exclamation-circle"></i>
                                    </c:if>
                                </td>

                                <c:if test="${item.rating == 5}">
                                    <td style="text-align: center; background-color: green">
                                        <strong><fmt:message key="tool-status.rating.very.good"/></strong>
                                    </td>
                                </c:if>

                                <c:if test="${item.rating == 4}">
                                    <td style="text-align: center; background-color: yellowgreen">
                                        <strong><fmt:message key="tool-status.rating.good"/></strong>
                                    </td>
                                </c:if>

                                <c:if test="${item.rating eq 3}">
                                    <td style="text-align: center; background-color: yellow">
                                        <strong><fmt:message key="tool-status.rating.average"/></strong>
                                    </td>
                                </c:if>

                                <c:if test="${item.rating eq 2}">
                                    <td style="text-align: center; background-color: orangered">
                                        <strong><fmt:message key="tool-status.rating.bad"/></strong>
                                    </td>
                                </c:if>

                                <c:if test="${item.rating eq 1}">
                                    <td style="text-align: center; background-color: red">
                                        <strong><fmt:message key="tool-status.rating.very.bad"/></strong>
                                    </td>
                                </c:if>

                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr>
                            <th colspan="8" style="height:40px;"></th>
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
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });

</script>

<style>
    .dotted {
        border: none;
        border-bottom: 1px dotted #b0b2b5;
        padding-bottom: 12px;
        text-align: left;
    }

    .rating-style {
        text-align: center;
        border-radius: 6px;
    }
</style>