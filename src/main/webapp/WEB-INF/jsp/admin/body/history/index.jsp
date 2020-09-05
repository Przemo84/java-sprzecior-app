<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="page-header">
    <div class="pull-left">
        <h1><fmt:message key="menu.action.history"/></h1>
    </div>
</div>
<div class="breadcrumbs">
    <ul>
        <li>
            <a href="<c:url value="${baseUrl}"/>"><fmt:message key="home"/></a>
            <i class="icon-angle-right"></i>
        </li>
        <li>
            <a href="<c:url value="${moduleBaseUrl}"/>"><fmt:message key="menu.action.history"/></a>
        </li>
    </ul>
    <div class="close-bread">
        <a href="#"><i class="icon-remove"></i></a>
    </div>
</div>

<br>
<div class="row-fluid">
    <div class="span12">
        <div class="box">
            <div class="box-content nopadding">
                <div class="dataTables_wrapper">
                    <div class="row">
                        <div class="col-md-12 pull-right">
                            <div class="well ">
                                <form class="form-inline" action="${moduleBaseUrl}">
                                    <div class="form-group">
                                        <label>Od</label>
                                        <input type="text" class="form-control date_only_picker" name="startDate"
                                               value="${param.startDate}" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Do</label>
                                        <input type="text" class="form-control date_only_picker" name="endDate"
                                               value="${param.endDate}" required>
                                    </div>
                                    <button type="submit" class="btn btn-default">Filtruj</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="dataTables_length">
                        <label>
                            <div class="btn-group" style="float: left;">
                                <a href="#" data-toggle="dropdown" class="btn dropdown-toggle">
                                    ${(not empty param.size) ? param.size : 100}<span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <c:if test="${param.size != 10}">
                                        <li>
                                            <a href="<c:url value="${moduleBaseUrl}?size=10&sort=${param.sort}&order=${param.order}&page=${param.page}&startDate=${param.startDate}&endDate=${param.endDate}"/> ">10</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${param.size != 25}">
                                        <li>
                                            <a href="<c:url value="${moduleBaseUrl}?size=25&sort=${param.sort}&order=${param.order}&page=${param.page}&startDate=${param.startDate}&endDate=${param.endDate}"/> ">25</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${param.size != 50}">
                                        <li>
                                            <a href="<c:url value="${moduleBaseUrl}?size=50&sort=${param.sort}&order=${param.order}&page=${param.page}&startDate=${param.startDate}&endDate=${param.endDate}"/> ">50</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${param.size != 100}">
                                        <li>
                                            <a href="<c:url value="${moduleBaseUrl}?size=100&sort=${param.sort}&order=${param.order}&page=${param.page}&startDate=${param.startDate}&endDate=${param.endDate}"/> ">100</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                            <span><fmt:message key="elements.per.page"/> </span>
                        </label>
                    </div>

                    <div style="float: right; margin: 10px 10px 5px 5px">
                        <a href="<c:url value="${moduleBaseUrl}/export?startDate=${param.startDate}&endDate=${param.endDate}"/>"
                           class="btn">
                            <i class="icon-upload-alt"></i>
                            Export
                        </a>
                    </div>

                    <table class="table table-hover table-nomargin table-striped table-bordered" style="clear: both;">
                        <thead>
                        <tr>
                            <th>
                                <a href="<c:out value="?sort=user&order=${order}&size=${param.size}&page=${param.page}&startDate=${param.startDate}&endDate=${param.endDate}" />">
                                    Użytkownik
                                </a>
                            </th>
                            <th>
                                <a href="<c:out value="?sort=actionName&order=${order}&size=${param.size}&page=${param.page}&startDate=${param.startDate}&endDate=${param.endDate}" />">
                                    Akcja
                                </a>
                            </th>
                            <th>
                                <a href="<c:out value="?sort=createDate&order=${order}&size=${param.size}&page=${param.page}&startDate=${param.startDate}&endDate=${param.endDate}" />">
                                    Data
                                </a>
                            </th>
                        </tr>
                        </thead>
                        <tbody class="ui-sortable">
                        <c:forEach var="item" items="${page.iterator()}">
                            <tr>
                                <td>${item.user.fullName}</td>
                                <td>${item.actionName}</td>
                                <td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="pagination pagination-small">
            <tag:paginate pageData="${page}" next="&#62;" previous="&#60;"/>
        </div>
    </div>
</div>

<style>
    td {
        text-align: center;
    }

    th {
        text-align: center;
    }

    .table > tfoot > tr > th {
        text-align: left;
    }
</style>
