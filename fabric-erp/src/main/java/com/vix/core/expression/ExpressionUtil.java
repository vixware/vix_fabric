package com.vix.core.expression;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.vix.core.utils.StrUtils;

import neu.sxc.expression.Expression;
import neu.sxc.expression.syntax.SyntaxException;
import neu.sxc.expression.tokens.Valuable;

/**
 * @ClassName: ExpressionUtil
 * @Description: 表达式计算工具
 * @author wangmingchen
 * @date 2016年2月16日 上午9:05:02
 */
public class ExpressionUtil {

	/**
	 * @Title: isTrue
	 * @Description: 布尔表达式，必须以 ; 结尾
	 *    比如：(20<VAL)&&(VAL<=40);
	 * @param @param formular
	 * @param @param varList 表达式中的变量
	 * @param @param valueList 变量值
	 */
	public static Boolean isTrue(String formular,List<String> varList,List<Object> valueList){
		try {
			Expression exp = new Expression(formular);
			if(varList!=null){
				for(int i =0 ; i<varList.size();i++){
					String var = varList.get(i);
					Object value = valueList.get(i);
					exp.setVariableValue(var, value);
				}
			}
			
			Valuable result = exp.evaluate();
			return result.getBooleanValue();
		} catch (SyntaxException e) {
			e.printStackTrace();
			throw new SyntaxException("公式解析错误："+e.getMessage());
		}
	}
	
	/**
	 * @Title: isTrue
	 * @Description: 最多一个变量的表达式
	 */
	public static Boolean isTrue(String formular,String var,Object value){
		try {
			Expression exp = new Expression(formular);
			if(StrUtils.isNotEmpty(var)){
				exp.setVariableValue(var, value);
			}
			Valuable result = exp.evaluate();
			return result.getBooleanValue();
		} catch (SyntaxException e) {
			e.printStackTrace();
			throw new SyntaxException("公式解析错误："+e.getMessage());
		}
	}
	
	/**
	 * @Title: main
	 * @Description: TEST
	 */
	public static void main(String[] args) {
		System.out.println(NumberUtils.isNumber(null));
		/*String fromular = "JBGZ+BZ_KQ1*21";
		
		Expression exp = new Expression(fromular);
		
		//System.out.println(result.getNumberValue());
		exp.setVariableValue("JBGZ", 1D);
		exp.setVariableValue("BZ_KQ1", 1D);
		Valuable result = exp.evaluate();
		BigDecimal rr = result.getNumberValue();
		System.out.println(rr);*/
		
		List<String> varList = new ArrayList<String>();
		List<Object> valueList = new ArrayList<Object>();
		varList.add("VAL");
		valueList.add(40.1);
		String fromular = "VAL>10;";
		fromular = "(20<VAL)&&(VAL<=40);";
		System.out.println(isTrue(fromular, varList, valueList));
		
	}

}
