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
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 
 * @类全名 com.vix.nvix.drp.action.VixntSupplierEmployeeAction
 *
 * @author zhanghaibing
 *
 * @date 2016年9月2日
 */
@Controller
@Scope("prototype")
public class VixntSupplierEmployeeAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private Employee emp;
	private UserAccount entity;
	private UserAccount entityForm;
	private String addUserAccountRoleIds;
	private String rolenames;

	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (StringUtils.isEmpty(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			params.put("empType," + SearchCondition.EQUAL, "SE");
			String employeeName = getDecodeRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("name," + SearchCondition.ANYLIKE, employeeName.trim());
			}
			Employee employee = super.getEmployee();
			if (employee != null) {

				if ("1".equals(employee.getIsViewData())) {
					// 拥有查看数据权限的人员不进行控制
				} else {
					if (employee.getSupplier() != null) {
						Supplier supplier = vixntBaseService.findEntityById(Supplier.class, employee.getSupplier().getId());
						if (supplier != null) {
							params.put("supplier.id," + SearchCondition.EQUAL, supplier.getId());
						}
					}
				}
				pager = vixntBaseService.findPagerByHqlConditions(pager, Employee.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				emp = vixntBaseService.findEntityById(Employee.class, id);
			} else {
				emp = new Employee();
				emp.setCode(VixUUID.createCode(12));
				Employee e = super.getEmployee();
				if (e != null && e.getSupplier() != null) {
					Supplier supplier = vixntBaseService.findEntityById(Supplier.class, e.getSupplier().getId());
					emp.setSupplier(supplier);
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
			emp.setEmpType("SE");
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
			Employee emp = vixntBaseService.findEntityById(Employee.class, id);
			if (emp != null) {
				Map<String, Object> p = getParams();
				p.put("employee.id," + SearchCondition.EQUAL, emp.getId());
				List<UserAccount> userAccountList = this.vixntBaseService.findAllByConditions(UserAccount.class, p);
				for (UserAccount userAccount : userAccountList) {
					if (userAccount != null) {
						vixntBaseService.batchDeleteBySql("DELETE from cmn_sec_useraccount_role where UserAccount_ID ='" + userAccount.getId() + "'", null);
					}
				}
				vixntBaseService.batchDeleteBySql("DELETE from cmn_sec_useraccount where Employee_ID ='" + emp.getId() + "'", null);
				vixntBaseService.batchDeleteBySql("DELETE from oa_uploader where employee_ID ='" + emp.getId() + "'", null);
				vixntBaseService.batchDeleteBySql("DELETE from hr_org_employee where id ='" + emp.getId() + "'", null);
			}
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public UserAccount getEntity() {
		return entity;
	}

	public void setEntity(UserAccount entity) {
		this.entity = entity;
	}

	public UserAccount getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(UserAccount entityForm) {
		this.entityForm = entityForm;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
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

	public String getAddUserAccountRoleIds() {
		return addUserAccountRoleIds;
	}

	public void setAddUserAccountRoleIds(String addUserAccountRoleIds) {
		this.addUserAccountRoleIds = addUserAccountRoleIds;
	}

	public String getRolenames() {
		return rolenames;
	}

	public void setRolenames(String rolenames) {
		this.rolenames = rolenames;
	}

}