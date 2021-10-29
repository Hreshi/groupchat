let chatDiv = document.getElementById('chat-page')
let loadingDiv = document.getElementById('loading')
let sendBtn = document.getElementById('send-btn')
let messageList = document.getElementById('message-list')
let messageInput = document.getElementById('message-input')
let stompClient = null
let username = sessionStorage.getItem('username')
let password = sessionStorage.getItem('password')

sendBtn.addEventListener('click', function (event) {
    event.preventDefault()
    stompClient.send('/app/message', {}, JSON.stringify({'username':username,'password':password, 'message':messageInput.value}))
    messageInput.value = ""
})

function make_connection() {
    let socket = new SockJS('/message-system')
    stompClient = Stomp.over(socket)
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/chat/messages', function (response) {
            const res = JSON.parse(response.body)   
            appendMessage(res.username, res.message)
        })
        showChatPage()
    })
}

function makeMessage(name, message) {
    let messageDiv = document.createElement('div')
    let nameP = document.createElement('span')
    nameP.innerText = name 
    nameP.className = " username"
    let messageP = document.createElement('span')
    messageP.innerText = message
    messageP.className = " ms-1"
    messageDiv.append(nameP)
    messageDiv.append(messageP)
    messageDiv.className = "card text-wrap left-message mt-3 text-secondary "
    return messageDiv
}

function appendMessage(name, message) {
    messageList.append(makeMessage(name, message))
    messageList.scrollTop = messageList.scrollHeight
}
function showChatPage() {
    document.getElementById('loading').hidden = true
    document.getElementById('chat-page').hidden = false
}
make_connection()