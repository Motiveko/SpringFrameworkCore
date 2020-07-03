package com.motiveko.testmaven.web.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.motiveko.testmaven.web.dao.MemberDao;
import com.motiveko.testmaven.web.entity.Member;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberService {

	// Service Layer의 존재이유
	// Dao가 여러개일 때, Dao마다 @Transactional 을 다 걸어주면 transaction이 꼬이는 경우가 발생해서, Service에서 트랜잭션을 전부 관리하고 dao에 뿌려주는 식으로 코드를 짜게 된다.
	
	private MemberDao memberDao;
	
	@Transactional
	public void insert(String username, String password){	
		//JDBC template은 exception을 날리지 않으므로 throws필요없다.
		memberDao.insert(username,password);					
	}
	public List<Member> list() {
		return memberDao.list();
	}
}
