package com.vix.crm.business.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.drp.MembershipCardmanagement.entity.MemberShipCardType;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.playgroundmanagementstatistics.service.IPlaygroundManagementStatisticsService;
import com.vix.drp.playgroundmanagementstatistics.util.DateUtil;
import com.vix.drp.playgroundmanagementstatistics.vo.AgeStatistic;
import com.vix.drp.playgroundmanagementstatistics.vo.ClassifiedRevenue;
import com.vix.drp.playgroundmanagementstatistics.vo.ConsumerPeopleReport;
import com.vix.drp.playgroundmanagementstatistics.vo.CustomerCount;
import com.vix.drp.playgroundmanagementstatistics.vo.DayIncomeSummary;
import com.vix.drp.playgroundmanagementstatistics.vo.EquipmentCoinNumber;
import com.vix.drp.playgroundmanagementstatistics.vo.EquipmentSales;
import com.vix.drp.playgroundmanagementstatistics.vo.GoodsStatistics;
import com.vix.drp.playgroundmanagementstatistics.vo.GradeRatio;
import com.vix.drp.playgroundmanagementstatistics.vo.GroupIncomeStatement;
import com.vix.drp.playgroundmanagementstatistics.vo.GroupMembers;
import com.vix.drp.playgroundmanagementstatistics.vo.MonthAccountBalance;
import com.vix.drp.playgroundmanagementstatistics.vo.PackagesSalesStatistics;
import com.vix.drp.playgroundmanagementstatistics.vo.SexProportion;
import com.vix.drp.playgroundmanagementstatistics.vo.WaterSalesStatistics;
import com.vix.drp.rides.entity.TranLog;
import com.vix.ebusiness.statisticalAnalysis.hql.StatisticalAnalysisHqlProvider;
import com.vix.hr.organization.entity.Employee;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class OrderSupervisoryControlAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private List<String> dayList = new LinkedList<String>();
	/**
	 * 商品列表
	 */
	private List<String> goodsList = new LinkedList<String>();
	private List<String> channelDistributorNameList = new ArrayList<String>();
	/**
	 * 会员统计
	 */
	private String customerList;
	/**
	 * 会员年龄统计
	 */
	private List<Double> customerAgeList = new LinkedList<Double>();
	/**
	 * 会员卡类型列表
	 */
	private List<String> memberShipCardTypeStr = new LinkedList<String>();
	/**
	 * 会员年龄
	 */
	private List<String> ageStatisticStr = new LinkedList<String>();
	/**
	 * 商品销售金额列表
	 */
	private List<Double> netTotalList = new LinkedList<Double>();
	/**
	 * 设备投币数排行名称
	 */
	private List<String> classifiedRevenueNameList = new LinkedList<String>();
	/**
	 * 设备投币数排行金额
	 */
	private List<Double> classifiedRevenueAmountList = new LinkedList<Double>();
	/**
	 * 设备分类营收之设备名称
	 */
	private List<String> nameList = new LinkedList<String>();
	/**
	 * 设备分类营收之设备收入金额
	 */
	private List<Double> salesAmountList = new LinkedList<Double>();
	private List<Double> personList = new ArrayList<Double>();
	private List<Integer> plist = new ArrayList<Integer>();
	private List<ChannelDistributor> channelDistributorList;
	@Autowired
	private IPlaygroundManagementStatisticsService playgroundManagementStatisticsService;
	@Resource(name = "statisticalAnalysisHqlProvider")
	private StatisticalAnalysisHqlProvider statisticalAnalysisHqlProvider;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private Integer tickInterval;

	private String gradeRatioStr;
	private String sexProportionStr;

	@Override
	public String goList() {
		try {
			Map<String, Object> oparams = getParams();
			oparams.put("type," + SearchCondition.EQUAL, "5");
			channelDistributorList = playgroundManagementStatisticsService.findAllByConditions(ChannelDistributor.class, oparams);
			if (channelDistributorList != null) {
				for (ChannelDistributor channelDistributor : channelDistributorList) {
					channelDistributorNameList.add("'" + channelDistributor.getName() + "'");
				}
			}
			String startTime = getRequestParameter("createTime1");
			String endTime = getRequestParameter("endTime1");
			List<String> dayList1 = null;
			if (startTime != null && !"".equals(startTime) && endTime != null && !"".equals(endTime)) {
				dayList = DateUtil.findDates(sdf.parse(startTime), sdf.parse(endTime));
				dayList1 = DateUtil.findDates1(sdf.parse(startTime), sdf.parse(endTime));
			} else {
				dayList = DateUtil.getSevenDay();
				dayList1 = DateUtil.getSevenDay1();
			}

			if (dayList.size() < 7) {
				tickInterval = 1;
			} else {
				tickInterval = dayList.size() / 7;
			}
			for (int i = 0; i < dayList1.size(); i++) {
				Map<String, Object> params = new HashMap<String, Object>();
				String date = dayList1.get(i);
				if (date != null && !"".equals(date)) {
					String channelDistributorId = getRequestParameter("channelDistributorId1");
					if (channelDistributorId != null && !"".equals(channelDistributorId)) {
						params.put("channelDistributorId", channelDistributorId);
					}
					params.put("date", date);
				}
				Double sumAmount = 0d;
				StringBuilder hql = statisticalAnalysisHqlProvider.findSalesOrder(params);
				sumAmount = playgroundManagementStatisticsService.findSumAmountByHql(hql.toString());
				personList.add(sumAmount);
			}
			getGoodsStatisticsList();
			getCustomerCountList();
			getEquipmentCoinNumberList();
			getClassifiedRevenueList();
			getGradeRatioList();
			getCustomerSexList();
			getAgeStatisticList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		return GO_SINGLE_LIST;
	}

	/**
	 * 会员年龄段统计
	 */
	public void getAgeStatisticList() {

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			// 获取当前登录用户所在的公司或供应商
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = playgroundManagementStatisticsService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					params.put("companyCode", employee.getCompanyCode());
				}
			}
			String startTime = getRequestParameter("createTime8");
			if (startTime != null) {
				params.put("startTime", startTime);
			}
			String endTime = getRequestParameter("endTime8");
			if (endTime != null) {
				params.put("endTime", endTime);
			}
			String channelDistributorId = getRequestParameter("channelDistributorId8");
			if (channelDistributorId != null && !"".equals(channelDistributorId)) {
				params.put("channelDistributorId", channelDistributorId);
			}
			StringBuilder hql = statisticalAnalysisHqlProvider.findCustomerCountAgeList(params);
			List<AgeStatistic> ageStatisticList = playgroundManagementStatisticsService.findAgeStatistic(hql.toString());
			if (ageStatisticList != null && ageStatisticList.size() > 0) {
				for (AgeStatistic ageStatistic : ageStatisticList) {
					ageStatisticStr.add("'" + ageStatistic.getAge() + "'");
					customerAgeList.add(ageStatistic.getAmount() / ageStatisticList.size() * 100);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 设备投币数排行
	 */
	public void getClassifiedRevenueList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			// 获取当前登录用户所在的公司或供应商
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = playgroundManagementStatisticsService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					params.put("companyCode", employee.getCompanyCode());
				}
			}
			StringBuilder hql = statisticalAnalysisHqlProvider.findClassifiedRevenueList(params);
			List<ClassifiedRevenue> classifiedRevenueList = playgroundManagementStatisticsService.findClassifiedRevenue(hql.toString());
			if (classifiedRevenueList != null && classifiedRevenueList.size() > 0) {
				for (ClassifiedRevenue classifiedRevenue : classifiedRevenueList) {
					classifiedRevenueNameList.add("'" + classifiedRevenue.getMacName() + "'");
					classifiedRevenueAmountList.add(classifiedRevenue.getCoinNumber());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 消费人次报表
	 */
	public void getConsumerPeopleReportList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			// 获取当前登录用户所在的公司或供应商
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = playgroundManagementStatisticsService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					params.put("companyCode", employee.getCompanyCode());
				}
			}
			String startTime = getRequestParameter("createTime15");
			if (startTime != null && !"".equals(startTime)) {
				params.put("startTime", startTime);
			}

			String endTime = getRequestParameter("endTime15");
			if (endTime != null && !"".equals(endTime)) {
				params.put("endTime", endTime);
			}

			String channelDistributorId = getRequestParameter("channelDistributorId15");
			if (channelDistributorId != null && !"".equals(channelDistributorId)) {
				params.put("channelDistributorId", channelDistributorId);
			}
			StringBuilder hql = statisticalAnalysisHqlProvider.findConsumerPeopleReportList(params);
			List<ConsumerPeopleReport> consumerPeopleReportList = playgroundManagementStatisticsService.findConsumerPeopleReport(hql.toString());
			String json = "";
			if (null != consumerPeopleReportList) {
				json = convertListToJson(consumerPeopleReportList, consumerPeopleReportList.size());
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 会员统计
	 */
	public void getCustomerCountList() {
		try {
			//MemberShipCardType
			List<MemberShipCardType> memberShipCardTypeList = playgroundManagementStatisticsService.findAllByEntityClass(MemberShipCardType.class);
			if (memberShipCardTypeList != null && memberShipCardTypeList.size() > 0) {
				for (MemberShipCardType memberShipCardType : memberShipCardTypeList) {
					memberShipCardTypeStr.add("'" + memberShipCardType.getVipTypeName() + "'");
				}
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			// 获取当前登录用户所在的公司或供应商
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = playgroundManagementStatisticsService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					params.put("companyCode", employee.getCompanyCode());
				}
			}
			String startTime = getRequestParameter("createTime8");
			if (startTime != null) {
				params.put("startTime", startTime);
			}
			String endTime = getRequestParameter("endTime8");
			if (endTime != null) {
				params.put("endTime", endTime);
			}
			String channelDistributorId = getRequestParameter("channelDistributorId8");
			if (channelDistributorId != null && !"".equals(channelDistributorId)) {
				params.put("channelDistributorId", channelDistributorId);
			}
			StringBuilder hql = statisticalAnalysisHqlProvider.findCustomerCountList(params);
			List<CustomerCount> customerCountList = playgroundManagementStatisticsService.findCustomerCount(hql.toString());
			if (customerCountList != null && customerCountList.size() > 0) {
				JSONArray customerJSONArray = new JSONArray();
				for (CustomerCount customerCount : customerCountList) {
					JSONObject jsonobject = new JSONObject();
					jsonobject.put("name", customerCount.getStoreName());
					String datastr = "[";
					if (customerCount.getOpencard1() != null && !"null".equals(customerCount.getOpencard1())) {
						datastr += customerCount.getOpencard1();
					}
					if (customerCount.getOpencard2() != null && !"null".equals(customerCount.getOpencard2())) {
						datastr += "," + customerCount.getOpencard2();
					}
					if (customerCount.getOpencard3() != null && !"null".equals(customerCount.getOpencard3())) {
						datastr += "," + customerCount.getOpencard3();
					}
					if (customerCount.getOpencard4() != null && !"null".equals(customerCount.getOpencard4())) {
						datastr += "," + customerCount.getOpencard4();
					}
					if (customerCount.getOpencard5() != null && !"null".equals(customerCount.getOpencard5())) {
						datastr += "," + customerCount.getOpencard5();
					}
					datastr += "]";
					jsonobject.put("data", datastr);
					customerJSONArray.add(jsonobject);
				}
				String customer = customerJSONArray.toString().replaceAll("\"\\[", "[");
				customer = customer.replaceAll("\\]\"", "]");
				this.setCustomerList(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 日收入汇总
	 */
	public void getDayIncomeSummaryList() {
		try {

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			// 获取当前登录用户所在的公司或供应商
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = playgroundManagementStatisticsService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					params.put("companyCode", employee.getCompanyCode());
				}
			}
			StringBuilder hql = statisticalAnalysisHqlProvider.findSalesOrderList(params);
			List<DayIncomeSummary> dayIncomeSummaryList = playgroundManagementStatisticsService.findDayIncomeSummaryByHql(hql.toString());
			String json = "";
			if (null != dayIncomeSummaryList) {
				json = convertListToJson(dayIncomeSummaryList, dayIncomeSummaryList.size());
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 商品销售排名
	 */
	public void getEquipmentCoinNumberList() {

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			StringBuilder hql = statisticalAnalysisHqlProvider.findEquipmentCoinNumberList(params);
			List<EquipmentCoinNumber> equipmentCoinNumberList = playgroundManagementStatisticsService.findEquipmentCoinNumber(hql.toString());
			if (equipmentCoinNumberList != null && equipmentCoinNumberList.size() > 0) {
				for (EquipmentCoinNumber equipmentCoinNumber : equipmentCoinNumberList) {
					if (equipmentCoinNumber != null) {
						nameList.add("'" + equipmentCoinNumber.getName() + "'");
						salesAmountList.add(equipmentCoinNumber.getSalesAmount());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设备销售占比
	 */
	public void getEquipmentSalesList() {
		try {
			List<EquipmentSales> equipmentSalesList = new ArrayList<EquipmentSales>();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = playgroundManagementStatisticsService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					params.put("companyCode", employee.getCompanyCode());
				}
			}
			StringBuilder hql = statisticalAnalysisHqlProvider.findTranLogList(params);
			List<TranLog> tranLogList = playgroundManagementStatisticsService.findTranLogByHql(hql.toString());
			if (tranLogList != null && tranLogList.size() > 0) {
				for (TranLog tranLog : tranLogList) {
					EquipmentSales equipmentSales = new EquipmentSales();
					equipmentSales.setType(tranLog.getMacDepartName());
					equipmentSales.setProject(tranLog.getMacTypeName());
					equipmentSales.setCoinNumber(tranLog.getAmount());
					equipmentSalesList.add(equipmentSales);
				}
			}
			String json = "";
			if (null != equipmentSalesList) {
				json = convertListToJson(equipmentSalesList, equipmentSalesList.size());
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 商品统计信息
	 */
	public void getGoodsStatisticsList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = playgroundManagementStatisticsService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					params.put("companyCode", employee.getCompanyCode());
				}
			}
			StringBuilder hql = statisticalAnalysisHqlProvider.findGoodsStatisticsList(params);
			List<GoodsStatistics> goodsStatisticsList = playgroundManagementStatisticsService.findGoodsStatistics(hql.toString());
			if (goodsStatisticsList != null && goodsStatisticsList.size() > 0) {
				for (GoodsStatistics goodsStatistics : goodsStatisticsList) {
					goodsList.add("'" + goodsStatistics.getName() + "'");
					netTotalList.add(goodsStatistics.getSalesAmount());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 会员级别比例
	 */
	public void getGradeRatioList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = playgroundManagementStatisticsService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					params.put("companyCode", employee.getCompanyCode());
				}
			}
			StringBuilder hql = statisticalAnalysisHqlProvider.findMemberShipCardList(params);
			List<GradeRatio> gradeRatioList = playgroundManagementStatisticsService.findGradeRatio(hql.toString());
			if (gradeRatioList != null && gradeRatioList.size() > 0) {
				gradeRatioStr = "[";
				for (GradeRatio gradeRatio : gradeRatioList) {
					gradeRatioStr += "['" + gradeRatio.getGrade() + "'," + gradeRatio.getProportion() + "],";
				}
				gradeRatioStr = gradeRatioStr.substring(0, gradeRatioStr.length() - 1);
				gradeRatioStr += " ]";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取会员性别
	 */
	public void getCustomerSexList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = playgroundManagementStatisticsService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					params.put("companyCode", employee.getCompanyCode());
				}
			}
			StringBuilder hql = statisticalAnalysisHqlProvider.findCustomerAccountList(params);
			List<SexProportion> sexProportionList = playgroundManagementStatisticsService.findSexProportionList(hql.toString());
			if (sexProportionList != null && sexProportionList.size() > 0) {
				sexProportionStr = "[";
				for (SexProportion sexProportion : sexProportionList) {
					sexProportionStr += "['" + getSex(sexProportion.getSex()) + "'," + sexProportion.getProportion() + "],";
				}
				sexProportionStr = sexProportionStr.substring(0, sexProportionStr.length() - 1);
				sexProportionStr += " ]";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getSex(String sex) {

		String sexName = "";
		if (sex != null) {
			if ("M".equals(sex)) {
				sexName = "男";
			} else if ("W".equals(sex)) {
				sexName = "女";
			} else {
				sexName = "未知";
			}
		} else {
			sexName = "未知";
		}
		return sexName;
	}

	/**
	 * 集团收入报表
	 */
	public void getGroupIncomeStatementList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			// 获取当前登录用户所在的公司或供应商
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = playgroundManagementStatisticsService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					params.put("companyCode", employee.getCompanyCode());
				}
			}

			String startTime = getRequestParameter("createTime6");
			if (startTime != null && !"".equals(startTime)) {
				params.put("startTime", startTime);
			}

			String endTime = getRequestParameter("endTime6");
			if (endTime != null && !"".equals(endTime)) {
				params.put("endTime", endTime);
			}
			String channelDistributorId = getRequestParameter("channelDistributorId6");
			if (channelDistributorId != null && !"".equals(channelDistributorId)) {
				params.put("channelDistributorId", channelDistributorId);
			}
			StringBuilder hql = statisticalAnalysisHqlProvider.findGroupIncomeStatementList(params);
			List<GroupIncomeStatement> groupIncomeStatementList = playgroundManagementStatisticsService.findGroupIncomeStatement(hql.toString());
			String json = "";
			if (null != groupIncomeStatementList) {
				json = convertListToJson(groupIncomeStatementList, groupIncomeStatementList.size());
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 集团会员分布
	 */
	public void getGroupMembersList() {
		try {
			List<GroupMembers> groupMembersList = new ArrayList<GroupMembers>();
			String json = "";
			if (null != groupMembersList) {
				json = convertListToJson(groupMembersList, groupMembersList.size());
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 月收入明细
	 */
	public void getMonthAccountBalanceList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			// 获取当前登录用户所在的公司或供应商
			/*if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = playgroundManagementStatisticsService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					params.put("companyCode", employee.getCompanyCode());
				}
			}*/
			String startTime = getRequestParameter("createTime9");
			if (startTime != null && !"".equals(startTime)) {
				params.put("startTime", startTime);
			}
			String endTime = getRequestParameter("endTime9");
			if (endTime != null && !"".equals(endTime)) {
				params.put("endTime", endTime);
			}
			String channelDistributorId = getRequestParameter("channelDistributorId9");
			if (channelDistributorId != null && !"".equals(channelDistributorId)) {
				params.put("channelDistributorId", channelDistributorId);
			}

			StringBuilder hql = statisticalAnalysisHqlProvider.findMonthAccountBalanceList(params);
			List<MonthAccountBalance> monthAccountBalanceList = playgroundManagementStatisticsService.findMonthAccountBalance(hql.toString());
			String json = "";
			if (null != monthAccountBalanceList) {
				json = convertListToJson(monthAccountBalanceList, monthAccountBalanceList.size());
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 套餐销售统计
	 */
	public void getPackagesSalesStatisticsList() {
		try {
			List<PackagesSalesStatistics> packagesSalesStatisticsList = new ArrayList<PackagesSalesStatistics>();
			String json = "";
			if (null != packagesSalesStatisticsList) {
				json = convertListToJson(packagesSalesStatisticsList, packagesSalesStatisticsList.size());
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 会员性别比例
	 */
	public void getSexProportionList() {
		try {
			List<SexProportion> sexProportionList = new ArrayList<SexProportion>();
			String json = "";
			if (null != sexProportionList) {
				json = convertListToJson(sexProportionList, sexProportionList.size());
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 水吧销售统计
	 */
	public void getWaterSalesStatisticsList() {
		try {
			List<WaterSalesStatistics> waterSalesStatisticsList = new ArrayList<WaterSalesStatistics>();
			String json = "";
			if (null != waterSalesStatisticsList) {
				json = convertListToJson(waterSalesStatisticsList, waterSalesStatisticsList.size());
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getDayList() {
		return dayList;
	}

	public void setDayList(List<String> dayList) {
		this.dayList = dayList;
	}

	public List<Double> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Double> personList) {
		this.personList = personList;
	}

	public List<Integer> getPlist() {
		return plist;
	}

	public void setPlist(List<Integer> plist) {
		this.plist = plist;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

	public List<String> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<String> goodsList) {
		this.goodsList = goodsList;
	}

	public List<Double> getNetTotalList() {
		return netTotalList;
	}

	public void setNetTotalList(List<Double> netTotalList) {
		this.netTotalList = netTotalList;
	}

	public String getCustomerList() {
		return customerList;
	}

	public void setCustomerList(String customerList) {
		this.customerList = customerList;
	}

	public List<String> getNameList() {
		return nameList;
	}

	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}

	public List<Double> getSalesAmountList() {
		return salesAmountList;
	}

	public void setSalesAmountList(List<Double> salesAmountList) {
		this.salesAmountList = salesAmountList;
	}

	public List<String> getClassifiedRevenueNameList() {
		return classifiedRevenueNameList;
	}

	public void setClassifiedRevenueNameList(List<String> classifiedRevenueNameList) {
		this.classifiedRevenueNameList = classifiedRevenueNameList;
	}

	public List<Double> getClassifiedRevenueAmountList() {
		return classifiedRevenueAmountList;
	}

	public void setClassifiedRevenueAmountList(List<Double> classifiedRevenueAmountList) {
		this.classifiedRevenueAmountList = classifiedRevenueAmountList;
	}

	public Integer getTickInterval() {
		return tickInterval;
	}

	public void setTickInterval(Integer tickInterval) {
		this.tickInterval = tickInterval;
	}

	public List<String> getMemberShipCardTypeStr() {
		return memberShipCardTypeStr;
	}

	public void setMemberShipCardTypeStr(List<String> memberShipCardTypeStr) {
		this.memberShipCardTypeStr = memberShipCardTypeStr;
	}

	public String getGradeRatioStr() {
		return gradeRatioStr;
	}

	public void setGradeRatioStr(String gradeRatioStr) {
		this.gradeRatioStr = gradeRatioStr;
	}

	public List<String> getChannelDistributorNameList() {
		return channelDistributorNameList;
	}

	public void setChannelDistributorNameList(List<String> channelDistributorNameList) {
		this.channelDistributorNameList = channelDistributorNameList;
	}

	public String getSexProportionStr() {
		return sexProportionStr;
	}

	public void setSexProportionStr(String sexProportionStr) {
		this.sexProportionStr = sexProportionStr;
	}

	public List<Double> getCustomerAgeList() {
		return customerAgeList;
	}

	public void setCustomerAgeList(List<Double> customerAgeList) {
		this.customerAgeList = customerAgeList;
	}

	public List<String> getAgeStatisticStr() {
		return ageStatisticStr;
	}

	public void setAgeStatisticStr(List<String> ageStatisticStr) {
		this.ageStatisticStr = ageStatisticStr;
	}

}
