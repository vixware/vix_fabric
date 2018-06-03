package com.vix.nvix.customer.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.properties.util.MyTool;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.customer.service.ICustomerService;
import com.vix.nvix.customer.vo.CustomerAccountStatisticsVo;
/**
 * 大B热度统计报表
 * 
 * @类全名 com.vix.diandoc.crm.action.DiandocDoctorStatisticsAction
 *
 * @author yhl
 *
 * @date 2017年6月23日
 */
@Controller
@Scope("prototype")
public class NvixCustomerStatisticsAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	public ICustomerService customerAccountService;
	private String queryTime;//queryTime是一段字符串就代表其时间如'ThisMonthOT'本月
	private String customerNum;// 客户数量
	private String type;// 维度1
	private String controlSQL;// 维度2
	private String topNum;//排行数字
	
	/** 初始化页面的自定义时间的2个时间值,用于初始化页面时显示**/
	public void reqSetAttrTwoDateStr() {
		String[] objMonth = MyTool.getThisMonth_SOnlyAndW();
		getRequest().setAttribute("dateStrFront",objMonth[0]);
		getRequest().setAttribute("dateStrAfter",objMonth[1]);
	}
	@Override
	public String goList() {
		reqSetAttrTwoDateStr();
		goCustomerAccountDistribute();
		return GO_LIST;
	}
	public String goCustomerAccountDistribute() {
		/**需要和CustomerServiceImpl的customerDatePieQuery方法判断保持一致**/
		if (StringUtils.isNotEmpty(type)) {
			if ("customerType".equals(type)) {//customerType 客户类型
				getCustomerTypeList();
			}else if ("customerStage".equals(type)) {//customerStage  客户阶段
				getCustomerStageList();
			}else if (type.startsWith("customer") && (!"customerType".equals(type)) && (!"customerStage".equals(type))) {
				/** (getCustomerListByControlSQL本方法查询目标的type必须以customer开头如'customerRelationshipClass','关系等级') **/
				/**处理以customer开头的部分customerHotLevel  热点程度;customerCreditLevel 信用等级;...等到价值评估 **/
				getCustomerListByControlSQL();
			}else if (type.startsWith("cstomer")) {//处理以cstomer开头的部分 
				/** (getCustomerListByControlSQLStr本方法查询目标的type必须以cstomer开头如"cstomerAccountType": "账户类型") **/
				getCustomerListByControlSQLStr();
			}
		} else {
			getCustomerTypeList();
		}
		return "goCustomerAccountDistribute";
	}
	private void getCustomerTypeList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			if (StringUtils.isNotEmpty(queryTime)) {
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				params.put("timeArr",timeArr);
			}
			List<CustomerAccountStatisticsVo> customerAccountStatisticsVoList = customerAccountService.getCustomerTypeList(params);
			StringBuilder hql = new StringBuilder();
			if (customerAccountStatisticsVoList != null && customerAccountStatisticsVoList.size() > 0) {
				for (CustomerAccountStatisticsVo customerAccountStatisticsVo : customerAccountStatisticsVoList) {
					hql.append("{value:" + customerAccountStatisticsVo.getRegnum() + ",name:'"+customerAccountStatisticsVo.getCustomerTypeName()+"'},");
				}
				customerNum = hql.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void getCustomerStageList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			if (StringUtils.isNotEmpty(queryTime)) {
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				params.put("timeArr",timeArr);
			}
			List<CustomerAccountStatisticsVo> customerAccountStatisticsVoList = customerAccountService.getCustomerStageList(params);
			StringBuilder hql = new StringBuilder();
			if (customerAccountStatisticsVoList != null && customerAccountStatisticsVoList.size() > 0) {
				for (CustomerAccountStatisticsVo customerAccountStatisticsVo : customerAccountStatisticsVoList) {
					hql.append("{value:" + customerAccountStatisticsVo.getRegnum() + ",name:'"+customerAccountStatisticsVo.getCustomerStageName()+"'},");
				}
				customerNum = hql.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 查询 客户关系管理 > 客户管理 > 客户统计 饼图  (本方法查询目标的type必须以customer开头如'customerRelationshipClass','关系等级') **/
	private void getCustomerListByControlSQL() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			if (StringUtils.isNotEmpty(queryTime)) {
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				params.put("timeArr",timeArr);
			}
			/**type就是控制sql查询的条件customerHotLevel热点程度 ;customerCreditLevel 信用等级; **/
			params.put("type",type);
			List<CustomerAccountStatisticsVo> customerAccountStatisticsVoList = customerAccountService.getCustomerListByControlSQL(params);
			StringBuilder hql = new StringBuilder();
			if (customerAccountStatisticsVoList != null && customerAccountStatisticsVoList.size() > 0) {
				for (CustomerAccountStatisticsVo customerAccountStatisticsVo : customerAccountStatisticsVoList) {
					hql.append("{value:" + customerAccountStatisticsVo.getRegnum() + ",name:'"+customerAccountStatisticsVo.getCustomerStageName()+"'},");
				}
				customerNum = hql.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 查询 客户关系管理 > 客户管理 > 客户统计 饼图2   (getCustomerListByControlSQLStr本方法查询目标的type必须以cstomer开头如"cstomerAccountType": "账户类型") **/
	private void getCustomerListByControlSQLStr() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			if (StringUtils.isNotEmpty(queryTime)) {
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				params.put("timeArr",timeArr);
			}
			/**type就是控制sql查询的条件 **/
			params.put("type",type);
			List<CustomerAccountStatisticsVo> customerAccountStatisticsVoList = customerAccountService.getCustomerListByControlSQLStr(params);
			StringBuilder hql = new StringBuilder();
			if (customerAccountStatisticsVoList != null && customerAccountStatisticsVoList.size() > 0) {
				for (CustomerAccountStatisticsVo customerAccountStatisticsVo : customerAccountStatisticsVoList) {
					hql.append("{value:" + customerAccountStatisticsVo.getRegnum() + ",name:'"+customerAccountStatisticsVo.getCustomerStageName()+"'},");
				}
				customerNum = hql.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 查询 客户关系管理 > 客户管理 > 客户统计 >合同排行 barView 根据不同的controlSQL topNum queryTime参数,查询不同的数据  ***/
	public void queryContractTopBar() {
		try {
		Map<String, Object> params = new HashMap<String, Object>();
		Employee employee = getEmployee();
		if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
			params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
		}
		if (StringUtils.isNotEmpty(queryTime)) {
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			params.put("timeArr",timeArr);
		}
		/**controlSQL就是控制sql查询的条件 **/
		params.put("controlSQL",controlSQL);
		params.put("topNum",topNum);
		String sBufferJsonToString =(String)customerAccountService.queryContractTopBar(params).get("sBufferJsonToString");
		/**  根据参数控制页面显示EchartsOption有的是万元,有的是次数....**/
		String optionStr ="{\"optionStr\":"+("\""+"No"+"\"")+"}";
		if (StringUtils.isNotEmpty(controlSQL) && controlSQL.equals("activityFrequency") ) {
			optionStr ="{\"optionStr\":"+("\""+"activityFrequency"+"\"")+"}";
		}
		renderJson(MyTool.mergeJsonStringOne(sBufferJsonToString, optionStr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	///////下面是把'客户统计'客户分布饼图的多条件拆成菜单后的操作///////
	/** 客户关系管理 > 客户分析 > 客户种类分布 **/
	public String customerKindPie() {
		reqSetAttrTwoDateStr();
		return "customerKindPie";
	}
	/** 客户关系管理 > 客户分析 > 客户种类分布的pie图数据查询  & 客户阶段分布pie图查询 &.... 只要是客户分布菜单pie图都是经过此方法查询,根据type区分  ***/
	public void customerDatePieQuery() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			if (StringUtils.isNotEmpty(queryTime)) {
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				params.put("timeArr",timeArr);
			}
			/**type就是控制sql查询的条件 **/
			params.put("type",type);
			String sBufferJsonToString =(String)customerAccountService.customerDatePieQuery(params).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**  客户关系管理 > 客户分析 >客户种类分布列表  &.... 只要是客户分布菜单列表都是经过此方法查询 **/
	public void customerDatePieQueryTable() {
		try {
			String tableGroupArrName = getDecodeRequestParameter("tableGroupArrName");
			String tableGroupArrValue = getDecodeRequestParameter("tableGroupArrValue");
			String tableTotalDouble = getDecodeRequestParameter("tableTotalDouble");
			StringBuffer sqlBuffer = new StringBuffer();
			int intState = 0;
			if(StringUtils.isNotEmpty(tableGroupArrName) && tableGroupArrName.startsWith("[") && tableGroupArrName.endsWith("]"  )
				&& StringUtils.isNotEmpty(tableGroupArrValue) && tableGroupArrValue.startsWith("[") && tableGroupArrValue.endsWith("]")	
			  ){
				JSONArray arrName = new JSONArray(tableGroupArrName);
				JSONArray arrValue = new JSONArray(tableGroupArrValue);
				Double  down = (  StringUtils.isNotEmpty(tableTotalDouble)  ) ? (Double.parseDouble(tableTotalDouble)) : 0.000001 ;
				if(arrName !=null  &&  arrValue!=null &&  arrName.length()>0  &&  arrValue.length()>0  &&  arrName.length()==arrValue.length()  ){
					for(int x=0;x<arrName.length();x++){
						intState++;
						String name = arrName.getString(x);
						Double upper = arrValue.getDouble(x);
						 Double pro = MyTool.roundingDoubleAppointDecimal((upper/down),4);//pro 占比
						if(x==0){
							sqlBuffer.append(" select '"+name+"' as tname,"+(x+1)+" as numsign, '"+upper+"' as valuenum ,'"+(pro*100)+"' as pro");
						}else{
							sqlBuffer.append(" union all  select '"+name+"' as tname,"+(x+1)+" as numsign, '"+upper+"' as valuenum ,'"+(pro*100)+"' as pro");
						}
					}
				}
			}
			/** intState==0时，必须拼装一个可执行的空子查询sql **/
			if(intState == 0){
				sqlBuffer = new StringBuffer(" select null as tname,null as numsign,null as valuenum,null as pro ");
			}
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select m.tname,m.numsign,m.valuenum,m.pro ");
				tableSql.append(" from ("+sqlBuffer.toString()+")m order by m.numsign asc ");
				this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
			}
			if(intState == 0){
				tablePager = new Pager();
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 客户关系管理 > 客户分析 > 客户阶段分布 **/
	public String customerStagePie() {
		reqSetAttrTwoDateStr();
		return "customerStagePie";
	}
	/** 客户关系管理 > 客户分析 > 客户阶段分布 **/
	public String customerHotLevelPie() {
		reqSetAttrTwoDateStr();
		return "customerHotLevelPie";
	}
	/** 客户关系管理 > 客户分析 > 信用等级分布 **/
	public String customerCreditLevelPie() {
		reqSetAttrTwoDateStr();
		return "customerCreditLevelPie";
	}
	/** 客户关系管理 > 客户分析 > 关系等级分布 **/
	public String customerRelationshipClassPie() {
		reqSetAttrTwoDateStr();
		return "customerRelationshipClassPie";
	}
	/** 客户关系管理 > 客户分析 > 人员规模分布 **/
	public String customerStaffScalePie() {
		reqSetAttrTwoDateStr();
		return "customerStaffScalePie";
	}
	/** 客户关系管理 > 客户分析 > 来源分布分布 **/
	public String customerCustomerSourcePie() {
		reqSetAttrTwoDateStr();
		return "customerCustomerSourcePie";
	}
	/** 客户关系管理 > 客户分析 > 所属行业分布 **/
	public String customerIndustryPie() {
		reqSetAttrTwoDateStr();
		return "customerIndustryPie";
	}
	/** 客户关系管理 > 客户分析 > 价值评估分布 **/
	public String customerValueAssessmentPie() {
		reqSetAttrTwoDateStr();
		return "customerValueAssessmentPie";
	}
	/** 客户关系管理 > 客户分析 > 账户类型分布 **/
	public String customerAccountTypePie() {
		reqSetAttrTwoDateStr();
		return "customerAccountTypePie";
	}
	/** 客户关系管理 > 客户分析 > 客户省份分布 **/
	public String customerProvincePie() {
		reqSetAttrTwoDateStr();
		return "customerProvincePie";
	}
	/** 客户关系管理 > 客户分析 > 客户城市分布 **/
	public String customerCityPie() {
		reqSetAttrTwoDateStr();
		return "customerCityPie";
	}
	/** 客户关系管理 > 客户分析 > 所有者分布 **/
	public String customerEmployeePie() {
		reqSetAttrTwoDateStr();
		return "customerEmployeePie";
	}
	/** 客户关系管理 > 客户分析 >客户分类分布**/
	public String customerAccountCategoryPie() {
		reqSetAttrTwoDateStr();
		return "customerAccountCategoryPie";
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getControlSQL() {
		return controlSQL;
	}
	public void setControlSQL(String controlSQL) {
		this.controlSQL = controlSQL;
	}
	public String getTopNum() {
		return topNum;
	}
	public void setTopNum(String topNum) {
		this.topNum = topNum;
	}
}