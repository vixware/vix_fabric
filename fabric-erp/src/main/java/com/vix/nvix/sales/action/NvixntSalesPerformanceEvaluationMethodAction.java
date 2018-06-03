package com.vix.nvix.sales.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.sales.common.entity.SalesPerformanceEvaluationMethod;
@Controller
@Scope("prototype")
public class NvixntSalesPerformanceEvaluationMethodAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SalesPerformanceEvaluationMethod salesPerformanceEvaluationMethod;
	private String id;
	
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String name = getDecodeRequestParameter("name");
			if(StringUtils.isNotEmpty(name)) {
				params.put("name,"+SearchCondition.ANYLIKE, name);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, SalesPerformanceEvaluationMethod.class, params);
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				salesPerformanceEvaluationMethod = vixntBaseService.findEntityById(SalesPerformanceEvaluationMethod.class, id);
			}else{
				salesPerformanceEvaluationMethod = new SalesPerformanceEvaluationMethod();
				salesPerformanceEvaluationMethod.setCode(VixUUID.createCode(12));
				salesPerformanceEvaluationMethod.setIsDefault("1");
				salesPerformanceEvaluationMethod.setEnable("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(salesPerformanceEvaluationMethod.getId())) {
				isSave = false;
				salesPerformanceEvaluationMethod.setUpdateTime(new Date());
			}else {
				salesPerformanceEvaluationMethod.setCreateTime(new Date());
				loadCommonData(salesPerformanceEvaluationMethod);
			}
			salesPerformanceEvaluationMethod = vixntBaseService.merge(salesPerformanceEvaluationMethod);
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
	public void deleteById() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				SalesPerformanceEvaluationMethod salesPerformanceEvaluationMethod = vixntBaseService.findEntityById(SalesPerformanceEvaluationMethod.class, id);
				if(salesPerformanceEvaluationMethod != null) {
					vixntBaseService.deleteByEntity(salesPerformanceEvaluationMethod);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}

	public SalesPerformanceEvaluationMethod getSalesPerformanceEvaluationMethod() {
		return salesPerformanceEvaluationMethod;
	}

	public void setSalesPerformanceEvaluationMethod(SalesPerformanceEvaluationMethod salesPerformanceEvaluationMethod) {
		this.salesPerformanceEvaluationMethod = salesPerformanceEvaluationMethod;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
}
