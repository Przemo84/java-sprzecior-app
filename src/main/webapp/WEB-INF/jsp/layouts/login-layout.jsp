<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="date" class="java.util.Date"/>
<html>
<head>
    <meta charset="utf8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <!-- Apple devices fullscreen -->
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!-- Apple devices fullscreen -->
    <meta names="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="" name="description"/>
    <meta content="" name="author"/>

    <title>admin</title>

    <link href="<c:url value="/resources/css/bootstrap-custom.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-responsive.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/themes.css"/>" rel="stylesheet">

    <script src="<c:url value="/resources/jquery4.js"/>"></script>
<%--    <script src="<c:url value="/resources/js/jquery.js"/> "></script>--%>
<%--    <script src="<c:url value="/resources/js/jquery/jquery.ui.js"/> "></script>--%>
    <script src="<c:url value="/resources/js/jquery/jquery.alerts.js"/> "></script>
    <script src="<c:url value="/resources/js/jquery/jquery.form.js"/> "></script>
    <script src="<c:url value="/resources/js/jquery/jquery.bootbox.js"/> "></script>
    <script src="<c:url value="/resources/js/sb-admin.js"/> "></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/> "></script>
    <script src="<c:url value="/resources/js/date.js"/> "></script>
<%--    <script src="<c:url value="/resources/js/jquery/jquery.custom.js"/> "></script>--%>
    <script src="<c:url value="/resources/js/application.min.js"/> "></script>
</head>

<body class="login theme-darkblue">
<div class="login-logo"  style="text-align: center; padding: 36px"><img src="<c:url value="/resources/img/logo_login.png"/> " alt=""></div>
    <div>
        <tiles:insertAttribute name="body"/>
    </div>
</body>
</html>

