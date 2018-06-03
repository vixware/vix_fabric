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
import com.vix.oa.adminMg.requisitionCar.domain.VehicleRequestDomain;
import com.vix.oa.adminMg.requisitionCar.entity.CarMaintenance;
import com.vix.oa.adminMg.requisitionCar.entity.Tpk;
import com.vix.oa.adminMg.requisitionCar.entity.VehicleRequest;
import com.vix.oa.adminMg.typeSettings.entity.DisplacementType;
import com.vix.oa.adminMg.typeSettings.entity.EngineType;
import com.vix.oa.adminMg.typeSettings.entity.MaintenanceType;
import com.vix.oa.adminMg.typeSettings.entity.TransmissionType;
import com.vix.oa.adminMg.typeSettings.entity.VehicleColor;
import com.vix.oa.adminMg.typeSettings.entity.VehicleType;

/**
 * 
 * @ClassName: VehicleRequestController
 * @Description: 车辆管理 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-6-3 下午3:33:26
 */
@Controller("vehicleRequestController")
@Scope("prototype")
public class VehicleRequestController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("vehicleRequestController");

	@Autowired
	private VehicleRequestDomain vehicleRequestDomain;

	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = vehicleRequestDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	/** 获取车辆维修列表数据 */
	public Pager goCarMaintenance(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = vehicleRequestDomain.findPagerByHqlConditions1(params, pager);
		return p;
	}
	
	/** 获取列表数据 */
	public Pager goTpkList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = vehicleRequestDomain.findPagerByHqlConditions2(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = vehicleRequestDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}
	
	/** 获取车辆维修列表数据 */
	public Pager goSearchList1(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = vehicleRequestDomain.findPagerByOrHqlConditions1(params, pager);
		return p;
	}
	/** 获取车辆油耗列表数据 */
	public Pager goSearchList2(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = vehicleRequestDomain.findPagerByOrHqlConditions2(params, pager);
		return p;
	}
	
	
	
	/**
	 * doSaveExecute实现查询车辆维修 的业务逻辑处理
	 */
	public List<CarMaintenance> doListSalesOrderIndex() {
		List<CarMaintenance> carMaintenance = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			carMaintenance = vehicleRequestDomain.findSalesOrderIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询车辆维修成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询车辆维修失败！" + "失败原因:" + ex.getMessage());
		}
		return carMaintenance;
	}
	
	/**
	 * doSaveExecute实现查询车辆油耗管理的业务逻辑处理
	 */
	public List<Tpk> doLisTpkIndex() {
		List<Tpk> tpk = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			tpk = vehicleRequestDomain.findTpkIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询车辆油耗管理成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询车辆油耗管理 失败！" + "失败原因:" + ex.getMessage());
		}
		return tpk;
	}

	/**
	 * 
	 * 
	 * @param salesPlan
	 * @return
	 */
	public VehicleRequest doSaveVehicleRequest(VehicleRequest vehicleRequest) {
		VehicleRequest vehicleRequestTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSavePlan(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			vehicleRequestTemp = vehicleRequestDomain.merge(vehicleRequest);
			// 4. 触发事件 afterEventSavePlan(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + vehicleRequest + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + vehicleRequest + "失败！失败原因：" + ex.getMessage());
		}
		return vehicleRequestTemp;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public VehicleRequest doListVehicleRequestById(String id) {
		VehicleRequest vehicleRequestTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSavePlan(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			vehicleRequestTemp = vehicleRequestDomain.findVehicleRequestById(id);
			// 4. 触发事件 afterEventSavePlan(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return vehicleRequestTemp;
	}

	/**
	 * 
	 * 
	 * @param supplier
	 */
	public void doDeleteByVehicleRequest(VehicleRequest vehicleRequest) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSavePlan(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			vehicleRequestDomain.deleteByVehicleRequest(vehicleRequest);
			// 4. 触发事件 afterEventSavePlan(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
	}
	
	/**
	 * doSaveExecute实现获取车辆类型列表的业务逻辑处理
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doSubSingleList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = vehicleRequestDomain.findPagerByVehicleHqlConditions(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询车辆类型信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询车辆类型信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/**
	 * doSaveExecute实现保存车辆类型业务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public VehicleType doListEntityById(String id) throws Exception {
		VehicleType vehicleType = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			vehicleType = vehicleRequestDomain.findEntityById(id);
			
			
			// 3.执行查询操作 contractDomain.findEntityById(id);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询车辆类型信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询车辆类型信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return vehicleType;
	}
	
	
	/**
	 * doSaveExecute实现保存车辆类型设置的业务逻辑处理
	 * 
	 * @param contractClause
	 * @return
	 */
	public VehicleType doSaveSalesOrder(VehicleType vehicleType) {
		VehicleType vehicleType1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			vehicleType.setUpdateTime(new Date());
			// 3. 执行保存操作
			vehicleType1 = vehicleRequestDomain.merge(vehicleType);			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存车辆类型：" + vehicleType + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存车辆类型：" + vehicleType + "失败！失败原因：" + ex.getMessage());
		}
		return vehicleType1;
	}	
	
	
	
	/**
	 * doSaveExecute实现删除车辆类型设置的业务逻辑处理
	 * 
	 * @param 
	 */
	public void doDeleteByEntity(VehicleType vehicleType) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3. 执行删除操作
			vehicleRequestDomain.deleteByEntity(vehicleType);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除车辆类型设置成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除车辆类型设置信息失败！失败原因：" + ex.getMessage());
		}
	}
	
	/////////
	/**
	 * doSaveExecute实现获取车辆颜色列表的业务逻辑处理
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doSubVehicleColorList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = vehicleRequestDomain.findPagerByVehicleColorConditions(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询车辆颜色信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询车辆颜色信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/**
	 * doSaveExecute实现保存车辆颜色业务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public VehicleColor doListVehicleColorById(String id) throws Exception {
		VehicleColor vehicleColor = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			vehicleColor = vehicleRequestDomain.findVehicleColorById(id);
			
			
			// 3.执行查询操作 contractDomain.findEntityById(id);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询车辆颜色信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询车辆颜色信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return vehicleColor;
	}
	
	/**
	 * doSaveExecute实现保存车辆类型设置的业务逻辑处理
	 * 
	 * @param contractClause
	 * @return
	 */
	public VehicleColor doSaveVehicleColor(VehicleColor vehicleColor) {
		VehicleColor vehicleColor1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			vehicleColor.setUpdateTime(new Date());
			// 3. 执行保存操作
			vehicleColor1 = vehicleRequestDomain.merge(vehicleColor);			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存车辆颜色：" + vehicleColor + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存车辆颜色：" + vehicleColor + "失败！失败原因：" + ex.getMessage());
		}
		return vehicleColor1;
	}	
	
	/**
	 * doSaveExecute实现删除车辆颜色设置的业务逻辑处理
	 * 
	 * @param 
	 */
	public void doDeleteByVehicleColor(VehicleColor vehicleColor) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			vehicleRequestDomain.deleteByEntity(vehicleColor);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除车辆颜色设置成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除车辆颜色设置信息失败！失败原因：" + ex.getMessage());
		}
	}
	
	/////////////
	/**
	 * doSaveExecute实现获取变速器类型列表的业务逻辑处理
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doSubTransmissionTypeList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = vehicleRequestDomain.findPagerByTransmissionType(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询变速器类型信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询变速器类型信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/**
	 * doSaveExecute实现保存车辆颜色业务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public TransmissionType doListTransmissionTypeById(String id) throws Exception {
		TransmissionType transmissionType = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			transmissionType = vehicleRequestDomain.findTransmissionTypeById(id);
			
			
			// 3.执行查询操作 contractDomain.findEntityById(id);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询变速器类型信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询变速器类型信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return transmissionType;
	}
	
	/**
	 * doSaveExecute实现保存变速器类型设置的业务逻辑处理
	 * 
	 * @param contractClause
	 * @return
	 */
	public TransmissionType doSaveTransmissionType(TransmissionType transmissionType) {
		TransmissionType transmissionType1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			transmissionType.setUpdateTime(new Date());
			// 3. 执行保存操作
			transmissionType1 = vehicleRequestDomain.merge(transmissionType);			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存变速器类型：" + transmissionType + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存变速器类型：" + transmissionType + "失败！失败原因：" + ex.getMessage());
		}
		return transmissionType1;
	}	
	
	/**
	 * doSaveExecute实现删除车辆变速器设置的业务逻辑处理
	 * 
	 * @param 
	 */
	public void doDeleteByTransmissionType(TransmissionType transmissionType) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			vehicleRequestDomain.deleteByTransmissionType(transmissionType);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除变速器类型设置成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除变速器类型设置信息失败！失败原因：" + ex.getMessage());
		}
	}
	
		
	/////////////
	/**
	* doSaveExecute实现获取引擎类型列表的业务逻辑处理
	* 
	* @param params
	* @param pager
	* @return
	*/
	public Pager doSubEngineTypeList(Map<String, Object> params, Pager pager) {
	Pager p = null;
	try {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();
		
		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3.执行查询操作
		p = vehicleRequestDomain.findPagerByEngineType(params, pager);
		
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "查询引擎类型信息成功！");
	} catch (Exception ex) {
		executeLogger(true, "查询引擎类型信息失败！" + "失败原因:" + ex.getMessage());
	}
	return p;
	}
	
	/**
	* doSaveExecute实现保存引擎业务逻辑处理
	* 
	* @param id
	* @return
	* @throws Exception
	*/
	public EngineType doListEngineTypeById(String id) throws Exception {
		EngineType engineType = null;
	try {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();
		
		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		
		// 3.执行查询操作
		engineType = vehicleRequestDomain.findEngineTypeById(id);
		
		
		// 3.执行查询操作 contractDomain.findEntityById(id);
		
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "查询引擎类型信息成功！");
	} catch (Exception ex) {
		executeLogger(true, "查询引擎类型信息失败！" + "失败原因:" + ex.getMessage());
	}
	
	return engineType;
	}
	
	/**
	* doSaveExecute实现保存引擎类型设置的业务逻辑处理
	* 
	* @param contractClause
	* @return
	*/
	public EngineType doSaveEngineType(EngineType engineType) {
		EngineType engineType1 = null;
	try {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();
		
		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		//保存系统当前时间
		engineType.setUpdateTime(new Date());
		// 3. 执行保存操作
		engineType1 = vehicleRequestDomain.merge(engineType);			
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "保存引擎类型：" + engineType + "成功！");
	} catch (Exception ex) {
		executeLogger(true, "保存引擎类型：" + engineType + "失败！失败原因：" + ex.getMessage());
	}
	return engineType1;
	}	
	
	/**
	* doSaveExecute实现删除引擎设置的业务逻辑处理
	* 
	* @param 
	*/
	public void doDeleteByEngineType(EngineType engineType) {
	try {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();
	
		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
	
		// 3. 执行删除操作
		vehicleRequestDomain.deleteByEngineType(engineType);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
	
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
	
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "删除引擎类型设置成功！");
	} catch (Exception ex) {
		executeLogger(true, "删除引擎类型设置信息失败！失败原因：" + ex.getMessage());
	}
	}
	
	
		/////////////
		/**
		* doSaveExecute实现获取排量类型列表的业务逻辑处理
		* 
		* @param params
		* @param pager
		* @return
		*/
		public Pager doSubDisplacementTypeList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();
		
		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3.执行查询操作
		p = vehicleRequestDomain.findPagerByDisplacementType(params, pager);
		
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "查询排量类型信息成功！");
		} catch (Exception ex) {
		executeLogger(true, "查询排量类型信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
		}
		
		/**
		* doSaveExecute实现保存排量类型业务逻辑处理
		* 
		* @param id
		* @return
		* @throws Exception
		*/
		public DisplacementType doListDisplacementTypeById(String id) throws Exception {
			DisplacementType displacementType = null;
		try {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();
		
		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		
		// 3.执行查询操作
		displacementType = vehicleRequestDomain.findDisplacementTypeById(id);
		
		
		// 3.执行查询操作 contractDomain.findEntityById(id);
		
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "查询排量类型信息成功！");
		} catch (Exception ex) {
		executeLogger(true, "查询排量类型信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return displacementType;
		}
		
		/**
		* doSaveExecute实现保存排量类型设置的业务逻辑处理
		* 
		* @param contractClause
		* @return
		*/
		public DisplacementType doSaveDisplacementType(DisplacementType displacementType) {
			DisplacementType displacementType1 = null;
		try {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();
		
		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		//保存系统当前时间
		displacementType.setUpdateTime(new Date());
		// 3. 执行保存操作
		displacementType1 = vehicleRequestDomain.merge(displacementType);			
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "保存排量类型：" + displacementType + "成功！");
		} catch (Exception ex) {
		executeLogger(true, "保存排量类型：" + displacementType + "失败！失败原因：" + ex.getMessage());
		}
		return displacementType1;
		}	
		
		/**
		* doSaveExecute实现删除排量类型设置的业务逻辑处理
		* 
		* @param 
		*/
		public void doDeleteByDisplacementType(DisplacementType displacementType) {
		try {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();
		
		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		
		// 3. 执行删除操作
		vehicleRequestDomain.deleteByDisplacementType(displacementType);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "删除排量类型设置成功！");
		} catch (Exception ex) {
		executeLogger(true, "删除排量类型设置信息失败！失败原因：" + ex.getMessage());
		}
		}
		
