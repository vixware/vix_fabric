package com.vix.crm.agenda.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.crm.agenda.entity.Monthly;
import com.vix.crm.agenda.entity.Weekly;
import com.vix.hr.organization.entity.Employee;

@Controller
@Scope("prototype")
public class MonthlyAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String ids;
	private Monthly monthly;
	private Monthly preMonthly;
	private String month;
	private String pageNo;
	private String currentMonth;
	private String nextMonthNumber;
	private String previousMonthNumber;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	private List<Weekly> weeklyList = new ArrayList<Weekly>();
	
	@Override
	public String goList(){
		loadStaffEmployee();
		return GO_LIST;
	}
	
	public String goTab1ListContent(){
		try {
			String employeeId = SecurityUtil.getCurrentEmpId();
			if(null != employeeId && !employeeId.equals("0") && !"".equals(employeeId)){
				Employee emp = baseHibernateService.findEntityById(Employee.class,employeeId);
				if(null != emp && null != emp.getCode()){
					loadWeek(null,emp.getCode());
					String monthCode = sdf.format(new Date());
					Map<String,Object> params = new HashMap<String,Object>();
					params.put("monthlyCode,"+SearchCondition.EQUAL, monthCode);
					params.put("employeeCode,"+SearchCondition.EQUAL, emp.getCode());
					List<Monthly> monthlyList = baseHibernateService.findAllByConditions(Monthly.class, params);
					if(null != monthlyList && monthlyList.size() > 0){
						monthly = monthlyList.get(0);
					}
					String pmn = "";
					int year = DateUtil.getYear();
					int month = DateUtil.getMonth();
					if(month == 12){
						pmn =  year + "" + (month - 1);
					}else if(month == 1){
						pmn=  year-1 + "12";
					}else{
						if(month <= 10){
							pmn =  year + "0" + (month - 1);
						}else{
							pmn =  year + "" + (month - 1);
						}
					}
					params.put("monthlyCode,"+SearchCondition.EQUAL, pmn);
					List<Monthly> preMonthlyList = baseHibernateService.findAllByConditions(Monthly.class, params);
					if(null != preMonthlyList && preMonthlyList.size() > 0){
						preMonthly = preMonthlyList.get(0);
					}
					currentMonth =  DateUtil.getMonth() + "月份";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goSingleListTab1";
	}
	
	public String goTab2ListContent(){
		try {
			loadStaffEmployee();
			String wn = getRequestParameter("monthNumber");
			String employeeId = getRequestParameter("employeeId");
			if(null == employeeId || "".equals(employeeId)){
				employeeId = SecurityUtil.getCurrentEmpId() + "";
			}
			String empCode = "";
			if(null != employeeId){
				Employee emp = baseHibernateService.findEntityById(Employee.class,employeeId);
				if(null != emp){
					empCode = emp.getCode();
				}
			}
			if(null == empCode || "".equals(empCode)){
				return "goSingleListTab2";
			}
			if(null != wn && !"".equals(wn)){
				String yearCode = wn.substring(0,4);
				int year = Integer.parseInt(yearCode);
				int monthNumber = Integer.parseInt(wn.substring(4,wn.length()));
				if(monthNumber == 12){
					nextMonthNumber=  year + 1 + "01";
				}else{
					nextMonthNumber=  yearCode + (monthNumber+1);
				}
				if(monthNumber == 1){
					previousMonthNumber=  year - 1 + "12";
				}else{
					previousMonthNumber=  yearCode + (monthNumber-1);
				}
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("monthlyCode,"+SearchCondition.EQUAL, wn);
				params.put("employeeCode,"+SearchCondition.EQUAL, empCode);
				List<Monthly> monthlyList = baseHibernateService.findAllByConditions(Monthly.class, params);
				if(null != monthlyList && monthlyList.size() > 0){
					monthly = monthlyList.get(0);
				}
				params.put("monthlyCode,"+SearchCondition.EQUAL, previousMonthNumber);
				List<Monthly> preMonthlyList = baseHibernateService.findAllByConditions(Monthly.class, params);
				if(null != preMonthlyList && preMonthlyList.size() > 0){
					preMonthly = preMonthlyList.get(0);
				}
				currentMonth = year + "年" + wn.substring(4,wn.length()) + "月";
				Calendar cal=Calendar.getInstance();
			    cal.set(Calendar.YEAR,Integer.parseInt(yearCode));
			    cal.set(Calendar.MONTH,Integer.parseInt(wn.substring(4,wn.length())));
				loadWeek(cal.getTime(),empCode);
			}else{
				int year = DateUtil.getYear();
				int month = DateUtil.getMonth();
				String monthNumber = "";
				if(month == 12){
					nextMonthNumber=  year +1 +"01";
					monthNumber = year + "" + month;
					previousMonthNumber =  year + "" + (month - 1);
				}else if(month == 1){
					nextMonthNumber=  year +"0" + (month + 1);
					monthNumber = year + "0" + month;
					previousMonthNumber=  year-1 + "12";
				}else{
					if(month <= 10){
						nextMonthNumber=  DateUtil.getYear() + "0" + (month+1);
						monthNumber=  year + "0" + month;
						previousMonthNumber =  year + "0" + (month - 1);
					}else{
						nextMonthNumber=  DateUtil.getYear() + "" + (month+1);
						monthNumber=  year + "" + month;
						previousMonthNumber =  year + "" + (month - 1);
					}
				}
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("monthlyCode,"+SearchCondition.EQUAL, year + "" +monthNumber);
				params.put("employeeCode,"+SearchCondition.EQUAL, empCode);
				List<Monthly> monthlyList = baseHibernateService.findAllByConditions(Monthly.class, params);
				if(null != monthlyList && monthlyList.size() > 0){
					monthly = monthlyList.get(0);
				}
				params.put("monthlyCode,"+SearchCondition.EQUAL, previousMonthNumber);
				List<Monthly> preMonthlyList = baseHibernateService.findAllByConditions(Monthly.class, params);
				if(null != preMonthlyList && preMonthlyList.size() > 0){
					preMonthly = preMonthlyList.get(0);
				}
				currentMonth = year + "年" + DateUtil.getMonth() + "月";
				loadWeek(null,empCode);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goSingleListTab2";
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			String monthlyCode = sdf.format(new Date());
			String employeeId = SecurityUtil.getCurrentEmpId();
			if(null != employeeId && !"".equals(employeeId) && !employeeId.equals("")){
				Employee emp = baseHibernateService.findEntityById(Employee.class,employeeId);
				if(null != emp && null != emp.getCode()){
					Map<String,Object> params = new HashMap<String,Object>();
					params.put("monthlyCode,"+SearchCondition.EQUAL, monthlyCode);
					params.put("employeeCode,"+SearchCondition.EQUAL, emp.getCode());
					List<Monthly> monthlyList = baseHibernateService.findAllByConditions(Monthly.class, params);
					if(null != monthlyList && monthlyList.size() > 0){
						monthly = monthlyList.get(0);
						if(null == monthly){
							monthly = new Monthly();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							String[] currentDay = sdf.format(new Date()).split(" ");
							monthly.setStartTime(new Date());
							monthly.setEndTime(sdf.parse(currentDay[0] + " 23:59:59"));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != monthly.getId()){
				isSave = false;
			}else{
				monthly.setCreateTime(new Date());
				loadCommonData(monthly);
			}
			if(null == monthly.getMonthlyCode() || "".equals(monthly.getMonthlyCode())){
				String monthlyCode = sdf.format(new Date());
				monthly.setMonthlyCode(monthlyCode);
			}
			if(null == monthly.getEmployeeCode() || "".equals(monthly.getEmployeeCode())){
				String empId = SecurityUtil.getCurrentEmpId();
				if(null != empId){
					Employee emp = baseHibernateService.findEntityById(Employee.class, empId);
					if(null != emp){
						monthly.setEmployeeCode(emp.getCode());
					}
				}
			}
			monthly = baseHibernateService.merge(monthly);
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			Monthly pb = baseHibernateService.findEntityById(Monthly.class,id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	 
	private void loadWeek(Date date,String empCode) throws Exception{
		if(null == date){
			date = new Date();
		}
		String sDate = DateUtil.format(DateUtil.getFirstDayOfMonth(date), "yyyy-MM-dd");
		String eDate = DateUtil.format(DateUtil.getLastDayOfMonth(date), "yyyy-MM-dd");
		Map<String,Object> params = getParams();
		params.put("createTime,"+SearchCondition.BETWEENAND, sDate + " 00:00:01!" + eDate+" 23:59:59");
		params.put("employeeCode,"+SearchCondition.EQUAL, empCode);
		weeklyList = baseHibernateService.findAllByConditions(Weekly.class, params);
	}
	
	private List<Employee> staffEmployeeList;
	private void loadStaffEmployee(){
		try{
			String empId = SecurityUtil.getCurrentEmpId();
			Employee emp = baseHibernateService.findEntityById(Employee.class, empId);
			if(null != emp){
				OrganizationUnit ou = baseHibernateService.findEntityByAttribute(OrganizationUnit.class, "manager.id", emp.getId());
				if(null != ou){
					staffEmployeeList = baseHibernateService.findAllByEntityClassAndAttribute(Employee.class, "organizationUnit.id", ou.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goMonthlyTotal(){
		return "goMonthlyTotal";
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}


	public String getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}

	public String getNextMonthNumber() {
		return nextMonthNumber;
	}

	public void setNextMonthNumber(String nextMonthNumber) {
		this.nextMonthNumber = nextMonthNumber;
	}

	public String getPreviousMonthNumber() {
		return previousMonthNumber;
	}

	public void setPreviousMonthNumber(String previousMonthNumber) {
		this.previousMonthNumber = previousMonthNumber;
	}

	public List<Weekly> getWeeklyList() {
		return weeklyList;
	}

	public void setWeeklyList(List<Weekly> weeklyList) {
		this.weeklyList = weeklyList;
	}

	public Monthly getMonthly() {
		return monthly;
	}

	public void setMonthly(Monthly monthly) {
		this.monthly = monthly;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Monthly getPreMonthly() {
		return preMonthly;
	}

	public void setPreMonthly(Monthly preMonthly) {
		this.preMonthly = preMonthly;
	}

	public List<Employee> getStaffEmployeeList() {
		return staffEmployeeList;
	}

	public void setStaffEmployeeList(List<Employee> staffEmployeeList) {
		this.staffEmployeeList = staffEmployeeList;
	}
}
