package com.motiveko.testmaven.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.ComponentScan.Filter;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;


@Slf4j
public class Main {

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		log.info("Hello World");			
														//()안의 클래스가 있는 패키지의 하위 디렉토리에 있는 전체클래스중에서 @Configuration 붙은 class를 bean으로 생성한다.
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//-Dspring.profiles.active=dev
		
//		context.getEnvironment().setActiveProfiles("dev");	//순서 중요하다. setprofie먼저 해주고 appConfig를 register해줘야한다.
//		context.getEnvironment().setDefaultProfiles("dev");		
		
		context.register(AppConfig.class);
		
		
		context.refresh();
		Dao dao = context.getBean(Dao.class);
		dao.run();
		log.info("dao ::::" + dao);
	
		context.close();
	}
	
}

