/**
 * Created by teocci on 7/16/16.
 */
var webSocket = new WebSocket("ws://localhost:8080/wsimgchat/wschat");
webSocket.onmessage = onMessage;


var webSocket;
var messages = document.getElementById("messages");
var reader;

function onMessage(event) {
    console.log(event.data);
    writeResponse(event.data);
}

function openSocket() {
    // Ensures only one connection is open at a time
    if (webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED) {
        messages.innterHTML += "<br/>" + "WebSocket is already opened.";
        return;
    }
    // Create a new instance of the websocket
    webSocket = new WebSocket("ws://localhost:8080/wsimgchat/wschat");

    /**
     * Binds functions to the listeners for the websocket.
     */
    webSocket.onopen = function (event) {
        // For reasons I can't determine, onopen gets called twice
        // and the first time event.data is undefined.
        // Leave a comment if you know the answer.
        if (event.data === undefined)
            return;
    };

    webSocket.onmessage = function (event) {
        console.log(event.data);
        writeResponse(event.data);
    };

    webSocket.onclose = function (event) {
        messages.innerHTML += "<br/>" + "Connection closed";
    };
}

/**
 * Sends the value of the text input to the server
 */
function sendImage() {
    sendMessage("image", reader.result);
}

function sendText() {
    sendMessage("text", document.getElementById("messageinput").value);
}

function sendMessage(dataType, dataValue) {
    var json = JSON.stringify({
        "type": dataType,
        "data": dataValue
    });
    webSocket.send(json);
}

function closeSocket() {
    webSocket.close();
}

function writeResponse(json) {

    var response = JSON.parse(json);
    var output;
    console.log(response);
    console.log(response.type);
    console.log(response.data);
    switch (response.type) {
        case "image":
            output = "<img src=\'" + response.data + "\'/>"
            break;
        case "text":
            output = response.data;
            break;
    }

    messages.innerHTML += "<br/>"
        + output;
}

function previewImage() {
    var file = document.getElementById("imageinput").files[0];
    var preview = document.getElementById("imgpreview");
    reader = new FileReader();

    reader.onloadend = function () {
        preview.src = reader.result;
    };

    if (file && file.type.match("image")) {
        reader.readAsDataURL(file);
    } else {
        preview.src = "";
    }

}
