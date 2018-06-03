/**
 * 
 */
package com.vix.system.remindsCenter.base.basetask;

/**
 * @author zhanghaibing
 * 
 * @date 2014-5-22
 */
public abstract class VixAlertTask {
	/**
	 * 唯一标识
	 * 
	 * @return
	 */
	public abstract String getTaskMethodName();

	/**
	 * 任务
	 * 
	 * @throws Exception
	 */
	public void doTask() throws Exception {
	}
}
