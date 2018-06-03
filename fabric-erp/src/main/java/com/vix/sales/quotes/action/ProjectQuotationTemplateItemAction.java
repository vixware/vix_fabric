package com.vix.sales.quotes.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.core.constant.SearchCondition;
import com.vix.sales.quotes.entity.ProjectQuotationTemplate;
import com.vix.sales.quotes.entity.ProjectQuotationTemplateItem;

/**
 * @author Bluesnow
 * 2015年12月30日
 */
@Controller
@Scope("prototype")
public class ProjectQuotationTemplateItemAction extends BaseAction {

	private static final long serialVersionUID = 1L;


	private String id;
	private String projectQuotationTemplateId;
	private ProjectQuotationTemplateItem projectQuotationTemplateItem;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			String projectQuotationTemplateId = getRequestParameter("projectQuotationTemplateId");
			if (null != projectQuotationTemplateId && !"".equals(projectQuotationTemplateId)) {
				params.put("projectQuotationTemplate.id," + SearchCondition.EQUAL,projectQuotationTemplateId);
				baseHibernateService.findPagerByHqlConditions(getPager(), ProjectQuotationTemplateItem.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	/** 跳转至用户修改页面 */
	private List<CurrencyType> currencyTypeList;//币种管理
	private List<MeasureUnitGroup> measureUnitGroupList;
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name," + SearchCondition.NOEQUAL, "");
			measureUnitGroupList = baseHibernateService.findAllByConditions(MeasureUnitGroup.class, params);
			if (null != id && !"".equals(id)) {
				projectQuotationTemplateItem = baseHibernateService.findEntityById(ProjectQuotationTemplateItem.class, id);
			} else {
				projectQuotationTemplateItem = new ProjectQuotationTemplateItem();
				projectQuotationTemplateItem.setIsTemp("1");
				projectQuotationTemplateItem.setCreateTime(new Date());
				loadCommonData(projectQuotationTemplateItem);
				projectQuotationTemplateItem = baseHibernateService.merge(projectQuotationTemplateItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	private String measureUnitGroupId;
	private String type;
	private List<MeasureUnit> measureUnitList;
	public String loadMeasureUnit() {
		try {
			if(StringUtils.isNotEmpty(measureUnitGroupId)){
				measureUnitList = baseHibernateService.findAllByEntityClassAndAttribute(MeasureUnit.class, "measureUnitGroup.id",measureUnitGroupId);
			}
			if (null != id && !id.equals("") && !id.equals("0")) {
				projectQuotationTemplateItem = baseHibernateService.findEntityById(ProjectQuotationTemplateItem.class, id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return type;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != projectQuotationTemplateItem.getId() && !"".equals(projectQuotationTemplateItem.getId())){
				isSave = false;
			} else {
				projectQuotationTemplateItem.setCreateTime(new Date());
				loadCommonData(projectQuotationTemplateItem);
			}
			
			String [] attrArray = {"parentProjectQuotationTemplateItem","projectQuotationTemplate","item","measureUnitGroup","measureUnit","assitMeasureUnit"};
			checkEntityNullValue(projectQuotationTemplateItem, attrArray);
			
			projectQuotationTemplateItem.setIsTemp("0");
			baseHibernateService.merge(projectQuotationTemplateItem);
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
			ProjectQuotationTemplateItem p = baseHibernateService
					.findEntityById(ProjectQuotationTemplateItem.class, id);
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

	public String goChooseQuotationTemplateItem() {
		return "goChooseQuotationTemplateItem";
	}

	/** 订单子项价格汇总 */
	public void getProjectQuotationTemplateItemItemTotal() {
		try {
			if (null != id && !"".equals(id)) {
				ProjectQuotationTemplate sq = baseHibernateService.findEntityById(ProjectQuotationTemplate.class, id);
				if (null != sq) {
					Double totalAmount = 0d;
					for (ProjectQuotationTemplateItem sqi : sq.getProjectQuotationTemplateItems()) {
						totalAmount += sqi.getPrice() * sqi.getAmount();
					}
					DecimalFormat df = new DecimalFormat(".##");
					String st = df.format(totalAmount);
					renderJson(st);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<ProjectQuotationTemplateItem> listProjectQuotationTemplateItem = new ArrayList<ProjectQuotationTemplateItem>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (null != projectQuotationTemplateId && !"".equals(projectQuotationTemplateId)) {
				params.put("projectQuotationTemplate.id," + SearchCondition.EQUAL, projectQuotationTemplateId);
			} else {
				params.put("projectQuotationTemplate.id,"+ SearchCondition.EQUAL, null);
			}
			if (null != id && !"".equals(id)) {
				listProjectQuotationTemplateItem = baseHibernateService.findAllSubEntity(ProjectQuotationTemplateItem.class,
								"parentProjectQuotationTemplateItem.id", id,params);
			} else {
				listProjectQuotationTemplateItem = baseHibernateService
						.findAllSubEntity(ProjectQuotationTemplateItem.class, "parentProjectQuotationTemplateItem.id",null,params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllProjectQuotationTemplateItem(strSb,listProjectQuotationTemplateItem);
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuilder loadAllProjectQuotationTemplateItem(StringBuilder strSb, List<ProjectQuotationTemplateItem> listProjectQuotationTemplateItem) throws Exception {
		for (int i = 0; i < listProjectQuotationTemplateItem.size(); i++) {
			ProjectQuotationTemplateItem pqsi = listProjectQuotationTemplateItem.get(i);
			if (pqsi.getSubProjectQuotationTemplateItems().size() > 0) {
				strSb.append("{id:\"");
				strSb.append(pqsi.getId());
				strSb.append("\",name:\"");
				if (null != pqsi.getItem() && null != pqsi.getItem().getName()) {
					strSb.append(pqsi.getItem().getName());
				} else {
					strSb.append(pqsi.getName());
				}
				strSb.append("\",open:true,isParent:true,children:[");
				loadAllProjectQuotationTemplateItem(strSb,
						new ArrayList<ProjectQuotationTemplateItem>(pqsi.getSubProjectQuotationTemplateItems()));
				strSb.append("]}");
			} else {
				strSb.append("{id:\"");
				strSb.append(pqsi.getId());
				strSb.append("\",name:\"");
				if (null != pqsi.getItem() && null != pqsi.getItem().getName()) {
					strSb.append(pqsi.getItem().getName());
				} else {
					strSb.append(pqsi.getName());
				}
				strSb.append("\",open:false,isParent:false}");
			}
			if (i < listProjectQuotationTemplateItem.size() - 1) {
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


	public String getProjectQuotationTemplateId() {
		return projectQuotationTemplateId;
	}

	public void setProjectQuotationTemplateId(String projectQuotationTemplateId) {
		this.projectQuotationTemplateId = projectQuotationTemplateId;
	}

	public ProjectQuotationTemplateItem getProjectQuotationTemplateItem() {
		return projectQuotationTemplateItem;
	}

	public void setProjectQuotationTemplateItem(
			ProjectQuotationTemplateItem projectQuotationTemplateItem) {
		this.projectQuotationTemplateItem = projectQuotationTemplateItem;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public List<MeasureUnitGroup> getMeasureUnitGroupList() {
		return measureUnitGroupList;
	}

	public void setMeasureUnitGroupList(List<MeasureUnitGroup> measureUnitGroupList) {
		this.measureUnitGroupList = measureUnitGroupList;
	}

	public String getMeasureUnitGroupId() {
		return measureUnitGroupId;
	}

	public void setMeasureUnitGroupId(String measureUnitGroupId) {
		this.measureUnitGroupId = measureUnitGroupId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<MeasureUnit> getMeasureUnitList() {
		return measureUnitList;
	}

	public void setMeasureUnitList(List<MeasureUnit> measureUnitList) {
		this.measureUnitList = measureUnitList;
	}
	
	
}