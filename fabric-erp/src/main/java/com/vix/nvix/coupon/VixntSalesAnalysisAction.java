package com.vix.nvix.coupon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.vix.common.properties.util.MyTool;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.vixdata.dataappinterface.IDataAccuracy;
import com.vix.core.web.Pager;
import com.vix.crm.business.hql.PurchasingBehaviorHqlProvider;
import com.vix.crm.business.service.IPurchasingBehaviorService;
import com.vix.crm.business.vo.CombinedSalesGoods;
import com.vix.crm.business.vo.IdAndTitleVo;
import com.vix.crm.business.vo.SalesAnalysis;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.personaloffice.service.IQueryDataService;
import com.vix.sales.order.entity.SaleOrderItem;

@Controller
@Scope("prototype")
public class VixntSalesAnalysisAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	@Resource(name = "purchasingBehaviorHqlProvider")
	private PurchasingBehaviorHqlProvider purchasingBehaviorHqlProvider;
	@Autowired
	private IPurchasingBehaviorService purchasingBehaviorService;
	@Autowired
	private IDataAccuracy dataAccuracy;
	private List<CombinedSalesGoods> combinedSalesGoodsList;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private IQueryDataService queryDataService;
	@Override
	public String goList() {
		try {
			Map<String, Object> p = new HashMap<String, Object>();
			// 通过条件获取相应数据
			String dateType = getRequestParameter("dateType");
			// 获取某一天的数据
			String date = getRequestParameter("date");
			if (dateType != null && !"".equals(dateType)) {
				p.putAll(getDateMap(dateType, date));
			} else if (date != null && !"".equals(date)) {
				p.putAll(getDate(date));
			} else {
				// 默认获取本周的数据
				p.putAll(getWeekDay());
			}
			// 通过条件获取相应数据
			StringBuilder hql = purchasingBehaviorHqlProvider.findIdAndTitleVoList(p);
			List<IdAndTitleVo> idAndTitleVoList = purchasingBehaviorService.findIdAndTitleVoList(hql.toString());
			if (idAndTitleVoList != null && idAndTitleVoList.size() > 0) {
				combinedSalesGoodsList = new ArrayList<CombinedSalesGoods>();
				for (IdAndTitleVo idAndTitleVo : idAndTitleVoList) {
					if (idAndTitleVo != null) {
						String[] titles = idAndTitleVo.getTitles().split(",");
						if (titles.length == 2) {
							CombinedSalesGoods combinedSalesGoods = new CombinedSalesGoods();
							if (titles[0] != null) {
								combinedSalesGoods.setAgoodsName(titles[0]);
								List<SaleOrderItem> saleOrderItemList = purchasingBehaviorService.findAllByEntityClassAndAttribute(SaleOrderItem.class, "title", titles[0]);
								combinedSalesGoods.setApeoplenumber(Double.valueOf(saleOrderItemList.size()));
							}
							String[] ids = idAndTitleVo.getIds().split(",");
							combinedSalesGoods.setBgoodsName(titles[1]);
							combinedSalesGoods.setBpeoplenumber(Double.valueOf(ids.length));
							combinedSalesGoods.setPurchaseJointRate(Double.valueOf(dataAccuracy.getAmountDecimal(combinedSalesGoods.getBpeoplenumber() / combinedSalesGoods.getApeoplenumber() * 100)));
							combinedSalesGoodsList.add(combinedSalesGoods);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	// 成交金额
	public void goShowData() {
		try {
			String str = "";
			// 获取当天的数据
			Map<String, Object> p = new HashMap<String, Object>();
			// 通过条件获取相应数据
			String dateType = getRequestParameter("dateType");
			// 获取某一天的数据
			String date = getRequestParameter("date");
			if (dateType != null && !"".equals(dateType)) {
				p.putAll(getDateMap(dateType, date));
			} else if (date != null && !"".equals(date)) {
				p.putAll(getDate(date));
			} else {
				// 默认获取本周的数据
				p.putAll(getDate(sdf.format(new Date())));
			}
			// 通过条件获取相应数据
			StringBuilder hql = purchasingBehaviorHqlProvider.findSalesAnalysis(p);
			SalesAnalysis salesAnalysis = purchasingBehaviorService.findSalesAnalysis(hql.toString());
			// 获取昨天的数据
			Map<String, Object> map = new HashMap<String, Object>();
			if (date != null && !"".equals(date)) {
				map.putAll(getSpecifiedDayBefore(sdf.parse(date)));
			} else {
				map.putAll(getSpecifiedDayBefore(new Date()));
			}
			StringBuilder sql1 = purchasingBehaviorHqlProvider.findSalesAnalysis(map);
			SalesAnalysis salesAnalysis1 = purchasingBehaviorService.findSalesAnalysis(sql1.toString());
			if (salesAnalysis != null) {
				if (salesAnalysis.getAmount() != null) {
					str += "¥  " + dataAccuracy.getAmountDecimal(salesAnalysis.getAmount()) + "\n";
					str += "vs 前一天" + dataAccuracy.getAmountDecimal((salesAnalysis1.getAmount() - salesAnalysis.getAmount()) / salesAnalysis.getAmount()) + "\n";
				} else {
					str += "¥ 0 " + "\n";
					str += "vs 前一天 0";
				}
			}
			if (!"".equals(str)) {
				renderText(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSearch() {
		return "goSearch";
	}

	// 成交订单数
	public void goBuyNumData() {
		try {
			String str = "";
			// 获取当天的数据
			Map<String, Object> p = new HashMap<String, Object>();
			// 通过条件获取相应数据
			String dateType = getRequestParameter("dateType");
			// 获取某一天的数据
			String date = getRequestParameter("date");
			if (dateType != null && !"".equals(dateType)) {
				p.putAll(getDateMap(dateType, date));
			} else if (date != null && !"".equals(date)) {
				p.putAll(getDate(date));
			} else {
				// 默认获取本周的数据
				p.putAll(getDate(sdf.format(new Date())));
			}
			// 通过条件获取相应数据
			StringBuilder hql = purchasingBehaviorHqlProvider.findSalesAnalysis(p);
			SalesAnalysis salesAnalysis = purchasingBehaviorService.findSalesAnalysis(hql.toString());
			// 获取昨天的数据
			Map<String, Object> map = new HashMap<String, Object>();
			if (date != null && !"".equals(date)) {
				map.putAll(getSpecifiedDayBefore(sdf.parse(date)));
			} else {
				map.putAll(getSpecifiedDayBefore(new Date()));
			}
			StringBuilder sql1 = purchasingBehaviorHqlProvider.findSalesAnalysis(map);
			SalesAnalysis salesAnalysis1 = purchasingBehaviorService.findSalesAnalysis(sql1.toString());
			if (salesAnalysis != null && salesAnalysis1 != null) {
				if (salesAnalysis.getOrderNum() != null) {
					str += salesAnalysis.getOrderNum() + "\n";
					str += "vs 前一天" + dataAccuracy.getAmountDecimal((salesAnalysis1.getOrderNum() - salesAnalysis.getOrderNum()) / salesAnalysis.getOrderNum()) + "\n";
				} else {
					str += "0" + "\n";
					str += "vs 前一天 0";
				}
			}
			if (!"".equals(str)) {
				renderText(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 回头购买订单数
	public void goBuyMoreData() {
		try {
			String str = "";
			// 获取当天的数据
			Map<String, Object> p = new HashMap<String, Object>();
			// 通过条件获取相应数据
			String dateType = getRequestParameter("dateType");
			// 获取某一天的数据
			String date = getRequestParameter("date");
			if (dateType != null && !"".equals(dateType)) {
				p.putAll(getDateMap(dateType, date));
			} else if (date != null && !"".equals(date)) {
				p.putAll(getDate(date));
			} else {
				// 默认获取本周的数据
				p.putAll(getDate(sdf.format(new Date())));
			}
			// 通过条件获取相应数据
			StringBuilder hql = purchasingBehaviorHqlProvider.findBuyMoreData(p);
			SalesAnalysis salesAnalysis = purchasingBehaviorService.findSalesAnalysis(hql.toString());
			// 获取昨天的数据
			Map<String, Object> map = new HashMap<String, Object>();
			if (date != null && !"".equals(date)) {
				map.putAll(getSpecifiedDayBefore(sdf.parse(date)));
			} else {
				map.putAll(getSpecifiedDayBefore(new Date()));
			}
			StringBuilder sql1 = purchasingBehaviorHqlProvider.findBuyMoreData(map);
			SalesAnalysis salesAnalysis1 = purchasingBehaviorService.findSalesAnalysis(sql1.toString());
			if (salesAnalysis != null && salesAnalysis1 != null) {
				if (salesAnalysis.getOrderNum() != null) {
					str += salesAnalysis.getOrderNum() + "\n";
					str += "vs 前一天" + dataAccuracy.getAmountDecimal((salesAnalysis1.getOrderNum() - salesAnalysis.getOrderNum()) / salesAnalysis.getOrderNum()) + "\n";
				} else {
					str += "0" + "\n";
					str += "vs 前一天 0";
				}
			}
			if (!"".equals(str)) {
				renderText(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 首次购买订单数
	public void goBuyOneData() {
		try {
			String str = "";
			// 获取当天的数据
			Map<String, Object> p = new HashMap<String, Object>();
			// 通过条件获取相应数据
			String dateType = getRequestParameter("dateType");
			// 获取某一天的数据
			String date = getRequestParameter("date");
			if (dateType != null && !"".equals(dateType)) {
				p.putAll(getDateMap(dateType, date));
			} else if (date != null && !"".equals(date)) {
				p.putAll(getDate(date));
			} else {
				// 默认获取本周的数据
				p.putAll(getDate(sdf.format(new Date())));
			}
			// 通过条件获取相应数据
			StringBuilder hql = purchasingBehaviorHqlProvider.findBuyOneData(p);
			SalesAnalysis salesAnalysis = purchasingBehaviorService.findSalesAnalysis(hql.toString());
			// 获取昨天的数据
			Map<String, Object> map = new HashMap<String, Object>();
			if (date != null && !"".equals(date)) {
				map.putAll(getSpecifiedDayBefore(sdf.parse(date)));
			} else {
				map.putAll(getSpecifiedDayBefore(new Date()));
			}
			StringBuilder sql1 = purchasingBehaviorHqlProvider.findBuyOneData(map);
			SalesAnalysis salesAnalysis1 = purchasingBehaviorService.findSalesAnalysis(sql1.toString());
			if (salesAnalysis != null && salesAnalysis1 != null) {
				if (salesAnalysis.getOrderNum() != null && salesAnalysis1.getOrderNum() != null) {
					str += salesAnalysis.getOrderNum() + "\n";
					str += "vs 前一天" + dataAccuracy.getAmountDecimal((salesAnalysis1.getOrderNum() - salesAnalysis.getOrderNum()) / salesAnalysis.getOrderNum()) + "\n";
				} else {
					str += "0" + "\n";
					str += "vs 前一天 0";
				}
			}
			if (!"".equals(str)) {
				renderText(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goOrderPriceData() {
		try {
			String str = "";
			// 获取当天的数据
			Map<String, Object> p = new HashMap<String, Object>();
			// 通过条件获取相应数据
			String dateType = getRequestParameter("dateType");
			// 获取某一天的数据
			String date = getRequestParameter("date");
			if (dateType != null && !"".equals(dateType)) {
				p.putAll(getDateMap(dateType, date));
			} else if (date != null && !"".equals(date)) {
				p.putAll(getDate(date));
			} else {
				// 默认获取本周的数据
				p.putAll(getDate(sdf.format(new Date())));
			}
			// 通过条件获取相应数据
			StringBuilder hql = purchasingBehaviorHqlProvider.findSalesAnalysis(p);
			SalesAnalysis salesAnalysis = purchasingBehaviorService.findSalesAnalysis(hql.toString());
			// 获取昨天的数据
			Map<String, Object> map = new HashMap<String, Object>();
			if (date != null && !"".equals(date)) {
				map.putAll(getSpecifiedDayBefore(sdf.parse(date)));
			} else {
				map.putAll(getSpecifiedDayBefore(new Date()));
			}
			StringBuilder sql1 = purchasingBehaviorHqlProvider.findSalesAnalysis(map);
			SalesAnalysis salesAnalysis1 = purchasingBehaviorService.findSalesAnalysis(sql1.toString());
			if (salesAnalysis != null) {
				if (salesAnalysis.getOrderPrice() != null) {
					str += "¥  " + dataAccuracy.getAmountDecimal(salesAnalysis.getOrderPrice()) + "\n";
					str += "vs 前一天" + dataAccuracy.getAmountDecimal((salesAnalysis1.getOrderPrice() - salesAnalysis.getOrderPrice()) / salesAnalysis.getOrderPrice()) + "\n";
				} else {
					str += "¥ 0 " + "\n";
					str += "vs 前一天 0";
				}
			}
			if (!"".equals(str)) {
				renderText(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 平均订单件数
	public void goOrderAvgNumData() {
		try {
			String str = "";
			// 获取当天的数据
			Map<String, Object> p = new HashMap<String, Object>();
			// 通过条件获取相应数据
			String dateType = getRequestParameter("dateType");
			// 获取某一天的数据
			String date = getRequestParameter("date");
			if (dateType != null && !"".equals(dateType)) {
				p.putAll(getDateMap(dateType, date));
			} else if (date != null && !"".equals(date)) {
				p.putAll(getDate(date));
			} else {
				// 默认获取本周的数据
				p.putAll(getDate(sdf.format(new Date())));
			}
			// 通过条件获取相应数据
			StringBuilder hql = purchasingBehaviorHqlProvider.findSalesAnalysis(p);
			SalesAnalysis salesAnalysis = purchasingBehaviorService.findSalesAnalysis(hql.toString());
			// 获取昨天的数据
			Map<String, Object> map = new HashMap<String, Object>();
			if (date != null && !"".equals(date)) {
				map.putAll(getSpecifiedDayBefore(sdf.parse(date)));
			} else {
				map.putAll(getSpecifiedDayBefore(new Date()));
			}
			StringBuilder sql1 = purchasingBehaviorHqlProvider.findSalesAnalysis(map);
			SalesAnalysis salesAnalysis1 = purchasingBehaviorService.findSalesAnalysis(sql1.toString());
			if (salesAnalysis != null) {
				if (salesAnalysis.getOrderAvgNum() != null) {
					str += dataAccuracy.getAmountDecimal(salesAnalysis.getOrderAvgNum()) + "\n";
					str += "vs 前一天" + dataAccuracy.getAmountDecimal((salesAnalysis1.getOrderAvgNum() - salesAnalysis.getOrderAvgNum()) / salesAnalysis.getOrderAvgNum()) + "\n";
				} else {
					str += "0" + "\n";
					str += "vs 前一天 0";
				}
			}
			if (!"".equals(str)) {
				renderText(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得指定日期的前一天
	 * 
	 * @param specifiedDay
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getSpecifiedDayBefore(Date specifiedDay) {
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = specifiedDay;
		} catch (Exception e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		map.put("startTime", dayBefore + " 00:00:00");
		map.put("endTime", dayBefore + " 23:59:59");
		return map;
	}

	public Map<String, Object> getDate(String date) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (date != null) {
		} else {
			date = sdf.format(new Date());
		}
		map.put("startTime", date + " 00:00:00");
		map.put("endTime", date + " 23:59:59");
		return map;
	}

	public Map<String, Object> getDateMap(String dateType, String date) {
		Map<String, Object> params = new HashMap<String, Object>();
		if ("d".equals(dateType)) {
			params = getDate(date);
		} else if ("w".equals(dateType)) {
			params = getWeekDay();
		} else if ("m".equals(dateType)) {
			params = getMonthDate();
		}
		return params;
	}

	public Map<String, Object> getWeekDay() {
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		map.put("startTime", sdf.format(cal.getTime()) + " 00:00:00");
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		map.put("endTime", sdf.format(cal.getTime()) + " 23:59:59");
		return map;
	}

	public Map<String, Object> getMonthDate() {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取Calendar
		Calendar calendar = Calendar.getInstance();
		// 设置时间,当前时间不用设置
		// calendar.setTime(new Date());
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		map.put("startTime", sdf.format(calendar.getTime()) + " 00:00:00");
		// 设置日期为本月最大日期
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		map.put("endTime", sdf.format(calendar.getTime()) + " 23:59:59");
		return map;
	}

	/** 会员消费分析 '数据块'返回json */
	public void consumptionAnalysis() {
		try {
			String todayStr = "";
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			ArrayList<String> timeArr = new ArrayList<String>();
			if (endTime.equals("Today")) {
				timeArr.add(MyTool.getTodayBy_yyyyMMdd());// 查询今天
				hsMap.put("timeArr", timeArr);
				todayStr = "今日";
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
				hsMap.put("timeArr", timeArr);
				String todayString = MyTool.getTodayBy_yyyyMMdd();
				if (todayString.equals(startTime) && todayString.equals(endTime)) {
					todayStr = "今日";
				}
			}
			/**
			 * 支付方式 查询来自于： RequireGoodsOrder 的 private String payType;//#支付方式
			 * 1,现金; ;3,银行卡;4,微信;5,支付宝;
			 */
			/*
			 * 今日消费总金额//Total 今日现金消费// 1 今日微信消费// 4 Cash 现金 ，WeChat 微信 ，Alipay
			 * 支付宝 ，BankCard银行卡 ,MemberCard 会员卡也就是余额 ，All 全部 今日支付宝消费// 5
			 * 今日其他消费//
			 */
			hsMap.put("conditionSQL", "   "); // and 1=1 消费总金额
			Double Total = (Double) queryDataService.findConsumptionAnalysisJsonA(hsMap).get("totalDouble");

			hsMap.put("conditionSQL", " and payType in('1') "); // Cash 现金
			Double Cash = (Double) queryDataService.findConsumptionAnalysisJsonA(hsMap).get("totalDouble");

			hsMap.put("conditionSQL", " and payType in('4') "); // and payType
																// in('4')
																// WeChat 微信
			Double WeChat = (Double) queryDataService.findConsumptionAnalysisJsonA(hsMap).get("totalDouble");

			hsMap.put("conditionSQL", " and payType in('5') "); // and payType
																// in('1')
																// Alipay 支付宝
			Double Alipay = (Double) queryDataService.findConsumptionAnalysisJsonA(hsMap).get("totalDouble");
			Double Other = Total - Cash - WeChat - Alipay;// 其他消费
			StringBuffer sbJson = new StringBuffer();
			sbJson.append("{");
			sbJson.append("\"Total\":" + Total );
			sbJson.append(",\"Cash\":" + Cash );
			sbJson.append(",\"WeChat\":" + WeChat );
			sbJson.append(",\"Alipay\":" + Alipay );
			sbJson.append(",\"Other\":" + Other );
			sbJson.append(",\"todayStr\":" + MyTool.StringJsonReturn(todayStr) );
			sbJson.append("}");
			renderJson(sbJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//
	/** 会员消费分析 '视图A'返回json 会员消费方式分析 */
	@SuppressWarnings("unchecked")
	public void consumptionAnalysisViewA() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			ArrayList<String> timeArr = new ArrayList<String>();
			if (endTime.equals("Today")) {
				timeArr.add(MyTool.getTodayBy_yyyyMMdd());// 查询今天
				hsMap.put("timeArr", timeArr);
			} else if (endTime.equals("Day30")) {
				timeArr = MyTool.getNewlyDateArrayByInt_ZX(-29);// 改需求：最近30日
				hsMap.put("timeArr", timeArr);
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
				hsMap.put("timeArr", timeArr);
			}
			// 先查询'现金消费'/** 支付方式 查询来自于： RequireGoodsOrder 的 private String
			// payType;//#支付方式 1,现金; ;3,银行卡;4,微信;5,支付宝; */
			hsMap.put("conditionSQL", " and payType in('1') ");
			ArrayList<String> CashValueArr = (ArrayList<String>) queryDataService.findConsumptionAnalysisViewA(hsMap).get("valueArr");

			hsMap.put("conditionSQL", " and payType in('4') ");
			ArrayList<String> WeChatValueArr = (ArrayList<String>) queryDataService.findConsumptionAnalysisViewA(hsMap).get("valueArr");

			hsMap.put("conditionSQL", " and payType in('5') ");
			ArrayList<String> AlipayValueArr = (ArrayList<String>) queryDataService.findConsumptionAnalysisViewA(hsMap).get("valueArr");

			hsMap.put("conditionSQL", " and payType in('3') ");
			ArrayList<String> BankCardValueArr = (ArrayList<String>) queryDataService.findConsumptionAnalysisViewA(hsMap).get("valueArr");

			hsMap.put("conditionSQL", " and ( payType not in('1','3','4','5') or payType is null ) ");
			ArrayList<String> OtherValueArr = (ArrayList<String>) queryDataService.findConsumptionAnalysisViewA(hsMap).get("valueArr");

			ArrayList<String> TotalValueArr = MyTool.calculationArrayStringToTotal(CashValueArr, WeChatValueArr, AlipayValueArr, BankCardValueArr, OtherValueArr);

			ArrayList<String> timeStr = MyTool.substringDateStrTwo_Array(timeArr);
			Gson gosn = new Gson();
			
			StringBuffer sbJson = new StringBuffer();
			sbJson.append("{");
			sbJson.append("\"timeStr\":" + gosn.toJson(timeStr) );
			sbJson.append(",\"Cash\":" + CashValueArr.toString() );
			sbJson.append(",\"WeChat\":" + WeChatValueArr.toString() );
			sbJson.append(",\"Alipay\":" + AlipayValueArr.toString() );
			sbJson.append(",\"Other\":" + OtherValueArr.toString() );
			sbJson.append(",\"BankCard\":" + BankCardValueArr.toString() );
			sbJson.append(",\"Total\":" + TotalValueArr.toString() );
			sbJson.append("}");
			renderJson(sbJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员消费排行 '视图B'返回json 会员消费方式分析 */
	public void consumptionAnalysisViewB() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			ArrayList<String> timeArr = new ArrayList<String>();
			if (endTime.equals("Today")) {
				timeArr.add(MyTool.getTodayBy_yyyyMMdd());// 查询今天
				hsMap.put("timeArr", timeArr);
			} else if (endTime.equals("Day30")) {
				timeArr = MyTool.getNewlyDateArrayByInt_ZX(-29);// 改需求：最近30日
				hsMap.put("timeArr", timeArr);
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
				hsMap.put("timeArr", timeArr);
			}
			hsMap.put("topNum", topNum);
			String jsonStrReturn = (String) queryDataService.findConsumptionAnalysisViewB(hsMap).get("jsonStrReturn");
			renderJson(jsonStrReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员消费排行 '视图C'返回json 最近30日商品销量排行Top10 */
	public void consumptionAnalysisViewC() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			ArrayList<String> timeArr = new ArrayList<String>();
			if (endTime.equals("Today")) {
				timeArr.add(MyTool.getTodayBy_yyyyMMdd());// 查询今天
				hsMap.put("timeArr", timeArr);
			} else if (endTime.equals("Day30")) {
				timeArr = MyTool.getNewlyDateArrayByInt_ZX(-29);// 改需求：最近30日
				hsMap.put("timeArr", timeArr);
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
				hsMap.put("timeArr", timeArr);
			}
			hsMap.put("topNum", topNum);
			String jsonStrReturn = (String) queryDataService.findConsumptionAnalysisViewC(hsMap).get("jsonStrReturn");
			renderJson(jsonStrReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 客户消费明细 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				params.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			Pager myPager = this.getPager();

			StringBuffer fullSql = new StringBuffer();
			String mytitle = getDecodeRequestParameter("mytitle");
			String startTime = getDecodeRequestParameter("startTime");
			String endTime = getDecodeRequestParameter("endTime");
			String mymobilePhone = getDecodeRequestParameter("mymobilePhone");
			if (startTime != null && !"".equals(startTime) && endTime != null && !"".equals(endTime)) {
				if (startTime.contains("-") && endTime.contains("-")) {
					fullSql.append(" SELECT many.customerAccount_id as cid  from DRP_REQUIREGOODSORDER many ");
					if ((mytitle != null && !"".equals(mytitle)) || (mymobilePhone != null && !"".equals(mymobilePhone))) {
						fullSql.append(" INNER JOIN CRM_CUSTOMERACCOUNT toone on many.customerAccount_id = toone.id ");
						if (mytitle != null && !"".equals(mytitle)) {
							fullSql.append(" and toone.name like '%" + mytitle.trim() + "%' ");
						}
						if (mymobilePhone != null && !"".equals(mymobilePhone)) {
							fullSql.append(" and toone.mobilePhone like '%" + mymobilePhone.trim() + "%' ");
						}
					}
					fullSql.append(" where many.customerAccount_id is not null and many.isTemp != '1' and many.type  = '1' ");
					fullSql.append(" and many.TENANTID ='" + params.get("tenantId") + "' ");
					fullSql.append(" and many.COMPANYINNERCODE = '" + params.get("companyInnerCode") + "' ");
					if(params.containsKey("channelDistributorId")){
						fullSql.append(" and many.CHANNELDISTRIBUTOR_ID='"+params.get("channelDistributorId")+"'");  
					}else{
						fullSql.append(" and 1=2 ");
					}
					fullSql.append(" and many.payTime >= " + MyTool.StringUseToSql(startTime));
					fullSql.append(" and many.payTime <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(endTime)));
					fullSql.append(" GROUP BY many.customerAccount_id ");
					fullSql.append(" ORDER BY payTime desc ");
				} else if (startTime.equals("NoTime")) {
					startTime = "";
					endTime = "";
					fullSql.append(" SELECT many.customerAccount_id as cid  from DRP_REQUIREGOODSORDER many ");
					if ((mytitle != null && !"".equals(mytitle)) || (mymobilePhone != null && !"".equals(mymobilePhone))) {
						fullSql.append(" INNER JOIN CRM_CUSTOMERACCOUNT toone on many.customerAccount_id = toone.id ");
						if (mytitle != null && !"".equals(mytitle)) {
							fullSql.append(" and toone.name like '%" + mytitle.trim() + "%' ");
						}
						if (mymobilePhone != null && !"".equals(mymobilePhone)) {
							fullSql.append(" and toone.mobilePhone like '%" + mymobilePhone.trim() + "%' ");
						}
					}
					fullSql.append(" where many.customerAccount_id is not null and many.isTemp != '1' and many.type  = '1' ");
					fullSql.append(" and many.TENANTID ='" + params.get("tenantId") + "' ");
					fullSql.append(" and many.COMPANYINNERCODE = '" + params.get("companyInnerCode") + "' ");
					if(params.containsKey("channelDistributorId")){
						fullSql.append(" and many.CHANNELDISTRIBUTOR_ID='"+params.get("channelDistributorId")+"'");  
					}else{
						fullSql.append(" and 1=2 ");
					}
					fullSql.append(" and many.payTime <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(MyTool.getTodayBy_yyyyMMdd())));
					fullSql.append(" GROUP BY many.customerAccount_id ");
					fullSql.append(" ORDER BY payTime desc ");
				}
			} else {
				fullSql.append(" SELECT customerAccount_id as cid  from DRP_REQUIREGOODSORDER ");
				fullSql.append(" where customerAccount_id is not null and isTemp != '1' and type  = '1'  ");
				fullSql.append(" and payTime is not null ");
				fullSql.append(" and TENANTID ='" + params.get("tenantId") + "' ");
				fullSql.append(" and COMPANYINNERCODE = '" + params.get("companyInnerCode") + "' ");
				if(params.containsKey("channelDistributorId")){
					fullSql.append(" and CHANNELDISTRIBUTOR_ID='"+params.get("channelDistributorId")+"'");  
				}else{
					fullSql.append(" and 1=2 ");
				}
				fullSql.append(" GROUP BY customerAccount_id ");
				fullSql.append(" ORDER BY payTime desc ");
			}

			Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, fullSql.toString(), params);

			List resultList = pager.getResultList();
			for (int x = 0; x < resultList.size(); x++) {
				HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
				String customerID = objectMap.get("cid").toString();
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
				if(params.containsKey("channelDistributorId")){
					hsMap.put("channelDistributorId",params.get("channelDistributorId"));
				}
				if (startTime != null && !"".equals(startTime) && endTime != null && !"".equals(endTime)) {
					hsMap.put("startTime", startTime);
					hsMap.put("endTime", endTime);
				}
				hsMap.put("customerID", customerID);// 客户消费明细(消费总金额)
													// {controlSQL:'selfExtendStringField1'}/**
													// 自定义扩展字段1 */
				hsMap.put("controlSQL", "selfExtendStringField1");
				Double selfExtendStringField1 = (Double) queryDataService.findCustomerConsumptionDetailsA(hsMap).get("totalDouble");
				objectMap.put("selfExtendStringField1", "" + MyTool.roundingTwoDecimal(selfExtendStringField1));

				hsMap.put("controlSQL", "selfExtendStringField2");// 客户消费明细(消费次数)
																	// {controlSQL:'selfExtendStringField2'}/**
																	// 自定义扩展字段2
																	// */
				Double selfExtendStringField2 = (Double) queryDataService.findCustomerConsumptionDetailsA(hsMap).get("totalDouble");
				objectMap.put("selfExtendStringField2", "" + MyTool.getIntFromDouble(selfExtendStringField2));

				Double selfExtendStringField3 = 0.0;
				if (selfExtendStringField2 != 0.0) {// 客户消费明细(消费均价)
													// {controlSQL:'selfExtendStringField3'}/**
													// 自定义扩展字段3 */
					selfExtendStringField3 = selfExtendStringField1 / selfExtendStringField2;
				}
				objectMap.put("selfExtendStringField3", "" + MyTool.roundingTwoDecimal(selfExtendStringField3));

				hsMap.put("controlSQL", "selfExtendStringField4");// 客户消费明细(最近消费时间)
																	// {controlSQL:'selfExtendStringField4'}/**
																	// 自定义扩展字段4
																	// */
				Map<String, Object> objectQuery = queryDataService.findCustomerConsumptionDetailsA(hsMap);
				String selfExtendStringField4 = (String) objectQuery.get("stringResult");
				Double selfExtendStringField5 = (Double) objectQuery.get("totalDouble");
				objectMap.put("selfExtendStringField4", selfExtendStringField4);// 客户消费明细(最近消费时间)
				objectMap.put("selfExtendStringField5", "" + MyTool.roundingTwoDecimal(selfExtendStringField5));// 客户消费明细(最近消费金额)

				hsMap.put("controlSQL", "mobilePhoneANDname");// mobilePhoneANDname
																// 查‘移动电话’和‘名字’
				Map<String, Object> objectQueryB = queryDataService.findCustomerConsumptionDetailsA(hsMap);
				String mobilePhone = (String) objectQueryB.get("stringResult");// 手机号
				String name = (String) objectQueryB.get("stringResultB");// 名字
				objectMap.put("mobilePhone", mobilePhone);
				objectMap.put("name", name);

				resultList.set(x, objectMap);
			}
			pager.setResultList(resultList);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 获得’会员消费分析 ’>客单价视图(每日订单总金额/对应客户人数) **/
	@SuppressWarnings("unchecked")
	public void consumptionAnalysisViewD() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			ArrayList<String> timeArr = new ArrayList<String>();
			if (endTime.equals("Today")) {
				timeArr.add(MyTool.getTodayBy_yyyyMMdd());// 查询今天
				hsMap.put("timeArr", timeArr);
			} else if (endTime.equals("Day30")) {
				timeArr = MyTool.getNewlyDateArrayByInt_ZX(-29);// 改需求：最近30日
				hsMap.put("timeArr", timeArr);
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
				hsMap.put("timeArr", timeArr);
			}
			hsMap.put("controlSQL", "memberOrderTotalMoney");// memberOrderTotalMoney
																// | 会员付款订单总金额
			hsMap.put("myDATE_FORMAT", "'%Y-%m-%d'");// 格式成 年月日
			ArrayList<String> memberOrderTotalMoneyArr = (ArrayList<String>) queryDataService.findFsyqfraConsumptionAnalysisViewD(hsMap).get("valueArr");
			hsMap.put("controlSQL", "orderMemberIdDistinct");// orderMemberIdDistinct
																// |
																// 会员付款订单对应会员人数，去重，Distinct
			ArrayList<String> orderMemberIdDistinctArr = (ArrayList<String>) queryDataService.findFsyqfraConsumptionAnalysisViewD(hsMap).get("valueArr");

			ArrayList<String> perTicketSalesArr = new ArrayList<String>();// perTicketSalesArr
																			// 客单价数组
			for (int x = 0; x < timeArr.size(); x++) {
				perTicketSalesArr.add("0.0");
				Double fm = Double.parseDouble(orderMemberIdDistinctArr.get(x));
				Double fz = Double.parseDouble(memberOrderTotalMoneyArr.get(x));
				Double result = 0.0;
				if (fm != 0) {
					result = MyTool.roundingTwoDecimal(fz / fm);
				}
				perTicketSalesArr.set(x, "" + result);
			}
			ArrayList<String> timeStr = MyTool.substringDateStrTwo_Array(timeArr);
			Gson gosn = new Gson();
			String jsonOnlyFour = "{\"memberOrderTotalMoneyArr\":" + memberOrderTotalMoneyArr.toString() + ",\"orderMemberIdDistinctArr\":" + orderMemberIdDistinctArr.toString() + ",\"perTicketSalesArr\":" + perTicketSalesArr.toString() + ",\"timeStr\":" + gosn.toJson(timeStr) + "}";
			renderJson(jsonOnlyFour);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 智能分析 > 会员消费分析>最近30日订单数 **/
	@SuppressWarnings("unchecked")
	public void consumptionAnalysisViewF() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			ArrayList<String> timeArr = new ArrayList<String>();
			if (endTime.equals("Today")) {
				timeArr.add(MyTool.getTodayBy_yyyyMMdd());// 查询今天
				hsMap.put("timeArr", timeArr);
			} else if (endTime.equals("Day30")) {
				timeArr = MyTool.getNewlyDateArrayByInt_ZX(-29);// 改需求：最近30日
				hsMap.put("timeArr", timeArr);
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
				hsMap.put("timeArr", timeArr);
			}
			hsMap.put("controlSQL", "orderNumberSojn");// orderNumberSojn |
														// 每日订单数
			hsMap.put("myDATE_FORMAT", "'%Y-%m-%d'");// 格式成 年月日
			ArrayList<String> memberOrderPassengersArr = (ArrayList<String>) queryDataService.findFsycfaaMemberAnalysisJsonC(hsMap).get("valueArr");
			ArrayList<String> timeStr = MyTool.substringDateStrTwo_Array(timeArr);
			Gson gosn = new Gson();
			String jsonOnlyTwo = "{\"memberOrderPassengersArr\":" + memberOrderPassengersArr.toString() + ",\"timeStr\":" + gosn.toJson(timeStr) + "}";
			renderJson(jsonOnlyTwo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<CombinedSalesGoods> getCombinedSalesGoodsList() {
		return combinedSalesGoodsList;
	}

	public void setCombinedSalesGoodsList(List<CombinedSalesGoods> combinedSalesGoodsList) {
		this.combinedSalesGoodsList = combinedSalesGoodsList;
	}

	private String conditionSQL;
	public String getConditionSQL() {
		return conditionSQL;
	}
	public void setConditionSQL(String conditionSQL) {
		this.conditionSQL = conditionSQL;
	}
	private String controlSQL;
	public String getControlSQL() {
		return controlSQL;
	}
	public void setControlSQL(String controlSQL) {
		this.controlSQL = controlSQL;
	}
	private String topNum;
	public String getTopNum() {
		return topNum;
	}
	public void setTopNum(String topNum) {
		this.topNum = topNum;
	}
	private String startTime;
	private String endTime;
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

}
