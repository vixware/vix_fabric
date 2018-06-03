/**   
* @Title: IGlobalParameters.java 
* @Package com.vix.contract.share 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-24 下午2:41:06  
*/
package com.vix.contract.share;

/**
 * @ClassName: IGlobalParameters
 * @Description: 全局参数
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-24 下午2:41:06
 */
public interface IGlobalParameters {
	/** 合同金额大于 */
	public static final String CTM_CONTRACT_AMOUNT_GREATER_THAN = "CTM_CONTRACT_AMOUNT_GREATER_THAN";
	/** 是否启用财务模块 */
	public static final String CTM_FINANCE_MODULE_ENABLED = "CTM_FINANCE_MODULE_ENABLED";
	/** 是否允许导出 */
	public static final String CTM_WHETHER_ALLOW_EXPORT = "CTM_WHETHER_ALLOW_EXPORT";
	/** 是否启用电子印章 */
	public static final String CTM_WHETHER_ENABLE_ELECTRONIC_SEAL = "CTM_WHETHER_ENABLE_ELECTRONIC_SEAL";
	/** 附件类型 */
	public static final String CTM_ACCESSORY_TYPE = "TM_ACCESSORY_TYPE";
	/** 附件大小 */
	public static final String CTM_ATTACHMENT_SIZE = "CTM_ATTACHMENT_SIZE";
	/** 设置合同附件目录*/
	public static final String CTM_SET_CONTRACT_ACCESSORIES_CATALOGUE = "CTM_SET_CONTRACT_ACCESSORIES_CATALOGUE";
}
