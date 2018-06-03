package com.vix.purchase.plan.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.plan.entity.PurchasePlanItems;
import com.vix.mdm.purchase.plan.entity.PurchasePlanPackage;
import com.vix.purchase.plan.controller.PurchasePackageController;

/**
 * 计划打包汇总 com.vix.purchase.plan.action.PurchasePackageAction
 *
 * @author bjitzhang
 *
 * @date 2015年11月12日
 */
@Controller
@Scope("prototype")
public class PurchasePackageAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private PurchasePackageController purchasePackageController;
	private String id;
	private String pageNo;
	private String purchasePlanIds;
	private PurchasePlanPackage purchasePlanPackage;
	private List<PurchasePlan> purchasePlanList;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			purchasePlanList = purchasePackageController.findPurchasePlanList(params);
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

			Pager pager = purchasePackageController.goSingleList(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public void getPurchasePlanItemsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id)) {
				PurchasePlan purchasePlan = purchasePackageController.findPurchasePlanById(id);
				if (null != purchasePlan) {
					json = convertListToJson(new ArrayList<PurchasePlanItems>(purchasePlan.getPurchasePlanItems()), purchasePlan.getPurchasePlanItems().size());
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

	/** 处理修改操作 */
	public String goPack() {
		boolean isSave = true;
		try {
			if (null != purchasePlanPackage.getId() && !"".equals(purchasePlanPackage.getId())) {
				isSave = false;
			}
			purchasePlanPackage = purchasePackageController.doSavePurchasePlanPackage(purchasePlanPackage);
			if (StringUtils.isNotEmpty(purchasePlanIds) && !"0".equals(purchasePlanIds)) {
				String[] purchasePlanIdsArr = purchasePlanIds.split(",");
				if (purchasePlanIdsArr != null && purchasePlanIdsArr.length > 0) {
					for (String purchasePlanId : purchasePlanIdsArr) {
						if (purchasePlanId != null && !"".equals(purchasePlanId)) {
							PurchasePlan purchasePlan = purchasePackageController.findPurchasePlanById(purchasePlanId);
							if (purchasePlan != null) {
								if (purchasePlanPackage != null) {
									purchasePlan.setPurchasePlanPackage(purchasePlanPackage);
									purchasePlan.setIsReport("1");
									purchasePackageController.doSavePurchasePlan(purchasePlan);
								}
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

	public String goPurchasePlanPackage() {
		return "goPurchasePlanPackage";
	}

	/**
	 * 跳转到用户查看页面
	 * 
	 * @return
	 */
	public String goShowPurchasePlan() {

		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchasePlanPackage = purchasePackageController.findEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchasePlan";
	}

	public String goSearch() {
		return "goSearch";
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
	 * @return the fileToUpload
	 */
	@Override
	public File getFileToUpload() {
		return fileToUpload;
	}

	/**
	 * @param fileToUpload
	 *            the fileToUpload to set
	 */
	@Override
	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	/**
	 * @return the fileToUploadFileName
	 */
	@Override
	public String getFileToUploadFileName() {
		return fileToUploadFileName;
	}

	/**
	 * @param fileToUploadFileName
	 *            the fileToUploadFileName to set
	 */
	@Override
	public void setFileToUploadFileName(String fileToUploadFileName) {
		this.fileToUploadFileName = fileToUploadFileName;
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

}
