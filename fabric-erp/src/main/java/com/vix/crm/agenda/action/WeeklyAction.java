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
import com.vix.crm.agenda.entity.Daily;
import com.vix.crm.agenda.entity.Weekly;
import com.vix.hr.organization.entity.Employee;

@Controller
@Scope("prototype")
public class WeeklyAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String ids;
	private Weekly weekly;
	private Weekly preWeekly;
	private String pageNo;
	private String currentWeek;
	private String nextWeekNumber;
	private String previousWeekNumber;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	private SimpleDateFormat sdfDaily = new SimpleDateFormat("yyyyMMdd");
	private List<Daily> dailyList = new ArrayList<Daily>();

	public String goTab1ListContent(){
		try {
			String yearCode = sdf.format(new Date());
			Integer weekNumber = DateUtil.getWeekNumber();
			weekly = baseHibernateService.findEntityByAttribute(Weekly.class, "weeklyCode", yearCode+weekNumber);
			currentWeek = "第" + DateUtil.getWeekNumber() + "周";
			String employeeId = SecurityUtil.getCurrentEmpId();
			if(null != employeeId && !"".equals(employeeId) && !employeeId.equals("0")){
				Employee emp = baseHibernateService.findEntityById(Employee.class,employeeId);
				if(null != emp && null != emp.getCode()){
					loadDaily(null,emp.getCode());
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
			String wn = getRequestParameter("weekNumber");
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
				int weekNumber = 0;
				if(wn.length() == 8){
					Date date = sdfDaily.parse(wn);
					Calendar calendar = Calendar.getInstance();
			    	calendar.setFirstDayOfWeek(Calendar.MONDAY);
			        calendar.setTime(date);
			        weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
				}else{
					weekNumber = Integer.parseInt(wn.substring(4,wn.length()));
				}
				nextWeekNumber=  yearCode+(weekNumber+1);
				previousWeekNumber=  yearCode+(weekNumber-1);
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("weeklyCode,"+SearchCondition.EQUAL, wn);
				params.put("employeeCode,"+SearchCondition.EQUAL, empCode);
				List<Weekly> weeklyList = baseHibernateService.findAllByConditions(Weekly.class, params);
				if(null != weeklyList && weeklyList.size() > 0){
					weekly = weeklyList.get(0);
				}
				params.put("weeklyCode,"+SearchCondition.EQUAL, previousWeekNumber);
				List<Weekly> preWeeklyList = baseHibernateService.findAllByConditions(Weekly.class, params);
				if(null != preWeeklyList && preWeeklyList.size() > 0){
					preWeekly = preWeeklyList.get(0);
				}
				currentWeek = "第" + wn.substring(4,wn.length()) + "周";
				Calendar cal=Calendar.getInstance();
			    cal.set(Calendar.YEAR,Integer.parseInt(yearCode));
			    cal.set(Calendar.WEEK_OF_YEAR,Integer.parseInt(wn.substring(4,wn.length())));
				loadDaily(cal.getTime(),empCode);
			}else{
				String yearCode = sdf.format(new Date());
				int weekNumber = DateUtil.getWeekNumber();
				nextWeekNumber=  yearCode+(weekNumber+1);
				previousWeekNumber=  yearCode+(weekNumber-1);
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("weeklyCode,"+SearchCondition.EQUAL, yearCode + weekNumber);
				params.put("employeeCode,"+SearchCondition.EQUAL, empCode);
				List<Weekly> weeklyList = baseHibernateService.findAllByConditions(Weekly.class, params);
				if(null != weeklyList && weeklyList.size() > 0){
					weekly = weeklyList.get(0);
				}
				params.put("weeklyCode,"+SearchCondition.EQUAL, previousWeekNumber);
				List<Weekly> preWeeklyList = baseHibernateService.findAllByConditions(Weekly.class, params);
				if(null != preWeeklyList && preWeeklyList.size() > 0){
					preWeekly = preWeeklyList.get(0);
				}
				currentWeek = "第" + DateUtil.getWeekNumber() + "周";
				loadDaily(null,empCode);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goSingleListTab2";
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			String yearCode = sdf.format(new Date());
			int weekNumber = DateUtil.getWeekNumber();
			String employeeId = SecurityUtil.getCurrentEmpId();
			if(null != employeeId && !"".equals(employeeId) && !employeeId.equals("0")){
				Employee emp = baseHibernateService.findEntityById(Employee.class,employeeId);
				if(null != emp && null != emp.getCode()){
					Map<String,Object> params = new HashMap<String,Object>();
					params.put("weeklyCode,"+SearchCondition.EQUAL, yearCode+weekNumber);
					params.put("employeeCode,"+SearchCondition.EQUAL, emp.getCode());
					List<Weekly> weeklyList = baseHibernateService.findAllByConditions(Weekly.class, params);
					if(null != weeklyList && weeklyList.size() > 0){
						weekly = weeklyList.get(0);
						if(null == weekly){
							weekly = new Weekly();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							String[] currentDay = sdf.format(new Date()).split(" ");
							weekly.setStartTime(new Date());
							weekly.setEndTime(sdf.parse(currentDay[0] + " 23:59:59"));
						}
					}
					params.put("weeklyCode,"+SearchCondition.EQUAL, yearCode+(weekNumber-1));
					List<Weekly> preWeeklyList = baseHibernateService.findAllByConditions(Weekly.class, params);
					if(null != preWeeklyList && preWeeklyList.size() > 0){
						preWeekly = preWeeklyList.get(0);
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
			if(null != weekly.getId()){
				isSave = false;
			}else{
				weekly.setCreateTime(new Date());
				loadCommonData(weekly);
			}
			if(null == weekly.getWeeklyCode() || "".equals(weekly.getWeeklyCode())){
				String yearCode = sdf.format(new Date());
				int weekNumber = DateUtil.getWeekNumber();
				weekly.setWeeklyCode(yearCode+weekNumber);
			}
			if(null == weekly.getEmployeeCode() || "".equals(weekly.getEmployeeCode())){
				String empId = SecurityUtil.getCurrentEmpId();
				if(null != empId){
					Employee emp = baseHibernateService.findEntityById(Employee.class, empId);
					if(null != emp){
						weekly.setEmployeeCode(emp.getCode());
					}
				}
			}
			weekly = baseHibernateService.merge(weekly);
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
			Weekly pb = baseHibernateService.findEntityById(Weekly.class,id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	private String firstDailyDate = "";
	private String secondDailyDate = "";
	private String thirdDailyDate = "";
	private String fourDailyDate = "";
	private String fiveDailyDate = "";
	private String sixDailyDate = "";
	private String sevenDailyDate = "";
	private void loadDaily(Date date,String empCode) throws Exception{
		Map<String,Object> params = new HashMap<String,Object>();
		if(null == date){
			date = new Date();
		}
		params.put("employeeCode,"+SearchCondition.EQUAL,empCode);
		SimpleDateFormat sdfDailyDate = new SimpleDateFormat("yyyy-MM-dd");
		Date fdw = DateUtil.getFirstDayOfWeek(date);
		String firstDailyCode = sdfDailyDate.format(fdw);
		firstDailyDate = sdfDailyDate.format(fdw);
		Date secondDate = DateUtil.getNextDate(fdw);
		String secondDailyCode = sdfDailyDate.format(secondDate);
		secondDailyDate = sdfDailyDate.format(secondDate);
		Date thirdDate = DateUtil.getNextDate(secondDate);
		String thirdDailyCode = sdfDailyDate.format(thirdDate);
		thirdDailyDate = sdfDailyDate.format(thirdDate);
		Date fourDate = DateUtil.getNextDate(thirdDate);
		String fourDailyCode = sdfDailyDate.format(fourDate);
		fourDailyDate = sdfDailyDate.format(fourDate);
		Date fiveDate = DateUtil.getNextDate(fourDate);
		String fiveDailyCode = sdfDailyDate.format(fiveDate);
		fiveDailyDate = sdfDailyDate.format(fiveDate);
		Date sixDate = DateUtil.getNextDate(fiveDate);
		String sixDailyCode = sdfDailyDate.format(sixDate);
		sixDailyDate = sdfDailyDate.format(sixDate);
		Date ldw = DateUtil.getLastDayOfWeek(date);
		String sevenDailyCode = sdfDailyDate.format(ldw);
		sevenDailyDate = sdfDailyDate.format(ldw);
		params.put("dailyCode,"+SearchCondition.EQUAL, firstDailyCode);
		List<Daily> firstDailyList = baseHibernateService.findAllByConditions(Daily.class, params);
		if(null != firstDailyList && firstDailyList.size() > 0){
			dailyList.add(firstDailyList.get(0));
		}else{
			dailyList.add(new Daily());
		}
		params.put("dailyCode,"+SearchCondition.EQUAL, secondDailyCode);
		List<Daily> secondDailyList = baseHibernateService.findAllByConditions(Daily.class, params);
		if(null != secondDailyList && secondDailyList.size() > 0){
			dailyList.add(secondDailyList.get(0));
		}else{
			dailyList.add(new Daily());
		}
		params.put("dailyCode,"+SearchCondition.EQUAL, thirdDailyCode);
		List<Daily> thirdDailyList = baseHibernateService.findAllByConditions(Daily.class, params);
		if(null != thirdDailyList && thirdDailyList.size() > 0){
			dailyList.add(thirdDailyList.get(0));
		}else{
			dailyList.add(new Daily());
		}
		params.put("dailyCode,"+SearchCondition.EQUAL, fourDailyCode);
		List<Daily> fourDailyList = baseHibernateService.findAllByConditions(Daily.class, params);
		if(null != fourDailyList && fourDailyList.size() > 0){
			dailyList.add(fourDailyList.get(0));
		}else{
			dailyList.add(new Daily());
		}
		params.put("dailyCode,"+SearchCondition.EQUAL, fiveDailyCode);
		List<Daily> fiveDailyList = baseHibernateService.findAllByConditions(Daily.class, params);
		if(null != fiveDailyList && fiveDailyList.size() > 0){
			dailyList.add(fiveDailyList.get(0));
		}else{
			dailyList.add(new Daily());
		}
		params.put("dailyCode,"+SearchCondition.EQUAL, sixDailyCode);
		List<Daily> sixDailyList = baseHibernateService.findAllByConditions(Daily.class, params);
		if(null != sixDailyList && sixDailyList.size() > 0){
			dailyList.add(sixDailyList.get(0));
		}else{
			dailyList.add(new Daily());
		}
		params.put("dailyCode,"+SearchCondition.EQUAL, sevenDailyCode);
		List<Daily> sevenDailyList = baseHibernateService.findAllByConditions(Daily.class, params);
		if(null != sevenDailyList && sevenDailyList.size() > 0){
			dailyList.add(sevenDailyList.get(0));
		}else{
			dailyList.add(new Daily());
		}
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

	public String goWeeklyTotal(){
		try {
			String yearCode = sdf.format(new Date());
			int weekNumber = DateUtil.getWeekNumber();
			String employeeId = SecurityUtil.getCurrentEmpId();
			if(null != employeeId && !"".equals(employeeId) && !employeeId.equals("0")){
				Employee emp = baseHibernateService.findEntityById(Employee.class,employeeId);
				if(null != emp && null != emp.getCode()){
					Map<String,Object> params = new HashMap<String,Object>();
					params.put("weeklyCode,"+SearchCondition.EQUAL, yearCode+weekNumber);
					params.put("employeeCode,"+SearchCondition.EQUAL, emp.getCode());
					List<Weekly> weeklyList = baseHibernateService.findAllByConditions(Weekly.class, params);
					if(null != weeklyList && weeklyList.size() > 0){
						weekly = weeklyList.get(0);
						if(null == weekly){
							weekly = new Weekly();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							String[] currentDay = sdf.format(new Date()).split(" ");
							weekly.setStartTime(new Date());
							weekly.setEndTime(sdf.parse(currentDay[0] + " 23:59:59"));
						}
					}
					params.put("weeklyCode,"+SearchCondition.EQUAL, yearCode+(weekNumber-1));
					List<Weekly> preWeeklyList = baseHibernateService.findAllByConditions(Weekly.class, params);
					if(null != preWeeklyList && preWeeklyList.size() > 0){
						preWeekly = preWeeklyList.get(0);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goWeeklyTotal";
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

	public List<Employee> getStaffEmployeeList() {
		return staffEmployeeList;
	}

	public void setStaffEmployeeList(List<Employee> staffEmployeeList) {
		this.staffEmployeeList = staffEmployeeList;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Weekly getPreWeekly() {
		return preWeekly;
	}

	public void setPreWeekly(Weekly preWeekly) {
		this.preWeekly = preWeekly;
	}

	public Weekly getWeekly() {
		return weekly;
	}

	public void setWeekly(Weekly weekly) {
		this.weekly = weekly;
	}

	public String getNextWeekNumber() {
		return nextWeekNumber;
	}

	public void setNextWeekNumber(String nextWeekNumber) {
		this.nextWeekNumber = nextWeekNumber;
	}

	public String getPreviousWeekNumber() {
		return previousWeekNumber;
	}

	public void setPreviousWeekNumber(String previousWeekNumber) {
		this.previousWeekNumber = previousWeekNumber;
	}

	public String getCurrentWeek() {
		return currentWeek;
	}

	public void setCurrentWeek(String currentWeek) {
		this.currentWeek = currentWeek;
	}

	public List<Daily> getDailyList() {
		return dailyList;
	}

	public void setDailyList(List<Daily> dailyList) {
		this.dailyList = dailyList;
	}

	public String getFirstDailyDate() {
		return firstDailyDate;
	}

	public void setFirstDailyDate(String firstDailyDate) {
		this.firstDailyDate = firstDailyDate;
	}

	public String getSecondDailyDate() {
		return secondDailyDate;
	}

	public void setSecondDailyDate(String secondDailyDate) {
		this.secondDailyDate = secondDailyDate;
	}

	public String getThirdDailyDate() {
		return thirdDailyDate;
	}

	public void setThirdDailyDate(String thirdDailyDate) {
		this.thirdDailyDate = thirdDailyDate;
	}

	public String getFourDailyDate() {
		return fourDailyDate;
	}

	public void setFourDailyDate(String fourDailyDate) {
		this.fourDailyDate = fourDailyDate;
	}

	public String getFiveDailyDate() {
		return fiveDailyDate;
	}

	public void setFiveDailyDate(String fiveDailyDate) {
		this.fiveDailyDate = fiveDailyDate;
	}

	public String getSixDailyDate() {
		return sixDailyDate;
	}

	public void setSixDailyDate(String sixDailyDate) {
		this.sixDailyDate = sixDailyDate;
	}

	public String getSevenDailyDate() {
		return sevenDailyDate;
	}

	public void setSevenDailyDate(String sevenDailyDate) {
		this.sevenDailyDate = sevenDailyDate;
	}
}
