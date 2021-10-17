package com.aissms.groupchat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class Home {
	
	@GetMapping("/")
	public String home(Model model) {
		return "chat";
	}
}