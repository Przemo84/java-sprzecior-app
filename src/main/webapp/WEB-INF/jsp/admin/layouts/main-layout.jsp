<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="currentUrl" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="currentQuery" value="${requestScope['javax.servlet.forward.request_uri']}"/>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <!-- Apple devices fullscreen -->
    <meta name="apple-mobile-web-app-capable" cologontent="yes"/>
    <!-- Apple devices fullscreen -->
    <meta names="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="" name="description"/>
    <meta content="" name="author"/>

    <title>Sprzęcior - Panel Administratora</title>

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">


    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">


    <link href="<c:url value="/resources/summernote/summernote-lite.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/sb-admin.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-custom.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/themes.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/cropper/cropper.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/chosen/chosen.css"/>" rel="stylesheet"/>

    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-datetimepicker.min.css"/>"/>

    <script src="<c:url value="/resources/jquery4.js"/>"></script>
    <script src="<c:url value="/resources/boot.js"/>"></script>

<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script>--%>

<%--    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"--%>
<%--            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"--%>
<%--            crossorigin="anonymous"></script>--%>
    <script src="<c:url value="/resources/summernote/summernote-lite.js"/>"></script>
    <script src="<c:url value="/resources/js/sb-admin.js"/>"></script>
<%--    <script src="<c:url value="/resources/js/jquery/jquery.ui.js"/> "></script>--%>

    <script src="<c:url value="/resources/js/jquery/jquery.alerts.js"/> "></script>
    <script src="<c:url value="/resources/js/jquery/bootbox.js"/> "></script>
    <script src="<c:url value="/resources/js/moment.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js"/>"></script>
    <script src="<c:url value="/resources/js/date.js"/> "></script>
    <script src="<c:url value="/resources/js/application.min.js"/> "></script>
<%--    <script src="<c:url value="/resources/js/jquery/jquery.custom.js"/> "></script>--%>
    <script src="<c:url value="/resources/js/cropper/cropper.js"/>"></script>
    <script src="<c:url value="/resources/js/chosen/chosen.jquery.js"/>"></script>

    <script src="<c:url value="/resources/Chart.min.js"/>"></script>

    <script>
        $(document).ready(function () {
            $('.wysiwyg-small').summernote({
                height: "120px"
            });

            $('.wysiwyg').summernote({
                height: "500px"
            });

            $('.datepicker').datetimepicker({
                format: 'YYYY-MM-DD HH:mm',
                sideBySide: true,
                locale: 'PL',
                icons: {
                    time: "fa fa-clock-o",
                    date: "fa fa-calendar",
                    up: "fa fa-arrow-up",
                    down: "fa fa-arrow-down",
                    next: 'fa fa-chevron-circle-right',
                    previous: 'fa fa-chevron-circle-left'
                }
            });

            $('.date_only_picker').datetimepicker({
                format: 'YYYY-MM-DD',
                sideBySide: true,
                locale: 'PL',
                icons: {
                    time: "fa fa-clock-o",
                    date: "fa fa-calendar",
                    up: "fa fa-arrow-up",
                    down: "fa fa-arrow-down",
                    next: 'fa fa-chevron-circle-right',
                    previous: 'fa fa-chevron-circle-left'
                }
            });

            if ($('.sidebar-left').height() < window.outerHeight) {
                $('.sidebar-left').css('bottom', '0')
            }

        });

        function check(field) {
            if (checkflag == "false") {
                for (i = 0; i < field.length; i++) {
                    field[i].checked = true;
                }
                checkflag = "true";
                return "check_all";
            } else {
                for (i = 0; i < field.length; i++) {
                    field[i].checked = false;
                }
                checkflag = "false";
                return "check_none";
            }
        }

        function check_all_box(form) {
            var checked = 0;
            if (form) {
                for (var i = 0; i < form.length; i++) {
                    if (form[i].value != 'check_none') {
                        if (form[i].type == 'checkbox') {
                            form[i].checked = !form[i].checked;
                        }
                    }
                }
            }
        }


        function confirm_action(redirect_uri) {
            bootbox.confirm({
                title: '<h3>Uwaga</h3>',
                message: '<p>Czy na pewno chcesz wykonać wybraną akcję?</p>',
                buttons: {
                    confirm: {
                        label: 'OK',
                        className: 'btn-primary'
                    },
                    cancel: {
                        label: 'Anuluj',
                        className: 'btn'
                    }
                },
                callback: function (result) {
                    if (result)
                        window.location.href = redirect_uri;
                }

            });
        }
    </script>

    <style>
        .panel-heading {
            border-left: 1px solid #ddd;
        }
    </style>

</head>
<body class="theme-darkblue">

<div id="navigation">
    <tiles:insertAttribute name="header"/>
</div>

<div class="container-fluid" id="content">
    <div class="ui-sortable ui-resizable" id="left">
        <tiles:insertAttribute name="menu"/>
        <div class="ui-resizable-handle ui-resizable-e" style="z-index: 1000; height: 164px; top: 0px;"></div>
    </div>

    <div id="main">
        <div class="container-fluid">
            <c:if test="${not empty msg}">
                <div class="alert alert-${css} alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert"
                            aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <strong>${msg}</strong>
                </div>
            </c:if>

            <tiles:insertAttribute name="body"/>
        </div>
    </div>
</div>
</body>
</html>

<script>
    var time = ${pageContext.session.maxInactiveInterval};
    var timeCounter = time;

    setInterval(function () {
        timeCounter--;
        $('.auth-timer > span').text(timeCounter)
    }, 1000);

    setTimeout(function () {
        window.location.href = "<c:url value="/signout"/>";
    }, time * 1000);

    $('#session-regenerate').click(function () {
        $.get('<c:url value="/admin"/>').done(function () {
            timeCounter = time
            $('.auth-timer > span').text(time)
        })
    })
</script>