package com.aissms.groupchat;

public class Message{
	private String name;
	private String message;
	
	Message() {
	}
	Message(String message, String name) {
		this.message = message;
		this.name = name;
	}
	
	// getters and setters methods

	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}