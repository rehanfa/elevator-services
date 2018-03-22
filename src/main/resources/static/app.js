var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#status").html("");
}

function getContextPath() {
   return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}


function connect() {
    var context = getContextPath();
    if (context.substring('/') < 0) {
        context = '';
    }
    var socket = new SockJS(context + '/elevator-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/status', function (status) {
            showStatus(JSON.parse(status.body).content);
        });
    });
}

function showStatus(message) {
    $("#status").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
});

