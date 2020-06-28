package com.motiveko.testmaven.cli;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;


@Slf4j
public class Main {

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		log.info("Hello World");
		
		// Dao.xml 의 connectionFactory bean의 constructor arg에 
		// driverclassName 넣어주고 이거를 가지고 factory에서 Class.forName~해준다.
//		try {
//			Class.forName("org.h2.Driver");
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}		
		
//		스프링의 컨테이너를 사용하지 않고 Dao 가져오는방법
//		Dao dao = new Dao();
		
		// 스프링 컨테이너를 활용해 객체를 bean으로 선언해놓고 가져옴
		// context에는 설정 정보를 입력한다 
		// ApplicationContext context 는 close() 기능이 없어서 정리하질 못하므로 이를 상속하고있는 더 넓은 자식으로 선언한다.
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("dao.xml");
		
		Lifecycle lifecycle = context.getBean(Lifecycle.class); // 컨테이너 생성시 자동으로 생성해준다.
		log.info(""+ (lifecycle.isRunning()));// context가 동작중이므로(컨테이너가 돌아가는 중이므로) true
		
		Dao dao = context.getBean("dao",Dao.class);
		dao.run();

		ConnectionFactory factory = context.getBean(ConnectionFactory.class);
		log.info("result :: " + (factory.getConnection()!=null));
		
		// 이 Application Context에 있는 bean들도 이 때 destruction된다.
		context.close();
		log.info(""+ (lifecycle.isRunning())); // context가 close했으므로(컨테이너가 죽었으므로) false

	}
	
}

