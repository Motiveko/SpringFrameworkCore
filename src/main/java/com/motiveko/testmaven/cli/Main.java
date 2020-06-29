package com.motiveko.testmaven.cli;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.annotation.ComponentScan.Filter;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;


@Slf4j
public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		log.info("Hello World");			
														//()안의 클래스가 있는 패키지의 하위 디렉토리에 있는 전체클래스중에서 @Configuration 붙은 class를 bean으로 생성한다.
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppDefaultConfig.class,AppDevConfig.class);
		context.getEnvironment().setActiveProfiles("dev");		
		context.refresh();
		Dao dao = context.getBean(Dao.class);
		dao.run();
		log.info("dao ::::" + dao);
	
		context.close();
		
		
	}
	
}

