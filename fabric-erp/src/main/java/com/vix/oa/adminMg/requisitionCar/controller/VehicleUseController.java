/*
 * Copyright (C) 2013 VixSoft Inc.
 *
 * Licensed under the VixSoft License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at license.txt
 * 
 * Purpose:  
 * Author:   
 * Date:     2013.07.03
 * Version:  1.0
 *
 */

/**   
 * @Title: ContractController.java 
 * @Package com.vix.contract.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:56:42  
 */
package com.vix.oa.adminMg.requisitionCar.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.requisitionCar.domain.VehicleUseDomain;
import com.vix.oa.adminMg.requisitionCar.entity.CarUse;

/**
 * 
 * @author Thinkpad
 *  车辆使用申请管理
 */
@Controller("vehicleUseController")
@Scope("prototype")
public class VehicleUseController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("vehicleUseController");

	@Autowired
	private VehicleUseDomain vehicleUseDomain;
	/**
	 * doSaveExecute实现获取车辆使用列表的业务逻辑处理
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doCarUseSingleList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = vehicleUseDomain.findPagerByVehicleHqlCarUse(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询车辆使用信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询车辆使用信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/**
	 * doSaveExecute实现查询车辆使用的业务逻辑处理
	 */
	public List<CarUse> doListCarUseIndex() {
		List<CarUse> carUse = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			carUse = vehicleUseDomain.findCarUseIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询车辆使用成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询车辆使用失败！" + "失败原因:" + ex.getMessage());
		}
		return carUse;
	}
	
	/**
	 * doSaveExecute实现保存车辆使用业务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CarUse doListCarUseById(String id) throws Exception {
		CarUse carUse = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			carUse = vehicleUseDomain.findCarUseById(id);
			
			
			// 3.执行查询操作 contractDomain.findEntityById(id);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询车辆使用信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询车辆使用信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return carUse;
	}
	
	
	/**
	 * doSaveExecute实现保存车辆使用设置的业务逻辑处理
	 * 
	 * @param contractClause
	 * @return
	 */
	public CarUse doSaveCarUse(CarUse carUse) {
		CarUse carUse1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			carUse.setUpdateTime(new Date());
			// 3. 执行保存操作
			carUse1 = vehicleUseDomain.merge(carUse);			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存车辆使用：" + carUse + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存车辆使用：" + carUse + "失败！失败原因：" + ex.getMessage());
		}
		return carUse1;
	}	
	
	/**删除*/
	public CarUse findEntityById(String id) throws Exception {
		return vehicleUseDomain.findCarUseById(id);
	}
	
	
	/**
	 * doSaveExecute实现删除车辆使用设置的业务逻辑处理
	 * 
	 * @param 
	 */
	public void doDeleteByCarUse(CarUse carUse) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			vehicleUseDomain.deleteByCarUse(carUse);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除车辆使用设置成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除车辆使用设置信息失败！失败原因：" + ex.getMessage());
		}
	}
	

	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = vehicleUseDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = vehicleUseDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}
		
	/**
	 * 输入信息到日志文件中,可以考虑是用Helper类提供服务.
	 * 
	 * @param message
	 */
	public void executeLogger(boolean isShow, String... message) {
		if (isShow) {
			logger.info(message);
		}
	}

	/**
	 * beforeEventTrigger 用于触发在执行业务逻辑前的事件产生, 将产生一个业务对象执行动作的before事件.
	 * 使用Esper引擎处理,事件体执行过程为异步.afterEventTrigger采用相同的处理方式.
	 * 
	 * @param eventName
	 *            根据业务对象来定义
	 */
	private void beforeEventTrigger(String eventName) {
		// to do something
	}

	/**
	 * afterEventTrigger 用于触发在执行业务逻辑后的事件产生, 将产生一个业务对象执行动作的after事件.
	 * 
	 * @param eventName
	 *            根据业务对象来定义
	 */
	private void afterEventTrigger(String eventName) {
		// to do something
	}


}
