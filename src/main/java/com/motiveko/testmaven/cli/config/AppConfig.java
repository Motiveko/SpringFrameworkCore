package com.motiveko.testmaven.cli.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.motiveko.testmaven.cli.controller.MemberController;
import com.motiveko.testmaven.cli.dao.MemberDao;
import com.motiveko.testmaven.cli.service.MemberService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:application-default.properties") 
@EnableAspectJAutoProxy
//이거를 AppConfig에 붙이고 Bean중에서 transaction이 필요한 곳에 가서 @Transactional 붙여주면 된다.
@EnableTransactionManagement//spring-tx, Config 내에 생성된 bean에 대해 @Transactional class/method 레벨  사용가능
public class AppConfig {

	

	
	/*
	 * @Bean
	 * 
	 * @Lazy public Dao dao(Connection connection) { return new Dao(connection); }
	 */
	
	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean () {
		return new LocalValidatorFactoryBean();
	}
	
	@Bean // connectionfactory를 datasource로 리팩토링
	public DataSource dataSource(
			@Value("${jdbc.driver-class}") String driverClass,
			@Value("${jdbc.url}") String url,
			@Value("${jdbc.username}") String username,
			@Value("${jdbc.password}") String password ) {
		
		//깃헙에서 복붙해오면 된다. driverclass는 추가함, propert부분은 지움
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(driverClass);
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);

		return new HikariDataSource(config); // 데이타소스 구현체;
	}
	
	@Bean
	public MemberService service(MemberDao dao) { return new MemberService(dao); }
	
	@Bean
	public MemberController controller(MemberService service) { return new MemberController(service); }
	
	@Bean
	public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}	
	
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		//dao에 직접 datasource를 DI해서 dao에서 직접 트랜잭션을 가져다가 관리하는게 아닌, jdbcTemplate을 DI해서 간접적으로 쓴다(추상화)
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public MemberDao memberDao( JdbcTemplate jdbcTemplate) {
		return new MemberDao(jdbcTemplate);
	}

}
