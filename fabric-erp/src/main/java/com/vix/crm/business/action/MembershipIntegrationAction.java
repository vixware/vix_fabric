package com.vix.crm.business.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.vixdata.dataappinterface.IDataAccuracy;
import com.vix.common.vixlog.IOperateLog;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.MembershipIntegrationController;
import com.vix.crm.business.entity.CrmMember;
import com.vix.crm.business.entity.CustomerAndCustomerAccount;
import com.vix.crm.business.hql.OrderManagementHqlProvider;
import com.vix.crm.business.service.IOrderManagementService;
import com.vix.crm.business.util.CustomerCombinedCollections;
import com.vix.crm.business.util.CustomerCombinedModel;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.BusinessCustomer;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderDetail;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

/**
 * 会员整合管理
 * 
 * com.vix.crm.business.action.MembershipIntegrationAction
 *
 * @author zhanghaibing
 *
 * @date 2014年12月29日
 */
@Controller
@Scope("prototype")
public class MembershipIntegrationAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MembershipIntegrationController membershipIntegrationController;
	@Autowired
	private OrderManagementHqlProvider orderManagementHqlProvider;
	@Autowired
	private IOrderManagementService orderManagementService;
	@Autowired
	private IDataAccuracy dataAccuracy;
	@Autowired
	public IOperateLog vixOperateLog;
	private String pageNo;
	private String ids;

	@Override
	public String goList() {
		return GO_LIST;
	}

	/**
	 * 待同步会员
	 * 
	 * @return
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("status," + SearchCondition.EQUAL, "1");
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}

			pager = membershipIntegrationController.doListCustomerPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 合并后的会员信息
	 * 
	 * @return
	 */
	public String goSingleList2() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");

			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			// 处理查询条件
			String buyerNick = getDecodeRequestParameter("buyerNick");
			if (null != buyerNick && !"".equals(buyerNick)) {
				params.put("name," + SearchCondition.ANYLIKE, buyerNick.trim());
			}
			String receiverMobile = getRequestParameter("receiverMobile");
			if (null != receiverMobile && !"".equals(receiverMobile)) {
				params.put("mobilePhone," + SearchCondition.EQUAL, receiverMobile.trim());
			}
			// 处理查询条件
			pager = membershipIntegrationController.doListCustomerAccountPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSingleList2";
	}

	/**
	 * 获取整合日志
	 * 
	 * @return
	 */
	public String goSingleList1() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("url," + SearchCondition.EQUAL, "MembershipIntegrationAction");
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = membershipIntegrationController.doListLatestOperateEntityPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSingleList1";
	}

	/**
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * status 会员状态 1 未处理 2 已同步
	 * 
	 * @return
	 */
	public String goSyncCustomerAccount() {
		Map<String, Object> params = getParams();
		params.put("status," + SearchCondition.NOEQUAL, "2");
		try {
			List<BusinessCustomer> customerList = orderManagementService.findAllByConditions(BusinessCustomer.class, params);
			syncCustomerAccount(customerList);
			setMessage("会员整合成功");
		} catch (Exception e) {
			setMessage("会员整合失败");
			e.printStackTrace();
		}
		return UPDATE;
	}

	// 同步会员
	public void syncCustomerAccount(List<BusinessCustomer> businessCustomerList) {

		try {
			CustomerCombinedCollections customerCombinedCollections = dealCustomerAccountByCustomer(businessCustomerList);
			dealCustomer(customerCombinedCollections.getCustomerCombinedModelMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private CustomerCombinedCollections dealCustomerAccountByCustomer(List<BusinessCustomer> businessCustomerList) throws Exception {
		CustomerCombinedCollections customerCombinedCollections = new CustomerCombinedCollections();
		if (businessCustomerList != null && businessCustomerList.size() > 0) {
			for (BusinessCustomer businessCustomer : businessCustomerList) {
				CustomerCombinedModel model = new CustomerCombinedModel();
				model.setOuterId(businessCustomer.getOuterId());
				model.setCustomerName(businessCustomer.getCustomerName());
				model.setMobile(businessCustomer.getMobile());
				model.setTel(businessCustomer.getTel());
				model.setEmail(businessCustomer.getEmail());
				model.setAddr(businessCustomer.getAddr());
				model.setChannelDistributorId(businessCustomer.getChannelDistributor().getId());
				model.setBusinessCustomerId(businessCustomer.getId());
				customerCombinedCollections.addParam(model);
			}
		}
		return customerCombinedCollections;
	}

	/**
	 * 处理会员
	 * 
	 * @param customerCombinedCollections
	 */
	private void dealCustomer(Map<String, List<CustomerCombinedModel>> customerCombinedModelList) throws Exception {
		Set<Entry<String, List<CustomerCombinedModel>>> entries = customerCombinedModelList.entrySet();
		for (Iterator<Entry<String, List<CustomerCombinedModel>>> iterator = entries.iterator(); iterator.hasNext();) {
			Entry<String, List<CustomerCombinedModel>> entry = iterator.next();
			List<CustomerCombinedModel> list = entry.getValue();
			// 如果大于1需要合并
			if (list != null && list.size() > 0) {
				String pkValue = null;
				// 先移除掉已经归并过的会员
				for (Iterator<CustomerCombinedModel> modeIterator = list.iterator(); modeIterator.hasNext();) {
					CustomerCombinedModel customerCombinedModel = modeIterator.next();
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("name", customerCombinedModel.getCustomerName());
					params.put("mobilePhone", customerCombinedModel.getMobile());
					params.put("addr", customerCombinedModel.getAddr());
					StringBuilder hql = orderManagementHqlProvider.findCustomerAccount(params);
					CustomerAccount customerAccount = orderManagementService.findObjectByHql(hql.toString(), params);
					if (customerAccount != null) {
						pkValue = customerAccount.getId();
						// 从队列移除已归并会员
						modeIterator.remove();
					}
				}
				if (list == null || list.size() == 0) {
					continue;
				}
				CustomerCombinedModel firstCustomerCombinedModel = list.get(0);
				CustomerAccount customerAccount = null;
				if (pkValue == null) {
					// 保存会员信息
					customerAccount = new CustomerAccount();
					customerAccount.setName(firstCustomerCombinedModel.getCustomerName());
					// 处理中文索引
					String py = ChnToPinYin.getPYString(firstCustomerCombinedModel.getCustomerName());
					customerAccount.setChineseCharacter(py.toUpperCase());

					customerAccount.setMobilePhone(firstCustomerCombinedModel.getMobile());
					customerAccount.setTelephone(firstCustomerCombinedModel.getTel());
					customerAccount.setAddress(firstCustomerCombinedModel.getAddr());
					customerAccount.setIsTemp("");
					customerAccount.setCode(firstCustomerCombinedModel.getOuterId());
					customerAccount.setType("personal");
					customerAccount.setEmail(firstCustomerCombinedModel.getEmail());
					if (firstCustomerCombinedModel.getChannelDistributorId() != null) {
						ChannelDistributor channelDistributor = orderManagementService.findEntityById(ChannelDistributor.class, firstCustomerCombinedModel.getChannelDistributorId());
						if (channelDistributor != null) {
							customerAccount.setChannelDistributor(channelDistributor);
						}
					}
					if (firstCustomerCombinedModel.getChannelDistributorId() != null) {
						ChannelDistributor channelDistributor = orderManagementService.findEntityById(ChannelDistributor.class, firstCustomerCombinedModel.getChannelDistributorId());
						customerAccount.setMemberSource(channelDistributor.getName());
					}
					initEntityBaseController.initEntityBaseAttribute(customerAccount);
					customerAccount = orderManagementService.merge(customerAccount);
					vixOperateLog.saveOperateLog("", "", "MembershipIntegrationAction", "会员: " + customerAccount.getName() + " 整合完成");
				} else {
					// 修改会员信息 暂不做处理
					customerAccount = orderManagementService.findEntityById(CustomerAccount.class, pkValue);
					if (firstCustomerCombinedModel.getChannelDistributorId() != null) {
						ChannelDistributor channelDistributor = orderManagementService.findEntityById(ChannelDistributor.class, firstCustomerCombinedModel.getChannelDistributorId());
						customerAccount.setMemberSource(channelDistributor.getName());
					}
					customerAccount = orderManagementService.merge(customerAccount);
				}
				for (int j = 0; j < list.size(); j++) {
					CustomerCombinedModel customerCombinedModel = list.get(j);
					// 将被合并的会员跟新合并后的会员建立关联
					// 保存关联表
					CustomerAndCustomerAccount customerAndCustomerAccount = new CustomerAndCustomerAccount();
					BusinessCustomer businessCustomer = null;
					if (customerCombinedModel.getBusinessCustomerId() != null) {
						businessCustomer = orderManagementService.findEntityById(BusinessCustomer.class, customerCombinedModel.getBusinessCustomerId());
						if (businessCustomer != null) {
							customerAndCustomerAccount.setCustomer(businessCustomer);
						}
					}
					customerAndCustomerAccount.setCustomerAccount(customerAccount);
					customerAndCustomerAccount.setChannelDistributorId(customerCombinedModel.getChannelDistributorId());
					customerAndCustomerAccount = orderManagementService.merge(customerAndCustomerAccount);
					// 更改会员状态为已同步
					businessCustomer.setStatus("2");
					businessCustomer = orderManagementService.merge(businessCustomer);
					CrmMember crmMember = saveOrUpdateCrmMember(customerAccount, customerCombinedModel.getChannelDistributorId());
					// 通过会员获取订单信息
					Map<String, Object> orderparams = new HashMap<String, Object>();
					orderparams.put("customer.id," + SearchCondition.EQUAL, customerCombinedModel.getBusinessCustomerId());
					List<Order> orderList = orderManagementService.findAllByConditions(Order.class, orderparams);
					if (orderList != null && orderList.size() > 0) {
						for (Order order : orderList) {
							SalesOrder salesOrder = saveSalesOrder(order, customerAccount);
							updateCrmMember(crmMember, salesOrder);
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param customerAccount
	 * @param channelDistributorId
	 * @return
	 */
	public CrmMember saveOrUpdateCrmMember(CustomerAccount customerAccount, String channelDistributorId) {
		CrmMember crmMember = null;

		try {
			if (customerAccount != null) {
				crmMember = orderManagementService.findEntityByAttribute(CrmMember.class, "customerAccount.id", customerAccount.getId());
				if (crmMember != null) {

				} else {
					crmMember = new CrmMember();
					crmMember.setLastTradeTime(customerAccount.getUpdateTime());
					crmMember.setFirstTradeTime(customerAccount.getCreateTime());
					crmMember.setCustomerAccount(customerAccount);
					crmMember.setChannelDistributorId(channelDistributorId);
					initEntityBaseController.initEntityBaseAttribute(crmMember);
					crmMember = orderManagementService.merge(crmMember);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return crmMember;
	}

	/**
	 * 同步订单
	 * 
	 * @throws Exception
	 */
	public SalesOrder saveSalesOrder(Order order, CustomerAccount customerAccount) throws Exception {
		SalesOrder salesOrder = null;
		salesOrder = new SalesOrder();
		salesOrder.setOuterId(order.getOuterId());
		salesOrder.setNum(order.getNum());
		salesOrder.setTotalFee(order.getTotalFee());
		salesOrder.setCreateTime(order.getCreateTime());
		salesOrder.setPayTime(order.getPayTime());
		salesOrder.setUpdateTime(order.getUpdateTime());
		salesOrder.setBuyerMessage(order.getBuyerMessage());
		salesOrder.setSellerMemo(order.getSellerMemo());
		salesOrder.setBuyerNick(order.getBuyerNick());
		salesOrder.setBuyerMemo(order.getBuyerMemo());
		salesOrder.setPayment(dataAccuracy.getAmountDecimal(Double.parseDouble(order.getPayment())));
		salesOrder.setPostFee(order.getPostFee());
		salesOrder.setPayTypeCn(order.getPayTypeCn());
		salesOrder.setReceiverAddress(order.getReceiverAddress());
		salesOrder.setReceiverMobile(order.getReceiverMobile());
		salesOrder.setDealState(order.getDealState());
		salesOrder.setOrderCreateDate(order.getCreateTime());
		salesOrder.setIsTemp("");
		salesOrder.setChannelDistributor(order.getChannelDistributor());
		if (customerAccount != null) {
			salesOrder.setCustomerAccount(customerAccount);
		}
		initEntityBaseController.initEntityBaseAttribute(salesOrder);
		salesOrder = orderManagementService.merge(salesOrder);
		if (order.getOrderDetailList() != null && order.getOrderDetailList().size() > 0) {
			for (OrderDetail orderDetail : order.getOrderDetailList()) {
				saveSaleOrderItem(orderDetail, salesOrder, customerAccount);
			}
		}
		return salesOrder;
	}

	public void saveSaleOrderItem(OrderDetail orderDetail, SalesOrder salesOrder, CustomerAccount customerAccount) throws Exception {
		SaleOrderItem saleOrderItem = new SaleOrderItem();
		saleOrderItem.setTenantId(SecurityUtil.getCurrentUserTenantId());
		saleOrderItem.setBuyerNick(orderDetail.getBuyerNick());
		saleOrderItem.setOuterGoodsId(orderDetail.getOuterGoodsId());
		saleOrderItem.setOuterSkuId(orderDetail.getOuterGoodsId());
		saleOrderItem.setIsOverSold(orderDetail.getIsOverSold());
		saleOrderItem.setNum(orderDetail.getNum());
		saleOrderItem.setOuterId(orderDetail.getOuterId());
		saleOrderItem.setPayment(dataAccuracy.getAmountDecimal(Double.parseDouble(orderDetail.getPayment())));
		saleOrderItem.setBn(orderDetail.getBn());
		saleOrderItem.setTradeNo(orderDetail.getTradeNo());
		saleOrderItem.setPrice(orderDetail.getPrice());
		saleOrderItem.setSellerNick(orderDetail.getSellerNick());
		saleOrderItem.setSellerType(orderDetail.getSellerType());
		saleOrderItem.setSkuPropertiesName(orderDetail.getSkuPropertiesName());
		saleOrderItem.setTitle(orderDetail.getTitle());
		saleOrderItem.setConfirm(orderDetail.getConfirm());
		saleOrderItem.setStatus(orderDetail.getStatus());
		saleOrderItem.setPayStatus(orderDetail.getPayStatus());
		saleOrderItem.setShipStatus(orderDetail.getShipStatus());
		saleOrderItem.setUserStatus(orderDetail.getUserStatus());
		saleOrderItem.setIsDelivery(orderDetail.getIsDelivery());
		saleOrderItem.setDiscountFee(orderDetail.getDiscountFee());
		saleOrderItem.setStatus("未配货");
		saleOrderItem.setSalesOrder(salesOrder);
		saleOrderItem.setCustomerAccount(customerAccount);
		initEntityBaseController.initEntityBaseAttribute(saleOrderItem);
		saleOrderItem = orderManagementService.merge(saleOrderItem);
	}

	/**
	 * 更新会员消费记录表
	 * 
	 * @param crmMember
	 * @param salesOrder
	 * @return
	 * @throws Exception
	 */
	public CrmMember updateCrmMember(CrmMember crmMember, SalesOrder salesOrder) throws Exception {
		if (crmMember != null && salesOrder != null) {
			if (crmMember.getTradeCount() != null) {
				crmMember.setTradeCount(crmMember.getTradeCount() + 1);
			} else {
				crmMember.setTradeCount(1L);
			}
			if (salesOrder.getTotalFee() != null) {
				if (crmMember.getTradeAmount() != null) {
					crmMember.setTradeAmount(crmMember.getTradeAmount() + Double.parseDouble(salesOrder.getTotalFee()));
				} else {
					crmMember.setTradeAmount(Double.parseDouble(salesOrder.getTotalFee()));
				}
			}
			if (crmMember.getItemNum() != null) {
				// crmMember.setItemNum(crmMember.getItemNum() +
				// salesOrder.getNum());
			} else {
				// crmMember.setItemNum(salesOrder.getNum());
			}
			crmMember.setLastTradeTime(salesOrder.getCreated());
			crmMember = orderManagementService.merge(crmMember);
		}
		return crmMember;
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

}
