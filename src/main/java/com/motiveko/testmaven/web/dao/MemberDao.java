package com.motiveko.testmaven.web.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.annotation.Transactional;

import com.motiveko.testmaven.web.entity.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberDao {
	// Aspect를 활용한 Dao클래스 리빌딩, 트랜잭션을 Aop로 빼서 처리했다(setAutoCommit, commit, rollback) 
	private JdbcTemplate jdbcTemplate; 

	@PostConstruct
	void init() {
		jdbcTemplate.update("create table member(id int auto_increment, username varchar(255) not null, password varchar(255) not null, primary key(id))");
		jdbcTemplate.update("insert into member(username, password) values('motiveko','1234')");
	}
	
	//dao에 직접 datasource를 DI해서 dao에서 직접 트랜잭션을 가져다가 관리하는게 아닌, jdbcTemplate을 DI해서 간접적으로 쓴다(추상화)
	public MemberDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	} 
	
	
	public void insert(String username, String password) {
		//datasource.getConnection() 은 같은 설정의 새로운 트랜잭션을 생성하는것이다. 하나의 트랜잭션으로 공유하려면 아래의 DataSourceUtils를 이용해서 connection을 가져와야한다.
//		Statement statement = dataSource.getConnection().createStatement(); // datasource를 통해서 커넥션을 가져오는 방식으로 리팩터링
//		Statement statement = DataSourceUtils.getConnection(dataSource).createStatement(); //
//		statement.executeUpdate("insert into member(username,password) values('motiveko','1234')");	
		
		jdbcTemplate.update("insert into member(username,password) values(?,?)",username, password);// ?순서대로 뒤에 변수 할당된다.
	}
	
	public List<Member> list() {
		
//		Statement statement = dataSource.getConnection().createStatement();
//		Statement statement = DataSourceUtils.getConnection(dataSource).createStatement();

//		ResultSet resultSet = statement.executeQuery("select id, username, password from member");
//		while (resultSet.next()) {
//			Member member = new Member(resultSet);
//			log.info(member.toString());
//		}
		
		//RowMapper는 method가 1개인 @FunctionalInterface 이므로, 아래와 같은 람다식으로 바꿀 수 있다!!! 신기방기
		
		
		
		
		
		List<Member> list = jdbcTemplate.query("select id, username, password from member",
//				new RowMapper<Member>() {
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				return new Member(rs);
//			}
//		}
		(resultSet,i)->new Member(resultSet)
		);
		list.forEach(x->log.info(">> Member : " + x.getUsername() + "/" + x.getPassword()));
		
		
		
		return list;//mvc로 리팩토링 과정에서 바꿈
		
	}
}

