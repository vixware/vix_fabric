/**
 * 
 */
package com.vix.common.initTenantParameter;

/**
 * TODO
 * 
 * 判断驻户初始化是否完成
 * 
 * @author zhanghaibing
 * 
 * @date 2014-5-23
 */
public interface InitTenantParameter {
	/**
	 * 
	 * @param tenantId
	 *            驻户ID
	 * @return 返回是否初始化完成
	 * @throws Exception
	 */
	public Boolean initTenantParameters(String tenantId) throws Exception;
}
