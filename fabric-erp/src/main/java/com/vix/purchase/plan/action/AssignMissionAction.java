package com.vix.purchase.plan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.plan.entity.PurchasePlanPackage;
import com.vix.purchase.plan.controller.PurchaseApproveController;

/**
 * 采购计划任务下达 com.vix.purchase.plan.action.AssignMissionAction
 *
 * @author bjitzhang
 *
 * @date 2016年1月12日
 */
@Controller
@Scope("prototype")
public class AssignMissionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private PurchaseApproveController purchaseApproveController;
	private String id;
	private String pageNo;
	private PurchasePlanPackage purchasePlanPackage;
	private List<PurchasePlanPackage> purchasePlanPackageList;

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

	public String goSaveOrUpdate() {

		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchasePlanPackage = purchaseApproveController.doListPurchasePlanPackageById(id);
			} else {
				purchasePlanPackage = new PurchasePlanPackage();
				purchasePlanPackage.setCreator(SecurityUtil.getCurrentUserName());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != purchasePlanPackage.getId() && !"".equals(purchasePlanPackage.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(purchasePlanPackage);
			purchasePlanPackage = purchaseApproveController.doSavePurchasePlanPackage(purchasePlanPackage);
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

}
