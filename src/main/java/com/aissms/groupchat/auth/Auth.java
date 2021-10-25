package com.aissms.groupchat.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.sql.*;

@Controller
public class Auth {
	private static String getUserQuery = "select * from cred where username=\"%s\";";
	private static String setUserQuery = "insert into cred (username, password) values (\"%s\", \"%s\");";

	@GetMapping("/")
	public String signUpTemp() {
		return "home";
	}

	@PostMapping("/signin")
	public String signInService(@RequestParam Map map) throws Exception {
		boolean success = validateUser(map.get("username").toString(), map.get("password").toString());
		return success ? "chat" : "home";
	}
	@PostMapping("/signup")
	public String signUpService(@RequestParam Map map) throws Exception{
		boolean success = registerUser(map.get("username").toString(), map.get("password").toString());
		return success ? "chat" : "home";
	}
	public boolean validateUser(String user, String pass) throws Exception {
		Statement statement = DBService.getStatement();
		if(user != null && pass != null && validString(user) && validString(pass)) {
			ResultSet res = statement.executeQuery(String.format(Auth.getUserQuery, user));
			return res.first() && pass.equals(res.getString("password"));
		}
		return false;
	}
	public boolean registerUser(String user, String pass) throws Exception {
		Statement statement = DBService.getStatement();
		if(user != null && pass != null && validString(user) && validString(pass)) {
			if(!isUserAlreadyRegistered(user)) {
				statement.executeUpdate(String.format(Auth.setUserQuery, user, pass));
				return true;
			}
		}
		return false;
	}
	private boolean isUserAlreadyRegistered(String user) throws Exception {
		Statement statement = DBService.getStatement();
		ResultSet res = statement.executeQuery(String.format(Auth.getUserQuery, user));
		return res.first();
	}
	private boolean validString(String str) {
		return str != null && str.length() >=8 && str.length() <= 20;
	}
}