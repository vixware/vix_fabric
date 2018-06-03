package com.vix.sales.quotes.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.sales.quotes.entity.SalesQuotationCategory;

@Controller
@Scope("prototype")
public class SalesQuotationCategoryAction extends BaseAction {

	private static final long serialVersionUID = 1L;


	private String id;
	private SalesQuotationCategory salesQuotationCategory;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String parentId = getRequestParameter("parentId");
			if (null != parentId && !"".equals(parentId)) {
				params.put("parentSalesQuotationCategory.id,"
						+ SearchCondition.EQUAL, Long.parseLong(parentId));
			}
			String name = getRequestParameter("name");
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			Pager pager = baseHibernateService
					.findPagerByHqlConditions(getPager(),
							SalesQuotationCategory.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id) && !"0".equals(id)) {
				salesQuotationCategory = baseHibernateService.findEntityById(SalesQuotationCategory.class, id);
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
			if (StrUtils.objectIsNotNull(salesQuotationCategory.getId()) ) {
				isSave = false;
				salesQuotationCategory.setUpdateTime(new Date());
			} else {
				salesQuotationCategory.setCreateTime(new Date());
				loadCommonData(salesQuotationCategory);// 载入数据公司码
			}
			
			String[] attrArray = {"parentSalesQuotationCategory"};
			checkEntityNullValue(salesQuotationCategory, attrArray);
			
			baseHibernateService.merge(salesQuotationCategory);
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
			SalesQuotationCategory p = baseHibernateService
					.findEntityById(SalesQuotationCategory.class, id);
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

	/** 选择分类 */
	public String goChooseSalesQuotationCategory() {
		return "goChooseSalesQuotationCategory";
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<SalesQuotationCategory> listSalesQuotationCategory = new ArrayList<SalesQuotationCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listSalesQuotationCategory = baseHibernateService
						.findAllSubEntity(SalesQuotationCategory.class,
								"parentSalesQuotationCategory.id", id, params);
			} else {
				listSalesQuotationCategory = baseHibernateService
						.findAllSubEntity(SalesQuotationCategory.class,
								"parentSalesQuotationCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listSalesQuotationCategory.size(); i++) {
				SalesQuotationCategory cc = listSalesQuotationCategory.get(i);
				if (cc.getSubSalesQuotationCategorys().size() > 0) {
					strSb.append("{id:");
					strSb.append(cc.getId());
					strSb.append(",name:\"");
					strSb.append(cc.getName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:");
					strSb.append(cc.getId());
					strSb.append(",name:\"");
					strSb.append(cc.getName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < listSalesQuotationCategory.size() - 1) {
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

	public SalesQuotationCategory getSalesQuotationCategory() {
		return salesQuotationCategory;
	}

	public void setSalesQuotationCategory(
			SalesQuotationCategory salesQuotationCategory) {
		this.salesQuotationCategory = salesQuotationCategory;
	}
}