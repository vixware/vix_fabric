/**
 * 
 */
package com.vix.common.properties.test;

import com.vix.common.properties.PropertiesFactory;
import com.vix.common.properties.PropertiesFile;
import com.vix.common.properties.PropertiesHelper;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-20
 */
public class Test {

	public static void main(String[] args) {
		PropertiesHelper vix = PropertiesFactory.getPropertiesHelper(PropertiesFile.VIX);
		System.out.println(vix.getValue("VIX"));
		PropertiesHelper vix_org = PropertiesFactory.getPropertiesHelper(PropertiesFile.VIX_ORG);
		System.out.println(vix_org.getValue("VIX_ORG"));
	}
}
