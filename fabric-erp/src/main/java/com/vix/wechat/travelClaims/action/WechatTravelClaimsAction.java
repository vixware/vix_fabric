package com.vix.wechat.travelClaims.action;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.oa.travelclaims.entity.TravelExpense;
import com.vix.wechat.base.action.WechatBaseAction;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.entity.WxpQyWeixinTicket;

/**
 * @ClassFullName com.vix.wechat.travelClaims.action.WechatTravelClaimsAction
 *
 * @author bjitzhang
 *
 * @date 2016年3月16日
 * 
 */
@Controller
@Scope("prototype")
public class WechatTravelClaimsAction extends WechatBaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	//所选项目
	private List<TravelExpense> travelExpenseList;
	private List<Employee> employeeList;
	private Integer employeeNum = 0;
	private Integer docNum = 0;
	private List<Uploader> uploaderList;
	/**
	 * 报销单
	 */
	private TravelExpense travelExpense;

	private String timestamp; // 必填，生成签名的时间戳
	private String nonceStr; // 必填，生成签名的随机串
	private String signature;// 必填，签名，见附录1
	private String qiyeCorpId;//企业号id
	private String jsapiTicket;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId," + SearchCondition.EQUAL, api_qiye_tenantid);
			travelExpenseList = wechatBaseService.findAllDataByConditions(TravelExpense.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goList";
	}

	public String goSaveOrUpdate() {
		try {
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "tenantId", api_qiye_tenantid);
			//注意这里参数名必须全部小写，且必须有序
			if (site != null) {
				site = saveOrUpdateWxpWeixinSite(site);
				qiyeCorpId = site.getQiyeCorpId();
				WxpQyWeixinTicket wxpWeixinTicket = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinTicket.class, "qiyeCorpId", qiyeCorpId);
				wxpWeixinTicket = saveOrUpdateWxpWeixinTicket(wxpWeixinTicket, site);
				jsapiTicket = wxpWeixinTicket.getQiyeTicket();
				nonceStr = UUID.randomUUID().toString();
				timestamp = Long.toString(System.currentTimeMillis() / 1000);
				StringBuffer url = getRequest().getRequestURL();
				signature = getSign(jsapiTicket, nonceStr, timestamp, url.toString());
				if (StringUtils.isNotEmpty(jsapiTicket) && StringUtils.isNotEmpty(nonceStr) && StringUtils.isNotEmpty(timestamp)) {
					try {
						getSession().setAttribute("jsapi_ticket", jsapiTicket);
						String sign = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
						MessageDigest crypt = MessageDigest.getInstance("SHA-1");
						crypt.reset();
						crypt.update(sign.getBytes("UTF-8"));
						signature = byteToHex(crypt.digest());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				travelExpense = wechatBaseService.findEntityById(TravelExpense.class, id);
				uploaderList = new ArrayList<Uploader>();
				if (travelExpense.getSubUploaders() != null && travelExpense.getSubUploaders().size() > 0) {
					for (Uploader uploader : travelExpense.getSubUploaders()) {
						uploaderList.add(uploader);
					}
					docNum = uploaderList.size();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		try {
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "tenantId", api_qiye_tenantid);
			if (site != null) {
				travelExpense.setTenantId(site.getTenantId());
				travelExpense.setCompanyInnerCode(site.getCompanyInnerCode());
			}
			travelExpense.setCreateTime(new Date());
			travelExpense = wechatBaseService.mergeOriginal(travelExpense);
			renderText(travelExpense.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goChooseEmployee() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				travelExpense = wechatBaseService.findEntityById(TravelExpense.class, id);
			}
			Map<String, Object> params = getParams();
			params.put("tenantId," + SearchCondition.EQUAL, api_qiye_tenantid);
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
	 * @return the uploaderList
	 */
	public List<Uploader> getUploaderList() {
		return uploaderList;
	}

	/**
	 * @param uploaderList
	 *            the uploaderList to set
	 */
	public void setUploaderList(List<Uploader> uploaderList) {
		this.uploaderList = uploaderList;
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
	 * @return the travelExpenseList
	 */
	public List<TravelExpense> getTravelExpenseList() {
		return travelExpenseList;
	}

	/**
	 * @param travelExpenseList
	 *            the travelExpenseList to set
	 */
	public void setTravelExpenseList(List<TravelExpense> travelExpenseList) {
		this.travelExpenseList = travelExpenseList;
	}

	/**
	 * @return the travelExpense
	 */
	public TravelExpense getTravelExpense() {
		return travelExpense;
	}

	/**
	 * @param travelExpense
	 *            the travelExpense to set
	 */
	public void setTravelExpense(TravelExpense travelExpense) {
		this.travelExpense = travelExpense;
	}

}
