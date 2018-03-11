function showUser() {

    $.ajax({
            url: "/findAllUsers",
            method: "GET",
            dataType: 'text',
            cache: false,
            success: function (data) {
                writtingHTMLcode(data);
            },
            error: function (data) {
                alert("Error showUser" + data);
            }
        }
    )
}

function searchUsers() {

    var s = document.getElementById('search').value;

    if(s=="*"){
        showUser();
        return;
    }

    if(s!="") {
        $.ajax({
            url: "/searchUser2",
            method: "GET",
            dataType: 'text',
            cache: false,
            data: {
                "name": s,
            },

            success: function (data) {
                writtingHTMLcode(data);
            },

            error: function (data) {
                alert("Error searchUser" + data);
            }
        })
    }
}

function saveUpdateUserAjax() {
    //# = id, .=class
    var id = document.getElementById("id").value;
    var name = document.getElementById("name").value;
    var sername = document.getElementById("sername").value;
    var location = document.getElementById("location").value;
    var role = document.getElementById("role").value;
    var login = document.getElementById("login").value;
    var password = document.getElementById("password").value;
    var email = document.getElementById("email").value;

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
                alert("user was updated!")
            } else {
                var body = document.getElementById('error');
                var innerTrHtml = '';
                innerTrHtml += responce;
                body.innerHTML = innerTrHtml;
            }
        },

        error: function (responce) {
            alert("Error saveUpdateUserAjax" + responce);
        }
    });
}

function deleteUser() {
    var id = document.getElementById("id").value;

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
           
            if (data == 'null') {
                document.location.href = "/resources/jsp/home.jsp";
                alert("User was deleted")
            } else {
                var body = document.getElementById('error')
                var innerTrHtml = '';
                //   innerTrHtml += '<div class="warning">';
                innerTrHtml += data;
                //    innerTrHtml += '</div>';
                body.innerHTML = innerTrHtml;
            }
        },

        error: function (data) {
            alert("Error deleteUser" + data.toString());

        }
    });
}


function writtingHTMLcode(data) {
    var userDTOList = jQuery.parseJSON(data);


    var body = document.getElementById('userInfoTableBody')

    var innerTrHtml = '';

    innerTrHtml += '<tr>';
    innerTrHtml += '<td colspan="2" align="center">';
    innerTrHtml += '<div class="form-inline">';
    innerTrHtml += '<form form onsubmit="searchUsers();return false" class="form-group">';
    innerTrHtml += '<input id="search" type="text" placeholder="Name or Lastname or *" class="form-control"/>';
    innerTrHtml += '<input style="margin-left: 10px;" type="button" class="btn btn-info dropdown-toggle" value="Search" onclick="searchUsers()"/>';
    innerTrHtml += '</form></div>';
    innerTrHtml += '</td>';
    innerTrHtml += '</tr> ';
    innerTrHtml += '<tr>';
    innerTrHtml += '<th>Users (name surname (email))</th>';
    innerTrHtml += '<th>Devices (model name, type OS, OS version)</th>';
    innerTrHtml += '</tr> ';

    for (var index = 0; index < userDTOList.length; ++index) {

        //innerTrHtml += '<tr id ="' + userDTOList[index].id + '" style="cursor: pointer;" data-toggle="tab" href="#deviceData" onclick=showDeviceData(this)>';
        innerTrHtml += '<tr>';
        innerTrHtml += '<td align="left"><a href="/detailUserInformations?param=' + userDTOList[index].id + '">'+userDTOList[index].name +" / "+ userDTOList[index].sername +" ("+ userDTOList[index].email + ")"+'</a></td>';

        if (userDTOList[index].deviceDTOs.length != 0) {
            var devices = userDTOList[index].deviceDTOs;
            innerTrHtml += '<td align="left">';
            for (index1 = 0; index1 < devices.length; index1++) {

                innerTrHtml += '<a href="/detailDeviceInformations?param=' + devices[index1].deviceID + '">';
                innerTrHtml += devices[index1].modelName + ", "+devices[index1].typeOS+", "+devices[index1].oSVersion+ '</a><br />';
            }
            innerTrHtml += '</td>';
        } else {
            innerTrHtml += '<td>' + ' --- ' + '</td>'
        }
        innerTrHtml += '</tr>';
    }
    body.innerHTML = innerTrHtml;
}


