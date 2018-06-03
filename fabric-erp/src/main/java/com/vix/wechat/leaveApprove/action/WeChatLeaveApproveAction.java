package com.vix.wechat.leaveApprove.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.personaloffice.entity.TripRecord;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.wechat.base.action.WechatBaseAction;
import com.vix.wechat.base.entity.WxpQyPicture;
import com.vix.wechat.base.entity.WxpQyWeixinSite;

@Controller
@Scope("prototype")
public class WeChatLeaveApproveAction extends WechatBaseAction {

	private static final long serialVersionUID = 1L;
	/**
	 * 请假 status 0 待审批, status 1 审批通过
	 */
	private TripRecord tripRecord;
	private List<TripRecord> tripRecordList;
	private List<Employee> employeeList;
	private Set<WxpQyPicture> wxpQyPictureList;
	private String id;
	private String ids;
	private Integer employeeNum = 0;
	private Employee employee;
	private String userId;
	private EvaluationReview evaluationReview;
	private List<EvaluationReview> evaluationReviewList;
	private Integer evaluationReviewNum = 0;
	private String corpid;// 企业号ID

	@Override
	public String goList() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			params.put("status," + SearchCondition.EQUAL, "1");
			tripRecordList = wechatBaseService.findAllDataByConditions(TripRecord.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goList";
	}

	/**
	 * 我提交的请假单列表
	 * 
	 * @return
	 */
	public String goExamineList() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				// 检查是否为已从微信验证后跳转回来
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/weChatLeaveApproveAction!goExamineList.action?corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			params.put("status," + SearchCondition.EQUAL, "0");
			params.put("employee.id," + SearchCondition.EQUAL, employee.getId());
			tripRecordList = wechatBaseService.findAllDataByConditions(TripRecord.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goExamineList";
	}

