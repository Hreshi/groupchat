package com.aissms.groupchat.auth;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

@Component
public class DBService {

	static String dbURL = "jdbc:mysql://localhost:3306/dbname";
	static String username = "user";
	static String password = "password";
	private Connection connection;
	
	@PostConstruct
	public void databaseConfig() {
		if(System.getenv("DB_SERVER") != null) {
			DBService.dbURL = "jdbc:mysql://"+System.getenv("DB_SERVER") + ":3306/" + System.getenv("DB_NAME");
			DBService.username = System.getenv("DB_USERNAME");
			DBService.password = System.getenv("DB_PASSWORD");
		} else {
			System.out.println("working on non heroku env!");
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
