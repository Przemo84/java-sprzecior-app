<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<div class="container log-row">
    <div class="form-signin">
        </br>
        </br>
        </br>
        <p style="font-size: 50px;color: white;text-align: center">Błąd 403</p>
        </br>
        <p style="font-size: 36px;color: white; text-align: center">Forbidden</p>
        </br>
        <p style="font-size: 20px;color: white; text-align: center">Nie masz uprawnień do żądanej strony</p>
        </br>
        </br>
        </br>
        <sec:authorize access="hasAnyAuthority('Editor', 'Employee')">
            <div style="text-align: center">
                <a class="btn btn-large btn-green" href="<c:url value="/app/dashboard/"/> ">
                    Powrót do Aplikacji
                </a>
            </div>
        </sec:authorize>

        <sec:authorize access="hasAnyAuthority('Admin')">
            <div style="text-align: center">
                <a class="btn btn-large btn-green" href="<c:url value="/admin/dashboard/"/> ">
                    Powrót do CMS
                </a>
            </div>
        </sec:authorize>
    </div>
    <br/>
<%--    <c:if test="${not empty sessionScope.message}">--%>
<%--        <span style="color:green"><c:out value="${sessionScope.message}"/></span>--%>
<%--        <c:remove var="message" scope="session"/>--%>
<%--    </c:if>--%>
</div>