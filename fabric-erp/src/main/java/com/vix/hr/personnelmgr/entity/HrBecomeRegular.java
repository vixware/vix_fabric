package com.vix.hr.personnelmgr.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.job.entity.HrAttachments;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 人事事务
 * @author 李大鹏
 * @date 2013-09-22
 */
public class HrBecomeRegular extends BaseEntity {

	private static final long serialVersionUID = -683911597742172154L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 主题 */
	private String theme;
	/** 操作类型 */
	private String types;
	/** 当事人 */
	private String litigant;
	/** 经办人 */
	private String gestores;
	/** 事务状态 */ //通过:1 待商议:0 未通过:-1
	private Integer transactionState;
	/** 事务时间 */
	private Date transactionTime;
	/** 审批人 */
	private String approval;
	/** 审批人评价 */
	private String approvalEvaluate;
	/** 姓名 */
	private String employeeName;
	/** 员工租 */
	private String staff;
	/** 员工子组 */
	private String subStaff;
	/** 工号 */
	private String jobNumber;
	/** 身份证号 */
	private String iDNumber;
	/** 试用期所属部门 */
	private String probationDepartment;
	/** 试用期职位 */
	private String probationPost;
	/** 转正后所属部门 */
	private String afterThePromotionDepartment;
	/** 转正后职位 */
	private String afterThePromotionPost;
	/** 转正原因 */
	private String becomeFullMembercause;
	/** 转正状态 */ // 是:1  否:0
	private Integer becomeFullMemberState;
	/** 借调状态*/ // 已借调:1  未借调:0
	private Integer loanState;
	/** 试用期表现 */
	private String trialPeriod;
	/** 批准文号 */
	private String licenseNumber;
	/** 批准日期 */
	private Date licenseDate;
	/** 借调期限 */
	private String loanPeriod;
	/** 借调日期 */
	private Date loadDate;
	/** 所在岗位/部门 */
	private String wherePosition;
	/** 请假类别 */ // 病假:1  事假:2
	private Integer leaveType;
	/** 请假期间名称 */
	private String leaveName;
	/** 请假开始日期 */
	private Date leaveStartDate;
	/** 请假结束日期 */
	private Date leaveEndDate;
	/** 备注 */
	private String remarks;
	/** 入司日期 */
	private Date infoOrgDate;
	/** 任职日期 */
	private Date employmentDate;
	/** 年龄 */
	private String age;
	/** 入职日期 */
	private Date entryDate;
	/** 甲方名称 */
	private String jiaName;
	/** 甲方地址 */
	private String jiaAddress;
	/** 邮编 */
	private String zipCode;
	/** 法定代表人 */
	private String legalRepresentative;
	/** 电话 */
	private String telephone;
	/** 传真 */
	private String fax;
	/** 乙方名称 */
	private String yiName;
	/** 乙方住址 */
	private String yiAddress;
	/** 返聘协议 */
	private String bridgeProtocol;
	/** 乙方家属 */
	private String yiFamily;
	/** 乙方家属意见 */
	private String yiFamilySuggestion;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;

