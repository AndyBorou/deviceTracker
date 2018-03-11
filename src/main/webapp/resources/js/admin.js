function writtingWorkSpace() {

    var body = document.getElementById('workspace');
    var innerTrHtml = '';

    innerTrHtml += '<div class="container">';
    innerTrHtml += '<div class="row" style="min-height:600px;">';
    innerTrHtml += '<div  class="col-sm-6">';
    innerTrHtml += '<div class="col-xs-3">';
    <!-- required for floating -->
    <!-- Nav tabs -->
    innerTrHtml += '<ul class="nav nav-tabs tabs-left">';
    innerTrHtml += '<li id="vtab1" class="active"><a href="#status" data-toggle="tab" onclick="displayingWorkSpace();">Status</a></li>';
    innerTrHtml += '<li id="vtab2"><a href="#createUser" data-toggle="tab" onclick="wtittingCreateUserForm();">Create user</a></li>';
    innerTrHtml += '<li id="vtab3"><a href="#createDevice" data-toggle="tab" onclick="wtittingCreateDeviceForm();">Create device</a></li>';
    innerTrHtml += '<li id="vtab4"><a href="#allUsers" data-toggle="tab" onclick="showUsers();">Show all users</a></li>';
    innerTrHtml += '<li id="vtab5"><a href="#allDevices" data-toggle="tab" onclick="showAllDevices();">Show all devices</a></li>';
    innerTrHtml += '</ul>';
    innerTrHtml += '</div>';

    innerTrHtml += '<div class="col-xs-9">';
    <!-- Tab panes -->
    innerTrHtml += '<div class="tab-content">';

    innerTrHtml += '<div class="tab-pane active" id="status">';
    //innerTrHtml += '<script>displayingWorkSpace();</script>';
    innerTrHtml += '<div id="writeWorkspace" class="center"></div>';
    innerTrHtml += '</div>';

    innerTrHtml += '<div class="tab-pane" id="createUser">';
    innerTrHtml += '<div id="writeCreateUserPage" class="center"></div>';
    innerTrHtml += '</div>';

    innerTrHtml += '<div class="tab-pane" id="createDevice">';
    innerTrHtml += '<div id="writeCreateDevicePage" class="center"></div>';
    innerTrHtml += '</div>';

    innerTrHtml += '<div class="tab-pane" id="allUsers">';
    innerTrHtml += '<div id="writeAllUsersPage"></div>';
    innerTrHtml += '</div>';

    innerTrHtml += '<div class="tab-pane" id="allDevices">';
    innerTrHtml += '<div id="writeAllDdevicePage" style="overflow: scroll" class="center"></div>';
    innerTrHtml += '</div>';

    innerTrHtml += '</div>';
    innerTrHtml += '</div>';

    innerTrHtml += '<div class="clearfix"></div>';
    innerTrHtml += '</div></div></div>';

    body.innerHTML = innerTrHtml;


    var activeTABs = document.getElementsByClassName("active");
    var a = activeTABs[0].getAttribute("id")

    switch (a) {
        case "vtab1":
            displayingWorkSpace();
            break;
        case "vtab2":
            alert('2');
            break;
        case "vtab3":
            alert('3');
            break;
        case "vtab4":
            alert('4');
            break;
        case "vtab5":
            alert('5');
            break;

        default:
            alert('Я таких значений не знаю');
    }

}


function wtittingCreateUserForm() {
    clearCreateDeviceForm();
    var body = document.getElementById('writeCreateUserPage');
    var innerTrHtml = '';
    innerTrHtml += '<h2 align="center">Create new user</h2>';
    innerTrHtml += '<div class="warning" id="errorCrUs"></div>';

    innerTrHtml += '<table border="1" rel="stylesheet" class="table table-bordered">';

    innerTrHtml += '<tr>';
    innerTrHtml += '<th style="width: 160px">name:</th>';
    innerTrHtml += '<td><input id="name" name="inputed"/></td>';
    innerTrHtml += '<th style="width: 160px">surname:</th>';
    innerTrHtml += '<td><input id="sername" name="inputed"/></td>';
    innerTrHtml += '</tr><tr>';
    innerTrHtml += '<th style="width: 160px">location:</th>';
    innerTrHtml += '<td><input id="location" name="inputed"/></td>';
    innerTrHtml += '<th style="width: 160px">role:</th>';
    innerTrHtml += '<td><input id="role" name="inputed"/></td>';
    innerTrHtml += '</tr><tr>';
    innerTrHtml += '<th style="width: 160px">login:</th>';
    innerTrHtml += '<td><input id="login" name="inputed"/></td>';
    innerTrHtml += '<th style="width: 160px">password:</th>';
    innerTrHtml += '<td><input id="password" name="inputed"/></td>';
    innerTrHtml += '</tr><tr>';
    innerTrHtml += '<th style="width: 160px">email:</th>';
    innerTrHtml += '<td><input id="email" name="inputed"/></td>';

    innerTrHtml += '<td colspan="2" align="center"></td>';
    innerTrHtml += '</tr>';
    innerTrHtml += '</table>';

    innerTrHtml += '<input type="button" style="width: 60px;" class="btn btn-updateSave" value="Save" onclick="createNewUser();"/>';

    body.innerHTML = innerTrHtml;
}

