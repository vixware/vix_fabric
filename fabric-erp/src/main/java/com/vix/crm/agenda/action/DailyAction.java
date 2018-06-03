package com.vix.crm.agenda.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.utils.StrUtils;
import com.vix.crm.agenda.entity.Daily;
import com.vix.crm.agenda.service.IDailyService;
import com.vix.hr.organization.entity.Employee;

@Controller
@Scope("prototype")
public class DailyAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IDailyService dailyService;

	private String id;
	private String ids;
	private Daily daily;
	private Employee employee;
	private Daily yesterdayDaily;
	private String pageNo;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String today;
	private List<Employee> staffEmployeeList = new ArrayList<Employee>();
	@Override
	public String goList() {
		loadStaffEmployee();
		return GO_LIST;
	}

	public String goListContent() {
		try {
			String dailyCode = getRequestParameter("date");
			String employeeId = getRequestParameter("employeeId");
			if (null == dailyCode || "".equals(dailyCode)) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dailyCode = simpleDateFormat.format(new Date());
			}
			if (dailyCode.length() > 10) {
				String ym = dailyCode.substring(0, 8);
				String[] d = dailyCode.split(",");
				dailyCode = ym + d[1];
			}
			SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
			String wk = dateFm.format(sdf.parse(dailyCode));
			today = dailyCode + "[" + wk + "]";
			if (null != employeeId && !"".equals(employeeId)) {
				employee = dailyService.findEntityById(Employee.class,employeeId);
			} else {
				String empId = SecurityUtil.getCurrentEmpId();
				if (null != empId) {
					employee = dailyService.findEntityById(Employee.class,empId);
				}
			}
			if (null == employee) {
				return GO_SINGLE_LIST;
			}
			daily = dailyService.findEntityByEmployeeCodeAndDailyCode(dailyCode, employee.getCode());
			String yesDailyCode = getYesterday(sdf.parse(dailyCode));
			yesterdayDaily = dailyService.findEntityByEmployeeCodeAndDailyCode(yesDailyCode, employee.getCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		String dailyCode = "";
		Date date = null;
		try {
			if(null != today && !"".trim().equals(today)){
				dailyCode = today;
				date = sdf.parse(today);
			}else{
				dailyCode = sdf.format(new Date());
				date = new Date();
			}
			String empId = SecurityUtil.getCurrentEmpId();
			if (null != empId) {
				Employee emp = dailyService.findEntityById(Employee.class,empId);
				if (null != emp) {
					daily = dailyService.findEntityByEmployeeCodeAndDailyCode(dailyCode, emp.getCode());
					if (null == daily) {
						daily = new Daily();
//						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//						String[] currentDay = sdf.format(new Date()).split(" ");
//						daily.setStartTime(new Date());
//						daily.setEndTime(sdf.parse(currentDay[0] + " 23:59:59"));
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						String[] currentDay = simpleDateFormat.format(date).split(" ");
						daily.setStartTime(date);
						daily.setEndTime(simpleDateFormat.parse(currentDay[0] + " 23:59:59"));
					}
					String yesDailyCode = getYesterday(date);
					yesterdayDaily = dailyService.findEntityByEmployeeCodeAndDailyCode(yesDailyCode,emp.getCode());
					if (null != daily && StrUtils.objectIsNotNull(daily.getRepeat())) {
						return "showDaily";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		Date date = null;
		try {
			if (StrUtils.objectIsNotNull(daily.getId())) {
				isSave = false;
			} else {
				date = sdf.parse(today);
				daily.setCreateTime(new Date());
				loadCommonData(daily);
			}

			if (null == daily.getDailyCode() || "".equals(daily.getDailyCode())) {
				daily.setDailyCode(sdf.format(date));
			}

			if (null == daily.getEmployeeCode() || "".equals(daily.getEmployeeCode())) {
				String empId = SecurityUtil.getCurrentEmpId();
				if (null != empId) {
					Employee emp = dailyService.findEntityById(Employee.class,empId);
					if (null != emp) {
						daily.setEmployeeCode(emp.getCode());
					}
				}
			}
			if (null != daily.getRepeat() && !"".trim().equals(daily.getRepeat())) {
				String empId = SecurityUtil.getCurrentEmpId();
				Employee emp = dailyService.findEntityById(Employee.class,empId);
				if (null != emp) {
					daily.setRepeatEmployeeCode(emp.getCode());
				}
			}
			daily = dailyService.merge(daily);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public String saveRepeat() {
		try {
			if (null != daily.getId()) {
				String repeat = daily.getRepeat();
				daily = dailyService.findEntityById(Daily.class, daily.getId());
				if (null != daily) {
					daily.setRepeat(repeat);
					dailyService.merge(daily);
				}
			}
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(UPDATE_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			Daily pb = dailyService.findEntityById(Daily.class, id);
			if (null != pb) {
				dailyService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void findDailyJsonByEmployee() {
		try {
			StringBuilder json = new StringBuilder();
			json.append("[");
			if (null != id && !"".equals(id)) {
				Employee employee = dailyService.findEntityById(Employee.class,
						id);
				if (null != employee && null != employee.getCode()) {
					String st = getRequestParameter("start");
					String et = getRequestParameter("end");
					if (null != st && null != et && !"".equals(st)
							&& !"".equals(et)) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						Date startTime = sdf.parse("2013-09-01");
						Date endTime = sdf.parse("2013-09-30");
						List<Daily> listDaily = dailyService
								.findAllDailyByEmployee(startTime, endTime,
										employee.getCode());
						for (int i = 0; i < listDaily.size(); i++) {
							Daily d = listDaily.get(i);
							json.append("{\"id\":");
							json.append("\"");
							json.append(d.getId());
							json.append("\",\"title\":");
							json.append("\"");
							json.append(d.getName());
							json.append("\",\"start\":\"");
							json.append(d.getStartTime());
							json.append("\",\"end\":\"");
							json.append(d.getEndTime());
							json.append("\"}");

							if (i < listDaily.size() - 1) {
								json.append(",");
							}
						}
					}
				}
			}
			json.append("]");
			renderJson(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadStaffEmployee() {
		try {
			String empId = SecurityUtil.getCurrentEmpId();
			Employee emp = dailyService.findEntityById(Employee.class, empId);
			if (null != emp) {
				List<OrganizationUnit> ouList = dailyService.findAllByEntityClassAndAttribute(OrganizationUnit.class, "manager.id",emp.getId());
				if (null != ouList && ouList.size() > 0) {
					for (OrganizationUnit ou : ouList) {
						List<Employee> eList = dailyService.findAllByEntityClassAndAttribute(Employee.class, "organizationUnit.id",ou.getId());
						staffEmployeeList.addAll(eList);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getYesterday(Date dates) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dates);
		cal.add(Calendar.DATE, -1);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

	}

	public String goDailyTotal() {
		return "goDailyTotal";
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

	public Daily getDaily() {
		return daily;
	}

	public void setDaily(Daily daily) {
		this.daily = daily;
	}

	public Daily getYesterdayDaily() {
		return yesterdayDaily;
	}

	public void setYesterdayDaily(Daily yesterdayDaily) {
		this.yesterdayDaily = yesterdayDaily;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public List<Employee> getStaffEmployeeList() {
		return staffEmployeeList;
	}

	public void setStaffEmployeeList(List<Employee> staffEmployeeList) {
		this.staffEmployeeList = staffEmployeeList;
	}

	@Override
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
