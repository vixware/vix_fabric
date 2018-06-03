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

package com.vix.purchase.share;

/**
 * 采购设置参数主键定义对照
 * 
 * @author Ivan 2013-06-24
 * 
 */
public interface IPurchaseSetParam {
	/** 普通业务必须要有订单 */
	public static final String PUR_ORDINARY_BIZ_MUST_HAVE_ORDERS = "PUR_ORDINARY_BIZ_MUST_HAVE_ORDERS";
	/** 直运业务必须要有订单 */
	public static final String PUR_DIRECT_SHIPMENT_BIZ_MUST_HAVE_ORDERS = "PUR_DIRECT_SHIPMENT_BIZ_MUST_HAVE_ORDERS";
	/** 受托代销业务必须要有订单 */
	public static final String PUR_CONSIGNMENT_BIZ_MUST_HAVE_ORDERS = "PUR_CONSIGNMENT_BIZ_MUST_HAVE_ORDERS";
	/** 启用受托代销 */
	public static final String PUR_ENABLE_CONSIGNMENT = "PUR_ENABLE_CONSIGNMENT";
	/** 是否允许超订单到货及入库 */
	public static final String PUR_WHETHER_TO_ALLOW_ULTRA_ORDER_ARRIVAL_AND_STORAGE = "PUR_WHETHER_TO_ALLOW_ULTRA_ORDER_ARRIVAL_AND_STORAGE";
	/** 是否允许超计划订货 */
	public static final String PUR_WHETHER_TO_ALLOW_ULTRA_PLANNED_ORDERS = "PUR_WHETHER_TO_ALLOW_ULTRA_PLANNED_ORDERS";
	/** 入库单是否自动带入单价 */
	public static final String PUR_WHETHER_TO_AUTOMATICALLY_INTO_THE_UNIT_PRICE = "PUR_WHETHER_TO_AUTOMATICALLY_INTO_THE_UNIT_PRICE";
	/** 录入方式 */
	public static final String PUR_INPUT_MODE = "PUR_INPUT_MODE";
	/** 是否按供应商取价 */
	public static final String PUR_WHETHER_THE_SUPPLIERS_TAKE_PRICE = "PUR_WHETHER_THE_SUPPLIERS_TAKE_PRICE";
	/** 来源 */
	public static final String PUR_SOURCE = "PUR_SOURCE";
	/** 最高进阶控制口令 */
	public static final String PUR_THE_ADVANCED_CONTROL_PASSWORD = "PUR_THE_ADVANCED_CONTROL_PASSWORD";
	/** 单行容差 */
	public static final String PUR_SPECIFIC_TOLERANCE = "PUR_SPECIFIC_TOLERANCE";
	/** 合计容差 */
	public static final String PUR_TOTAL_TOLERANCE = "PUR_TOTAL_TOLERANCE";
	/** 结算选项 */
	public static final String PUR_SETTLEMENT_OPTIONS = "PUR_SETTLEMENT_OPTIONS";
	/** 提前预警天数 */
	public static final String PUR_Advance_Warning_Time = "PUR_Advance_Warning_Time";
	/** 逾期预警天数 */
	public static final String PUR_EARLY_DAYS_OVERDUE = "PUR_EARLY_DAYS_OVERDUE";
	/** 检查存货权限 */
	public static final String PUR_CHECK_INVENTORY_PERMISSIONS = "PUR_CHECK_INVENTORY_PERMISSIONS";
	/** 检查供应商权限 */
	public static final String PUR_CHECK_SUPPLIERS_PERMISSIONS = "PUR_CHECK_SUPPLIERS_PERMISSIONS";
	/** 检查部门权限 */
	public static final String PUR_INSPECTION_DEPARTMENT_PERMISSION = "PUR_INSPECTION_DEPARTMENT_PERMISSION";
	/** 检查业务员权限 */
	public static final String PUR_CHECK_SALESMAN_PERMISSIONS = "PUR_CHECK_SALESMAN_PERMISSIONS";
	/** 检查操作员权限 */
	public static final String PUR_CHECK_THE_OPERATOR_PRIVILEGES = "PUR_CHECK_THE_OPERATOR_PRIVILEGES";
	/** 检查金额审核权限 */
	public static final String PUR_CHECK_THE_AMOUNT_OF_AUDIT_PERMISSIONS = "PUR_CHECK_THE_AMOUNT_OF_AUDIT_PERMISSIONS";
	/** 启用日期 */
	public static final String PUR_OPENING_DATE = "PUR_OPENING_DATE";
	/** 启用会计月 */
	public static final String PUR_ENABLE_ACCOUNTING_MONTH = "PUR_ENABLE_ACCOUNTING_MONTH";
	/** 浮动换算率规则 */
	public static final String PUR_FLOATING_CONVERSION_RATE_RULES = "PUR_FLOATING_CONVERSION_RATE_RULES";
	/** 启用远程 */
	public static final String PUR_ENABLE_REMOTE = "PUR_ENABLE_REMOTE";
	/** 远程识别号 */
	public static final String PUR_REMOTE_IDENTIFICATION_NUMBER = "PUR_REMOTE_IDENTIFICATION_NUMBER";
	/** 专用发票默认税率 */
	public static final String PUR_SPECIAL_INVOICES_DEFAULT_RATE = "PUR_SPECIAL_INVOICES_DEFAULT_RATE";
	/** 参照控制 */
	public static final String PUR_Reference_Control = "PUR_Reference_Control";
	/** 控制打印次数 */
	public static final String PUR_CONTROL_THE_NUMBER_OF_PRINTS = "PUR_CONTROL_THE_NUMBER_OF_PRINTS";
	/** 打印预览 */
	public static final String PUR_PRINT_PREVIEW = "PUR_PRINT_PREVIEW";
}
