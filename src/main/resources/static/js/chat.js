let usernameInput = document.getElementById('username')
let continueBtn = document.getElementById('continue-btn')
let chatDiv = document.getElementById('chat-page')
let nameDiv = document.getElementById('username-page')
let sendBtn = document.getElementById('send-btn')
let messageList = document.getElementById('message-list')
let messageInput = document.getElementById('message-input')
let stompClient = null
let username = null


continueBtn.addEventListener('click', function () {
    username = usernameInput.value
    make_connection()
    nameDiv.hidden = true
    chatDiv.hidden = false
})

sendBtn.addEventListener('click', function () {
    stompClient.send('/app/message', {}, JSON.stringify({'name':username, 'message':messageInput.value}))
})

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
    messageInput.value = ''
    let messageEle = document.createElement('li')
    messageEle.innerHTML = '<p>' + name + ' : ' + message + '</p>'
    messageList.append(messageEle)
}