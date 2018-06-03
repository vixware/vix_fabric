package com.vix.core.utils;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;

/**对Set某一属性升序排序的方法
 * 
 * 传入的参数类型应该为Long或String
 * 
 * 用法示例：
 * 
 *  Set<PromotionRule> ruleSet = medicine.getPromotionRuleSet();
 *				
 *	List<PromotionRule> ruleList = null;
 *	if(null != ruleSet){
 *		ruleList = VixShopUtils.convertSetToList(PromotionRule.class, ruleSet);
 *		//根据id排序
 *      SortSet scm = new SortSet("id");
 *		
 *		Collections.sort(ruleList, scm);
 *	}
 *  //此时ruleList已经是排好序的集合
 *  
 * @author Morion
 *
 */
public class SortSet implements Comparator<Object>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String propertyName;
	private String orderBy = "asc";
	public SortSet(String propertyName) {
		this.propertyName = propertyName;
	}
	public SortSet(String propertyName,String orderBy) {
		if(null == orderBy || "".equals(orderBy)){
			orderBy = "asc";
		}
		this.propertyName = propertyName;
		this.orderBy = orderBy;
	}
	@Override
	public int compare(Object objOne, Object objTwo) {
		try {
			if (null != objOne && null != objTwo && null != propertyName && !"".equals(propertyName)) {
				
				Object dataOne = PropertyUtils.getSimpleProperty(objOne, propertyName);
				Object dataTwo = PropertyUtils.getSimpleProperty(objTwo, propertyName);
				
				if (null != dataOne && null != dataTwo) {
					if(dataOne instanceof Long && dataTwo instanceof Long){
						return new Long(dataOne.toString()).compareTo(new Long(dataTwo.toString()));
					}else if(dataOne instanceof Integer && dataTwo instanceof Integer){
						return new Integer(dataOne.toString()).compareTo(new Integer(dataTwo.toString()));
					}else if(dataOne instanceof Double && dataTwo instanceof Double){
						return new Double(dataOne.toString()).compareTo(new Double(dataTwo.toString()));
					}else{
						return dataOne.toString().compareTo(dataTwo.toString());
					}
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
}
