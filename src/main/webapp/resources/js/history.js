/**
 * Created by Andrey on 2/19/2017.
 */
var indexHistory;
var lisyOfHistoryElem;

function showHistory() {
    clearHistory();
    $.ajax({

        url: "/showHistoryAjax",
        method: "GET",
        dataType: 'text',
        cache: false,

        success: function (data) {

            writtingHTMLcodeHistory(data);
        },

        error: function (data) {
            alert("ERROR showHistory" + data);
        }
    });
}



function searchHistory() {

    var typeUserSearch = $('#searchByUserPar').val();
    var typeDeviceSearch = $('#searchByDevPar').val();
    var userInput = $('#userInput').val();
    var deviceInput = $('#deviceInput').val();
    var firstData = $('#fDate').val();
    var lastData = $('#lDate').val();
    var event = $('#event').val();
    if(typeUserSearch=="" && typeDeviceSearch=="" && event=="" && (firstData=="" || firstData=="yy-mm-dd")&&(lastData=="" || lastData=="yy-mm-dd")){
        showHistory();
        return;
    }

    $.ajax({
        url: "/findEventAjax",
        method: "GET",
        dataType: 'text',
        cache: false,
        data:{
            typeUserSearch: typeUserSearch,
            typeDeviceSearch:typeDeviceSearch,
            userInput:userInput,
            deviceInput:deviceInput,
            firstData:firstData,
            lastData:lastData,
            event:event,

            // fDate: firstData,
            // lDate:lastData,
            // event:events,
            // Fname:firstName,
            // Lname:lastName,
            // ModelName:modelName,
            // SerialNumber:serialNumber,
            // userID:userID,
            // deviceID:dID,
        },
        success: function (data) {
            writtingHTMLcodeHistory(data);
        },
        error: function (data) {
            alert("Event was not found");
            // alert("ERROR searchHistory" + data);
        }
    });
}

function writtingHTMLcodeHistory(data) {

    var eventListDTO = jQuery.parseJSON(data);

    var body = document.getElementById('history')
    var innerTrHtml = '';

    var bodySearch = document.getElementById('BackSearch');

    if (bodySearch==null) {
        innerTrHtml += '<input type="button"  style="margin-bottom: 10px;" value="searchEvent" id="searhHistoryID" class="btn btn-updateSave" onClick="writtingSearchHTMLcodeHistory()">';}

    // innerTrHtml += '<input type="button"  style="margin-bottom: 10px;" value="searchEvent" id="searhHistoryID" class="btn btn-updateSave" onClick="writtingSearchHTMLcodeHistory()">';

    innerTrHtml += '<table rel="stylesheet"class="table table-bordered">';

    innerTrHtml += '<th>Date</th>';
    innerTrHtml += '<th>Event</th>';
    innerTrHtml += '<th>User ID</th>';
    innerTrHtml += '<th>Device ID</th>';
    innerTrHtml += '<th>Description</th>';
    innerTrHtml += '<th style="width: 30%">UserInfo</th>';
    innerTrHtml += '<th style="width: 30%">DeviceInfo</th>';


    for (var index = eventListDTO.length-1; index > -1; index--) {
        innerTrHtml += '<tr>';
        innerTrHtml += '<td>' + eventListDTO[index].date+'</td>';
        innerTrHtml += '<td>' + eventListDTO[index].event+'</td>';
        innerTrHtml += '<td>' + eventListDTO[index].historyUserID+'</td>';
        innerTrHtml += '<td>' + eventListDTO[index].historyDeviceID+'</td>';
        innerTrHtml += '<td>' + eventListDTO[index].description+'</td>';



         //   innerTrHtml += '<td><textarea rows="3" disabled cols="60">' + eventListDTO[index].userInfo+'</textarea></td>';
        innerTrHtml += '<td style="table-layout: fixed;"><textarea rows="3" disabled  style="width: 100%">' + eventListDTO[index].userInfo+'</textarea></td>';

       // innerTrHtml += '<td><textarea rows="3" disabled cols="70">' + eventListDTO[index].deviceInfo+'</textarea></td>';
        innerTrHtml += '<td style="table-layout: fixed;"><textarea rows="3" disabled  style="width: 100%">' + eventListDTO[index].deviceInfo+'</textarea></td>';
        innerTrHtml += '</tr>'
        if( (eventListDTO.length-(index+1))==19){
            innerTrHtml += '</table>'

            indexHistory = index;
            lisyOfHistoryElem = eventListDTO;


            innerTrHtml += '<input type="BUTTON" class="btn" style="width: 80px; margin-bottom: 10px;" id="BackH" value="Back" onclick="backH(indexHistory, lisyOfHistoryElem)"/>';
            body.innerHTML = innerTrHtml;
            break;
        }
    }
    innerTrHtml += '</table>'

    body.innerHTML = innerTrHtml;
}


