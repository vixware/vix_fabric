package com.vix.crm.service.entity;

import java.util.Date;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseBOEntity;
import com.vix.core.utils.DateUtil;
import com.vix.crm.base.entity.RepairOrderType;
import com.vix.crm.project.entity.CrmProject;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 维修工单 */
public class RepairOrder extends BaseBOEntity {

	private static final long serialVersionUID = 1L;

	/** 工单主题 */
	private String subject;
	/** 接单人 */
	private Employee employee;
	/** 接单日期 */
	private Date receiveDate;
	/** 维修产品 */
	private String repairProduct;
	/** 产品生产日期 */
	private Date produceDate;
	/** 产品销售日期 */
	private Date saleDate;
	/** 是否保修 */
	private String isWarranty;
	/** 故障描述 */
	private String troubleContent;
	/** 注意事项 */
	private String notice;
	/** 费用 */
	private Double money;
	/** 已收款 */
	private Double backSection;
	/** 约定交付日期 */
	private Date deliveryDate;
	/** 备注 */
	private String memo;
	/** 承接部门 */
	private OrganizationUnit organizationUnit;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 联系人 */
	private ContactPerson contactPerson;
	/** 项目 */
	private CrmProject crmProject;
	/** 工单类型 */
	private RepairOrderType repairOrderType;

	public RepairOrder() {
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}
	
	public String getReceiveDateStr() {
		if(receiveDate != null){
			return DateUtil.format(receiveDate);
		}
		return "";
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getRepairProduct() {
		return repairProduct;
	}

	public void setRepairProduct(String repairProduct) {
		this.repairProduct = repairProduct;
	}

	public Date getProduceDate() {
		return produceDate;
	}
	
	public String getProduceDateStr() {
		if(produceDate != null){
			return DateUtil.format(produceDate);
		}
		return "";
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public Date getSaleDate() {
		return saleDate;
	}
	
	public String getSaleDateStr() {
		if(saleDate != null){
			return DateUtil.format(saleDate);
		}
		return "";
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getIsWarranty() {
		return isWarranty;
	}

	public void setIsWarranty(String isWarranty) {
		this.isWarranty = isWarranty;
	}

	public String getTroubleContent() {
		return troubleContent;
	}

	public void setTroubleContent(String troubleContent) {
		this.troubleContent = troubleContent;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getBackSection() {
		return backSection;
	}

	public void setBackSection(Double backSection) {
		this.backSection = backSection;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}
	
	public String getDeliveryDateStr() {
		if(deliveryDate != null){
			return DateUtil.format(deliveryDate);
		}
		return "";
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}

	public RepairOrderType getRepairOrderType() {
		return repairOrderType;
	}

	public void setRepairOrderType(RepairOrderType repairOrderType) {
		this.repairOrderType = repairOrderType;
	}

}
