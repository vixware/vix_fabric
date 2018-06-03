package com.vix.drp.customerAndSalesDistribution.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.customerAndSalesDistribution.controller.CustomerAndSalesDistributionController;
import com.vix.drp.customerAndSalesDistribution.entity.Customer;
import com.vix.drp.customerAndSalesDistribution.entity.SalesDistribution;

@Controller
@Scope("prototype")
public class CustomerAndSalesDistributionAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CustomerAndSalesDistributionController customerAndSalesDistributionController;
	private String id;
	private ChannelDistributor channelDistributor;
	private List<ChannelDistributor> channelDistributorList;
	/**
	 * 客户
	 */
	private Customer customer;
	/**
	 * 销售分布
	 */
	private SalesDistribution salesDistribution;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("type," + SearchCondition.ANYLIKE, "5");
			Pager pager = customerAndSalesDistributionController.findPagerByHqlConditions(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				channelDistributor = customerAndSalesDistributionController.doListChannelDistributorById(id);
			} else {
				channelDistributor = new ChannelDistributor();
				channelDistributor.setIsTemp("1");
				channelDistributor = customerAndSalesDistributionController.doSaveChannelDistributor(channelDistributor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 
	 * @return
	 */
	public String goSaveOrUpdateCustomer() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				customer = customerAndSalesDistributionController.doListCustomerById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateCustomer";
	}

	/**
	 * 
	 * @return
	 */
	public String goSaveOrUpdateSalesDistribution() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				salesDistribution = customerAndSalesDistributionController.doListSalesDistributionById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateSalesDistribution";
	}

	/**
	 * 保存渠道
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != channelDistributor.getId() && !"".equals(channelDistributor.getId())) {
				isSave = false;
			}
			channelDistributor.setType("5");
			channelDistributor.setIsTemp("");
			channelDistributor = customerAndSalesDistributionController.doSaveChannelDistributor(channelDistributor);
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

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdateCustomer() {
		boolean isSave = true;
		try {
			if (null != customer.getId() && !"".equals(customer.getId())) {
				isSave = false;
			}
			channelDistributor = customerAndSalesDistributionController.doListChannelDistributorById(id);
			customer.setChannelDistributor(channelDistributor);
			customer = customerAndSalesDistributionController.doSaveCustomer(customer);
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

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdateSalesDistribution() {
		boolean isSave = true;
		try {
			if (null != salesDistribution.getId() && !"".equals(salesDistribution.getId())) {
				isSave = false;
			}
			channelDistributor = customerAndSalesDistributionController.doListChannelDistributorById(id);
			salesDistribution.setChannelDistributor(channelDistributor);
			salesDistribution = customerAndSalesDistributionController.doSaveSalesDistribution(salesDistribution);
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
			ChannelDistributor channelDistributor = customerAndSalesDistributionController.doListChannelDistributorById(id);
			if (null != channelDistributor) {
				customerAndSalesDistributionController.doDeleteByEntity(channelDistributor);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteCustomerById() {
		try {
			Customer customer = customerAndSalesDistributionController.doListCustomerById(id);
			if (null != customer) {
				customerAndSalesDistributionController.doDeleteCustomerByEntity(customer);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteSalesDistributionById() {
		try {
			SalesDistribution salesDistribution = customerAndSalesDistributionController.doListSalesDistributionById(id);
			if (null != salesDistribution) {
				customerAndSalesDistributionController.doDeleteSalesDistributionByEntity(salesDistribution);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getCustomerJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ChannelDistributor channelDistributor = customerAndSalesDistributionController.doListChannelDistributorById(id);
				if (channelDistributor != null) {
					json = convertListToJson(new ArrayList<Customer>(channelDistributor.getCustomers()), channelDistributor.getCustomers().size());
				}
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

	public void getSalesDistributionJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ChannelDistributor channelDistributor = customerAndSalesDistributionController.doListChannelDistributorById(id);
				if (channelDistributor != null) {
					json = convertListToJson(new ArrayList<SalesDistribution>(channelDistributor.getSalesDistributions()), channelDistributor.getSalesDistributions().size());
				}
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

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public SalesDistribution getSalesDistribution() {
		return salesDistribution;
	}

	public void setSalesDistribution(SalesDistribution salesDistribution) {
		this.salesDistribution = salesDistribution;
	}

}
