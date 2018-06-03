package com.vix.oa.adminMg.requisitionCar.entity;

import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.entity.Role;
import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;
/**
 * 
 * @ClassName: CarUse
 * @Description: 车辆使用管理 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-6-5 下午6:15:47
 */
public class CarUse extends BaseEntity {

	private static final long serialVersionUID = 375069367357967245L;
	
	/** 中文首字母 */
	private String chineseFirstLetter;
	
	/** 主题 */
	private String theme;
	
	/** 车辆牌号  */
	private VehicleRequest vehicleRequest;
	
	/** 用车人*/
	private String carName;
	
	/** 部门审批人*/
	private String approver;
	
	/** 实际结束*/
	private String physicalEnd;
	
	/** 目的地*/
	private String destination;
	
	/** 申请里程*/
	private String mileage;
	
	/** 事由*/
	private String reasons;
	
	/** 备注*/
	private String remarks;
	
	/** 预约状况   0申请车辆、待批车辆 1已准申请 2使用中车辆 3结束使用车辆 4未准申请*/
	public Integer bookingSituation;
	
	 /**
     * 发布人员类型
     * "O\":\"部门\",\"R\":\"角色\",\"E\":\"人员
     */
    private String pubType;
    
    /**按部门发布*/
    private Set<OrganizationUnit> organizationUnits;
    
    /**按角色发布*/
    private Set<Role> roles;
    
    /**按人员发布*/
    private Set<Employee> employees;
    
    /** 发布对象的id集合 */
    private String pubIds;
    
    /** 发布对象的名称集合 */
    private String pubNames;
    
    /** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	
	public VehicleRequest getVehicleRequest() {
		return vehicleRequest;
	}

	public void setVehicleRequest(VehicleRequest vehicleRequest) {
		this.vehicleRequest = vehicleRequest;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getPhysicalEnd() {
		return physicalEnd;
	}

	public void setPhysicalEnd(String physicalEnd) {
		this.physicalEnd = physicalEnd;
	}

	public Integer getBookingSituation() {
		return bookingSituation;
	}

	public void setBookingSituation(Integer bookingSituation) {
		this.bookingSituation = bookingSituation;
	}

	public String getPubType() {
		return pubType;
	}

	public void setPubType(String pubType) {
		this.pubType = pubType;
	}

	public Set<OrganizationUnit> getOrganizationUnits() {
		return organizationUnits;
	}

	public void setOrganizationUnits(Set<OrganizationUnit> organizationUnits) {
		this.organizationUnits = organizationUnits;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public String getPubIds() {
		return pubIds;
	}

	public void setPubIds(String pubIds) {
		this.pubIds = pubIds;
	}

	public String getPubNames() {
		return pubNames;
	}

	public void setPubNames(String pubNames) {
		this.pubNames = pubNames;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
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

}
