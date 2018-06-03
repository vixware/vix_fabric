package com.vix.nvix.sales.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.sales.commission.entity.SaleAmount;
import com.vix.sales.commission.entity.SaleAmountItem;
@Controller
@Scope("prototype")
public class NvixntSaleAmountAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SaleAmount saleAmount;
	private SaleAmountItem saleAmountItem;
	private String id;
	
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			
			pager = vixntBaseService.findPagerByHqlConditions(pager, SaleAmount.class, params);
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				saleAmount = vixntBaseService.findEntityById(SaleAmount.class, id);
			}else {
				saleAmount = new SaleAmount();
				saleAmount.setCode(VixUUID.createCode(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(saleAmount.getId())) {
				isSave = false;
				saleAmount.setUpdateTime(new Date());
			}else {
				saleAmount.setCreateTime(new Date());
			}
			initEntityBaseController.initEntityBaseAttribute(saleAmount);
			saleAmount = vixntBaseService.merge(saleAmount);
			if(isSave){
				renderText("1:"+SAVE_SUCCESS+":"+saleAmount.getId());
			}else{
				renderText("1:"+UPDATE_SUCCESS+":"+saleAmount.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText("0:"+SAVE_FAIL);
			}else{
				renderText("0:"+UPDATE_FAIL);
			}
		}
	}
	/** 处理删除操作 */
	public void deleteById() {
		try {
			SaleAmount pb = baseHibernateService.findEntityById(SaleAmount.class,id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				renderText("该销售定额不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	public void goSingleListSaleAmountItem() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			
			if(StringUtils.isNotEmpty(id)) {
				params.put("saleAmount.id,"+SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, SaleAmountItem.class, params);
			}
			
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdateSaleAmountItem() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				saleAmountItem = vixntBaseService.findEntityById(SaleAmountItem.class, id);
			}else {
				saleAmountItem = new SaleAmountItem();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateSaleAmountItem";
	}
	
	public void saveOrUpdateSaleAmountItem() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(saleAmountItem.getId())) {
				isSave = false;
				saleAmountItem.setUpdateTime(new Date());
			}else {
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
	}
	public void deleteSaleAmountItemById() {
		try {
			SaleAmountItem pb = baseHibernateService.findEntityById(SaleAmountItem.class,id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				renderText("该定额明细未找到!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
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
	public SaleAmount getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(SaleAmount saleAmount) {
		this.saleAmount = saleAmount;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
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
