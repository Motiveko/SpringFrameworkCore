package com.motiveko.testmaven.web.config.servlet;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer 
	extends AbstractSecurityWebApplicationInitializer{
	//Spring security
	//extends AbstractSecurityWebApplicationInitializer 하면
	//servlet에서 servlet filter 기능을 이용하여 spring security를 사용할 숭 있다.
	
	// springSecurityFilterChain bean을 등록해줘야한다.
}
