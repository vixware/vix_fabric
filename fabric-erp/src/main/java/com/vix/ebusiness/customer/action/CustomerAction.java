package com.vix.ebusiness.customer.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.ebusiness.customer.controller.CustomerController;
import com.vix.ebusiness.entity.BusinessCustomer;

@Controller
@Scope("prototype")
public class CustomerAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(CustomerAction.class);
	@Autowired
	private CustomerController customerController;
	private String pageNo;
	/* 销售订单 */
	private BusinessCustomer customer;
	private List<BusinessCustomer> customerList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			customerList = customerController.doListCustomerList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 根据条件查询入库单信息
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = customerController.doListCustomerPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public BusinessCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(BusinessCustomer customer) {
		this.customer = customer;
	}

	public List<BusinessCustomer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<BusinessCustomer> customerList) {
		this.customerList = customerList;
	}

}
