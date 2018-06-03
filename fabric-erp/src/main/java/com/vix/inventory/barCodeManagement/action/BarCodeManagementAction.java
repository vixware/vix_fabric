package com.vix.inventory.barCodeManagement.action;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.vo.OrgUnit;
import com.vix.core.web.Pager;
import com.vix.inventory.barCodeManagement.controller.BarCodeManagementController;
import com.vix.inventory.barCodeManagement.service.IBarCodeManagementService;
import com.vix.inventory.batch.entity.BaseCoder;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;

@Controller
@Scope("prototype")
public class BarCodeManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	@Autowired
	private BarCodeManagementController barCodeManagementController;
	@Autowired
	private IBarCodeManagementService barCodeManagementService;
	private String treeType;
	/**
	 * 编码规则表
	 */
	private EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle;
	private String billTypeCode;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = barCodeManagementService.findPagerByHqlConditions(getPager(), BaseCoder.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {

		try {
			if (StringUtils.isNotEmpty(billTypeCode)) {
				encodingRulesTableInTheMiddle = barCodeManagementService.findEntityByAttribute(EncodingRulesTableInTheMiddle.class, "billType", billTypeCode);
				if (encodingRulesTableInTheMiddle != null) {
				} else {
					encodingRulesTableInTheMiddle = new EncodingRulesTableInTheMiddle();
					encodingRulesTableInTheMiddle.setBillType(billTypeCode);
					encodingRulesTableInTheMiddle.setCodeType("C");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {

		boolean isSave = true;
		try {
			encodingRulesTableInTheMiddle = barCodeManagementController.doSaveEncodingRulesTableInTheMiddle(encodingRulesTableInTheMiddle);
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

	public String goCodeList() {
		return "goSaveOrUpdate";
	}

	/** 树形结构JSON */
	/**
	 * 
	 * @Title: findOrgAndUnitTreeToJson
	 * @Description: 加载公司和部门的混合树
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void findItemCatalogAndItemTreeToJson() {
		try {
			loadOrg(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadOrg(String nodeId, String nodeTreeType) {
		try {
			List<OrgUnit> orgUnitList = null;
			List<ItemCatalog> itemCatalogList = null;
			if (StringUtils.isNotEmpty(nodeId) && !"0".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					if (nodeTreeType.equals("C")) {
						orgUnitList = barCodeManagementService.findOrgAndUnitTreeList(nodeTreeType, nodeId);
					}
				}
			} else {
				// id为空 则类型也为空
				Map<String, Object> params = getParams();
				itemCatalogList = barCodeManagementService.findAllSubEntity(ItemCatalog.class, "parentItemCatalog.id", null, params);
			}

			if (orgUnitList == null) {
				orgUnitList = new LinkedList<OrgUnit>();
			}

			if (itemCatalogList != null) {
				for (ItemCatalog itemCatalog : itemCatalogList) {
					OrgUnit ou1 = new OrgUnit(itemCatalog.getId(), "C", itemCatalog.getName(), itemCatalog.getCode());
					if (itemCatalog.getSubItemCatalogs().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (ItemCatalog childItemCatalog : itemCatalog.getSubItemCatalogs()) {
							ou2List.add(new OrgUnit(childItemCatalog.getId(), "C", childItemCatalog.getName(), childItemCatalog.getCode()));
						}
						ou1.setSubOrgUnits(ou2List);
					}
					if (itemCatalog.getItems().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (Item item : itemCatalog.getItems()) {
							OrgUnit ou2 = new OrgUnit(item.getId(), "I", item.getName(), item.getCode());
							ou2List.add(ou2);
						}
						ou1.setSubOrgUnits(ou2List);
					}
					orgUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				OrgUnit org = orgUnitList.get(i);
				if (org.getSubOrgUnits() != null && org.getSubOrgUnits().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",code:\"");
					strSb.append(org.getOrgCode());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",code:\"");
					strSb.append(org.getOrgCode());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getBillTypeCode() {
		return billTypeCode;
	}

	public void setBillTypeCode(String billTypeCode) {
		this.billTypeCode = billTypeCode;
	}

	public EncodingRulesTableInTheMiddle getEncodingRulesTableInTheMiddle() {
		return encodingRulesTableInTheMiddle;
	}

	public void setEncodingRulesTableInTheMiddle(EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle) {
		this.encodingRulesTableInTheMiddle = encodingRulesTableInTheMiddle;
	}

}
