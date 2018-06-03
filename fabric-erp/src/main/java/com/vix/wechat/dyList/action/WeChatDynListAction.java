package com.vix.wechat.dyList.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.vix.core.constant.SearchCondition;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.bulletin.entity.AnnouncementNotification;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.wechat.base.action.WechatBaseAction;
import com.vix.wechat.base.entity.WxpQyPicture;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.entity.WxpTrendsAndEmployee;
import com.vix.wechat.base.util.WxQyUtil;

@Controller
@Scope("prototype")
public class WeChatDynListAction extends WechatBaseAction {

	private static final long serialVersionUID = 1L;
	/**
	 * 公告通知
	 */
	private List<AnnouncementNotification> announcementNotificationList;
	/**
	 * 公告
	 */
	private AnnouncementNotification announcementNotification;
	private String id;
	private List<WxpTrendsAndEmployee> wxpTrendsAndEmployeeList;

	private List<Employee> employeeList;
	private Integer employeeNum;
	private String tenantId;
	private String userIdStr = "";
	private Employee employee;
	private String userId;
	/**
	 * 回复数量
	 */
	private Integer evaluationReviewNum = 0;
	private List<EvaluationReview> evaluationReviewList;
	private EvaluationReview evaluationReview;
	private WxpTrendsAndEmployee wxpTrendsAndEmployee;
	private String corpid;// 企业号ID
	// 公告通知

