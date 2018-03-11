<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 2/7/2017
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="calendar" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>


    <meta http-equiv="Content-Type" content="application/xhtml+xml; charset=utf-8">

    <title>Title</title>
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">


    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/verticalTab.css">
    <link rel="stylesheet" href="/resources/css/3.css">
    <!--Callendar-->
    <link rel="stylesheet" type="text/css" href="/resources/simplecalendar/tcal.css"/>
    <link rel="stylesheet" href="/resources/css/buttons.css">


    <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="/resources/js/user.js"></script>
    <script src="/resources/js/device.js"></script>

    <script src="/resources/js/workSpace.js"></script>

    <script src="/resources/js/history.js"></script>
    <script src="/resources/js/admin.js"></script>

    <!--allendar-->
    <script type="text/javascript" src="/resources/simplecalendar/tcal_en.js"></script>
    <%--<script src="/resources/js/leftTab.js"></script>--%>

    <%--<script src="/resources/js/jquery-2.1.1.min.js"></script>--%>
    <script src="/resources/js/bootstrap.min.js"></script>

</head>
<body>

<c:url value="/j_spring_security_logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>
<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<c:if test="${pageContext.request.userPrincipal.name != null}">

    <h2 style="margin-left: 80%">
       Hello ${pageContext.request.userPrincipal.name} / <a
            href="javascript:formSubmit()"> Logout</a>
    </h2>
</c:if>

<div class="tabs">

    <input id="tab1" type="radio" name="tabs"

    >
    <label for="tab1" onclick="showUser()">
        Users</label>

    <input id="tab2" type="radio" name="tabs">
    <label for="tab2" onclick="showDevices()">Devices</label>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <input id="tab3" type="radio" name="tabs">
        <label for="tab3" onclick="writtingWorkSpace()">Work space</label>
    </sec:authorize>

    <input id="tab4" type="radio" name="tabs">
    <label for="tab4" onclick="showHistory()">History</label>

    <section id="content-tab1">
        <table id="userInfoTableBody" class="table table-bordered">

        </table>
    </section>

    <section id="content-tab2">
        <table id="deviceInfoTableBody" class="table table-bordered">
        </table>
    </section>

    <section id="content-tab3">
        <div id="workspace">
        </div>
    </section>

    <section id="content-tab4">
        <div id="searcHistory">
        </div>

        <div id="history" style="overflow: auto;" >
        </div>
    </section>

</div>

<script type="text/javascript">
    window.onload = function () {
        selectTab();
        showUser();
        showDevices();
        writtingWorkSpace();
        showHistory();
    }
//    window.onload = writtingWorkSpace();
//    window.onload = displayingWorkSpace();

</script>

</body>
</html>

