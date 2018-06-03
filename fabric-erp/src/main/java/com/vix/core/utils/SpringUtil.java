package com.vix.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext; // Spring应用上下文环境

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/*	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}*/
	public static <T> T getBean(String name) throws BeansException {
		return (T)applicationContext.getBean(name);
	}
}
