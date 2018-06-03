package com.vix.nvix.drp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.mdm.srm.share.entity.SupplierCategory;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;
/**
 * 供应商分类管理
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class VixntSupplierCategoryAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String parentId;
	private String id;
	private SupplierCategory supplierCategory;
	private String searchName;
	private String name;
	private String searchCode;
	
	public void getSupplierCategoryJson(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (StrUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, decode(searchName, "UTF-8"));
			}
			if(StringUtils.isNotEmpty(searchCode)){
				params.put("code,"+SearchCondition.ANYLIKE, searchCode);
			}
			if (StringUtils.isNotEmpty(parentId) && !parentId.equals("0")) {
				params.put("supplierCategory.id," + SearchCondition.EQUAL, parentId);
			} else {
				params.put("supplierCategory.id," + SearchCondition.IS, "NULL");
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, SupplierCategory.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), SupplierCategory.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				supplierCategory = vixntBaseService.findEntityById(SupplierCategory.class, id);
			} else {
				if (StringUtils.isNotEmpty(parentId) && !parentId.equals("0")) {
					supplierCategory = new SupplierCategory();
					SupplierCategory category = vixntBaseService.findEntityById(SupplierCategory.class, parentId);
					supplierCategory.setSupplierCategory(category);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != supplierCategory.getId()) {
				isSave = false;
			}
			if (null == supplierCategory.getSupplierCategory()|| null == supplierCategory.getSupplierCategory().getId() || "".equals(supplierCategory.getSupplierCategory().getId())) {
				supplierCategory.setSupplierCategory(null);
			} else {
				supplierCategory.setCode(supplierCategory.getSupplierCategory().getCode());
			}
			supplierCategory = vixntBaseService.merge(supplierCategory);
			EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle = new EncodingRulesTableInTheMiddle();
			initEntityBaseController.initEntityBaseAttribute(encodingRulesTableInTheMiddle);
			encodingRulesTableInTheMiddle.setBillType(supplierCategory.getCode());
			encodingRulesTableInTheMiddle.setCodeLength(10);
			encodingRulesTableInTheMiddle.setEnableSeries(2);
			encodingRulesTableInTheMiddle.setCodeType("C");
			encodingRulesTableInTheMiddle.setSerialNumberBegin(1l);
			encodingRulesTableInTheMiddle.setSequenceID(supplierCategory.getCode());
			encodingRulesTableInTheMiddle.setSerialNumberEnd(999999999L);
			encodingRulesTableInTheMiddle.setSerialNumberStep(1);
			vixntBaseService.merge(encodingRulesTableInTheMiddle);
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
	/** 处理删除操作 */
	public void deleteById() {
		try {
			SupplierCategory pb = vixntBaseService.findEntityById(SupplierCategory.class, id);
			if (null != pb) {
				if (pb.getSupplierCategories() != null && pb.getSupplierCategories().size() > 0) {
					renderText("含有子分类的分类不能删除!");
				} else if (pb.getSuppliers() != null && pb.getSuppliers().size() > 0) {
					renderText("含有供应商的分类不能删除!");
				} else {
					vixntBaseService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<SupplierCategory> listSupplierCategory = new ArrayList<SupplierCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listSupplierCategory = vixntBaseService.findAllSubEntity(SupplierCategory.class, "supplierCategory.id", id, params);
			} else {
				listSupplierCategory = vixntBaseService.findAllSubEntity(SupplierCategory.class, "supplierCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllItemCatalog(strSb, listSupplierCategory);
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuilder loadAllItemCatalog(StringBuilder strSb, List<SupplierCategory> listSupplierCategory) throws Exception {
		for (int i = 0; i < listSupplierCategory.size(); i++) {
			SupplierCategory ic = listSupplierCategory.get(i);
			if (ic.getSupplierCategories().size() > 0) {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:false,isParent:true");
				// loadAllItemCatalog(strSb, new
				// ArrayList<ItemCatalog>(ic.getSubItemCatalogs()));
				strSb.append("}");
			} else {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:false,isParent:false}");
			}
			if (i < listSupplierCategory.size() - 1) {
				strSb.append(",");
			}
		}
		return strSb;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public SupplierCategory getSupplierCategory() {
		return supplierCategory;
	}

	public void setSupplierCategory(SupplierCategory supplierCategory) {
		this.supplierCategory = supplierCategory;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSearchCode() {
		return searchCode;
	}

	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}
}
