package com.vix.nvix.sales.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.security.util.DaysUtils;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.refund.entity.ReturnRule;
import com.vix.sales.refund.entity.ReturnRuleItem;
import com.vix.sales.refund.entity.SaleReturnBill;
import com.vix.sales.refund.entity.SaleReturnBillDetail;

@Controller
@Scope("prototype")
public class NvixntSaleReturnBillAction extends VixntBaseAction {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private SaleReturnBill saleReturnBill;
	private String name;
	private String ids;
	
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(name)) {
				params.put("name,"+SearchCondition.EQUAL, name);
			}
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("rbDate");
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, SaleReturnBill.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String showSaleReturnBill() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				saleReturnBill = vixntBaseService.findEntityById(SaleReturnBill.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showSaleReturnBill";
	}
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				saleReturnBill = vixntBaseService.findEntityById(SaleReturnBill.class, id);
			}else {
				saleReturnBill = new SaleReturnBill();
				saleReturnBill.setCode(VixUUID.createCode(15));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(saleReturnBill.getId())) {
				isSave = false;
				saleReturnBill.setUpdateTime(new Date());
			}else {
				saleReturnBill.setCreateTime(new Date());
			}
			saleReturnBill.setStatus("1");//未返款
			initEntityBaseController.initEntityBaseAttribute(saleReturnBill);
			saleReturnBill = vixntBaseService.merge(saleReturnBill);
			if(isSave) {
				renderText(SAVE_SUCCESS);
			}else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if(isSave) {
				renderText(SAVE_FAIL);
			}else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}
	public String goChooseCondition() {
		return "goChooseCondition";
	}
	public void deleteById() {
		try {
			if(StringUtils.isNotBlank(id)) {
				saleReturnBill = vixntBaseService.findEntityById(SaleReturnBill.class, id);
				if(saleReturnBill != null) {
					vixntBaseService.deleteByEntity(saleReturnBill);
					renderText(DELETE_SUCCESS);
				}else {
					renderText("未找到返利单!");
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}
	private String customerAccountId;
	private String itemId;
	private String startTime;
	private String endTime;
	public String goChooseSaleOrderItem() {
		
		return "goChooseSaleOrderItem";
	}
	public void goSingleListForSaleOrder() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			params.put("isSaleReturnBillCalculation,"+ SearchCondition.NOEQUAL, "1");//销售明细未参与计算
			params.put("salesOrder.isTemp," + SearchCondition.EQUAL,"0");
			params.put("salesOrder.isComplate," + SearchCondition.EQUAL,"1");// 销售订单已完成
			params.put("salesOrder.billDate,"+ SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(startTime)) + ":01!"+ DaysUtils.getEndDay(dateFormat.parse(endTime)) + ":01");
			if(StringUtils.isNotEmpty(itemId)) {
				params.put("item.id,"+SearchCondition.EQUAL, itemId);
			}
			if(StringUtils.isNotEmpty(customerAccountId)) {
				params.put("salesOrder.customerAccount.id,"+ SearchCondition.EQUAL,customerAccountId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, SaleOrderItem.class, params);
			}
			String [] excludes = {" "};
 			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createSaleReturnBill() {
		try {
			if(StringUtils.isNotEmpty(ids)) {
				String [] orderItemIds = ids.split(",");
				if(orderItemIds != null && orderItemIds.length > 0 && StringUtils.isNotEmpty(customerAccountId)) {
					List<ReturnRule> returnRuleList = vixntBaseService.findAllByEntityClassAndAttribute(ReturnRule.class, "customerAccount.id", customerAccountId);
					ReturnRule returnRule = null;
					if(returnRuleList != null && returnRuleList.size() > 0) {
						returnRule = returnRuleList.get(0);
					}
					Double totalAmount = 0d;// 客户订单总金额
					Double totalCount = 0d;// 客户订单总数量
					List<SaleOrderItem> saleOrderItemList = new ArrayList<SaleOrderItem>();
					for (String orderItemId : orderItemIds) {
						if(StringUtils.isNotEmpty(orderItemId)) {
							SaleOrderItem saleOrderItem = vixntBaseService.findEntityById(SaleOrderItem.class, orderItemId);
							if(saleOrderItem != null) {
								saleOrderItemList.add(saleOrderItem);
								totalAmount += saleOrderItem.getAmount() * saleOrderItem.getPrice();
								totalCount += saleOrderItem.getAmount();
							}
						}
					}
					//根据客户和物料计算
					if(StringUtils.isNotEmpty(customerAccountId)&& StringUtils.isNotBlank(itemId) && saleOrderItemList != null && saleOrderItemList.size() > 0) {
						/** 根据用户设定的金额明细计算 */
						if (null == returnRule.getRatio() && null != returnRule.getDetailType()
								&& returnRule.getDetailType().equals("money")) {
							for (ReturnRuleItem returnRuled : returnRule.getReturnRuleItems()) {
								if (null != returnRuled.getFrom() && returnRuled.getFrom() > 0
										&& null != returnRuled.getTo() && returnRuled.getTo() > 0) {
									if (totalAmount >= returnRuled.getFrom()&& totalAmount <= returnRuled.getTo()) {
										SaleReturnBill saleReturnBill = new SaleReturnBill();
										saleReturnBill.setCustomerAccount(returnRule.getCustomerAccount());
										saleReturnBill.setRbDate(new Date());
										saleReturnBill.setReturnAmount(totalAmount * returnRuled.getRatio() * 0.01);
										saleReturnBill.setCurrencyType(returnRuled.getCurrencyType());
										saleReturnBill = baseHibernateService.merge(saleReturnBill);
										for (SaleOrderItem saleOrderItem : saleOrderItemList) {
											SaleReturnBillDetail saleReturnBillDetail = new SaleReturnBillDetail();
											saleReturnBillDetail.setBillCode(saleOrderItem.getId().toString());
											saleReturnBillDetail.setBillType("SaleOrderItem");
											saleReturnBillDetail.setItem(saleOrderItem.getItem());
											saleReturnBillDetail.setAmount(saleOrderItem.getAmount());
											saleReturnBillDetail.setAcount(saleOrderItem.getAmount() * saleOrderItem.getPrice());
											saleReturnBillDetail.setTotalAmount(saleOrderItem.getAmount() * saleOrderItem.getPrice() * returnRuled.getRatio() * 0.01);
											saleReturnBillDetail.setSaleReturnBill(saleReturnBill);
											saleReturnBillDetail.setSaleOrderItem(saleOrderItem);
											baseHibernateService.merge(saleReturnBillDetail);
											saleOrderItem.setIsSaleReturnBillCalculation("1");
											saleOrderItem = vixntBaseService.merge(saleOrderItem);
										}
										renderText("1:计算成功!:"+saleReturnBill.getId());
										return;
									}
								}
							}
						}
						/** 根据用户设定的数量明细计算 */
						if (null == returnRule.getRatio() && null != returnRule.getDetailType()
								&& returnRule.getDetailType().equals("count")) {
							for (ReturnRuleItem returnRuled : returnRule.getReturnRuleItems()) {
								if (null != returnRuled.getFrom() && returnRuled.getFrom() > 0 && null != returnRuled.getTo() && returnRuled.getTo() > 0) {
									if (totalCount >= returnRuled.getFrom() && totalAmount <= returnRuled.getTo()) {
										SaleReturnBill saleReturnBill = new SaleReturnBill();
										saleReturnBill.setCustomerAccount(returnRule.getCustomerAccount());
										saleReturnBill.setRbDate(new Date());
										saleReturnBill.setReturnAmount(totalAmount * returnRuled.getRatio() * 0.01);
										saleReturnBill.setCurrencyType(returnRuled.getCurrencyType());
										saleReturnBill = baseHibernateService.merge(saleReturnBill);
										for (SaleOrderItem saleOrderItem : saleOrderItemList) {
											SaleReturnBillDetail saleReturnBillDetail = new SaleReturnBillDetail();
											saleReturnBillDetail.setBillCode(saleOrderItem.getId().toString());
											saleReturnBillDetail.setBillType("SaleOrderItem");
											saleReturnBillDetail.setAcount(saleOrderItem.getAmount() * saleOrderItem.getPrice());
											saleReturnBillDetail.setTotalAmount(saleOrderItem.getAmount() * saleOrderItem.getPrice() * returnRuled.getRatio() * 0.01);
											saleReturnBillDetail.setSaleReturnBill(saleReturnBill);
											saleReturnBillDetail.setItem(saleOrderItem.getItem());
											saleReturnBillDetail.setAmount(saleOrderItem.getAmount());
											saleReturnBillDetail.setSaleOrderItem(saleOrderItem);
											baseHibernateService.merge(saleReturnBillDetail);
											saleOrderItem.setIsSaleReturnBillCalculation("1");
											saleOrderItem = vixntBaseService.merge(saleOrderItem);
										}
										renderText("1:计算成功!:"+saleReturnBill.getId());
										return;
									}
								}
							}
						}
						if (null != returnRule.getRatio() && returnRule.getRatio() > 0 && null != returnRule.getLowerSaleCount() && returnRule.getLowerSaleCount() > 0 && totalAmount > returnRule.getLowerSaleCount()) {
							SaleReturnBill saleReturnBill = new SaleReturnBill();
							saleReturnBill.setCustomerAccount(returnRule.getCustomerAccount());
							saleReturnBill.setRbDate(new Date());
							saleReturnBill.setReturnAmount(totalAmount * returnRule.getRatio() * 0.01);
							saleReturnBill = baseHibernateService.merge(saleReturnBill);
							for (SaleOrderItem saleOrderItem : saleOrderItemList) {
								SaleReturnBillDetail saleReturnBillDetail = new SaleReturnBillDetail();
								saleReturnBillDetail.setBillCode(saleOrderItem.getId());
								saleReturnBillDetail.setBillType("SaleOrderItem");
								saleReturnBillDetail.setAcount(saleOrderItem.getAmount() * saleOrderItem.getPrice());
								saleReturnBillDetail.setTotalAmount(saleOrderItem.getAmount() * saleOrderItem.getPrice() * returnRule.getRatio() *0.01);
								saleReturnBillDetail.setSaleReturnBill(saleReturnBill);
								saleReturnBillDetail.setItem(saleOrderItem.getItem());
								saleReturnBillDetail.setAmount(saleOrderItem.getAmount());
								saleReturnBillDetail.setSaleOrderItem(saleOrderItem);
								saleReturnBillDetail = vixntBaseService.merge(saleReturnBillDetail);
								saleOrderItem.setIsSaleReturnBillCalculation("1");
								saleOrderItem = vixntBaseService.merge(saleOrderItem);
							}
							renderText("1:计算成功!:"+saleReturnBill.getId());
							return;
						}
					}
					if (null == returnRule.getRatio() && null != returnRule.getDetailType() && returnRule.getDetailType().equals("money")) {
						for (ReturnRuleItem returnRuleItem : returnRule.getReturnRuleItems()) {
							if (null != returnRuleItem.getFrom() && returnRuleItem.getFrom() > 0 && null != returnRuleItem.getTo() && returnRuleItem.getTo() > 0) {
								if (totalAmount >= returnRuleItem.getFrom() && totalAmount <= returnRuleItem.getTo()) {
									SaleReturnBill saleReturnBill = new SaleReturnBill();
									saleReturnBill.setCustomerAccount(returnRule.getCustomerAccount());
									saleReturnBill.setRbDate(new Date());
									saleReturnBill.setReturnAmount(totalAmount * returnRuleItem.getRatio() * 0.01);
									saleReturnBill.setCurrencyType(returnRuleItem.getCurrencyType());
									saleReturnBill = baseHibernateService.merge(saleReturnBill);
									for (SaleOrderItem saleOrderItem : saleOrderItemList) {
										SaleReturnBillDetail saleReturnBillDetail = new SaleReturnBillDetail();
										saleReturnBillDetail.setBillCode(saleOrderItem.getId());
										saleReturnBillDetail.setBillType("SaleOrderItem");
										saleReturnBillDetail.setAcount(saleOrderItem.getAmount() * saleOrderItem.getPrice());
										saleReturnBillDetail.setTotalAmount(saleOrderItem.getAmount() * saleOrderItem.getPrice() * returnRuleItem.getRatio() *0.01);
										saleReturnBillDetail.setSaleReturnBill(saleReturnBill);
										saleReturnBillDetail.setItem(saleOrderItem.getItem());
										saleReturnBillDetail.setAmount(saleOrderItem.getAmount());
										saleReturnBillDetail.setSaleOrderItem(saleOrderItem);
										saleReturnBillDetail = vixntBaseService.merge(saleReturnBillDetail);
										saleOrderItem.setIsSaleReturnBillCalculation("1");
										saleOrderItem = vixntBaseService.merge(saleOrderItem);
									}
									renderText("1:计算成功!:"+saleReturnBill.getId());
									return;
								}
							}
						}
					}
					//根据客户计算
					if(returnRule != null && StringUtils.isNotEmpty(customerAccountId) && saleOrderItemList != null && saleOrderItemList.size() > 0) {
						if(returnRule.getRatio() != null && returnRule.getRatio() > 0) {
							SaleReturnBill saleReturnBill = new SaleReturnBill();
							saleReturnBill.setCustomerAccount(returnRule.getCustomerAccount());
							saleReturnBill.setRbDate(new Date());
							saleReturnBill.setReturnAmount(totalAmount * returnRule.getRatio() * 0.01);
							saleReturnBill = vixntBaseService.merge(saleReturnBill);
							for (SaleOrderItem saleOrderItem : saleOrderItemList) {
								SaleReturnBillDetail saleReturnBillDetail = new SaleReturnBillDetail();
								saleReturnBillDetail.setBillCode(saleOrderItem.getId());
								saleReturnBillDetail.setBillType("SaleOrderItem");
								saleReturnBillDetail.setAcount(saleOrderItem.getAmount() * saleOrderItem.getPrice());
								saleReturnBillDetail.setTotalAmount(saleOrderItem.getAmount() * saleOrderItem.getPrice() * returnRule.getRatio() *0.01);
								saleReturnBillDetail.setSaleReturnBill(saleReturnBill);
								saleReturnBillDetail.setItem(saleOrderItem.getItem());
								saleReturnBillDetail.setAmount(saleOrderItem.getAmount());
								saleReturnBillDetail.setSaleOrderItem(saleOrderItem);
								saleReturnBillDetail = vixntBaseService.merge(saleReturnBillDetail);
								saleOrderItem.setIsSaleReturnBillCalculation("1");
								saleOrderItem = vixntBaseService.merge(saleOrderItem);
								
							}
							renderText("1:计算成功!:"+saleReturnBill.getId());
							return;
						}
					}
				}
			}
			renderText("0:未找到合适的条件!");
		} catch (Exception e) {
			renderText("0:计算失败!");
			e.printStackTrace();
		}
	}
	/**
	 * 生成返利单
	 */
//	public void createSaleReturnBill1() {
//		try {
//			if(StringUtils.isNotEmpty(startTime)&&StringUtils.isNotEmpty(endTime)) {
//				Map<String, Object> params = getParams();
//				params.put("isSaleReturnBillCalculation,"+ SearchCondition.NOEQUAL, "1");//销售明细未参与计算
//				params.put("salesOrder.isTemp," + SearchCondition.EQUAL,"0");
//				params.put("salesOrder.isComplate," + SearchCondition.EQUAL,"1");// 销售订单已完成
//				params.put("salesOrder.billDate,"+ SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(startTime)) + ":01!"+ DaysUtils.getEndDay(dateFormat.parse(endTime)) + ":01");
//				params.put("salesOrder.customerAccount.id,"+ SearchCondition.EQUAL,customerAccountId);
//				if(StringUtils.isNotEmpty(itemId)) {
//					params.put("item.id,"+SearchCondition.EQUAL, itemId);
//				}
//				List<ReturnRule> returnRuleList = vixntBaseService.findAllByEntityClassAndAttribute(ReturnRule.class, "customerAccount.id", customerAccountId);
//				
//				List<SaleOrderItem> saleOrderItemList = vixntBaseService.findAllByConditions(SaleOrderItem.class, params);
//				Double totalAmount = 0d;// 客户订单总金额
//				Double totalCount = 0d;// 客户订单总数量
//				if(saleOrderItemList != null && saleOrderItemList.size() > 0) {
//					for (SaleOrderItem saleOrderItem : saleOrderItemList) {
//						totalAmount += saleOrderItem.getAmount() * saleOrderItem.getPrice();
//						totalCount += saleOrderItem.getAmount();
//					}
//				}
//				ReturnRule returnRule = null;
//				if(returnRuleList != null && returnRuleList.size() > 0) {
//					returnRule = returnRuleList.get(0);
//				}
//				//根据客户和物料计算
//				if(StringUtils.isNotEmpty(customerAccountId)&& StringUtils.isNotBlank(itemId) && saleOrderItemList != null && saleOrderItemList.size() > 0) {
//					/** 根据用户设定的金额明细计算 */
//					if (null == returnRule.getRatio() && null != returnRule.getDetailType()
//							&& returnRule.getDetailType().equals("money")) {
//						for (ReturnRuleItem returnRuled : returnRule.getReturnRuleItems()) {
//							if (null != returnRuled.getFrom() && returnRuled.getFrom() > 0
//									&& null != returnRuled.getTo() && returnRuled.getTo() > 0) {
//								if (totalAmount >= returnRuled.getFrom()&& totalAmount <= returnRuled.getTo()) {
//									SaleReturnBill saleReturnBill = new SaleReturnBill();
//									saleReturnBill.setCustomerAccount(returnRule.getCustomerAccount());
//									saleReturnBill.setRbDate(new Date());
//									saleReturnBill.setReturnAmount(totalAmount * returnRuled.getRatio() * 0.01);
//									saleReturnBill.setCurrencyType(returnRuled.getCurrencyType());
//									saleReturnBill = baseHibernateService.merge(saleReturnBill);
//									for (SaleOrderItem saleOrderItem : saleOrderItemList) {
//										SaleReturnBillDetail saleReturnBillDetail = new SaleReturnBillDetail();
//										saleReturnBillDetail.setBillCode(saleOrderItem.getId().toString());
//										saleReturnBillDetail.setBillType("SaleOrderItem");
//										saleReturnBillDetail.setItem(saleOrderItem.getItem());
//										saleReturnBillDetail.setAmount(saleOrderItem.getAmount());
//										saleReturnBillDetail.setTotalAmount(saleOrderItem.getAmount() * saleOrderItem.getPrice() * returnRule.getRatio() * 0.01);
//										saleReturnBillDetail.setSaleReturnBill(saleReturnBill);
//										baseHibernateService.merge(saleReturnBillDetail);
//										saleOrderItem.setIsSaleReturnBillCalculation("1");
//										saleOrderItem = vixntBaseService.merge(saleOrderItem);
//									}
//									renderText("计算成功!");
//									return;
//								}
//							}
//						}
//					}
//					/** 根据用户设定的数量明细计算 */
//					if (null == returnRule.getRatio() && null != returnRule.getDetailType()
//							&& returnRule.getDetailType().equals("count")) {
//						for (ReturnRuleItem returnRuled : returnRule.getReturnRuleItems()) {
//							if (null != returnRuled.getFrom() && returnRuled.getFrom() > 0 && null != returnRuled.getTo() && returnRuled.getTo() > 0) {
//								if (totalCount >= returnRuled.getFrom() && totalAmount <= returnRuled.getTo()) {
//									SaleReturnBill saleReturnBill = new SaleReturnBill();
//									saleReturnBill.setCustomerAccount(returnRule.getCustomerAccount());
//									saleReturnBill.setRbDate(new Date());
//									saleReturnBill.setReturnAmount(totalAmount * returnRuled.getRatio() * 0.01);
//									saleReturnBill.setCurrencyType(returnRuled.getCurrencyType());
//									saleReturnBill = baseHibernateService.merge(saleReturnBill);
//									for (SaleOrderItem saleOrderItem : saleOrderItemList) {
//										SaleReturnBillDetail saleReturnBillDetail = new SaleReturnBillDetail();
//										saleReturnBillDetail.setBillCode(saleOrderItem.getId().toString());
//										saleReturnBillDetail.setBillType("SaleOrderItem");
//										saleReturnBillDetail.setTotalAmount(saleOrderItem.getAmount() * saleOrderItem.getPrice() * returnRule.getRatio() * 0.01);
//										saleReturnBillDetail.setSaleReturnBill(saleReturnBill);
//										saleReturnBillDetail.setItem(saleOrderItem.getItem());
//										saleReturnBillDetail.setAmount(saleOrderItem.getAmount());
//										baseHibernateService.merge(saleReturnBillDetail);
//										saleOrderItem.setIsSaleReturnBillCalculation("1");
//										saleOrderItem = vixntBaseService.merge(saleOrderItem);
//									}
//									renderText("计算成功!");
//									return;
//								}
//							}
//						}
//					}
//					if (null != returnRule.getRatio() && returnRule.getRatio() > 0 && null != returnRule.getLowerSaleCount() && returnRule.getLowerSaleCount() > 0 && totalAmount > returnRule.getLowerSaleCount()) {
//						SaleReturnBill saleReturnBill = new SaleReturnBill();
//						saleReturnBill.setCustomerAccount(returnRule.getCustomerAccount());
//						saleReturnBill.setRbDate(new Date());
//						saleReturnBill.setReturnAmount(totalAmount * returnRule.getRatio() * 0.01);
//						saleReturnBill = baseHibernateService.merge(saleReturnBill);
//						for (SaleOrderItem saleOrderItem : saleOrderItemList) {
//							SaleReturnBillDetail saleReturnBillDetail = new SaleReturnBillDetail();
//							saleReturnBillDetail.setBillCode(saleOrderItem.getId());
//							saleReturnBillDetail.setBillType("SaleOrderItem");
//							saleReturnBillDetail.setTotalAmount(saleOrderItem.getAmount() * saleOrderItem.getPrice() * returnRule.getRatio() *0.01);
//							saleReturnBillDetail.setSaleReturnBill(saleReturnBill);
//							saleReturnBillDetail.setItem(saleOrderItem.getItem());
//							saleReturnBillDetail.setAmount(saleOrderItem.getAmount());
//							saleReturnBillDetail = vixntBaseService.merge(saleReturnBillDetail);
//							saleOrderItem.setIsSaleReturnBillCalculation("1");
//							saleOrderItem = vixntBaseService.merge(saleOrderItem);
//						}
//						renderText("计算成功!");
//						return;
//					}
//				}
//				if (null == returnRule.getRatio() && null != returnRule.getDetailType() && returnRule.getDetailType().equals("money")) {
//					for (ReturnRuleItem returnRuleItem : returnRule.getReturnRuleItems()) {
//						if (null != returnRuleItem.getFrom() && returnRuleItem.getFrom() > 0 && null != returnRuleItem.getTo() && returnRuleItem.getTo() > 0) {
//							if (totalAmount >= returnRuleItem.getFrom() && totalAmount <= returnRuleItem.getTo()) {
//								SaleReturnBill saleReturnBill = new SaleReturnBill();
//								saleReturnBill.setCustomerAccount(returnRule.getCustomerAccount());
//								saleReturnBill.setRbDate(new Date());
//								saleReturnBill.setReturnAmount(totalAmount * returnRuleItem.getRatio() * 0.01);
//								saleReturnBill.setCurrencyType(returnRuleItem.getCurrencyType());
//								saleReturnBill = baseHibernateService.merge(saleReturnBill);
//								for (SaleOrderItem saleOrderItem : saleOrderItemList) {
//									SaleReturnBillDetail saleReturnBillDetail = new SaleReturnBillDetail();
//									saleReturnBillDetail.setBillCode(saleOrderItem.getId());
//									saleReturnBillDetail.setBillType("SaleOrderItem");
//									saleReturnBillDetail.setTotalAmount(saleOrderItem.getAmount() * saleOrderItem.getPrice() * returnRuleItem.getRatio() *0.01);
//									saleReturnBillDetail.setSaleReturnBill(saleReturnBill);
//									saleReturnBillDetail.setItem(saleOrderItem.getItem());
//									saleReturnBillDetail.setAmount(saleOrderItem.getAmount());
//									saleReturnBillDetail = vixntBaseService.merge(saleReturnBillDetail);
//									saleOrderItem.setIsSaleReturnBillCalculation("1");
//									saleOrderItem = vixntBaseService.merge(saleOrderItem);
//								}
//								renderText("计算成功!");
//								return;
//							}
//						}
//					}
//				}
//				//根据客户计算
//				if(returnRule != null && StringUtils.isNotEmpty(customerAccountId) && saleOrderItemList != null && saleOrderItemList.size() > 0) {
//					if(returnRule.getRatio() != null && returnRule.getRatio() > 0) {
//						SaleReturnBill saleReturnBill = new SaleReturnBill();
//						saleReturnBill.setCustomerAccount(returnRule.getCustomerAccount());
//						saleReturnBill.setRbDate(new Date());
//						saleReturnBill.setReturnAmount(totalAmount * returnRule.getRatio() * 0.01);
//						saleReturnBill = vixntBaseService.merge(saleReturnBill);
//						for (SaleOrderItem saleOrderItem : saleOrderItemList) {
//							SaleReturnBillDetail saleReturnBillDetail = new SaleReturnBillDetail();
//							saleReturnBillDetail.setBillCode(saleOrderItem.getId());
//							saleReturnBillDetail.setBillType("SaleOrderItem");
//							saleReturnBillDetail.setTotalAmount(saleOrderItem.getAmount() * saleOrderItem.getPrice() * returnRule.getRatio() *0.01);
//							saleReturnBillDetail.setSaleReturnBill(saleReturnBill);
//							saleReturnBillDetail.setItem(saleOrderItem.getItem());
//							saleReturnBillDetail.setAmount(saleOrderItem.getAmount());
//							saleReturnBillDetail = vixntBaseService.merge(saleReturnBillDetail);
//							saleOrderItem.setIsSaleReturnBillCalculation("1");
//							saleOrderItem = vixntBaseService.merge(saleOrderItem);
//						}
//						renderText("计算成功!");
//						return;
//					}
//				}
//			}
//			renderText("未找到合适的条件!");
//		} catch (Exception e) {
//			renderText("计算失败!");
//			e.printStackTrace();
//		}
//	}
	public void goSingleSaleReturnBillList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(name)) {
				params.put("item.name,"+SearchCondition.ANYLIKE, name);
			}
			if(StringUtils.isNotEmpty(id)) {
				params.put("saleReturnBill.id,"+SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, SaleReturnBillDetail.class, params);
			}
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public SaleReturnBill getSaleReturnBill() {
		return saleReturnBill;
	}

	public void setSaleReturnBill(SaleReturnBill saleReturnBill) {
		this.saleReturnBill = saleReturnBill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustomerAccountId() {
		return customerAccountId;
	}

	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
}

