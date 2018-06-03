package com.vix.sales.refund.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.DaysUtils;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.refund.entity.ReturnRule;
import com.vix.sales.refund.entity.ReturnRuleItem;
import com.vix.sales.refund.entity.SaleReturnBill;
import com.vix.sales.refund.entity.SaleReturnBillDetail;

@Controller
@Scope("prototype")
public class SaleReturnBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private SaleReturnBill saleReturnBill;
	private String pageNo;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			baseHibernateService.findPagerByHqlConditions(getPager(),
					SaleReturnBill.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	private List<CurrencyType> currencyTypeList;

	public String goSaveOrUpdate() {
		try {
			currencyTypeList = baseHibernateService
					.findAllByEntityClass(CurrencyType.class);
			if (null != id && !"".equals(id)) {
				saleReturnBill = baseHibernateService.findEntityById(
						SaleReturnBill.class, id);
			} else {
				saleReturnBill = new SaleReturnBill();
				saleReturnBill.setIsTemp("1");
				saleReturnBill.setCreateTime(new Date());
				loadCommonData(saleReturnBill);
				saleReturnBill = baseHibernateService.merge(saleReturnBill);
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
			if (null != saleReturnBill.getId()) {
				isSave = false;
			} else {
				saleReturnBill.setCreateTime(new Date());
				loadCommonData(saleReturnBill);
			}
			if (null == saleReturnBill.getCustomerAccount()
					|| null == saleReturnBill.getCustomerAccount().getId()
					|| !saleReturnBill.getCustomerAccount().getId().equals("")
					|| !saleReturnBill.getCustomerAccount().getId().equals("0")) {
				saleReturnBill.setCustomerAccount(null);
			}
			if (null == saleReturnBill.getCurrencyType()
					|| null == saleReturnBill.getCurrencyType().getId()
					|| !saleReturnBill.getCurrencyType().getId().equals("")
					|| !saleReturnBill.getCurrencyType().getId().equals("0")) {
				saleReturnBill.setCurrencyType(null);
			}
			saleReturnBill.setIsTemp("0");
			baseHibernateService.merge(saleReturnBill);
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
			SaleReturnBill pb = baseHibernateService.findEntityById(
					SaleReturnBill.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("数据已删除!"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 选择计算条件 */
	public String goChooseCondition() {
		return "chooseCondition";
	}
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public String calculateSaleReturnBill() {
		try {
			String customerAccountId = getRequestParameter("customerAccountId");
			String itemId = getRequestParameter("itemId");
			String startTime = getRequestParameter("startTime");
			String endTime = getRequestParameter("endTime");
			if (StrUtils.objectIsNotNull(startTime)
					&& StrUtils.objectIsNotNull(endTime)) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("salesOrder.isTemp," + SearchCondition.NOEQUAL, "1");
				params.put("salesOrder.tenantId," + SearchCondition.EQUAL,
						SecurityUtil.getCurrentUserTenantId());
				params.put("isSaleReturnBillCalculation,"
						+ SearchCondition.NOEQUAL, "1");// 未参与计算
				params.put("salesOrder.isComplate," + SearchCondition.EQUAL,
						"1");// 已完成
				params.put("salesOrder.customerAccount.id,"
						+ SearchCondition.EQUAL,
						customerAccountId);
				params.put("salesOrder.billDate,"+ SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(startTime)) + ":01!"+ DaysUtils.getEndDay(dateFormat.parse(endTime))+":01");
				if (StrUtils.objectIsNotNull(itemId)) {
					params.put("item.id," + SearchCondition.EQUAL,
							itemId);
				}
				List<SaleOrderItem> soiList = baseHibernateService
						.findAllByConditions(SaleOrderItem.class, params);
				List<ReturnRule> rrList = baseHibernateService
						.findAllByEntityClassAndAttribute(ReturnRule.class,
								"customerAccount.id",
								customerAccountId);
				Double totalAmount = 0d;// 客户订单总金额
				Double totalCount = 0d;// 客户订单总数量
				for (SaleOrderItem soi : soiList) {
					soiList.add(soi);
					soi.setIsSaleReturnBillCalculation("1");
					baseHibernateService.merge(soi);
					totalAmount += soi.getAmount() * soi.getPrice();
					totalCount += soi.getAmount();
				}
				ReturnRule rr = null;
				if (null != rrList && rrList.size() > 0) {
					rr = rrList.get(0);
				}
				/** 根据客户计算 */
				if (StrUtils.objectIsNotNull(customerAccountId)
						&& StrUtils.objectIsNull(itemId) && null != rr
						&& soiList.size() > 0) {
					/** 根据用户设定的返款比率计算 */
					if (null != rr.getRatio() && rr.getRatio() > 0) {
						SaleReturnBill srb = new SaleReturnBill();
						srb.setCustomerAccount(rr.getCustomerAccount());
						srb.setRbDate(new Date());
						srb.setReturnAmount(totalAmount * rr.getRatio() * 0.01);
						srb = baseHibernateService.merge(srb);
						for (SaleOrderItem soi : soiList) {
							SaleReturnBillDetail srbd = new SaleReturnBillDetail();
							srbd.setBillCode(soi.getId().toString());
							srbd.setBillType("SaleOrderItem");
							srbd.setTotalAmount(soi.getAmount()
									* soi.getPrice() * rr.getRatio() * 0.01);
							srbd.setSaleReturnBill(srb);
							baseHibernateService.merge(srbd);
						}
						setMessage("计算成功!");
						return UPDATE;
					}
					/** 根据用户设定的金额明细计算 */
					if (null == rr.getRatio() && null != rr.getDetailType()
							&& rr.getDetailType().equals("money")) {
						for (ReturnRuleItem rrd : rr.getReturnRuleItems()) {
							if (null != rrd.getFrom() && rrd.getFrom() > 0
									&& null != rrd.getTo() && rrd.getTo() > 0) {
								if (totalAmount >= rrd.getFrom()
										&& totalAmount <= rrd.getTo()) {
									SaleReturnBill srb = new SaleReturnBill();
									srb.setCustomerAccount(rr
											.getCustomerAccount());
									srb.setRbDate(new Date());
									srb.setReturnAmount(totalAmount
											* rrd.getRatio() * 0.01);
									srb.setCurrencyType(rrd.getCurrencyType());
									srb = baseHibernateService.merge(srb);
									for (SaleOrderItem soi : soiList) {
										SaleReturnBillDetail srbd = new SaleReturnBillDetail();
										srbd.setBillCode(soi.getId().toString());
										srbd.setBillType("SaleOrderItem");
										srbd.setTotalAmount(soi.getAmount()
												* soi.getPrice()
												* rr.getRatio() * 0.01);
										srbd.setSaleReturnBill(srb);
										baseHibernateService.merge(srbd);
									}
									setMessage("计算成功!");
									return UPDATE;
								}
							}
						}
					}
				}
				/** 根据客户和物料计算 */
				if (StrUtils.objectIsNotNull(customerAccountId)
						&& StrUtils.objectIsNull(itemId) && soiList.size() > 0) {
					/** 根据用户设定的返款比率计算 */
					if (null != rr.getRatio() && rr.getRatio() > 0
							&& null != rr.getLowerSaleCount()
							&& rr.getLowerSaleCount() > 0
							&& totalAmount > rr.getLowerSaleCount()) {
						SaleReturnBill srb = new SaleReturnBill();
						srb.setCustomerAccount(rr.getCustomerAccount());
						srb.setRbDate(new Date());
						srb.setReturnAmount(totalAmount * rr.getRatio() * 0.01);
						srb = baseHibernateService.merge(srb);
						for (SaleOrderItem soi : soiList) {
							SaleReturnBillDetail srbd = new SaleReturnBillDetail();
							srbd.setBillCode(soi.getId().toString());
							srbd.setBillType("SaleOrderItem");
							srbd.setTotalAmount(soi.getAmount()
									* soi.getPrice() * rr.getRatio() * 0.01);
							srbd.setSaleReturnBill(srb);
							baseHibernateService.merge(srbd);
						}
						setMessage("计算成功!");
						return UPDATE;
					}
					/** 根据用户设定的金额明细计算 */
					if (null == rr.getRatio() && null != rr.getDetailType()
							&& rr.getDetailType().equals("money")) {
						for (ReturnRuleItem rrd : rr.getReturnRuleItems()) {
							if (null != rrd.getFrom() && rrd.getFrom() > 0
									&& null != rrd.getTo() && rrd.getTo() > 0) {
								if (totalAmount >= rrd.getFrom()
										&& totalAmount <= rrd.getTo()) {
									SaleReturnBill srb = new SaleReturnBill();
									srb.setCustomerAccount(rr
											.getCustomerAccount());
									srb.setRbDate(new Date());
									srb.setReturnAmount(totalAmount
											* rrd.getRatio() * 0.01);
									srb.setCurrencyType(rrd.getCurrencyType());
									srb = baseHibernateService.merge(srb);
									for (SaleOrderItem soi : soiList) {
										SaleReturnBillDetail srbd = new SaleReturnBillDetail();
										srbd.setBillCode(soi.getId().toString());
										srbd.setBillType("SaleOrderItem");
										srbd.setTotalAmount(soi.getAmount()
												* soi.getPrice()
												* rr.getRatio() * 0.01);
										srbd.setSaleReturnBill(srb);
										baseHibernateService.merge(srbd);
									}
									setMessage("计算成功!");
									return UPDATE;
								}
							}
						}
					}
					/** 根据用户设定的数量明细计算 */
					if (null == rr.getRatio() && null != rr.getDetailType()
							&& rr.getDetailType().equals("count")) {
						for (ReturnRuleItem rrd : rr.getReturnRuleItems()) {
							if (null != rrd.getFrom() && rrd.getFrom() > 0
									&& null != rrd.getTo() && rrd.getTo() > 0) {
								if (totalCount >= rrd.getFrom()
										&& totalAmount <= rrd.getTo()) {
									SaleReturnBill srb = new SaleReturnBill();
									srb.setCustomerAccount(rr
											.getCustomerAccount());
									srb.setRbDate(new Date());
									srb.setReturnAmount(totalAmount
											* rrd.getRatio() * 0.01);
									srb.setCurrencyType(rrd.getCurrencyType());
									srb = baseHibernateService.merge(srb);
									for (SaleOrderItem soi : soiList) {
										SaleReturnBillDetail srbd = new SaleReturnBillDetail();
										srbd.setBillCode(soi.getId().toString());
										srbd.setBillType("SaleOrderItem");
										srbd.setTotalAmount(soi.getAmount()
												* soi.getPrice()
												* rr.getRatio() * 0.01);
										srbd.setSaleReturnBill(srb);
										baseHibernateService.merge(srbd);
									}
									setMessage("计算成功!");
									return UPDATE;
								}
							}
						}
					}
				}
			}
			setMessage("未找到合适的条件!");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("计算失败!");
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public SaleReturnBill getSaleReturnBill() {
		return saleReturnBill;
	}

	public void setSaleReturnBill(SaleReturnBill saleReturnBill) {
		this.saleReturnBill = saleReturnBill;
	}
}