package com.vix.purchase.plan.action;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.plan.entity.PurchasePlanItems;
import com.vix.mdm.purchase.plan.entity.PurchasePlanPackage;
import com.vix.purchase.plan.controller.PurchaseApproveController;
import com.vix.purchase.plan.controller.PurchasePlanController;
import com.wsx.excel.loader.LoadManager;
import com.wsx.excel.service.ExcelService;
import com.wsx.excel.service.ExcelServiceImpl;

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
public class PurchasePlanFinalizedAction extends BaseAction implements LoadManager {

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
					if (employee.getOrganizationUnit() != null) {
						params.put("organizationUnit.id," + SearchCondition.EQUAL, employee.getOrganizationUnit().getId());
						params.put("packageType," + SearchCondition.EQUAL, "O");
					} /*else {
						params.put("employee.id," + SearchCondition.EQUAL, employee.getId());
						params.put("packageType," + SearchCondition.EQUAL, "E");
						}*/
				}
			}

			Pager pager = purchaseApproveController.goPurchasePlanPackagePager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
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
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchasePlanPackage = purchaseApproveController.doListPurchasePlanPackageById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchasePlanPackage";
	}

	/**
	 * 提交计划
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

	public String exportPurchasePlanExcel() throws Exception {
		try {
			String xlsPath = "resource/export/pur_purchaseplan_template.xls";
			String xmlPath = "resource/export/pur_purchaseplan_template.xml";
			File xlsFile = new File(Thread.currentThread().getContextClassLoader().getResource(xlsPath).toURI());
			File xmlFile = new File(Thread.currentThread().getContextClassLoader().getResource(xmlPath).toURI());
			ExcelService excelService = new ExcelServiceImpl(this);
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");

			String ua = getRequest().getHeader("user-agent");
			String fileName = "采购计划.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			excelService.exportExcel(xmlFile, xlsFile, excelResponse.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	@Override
	public List<Map<String, Object>> loadData(String arg0, String[] arg1) {
		List<Map<String, Object>> resList = new LinkedList<Map<String, Object>>();
		try {
			Map<String, Object> params = getParams();
			//根据盘点单导出相应数据
			params.put("purchasePlan.id," + SearchCondition.EQUAL, id);
			List<PurchasePlanItems> stockTakingList = purchasePlanController.doListPurchasePlanItemsList(params);
			for (PurchasePlanItems purchasePlanItems : stockTakingList) {
				if (purchasePlanItems != null) {
					Map<String, Object> reMap = new HashMap<String, Object>();
					reMap.put("itemCode", purchasePlanItems.getItemCode());
					reMap.put("itemName", purchasePlanItems.getItemName());
					reMap.put("amount", purchasePlanItems.getAmount());
					reMap.put("unitcost", purchasePlanItems.getUnitcost());
					resList.add(reMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resList;
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

}
