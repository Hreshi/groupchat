let usernameInput = document.getElementById('username')
let continueBtn = document.getElementById('continue-btn')
let chatDiv = document.getElementById('chat-page')
let nameDiv = document.getElementById('username-page')
let sendBtn = document.getElementById('send-btn')
let messageList = document.getElementById('message-list')
let messageInput = document.getElementById('message-input');
var scr;
let stompClient = null
let username = null

window.onload = function() {
  usernameInput.focus();
}

continueBtn.addEventListener('click', function () {
        username = usernameInput.value;
		if(usernameInput.value == ""){
			alert("Enter Your Name...");
		}
		else{
    username = usernameInput.value;
    document.getElementById("headname").innerHTML=username;
    make_connection()
    nameDiv.hidden = true;
    chatDiv.hidden = false;
        messageInput.focus();
    }
});



usernameInput.addEventListener("keyup", function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        continueBtn.click();
    }
});



sendBtn.addEventListener('click', function () {
		if(messageInput.value == ""){
			alert("Enter Message...");
		}
		else{

        stompClient.send('/app/message', {}, JSON.stringify({'name':username, 'message':messageInput.value}));
        messageInput.value = '';
    }
})
messageInput.addEventListener("keyup", function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        sendBtn.click();
    }
});

function make_connection() {
    let socket = new SockJS('/message-system')
    stompClient = Stomp.over(socket)
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/chat/messages', function (response) {
            const res = JSON.parse(response.body)   
            appendMessage(res.name, res.message)
        })
    })
}

function appendMessage(name, message) {

    //scr = messageInput.value + counter++;
    let messageEle = document.createElement('li');
   // messageEle.setAttribute("id", scr );
    messageEle.setAttribute("tabindex", "-1" )
    messageEle.innerHTML =name + ' : ' + message ;
    messageList.append(messageEle);
    messageList.lastElementChild.focus();
    messageInput.focus();
}