function createNewUser() {

    var name = document.getElementById("name").value;
    var sername = document.getElementById("sername").value;
    var location = document.getElementById("location").value;
    var role = document.getElementById("role").value;
    var login = document.getElementById("login").value;
    var password = document.getElementById("password").value;
    var email = document.getElementById("email").value;

    $.ajax({
        url: "/admin/createNewUserAJAX",
        method: "GET",
        dataType: 'text',
        cache: false,
        data: {
            "name": name,
            "sername": sername,
            "location": location,
            "role": role,
            "login": login,
            "password": password,
            "email": email,
        },
        success: function (responce) {
            if (responce == "") {
                alert("user was created!")
                wtittingCreateUserForm();
            } else {
                var body = document.getElementById('errorCrUs')
                var innerTrHtml = '';
                innerTrHtml += responce;
                body.innerHTML = innerTrHtml;
            }
        },

        error: function (responce) {
            alert("Error createNewUser " + responce);
        }
    });
}

function wtittingCreateDeviceForm() {
    clearCreateUserForm();
    var body = document.getElementById('writeCreateDevicePage');
    var innerTrHtml = '';

    innerTrHtml += '<h2 align="center">Create new device</h2>';

    innerTrHtml += '<div class="warning" id="errorCrDe"></div>';

    innerTrHtml += '<table border="1" rel="stylesheet" class="table table-bordered">';

    innerTrHtml += '<tr>';
    innerTrHtml += '<th style="width: 160px">Model Name:</th>';
    innerTrHtml += '<td><input id="modelName" name="inputed"/></div></td>';
    innerTrHtml += '<th style="width: 160px">Serial Number:</th>';
    innerTrHtml += '<td><input id="serialNumber" name="inputed"/></td>';
    innerTrHtml += '</tr><tr>';
    innerTrHtml += '<th style="width: 160px">Type OS:</th>';
    innerTrHtml += '<td><input id="typeOS" name="inputed"/></td>';
    innerTrHtml += '<th style="width: 160px">Os Version:</th>';
    innerTrHtml += '<td><input id="oSVersion" name="inputed"/></td>';
    innerTrHtml += '</tr><tr>';
    innerTrHtml += '<th style="width: 160px">Screen Resolution:</th>';
    innerTrHtml += '<td><input id="skreenResolution" name="inputed"/></td>';
    innerTrHtml += '<th style="width: 160px">Screen Diagonal:</th>';
    innerTrHtml += '<td><input id="screenDiagonal" name="inputed"/></td>';
    innerTrHtml += '</tr><tr>';
    innerTrHtml += '<th style="width: 160px">Description:</th>';
    innerTrHtml += '<td><input id="description" name="inputed"/></td>';
    innerTrHtml += '<th style="width: 160px">RAM:</th>';
    innerTrHtml += '<td><input id="rAM" name="inputed"/></td>';
    innerTrHtml += '</tr><tr>';
    innerTrHtml += '<th style="width: 160px">ROM:</th>';
    innerTrHtml += '<td><input id="rOM" name="inputed"/></td>';
    innerTrHtml += '<th style="width: 160px">Location:</th>';
    innerTrHtml += '<td><input id="location" name="inputed"/></td> ';
    innerTrHtml += '</tr>';
    innerTrHtml += '</table>';
    innerTrHtml += '<input type="button" style="width: 60px;" class="btn btn-updateSave" name="Update" value="Save" onclick="createNewDevice()"/>  Share this device for all users:  <input id="shared" type="checkbox" value="user" onclick="checkShared()">';

    body.innerHTML = innerTrHtml;
}
function checkShared() {
     var elem = document.getElementById("shared");
    if(elem.hasAttribute("ckecked")){
        elem.removeAttribute("ckecked");
    }else{elem.setAttribute("ckecked", "ckecked");}
}


