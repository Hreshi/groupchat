package com.aissms.groupchat;

public class Message{
	private String name;
	private String message;
	
	Message(String message, String name) {
		this.message = message;
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public String getName() {
		return name;
	}
}