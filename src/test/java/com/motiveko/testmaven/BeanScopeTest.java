package com.motiveko.testmaven;

import java.util.Objects;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class BeanScopeTest {
	
	
	
	@Test // main없이도 실행할수있는거같은데 이클립스에서 우예하는지 잘모르겠다.
	public void testIdentity() {
		//동일성(identity) : 객체 주소가 같다. - (obj1==obj2==obj3) ==로비교
		//동등성(equals) : 객체의 값이 같다. - 다른 주소를 가지지만 같은 값을 가지는 객체: obj1.equals(obj2)		
		A a1 = new A();
		A a2 = a1; 		
		// Assert 객체는 테스트를 위한 객체로 메소드가 실패하면 assertion애러낸다.
		// assertTrue() 는 괄호 안의 내용이 true면 ture, false면 실패
		Assert.assertTrue(a1==a2);
	}
	
	@Test
	public void testEquals() {
		//Equals는 Override하지 않으면 Identity검사하는것이 기본으로 구현되어있다.
		A a1 = new A(10,"Hello World");
		A a2 = new A(10,"Hello World");
		
		A a3 = new A(5,"Hello");
		
		Assert.assertTrue(a1.equals(a2));
		Assert.assertFalse(a1.equals(a3));
		
	}
	public static void main(String[] args) {
		BeanScopeTest bTest = new BeanScopeTest();
		bTest.testIdentity();
		bTest.testEquals();
	}
}

// 롬복으로 전부 가능하다..
class A{

	private int a1;
	private String a2;
	
	public A() {}
	public A(int a1, String a2) {
		this.a1 = a1;
		this.a2 = a2;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(this==obj) return true; // 동일하다면 true
		if(!(obj instanceof A)) return false; // 객체가 같은타입인가
		A a = (A) obj;
		return a1 == a.a1 && Objects.equals(a2, a.a2);		
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
}
class B{
	
}