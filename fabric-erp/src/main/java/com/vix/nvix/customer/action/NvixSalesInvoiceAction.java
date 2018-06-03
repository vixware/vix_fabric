package com.vix.nvix.customer.action;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.billtype.BillType;
import com.vix.common.share.entity.BillGroupCode;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.SaleInvoiceType;
import com.vix.crm.project.entity.ActionHistory;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.item.entity.Item;
import com.vix.sales.invoice.entity.SalesInvoice;
import com.vix.sales.invoice.entity.SalesInvoiceItem;
import com.vix.sales.order.entity.SalesOrder;

@Controller
@Scope("prototype")
public class NvixSalesInvoiceAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private SalesInvoice salesInvoice;
	private SalesInvoiceItem salesInvoiceItem;
	private String invoiceType;
	private String entityName;
	
	public void goListContent(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				code = decode(code, "UTF-8");
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			String customerName = getRequestParameter("customerName");
			if(StrUtils.objectIsNotNull(customerName)){
				customerName = decode(customerName, "UTF-8");
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, customerName);
			}
			pager = baseHibernateService.findPagerByHqlConditions(getPager(), SalesInvoice.class, params);
			renderDataTable(pager,null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	private List<SaleInvoiceType> saleInvoiceTypeList;
	public String goSaveOrUpdate() {
		try {
			saleInvoiceTypeList = baseHibernateService.findAllByEntityClassAndAttribute(SaleInvoiceType.class, "enable", "1");
			if(null != id && !"".equals(id)){
				salesInvoice = baseHibernateService.findEntityById(SalesInvoice.class,id);
				if(null != salesInvoice.getCustomerAccount() && StrUtils.isNotEmpty(salesInvoice.getCustomerAccount().getId())){
					salesOrderList = baseHibernateService.findAllByEntityClassAndAttribute(SalesOrder.class, "customerAccount.id", salesInvoice.getCustomerAccount().getId());
				}
			}else{
				salesInvoice = new SalesInvoice();
				salesInvoice.setInvoiceType(invoiceType);
				salesInvoice.setCreateTime(new Date());
				Employee e = getEmployee();
				if(e != null){
					salesInvoice.setEmployee(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != salesInvoice.getId()) {
				isSave = false;
			} else {
				salesInvoice.setCreateTime(new Date());
				loadCommonData(salesInvoice);
			}
			if(null == salesInvoice.getSalesOrder() || StrUtils.isEmpty(salesInvoice.getSalesOrder().getId())){
				salesInvoice.setSalesOrder(null);
			}
			salesInvoice.setStatus("S1");
			salesInvoice.setIsTemp("0");
			salesInvoice = baseHibernateService.merge(salesInvoice);
			// 增加行动历史和客户更新
			if(salesInvoice.getCustomerAccount() != null && StrUtils.isNotEmpty(salesInvoice.getCustomerAccount().getId())){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, salesInvoice.getCustomerAccount().getId());
				ActionHistory actionHistory = new ActionHistory();
				actionHistory.setSubject("开票记录");
				actionHistory.setActionDate(new Date());
				actionHistory.setCustomerAccount(customerAccount);
				//actionHistory.setCrmProject(salesInvoice.getCrmProject());
				actionHistory.setDescription("开票记录编辑");
				actionHistory = baseHibernateService.merge(actionHistory);
				customerAccount.setUpdateTime(new Date());
				customerAccount.setStagnateDay(0);
				customerAccount = baseHibernateService.merge(customerAccount);
			}
			dealBillGroupCode();
			if (isSave) {
				renderText(salesInvoice.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText(salesInvoice.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0" + SAVE_FAIL);
			} else {
				renderText("0" + UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	
	/** 处理删除操作 */
	public void deleteById() {
		try {
			SalesInvoice salesInvoice = baseHibernateService.findEntityById(SalesInvoice.class, id);
			if (null != salesInvoice) {
				if (null != salesInvoice.getSalesInvoiceItems() && salesInvoice.getSalesInvoiceItems().size() > 0) {
					renderText("发票信息存在明细,不允许删除!");
				} else {
					baseHibernateService.deleteByEntity(salesInvoice);
					renderText(DELETE_SUCCESS);
				}
			} else {
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	private void dealBillGroupCode() throws Exception {
		if (null != salesInvoice && null != salesInvoice.getId()) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("billType," + SearchCondition.EQUAL, BillType.SA_DELIVERY);
			p.put("billCode," + SearchCondition.EQUAL, salesInvoice.getId()
					.toString());
			List<BillGroupCode> bgcList = baseHibernateService
					.findAllByConditions(BillGroupCode.class, p);
			for (BillGroupCode bgc : bgcList) {
				baseHibernateService.deleteByEntity(bgc);
			}
			SalesInvoice si = baseHibernateService.findEntityById(
					SalesInvoice.class, salesInvoice.getId());
			Set<String> bgcSet = new HashSet<String>();
			for (SalesInvoiceItem sii : si.getSalesInvoiceItems()) {
				if (null != sii && null != sii.getGroupCode()
						&& !"".equals(sii.getGroupCode())) {
					bgcSet.add(sii.getGroupCode());
				}
			}
			if (bgcSet.size() > 0) {
				for (String bgc : bgcSet) {
					BillGroupCode billGroupCode = new BillGroupCode();
					billGroupCode.setBillType(BillType.SA_DELIVERY);
					billGroupCode.setBillCode(si.getId().toString());
					billGroupCode.setGroupCode(bgc);
					loadCommonData(billGroupCode);
					baseHibernateService.merge(billGroupCode);
				}
			}
		}
	}
	
	/** 获取发票明细*/
	public void getSalesInvoiceItemJson(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			if(null != id && StrUtils.isNotEmpty(id)){
				params.put("salesInvoice.id,"+SearchCondition.EQUAL, id);
				if(null != name && !"".equals(name)){
					params.put("itemName,"+SearchCondition.ANYLIKE,URLDecoder.decode(name.trim(), "UTF-8"));
				}
				baseHibernateService.findPagerByHqlConditions(pager, SalesInvoiceItem.class, params);
			}
			renderDataTable(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private List<CurrencyType> currencyTypeList;
	public String addSalesInvoiceItem(){
		try{
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			if (null != id && !"".equals(id)) {
				salesInvoiceItem = baseHibernateService.findEntityById(SalesInvoiceItem.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addSalesInvoiceItem";
	}
	
	public String goChooseItem(){
		return "goChooseItem";
	}
	
	public void getItemList(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			params.put("itemClass,"+SearchCondition.IN, "goods,finishedgoods");
			String selectName = getDecodeRequestParameter("selectName");
			if(StringUtils.isNotEmpty(selectName)){
				params.put("name,"+SearchCondition.ANYLIKE, selectName);
			}
			String code = getRequestParameter("code");
			if(StringUtils.isNotEmpty(code)){
				params.put("code,"+SearchCondition.ANYLIKE, code);
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, Item.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveOrUpdateSalesInvoiceItem(){
		boolean isSave = true;
		try {
			if (null != salesInvoiceItem.getId()) {
				isSave = false;
			} else {
				salesInvoiceItem.setCreateTime(new Date());
				loadCommonData(salesInvoiceItem);
			}
			baseHibernateService.merge(salesInvoiceItem);
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
	}
	
	public void deleteSalesInvoiceItemById() {
		try {
			if (null != id && StrUtils.isNotEmpty(id)) {
				SalesInvoiceItem pb = baseHibernateService.findEntityById(SalesInvoiceItem.class, id);
				if (null != pb) {
					baseHibernateService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				} else {
					renderText(DELETE_FAIL);
				}
			} else {
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	/** 加载销售订单  */
	private List<SalesOrder> salesOrderList;
	public String loadSalesOrder(){
		try {
			Map<String,Object> params = getParams();
			if(null != id){
				params.put("customerAccount.id", id);
				salesOrderList = baseHibernateService.findAllByConditions(SalesOrder.class,params);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "loadSalesOrder";
	}

	public SalesInvoice getSalesInvoice() {
		return salesInvoice;
	}

	public void setSalesInvoice(SalesInvoice salesInvoice) {
		this.salesInvoice = salesInvoice;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public SalesInvoiceItem getSalesInvoiceItem() {
		return salesInvoiceItem;
	}

	public void setSalesInvoiceItem(SalesInvoiceItem salesInvoiceItem) {
		this.salesInvoiceItem = salesInvoiceItem;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public List<SalesOrder> getSalesOrderList() {
		return salesOrderList;
	}

	public void setSalesOrderList(List<SalesOrder> salesOrderList) {
		this.salesOrderList = salesOrderList;
	}

	public List<SaleInvoiceType> getSaleInvoiceTypeList() {
		return saleInvoiceTypeList;
	}

	public void setSaleInvoiceTypeList(List<SaleInvoiceType> saleInvoiceTypeList) {
		this.saleInvoiceTypeList = saleInvoiceTypeList;
	}
}