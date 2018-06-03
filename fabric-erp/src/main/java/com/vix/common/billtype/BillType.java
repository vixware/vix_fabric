package com.vix.common.billtype;

import java.util.HashMap;
import java.util.Map;

/**
 * 单据类型常量表
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-15
 */
public class BillType {

	/** 采购订单 */
	public static final String PUR_ORDER = "PUR_ORDER";
	public static final String PUR_ORDER_ITEM = "PUR_ORDER_ITEM";
	/** 入库单 */
	public static final String INV_INBOUND = "INV_INBOUND";
	/** 采购计划 */
	public static final String PUR_PLAN = "PUR_PLAN";
	/** 采购询价单 */
	public static final String PUR_INQUERY = "PUR_INQUERY";
	public static final String PUR_INQUERY_ITEM = "PUR_INQUERY_ITEM";
	/** 采购入库 */
	public static final String PUR_INBOUND = "PUR_INBOUND";
	public static final String PUR_INBOUND_ITEM = "PUR_INBOUND_ITEM";
	/** 采购退货 */
	public static final String PUR_RETURN = "PUR_RETURN";
	public static final String PUR_RETURN_ITEM = "PUR_RETURN_ITEM";
	/** 采购申请 */
	public static final String PUR_APPLY = "PUR_APPLY";
	public static final String PUR_APPLY_ITEM = "PUR_APPLY_ITEM";
	/** 采购到货 */
	public static final String PUR_ARRIVAL = "PUR_ARRIVAL";
	public static final String PUR_ARRIVAL_ITEM = "PUR_ARRIVAL_ITEM";
	/** 采购发票 */
	public static final String PUR_INVOICE = "PUR_INVOICE";
	/** 进口单据 */
	public static final String PUR_IMPORT = "PUR_IMPORT";
	/** 销售计划 */
	public static final String SAL_PLAN = "SAL_PLAN";
	/** 销售报价单 */
	public static final String SAL_QUOTATION = "SAL_QUOTATION";
	/** 销售订单 */
	public static final String SA_ORDER = "SA_ORDER";
	/** 销售出库单 */
	public static final String SA_OUTBOUND = "SA_OUTBOUND";
	/** 销售发货单 */
	public static final String SA_DELIVERY = "SA_DELIVERY";
	/** 销售退货单 */
	public static final String SA_RETURN = "SA_RETURN";
	/** 出口单据 */
	public static final String SA_EXPORT = "SA_EXPORT";
	/** 出库单 */
	public static final String INV_OUTBOUND = "INV_OUTBOUND";
	/**
	 * 报销单
	 */
	public static final String OA_EXPENSE_ACCOUNT = "OA_EXPENSE_ACCOUNT";
	/**
	 * 加班记录
	 */
	public static final String OA_OVERTIME_RECORDS = "OA_OVERTIME_RECORDS";
	/**
	 * 外出记录
	 */
	public static final String OA_PERSONAL_ATTENDANCE = "OA_PERSONAL_ATTENDANCE";
	/**
	 * 收文管理
	 */
	public static final String OA_RECEIVE_DOCUMENT = "OA_RECEIVEDOCUMENT";
	/**
	 * 发文管理
	 */
	public static final String OA_SEND_DOCUMENT = "OA_SENDDOCUMENT";
	static Map<String, String> codeMap;
	static Map<String, String> urlMap;

	public static String getCodeText(String billcode) {
		if (codeMap == null)
			initBillsCodeMap();

		return codeMap.get(billcode);
	}

	public static String getUrl(String billcode) {
		if (urlMap == null)
			initUrlMap();

		return urlMap.get(billcode);
	}

	static void initUrlMap() {
		urlMap = new HashMap<String, String>();

		urlMap.put("0", "该单据尚未创建!");
		//其他入库单
		urlMap.put(INV_INBOUND, "/vixntInboundWarehouseAction!goAudit.action");
		//报销单
		urlMap.put(OA_EXPENSE_ACCOUNT, "/oa/travelClaimsAction!goAudit.action");
		//出库单
		urlMap.put(INV_OUTBOUND, "/vixntOutBoundAction!goAudit.action");
		//销售订单
		urlMap.put(SA_ORDER, "/nvixSalesOrderAction!goAudit.action");
		//销售计划
		urlMap.put(SAL_PLAN, "/nvixntSalePlanAction!goAudit.action");
		//销售报价
		urlMap.put(SAL_QUOTATION, "/nvixSalesQuotationAction!goAudit.action");
		//销售出库
		urlMap.put(SA_OUTBOUND, "/nvixntSalesOutBoundAction!goAudit.action");
		//采购订单
		urlMap.put(PUR_ORDER, "/purchase/vixntPurchaseOrderAction!goAudit.action");
		//采购申请
		urlMap.put(PUR_APPLY, "/purchase/vixntPurchaseApplyAction!goAudit.action");
		//采购计划
		urlMap.put(PUR_PLAN, "/purchase/vixntPurchasePlanAction!goAudit.action");
		//采购询价
		urlMap.put(PUR_INQUERY, "/purchase/vixntPurchaseInquireAction!goAudit.action");
		//采购退货
		urlMap.put(PUR_RETURN, "/purchase/vixntPurchaseReturnAction!goAudit.action");
		//报销单
		urlMap.put(OA_EXPENSE_ACCOUNT, "/vixntReimburseAction!goAudit.action");
	}

	static void initBillsCodeMap() {
		codeMap = new HashMap<String, String>();
		codeMap.put("0", "该单据尚未创建!");
		codeMap.put(INV_INBOUND, "入库单");
		codeMap.put(OA_EXPENSE_ACCOUNT, "报销单");
		codeMap.put(PUR_ORDER, "采购订单");
		codeMap.put(PUR_PLAN, "采购计划");
		codeMap.put(OA_PERSONAL_ATTENDANCE, "外出");
	}

}
