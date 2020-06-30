package com.motiveko.testmaven.cli;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("default | dev") // dev와 default 공동으로 사용하는것
// AppContext를 Profile에 따라 동적으로 택하는 방식이 아니라, propertySource파일을 동적으로 가져온다(url값만 다르고 동작하는 로직은 똑같기때문)
// 아래와같이 ${spring.profiles.active} 는(placeholder) active 된 프로필명을 변수화 시켜 넣어주고 거기에 맞춰 property를 가져와 로직을 실행하게 되는 방식이다!
//로직은 같고 사용하는 값만 다를 때 이렇게 할 수 잇따.
@PropertySource("classpath:application-${spring.profiles.active}.properties") 
public class AppConfig {

	
	@Bean
	public Connection connection(ConnectionFactory connectionFactory) {
		return connectionFactory.getConnection();
	}
	
	@Bean
	public Dao dao(Connection connection) {
		return new Dao(connection);
	}
	
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
