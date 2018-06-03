package com.vix.purchase.plan.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.vix.common.base.action.BaseAction;
import com.vix.common.billtype.BillType;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.plan.entity.PurchasePlanPackage;
import com.vix.purchase.plan.controller.PurchaseApproveController;
import com.vix.purchase.plan.controller.PurchasePlanController;
import com.vix.system.billTypeManagement.entity.BillsProperty;

import net.sf.json.JSONObject;

/**
 * 
 * com.vix.purchase.plan.action.PurchaseApproveAction
 *
 * @author bjitzhang
 *
 * @date 2015年11月12日
 */
@Controller
@Scope("prototype")
public class PurchaseApproveAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private PurchaseApproveController purchaseApproveController;
	private String id;
	private String pageNo;
	private PurchasePlanPackage purchasePlanPackage;
	private List<PurchasePlanPackage> purchasePlanPackageList;
	@Autowired
	private PurchasePlanController purchasePlanController;
	private String organdempid;
	private String purchasePlanIds;
	private List<PurchasePlan> purchasePlanList;
	private PurchasePlan purchasePlan;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			purchasePlanPackageList = purchaseApproveController.findPurchasePlanPackageList(params);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String status = getRequestParameter("status");
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 记录当前页面
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 获取当前员工信息
			if (SecurityUtil.getCurrentEmpId() != null) {
				Employee employee = purchaseApproveController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					// 如果登录的员工属于经销商或门店
					params.put("creator," + SearchCondition.EQUAL, employee.getName());
				}
			}
			Pager pager = purchaseApproveController.goPurchasePlanPackagePager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goListContent() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				Map<String, Object> params = getParams();
				params.put("purchasePlanPackage.id," + SearchCondition.EQUAL, id);
				purchasePlanList = purchaseApproveController.findPurchasePlanList(params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	public void getPurchasePlanJson() {
		try {
			String json = "";
			if (null != id && !"".equals(id)) {
				PurchasePlanPackage purchasePlanPackage = purchaseApproveController.doListPurchasePlanPackageById(id);
				if (null != purchasePlanPackage) {
					json = convertListToJson(new ArrayList<PurchasePlan>(purchasePlanPackage.getSubPurchasePlans()), purchasePlanPackage.getSubPurchasePlans().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goShowPurchasePlanPackage() {
		try {
			isAllowAudit = isAllowAudit(BillType.PUR_PLAN);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchasePlanPackage = purchaseApproveController.doListPurchasePlanPackageById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchasePlanPackage";
	}

	// 汇总
	public String goCollectPurchasePlan() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchasePlanPackage = purchaseApproveController.doListPurchasePlanPackageById(id);
			} else {
				purchasePlanPackage = new PurchasePlanPackage();
				purchasePlanPackage.setCode(autoCreateCode.getBillNO(BillType.PUR_PLAN));
				purchasePlanPackage.setCreator(SecurityUtil.getCurrentUserName());
				purchasePlanPackage.setCreateTime(new Date());
				purchasePlanPackage = purchaseApproveController.doSavePurchasePlanPackage(purchasePlanPackage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCollectPurchasePlan";
	}

	/**
	 * 计划汇总
	 * 
	 * @return
	 */
	public String goPack() {
		boolean isSave = true;
		try {
			if (null != purchasePlanPackage.getId() && !"".equals(purchasePlanPackage.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(purchasePlanPackage);

			if ("O".equals(purchasePlanPackage.getPackageType())) {
				organdempid = StringUtils.substring(organdempid, 0, organdempid.length() - 1);
				OrganizationUnit organizationUnit = purchasePlanController.doListOrganizationUnitById(organdempid);
				purchasePlanPackage.setOrganizationUnit(organizationUnit);
			} else if ("E".equals(purchasePlanPackage.getPackageType())) {
				Employee employee = purchasePlanController.doListEmployeeById(organdempid);
				purchasePlanPackage.setEmployee(employee);
			} else {
				purchasePlanPackage.setEmployee(null);
				purchasePlanPackage.setOrganizationUnit(null);
			}
			purchasePlanPackage.setPlanType("P");
			purchasePlanPackage = purchasePlanController.doSavePurchasePlanPackage(purchasePlanPackage);
			Double price = 0D;
			if (StringUtils.isNotEmpty(purchasePlanIds) && !"0".equals(purchasePlanIds)) {
				String[] purchasePlanIdsArr = purchasePlanIds.split(",");
				if (purchasePlanIdsArr != null && purchasePlanIdsArr.length > 0) {
					for (String purchasePlanId : purchasePlanIdsArr) {
						if (purchasePlanId != null && !"".equals(purchasePlanId)) {
							PurchasePlan purchasePlan = purchasePlanController.doListEntityById(purchasePlanId);
							if (purchasePlan != null && purchasePlan.getPrice() != null) {
								price += purchasePlan.getPrice();
								if (purchasePlanPackage != null) {
									purchasePlan.setPurchasePlanPackage(purchasePlanPackage);
									purchasePlan.setIsReport("1");
									purchasePlanController.doSavePurchasePlan(purchasePlan);
								}
							}
						}
					}
				}
			}
			purchasePlanPackage.setPrice(price);
			purchasePlanPackage = purchasePlanController.doSavePurchasePlanPackage(purchasePlanPackage);
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
		return UPDATE;
	}

	public String collectPurchasePlan() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(id)) {
				isSave = false;
			}
			purchasePlanPackage = purchasePlanController.doListPurchasePlanPackageById(id);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id," + SearchCondition.IN, purchasePlanIds);
			List<PurchasePlan> purchasePlanList = purchasePlanController.findPurchasePlanList(params);
			if (purchasePlanList != null && purchasePlanList.size() > 0) {
				for (PurchasePlan purchasePlan : purchasePlanList) {
					purchasePlan.setPurchasePlanPackage(purchasePlanPackage);
					purchasePlan = purchasePlanController.doSavePurchasePlan(purchasePlan);
				}
			}
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
		return UPDATE;
	}

	public String goChoosePurchasePlanList() {
		return "goChoosePurchasePlanList";
	}

	/** 获取供应商列表数据 */
	public String goChoosePurchasePlanSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isReport," + SearchCondition.EQUAL, "0");
			params.put("status," + SearchCondition.EQUAL, "1");
			Pager pager = purchasePlanController.goSingleList(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChoosePurchasePlanSingleList";
	}

	/**
	 * 计划汇总
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != purchasePlanPackage.getId() && !"".equals(purchasePlanPackage.getId())) {
				isSave = false;
			}
			// 采购计划明细
			initEntityBaseController.initEntityBaseAttribute(purchasePlanPackage);
			// 处理修改留痕
			// billMarkProcessController.processMark(purchasePlanPackage,
			// updateField);

			PurchasePlanPackage p = purchasePlanController.doListPurchasePlanPackageById(purchasePlanPackage.getId());
			Double price = 0D;
			Double amount = 0D;
			Set<PurchasePlan> purchasePlans = p.getSubPurchasePlans();
			for (PurchasePlan purchasePlan : purchasePlans) {
				price += purchasePlan.getPrice();
				amount += purchasePlan.getAmount();
			}
			purchasePlanPackage.setPrice(price);
			purchasePlanPackage.setAmount(amount);
			purchasePlanPackage = purchasePlanController.doSavePurchasePlanPackage(purchasePlanPackage);

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
		return UPDATE;
	}

	/**
	 * 提交审批
	 * 
	 * @return
	 */
	public String subPurchasePlanPackage() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(id)) {
				isSave = false;
			}
			PurchasePlanPackage purchasePlanPackage = purchasePlanController.doListPurchasePlanPackageById(id);
			Double price = 0D;
			Double amount = 0D;
			Set<PurchasePlan> purchasePlans = purchasePlanPackage.getSubPurchasePlans();
			for (PurchasePlan purchasePlan : purchasePlans) {
				if (purchasePlan != null) {
					if (purchasePlan.getPrice() != null) {
						price += purchasePlan.getPrice();
					}
					if (purchasePlan.getAmount() != null) {
						amount += purchasePlan.getAmount();
					}
				}
			}
			purchasePlanPackage.setPrice(price);
			purchasePlanPackage.setAmount(amount);
			if (purchasePlanPackage != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("propertyCode," + SearchCondition.EQUAL, BillType.PUR_PLAN);
				params.put("tenantId," + SearchCondition.EQUAL, purchasePlanPackage.getTenantId());
				List<BillsProperty> billsPropertyList = purchasePlanController.doListBillsPropertyList(params);
				if (billsPropertyList != null && billsPropertyList.size() > 0) {
					for (BillsProperty billsProperty : billsPropertyList) {

						String billsPropertyId = billsProperty.getId();
						String billTypeCode = BillType.PUR_PLAN;
						String billTitle = purchasePlanPackage.getName();
						String billId = purchasePlanPackage.getId();

						MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
						formData.add("billsPropertyId", billsPropertyId);
						formData.add("billTypeCode", billTypeCode);
						formData.add("billTitle", URLEncoder.encode(billTitle, "utf-8"));
						formData.add("billId", billId);
						Object objUser = getSession().getAttribute("userInfo");
						UserAccount user = null;
						if (null != objUser && objUser instanceof UserAccount) {
							user = (UserAccount) objUser;
							formData.add("userId", user.getId());
						}
						String url = "http://localhost:8080/vform/activiti/deployment/startAndSubmitByBillsCode";
						String response = postData(formData, url);
						JSONObject json = JSONObject.fromObject(response);
						if (json.has("status")) {
							if ("1".equals(json.getString("status"))) {
								purchasePlanPackage.setStatus("1");
								purchasePlanPackage = purchasePlanController.doSavePurchasePlanPackage(purchasePlanPackage);
								break;
							}
						}
					}
				}
			}

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
		return UPDATE;
	}

	public String postData(MultiValueMap<String, Object> formData, String url) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Accept-Charset", "utf-8");
		requestHeaders.setContentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(formData, requestHeaders);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class, formData);
		return response.getBody();
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchasePlan = purchasePlanController.doListEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSearch() {
		return "goSearch";
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
	 * @return the purchasePlanPackage
	 */
	public PurchasePlanPackage getPurchasePlanPackage() {
		return purchasePlanPackage;
	}

	/**
	 * @param purchasePlanPackage
	 *            the purchasePlanPackage to set
	 */
	public void setPurchasePlanPackage(PurchasePlanPackage purchasePlanPackage) {
		this.purchasePlanPackage = purchasePlanPackage;
	}

	/**
	 * @return the pageNo
	 */
	public String getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the purchasePlanPackageList
	 */
	public List<PurchasePlanPackage> getPurchasePlanPackageList() {
		return purchasePlanPackageList;
	}

	/**
	 * @param purchasePlanPackageList
	 *            the purchasePlanPackageList to set
	 */
	public void setPurchasePlanPackageList(List<PurchasePlanPackage> purchasePlanPackageList) {
		this.purchasePlanPackageList = purchasePlanPackageList;
	}

	/**
	 * @return the organdempid
	 */
	public String getOrgandempid() {
		return organdempid;
	}

	/**
	 * @param organdempid
	 *            the organdempid to set
	 */
	public void setOrgandempid(String organdempid) {
		this.organdempid = organdempid;
	}

	/**
	 * @return the purchasePlanIds
	 */
	public String getPurchasePlanIds() {
		return purchasePlanIds;
	}

	/**
	 * @param purchasePlanIds
	 *            the purchasePlanIds to set
	 */
	public void setPurchasePlanIds(String purchasePlanIds) {
		this.purchasePlanIds = purchasePlanIds;
	}

	/**
	 * @return the purchasePlanList
	 */
	public List<PurchasePlan> getPurchasePlanList() {
		return purchasePlanList;
	}

	/**
	 * @param purchasePlanList
	 *            the purchasePlanList to set
	 */
	public void setPurchasePlanList(List<PurchasePlan> purchasePlanList) {
		this.purchasePlanList = purchasePlanList;
	}

	/**
	 * @return the purchasePlan
	 */
	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}

	/**
	 * @param purchasePlan
	 *            the purchasePlan to set
	 */
	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
	}

	/**
	 * @return the isAllowAudit
	 */
	public String getIsAllowAudit() {
		return isAllowAudit;
	}

	/**
	 * @param isAllowAudit
	 *            the isAllowAudit to set
	 */
	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}

}
