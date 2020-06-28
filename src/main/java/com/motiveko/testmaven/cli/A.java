package com.motiveko.testmaven.cli;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A implements ApplicationContextAware {

	//특정 bean에서 ApplicationContext를 쓰고싶다면 implements ApplicationContextAware 해주고 set메서드 구현해주면 가능하다.
	
	private ApplicationContext applicationContext;
	
	public void init() {
		log.info(""+applicationContext);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	
}
