/**
 * Created by Andrey on 2/4/2017.
 */
src = "http://code.jquery.com/jquery-2.1.4.min.js";

function testAjax() {
    var inpTest = document.getElementById("test");
    //# = id, .=class
    $.ajax({
        url: "/test/home",
        method: "POST",
        dataType: 'text',
        cache: false,
        data: {
            "name": inpTest.value,
            "secName": inpTest.value,
            "age": 2
        },
        success: function (responce) {
            writtingHTMLcodeDev(data);
        },
        error: function (data) {
            alert("Error " + data);
        }
    });
}

function searchDevice() {

    var s = document.getElementById('searchDev').value;
    if(s==""||s==null){
       return;
    }else if(s=="*"){
        showDevices();
        return;
    }

    $.ajax({
        url: "/searchDeviceAjax",
        method: "GET",
        dataType: 'text',
        cache: false,
        data: {
            "name": s,
        },
        success: function (data) {
             writtingHTMLcodeDev(data);
        },
        error: function (data) {
            alert(data);
        }
    })
}

function showDevices() {
    //  var inpTest = document.getElementById("test");
    //# = id, .=class

    $.ajax({

        url: "/findAllDevicesAJAX",
        method: "GET",
        dataType: 'text',
        cache: false,

        success: function (data) {
            writtingHTMLcodeDev(data);
        },

        error: function (data) {
            alert("ERROR " + data);
        }
    });
}

function a(){
    $("#enter").searchDevice();
}



function saveUpdateDeviceAjax() {

    var deviceID = document.getElementById("deviceID").value;
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
    }else {var shared ="-"};


    //# = id, .=class
    $.ajax({

        url: "/admin/updateDevicesAJAX",
        method: "POST",
        dataType: 'text',
        cache: false,
        data: {

            "deviceID":deviceID,
            "modelName":modelName,
            "serialNumber":serialNumber,
            "TypeOS": TypeOS,
            "OSVersion": OSVersion,
            "SkreenResolution": SkreenResolution,
            "ScreenDiagonal": ScreenDiagonal,
            "Description":Description,
            "RAM": RAM,
            "ROM": ROM,
            "Location": Location,
            "shared": shared

        },

        success: function (responce) {

            if (responce == "") {
                alert("device was updated!")
            } else {
                var body = document.getElementById('error');
                var innerTrHtml = '';
                innerTrHtml += responce;
                body.innerHTML = innerTrHtml;
            }
        },

        error: function (responce) {
            alert("Error " + responce);
        }
    });
}

function deleteDevice() {
    var id = document.getElementById("deviceID").value;

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
            if (data == 'null') {
                alert("Device was deleted");
          //     window.history.back();
               document.location.href = "/resources/jsp/home.jsp?param=device";

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
            alert("Error " + data.toString());

        }
    });
}

function writtingHTMLcodeDev(data) {
    var deviceDTOList = jQuery.parseJSON(data);

    var body = document.getElementById('deviceInfoTableBody');

    var innerTrHtml = '';

    innerTrHtml += '<tr>';
    innerTrHtml += '<td colspan="2" align="center">';
    innerTrHtml += '<div class="form-inline">';
    innerTrHtml += '<form onsubmit="searchDevice();return false" class="form-group">';
    innerTrHtml += '<input id="searchDev" type="text" placeholder="Model name or *" class="form-control"/>';
    innerTrHtml += '<input style="margin-left: 10px;" type="button" class="btn btn-info dropdown-toggle" value="Search" onclick="searchDevice()"/>';
    innerTrHtml += '</form></div>';
    innerTrHtml += '</td>';
    innerTrHtml += '</tr>';
    innerTrHtml += '<tr>';
    innerTrHtml += '<th>Devices (model name, type OS, OS version)</th>';
    innerTrHtml += '<th>Users (name / surname (email))</th>';
    innerTrHtml += '</tr>';

    for (var index = 0; index < deviceDTOList.length; ++index) {

        //innerTrHtml += '<tr id ="' + userDTOList[index].id + '" style="cursor: pointer;" data-toggle="tab" href="#deviceData" onclick=showDeviceData(this)>';
        innerTrHtml += '<tr>';
        innerTrHtml += '<td align="left"><a href="/detailDeviceInformations?param=' + deviceDTOList[index].deviceID + '">' +deviceDTOList[index].modelName + ", " +deviceDTOList[index].typeOS+", "+deviceDTOList[index].oSVersion+ '</a></td>';

        if (deviceDTOList[index].userDTO != null) {
            var user = deviceDTOList[index].userDTO;
            innerTrHtml += '<td align="left"><a href="/detailUserInformations?param=' + user.id + '">';

                innerTrHtml += user.name +" / "+ user.sername +" ("+ user.email+")"+'<br />';

            innerTrHtml += '</a></td>';
        } else {
            innerTrHtml += '<td>' + ' --- ' + '</td>'
        }
        innerTrHtml += '</tr>';
    }
    body.innerHTML = innerTrHtml;
}


function selectTab() {

    var getUrlParameter = function getUrlParameter(sParam) {
        var sPageURL = decodeURIComponent(window.location.search.substring(1)),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');

            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : sParameterName[1];
            }
        }
    };


    var param = getUrlParameter('param');

    var user = document.getElementById('tab1');
    var device = document.getElementById('tab2');
    var workSpace = document.getElementById('tab3');
    var history = document.getElementById('tab4');

    switch(param) {
        case 'device':
            user.removeAttribute("checked");
            device.setAttribute("checked", "checked");
            break;

        case 'workSpace':  // if (x === 'value2')
            user.removeAttribute("checked");
            workSpace.setAttribute("checked", "checked");
            writtingWorkSpace();
            break;
        case 'history':  // if (x === 'value2')

            break;

        default:
            break;
    }
}