function backH(indexBack, eventListDTOback) {
    var a = true;
    // alert(indexBack+" "+ eventListDTOback.length);
    var eventListDTO = eventListDTOback;

    var body = document.getElementById('history')
    var innerTrHtml = '';
    var bodySearch = document.getElementById('BackSearch');

    if (bodySearch==null) {
        innerTrHtml += '<input type="button"  style="margin-bottom: 10px;" value="searchEvent" id="searhHistoryID" class="btn btn-updateSave" onClick="writtingSearchHTMLcodeHistory()">';
    }
    innerTrHtml += '<table rel="stylesheet"class="table table-bordered">';

    innerTrHtml += '<th>Date</th>';
    innerTrHtml += '<th>Event</th>';
    innerTrHtml += '<th>User ID</th>';
    innerTrHtml += '<th>Device ID</th>';
    innerTrHtml += '<th>Description</th>';
    innerTrHtml += '<th>UserInfo</th>';
    innerTrHtml += '<th>DeviceInfo</th>';

    var index = indexBack;
    for (index; index > -1; --index) {
        innerTrHtml += '<tr>';
        innerTrHtml += '<td>' + eventListDTO[index].date + '</td>';
        innerTrHtml += '<td>' + eventListDTO[index].event + '</td>';
        innerTrHtml += '<td>' + eventListDTO[index].historyUserID + '</td>';
        innerTrHtml += '<td>' + eventListDTO[index].historyDeviceID + '</td>';
        innerTrHtml += '<td>' + eventListDTO[index].description + '</td>';
        innerTrHtml += '<td style="table-layout: fixed;"><textarea rows="3" disabled cols="30%" style="width: 100%">' + eventListDTO[index].userInfo + '</textarea></td>';
        innerTrHtml += '<td style="table-layout: fixed;"><textarea rows="3" disabled cols="30%" style="width: 100%">' + eventListDTO[index].deviceInfo + '</textarea></td>';
        innerTrHtml += '</tr>'
        indexHistory = index+19;
        if ((indexBack - index)== 19) {
            a=false;
            innerTrHtml += '</table>'
            lisyOfHistoryElem = eventListDTO;
            innerTrHtml += '<input type="BUTTON" class="btn" style="margin-left: 20px; width: 80px; margin-bottom: 10px;" id="BackH" value="Next" onclick="next(indexHistory, lisyOfHistoryElem)"/>';

            innerTrHtml += '<input type="BUTTON" class="btn style="margin-left: 20px; width: 80px; margin-bottom: 10px;" id="BackH" value="Back" onclick="backH(indexHistory, lisyOfHistoryElem)"/>';
            body.innerHTML = innerTrHtml;
            break;
        }
    }
    if(a) {
        indexHistory = indexBack
        innerTrHtml += '</table>'
        innerTrHtml += '<input type="BUTTON" class="btn" style="width: 80px; margin-bottom: 10px;" id="BackH" value="Next" onclick="next(indexHistory, lisyOfHistoryElem)"/>';
        body.innerHTML = innerTrHtml;
    }
}



function next(indexBack, eventListDTOback) {
    var a = true;
    var eventListDTO = eventListDTOback;

    var body = document.getElementById('history')
    var innerTrHtml = '';
    var bodySearch = document.getElementById('BackSearch');
    if (bodySearch==null) {
        innerTrHtml += '<input type="button"  style="margin-bottom: 10px;" value="searchEvent" id="searhHistoryID" class="btn btn-updateSave" onClick="writtingSearchHTMLcodeHistory()">';
    }


    innerTrHtml += '<table rel="stylesheet"class="table table-bordered">';

    innerTrHtml += '<th>Date</th>';
    innerTrHtml += '<th>Event</th>';
    innerTrHtml += '<th>User ID</th>';
    innerTrHtml += '<th>Device ID</th>';
    innerTrHtml += '<th>Description</th>';
    innerTrHtml += '<th>UserInfo</th>';
    innerTrHtml += '<th>DeviceInfo</th>';


    var index = indexBack+19;
    for (index ; index > -1; --index) {
        innerTrHtml += '<tr>';
        innerTrHtml += '<td>' + eventListDTO[index].date+'</td>';
        innerTrHtml += '<td>' + eventListDTO[index].event+'</td>';
        innerTrHtml += '<td>' + eventListDTO[index].historyUserID+'</td>';
        innerTrHtml += '<td>' + eventListDTO[index].historyDeviceID+'</td>';
        innerTrHtml += '<td>' + eventListDTO[index].description+'</td>';
        innerTrHtml += '<td style="table-layout: fixed;"><textarea rows="3" disabled cols="30%" style="width: 100%">' + eventListDTO[index].userInfo+'</textarea></td>';
        innerTrHtml += '<td style="table-layout: fixed;"><textarea rows="3" disabled cols="30%" style="width: 100%">' + eventListDTO[index].deviceInfo+'</textarea></td>';
        innerTrHtml += '</tr>'
        indexHistory = index;


        if(index==indexBack){
            if( (index+19)==(lisyOfHistoryElem.length-1)){
                a = false;
                innerTrHtml += '</table>'
                innerTrHtml += '<input type="BUTTON" class="btn" style="margin-left: 20px; width: 80px; margin-bottom: 10px;" id="BackH" value="Back" onclick="backH(indexHistory, lisyOfHistoryElem)"/>';
                body.innerHTML = innerTrHtml;
                break
            }else {
                a = false;
                innerTrHtml += '</table>'
                innerTrHtml += '<input type="BUTTON" class="btn" style="margin-left: 20px; width: 80px; margin-bottom: 10px;" id="BackH" value="Next" onclick="next(indexHistory, lisyOfHistoryElem)"/>';
                innerTrHtml += '<input type="BUTTON" class="btn" style="margin-left: 20px; width: 80px; margin-bottom: 10px;" id="BackH" value="Back" onclick="backH(indexHistory, lisyOfHistoryElem)"/>';
                body.innerHTML = innerTrHtml;
                break;
            }
        }
    }
    if(a) {
        innerTrHtml += '</table>';
        body.innerHTML = innerTrHtml;
    }
}


