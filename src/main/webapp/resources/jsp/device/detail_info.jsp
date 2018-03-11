<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 12/4/2016
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <%--<script src="/resources/js/device.js"></script>--%>
    <script src="/resources/js/device.js"></script>
    <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
    <link rel="stylesheet" href="/resources/css/buttons.css">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">

</head>

<body>
<h2 align="center">Details information</h2>
<form name="myForm" method="get" action="/updateDeleteDevices">

    <div class="warning" id="error">
    </div>

    <table style="width:80%" border="1" align="center" rel="stylesheet" class="table table-bordered">

                <input id="deviceID" type="hidden" value="${device.deviceID}" checked></inputhidden>

        <tr>
            <th>
                Model Name:
            </th>
            <td>

                <input style="width: 100%;background-color: white;border: none;" id="modelName" name="inputed" disabled value="<c:out value="${device.modelName}"/>">
                </div>
            </td>

            <th>
                Serial Number:
            </th>
            <td>

                <input style="width: 100%;background-color: white;border: none;" id="serialNumber" name="inputed" disabled value="<c:out value="${device.serialNumber}"/>">

            </td>
            <th>
                Type OS:
            </th>
            <td>

                <input style="width: 100%;background-color: white;border: none;" id="typeOS" name="inputed" disabled value="<c:out value="${device.typeOS}"/>">

            </td>

        </tr>
        <tr>

            <th>
                Os Version:
            </th>
            <td>

                <input style="width: 100%;background-color: white;border: none;" id="oSVersion" name="inputed" disabled value="<c:out value="${device.oSVersion}"/>">

            </td>
            <th>
                ScreenResolution:
            </th>
            <td>

                <input style="width: 100%;background-color: white;border: none;" id="skreenResolution" name="inputed" disabled value="<c:out value="${device.skreenResolution}"/>">

            </td>

            <th>
                Screen Diagonal:
            </th>
            <td>

                <input style="width: 100%;background-color: white;border: none;" id="screenDiagonal" name="inputed" disabled value="<c:out value="${device.screenDiagonal}"/>">

            </td>
        </tr>
        <tr>
            <th>
                Description:
            </th>
            <td>

                <input style="width: 100%;background-color: white;border: none;" id="description" name="inputed" disabled value="<c:out value="${device.description}"/>">

            </td>

            <th>
                RAM:
            </th>
            <td>

                <input style="width: 100%;background-color: white;border: none;" id="rAM" name="inputed" disabled value="<c:out value="${device.rAM}"/>">

            </td>
            <th>
                ROM:
            </th>
            <td>

                <input style="width: 100%;background-color: white;border: none;" id="rOM" name="inputed" disabled value="<c:out value="${device.rOM}"/>">

            </td>
        </tr>
        <tr>

            <th>
                Location:
            </th>
            <td>

                <input style="width: 100%;background-color: white;border: none;" id="location" name="inputed" disabled value="<c:out value="${device.location}"/>">


            </td>
            <td colspan="4">

            </td>

        </tr>


    </table>

</form>



<div class="center">

    <div id="divShared">
    </div>
    <c:if test = "${device.shared == 'user'}">
        <input id="shared" type="hidden" checked="checked" value="user" onclick="checkShared()">
    </c:if>
    <c:if test = "${device.shared != 'user'}">
        <input id="shared" type="hidden" value="user" onclick="checkShared()">
    </c:if>

    <button onclick="goBack()" style="width: 80px;  margin-left: 20px" class="btn">Go Back</button>

    <sec:authorize access="hasRole('ROLE_ADMIN')">


    <input type="button"  name="Update" style="width: 80px;  margin-left: 20px" class="btn btn-updateSave" value="Update" onclick="buttonUpdateSaveClick()"/>
    <input type="hidden" name="Delete" style="width: 80px;  margin-left: 20px" class="btn btn-delete"value="Delete" onclick="buttonDeleteClick()"/>

    </sec:authorize>


    <script>
        function goBack() {
            window.history.back();
        }
    </script>

</div>


<script>

    function checkShared() {
        var elem = document.getElementById("shared");
        if(elem.hasAttribute("ckecked")){
            elem.removeAttribute("ckecked");
        }else{elem.setAttribute("ckecked", "");}
    }

    function buttonUpdateSaveClick() {
        var div = document.getElementsByName("inputed");
        var buttonUpdateSave = document.getElementsByName("Update");
        var buttonDelete = document.getElementsByName("Delete");
        var sh = document.getElementById('divShared');
        var shCh = document.getElementById('shared');

        if (buttonUpdateSave[0].value == "Update") {
            for (var i = 0; i < div.length; i++) {
                div[i].removeAttribute("disabled");
            }
            buttonUpdateSave[0].value = "Save";
            buttonDelete[0].type = "button";
            shCh.type = "checkbox";

            var innerTrHtml = '';
            innerTrHtml = '';
            innerTrHtml += 'Share this device for all users: ';
            sh.innerHTML = innerTrHtml;

        }

        else {
            var res = true;
            for (var i = 0; i < div.length; i++) {
                if (div[i].value == "") {
                    res = false;
                    break;
                }

            }
            if (res) {
                for (var i = 0; i < div.length; i++) {
                    div[i].setAttribute("disabled", "disabled");
                }
                buttonUpdateSave[0].value = "Update";
                buttonDelete[0].type = "hidden";

                var innerTrHtml = '';
                innerTrHtml += '';
                sh.innerHTML = innerTrHtml;
                shCh.type="hidden";

            }

            saveUpdateDeviceAjax();
// запусвк кантроллерадля обновленя
        }

    }
    function buttonDeleteClick() {

        var deleteAction = confirm("delete this device?");
        if (deleteAction) {
            deleteDevice();
        }
    }
</script>

</body>
</html>