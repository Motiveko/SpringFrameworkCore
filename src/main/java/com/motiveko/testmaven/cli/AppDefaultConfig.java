package com.motiveko.testmaven.cli;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

// bean 설정으로 등록이 되면서 이 객체가 spring설정에 대해서 관련있는 bean이라는것을 알려준다.
// anotaion 설명 읽으면 이해댐
//@ComponentScan(basePackageClasses = AppConfig.class) // Main클래스들어있는 패키지 하위로 스캔한다. 
//@Configuration
//@Profile("default")
@PropertySource("classpath:application-${spring.profiles.active}.properties")
public class AppDefaultConfig {
	
	@Bean(initMethod = "init",destroyMethod = "destroy")
	public ConnectionFactory connectionFactory(
			@Value("${jdbc.driver-class}") String driverClass,
			@Value("${jdbc.url}") String url,
			@Value("${jdbc.username}") String username,
			@Value("${jdbc.password}") String password
			) {
		return new ConnectionFactory(driverClass, url, username, password);
	}
}
