package com.vix.nvix.sales.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.sales.commission.entity.PersonnelCategory;
@Controller
@Scope("prototype")
public class NvixntPersonnelCategoryAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PersonnelCategory personnelCategory;
	private String id;
	private String parentId;
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(parentId)) {
				params.put("parentPersonnelCategory.id,"+SearchCondition.EQUAL, parentId);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, PersonnelCategory.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				personnelCategory = vixntBaseService.findEntityById(PersonnelCategory.class, id);
			}else {
				personnelCategory = new PersonnelCategory();
				personnelCategory.setCode(VixUUID.createCode(12));
				if(StringUtils.isNotEmpty(parentId)) {
					PersonnelCategory parentPersonnelCategory = vixntBaseService.findEntityById(PersonnelCategory.class, parentId);
					personnelCategory.setParentPersonnelCategory(parentPersonnelCategory);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotBlank(personnelCategory.getId())) {
				isSave = false;
			}
			if(StringUtils.isEmpty(personnelCategory.getParentPersonnelCategory().getId()) || personnelCategory.getParentPersonnelCategory() == null) {
				personnelCategory.setParentPersonnelCategory(null);
			}
			initEntityBaseController.initEntityBaseAttribute(personnelCategory);
			personnelCategory = vixntBaseService.merge(personnelCategory);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}
	public void deleteById() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				personnelCategory = vixntBaseService.findEntityById(PersonnelCategory.class, id);
				if(personnelCategory != null) {
					vixntBaseService.deleteByEntity(personnelCategory);
					renderText(DELETE_SUCCESS);
				}else {
					renderText("未找到该分类!");
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
			List<PersonnelCategory> listPersonnelCategory = new ArrayList<PersonnelCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listPersonnelCategory = baseHibernateService.findAllSubEntity(PersonnelCategory.class,"parentPersonnelCategory.id", id, params);
			} else {
				listPersonnelCategory = baseHibernateService.findAllSubEntity(PersonnelCategory.class,"parentPersonnelCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllPersonnelCategory(strSb, listPersonnelCategory);
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuilder loadAllPersonnelCategory(StringBuilder strSb,
			List<PersonnelCategory> listPersonnelCategory) throws Exception {
		for (int i = 0; i < listPersonnelCategory.size(); i++) {
			PersonnelCategory ic = listPersonnelCategory.get(i);
			if (ic.getSubPersonnelCategorys().size() > 0) {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:true,isParent:true,children:[");
				loadAllPersonnelCategory(
						strSb,
						new ArrayList<PersonnelCategory>(ic
								.getSubPersonnelCategorys()));
				strSb.append("]}");
			} else {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:false,isParent:false}");
			}
			if (i < listPersonnelCategory.size() - 1) {
				strSb.append(",");
			}
		}
		return strSb;
	}
	public PersonnelCategory getPersonnelCategory() {
		return personnelCategory;
	}
	public void setPersonnelCategory(PersonnelCategory personnelCategory) {
		this.personnelCategory = personnelCategory;
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
