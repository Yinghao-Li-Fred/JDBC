package cn.ybzy.test;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import cn.ybzy.model.User;

public class BeanUtilsTest {

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		User user = new User();
		System.out.println(user);
		
		BeanUtils.setProperty(user, "userName", "Fred");
		
		System.out.println(BeanUtils.getProperty(user, "userName"));
	}
}
