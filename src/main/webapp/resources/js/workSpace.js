/**
 * Created by Andrey on 2/18/2017.
 */



function displayingWorkSpace() {
    $.ajax({
        url: "/admin/workAjax",
        method: "GET",
        dataType: 'text',
        cache: false,

        success: function (data) {
            writtingHTMLcodeWorkSpace(data);
        },

        error: function (data) {
            alert("ERROR displayingWorkSpace" + data);
        }
    });
}

function writtingHTMLcodeWorkSpace(data) {
    var ab;
    var deviceDTOList = jQuery.parseJSON(data);

    var usersWithDeviceListVar = deviceDTOList[0];
    var assignedDeviceDTOList = deviceDTOList[1];
    var freeDevicesList = deviceDTOList[2];
    var userDTOList = deviceDTOList[3];


    var body = document.getElementById('writeWorkspace');
    var innerTrHtml = '';
    innerTrHtml += '<form name="myForm" method="get" action="/admin/unAssignAssignAjax" id="assignUnAssign" onsubmit="return checkSelected(ab)"> ';

    // innerTrHtml += '<div class="center">';
    // innerTrHtml += '<input type="button" class="b1" value="DisplayAllDevice" onClick=location.href="/findAllDevices">';
    // innerTrHtml += '<input type="button" class="b1" value="DisplayAllUsers" onClick=location.href="/userListNew">';
    // innerTrHtml += '</div>';

    innerTrHtml += '<table rel="stylesheet" class="table table-bordered">';
    innerTrHtml += '<tr>';
    innerTrHtml += '<th style="text-align: center">name surname (email)</th>';
    innerTrHtml += '<th style="text-align: center" colspan="2">model name, type OS, OS version</th>';
    innerTrHtml += '</tr> ';

    for (var index = 0; index < usersWithDeviceListVar.length; ++index) {
        innerTrHtml += '<tr><td>';
        innerTrHtml += '<a href="/detailUserInformations?param=' + usersWithDeviceListVar[index].id + '"/>';
        innerTrHtml += usersWithDeviceListVar[index].name + ' ' + usersWithDeviceListVar[index].sername+ ' (' + usersWithDeviceListVar[index].email+")";
        innerTrHtml += '</a></td>';
        innerTrHtml += '<td>'

          innerTrHtml += '<table cellspacing="0">'

        var assignDevices = usersWithDeviceListVar[index].deviceDTOs;
        for (var index1 = 0; index1 < assignDevices.length; ++index1) {

                innerTrHtml += '<tr><td width="100%">';
                innerTrHtml += '<a href="/detailDeviceInformations?param=' + assignDevices[index1].deviceID + '">'
                innerTrHtml += assignDevices[index1].modelName + ', ' + assignDevices[index1].typeOS+ ', ' + assignDevices[index1].oSVersion;
                innerTrHtml += '</a>';
                // innerTrHtml += '</td>';
                innerTrHtml += '<td style="text-align: right">';
                innerTrHtml += '<input type="checkbox" onclick="checkSelected3();" name="ch" value="' + assignDevices[index1].deviceID + '">'

                innerTrHtml += '</td></tr>';

        }

          innerTrHtml += '</table>'
        innerTrHtml += '</td></tr>';
    }
    innerTrHtml += '</table>';

    innerTrHtml += '<div class="warning" id="error"></div>';

    innerTrHtml += '<div class="center"><input type="submit"  id="anassign" style="width: 80px; margin-bottom: 10px; margin-left: 20px" class="btn btn-updateSave" disabled="disabled" onclick="sesAction(value);" name="button" value="Unassign"/>';

    innerTrHtml += '<input type="submit" id="assign" style="width: 80px; margin-bottom: 10px; margin-left: 20px" class="btn btn-updateSave" onclick="sesAction(value);"  disabled="disabled" name="button" value="Assign"/></div>';


    innerTrHtml += '<table class="table table-bordered">';
    innerTrHtml += '<tr>';
    innerTrHtml += '<th style="text-align: center">model name, type OS, OS version</th>';
    innerTrHtml += '<th style="text-align: center">name surname</th>';
    innerTrHtml += '</tr> ';

    innerTrHtml += '<tr><td>';
    innerTrHtml += '<table cellpadding="0">';
    for (var index = 0; index < freeDevicesList.length; ++index) {
        innerTrHtml += '<tr><td width="100%">';
        innerTrHtml += '<a href="/detailDeviceInformations?param=' + freeDevicesList[index].deviceID + '">'
        innerTrHtml += freeDevicesList[index].modelName + ', ' + freeDevicesList[index].typeOS+ ', ' + freeDevicesList[index].oSVersion;
        innerTrHtml += '</td>';
        innerTrHtml += '<td>';
        innerTrHtml += '<input type="checkbox" onclick="checkSelected2();" name="che" value="' + freeDevicesList[index].deviceID + '">';
        innerTrHtml += '</td><tr>';
    }
    innerTrHtml += '</table>';
    innerTrHtml += '</td>';
    innerTrHtml += '<td valign="top">';
    innerTrHtml += 'List of users: <select class="btn dropdown-toggle btn-default" name="users">'
    for (var index = 0; index < userDTOList.length; ++index) {
        innerTrHtml += '<option value="' + userDTOList[index].id + '">' + userDTOList[index].name + ' ' + userDTOList[index].sername + '   </option>';
    }
    innerTrHtml += '</select>'
    innerTrHtml += '</td><tr>';
    innerTrHtml += '</table>';
    innerTrHtml += '</form>';





    body.innerHTML = innerTrHtml;
}

