package com.cx.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StarPersonProxy {
	private Object startPerson = null;
	public StarPersonProxy(Object pp){
		this.startPerson =pp;
	}
	
	/**
	 * ��ʵ���ص�����ʵ����Person�ӿڣ����̳���Proxy�����ࡣ��������������п��Բ���ӿ�ʵ�ַ����ķ�����
	 * @return
	 */
	public Object getProxy()
	{
		return (Object) Proxy.newProxyInstance(startPerson.getClass().getClassLoader(), startPerson.getClass().getInterfaces(), 
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println(startPerson.getClass().getName());
						if(method.getName().equals("sing")||method.getName().equals("dance") || method.getName().equals("test"))
						{
							System.out.println("Please give me money.");
							return method.invoke(startPerson, args);
						}
						return null;
					}
				});
	}
}