function writtingSearchHTMLcodeHistory() {

    var searchEventButton = document.getElementById("searhHistoryID");
    if(searchEventButton!=null) {
        searchEventButton.type = "hidden";
    }

    var body = document.getElementById('searcHistory');
    var innerTrHtml = '';

    innerTrHtml += '<H2 align="center">Please enter search parameters</H2>';
    innerTrHtml += '<table border="1" rel="stylesheet" class="table table-bordered">';
    innerTrHtml += '<tr>';
    innerTrHtml += '<th>UserInfo</th>';
    innerTrHtml += '<th>DeviceInfo:</th>';
    innerTrHtml += '<th>Event / Date</th>';
    innerTrHtml += '</tr>';
    innerTrHtml += '<tr>';
    innerTrHtml += '<td><select id="searchByUserPar" class="btn dropdown-toggle btn-default" name="userPar" style="width: 28%;">';
    innerTrHtml += '<option value="" selected></option>';
    innerTrHtml += '<option value="name">name</option>';
    innerTrHtml += '<option value="sername">surname</option>';
    innerTrHtml += '<option value="id">id</option>';
    innerTrHtml += '</select></td>';

    innerTrHtml += '<td><select id="searchByDevPar" class="btn dropdown-toggle btn-default" name="devicePar" style="width: 28%;">';
    innerTrHtml += '<option value="" selected></option>';
    innerTrHtml += '<option value="model">model</option>';
    innerTrHtml += '<option value="serialNumber">serial number</option>';
    innerTrHtml += '<option value="devId">id</option>';
    innerTrHtml += '</select></td>';

    innerTrHtml += '<td><select id="event" class="btn dropdown-toggle btn-default" name="event" style="width: 28%;">';
    innerTrHtml += '<option value="" selected></option>';
    innerTrHtml += '<option value="CreateUser">CreateUser</option>';
    innerTrHtml += '<option value="UpdateUser">UpdateUser</option>';
    innerTrHtml += '<option value="DeleteUser">DeleteUser</option>';
    innerTrHtml += '<option value="CreateDevice">CreateDevice</option>';
    innerTrHtml += '<option value="UpdateDevice">UpdateDevice</option>';
    innerTrHtml += '<option value="DeleteDevice">DeleteDevice</option>';
    innerTrHtml += '<option value="Assign">Assign</option>';
    innerTrHtml += '<option value="UnAssign">UnAssign</option>';
    innerTrHtml += '</select></td>';
    innerTrHtml += '</tr>';

    innerTrHtml += '</tr>';
    innerTrHtml += '<td align="center"><input class="form-control" style="width: 29%" type="text" id="userInput" name="userInput"></td>';

        innerTrHtml += '<td align="center"><input class="form-control" style="width: 29%" input type="text" id="deviceInput" name="deviceInput"></td>';

    innerTrHtml +='<form action="#">';
    innerTrHtml += '<td width="30%">';
    innerTrHtml +='<div>From: <input type="date" id="fDate" name="fDate" class="tcal" value="yy-mm-dd"/>';
    innerTrHtml += '   Till: <input type="date" id="lDate"name="lDate" class="tcal" value="yy-mm-dd" /></div></td>';
    innerTrHtml +='</form>';


    innerTrHtml += '</tr>';
    innerTrHtml += '</table>';

    innerTrHtml += '<div class="center">';

    innerTrHtml += '<input type="BUTTON" class="btn" style="margin-left: 20px; width: 80px; margin-bottom: 10px;" id="BackSearch" value="Back" onclick="showHistory()"/>';

    innerTrHtml += '<input type="button" style="margin-left: 20px; margin-bottom: 10px;" class="btn btn-updateSave" name="Save" value="Search" onclick="searchHistory()"/>';
    innerTrHtml += '<input type="BUTTON" class="btn" style="margin-left: 20px; width: 80px; margin-bottom: 10px;" value="Clean" onclick="writtingSearchHTMLcodeHistory()"/></div>';

    body.innerHTML = innerTrHtml;

    f_tcalInit();
}

function cleanButton() {
    clearHistory();
    writtingSearchHTMLcodeHistory();
}

function clearHistory() {
    var body = document.getElementById('searcHistory');
    var innerTrHtml = '';
    body.innerHTML = innerTrHtml;
    var body = document.getElementById('history');
    innerTrHtml = '';
    body.innerHTML = innerTrHtml;

}