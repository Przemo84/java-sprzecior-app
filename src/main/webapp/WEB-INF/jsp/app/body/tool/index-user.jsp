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
        <h1><fmt:message key="menu.action.tools.user"/></h1>
    </div>
</div>

<div class="breadcrumbs">
    <ul>
        <li>
            <a href="<c:url value="${baseUrl}"/>"><fmt:message key="home"/></a>
            <i class="icon-angle-right"></i>
        </li>
        <li>
            <a href="<c:url value="${moduleBaseUrl}/user"/>"><fmt:message key="menu.action.tools.user"/></a>
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
                                            <a href="<c:url value="${moduleBaseUrl}/user?size=10&sort=${param.sort}&order=${param.order}&page=${param.page}"/> ">10</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${param.size != 25}">
                                        <li>
                                            <a href="<c:url value="${moduleBaseUrl}/user?size=25&sort=${param.sort}&order=${param.order}&page=${param.page}"/> ">25</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${param.size != 50}">
                                        <li>
                                            <a href="<c:url value="${moduleBaseUrl}/user?size=50&sort=${param.sort}&order=${param.order}&page=${param.page}"/> ">50</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${param.size != 100}">
                                        <li>
                                            <a href="<c:url value="${moduleBaseUrl}/user?size=100&sort=${param.sort}&order=${param.order}&page=${param.page}"/> ">100</a>
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
                                <tag:th param="available">
                                    <fmt:message key="tool.is.available"/>
                                </tag:th>
                            </th>

                            <th style="text-align: center;">
                                <tag:th param="user">
                                    <fmt:message key="tool.user"/>
                                </tag:th>
                            </th>

                            <th style="text-align: center;">
                                <tag:th param="takenDate">
                                    <fmt:message key="tool.taken.date"/>
                                </tag:th>
                            </th>
                            <th style="text-align: center;">
                                <tag:th param="model">
                                    <fmt:message key="tool.model"/>
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
                            <th style="text-align: center;" colspan="1"><fmt:message key="action.return"/></th>
                        </tr>
                        </thead>

                        <tbody id="toolTable">
                        <c:forEach var="item" items="${page.iterator()}">
                            <tr>
                            <tr>
                                <td style="text-align: center;">
                                    <a href="<c:url value="${moduleBaseUrl}/status/${item.id}"/>"
                                       rel="tooltip" title="Zobacz historię sprzetu">
                                            ${item.id}
                                    </a>
                                </td>
                                <td style="text-align: center;">
                                    <a href="<c:url value="${moduleBaseUrl}/status/${item.id}"/>"
                                       rel="tooltip" title="Zobacz historię sprzetu">
                                            ${item.companyId}
                                    </a>
                                </td>

                                <td style="text-align: center;">
                                    <c:if test="${item.available}">
                                        <i class="icon-ok" style="color: green"></i>
                                    </c:if>
                                    <c:if test="${item.available eq false}">
                                        <i class="icon-minus" style="color: red"></i>
                                    </c:if>
                                </td>
                                <td style="text-align: center;">${item.user.fullName}</td>
                                <td style="text-align: center;">
                                    <fmt:formatDate value="${item.takenDate}" pattern="YYYY-MM-D HH:MM:ss"/>
                                </td>

                                <td style="text-align: center;">
                                    <a href="<c:url value="${moduleBaseUrl}/status/${item.id}"/>"
                                       rel="tooltip" title="Zobacz historię sprzetu">
                                            ${item.model}
                                    </a>
                                </td>
                                <td style="text-align: center;">${item.toolType}</td>
                                <td style="text-align: center;">${item.serialNo}</td>
                                <td style="text-align: center;">
                                    <fmt:formatDate value="${item.productionDate}" pattern="YYYY-MM"/>
                                </td>
                                <td style="text-align: center;">
                                    <fmt:formatDate value="${item.calibrationDate}" pattern="YYYY-MM"/>
                                </td>

                                <td style="text-align: center" colspan="1">
                                    <a href="javascript:void(0);"
                                       rel="tooltip" title="Zwróć" class="btn btn-danger" data-toggle="modal"
                                       data-target="#myModal" data-tool-id="${item.id}" id="toolModal">
                                        <i class="icon-remove"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>

                        <tfoot>
                        <tr>
                            <th colspan="11"></th>
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

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <spring:url value="${moduleBaseUrl}/return" var="action"/>
            <form:form method="post" class="form-vertical form-bordered" enctype="multipart/form-data"
                       modelAttribute="toolStatus" action="${action}" id="newModalForm">
            <div class="modal-header">
                <h4 class="modal-title">Oceń oddawany Sprzęt</h4>
            </div>
            <form:hidden path="id"/>

            <form:hidden path="tool" id="toolId"/>

            <spring:bind path="description">
                <div class="control-group">
                    <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                        <label class="control-label" for="description"><fmt:message
                                key="tool-status.description"/></label>
                        <form:input path="description" type="text" class="form-control"/>
                        <span style="color: red"><form:errors path="description" class="control-label"/></span>
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="shape">
                <div class="control-group">
                    <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                        <label class="control-label"><fmt:message key="tool-status.shape"/></label>
                        <br>
                        <form:radiobutton path="shape" value="VERY_GOOD" label="Bardzo dobry"/>
                        <br>
                        <form:radiobutton path="shape" value="GOOD" label="Dobry" element="div"/>
                        <br>
                        <form:radiobutton path="shape" value="AVERAGE" label="Średni" element="div"/>
                        <br>
                        <form:radiobutton path="shape" value="BAD" label="Zły" element="div"/>
                        <br>
                        <form:radiobutton path="shape" value="VERY_BAD" label="Bardzo zły" element="div"/>
                        <span style="color: red"><form:errors path="shape" class="control-label"/></span>
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="needRepair">
                <div class="control-group">
                    <div class="form-group-mx-3 ${status.error ? 'has-error' : ''}">
                        <label class="control-label"><fmt:message key="tool-status.need.repair"/></label>
                        <br>
                        <form:radiobutton path="needRepair" element="div" value="true" label="Tak"/>
                        <form:radiobutton path="needRepair" element="div" value="false" label="Nie"/>
                        <span style="color: red"><form:errors needRepair="needRepair" class="control-label"/></span>
                    </div>
                </div>
            </spring:bind>

            <div class="modal-footer">
                <button class="btn btn-primary" type="submit"><fmt:message key="action.save"/></button>
                <button class="btn" data-dismiss="modal"><fmt:message key="action.cancel"/></button>
            </div>
        </div>
        </form:form>

    </div>
