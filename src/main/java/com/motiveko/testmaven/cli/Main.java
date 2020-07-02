package com.motiveko.testmaven.cli;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;

import com.motiveko.testmaven.cli.config.AppConfig;
import com.motiveko.testmaven.cli.controller.MemberController;
import com.motiveko.testmaven.cli.dao.MemberDao;

import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.DataSource;


@Slf4j
public class Main { 

	public static void main(String[] args) throws ClassNotFoundException, SQLException {		
		log.info("Hello World");			
														//()안의 클래스가 있는 패키지의 하위 디렉토리에 있는 전체클래스중에서 @Configuration 붙은 class를 bean으로 생성한다.
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class);
		context.refresh();	
		MemberDao dao = context.getBean(MemberDao.class);
		createTable(context.getBean(DataSource.class).getConnection());
//		createTable(DataSourceUtils.getConnection(context.getBean(DataSource.class)));

		System.out.println("=======================사용자의 username, password를 입력해주세요.=======================");
		Scanner sc = new Scanner(System.in);
		
		System.out.println("username : " );
		String username = sc.nextLine();
		System.out.println("password : " );
		String password = sc.nextLine();
		
		MemberController controller = context.getBean(MemberController.class);
		
		controller.insert(username, password);
		
		controller.print();
		context.close();
	}
	

	
	public static void createTable(Connection connection) throws SQLException {
		connection.createStatement()
		.execute("create table member(id int auto_increment, username varchar(255) not null, password varchar(255) not null, primary key(id))");		
	}	
	
}

