package com.vix.wechat.conferenceAssistant.action;

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
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.vix.core.constant.SearchCondition;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.adminMg.conference.entity.ApplicationMg;
import com.vix.oa.adminMg.conference.entity.MeetingRequest;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.wechat.base.action.WechatBaseAction;
import com.vix.wechat.base.entity.WxpQyPicture;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.entity.WxpTrendsAndEmployee;
import com.vix.wechat.base.util.WxQyUtil;

@Controller
@Scope("prototype")
public class ConferenceAssistantAction extends WechatBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String ids;
	private String affiliatedIds;
	private ApplicationMg applicationMg;
	private List<ApplicationMg> applicationMgList;
	private List<MeetingRequest> meetingRequestList;
	/**
	 * 发起人
	 */
	private List<Employee> employeeList;
	/**
	 * 参会人
	 */
	private List<Employee> empList;

	private Set<WxpQyPicture> wxpQyPictureList;
	private Integer employeeNum = 0;
	private Integer affiliatedEmployeeNum = 0;
	private Set<Uploader> uploaderList;
	private Integer docNum = 0;
	private EvaluationReview evaluationReview;
	private Employee employee;
	private String userId;
	private String userIdStr = "";
	private List<EvaluationReview> evaluationReviewList;
	private Integer evaluationReviewNum = 0;
	private Integer noFeedbackNum = 0;
	private String check = "0";
	private String isLeave = "";
	private String issuerIds = "";
	private String affIds = "";
	private String uploaderId;
	private String wxpQyPictureId;
	private String corpid;// 企业号ID

	/**
	 * 我发起的会议
	 */
	public String goMyList() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			Employee e = new Employee();
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/conferenceAssistantAction!goMyList.action?corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				e = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}
			Map<String, Object> params = getParams();
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			params.put("status," + SearchCondition.EQUAL, "0");
			List<ApplicationMg> mList = wechatBaseService.findAllDataByConditions(ApplicationMg.class, params);
			Map<String, ApplicationMg> empMap = new HashMap<String, ApplicationMg>();
			for (ApplicationMg applicationMg : mList) {
				Set<Employee> subEmployees = applicationMg.getIssuerEmployees();
				for (Employee employee : subEmployees) {
					empMap.put(employee.getUserId() + applicationMg.getId(), applicationMg);
				}
			}
			applicationMgList = new ArrayList<ApplicationMg>();
			for (ApplicationMg applicationMg : mList) {
				if (empMap.containsKey(e.getUserId() + applicationMg.getId())) {
					applicationMgList.add(empMap.get(e.getUserId() + applicationMg.getId()));
				}
			}
			// 倒序
			Collections.sort(applicationMgList, new Comparator<ApplicationMg>() {
				@Override
				public int compare(ApplicationMg o1, ApplicationMg o2) {
					return getTime(o2.getCreateTime(), o1.getCreateTime());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMyList";
	}

	/**
	 * 我发起的会议草稿
	 */
	public String goMyDraftList() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			Employee e = new Employee();
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/conferenceAssistantAction!goMyList.action?corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				e = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}
			Map<String, Object> params = getParams();
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			params.put("status," + SearchCondition.EQUAL, "2");
			List<ApplicationMg> mList = wechatBaseService.findAllDataByConditions(ApplicationMg.class, params);
			Map<String, ApplicationMg> empMap = new HashMap<String, ApplicationMg>();
			for (ApplicationMg applicationMg : mList) {
				Set<Employee> subEmployees = applicationMg.getIssuerEmployees();
				for (Employee employee : subEmployees) {
					empMap.put(employee.getUserId() + applicationMg.getId(), applicationMg);
				}
			}
			applicationMgList = new ArrayList<ApplicationMg>();
			for (ApplicationMg applicationMg : mList) {
				if (empMap.containsKey(e.getUserId() + applicationMg.getId())) {
					applicationMgList.add(empMap.get(e.getUserId() + applicationMg.getId()));
				}
			}
			// 倒序
			Collections.sort(applicationMgList, new Comparator<ApplicationMg>() {
				@Override
				public int compare(ApplicationMg o1, ApplicationMg o2) {
					return getTime(o2.getCreateTime(), o1.getCreateTime());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMyDraftList";
	}

	/**
	 * 我参加的会议
	 */
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
			Employee e = new Employee();
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/conferenceAssistantAction!goList.action");
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				e = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}
			Map<String, Object> params = getParams();
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			params.put("status," + SearchCondition.EQUAL, "0");
			List<ApplicationMg> mList = wechatBaseService.findAllDataByConditions(ApplicationMg.class, params);
			Map<String, ApplicationMg> empMap = new HashMap<String, ApplicationMg>();
			for (ApplicationMg applicationMg : mList) {
				Set<Employee> subEmployees = applicationMg.getAffiliatedEmployees();
				for (Employee employee : subEmployees) {
					empMap.put(employee.getUserId() + applicationMg.getId(), applicationMg);
				}
			}
			applicationMgList = new ArrayList<ApplicationMg>();
			for (ApplicationMg applicationMg : mList) {
				if (empMap.containsKey(e.getUserId() + applicationMg.getId())) {
					applicationMgList.add(empMap.get(e.getUserId() + applicationMg.getId()));
				}
			}
			// 倒序
			Collections.sort(applicationMgList, new Comparator<ApplicationMg>() {
				@Override
				public int compare(ApplicationMg o1, ApplicationMg o2) {
					return getTime(o2.getCreateTime(), o1.getCreateTime());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goList";
	}

	/**
	 * 我参加的历史会议
	 * 
	 * @return
	 */
	public String goHistoryMettingList() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			Employee e = new Employee();
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/conferenceAssistantAction!goHistoryMettingList.action?corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				e = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}

			Map<String, Object> params = getParams();
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			params.put("status," + SearchCondition.EQUAL, "1");
			List<ApplicationMg> mList = wechatBaseService.findAllDataByConditions(ApplicationMg.class, params);
			Map<String, ApplicationMg> empMap = new HashMap<String, ApplicationMg>();
			for (ApplicationMg applicationMg : mList) {
				Set<Employee> subEmployees = applicationMg.getAffiliatedEmployees();
				for (Employee employee : subEmployees) {
					empMap.put(employee.getUserId() + applicationMg.getId(), applicationMg);
				}
			}
			applicationMgList = new ArrayList<ApplicationMg>();
			for (ApplicationMg applicationMg : mList) {
				if (empMap.containsKey(e.getUserId() + applicationMg.getId())) {
					applicationMgList.add(empMap.get(e.getUserId() + applicationMg.getId()));
				}
			}
			// 倒序
			Collections.sort(applicationMgList, new Comparator<ApplicationMg>() {
				@Override
				public int compare(ApplicationMg o1, ApplicationMg o2) {
					return getTime(o2.getCreateTime(), o1.getCreateTime());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goHistoryMettingList";
	}

	/**
	 * 我发起的历史会议
	 * 
	 * @return
	 */
	public String goIssuerHistoryMettingList() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			Employee e = new Employee();
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/conferenceAssistantAction!goIssuerHistoryMettingList.action?corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				e = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}

			Map<String, Object> params = getParams();
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			params.put("status," + SearchCondition.EQUAL, "1");
			List<ApplicationMg> mList = wechatBaseService.findAllDataByConditions(ApplicationMg.class, params);
			Map<String, ApplicationMg> empMap = new HashMap<String, ApplicationMg>();
			for (ApplicationMg applicationMg : mList) {
				Set<Employee> subEmployees = applicationMg.getIssuerEmployees();
				for (Employee employee : subEmployees) {
					empMap.put(employee.getUserId() + applicationMg.getId(), applicationMg);
				}
			}
			applicationMgList = new ArrayList<ApplicationMg>();
			for (ApplicationMg applicationMg : mList) {
				if (empMap.containsKey(e.getUserId() + applicationMg.getId())) {
					applicationMgList.add(empMap.get(e.getUserId() + applicationMg.getId()));
				}
			} // 倒序
			Collections.sort(applicationMgList, new Comparator<ApplicationMg>() {
				@Override
				public int compare(ApplicationMg o1, ApplicationMg o2) {
					return getTime(o2.getCreateTime(), o1.getCreateTime());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goIssuerHistoryMettingList";
	}

	/**
	 * 新建会议
	 * 
	 * @return
	 */
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
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/conferenceAssistantAction!goSaveOrUpdate.action?id=" + id + "&corpid=" + corpid);
				}
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			meetingRequestList = wechatBaseService.findAllDataByConditions(MeetingRequest.class, params);

			if (StringUtils.isNotEmpty(id)) {
				applicationMg = wechatBaseService.findEntityById(ApplicationMg.class, id);
				if (applicationMg != null) {
					if (applicationMg.getIssuerEmployees() != null && applicationMg.getIssuerEmployees().size() > 0) {
						employeeList = new ArrayList<Employee>();
						employeeList.addAll(applicationMg.getIssuerEmployees());
						employeeNum = employeeList.size();
						for (Employee employee : applicationMg.getIssuerEmployees()) {
							ids += "," + employee.getId();
						}
					}
					if (applicationMg.getAffiliatedEmployees() != null && applicationMg.getAffiliatedEmployees().size() > 0) {
						empList = new ArrayList<Employee>();
						empList.addAll(applicationMg.getAffiliatedEmployees());
						affiliatedEmployeeNum = empList.size();
						for (Employee employee : applicationMg.getAffiliatedEmployees()) {
							affiliatedIds += "," + employee.getId();
						}
					}
					// 获取图片
					wxpQyPictureList = applicationMg.getSubWxpQyPictures();
					uploaderList = applicationMg.getSubUploaders();
					docNum = uploaderList.size();
				}
			}

			// 获取选定成员
			if (StringUtils.isNotEmpty(ids)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, ids);
				employeeList = wechatBaseService.findAllDataByConditions(Employee.class, p);
			}
			// 获取选定成员
			if (StringUtils.isNotEmpty(affiliatedIds)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, affiliatedIds);
				empList = wechatBaseService.findAllDataByConditions(Employee.class, p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	/**
	 * 草稿
	 * 
	 * @return
	 */
	public String goMyDraft() {
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
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/conferenceAssistantAction!goMyDraft.action?id=" + id + "&corpid=" + corpid);
				}
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			meetingRequestList = wechatBaseService.findAllDataByConditions(MeetingRequest.class, params);

			if (StringUtils.isNotEmpty(id)) {
				applicationMg = wechatBaseService.findEntityById(ApplicationMg.class, id);
				if (applicationMg != null) {
					if (applicationMg.getIssuerEmployees() != null && applicationMg.getIssuerEmployees().size() > 0) {
						employeeList = new ArrayList<Employee>();
						employeeList.addAll(applicationMg.getIssuerEmployees());
						employeeNum = employeeList.size();
						for (Employee employee : applicationMg.getIssuerEmployees()) {
							ids += "," + employee.getId();
						}
					}
					if (applicationMg.getAffiliatedEmployees() != null && applicationMg.getAffiliatedEmployees().size() > 0) {
						empList = new ArrayList<Employee>();
						empList.addAll(applicationMg.getAffiliatedEmployees());
						affiliatedEmployeeNum = empList.size();
						for (Employee employee : applicationMg.getAffiliatedEmployees()) {
							affiliatedIds += "," + employee.getId();
						}
					}
					// 获取图片
					wxpQyPictureList = applicationMg.getSubWxpQyPictures();
					uploaderList = applicationMg.getSubUploaders();
					docNum = uploaderList.size();
				}
			}

			// 获取选定成员
			if (StringUtils.isNotEmpty(ids)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, ids);
				employeeList = wechatBaseService.findAllDataByConditions(Employee.class, p);
			}
			// 获取选定成员
			if (StringUtils.isNotEmpty(affiliatedIds)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, affiliatedIds);
				empList = wechatBaseService.findAllDataByConditions(Employee.class, p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMyDraft";
	}

	/**
	 * 会议纪要
	 * 
	 * @return
	 */
	public String goMeetingSummary() {
		try {
			if (getSession().getAttribute("corpid") != null && !"".equals(getSession().getAttribute("corpid"))) {
				corpid = String.valueOf(getSession().getAttribute("corpid"));
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			meetingRequestList = wechatBaseService.findAllDataByConditions(MeetingRequest.class, params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				applicationMg = wechatBaseService.findEntityById(ApplicationMg.class, id);
			}

			employeeList = new ArrayList<Employee>();
			employeeList.addAll(applicationMg.getIssuerEmployees());
			employeeNum = employeeList.size();

			empList = new ArrayList<Employee>();
			empList.addAll(applicationMg.getAffiliatedEmployees());
			affiliatedEmployeeNum = empList.size();

			// 获取图片
			wxpQyPictureList = applicationMg.getSubWxpQyPictures();
			// 获取附件
			uploaderList = applicationMg.getSubUploaders();
			docNum = uploaderList.size();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMeetingSummary";
	}

	/**
	 * 会议查看
	 * 
	 * @return
	 */
	public String goApplicationMg() {
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
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/conferenceAssistantAction!goApplicationMg.action?id=" + id + "&corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(id)) {
				applicationMg = wechatBaseService.findEntityByAttributeNoTenantId(ApplicationMg.class, "id", id);
				if (applicationMg != null) {
					if (applicationMg.getEmployee() != null && applicationMg.getEmployee().getUserId() != null && applicationMg.getEmployee().getUserId().equals(userId)) {
						check = "1";
					}
					if (applicationMg.getIssuerEmployees() != null && applicationMg.getIssuerEmployees().size() > 0) {
						for (Employee emp : applicationMg.getIssuerEmployees()) {
							if (emp != null) {
								ids += "," + emp.getId();
							}
						}
						employeeList = new ArrayList<Employee>();
						employeeList.addAll(applicationMg.getIssuerEmployees());
						employeeNum = employeeList.size();
					}
					if (applicationMg.getAffiliatedEmployees() != null && applicationMg.getAffiliatedEmployees().size() > 0) {
						for (Employee emp : applicationMg.getAffiliatedEmployees()) {
							if (emp != null) {
								affiliatedIds += "," + emp.getId();
							}
						}
						empList = new ArrayList<Employee>();
						empList.addAll(applicationMg.getAffiliatedEmployees());
						affiliatedEmployeeNum = empList.size();
						if (empList != null && applicationMg != null && applicationMg.getLeaveNum() != null && applicationMg.getArrinliatedNum() != null) {
							if ((empList.size() - applicationMg.getLeaveNum() - applicationMg.getArrinliatedNum()) > 0) {
								noFeedbackNum = empList.size() - applicationMg.getLeaveNum() - applicationMg.getArrinliatedNum();
							}
						}
					}
					if (applicationMg.getSubEvaluationReviews() != null && applicationMg.getSubEvaluationReviews().size() > 0) {
						evaluationReviewList = new ArrayList<EvaluationReview>();
						evaluationReviewList.addAll(applicationMg.getSubEvaluationReviews());
						evaluationReviewNum = evaluationReviewList.size();
						// 倒序
						Collections.sort(evaluationReviewList, new Comparator<EvaluationReview>() {
							@Override
							public int compare(EvaluationReview o1, EvaluationReview o2) {
								return getTime(o2.getEvaluationTime(), o1.getEvaluationTime());
							}
						});
					}
					if (applicationMg.getSubUploaders() != null && applicationMg.getSubUploaders().size() > 0) {
						uploaderList = applicationMg.getSubUploaders();
						docNum = uploaderList.size();
					}
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				if (employee != null && applicationMg != null) {
					WxpTrendsAndEmployee wxpTrendsAndEmployee = wechatBaseService.findEntityByAttributeNoTenantId(WxpTrendsAndEmployee.class, "trendsIdAndEmployeeId", applicationMg.getId() + employee.getId());
					if (wxpTrendsAndEmployee != null) {
						isLeave = wxpTrendsAndEmployee.getStatus();
						System.out.println(isLeave);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goApplicationMg";
	}

	/**
	 * 历史会议查看
	 * 
	 * @return
	 */
	public String goShowHistoryMetting() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				applicationMg = wechatBaseService.findEntityById(ApplicationMg.class, id);
			}

			if (applicationMg.getIssuerEmployees() != null && applicationMg.getIssuerEmployees().size() > 0) {
				employeeList = new ArrayList<Employee>();
				employeeList.addAll(applicationMg.getIssuerEmployees());
				employeeNum = employeeList.size();
			}
			if (applicationMg.getAffiliatedEmployees() != null && applicationMg.getAffiliatedEmployees().size() > 0) {
				empList = new ArrayList<Employee>();
				empList.addAll(applicationMg.getAffiliatedEmployees());
				affiliatedEmployeeNum = empList.size();
			}
			if (applicationMg.getSubEvaluationReviews() != null && applicationMg.getSubEvaluationReviews().size() > 0) {
				evaluationReviewList = new ArrayList<EvaluationReview>();
				evaluationReviewList.addAll(applicationMg.getSubEvaluationReviews());
				evaluationReviewNum = evaluationReviewList.size();
				// 倒序
				Collections.sort(evaluationReviewList, new Comparator<EvaluationReview>() {
					@Override
					public int compare(EvaluationReview o1, EvaluationReview o2) {
						return getTime(o2.getEvaluationTime(), o1.getEvaluationTime());
					}
				});
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowHistoryMetting";
	}

	/**
	 * 保存会议
	 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (getSession().getAttribute("corpid") != null && !"".equals(getSession().getAttribute("corpid"))) {
				corpid = String.valueOf(getSession().getAttribute("corpid"));
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
				if (StringUtils.isNotEmpty(userId)) {
					employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				}
			}
			if (applicationMg.getId() != null && !"".equals(applicationMg.getId())) {
				ApplicationMg a = wechatBaseService.findEntityByAttributeNoTenantId(ApplicationMg.class, "id", applicationMg.getId());
				BeanUtils.copyProperties(a, applicationMg, new String[] { "meetingTheme", "meetingDescription", "meetingStartTime", "meetingEndTime" });
			} else {
				if (site != null) {
					applicationMg.setTenantId(site.getTenantId());
					applicationMg.setCompanyInnerCode(site.getCompanyInnerCode());
				}
				applicationMg.setBookingSituation(0);
				applicationMg.setArrinliatedNum(0);
				applicationMg.setLeaveNum(0);
				if (employee != null) {
					applicationMg.setEmployee(employee);
				}
				applicationMg.setIsTemps(0);
				applicationMg.setCreateTime(new Date());
			}
			String sub = getRequestParameter("sub");
			if (StringUtils.isNotEmpty(sub) && "1".equals(sub)) {
				// 草稿
				applicationMg.setStatus("2");
			} else {
				applicationMg.setStatus("0");
			}
			if (applicationMg.getMeetingRequest() != null && StringUtils.isNotEmpty(applicationMg.getMeetingRequest().getId()) && !"-1".equals(applicationMg.getMeetingRequest().getId())) {
			} else {
				applicationMg.setMeetingRequest(null);
			}
			// 获取发起人
			if (StringUtils.isNotEmpty(ids)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, ids);
				employeeList = wechatBaseService.findAllDataByConditions(Employee.class, p);
				Set<Employee> subEmployees = new HashSet<Employee>();
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee employee : employeeList) {
						subEmployees.add(employee);
					}
				}
				applicationMg.setIssuerEmployees(subEmployees);
			}
			// 获取参会人
			if (StringUtils.isNotEmpty(affiliatedIds)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, affiliatedIds);
				empList = wechatBaseService.findAllDataByConditions(Employee.class, p);
				Set<Employee> subEmployees = new HashSet<Employee>();
				if (empList != null && empList.size() > 0) {
					for (Employee employee : empList) {
						subEmployees.add(employee);
					}
				}
				applicationMg.setAffiliatedEmployees(subEmployees);
			}
			applicationMg = wechatBaseService.mergeOriginal(applicationMg);
			// 保存附件
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				WxpQyPicture wxpQyPicture = new WxpQyPicture();
				wxpQyPicture.setPictureUrl("/img/wechat/" + savePathAndName[1].toString());
				wxpQyPicture.setApplicationMg(applicationMg);
				wxpQyPicture = wechatBaseService.mergeOriginal(wxpQyPicture);
			}

			String[] saveDocPathAndName = saveDocUploadPic();
			if (saveDocPathAndName != null && saveDocPathAndName.length == 3) {
				Uploader uploader = new Uploader();
				uploader.setFileName(saveDocPathAndName[1].toString());
				uploader.setFilePath("/img/wechat/" + saveDocPathAndName[1].toString());
				uploader.setApplicationMg(applicationMg);
				uploader.setFilesize(saveDocPathAndName[2].toString());
				uploader.setCreateTime(new Date());
				uploader = wechatBaseService.mergeOriginal(uploader);
			}
			if (applicationMg.getAffiliatedEmployees() != null && applicationMg.getAffiliatedEmployees().size() > 0) {
				for (Employee employee : applicationMg.getAffiliatedEmployees()) {
					userIdStr += "|" + employee.getUserId();
				}
			}
			if (StringUtils.isNotEmpty(userIdStr)) {
				if (!"1".equals(applicationMg.getIsTemps()) && "0".equals(sub)) {
					// 更新企业号token
					WxQyUtil.messageSendNews("7", userIdStr.substring(1), applicationMg.getMeetingTheme(), site.getQiyeAccessToken(), applicationMg.getMeetingDescription(), redirect_ip + "/wechat/conferenceAssistantAction!goApplicationMg.action?id=" + applicationMg.getId(), "");
					applicationMg.setIsTemps(1);
				}
			}
			renderText(applicationMg.getId() + ";" + ids + ";" + affiliatedIds);
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

	/**
	 * 选择发起人
	 * 
	 * @return
	 */
	public String goChooseIssuer() {
		try {
			if (getSession().getAttribute("corpid") != null && !"".equals(getSession().getAttribute("corpid"))) {
				corpid = String.valueOf(getSession().getAttribute("corpid"));
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			if (StringUtils.isNotEmpty(id)) {
				applicationMg = wechatBaseService.findEntityById(ApplicationMg.class, id);
				if (applicationMg != null) {
					if (applicationMg.getIssuerEmployees() != null && applicationMg.getIssuerEmployees().size() > 0) {
						for (Employee emp : applicationMg.getIssuerEmployees()) {
							if (emp != null) {
								issuerIds += "," + emp.getId();
							}
						}
					} else {
						issuerIds = getRequestParameter("issuerIds");
					}
				}
			}
			Map<String, Object> params = getParams();
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			params.put("empType," + SearchCondition.EQUAL, "S");
			employeeList = wechatBaseService.findAllDataByConditions(Employee.class, params);
			if (employeeList != null && employeeList.size() > 0) {
				employeeNum = employeeList.size();
				// 倒序
				Collections.sort(employeeList, new Comparator<Employee>() {
					@Override
					public int compare(Employee o1, Employee o2) {
						return getTime(o2.getCreateTime(), o1.getCreateTime());
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseIssuer";
	}

	/**
	 * id 选择参会人
	 * 
	 * @return
	 */
	public String goChooseAffiliated() {
		try {
			if (getSession().getAttribute("corpid") != null && !"".equals(getSession().getAttribute("corpid"))) {
				corpid = String.valueOf(getSession().getAttribute("corpid"));
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			if (StringUtils.isNotEmpty(id)) {
				applicationMg = wechatBaseService.findEntityByAttributeNoTenantId(ApplicationMg.class, "id", id);
				if (applicationMg != null) {
					if (applicationMg.getAffiliatedEmployees() != null && applicationMg.getAffiliatedEmployees().size() > 0) {
						for (Employee emp : applicationMg.getAffiliatedEmployees()) {
							if (emp != null) {
								affIds += "," + emp.getId();
							}
						}
					} else {
						affIds = getRequestParameter("affIds");
					}
				}
			}
			Map<String, Object> params = getParams();
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			params.put("empType," + SearchCondition.EQUAL, "S");
			empList = wechatBaseService.findAllDataByConditions(Employee.class, params);
			if (empList != null && empList.size() > 0) {
				affiliatedEmployeeNum = empList.size();
				// 倒序
				Collections.sort(empList, new Comparator<Employee>() {
					@Override
					public int compare(Employee o1, Employee o2) {
						return getTime(o2.getCreateTime(), o1.getCreateTime());
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseAffiliated";
	}

	/**
	 * 确认参加
	 */
	public void goArrinliated() {
		try {
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
				if (StringUtils.isNotEmpty(userId)) {
					employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				}
			}
			if (StringUtils.isNotEmpty(id)) {
				applicationMg = wechatBaseService.findEntityByAttributeNoTenantId(ApplicationMg.class, "id", id);
				WxpTrendsAndEmployee wxpTrendsAndEmployee = null;
				if (applicationMg != null && employee != null && StringUtils.isNotEmpty(applicationMg.getId()) && StringUtils.isNotEmpty(employee.getId())) {
					wxpTrendsAndEmployee = wechatBaseService.findEntityByAttributeNoTenantId(WxpTrendsAndEmployee.class, "trendsIdAndEmployeeId", applicationMg.getId() + employee.getId());
					if (wxpTrendsAndEmployee != null && StringUtils.isNotEmpty(wxpTrendsAndEmployee.getId())) {
						wxpTrendsAndEmployee.setStatus("C");
						if (applicationMg.getArrinliatedNum() != null) {
							applicationMg.setArrinliatedNum(applicationMg.getArrinliatedNum() + 1);
						} else {
							applicationMg.setArrinliatedNum(1);
						}
						if (applicationMg.getLeaveNum() != null && applicationMg.getLeaveNum() > 1) {
							applicationMg.setLeaveNum(applicationMg.getLeaveNum() - 1);
						} else {
							applicationMg.setLeaveNum(0);
						}
						applicationMg = wechatBaseService.mergeOriginal(applicationMg);
					} else {
						wxpTrendsAndEmployee = new WxpTrendsAndEmployee();
						wxpTrendsAndEmployee.setTenantId(applicationMg.getTenantId());
						wxpTrendsAndEmployee.setCompanyInnerCode(applicationMg.getCompanyInnerCode());
						wxpTrendsAndEmployee.setCompanyCode(applicationMg.getCompanyCode());
						wxpTrendsAndEmployee.setCreateTime(new Date());
						wxpTrendsAndEmployee.setTrendsId(applicationMg.getId());
						wxpTrendsAndEmployee.setEmployeeId(employee.getId());
						wxpTrendsAndEmployee.setEmployeeName(employee.getName());
						wxpTrendsAndEmployee.setHeadImgUrl(employee.getHeadImgUrl());
						wxpTrendsAndEmployee.setStatus("C");
						wxpTrendsAndEmployee.setTrendsIdAndEmployeeId(applicationMg.getId() + employee.getId());
						wechatBaseService.mergeOriginal(wxpTrendsAndEmployee);
						if (StringUtils.isNotEmpty(id)) {
							applicationMg = wechatBaseService.findEntityById(ApplicationMg.class, id);
							if (applicationMg.getArrinliatedNum() != null) {
								applicationMg.setArrinliatedNum(applicationMg.getArrinliatedNum() + 1);
							} else {
								applicationMg.setArrinliatedNum(1);
							}
							applicationMg = wechatBaseService.mergeOriginal(applicationMg);
						}
					}
				}
				renderText(id);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 会议签到
	 */
	public void goConferenceAttendance() {
		try {
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
				if (StringUtils.isNotEmpty(userId)) {
					employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				}
			}
			if (StringUtils.isNotEmpty(id)) {
				applicationMg = wechatBaseService.findEntityByAttributeNoTenantId(ApplicationMg.class, "id", id);
				WxpTrendsAndEmployee wxpTrendsAndEmployee = null;
				if (applicationMg != null && employee != null && StringUtils.isNotEmpty(applicationMg.getId()) && StringUtils.isNotEmpty(employee.getId())) {
					wxpTrendsAndEmployee = wechatBaseService.findEntityByAttributeNoTenantId(WxpTrendsAndEmployee.class, "trendsIdAndEmployeeId", applicationMg.getId() + employee.getId());
					if (wxpTrendsAndEmployee != null && StringUtils.isNotEmpty(wxpTrendsAndEmployee.getId())) {
						wxpTrendsAndEmployee.setStatus("H");
						applicationMg = wechatBaseService.mergeOriginal(applicationMg);
					} else {
						wxpTrendsAndEmployee = new WxpTrendsAndEmployee();
						wxpTrendsAndEmployee.setTenantId(applicationMg.getTenantId());
						wxpTrendsAndEmployee.setCompanyInnerCode(applicationMg.getCompanyInnerCode());
						wxpTrendsAndEmployee.setCompanyCode(applicationMg.getCompanyCode());
						wxpTrendsAndEmployee.setCreateTime(new Date());
						wxpTrendsAndEmployee.setTrendsId(applicationMg.getId());
						wxpTrendsAndEmployee.setEmployeeId(employee.getId());
						wxpTrendsAndEmployee.setEmployeeName(employee.getName());
						wxpTrendsAndEmployee.setHeadImgUrl(employee.getHeadImgUrl());
						wxpTrendsAndEmployee.setStatus("H");
						wxpTrendsAndEmployee.setTrendsIdAndEmployeeId(applicationMg.getId() + employee.getId());
						wechatBaseService.mergeOriginal(wxpTrendsAndEmployee);
					}
				}
				renderText(id);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 确认请假
	 */
	public void goLeave() {
		try {
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
				if (StringUtils.isNotEmpty(userId)) {
					employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				}
			}
			if (StringUtils.isNotEmpty(id)) {
				applicationMg = wechatBaseService.findEntityByAttributeNoTenantId(ApplicationMg.class, "id", id);
				WxpTrendsAndEmployee wxpTrendsAndEmployee = null;
				if (applicationMg != null && employee != null && StringUtils.isNotEmpty(applicationMg.getId()) && StringUtils.isNotEmpty(employee.getId())) {
					wxpTrendsAndEmployee = wechatBaseService.findEntityByAttributeNoTenantId(WxpTrendsAndEmployee.class, "trendsIdAndEmployeeId", applicationMg.getId() + employee.getId());
					if (wxpTrendsAndEmployee != null && StringUtils.isNotEmpty(wxpTrendsAndEmployee.getId())) {
						wxpTrendsAndEmployee.setStatus("Q");
						wechatBaseService.mergeOriginal(wxpTrendsAndEmployee);
						applicationMg = wechatBaseService.findEntityById(ApplicationMg.class, id);
						if (applicationMg.getLeaveNum() != null) {
							applicationMg.setLeaveNum(applicationMg.getLeaveNum() + 1);
						} else {
							applicationMg.setLeaveNum(1);
						}
						if (applicationMg.getArrinliatedNum() != null && applicationMg.getArrinliatedNum() > 1) {
							applicationMg.setArrinliatedNum(applicationMg.getArrinliatedNum() - 1);
						} else {
							applicationMg.setArrinliatedNum(0);
						}
						applicationMg = wechatBaseService.mergeOriginal(applicationMg);
					} else {
						wxpTrendsAndEmployee = new WxpTrendsAndEmployee();
						wxpTrendsAndEmployee.setTenantId(applicationMg.getTenantId());
						wxpTrendsAndEmployee.setCompanyInnerCode(applicationMg.getCompanyInnerCode());
						wxpTrendsAndEmployee.setCompanyCode(applicationMg.getCompanyCode());
						wxpTrendsAndEmployee.setCreateTime(new Date());
						wxpTrendsAndEmployee.setTrendsId(applicationMg.getId());
						wxpTrendsAndEmployee.setEmployeeId(employee.getId());
						wxpTrendsAndEmployee.setEmployeeName(employee.getName());
						wxpTrendsAndEmployee.setHeadImgUrl(employee.getHeadImgUrl());
						wxpTrendsAndEmployee.setStatus("Q");
						wxpTrendsAndEmployee.setTrendsIdAndEmployeeId(applicationMg.getId() + employee.getId());
						wechatBaseService.mergeOriginal(wxpTrendsAndEmployee);
						if (StringUtils.isNotEmpty(id)) {
							applicationMg = wechatBaseService.findEntityById(ApplicationMg.class, id);
							if (applicationMg.getLeaveNum() != null) {
								applicationMg.setLeaveNum(applicationMg.getLeaveNum() + 1);
							} else {
								applicationMg.setLeaveNum(1);
							}
							applicationMg = wechatBaseService.mergeOriginal(applicationMg);
						}
					}
				}
				renderText(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取消请假
	 */
	public void goCannelLeave() {
		try {
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
				if (StringUtils.isNotEmpty(userId)) {
					employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				}
			}
			if (StringUtils.isNotEmpty(id)) {
				applicationMg = wechatBaseService.findEntityByAttributeNoTenantId(ApplicationMg.class, "id", id);
				WxpTrendsAndEmployee wxpTrendsAndEmployee = null;
				if (applicationMg != null && employee != null) {
					wxpTrendsAndEmployee = wechatBaseService.findEntityByAttributeNoTenantId(WxpTrendsAndEmployee.class, "trendsIdAndEmployeeId", applicationMg.getId() + employee.getId());
					if (wxpTrendsAndEmployee != null) {
						wxpTrendsAndEmployee.setStatus("");
						wxpTrendsAndEmployee = wechatBaseService.mergeOriginal(wxpTrendsAndEmployee);
					}
					if (applicationMg.getLeaveNum() != null) {
						applicationMg.setLeaveNum(applicationMg.getLeaveNum() - 1);
					}
					applicationMg = wechatBaseService.mergeOriginal(applicationMg);
				}
				renderText(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取消签到
	 */
	public void goCannelConferenceAttendance() {
		try {
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
				if (StringUtils.isNotEmpty(userId)) {
					employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				}
			}
			if (StringUtils.isNotEmpty(id)) {
				applicationMg = wechatBaseService.findEntityByAttributeNoTenantId(ApplicationMg.class, "id", id);
				WxpTrendsAndEmployee wxpTrendsAndEmployee = null;
				if (applicationMg != null && employee != null) {
					wxpTrendsAndEmployee = wechatBaseService.findEntityByAttributeNoTenantId(WxpTrendsAndEmployee.class, "trendsIdAndEmployeeId", applicationMg.getId() + employee.getId());
					if (wxpTrendsAndEmployee != null) {
						wxpTrendsAndEmployee.setStatus("C");
						wxpTrendsAndEmployee = wechatBaseService.mergeOriginal(wxpTrendsAndEmployee);
					}
				}
				renderText(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 结束会议
	 */
	public void goFinished() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				applicationMg = wechatBaseService.findEntityById(ApplicationMg.class, id);
				applicationMg.setBookingSituation(1);
				applicationMg.setStatus("1");
				applicationMg = wechatBaseService.mergeOriginal(applicationMg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 回复
	 */
	public void saveOrUpdateEvaluationReview() {
		try {
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
				if (StringUtils.isNotEmpty(userId)) {
					employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				}
			}
			if (applicationMg != null && applicationMg.getId() != null) {
				evaluationReview.setApplicationMg(applicationMg);
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
			renderText(applicationMg.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 批量处理删除操作 */
	public void deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				for (String idStr : ids.split(",")) {
					if (StringUtils.isNotEmpty(idStr)) {
						ApplicationMg applicationMg = wechatBaseService.findEntityByAttributeNoTenantId(ApplicationMg.class, "id", idStr);
						if (applicationMg != null) {
							applicationMg.setStatus("3");
							wechatBaseService.mergeOriginal(applicationMg);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 开启回调,成员进入应用的事件推送
	 */
	public void getWechatMessage() {
		String sToken = "kyEzXXwgHVJ8r";
		String sCorpID = "wx1a67071803f6077f";
		String sEncodingAESKey = "YiINyiOcBmVmu2r7cHXEZDKd1bHKj2isMuqnwmioKHD";
		try {
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
			// 解析出url上的参数值如下：
			String sVerifyMsgSig = getRequest().getParameter("msg_signature");
			String sVerifyTimeStamp = getRequest().getParameter("timestamp");
			String sVerifyNonce = getRequest().getParameter("nonce");
			String sVerifyEchoStr = getRequest().getParameter("echostr");
			if (StringUtils.isNotEmpty(sVerifyEchoStr)) {
				// 需要返回的明文
				String sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, sVerifyEchoStr);
				System.out.println("verifyurl echostr: " + sEchoStr);
				renderText(sEchoStr);
			}

			String postStrXml = wxcpt.DecryptMsg(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, this.readStreamParameter(getRequest().getInputStream()));
			System.out.println("postStrXml:" + postStrXml);
			if (StringUtils.isNotEmpty(postStrXml)) {
				Document xmlData = DocumentHelper.parseText(postStrXml);
				Element root = xmlData.getRootElement();
				String qiyeCorpId = root.elementText("ToUserName");
				String userId = root.elementText("FromUserName");
				if (StringUtils.isNotEmpty(qiyeCorpId)) {
					System.out.println("企业号CorpID:" + qiyeCorpId);
				}
				if (StringUtils.isNotEmpty(userId)) {
					System.out.println("企业号UserID:" + userId);
				}
			}
		} catch (Exception e) {
			// 验证URL失败，错误原因请查看异常
			// e.printStackTrace();
		}
	}

	public void deleteUploaderById() {
		try {
			String applicationMgId = "";
			Uploader uploader = wechatBaseService.findEntityByAttributeNoTenantId(Uploader.class, "id", uploaderId);
			if (null != uploader) {
				applicationMgId = uploader.getApplicationMg().getId();
				wechatBaseService.deleteByEntity(uploader);
			}
			renderText(applicationMgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除图片
	 */
	public void deletePictureById() {
		try {
			if (StringUtils.isNotEmpty(wxpQyPictureId) && !"undefined".equals(wxpQyPictureId)) {
				for (String idStr : wxpQyPictureId.split(",")) {
					if (StringUtils.isNotEmpty(idStr)) {
						WxpQyPicture wxpQyPicture = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyPicture.class, "id", idStr);
						if (null != wxpQyPicture) {
							wechatBaseService.deleteByEntity(wxpQyPicture);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * @return the uploaderId
	 */
	public String getUploaderId() {
		return uploaderId;
	}

	/**
	 * @param uploaderId
	 *            the uploaderId to set
	 */
	public void setUploaderId(String uploaderId) {
		this.uploaderId = uploaderId;
	}

	/**
	 * @return the wxpQyPictureId
	 */
	public String getWxpQyPictureId() {
		return wxpQyPictureId;
	}

	/**
	 * @param wxpQyPictureId
	 *            the wxpQyPictureId to set
	 */
	public void setWxpQyPictureId(String wxpQyPictureId) {
		this.wxpQyPictureId = wxpQyPictureId;
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
	 * @return the issuerIds
	 */
	public String getIssuerIds() {
		return issuerIds;
	}

	/**
	 * @param issuerIds
	 *            the issuerIds to set
	 */
	public void setIssuerIds(String issuerIds) {
		this.issuerIds = issuerIds;
	}

	/**
	 * @return the affIds
	 */
	public String getAffIds() {
		return affIds;
	}

	/**
	 * @param affIds
	 *            the affIds to set
	 */
	public void setAffIds(String affIds) {
		this.affIds = affIds;
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
	 * @return the affiliatedIds
	 */
	public String getAffiliatedIds() {
		return affiliatedIds;
	}

	/**
	 * @param affiliatedIds
	 *            the affiliatedIds to set
	 */
	public void setAffiliatedIds(String affiliatedIds) {
		this.affiliatedIds = affiliatedIds;
	}

	/**
	 * @return the userIdStr
	 */
	public String getUserIdStr() {
		return userIdStr;
	}

	/**
	 * @param userIdStr
	 *            the userIdStr to set
	 */
	public void setUserIdStr(String userIdStr) {
		this.userIdStr = userIdStr;
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
	 * @return the empList
	 */
	public List<Employee> getEmpList() {
		return empList;
	}

	/**
	 * @param empList
	 *            the empList to set
	 */
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	/**
	 * @return the affiliatedEmployeeNum
	 */
	public Integer getAffiliatedEmployeeNum() {
		return affiliatedEmployeeNum;
	}

	/**
	 * @param affiliatedEmployeeNum
	 *            the affiliatedEmployeeNum to set
	 */
	public void setAffiliatedEmployeeNum(Integer affiliatedEmployeeNum) {
		this.affiliatedEmployeeNum = affiliatedEmployeeNum;
	}

	/**
	 * @return the uploaderList
	 */
	public Set<Uploader> getUploaderList() {
		return uploaderList;
	}

	/**
	 * @param uploaderList
	 *            the uploaderList to set
	 */
	public void setUploaderList(Set<Uploader> uploaderList) {
		this.uploaderList = uploaderList;
	}

	/**
	 * @return the docNum
	 */
	public Integer getDocNum() {
		return docNum;
	}

	/**
	 * @param docNum
	 *            the docNum to set
	 */
	public void setDocNum(Integer docNum) {
		this.docNum = docNum;
	}

	/**
	 * @return the applicationMg
	 */
	public ApplicationMg getApplicationMg() {
		return applicationMg;
	}

	/**
	 * @param applicationMg
	 *            the applicationMg to set
	 */
	public void setApplicationMg(ApplicationMg applicationMg) {
		this.applicationMg = applicationMg;
	}

	/**
	 * @return the applicationMgList
	 */
	public List<ApplicationMg> getApplicationMgList() {
		return applicationMgList;
	}

	/**
	 * @param applicationMgList
	 *            the applicationMgList to set
	 */
	public void setApplicationMgList(List<ApplicationMg> applicationMgList) {
		this.applicationMgList = applicationMgList;
	}

	/**
	 * @return the meetingRequestList
	 */
	public List<MeetingRequest> getMeetingRequestList() {
		return meetingRequestList;
	}

	/**
	 * @param meetingRequestList
	 *            the meetingRequestList to set
	 */
	public void setMeetingRequestList(List<MeetingRequest> meetingRequestList) {
		this.meetingRequestList = meetingRequestList;
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

	/**
	 * @return the noFeedbackNum
	 */
	public Integer getNoFeedbackNum() {
		return noFeedbackNum;
	}

	/**
	 * @param noFeedbackNum
	 *            the noFeedbackNum to set
	 */
	public void setNoFeedbackNum(Integer noFeedbackNum) {
		this.noFeedbackNum = noFeedbackNum;
	}

	/**
	 * @return the check
	 */
	public String getCheck() {
		return check;
	}

	/**
	 * @param check
	 *            the check to set
	 */
	public void setCheck(String check) {
		this.check = check;
	}

	public String getCorpid() {
		return corpid;
	}

	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}

	public String getIsLeave() {
		return isLeave;
	}

	public void setIsLeave(String isLeave) {
		this.isLeave = isLeave;
	}

}