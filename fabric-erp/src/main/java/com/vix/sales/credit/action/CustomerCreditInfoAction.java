package com.vix.sales.credit.action;

import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.sales.credit.entity.CustomerCreditInfo;

@Controller
@Scope("prototype")
public class CustomerCreditInfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private CustomerCreditInfo customerCreditInfo;
	private String pageNo;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			String customerName = getRequestParameter("customerName");
			if (null != customerName && !"".equals(customerName)) {
				customerName = decode(customerName, "UTF-8");
				params.put("customerAccount.name," + SearchCondition.ANYLIKE,
						customerName);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), CustomerCreditInfo.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				customerCreditInfo = baseHibernateService.findEntityById(
						CustomerCreditInfo.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
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
			if (null != customerCreditInfo.getId()) {
				isSave = false;
			} else {
				customerCreditInfo.setCreateTime(new Date());
				loadCommonData(customerCreditInfo);
			}
			if (null == customerCreditInfo.getCustomerAccount()
					|| null != customerCreditInfo.getCustomerAccount().getId()
					|| !customerCreditInfo.getCustomerAccount().getId()
							.equals("")
					|| !customerCreditInfo.getCustomerAccount().getId()
							.equals("0")) {
				customerCreditInfo.setCustomerAccount(null);
			}
			if (null == customerCreditInfo.getCreditChecker()
					|| null != customerCreditInfo.getCreditChecker().getId()
					|| !customerCreditInfo.getCreditChecker().getId()
							.equals("")
					|| !customerCreditInfo.getCreditChecker().getId()
							.equals("0")) {
				customerCreditInfo.setCustomerAccount(null);
			}
			baseHibernateService.merge(customerCreditInfo);
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
			CustomerCreditInfo pb = baseHibernateService.findEntityById(
					CustomerCreditInfo.class, id);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public CustomerCreditInfo getCustomerCreditInfo() {
		return customerCreditInfo;
	}

	public void setCustomerCreditInfo(CustomerCreditInfo customerCreditInfo) {
		this.customerCreditInfo = customerCreditInfo;
	}
}