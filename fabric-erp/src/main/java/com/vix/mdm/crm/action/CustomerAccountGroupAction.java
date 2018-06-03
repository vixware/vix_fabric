package com.vix.mdm.crm.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.entity.CustomerAccountGroup;

@Controller
@Scope("prototype")
public class CustomerAccountGroupAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private String ids;
	private CustomerAccountGroup customerAccountGroup;
	private String pageNo;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), CustomerAccountGroup.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), CustomerAccountGroup.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				customerAccountGroup = baseHibernateService.findEntityById(CustomerAccountGroup.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			} else {
				customerAccountGroup = new CustomerAccountGroup();
				customerAccountGroup.setCreateTime(new Date());
				loadCommonData(customerAccountGroup);
				customerAccountGroup = baseHibernateService.merge(customerAccountGroup);
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
			if (null != customerAccountGroup.getId()) {
				isSave = false;
			}
			customerAccountGroup = baseHibernateService.merge(customerAccountGroup);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			CustomerAccountGroup pb = baseHibernateService.findEntityById(CustomerAccountGroup.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
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

	public String goChooseCustomerAccountGroup() {
		return "goChooseCustomerAccountGroup";
	}

	public void getCustomerAccountJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				CustomerAccountGroup pg = baseHibernateService.findEntityById(CustomerAccountGroup.class, id);
				json = convertListToJson(new ArrayList<CustomerAccount>(pg.getCustomerAccounts()), pg.getCustomerAccounts().size(), "customerAccountGroup");
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

	public String addCustomerAccountById() {
		try {
			CustomerAccountGroup pg = baseHibernateService.findEntityById(CustomerAccountGroup.class, id);
			if (null != pg) {
				String customerAccountId = getRequestParameter("customerAccountId");
				if (null != customerAccountId && !"".equals(customerAccountId)) {
					CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, customerAccountId);
					if (null != customerAccount && !pg.getCustomerAccounts().contains(customerAccount)) {
						customerAccount.setCustomerAccountGroup(pg);
						baseHibernateService.merge(customerAccount);
						setMessage("添加成功!");
						return UPDATE;
					}
				}
			}
			setMessage(getText("mdm_customerAccountNotExist"));
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("添加失败!");
		}
		return UPDATE;
	}

	public String deleteCustomerAccountById() {
		try {
			CustomerAccountGroup pg = baseHibernateService.findEntityById(CustomerAccountGroup.class, id);
			if (null != pg) {
				String customerAccountId = getRequestParameter("customerAccountId");
				if (null != customerAccountId && !"".equals(customerAccountId)) {
					for (CustomerAccount customerAccount : pg.getCustomerAccounts()) {
						if (customerAccount.getId() == customerAccountId) {
							customerAccount.setCustomerAccountGroup(null);
							baseHibernateService.merge(customerAccount);
							renderText(DELETE_SUCCESS);
							return UPDATE;
						}
					}
				}
			}
			setMessage(getText("mdm_customerAccountNotExist"));
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
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

	public CustomerAccountGroup getCustomerAccountGroup() {
		return customerAccountGroup;
	}

	public void setCustomerAccountGroup(CustomerAccountGroup customerAccountGroup) {
		this.customerAccountGroup = customerAccountGroup;
	}
}