</div>

<script>
    $(document).ready(function () {
        $("#filterInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#toolTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });

    $(document).on("click", "#toolModal", function () {
        var toolId = $(this).data('tool-id');
        $(".modal-content #toolId").val(toolId);
    });

    $(function () {
        $("#newModalForm").validate({
            rules: {
                description: {
                    required: true,
                    minlength: 2,
                    maxlength: 250
                },
                action: "required"
            },

            messages: {
                description: {
                    required: "Pole nie może być puste. Wprowadź krótki komentarz oddawanego sprzętu",
                    minlength: "Minimalna długość znaków: 2",
                    maxlength: "Maksymalna długość znaków: 250"
                },
                action: "Proszę wprowadzić krótki komentarz oddawanego sprzętu"
            },
            highlight: function (element) {
                $(element).parent().addClass('error')
            },
            unhighlight: function (element) {
                $(element).parent().removeClass('error')
            }
        }, {
            rules: {
                shape: {
                    required: true,
                },
                action: "required"
            },

            messages: {
                shape: {
                    required: "Pole nie może być puste. Proszę ocenić sprzęt.",
                },
                action: "Proszę ocenić sprzęt."
            },
            highlight: function (element) {
                $(element).parent().addClass('error')
            },
            unhighlight: function (element) {
                $(element).parent().removeClass('error')
            }
        });
    });
</script>
<style>
    #newModalForm .error {
        color: red;
    }
</style>
