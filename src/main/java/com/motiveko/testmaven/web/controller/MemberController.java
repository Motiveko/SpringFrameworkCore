package com.motiveko.testmaven.web.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.motiveko.testmaven.web.entity.Member;
import com.motiveko.testmaven.web.model.MemberDto;
import com.motiveko.testmaven.web.service.MemberService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@RequestMapping("/index")
	public ModelAndView print(){		
		// 원래 db에 접근하는 것들은 throws SQLException이 필요하나 JdbcTemplate는 exception을 안던진다.
		List<Member> list = memberService.list();	
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("members",list);
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++");
		System.out.println(list.get(0).getUsername());
		System.out.println(list.get(0).getPassword());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++");
		
		// MvcConfig에 thymeLeaf의 TemplateResolver(viewResolver)가 매핑해준당.
		return mav; 
	}
	
	@RequestMapping("/create")
	public String create(MemberDto dto) {
		// RequestParam 말고 Dto 객체로 받을 수 있다.
		// Dto의 필드명을 form-input tag의 neme과 같게하면 자동으로 매핑!

	
//		ModelAndView mav = new ModelAndView("create");
		memberService.insert(dto.getUsername(),dto.getPassword());
		
		
		return "redirect:index";
	}
	
	
		
}
