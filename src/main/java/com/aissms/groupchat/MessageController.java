package com.aissms.groupchat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import java.util.Map;

import com.aissms.groupchat.auth.Auth;

@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/chat/messages")
    public Message sendMessage(Message message) throws Exception {
        if(message != null && message.getMessage()!=null && !message.getMessage().equals("")) {
            Auth auth = new Auth();
            if(!auth.validateUser(message.getUsername(), message.getPassword())) {
                message = null;
            } else {
                message.clearPass();
            }
        } else {
            message = null;
        }
        return message;
    }

}