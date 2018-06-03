package com.vix.wechat.identityVerification.action;

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
import com.vix.oa.share.entity.Trends;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.wechat.base.action.WechatBaseAction;
import com.vix.wechat.base.entity.WxpQyPicture;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.entity.WxpTrendsAndEmployee;

@Controller
@Scope("prototype")
public class IdentityVerificationAction extends WechatBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private Trends trends;
	/** 新闻管理 */
	private List<Trends> trendsList;
	/**
	 * 成员UserID
	 */
	private String userId;
	private Employee employee;
	private List<WxpTrendsAndEmployee> wxpTrendsAndEmployeeList;
	private WxpTrendsAndEmployee wxpTrendsAndEmployee;
	/**
	 * 回复数量
	 */
	private Integer evaluationReviewNum = 0;
	private List<EvaluationReview> evaluationReviewList;
	private EvaluationReview evaluationReview;
	private String timestamp; // 必填，生成签名的时间戳
	private String nonceStr; // 必填，生成签名的随机串
	private String signature;// 必填，签名，见附录1
	private String qiyeCorpId;// 企业号CorpID
	private String jsapiTicket;
	private List<Employee> empList;
	private String userIdStr;
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

			String code = getRequestParameter("code");
			String state = getRequestParameter("state");
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/identityVerificationAction!goList.action?corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}

			Map<String, Object> params = new HashMap<String, Object>();
			if (site != null) {
				params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			}
			trendsList = wechatBaseService.findAllDataByConditions(Trends.class, params);
			Collections.sort(trendsList, new Comparator<Trends>() {
				@Override
				public int compare(Trends o1, Trends o2) {
					return getTime(o2.getCreateTime(), o1.getCreateTime());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				trends = wechatBaseService.findEntityById(Trends.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	public String goEvaluationReviewList() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				trends = wechatBaseService.findEntityByAttributeNoTenantId(Trends.class, "id", id);
				if (trends != null) {
					if (trends.getSubEvaluationReviews() != null && trends.getSubEvaluationReviews().size() > 0) {
						evaluationReviewNum = trends.getSubEvaluationReviews().size();
						evaluationReviewList = new ArrayList<EvaluationReview>();
						evaluationReviewList.addAll(trends.getSubEvaluationReviews());
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
		return "goEvaluationReviewList";
	}

	public String goIsPointPraiseList() {
		try {
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
				if (StringUtils.isNotEmpty(userId)) {
					employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				}
			}
			if (StringUtils.isNotEmpty(id)) {
				trends = wechatBaseService.findEntityByAttributeNoTenantId(Trends.class, "id", id);
				if (trends != null) {// 获取点赞人
					if (employee != null && StringUtils.isNotEmpty(trends.getId()) && StringUtils.isNotEmpty(employee.getId())) {
						System.out.println(trends.getId() + employee.getId());
						wxpTrendsAndEmployee = wechatBaseService.findEntityByAttributeNoTenantId(WxpTrendsAndEmployee.class, "trendsIdAndEmployeeId", trends.getId() + employee.getId());
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("trendsId," + SearchCondition.EQUAL, trends.getId());
						params.put("isPointPraise," + SearchCondition.EQUAL, "1");
						wxpTrendsAndEmployeeList = wechatBaseService.findAllDataByConditions(WxpTrendsAndEmployee.class, params);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goIsPointPraiseList";
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		try {
			if (getSession().getAttribute("corpid") != null && !"".equals(getSession().getAttribute("corpid"))) {
				corpid = String.valueOf(getSession().getAttribute("corpid"));
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			trends.setTenantId(site.getTenantId());
			trends.setCompanyInnerCode(site.getCompanyInnerCode());
			trends.setCreateTime(new Date());
			trends = wechatBaseService.mergeOriginal(trends);

			// 保存图片
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				WxpQyPicture wxpQyPicture = new WxpQyPicture();
				wxpQyPicture.setPictureUrl("/img/wechat/" + savePathAndName[1].toString());
				wxpQyPicture.setTrends(trends);
				wxpQyPicture = wechatBaseService.mergeOriginal(wxpQyPicture);
				trends.setFirstPictureUrl(wxpQyPicture.getPictureUrl());
			}
			trends = wechatBaseService.mergeOriginal(trends);
			Map<String, Object> p = getParams();
			p.put("empType," + SearchCondition.EQUAL, "WE");
			empList = wechatBaseService.findAllDataByConditions(Employee.class, p);
			if (empList != null && empList.size() > 0) {
				for (Employee e : empList) {
					userIdStr += "|" + e.getUserId();
				}
			}
			if (StringUtils.isNotEmpty(userIdStr)) {
				// 更新企业号token
				saveOrUpdateWxpWeixinSite(site);
				sendMessage("15", userIdStr.substring(1), trends.getTitle(), trends.getSourceFrom(), trends.getId(), trends.getFirstPictureUrl(), trends.getTenantId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
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
				System.out.println("pointPraise,id" + id);
				trends = wechatBaseService.findEntityByAttributeNoTenantId(Trends.class, "id", id);
				WxpTrendsAndEmployee wxpTrendsAndEmployee = null;
				if (trends != null && employee != null && StringUtils.isNotEmpty(trends.getId()) && StringUtils.isNotEmpty(employee.getId())) {
					wxpTrendsAndEmployee = wechatBaseService.findEntityByAttributeNoTenantId(WxpTrendsAndEmployee.class, "trendsIdAndEmployeeId", trends.getId() + employee.getId());
					if (wxpTrendsAndEmployee != null && StringUtils.isNotEmpty(wxpTrendsAndEmployee.getId())) {
						if ("0".equals(wxpTrendsAndEmployee.getIsPointPraise())) {
							wxpTrendsAndEmployee.setIsPointPraise("1");
							wxpTrendsAndEmployee = wechatBaseService.mergeOriginal(wxpTrendsAndEmployee);

							Map<String, Object> params = new HashMap<String, Object>();
							params.put("trendsId," + SearchCondition.EQUAL, trends.getId());
							params.put("isPointPraise," + SearchCondition.EQUAL, "1");
							wxpTrendsAndEmployeeList = wechatBaseService.findAllDataByConditions(WxpTrendsAndEmployee.class, params);
							if (wxpTrendsAndEmployeeList != null && wxpTrendsAndEmployeeList.size() > 0) {
								trends.setPointPraiseNums(wxpTrendsAndEmployeeList.size());
								wechatBaseService.mergeOriginal(trends);
							}
						}
					} else {
						wxpTrendsAndEmployee = new WxpTrendsAndEmployee();
						wxpTrendsAndEmployee.setTenantId(trends.getTenantId());
						wxpTrendsAndEmployee.setCompanyInnerCode(trends.getCompanyInnerCode());
						wxpTrendsAndEmployee.setCompanyCode(trends.getCompanyCode());
						wxpTrendsAndEmployee.setCreateTime(new Date());
						wxpTrendsAndEmployee.setTrendsId(trends.getId());
						wxpTrendsAndEmployee.setEmployeeId(employee.getId());
						wxpTrendsAndEmployee.setEmployeeName(employee.getName());
						wxpTrendsAndEmployee.setHeadImgUrl(employee.getHeadImgUrl());
						wxpTrendsAndEmployee.setIsPointPraise("1");
						wxpTrendsAndEmployee.setTrendsIdAndEmployeeId(trends.getId() + employee.getId());
						wechatBaseService.mergeOriginal(wxpTrendsAndEmployee);

						Map<String, Object> params = new HashMap<String, Object>();
						params.put("trendsId," + SearchCondition.EQUAL, trends.getId());
						params.put("isPointPraise," + SearchCondition.EQUAL, "1");
						wxpTrendsAndEmployeeList = wechatBaseService.findAllDataByConditions(WxpTrendsAndEmployee.class, params);
						if (wxpTrendsAndEmployeeList != null && wxpTrendsAndEmployeeList.size() > 0) {
							trends.setPointPraiseNums(wxpTrendsAndEmployeeList.size());
							wechatBaseService.mergeOriginal(trends);
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
				trends = wechatBaseService.findEntityByAttributeNoTenantId(Trends.class, "id", id);
				WxpTrendsAndEmployee wxpTrendsAndEmployee = null;
				if (trends != null && employee != null && StringUtils.isNotEmpty(trends.getId()) && StringUtils.isNotEmpty(employee.getId())) {
					wxpTrendsAndEmployee = wechatBaseService.findEntityByAttributeNoTenantId(WxpTrendsAndEmployee.class, "trendsIdAndEmployeeId", trends.getId() + employee.getId());
					if (wxpTrendsAndEmployee != null) {
						if ("1".equals(wxpTrendsAndEmployee.getIsPointPraise())) {
							wxpTrendsAndEmployee.setIsPointPraise("0");
							wxpTrendsAndEmployee = wechatBaseService.mergeOriginal(wxpTrendsAndEmployee);

							Map<String, Object> params = new HashMap<String, Object>();
							params.put("trendsId," + SearchCondition.EQUAL, trends.getId());
							params.put("isPointPraise," + SearchCondition.EQUAL, "1");
							wxpTrendsAndEmployeeList = wechatBaseService.findAllDataByConditions(WxpTrendsAndEmployee.class, params);
							if (wxpTrendsAndEmployeeList != null && wxpTrendsAndEmployeeList.size() > 0) {
								trends.setPointPraiseNums(wxpTrendsAndEmployeeList.size());
								wechatBaseService.mergeOriginal(trends);
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

	public String goShowNews() {
		try {
			// 注意这里参数名必须全部小写，且必须有序
			/*
			 * if (site != null) { site = saveOrUpdateWxpWeixinSite(site);
			 * qiyeCorpId = site.getQiyeCorpId(); WxpWeixinTicket
			 * wxpWeixinTicket =
			 * wechatBaseService.findEntityByAttributeNoTenantId(WxpWeixinTicket
			 * .class, "qiyeCorpId", qiyeCorpId); wxpWeixinTicket =
			 * saveOrUpdateWxpWeixinTicket(wxpWeixinTicket, site); jsapiTicket =
			 * wxpWeixinTicket.getQiyeTicket(); nonceStr =
			 * UUID.randomUUID().toString(); timestamp =
			 * Long.toString(System.currentTimeMillis() / 1000); StringBuffer
			 * url = getRequest().getRequestURL(); signature =
			 * getSign(jsapiTicket, nonceStr, timestamp, url.toString()); if
			 * (StringUtils.isNotEmpty(jsapiTicket) &&
			 * StringUtils.isNotEmpty(nonceStr) &&
			 * StringUtils.isNotEmpty(timestamp)) { try {
			 * getSession().setAttribute("jsapi_ticket", jsapiTicket); String
			 * sign = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr +
			 * "&timestamp=" + timestamp + "&url=" + url; MessageDigest crypt =
			 * MessageDigest.getInstance("SHA-1"); crypt.reset();
			 * crypt.update(sign.getBytes("UTF-8")); signature =
			 * byteToHex(crypt.digest()); } catch (Exception e) {
			 * e.printStackTrace(); } } }
			 */
			/*
			 * if (getSession().getAttribute("userId") != null &&
			 * !"".equals(getSession().getAttribute("userId"))) { userId =
			 * String.valueOf(getSession().getAttribute("userId")); if
			 * (StringUtils.isNotEmpty(userId)) { employee =
			 * wechatBaseService.findEntityByAttributeNoTenantId(Employee.class,
			 * "userId", userId); } }
			 */
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
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/identityVerificationAction!goShowNews.action?id=" + id + "&corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}

			if (StringUtils.isNotEmpty(id)) {
				trends = wechatBaseService.findEntityByAttributeNoTenantId(Trends.class, "id", id);
				// 阅读次数readTimes
				if (trends != null) {
					if (trends.getReadTimes() != null) {
						trends.setReadTimes(trends.getReadTimes() + 1);
						trends = wechatBaseService.mergeOriginal(trends);
					} else {
						trends.setReadTimes(1L);
						trends = wechatBaseService.mergeOriginal(trends);
					}
					// 回复人
					if (trends.getSubEvaluationReviews() != null && trends.getSubEvaluationReviews().size() > 0) {
						evaluationReviewNum = trends.getSubEvaluationReviews().size();
						evaluationReviewList = new ArrayList<EvaluationReview>();
						evaluationReviewList.addAll(trends.getSubEvaluationReviews());
						// 倒序
						Collections.sort(evaluationReviewList, new Comparator<EvaluationReview>() {
							@Override
							public int compare(EvaluationReview o1, EvaluationReview o2) {
								return getTime(o2.getEvaluationTime(), o1.getEvaluationTime());
							}
						});
					}
					// 获取点赞人
					if (employee != null && StringUtils.isNotEmpty(trends.getId()) && StringUtils.isNotEmpty(employee.getId())) {
						System.out.println(trends.getId() + employee.getId());
						wxpTrendsAndEmployee = wechatBaseService.findEntityByAttributeNoTenantId(WxpTrendsAndEmployee.class, "trendsIdAndEmployeeId", trends.getId() + employee.getId());
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("trendsId," + SearchCondition.EQUAL, trends.getId());
						params.put("isPointPraise," + SearchCondition.EQUAL, "1");
						wxpTrendsAndEmployeeList = wechatBaseService.findAllDataByConditions(WxpTrendsAndEmployee.class, params);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowNews";
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
			if (trends != null && trends.getId() != null) {
				evaluationReview.setTrends(trends);
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

			if (trends != null && trends.getId() != null) {
				trends = wechatBaseService.findEntityByAttributeNoTenantId(Trends.class, "id", trends.getId());
				if (trends.getReplyNums() != null) {
					trends.setReplyNums(trends.getReplyNums() + 1);
				} else {
					trends.setReplyNums(1L);
				}
				trends = wechatBaseService.mergeOriginal(trends);
			}
			renderText(trends.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 开启回调,成员进入应用的事件推送
	 */
	public void getWechatMessage() {
		String sToken = "vixnterp";
		String sCorpID = "wx1a67071803f6077f";
		String sEncodingAESKey = "Xt32xfiM9LSKOHqM2GkzuFC3wGVFtPLlaHukKsRMx7P";
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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
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
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * @return the trends
	 */
	public Trends getTrends() {
		return trends;
	}

	/**
	 * @param trends
	 *            the trends to set
	 */
	public void setTrends(Trends trends) {
		this.trends = trends;
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
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the nonceStr
	 */
	public String getNonceStr() {
		return nonceStr;
	}

	/**
	 * @param nonceStr
	 *            the nonceStr to set
	 */
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature
	 *            the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @return the qiyeCorpId
	 */
	public String getQiyeCorpId() {
		return qiyeCorpId;
	}

	/**
	 * @param qiyeCorpId
	 *            the qiyeCorpId to set
	 */
	public void setQiyeCorpId(String qiyeCorpId) {
		this.qiyeCorpId = qiyeCorpId;
	}

	/**
	 * @return the jsapiTicket
	 */
	public String getJsapiTicket() {
		return jsapiTicket;
	}

	/**
	 * @param jsapiTicket
	 *            the jsapiTicket to set
	 */
	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
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
	 * @return the trendsList
	 */
	public List<Trends> getTrendsList() {
		return trendsList;
	}

	/**
	 * @param trendsList
	 *            the trendsList to set
	 */
	public void setTrendsList(List<Trends> trendsList) {
		this.trendsList = trendsList;
	}

}