package com.vix.sales.quotes.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseBOEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.Regional;
import com.vix.core.utils.DateUtil;
import com.vix.crm.salechance.entity.SaleChance;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 销售报价单  列表显示数据：主题，客户，单据类型，价格，单据日期*/
public class SalesQuotation extends BaseBOEntity {

	private static final long serialVersionUID = 1L;

	/** 单据日期 */
	private Date billDate;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 报价主题 */
	private String quoteSubject;
	/** 地域 */
	private Regional regional;
	/** 部门 */
	private OrganizationUnit dept;
	/** 业务员编码 */
	private String salePersonCode;
	/** 报价单类型 */
	private String type;
	/** 报价模板编码 */
	private String quotationTemplateCode;
	/** 销售订单编号 */
	private String saleOrderCode;
	/** 单据成组编码 */
	private String groupCode;
	/** 业务类型 */
	private String bizType;
	/** 单据类型 */
	private String formType;
	/** 联系人 */
	private ContactPerson contactPerson;
	/** 销售组织 */
	private OrganizationUnit salesOrg;
	/** 销售人员 */
	private Employee salesMan;
	/** 订单日期 */
	private Date orderTime;
	/** 币种 */
	private CurrencyType currencyType;
	/** 税率 */
	private String tax;
	/** 版本 */
	private String version;
	/** 交货日期 */
	private Date dilveryDate;
	/** 地址1 */
	private String address1;
	/** 地址2 */
	private String address2;
	/** 城市 */
	private String city;
	/** 省/州 */
	private String state;
	/** 国家 */
	private String country;
	/** 邮政编码 */
	private String postCode;
	/** 报价有效期从 */
	private Date validQuotationFrom;
	/** 报价有效期至 */
	private Date validQuotationTo;
	/** 报价单明细 */
	private Set<SalesQuotationItem> salesQuotationItems = new HashSet<SalesQuotationItem>();
	/** 是否为临时对象 临时为：1 实际使用为空 */
	private String isTemp;
	/** 是否为项目式报价 是：1 否：0 */
	private String isProjectQuotes;
	/** 金额 */
	private Double amount;
	/** 是否已转化为销售订单 是：1 否：0 */
	private String isChangeToSaleOrder;
	/** 销售机会  */
	private SaleChance saleChance;
	/** 报价人  */
	private Employee employee;
	/** 联系方式  */
	private String phone;
	/** 交付说明  */
	private String deliveryMemo;
	/** 付款说明  */
	private String paymentMemo;
	/** 包装运输说明  */
	private String packMemo;
	
	public SalesQuotation(){}

	public Date getBillDate() {
		return billDate;
	}

	public String getBillDateStr(){
		if (null != billDate) {
			return DateUtil.format(billDate);
		} else {
			return "";
		}
	}
	
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getQuoteSubject() {
		return quoteSubject;
	}

	public void setQuoteSubject(String quoteSubject) {
		this.quoteSubject = quoteSubject;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public OrganizationUnit getDept() {
		return dept;
	}

	public void setDept(OrganizationUnit dept) {
		this.dept = dept;
	}

	public String getSalePersonCode() {
		return salePersonCode;
	}

	public void setSalePersonCode(String salePersonCode) {
		this.salePersonCode = salePersonCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuotationTemplateCode() {
		return quotationTemplateCode;
	}

	public void setQuotationTemplateCode(String quotationTemplateCode) {
		this.quotationTemplateCode = quotationTemplateCode;
	}

	public String getSaleOrderCode() {
		return saleOrderCode;
	}

	public void setSaleOrderCode(String saleOrderCode) {
		this.saleOrderCode = saleOrderCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public OrganizationUnit getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(OrganizationUnit salesOrg) {
		this.salesOrg = salesOrg;
	}

	public Employee getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(Employee salesMan) {
		this.salesMan = salesMan;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

	public Date getDilveryDate() {
		return dilveryDate;
	}
	
	public String getDilveryDateStr(){
		if(null != dilveryDate){
			return DateUtil.format(dilveryDate);
		}
		return "";
	}

	public void setDilveryDate(Date dilveryDate) {
		this.dilveryDate = dilveryDate;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Date getValidQuotationFrom() {
		return validQuotationFrom;
	}
	
	public String getValidQuotationFromStr(){
		if(null != validQuotationFrom){
			return DateUtil.format(validQuotationFrom);
		}
		return "";
	}

	public void setValidQuotationFrom(Date validQuotationFrom) {
		this.validQuotationFrom = validQuotationFrom;
	}

	public Date getValidQuotationTo() {
		return validQuotationTo;
	}
	
	public String getValidQuotationToStr(){
		if(null != validQuotationTo){
			return DateUtil.format(validQuotationTo);
		}
		return "";
	}


	public void setValidQuotationTo(Date validQuotationTo) {
		this.validQuotationTo = validQuotationTo;
	}
 

	public Set<SalesQuotationItem> getSalesQuotationItems() {
		return salesQuotationItems;
	}

	public void setSalesQuotationItems(Set<SalesQuotationItem> salesQuotationItems) {
		this.salesQuotationItems = salesQuotationItems;
	}

	@Override
	public String getIsTemp() {
		return isTemp;
	}

	@Override
	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	public String getIsProjectQuotes() {
		return isProjectQuotes;
	}

	public void setIsProjectQuotes(String isProjectQuotes) {
		this.isProjectQuotes = isProjectQuotes;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getIsChangeToSaleOrder() {
		return isChangeToSaleOrder;
	}

	public void setIsChangeToSaleOrder(String isChangeToSaleOrder) {
		this.isChangeToSaleOrder = isChangeToSaleOrder;
	}

	public SaleChance getSaleChance() {
		return saleChance;
	}

	public void setSaleChance(SaleChance saleChance) {
		this.saleChance = saleChance;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDeliveryMemo() {
		return deliveryMemo;
	}

	public void setDeliveryMemo(String deliveryMemo) {
		this.deliveryMemo = deliveryMemo;
	}

	public String getPaymentMemo() {
		return paymentMemo;
	}

	public void setPaymentMemo(String paymentMemo) {
		this.paymentMemo = paymentMemo;
	}

	public String getPackMemo() {
		return packMemo;
	}

	public void setPackMemo(String packMemo) {
		this.packMemo = packMemo;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
