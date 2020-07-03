package com.motiveko.testmaven.web.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.*;

//@NoArgsConstructor //default constructor
//@ RequiredArgsConstructor //@NonNull 붙은 필드만 사용하는 constructor
@AllArgsConstructor // constructor
 // 겟, 셋, 이꼴해쉬, 투스트링 등 종합세트
@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Member{

	// 필드
	private int id;
	@NonNull private String username;
	@NonNull private String password;
	
	public Member(ResultSet resultSet) {
		try {			
			id = resultSet.getInt("id");
			username = resultSet.getString("username");
			password = resultSet.getString("password");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
