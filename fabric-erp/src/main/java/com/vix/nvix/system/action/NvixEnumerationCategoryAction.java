/**
 * 
 */
package com.vix.nvix.system.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.system.enumeration.entity.EnumerationCategory;

/**
 * @author Bluesnow 2016年8月26日
 * 
 *         引用字典分类管理
 */
@Controller
@Scope("prototype")
public class NvixEnumerationCategoryAction extends VixntBaseAction {

	private static final long serialVersionUID = 7321487781016074739L;

	private String id;
	private EnumerationCategory enumerationCategory;
	private String parentId;
	private String searchName;

	/** 获取列表数据 */
	@SuppressWarnings("unchecked")
	public void getEnumerationCategoryJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (StrUtils.isNotEmpty(parentId)) {
				params.put("parentEnumerationCategory.id," + SearchCondition.EQUAL, parentId);
			} else {
				params.put("parentEnumerationCategory.id," + SearchCondition.IS, null);
			}
			if (StrUtils.isNotEmpty(searchName)) {
				params.clear();
				params.put("name," + SearchCondition.ANYLIKE, URLDecoder.decode(searchName.trim(), "UTF-8"));
				params.put("parentEnumerationCategory.name," + SearchCondition.ANYLIKE, URLDecoder.decode(searchName.trim(), "UTF-8"));
			}
			vixntBaseService.findPagerByHqlConditions(pager, EnumerationCategory.class, params);
			if (pager.getResultList().size() < 10) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 10 - listSize; i++) {
					pager.getResultList().add(new EnumerationCategory());
				}
			}
			String[] excludes = {""};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StrUtils.isNotEmpty(id)) {
				enumerationCategory = vixntBaseService.findEntityById(EnumerationCategory.class, id);
			} else {
				if (StrUtils.isNotEmpty(parentId)) {
					enumerationCategory = new EnumerationCategory();
					enumerationCategory.setParentEnumerationCategory(vixntBaseService.findEntityById(EnumerationCategory.class, parentId));
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
			if (null != enumerationCategory.getId()) {
				isSave = false;
				enumerationCategory.setUpdateTime(new Date());
			} else {
				enumerationCategory.setCreateTime(new Date());
				loadCommonData(enumerationCategory);// 载入数据公司码
			}
			String[] attrArrays = {"parentEnumerationCategory"};
			checkEntityNullValue(enumerationCategory, attrArrays);
			enumerationCategory = vixntBaseService.merge(enumerationCategory);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			EnumerationCategory pb = vixntBaseService.findEntityById(EnumerationCategory.class, id);
			if (null != pb) {
				vixntBaseService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("system_enumerationCategoryNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<EnumerationCategory> listEnumerationCategory = new ArrayList<EnumerationCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StrUtils.isNotEmpty(parentId)) {
				listEnumerationCategory = vixntBaseService.findAllSubEntity(EnumerationCategory.class, "parentEnumerationCategory.id", parentId, params);
			} else {
				listEnumerationCategory = vixntBaseService.findAllSubEntity(EnumerationCategory.class, "parentEnumerationCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllEnumerationCategory(strSb, listEnumerationCategory);
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuilder loadAllEnumerationCategory(StringBuilder strSb, List<EnumerationCategory> listEnumerationCategory) throws Exception {
		for (int i = 0; i < listEnumerationCategory.size(); i++) {
			EnumerationCategory pc = listEnumerationCategory.get(i);
			if (pc.getSubEnumerationCategorys().size() > 0) {
				strSb.append("{id:\"");
				strSb.append(pc.getId());
				strSb.append("\",name:\"");
				strSb.append(pc.getName());
				strSb.append("\",open:true,isParent:true,children:[");
				loadAllEnumerationCategory(strSb, new ArrayList<EnumerationCategory>(pc.getSubEnumerationCategorys()));
				strSb.append("]}");
			} else {
				strSb.append("{id:\"");
				strSb.append(pc.getId());
				strSb.append("\",name:\"");
				strSb.append(pc.getName());
				strSb.append("\",open:false,isParent:false}");
			}
			if (i < listEnumerationCategory.size() - 1) {
				strSb.append(",");
			}
		}
		return strSb;
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

	public EnumerationCategory getEnumerationCategory() {
		return enumerationCategory;
	}

	public void setEnumerationCategory(EnumerationCategory enumerationCategory) {
		this.enumerationCategory = enumerationCategory;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
}