package com.vix.nvix.sales.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.sales.plan.entity.SalePlan;
import com.vix.sales.plan.entity.SalePlanDetails;
/**
 * 销售计划明细
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixntSalePlanDetailsAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SalePlanDetails salePlanDetails;
	private String name;
	private SalePlan salePlan;
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String itemName = getDecodeRequestParameter("itemName");
			if(StringUtils.isNotEmpty(itemName)) {
				params.put("item.name,"+SearchCondition.ANYLIKE, itemName);
			}
			if(StringUtils.isNotEmpty(id)) {
				params.put("salePlan.id,"+SearchCondition.EQUAL, id);
				pager = baseHibernateService.findPagerByHqlConditions(pager, SalePlanDetails.class, params);
			}
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				salePlanDetails = baseHibernateService.findEntityById(SalePlanDetails.class, id);
			}else {
				salePlanDetails = new SalePlanDetails();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(salePlanDetails.getId())) {
				isSave = false;
				salePlanDetails.setUpdateTime(new Date());
			}else {
				salePlanDetails.setCreateTime(new Date());
				salePlanDetails.setUpdateTime(new Date());
			}
			initEntityBaseController.initEntityBaseAttribute(salePlanDetails);
			salePlanDetails = baseHibernateService.merge(salePlanDetails);
			if(isSave) {
				renderText(SAVE_SUCCESS);
			}else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave) {
				renderText(SAVE_FAIL);
			}else {
				renderText(UPDATE_FAIL);
			}
		}
	}
	public void deleteById() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				salePlanDetails = baseHibernateService.findEntityById(SalePlanDetails.class, id);
				if(salePlanDetails != null) {
					baseHibernateService.deleteByEntity(salePlanDetails);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 计算计划金额和数量
	 * @return
	 */
	public void compSalePlanAmountAndAcount() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				salePlan = baseHibernateService.findEntityById(SalePlan.class, id);
				if(salePlan != null) {
					List<SalePlanDetails> salePlanDetailsList = baseHibernateService.findAllByEntityClassAndAttribute(SalePlanDetails.class, "salePlan.id", salePlan.getId());
					Long amount = 0l;
					Double acount = 0d;
					if(salePlanDetailsList != null && salePlanDetailsList.size() > 0) {
						for (SalePlanDetails salePlanDetails : salePlanDetailsList) {
							if(salePlanDetails != null && salePlanDetails.getItem() != null) {
								amount += salePlanDetails.getAmount();
								acount += salePlanDetails.getItem().getPrice() * salePlanDetails.getAmount();
							}
						}
					}
					renderText(amount + ":" + acount);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public SalePlanDetails getSalePlanDetails() {
		return salePlanDetails;
	}
	public void setSalePlanDetails(SalePlanDetails salePlanDetails) {
		this.salePlanDetails = salePlanDetails;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SalePlan getSalePlan() {
		return salePlan;
	}
	public void setSalePlan(SalePlan salePlan) {
		this.salePlan = salePlan;
	}
}