function createNewDevice() {

    var modelName = document.getElementById("modelName").value;
    var serialNumber = document.getElementById("serialNumber").value;
    var TypeOS = document.getElementById("typeOS").value;
    var OSVersion = document.getElementById("oSVersion").value;
    var SkreenResolution = document.getElementById("skreenResolution").value;
    var ScreenDiagonal = document.getElementById("screenDiagonal").value;
    var Description = document.getElementById("description").value;
    var RAM = document.getElementById("rAM").value;
    var ROM = document.getElementById("rOM").value;
    var Location = document.getElementById("location").value;

    if(document.getElementById("shared").checked){
        var shared = document.getElementById("shared").value;
    }else{var shared ="-"};
    //# = id, .=class
    $.ajax({

        url: "/admin/createDevicesAJAX",
        method: "POST",
        dataType: 'text',
        cache: false,
        data: {

            "modelName": modelName,
            "serialNumber": serialNumber,
            "TypeOS": TypeOS,
            "OSVersion": OSVersion,
            "SkreenResolution": SkreenResolution,
            "ScreenDiagonal": ScreenDiagonal,
            "Description": Description,
            "RAM": RAM,
            "ROM": ROM,
            "Location": Location,
            "shared": shared,
        },

        success: function (responce) {

            if (responce == "") {
                alert("device was created!")
                wtittingCreateDeviceForm();
            } else {
                var body = document.getElementById('errorCrDe')
                var innerTrHtml = '';
                innerTrHtml += responce;
                body.innerHTML = innerTrHtml;
            }
        },

        error: function (responce) {
            alert("Error createNewDevice " + responce);
        }
    });
}

function showUsers() {
    clearDevicesListTab();
    $.ajax({
        url: "/findAllUsers",
        method: "GET",
        dataType: 'text',
        cache: false,

        success: function (data) {
            wtittingUsersLlist(data);
        },
        error: function (data) {
            alert("ERROR showUsers " + data);
        }
    });
}

