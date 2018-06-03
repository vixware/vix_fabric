package com.vix.system.orginialBillTypeManagement.action;

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
import com.vix.common.orginialMeta.entity.OrginialBillsCategory;
import com.vix.common.orginialMeta.entity.OrginialBillsProperty;
import com.vix.common.orginialMeta.entity.OrginialBillsType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.system.billTypeManagement.vo.BillTypeUnit;
import com.vix.system.billTypeSet.entity.BillsCategoryDictionary;
import com.vix.system.billTypeSet.entity.BillsPropertyDictionary;
import com.vix.system.orginialBillTypeManagement.controller.OrginialBillTypeController;
import com.vix.system.orginialBillTypeManagement.service.IOrginialBillTypeService;

@Controller
@Scope("prototype")
public class OrginialBillTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private OrginialBillTypeController orginialBillTypeController;
	@Autowired
	private IOrginialBillTypeService orginialBillTypeService;

	private String id;
	private String ids;
	private String parentId;
	private String pageNo;
	private String type;
	private String treeType;
	private OrginialBillsType orginialBillsType;
	private List<OrginialBillsType> orginialBillsTypeList;

	private OrginialBillsCategory orginialBillsCategory;
	private List<OrginialBillsCategory> orginialBillsCategoryList;

	private String billsPropertyId;
	private String billsCategoryDictionaryCategoryCode;

	/**
	 * 单据分类字典表
	 */
	private List<BillsCategoryDictionary> billsCategoryDictionaryList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			orginialBillsTypeList = orginialBillTypeController.doListOrginialBillsTypeList();
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
				params.put("orginialBillsProperty.id," + SearchCondition.EQUAL, id);
			}
			Pager pager = orginialBillTypeController.doListOrginialBillsTypePager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				orginialBillsType = orginialBillTypeController.doListEntityById(id);
			} else {
				if (null != billsPropertyId && !"".equals(billsPropertyId)) {
					OrginialBillsProperty orginialBillsProperty = orginialBillTypeController.doListOrginialBillsPropertyById(billsPropertyId);
					if (null != orginialBillsProperty) {
						orginialBillsType = new OrginialBillsType();
						orginialBillsType.setOrginialBillsProperty(orginialBillsProperty);
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
			billsCategoryDictionaryList = orginialBillTypeController.doListbillsCategoryDictionary();
			if (null != id && !"".equals(id)) {
				orginialBillsCategory = orginialBillTypeController.doListBillsCategoryById(id);
			} else {
				orginialBillsCategory = new OrginialBillsCategory();
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
			if (null != orginialBillsType.getId()) {
				isSave = false;
			}
			if (null != billsPropertyId && !"".equals(billsPropertyId) && !"undefined".equals(billsPropertyId)) {
				OrginialBillsProperty orginialBillsProperty = orginialBillTypeController.doListOrginialBillsPropertyById(billsPropertyId);
				orginialBillsType.setOrginialBillsProperty(orginialBillsProperty);
			} else {
				orginialBillsType.setOrginialBillsProperty(null);
			}
			orginialBillTypeController.doSaveOrginialBillsType(orginialBillsType);
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
			if (null != orginialBillsCategory.getId()) {
				isSave = false;
			}
			BillsCategoryDictionary billsCategoryDictionary = orginialBillTypeController.doListBillsCategoryDictionaryByCode(orginialBillsCategory.getCategoryCode());
			orginialBillsCategory.setCategoryName(billsCategoryDictionary.getCategoryName());
			orginialBillsCategory = orginialBillTypeController.doSaveOrginialBillsCategory(orginialBillsCategory);
			//更改数据字典的状态
			billsCategoryDictionary.setStatus("2");
			billsCategoryDictionary = orginialBillTypeController.doSaveBillsCategoryDictionary(billsCategoryDictionary);
			if (billsCategoryDictionary != null) {
				for (BillsPropertyDictionary billsPropertyDictionary : billsCategoryDictionary.getBillsPropertyDictionarys()) {
					OrginialBillsProperty orginialBillsProperty = new OrginialBillsProperty();
					orginialBillsProperty.setPropertyCode(billsPropertyDictionary.getPropertyCode());
					orginialBillsProperty.setPropertyName(billsPropertyDictionary.getPropertyName());
					orginialBillsProperty.setOrginialBillsCategory(orginialBillsCategory);
					orginialBillTypeController.doSaveOrginialBillsProperty(orginialBillsProperty);
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
			if (null != billsCategoryDictionaryCategoryCode && !"".equals(billsCategoryDictionaryCategoryCode)) {
				BillsCategoryDictionary billsCategoryDictionary = orginialBillTypeController.doListBillsCategoryDictionaryByCode(billsCategoryDictionaryCategoryCode);
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
			List<OrginialBillsCategory> orgList = null;
			if (null != nodeId && !"".equals(nodeId) && !"-1".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					if (nodeTreeType.equals("B") || nodeTreeType.equals("X")) {
						// 加载公司信息和分类信息
						orgUnitList = orginialBillTypeService.findOrgAndUnitTreeList(nodeTreeType, nodeId);
					}
				}
			} else {
				orgList = orginialBillTypeService.findAllByEntityClass(OrginialBillsCategory.class);
			}
			if (orgUnitList == null) {
				orgUnitList = new LinkedList<BillTypeUnit>();
			}
			if (orgList != null) {
				for (OrginialBillsCategory orgTmp : orgList) {
					BillTypeUnit ou1 = new BillTypeUnit(orgTmp.getId(), "B", orgTmp.getCategoryName(), orgTmp.getCategoryCode());
					if (orgTmp.getOrginialBillsPropertys() != null && orgTmp.getOrginialBillsPropertys().size() > 0) {
						List<BillTypeUnit> ou2List = new LinkedList<BillTypeUnit>();
						for (OrginialBillsProperty childOrg : orgTmp.getOrginialBillsPropertys()) {
							ou2List.add(new BillTypeUnit(childOrg.getId(), "X", childOrg.getPropertyName(), childOrg.getPropertyCode()));
						}
						ou1.setSubBillTypeUnits(ou2List);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public OrginialBillsType getOrginialBillsType() {
		return orginialBillsType;
	}

	public void setOrginialBillsType(OrginialBillsType orginialBillsType) {
		this.orginialBillsType = orginialBillsType;
	}

	public List<OrginialBillsType> getOrginialBillsTypeList() {
		return orginialBillsTypeList;
	}

	public void setOrginialBillsTypeList(List<OrginialBillsType> orginialBillsTypeList) {
		this.orginialBillsTypeList = orginialBillsTypeList;
	}

	public OrginialBillsCategory getOrginialBillsCategory() {
		return orginialBillsCategory;
	}

	public void setOrginialBillsCategory(OrginialBillsCategory orginialBillsCategory) {
		this.orginialBillsCategory = orginialBillsCategory;
	}

	public String getBillsPropertyId() {
		return billsPropertyId;
	}

	public void setBillsPropertyId(String billsPropertyId) {
		this.billsPropertyId = billsPropertyId;
	}

	public String getBillsCategoryDictionaryCategoryCode() {
		return billsCategoryDictionaryCategoryCode;
	}

	public void setBillsCategoryDictionaryCategoryCode(String billsCategoryDictionaryCategoryCode) {
		this.billsCategoryDictionaryCategoryCode = billsCategoryDictionaryCategoryCode;
	}

	public List<OrginialBillsCategory> getOrginialBillsCategoryList() {
		return orginialBillsCategoryList;
	}

	public void setOrginialBillsCategoryList(List<OrginialBillsCategory> orginialBillsCategoryList) {
		this.orginialBillsCategoryList = orginialBillsCategoryList;
	}

	public List<BillsCategoryDictionary> getBillsCategoryDictionaryList() {
		return billsCategoryDictionaryList;
	}

	public void setBillsCategoryDictionaryList(List<BillsCategoryDictionary> billsCategoryDictionaryList) {
		this.billsCategoryDictionaryList = billsCategoryDictionaryList;
	}

}
