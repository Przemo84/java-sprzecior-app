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
        <div class="box">
            <div class="box-content nopadding">
                <div class="dataTables_wrapper">
                    <div class="dataTables_length">
                        <label>
                            <div class="btn-group" style="float: left;">
                                <a href="#" data-toggle="dropdown" class="btn dropdown-toggle">10 <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="<c:url value="${moduleBaseUrl}"/>/admin/admins?size=10">10</a>
                                    </li>
                                    <li>
                                        <a href="<c:url value="${moduleBaseUrl}"/>/admin/admins?size=25">25</a>
                                    </li>
                                    <li>
                                        <a href="<c:url value="${moduleBaseUrl}"/>/admin/admins?size=50">50</a>
                                    </li>
                                    <li>
                                        <a href="<c:url value="${moduleBaseUrl}"/>/admin/admins?size=100">100</a>
                                    </li>
                                </ul>
                            </div>
                            <span>Elementów na strone</span>
                        </label>
                    </div>
                    <div style="float: right; margin: 10px 10px 5px 5px">
                        <a href="<c:url value="${moduleBaseUrl}/form"/>" class="btn"><i class="icon-plus"></i>
                            Dodaj</a>
                    </div>
                    <form autocomplete="off" action="<c:url value="${moduleBaseUrl}"/>/admin/admins/checkbox"
                          method="post">
                        <table class="table table-hover table-nomargin table-striped table-bordered" style="clear: both;">
                            <thead>
                            <tr>
                                <th style="text-align: center;"><input type="checkbox" value="check_none"
                                                                       onclick="javascript:check_all_box(this.form)"></th>
                                <th style="text-align: center;"><a
                                        href="/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins?sort=firstname&amp;how=ASC">Imię</a>
                                </th>
                                <th style="text-align: center;"><a
                                        href="/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins?sort=lastname&amp;how=ASC">Nazwisko</a>
                                </th>
                                <th style="text-align: center;"><a
                                        href="/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins?sort=email&amp;how=ASC">E-mail</a>
                                </th>
                                <th style="text-align: center;"><a
                                        href="/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins?sort=username&amp;how=ASC">Nazwa
                                    użytkownika</a></th>
                                <th style="text-align: center;"><a
                                        href="/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins?sort=lastlogin&amp;how=ASC">Ostatnie
                                    logowanie</a></th>
                                <th style="text-align: center;" colspan="2">Opcje</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th colspan="8">
                                    <img src="/resources/img/arrow_ltr.png">&nbsp;
                                    <select name="action">
                                        <option value="0"><fmt:message key="select.list.select"/> </option>
                                        <option value="delete"><fmt:message key="select.list.lock"/> </option>
                                    </select>&nbsp;<button class="btn btn-primary" type="submit"><fmt:message key="select.list.execute"/> </button>
                                </th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <tr>
                                <td style="width: 32px; text-align: center;">
                                    <input type="checkbox" name="list[]" value="26">
                                </td>
                                <td style="text-align: center;">
                                    Anna
                                </td>
                                <td style="text-align: center;">
                                    Bednarska
                                </td>
                                <td style="text-align: center;">
                                    anna2.bednarska@citi.com
                                </td>
                                <td style="text-align: center;">
                                    annabednarska
                                </td>
                                <td style="text-align: center;">
                                    27.06.2018
                                </td>
                                <td style="width: 32px; text-align: center;">
                                    <a href="/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins/edit/26" rel="tooltip"
                                       title="Edytuj" class="btn">
                                        <i class="icon-pencil"></i>
                                    </a>
                                </td>
                                <td style="width: 32px; text-align: center;">
                                    <a href="javascript:void(0);"
                                       onclick="javascript:confirm_action('/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins/delete/26');"
                                       rel="tooltip" title="Zablokuj" class="btn btn-danger">
                                        <i class="icon-minus-sign"></i>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 32px; text-align: center;">
                                    <input type="checkbox" name="list[]" value="27">
                                </td>
                                <td style="text-align: center;">
                                    Jovanka
                                </td>
                                <td style="text-align: center;">
                                    Uliver Romaniuk
                                </td>
                                <td style="text-align: center;">
                                    jovanka.uliverromaniuk@citi.com
                                </td>
                                <td style="text-align: center;">
                                    jovankauliverromaniuk
                                </td>
                                <td style="text-align: center;">
                                    19.11.2018
                                </td>
                                <td style="width: 32px; text-align: center;">
                                    <a href="/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins/edit/27" rel="tooltip"
                                       title="Edytuj" class="btn">
                                        <i class="icon-pencil"></i>
                                    </a>
                                </td>
                                <td style="width: 32px; text-align: center;">
                                    <a href="javascript:void(0);"
                                       onclick="javascript:confirm_action('/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins/delete/27');"
                                       rel="tooltip" title="Zablokuj" class="btn btn-danger">
                                        <i class="icon-minus-sign"></i>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 32px; text-align: center;">
                                    <input type="checkbox" name="list[]" value="28">
                                </td>
                                <td style="text-align: center;">
                                    Michał
                                </td>
                                <td style="text-align: center;">
                                    Naziębło
                                </td>
                                <td style="text-align: center;">
                                    michal.nazieblo@citi.com
                                </td>
                                <td style="text-align: center;">
                                    michalnazieblo
                                </td>
                                <td style="text-align: center;">
                                    20.11.2018
                                </td>
                                <td style="width: 32px; text-align: center;">
                                    <a href="/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins/edit/28" rel="tooltip"
                                       title="Edytuj" class="btn">
                                        <i class="icon-pencil"></i>
                                    </a>
                                </td>
                                <td style="width: 32px; text-align: center;">
                                    <a href="javascript:void(0);"
                                       onclick="javascript:confirm_action('/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins/delete/28');"
                                       rel="tooltip" title="Zablokuj" class="btn btn-danger">
                                        <i class="icon-minus-sign"></i>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 32px; text-align: center;">
                                    <input type="checkbox" name="list[]" value="29">
                                </td>
                                <td style="text-align: center;">
                                    Bartłomiej
                                </td>
                                <td style="text-align: center;">
                                    Łada
                                </td>
                                <td style="text-align: center;">
                                    bartlomiej.lada@citi.com
                                </td>
                                <td style="text-align: center;">
                                    bartlomiejlada
                                </td>
                                <td style="text-align: center;">
                                    12.06.2018
                                </td>
                                <td style="width: 32px; text-align: center;">
                                    <a href="/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins/edit/29" rel="tooltip"
                                       title="Edytuj" class="btn">
                                        <i class="icon-pencil"></i>
                                    </a>
                                </td>
                                <td style="width: 32px; text-align: center;">
                                    <a href="javascript:void(0);"
                                       onclick="javascript:confirm_action('/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins/delete/29');"
                                       rel="tooltip" title="Zablokuj" class="btn btn-danger">
                                        <i class="icon-minus-sign"></i>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 32px; text-align: center;">
                                    <input type="checkbox" name="list[]" value="30">
                                </td>
                                <td style="text-align: center;">
                                    Piotr
                                </td>
                                <td style="text-align: center;">
                                    Kalisz
                                </td>
                                <td style="text-align: center;">
                                    piotr.kalisz@citi.com
                                </td>
                                <td style="text-align: center;">
                                    piotrkalisz
                                </td>
                                <td style="text-align: center;">
                                    21.09.2018
                                </td>
                                <td style="width: 32px; text-align: center;">
                                    <a href="/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins/edit/30" rel="tooltip"
                                       title="Edytuj" class="btn">
                                        <i class="icon-pencil"></i>
                                    </a>
                                </td>
                                <td style="width: 32px; text-align: center;">
                                    <a href="javascript:void(0);"
                                       onclick="javascript:confirm_action('/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins/delete/30');"
                                       rel="tooltip" title="Zablokuj" class="btn btn-danger">
                                        <i class="icon-minus-sign"></i>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 32px; text-align: center;">
                                    <input type="checkbox" name="list[]" value="31">
                                </td>
                                <td style="text-align: center;">
                                    Cezary
                                </td>
                                <td style="text-align: center;">
                                    Chrapek
                                </td>
                                <td style="text-align: center;">
                                    cezary.chrapek@citi.com
                                </td>
                                <td style="text-align: center;">
                                    cezarychrapek
                                </td>
                                <td style="text-align: center;">
                                    21.11.2018
                                </td>
                                <td style="width: 32px; text-align: center;">
                                    <a href="/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins/edit/31" rel="tooltip"
                                       title="Edytuj" class="btn">
                                        <i class="icon-pencil"></i>
                                    </a>
                                </td>
                                <td style="width: 32px; text-align: center;">
                                    <a href="javascript:void(0);"
                                       onclick="javascript:confirm_action('/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins/delete/31');"
                                       rel="tooltip" title="Zablokuj" class="btn btn-danger">
                                        <i class="icon-minus-sign"></i>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 32px; text-align: center;">
                                    <input type="checkbox" name="list[]" value="42">
                                </td>
                                <td style="text-align: center;">
                                    Piotr
                                </td>
                                <td style="text-align: center;">
                                    Gołasz
                                </td>
                                <td style="text-align: center;">
                                    piotr.golasz@etendard.pl
                                </td>
                                <td style="text-align: center;">
                                    piotrgolasz
                                </td>
                                <td style="text-align: center;">
                                    21.11.2018
                                </td>
                                <td style="width: 32px; text-align: center;">
                                    <a href="/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins/edit/42" rel="tooltip"
                                       title="Edytuj" class="btn">
                                        <i class="icon-pencil"></i>
                                    </a>
                                </td>
                                <td style="width: 32px; text-align: center;">
                                    <a href="javascript:void(0);"
                                       onclick="javascript:confirm_action('/ed5446b508a68e45531f4694dcb4edd125e2eb04/admin/admins/delete/42');"
                                       rel="tooltip" title="Zablokuj" class="btn btn-danger">
                                        <i class="icon-minus-sign"></i>
                                    </a>
                                </td>
                            </tr>


                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>