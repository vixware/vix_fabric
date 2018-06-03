/**
 * 
 */
package com.vix.common.id.test;

import com.vix.common.id.ICodeGenerator;
import com.vix.common.id.generator.CodeGenerator;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-20
 */
public class Test {

	public static void main(String[] args) {
		ICodeGenerator codeGenerator = new CodeGenerator();
		System.out.println(codeGenerator.createRandomCode());
		/*
		 * for (int i = 0; i < 100; i++) {
		 * System.out.println(codeGenerator.createCodeByRule("VixDrpCmn",
		 * codeGenerator.TIMEPATTERN, 1L, 9999L, 1, 15, false)); }
		 */
		for (String s : codeGenerator.createCodesByRule("Vix", "qq", ICodeGenerator.TIMEPATTERN, 1L, 99999L, 1, 15, true, 10)) {
			System.out.println(s);
		}
	}
}
