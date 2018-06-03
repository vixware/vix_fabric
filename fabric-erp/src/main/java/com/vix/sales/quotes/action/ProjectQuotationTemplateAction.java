package com.vix.sales.quotes.action;

import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.sales.quotes.entity.ProjectQuotationTemplate;
import com.vix.sales.quotes.entity.ProjectQuotationTemplateItem;

@Controller
@Scope("prototype")
public class ProjectQuotationTemplateAction extends BaseAction {

	private static final long serialVersionUID = 1L;


	private String id;
	private String ids;
	private ProjectQuotationTemplate projectQuotationTemplate;
	private ProjectQuotationTemplateItem projectQuotationTemplateItem;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			String quoteSubject = getRequestParameter("quoteSubject");
			if (null != quoteSubject && !"".equals(quoteSubject)) {
				quoteSubject = decode(quoteSubject, "UTF-8");
				params.put("quoteSubject," + SearchCondition.ANYLIKE,
						quoteSubject);
			}
			Pager pager = baseHibernateService
					.findPagerByHqlConditions(getPager(),
							ProjectQuotationTemplate.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	/** 获取列表数据 */
	public String goSubListContent() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			String quoteSubject = getRequestParameter("quoteSubject");
			if (null != quoteSubject && !"".equals(quoteSubject)) {
				quoteSubject = decode(quoteSubject, "UTF-8");
				params.put("quoteSubject," + SearchCondition.ANYLIKE,
						quoteSubject);
			}
			getPager().setPageSize(6);
			Pager pager = baseHibernateService
					.findPagerByHqlConditions(getPager(),
							ProjectQuotationTemplate.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubListContent";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				projectQuotationTemplate = baseHibernateService.findEntityById(ProjectQuotationTemplate.class, id);
			} else {
				projectQuotationTemplate = new ProjectQuotationTemplate();
				projectQuotationTemplate.setIsTemp("1");
				projectQuotationTemplate.setCreateTime(new Date());
				loadCommonData(projectQuotationTemplate);
				projectQuotationTemplate = baseHibernateService
						.merge(projectQuotationTemplate);
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
			if (null != projectQuotationTemplate.getId()) {
				isSave = false;
			} else {
				projectQuotationTemplate.setCreateTime(new Date());
				loadCommonData(projectQuotationTemplate);
			}
			projectQuotationTemplate.setIsTemp("");
			baseHibernateService.merge(projectQuotationTemplate);
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
			ProjectQuotationTemplate p = baseHibernateService
					.findEntityById(ProjectQuotationTemplate.class, id);
			if (null != p) {
				baseHibernateService.deleteByEntity(p);
				renderText(DELETE_SUCCESS);
			} else {
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goChooseProjectQuotationTemplate() {
		return "goChooseProjectQuotationTemplate";
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

	public ProjectQuotationTemplate getProjectQuotationTemplate() {
		return projectQuotationTemplate;
	}

	public void setProjectQuotationTemplate(
			ProjectQuotationTemplate projectQuotationTemplate) {
		this.projectQuotationTemplate = projectQuotationTemplate;
	}

	public ProjectQuotationTemplateItem getProjectQuotationTemplateItem() {
		return projectQuotationTemplateItem;
	}

	public void setProjectQuotationTemplateItem(
			ProjectQuotationTemplateItem projectQuotationTemplateItem) {
		this.projectQuotationTemplateItem = projectQuotationTemplateItem;
	}
}