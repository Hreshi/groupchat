package com.aissms.groupchat;

public class Message{
	private String username;
	private String password;
	private String message;
	
	Message(String username, String password, String message) {
		this.username = username;
		this.password = password;
		this.message = message;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getMessage() {
		return message;
	}
	public void clearPass() {
		password = "abcd";
	}
}