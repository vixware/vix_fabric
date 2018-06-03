package com.vix.core.constant;

import java.util.LinkedHashSet;
import java.util.Set;

public final class SecurityDataLevel {

	//数据安全级别
	public static final Integer SEC_DATA_LEVEL_0 = 0;
	
	public static final Integer SEC_DATA_LEVEL_1 = 1;
	
	public static final Integer SEC_DATA_LEVEL_2 = 2;
	
	public static final Integer SEC_DATA_LEVEL_3 = 3;
	
	public static final Integer SEC_DATA_LEVEL_4 = 4;
	
	public static final Integer SEC_DATA_LEVEL_5 = 5;
	
	public static final Integer SEC_DATA_LEVEL_6 = 6;
	
	public static final Integer SEC_DATA_LEVEL_7 = 7;
	
	public static final Integer SEC_DATA_LEVEL_8 = 8;
	
	public static final Integer SEC_DATA_LEVEL_9 = 9;
	
	
	/** 业务数据密集 最低 */
	public static final Integer BIZ_SEC_DATA_LEVEL_MIN = SEC_DATA_LEVEL_9;
	/** 业务数据密集 最高 */
	public static final Integer BIZ_SEC_DATA_LEVEL_MAX = SEC_DATA_LEVEL_3;
	
	/**
	 * 具有设定密集权限的人的密集 
	 */
	private static final Set<Integer> OWNER_DATA_LEVEL_SET = new LinkedHashSet<Integer>(); 
	
	/**
	 * 业务数据密集
	 */
	private static final Set<Integer> BIZ_DATA_LEVEL_SET = new LinkedHashSet<Integer>(); 
	
	
	
	static {
		//OWNER_DATA_LEVEL_SET.add(SEC_DATA_LEVEL_0);
		OWNER_DATA_LEVEL_SET.add(SEC_DATA_LEVEL_1);
		OWNER_DATA_LEVEL_SET.add(SEC_DATA_LEVEL_2);
		
		
		BIZ_DATA_LEVEL_SET.add(SEC_DATA_LEVEL_3);
		BIZ_DATA_LEVEL_SET.add(SEC_DATA_LEVEL_4);
		BIZ_DATA_LEVEL_SET.add(SEC_DATA_LEVEL_5);
		BIZ_DATA_LEVEL_SET.add(SEC_DATA_LEVEL_6);
		BIZ_DATA_LEVEL_SET.add(SEC_DATA_LEVEL_7);
		BIZ_DATA_LEVEL_SET.add(SEC_DATA_LEVEL_8);
		BIZ_DATA_LEVEL_SET.add(SEC_DATA_LEVEL_9);
	}
}
