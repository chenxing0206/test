package com.cx.test.proxy;

public class StarPerson implements Person {

	@Override
	public String sing(String name) {
		System.out.println("xxx sing "+name+" song.");
		return "Thank you for listening this song.";
	}

	@Override
	public String dance(String name) {
		System.out.println("xxx dance "+name+".");
		return "Thank you for looking this dance.";
	}

	@Override
	public void test() {
		System.out.println("StarPerson test.");
		
	}

}
