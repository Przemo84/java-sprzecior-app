<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="wrapper">
    <div class="login-body">
        <h2>ZALOGUJ SIĘ</h2>
        <form autocomplete="new-password" action='<spring:url value="/signin"/>' method="post" class="form-signin" autocomplete="off">
            <div class="email">
                <input type="text" autocomplete="off" name='userid' placeholder="Login" class='input-block-level'>
            </div>
            <div class="pw">
                <input type="password" autocomplete="new-password" name="passwd" placeholder="Hasło" class='input-block-level'>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="submit">
                <input type="submit" value="Zaloguj" class='btn btn-primary'>
            </div>
        </form>
        <div class="forget">
            <a href="#lost-password" data-toggle="modal">
                <span>Zapomniałeś hasła?</span>
            </a>
        </div>
    </div>
</div>
<div id="lost-password" class="modal hide" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">Resetowanie hasła</h3>
    </div>
    <form action="/password/reset" method="POST" class="lost-password-form form-horizontal form-bordered" autocomplete="OFF">
        <div class="modal-body nopadding">
            <div class="alert alert-info" style="margin-bottom: 0px;">
                <button data-dismiss="alert" class="close" type="button">×</button>
                Na podany e-mail zostanie przesłany link umożliwiający zresetowanie hasła
            </div>
            <div class="control-group">
                <label for="email" class="control-label">E-mail</label>
                <div class="controls">
                    <input type="text" name="email" id="email">
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <input type="submit" class="btn btn-primary" value="Wyślij">
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <br/>
    <c:if test="${not empty sessionScope.message}">
        <span style="color:green"><c:out value="${sessionScope.message}"/></span>
        <c:remove var="message" scope="session"/>
    </c:if>
</div>

<div style="color: white; text-align: center; position: absolute ;bottom: 0;left: 0;margin: 0 auto; right: 0;">
    Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy"/>
    <a href="http://nordgeo.pl" style="color: white;">Nordgeo</a>
</div>

<script type="text/javascript">
    jQuery(document).ready(function () {
        jQuery('.lost-password-form').ajaxForm({
            dataType: 'json',
            success: function (data, statusText, xhr, $form) {
                jQuery().toastmessage('showToast', {
                    text: data.message,
                    position: 'middle-center',
                    sticky: true,
                    type: data.status
                });
            }
        });
    });
</script>
