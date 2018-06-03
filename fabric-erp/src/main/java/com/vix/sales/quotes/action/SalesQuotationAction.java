package com.vix.sales.quotes.action;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import com.vix.common.id.VixUUID;
import com.vix.common.security.util.DaysUtils;
import com.vix.common.share.entity.BillGroupCode;
import com.vix.common.share.entity.Regional;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;
import com.vix.sales.quotes.entity.SalesQuotation;
import com.vix.sales.quotes.entity.SalesQuotationItem;
import com.vix.sales.quotes.entity.SalesQuotationTemplate;
import com.vix.sales.quotes.entity.SalesQuotationTemplateItem;
import com.vix.system.billTypeManagement.entity.BillsType;

@Controller
@Scope("prototype")
public class SalesQuotationAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private OrderProcessController orderProcessController;
	private String id;
	private String ids;
	private String groupCodes;// 成组码
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	private SalesQuotation salesQuotation;
	private List<SalesQuotation> sqList;
	private SalesQuotationItem salesQuotationItem;
	private String str;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
			baseHibernateService.findPagerByHqlConditions(getPager(), SalesQuotation.class, params);
			sqList = getPager().getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goList";
	}

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			String isProjectQuotes = getRequestParameter("isProjectQuotes");
			if (null != isProjectQuotes && !"".equals(isProjectQuotes)) {
				params.put("isProjectQuotes," + SearchCondition.EQUAL, isProjectQuotes);
			}
			String quoteSubject = getRequestParameter("quoteSubject");
			if (null != quoteSubject && !"".equals(quoteSubject)) {
				quoteSubject = decode(quoteSubject, "UTF-8");
				params.put("quoteSubject," + SearchCondition.ANYLIKE, quoteSubject);
			}
			String customerAccount = getRequestParameter("customerAccount");
			if (StrUtils.objectIsNotNull(customerAccount)) {
				customerAccount = decode(customerAccount, "UTF-8");
				params.put("customerAccount.name," + SearchCondition.ANYLIKE, customerAccount);
			}
			String startTime = getRequestParameter("startTime");
			String endTime = getRequestParameter("endTime");
			if (StrUtils.objectIsNotNull(startTime) && StrUtils.objectIsNotNull(endTime)) {
				Date sTime = sdf.parse(startTime);
				Date eTime = sdf.parse(endTime);
				params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(sTime) + "!" + DaysUtils.getEndDay(eTime));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), SalesQuotation.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	public String goChooseSalesQuotation() {
		return "goChooseSalesQuotation";
	}

	/** 获取列表数据 */
	public String goSubListContent() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			String isProjectQuotes = getRequestParameter("isProjectQuotes");
			if (null != isProjectQuotes && !"".equals(isProjectQuotes)) {
				params.put("isProjectQuotes," + SearchCondition.EQUAL, isProjectQuotes);
			}
			String quoteSubject = getRequestParameter("quoteSubject");
			if (null != quoteSubject && !"".equals(quoteSubject)) {
				quoteSubject = decode(quoteSubject, "UTF-8");
				params.put("quoteSubject," + SearchCondition.ANYLIKE, quoteSubject);
			}
			getPager().setPageSize(6);
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), SalesQuotation.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubListContent";
	}

	public String goRelatedSalesQuotation() {
		try {
			String id = getRequestParameter("id");
			String type = getRequestParameter("type");
			groupCodes = "";
			if (null != id && !"".equals(id) && null != type && !"".equals(type)) {
				if ("salesOrder".equals(type)) {
					Map<String, Object> p = new HashMap<String, Object>();
					p.put("billType," + SearchCondition.EQUAL, BillType.SA_ORDER);
					p.put("billCode," + SearchCondition.EQUAL, id);
					p.put("isTemp," + SearchCondition.EQUAL, "0");
					List<BillGroupCode> bgcList = baseHibernateService.findAllByConditions(BillGroupCode.class, p);
					for (BillGroupCode bgc : bgcList) {
						groupCodes += bgc.getGroupCode() + ",";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goRelatedSalesQuotation";
	}

	/** 获取列表数据 */
	public String goRelatedListContent() {
		try {
			if (null != groupCodes && !"".equals(groupCodes)) {
				Map<String, Object> params = getParams();
				params.put("isTemp," + SearchCondition.NOEQUAL, "1");
				params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
				params.put("groupCode," + SearchCondition.IN, groupCodes);
				getPager().setPageSize(1000);
				baseHibernateService.findPagerByHqlConditions(getPager(), SalesQuotation.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goRelatedListContent";
	}

	/** 跳转至用户修改页面 */
	private BillGroupCode billGroupCode = new BillGroupCode();// 成组码
	private List<Regional> regionalList;
	private List<BillsType> billsTypeList;

	public String goSaveOrUpdate() {
		try {
			regionalList = baseHibernateService.findAllByEntityClass(Regional.class);
			billsTypeList = baseHibernateService.findAllByEntityClassAndAttribute(BillsType.class, "billsProperty.propertyCode", "SA_QUOTATION");
			if (null != id && !"".equals(id) && !"0".equals(id)) {
				isAllowAudit = isAllowAudit(BillType.SAL_QUOTATION);
				salesQuotation = baseHibernateService.findEntityById(SalesQuotation.class, id);
			} else {
				billGroupCode.setIsTemp("1");
				billGroupCode.setBillType(BillType.SAL_QUOTATION);
				billGroupCode.setGroupCode(UUID.randomUUID().toString());
				loadCommonData(billGroupCode);
				salesQuotation = new SalesQuotation();
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

	public String showSalesQuotation() {
		try {
			if (null != id && !"".equals(id)) {
				salesQuotation = baseHibernateService.findEntityById(SalesQuotation.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showSalesQuotation";
	}

	// 打印
	public String goPrintSalesQuotation() {
		try {
			salesQuotation = baseHibernateService.findEntityById(SalesQuotation.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintSalesQuotation";
	}

	// 上一条 下一条
	public String goShowBeforeAndAfter() {
		try {
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				salesQuotation = baseHibernateService.findEntityById(SalesQuotation.class, id);
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
						salesQuotation = baseHibernateService.findEntityById(SalesQuotation.class, id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showSalesQuotation";
	}

	// 高级搜索
	public String goSearch() {
		return "goSearch";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != salesQuotation.getId() && !"".equals(salesQuotation.getId())) {
				isSave = false;
			} else {
				salesQuotation.setCreateTime(new Date());
			}
			dealBillGroupCode();

			String[] attrArray = { "salesMan", "salesOrg", "dept", "regional", };
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
			baseHibernateService.merge(salesQuotation);
			/** 处理成组码 */
			billGroupCode = baseHibernateService.findEntityByAttribute(BillGroupCode.class, "groupCode", salesQuotation.getGroupCode());
			if (null != billGroupCode) {
				billGroupCode.setIsTemp("0");
				baseHibernateService.merge(billGroupCode);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			SalesQuotation p = baseHibernateService.findEntityById(SalesQuotation.class, id);
			if (null != p) {
				p.setIsDeleted("1");
				baseHibernateService.merge(p);
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

	/** 处理删除操作 */
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
				baseHibernateService.batchDelete(SalesQuotation.class, delIds);
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

	public void getSalesQuotationItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				SalesQuotation sq = baseHibernateService.findEntityById(SalesQuotation.class, id);
				if (null != sq) {
					json = convertListToJson(new ArrayList<SalesQuotationItem>(sq.getSalesQuotationItems()), sq.getSalesQuotationItems().size(), "salesQuotation");
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
				setMessage("1:" + SAVE_SUCCESS);
			} else {
				setMessage("1:" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage("0:" + SAVE_FAIL);
			} else {
				this.setMessage("0:" + UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public String goSaveOrUpdateSalesQuotationItem() {
		try {
			if (null != id && !"".equals(id)) {
				salesQuotationItem = baseHibernateService.findEntityById(SalesQuotationItem.class, id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdateSalesQuotationItem";
	}

	/** 订单子项价格汇总 */
	public void getSalesQuotationItemTotal() {
		try {
			if (null != id && !"".equals(id)) {
				SalesQuotation sq = baseHibernateService.findEntityById(SalesQuotation.class, id);
				if (null != sq) {
					Double totalAmount = 0d;
					for (SalesQuotationItem sqi : sq.getSalesQuotationItems()) {
						totalAmount += sqi.getPrice() * sqi.getAmount();
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

	private void dealBillGroupCode() throws Exception {
		if (null != billGroupCode.getId() && !"".equals(billGroupCode.getId())) {
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

	public String saveOrUpdateForCustomerAccount() {
		saveOrUpdate();
		return UPDATE;
	}

	public String goSaveOrUpdateForCustomerAccount() {
		goSaveOrUpdate();
		return "goSaveOrUpdateForCustomerAccount";
	}

	public String goListContentForCustomerAccount() {
		Map<String, Object> params = getParams();
		if (null != id && !"".equals(id)) {
			params.put("customerAccount.id," + SearchCondition.EQUAL, id);
		}
		goListContent();
		return "goSingleListForCustomerAccount";
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

	public SalesQuotation getSalesQuotation() {
		return salesQuotation;
	}

	public void setSalesQuotation(SalesQuotation salesQuotation) {
		this.salesQuotation = salesQuotation;
	}

	public BillGroupCode getBillGroupCode() {
		return billGroupCode;
	}

	public void setBillGroupCode(BillGroupCode billGroupCode) {
		this.billGroupCode = billGroupCode;
	}

	public SalesQuotationItem getSalesQuotationItem() {
		return salesQuotationItem;
	}

	public void setSalesQuotationItem(SalesQuotationItem salesQuotationItem) {
		this.salesQuotationItem = salesQuotationItem;
	}

	public List<Regional> getRegionalList() {
		return regionalList;
	}

	public void setRegionalList(List<Regional> regionalList) {
		this.regionalList = regionalList;
	}

	public List<SalesQuotation> getSqList() {
		return sqList;
	}

	public void setSqList(List<SalesQuotation> sqList) {
		this.sqList = sqList;
	}

	public List<BillsType> getBillsTypeList() {
		return billsTypeList;
	}

	public void setBillsTypeList(List<BillsType> billsTypeList) {
		this.billsTypeList = billsTypeList;
	}

	public String getIsAllowAudit() {
		return isAllowAudit;
	}

	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
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