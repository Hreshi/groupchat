package com.aissms.groupchat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/chat/messages")
    public Message sendMessage(Message message) throws Exception {
        return message;
    }

    @GetMapping("/socket")
    public String testMessaging() {
        return "socket";
    }

    @GetMapping("/chat")
    public String chatMessaging() {
        return "chat";
    }

}