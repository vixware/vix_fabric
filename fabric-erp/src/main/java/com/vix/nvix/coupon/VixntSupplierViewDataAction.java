package com.vix.nvix.coupon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.vix.common.properties.util.MyTool;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.personaloffice.service.IQueryDataService;

@Controller
@Scope("prototype")
public class VixntSupplierViewDataAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IQueryDataService queryDataService;
	private String startTime;
	private String endTime;
	private String topNum;
	private String setSupplierID = "";// 控制查询哪些供应商的id集合，限制查询条件 // setSupplierID
										// 设置供应商id
	private String queryTime;
	private String id;
	private String changeSQL;
	private String fromPage;

	/**
	 * 供应商统计页面
	 */
	public String goSupplierView() {
		try {
			if ("查询平台所有的供应商相关数据".equals("查询平台所有的供应商相关数据")) {// 模拟根据账号角色，分配查询供应商的相关id集合，将来需要替换代码
				setSupplierID = "all-Supplier-ID";
			} else if ("查询具体某个供应商的相关数据".equals("1")) {// 控制查询哪些供应商的id集合，限制查询条件
														// // setSupplierID
														// 设置供应商id
				setSupplierID = "{id001}";
			}
			setSupplierID = new String("{S---tH-d}" + setSupplierID + "{E---dT-l}");
			supplierSaleGoodsNumMrp(setSupplierID);
			supplierSaleGoodsMeyMc(setSupplierID);
			supplierSaleOrderNumMm(setSupplierID);
			supplierSaleOrderCustomNumMi(setSupplierID);
			headDataBlockMcz(setSupplierID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSupplierView";
	}
	/** 本月新增供应商|本月新增销售商品 |本月新增客户 **/
	public void headDataBlockMcz(String setSupplierID) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", "3");// 正式的3
			List<String> timeArr = MyTool.getTimeArrByHtmlParameterString("ThisMonthOT");
			if (timeArr != null && timeArr.size() > 0) {
				String startTime = timeArr.get(0);
				String endTime = timeArr.get(timeArr.size() - 1);
				params.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
			}
			List<Supplier> sList = queryDataService.findAllByConditions(Supplier.class, params);
			getRequest().setAttribute("supplierLSizeUac", (sList == null ? "0" : sList.size()));
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("setSupplierID", setSupplierID);
			hsMap.put("controlSQL", "suplierSaleNewAddGsNumUin");// suplierSaleNewAddGsNumUin
																	// 供应商统计报表
																	// |本月新增销售商品
			hsMap.put("timeStr", "ThisMonthOT");
			Double NewAddGsNumUin = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
			getRequest().setAttribute("NewAddGsNumUin", MyTool.getIntFromDouble(NewAddGsNumUin));

			hsMap.put("controlSQL", "suplierSaleNewAddCustomNumUnq");// suplierSaleNewAddCustomNumUnq
																		// 供应商统计报表
																		// |本月新增客户
			Double NewAddCustomNumUnq = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
			getRequest().setAttribute("NewAddCustomNumUnq", MyTool.getIntFromDouble(NewAddCustomNumUnq));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 供应商统计报表 |今日客户数量 **/
	public void supplierSaleOrderCustomNumMi(String setSupplierID) {
		try {
			String timeA = MyTool.getTodayBy_yyyyMMdd();// 当日
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("setSupplierID", setSupplierID);
			hsMap.put("controlSQL", "supplierSaleOrderCustomNumUin");// supplierSaleOrderCustomNumUin
																		// 供应商统计报表
																		// |今日客户数量
			hsMap.put("timeStr", timeA + timeA);
			Double sSOCustomtodayNum = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
			String timeB = MyTool.dateReduceOrAddByInt(timeA, -1);// 昨日
			hsMap.put("timeStr", timeB + timeB);
			Double sSOCustomyesteNum = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
			getRequest().setAttribute("sSOCustomtodayNum", MyTool.getIntFromDouble(sSOCustomtodayNum));
			getRequest().setAttribute("sSOCustomyesteNum", MyTool.getIntFromDouble(sSOCustomyesteNum));
			String divClass = "sSOCustomtodayIclass";
			String divMomNum = "sSOCustomtodayImNum";
			String momNum = MyTool.getMomOne(sSOCustomtodayNum, sSOCustomyesteNum);
			if (momNum.startsWith("-")) { // 页面标签中的class是不完整的，这里通过计算，补充完整class样式
				String num = momNum.replace("-", "");
				getRequest().setAttribute(divClass, "down tmyColorA");
				getRequest().setAttribute(divMomNum, num);
			} else if (momNum.startsWith("+")) { // .tmyColorB{color:#D0000D} /*
													// 蓝色箭头 用于环比符号显示 */
				String num = momNum.replace("+", ""); // .tmyColorA{color:#05CD15}
														// /* 粉红色箭头 用于环比符号显示 */
				getRequest().setAttribute(divClass, "up tmyColorB");
				getRequest().setAttribute(divMomNum, num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 供应商统计报表 |今日销售订单数量 **/
	public void supplierSaleOrderNumMm(String setSupplierID) {
		try {
			String timeA = MyTool.getTodayBy_yyyyMMdd();// 当日
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("setSupplierID", setSupplierID);
			hsMap.put("controlSQL", "supplierSaleOrderNumUnm");// supplierSaleOrderNumUnm
																// 供应商统计报表
																// |今日销售订单数量
			hsMap.put("timeStr", timeA + timeA);
			Double sSOrdertodayNum = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
			String timeB = MyTool.dateReduceOrAddByInt(timeA, -1);// 昨日
			hsMap.put("timeStr", timeB + timeB);
			Double sSOrderyesteNum = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
			getRequest().setAttribute("sSOrdertodayNum", MyTool.getIntFromDouble(sSOrdertodayNum));
			getRequest().setAttribute("sSOrderyesteNum", MyTool.getIntFromDouble(sSOrderyesteNum));
			String divClass = "sSOrdertodayIclass";
			String divMomNum = "sSOrdertodayImNum";
			String momNum = MyTool.getMomOne(sSOrdertodayNum, sSOrderyesteNum);
			if (momNum.startsWith("-")) { // 页面标签中的class是不完整的，这里通过计算，补充完整class样式
				String num = momNum.replace("-", "");
				getRequest().setAttribute(divClass, "down tmyColorA");
				getRequest().setAttribute(divMomNum, num);
			} else if (momNum.startsWith("+")) { // .tmyColorB{color:#D0000D} /*
													// 蓝色箭头 用于环比符号显示 */
				String num = momNum.replace("+", ""); // .tmyColorA{color:#05CD15}
														// /* 粉红色箭头 用于环比符号显示 */
				getRequest().setAttribute(divClass, "up tmyColorB");
				getRequest().setAttribute(divMomNum, num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 供应商统计报表 |销售商品金额 **/
	public void supplierSaleGoodsMeyMc(String setSupplierID) {
		try {
			String timeA = MyTool.getTodayBy_yyyyMMdd();// 当日
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("setSupplierID", setSupplierID);
			hsMap.put("controlSQL", "supplierSaleGoodsMeyUrvp");// supplierSaleGoodsMeyUrvp
																// 供应商统计报表
																// |销售商品金额
			hsMap.put("timeStr", timeA + timeA);
			Double sSaleGstodayMey = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
			String timeB = MyTool.dateReduceOrAddByInt(timeA, -1);// 昨日
			hsMap.put("timeStr", timeB + timeB);
			Double sSaleGsyesteMey = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
			getRequest().setAttribute("sSaleGstodayMey", MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(sSaleGstodayMey)));
			getRequest().setAttribute("sSaleGsyesteMey", MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(sSaleGsyesteMey)));
			String divClass = "sSaleGMeytodayIclass";
			String divMomNum = "sSaleGMeytodayImNum";
			String momNum = MyTool.getMomOne(sSaleGstodayMey, sSaleGsyesteMey);
			if (momNum.startsWith("-")) { // 页面标签中的class是不完整的，这里通过计算，补充完整class样式
				String num = momNum.replace("-", "");
				getRequest().setAttribute(divClass, "down tmyColorA");
				getRequest().setAttribute(divMomNum, num);
			} else if (momNum.startsWith("+")) { // .tmyColorB{color:#D0000D} /*
													// 蓝色箭头 用于环比符号显示 */
				String num = momNum.replace("+", ""); // .tmyColorA{color:#05CD15}
														// /* 粉红色箭头 用于环比符号显示 */
				getRequest().setAttribute(divClass, "up tmyColorB");
				getRequest().setAttribute(divMomNum, num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 供应商统计报表 |销售商品数量 (种类) SupplierSaleGoodsNumArp **/
	public void supplierSaleGoodsNumMrp(String setSupplierID) {
		try {
			String timeA = MyTool.getTodayBy_yyyyMMdd();// 当日
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("setSupplierID", setSupplierID);
			hsMap.put("controlSQL", "supplierSaleGoodsNumUrvp");// supplierSaleGoodsNumUrvp
																// 供应商统计报表
																// |销售商品数量 (种类)
			hsMap.put("timeStr", timeA + timeA);
			Double sSaleGstodayNum = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
			String timeB = MyTool.dateReduceOrAddByInt(timeA, -1);// 昨日
			hsMap.put("timeStr", timeB + timeB);
			Double sSaleGsyesteNum = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
			getRequest().setAttribute("sSaleGstodayNum", MyTool.getIntFromDouble(sSaleGstodayNum));
			getRequest().setAttribute("sSaleGsyesteNum", MyTool.getIntFromDouble(sSaleGsyesteNum));
			String divClass = "sSaleGstodayIclass";
			String divMomNum = "sSaleGstodayImNum";
			String momNum = MyTool.getMomOne(sSaleGstodayNum, sSaleGsyesteNum);
			if (momNum.startsWith("-")) { // 页面标签中的class是不完整的，这里通过计算，补充完整class样式
				String num = momNum.replace("-", "");
				getRequest().setAttribute(divClass, "down tmyColorA");
				getRequest().setAttribute(divMomNum, num);
			} else if (momNum.startsWith("+")) { // .tmyColorB{color:#D0000D} /*
													// 蓝色箭头 用于环比符号显示 */
				String num = momNum.replace("+", ""); // .tmyColorA{color:#05CD15}
														// /* 粉红色箭头 用于环比符号显示 */
				getRequest().setAttribute(divClass, "up tmyColorB");
				getRequest().setAttribute(divMomNum, num); // class="fa
															// fa-arrow-down
															// tmyColorA"//class="fa
															// fa-arrow-up
															// tmyColorB"
			}
			// 页面应该是这样的
			// <i class="fa fa-arrow-${sSaleGstodayIclass
			// }"></i>${sSaleGstodayImNum }%</strong>
			// <style type="text/css">
			// .tmyColorA{color:#05CD15} /* 粉红色下箭头 用于环比符号显示 */
			// .tmyColorB{color:#D0000D} /* 蓝色上箭头 用于环比符号显示 */
			// </style>
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店订单统计列表 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void goGoodsQueryList() {
		try {
			Map<String, Object> params = getParams();
			Pager myPager = this.getPager();
			String setSupplierID = getDecodeRequestParameter("setSupplierID");
			String setSupplierIDSql = "('1')";
			if (StringUtils.isNotEmpty(setSupplierID)) {
				setSupplierIDSql = queryDataService.queryDifferentIDsAbcd(setSupplierID);
			}
			String startTime = getDecodeRequestParameter("startTime");
			String endTime = getDecodeRequestParameter("endTime");
			String mynum = getDecodeRequestParameter("mynum");// mynum 是采购门店

			StringBuffer querySql = new StringBuffer();
			querySql.append(" SELECT mendianID as mdid FROM ( ");
			querySql.append(" SELECT ");
			querySql.append(" toone.id  as mendianID ");
			querySql.append(" ,many.CREATETIME as mCREATETIME ");
			querySql.append(" FROM ");
			querySql.append(" pur_order many ");
			querySql.append(" INNER JOIN DRP_CHANNELDISTRIBUTOR toone ON many.channelDistributor_id = toone.id ");
			querySql.append(" AND toone. NAME IS NOT NULL ");
			querySql.append(" AND toone. NAME <> '' ");
			String mynumSQL = "  ";
			if (StringUtils.isNotEmpty(mynum)) {
				mynumSQL = " and toone. NAME like '%" + mynum.trim() + "%' ";
				querySql.append(mynumSQL);
			}
			String timeSQL = "  ";
			if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
				List<String> obTimeArr = MyTool.getTimeArrByHtmlParameterString(startTime + endTime);
				StringBuffer forSql = new StringBuffer();
				forSql.append(" and many.CREATETIME >= " + MyTool.StringUseToSql(obTimeArr.get(0)));
				forSql.append(" and many.CREATETIME <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(obTimeArr.get(obTimeArr.size() - 1))));
				timeSQL = forSql.toString();
			} else {
				timeSQL = " AND many.CREATETIME IS NOT NULL ";
			}
			String timeSQLipml = timeSQL.replace("many.", "toone.");
			querySql.append(timeSQL);
			querySql.append(" AND many.STATUS = '3' ");
			querySql.append(" AND many.SUPPLIER_ID IS NOT NULL ");
			querySql.append(" AND many.SUPPLIER_ID <> '' ");
			querySql.append(" and many.SUPPLIER_ID in " + setSupplierIDSql + " ");
			querySql.append(" ORDER BY many.CREATETIME desc ");
			querySql.append(" )A GROUP BY A.mendianID ");
			querySql.append(" ORDER BY A.mCREATETIME desc ");
			Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, querySql.toString(), params);
			List resultList = pager.getResultList();
			for (int x = 0; x < resultList.size(); x++) {
				HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
				String mendianID = objectMap.get("mdid").toString();
				ChannelDistributor channelDistributor = this.baseHibernateService.findEntityById(ChannelDistributor.class, mendianID);
				objectMap.put("mdname", (channelDistributor.getName() == null ? "" : channelDistributor.getName() + ""));

				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("setSupplierIDSql", setSupplierIDSql);
				hsMap.put("mendianID", mendianID);
				hsMap.put("controlSQL", "MDDDLBPurchaseGoodsNumUio");// MDDDLBPurchaseGoodsNumUio
																		// 门店订单统计列表
																		// 采购商品总数量
				hsMap.put("timeSQLipml", timeSQLipml);// 需要把 ‘many.CREATETIME’
														// 变成 ‘toone.CREATETIME’
				Double purchaseGoodsNum = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
				objectMap.put("purchaseGoodsNum", purchaseGoodsNum);

				hsMap.put("controlSQL", "MDDDLBPurchaseGoodsMeyUio");// MDDDLBPurchaseGoodsMeyUio
																		// 门店订单统计列表
																		// 采购商品总金额
				Double purchaseGoodsMey = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
				objectMap.put("purchaseGoodsMey", purchaseGoodsMey);

				hsMap.put("controlSQL", "MDDDLBPurchaseGoodsTimeUio");// MDDDLBPurchaseGoodsTimeUio
																		// 门店订单统计列表
																		// 最近交易时间（采购订单创建时间）
				String innerCREATETIME = (String) queryDataService.findDataRtOneUajoop(hsMap).get("stringRTinner");
				objectMap.put("innerCREATETIME", innerCREATETIME);

				resultList.set(x, objectMap);
			}
			pager.setResultList(resultList);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 总部订单统计列表 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void goGoodsQueryListTwoPerson() {
		try {
			Map<String, Object> params = getParams();
			Pager myPager = this.getPager();
			String setSupplierID = getDecodeRequestParameter("setSupplierID");
			String setSupplierIDSql = "('1')";
			if (StringUtils.isNotEmpty(setSupplierID)) {
				setSupplierIDSql = queryDataService.queryDifferentIDsAbcd(setSupplierID);
			}
			String startTime = getDecodeRequestParameter("startTime");
			String endTime = getDecodeRequestParameter("endTime");
			String mypeople = getDecodeRequestParameter("mypeople");// mynum
																	// 是采购人

			StringBuffer querySql = new StringBuffer();
			querySql.append(" SELECT personid as psid FROM ( ");
			querySql.append(" SELECT ");
			querySql.append(" toone.purchasePersonId  as personid ");
			querySql.append(" ,toone.CREATETIME as mCREATETIME ");
			querySql.append(" FROM ");
			querySql.append(" pur_order toone ");
			querySql.append(" WHERE  ");
			querySql.append("  toone.purchasePersonId IS NOT NULL ");
			querySql.append(" AND toone.purchasePersonId <> '' ");
			querySql.append(" AND toone.purchasePerson IS NOT NULL ");
			querySql.append(" AND toone.purchasePerson <> '' ");
			String mynumSQL = "  ";
			if (StringUtils.isNotEmpty(mypeople)) {
				mynumSQL = " and toone. purchasePerson like '%" + mypeople.trim() + "%' ";
				querySql.append(mynumSQL);
			}
			String timeSQL = "  ";
			if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
				List<String> obTimeArr = MyTool.getTimeArrByHtmlParameterString(startTime + endTime);
				StringBuffer forSql = new StringBuffer();
				forSql.append(" and toone.CREATETIME >= " + MyTool.StringUseToSql(obTimeArr.get(0)));
				forSql.append(" and toone.CREATETIME <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(obTimeArr.get(obTimeArr.size() - 1))));
				timeSQL = forSql.toString();
			} else {
				timeSQL = " AND toone.CREATETIME IS NOT NULL ";
			}
			querySql.append(timeSQL);
			querySql.append(" AND toone.STATUS = '3' ");
			querySql.append(" AND toone.SUPPLIER_ID IS NOT NULL ");
			querySql.append(" AND toone.SUPPLIER_ID <> '' ");
			querySql.append(" and toone.SUPPLIER_ID in " + setSupplierIDSql + " ");
			querySql.append(" ORDER BY toone.CREATETIME desc ");
			querySql.append(" )A GROUP BY A.personid ");
			querySql.append(" ORDER BY A.mCREATETIME desc ");
			Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, querySql.toString(), params);
			List resultList = pager.getResultList();

			for (int x = 0; x < resultList.size(); x++) {
				HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
				String personID = objectMap.get("psid").toString();

				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("setSupplierIDSql", setSupplierIDSql);
				hsMap.put("personID", personID);
				hsMap.put("controlSQL", "ZBPurchaseGoodsPersonUic");// ZBPurchaseGoodsPersonUic
																	// 总部订单统计列表
																	// 总部采购人姓名
				hsMap.put("timeSQL", timeSQL);
				String innerPname = (String) queryDataService.findDataRtOneUajoop(hsMap).get("stringRTinner");
				objectMap.put("innerPname", innerPname);

				hsMap.put("controlSQL", "ZBPurchaseGoodsPersonMeyUid");// ZBPurchaseGoodsPersonMeyUid
																		// 总部订单统计列表
																		// 总部采购人总金额
				Double personGoodsMey = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
				objectMap.put("personGoodsMey", personGoodsMey);

				hsMap.put("controlSQL", "ZBPurchaseGoodsPersonNumbUid");// ZBPurchaseGoodsPersonNumbUid
																		// 总部订单统计列表
																		// 总部采购人总数量
				Double personGoodsNum = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
				objectMap.put("personGoodsNum", personGoodsNum);

				hsMap.put("controlSQL", "ZBPurchaseGoodsPersonTimeUid");// ZBPurchaseGoodsPersonTimeUid
																		// 总部订单统计列表
																		// 最近交易时间（采购订单创建时间）
				String innerCREATETIME = (String) queryDataService.findDataRtOneUajoop(hsMap).get("stringRTinner");
				objectMap.put("innerCREATETIME", innerCREATETIME);

				resultList.set(x, objectMap);
			}
			pager.setResultList(resultList);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 门店订单统计列表>查询‘明细’
	 */
	public String goPurchaseDetailsPage() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPurchaseDetailsPage";
	}
	/**
	 * 门店订单统计列表>查询‘订单明细’的具体数据 列表显示
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void goOrderDetailsPageData() {
		try {
			Map<String, Object> params = getParams();
			Pager myPager = this.getPager();

			String queryTime = getDecodeRequestParameter("queryTime");
			String mdid = getDecodeRequestParameter("id");
			String setSupplierID = getDecodeRequestParameter("setSupplierID");
			String setSupplierIDSql = "('1')";
			if (StringUtils.isNotEmpty(setSupplierID)) {
				setSupplierIDSql = queryDataService.queryDifferentIDsAbcd(setSupplierID);
			}
			String timeSQL = "  ";
			if (StringUtils.isNotEmpty(queryTime)) {
				List<String> obTimeArr = MyTool.getTimeArrByHtmlParameterString(queryTime);
				StringBuffer forSql = new StringBuffer();
				forSql.append(" and toone.CREATETIME >= " + MyTool.StringUseToSql(obTimeArr.get(0)));
				forSql.append(" and toone.CREATETIME <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(obTimeArr.get(obTimeArr.size() - 1))));
				timeSQL = forSql.toString();
			} else {
				timeSQL = " AND toone.CREATETIME IS NOT NULL ";
			}
			StringBuffer querySql = new StringBuffer();
			querySql.append(" SELECT ");
			querySql.append(" many.itemCode as imid ");
			querySql.append(" FROM ");
			querySql.append(" pur_orderlineitem many ");
			querySql.append(" INNER JOIN pur_order toone ON many.PURCHASEORDER_ID = toone.id ");
			querySql.append(" AND toone.channelDistributor_id IS NOT NULL ");
			querySql.append(" AND toone.channelDistributor_id <> '' ");
			querySql.append(" " + timeSQL + " ");
			querySql.append(" AND toone. STATUS = '3' ");
			querySql.append(" AND toone.SUPPLIER_ID IS NOT NULL ");
			querySql.append(" AND toone.SUPPLIER_ID <> '' ");
			querySql.append(" AND toone.SUPPLIER_ID in " + setSupplierIDSql + " ");
			querySql.append(" and toone.channelDistributor_id = '" + mdid + "' ");
			querySql.append(" and many.itemCode is not null ");
			querySql.append(" GROUP BY many.itemCode ");

			ChannelDistributor channelDistributor = this.baseHibernateService.findEntityById(ChannelDistributor.class, mdid);

			Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, querySql.toString(), params);
			List resultList = pager.getResultList();
			for (int x = 0; x < resultList.size(); x++) {
				HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
				String itemCode = objectMap.get("imid").toString();
				objectMap.put("mdname", (channelDistributor.getName() == null ? "" : channelDistributor.getName() + ""));

				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("timeSQL", timeSQL);
				hsMap.put("setSupplierIDSql", setSupplierIDSql);
				hsMap.put("mdid", mdid);
				hsMap.put("itemCode", itemCode);
				hsMap.put("controlSQL", "detailedUuiuyjglk");// detailedUuiuyjglk
																// 门店订单统计列表>查看pur_orderlineitem的ID
				String innerID = (String) queryDataService.findDataRtOneUajoop(hsMap).get("stringRTinner");
				PurchaseOrderLineItem purchaseOrderLineItem = this.baseHibernateService.findEntityById(PurchaseOrderLineItem.class, innerID);
				objectMap.put("itemName", (purchaseOrderLineItem.getItemName() == null ? "" : purchaseOrderLineItem.getItemName() + ""));
				objectMap.put("itemCode", (purchaseOrderLineItem.getItemCode() == null ? "" : purchaseOrderLineItem.getItemCode() + ""));

				hsMap.put("controlSQL", "detailedTotalNumUuiuyjglk");// detailedTotalNumUuiuyjglk
																		// 门店订单统计列表>查看采购商品总数量
				Double detailedTotalNum = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
				objectMap.put("detailedTotalNum", detailedTotalNum);

				hsMap.put("controlSQL", "detailedTotalMeyUuiuyjglk");// detailedTotalMeyUuiuyjglk
																		// 门店订单统计列表>查看采购商品总金额
				Double detailedTotalMey = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
				objectMap.put("detailedTotalMey", detailedTotalMey);
				resultList.set(x, objectMap);
			}
			pager.setResultList(resultList);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 门店订单统计列表>查询‘明细’Two
	 */
	public String goPurchaseDetailsPageTwo() {
		try {
			getRequest().setAttribute("personName", getRequestParameter("personName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPurchaseDetailsPageTwo";
	}
	/**
	 * 门店订单统计列表>查询‘订单明细’的具体数据 列表显示 Two
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void goOrderDetailsPageDataTwo() {
		try {
			Map<String, Object> params = getParams();
			Pager myPager = this.getPager();

			String queryTime = getDecodeRequestParameter("queryTime");
			String personid = getDecodeRequestParameter("id");
			String setSupplierID = getDecodeRequestParameter("setSupplierID");
			String setSupplierIDSql = "('1')";
			if (StringUtils.isNotEmpty(setSupplierID)) {
				setSupplierIDSql = queryDataService.queryDifferentIDsAbcd(setSupplierID);
			}
			String timeSQL = "  ";
			if (StringUtils.isNotEmpty(queryTime)) {
				List<String> obTimeArr = MyTool.getTimeArrByHtmlParameterString(queryTime);
				StringBuffer forSql = new StringBuffer();
				forSql.append(" and toone.CREATETIME >= " + MyTool.StringUseToSql(obTimeArr.get(0)));
				forSql.append(" and toone.CREATETIME <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(obTimeArr.get(obTimeArr.size() - 1))));
				timeSQL = forSql.toString();
			} else {
				timeSQL = " AND toone.CREATETIME IS NOT NULL ";
			}
			StringBuffer querySql = new StringBuffer();
			querySql.append(" SELECT ");
			querySql.append(" many.itemCode as imid ");
			querySql.append(" FROM ");
			querySql.append(" pur_orderlineitem many ");
			querySql.append(" INNER JOIN pur_order toone ON many.PURCHASEORDER_ID = toone.id ");
			querySql.append(" AND toone.purchasePersonId IS NOT NULL ");
			querySql.append(" AND toone.purchasePersonId <> '' ");
			querySql.append(" " + timeSQL + " ");
			querySql.append(" AND toone. STATUS = '3' ");
			querySql.append(" AND toone.SUPPLIER_ID IS NOT NULL ");
			querySql.append(" AND toone.SUPPLIER_ID <> '' ");
			querySql.append(" AND toone.SUPPLIER_ID in " + setSupplierIDSql + " ");
			querySql.append(" and toone.purchasePersonId = '" + personid + "' ");
			querySql.append(" and many.itemCode is not null ");
			querySql.append(" GROUP BY many.itemCode ");

			Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, querySql.toString(), params);
			List resultList = pager.getResultList();
			for (int x = 0; x < resultList.size(); x++) {
				HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
				String itemCode = objectMap.get("imid").toString();

				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("timeSQL", timeSQL);
				hsMap.put("setSupplierIDSql", setSupplierIDSql);
				hsMap.put("personid", personid);
				hsMap.put("itemCode", itemCode);
				hsMap.put("controlSQL", "detailedUuiDFyjglk");// detailedUuiDFyjglk
																// 总部订单统计列表>查看pur_orderlineitem的ID
				String innerID = (String) queryDataService.findDataRtOneUajoop(hsMap).get("stringRTinner");
				PurchaseOrderLineItem purchaseOrderLineItem = this.baseHibernateService.findEntityById(PurchaseOrderLineItem.class, innerID);
				objectMap.put("itemName", (purchaseOrderLineItem.getItemName() == null ? "" : purchaseOrderLineItem.getItemName() + ""));
				objectMap.put("itemCode", (purchaseOrderLineItem.getItemCode() == null ? "" : purchaseOrderLineItem.getItemCode() + ""));

				hsMap.put("controlSQL", "detailedTotalNumUuiDFuyjglk");// detailedTotalNumUuiDFuyjglk
																		// 总部订单统计列表>查看采购商品总数量
				Double detailedTotalNum = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
				objectMap.put("detailedTotalNum", detailedTotalNum);

				hsMap.put("controlSQL", "detailedTotalMeyUuiudFyjglk");// detailedTotalMeyUuiudFyjglk
																		// 总部订单统计列表>查看采购商品总金额
				Double detailedTotalMey = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
				objectMap.put("detailedTotalMey", detailedTotalMey);
				resultList.set(x, objectMap);
			}
			pager.setResultList(resultList);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 最近30日销售商品数量Top10 **/
	public void supplierAnalysisViewA() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			List<String> timeArr = new ArrayList<String>();
			if (endTime.startsWith("-Lately-Day{")) {
				timeArr = MyTool.getTimeArrByHtmlParameterString(endTime);// 查询最近几天
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
			}
			hsMap.put("timeArr", timeArr);
			hsMap.put("topNum", topNum);
			hsMap.put("controlSQL", "supplierSaleNumByPeopleSaey");// supplierSaleNumByPeopleSaey
																	// 供应商销售商品数量按客户（人）排行
			hsMap.put("setSupplierID", setSupplierID);
			String jsonStrReturn = (String) queryDataService.findAnalysisTopViewAScdsvb(hsMap).get("jsonStrReturn");
			renderJson(jsonStrReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 最近30日销售商品金额Top10 **/
	public void supplierAnalysisViewB() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			List<String> timeArr = new ArrayList<String>();
			if (endTime.startsWith("-Lately-Day{")) {
				timeArr = MyTool.getTimeArrByHtmlParameterString(endTime);// 查询最近几天
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
			}
			hsMap.put("timeArr", timeArr);
			hsMap.put("topNum", topNum);
			hsMap.put("controlSQL", "supplierSaleMoneyByPeopleSc");// supplierSaleMoneyByPeopleSc
																	// 供应商销售商品数量按客户（人）排行
			hsMap.put("setSupplierID", setSupplierID);
			String jsonStrReturn = (String) queryDataService.findAnalysisTopViewAScdsvb(hsMap).get("jsonStrReturn");
			renderJson(jsonStrReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 最近30日销售商品数量按客户排行Top10 **/
	public void supplierAnalysisViewE() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			List<String> timeArr = new ArrayList<String>();
			if (endTime.startsWith("-Lately-Day{")) {
				timeArr = MyTool.getTimeArrByHtmlParameterString(endTime);// 查询最近几天
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
			}
			hsMap.put("timeArr", timeArr);
			hsMap.put("topNum", topNum);
			hsMap.put("controlSQL", "supplierSaleNumByStoreSavey");// supplierSaleNumByStoreSavey
																	// 供应商销售商品数量按客户（门店）排行
			hsMap.put("setSupplierID", setSupplierID);
			String jsonStrReturn = (String) queryDataService.findAnalysisTopViewAScdsvb(hsMap).get("jsonStrReturn");
			renderJson(jsonStrReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 最近30日销售商品金额按客户排行Top10 **/
	public void supplierAnalysisViewF() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			List<String> timeArr = new ArrayList<String>();
			if (endTime.startsWith("-Lately-Day{")) {
				timeArr = MyTool.getTimeArrByHtmlParameterString(endTime);// 查询最近几天
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
			}
			hsMap.put("timeArr", timeArr);
			hsMap.put("topNum", topNum);
			hsMap.put("controlSQL", "supplierSaleMoneyByStoreSiuey");// supplierSaleMoneyByStoreSiuey
																		// 供应商销售商品金额按客户（门店）排行
			hsMap.put("setSupplierID", setSupplierID);
			String jsonStrReturn = (String) queryDataService.findAnalysisTopViewAScdsvb(hsMap).get("jsonStrReturn");
			renderJson(jsonStrReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 最近30日采购订单视图 **/
	@SuppressWarnings("unchecked")
	public void supplierAnalysisViewG() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			List<String> timeArr = new ArrayList<String>();
			if (endTime.startsWith("-Lately-Day{")) {
				timeArr = MyTool.getTimeArrByHtmlParameterString(endTime);// 查询最近几天
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
			}
			String setSupplierIDSql = "('1')";
			if (StringUtils.isNotEmpty(setSupplierID)) {
				setSupplierIDSql = queryDataService.queryDifferentIDsAbcd(setSupplierID);
			}
			hsMap.put("timeArr", timeArr);
			hsMap.put("setSupplierIDSql", setSupplierIDSql);
			hsMap.put("conditionSQL", " and toone.type ='0' ");// type
																// 订单类型0,门店采购订单;1,总部采购订单;
			hsMap.put("controlSQL", "PurchaseOrderNumbUabc");// PurchaseOrderNumbUabc
																// 最近30日采购订单数量视图
			ArrayList<String> mdValueArr = (ArrayList<String>) queryDataService.lookupDateBrokenlineViewUcbf(hsMap).get("valueArr");

			hsMap.put("conditionSQL", " and toone.type ='1' ");// type
																// 订单类型0,门店采购订单;1,总部采购订单;
			ArrayList<String> zbValueArr = (ArrayList<String>) queryDataService.lookupDateBrokenlineViewUcbf(hsMap).get("valueArr");

			ArrayList<String> totalValueArr = MyTool.calculationArrayStringToTotalB(mdValueArr, zbValueArr);

			ArrayList<String> timeStr = MyTool.substringDateStrTwo_Array((ArrayList<String>) timeArr);
			Gson gosn = new Gson();
			String strA = "{\"timeStr\":" + gosn.toJson(timeStr) + "}";
			String strB = "{\"Total\":" + totalValueArr.toString() + "}";
			String strC = "{\"MD\":" + mdValueArr.toString() + "}";
			String strD = "{\"ZB\":" + zbValueArr.toString() + "}";
			String ABC = MyTool.mergeJsonStringTwo(strA, strB, strC, strD);
			renderJson(ABC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 点击页面数字跳转查询相关列表
	 */
	public String startsWithForListPageSupplier() {
		try {
			if (StringUtils.isNotEmpty(changeSQL)) {
				if (changeSQL.startsWith("JfUr8D{")) {// <!--
														// fromPage：PurchasePageUnu
														// 返回 供应商统计报表 |
														// purchaseGsNum
														// 今日采购商品数量 -->
					return "toPurchaseGsAPage";// onClick="controlSQLThree('JfUr8D{purchaseGsNum{Today}}','PurchasePageUnu');"
				} else if (changeSQL.startsWith("Jfbr9D{")) {// onClick="controlSQLThree('Jfbr9D{SupplierBusinessUi{本月}}','PurchasePageUnu');"
					return "toSupplierBusinessUiPage";// 本月新增供应商
				} else if (changeSQL.startsWith("JfYr7D{")) {// onClick="controlSQLThree('JfYr7D{purchaseNewAddCustomUixu{ThisMonthOT}}'
					return "toSupplierNewAddCustomUmPage";// 本月新增客户 //和‘今日客户数量’
				} else if (changeSQL.startsWith("JfHr5D{")) {// onClick="controlSQLThree('JfHr5D{todayPOrderNum{Today}}','PurchasePageUnu');"
					return "toTodayPOrderNumUoPage";// 今日采购订单数量
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询不同的的列表 数据1
	 */
	@SuppressWarnings({"unchecked"})
	public void queryDataToList() {
		try {
			String changeSQL = getDecodeRequestParameter("changeSQL");// JfUr8D{purchaseGsNum{Today}}
			String setSupplierID = queryDataService.queryDifferentIDsAbcd(getDecodeRequestParameter("setSupplierID"));

			StringBuffer hql = new StringBuffer();
			Map<String, Object> HQLhsMap = new HashMap<String, Object>();

			int stateInt = -1;
			if (StringUtils.isNotEmpty(changeSQL)) {
				if (changeSQL.startsWith("JfUr8D{")) {// 查询'今日采购商品数量'
					stateInt = 40;
				} else if (changeSQL.startsWith("Jfbr9D{")) {// 查询'本月新增供应商'
					stateInt = 41;
				}
			}
			if (stateInt == 40) {
				Pager myPager = this.getPager();
				String conditionStr = MyTool.analysisJsonStringFive(changeSQL);
				if (conditionStr.startsWith("purchaseGsNum{")) {// 今日采购商品数量 和
																// 今日采购商品金额
					String timeStr = MyTool.analysisJsonStringFive(conditionStr);
					List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
					HQLhsMap.put("timeArr", timeArr);
					hql = new StringBuffer();
					hql.append(" SELECT ");// #查询供应商销售商品种数 的 ITEMCODE
					hql.append(" many.ITEMCODE as qitemcode,many.ITEMname as qitemname,IFNULL(sum(many.AMOUNT), 0)  as qsumamount,IFNULL(sum(many.PRICE), 0)  as qprice ");
					hql.append(" FROM ");
					hql.append(" pur_orderlineitem many ");
					hql.append(" INNER JOIN pur_order toone ");
					hql.append(" ON ");
					hql.append(" many.PURCHASEORDER_ID = toone.id ");
					hql.append(" AND toone.STATUS = '3' ");
					hql.append(" AND toone.SUPPLIER_ID is not null and toone.SUPPLIER_ID <> '' and toone.SUPPLIER_ID in " + setSupplierID + " ");
					hql.append(" and many.ITEMCODE <> '' and many.ITEMCODE IS NOT NULL and many.ITEMname <> '' and many.ITEMname IS NOT NULL ");
					ArrayList<String> objTimeArr = (ArrayList<String>) HQLhsMap.get("timeArr");
					hql.append(" and toone.CREATETIME >= " + MyTool.StringUseToSql(objTimeArr.get(0)));
					hql.append(" and toone.CREATETIME <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size() - 1))));
					String selectName = getDecodeRequestParameter("selectName");
					if (StringUtils.isNotEmpty(selectName)) {
						hql.append(" and many.itemName like '%" + selectName.trim() + "%' ");
					}
					String phone = getDecodeRequestParameter("phone");
					if (StringUtils.isNotEmpty(phone)) {
						hql.append(" and many.itemCode like '%" + phone.trim() + "%' ");
					}
					hql.append(" GROUP BY ");
					hql.append(" many.ITEMCODE ");
				} else if (conditionStr.startsWith("purchaseNewAddGsNumUiiu{")) {// 本月新增采购商品
																					// onClick="controlSQLThree('JfUr8D{purchaseNewAddGsNumUiiu{ThisMonthOT}}','PurchasePageUnu');"
					String timeStr = MyTool.analysisJsonStringFive(conditionStr);
					List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
					HQLhsMap.put("timeArr", timeArr);
					hql = new StringBuffer();
					hql.append(" SELECT ");
					hql.append(" many.ITEMCODE as qitemcode,many.ITEMname as qitemname,IFNULL(sum(many.AMOUNT), 0)  as qsumamount,IFNULL(sum(many.PRICE), 0)  as qprice ");
					hql.append(" FROM ");
					hql.append(" pur_orderlineitem many ");
					hql.append(" INNER JOIN pur_order toone ");
					hql.append(" ON ");
					hql.append(" many.PURCHASEORDER_ID = toone.id ");
					hql.append(" AND toone.STATUS = '3' ");
					hql.append(" AND toone.SUPPLIER_ID is not null and toone.SUPPLIER_ID <> '' and toone.SUPPLIER_ID in " + setSupplierID + " ");
					hql.append(" and many.ITEMCODE <> '' and many.ITEMCODE IS NOT NULL and many.ITEMname <> '' and many.ITEMname IS NOT NULL ");
					ArrayList<String> objTimeArr = (ArrayList<String>) HQLhsMap.get("timeArr");
					hql.append(" and toone.CREATETIME >= " + MyTool.StringUseToSql(objTimeArr.get(0)));
					hql.append(" and toone.CREATETIME <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size() - 1))));
					hql.append(" and many.ITEMCODE not in ( ");
					hql.append(" SELECT ");
					hql.append(" many.ITEMCODE ");
					hql.append(" FROM ");
					hql.append(" pur_orderlineitem many ");
					hql.append(" INNER JOIN pur_order toone ");
					hql.append(" ON ");
					hql.append(" many.PURCHASEORDER_ID = toone.id ");
					hql.append(" AND toone.STATUS = '3' ");
					hql.append(" AND toone.SUPPLIER_ID is not null and toone.SUPPLIER_ID <> '' and toone.SUPPLIER_ID in " + setSupplierID + " ");
					hql.append(" and many.ITEMCODE <> '' and many.ITEMCODE IS NOT NULL and many.ITEMname <> '' and many.ITEMname IS NOT NULL ");
					hql.append(" and toone.CREATETIME < " + MyTool.StringUseToSql(objTimeArr.get(0)));
					hql.append(" GROUP BY ");
					hql.append(" many.ITEMCODE ");
					hql.append(" ) ");
					String selectName = getDecodeRequestParameter("selectName");
					if (StringUtils.isNotEmpty(selectName)) {
						hql.append(" and many.itemName like '%" + selectName.trim() + "%' ");
					}
					String phone = getDecodeRequestParameter("phone");
					if (StringUtils.isNotEmpty(phone)) {
						hql.append(" and many.itemCode like '%" + phone.trim() + "%' ");
					}
					hql.append(" GROUP BY ");
					hql.append(" many.ITEMCODE ");
				}
				Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, hql.toString(), params);
				renderDataTable(pager);
			}
			if (stateInt == 41) {
				String conditionStr = MyTool.analysisJsonStringFive(changeSQL);
				if (conditionStr.startsWith("SupplierBusinessUi{")) {
					Map<String, Object> params = new HashMap<String, Object>();
					Pager pager = getPager();
					params.put("status," + SearchCondition.EQUAL, "3");
					String timeStr = MyTool.analysisJsonStringFive(conditionStr);
					List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
					if (timeArr != null && timeArr.size() > 0) {
						String startTime = timeArr.get(0);
						String endTime = timeArr.get(timeArr.size() - 1);
						params.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
					}
					if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
						pager.setOrderBy("desc");
						pager.setOrderField("createTime");
					}
					String supplierName = getDecodeRequestParameter("selectName");
					if (StringUtils.isNotEmpty(supplierName)) {
						params.put("name," + SearchCondition.ANYLIKE, supplierName.trim());
					}
					pager = vixntBaseService.findPagerByHqlConditions(pager, Supplier.class, params);
					renderDataTable(pager);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询不同的的列表 数据2
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void queryDataToListTwo() {
		try {
			String changeSQL = getDecodeRequestParameter("changeSQL");
			String setSupplierID = queryDataService.queryDifferentIDsAbcd(getDecodeRequestParameter("setSupplierID"));

			StringBuffer hql = new StringBuffer();
			Map<String, Object> HQLhsMap = new HashMap<String, Object>();

			int stateInt = -1;
			if (StringUtils.isNotEmpty(changeSQL)) {
				if (changeSQL.startsWith("JfYr7D{")) {// 查询'本月新增客户'
					stateInt = 50;
				}
			}
			if (stateInt == 50) {
				Pager myPager = this.getPager();
				String conditionStr = MyTool.analysisJsonStringFive(changeSQL);
				if (conditionStr.startsWith("purchaseNewAddCustomUixu{")) {// 查询'本月新增客户'
					String timeStr = MyTool.analysisJsonStringFive(conditionStr);
					List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
					HQLhsMap.put("timeArr", timeArr);
					hql = new StringBuffer();
					hql.append(" SELECT ");
					hql.append(" toone.channelDistributor_id as khid ");
					hql.append(" FROM ");
					hql.append(" pur_order toone ");
					hql.append(" where ");
					hql.append(" toone.STATUS = '3' ");
					hql.append(" AND toone.SUPPLIER_ID is not null and toone.SUPPLIER_ID <> '' and toone.SUPPLIER_ID in " + setSupplierID + " ");
					hql.append(" and toone.channelDistributor_id <> '' and toone.channelDistributor_id IS NOT NULL ");
					ArrayList<String> objTimeArr = (ArrayList<String>) HQLhsMap.get("timeArr");
					hql.append(" and toone.CREATETIME >= " + MyTool.StringUseToSql(objTimeArr.get(0)));
					hql.append(" and toone.CREATETIME <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size() - 1))));
					hql.append(" and toone.channelDistributor_id not in ( ");
					hql.append(" SELECT ");
					hql.append(" DISTINCT(toone.channelDistributor_id) ");
					hql.append(" FROM ");
					hql.append(" pur_order toone ");
					hql.append(" where ");
					hql.append(" toone.STATUS = '3' ");
					hql.append(" AND toone.SUPPLIER_ID is not null and toone.SUPPLIER_ID <> '' and toone.SUPPLIER_ID in " + setSupplierID + " ");
					hql.append(" and toone.channelDistributor_id <> '' and toone.channelDistributor_id IS NOT NULL ");
					hql.append(" and toone.CREATETIME < " + MyTool.StringUseToSql(objTimeArr.get(0)));
					hql.append(" ) ");
					hql.append(" GROUP BY ");
					hql.append(" toone.channelDistributor_id ");
					Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, hql.toString(), params);
					List resultList = pager.getResultList();
					for (int x = 0; x < resultList.size(); x++) {
						HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
						String channelDistributorid = objectMap.get("khid").toString();
						ChannelDistributor channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, channelDistributorid);
						objectMap.put("cname", (channelDistributor.getName() == null ? "" : channelDistributor.getName() + ""));

						Map<String, Object> hsMap = new HashMap<String, Object>();
						hsMap.put("setSupplierID", setSupplierID);
						hsMap.put("objTimeArr", objTimeArr);
						hsMap.put("channelDistributorid", channelDistributorid);
						hsMap.put("controlSQL", "goodsNumMeyBykhidUiiuk");// goodsNumMeyBykhidUiiuk
																			// 根据和客户id查询采购商品数量和金额
						Double cnum = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
						Double cmey = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("doubleInnerTwo");

						objectMap.put("cnum", cnum);
						objectMap.put("cmey", cmey);
						resultList.set(x, objectMap);
					}
					pager.setResultList(resultList);
					renderDataTable(pager);
				} else if (conditionStr.startsWith("purchaseTodayCustomUicu{")) {// 查询'今日客户数量'
					String timeStr = MyTool.analysisJsonStringFive(conditionStr);
					List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
					HQLhsMap.put("timeArr", timeArr);
					hql = new StringBuffer();
					hql.append(" SELECT ");
					hql.append(" toone.channelDistributor_id as khid ");
					hql.append(" FROM ");
					hql.append(" pur_order toone ");
					hql.append(" where ");
					hql.append(" toone.STATUS = '3' AND toone.channelDistributor_id <> '' and toone.channelDistributor_id IS NOT NULL");
					hql.append(" AND toone.SUPPLIER_ID is not null and toone.SUPPLIER_ID <> '' and toone.SUPPLIER_ID in " + setSupplierID + " ");
					ArrayList<String> objTimeArr = (ArrayList<String>) HQLhsMap.get("timeArr");
					hql.append(" and toone.CREATETIME >= " + MyTool.StringUseToSql(objTimeArr.get(0)));
					hql.append(" and toone.CREATETIME <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size() - 1))));
					hql.append(" GROUP BY ");
					hql.append(" toone.channelDistributor_id ");
					Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, hql.toString(), params);
					List resultList = pager.getResultList();
					for (int x = 0; x < resultList.size(); x++) {
						HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
						String channelDistributorid = objectMap.get("khid").toString();
						ChannelDistributor channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, channelDistributorid);
						objectMap.put("cname", (channelDistributor.getName() == null ? "" : channelDistributor.getName() + ""));
						Map<String, Object> hsMap = new HashMap<String, Object>();
						hsMap.put("setSupplierID", setSupplierID);
						hsMap.put("objTimeArr", objTimeArr);
						hsMap.put("channelDistributorid", channelDistributorid);
						hsMap.put("controlSQL", "goodsNumMeyBykhidUiiuk");// goodsNumMeyBykhidUiiuk
																			// 根据和客户id查询采购商品数量和金额
						Double cnum = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("totalDouble");
						Double cmey = (Double) queryDataService.findDataRtOneUajoop(hsMap).get("doubleInnerTwo");

						objectMap.put("cnum", cnum);
						objectMap.put("cmey", cmey);
						resultList.set(x, objectMap);
					}
					pager.setResultList(resultList);
					renderDataTable(pager);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店采购订单 **/
	public void queryDataPurchaseOrderA() {
		try {
			String setSupplierID = queryDataService.queryDifferentIDsAbcd(getDecodeRequestParameter("setSupplierID"));
			setSupplierID = new String(setSupplierID.replaceAll("'", ""));
			setSupplierID = new String(setSupplierID.replaceAll("\\(", ""));
			setSupplierID = new String(setSupplierID.replaceAll("\\)", ""));
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String conditionStr = MyTool.analysisJsonStringFive(getDecodeRequestParameter("changeSQL"));
			String timeStr = MyTool.analysisJsonStringFive(conditionStr);
			List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
			params.put("status," + SearchCondition.EQUAL, "3");// 3正式的
			if (timeArr != null && timeArr.size() > 0) {
				String startTime = timeArr.get(0);
				String endTime = timeArr.get(timeArr.size() - 1);
				params.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
			}
			String sName = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(sName)) {
				params.put("channelDistributor.name," + SearchCondition.ANYLIKE, sName.trim());
			}
			String sPhone = getDecodeRequestParameter("phone");
			if (StringUtils.isNotEmpty(sPhone)) {
				params.put("code," + SearchCondition.ANYLIKE, sPhone.trim());
			}
			params.put("type," + SearchCondition.EQUAL, "0");// 订单类型0,门店采购订单;1,总部采购订单;
			params.put("supplier.id," + SearchCondition.IN, setSupplierID);
			pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 总部采购订单 **/
	public void queryDataPurchaseOrderB() {
		try {
			String setSupplierID = queryDataService.queryDifferentIDsAbcd(getDecodeRequestParameter("setSupplierID"));
			setSupplierID = new String(setSupplierID.replaceAll("'", ""));
			setSupplierID = new String(setSupplierID.replaceAll("\\(", ""));
			setSupplierID = new String(setSupplierID.replaceAll("\\)", ""));
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String conditionStr = MyTool.analysisJsonStringFive(getDecodeRequestParameter("changeSQL"));
			String timeStr = MyTool.analysisJsonStringFive(conditionStr);
			List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
			params.put("status," + SearchCondition.EQUAL, "3");// 3正式的
			if (timeArr != null && timeArr.size() > 0) {
				String startTime = timeArr.get(0);
				String endTime = timeArr.get(timeArr.size() - 1);
				params.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
			}
			String sName = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(sName)) {
				params.put("purchasePerson," + SearchCondition.ANYLIKE, sName.trim());
			}
			String sPhone = getDecodeRequestParameter("phone");
			if (StringUtils.isNotEmpty(sPhone)) {
				params.put("code," + SearchCondition.ANYLIKE, sPhone.trim());
			}
			params.put("type," + SearchCondition.EQUAL, "1");// 订单类型0,门店采购订单;1,总部采购订单;
			params.put("supplier.id," + SearchCondition.IN, setSupplierID);
			pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTopNum() {
		return topNum;
	}
	public void setTopNum(String topNum) {
		this.topNum = topNum;
	}
	public String getSetSupplierID() {
		return setSupplierID;
	}
	public void setSetSupplierID(String setSupplierID) {
		this.setSupplierID = setSupplierID;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public String getChangeSQL() {
		return changeSQL;
	}
	public void setChangeSQL(String changeSQL) {
		this.changeSQL = changeSQL;
	}
	public String getFromPage() {
		return fromPage;
	}
	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}
}
