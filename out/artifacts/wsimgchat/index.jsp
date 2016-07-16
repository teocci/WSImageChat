<!DOCTYPE html>

<html>
<head>
    <title>Websocket Chat with Image Transfer</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <script src="websocket.js"></script>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<div>
    <input type="file" id="imageinput" onchange="previewImage()"/>
    <button type="button" onclick="sendImage();">Send</button>
</div>
<div>
    <input type="text" id="messageinput" onchange="previewImage()"/>
    <button type="button" onclick="sendText();">Send</button>
</div>
<div>
    <button type="button" onclick="openSocket();">Open</button>
    <button type="button" onclick="closeSocket();">Close</button>
</div>
<div><span style="border:2px red dotted; display: inline-block; "><img height="250" id="imgpreview" src=""
                                                                       alt="preview image"/></span></div>
<!-- Server responses get written here -->
<div id="messages"></div>

</body>
</html>
