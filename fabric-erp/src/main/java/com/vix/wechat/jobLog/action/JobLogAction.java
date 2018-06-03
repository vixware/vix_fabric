package com.vix.wechat.jobLog.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.personaloffice.entity.WorkLog;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.wechat.base.action.WechatBaseAction;
import com.vix.wechat.base.entity.WxpQyPicture;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.util.WxQyUtil;

@Controller
@Scope("prototype")
public class JobLogAction extends WechatBaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private String wxpQyPictureId;
	private String ids;
	private List<WorkLog> workLogList;
	private Set<WxpQyPicture> wxpQyPictureList;
	private Employee employee;
	private String userId;
	private Set<Uploader> uploaderList;
	private Integer docNum = 0;
	private String workLogId = "";
	private EvaluationReview evaluationReview;
	private List<EvaluationReview> evaluationReviewList;
	private Integer evaluationReviewNum = 0;
	private Integer employeeNum = 0;
	private WorkLog workLog;
	private List<Employee> employeeList;
	private String userIdStr = "";
	private String check = "0";

	private String timestamp; // 必填，生成签名的时间戳
	private String nonceStr; // 必填，生成签名的随机串
	private String signature;// 必填，签名，见附录1
	private String qiyeCorpId;// 企业号id
	private String jsapiTicket;
	private String uploaderId;

	private String corpid;// 企业号ID

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
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

			String state = getRequestParameter("state");
			String code = getRequestParameter("code");
			userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/jobLogAction!goSaveOrUpdate.action?id=" + id + "&corpid=" + corpid);
			if (StringUtils.isNotEmpty(id)) {
				workLog = wechatBaseService.findEntityById(WorkLog.class, id);
				if (workLog != null) {
					wxpQyPictureList = workLog.getSubWxpQyPictures();
					uploaderList = workLog.getSubUploaders();
					docNum = uploaderList.size();
					if (workLog.getSubEmployees() != null && workLog.getSubEmployees().size() > 0) {
						employeeList = new ArrayList<Employee>();
						employeeList.addAll(workLog.getSubEmployees());
						for (Employee employee : employeeList) {
							ids += "," + employee.getId();
						}
					}
				}
			}
			if (StringUtils.isNotEmpty(ids)) {
				// 获取选定成员
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, ids);
				employeeList = wechatBaseService.findAllDataByConditions(Employee.class, p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	/**
	 * 获取媒体文件
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param media_id
	 *            媒体文件id
	 * @param savePath
	 *            文件在服务器上的存储路径
	 */
	public void downloadMedia() {
		String mediaId = getRequestParameter("mediaId");
		String filePath = null;
		try {
			// 拼接请求地址
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "tenantId", api_qiye_tenantid);
			String requestUrl = WxQyUtil.getMediaByMediaId(mediaId, site.getQiyeAccessToken());
			System.out.println(requestUrl);
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			String savePath = this.getUploadFileSavePic();
			// 根据内容类型获取扩展名
			String fileExt = conn.getHeaderField("Content-Type");
			// 将mediaId作为文件名
			filePath = savePath + mediaId + fileExt;

			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();
			conn.disconnect();
		} catch (Exception e) {
			filePath = null;
		}
		renderText(filePath);
	}

	/**
	 * 查看日志详情
	 * 
	 * @return
	 */
	public String goShowDetails() {
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
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/jobLogAction!goShowDetails.action?id=" + id + "&corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(id)) {
				workLog = wechatBaseService.findEntityByAttributeNoTenantId(WorkLog.class, "id", id);
				if (workLog != null) {
					if (workLog.getEmployee() != null && workLog.getEmployee().getUserId() != null && workLog.getEmployee().getUserId().equals(userId)) {
						check = "1";
					}
					if (workLog.getReadTimes() != null) {
						workLog.setReadTimes(workLog.getReadTimes() + 1);
						workLog = wechatBaseService.mergeOriginal(workLog);
					} else {
						workLog.setReadTimes(1L);
						workLog = wechatBaseService.mergeOriginal(workLog);
					}
					if (workLog.getSubEmployees() != null && workLog.getSubEmployees().size() > 0) {
						employeeList = new ArrayList<Employee>();
						employeeList.addAll(workLog.getSubEmployees());
						employeeNum = employeeList.size();
					}
					wxpQyPictureList = workLog.getSubWxpQyPictures();
					uploaderList = workLog.getSubUploaders();
					docNum = uploaderList.size();
					if (workLog.getSubEvaluationReviews() != null && workLog.getSubEvaluationReviews().size() > 0) {
						evaluationReviewList = new ArrayList<EvaluationReview>();
						evaluationReviewList.addAll(workLog.getSubEvaluationReviews());
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
		return "goShowDetails";
	}

	/**
	 * 我创建的日志
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
			System.out.println(corpid);
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/jobLogAction!goList.action?corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}
			if (employee != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
				params.put("status," + SearchCondition.EQUAL, "1");
				params.put("employee.id," + SearchCondition.EQUAL, employee.getId());
				workLogList = wechatBaseService.findAllDataByConditions(WorkLog.class, params);
				// 倒序
				Collections.sort(workLogList, new Comparator<WorkLog>() {
					@Override
					public int compare(WorkLog o1, WorkLog o2) {
						return getTime(o2.getCreateTime(), o1.getCreateTime());
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goList";
	}

	/**
	 * 草稿
	 */
	public String goDraftList() {
		try {
			if (StringUtils.isNotEmpty(getRequest().getParameter("corpid"))) {
				corpid = getRequest().getParameter("corpid");
				getSession().setAttribute("corpid", getRequest().getParameter("corpid"));
			} else {
				corpid = api_qiye_corpid;
			}
			System.out.println(corpid);
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/jobLogAction!goDraftList.action?corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}
			if (employee != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
				params.put("status," + SearchCondition.EQUAL, "0");
				params.put("employee.id," + SearchCondition.EQUAL, employee.getId());
				workLogList = wechatBaseService.findAllDataByConditions(WorkLog.class, params);
				// 倒序
				Collections.sort(workLogList, new Comparator<WorkLog>() {
					@Override
					public int compare(WorkLog o1, WorkLog o2) {
						return getTime(o2.getCreateTime(), o1.getCreateTime());
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDraftList";
	}

	/**
	 * 下属的日志
	 * 
	 * @return
	 */
	public String goSubordinateList() {
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
					userId = checkWxQyVisitUser(site, state, code, redirect_ip + "/wechat/jobLogAction!goSubordinateList.action?corpid=" + corpid);
				}
			}
			if (StringUtils.isNotEmpty(userId)) {
				e = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
			}
			Map<String, Object> params = getParams();
			params.put("tenantId," + SearchCondition.EQUAL, site.getTenantId());
			params.put("status," + SearchCondition.EQUAL, "1");
			List<WorkLog> workList = wechatBaseService.findAllDataByConditions(WorkLog.class, params);

			Map<String, WorkLog> empMap = new HashMap<String, WorkLog>();
			for (WorkLog workLog : workList) {
				Set<Employee> subEmployees = workLog.getSubEmployees();
				for (Employee employee : subEmployees) {
					empMap.put(employee.getUserId() + workLog.getId(), workLog);
				}
			}
			workLogList = new ArrayList<WorkLog>();
			for (WorkLog work : workList) {
				if (empMap.containsKey(e.getUserId() + work.getId())) {
					workLogList.add(empMap.get(e.getUserId() + work.getId()));
				}
			} // 倒序
			Collections.sort(workLogList, new Comparator<WorkLog>() {
				@Override
				public int compare(WorkLog o1, WorkLog o2) {
					return getTime(o2.getCreateTime(), o1.getCreateTime());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubordinateList";
	}

	/** 处理修改操作 */
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
			if (workLog != null && StringUtils.isNotEmpty(workLog.getId())) {
				WorkLog worklog = wechatBaseService.findEntityByAttributeNoTenantId(WorkLog.class, "id", workLog.getId());
				BeanUtils.copyProperties(worklog, workLog, new String[] { "title", "logContent" });
			} else {
				if (employee != null && StringUtils.isNotEmpty(employee.getId())) {
					workLog.setEmployee(employee);
					workLog.setTenantId(employee.getTenantId());
					workLog.setCompanyCode(employee.getCompanyCode());
					workLog.setCompanyInnerCode(employee.getCompanyInnerCode());
					workLog.setCreator(employee.getName());
				} else {
					workLog.setEmployee(null);
				}
				workLog.setCreateTime(new Date());
				workLog.setLogDate(new Date());
			}
			workLog.setUpdateTime(new Date());
			String sub = getRequestParameter("sub");
			if (StringUtils.isNotEmpty(sub)) {
				if ("1".equals(sub)) {
					workLog.setStatus("1");
				} else {
					workLog.setStatus("0");
				}
			}
			// 获取选定成员
			if (StringUtils.isNotEmpty(ids)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, ids);
				employeeList = wechatBaseService.findAllDataByConditions(Employee.class, p);
				Set<Employee> subEmployees = new HashSet<Employee>();
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee employee : employeeList) {
						subEmployees.add(employee);
						userIdStr += "|" + employee.getUserId();
					}
				}
				workLog.setSubEmployees(subEmployees);
			}
			workLog = wechatBaseService.mergeOriginal(workLog);

			// 保存附件
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				WxpQyPicture wxpQyPicture = new WxpQyPicture();
				wxpQyPicture.setPictureUrl("/img/wechat/" + savePathAndName[1].toString());
				wxpQyPicture.setWorkLog(workLog);
				wxpQyPicture.setVixTask(null);
				wxpQyPicture = wechatBaseService.mergeOriginal(wxpQyPicture);
			}

			String[] saveDocPathAndName = saveDocUploadPic();
			if (saveDocPathAndName != null && saveDocPathAndName.length == 3) {
				Uploader uploader = new Uploader();
				uploader.setFileName(saveDocPathAndName[1].toString());
				uploader.setFilePath("/img/wechat/" + saveDocPathAndName[1].toString());
				uploader.setWorkLog(workLog);
				uploader.setFilesize(saveDocPathAndName[2].toString());
				uploader = wechatBaseService.mergeOriginal(uploader);
			}
			if (StringUtils.isNotEmpty(userIdStr)) {
				if (StringUtils.isNotEmpty(sub) && "1".equals(sub)) {
					// 更新企业号token
					WxQyUtil.messageSendNews("4", userIdStr.substring(1), workLog.getTitle(), site.getQiyeAccessToken(), workLog.getLogContent(), redirect_ip + "/wechat/jobLogAction!goShowDetails.action?id=" + workLog.getId() + "&corpid=" + corpid, "");
				}
			}
			renderText(workLog.getId() + "," + sub);
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
	 * 负责人
	 * 
	 * @return
	 */
	public String goChooseEmployee() {
		try {
			if (getSession().getAttribute("corpid") != null && !"".equals(getSession().getAttribute("corpid"))) {
				corpid = String.valueOf(getSession().getAttribute("corpid"));
			}
			System.out.println(corpid);
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				workLog = wechatBaseService.findEntityById(WorkLog.class, id);
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
		return "goChooseEmployee";
	}

	public String goShowPics() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				workLog = wechatBaseService.findEntityByAttributeNoTenantId(WorkLog.class, "id", id);
				if (workLog != null) {
					wxpQyPictureList = workLog.getSubWxpQyPictures();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPics";
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				for (String idStr : ids.split(",")) {
					if (StringUtils.isNotEmpty(idStr)) {
						WorkLog workLog = wechatBaseService.findEntityByAttributeNoTenantId(WorkLog.class, "id", idStr);
						if (workLog != null) {
							workLog.setStatus("2");
							wechatBaseService.mergeOriginal(workLog);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goWorkLogList";
	}

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
			Employee e = null;
			if (workLog != null && workLog.getId() != null) {
				evaluationReview.setWorkLog(workLog);
				e = workLog.getEmployee();
			}
			if (employee != null) {
				evaluationReview.setEmployee(employee);
				evaluationReview.setTenantId(employee.getTenantId());
				evaluationReview.setEvaluationTime(new Date());
				evaluationReview.setCompanyCode(employee.getCompanyCode());
				evaluationReview.setCompanyInnerCode(employee.getCompanyInnerCode());
				WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "tenantId", employee.getTenantId());
				if (site != null) {
					WxQyUtil.messageSend("4", e.getUserId(), employee.getName() + "评论了:" + workLog.getTitle(), site.getQiyeAccessToken());
				}
			}
			evaluationReview = wechatBaseService.mergeOriginal(evaluationReview);
			renderText(workLog.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 开启回调,成员进入应用的事件推送
	 */
	public void getWechatMessage() {
		// System.out.println("授权企业号的corpid:" +
		// getRequest().getParameter("corpid"));
		// String sToken = "4u6XejrVLhjhFU9le5X";
		// String sCorpID = "wx1a67071803f6077f";
		// String sEncodingAESKey =
		// "5REem6ht0GJb8XNX8INSw4HQllWF8nLQmp3GkaKs1ry";
		try {
			/*
			 * WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey,
			 * sCorpID); // 解析出url上的参数值如下： String sVerifyMsgSig =
			 * getRequest().getParameter("msg_signature"); String
			 * sVerifyTimeStamp = getRequest().getParameter("timestamp"); String
			 * sVerifyNonce = getRequest().getParameter("nonce"); String
			 * sVerifyEchoStr = getRequest().getParameter("echostr");
			 * getSession().setAttribute("sVerifyTimeStamp", sVerifyTimeStamp);
			 * System.out.println(getSession().getId()); if
			 * (StringUtils.isNotEmpty(sVerifyEchoStr)) { // 需要返回的明文 String
			 * sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,
			 * sVerifyNonce, sVerifyEchoStr); System.out.println(
			 * "verifyurl echostr: " + sEchoStr); renderText(sEchoStr); }
			 * 
			 * String postStrXml = wxcpt.DecryptMsg(sVerifyMsgSig,
			 * sVerifyTimeStamp, sVerifyNonce,
			 * this.readStreamParameter(getRequest().getInputStream()));
			 * System.out.println("postStrXml:" + postStrXml); if
			 * (StringUtils.isNotEmpty(postStrXml)) { Document xmlData =
			 * DocumentHelper.parseText(postStrXml); Element root =
			 * xmlData.getRootElement(); String qiyeCorpId =
			 * root.elementText("ToUserName"); String userId =
			 * root.elementText("FromUserName"); if
			 * (StringUtils.isNotEmpty(qiyeCorpId)) {
			 * System.out.println("企业号CorpID:" + qiyeCorpId); } if
			 * (StringUtils.isNotEmpty(userId)) {
			 * System.out.println("企业号UserID:" + userId); } }
			 */} catch (Exception e) {
			// 验证URL失败，错误原因请查看异常
			// e.printStackTrace();
		}
	}

	public void deleteUploaderById() {
		try {
			String workLogId = "";
			Uploader uploader = wechatBaseService.findEntityByAttributeNoTenantId(Uploader.class, "id", uploaderId);
			if (null != uploader) {
				workLogId = uploader.getWorkLog().getId();
				wechatBaseService.deleteByEntity(uploader);
			}
			renderText(workLogId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 点赞刷新页面
	 * 
	 * @return
	 */
	public String goJobLogDocList() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				workLog = wechatBaseService.findEntityById(WorkLog.class, id);
				if (workLog != null) {
					uploaderList = workLog.getSubUploaders();
					docNum = uploaderList.size();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goJobLogDocList";
	}

	/**
	 * 评论内容区切换
	 * 
	 * @return
	 */
	public String goEvaluationReviewList() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				workLog = wechatBaseService.findEntityByAttributeNoTenantId(WorkLog.class, "id", id);
				if (workLog != null && workLog.getSubEvaluationReviews() != null && workLog .getSubEvaluationReviews().size() > 0) {
					evaluationReviewList = new ArrayList<EvaluationReview>();
					evaluationReviewList.addAll(workLog.getSubEvaluationReviews());
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goEvaluationReviewList";
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
	 * @return the employeeList
	 */
	public List<Employee> getEmployeeList() {
		return employeeList;
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
	 * @param employeeList
	 *            the employeeList to set
	 */
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	/**
	 * @return the workLogList
	 */
	public List<WorkLog> getWorkLogList() {
		return workLogList;
	}

	/**
	 * @param workLogList
	 *            the workLogList to set
	 */
	public void setWorkLogList(List<WorkLog> workLogList) {
		this.workLogList = workLogList;
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
	 * @return the workLogId
	 */
	public String getWorkLogId() {
		return workLogId;
	}

	/**
	 * @param workLogId
	 *            the workLogId to set
	 */
	public void setWorkLogId(String workLogId) {
		this.workLogId = workLogId;
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
	 * @return the workLog
	 */
	public WorkLog getWorkLog() {
		return workLog;
	}

	/**
	 * @param workLog
	 *            the workLog to set
	 */
	public void setWorkLog(WorkLog workLog) {
		this.workLog = workLog;
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

}
