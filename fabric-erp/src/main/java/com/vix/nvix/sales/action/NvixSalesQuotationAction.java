package com.vix.nvix.sales.action;

import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.billtype.BillType;
import com.vix.common.id.VixUUID;
import com.vix.common.job.entity.FlowApprovalOpinion;
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.BillGroupCode;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.Regional;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.salechance.entity.SaleChance;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.item.entity.Item;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.sales.quotes.entity.SalesQuotation;
import com.vix.sales.quotes.entity.SalesQuotationItem;
import com.vix.sales.quotes.entity.SalesQuotationTemplate;
import com.vix.sales.quotes.entity.SalesQuotationTemplateItem;
import com.vix.system.billTypeManagement.entity.BillsType;

import net.sf.json.JSONObject;

/**
 * 报价单
 * 
 * @author Bluesnow 2015年12月15日
 */
@Controller
@Scope("prototype")
public class NvixSalesQuotationAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private String types;
	private String groupCodes;// 成组码
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	private SalesQuotation salesQuotation;
	private List<SalesQuotation> sqList;
	private SalesQuotationItem salesQuotationItem;
	private String theme;
	private String name;
	private String entityName;
	private ContactPerson contactPerson;
	private BillGroupCode billGroupCode = new BillGroupCode();// 成组码
	private List<Regional> regionalList;
	private List<BillsType> billsTypeList;
	@Autowired
	private OrderProcessController orderProcessController;
	private List<BaseEntity> baseEntityList;
	private String str;
	private List<FlowApprovalOpinion> flowApprovalOpinionList;
	@Override
	public String goList() {
		return GO_LIST;
	}

	public void goListContentJson() {
		try {
			Pager pager = getPager();

			Map<String, Object> params = getParams();
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			if (null != theme && !"".equals(theme)) {
				params.put("quoteSubject," + SearchCondition.ANYLIKE, URLDecoder.decode(theme.trim(), "UTF-8"));
			}

			if (null != name && !"".equals(name)) {
				params.put("customerAccount.name," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
			}

			baseHibernateService.findPagerByHqlConditions(pager, SalesQuotation.class, params);
			String[] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<CurrencyType> currencyTypeList;
	private List<SaleChance> saleChanceList;
	private List<ContactPerson> contactPersonList;
	public String goSaveOrUpdate() {
		try {
			isAllowAudit = isAllowAudit(BillType.SAL_QUOTATION);
			regionalList = baseHibernateService.findAllByEntityClass(Regional.class);
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			billsTypeList = baseHibernateService.findAllByEntityClassAndAttribute(BillsType.class, "billsProperty.propertyCode", "SA_QUOTATION");
			if (null != id && StrUtils.isNotEmpty(id)) {
				salesQuotation = baseHibernateService.findEntityById(SalesQuotation.class, id);
				if(null != salesQuotation.getCustomerAccount() && StrUtils.isNotEmpty(salesQuotation.getCustomerAccount().getId())){
					saleChanceList = baseHibernateService.findAllByEntityClassAndAttribute(SaleChance.class, "customerAccount.id", salesQuotation.getCustomerAccount().getId());
					contactPersonList = baseHibernateService.findAllByEntityClassAndAttribute(ContactPerson.class, "customerAccount.id", salesQuotation.getCustomerAccount().getId());
				}
			} else {
				billGroupCode.setIsTemp("1");
				billGroupCode.setBillType(BillType.SAL_QUOTATION);
				billGroupCode.setGroupCode(UUID.randomUUID().toString());
				loadCommonData(billGroupCode);
				salesQuotation = new SalesQuotation();
				salesQuotation.setCode(autoCreateCode.getBillNO(BillType.SAL_QUOTATION));
				salesQuotation.setIsTemp("1");
				salesQuotation.setIsDeleted("0");
				salesQuotation.setCreateTime(new Date());
				loadCommonData(salesQuotation);
				salesQuotation.setGroupCode(billGroupCode.getGroupCode());
				salesQuotation.setBillDate(new Date());
				Employee baseEmp = getEmployee();
				if (null != baseEmp) {
					Employee emp = baseHibernateService.findEntityById(Employee.class, baseEmp.getId());
					salesQuotation.setSalesMan(emp);
					salesQuotation.setSalePersonCode(emp.getCode());
					if (null != emp) {
						salesQuotation.setSalesOrg(emp.getOrganizationUnit());
					}
				}
				salesQuotation = baseHibernateService.merge(salesQuotation);
				billGroupCode.setBillCode(salesQuotation.getId());
				billGroupCode = baseHibernateService.merge(billGroupCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != salesQuotation.getId() && StrUtils.isNotEmpty(salesQuotation.getId())) {
				isSave = false;
				salesQuotation.setUpdateTime(new Date());
			} else {
				salesQuotation.setCreateTime(new Date());
			}
			dealBillGroupCode();

			String[] attrArray = { "salesMan", "salesOrg", "dept", "regional", "saleChance", "employee", "contactPerson", "currencyType" };
			checkEntityNullValue(salesQuotation, attrArray);
			if (null == salesQuotation.getGroupCode() || "".equals(salesQuotation.getGroupCode())) {
				salesQuotation.setGroupCode(VixUUID.getUUID());
			}

			String quoteSubject = salesQuotation.getQuoteSubject();
			String py = ChnToPinYin.getPYString(quoteSubject);
			salesQuotation.setChineseFirstLetter(py.toUpperCase());
			loadCommonData(salesQuotation);
			salesQuotation.setIsTemp("0");
			salesQuotation.setIsDeleted("0");
			List<SalesQuotationItem> sqiList = baseHibernateService.findAllByEntityClassAndAttribute(SalesQuotationItem.class, "salesQuotation.id", salesQuotation.getId());
			Double amount = 0D;
			if (sqiList.size() > 0) {
				for (SalesQuotationItem sqi : sqiList) {
					if (null != sqi) {
						if (null != sqi.getItem().getPrice()) {
							amount += sqi.getItem().getPrice() * sqi.getAmount();
						} else {
							amount += sqi.getPrice() * sqi.getAmount();
						}
					}
				}
				salesQuotation.setAmount(amount);
			}
			baseHibernateService.merge(salesQuotation);
			/** 处理成组码 */
			billGroupCode = baseHibernateService.findEntityByAttribute(BillGroupCode.class, "groupCode", salesQuotation.getGroupCode());
			if (null != billGroupCode) {
				billGroupCode.setIsTemp("0");
				baseHibernateService.merge(billGroupCode);
			}
			if (isSave) {
				renderText("1:" + SAVE_SUCCESS);
			} else {
				renderText("1:" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
	}
	public void audit() {
		try {
			if (null != salesQuotation.getId() && StrUtils.isNotEmpty(salesQuotation.getId())) {
				salesQuotation.setUpdateTime(new Date());
			} else {
				salesQuotation.setCreateTime(new Date());
			}
			dealBillGroupCode();
			
			String[] attrArray = { "salesMan", "salesOrg", "dept", "regional", "saleChance", "employee", "contactPerson", "currencyType" };
			checkEntityNullValue(salesQuotation, attrArray);
			if (null == salesQuotation.getGroupCode() || "".equals(salesQuotation.getGroupCode())) {
				salesQuotation.setGroupCode(VixUUID.getUUID());
			}
			
			String quoteSubject = salesQuotation.getQuoteSubject();
			String py = ChnToPinYin.getPYString(quoteSubject);
			salesQuotation.setChineseFirstLetter(py.toUpperCase());
			loadCommonData(salesQuotation);
			salesQuotation.setIsTemp("0");
			salesQuotation.setIsDeleted("0");
			List<SalesQuotationItem> sqiList = baseHibernateService.findAllByEntityClassAndAttribute(SalesQuotationItem.class, "salesQuotation.id", salesQuotation.getId());
			Double amount = 0D;
			if (sqiList.size() > 0) {
				for (SalesQuotationItem sqi : sqiList) {
					if (null != sqi) {
						if (null != sqi.getItem().getPrice()) {
							amount += sqi.getItem().getPrice() * sqi.getAmount();
						} else {
							amount += sqi.getPrice() * sqi.getAmount();
						}
					}
				}
				salesQuotation.setAmount(amount);
			}
			baseHibernateService.merge(salesQuotation);
			/** 处理成组码 */
			billGroupCode = baseHibernateService.findEntityByAttribute(BillGroupCode.class, "groupCode", salesQuotation.getGroupCode());
			if (null != billGroupCode) {
				billGroupCode.setIsTemp("0");
				baseHibernateService.merge(billGroupCode);
			}
			if("1".equals(isAllowAudit(BillType.SAL_QUOTATION))) {
				String response = dealStartAndSubmitByBillsCode(BillType.SAL_QUOTATION, salesQuotation);
				if(StringUtils.isNotEmpty(response)) {
					JSONObject json = JSONObject.fromObject(response);
					if(json.has("status")) {
						if("1".equals(json.getString("status"))) {
							salesQuotation.setStatus("1");
							salesQuotation = vixntBaseService.merge(salesQuotation);
							renderText("提交成功!");
						}
					}
				}
			}else {
				renderText("提交失败!未绑定审批流程!");
			}
		} catch (Exception e) {
			renderText("提交失败!");
			e.printStackTrace();
		}
	}
	
	public String goAudit() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				salesQuotation = vixntBaseService.findEntityById(SalesQuotation.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAudit";
	}
	/** 删除报价单 */
	public String deleteById() {
		try {
			if(StrUtils.isNotEmpty(id)){
				SalesQuotation p = baseHibernateService.findEntityById(SalesQuotation.class, id);
				if (null != p) {
					//p.setIsDeleted("1");
					baseHibernateService.deleteByEntity(p);
					renderText(DELETE_SUCCESS);
				} else {
					renderText(DELETE_FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 删除报价单明细 */
	public String deleteSalesQuotationItemById() {
		try {
			SalesQuotationItem p = baseHibernateService.findEntityById(SalesQuotationItem.class, id);
			if (null != p) {
				if (null != p.getIsHasGift() && "1".equals(p.getIsHasGift())) {
					baseHibernateService.deleteByAttribute(SalesQuotationItem.class, "salesQuotationItemId", id);
				}
				baseHibernateService.deleteByEntity(p);
				renderText(DELETE_SUCCESS);
			} else {
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String saveOrUpdateSalesQuotationItem() {
		boolean isSave = true;
		try {
			if (null != salesQuotationItem.getId()) {
				isSave = false;
			}
			loadCommonData(salesQuotationItem);
			String barCode = getRequestParameter("barCode");
			if (null != barCode && !"".equals(barCode)) {
				Item item = baseHibernateService.findEntityByAttribute(Item.class, "barCode", barCode);
				if (null != item) {
					SalesQuotationItem sqi = new SalesQuotationItem();
					sqi.setItem(item);
					sqi.setAmount(1D);
					sqi.setPrice(item.getPrice());
					loadCommonData(sqi);
					baseHibernateService.merge(sqi);
				}
			} else {
				String giftJson = getRequestParameter("giftJson");
				if (null != giftJson && !"".equals(giftJson)) {
					salesQuotationItem.setIsHasGift("1");
				}
				String[] attrArray = { "item", "salesQuotation" };
				checkEntityNullValue(salesQuotationItem, attrArray);
				salesQuotationItem = baseHibernateService.merge(salesQuotationItem);
				if (null != giftJson && !"".equals(giftJson)) {
					String[] gifts = giftJson.split("_");
					for (String gift : gifts) {
						if (null != gift && !"".equals(gift)) {
							String[] giftItem = gift.split("-");
							SalesQuotationItem sqi = new SalesQuotationItem();
							sqi.setSalesQuotation(salesQuotationItem.getSalesQuotation());
							Item i = baseHibernateService.findEntityById(Item.class, giftItem[0]);
							if (null != i) {
								sqi.setItem(i);
								if (!",".equals(giftItem[1])) {
									sqi.setSpecification(giftItem[1]);
								}
								sqi.setAmount(Double.parseDouble(giftItem[2]));
								sqi.setPrice(Double.parseDouble(giftItem[3]));
								loadCommonData(sqi);
								sqi.setIsGift("1");
								sqi.setSalesQuotationItemId(salesQuotationItem.getId());
								if (sqi.getPrice() > 0) {
									sqi.setMemo("买赠");
								} else {
									sqi.setMemo("赠品");
								}
								baseHibernateService.merge(sqi);
							}
						}
					}
				}
			}
			if (isSave) {
				renderText("1:" + SAVE_SUCCESS);
			} else {
				renderText("1:" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public String goSaveOrUpdateSalesQuotationItem() {
		try {
			if (null != id && StrUtils.isNotEmpty(id)) {
				salesQuotationItem = baseHibernateService.findEntityById(SalesQuotationItem.class, id);
			}
			if (null != types && StrUtils.isNotEmpty(types)) {
				return "scanUpdateSalesQuotationItem";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdateSalesQuotationItem";
	}

	public void getSalesQuotationItemJson() {
		try {
			Pager pager = getPager();

			Map<String, Object> params = getParams();
			if (null != id && StrUtils.isNotEmpty(id)) {
				params.put("salesQuotation.id," + SearchCondition.EQUAL, id);
				if (null != name && !"".equals(name)) {
					params.put("item.name," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				baseHibernateService.findPagerByHqlConditions(pager, SalesQuotationItem.class, params);
			}
			String[] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 订单子项价格汇总 */
	public void getSalesQuotationItemTotal() {
		try {
			if (null != id && StrUtils.isNotEmpty(id)) {
				SalesQuotation sq = baseHibernateService.findEntityById(SalesQuotation.class, id);
				if (null != sq) {
					Double totalAmount = 0d;
					for (SalesQuotationItem sqi : sq.getSalesQuotationItems()) {
						totalAmount += sqi.getPrice() * sqi.getAmount();
					}
					DecimalFormat df = new DecimalFormat(".##");
					if(totalAmount != 0){
						String st = df.format(totalAmount);
						renderJson(st);
					}else{
						renderJson(totalAmount.toString());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void dealBillGroupCode() throws Exception {
		if (null != billGroupCode.getId() && StrUtils.isNotEmpty(billGroupCode.getId())) {
			billGroupCode = baseHibernateService.findEntityById(BillGroupCode.class, billGroupCode.getId());
			if (null != billGroupCode) {
				if (null == billGroupCode.getBillCode() || "".equals(billGroupCode.getBillCode())) {
					billGroupCode.setBillCode(salesQuotation.getId().toString());
					baseHibernateService.merge(billGroupCode);
				}
			}
		}
	}

	public String convertSalesQuotationTemplateToSalesQuotation() {
		try {
			salesQuotation = new SalesQuotation();
			billGroupCode.setBillType(BillType.SAL_QUOTATION);
			billGroupCode.setGroupCode(UUID.randomUUID().toString());
			loadCommonData(billGroupCode);
			billGroupCode = baseHibernateService.merge(billGroupCode);
			String salesQuotationTemplateIds = getRequestParameter("salesQuotationTemplateIds");
			if (null != salesQuotationTemplateIds && !"".equals(salesQuotationTemplateIds)) {
				String[] sqtStr = salesQuotationTemplateIds.split(",");
				Set<String> sqtIdSet = new HashSet<String>();
				for (String sqId : sqtStr) {
					if (null != sqId && !"".equals(sqId)) {
						sqtIdSet.add(sqId);
					}
				}
				salesQuotation.setCreateTime(new Date());
				loadCommonData(salesQuotation);
				salesQuotation.setGroupCode(billGroupCode.getGroupCode());
				salesQuotation = baseHibernateService.merge(salesQuotation);
				for (String sqtId : sqtIdSet) {
					SalesQuotationTemplate sqt = baseHibernateService.findEntityById(SalesQuotationTemplate.class, sqtId);
					if (null != sqt && sqt.getSalesQuotationTemplateItems().size() > 0) {
						for (SalesQuotationTemplateItem sqti : sqt.getSalesQuotationTemplateItems()) {
							SalesQuotationItem sqi = new SalesQuotationItem();
							sqi.setSalesQuotation(salesQuotation);
							if (null != sqti.getItem()) {
								sqi.setItem(sqti.getItem());
							}
							sqi.setAmount(sqti.getAmount());
							sqi.setNetPrice(sqti.getPrice());
							loadCommonData(sqi);
							baseHibernateService.merge(sqi);
						}
					}
				}
				salesQuotation = baseHibernateService.merge(salesQuotation);
				dealBillGroupCode();
			} else {
				salesQuotation.setCreateTime(new Date());
				loadCommonData(salesQuotation);
				salesQuotation = baseHibernateService.merge(salesQuotation);
				dealBillGroupCode();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	public String goSourceList() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				salesQuotation = vixntBaseService.findEntityById(SalesQuotation.class, id);
				if(salesQuotation != null) {
					baseEntityList = new ArrayList<>();
					baseEntityList.add(salesQuotation);
					if(StringUtils.isNotEmpty(salesQuotation.getSourceBillCode())&&StringUtils.isNotEmpty(salesQuotation.getSourceClassName())) {
						getSourceBaseEntity(baseEntityList, salesQuotation);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSourceList";
	}
	public String goShowBeforeAndAfter() {
		try {
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				salesQuotation = baseHibernateService.findEntityById(SalesQuotation.class,id);
				if (salesQuotation != null && salesQuotation.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(salesQuotation.getCreateTime()));
					params.put("isTemp", "0");
					params.put("isDelete", "0");
					if (StrUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							salesQuotation = (SalesQuotation) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(salesQuotation.getCreateTime()), params, salesQuotation, "before");
						} else if ("after".equals(str)) {
							salesQuotation = (SalesQuotation) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(salesQuotation.getCreateTime()), params, salesQuotation, "after");
						}
					}
					if (salesQuotation == null || StrUtils.isEmpty(salesQuotation.getId())) {
						salesQuotation = baseHibernateService.findEntityById(SalesQuotation.class,id);
						Map<String, Object> params1 = new HashMap<String, Object>();
						params.put("sourceClassPk," + SearchCondition.EQUAL, salesQuotation.getId());
						flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params1);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	public String show() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				salesQuotation = vixntBaseService.findEntityById(SalesQuotation.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BillGroupCode getBillGroupCode() {
		return billGroupCode;
	}

	public void setBillGroupCode(BillGroupCode billGroupCode) {
		this.billGroupCode = billGroupCode;
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

	public String getGroupCodes() {
		return groupCodes;
	}

	public void setGroupCodes(String groupCodes) {
		this.groupCodes = groupCodes;
	}

	public String getIsAllowAudit() {
		return isAllowAudit;
	}

	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}

	public SalesQuotation getSalesQuotation() {
		return salesQuotation;
	}

	public void setSalesQuotation(SalesQuotation salesQuotation) {
		this.salesQuotation = salesQuotation;
	}

	public List<SalesQuotation> getSqList() {
		return sqList;
	}

	public void setSqList(List<SalesQuotation> sqList) {
		this.sqList = sqList;
	}

	public SalesQuotationItem getSalesQuotationItem() {
		return salesQuotationItem;
	}

	public void setSalesQuotationItem(SalesQuotationItem salesQuotationItem) {
		this.salesQuotationItem = salesQuotationItem;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	@Override
	public String getEntityName() {
		return entityName;
	}

	@Override
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public List<SaleChance> getSaleChanceList() {
		return saleChanceList;
	}

	public void setSaleChanceList(List<SaleChance> saleChanceList) {
		this.saleChanceList = saleChanceList;
	}

	public List<ContactPerson> getContactPersonList() {
		return contactPersonList;
	}

	public void setContactPersonList(List<ContactPerson> contactPersonList) {
		this.contactPersonList = contactPersonList;
	}

	public List<BaseEntity> getBaseEntityList() {
		return baseEntityList;
	}

	public void setBaseEntityList(List<BaseEntity> baseEntityList) {
		this.baseEntityList = baseEntityList;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public List<FlowApprovalOpinion> getFlowApprovalOpinionList() {
		return flowApprovalOpinionList;
	}

	public void setFlowApprovalOpinionList(List<FlowApprovalOpinion> flowApprovalOpinionList) {
		this.flowApprovalOpinionList = flowApprovalOpinionList;
	}
}