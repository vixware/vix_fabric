package com.vix.nvix.sales.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 佣金计划/方案
 * @author jackie
 *
 */
import com.vix.sales.commission.entity.CommissionPlan;
import com.vix.sales.commission.entity.CommissionPlanItem;
import com.vix.sales.common.entity.SalesPerformanceEvaluationMethod;
@Controller
@Scope("prototype")
public class NvixntCommissionPlanAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CommissionPlan commissionPlan;
	private CommissionPlanItem commissionPlanItem;
	private String id;
	private List<SalesPerformanceEvaluationMethod> salesPerformanceEvaluationMethodList;
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			
			pager = vixntBaseService.findPagerByHqlConditions(pager, CommissionPlan.class, params);
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				commissionPlan = vixntBaseService.findEntityById(CommissionPlan.class, id);
			}else {
				commissionPlan = new CommissionPlan();
				commissionPlan.setCode(VixUUID.createCode(12));
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
			if (StringUtils.isNotEmpty(commissionPlan.getId())) {
				isSave = false;
				commissionPlan.setUpdateTime(new Date());
			} else {
				commissionPlan.setCreateTime(new Date());
				loadCommonData(commissionPlan);
			}
			commissionPlan = baseHibernateService.merge(commissionPlan);
			if (isSave) {
				renderText("1:"+SAVE_SUCCESS+":"+commissionPlan.getId());
			} else {
				renderText("1:"+UPDATE_SUCCESS+":"+commissionPlan.getId());
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
			CommissionPlan pb = baseHibernateService.findEntityById(CommissionPlan.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				renderText("该佣金计划未找到!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public void goSingleListCommissionPlanItem() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			
			pager = vixntBaseService.findPagerByHqlConditions(pager, CommissionPlanItem.class, params);
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdateCommissionPlanItem() {
		try {
			salesPerformanceEvaluationMethodList = vixntBaseService.findAllByEntityClass(SalesPerformanceEvaluationMethod.class);
			if(StringUtils.isNotEmpty(id)) {
				commissionPlanItem = vixntBaseService.findEntityById(CommissionPlanItem.class, id);
			}else {
				commissionPlanItem = new CommissionPlanItem();
				commissionPlanItem.setCode(VixUUID.createCode(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateCommissionPlanItem";
	}
	
	public void saveOrUpdateCommissionPlanItem() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(commissionPlanItem.getId())) {
				isSave = false;
				commissionPlanItem.setUpdateTime(new Date());
			}else {
				commissionPlanItem.setCreateTime(new Date());
				loadCommonData(commissionPlanItem);
			}
			commissionPlanItem = vixntBaseService.merge(commissionPlanItem);
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
	public String deleteCommissionPlanItemById() {
		try {
			CommissionPlanItem pb = baseHibernateService.findEntityById(CommissionPlanItem.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	public String goChooseItemCatalog() {
		return "goChooseItemCatalog";
	}
	public void getItemCatalogJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String selectName = getDecodeRequestParameter("selectName");
			if (StrUtils.isNotEmpty(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, decode(selectName, "UTF-8"));
			}
			vixntBaseService.findPagerByHqlConditions(pager, ItemCatalog.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public CommissionPlan getCommissionPlan() {
		return commissionPlan;
	}
	public void setCommissionPlan(CommissionPlan commissionPlan) {
		this.commissionPlan = commissionPlan;
	}
	
	public CommissionPlanItem getCommissionPlanItem() {
		return commissionPlanItem;
	}

	public void setCommissionPlanItem(CommissionPlanItem commissionPlanItem) {
		this.commissionPlanItem = commissionPlanItem;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public List<SalesPerformanceEvaluationMethod> getSalesPerformanceEvaluationMethodList() {
		return salesPerformanceEvaluationMethodList;
	}

	public void setSalesPerformanceEvaluationMethodList(
			List<SalesPerformanceEvaluationMethod> salesPerformanceEvaluationMethodList) {
		this.salesPerformanceEvaluationMethodList = salesPerformanceEvaluationMethodList;
	}
}
