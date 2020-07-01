package com.motiveko.testmaven.cli;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Dao2 {
	// Aspect를 활용한 Dao클래스 리빌딩, 트랜잭션을 Aop로 빼서 처리했다(setAutoCommit, commit, rollback) 
	private Connection connection; 

	public Dao2(Connection connection) {
		this.connection = connection;
	} 
	
	
	//
	public void insert() throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("insert into member(username,password) values('motiveko','1234')");	
	}
	
	public void print() throws SQLException {
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select id, username, password from member");
		while (resultSet.next()) {
			Member member = new Member(resultSet);
			log.info(member.toString());
		}

	}
}

