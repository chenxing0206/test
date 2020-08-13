package com.cx.test.proxy;

public class NormalPerson implements Person {

	@Override
	public String sing(String name) {
		System.out.println("Oh god,i can't sing this "+name+".");
		return "";
	}

	@Override
	public String dance(String name) {
		System.out.println("Oh god,i can't dance this "+name+".");
		return "";
	}

	@Override
	public void test() {
		System.out.println("NormalPerson test.");
		
	}

}
