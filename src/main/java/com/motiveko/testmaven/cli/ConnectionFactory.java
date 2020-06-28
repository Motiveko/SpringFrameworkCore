package com.motiveko.testmaven.cli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionFactory implements InitializingBean, DisposableBean{

	private String driverClass;
	private String url;
	private String user;
	private String password;
	@Getter private Connection connection = null;

	public ConnectionFactory(String driverClass, String url, String user, String password) {
		this.driverClass = driverClass;
		this.url = url;
		this.user = user;
		this.password = password;

	}

//	String url = "jdbc:h2:mem:test;MODE=MySQL;";
	public Connection createConnection() throws SQLException {
		try {
			Class.forName(this.driverClass);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}			
		return DriverManager.getConnection(url, user, password);
	}

	// InitializingBean 을 implements 하고 overriding한 method, bean 생성 시 바로 callback되어 실행한다.
	@Override 
	public void afterPropertiesSet() throws Exception {
//		this.connection = createConnection();
	}
	
	// Application Context의 bean에 init-method 로 설정된부분, afterPropertiesSet()과 동일한 역할을 한다.
	// 전체 bean에 다 적용하고 싶으면 <beans> 에다가 default-init-method 설정해주면됨
	public void init() throws SQLException {
		log.info("init()");
		this.connection = createConnection();
	}
	//DisposableBean 을 Implements하면 생성하는 method, bean이 없어지기 직전에 실행
	@Override
	public void destroy() throws Exception {
//		log.info("factory cleanUp()");
//		if(this.connection!=null) this.connection.close();
	}
	
	//위와 동일하다. xml에서 설정함
	public void cleanup() throws SQLException {
		log.info("factory cleanUp()");
		if(this.connection!=null) this.connection.close();		
	}
}
