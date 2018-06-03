package com.process.vreport.service;

import org.apache.camel.ServiceStatus;

import com.process.vreport.model.FlowTask;

public interface FlowTaskService {

	/**
	 * 启用流程
	 * 
	 * @param flowTask
	 */
	public void startFlowTask(FlowTask flowTask);

	/**
	 * 停止流程
	 * 
	 * @param id
	 */
	public void stopFLowTask(String id);

	/**
	 * 获取流程状态
	 * 
	 * @param id
	 * @return
	 */
	public ServiceStatus getFlowTaskStatus(String id);

	/**
	 * 解析流程字符串
	 * 
	 * @param src
	 * @return
	 */
	public FlowTask parseFlowTask(String src);

	public FlowTask parseFlowTask(String id, String src);

}
