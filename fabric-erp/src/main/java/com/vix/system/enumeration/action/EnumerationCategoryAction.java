package com.vix.system.enumeration.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.system.enumeration.entity.EnumerationCategory;

/**
 * 字典分类
 */
@Controller
@Scope("prototype")
public class EnumerationCategoryAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private EnumerationCategory enumerationCategory;
	private String parentId;

	/** 获取列表数据 */
	@SuppressWarnings("unchecked")
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			if (null != parentId) {
				params.put("parentEnumerationCategory.id," + SearchCondition.EQUAL, parentId);
			} else {
				params.put("parentEnumerationCategory.id," + SearchCondition.IS, null);
			}
			getPager().setPageSize(20);
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), EnumerationCategory.class, params);
			if (pager.getResultList().size() < 20) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 20 - listSize; i++) {
					pager.getResultList().add(new EnumerationCategory());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				enumerationCategory = baseHibernateService.findEntityById(EnumerationCategory.class, id);
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
			if (null == enumerationCategory.getParentEnumerationCategory() || null == enumerationCategory.getParentEnumerationCategory().getId() || !enumerationCategory.getParentEnumerationCategory().getId().equals("") || !enumerationCategory.getParentEnumerationCategory().getId().equals("0")) {
				enumerationCategory.setParentEnumerationCategory(null);
			}
			enumerationCategory = baseHibernateService.merge(enumerationCategory);
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
			EnumerationCategory pb = baseHibernateService.findEntityById(EnumerationCategory.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
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
			if (null != id && !"".equals(id)) {
				listEnumerationCategory = baseHibernateService.findAllSubEntity(EnumerationCategory.class, "parentEnumerationCategory.id", id, params);
			} else {
				listEnumerationCategory = baseHibernateService.findAllSubEntity(EnumerationCategory.class, "parentEnumerationCategory.id", null, params);
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
				strSb.append("{id:");
				strSb.append(pc.getId());
				strSb.append(",name:\"");
				strSb.append(pc.getName());
				strSb.append("\",open:false,isParent:false}");
			}
			if (i < listEnumerationCategory.size() - 1) {
				strSb.append(",");
			}
		}
		return strSb;
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

	public EnumerationCategory getEnumerationCategory() {
		return enumerationCategory;
	}

	public void setEnumerationCategory(EnumerationCategory enumerationCategory) {
		this.enumerationCategory = enumerationCategory;
	}

}