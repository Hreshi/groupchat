package com.aissms.groupchat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/chat/messages")
    public Message sendMessage(Message message) throws Exception {
        return message;
    }

    @GetMapping("/socket")
    public String messaging() {
        return "socket";
    }

}