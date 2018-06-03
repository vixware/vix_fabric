package com.vix.nvix.drp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.hr.organization.entity.EmployeeType;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 门店人员信息
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-10
 */
@Controller
@Scope("prototype")
public class VixntStorePersonAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private Employee emp;
	private String parentId;
	private String treeType;
	private List<EmployeeType> employeeTypeList;

	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (StringUtils.isNotEmpty(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("empType," + SearchCondition.EQUAL, "ST");
			String employeeName = getDecodeRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("name," + SearchCondition.ANYLIKE, employeeName.trim());
			}
			Employee e = super.getEmployee();
			if (e != null && e.getChannelDistributor() != null && StringUtils.isNotEmpty(e.getChannelDistributor().getId())) {
				ChannelDistributor channelDistributor = vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "id", e.getChannelDistributor().getId());
				if (channelDistributor != null) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, Employee.class, params);
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private UserAccount entity;
	private UserAccount entityForm;
	private String addUserAccountRoleIds;
	private String rolenames;
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {

			if (StringUtils.isNotEmpty(id)) {
				emp = vixntBaseService.findEntityById(Employee.class, id);
			} else {
				emp = new Employee();
				emp.setCode(VixUUID.createCode(12));
				Employee e = super.getEmployee();
				if (e != null && e.getChannelDistributor() != null) {
					ChannelDistributor channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, e.getChannelDistributor().getId());
					emp.setChannelDistributor(channelDistributor);
				}
			}

			if (emp != null) {
				if (emp.getUserAccounts() != null && emp.getUserAccounts().size() > 0) {
					for (UserAccount userAccount : emp.getUserAccounts()) {
						entity = userAccount;
						addUserAccountRoleIds = "";
						rolenames = "";
						if (entity != null) {
							for (Role role : entity.getRoles()) {
								addUserAccountRoleIds += "," + role.getId();
								rolenames += " " + role.getName();
							}
						}
					}
				} else {
					entity = new UserAccount();
					entity.setEmployee(new BaseEmployee(id));
					entity.setEnable("1");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		String accountId = null;
		try {
			if (StringUtils.isNotEmpty(emp.getId())) {
				isSave = false;
			}
			emp.setEmpType("ST");
			if (emp.getEmployeePercentageType() == null || StringUtils.isEmpty(emp.getEmployeePercentageType().getId())) {
				emp.setEmployeePercentageType(null);
			}

			initEntityBaseController.initEntityBaseAttribute(emp);
			emp = vixntBaseService.merge(emp);

			if (null != entityForm.getId()) {
				accountId = entityForm.getId();
				isSave = false;
			}

			String addRoleIds = getRequest().getParameter("addUserAccountRoleIds");
			String deleteRoleIds = getRequest().getParameter("deleteUserAccountRoleIds");
			if (emp != null) {
				accountManagementService.saveOrUpdate(addRoleIds, deleteRoleIds, accountId, "fx", entityForm.getAccount(), entityForm.getPassword(), emp.getId(), "1", emp.getCompanyCode());
			}
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
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			Employee employee = vixntBaseService.findEntityById(Employee.class, id);
			if (null != employee) {
				Map<String, Object> p = getParams();
				p.put("employee.id," + SearchCondition.EQUAL, employee.getId());
				List<UserAccount> userAccountList = this.vixntBaseService.findAllByConditions(UserAccount.class, p);
				for (UserAccount userAccount : userAccountList) {
					if (userAccount != null) {
						vixntBaseService.batchDeleteBySql("DELETE from cmn_sec_useraccount_role where UserAccount_ID ='" + userAccount.getId() + "'", null);
					}
				}
				vixntBaseService.batchDeleteBySql("DELETE from cmn_sec_useraccount where Employee_ID ='" + employee.getId() + "'", null);
				vixntBaseService.batchDeleteBySql("DELETE from oa_uploader where employee_ID ='" + employee.getId() + "'", null);
				vixntBaseService.batchDeleteBySql("DELETE from hr_org_employee where id ='" + employee.getId() + "'", null);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
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

	public List<EmployeeType> getEmployeeTypeList() {
		return employeeTypeList;
	}

	public void setEmployeeTypeList(List<EmployeeType> employeeTypeList) {
		this.employeeTypeList = employeeTypeList;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	/**
	 * @return the entity
	 */
	public UserAccount getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(UserAccount entity) {
		this.entity = entity;
	}

	/**
	 * @return the entityForm
	 */
	public UserAccount getEntityForm() {
		return entityForm;
	}

	/**
	 * @param entityForm
	 *            the entityForm to set
	 */
	public void setEntityForm(UserAccount entityForm) {
		this.entityForm = entityForm;
	}

	/**
	 * @return the addUserAccountRoleIds
	 */
	public String getAddUserAccountRoleIds() {
		return addUserAccountRoleIds;
	}

	/**
	 * @param addUserAccountRoleIds
	 *            the addUserAccountRoleIds to set
	 */
	public void setAddUserAccountRoleIds(String addUserAccountRoleIds) {
		this.addUserAccountRoleIds = addUserAccountRoleIds;
	}

	/**
	 * @return the rolenames
	 */
	public String getRolenames() {
		return rolenames;
	}

	/**
	 * @param rolenames
	 *            the rolenames to set
	 */
	public void setRolenames(String rolenames) {
		this.rolenames = rolenames;
	}

}