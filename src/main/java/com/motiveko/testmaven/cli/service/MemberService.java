package com.motiveko.testmaven.cli.service;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

import com.motiveko.testmaven.cli.dao.MemberDao;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberService {

	// Service Layer의 존재이유
	// Dao가 여러개일 때, Dao마다 @Transactional 을 다 걸어주면 transaction이 꼬이는 경우가 발생해서, Service에서 트랜잭션을 전부 관리하고 dao에 뿌려주는 식으로 코드를 짜게 된다.
	
	private MemberDao memberDao;
	
	@Transactional
	public void insert(String username, String password) throws SQLException {	
		memberDao.insert(username,password);					
	}
	public void print() throws SQLException {
		memberDao.print();
	}
}
