package com.motiveko.testmaven.web.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@Configuration
@EnableWebSecurity//@Configuration 을 포함하고있다
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/**").authenticated()   //모든url에 대해서 검사한다?
			.and()
			.formLogin(); 						// /login이라는 url로 redirect한다. 여기는 spring security에서 기본제공한다.
		
	}

	@Override
	public UserDetailsService userDetailsService() {
		// spring security는 디테일한것은 api를 참고하고 더 공부해라..
		// user와 password라는 이름으로 인증할 수 있게 간단하게 만들었따.
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build();
		return new InMemoryUserDetailsManager(user);
	}

}
