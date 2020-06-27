package com.motiveko.testmaven.cli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private String url;
	private String user;
	private String password;

	public ConnectionFactory(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

//	String url = "jdbc:h2:mem:test;MODE=MySQL;";
	public Connection creatConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

}