	/**
	 * 我要审批的请假单列表
	 * 
	 * @return
	 */
	public String goMyExamineList() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				// 检查是否为已从微信验证后跳转回来
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/weChatLeaveApproveAction!goMyExamineList.action?corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}
			Map<String, Object> params = getParams();
			params.put("status," + SearchCondition.EQUAL, "0");
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			List<TripRecord> tList = wechatBaseService.findAllDataByConditions(TripRecord.class, params);

			Map<String, TripRecord> empMap = new HashMap<String, TripRecord>();
			for (TripRecord tripRecord : tList) {
				Set<Employee> subEmployees = tripRecord.getSubEmployees();
				for (Employee emp : subEmployees) {
					empMap.put(emp.getUserId() + tripRecord.getId(), tripRecord);
				}
			}
			tripRecordList = new ArrayList<TripRecord>();
			for (TripRecord tripRecord : tList) {
				if (empMap.containsKey(employee.getUserId() + tripRecord.getId())) {
					tripRecordList.add(empMap.get(employee.getUserId() + tripRecord.getId()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMyExamineList";
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				// 检查是否为已从微信验证后跳转回来
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/weChatLeaveApproveAction!goSaveOrUpdate.action?id=" + id + "&corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}
			// 获取审批人
			if (StringUtils.isNotEmpty(ids)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, ids);
				employeeList = wechatBaseService.findAllDataByConditions(Employee.class, p);
			}
			if (StringUtils.isNotEmpty(id)) {
				tripRecord = wechatBaseService.findEntityByAttributeNoTenantId(TripRecord.class, "id", id);
				if (tripRecord.getSubEmployees() != null && tripRecord.getSubEmployees().size() > 0) {
					employeeList = new ArrayList<Employee>();
					employeeList.addAll(tripRecord.getSubEmployees());
				}
				if (tripRecord != null && tripRecord.getSubWxpQyPictures() != null && tripRecord.getSubWxpQyPictures().size() > 0) {
					wxpQyPictureList = tripRecord.getSubWxpQyPictures();
				}
			} else {
				tripRecord = new TripRecord();
				if (employee != null) {
					tripRecord.setEmployee(employee);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	/**
	 * 我提交的
	 * 
	 * @return
	 */
	public String goExamine() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				tripRecord = wechatBaseService.findEntityByAttributeNoTenantId(TripRecord.class, "id", id);
				if (tripRecord != null) {
					if (tripRecord.getSubEmployees() != null && tripRecord.getSubEmployees().size() > 0) {
						employeeList = new ArrayList<Employee>();
						employeeList.addAll(tripRecord.getSubEmployees());
						employeeNum = employeeList.size();
					}
					if (tripRecord.getSubEvaluationReviews() != null && tripRecord.getSubEvaluationReviews().size() > 0) {
						evaluationReviewList = new ArrayList<EvaluationReview>();
						evaluationReviewList.addAll(tripRecord.getSubEvaluationReviews());
						evaluationReviewNum = evaluationReviewList.size();
						// 倒序
						Collections.sort(evaluationReviewList, new Comparator<EvaluationReview>() {
							@Override
							public int compare(EvaluationReview o1, EvaluationReview o2) {
								return getTime(o2.getEvaluationTime(), o1.getEvaluationTime());
							}
						});
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goExamine";
	}

	/**
	 * 需要我审批的
	 * 
	 * @return
	 */
	public String goMyExamine() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				tripRecord = wechatBaseService.findEntityByAttributeNoTenantId(TripRecord.class, "id", id);
				if (tripRecord != null) {
					if (tripRecord.getSubEmployees() != null && tripRecord.getSubEmployees().size() > 0) {
						employeeList = new ArrayList<Employee>();
						employeeList.addAll(tripRecord.getSubEmployees());
						employeeNum = employeeList.size();
					}
					if (tripRecord.getSubEvaluationReviews() != null && tripRecord.getSubEvaluationReviews().size() > 0) {
						evaluationReviewList = new ArrayList<EvaluationReview>();
						evaluationReviewList.addAll(tripRecord.getSubEvaluationReviews());
						evaluationReviewNum = evaluationReviewList.size();
						// 倒序
						Collections.sort(evaluationReviewList, new Comparator<EvaluationReview>() {
							@Override
							public int compare(EvaluationReview o1, EvaluationReview o2) {
								return getTime(o2.getEvaluationTime(), o1.getEvaluationTime());
							}
						});
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMyExamine";
	}

	public void saveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
				if (StringUtils.isNotEmpty(userId)) {
					employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				}
			}
			if (site != null) {
				tripRecord.setTenantId(site.getTenantId());
				tripRecord.setCompanyInnerCode(site.getCompanyInnerCode());
			}
			tripRecord.setStatus("0");
			if (employee != null) {
				tripRecord.setEmployee(employee);
			}
			tripRecord.setCreateTime(new Date());
			// 获取审批人
			if (StringUtils.isNotEmpty(ids)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, ids);
				p.put("empType," + SearchCondition.EQUAL, "WE");
				employeeList = wechatBaseService.findAllDataByConditions(Employee.class, p);
				Set<Employee> subEmployees = new HashSet<Employee>();
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee employee : employeeList) {
						subEmployees.add(employee);
					}
				}
				tripRecord.setSubEmployees(subEmployees);
			}

			tripRecord = wechatBaseService.mergeOriginal(tripRecord);

			// 保存图片
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				WxpQyPicture wxpQyPicture = new WxpQyPicture();
				wxpQyPicture.setPictureUrl("/img/wechat/" + savePathAndName[1].toString());
				wxpQyPicture.setTripRecord(tripRecord);
				wxpQyPicture = wechatBaseService.mergeOriginal(wxpQyPicture);
			}
			renderText(tripRecord.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 选择审批人
	 * 
	 * @return
	 */
	public String goChooseApprovalPerson() {
		try {
			if (getSession().getAttribute("corpid") != null && !"".equals(getSession().getAttribute("corpid"))) {
				corpid = String.valueOf(getSession().getAttribute("corpid"));
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				tripRecord = wechatBaseService.findEntityByAttributeNoTenantId(TripRecord.class, "id", id);
			}
			Map<String, Object> params = getParams();
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			params.put("empType," + SearchCondition.EQUAL, "S");
			employeeList = wechatBaseService.findAllDataByConditions(Employee.class, params);
			if (employeeList != null && employeeList.size() > 0) {
				employeeNum = employeeList.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseApprovalPerson";
	}

	public void goFinishTripRecord() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				tripRecord = wechatBaseService.findEntityByAttributeNoTenantId(TripRecord.class, "id", id);
				tripRecord.setStatus("1");
				tripRecord = wechatBaseService.mergeOriginal(tripRecord);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 保存回复
	public void saveOrUpdateEvaluationReview() {
		try {

			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
				if (StringUtils.isNotEmpty(userId)) {
					employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				}
			}

			if (tripRecord != null && tripRecord.getId() != null) {
				evaluationReview.setTripRecord(tripRecord);
			}
			if (employee != null) {
				evaluationReview.setEmployee(employee);
				evaluationReview.setTenantId(employee.getTenantId());
				evaluationReview.setEvaluationTime(new Date());
				evaluationReview.setCompanyCode(employee.getCompanyCode());
				evaluationReview.setCompanyInnerCode(employee.getCompanyInnerCode());
			}
			evaluationReview.setCreateTime(new Date());
			evaluationReview = wechatBaseService.mergeOriginal(evaluationReview);
			renderText(tripRecord.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the employee
	 */
	@Override
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee
	 *            the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the evaluationReviewList
	 */
	public List<EvaluationReview> getEvaluationReviewList() {
		return evaluationReviewList;
	}

	/**
	 * @param evaluationReviewList
	 *            the evaluationReviewList to set
	 */
	public void setEvaluationReviewList(List<EvaluationReview> evaluationReviewList) {
		this.evaluationReviewList = evaluationReviewList;
	}

	/**
	 * @return the evaluationReviewNum
	 */
	public Integer getEvaluationReviewNum() {
		return evaluationReviewNum;
	}

	/**
	 * @param evaluationReviewNum
	 *            the evaluationReviewNum to set
	 */
	public void setEvaluationReviewNum(Integer evaluationReviewNum) {
		this.evaluationReviewNum = evaluationReviewNum;
	}

	/**
	 * @return the evaluationReview
	 */
	public EvaluationReview getEvaluationReview() {
		return evaluationReview;
	}

	/**
	 * @param evaluationReview
	 *            the evaluationReview to set
	 */
	public void setEvaluationReview(EvaluationReview evaluationReview) {
		this.evaluationReview = evaluationReview;
	}

	/**
	 * @return the tripRecord
	 */
	public TripRecord getTripRecord() {
		return tripRecord;
	}

	/**
	 * @param tripRecord
	 *            the tripRecord to set
	 */
	public void setTripRecord(TripRecord tripRecord) {
		this.tripRecord = tripRecord;
	}

	/**
	 * @return the tripRecordList
	 */
	public List<TripRecord> getTripRecordList() {
		return tripRecordList;
	}

	/**
	 * @param tripRecordList
	 *            the tripRecordList to set
	 */
	public void setTripRecordList(List<TripRecord> tripRecordList) {
		this.tripRecordList = tripRecordList;
	}

	/**
	 * @return the wxpQyPictureList
	 */
	public Set<WxpQyPicture> getWxpQyPictureList() {
		return wxpQyPictureList;
	}

	/**
	 * @param wxpQyPictureList
	 *            the wxpQyPictureList to set
	 */
	public void setWxpQyPictureList(Set<WxpQyPicture> wxpQyPictureList) {
		this.wxpQyPictureList = wxpQyPictureList;
	}

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * @return the wxpQyContactsNum
	 */
	public Integer getWxpQyContactsNum() {
		return employeeNum;
	}

	/**
	 * @param wxpQyContactsNum
	 *            the wxpQyContactsNum to set
	 */
	public void setWxpQyContactsNum(Integer wxpQyContactsNum) {
		this.employeeNum = wxpQyContactsNum;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the employeeList
	 */
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	/**
	 * @param employeeList
	 *            the employeeList to set
	 */
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	/**
	 * @return the employeeNum
	 */
	public Integer getEmployeeNum() {
		return employeeNum;
	}

	/**
	 * @param employeeNum
	 *            the employeeNum to set
	 */
	public void setEmployeeNum(Integer employeeNum) {
		this.employeeNum = employeeNum;
	}

}
