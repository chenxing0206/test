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
	 * 其实返回的类是实现了Person接口，并继承了Proxy的子类。并且这个子类中有可以捕获接口实现方法的方法。
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
