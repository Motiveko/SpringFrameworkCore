package com.motiveko.testmaven.cli;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;


import lombok.extern.slf4j.Slf4j;

// @Autowired : 생성자, setter, field에 넣어줄 수 있다. 자동으로 컨테이너에 있는 객체를 주입해준다.
// @Inject와 매우 비슷하다.
//@Component
@Slf4j
public class A {

	private B b;	
	// Aware~Interface 구현 안해도 이렇게 해서 applicationcontext넣어줄수잇따.

	public A (B b) {
		this.b = b;
	}

	
	
	void init() { 
		log.info("b ::: "+ b);
	}

	void destroy() { }
}
