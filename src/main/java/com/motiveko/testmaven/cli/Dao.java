package com.motiveko.testmaven.cli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lombok.extern.slf4j.Slf4j;

//Data Access Object, db관련 코드는 다 여기로 몰아넣는다.
@Slf4j //Logger를 log라는 변수로 바로 사용 가능하다.
public class Dao {

	
	public void run() {
		//Statement, Connection 등 close() 필요한 객체를 자동으로 close()하기 위해 java7 부터 autoclosable을 구현시켰다.
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url = "jdbc:h2:mem:test;MODE=MySQL;";
		
		//try resource문, connection, statement 선언을 try(resource)로 해준다.
		try (Connection connection = DriverManager.getConnection(url, "sa", "");
			Statement statement = connection.createStatement();){
			
			
			connection.setAutoCommit(false);
			
			statement.execute("create table member(id int auto_increment, username varchar(255) not null, password varchar(255) not null, primary key(id))");
			
			try {				
				statement.executeUpdate("insert into member(username,password) values('motiveko','1234')");
				connection.commit();				
			} catch (SQLException e) {
				//DML 구문 실행 중 SQLException 발생하면 롤백
				connection.rollback();
			}
			
			//result query 실행 결과를 row단위를 frame 받은것이다.
			ResultSet resultSet = statement.executeQuery("select id, username, password from member");
			// resultset의 커서는 맨 처음에는 아무것도 안가르키고 있다가 next하면 첫 프레임으로, 계속 다음 프레임으로 넘어가며 
			// 프레임이 존재하면 true 없으면 false를 반환한다.
			while(resultSet.next()) {
				//OOP를 활용한 코드의 간소화
				Member member = new Member(resultSet);
				log.info(member.toString());
			}
			log.info("Bye World!");
		} catch (SQLException e) {	
			
		} // finally{ resource.close} 해줘야 하나 try에 resource가 선언되어서 불필요해졌따...맞나몰겠네
			 
	}
}
