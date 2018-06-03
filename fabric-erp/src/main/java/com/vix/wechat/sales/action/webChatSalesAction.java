/**
 * 
 */
package com.vix.wechat.sales.action;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.billtype.BillType;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.Regional;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.NumberUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;
import com.vix.system.billTypeManagement.entity.BillsType;
import com.vix.wechat.base.action.WechatBaseAction;

/**
 * @author Bluesnow 2016年8月22日
 * 
 *         移动销售
 */
@Controller
@Scope("prototype")
public class webChatSalesAction extends WechatBaseAction {

	private static final long serialVersionUID = 5723683711723147798L;

	private String id;
	private String customerAccountId;
	private String name;
	private String title;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	private SalesOrder salesOrder;
	private List<SalesOrder> salesOrderList;
	private List<CurrencyType> currencyTypeList;
	private List<Regional> regionalList;
	private List<BillsType> billsTypeList;
	private List<ContactPerson> contactPersonList;
	private List<CustomerAccount> customerList;

	public String goSalesList() {
		try {
			Map<String,Object> params = getParams();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			if (null != title && !"".equals(title)) {
				params.put("title," + SearchCondition.ANYLIKE, URLDecoder.decode(title.trim(), "UTF-8"));
			}
			if(null != name && !"".equals(name)){
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE,URLDecoder.decode(name.trim(), "UTF-8"));
			}
			salesOrderList = wechatBaseService.findAllDataByConditions(SalesOrder.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesList";
	}
	
	public String goSaveOrUpdate(){
		try {
			Map<String ,Object> params = getParams();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			customerList = wechatBaseService.findAllByConditions(CustomerAccount.class, params);
			isAllowAudit = isAllowAudit(BillType.SA_ORDER);
			currencyTypeList = wechatBaseService.findAllByEntityClass(CurrencyType.class);
			regionalList = wechatBaseService.findAllByEntityClass(Regional.class);
			billsTypeList = wechatBaseService.findAllByEntityClassAndAttribute(BillsType.class, "billsProperty.propertyCode", BillType.SA_ORDER);
			if (null != id && StrUtils.isNotEmpty(id)) {
				salesOrder = wechatBaseService.findEntityById(SalesOrder.class, id);
				if(null != salesOrder){
					if(null != salesOrder.getSaleOrderItems() && salesOrder.getSaleOrderItems().size() > 0){
						Double count = 0.00;
						Set<SaleOrderItem> soiSet = salesOrder.getSaleOrderItems();
						for(SaleOrderItem soi : soiSet){
							if(null != soi.getItem().getPrice()){
								count += soi.getItem().getPrice() * soi.getAmount();
							}else {
								count += soi.getPrice() * soi.getAmount();
							}
						}
						salesOrder.setAmount(NumberUtil.round4(count));
					}
					if(null != salesOrder.getCustomerAccount().getId() && StrUtils.isNotEmpty(salesOrder.getCustomerAccount().getId())){
						contactPersonList = wechatBaseService.findAllByEntityClassAndAttribute(ContactPerson.class, "customerAccount.id", salesOrder.getCustomerAccount().getId());
					}
					if (null != salesOrder.getAuditStatus() && "1".equals(salesOrder.getAuditStatus())) {
						return "showOrder";
					}
				}
			} else {
				salesOrder = new SalesOrder();
				salesOrder.setIsTemp("1");
				salesOrder.setIsDeleted("0");
				salesOrder.setBillDate(new Date());
				Employee baseEmp = getEmployee();
				if (null != baseEmp) {
					Employee emp = wechatBaseService.findEntityById(Employee.class, baseEmp.getId());
					salesOrder.setSalePerson(emp);
					salesOrder.setSalePersonCode(emp.getCode());
					if (null != emp) {
						salesOrder.setSaleOrg(emp.getOrganizationUnit());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	public String saveOrUpdate(){
		boolean isSave = true;
		try {
			if (null != salesOrder.getId() && StrUtils.isNotEmpty(salesOrder.getId())) {
				isSave = false;
			} else {
				salesOrder.setCreateTime(new Date());
				loadCommonData(salesOrder);
			}
			String[] attrArray ={"customerAccount","contactPerson","salePerson","currencyType","saleOrg","regional","crmProject","channelDistributor",
					"employee","orderPrintData","distributionCenter","organization","invWarehouse","logistics","orderBatch","",""};
			checkEntityNullValue(salesOrder,attrArray);
		
			String title = salesOrder.getTitle();
			String py = ChnToPinYin.getPYString(title);
			salesOrder.setChineseFirstLetter(py.toUpperCase());
			salesOrder.setIsTemp("0");
			salesOrder.setIsDeleted("0");
			salesOrder = wechatBaseService.mergeOriginal(salesOrder);
			
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
	
	public String loadContentPerson(){
		try {
			Map<String, Object> param = getParams();
			if(null != customerAccountId && customerAccountId != ""){
				param.put("customerAccount.id," + SearchCondition.EQUAL, customerAccountId);
				contactPersonList = wechatBaseService.findAllByConditions(ContactPerson.class, param);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "loadContentPerson";
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsAllowAudit() {
		return isAllowAudit;
	}

	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
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

	public List<ContactPerson> getContactPersonList() {
		return contactPersonList;
	}

	public void setContactPersonList(List<ContactPerson> contactPersonList) {
		this.contactPersonList = contactPersonList;
	}

	public List<CustomerAccount> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerAccount> customerList) {
		this.customerList = customerList;
	}

	public String getCustomerAccountId() {
		return customerAccountId;
	}

	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}
}
