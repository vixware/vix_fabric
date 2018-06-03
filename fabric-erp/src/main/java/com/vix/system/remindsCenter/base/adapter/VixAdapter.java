/**
 * 
 */
package com.vix.system.remindsCenter.base.adapter;

/**
 * @author zhanghaibing
 * 
 * @date 2014-5-22
 */
public interface VixAdapter {
	/**
	 * 需要执行的任务
	 * 
	 * @param classUniqueId
	 *            需要执行任务的类的唯一标识
	 * @throws Exception
	 */
	public void toDoTask(String classUniqueId) throws Exception;
}