function sesAction(action) {
    ab=action;
}

function checkSelected(p){

    var button = p;
    var emptyAs = true;
    var emptyAn = true;
    var numbersOfDeviceIDforAssign = document.getElementsByName("che");
    var numbersOfDeviceIDforAnAssign = document.getElementsByName("ch");

    for (var index1 = 0; index1 < numbersOfDeviceIDforAssign.length; ++index1) {
        if(numbersOfDeviceIDforAssign[index1].checked){
            emptyAs = false;
        }
    }

    if(button=="Assign" && emptyAs){
        var body = document.getElementById('error')
        var innerTrHtml = '';
        innerTrHtml += 'select device for assign';
        body.innerHTML = innerTrHtml;
        return false;
    }


    for (var index1 = 0; index1 < numbersOfDeviceIDforAnAssign.length; ++index1) {
        if(numbersOfDeviceIDforAnAssign[index1].checked){
            emptyAn = false;
        }
    }

    if(button=="UnAssign" && emptyAn){
        var body = document.getElementById('error')
        var innerTrHtml = '';
        innerTrHtml += 'select device for unassign';
        body.innerHTML = innerTrHtml;
        return false;
    }

return true;

}

function checkSelected2() {


    var emptyAssign = true;

    var assign = document.getElementById("assign");

    var numbersOfDeviceIDforAssign = document.getElementsByName("che");

    for (var index1 = 0; index1 < numbersOfDeviceIDforAssign.length; ++index1) {
        if (numbersOfDeviceIDforAssign[index1].checked) {
            emptyAssign = false;
        }
    }

    if (emptyAssign) {
        assign.setAttribute("disabled", "disabled");
    } else {
        assign.removeAttribute("disabled");
    }
}
function checkSelected3(){
        var emptyAna = true;
        var numbersOfDeviceIDforAnAssign = document.getElementsByName("ch");
        var anassign = document.getElementById("anassign");

    for (var index1 = 0; index1 < numbersOfDeviceIDforAnAssign.length; ++index1) {
        if(numbersOfDeviceIDforAnAssign[index1].checked){
            emptyAna = false;
        }
    }

    if( emptyAna){
        anassign.setAttribute("disabled", "disabled");
    }else {
        anassign.removeAttribute("disabled");
    }


}

//
// function myFormSubmit(f) {
//
//        if( f.submit()) {
//            writtingWorkSpace();
//        }
// }