	/** 附件 */
	private Set<HrAttachments> attachments = new HashSet<HrAttachments>();
	private Employee employee;
	/** 审批 */
	private Set<HrApprovalOpinion> hrapprovalOpinions = new HashSet<HrApprovalOpinion>();

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Set<HrAttachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<HrAttachments> attachments) {
		this.attachments = attachments;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getLitigant() {
		return litigant;
	}

	public void setLitigant(String litigant) {
		this.litigant = litigant;
	}

	public String getGestores() {
		return gestores;
	}

	public void setGestores(String gestores) {
		this.gestores = gestores;
	}

	public Integer getTransactionState() {
		return transactionState;
	}

	public void setTransactionState(Integer transactionState) {
		this.transactionState = transactionState;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getSubStaff() {
		return subStaff;
	}

	public void setSubStaff(String subStaff) {
		this.subStaff = subStaff;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getiDNumber() {
		return iDNumber;
	}

	public void setiDNumber(String iDNumber) {
		this.iDNumber = iDNumber;
	}

	public String getProbationDepartment() {
		return probationDepartment;
	}

	public void setProbationDepartment(String probationDepartment) {
		this.probationDepartment = probationDepartment;
	}

	public String getProbationPost() {
		return probationPost;
	}

	public void setProbationPost(String probationPost) {
		this.probationPost = probationPost;
	}

	public String getAfterThePromotionDepartment() {
		return afterThePromotionDepartment;
	}

	public void setAfterThePromotionDepartment(String afterThePromotionDepartment) {
		this.afterThePromotionDepartment = afterThePromotionDepartment;
	}

	public String getAfterThePromotionPost() {
		return afterThePromotionPost;
	}

	public void setAfterThePromotionPost(String afterThePromotionPost) {
		this.afterThePromotionPost = afterThePromotionPost;
	}

	public String getBecomeFullMembercause() {
		return becomeFullMembercause;
	}

	public void setBecomeFullMembercause(String becomeFullMembercause) {
		this.becomeFullMembercause = becomeFullMembercause;
	}

	public Integer getBecomeFullMemberState() {
		return becomeFullMemberState;
	}

	public void setBecomeFullMemberState(Integer becomeFullMemberState) {
		this.becomeFullMemberState = becomeFullMemberState;
	}

	public String getTrialPeriod() {
		return trialPeriod;
	}

	public void setTrialPeriod(String trialPeriod) {
		this.trialPeriod = trialPeriod;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getApprovalEvaluate() {
		return approvalEvaluate;
	}

	public void setApprovalEvaluate(String approvalEvaluate) {
		this.approvalEvaluate = approvalEvaluate;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public Date getLicenseDate() {
		return licenseDate;
	}
	
	public String getLicenseTimeStr() {
		if (null != licenseDate) {
			return DateUtil.format(licenseDate);
		}
		return "";
	}
	
	public String getEmploymentTimeStr() {
		if (null != employmentDate) {
			return DateUtil.format(employmentDate);
		}
		return "";
	}
	
	public String getInfoOrgTimeStr() {
		if (null != infoOrgDate) {
			return DateUtil.format(infoOrgDate);
		}
		return "";
	}
	
	public String getLeaveEndTimeStr() {
		if (null != leaveEndDate) {
			return DateUtil.format(leaveEndDate);
		}
		return "";
	}
	
	public String getLeaveStartTimeStr() {
		if (null != leaveStartDate) {
			return DateUtil.format(leaveStartDate);
		}
		return "";
	}
	
	public void setLicenseDate(Date licenseDate) {
		this.licenseDate = licenseDate;
	}

	public String getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(String loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public Date getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}

	public String getWherePosition() {
		return wherePosition;
	}

	public void setWherePosition(String wherePosition) {
		this.wherePosition = wherePosition;
	}

	public Integer getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(Integer leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public Date getLeaveStartDate() {
		return leaveStartDate;
	}

	public void setLeaveStartDate(Date leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	public Date getLeaveEndDate() {
		return leaveEndDate;
	}

	public void setLeaveEndDate(Date leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getInfoOrgDate() {
		return infoOrgDate;
	}

	public void setInfoOrgDate(Date infoOrgDate) {
		this.infoOrgDate = infoOrgDate;
	}

	public Date getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getJiaName() {
		return jiaName;
	}

	public void setJiaName(String jiaName) {
		this.jiaName = jiaName;
	}

	public String getJiaAddress() {
		return jiaAddress;
	}

	public void setJiaAddress(String jiaAddress) {
		this.jiaAddress = jiaAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getYiName() {
		return yiName;
	}

	public void setYiName(String yiName) {
		this.yiName = yiName;
	}

	public String getYiAddress() {
		return yiAddress;
	}

	public void setYiAddress(String yiAddress) {
		this.yiAddress = yiAddress;
	}

	public String getBridgeProtocol() {
		return bridgeProtocol;
	}

	public void setBridgeProtocol(String bridgeProtocol) {
		this.bridgeProtocol = bridgeProtocol;
	}

	public String getYiFamily() {
		return yiFamily;
	}

	public void setYiFamily(String yiFamily) {
		this.yiFamily = yiFamily;
	}

	public String getYiFamilySuggestion() {
		return yiFamilySuggestion;
	}

	public void setYiFamilySuggestion(String yiFamilySuggestion) {
		this.yiFamilySuggestion = yiFamilySuggestion;
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

	public Integer getLoanState() {
		return loanState;
	}

	public void setLoanState(Integer loanState) {
		this.loanState = loanState;
	}

	public Set<HrApprovalOpinion> getHrapprovalOpinions() {
		return hrapprovalOpinions;
	}

	public void setHrapprovalOpinions(Set<HrApprovalOpinion> hrapprovalOpinions) {
		this.hrapprovalOpinions = hrapprovalOpinions;
	}
    
}
