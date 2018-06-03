/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.oa.task.taskDefinition.entity.ExecutionFeedback;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.oa.task.taskDefinition.entity.VixTask;
import com.vix.wechat.base.entity.WxpQyPicture;

/**
 * 项目信息表
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-22
 */
public class Project extends BaseEntity {
	/**
	 * isDeleted 0 正常,1 已删除 isTemp 0 草稿,1 正式数据 status: 1 未开始,2 进行中,3 已完成,4 已逾期
	 */
	private static final long serialVersionUID = 1L;
	/** 项目名称 */
	private String projectName;
	/** 项目概述 */
	private String note;
	/** 项目拥有者 */
	/* private User owner; */
	/** 项目预计开始时间 */
	private Date estimateStartTime;
	/** 项目预计完成时间 */
	private Date estimateEndTime;
	/** 项目编号 */
	private String projectCode;
	/** 客户编码 */
	private String customerCode;
	/** 客户 */
	private String customerAccount;
	/** 物料编码 */
	private String itemCode;
	/** 项目阶段 */
	private String phase;
	/** 项目状态 */
	private String status;
	/** 部门 */
	private String department;
	/** 项目概述 */
	private String summary;
	/** 项目负责人 */
	private String charger;
	/** 项目审核人 */
	private String checker;
	/** 审核日期 */
	private Date checkTime;
	/**
	 * 是否查看 N :新数据,O:已查看数据
	 */
	public String isNew;
	/**
	 * 项目进度
	 */
	private Integer projectSchedule;
	/**
	 * 任务
	 */
	private Set<VixTask> subTaskDefinition;
	/**
	 * 沟通
	 */
	private Set<EvaluationReview> subEvaluationReviews = new HashSet<EvaluationReview>();
	/**
	 * 任务数量
	 */
	private Integer taskNum;
	/**
	 * 已完成任务数量
	 */
	private Integer finishTaskNum;
	/**
	 * 未开始任务数量
	 */
	private Integer nostartTaskNum;
	/**
	 * 进行中任务数量
	 */
	private Integer progressTaskNum;
	/**
	 * 已超时任务数量
	 */
	private Integer timeoutTaskNum;
	/**
	 * 人员
	 */
	private Set<Employee> subEmployees;

	/** 附件 */
	private Set<Uploader> subUploaders = new HashSet<Uploader>();
	/**
	 * 图片
	 */
	private Set<WxpQyPicture> subWxpQyPictures = new HashSet<WxpQyPicture>();

	private Set<ExecutionFeedback> subExecutionFeedbacks = new HashSet<ExecutionFeedback>();
	/**
	 * 照片路径
	 */
	private String pictureurl;

	private String empliststr;

	public String getEstimateStartTimeStr() {
		if (null != estimateStartTime) {
			return DateUtil.format(estimateStartTime);
		}
		return "";
	}

	public String getEstimateStartTimeTimeStr() {
		if (null != estimateStartTime) {
			return DateUtil.formatTime(estimateStartTime);
		}
		return "";
	}

	public String getEstimateEndTimeStr() {
		if (null != estimateEndTime) {
			return DateUtil.format(estimateEndTime);
		}
		return "";
	}

