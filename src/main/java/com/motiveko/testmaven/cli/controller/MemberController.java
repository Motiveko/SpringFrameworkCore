package com.motiveko.testmaven.cli.controller;

import java.sql.SQLException;

import com.motiveko.testmaven.cli.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class MemberController {
	//Service Layer 객체 호출 역할, error핸들링은 controller에서 한다.
	
	private MemberService memberService;
	
	public void insert(String username, String password) {
		
		try {
			memberService.insert(username, password);
			throw new SQLException("호호");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	public void print() {
		try {
			memberService.print();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}
}
