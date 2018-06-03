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

import com.vix.common.properties.util.MyTool;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.crm.business.controller.CategoryAnalysisController;
import com.vix.crm.business.hql.PurchasingBehaviorHqlProvider;
import com.vix.crm.business.service.IPurchasingBehaviorService;
import com.vix.crm.business.vo.GoodsSaleInformation;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.personaloffice.service.IQueryDataService;

@Controller
@Scope("prototype")
public class VixntCategoryAnalysisAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CategoryAnalysisController categoryAnalysisController;
	@Resource(name = "purchasingBehaviorHqlProvider")
	private PurchasingBehaviorHqlProvider purchasingBehaviorHqlProvider;
	@Autowired
	private IPurchasingBehaviorService purchasingBehaviorService;
	@Autowired
	private IQueryDataService queryDataService;
	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy年-MM月");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
	static SimpleDateFormat msdf = new SimpleDateFormat("MM");
	/**
	 * 店铺
	 */
	private List<ChannelDistributor> channelDistributorList;
	private List<GoodsSaleInformation> goodsSaleInformationList;

	@Override
	public String goList() {
		try {
			Map<String, Object> p = getParams();
			p.put("type," + SearchCondition.ANYLIKE, "5");
			channelDistributorList = categoryAnalysisController.doListChannelDistributorList(p);

			// 获取店铺
			Map<String, Object> params = new HashMap<String, Object>();
			// 获取某一天的数据
			String channelDistributorId = getRequestParameter("channelDistributorId");
			if (StringUtils.isNotEmpty(channelDistributorId) && !"-1".equals(channelDistributorId)) {
				params.put("channelDistributorId", channelDistributorId);
			}
			String date = getRequestParameter("date");
			String weekday = getRequestParameter("weekday");
			String monthdate = getRequestParameter("monthdate");
			String startdate = getRequestParameter("startdate");
			String enddate = getRequestParameter("enddate");
			String dateType = getRequestParameter("dateType");
			if (dateType != null && !"".equals(dateType)) {
				if ("d".equals(dateType)) {
					// 天
					if (date != null && !"".equals(date)) {
						params.putAll(getDate(date));
					}
				} else if ("w".equals(dateType)) {
					// 周
					if (weekday != null && !"".equals(weekday)) {
						String weekdays[] = weekday.split("-");
						params = getWeekDay(weekdays[0], weekdays[1]);
					}
				} else if ("m".equals(dateType)) {
					// 月
					if (monthdate != null && !"".equals(monthdate)) {
						String monthdates[] = monthdate.split("-");
						params = getMonthDate(monthdates[0], monthdates[1]);
					}
				} else if ("b".equals(dateType)) {
					// 时间段
					if (startdate != null && !"".equals(startdate) && enddate != null && !"".equals(enddate)) {
						params = getBetweenDate(startdate, enddate);
					}
				}
			} else {
				// 默认获取本月的数据
				params.putAll(getMonthDate(ysdf.format(new Date()), msdf.format(new Date())));
			}
			StringBuilder hql = purchasingBehaviorHqlProvider.findGoodsSaleInformationList(params);
			goodsSaleInformationList = purchasingBehaviorService.findGoodsSaleInformationList(hql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
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

	public Map<String, Object> getWeekDay(String yeardate, String weekday) {

		Map<String, Object> map = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(yeardate));
		cal.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(weekday));
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		map.put("startTime", sdf.format(cal.getTime()) + " 00:00:00");
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		map.put("endTime", sdf.format(cal.getTime()) + " 23:59:59");
		return map;
	}

	public Map<String, Object> getMonthDate(String yeardate, String monthdate) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取Calendar
		Calendar calendar = Calendar.getInstance();
		// 设置时间,当前时间不用设置
		calendar.set(Calendar.YEAR, Integer.parseInt(yeardate));
		calendar.set(Calendar.MONTH, Integer.parseInt(monthdate) - 1);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		map.put("startTime", sdf.format(calendar.getTime()) + " 00:00:00");
		// 设置日期为本月最大日期
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		map.put("endTime", sdf.format(calendar.getTime()) + " 23:59:59");
		return map;
	}

	/**
	 * 获取指定时间段的开始,结束日期
	 * 
	 * @param startdate
	 * @param enddate
	 * @return
	 */
	public Map<String, Object> getBetweenDate(String startdate, String enddate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startdate + " 00:00:00");
		map.put("endTime", enddate + " 23:59:59");
		return map;
	}

	/*
	 * public Map<String, Object> getDate(String date) { Map<String, Object> map
	 * = new HashMap<String, Object>(); if (date != null) { } else { date =
	 * sdf.format(new Date()); } map.put("startTime", date + " 00:00:00");
	 * map.put("endTime", date + " 23:59:59"); return map; }
	 * 
	 * public Map<String, Object> getWeekDay() { Map<String, Object> map = new
	 * HashMap<String, Object>(); Calendar cal = Calendar.getInstance();
	 * cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
	 * map.put("startTime", sdf.format(cal.getTime()) + " 00:00:00");
	 * //这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天 cal.set(Calendar.DAY_OF_WEEK,
	 * Calendar.SUNDAY); //增加一个星期，才是我们中国人理解的本周日的日期
	 * cal.add(Calendar.WEEK_OF_YEAR, 1); map.put("endTime",
	 * sdf.format(cal.getTime()) + " 23:59:59"); return map; }
	 * 
	 * public Map<String, Object> getMonthDate() { Map<String, Object> map = new
	 * HashMap<String, Object>(); // 获取Calendar Calendar calendar =
	 * Calendar.getInstance(); // 设置时间,当前时间不用设置 // calendar.setTime(new Date());
	 * calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
	 * map.put("startTime", sdf.format(calendar.getTime()) + " 00:00:00"); //
	 * 设置日期为本月最大日期 calendar.set(Calendar.DATE,
	 * calendar.getActualMaximum(Calendar.DATE)); map.put("endTime",
	 * sdf.format(calendar.getTime()) + " 23:59:59"); return map; }
	 */
	public String goSearch() {
		return "goSearch";
	}

	public String goSingleList() {
		return GO_SINGLE_LIST;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

	public List<GoodsSaleInformation> getGoodsSaleInformationList() {
		return goodsSaleInformationList;
	}

	public void setGoodsSaleInformationList(List<GoodsSaleInformation> goodsSaleInformationList) {
		this.goodsSaleInformationList = goodsSaleInformationList;
	}

	/** 会员画像分析>顾客消费次数分析视图 */
	public void categoryAnalysisViewA() {
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
			String jsonStrReturn = (String) queryDataService.findCategoryAnalysisViewA(hsMap).get("jsonStrReturn");
			renderJson(jsonStrReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员画像分析>会员消费次数排行 */
	public void categoryAnalysisViewB() {
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
			String jsonStrReturn = (String) queryDataService.findCategoryAnalysisViewB(hsMap).get("jsonStrReturn");
			renderJson(jsonStrReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 会员画像分析> 顾客流失风险 | A 主力客户 | frequency 频率，次数 |LatelyMonths1 最近 1个月 */
	public void customerLossRiskA() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			ArrayList<String> timeArr = new ArrayList<String>();
			if (startTime.contains("LatelyMonths")) {// 最近月份
				Integer start = Integer.parseInt(MyTool.analysisJsonStringFive(startTime));
				start = ((start * 30) - 1) * (-1);
				timeArr = MyTool.getNewlyDateArrayByInt_ZX(start);// 这就得到最近
																	// N个月的时间集合（30天/月）
				hsMap.put("timeArr", timeArr);
			} else if (startTime.equals("Day30")) {
				timeArr = MyTool.getNewlyDateArrayByInt_ZX(-29);// 改需求：最近30日
				hsMap.put("timeArr", timeArr);
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
				hsMap.put("timeArr", timeArr);
			}
			hsMap.put("frequency", frequency);
			Double douA = (Double) queryDataService.findFsycftaMainCustomer(hsMap).get("totalDouble");
			Integer intA = MyTool.getIntFromDouble(douA + 0.0);
			String jsonOnlyOne = "{\"jsonOnlyOne\":" + intA + "}";
			renderJson(jsonOnlyOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员画像分析> 顾客流失风险 | B 新客户 | frequency 频率，次数|新顾客：只消费1次的顾客 */
	public void customerLossRiskB() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			hsMap.put("frequency", frequency);
			Double douA = (Double) queryDataService.findFsmcftaNewCustomer(hsMap).get("totalDouble");
			Integer intA = MyTool.getIntFromDouble(douA + 0.0);
			String jsonOnlyOne = "{\"jsonOnlyOne\":" + intA + "}";
			renderJson(jsonOnlyOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员画像分析> 顾客流失风险 | C 已经流失顾客 | frequency 频率，次数 AlreadyLost */
	public void customerLossRiskC() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			ArrayList<String> timeArr = new ArrayList<String>();
			if (startTime.contains("LatelyMonths")) {// 最近月份
				Integer start = Integer.parseInt(MyTool.analysisJsonStringFive(startTime));
				start = ((start * 30) - 1) * (-1);
				timeArr = MyTool.getNewlyDateArrayByInt_ZX(start);// 这就得到最近
																	// N个月的时间集合（30天/月）
				hsMap.put("timeArr", timeArr);
			} else if (startTime.equals("Day30")) {
				timeArr = MyTool.getNewlyDateArrayByInt_ZX(-29);// 改需求：最近30日
				hsMap.put("timeArr", timeArr);
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
				hsMap.put("timeArr", timeArr);
			}
			hsMap.put("frequency", frequency);
			Double douA = (Double) queryDataService.findFsmcftaAlreadyLostCustomer(hsMap).get("totalDouble");
			Integer intA = MyTool.getIntFromDouble(douA + 0.0);
			String jsonOnlyOne = "{\"jsonOnlyOne\":" + intA + "}";
			renderJson(jsonOnlyOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员画像分析> 顾客流失风险 | D 将要流失顾客 | frequency 频率，次数WillLoss 将要流失 */
	public void customerLossRiskD() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			ArrayList<String> timeArrStart = new ArrayList<String>();
			ArrayList<String> timeArrEnd = new ArrayList<String>();
			if (startTime.contains("LatelyMonths")) {// 最近月份
				Integer start = Integer.parseInt(MyTool.analysisJsonStringFive(startTime));
				start = ((start * 30) - 1) * (-1);
				timeArrStart = MyTool.getNewlyDateArrayByInt_ZX(start);// 这就得到最近
																		// N个月的时间集合（30天/月）
				hsMap.put("timeArrStart", timeArrStart);// timeArrStart 最近1个月
				Integer end = Integer.parseInt(MyTool.analysisJsonStringFive(endTime));
				end = ((end * 30) - 1) * (-1);
				timeArrEnd = MyTool.getNewlyDateArrayByInt_ZX(end);
				timeArrEnd.removeAll(timeArrStart);
				hsMap.put("timeArrEnd", timeArrEnd);// timeArrEnd 最近上个月
			}
			Double douA = (Double) queryDataService.findFsmcftaWillLossCustomer(hsMap).get("totalDouble");
			Integer intA = MyTool.getIntFromDouble(douA + 0.0);
			String jsonOnlyOne = "{\"jsonOnlyOne\":" + intA + "}";
			renderJson(jsonOnlyOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员画像分析> 顾客流失风险 | E 全部客户 */
	public void customerLossRiskE() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			hsMap.put("controlSQL", "customerLossRiskEAllCustomer");
			Double douA = (Double) queryDataService.findnvixContentJsonA(hsMap).get("totalDouble");
			Integer intA = MyTool.getIntFromDouble(douA + 0.0);
			String jsonOnlyOne = "{\"jsonOnlyOne\":" + intA + "}";
			renderJson(jsonOnlyOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员画像分析> highValueMember 高价值客户分析 |A 忠诚客户 |Loyal 忠诚 **/
	public void highValueMemberA() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			ArrayList<String> timeArrStart = new ArrayList<String>();
			ArrayList<String> timeArrEnd = new ArrayList<String>();
			if (startTime.contains("LatelyMonths")) {// 最近月份
				Integer start = Integer.parseInt(MyTool.analysisJsonStringFive(startTime));
				start = ((start * 30) - 1) * (-1);
				timeArrStart = MyTool.getNewlyDateArrayByInt_ZX(start);// 这就得到最近
																		// N个月的时间集合（30天/月）
				hsMap.put("timeArrStart", timeArrStart);// timeArrStart 最近1个月
				Integer end = Integer.parseInt(MyTool.analysisJsonStringFive(endTime));
				end = ((end * 30) - 1) * (-1);
				timeArrEnd = MyTool.getNewlyDateArrayByInt_ZX(end);
				timeArrEnd.removeAll(timeArrStart);
				hsMap.put("timeArrEnd", timeArrEnd);// timeArrEnd 最近上个月
			}
			hsMap.put("frequency", frequency);
			Double douA = (Double) queryDataService.findFvmqftaLoyalCustomer(hsMap).get("totalDouble");
			Integer intA = MyTool.getIntFromDouble(douA + 0.0);
			String jsonOnlyOne = "{\"jsonOnlyOne\":" + intA + "}";
			renderJson(jsonOnlyOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员画像分析> highValueMember 高价值客户分析 |B 瞌睡客户 |Doze 瞌睡 **/
	public void highValueMemberB() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			ArrayList<String> timeArrStart = new ArrayList<String>();
			ArrayList<String> timeArrEnd = new ArrayList<String>();
			if (startTime.contains("LatelyMonths")) {// 最近月份
				Integer start = Integer.parseInt(MyTool.analysisJsonStringFive(startTime));
				start = ((start * 30) - 1) * (-1);
				timeArrStart = MyTool.getNewlyDateArrayByInt_ZX(start);// 这就得到最近
																		// N个月的时间集合（30天/月）
				hsMap.put("timeArrStart", timeArrStart);// timeArrStart 最近1个月
				Integer end = Integer.parseInt(MyTool.analysisJsonStringFive(endTime));
				end = ((end * 30) - 1) * (-1);
				timeArrEnd = MyTool.getNewlyDateArrayByInt_ZX(end);
				timeArrEnd.removeAll(timeArrStart);
				hsMap.put("timeArrEnd", timeArrEnd);// timeArrEnd 最近上个月
			}
			hsMap.put("frequency", frequency);
			Double douA = (Double) queryDataService.findFvmqftaDozeCustomer(hsMap).get("totalDouble");
			Integer intA = MyTool.getIntFromDouble(douA + 0.0);
			String jsonOnlyOne = "{\"jsonOnlyOne\":" + intA + "}";
			renderJson(jsonOnlyOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员画像分析> highValueMember 高价值客户分析 |C 半睡客户 |HalfAsleep半睡 **/
	public void highValueMemberC() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			ArrayList<String> timeArrStart = new ArrayList<String>();
			if (startTime.contains("LatelyMonths")) {// 最近月份
				Integer start = Integer.parseInt(MyTool.analysisJsonStringFive(startTime));
				start = ((start * 30) - 1) * (-1);
				timeArrStart = MyTool.getNewlyDateArrayByInt_ZX(start);// 这就得到最近
																		// N个月的时间集合（30天/月）
				hsMap.put("timeArrStart", timeArrStart);// timeArrStart 最近1个月
			}
			hsMap.put("frequency", frequency);
			Double douA = (Double) queryDataService.findFvmmftaHalfAsleepCustomer(hsMap).get("totalDouble");
			Integer intA = MyTool.getIntFromDouble(douA + 0.0);
			String jsonOnlyOne = "{\"jsonOnlyOne\":" + intA + "}";
			renderJson(jsonOnlyOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String frequency;// 频率，次数 ;
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
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