function wtittingUsersLlist(data) {
    var eventListDTO = jQuery.parseJSON(data);
    var body = document.getElementById('writeAllUsersPage');

    var innerTrHtml = '';
    innerTrHtml += '<div class="warning" id="errorUpUs"></div>';

        innerTrHtml += '<table rel="stylesheet" class="table table-bordered" style="max-width: 115%;width: 115%">';

    innerTrHtml += '<tr><td colspan="8" align="center">';

    innerTrHtml += '<input type="hidden" class="btn btn-back" style="width: 80px; margin-left: 20px" id="BackU" value="Back" onclick="showUsers()"/>';

    innerTrHtml += '<input type="button" class="btn btn-updateSave" style="width: 80px; margin-left: 20px" id="UpdateU" value="Update" onclick="buttonUpdateSaveClick()"/>';

    innerTrHtml += '<input type="hidden" class="btn btn-delete" style="width: 80px; margin-left: 20px" id="DeleteU" value="Delete" onclick="buttonDeleteClick()"/>';


    innerTrHtml += '</td></tr>';

    innerTrHtml += '<tr>';
    innerTrHtml += '<th style="width: 10px">ID</th>';
    innerTrHtml += '<th style="width: 12%">name</th>';
    innerTrHtml += '<th style="width: 12%">surname</th>';
    innerTrHtml += '<th style="width: 12%">location</th>';
    innerTrHtml += '<th style="width: 12%">role</th>';
    innerTrHtml += '<th style="width: 12%">login</th>';
    innerTrHtml += '<th style="width: 12%">password</th>';
    innerTrHtml += '<th style="width: 20%">email</th>';
    innerTrHtml += '<th style="width: 15px;" name="del" hidden>Delete Update</th>';
    innerTrHtml += '</tr>';

    for (var index = 0; index < eventListDTO.length; ++index) {
        innerTrHtml += '<tr>';

        innerTrHtml += '<td id = "id">' + eventListDTO[index].id + '</td>';
        innerTrHtml += '<td><input id="name" style="width: 100%" name="inputedU" disabled value="' + eventListDTO[index].name + '"/></td>';
        innerTrHtml += '<td><input id="sername"  style="width: 100%" name="inputedU" disabled value="' + eventListDTO[index].sername + '"/></td>';
        innerTrHtml += '<td><input id="location" style="width: 100%" name="inputedU" disabled value="' + eventListDTO[index].location + '"/></td>';
        innerTrHtml += '<td><input id="role" style="width: 100%" name="inputedU" disabled value="' + eventListDTO[index].role + '"/></td>';
        innerTrHtml += '<td><input id="login" style="width: 100%" name="inputedU" disabled value="' + eventListDTO[index].login + '"/></td>';
        innerTrHtml += '<td><input id="password" style="width: 100%" name="inputedU" disabled value="' + eventListDTO[index].password + '"/></td>';
        innerTrHtml += '<td><input id="email"  style="width: 100%" name="inputedU" disabled value="' + eventListDTO[index].email + '"/></td>';

        innerTrHtml += '<td align="center" hidden name = "del"><input id="' + (index + 1) + '" onclick="edit(' + (index + 1) + ');" type="checkbox" value="' + eventListDTO[index].id + '"/></td>';

        innerTrHtml += '</tr>'


    }



    innerTrHtml += '</table>'

        body.innerHTML = innerTrHtml;
}
//Show delete column and delete button, hidde delete column and delete button
function buttonUpdateSaveClick() {
    var del = document.getElementsByName("del");
    var div = document.getElementsByName("inputedU");
    var buttonUpdateSave = document.getElementById("UpdateU");
    var buttonDelete = document.getElementById("DeleteU");
    var buttonBack = document.getElementById("BackU");

    if (buttonUpdateSave.value == "Update") {

        for (var i = 0; i < del.length; i++) {
            del[i].removeAttribute("hidden");
        }
        buttonUpdateSave.value = "Save";
        buttonDelete.type = "button";
        buttonBack.type = "button";

    }
    else {
        var res = true;
        for (var i = 0; i < div.length; i++) {
            if (div[i].value == "") {
                res = false;

                var body = document.getElementById('errorUpUs')
                var innerTrHtml = '';
                innerTrHtml += "Please fill all parameters";
                body.innerHTML = innerTrHtml;

                break;
            }
        }

        if (res) {
            saveSelectedUpdatedUserAjax();
            showUsers();
        }
    }
}
function buttonDeleteClick() {
    // получаем все выделенные элементы
    var ch = document.getElementsByName("check");
    //  получаем все элементы всех текстовых полей
    var div = document.getElementsByName("inputedU");
    var idArr = new Array();
    var nameArr = new Array();
    // получаем все ID user которых надо удалить
    for (var i = 0; i < ch.length; i++) {
        // получаем ID user которых надо удалить
        var id = ch[i].value;
        idArr.push(id);
// получаем инекс, порядковый номер строки, для обновления
// расчитываем по прядковому номеру строки нужные номера элементов для обновления
        var index = ch[i].id * 7;
        var name = div[index - 7].value;
        nameArr.push(name);
    }

    var deleteAction = confirm("delete this user(s): " + nameArr + " ?");

    if (deleteAction) {
         deleteSelectedUser(idArr);
    }
}

function deleteSelectedUser(idArr) {

    for (var i = 0; i<idArr.length; i++) {
        var id = idArr[i];

        $.ajax({
            url: "/admin/deleteUserAJAX",
            method: "GET",
            dataType: 'text',
            cache: false,
            data: {
                "id": id,
                "error": "null",
            },
            success: function (data) {
                if (data == "null") {
                    // document.location.href = "/resources/jsp/home.jsp";
                    showUsers();
                  
                } else {
                    var body = document.getElementById('errorUpUs');
                    var innerTrHtml = '';
                    //   innerTrHtml += '<div class="warning">';
                    innerTrHtml += data;
                    //    innerTrHtml += '</div>';
                    body.innerHTML = innerTrHtml;
                }
            },
            error: function (data) {
                alert("Error deleteSelectedUser " + data.toString());
            }
        });
    }  
}

//Make editable row
function edit(ind) {

    var div = document.getElementsByName("inputedU");
    for (var i = ind * 7 - 7; i < ind * 7; i++) {
        if (div[i].hasAttribute("disabled")) {
            div[i].removeAttribute("disabled");
        } else {
            div[i].setAttribute("disabled", "disabled");
        }
    };
    var ch = document.getElementById(ind);
    if (ch.hasAttribute("name")) {
        ch.removeAttribute("name");
    } else {
        ch.setAttribute("name", "check");
    };
}

