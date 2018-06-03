package com.vix.oa.adminMg.officeSupplies.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 
 * @ClassName: OfficeSupplies
 * @Description: 办公用品信息管理
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-5-12 下午2:27:02
 */
public class OfficeSupplies extends BaseEntity {

	private static final long serialVersionUID = 87031862813442337L;

	/** 办公用名称 */
	public String officeSuppliesName;
	/** 规格/型号 */
	public String model;
	/** 计量单位 */
	public String prickle;
	/** 办公用品编码 */
	public String officeSuppliesCode;
	/** 单价 */
	public Double unitPrice;
	/** 当前库存 */
	public Double currentInventory;
	/** 供应商 */
	public String supplier;
	/** 最低警戒库存 */
	public String lowestVigilance;
	/** 最高警戒库存 */
	public String maximumVigilance;
	/** 创建人 */
	public String creater;
	/** 登记人 */
	public String registrant;
	/** 登记部门 */
	public String registrationAuthorities;
	/** 审批人 */
	public String approver;
	/**发布人员类型 "O\":\"部门\",\"R\":\"角色\",\"E\":\"人员*/
	private String pubType;
	/** 发布对象的id集合 */
	private String pubIds;
	/**发布人员类型 "O\":\"部门\",\"R\":\"角色\",\"E\":\"人员*/
	private String pubType1;
	/** 发布对象的id集合 */
	private String pubIds1;
	/** 经办人id */
	private String uploadPersonId;	
	/** id经办人 */
	private String uploadPerson;
	/**经办人*/
	private String uploadPersonName;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 按部门发布 */
	private Set<OrganizationUnit> organizationUnits;
	/** 按人员发布 */
	private Set<Employee> employees;
	/** 父分类 */
	private OfficeCategory officeCategory;
	/** 领用、借用、归还单 */
	private OfficeSuppliesRegister officeSuppliesRegister;
	/**办公用品领用明细*/
	private Set<OfficeSuppliesRegisterItem> officeSuppliesRegisterItem = new HashSet<OfficeSuppliesRegisterItem>();
	/** 办公用品借用明细*/
	private Set<OfficeSuppliesBorrow> officeSuppliesBorrow = new HashSet<OfficeSuppliesBorrow>();

	public OfficeSupplies() {
		super();
	}

	public OfficeSupplies(String id) {
		super();
		setId(id);
	}

	public String getPubType() {
		return pubType;
	}

	public void setPubType(String pubType) {
		this.pubType = pubType;
	}

	public String getPubIds() {
		return pubIds;
	}

	public void setPubIds(String pubIds) {
		this.pubIds = pubIds;
	}

	public Set<OrganizationUnit> getOrganizationUnits() {
		return organizationUnits;
	}

	public void setOrganizationUnits(Set<OrganizationUnit> organizationUnits) {
		this.organizationUnits = organizationUnits;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public OfficeCategory getOfficeCategory() {
		return officeCategory;
	}

	public void setOfficeCategory(OfficeCategory officeCategory) {
		this.officeCategory = officeCategory;
	}

	public String getOfficeSuppliesName() {
		return officeSuppliesName;
	}

	public void setOfficeSuppliesName(String officeSuppliesName) {
		this.officeSuppliesName = officeSuppliesName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPrickle() {
		return prickle;
	}

	public void setPrickle(String prickle) {
		this.prickle = prickle;
	}

	public String getOfficeSuppliesCode() {
		return officeSuppliesCode;
	}

	public void setOfficeSuppliesCode(String officeSuppliesCode) {
		this.officeSuppliesCode = officeSuppliesCode;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getCurrentInventory() {
		return currentInventory;
	}

	public void setCurrentInventory(Double currentInventory) {
		this.currentInventory = currentInventory;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getLowestVigilance() {
		return lowestVigilance;
	}

	public void setLowestVigilance(String lowestVigilance) {
		this.lowestVigilance = lowestVigilance;
	}

	public String getMaximumVigilance() {
		return maximumVigilance;
	}

	public void setMaximumVigilance(String maximumVigilance) {
		this.maximumVigilance = maximumVigilance;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getRegistrant() {
		return registrant;
	}

	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}

	public String getRegistrationAuthorities() {
		return registrationAuthorities;
	}

	public void setRegistrationAuthorities(String registrationAuthorities) {
		this.registrationAuthorities = registrationAuthorities;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getPubType1() {
		return pubType1;
	}

	public void setPubType1(String pubType1) {
		this.pubType1 = pubType1;
	}

	public String getPubIds1() {
		return pubIds1;
	}

	public void setPubIds1(String pubIds1) {
		this.pubIds1 = pubIds1;
	}

	public OfficeSuppliesRegister getOfficeSuppliesRegister() {
		return officeSuppliesRegister;
	}

	public void setOfficeSuppliesRegister(OfficeSuppliesRegister officeSuppliesRegister) {
		this.officeSuppliesRegister = officeSuppliesRegister;
	}

	public String getUploadPersonId() {
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId) {
		this.uploadPersonId = uploadPersonId;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public Set<OfficeSuppliesRegisterItem> getOfficeSuppliesRegisterItem() {
		return officeSuppliesRegisterItem;
	}

	public void setOfficeSuppliesRegisterItem(Set<OfficeSuppliesRegisterItem> officeSuppliesRegisterItem) {
		this.officeSuppliesRegisterItem = officeSuppliesRegisterItem;
	}

	public Set<OfficeSuppliesBorrow> getOfficeSuppliesBorrow() {
		return officeSuppliesBorrow;
	}

	public void setOfficeSuppliesBorrow(Set<OfficeSuppliesBorrow> officeSuppliesBorrow) {
		this.officeSuppliesBorrow = officeSuppliesBorrow;
	}

}
