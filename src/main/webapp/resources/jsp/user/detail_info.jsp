<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 12/2/2016
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/resources/js/user.js"></script>
    <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
    <link rel="stylesheet" href="/resources/css/buttons.css">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
</head>

<body>
<h2 align="center">Details information</h2>
<form name="myForm" method="get" action="/updateDeleteUsers">

    <div class="warning" id="error">

    </div>

    <table style="width:80%" border="1" align="center" rel="stylesheet" class="table table-bordered">

        <tr>

                           <input id="id" type="hidden" name="ch" value="${userDTO.id}" checked>


            <th>
                name:
            </th>
            <td>
                <input style="width: 100%;background-color: white;border: none;" id="name"  name="inputed" disabled value="<c:out value="${userDTO.name}"/>">
            </td>
            <th>
                surname:
            </th>
            <td>
                <input style="width: 100%;background-color: white;border: none;" id="sername" name="inputed" disabled value="<c:out value="${userDTO.sername}"/>">
            </td>
            <th>
                location:
            </th>
            <td>
                <input style="width: 100%;background-color: white;border: none;" id="location" name="inputed" disabled value="<c:out value="${userDTO.location}"/>">
            </td>

        </tr>
        <tr>

             <th>
                role:
            </th>
            <td>
                <input style="width: 100%;background-color: white;border: none;" id="role" name="inputed" disabled value="<c:out value="${userDTO.role}"/>">
            </td>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th>
                    login:
                </th>
                <td>
                    <input style="width: 100%;background-color: white;border: none;" id="login" name="inputed" disabled value="<c:out value="${userDTO.login}"/>">
                </td>

                <th>
                    password:
                </th>
                <td>
                    <input style="width: 100%;background-color: white;border: none;" id="password" name="inputed" disabled value="<c:out value="${userDTO.password}"/>">
                </td>

        </tr>
        <tr>
            </sec:authorize>
            <th>
                email:
            </th>
            <td>
                <input style="width: 100%;background-color: white;border: none;" id="email" name="inputed" disabled value="<c:out value="${userDTO.email}"/>">
            </td>

            <td colspan="4">

            </td>
        </tr>


    </table>


</form>

<div class="center">

    <button style="width: 80px;  margin-left: 20px" onclick="goBack()" class="btn">Go Back</button>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <input type="button" class="btn btn-updateSave" style="width: 80px;  margin-left: 20px" name="Update" value="Update" onclick="buttonUpdateSaveClick()"/>
    <input type="hidden" class="btn btn-delete" style="width: 80px;  margin-left: 20px" name="Delete" value="Delete" onclick="buttonDeleteClick()"/>
    </sec:authorize>



    <script>
        function goBack() {
//            document.location.replace("http://localhost:8080/resources/jsp/mainPage3.jsp");
           window.history.back();
            // showUser();
        }
    </script>
</div>

<script type="text/javascript">

    function buttonUpdateSaveClick() {
        var div = document.getElementsByName("inputed");
        var buttonUpdateSave = document.getElementsByName("Update");
        var buttonDelete = document.getElementsByName("Delete");

        if (buttonUpdateSave[0].value == "Update") {
            for (var i = 0; i < div.length; i++) {
                div[i].removeAttribute("disabled");
            }
            buttonUpdateSave[0].value = "Save";
            buttonDelete[0].type = "button";
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
            }

            saveUpdateUserAjax();
// запусвк кантроллерадля обновленя
        }
    }
    function buttonDeleteClick() {

        var deleteAction = confirm("delete this userDTO?");
        if (deleteAction) {
            deleteUser();
        }
    }
</script>



</body>
</html>