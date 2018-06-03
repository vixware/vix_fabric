package com.vix.nvix.oa.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.oa.attendance.entity.LeaveRecord;
import com.vix.oa.personaloffice.entity.TripRecord;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;

/**
 * 
 * @类全名 com.vix.nvix.oa.action.VixntLeaveAction
 *
 * @author zhanghaibing
 *
 * @date 2016年8月8日
 */
@Controller
@Scope("prototype")
public class VixntLeaveAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private TripRecord tripRecord;
	private List<TripRecord> tripRecordList;
	// 页面跳转来源
	private String source;
	private String employeeIds;
	private String employeeNames;
	private List<Employee> employeeList;
	private EvaluationReview evaluationReview;
	private List<EvaluationReview> evaluationReviewsList;
	/**
	 * 回复数量
	 */
	private Integer evaluationReviewNum = 0;

	// 下属的请假单 获取方式不对
	public void goTripRecord() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("name," + SearchCondition.ANYLIKE, title);
			}
			Employee e = getEmployee();
			if (e != null) {
				params.put("employeeId", e.getId());
				pager = vixntBaseService.findTripRecordPager(pager, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 我的请假单
	public void goMyTripRecord() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			Employee e = getEmployee();
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("name," + SearchCondition.ANYLIKE, title);
			}
			if (e != null) {
				params.put("employee.id," + SearchCondition.EQUAL, e.getId());
				pager = vixntBaseService.findPagerByHqlConditions(pager, TripRecord.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				tripRecord = vixntBaseService.findEntityById(TripRecord.class, id);
				if (tripRecord != null && tripRecord.getSubEmployees() != null) {
					employeeIds = "";
					employeeNames = "";
					for (Employee e : tripRecord.getSubEmployees()) {
						if (e != null) {
							employeeIds += "," + e.getId();
							employeeNames += "," + e.getName();
						}
					}
				}
			} else {
				tripRecord = new TripRecord();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改工作计划操作 */
	public void saveOrUpdate() {

		boolean isSave = true;
		try {
			if (tripRecord != null && StringUtils.isNotEmpty(tripRecord.getId())) {
				isSave = false;
			}
			Employee employee = getEmployee();
			if (employee != null) {
				tripRecord.setEmployee(employee);
				tripRecord.setUploadPersonId(employee.getId());
				tripRecord.setUploadPersonName(employee.getName());
				tripRecord.setUploadPerson(employee.getName());
				LeaveRecord leaveRecord = new LeaveRecord();
				leaveRecord.setLeaveType(tripRecord.getVacateType());
				leaveRecord.setEmployee(employee);
				leaveRecord.setStartTime(tripRecord.getVacateStartdate());
				leaveRecord.setEndTime(tripRecord.getVacateendDate());
				initEntityBaseController.initEntityBaseAttribute(leaveRecord);
				leaveRecord = vixntBaseService.merge(leaveRecord);
			}
			// 获取选定成员
			if (StringUtils.isNotEmpty(employeeIds)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, employeeIds);
				employeeList = vixntBaseService.findAllByConditions(Employee.class, p);
				Set<Employee> subEmployees = new HashSet<Employee>();
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee e : employeeList) {
						subEmployees.add(e);
					}
				}
				tripRecord.setSubEmployees(subEmployees);
			}
			initEntityBaseController.initEntityBaseAttribute(tripRecord);
			tripRecord = vixntBaseService.merge(tripRecord);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				TripRecord pb = vixntBaseService.findEntityById(TripRecord.class, id);
				if (null != pb) {
					vixntBaseService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public void deleteEvaluationReviewById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				EvaluationReview pb = vixntBaseService.findEntityById(EvaluationReview.class, id);
				if (null != pb) {
					vixntBaseService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public String goShowTripRecord() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				tripRecord = vixntBaseService.findEntityById(TripRecord.class, id);
				if (tripRecord.getSubEvaluationReviews() != null && tripRecord.getSubEvaluationReviews().size() > 0) {
					evaluationReviewNum = tripRecord.getSubEvaluationReviews().size();
					evaluationReviewsList = new ArrayList<EvaluationReview>();
					evaluationReviewsList.addAll(tripRecord.getSubEvaluationReviews());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowTripRecord";
	}

	public void saveOrUpdateEvaluationReview() {
		try {
			if (tripRecord != null && StringUtils.isNotEmpty(tripRecord.getId())) {
				evaluationReview.setTripRecord(tripRecord);
				Employee employee = getEmployee();
				if (employee != null) {
					evaluationReview.setEmployee(employee);
					evaluationReview.setUploadPersonId(employee.getId());
					evaluationReview.setUploadPersonName(employee.getName());
					evaluationReview.setUploadPerson(employee.getName());
				}
				evaluationReview.setEvaluationTime(new Date());
				initEntityBaseController.initEntityBaseAttribute(evaluationReview);
				evaluationReview = vixntBaseService.merge(evaluationReview);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public TripRecord getTripRecord() {
		return tripRecord;
	}

	public void setTripRecord(TripRecord tripRecord) {
		this.tripRecord = tripRecord;
	}

	public List<TripRecord> getTripRecordList() {
		return tripRecordList;
	}

	public void setTripRecordList(List<TripRecord> tripRecordList) {
		this.tripRecordList = tripRecordList;
	}

	@Override
	public String getSource() {
		return source;
	}

	@Override
	public void setSource(String source) {
		this.source = source;
	}

	public String getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(String employeeIds) {
		this.employeeIds = employeeIds;
	}

	public String getEmployeeNames() {
		return employeeNames;
	}

	public void setEmployeeNames(String employeeNames) {
		this.employeeNames = employeeNames;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public EvaluationReview getEvaluationReview() {
		return evaluationReview;
	}

	public void setEvaluationReview(EvaluationReview evaluationReview) {
		this.evaluationReview = evaluationReview;
	}

	public List<EvaluationReview> getEvaluationReviewsList() {
		return evaluationReviewsList;
	}

	public void setEvaluationReviewsList(List<EvaluationReview> evaluationReviewsList) {
		this.evaluationReviewsList = evaluationReviewsList;
	}

	public Integer getEvaluationReviewNum() {
		return evaluationReviewNum;
	}

	public void setEvaluationReviewNum(Integer evaluationReviewNum) {
		this.evaluationReviewNum = evaluationReviewNum;
	}

}