function saveSelectedUpdatedUserAjax() {
    //  получаем все элементы (номер по счету - строка) всех отмеченных чекбоксов колонкт делете
    var ch = document.getElementsByName("check");
    //  получаем все элементы всех текстовых полей
    var div = document.getElementsByName("inputedU");

    for (var i = 0; i < ch.length; i++) {

        var id = ch[i].value;
        // получаем инекс, порядковый номер строки, для обновления
// расчитываем попрядковому номеру строки нужные номера элементов для обновления
        var index = ch[i].id * 7;

        var name = div[index - 7].value;
        var sername = div[index - 6].value;
        var location = div[index - 5].value;
        var role = div[index - 4].value;
        var login = div[index - 3].value;
        var password = div[index - 2].value;
        var email = div[index - 1].value;

        $.ajax({
            url: "/admin/updateUserAJAX",
            method: "POST",
            dataType: 'text',
            cache: false,
            data: {
                "id": id,
                "name": name,
                "sername": sername,
                "location": location,
                "role": role,
                "login": login,
                "password": password,
                "email": email,
            },
            success: function (responce) {
                if (responce == "") {
                    alert("user " + name + " was updated!")
                } else {
                    var body = document.getElementById('errorUpUs');
                    var innerTrHtml = '';
                    innerTrHtml += responce;
                    body.innerHTML = innerTrHtml;

                }
            },
            error: function (responce) {
                alert("Error saveSelectedUpdatedUserAjax " + responce);
            }
        });
    }
}





function showAllDevices() {
    clearDevicesListTab();
    clearUsersListTab();

    $.ajax({

        url: "/findAllDevicesAJAX",
        method: "GET",
        dataType: 'text',
        cache: false,

        success: function (data) {
            wtittingDevicesList(data);
        },

        error: function (data) {
            alert("ERROR showAllDevices " + data);
        }
    });
}

