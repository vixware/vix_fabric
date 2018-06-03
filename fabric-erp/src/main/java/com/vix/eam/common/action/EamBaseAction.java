package com.vix.eam.common.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.eam.entity.EqStructure;

public abstract class EamBaseAction extends BaseAction {
	/** 主键id */
	protected String id;
	/** 分类父ID */
	protected String parentId;
	/** 设备分类id */
	protected String categoryId;


	/**
	 * 树形结构JSON
	 */
	public void findTreeToJson() {
		try {
			List<EqStructure> listCategory = new ArrayList<EqStructure>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			this.addTimeLimitToParam(params);

			if (null != id && !"".equals(id)) {
				params.put("parentId," + SearchCondition.EQUAL, id);
				// listCategory =
				// baseHibernateService.findAllSubEntity(EqCategory.class,
				// "parentId", id, params);
			} else {
				params.put("parentId," + SearchCondition.EQUAL, -1L);
				// listCategory =
				// baseHibernateService.findAllSubEntity(EqCategory.class,
				// "parentId", -1L, params);
			}
			listCategory = baseHibernateService.findAllByConditions(
					EqStructure.class, params);
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listCategory.size(); i++) {
				EqStructure cc = listCategory.get(i);
				if (cc.getSubEqStructure() != null
						&& cc.getSubEqStructure().size() > 0) {
					strSb.append("{id:");
					strSb.append(cc.getId());
					strSb.append(",name:\"");
					strSb.append(cc.getEqName());
					strSb.append("\",eqCode:\"");
					strSb.append(cc.getEqCode());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:");
					strSb.append(cc.getId());
					strSb.append(",name:\"");
					strSb.append(cc.getEqName());
					strSb.append("\",eqCode:\"");
					strSb.append(cc.getEqCode());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < listCategory.size() - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> getParams() {
		return new HashMap<String, Object>();
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}


}
