package com.aissms.groupchat.auth;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

@Component
public class DBService {
	static final String dbURL = "jdbc:mysql://"+System.getenv("DB_SERVER") + ":3306/" + System.getenv("DB_NAME");
	static final String username = System.getenv("DB_USERNAME");
	static final String password = System.getenv("DB_PASSWORD");
	
	private static Connection connection;

	@PostConstruct
	public void initDatabase() throws Exception{
		DBService.connection = DriverManager.getConnection(DBService.dbURL, DBService.username, DBService.password);
		// create table if not exists
	}

	public static Statement getStatement() throws Exception{
		return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}

}