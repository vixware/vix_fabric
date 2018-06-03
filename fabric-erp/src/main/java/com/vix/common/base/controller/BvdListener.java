package com.vix.common.base.controller;

import java.lang.reflect.Field;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 
 * @类全名 com.vix.common.base.controller.BvdListener
 * 
 * @author zhanghaibing
 *
 * @date 2017年11月2日
 */

public class BvdListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 获取存放dll文件的绝对路径（假设将dll文件放在系统根目录下的WEB-INF文件夹中）
		String path = event.getServletContext().getRealPath("WEB-INF/dll");
		// 将此目录添加到系统环境变量中
		addDirToPath(path);
		// 加载相应的dll/so文件，注意要将'\'替换为'/'
		System.load(path.replaceAll("\\\\", "/") + "/BVIP_SDK.dll");

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

	private void addDirToPath(String s) {
		try {
			// 获取系统path变量对象
			Field field = ClassLoader.class.getDeclaredField("sys_paths");
			// 设置此变量对象可访问
			field.setAccessible(true);
			// 获取此变量对象的值
			String[] path = (String[]) field.get(null);
			// 创建字符串数组，在原来的数组长度上增加一个，用于存放增加的目录
			String[] tem = new String[path.length + 1];
			// 将原来的path变量复制到tem中
			System.arraycopy(path, 0, tem, 0, path.length);
			// 将增加的目录存入新的变量数组中
			tem[path.length] = s;
			// 将增加目录后的数组赋给path变量对象
			field.set(null, tem);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}