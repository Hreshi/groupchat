package com.aissms.groupchat.auth;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class DBService {
	static final String dbURL = "jdbc:mariadb://localhost:3306/logsystem";
	static final String username = "hreshi";
	static final String password = "Laxmi.8149";
	private static Connection connection;

	@PostConstruct
	public void initDatabase() throws Exception{
		DBService.connection = DriverManager.getConnection(DBService.dbURL, DBService.username, DBService.password);
		// create table if not exists
	}

	public static Statement getStatement() throws Exception{
		return connection.createStatement();
	}

}