package com.aissms.groupchat.auth;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBService {
	static String dbURL = "jdbc:mysql://localhost:3306/dbname";
	static String username = "user";
	static String password = "password";
	private Connection connection;
	{
		if(System.getenv("DB_SERVER") != null) {
			dbURL = "jdbc:mysql://"+System.getenv("DB_SERVER") + ":3306/" + System.getenv("DB_NAME");
			username = System.getenv("DB_USERNAME");
			password = System.getenv("DB_PASSWORD");
		} else {
			System.out.println("Connected to localhost!");
		}
	}


	public Statement getStatement() throws Exception{
		connection = DriverManager.getConnection(DBService.dbURL, DBService.username, DBService.password);
		return connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}

	public void close(Statement stmt, ResultSet res) throws Exception{
		connection.close();
		stmt.close();
		if(res != null) res.close();
	}

}