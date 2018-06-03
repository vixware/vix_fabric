package com.vix.hr.organization.entity;

import java.util.EnumMap;

/**
 * 人事事务管理类型
 * 
 */
public enum RegularType{
	// 利用构造函数传参
	REGULAR("regular"), UNUSUAL("unusual"),BORROW("borrow"), LEAVE("leave"),DIMISSION("dimission"), REFUSE("refuse"), RETIRE("retire"), REHIRE("rehire");
	// 定义私有变量
	private String nCode;
	// 构造函数，枚举类型只能为私有
	private RegularType(String _nCode) {
		this.nCode = _nCode;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.nCode);
	}
	
	/**
	 * 
	 * 演示EnumMap的使用，EnumMap跟HashMap的使用差不多，只不过key要是枚举类型
	 */
	public static EnumMap<RegularType, String> getRegularTypeMap() {
		// 定义EnumMap对象，EnumMap对象的构造函数需要参数传入,默认是key的类的类型
		EnumMap<RegularType, String> currEnumMap = new EnumMap<RegularType, String>(RegularType.class);

		currEnumMap.put(RegularType.REGULAR, "转正");
		currEnumMap.put(RegularType.UNUSUAL, "异动");
		currEnumMap.put(RegularType.BORROW, "借调");
		currEnumMap.put(RegularType.LEAVE, "请假");
		currEnumMap.put(RegularType.DIMISSION, "离职");
		currEnumMap.put(RegularType.REFUSE, "辞退");
		currEnumMap.put(RegularType.RETIRE, "离退休");
		currEnumMap.put(RegularType.REHIRE, "返聘");
		
		return currEnumMap;
	}
}