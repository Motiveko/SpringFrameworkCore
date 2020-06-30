package com.motiveko.testmaven.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class ResourceTester {

	public static String helper(InputStream inputStream) throws IOException {
		// https://www.tutorialspoint.com/how-to-convert-inputstream-object-to-a-string-in-java
		// InputStream -> String
		InputStreamReader isReader = new InputStreamReader(inputStream);
		BufferedReader reader = new BufferedReader(isReader);
		StringBuffer sb = new StringBuffer();

		String str;
		while ((str = reader.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}

		return sb.toString();
	}

	public static void main(String[] args) throws MalformedURLException {
		// Resource는 말그대로 Resource다. .xml, .properties 등 모든 파일들
		// Resource Interface를 구현한 다양한 class들을 이용해 각자의 방식으로 Resource를 가져올 수 있다.
		
		//ClassPathResource example
//		ClassPathResource resource = new ClassPathResource("Dao.xml");
//		byte[] bytes = resource.getInputStream().readAllBytes(); //Java9부터 가능..
//		String daoStr = new String(bytes);
//		System.out.println(daoStr);
		
//		Resource resource = new UrlResource("file:E:\\MyWork\\Workspace\\testmaven\\src\\main\\resources\\Dao.xml");
//		System.out.println(resource.exists());
//		byte[] bytes = resource.getInputStream().readAllBytes(); //Java9부터 가능..
//		String daoStr = new String(bytes);
//		System.out.println(daoStr);		
		
		// ResourceLoader를 이용한 Resource 로딩?
		// Interface이기때문에 Api doc을 참조해 적절한 구현체를 이용한다.
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();
		Resource resource = ctx.getResource("Dao.xml");
		
		try {
			System.out.println(helper(resource.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
