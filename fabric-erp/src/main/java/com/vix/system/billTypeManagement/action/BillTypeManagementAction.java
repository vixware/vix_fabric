package com.vix.system.billTypeManagement.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.system.billTypeManagement.controller.BillTypeManagementController;
import com.vix.system.billTypeManagement.entity.BillsCategory;
import com.vix.system.billTypeManagement.entity.BillsProperty;
import com.vix.system.billTypeManagement.entity.BillsType;
import com.vix.system.billTypeManagement.service.IBillTypeManagementService;
import com.vix.system.billTypeManagement.vo.BillTypeCodeParameter;
import com.vix.system.billTypeManagement.vo.BillTypeCodeParameterList;
import com.vix.system.billTypeManagement.vo.BillTypeUnit;
import com.vix.system.billTypeSet.entity.BillsCategoryDictionary;
import com.vix.system.billTypeSet.entity.BillsPropertyDictionary;

@Controller
@Scope("prototype")
public class BillTypeManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private BillTypeManagementController billTypeManagementController;

	private String id;
	private String ids;
	private String parentId;
	private String pageNo;
	private String type;
	private String treeType;
	private BillsType billsType;
	private List<BillsType> billsTypeList;
	private BillsCategory billsCategory;
	private List<BillsCategory> billsCategoryList;
	private List<BillTypeCodeParameter> billsTypeCodeList;

	/**
	 * 单据分类字典表
	 */
	private List<BillsCategoryDictionary> billsCategoryDictionaryList;
	@Autowired
	private IBillTypeManagementService billTypeManagementService;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			billsTypeList = billTypeManagementController.doListBillsTypeIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (null == id) {
			} else {
				params.put("billsProperty.id," + SearchCondition.EQUAL, id);
			}
			Pager pager = billTypeManagementController.doListBillsType(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		billsTypeCodeList = BillTypeCodeParameterList.getBillTypeCodeParameterList();
		try {
			if (null != id && !"".equals(id) && !"0".equals(id)) {
				billsType = billTypeManagementController.doListEntityById(id);
			} else {
				String billsPropertyId = getRequestParameter("billsPropertyId");
				if (null != billsPropertyId && !"".equals(billsPropertyId)) {
					BillsProperty billsProperty = billTypeManagementController.doListBillsPropertyById(billsPropertyId);
					if (null != billsProperty) {
						billsType = new BillsType();
						billsType.setBillsProperty(billsProperty);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateBillType";
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public String goSaveOrUpdateBillsCategory() {
		try {
			billsCategoryDictionaryList = billTypeManagementController.doListbillsCategoryDictionary();
			if (null != id && !"".equals(id)) {
				billsCategory = billTypeManagementController.doListBillsCategoryById(id);
			} else {
				String parentId = getRequestParameter("parentId");
				if (null != parentId && !"".equals(parentId)) {
					Organization organization = billTypeManagementController.doListOrganizationById(parentId);
					if (null != organization) {
						billsCategory = new BillsCategory();
						if (billsCategoryDictionaryList != null && billsCategoryDictionaryList.isEmpty()) {
							for (BillsCategoryDictionary billsCategoryDictionary : billsCategoryDictionaryList) {
								BillsCategory bCategory = billTypeManagementController.dolistBillsCategoryByCompanyCodeAndCategoryCode(organization.getCompanyCode(), billsCategoryDictionary.getCategoryCode());
								if (bCategory != null) {
									billsCategoryDictionaryList.remove(billsCategoryDictionary);
								}
							}
						}
						billsCategory.setCompanyCode(organization.getCompanyCode());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateBillsCategory";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			String billsPropertyId = getRequestParameter("billsPropertyId");
			if (null != billsType.getId()) {
				isSave = false;
			}

			if (null != billsPropertyId && !"".equals(billsPropertyId) && !"undefined".equals(billsPropertyId)) {
				BillsProperty billsProperty = billTypeManagementController.doListBillsPropertyById(billsPropertyId);
				billsType.setBillsProperty(billsProperty);
			} else {
				billsType.setBillsProperty(null);
			}
			billTypeManagementController.doSaveBillsType(billsType);
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

	/** 新增分类 */
	public String saveOrUpdateBillsCategory() {
		boolean isSave = true;
		try {
			if (null != billsCategory.getId()) {
				isSave = false;
			}
			BillsCategoryDictionary billsCategoryDictionary = billTypeManagementController.doListBillsCategoryDictionaryByCode(billsCategory.getCategoryCode());
			billsCategory.setCategoryName(billsCategoryDictionary.getCategoryName());
			billTypeManagementController.doSavebillsCategory(billsCategory);
			if (billsCategoryDictionary != null) {
				for (BillsPropertyDictionary billsPropertyDictionary : billsCategoryDictionary.getBillsPropertyDictionarys()) {
					BillsProperty billsProperty = new BillsProperty();
					billsProperty.setPropertyCode(billsPropertyDictionary.getPropertyCode());
					billsProperty.setPropertyName(billsPropertyDictionary.getPropertyName());
					billsProperty.setPropertyDescription(billsPropertyDictionary.getPropertyDescription());
					billsProperty.setBillsCategory(billsCategory);
					billTypeManagementController.doSaveBillsProperty(billsProperty);
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

	public void getBillsPropertyDictionaryJson() {
		try {
			String json = "";
			String categoryCode = getRequestParameter("billsCategoryDictionaryCategoryCode");
			if (null != categoryCode && !"".equals(categoryCode)) {
				BillsCategoryDictionary billsCategoryDictionary = billTypeManagementController.doListBillsCategoryDictionaryByCode(categoryCode);
				if (billsCategoryDictionary != null) {
					json = convertListToJson(new ArrayList<BillsPropertyDictionary>(billsCategoryDictionary.getBillsPropertyDictionarys()), billsCategoryDictionary.getBillsPropertyDictionarys().size());
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

	/**
	 * 
	 * @Title: findOrgAndUnitTreeToJson
	 * @Description: 加载分类和l的混合树
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void findOrgAndUnitTreeToJson() {
		try {
			loadOrg(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 异步获取树结构
	 * 
	 * @param nodeId
	 * @param nodeTreeType
	 */
	private void loadOrg(String nodeId, String nodeTreeType) {
		try {
			List<BillTypeUnit> orgUnitList = null;
			List<Organization> orgList = null;
			if (null != nodeId && !"".equals(nodeId) && !"-1".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					// 没有根结点 需要加载公司信息 其实id不为空 则treetype肯定也不为空
					if (nodeTreeType.equals("C") || nodeTreeType.equals("B") || nodeTreeType.equals("X")) {
						// 加载公司信息和分类信息
						orgUnitList = billTypeManagementService.findOrgAndUnitTreeList(nodeTreeType, nodeId);
					}
				}
			} else {
				// id为空 则类型也为空
				// 加载公司信息
				Map<String, Object> params = getParams();
				orgList = billTypeManagementService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			if (orgUnitList == null) {
				orgUnitList = new LinkedList<BillTypeUnit>();
			}
			if (orgList != null) {
				for (Organization orgTmp : orgList) {
					BillTypeUnit ou1 = new BillTypeUnit(orgTmp.getId(), "C", orgTmp.getOrgName(), orgTmp.getCompanyInnerCode());
					if (orgTmp.getSubOrganizations() != null && orgTmp.getSubOrganizations().size() > 0) {
						List<BillTypeUnit> ou2List = new LinkedList<BillTypeUnit>();
						for (Organization childOrg : orgTmp.getSubOrganizations()) {
							ou2List.add(new BillTypeUnit(childOrg.getId(), "C", childOrg.getOrgName(), childOrg.getCompanyInnerCode()));
						}
						ou1.setSubBillTypeUnits(ou2List);
					}
					List<BillsCategory> bcList = billTypeManagementService.findBillsCategoryListByOrgId(orgTmp.getId());
					if (bcList != null && bcList.size() > 0) {
						List<BillTypeUnit> billTypeUnitList = new LinkedList<BillTypeUnit>();
						for (BillsCategory billsCategory : bcList) {
							if (billsCategory != null) {
								billTypeUnitList.add(new BillTypeUnit(billsCategory.getId(), "B", billsCategory.getCategoryName(), billsCategory.getCategoryCode()));
							}
						}
						ou1.setSubBillTypeUnits(billTypeUnitList);
					}
					orgUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				BillTypeUnit billTypeUnit = orgUnitList.get(i);
				if (billTypeUnit.getSubBillTypeUnits() != null && billTypeUnit.getSubBillTypeUnits().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(billTypeUnit.getId());
					strSb.append("\",treeType:\"");
					strSb.append(billTypeUnit.getTreeType());
					strSb.append("\",name:\"");
					strSb.append(billTypeUnit.getTreeName());
					strSb.append("\",code:\"");
					strSb.append(billTypeUnit.getTreeCode());
					strSb.append("\",open:true,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(billTypeUnit.getId());
					strSb.append("\",treeType:\"");
					strSb.append(billTypeUnit.getTreeType());
					strSb.append("\",name:\"");
					strSb.append(billTypeUnit.getTreeName());
					strSb.append("\",code:\"");
					strSb.append(billTypeUnit.getTreeCode());
					strSb.append("\",open:true,isParent:false}");
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

	public void findBillTypeTreeToJson() {
		try {
			loadBillType(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取单据类型树
	 * 
	 * @param nodeId
	 * @param nodeTreeType
	 */
	public void loadBillType(String nodeId, String nodeTreeType) {
		try {
			List<BillTypeUnit> orgUnitList = null;
			List<BillsCategory> orgList = null;
			if (null != nodeId && !"".equals(nodeId) && !"-1".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					if (nodeTreeType.equals("B") || nodeTreeType.equals("X")) {
						if (nodeId.substring(0, nodeId.length() - 1) != null) {
							orgUnitList = billTypeManagementService.findBillTypeList(nodeTreeType, nodeId.substring(0, nodeId.length() - 1));
						}
					}
				}
			} else {
				orgList = billTypeManagementService.findAllByEntityClass(BillsCategory.class);
			}
			if (orgUnitList == null) {
				orgUnitList = new LinkedList<BillTypeUnit>();
			}
			if (orgList != null) {
				for (BillsCategory billsCategory : orgList) {
					BillTypeUnit ou1 = new BillTypeUnit(billsCategory.getId(), "B", billsCategory.getCategoryName(), billsCategory.getCategoryCode());
					List<BillsProperty> bcList = billTypeManagementService.findBillsPropertyList(billsCategory.getId());
					if (bcList != null && bcList.size() > 0) {
						List<BillTypeUnit> billTypeUnitList = new LinkedList<BillTypeUnit>();
						for (BillsProperty billsProperty : bcList) {
							if (billsProperty != null) {
								billTypeUnitList.add(new BillTypeUnit(billsProperty.getId(), "X", billsProperty.getPropertyName(), billsProperty.getPropertyCode()));
							}
						}
						ou1.setSubBillTypeUnits(billTypeUnitList);
					}
					orgUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				BillTypeUnit billTypeUnit = orgUnitList.get(i);
				if (billTypeUnit.getSubBillTypeUnits() != null && billTypeUnit.getSubBillTypeUnits().size() > 0) {
					strSb.append("{\"id\":\"");
					strSb.append(billTypeUnit.getId() + billTypeUnit.getTreeType());
					strSb.append("\",\"treeType\":\"");
					strSb.append(billTypeUnit.getTreeType());
					strSb.append("\",\"text\":\"");
					strSb.append(billTypeUnit.getTreeName());
					strSb.append("\",\"leaf\":false,\"checked\":false}");
				} else {
					strSb.append("{\"id\":\"");
					strSb.append(billTypeUnit.getId() + billTypeUnit.getTreeType());
					strSb.append("\",\"treeType\":\"");
					strSb.append(billTypeUnit.getTreeType());
					strSb.append("\",\"text\":\"");
					strSb.append(billTypeUnit.getTreeName());
					strSb.append("\",\"leaf\":true,\"checked\":false}");
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public BillsType getBillsType() {
		return billsType;
	}

	public void setBillsType(BillsType billsType) {
		this.billsType = billsType;
	}

	public List<BillsType> getBillsTypeList() {
		return billsTypeList;
	}

	public void setBillsTypeList(List<BillsType> billsTypeList) {
		this.billsTypeList = billsTypeList;
	}

	public BillsCategory getBillsCategory() {
		return billsCategory;
	}

	public void setBillsCategory(BillsCategory billsCategory) {
		this.billsCategory = billsCategory;
	}

	public List<BillsCategory> getBillsCategoryList() {
		return billsCategoryList;
	}

	public void setBillsCategoryList(List<BillsCategory> billsCategoryList) {
		this.billsCategoryList = billsCategoryList;
	}

	public List<BillTypeCodeParameter> getBillsTypeCodeList() {
		return billsTypeCodeList;
	}

	public void setBillsTypeCodeList(List<BillTypeCodeParameter> billsTypeCodeList) {
		this.billsTypeCodeList = billsTypeCodeList;
	}

	public List<BillsCategoryDictionary> getBillsCategoryDictionaryList() {
		return billsCategoryDictionaryList;
	}

	public void setBillsCategoryDictionaryList(List<BillsCategoryDictionary> billsCategoryDictionaryList) {
		this.billsCategoryDictionaryList = billsCategoryDictionaryList;
	}

}
