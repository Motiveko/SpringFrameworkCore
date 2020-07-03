package com.motiveko.testmaven.web.config.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.motiveko.testmaven.web.config.spring.AppConfig;
import com.motiveko.testmaven.web.config.spring.MvcConfig;


public class TestmavenInitializer
		implements WebApplicationInitializer{
	// WebApplicationInitializer :: api문서 참조
	// servlet 3.0기반으로 만들어진 interface, 기존의 web.xml로 설정하던 servlet context과 달리 progream으로 설정할 수 있는 servlet context

	
	@Override //tomcat 실행하면 자동으로 이거 찾아서 실행한다.
	public void onStartup(ServletContext container) throws ServletException {
			// 이 자체가 스프링 컨테이너로 돌아간다.
		
	      // Create the 'root' Spring application context
	      AnnotationConfigWebApplicationContext rootContext =new AnnotationConfigWebApplicationContext();
	      
	      System.out.println("안녕하세요");
	      rootContext.register(AppConfig.class);
	      rootContext.register(MvcConfig.class);
	      System.out.println("반갑습니다.");

	      // Manage the lifecycle of the root application context
	      container.addListener(new ContextLoaderListener(rootContext));

	      // Create the dispatcher servlet's Spring application context
	      AnnotationConfigWebApplicationContext dispatcherContext =
	        new AnnotationConfigWebApplicationContext();
	      
//	      dispatcherContext.register(DispatcherConfig.class); //이거는 주석처리

	      // Register and map the dispatcher servlet
	      ServletRegistration.Dynamic dispatcher =
	        container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
	      dispatcher.setLoadOnStartup(1);
	      dispatcher.addMapping("/"); //root url 이하로는 이 dispathcer가 동작, dispatcher를 여러개 만들어서 /a , /b ...로 주소 분기 시킬수 있지만 보통 root하나만 만든다.
	}

}
