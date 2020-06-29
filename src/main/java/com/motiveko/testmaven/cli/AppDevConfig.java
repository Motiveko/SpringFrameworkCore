package com.motiveko.testmaven.cli;

import java.sql.Connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// bean 설정으로 등록이 되면서 이 객체가 spring설정에 대해서 관련있는 bean이라는것을 알려준다.
// anotaion 설명 읽으면 이해댐
@Configuration
//@ComponentScan(basePackageClasses = AppConfig.class) // Main클래스들어있는 패키지 하위로 스캔한다.
@Profile("dev")
public class AppDevConfig {
	
	@Bean
	public B b() {
		return new B();
	}
					//@PostConstruct, @PreDestory 를 A에 써주는 것은 해당 빈에 설정값을 넣는것이므로 지양한다. 클래스에는 클래스 로직만 들어가게!
	@Bean(initMethod = "init", destroyMethod = "destroy")
	public A a(B b) {
		return new A(b);
	}
	
	
	@Bean(initMethod = "init",destroyMethod = "destroy")
	public ConnectionFactory connectionFactory() {
		return new ConnectionFactory("org.h2.Driver", "jdbc:h2:file:~/test", "sa", "");
	}
	
	@Bean
	public Connection connection(ConnectionFactory connectionFactory) {
		return connectionFactory.getConnection();
	}
	
	@Bean
	public Dao dao(Connection connection) {
		return new Dao(connection);
	}

}
