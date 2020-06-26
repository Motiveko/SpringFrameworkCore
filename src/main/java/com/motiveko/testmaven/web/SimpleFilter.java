package com.motiveko.testmaven.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("Filter-Hello World!");
		
		// ServletRequest는 interface이므로 HttpServletRequest로 형변환 후 getSession()으로 세션을 취득한다.
		// 세션은 Http 헤더의 쿠키값을 이용해 세션을 관리하는데..
		HttpSession session = ((HttpServletRequest) request).getSession();
		
		// 맨 처음에 사용자가 없으면 username에 motiveko를 넣어주고 그 뒤에는 새로고침할때마다 session에 들어가있는 motiveko가 출력된다.
		// Http는 사용자 식별을 위해 헤더를 이용한는데 이를 확인하는것은
		// 브라우저-개발자도구-network에 request의 header-request header - coockie에 jsessionID를 통해 톰캣이 사용자 식별을 한다.
		// 우리가 setAttribute를 하면 response header에서 Set-Cookie로 jsessionID를 보내준다.
		// 그러면 클라이언트가 이를 저장하고 request에 담아서 보내면 톰캣이 사용자를 식별하는것이다.
		// session을 통해 Spring security의 로그인 처리를 진행할 예정이다.
		String username = (String) session.getAttribute("username");
		if(username==null) {
			log.info("new user");
			session.setAttribute("username", "motiveko");
		} else {
			log.info("user -> " + username);
		}
		
		chain.doFilter(request, response);
		PrintWriter writer = response.getWriter();
		
		writer.println("filter - hello world!");
	}

	
}