function wtittingDevicesList(data) {
    var deviceDTOList = jQuery.parseJSON(data);
    var body = document.getElementById('writeAllDdevicePage');

    var innerTrHtml = '';
    innerTrHtml += '<div class="warning" id="errorUpDe"></div>';
    innerTrHtml += '<table rel="stylesheet" class="table table-bordered">';

    innerTrHtml += '<tr><td colspan="11" align="center">';

    innerTrHtml += '<input type="button" class="btn btn-back" id="showFullID" value="FullTable" onclick="showFullTable()"/>';

    innerTrHtml += '<input type="hidden" class="btn btn-back" style="width: 80px; margin-left: 20px" id="BackD" value="Back" onclick="showAllDevices()"/>';

    innerTrHtml += '<input type="button" class="btn btn-updateSave" style="width: 80px; margin-left: 20px" id="UpdateD" value="Update" onclick="buttonUpdateSaveDeviceClick()"/>';

    innerTrHtml += '<input type="hidden" class="btn btn-delete" style="width: 80px; margin-left: 20px" id="DeleteD" value="Delete" onclick="buttonDeleteDeviceClick()"/>';


    innerTrHtml += '</td></tr>';

    innerTrHtml += '<tr>';
    innerTrHtml += '<th>ID</th>';
    innerTrHtml += '<th>Model name</th>';
    innerTrHtml += '<th>Serial number</th>';
    innerTrHtml += '<th>Type OS</th>';
    innerTrHtml += '<th>Os Version</th>';
    innerTrHtml += '<th>Screen resolution</th>';
    innerTrHtml += '<th>Screen diagonal</th>';
    innerTrHtml += '<th>Description</th>';
    innerTrHtml += '<th>RAM</th>';
    innerTrHtml += '<th>ROM</th>';
    innerTrHtml += '<th>Location</th>';
    innerTrHtml += '<th name="sharedName" hidden>Share</th>';
    innerTrHtml += '<th name="delD" hidden>Delete Update</th>';
    innerTrHtml += '</tr>';

    for (var index = 0; index < deviceDTOList.length; ++index) {
        innerTrHtml += '<tr>';
        innerTrHtml += '<td id = "id">' + deviceDTOList[index].deviceID + '</td>';
        innerTrHtml += '<td><input style="width: 100%" id="modelName"  name="inputedD" disabled value="' + deviceDTOList[index].modelName + '"/></td>';
        innerTrHtml += '<td><input style="width: 100%" id="serialNumber"  name="inputedD" disabled value="' + deviceDTOList[index].serialNumber + '"/></td>';
        innerTrHtml += '<td><input style="width: 100%" id="typeOS"  name="inputedD" disabled value="' + deviceDTOList[index].typeOS + '"/></td>';
        innerTrHtml += '<td><input style="width: 100%" id="oSVersion"  name="inputedD" disabled value="' + deviceDTOList[index].oSVersion + '"/></td>';
        innerTrHtml += '<td><input style="width: 100%" id="skreenResolution"  name="inputedD" disabled value="' + deviceDTOList[index].skreenResolution + '"/></td>';
        innerTrHtml += '<td><input style="width: 100%"  id="screenDiagonal"  name="inputedD" disabled value="' + deviceDTOList[index].screenDiagonal + '"/></td>';
        innerTrHtml += '<td><input style="width: 100%" id="description"  name="inputedD" disabled value="' + deviceDTOList[index].description + '"/></td>';
        innerTrHtml += '<td><input style="width: 100%" id="rAM" name="inputedD"  disabled value="' + deviceDTOList[index].rAM + '"/></td>';
        innerTrHtml += '<td><input style="width: 100%" id="rOM" name="inputedD"  disabled value="' + deviceDTOList[index].rOM + '"/></td>';
        innerTrHtml += '<td><input style="width: 100%" id="location" name="inputedD"  disabled value="' + deviceDTOList[index].location + '"/></td>';
        innerTrHtml += '<td hidden name = "sharedName">';

            if(deviceDTOList[index].shared == 'user') {
                innerTrHtml += '<input id="shared" disabled type="checkbox" name="inputedD" checked="checked" value="user" onclick="checkShared()">';
            }else {
                innerTrHtml += '<input id="shared" type="checkbox" disabled name="inputedD" value="user" onclick="checkShared()">';
            }

        innerTrHtml += '</td>';


        innerTrHtml += '<td hidden name = "delD"><input id="' + (index + 1) + '" onclick="editDevice(' + (index + 1) + ');" type="checkbox" value="' + deviceDTOList[index].deviceID + '"/></td>';
        innerTrHtml += '</tr>'
    }

    innerTrHtml += '</table>'
    body.innerHTML = innerTrHtml;
}
//Show delete column and delete button, hidde delete column and delete button
function buttonUpdateSaveDeviceClick() {
    var del = document.getElementsByName("delD");
    var div = document.getElementsByName("inputedD");
    var share = document.getElementsByName("sharedName");
    var buttonUpdateSave = document.getElementById("UpdateD");

    var buttonDelete = document.getElementById("DeleteD");
    var buttonBack = document.getElementById("BackD");

    if (buttonUpdateSave.value == "Update") {

        for (var i = 0; i < del.length; i++) {
            del[i].removeAttribute("hidden");
            share[i].removeAttribute("hidden");
        }
        buttonUpdateSave.value = "Save";
        buttonDelete.type = "button";
        buttonBack.type = "button";

    }
    else {
        var res = true;
        for (var i = 0; i < div.length; i++) {
            if (div[i].value == "") {
                res = false;

                var body = document.getElementById('errorUpDe')
                var innerTrHtml = '';
                innerTrHtml += "Please fill all parameters";
                body.innerHTML = innerTrHtml;

                break;
            }
        }
        if (res) {
            saveSelectedUpdatedDeviceAjax();
            showAllDevices();

        }
    }
}
function buttonDeleteDeviceClick() {
    // получаем все выделенные элементы
    var ch = document.getElementsByName("check");
    //  получаем все элементы всех текстовых полей
    var div = document.getElementsByName("inputedD");
    var idArr = new Array();
    var nameArr = new Array();
    var serialArr = new Array();
    // получаем все ID device которых надо удалить
    for (var i = 0; i < ch.length; i++) {
        // получаем ID device которых надо удалить
        var id = ch[i].value;
        idArr.push(id);
// получаем индекс, порядковый номер строки, для обновления
// расчитываем по прядковому номеру строки нужные номера элементов для обновления
        var index = ch[i].id * 10;
        var name = div[index - 10].value;
        var serialN = div[index - 9].value;
        nameArr.push(name +"_"+serialN);
    }


    var deleteAction = confirm("delete this device(s): " + nameArr + " ?");

    if (deleteAction) {
        deleteSelectedDevice(idArr);
    }
}

