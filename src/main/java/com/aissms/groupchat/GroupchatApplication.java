package com.aissms.groupchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class GroupchatApplication {

	public static void main(String[] args) {
		// Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cred","hreshi","Laxmi.8149")
		SpringApplication.run(GroupchatApplication.class, args);
	}

}
