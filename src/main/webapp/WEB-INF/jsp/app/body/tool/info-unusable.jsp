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
                    <p class="h4 dotted">
                        ID: <strong>${tool.id}</strong>
                    </p>
                    <p class="h4 dotted">
                        Nordgeo ID: <strong>${tool.companyId}</strong>
                    </p>
                    <p class="h4 dotted">
                        Przyczyna:
                        <strong>${tool.unusableReason}</strong>
                    </p>
                    <p class="h4 dotted">
                        Data przeniesienia do nieużytków:
                        <strong><fmt:formatDate value="${tool.unusableDate}" pattern="YYYY-MM-dd HH:MM:ss"/></strong>
                    </p>

                    <p class="h4 dotted">
                        Model: <strong>${tool.model}</strong>
                    </p>

                    <p class="h4 dotted">
                        Rodzaj sprzętu: <strong>${tool.toolType}</strong>
                    </p>
                    <p class="h4 dotted">
                        Serial No: <strong>${tool.serialNo} </strong>
                    </p>
                    <p class="h4 dotted">
                        Data produkcji: <strong><fmt:formatDate value="${tool.productionDate}"
                                                                pattern="YYYY-MM"/></strong>
                    </p>
                    <p class="h4 dotted">
                        Data kalibracji: <strong><fmt:formatDate value="${tool.calibrationDate}"
                                                                 pattern="YYYY-MM"/></strong>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<style>
    .dotted  {
        border:none;
        border-bottom:1px dotted #b0b2b5;
        padding-bottom: 12px;
    }
</style>