	public String goAnnouncementNotificationList() {
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
			announcementNotificationList = wechatBaseService.findAllDataByConditions(AnnouncementNotification.class, params);
			// 倒序
			Collections.sort(announcementNotificationList, new Comparator<AnnouncementNotification>() {
				@Override
				public int compare(AnnouncementNotification o1, AnnouncementNotification o2) {
					return getTime(o2.getCreateTime(), o1.getCreateTime());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAnnouncementNotificationList";
	}

	/**
	 * 发布公告
	 * 
	 * @return
	 */
	public String goSaveOrUpdateAnnouncementNotification() {

		try {
			if (StringUtils.isNotEmpty(id)) {
				announcementNotification = wechatBaseService.findEntityById(AnnouncementNotification.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateAnnouncementNotification";
	}

	/**
	 * 显示公告详情
	 * 
	 * @return
	 */
	public String goShowAnnouncementNotification() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			String code = getRequestParameter("code");
			String state = getRequestParameter("state");
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/weChatDynListAction!goShowAnnouncementNotification.action?id=" + id);
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}
			if (StringUtils.isNotEmpty(id)) {
				announcementNotification = wechatBaseService.findEntityByAttributeNoTenantId(AnnouncementNotification.class, "id", id);
				// 阅读次数readTimes
				if (announcementNotification != null) {
					if (announcementNotification.getReadTimes() != null) {
						announcementNotification.setReadTimes(announcementNotification.getReadTimes() + 1);
						announcementNotification = wechatBaseService.mergeOriginal(announcementNotification);
					} else {
						announcementNotification.setReadTimes(1L);
						announcementNotification = wechatBaseService.mergeOriginal(announcementNotification);
					}
				}

				// 获取点赞人
				if (announcementNotification != null && StringUtils.isNotEmpty(announcementNotification.getId()) && employee != null && StringUtils.isNotEmpty(employee.getId())) {
					System.out.println(announcementNotification.getId() + employee.getId());
					wxpTrendsAndEmployee = wechatBaseService.findEntityByAttributeNoTenantId(WxpTrendsAndEmployee.class, "trendsIdAndEmployeeId", announcementNotification.getId() + employee.getId());
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("trendsId," + SearchCondition.EQUAL, announcementNotification.getId());
					params.put("isPointPraise," + SearchCondition.EQUAL, "1");
					wxpTrendsAndEmployeeList = wechatBaseService.findAllDataByConditions(WxpTrendsAndEmployee.class, params);
				}
				if (announcementNotification.getSubEvaluationReviews() != null && announcementNotification.getSubEvaluationReviews().size() > 0) {
					evaluationReviewList = new ArrayList<EvaluationReview>();
					evaluationReviewList.addAll(announcementNotification.getSubEvaluationReviews());
					evaluationReviewNum = announcementNotification.getSubEvaluationReviews().size();
					// 倒序
					Collections.sort(evaluationReviewList, new Comparator<EvaluationReview>() {
						@Override
						public int compare(EvaluationReview o1, EvaluationReview o2) {
							return getTime(o2.getEvaluationTime(), o1.getEvaluationTime());
						}
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowAnnouncementNotification";
	}

	/**
	 * 保存公告
	 */
	public void saveOrUpdateAnnouncementNotification() {
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
			announcementNotification.setTenantId(site.getTenantId());
			announcementNotification.setCompanyInnerCode(site.getCompanyInnerCode());
			announcementNotification.setCreateTime(new Date());
			announcementNotification.setUpdateTime(new Date());
			if (employee != null) {
				announcementNotification.setEmployee(employee);
			}
			announcementNotification = wechatBaseService.mergeOriginal(announcementNotification);

			// 保存图片
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				WxpQyPicture wxpQyPicture = new WxpQyPicture();
				wxpQyPicture.setPictureUrl("/img/wechat/" + savePathAndName[1].toString());
				wxpQyPicture.setAnnouncementNotification(announcementNotification);
				wxpQyPicture = wechatBaseService.mergeOriginal(wxpQyPicture);
				announcementNotification.setFirstPictureUrl(wxpQyPicture.getPictureUrl());
			}

			announcementNotification = wechatBaseService.mergeOriginal(announcementNotification);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("empType," + SearchCondition.EQUAL, "S");
			employeeList = wechatBaseService.findAllDataByConditions(Employee.class, params);

			if (employeeList != null && employeeList.size() > 0) {
				for (Employee employee : employeeList) {
					tenantId = employee.getTenantId();
					userIdStr += "|" + employee.getUserId();
				}
			}

			// 更新企业号token
			saveOrUpdateWxpWeixinSite(site);
			WxQyUtil.messageSendNews("2", userIdStr.substring(1), announcementNotification.getTitle(), site.getQiyeAccessToken(), announcementNotification.getContent(), redirect_ip + "/wechat/weChatDynListAction!goShowAnnouncementNotification.action?id=" + announcementNotification.getId(), "");
			renderText(announcementNotification.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 选择发布对象
	 * 
	 * @return
	 */
	public String goCheckPerson() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				announcementNotification = wechatBaseService.findEntityById(AnnouncementNotification.class, id);
			}
			Map<String, Object> params = getParams();
			params.put("tenantId," + SearchCondition.EQUAL, api_qiye_tenantid);
			employeeList = wechatBaseService.findAllDataByConditions(Employee.class, params);
			if (employeeList != null && employeeList.size() > 0) {
				employeeNum = employeeList.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCheckPerson";
	}

	/**
	 * 点赞
	 */
	public void pointPraise() {
		try {
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
				if (StringUtils.isNotEmpty(userId)) {
					employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				}
			}
			if (StringUtils.isNotEmpty(id)) {
				announcementNotification = wechatBaseService.findEntityByAttributeNoTenantId(AnnouncementNotification.class, "id", id);
				WxpTrendsAndEmployee wxpTrendsAndEmployee = null;
				if (announcementNotification != null && employee != null && StringUtils.isNotEmpty(announcementNotification.getId()) && StringUtils.isNotEmpty(employee.getId())) {
					wxpTrendsAndEmployee = wechatBaseService.findEntityByAttributeNoTenantId(WxpTrendsAndEmployee.class, "trendsIdAndEmployeeId", announcementNotification.getId() + employee.getId());
					if (wxpTrendsAndEmployee != null) {
						if ("0".equals(wxpTrendsAndEmployee.getIsPointPraise())) {
							wxpTrendsAndEmployee.setIsPointPraise("1");
							wxpTrendsAndEmployee = wechatBaseService.mergeOriginal(wxpTrendsAndEmployee);
							Map<String, Object> params = new HashMap<String, Object>();
							params.put("trendsId," + SearchCondition.EQUAL, announcementNotification.getId());
							params.put("isPointPraise," + SearchCondition.EQUAL, "1");
							wxpTrendsAndEmployeeList = wechatBaseService.findAllDataByConditions(WxpTrendsAndEmployee.class, params);
							if (wxpTrendsAndEmployeeList != null && wxpTrendsAndEmployeeList.size() > 0) {
								announcementNotification.setPointPraiseNums(wxpTrendsAndEmployeeList.size());
								wechatBaseService.mergeOriginal(announcementNotification);
							}
						}
					} else {
						wxpTrendsAndEmployee = new WxpTrendsAndEmployee();
						wxpTrendsAndEmployee.setTenantId(announcementNotification.getTenantId());
						wxpTrendsAndEmployee.setCompanyInnerCode(announcementNotification.getCompanyInnerCode());
						wxpTrendsAndEmployee.setCompanyCode(announcementNotification.getCompanyCode());
						wxpTrendsAndEmployee.setCreateTime(new Date());
						wxpTrendsAndEmployee.setTrendsId(announcementNotification.getId());
						wxpTrendsAndEmployee.setEmployeeId(employee.getId());
						wxpTrendsAndEmployee.setEmployeeName(employee.getName());
						wxpTrendsAndEmployee.setHeadImgUrl(employee.getHeadImgUrl());
						wxpTrendsAndEmployee.setIsPointPraise("1");
						wxpTrendsAndEmployee.setTrendsIdAndEmployeeId(announcementNotification.getId() + employee.getId());
						wechatBaseService.mergeOriginal(wxpTrendsAndEmployee);
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("trendsId," + SearchCondition.EQUAL, announcementNotification.getId());
						params.put("isPointPraise," + SearchCondition.EQUAL, "1");
						wxpTrendsAndEmployeeList = wechatBaseService.findAllDataByConditions(WxpTrendsAndEmployee.class, params);
						if (wxpTrendsAndEmployeeList != null && wxpTrendsAndEmployeeList.size() > 0) {
							announcementNotification.setPointPraiseNums(wxpTrendsAndEmployeeList.size());
							wechatBaseService.mergeOriginal(announcementNotification);
						}
					}
				}
				renderText(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cancelPointPraise() {
		try {
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
				if (StringUtils.isNotEmpty(userId)) {
					employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				}
			}
			if (StringUtils.isNotEmpty(id)) {
				announcementNotification = wechatBaseService.findEntityByAttributeNoTenantId(AnnouncementNotification.class, "id", id);
				WxpTrendsAndEmployee wxpTrendsAndEmployee = null;
				if (announcementNotification != null && employee != null && StringUtils.isNotEmpty(announcementNotification.getId()) && StringUtils.isNotEmpty(employee.getId())) {
					wxpTrendsAndEmployee = wechatBaseService.findEntityByAttributeNoTenantId(WxpTrendsAndEmployee.class, "trendsIdAndEmployeeId", announcementNotification.getId() + employee.getId());
					if (wxpTrendsAndEmployee != null) {
						if ("1".equals(wxpTrendsAndEmployee.getIsPointPraise())) {
							wxpTrendsAndEmployee.setIsPointPraise("0");
							wxpTrendsAndEmployee = wechatBaseService.mergeOriginal(wxpTrendsAndEmployee);

							Map<String, Object> params = new HashMap<String, Object>();
							params.put("trendsId," + SearchCondition.EQUAL, announcementNotification.getId());
							params.put("isPointPraise," + SearchCondition.EQUAL, "1");
							wxpTrendsAndEmployeeList = wechatBaseService.findAllDataByConditions(WxpTrendsAndEmployee.class, params);
							if (wxpTrendsAndEmployeeList != null && wxpTrendsAndEmployeeList.size() > 0) {
								announcementNotification.setPointPraiseNums(wxpTrendsAndEmployeeList.size());
								wechatBaseService.mergeOriginal(announcementNotification);
							}
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

			if (announcementNotification != null && announcementNotification.getId() != null) {
				evaluationReview.setAnnouncementNotification(announcementNotification);
			}
			if (employee != null) {
				evaluationReview.setEmployee(employee);
				evaluationReview.setTenantId(employee.getTenantId());
				evaluationReview.setCompanyCode(employee.getCompanyCode());
				evaluationReview.setCompanyInnerCode(employee.getCompanyInnerCode());
			}
			evaluationReview.setEvaluationTime(new Date());
			evaluationReview.setCreateTime(new Date());
			evaluationReview = wechatBaseService.mergeOriginal(evaluationReview);
			if (announcementNotification != null && announcementNotification.getId() != null) {
				announcementNotification = wechatBaseService.findEntityByAttributeNoTenantId(AnnouncementNotification.class, "id", announcementNotification.getId());
				if (announcementNotification.getReplyNums() != null) {
					announcementNotification.setReplyNums(announcementNotification.getReplyNums() + 1);
				} else {
					announcementNotification.setReplyNums(1L);
				}
				announcementNotification = wechatBaseService.mergeOriginal(announcementNotification);
			}
			renderText(announcementNotification.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 点赞刷新页面
	 * 
	 * @return
	 */
	public String goIsPointPraiseList() {
		try {
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
				if (StringUtils.isNotEmpty(userId)) {
					employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				}
			}
			if (StringUtils.isNotEmpty(id)) {
				announcementNotification = wechatBaseService.findEntityByAttributeNoTenantId(AnnouncementNotification.class, "id", id);
				// 获取点赞人
				if (employee != null && announcementNotification != null && StringUtils.isNotEmpty(announcementNotification.getId()) && StringUtils.isNotEmpty(employee.getId())) {
					System.out.println(announcementNotification.getId() + employee.getId());
					wxpTrendsAndEmployee = wechatBaseService.findEntityByAttributeNoTenantId(WxpTrendsAndEmployee.class, "trendsIdAndEmployeeId", announcementNotification.getId() + employee.getId());
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("trendsId," + SearchCondition.EQUAL, announcementNotification.getId());
					params.put("isPointPraise," + SearchCondition.EQUAL, "1");
					wxpTrendsAndEmployeeList = wechatBaseService.findAllDataByConditions(WxpTrendsAndEmployee.class, params);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goIsPointPraiseList";
	}

	/**
	 * 开启回调,成员进入应用的事件推送
	 */
	public void getWechatMessage() {
		String sToken = "skXm";
		String sCorpID = "wx1a67071803f6077f";
		String sEncodingAESKey = "8KauVvGEAYhH7BtiVU78ovzP0D3lf8JSSYI64tuhzTM";
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
					getSession().setAttribute("qiyeCorpId", qiyeCorpId);
				}
				if (StringUtils.isNotEmpty(userId)) {
					System.out.println("企业号UserID:" + userId);
					getSession().setAttribute("userId", userId);
				}
			}
		} catch (Exception e) {
			// 验证URL失败，错误原因请查看异常
			e.printStackTrace();
		}
	}

	/**
	 * @return the announcementNotificationList
	 */
	public List<AnnouncementNotification> getAnnouncementNotificationList() {
		return announcementNotificationList;
	}

	/**
	 * @param announcementNotificationList
	 *            the announcementNotificationList to set
	 */
	public void setAnnouncementNotificationList(List<AnnouncementNotification> announcementNotificationList) {
		this.announcementNotificationList = announcementNotificationList;
	}

	/**
	 * @return the wxpTrendsAndEmployeeList
	 */
	public List<WxpTrendsAndEmployee> getWxpTrendsAndEmployeeList() {
		return wxpTrendsAndEmployeeList;
	}

	/**
	 * @param wxpTrendsAndEmployeeList
	 *            the wxpTrendsAndEmployeeList to set
	 */
	public void setWxpTrendsAndEmployeeList(List<WxpTrendsAndEmployee> wxpTrendsAndEmployeeList) {
		this.wxpTrendsAndEmployeeList = wxpTrendsAndEmployeeList;
	}

	/**
	 * @return the wxpTrendsAndEmployee
	 */
	public WxpTrendsAndEmployee getWxpTrendsAndEmployee() {
		return wxpTrendsAndEmployee;
	}

	/**
	 * @param wxpTrendsAndEmployee
	 *            the wxpTrendsAndEmployee to set
	 */
	public void setWxpTrendsAndEmployee(WxpTrendsAndEmployee wxpTrendsAndEmployee) {
		this.wxpTrendsAndEmployee = wxpTrendsAndEmployee;
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
	 * @return the tenantId
	 */
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId
	 *            the tenantId to set
	 */
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
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
	 * @return the announcementNotification
	 */
	public AnnouncementNotification getAnnouncementNotification() {
		return announcementNotification;
	}

	/**
	 * @param announcementNotification
	 *            the announcementNotification to set
	 */
	public void setAnnouncementNotification(AnnouncementNotification announcementNotification) {
		this.announcementNotification = announcementNotification;
	}

}
