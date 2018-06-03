/**
 * 
 *  
 * 
 *    编写人：  李大鹏
 *   时间 ： 
 *   版本： 3.1
 *   修订版本： 
 *   1.   3.1.1  do sth.   xxxx   time
 *   2.   3.1.2  dxxxxx    time , who
 *   
 * 
 */

package com.vix.hr.hrmgr.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.hrmgr.controller.StaffRosterController;
import com.vix.hr.hrmgr.service.IStaffRosterService;
import com.vix.hr.organization.entity.Employee;

@Controller
@Scope("prototype")
public class StaffRosterAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(Employee.class);
	/** 注入service */
	@Autowired
	private StaffRosterController staffRosterController;

	@Autowired
	private IStaffRosterService iStaffRosterService;
	private String id;
	private Employee employee;
	private String pageNo;

	private List<Employee> employeeList;

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public IStaffRosterService getiStaffRosterService() {
		return iStaffRosterService;
	}

	public void setiStaffRosterService(IStaffRosterService iStaffRosterService) {
		this.iStaffRosterService = iStaffRosterService;
	}

	/**
	 * 加载顶端工具栏
	 * 
	 * @return
	 */

	public String goTopDynamicMenuContent() {
		return "goTopDynamicMenuContent";
	}

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			employeeList = staffRosterController.findEmployeeIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * goSingleList 获取当前企业的花名册列表
	 * 
	 * @return 跳转到xxxx.jsp
	 * 
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 状态 */
			String gender = getRequestParameter("gender");
			if (null != gender && !"".equals(gender)) {
				params.put("gender," + SearchCondition.EQUAL, Integer.parseInt(gender));
			}
			/* 按最近使用 */
			String entityTime = getRequestParameter("entityTime");
			if (entityTime != null && !"".equals(entityTime)) {
				params.put("entityTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(entityTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("entityTime");
				getPager().setOrderBy("desc");
			}
			// 花名册中的显示列表数据业务逻辑要求，增加按照当前的用户所在的公司进行数据过滤。LDP, 2014.05.22
			Pager pager = staffRosterController.goSingleList(params, getPager());
			logger.info("获取花名册列表上半部数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			/* 姓名 */
			String name = getRequestParameter("name");
			if (null != name && !"".equals(name)) {
				name = URLDecoder.decode(name, "utf-8");
			}
			/* 员工编码 */
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				code = URLDecoder.decode(code, "utf-8");
			}
			/* 曾用名 */
			String oldName = getRequestParameter("oldName");
			if (null != oldName && !"".equals(oldName)) {
				oldName = URLDecoder.decode(oldName, "utf-8");
			}
			/* 身份证号 */
			String idNumber = getRequestParameter("idNumber");
			if (null != idNumber && !"".equals(idNumber)) {
				idNumber = URLDecoder.decode(idNumber, "utf-8");
			}
			/* 员工职号 */
			String staffJobNumber = getRequestParameter("staffJobNumber");
			if (null != staffJobNumber && !"".equals(staffJobNumber)) {
				staffJobNumber = URLDecoder.decode(staffJobNumber, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
				pager = staffRosterController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != name && !"".equals(name)) {
					params.put("name," + SearchCondition.ANYLIKE, name);
				}
				if (null != code && !"".equals(code)) {
					params.put("code," + SearchCondition.ANYLIKE, code);
				}
				if (null != oldName && !"".equals(oldName)) {
					params.put("oldName," + SearchCondition.ANYLIKE, oldName);
				}
				if (null != idNumber && !"".equals(idNumber)) {
					params.put("idNumber," + SearchCondition.ANYLIKE, idNumber);
				}
				if (null != staffJobNumber && !"".equals(staffJobNumber)) {
					params.put("staffJobNumber," + SearchCondition.ANYLIKE, staffJobNumber);
				}
				pager = staffRosterController.goSingleList(params, getPager());
			}
			logger.info("获取在职管理搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				listOrganization = iStaffRosterService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = iStaffRosterService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listOrganization.size();
			for (int i = 0; i < count; i++) {
				Organization org = listOrganization.get(i);
				if (org.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
