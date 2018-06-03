package com.vix.sales.order.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.billtype.BillType;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.share.entity.BillGroupCode;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.Regional;
import com.vix.core.constant.SearchCondition;
import com.vix.core.flow.activiti.service.IStandardActivitiService;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.item.entity.Item;
import com.vix.sales.order.controller.SalesOrderController;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesAttachFile;
import com.vix.sales.order.entity.SalesDeliveryPlan;
import com.vix.sales.order.entity.SalesOrder;
import com.vix.sales.order.entity.SalesTicket;
import com.vix.sales.order.service.ISalesOrderService;
import com.vix.sales.quotes.entity.SalesQuotation;
import com.vix.sales.quotes.entity.SalesQuotationItem;
import com.vix.system.billTypeManagement.entity.BillsProperty;
import com.vix.system.billTypeManagement.entity.BillsType;

/**
 * 服务订单
 */
@Controller
@Scope("prototype")
public class SalesOrderAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SalesOrderController salesOrderController;
	@Autowired
	private ISalesOrderService salesOrderService;
	@Autowired
	private IStandardActivitiService standardActivitiService;
	@Autowired
	private OrderProcessController orderProcessController;
	private String id;
	private String customerAccountId;
	private String ids;
	private String groupCodes;// 成组码
	private SalesOrder salesOrder;
	private SaleOrderItem saleOrderItem;
	private String pageNo;
	private String title;
	private String companyCode;
	private String page;
	private String tag;// 创建数据标识
	private String chooseType;// 数据选择标识
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	private String taskId;
	private String str;

	private List<SalesOrder> salesOrderList;

	@Override
	@SuppressWarnings("unchecked")
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(20);
			getPager().setOrderField("id");
			getPager().setOrderBy("desc");
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			salesOrderService.findPagerByHqlConditions(getPager(), SalesOrder.class, params);
			salesOrderList = getPager().getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goList";
	}

	private List<ContactPerson> contactPersonList = new ArrayList<ContactPerson>();

	public String loadCustomerContactPerson() {
		try {
			if (null != id && !id.equals("") && !id.equals("0")) {
				salesOrder = salesOrderService.findEntityById(SalesOrder.class, id);
			}
			if (null != customerAccountId && !customerAccountId.equals("")) {
				CustomerAccount customerAccount = salesOrderService.findEntityById(CustomerAccount.class, customerAccountId);
				if (null != customerAccount && null != customerAccount.getContactPersons() && customerAccount.getContactPersons().size() > 0) {
					contactPersonList.addAll(customerAccount.getContactPersons());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "loadCustomerContactPerson";
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String titles = getRequestParameter("title");
			if (StrUtils.objectIsNotNull(titles)) {
				titles = decode(titles, "UTF-8");
				params.put("title," + SearchCondition.ANYLIKE, titles);
			}
			if (null != title && !"".equals(title)) {
				title = decode(title, "UTF-8");
				params.put("title," + SearchCondition.ANYLIKE, title);
			}
			String salePerson = getRequestParameter("salePerson");
			if (null != salePerson && !"".equals(salePerson)) {
				salePerson = decode(salePerson, "UTF-8");
				params.put("salePerson.name," + SearchCondition.ANYLIKE, salePerson);
			}
			String customerAccount = getRequestParameter("customerAccount");
			if (null != customerAccount && !"".equals(customerAccount)) {
				customerAccount = decode(customerAccount, "UTF-8");
				params.put("customerAccount.name," + SearchCondition.ANYLIKE, customerAccount);
			}
			if (null != companyCode && !"".equals(companyCode)) {
				params.put("companyCode," + SearchCondition.EQUAL, companyCode);
			}
			if (null != pageNo && !"".equals(pageNo) && !"undefined".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("id");
				getPager().setOrderBy("desc");
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			Pager pager = salesOrderController.goSingleList(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
			setError("数据列表获取失败!");
			return ERROR;
		}
		return GO_SINGLE_LIST;
	}

	public String goRelatedSalesOrder() {
		try {
			String id = getRequestParameter("id");
			String type = getRequestParameter("type");
			groupCodes = "";
			if (null != id && !"".equals(id) && null != type && !"".equals(type)) {
				if ("salesQuotation".equals(type)) {
					Map<String, Object> p = new HashMap<String, Object>();
					p.put("billType," + SearchCondition.EQUAL, BillType.SAL_QUOTATION);
					p.put("billCode," + SearchCondition.EQUAL, id);
					p.put("isTemp," + SearchCondition.EQUAL, "0");
					List<BillGroupCode> bgcList = salesOrderService.findAllByConditions(BillGroupCode.class, p);
					for (BillGroupCode bgc : bgcList) {
						groupCodes += bgc.getGroupCode() + ",";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goRelatedSalesOrder";
	}

	/** 获取列表数据 */
	@SuppressWarnings("unchecked")
	public String goRelatedListContent() {
		try {
			if (null != groupCodes && !"".equals(groupCodes)) {
				Map<String, Object> params = getParams();
				params.put("groupCode," + SearchCondition.IN, groupCodes);
				getPager().setPageSize(1000);
				salesOrderService.findPagerByHqlConditions(getPager(), SaleOrderItem.class, params);
				Set<SalesOrder> soSet = new HashSet<SalesOrder>();
				for (Object soiObj : getPager().getResultList()) {
					if (null != soiObj && soiObj instanceof SaleOrderItem) {
						SaleOrderItem soi = (SaleOrderItem) soiObj;
						if (null != soi.getSalesOrder() && null != soi.getSalesOrder().getIsTemp() && "0".equals(soi.getSalesOrder().getIsTemp())) {
							soSet.add(soi.getSalesOrder());
						}
					}
				}
				getPager().getResultList().clear();
				getPager().getResultList().addAll(soSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goRelatedListContent";
	}

	/** 跳转至用户修改页面 */
	private BillGroupCode billGroupCode = new BillGroupCode();// 成组码
	private List<CurrencyType> currencyTypeList;
	private List<Regional> regionalList;
	private List<BillsType> billsTypeList;

	public String goSaveOrUpdate() {
		try {
			isAllowAudit = isAllowAudit(BillType.SA_ORDER);
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			regionalList = baseHibernateService.findAllByEntityClass(Regional.class);
			billsTypeList = baseHibernateService.findAllByEntityClassAndAttribute(BillsType.class, "billsProperty.propertyCode", BillType.SA_ORDER);
			String pageNo = getRequestParameter("pageNo");
			if (null != pageNo) {
				getRequest().setAttribute("pageNo", pageNo);
			}
			if (null != id && !id.equals("") && !id.equals("0")) {
				salesOrder = salesOrderController.findEntityById(id);
				if (null != salesOrder && null != salesOrder.getAuditStatus() && "1".equals(salesOrder.getAuditStatus())) {
					return "showOrder";
				}
			} else {
				billGroupCode.setIsTemp("1");
				billGroupCode.setBillType(BillType.SAL_QUOTATION);
				billGroupCode.setGroupCode(UUID.randomUUID().toString());
				loadCommonData(billGroupCode);
				salesOrder = new SalesOrder();
				salesOrder.setIsTemp("1");
				salesOrder.setIsDeleted("0");
				salesOrder.setBillDate(new Date());
				salesOrder.setGroupCode(billGroupCode.getGroupCode());
				Employee baseEmp = getEmployee();
				if (null != baseEmp) {
					Employee emp = salesOrderService.findEntityById(Employee.class, baseEmp.getId());
					salesOrder.setSalePerson(emp);
					salesOrder.setSalePersonCode(emp.getCode());
					if (null != emp) {
						salesOrder.setSaleOrg(emp.getOrganizationUnit());
					}
				}
				salesOrder = salesOrderController.merge(salesOrder, null, null);
				billGroupCode.setBillCode(salesOrder.getId().toString());
				billGroupCode = salesOrderService.merge(billGroupCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setError("销售订单获取失败!");
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != salesOrder.getId()) {
				isSave = false;
			} else {
				salesOrder.setCreateTime(new Date());
				loadCommonData(salesOrder);
			}
			//			String[] attrArray ={"customerAccount","contactPerson","salePerson","currencyType","saleOrg","regional"};
			//			checkEntityNullValue(salesOrder,attrArray);
			if (null == salesOrder.getCustomerAccount() || null == salesOrder.getCustomerAccount().getId() || salesOrder.getCustomerAccount().getId().equals("")) {
				salesOrder.setCustomerAccount(null);
			}
			if (null == salesOrder.getContactPerson() || null == salesOrder.getContactPerson().getId() || salesOrder.getContactPerson().getId().equals("")) {
				salesOrder.setContactPerson(null);
			}
			if (null == salesOrder.getSalePerson() || null == salesOrder.getSalePerson().getId() || salesOrder.getSalePerson().getId().equals("")) {
				salesOrder.setSalePerson(null);
			}
			if (null == salesOrder.getCurrencyType() || null == salesOrder.getCurrencyType().getId() || salesOrder.getCurrencyType().getId().equals("")) {
				salesOrder.setCurrencyType(null);
			}
			if (null == salesOrder.getSaleOrg() || null == salesOrder.getSaleOrg().getId() || salesOrder.getSaleOrg().getId().equals("")) {
				salesOrder.setSaleOrg(null);
			}
			if (null == salesOrder.getRegional() || null == salesOrder.getRegional().getId() || salesOrder.getRegional().getId().equals("")) {
				salesOrder.setRegional(null);
			}
			String title = salesOrder.getTitle();
			String py = ChnToPinYin.getPYString(title);
			salesOrder.setChineseFirstLetter(py.toUpperCase());
			salesOrder.setIsTemp("0");
			salesOrder.setIsDeleted("0");
			salesOrder = salesOrderController.merge(salesOrder, null, null);
			dealBillGroupCode();
			if ("approval".equals(getRequestParameter("approval"))) {
				if ("1".equals(isAllowAudit(BillType.SA_ORDER))) {
					// BillsProperty bp =
					// salesOrderService.findEntityByAttribute(BillsProperty.class,
					// "propertyCode", BillType.SA_ORDER);
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("propertyCode," + SearchCondition.EQUAL, BillType.SA_ORDER);
					params.put("tenantId," + SearchCondition.EQUAL, salesOrder.getTenantId());
					List<BillsProperty> billsPropertyList = salesOrderService.findAllByConditions(BillsProperty.class, params);
					if (billsPropertyList != null && billsPropertyList.size() > 0) {
						for (BillsProperty billsProperty : billsPropertyList) {
							if (null != billsProperty) {
								Object objUser = getSession().getAttribute("userInfo");
								if (null != objUser && objUser instanceof UserAccount) {
									String deploymentId = standardActivitiService.findProcessDefinitionIdByCode(billsProperty.getId() + "X");
									UserAccount user = (UserAccount) objUser;
									Map<String, Object> param = new HashMap<String, Object>();
									param.put("BillType", BillType.SA_ORDER);
									param.put("BillId", salesOrder.getId());
									param.put("BillTitle", salesOrder.getTitle());
									param.put("assignee", user.getId());
									standardActivitiService.startSubmit(String.valueOf(user.getId()), deploymentId, param);
									salesOrder.setAuditStatus("1");
									salesOrderService.merge(salesOrder);
									setMessage("成功提交审批");
								} else {
									setMessage("用户未登陆!");
								}
							} else {
								setMessage("单据类型未配置!");
							}
							break;
						}
					}

				}
			} else {
				if (isSave) {
					renderText(SAVE_SUCCESS);
				} else {
					renderText(UPDATE_SUCCESS);
				}
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

	public String saveOrUpdateOrderItem() {
		boolean isSave = true;
		try {
			SalesOrder so = salesOrderService.findEntityById(SalesOrder.class, salesOrder.getId());
			if(null == so){
				renderText(SAVE_FAIL);
				return UPDATE;
			}
			String barCode = getRequestParameter("barCode");
			if (null != barCode && !"".equals(barCode)) {
				Item item = salesOrderService.findEntityByAttribute(Item.class, "barCode", barCode);
				if (null != item) {
					SaleOrderItem soi = new SaleOrderItem();
					soi.setSalesOrder(salesOrder);
					soi.setItem(item);
					soi.setAmount(1D);
					soi.setPrice(item.getPrice());
					soi.setGroupCode(so.getGroupCode());
					loadCommonData(soi);
					salesOrderController.merge(soi);
				}
			} else {
				if (null != saleOrderItem.getId()) {
					isSave = false;
				}
				saleOrderItem.setSalesOrder(so);
				String giftJson = getRequestParameter("giftJson");
				if (null != giftJson && !"".equals(giftJson)) {
					saleOrderItem.setIsHasGift("1");
				}
				saleOrderItem.setGroupCode(so.getGroupCode());
				saleOrderItem = salesOrderController.merge(saleOrderItem);
				if (null != giftJson && !"".equals(giftJson)) {
					String[] gifts = giftJson.split("_");
					for (String gift : gifts) {
						if (null != gift && !"".equals(gift)) {
							String[] giftItem = gift.split("-");
							SaleOrderItem soi = new SaleOrderItem();
							soi.setSalesOrder(saleOrderItem.getSalesOrder());
							Item i = salesOrderService.findEntityById(Item.class, giftItem[0]);
							if (null != i) {
								soi.setItem(i);
								if (!",".equals(giftItem[1])) {
									soi.setSpecification(giftItem[1]);
								}
								soi.setAmount(Double.parseDouble(giftItem[2]));
								soi.setPrice(Double.parseDouble(giftItem[3]));
								loadCommonData(soi);
								soi.setIsGift("1");
								soi.setSaleOrderItemId(saleOrderItem.getId());
								if (soi.getPrice() > 0) {
									soi.setMemo("买赠");
								} else {
									soi.setMemo("赠品");
								}
								salesOrderController.merge(soi);
							}
						}
					}
				}
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
		return UPDATE;
	}

	public String showSalesOrder() {
		try {
			if (null != id && !id.equals("") && !id.equals("0")) {
				salesOrder = salesOrderController.findEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return "showOrder";
	}

	//打印
	public String goPrintSalesOrder() {
		try {
			salesOrder = salesOrderService.findEntityById(SalesOrder.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintSalesOrder";
	}

	//上一条   下一条
	public String goShowBeforeAndAfter() {
		try {
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				salesOrder = salesOrderService.findEntityById(SalesOrder.class, id);
				if (salesOrder != null && salesOrder.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(salesOrder.getCreateTime()));
					params.put("isTemp", "0");
					params.put("isDelete", "0");
					if (StrUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							salesOrder = (SalesOrder) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(salesOrder.getCreateTime()), params, salesOrder, "before");
						} else if ("after".equals(str)) {
							salesOrder = (SalesOrder) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(salesOrder.getCreateTime()), params, salesOrder, "after");
						}
					}
					if (salesOrder == null || StrUtils.isEmpty(salesOrder.getId())) {
						salesOrder = salesOrderService.findEntityById(SalesOrder.class, id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showOrder";
	}

	//	高级搜索
	public String goSearch() {
		return "goSearch";
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			SalesOrder pb = salesOrderController.findEntityById(id);
			if (null != pb) {
				pb.setIsDeleted("1");
				salesOrderService.merge(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage("数据未找到!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String deleteSalesOrderItemById() {
		try {
			if (null != id && !id.equals("") && !id.equals("0")) {
				SaleOrderItem soi = salesOrderService.findEntityById(SaleOrderItem.class, id);
				if (null != soi) {
					if (null != soi.getIsHasGift() && "1".equals(soi.getIsHasGift())) {
						salesOrderService.deleteByAttribute(SaleOrderItem.class, "saleOrderItemId", id);
					}
					salesOrderService.deleteByEntity(soi);
					renderText(DELETE_SUCCESS);
				} else {
					setMessage("数据未找到!");
				}
			} else {
				setMessage("数据未找到!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 订单子项价格汇总 */
	public void getOrderItemTotal() {
		try {
			if (null != id && !id.equals("") && !id.equals("0")) {
				SalesOrder so = salesOrderController.findEntityById(id);
				if (null != so) {
					Double totalAmount = 0d;
					for (SaleOrderItem soi : so.getSaleOrderItems()) {
						totalAmount += soi.getPrice() * soi.getAmount();
					}
					DecimalFormat df = new DecimalFormat(".##");
					String st = df.format(totalAmount);
					renderJson(st);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String convertProjectQuotationSchemeToSalesOrder() {
		try {
			salesOrder = new SalesOrder();
			String salesOrderIds = getRequestParameter("projectQuotationSchemeIds");
			if (null != salesOrderIds && !"".equals(salesOrderIds)) {
				String[] sqStr = salesOrderIds.split(",");
				Set<String> sqIdSet = new HashSet<String>();
				for (String sqId : sqStr) {
					if (null != sqId && !"".equals(sqId)) {
						sqIdSet.add(sqId);
					}
				}
				salesOrder.setCreateTime(new Date());
				salesOrder.setBizType(null);
				loadCommonData(salesOrder);
				salesOrder = salesOrderService.merge(salesOrder);
				for (String sqId : sqIdSet) {
					SalesQuotation sq = salesOrderService.findEntityById(SalesQuotation.class, sqId);
					BillGroupCode bgc = new BillGroupCode();
					bgc.setBillType(BillType.SA_ORDER);
					bgc.setGroupCode(sq.getGroupCode());
					bgc.setBillCode(salesOrder.getId().toString());
					bgc.setIsTemp("0");
					salesOrderService.merge(bgc);
					if (null != sq && sq.getSalesQuotationItems().size() > 0) {
						if (null != sq.getCustomerAccount() && null == salesOrder.getCustomerAccount()) {
							salesOrder.setCustomerAccount(sq.getCustomerAccount());
						}
						for (SalesQuotationItem sqi : sq.getSalesQuotationItems()) {
							SaleOrderItem soi = new SaleOrderItem();
							soi.setSalesOrder(salesOrder);
							if (null != sqi.getItem()) {
								soi.setItem(sqi.getItem());
							}
							soi.setNetTotal(sqi.getNetTotal());
							soi.setNetPrice(sqi.getNetPrice());
							soi.setGroupCode(bgc.getGroupCode());
							loadCommonData(soi);
							salesOrderService.merge(soi);
						}
					}
				}
				salesOrder = salesOrderService.merge(salesOrder);
			} else {
				salesOrder.setCreateTime(new Date());
				loadCommonData(salesOrder);
				salesOrder = salesOrderService.merge(salesOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 销售报价转销售订单 */
	public String convertSalesQuotationToSalesOrder() {
		try {
			isAllowAudit = isAllowAudit(BillType.SA_ORDER);
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			regionalList = baseHibernateService.findAllByEntityClass(Regional.class);
			billsTypeList = baseHibernateService.findAllByEntityClassAndAttribute(BillsType.class, "billsProperty.propertyCode", "SA_ORDER");
			String salesQuotationIdStr = getRequestParameter("salesQuotationIds");
			if (null != salesQuotationIdStr && !"".equals(salesQuotationIdStr)) {
				salesOrder = new SalesOrder();
				salesOrder.setIsTemp("1");
				salesOrder.setIsDeleted("0");
				salesOrder.setBillDate(new Date());
				Employee baseEmp = getEmployee();
				if (null != baseEmp) {
					Employee emp = salesOrderService.findEntityById(Employee.class, baseEmp.getId());
					salesOrder.setSalePerson(emp);
					salesOrder.setSalePersonCode(emp.getCode());
					if (null != emp) {
						salesOrder.setSaleOrg(emp.getOrganizationUnit());
					}
				}
				salesOrder = salesOrderController.merge(salesOrder, null, null);
				String[] sqStr = salesQuotationIdStr.split(",");
				Set<String> sqIdSet = new HashSet<String>();
				for (String sqId : sqStr) {
					if (null != sqId && !"".equals(sqId)) {
						sqIdSet.add(sqId);
					}
				}
				for (String sqId : sqIdSet) {
					SalesQuotation sq = salesOrderService.findEntityById(SalesQuotation.class, sqId);
					BillGroupCode bgc = new BillGroupCode();
					bgc.setBillType(BillType.SA_ORDER);
					bgc.setGroupCode(sq.getGroupCode());
					bgc.setBillCode(salesOrder.getId().toString());
					bgc.setIsTemp("0");
					salesOrderService.merge(bgc);
					salesOrder.setCustomerAccount(sq.getCustomerAccount());
					Double amountTotal = 0.0;
					if (null != sq && sq.getSalesQuotationItems().size() > 0) {
						for (SalesQuotationItem sqi : sq.getSalesQuotationItems()) {
							SaleOrderItem soi = new SaleOrderItem();
							soi.setItem(sqi.getItem());
							soi.setAmount(sqi.getAmount());
							soi.setPrice(sqi.getPrice());
							soi.setSalesOrder(salesOrder);
							soi.setGroupCode(bgc.getGroupCode());
							amountTotal = soi.getAmount() * soi.getPrice() + amountTotal;
							loadCommonData(soi);
							salesOrderService.merge(soi);
						}
					}
					salesOrder.setAmountTotal(amountTotal);
				}
				setMessage("取单成功!");
			} else {
				setMessage("报价单未取到!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setMessage("转换失败!");
		}
		return GO_SAVE_OR_UPDATE;
	}

	private void dealBillGroupCode() throws Exception {
		if (null != salesOrder && null != salesOrder.getId()) {
			SalesOrder so = salesOrderService.findEntityById(SalesOrder.class, salesOrder.getId());
			Set<String> bgcSet = new HashSet<String>();
			for (SaleOrderItem soi : so.getSaleOrderItems()) {
				if (null != soi && null != soi.getGroupCode() && !"".equals(soi.getGroupCode()) && !soi.getGroupCode().equals(salesOrder.getGroupCode())) {
					bgcSet.add(soi.getGroupCode());
				}
			}
			if (bgcSet.size() > 0) {
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("billType," + SearchCondition.EQUAL, BillType.SA_ORDER);
				p.put("billCode," + SearchCondition.EQUAL, salesOrder.getId().toString());
				p.put("isTemp," + SearchCondition.EQUAL, "0");
				List<BillGroupCode> bgcList = salesOrderService.findAllByConditions(BillGroupCode.class, p);
				for (BillGroupCode bgc : bgcList) {
					salesOrderService.deleteByEntity(bgc);
				}
				for (String bgc : bgcSet) {
					BillGroupCode billGroupCode = new BillGroupCode();
					billGroupCode.setBillType(BillType.SA_ORDER);
					billGroupCode.setBillCode(so.getId().toString());
					billGroupCode.setGroupCode(bgc);
					loadCommonData(billGroupCode);
					billGroupCode.setIsTemp("0");
					salesOrderService.merge(billGroupCode);
				}
			} else {
				billGroupCode = salesOrderService.findEntityByAttribute(BillGroupCode.class, "groupCode", salesOrder.getGroupCode());
				if (null != billGroupCode) {
					billGroupCode.setIsTemp("0");
					salesOrderService.merge(billGroupCode);
				}
			}
		}
	}

	public void getSaleOrderItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				SalesOrder so = salesOrderController.findEntityById(id);
				if (null != so) {
					json = convertListToJson(new ArrayList<SaleOrderItem>(so.getSaleOrderItems()), so.getSaleOrderItems().size(), "salesOrder");
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

	public void getSalesTicketJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				SalesOrder so = salesOrderController.findEntityById(id);
				json = convertListToJson(new ArrayList<SalesTicket>(so.getSalesTickets()), so.getSalesTickets().size(), "salesOrder");
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

	/** 获取json数据 */
	public void getSalesDeliveryPlanJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				SalesOrder so = salesOrderController.findEntityById(id);
				json = convertListToJson(new ArrayList<SalesDeliveryPlan>(so.getSalesDeliveryPlans()), so.getSalesDeliveryPlans().size(), "salesOrder");
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

	/** 获取json数据 */
	public void getAttachFileJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				SalesOrder so = salesOrderController.findEntityById(id);
				json = convertListToJson(new ArrayList<SalesAttachFile>(so.getSalesAttachFiles()), so.getSalesAttachFiles().size(), "salesOrder");
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

	public String goSaveOrUpdateSaleOrderItem() {
		try {
			if (null != id && !id.equals("") && !id.equals("0")) {
				saleOrderItem = salesOrderService.findEntityById(SaleOrderItem.class, id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdateSaleOrderItem";
	}

	/** 处理删除操作 */
	public String deleteByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				salesOrderController.deleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotChoose"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	private List<SaleOrderItem> salesOrderItems;

	public String goChooseSalesOrderItem() {
		try {
			if (null != id && !id.equals("")) {
				salesOrderItems = salesOrderService.findAllByEntityClassAndAttribute(SaleOrderItem.class, "salesOrder.id", id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return "goChooseSalesOrderItem";
	}

	public String goChooseSalesOrder() {
		return "goChooseSalesOrder";
	}

	/** 获取列表数据 */
	public String goSubListContent() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			if (null != title && !"".equals(title)) {
				params.put("title," + SearchCondition.ANYLIKE, title);
			}
			getPager().setPageSize(6);
			Pager pager = salesOrderService.findPagerByHqlConditions(getPager(), SalesOrder.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubListContent";
	}

	public List<SalesOrder> getSalesOrderList() {
		return salesOrderList;
	}

	public void setSalesOrderList(List<SalesOrder> salesOrderList) {
		this.salesOrderList = salesOrderList;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getCustomerAccountId() {
		return customerAccountId;
	}

	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public List<ContactPerson> getContactPersonList() {
		return contactPersonList;
	}

	public void setContactPersonList(List<ContactPerson> contactPersonList) {
		this.contactPersonList = contactPersonList;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public SaleOrderItem getSaleOrderItem() {
		return saleOrderItem;
	}

	public void setSaleOrderItem(SaleOrderItem saleOrderItem) {
		this.saleOrderItem = saleOrderItem;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public List<SaleOrderItem> getSalesOrderItems() {
		return salesOrderItems;
	}

	public void setSalesOrderItems(List<SaleOrderItem> salesOrderItems) {
		this.salesOrderItems = salesOrderItems;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getChooseType() {
		return chooseType;
	}

	public void setChooseType(String chooseType) {
		this.chooseType = chooseType;
	}

	public List<Regional> getRegionalList() {
		return regionalList;
	}

	public void setRegionalList(List<Regional> regionalList) {
		this.regionalList = regionalList;
	}

	public List<BillsType> getBillsTypeList() {
		return billsTypeList;
	}

	public void setBillsTypeList(List<BillsType> billsTypeList) {
		this.billsTypeList = billsTypeList;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getIsAllowAudit() {
		return isAllowAudit;
	}

	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getGroupCodes() {
		return groupCodes;
	}

	public void setGroupCodes(String groupCodes) {
		this.groupCodes = groupCodes;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

}