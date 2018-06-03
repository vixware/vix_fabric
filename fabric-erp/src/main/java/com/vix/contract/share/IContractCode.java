/**   
* @Title: IContractCode.java 
* @Package com.vix.contract.share 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-24 下午2:56:09  
*/
package com.vix.contract.share;

/**
 * @ClassName: IContractCode
 * @Description: 合同编码
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-24 下午2:56:09
 */
public interface IContractCode {
	/** 完全手工编码 */
	public static final String CTM_COMPLETELY_HAND_CODED = "CTM_COMPLETELY_HAND_CODED";
	/** 手工改动，重号时自动重取 */
	public static final String CTM_MANUAL_CHANGES_RE_NUMBER_AUTOMATICALLY_RE_TAKE = "CTM_MANUAL_CHANGES_RE_NUMBER_AUTOMATICALLY_RE_TAKE";
	/** 长度 */
	public static final String CTM_LENGTH = "CTM_LENGTH";
	/** 规则 */
	public static final String CTM_RULE = "CTM_RULE";
	/** 前缀1*/
	public static final String CTM_PREFIX1 = "CTM_PREFIX1";
	/** 前缀2*/
	public static final String CTM_PREFIX2 = "CTM_PREFIX2";
	/** 前缀3 */
	public static final String CTM_PREFIX3 = "CTM_PREFIX3";
	/** 流水依据 */
	public static final String CTM_WATER_BASED = "CTM_WATER_BASED";
	/** 起始值 */
	public static final String CTM_STARTING_VALUE = "CTM_STARTING_VALUE";
	/** 单据编号 */
	public static final String CTM_DOCUMENT_NUMBER = "CTM_DOCUMENT_NUMBER";
	/** 效果*/
	public static final String CTM_EFFECT = "CTM_EFFECT";
}
