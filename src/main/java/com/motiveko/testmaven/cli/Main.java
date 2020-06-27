package com.motiveko.testmaven.cli;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

import org.slf4j.Logger;

public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		logger.info("Hello World");
		
		// 드라이버 로딩은 일단 메인에서..
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		
//		스프링의 컨테이너를 사용하지 않고 Dao 가져오는방법
//		Dao dao = new Dao();
		
		// 스프링 컨테이너를 활용해 객체를 bean으로 선언해놓고 가져옴
		// context에는 설정 정보를 입력한다 
		ApplicationContext context = new ClassPathXmlApplicationContext("dao.xml");
		Dao2 dao2 = context.getBean("dao2",Dao2.class);
		dao2.run();

		
	}
	
}

