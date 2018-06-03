package com.vix.drp.storePersonnelinformation.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.storePersonnelinformation.controller.StorePersonnelInformationController;
import com.vix.hr.organization.entity.Employee;

/**
 * 门店人员信息
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-10
 */
@Controller
@Scope("prototype")
public class StorePersonnelinformationAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private StorePersonnelInformationController storePersonnelInformationController;

	private String id;
	private String ids;
	private Employee employee;
	private String parentId;
	private String treeType;
	private List<Employee> employeeList;
	private String source;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("empType," + SearchCondition.ANYLIKE, "ST");
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name.trim());
			}
			String employeeCode = getRequestParameter("employeeCode");
			if (employeeCode != null && !"".equals(employeeCode)) {
				params.put("code," + SearchCondition.ANYLIKE, employeeCode.trim());
			}
			String employeeName = getDecodeRequestParameter("employeeName");
			if (employeeName != null && !"".equals(employeeName)) {
				params.put("name," + SearchCondition.ANYLIKE, employeeName.trim());
			}
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = storePersonnelInformationController.doListEntityById(SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					if (employee.getChannelDistributor() != null) {
						// 如果登录的员工属于经销商或门店
						ChannelDistributor channelDistributor = employee.getChannelDistributor();
						params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
					}
				}
			} else {
				if (StringUtils.isNotEmpty(treeType) && StringUtils.isNotEmpty(id)) {
					if ("CH".equals(treeType)) {
						// 点击的树节点是分销体系结构
						params.put("channelDistributor.id," + SearchCondition.EQUAL, id);
					}
				}
			}

			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			employeeList = storePersonnelInformationController.doListEmployeeList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			params.put("empType," + SearchCondition.ANYLIKE, "ST");
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name.trim());
			}
			String employeeCode = getRequestParameter("employeeCode");
			if (employeeCode != null && !"".equals(employeeCode)) {
				params.put("code," + SearchCondition.ANYLIKE, employeeCode.trim());
			}
			String employeeName = getDecodeRequestParameter("employeeName");
			if (employeeName != null && !"".equals(employeeName)) {
				params.put("name," + SearchCondition.ANYLIKE, employeeName.trim());
			}
			if (StringUtils.isNotEmpty(treeType) && StringUtils.isNotEmpty(id)) {
				if ("CH".equals(treeType)) {
					// 点击的树节点是分销体系结构
					params.put("channelDistributor.id," + SearchCondition.EQUAL, id);
				}
			} else {
				if (SecurityUtil.getCurrentEmpId() != null) {
					// 获取当前员工信息
					Employee employee = storePersonnelInformationController.doListEntityById(SecurityUtil.getCurrentEmpId());
					if (employee != null) {
						if (employee.getChannelDistributor() != null) {
							// 如果登录的员工属于经销商或门店
							ChannelDistributor channelDistributor = employee.getChannelDistributor();
							params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
						}
					}
				}
			}
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = storePersonnelInformationController.doListStoreInfomation(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				employee = storePersonnelInformationController.doListEntityById(id);
			} else {
				employee = new Employee();
				employee.setCode(VixUUID.createCode(12));
				if (null != parentId && !"".equals(parentId)) {
					if ("CH".equals(treeType)) {
						ChannelDistributor c = storePersonnelInformationController.doListChannelDistributorById(parentId);
						employee.setChannelDistributor(c);
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
		try {
			if (null != employee.getId() && !"".equals(employee.getId())) {
				isSave = false;
			}
			employee.setEmpType("ST");

			//处理中文索引
			String py = ChnToPinYin.getPYString(employee.getName());
			employee.setChineseCharacter(py.toUpperCase());
			initEntityBaseController.initEntityBaseAttribute(employee);
			//处理修改留痕
			//billMarkProcessController.processMark(employee, updateField);
			storePersonnelInformationController.doSaveEmployee(employee);
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

	public String goSearch() {
		return "goSearch";
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			Employee cde = storePersonnelInformationController.doListEntityById(id);
			if (null != cde) {
				storePersonnelInformationController.doDeleteByEntity(cde);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				storePersonnelInformationController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
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

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
