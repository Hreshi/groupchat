let url = location.host == 'localhost' ?
'ws://localhost:8081/ws' : location.host == 'javascript.local' ?
`ws://javascript.local/article/websocket/chat/ws` : // dev integration with local site
`wss://javascript.info/article/websocket/chat/ws`; // prod integration with javascript.info
let socket=new WebSocket(url);

document.forms.publish.onsubmit=function() {
    let outgoingMessage=this.messageinp.value;
    socket.send(outgoingMessage);
    this.messageinp.value="";
    return false;
};

socket.onmessage=function (event) {
    let incomingMessage=event.data;
    showMessage(incomingMessage);
}
socket.onclose=event=> console.log(`closed ${event.code}`);
function showMessage(messageinp) {
    let messageElem=document.createElement('div');
    messageElem.textContent=messageinp;
    document.getElementById('container').prepend(messageElem);
}