<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="page-header">
    <div class="pull-left">
        <h1><fmt:message key="menu.roles"/></h1>
    </div>
</div>

<div class="breadcrumbs">
    <ul>
        <li>
            <a href="<c:url value="${baseUrl}"/>"><fmt:message key="home"/></a>
            <i class="icon-angle-right"></i>
        </li>
        <li>
            <a href="<c:url value="${moduleBaseUrl}"/>"><fmt:message key="menu.roles"/></a>
        </li>
    </ul>
    <div class="close-bread">
        <a href="#"><i class="icon-remove"></i></a>
    </div>
</div>
</br>
</br>

<div class="panel panel-shadow col-md-12">
    <table class="table-role">
        <thead>
        <tr>
            <th>Rola</th>
            <th class="rotate" style="text-align: center">
                <div><span>Moduł Kalendarium</span></div>
            </th>
            <th class="rotate" style="text-align: center">
                <div><span>Moduł Rynki</span></div>
            </th>
            <th class="rotate" style="text-align: center">
                <div><span>Moduł Prognozy</span></div>
            </th>
            <th class="rotate" style="text-align: center">
                <div><span>Moduł Wiadomości</span></div>
            </th>
            <th class="rotate" style="text-align: center">
                <div><span>Moduł Ankiety</span></div>
            </th>
            <th class="rotate" style="text-align: center">
                <div><span>Moduł Komentarze</span></div>
            </th>
            <th class="rotate" style="text-align: center">
                <div><span>Moduł Treść Zakładek</span></div>
            </th>
            <th class="rotate" style="text-align: center">
                <div><span>Moduł Teksty Informacyjne</span></div>
            </th>
            <th class="rotate" style="text-align: center">
                <div><span>Moduł Analityka</span></div>
            </th>
            <th class="rotate" style="text-align: center">
                <div><span>Moduł Kody</span></div>
            </th>
            <th class="rotate" style="text-align: center">
                <div><span>Moduł Administracja</span></div>
            </th>
            <th class="rotate" style="text-align: center">
                <div><span>Moduł Konserwacji i utrzymania</span></div>
            </th>
            <th/>
            <th/>

        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="role-title">
                Administrator
            </td>
            <td align="center" style="text-align: center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked="false" disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked="false" disabled>
            </td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="role-title">
                Zarządca PIN
            </td>
            <td align="center" style="text-align: center">
                <input type="checkbox" disabled>
            </td>
            <td align="center">
                <input type="checkbox" disabled>
            </td>
            <td align="center">
                <input type="checkbox" disabled>
            </td>
            <td align="center">
                <input type="checkbox" disabled>
            </td>
            <td align="center">
                <input type="checkbox" disabled>
            </td>
            <td align="center">
                <input type="checkbox" disabled>
            </td>
            <td align="center">
                <input type="checkbox" disabled>
            </td>
            <td align="center">
                <input type="checkbox" disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" disabled>
            </td>
            <td align="center">
                <input type="checkbox" disabled>
            </td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="role-title">
                Dealer
            </td>
            <td align="center" style="text-align: center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" disabled>
            </td>
            <td align="center">
                <input type="checkbox" disabled>
            </td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>

<div class="panel panel-shadow col-md-6">
    <table class="table-role">
        <thead>
        <tr>
            <th></th>
            <th style="text-align: center">
                <div><span>ADMINISTRATOR</span></div>
            </th>
            <th style="text-align: center">
                <div><span>ZARZĄDCA PIN</span></div>
            </th>
            <th style="text-align: center">
                <div><span>DEALER</span></div>
            </th>
            </th></th></th></th></th>

        </tr>
        </thead>
        <tbody>
        <tr>
            <td style="font-size: large">
                Lista
            </td>
            <td align="center" style="text-align: center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td style="font-size: large">
                Zmiana statusu
            </td>
            <td align="center" style="text-align: center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td style="font-size: large">
                Generowanie
            </td>
            <td align="center" style="text-align: center">
                <input type="checkbox" disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td style="font-size: large">
                Opiekun PINu
            </td>
            <td align="center" style="text-align: center">
                <input type="checkbox" disabled>
            </td>
            <td align="center">
                <input type="checkbox" disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td></td>
            <td></td>
        </tr>


        <tr>
            <td style="font-size: large">
                Zmiana przypisania
            </td>
            <td align="center" style="text-align: center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" checked disabled>
            </td>
            <td align="center">
                <input type="checkbox" disabled>
            </td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>



