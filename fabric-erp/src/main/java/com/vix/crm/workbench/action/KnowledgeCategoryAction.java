package com.vix.crm.workbench.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.crm.workbench.entity.KnowledgeCategory;

@Service
@Scope("prototype")
public class KnowledgeCategoryAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id; 
	private String ids;
	private KnowledgeCategory knowledageCategory;
	private String pageNo;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), KnowledgeCategory.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), KnowledgeCategory.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				knowledageCategory = baseHibernateService.findEntityById(
						KnowledgeCategory.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
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
			if (null != knowledageCategory.getId()) {
				isSave = false;
			}else{
				knowledageCategory.setCreateTime(new Date());
				loadCommonData(knowledageCategory);
			}
			if (null == knowledageCategory.getParentKnowledgeCategory()
					|| null == knowledageCategory.getParentKnowledgeCategory().getId()) {
				knowledageCategory.setParentKnowledgeCategory(null);
			}
			knowledageCategory = baseHibernateService.merge(knowledageCategory);
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
			KnowledgeCategory pb = baseHibernateService.findEntityById(
					KnowledgeCategory.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
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
			List<KnowledgeCategory> listKnowledageCategory = new ArrayList<KnowledgeCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listKnowledageCategory = baseHibernateService.findAllSubEntity(
						KnowledgeCategory.class, "parentKnowledageCategory.id", id, params);
			} else {
				listKnowledageCategory = baseHibernateService.findAllSubEntity(
						KnowledgeCategory.class, "parentKnowledageCategory.id", "0", params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllKnowledageCategory(strSb, listKnowledageCategory);
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuilder loadAllKnowledageCategory(StringBuilder strSb,
			List<KnowledgeCategory> listKnowledageCategory) throws Exception {
		for (int i = 0; i < listKnowledageCategory.size(); i++) {
			KnowledgeCategory ic = listKnowledageCategory.get(i);
			if (ic.getSubKnowledgeCategorys().size() > 0) {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:true,isParent:true,children:[");
				loadAllKnowledageCategory(strSb,
						new ArrayList<KnowledgeCategory>(ic.getSubKnowledgeCategorys()));
				strSb.append("]}");
			} else {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:false,isParent:false}");
			}
			if (i < listKnowledageCategory.size() - 1) {
				strSb.append(",");
			}
		}
		return strSb;
	}

	public String goChooseKnowledageCategory() {
		return "goChooseKnowledageCategory";
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public KnowledgeCategory getKnowledageCategory() {
		return knowledageCategory;
	}

	public void setKnowledageCategory(KnowledgeCategory knowledageCategory) {
		this.knowledageCategory = knowledageCategory;
	}
}
