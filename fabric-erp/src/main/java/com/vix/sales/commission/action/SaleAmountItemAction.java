package com.vix.sales.commission.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.sales.commission.entity.SaleAmountItem;

@Controller
@Scope("prototype")
public class SaleAmountItemAction extends BaseAction{
	private static final long serialVersionUID = 1L;


	
	private String id;
	private SaleAmountItem saleAmountItem;
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(null != id && !"".equals(id)){
				saleAmountItem = baseHibernateService.findEntityById(SaleAmountItem.class,id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != saleAmountItem.getId()){
				isSave = false;
			}else{
				saleAmountItem.setCreateTime(new Date());
				loadCommonData(saleAmountItem);
			}
			calcuteTotal(saleAmountItem);
			saleAmountItem = baseHibernateService.merge(saleAmountItem);
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			SaleAmountItem pb = baseHibernateService.findEntityById(SaleAmountItem.class,id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	

	private void calcuteTotal(SaleAmountItem sai) throws Exception{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("saleAmount.id,"+SearchCondition.EQUAL, sai.getSaleAmount().getId());
		List<SaleAmountItem> listSAI =baseHibernateService.findAllByConditions(SaleAmountItem.class, params);
		Long totalCount = saleAmountItem.getSaleCountQuota();
		Double totalMoney = saleAmountItem.getSaleAmountQuota();
		if(null != listSAI && listSAI.size() > 0){
			for(SaleAmountItem s : listSAI){
				if(null != s){
					if(s.getMonth() < sai.getMonth()){
						totalCount += s.getSaleCountQuota();
						totalMoney += s.getSaleAmountQuota();
					}
				}
			}
		}
		sai.setSaleCountQuotaTotal(totalCount);
		sai.setSaleAmountQuotaTotal(totalMoney);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SaleAmountItem getSaleAmountItem() {
		return saleAmountItem;
	}

	public void setSaleAmountItem(SaleAmountItem saleAmountItem) {
		this.saleAmountItem = saleAmountItem;
	}
}