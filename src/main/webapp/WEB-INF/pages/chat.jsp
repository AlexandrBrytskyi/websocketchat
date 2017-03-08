<%@ page import="app.model.RoomNames" %>
<%@include file="include.jsp" %>


<html lang="en,ru">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <![endif]-->
    <title>WebSocket chat</title>
    <!-- BOOTSTRAP CORE STYLE CSS -->


    <%--websocket--%>
    <%--<script src="/webjars/sockjs-client/sockjs.min.js"></script>--%>
    <%--<script src="/webjars/stomp-websocket/stomp.min.js"></script>--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.2/sockjs.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>


</head>
<body style="font-family:Verdana" onload="setStartTrue()">
<div class="container">
    <div class="row " style="padding-top:40px;">
        <h3 class="text-center">BOOTSTRAP CHAT EXAMPLE </h3>


        <div class="col-md-4">
            <div class="panel panel-primary" <%--style="max-height: 45%--%>>
                <div class="panel-heading" style="overflow-y: auto">
                    Available Rooms
                </div>
                <div class="panel-body">
                    <ul class="media-list">
                        <c:set var="rooms" value="<%=RoomNames.values()%>"></c:set>
                        <c:forEach items="${rooms}" var="room">
                            <li class="media" id="chatDesc${room}">

                                <div class="media-body">

                                    <div class="media">
                                        <a class="pull-left" href="#" onclick="roomChanded('${room}')">
                                            <img class="media-object img-circle" style="max-height:40px;"
                                                 src="assets/img/roomPicture.png"/>
                                        </a>
                                        <div class="media-body">
                                            <h5 id="chatName">${room.toString()}</h5>

                                            <small class="text-muted" id="lastMessage${room}">here should be label if
                                                user is
                                                in chat
                                            </small>
                                        </div>
                                    </div>

                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

            <%--<div class="panel panel-primary" style="max-height: 45%">--%>
            <%--<div class="panel-heading" style="overflow-y: auto">--%>
            <%--ONLINE USERS--%>
            <%--</div>--%>
            <%--<div class="panel-body">--%>
            <%--<ul class="media-list">--%>

            <%--&lt;%&ndash;<li class="media">&ndash;%&gt;--%>

            <%--&lt;%&ndash;<div class="media-body">&ndash;%&gt;--%>

            <%--&lt;%&ndash;<div class="media">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<a class="pull-left" href="#">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<img class="media-object img-circle" style="max-height:40px;"&ndash;%&gt;--%>
            <%--&lt;%&ndash;src="assets/img/user.png"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<div class="media-body">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<h5>Alex Deo | User </h5>&ndash;%&gt;--%>

            <%--&lt;%&ndash;<small class="text-muted">Active From 3 hours</small>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

            <%--</ul>--%>
            <%--</div>--%>
            <%--</div>--%>

        </div>
        <div class="col-md-8">
            <div class="panel panel-info">
                <div class="panel-heading">
                    History
                    <div class="btn-group" role="group" style="float: right">
                        <button type="button" class="btn btn-default" onclick="loadPreviousMessages()">More</button>
                    </div>
                </div>
                <div class="panel-body" id="messages-block" style="max-height: 70%; overflow-y: auto">
                    <ul class="media-list" id="messages-list">

                    </ul>
                </div>
                <div class="panel-footer">
                    <div class="input-group">
                        <input type="text" name="messageInput" class="form-control" placeholder="Enter Message"/>
                        <span class="input-group-btn">
                                        <button class="btn btn-info" type="button" name="sendButton">SEND</button>
                                    </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script>
    var currentRoom;
    var openedRoomsWithMessages = [];
    var ajaxGotMessages;
    var userName = '${user.name}';
    var stompClients = [];
    var start = false;

    function setStartTrue() {
        start = true;
    }

    //    var odd = new Object();
    //    odd.sda = "sds";
    //    odd['sda'] = "sdadad";
    //    alert(odd['sda']);


    function roomChanded(roomName) {
        if (!start) return;
        currentRoom = roomName;
        $('#chatDesc' + roomName)

//        look if we are in this room
        var roomDetails = watchIfUserIsInRoom(roomName);
        if (null == roomDetails) {
//            if we go in room first time add room to opened and go in room on server and load messages
            goInRoomAjax(roomName);
            getMessagesAjax(roomName, 1, 10, true);

            openedRoomsWithMessages.push({roomName: roomName, messages: ajaxGotMessages});
        }
        clearBlockAndAddMessages(watchIfUserIsInRoom(roomName)['messages']);
    }


    function clearBlockAndAddMessages(messages) {
//        clear
        $('#messages-list').empty();

        if (null != messages) for (var i = 0; i < messages.length; i++) {
            addMessageOnBottom(messages[i]);
        }

    }

    function watchIfUserIsInRoom(roomName) {
        for (var i = 0; i < openedRoomsWithMessages.length; i++) {
            if (openedRoomsWithMessages[i]['roomName'] == (roomName)) {
                return openedRoomsWithMessages[i];
            }
        }
        return null;
    }


    function goInRoomAjax(roomName) {
        $.ajax({
            url: "/ajax/goInRoom",
            data: {room: roomName, userName: userName},
            success: function (result) {
                <%--alert(${user.name})--%>

            }
        });
        connectToRoom(roomName);
    }

    function getMessagesAjax(roomName, page, size, print) {
        $.ajax({
            url: "/ajax/getMessages",
            data: {room: roomName, page: page, size: size},
            async: false,
            success: function (result) {
                var messages = JSON.parse(result);
//                alert("Got ajax messages: " + messages.toString());
                ajaxGotMessages = messages;

//                set messages on the bottom of block
                if (print)   for (var i = 0; i < messages.length; i++) {
                    addMessageOnBottom(messages[i]);
                }
            }
        });
    }

    function loadPreviousMessages() {

        var messagesInRoom = watchIfUserIsInRoom(currentRoom)['messages'];
        var length = messagesInRoom.length;
        var ostatok = 10 - (length % 10);
        var pages = length / 10;
        var page;
        var length = 10;
        var room = currentRoom;
        if (ostatok == 0) {
            page = pages;
        } else {
            if (length != 0) {
                page = pages + 1;
            } else return;
        }


        getMessagesAjax(room, parseInt(page), length, false);
        var messages = ajaxGotMessages;
        if (messages.length == 0) {
            pages--;
            return;
        }

        var messagesInroomstr = JSON.stringify(messagesInRoom);
        for (var i = messages.length - 1; i > 0; i--) {
            var messageByIndex = messages[i];
            var message = JSON.stringify(messageByIndex);
            if (!messagesInroomstr.includes(message)) {
                addMessageOnTop(messageByIndex);
                messagesInRoom.unshift(messageByIndex);
            }
        }

    }

    function addMessageOnBottom(message) {
        $('#messages-list').append(
            '<li class="media">' +
            '<div class="media-body">' +
            '<div class="media">' +
            '<a class="pull-left" href="#">' +
            '<img class="media-object img-circle " src="../../assets/img/user.png"/></a>' +
            '<div class="media-body">' + message.messageContent + '<br/>' +
            '<small class="text-muted">' + message.sender + ' to ' + message.reciever + ' | ' + new Date(message.dateSent) + '</small>' +
            '<hr/>' +
            '</div>' +
            '</div>' +
            '   </div>' +
            '</li>');

        $("#messages-block").scrollTop($("#messages-block")[0].scrollHeight);

    }

    function addMessageOnTop(message) {
        $('#messages-list').prepend(
            '<li class="media">' +
            '<div class="media-body">' +
            '<div class="media">' +
            '<a class="pull-left" href="#">' +
            '<img class="media-object img-circle " src="../../assets/img/user.png"/></a>' +
            '<div class="media-body">' + message.messageContent + '<br/>' +
            '<small class="text-muted">' + message.sender + ' to ' + message.reciever + ' | ' + new Date(message.dateSent) + '</small>' +
            '<hr/>' +
            '</div>' +
            '</div>' +
            '   </div>' +
            '</li>');

    }


    $("button[name=sendButton]").click(function () {
        sendMessage(currentRoom);
    });


    //
    //
    //


    function connectToRoom(roomName) {
        var socket = new SockJS('/brytskyi-chat');
        var stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
//            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/' + roomName + '/messages', function (message) {
                var messageRecieved = JSON.parse(message.body);
//
//                alert("have new message " + messageRecieved + ", curr room " + currentRoom);
                if (messageRecieved['room'] == (currentRoom)) {
                    addMessageOnBottom(messageRecieved);
                    soundPlay();
//                    alert("added" + messageRecieved);
                }
                var roomDetails = watchIfUserIsInRoom(messageRecieved['room']);
                if (null != roomDetails) {
                    roomDetails['messages'].push(messageRecieved);

                }
                var li = $('#chatDesc' + roomName);
                li.parent().prepend(li);
                $('#lastMessage' + roomName).text(JSON.stringify(messageRecieved.messageContent).substring(0, 100));
            });
        });
        stompClients.push({room: roomName, stomp: stompClient});

    }

    function soundPlay() {
        var audio = new Audio(); // Создаём новый элемент Audio
        audio.src = '../../assets/sounds/sound.mp3'; // Указываем путь к звуку "клика"
        audio.autoplay = true; // Автоматически запускаем
    }

    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }

    function getStomp(roomName) {
        for (var i = 0; i < stompClients.length; i++) {
            if (stompClients[i]['room'] == (roomName)) return stompClients[i]['stomp'];
        }
    }

    function sendMessage(roomName) {
        var stompclient = getStomp(roomName);
        if (null != stompclient && $("input[name=messageInput]").val() != "") stompclient.send("/socket/" + currentRoom + "/chat", {}, JSON.stringify({
            room: currentRoom,
            messageContent: $("input[name=messageInput]").val(),
            dateSent: null,
            sender: userName,
            reciever: null
        }));
        $("input[name=messageInput]").val("");
    }


</script>


</html>