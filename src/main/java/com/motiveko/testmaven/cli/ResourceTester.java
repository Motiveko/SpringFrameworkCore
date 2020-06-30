package com.motiveko.testmaven.cli;

import java.net.MalformedURLException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class ResourceTester {

	
	public static void main(String[] args) throws MalformedURLException {
		// Resource는 말그대로 Resource다. .xml, .properties 등 모든 파일들
		// Resource Interface를 구현한 다양한 class들을 이용해 각자의 방식으로 Resource를 가져올 수 있다.
		
		//ClassPathResource example
//		ClassPathResource resource = new ClassPathResource("Dao.xml");
//		byte[] bytes = resource.getInputStream().readAllBytes(); //Java9부터 가능..
//		String daoStr = new String(bytes);
//		System.out.println(daoStr);
		
		Resource resource = new UrlResource("file:E:\\MyWork\\Workspace\\testmaven\\src\\main\\resources\\Dao.xml");
		System.out.println(resource.exists());
//		byte[] bytes = resource.getInputStream().readAllBytes(); //Java9부터 가능..
//		String daoStr = new String(bytes);
//		System.out.println(daoStr);		
		
		
	}
}