	public String getEstimateEndTimeTimeStr() {
		if (null != estimateEndTime) {
			return DateUtil.formatTime(estimateEndTime);
		}
		return "";
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getEmpliststr() {
		if (subEmployees != null) {
			StringBuffer sb = new StringBuffer();
			Set<Employee> employees = subEmployees;
			for (Employee employee : employees) {
				if (employee != null) {
					sb.append("<a href='javascript:void(0)'><img src='" + employee.getHeadImgUrl() + "'>" + employee.getName() + "</a>");
				}
			}
			return sb.toString();
		} else {
			return empliststr;
		}
	}

	public String getEmployeeNames() {
		if (subEmployees != null) {
			StringBuffer sb = new StringBuffer();
			Set<Employee> employees = subEmployees;
			for (Employee employee : employees) {
				if (employee != null) {
					sb.append(employee.getName() + ",");
				}
			}
			return sb.substring(0, sb.length() - 1);
		} else {
			return "";
		}
	}

	public void setEmpliststr(String empliststr) {
		this.empliststr = empliststr;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the pictureurl
	 */
	public String getPictureurl() {
		return pictureurl;
	}

	/**
	 * @param pictureurl
	 *            the pictureurl to set
	 */
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}

	/**
	 * @return the subWxpQyPictures
	 */
	public Set<WxpQyPicture> getSubWxpQyPictures() {
		return subWxpQyPictures;
	}

	/**
	 * @param subWxpQyPictures
	 *            the subWxpQyPictures to set
	 */
	public void setSubWxpQyPictures(Set<WxpQyPicture> subWxpQyPictures) {
		this.subWxpQyPictures = subWxpQyPictures;
	}

	/**
	 * @return the subUploaders
	 */
	public Set<Uploader> getSubUploaders() {
		return subUploaders;
	}

	/**
	 * @param subUploaders
	 *            the subUploaders to set
	 */
	public void setSubUploaders(Set<Uploader> subUploaders) {
		this.subUploaders = subUploaders;
	}

	/**
	 * @return the subExecutionFeedbacks
	 */
	public Set<ExecutionFeedback> getSubExecutionFeedbacks() {
		return subExecutionFeedbacks;
	}

	/**
	 * @param subExecutionFeedbacks
	 *            the subExecutionFeedbacks to set
	 */
	public void setSubExecutionFeedbacks(Set<ExecutionFeedback> subExecutionFeedbacks) {
		this.subExecutionFeedbacks = subExecutionFeedbacks;
	}

	/**
	 * @return the projectSchedule
	 */
	public Integer getProjectSchedule() {
		return projectSchedule;

	}

	/**
	 * @param projectSchedule
	 *            the projectSchedule to set
	 */
	public void setProjectSchedule(Integer projectSchedule) {
		this.projectSchedule = projectSchedule;
	}

	/**
	 * @return the taskNum
	 */
	public Integer getTaskNum() {
		return taskNum;
	}

	/**
	 * @param taskNum
	 *            the taskNum to set
	 */
	public void setTaskNum(Integer taskNum) {
		this.taskNum = taskNum;
	}

	public Integer getNostartTaskNum() {
		return nostartTaskNum;
	}

	public void setNostartTaskNum(Integer nostartTaskNum) {
		this.nostartTaskNum = nostartTaskNum;
	}

	public Integer getProgressTaskNum() {
		return progressTaskNum;
	}

	public void setProgressTaskNum(Integer progressTaskNum) {
		this.progressTaskNum = progressTaskNum;
	}

	public Integer getTimeoutTaskNum() {
		return timeoutTaskNum;
	}

	public void setTimeoutTaskNum(Integer timeoutTaskNum) {
		this.timeoutTaskNum = timeoutTaskNum;
	}

	/**
	 * @return the isNew
	 */
	public String getIsNew() {
		return isNew;
	}

	/**
	 * @param isNew
	 *            the isNew to set
	 */
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public Date getEstimateStartTime() {
		return estimateStartTime;
	}

	public void setEstimateStartTime(Date estimateStartTime) {
		this.estimateStartTime = estimateStartTime;
	}

	public Date getEstimateEndTime() {
		return estimateEndTime;
	}

	public void setEstimateEndTime(Date estimateEndTime) {
		this.estimateEndTime = estimateEndTime;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	/**
	 * @return the subEmployees
	 */
	public Set<Employee> getSubEmployees() {
		return subEmployees;
	}

	/**
	 * @param subEmployees
	 *            the subEmployees to set
	 */
	public void setSubEmployees(Set<Employee> subEmployees) {
		this.subEmployees = subEmployees;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getDepartment() {
		return department;
	}

	@Override
	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCharger() {
		return charger;
	}

	public void setCharger(String charger) {
		this.charger = charger;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	/**
	 * @return the subTaskDefinition
	 */
	public Set<VixTask> getSubTaskDefinition() {
		return subTaskDefinition;
	}

	/**
	 * @param subTaskDefinition
	 *            the subTaskDefinition to set
	 */
	public void setSubTaskDefinition(Set<VixTask> subTaskDefinition) {
		this.subTaskDefinition = subTaskDefinition;
	}

	public Integer getFinishTaskNum() {
		return finishTaskNum;
	}

	public void setFinishTaskNum(Integer finishTaskNum) {
		this.finishTaskNum = finishTaskNum;
	}

	/**
	 * @return the subEvaluationReviews
	 */
	public Set<EvaluationReview> getSubEvaluationReviews() {
		return subEvaluationReviews;
	}

	/**
	 * @param subEvaluationReviews
	 *            the subEvaluationReviews to set
	 */
	public void setSubEvaluationReviews(Set<EvaluationReview> subEvaluationReviews) {
		this.subEvaluationReviews = subEvaluationReviews;
	}

}
