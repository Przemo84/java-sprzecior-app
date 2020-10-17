<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="page-header">
    <div class="pull-left">
        <h1><fmt:message key="tool.unusable.info"/></h1>
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
            <i class="icon-angle-right"></i>
        </li>
        <li>
            <a href="<c:url value="${moduleBaseUrl}/unusable/${tool.id}"/>"><fmt:message key="tool.unusable.info"/></a>
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
                <div class="tab-content padding tab-content-inline tab-content-bottom">
                    <h3 class="dotted">
                        ID: <strong>${tool.id}</strong>
                    </h3>
                    <h3 class="dotted">
                        Nordgeo ID: <strong>${tool.companyId}</strong>
                    </h3>
                    <h3 class="dotted">
                        Przyczyna: </br>
                        <strong>${tool.unusableReason}</strong>
                    </h3>
                    <h3 class="dotted">
                        Data przeniesienia do nieużytków:
                        <strong><fmt:formatDate value="${tool.unusableDate}" pattern="YYYY-MM-dd HH:MM:ss"/></strong>
                    </h3>

                    <h3 class="dotted">
                        Model: <strong>${tool.model}</strong>
                    </h3>

                    <h3 class="dotted">
                        Rodzaj sprzętu: <strong>${tool.toolType}</strong>
                    </h3>
                    <h3 class="dotted">
                        Serial No: <strong>${tool.serialNo} </strong>
                    </h3>
                    <h3 class="dotted">
                        Średnia ocen:
                        <strong>
                            <div style="width:150px">
                                <c:if test="${tool.averageRating >= 4.0}">
                                    <h3 style="text-align: center; background-color: green">
                                        <strong><fmt:formatNumber type="number" maxFractionDigits="2"
                                                                  value="${tool.averageRating}"/></strong>
                                    </h3>
                                </c:if>

                                <c:if test="${tool.averageRating < 4.0 and tool.averageRating >= 3.0}">
                                    <h3 style="text-align: center; background-color: yellowgreen">
                                        <strong><fmt:formatNumber type="number" maxFractionDigits="2"
                                                                  value="${tool.averageRating}"/></strong>
                                    </h3>
                                </c:if>

                                <c:if test="${tool.averageRating < 3.0 and tool.averageRating >= 2.0}">
                                    <h3 style="text-align: center; background-color: yellow">
                                        <strong><fmt:formatNumber type="number" maxFractionDigits="2"
                                                                  value="${tool.averageRating}"/></strong>
                                    </h3>
                                </c:if>

                                <c:if test="${tool.averageRating < 2.0}">
                                    <h3 style="text-align: center; background-color: red">
                                        <strong><fmt:formatNumber type="number" maxFractionDigits="2"
                                                                  value="${tool.averageRating}"/></strong>
                                    </h3>
                                </c:if>

                                <c:if test="${tool.averageRating eq null}">
                                    <h3 style="width: 32px; text-align: center;">
                                    </h3>
                                </c:if>
                            </div>
                        </strong>
                    </h3>
                    <h3 class="dotted">
                        Data produkcji: <strong><fmt:formatDate value="${tool.productionDate}"
                                                                pattern="YYYY-MM"/></strong>
                    </h3>
                    <h3 class="dotted">
                        Data kalibracji: <strong><fmt:formatDate value="${tool.calibrationDate}"
                                                                 pattern="YYYY-MM"/></strong>
                    </h3>
                </div>
            </div>
        </div>
    </div>
</div>

<style>
    .dotted  {
        border:none;
        border-bottom:1px dotted #b0b2b5;
        padding-bottom: 20px;
    }
</style>
