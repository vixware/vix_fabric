/**
 * 
 */
package com.vix.common.properties;

import java.io.InputStream;

/**
 * Properties文件静态工厂
 * 
 * @author zhanghaibing
 * 
 * @date 2013-6-20
 */
@SuppressWarnings("unchecked")
public class PropertiesFactory {

	/**
	 * 属性文件实例容器
	 */
	private static Dto container = new BaseDto();

	static {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) {
			classLoader = PropertiesFactory.class.getClassLoader();
		}
		// 加载属性文件vix.properties
		try {
			InputStream is = classLoader.getResourceAsStream("vix.properties");
			PropertiesHelper ph = new PropertiesHelper(is);
			container.put(PropertiesFile.VIX, ph);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 加载属性文件application.properties
		try {
			InputStream is = classLoader.getResourceAsStream("application.properties");
			PropertiesHelper ph = new PropertiesHelper(is);
			container.put(PropertiesFile.VIX_APPLICATION, ph);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 加载属性文件vix_org.properties
		try {
			InputStream is = classLoader.getResourceAsStream("vix_org.properties");
			PropertiesHelper ph = new PropertiesHelper(is);
			container.put(PropertiesFile.VIX_ORG, ph);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 获取属性文件实例
	 * 
	 * @param pFile
	 *            文件类型
	 * @return 返回属性文件实例
	 */
	public static PropertiesHelper getPropertiesHelper(String pFile) {
		PropertiesHelper ph = (PropertiesHelper) container.get(pFile);
		return ph;
	}

}