/////////////
	/**
	* doSaveExecute实现获取维护类型列表的业务逻辑处理
	* 
	* @param params
	* @param pager
	* @return
	*/
	public Pager doSubMaintenanceTypeList(Map<String, Object> params, Pager pager) {
	Pager p = null;
	try {
	// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
	// Map parameters = baExecutor.executeParameterProcess();
	
	// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
	beforeEventTrigger("PO_CREATE_BEFORE");
	// 3.执行查询操作
	p = vehicleRequestDomain.findPagerByMaintenanceType(params, pager);
	
	// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
	afterEventTrigger("PO_CREATE_AFTER");
	
	// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
	
	// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	executeLogger(true, "查询维护类型信息成功！");
	} catch (Exception ex) {
	executeLogger(true, "查询维护类型信息失败！" + "失败原因:" + ex.getMessage());
	}
	return p;
	}
	
	/**
	* doSaveExecute实现保存维护类型业务逻辑处理
	* 
	* @param id
	* @return
	* @throws Exception
	*/
	public MaintenanceType doListMaintenanceTypeById(String id) throws Exception {
		MaintenanceType maintenanceType = null;
	try {
	// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
	// Map parameters = baExecutor.executeParameterProcess();
	
	// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
	beforeEventTrigger("PO_CREATE_BEFORE");
	
	// 3.执行查询操作
	maintenanceType = vehicleRequestDomain.findMaintenanceTypeById(id);
	
	
	// 3.执行查询操作 contractDomain.findEntityById(id);
	
	// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
	afterEventTrigger("PO_CREATE_AFTER");
	
	// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
	
	// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	executeLogger(true, "查询维护类型信息成功！");
	} catch (Exception ex) {
	executeLogger(true, "查询维护类型信息失败！" + "失败原因:" + ex.getMessage());
	}
	
	return maintenanceType;
	}
	
	/**
	* doSaveExecute实现保存维护类型设置的业务逻辑处理
	* 
	* @param contractClause
	* @return
	*/
	public MaintenanceType doSaveMaintenanceType(MaintenanceType maintenanceType) {
		MaintenanceType maintenanceType1 = null;
	try {
	// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
	// Map parameters = baExecutor.executeParameterProcess();
	
	// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
	beforeEventTrigger("PO_CREATE_BEFORE");
	//保存系统当前时间
	maintenanceType.setUpdateTime(new Date());
	// 3. 执行保存操作
	maintenanceType1 = vehicleRequestDomain.merge(maintenanceType);			
	// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
	afterEventTrigger("PO_CREATE_AFTER");
	// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
	// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	executeLogger(true, "保存维护类型：" + maintenanceType + "成功！");
	} catch (Exception ex) {
	executeLogger(true, "保存维护类型：" + maintenanceType + "失败！失败原因：" + ex.getMessage());
	}
	return maintenanceType1;
	}	
	
	/**
	* doSaveExecute实现删除维护类型设置的业务逻辑处理
	* 
	* @param 
	*/
	public void doDeleteByMaintenanceType(MaintenanceType maintenanceType) {
	try {
	// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
	// Map parameters = baExecutor.executeParameterProcess();
	
	// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
	beforeEventTrigger("PO_CREATE_BEFORE");
	
	// 3. 执行删除操作
	vehicleRequestDomain.deleteByMaintenanceType(maintenanceType);
	// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
	afterEventTrigger("PO_CREATE_AFTER");
	
	// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
	
	// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	executeLogger(true, "删除维护类型设置成功！");
	} catch (Exception ex) {
	executeLogger(true, "删除维护类型设置信息失败！失败原因：" + ex.getMessage());
	}
	}
	
		
		//////////

		/**
		 * doSaveExecute实现保存车辆维护业务逻辑处理
		 * @param id
		 * @return
		 * @throws Exception
		 */
		public CarMaintenance doListCarMaintenanceById(String id) throws Exception {
			CarMaintenance carMaintenance = null;
			try {
				// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
				// Map parameters = baExecutor.executeParameterProcess();

				// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
				beforeEventTrigger("PO_CREATE_BEFORE");

				// 3.执行查询操作
				carMaintenance = vehicleRequestDomain.findCarMaintenanceById(id);

				/*
				 * // 3.执行查询操作 contractDomain.findEntityById(id);
				 */
				// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
				afterEventTrigger("PO_CREATE_AFTER");

				// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

				// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
				executeLogger(true, "查询车辆维护信息成功！");
			} catch (Exception ex) {
				executeLogger(true, "查询车辆维护信息失败！" + "失败原因:" + ex.getMessage());
			}

			return carMaintenance;
		}
		/**
		 * doSaveExecute实现保存车辆维护业务逻辑处理
		 */
		public CarMaintenance doSaveCarMaintenance(CarMaintenance carMaintenance)throws Exception {
			CarMaintenance carMaintenance1 = null;
			try {
				// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
				// Map parameters = baExecutor.executeParameterProcess();

				// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
				beforeEventTrigger("PO_CREATE_BEFORE");
				//保存系统当前时间
				/*taskDefinition.setUpdateTime(new Date());*/
				// 3. 执行保存操作
				carMaintenance1 = vehicleRequestDomain.merge(carMaintenance);
				// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
				afterEventTrigger("PO_CREATE_AFTER");
				// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
				// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
				executeLogger(true, "保存车辆维护设置 ：" + carMaintenance + "成功！");
			} catch (Exception ex) {
				executeLogger(true,
						"保存车辆维护：" + carMaintenance + "失败！失败原因：" + ex.getMessage());
				throw new Exception("保存车辆维护设置", ex);
			}
			return carMaintenance1;
		}
		
		
		/**删除*/
		public CarMaintenance findCarMaintenanceById(String id) throws Exception {
			return vehicleRequestDomain.findCarMaintenanceById(id);
		}
		
		
		/**
		 * doSaveExecute实现删除车辆维护的业务逻辑处理
		 */
		public void doDeleteByCarMaintenance(CarMaintenance carMaintenance) {
			try {
				// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
				// Map parameters = baExecutor.executeParameterProcess();

				// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
				beforeEventTrigger("PO_CREATE_BEFORE");

				// 3. 执行删除操作
				vehicleRequestDomain.deleteByCarMaintenance(carMaintenance);
				// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
				afterEventTrigger("PO_CREATE_AFTER");

				// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

				// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
				executeLogger(true, "删除车辆维护设置成功！");
			} catch (Exception ex) {
				executeLogger(true, "删除车辆维护设置失败！失败原因：" + ex.getMessage());
			}
		}
		
		/**
		 * doSaveExecute实现获取车辆维护列表的业务逻辑处理
		 * 
		 * @param params
		 * @param pager
		 * @return
		 */
		public Pager doCarMaintenanceList(Map<String, Object> params, Pager pager) {
			Pager p = null;
			try {
				// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
				// Map parameters = baExecutor.executeParameterProcess();
				
				// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
				beforeEventTrigger("PO_CREATE_BEFORE");
				// 3.执行查询操作
				p = vehicleRequestDomain.findPagerByVehicleHqlCarMaintenance(params, pager);
				
				// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
				afterEventTrigger("PO_CREATE_AFTER");
				
				// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
				
				// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
				executeLogger(true, "查询车辆维护信息成功！");
			} catch (Exception ex) {
				executeLogger(true, "查询车辆维护信息失败！" + "失败原因:" + ex.getMessage());
			}
			return p;
		}
		
		
		//////////
		
		/**
		 * doSaveExecute实现获取车辆油耗列表的业务逻辑处理
		 * 
		 * @param params
		 * @param pager
		 * @return
		 */
		public Pager doTpkList(Map<String, Object> params, Pager pager) {
			Pager p = null;
			try {
				// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
				// Map parameters = baExecutor.executeParameterProcess();
				
				// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
				beforeEventTrigger("PO_CREATE_BEFORE");
				// 3.执行查询操作
				p = vehicleRequestDomain.findPagerByVehicleHqlTpk(params, pager);
				
				// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
				afterEventTrigger("PO_CREATE_AFTER");
				
				// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
				
				// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
				executeLogger(true, "查询车辆油耗信息成功！");
			} catch (Exception ex) {
				executeLogger(true, "查询车辆油耗信息失败！" + "失败原因:" + ex.getMessage());
			}
			return p;
		}

		/**
		 * doSaveExecute实现保存车辆油耗业务逻辑处理
		 * @param id
		 * @return
		 * @throws Exception
		 */
		public Tpk doListTpkById(String id) throws Exception {
			Tpk tpk = null;
			try {
				// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
				// Map parameters = baExecutor.executeParameterProcess();

				// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
				beforeEventTrigger("PO_CREATE_BEFORE");

				// 3.执行查询操作
				tpk = vehicleRequestDomain.findTpkById(id);

				/*
				 * // 3.执行查询操作 contractDomain.findEntityById(id);
				 */
				// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
				afterEventTrigger("PO_CREATE_AFTER");

				// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

				// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
				executeLogger(true, "查询车辆油耗信息成功！");
			} catch (Exception ex) {
				executeLogger(true, "查询车辆油耗信息失败！" + "失败原因:" + ex.getMessage());
			}

			return tpk;
		}
		/**
		 * doSaveExecute实现保存车辆油耗业务逻辑处理
		 */
		public Tpk doSaveTpk(Tpk tpk) throws Exception {
			Tpk tpk1 = null;
			try {
				// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
				// Map parameters = baExecutor.executeParameterProcess();

				// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
				beforeEventTrigger("PO_CREATE_BEFORE");
				//保存系统当前时间
				/*taskDefinition.setUpdateTime(new Date());*/
				// 3. 执行保存操作
				tpk1 = vehicleRequestDomain.merge(tpk);
				// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
				afterEventTrigger("PO_CREATE_AFTER");
				// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
				// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
				executeLogger(true, "保存车辆油耗设置 ：" + tpk + "成功！");
			} catch (Exception ex) {
				executeLogger(true,
						"保存车辆油耗：" + tpk + "失败！失败原因：" + ex.getMessage());
				throw new Exception("保存车辆油耗设置", ex);
			}
			return tpk1;
		}
		
		
		/**删除*/
		public Tpk findTpkById(String id) throws Exception {
			return vehicleRequestDomain.findTpkById(id);
		}
		
		/**
		 * doSaveExecute实现删除车辆油耗的业务逻辑处理
		 */
		public void doDeleteByTpk(Tpk tpk) {
			try {
				// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
				// Map parameters = baExecutor.executeParameterProcess();

				// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
				beforeEventTrigger("PO_CREATE_BEFORE");

				// 3. 执行删除操作
				vehicleRequestDomain.deleteByTpk(tpk);
				// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
				afterEventTrigger("PO_CREATE_AFTER");

				// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

				// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
				executeLogger(true, "删除车辆油耗设置成功！");
			} catch (Exception ex) {
				executeLogger(true, "删除车辆油耗设置失败！失败原因：" + ex.getMessage());
			}
		}
		
		/**
		 * doSaveExecute实现获取车辆维护列表的业务逻辑处理
		 * 
		 * @param params
		 * @param pager
		 * @return
		 */
		public Pager doCarTpkList(Map<String, Object> params, Pager pager) {
			Pager p = null;
			try {
				// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
				// Map parameters = baExecutor.executeParameterProcess();
				
				// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
				beforeEventTrigger("PO_CREATE_BEFORE");
				// 3.执行查询操作
				p = vehicleRequestDomain.findPagerByVehicleHqlTpk(params, pager);
				
				// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
				afterEventTrigger("PO_CREATE_AFTER");
				
				// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
				
				// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
				executeLogger(true, "查询车辆油耗信息成功！");
			} catch (Exception ex) {
				executeLogger(true, "查询车辆油耗信息失败！" + "失败原因:" + ex.getMessage());
			}
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

	public Pager goSubSingleList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = vehicleRequestDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public VehicleRequest findVehicleRequestById(String id) throws Exception {
		return vehicleRequestDomain.findVehicleRequestById(id);
	}

	public VehicleRequest merge(VehicleRequest vehicleRequest) throws Exception {
		VehicleRequest vTemp = vehicleRequestDomain.merge(vehicleRequest);
		return vTemp;
	}

	public void deleteByVehicleRequest(VehicleRequest vehicleRequest) throws Exception {
		vehicleRequestDomain.deleteByVehicleRequest(vehicleRequest);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		vehicleRequestDomain.deleteByIds(ids);
	}

	public List<VehicleRequest> findVehicleRequestIndex() throws Exception {
		return vehicleRequestDomain.findVehicleRequestIndex();
	}
}