function deleteSelectedDevice(idArr) {

    for (var i = 0; i<idArr.length; i++) {
        var id = idArr[i];

        $.ajax({
            url: "/admin/deleteDeviceAJAX",
            method: "GET",
            dataType: 'text',
            cache: false,
            data: {
                "id": id,
                "error": "null",
            },
            success: function (data) {
                if (data == "null") {
                    showAllDevices();

                } else {
                    var body = document.getElementById('errorUpDe')
                    var innerTrHtml = '';
                    innerTrHtml += data;
                    body.innerHTML = innerTrHtml;
                }
            },
            error: function (data) {
                alert("Error deleteSelectedDevice " + data.toString());
            }
        });
    }
}

//Make editable row
function editDevice(ind) {

    var div = document.getElementsByName("inputedD");
    for (var i = ind * 11 - 11; i < ind * 11; i++) {
        if (div[i].hasAttribute("disabled")) {
            div[i].removeAttribute("disabled");
        } else {
            div[i].setAttribute("disabled", "disabled");
        }
    }
    ;
    var ch = document.getElementById(ind);
    if (ch.hasAttribute("name")) {
        ch.removeAttribute("name");
    } else {
        ch.setAttribute("name", "check")
    }
    ;
}

function saveSelectedUpdatedDeviceAjax() {
    //  получаем все элементы (номер по счету - строка) всех отмеченных чекбоксов колонкт делете
    var ch = document.getElementsByName("check");
    //  получаем все элементы всех текстовых полей
    var div = document.getElementsByName("inputedD");

    for (var i = 0; i < ch.length; i++) {

        var deviceID = ch[i].value;
        // получаем инекс, порядковый номер строки, для обновления
// расчитываем попрядковому номеру строки нужные номера элементов для обновления
        var index = ch[i].id * 11;

        var modelName = div[index - 11].value;
        var serialNumber = div[index - 10].value;
        var TypeOS = div[index - 9].value;
        var OSVersion = div[index - 8].value;
        var SkreenResolution = div[index - 7].value;
        var ScreenDiagonal = div[index - 6].value;
        var Description = div[index - 5].value;
        var RAM = div[index - 4].value;
        var ROM = div[index - 3].value;
        var Location = div[index - 2].value;

        if(div[index - 1].checked){
            var shared = div[index - 1].value;
        }else{var shared ="-"};



        $.ajax({
            url: "/admin/updateDevicesAJAX",
            method: "POST",
            dataType: 'text',
            cache: false,
            data: {
                "deviceID":deviceID,
                "modelName":modelName,
                "serialNumber":serialNumber,
                "typeOS": TypeOS,
                "oSVersion": OSVersion,
                "skreenResolution": SkreenResolution,
                "screenDiagonal": ScreenDiagonal,
                "description":Description,
                "rAM": RAM,
                "rOM": ROM,
                "location": Location,
                "shared": shared,
            },
            success: function (responce) {
                if (responce == "") {
                    alert("Device " + modelName + " "+ serialNumber +" was updated!")
                } else {
                    var body = document.getElementById('errorUpDe');
                    var innerTrHtml = '';
                    innerTrHtml += responce;
                    body.innerHTML = innerTrHtml;
                }
            },
            error: function (responce) {
                alert("Error saveSelectedUpdatedDeviceAjax" + responce.toString());
            }
        });
    }
}

function clearUsersListTab() {
    var body = document.getElementById('writeAllUsersPage');
    var innerTrHtml = '';
    body.innerHTML = innerTrHtml;
}

function clearDevicesListTab() {
    var body = document.getElementById('writeAllDdevicePage');
    var innerTrHtml = '';
    body.innerHTML = innerTrHtml;
}

function clearCreateUserForm() {
    var body = document.getElementById('writeCreateUserPage');
    var innerTrHtml = '';
    body.innerHTML = innerTrHtml;
}
function clearCreateDeviceForm() {
    var body = document.getElementById('writeCreateDevicePage');
    var innerTrHtml = '';
    body.innerHTML = innerTrHtml;
}


function showFullTable() {
    var div = document.getElementById('writeAllDdevicePage');
    var sizeTableButton = document.getElementById('showFullID');
    if(sizeTableButton.value=="FullTable") {
        div.removeAttribute("style");
        sizeTableButton.value = "PartTable"
    }else {
        div.setAttribute("style", "overflow: scroll");
        sizeTableButton.value = "FullTable"
    }
}