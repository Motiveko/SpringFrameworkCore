package com.motiveko.testmaven.cli.others;

import java.sql.Connection;
import java.sql.SQLException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class TransactionBean {
	
	private Connection connection;
	
	public TransactionBean(Connection connection) {
		this.connection = connection;
	}
	
	//@Pointcut은 @Aspect붙은 클래스 내에
	@Pointcut("execution(* com.motiveko.testmaven.cli.Dao2.insert())")
	public void transactionPointcut() {}
		
		
	// @..Advice("pointcutMethod")
	@Around("transactionPointcut()")
	// 일반적으로는 void가 아니라 결과값을 Object형태로 만들어 반환해주는 형태를 쓴다.
	public Object aroundLog(ProceedingJoinPoint pjp) throws SQLException {
		System.out.println(">>>>>>>>>>>>>>setAuotoCommit(False)");
		connection.setAutoCommit(false);
		try {
			Object proceed = pjp.proceed();
			System.out.println(">>>>>>>>>>>>>>Commit()");
			connection.commit();			
			return proceed;
		} catch (Throwable e) {
			System.out.println(">>>>>>>>>>>>>>Rollback()");
			connection.rollback();
		}
		System.out.println(">>>>>>>>>>>>>>after()");

		return null;
	}
	
}
