package com.vix.nvix.sales.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 销售返利规则
 * @author jackie
 *
 */
import com.vix.sales.refund.entity.ReturnRule;
import com.vix.sales.refund.entity.ReturnRuleItem;
@Controller
@Scope("prototype")
public class NvixntReturnRuleAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private ReturnRule returnRule;
	private ReturnRuleItem returnRuleItem;
	private List<CurrencyType> currencyTypeList;
	private List<MeasureUnit> measureUnitList;
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String title = getDecodeRequestParameter("title");
			if(StringUtils.isNotEmpty(title)){
				params.put("item.name,"+SearchCondition.ANYLIKE, title);
			}
			String name = getDecodeRequestParameter("name");
			if(StringUtils.isNotEmpty(name)){
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, name);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, ReturnRule.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(id)){
				returnRule = vixntBaseService.findEntityById(ReturnRule.class, id);
			}else{
				returnRule = new ReturnRule();
				returnRule.setRrType("customerAccount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	public void saveOrUpdate(){
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(returnRule.getId())){
				isSave = false;
			}
			returnRule.setCreateTime(new Date());
			returnRule.setUpdateTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(returnRule);
			returnRule = vixntBaseService.merge(returnRule);
			if(isSave){
				renderText("1:"+SAVE_SUCCESS+":"+returnRule.getId());
			}else{
				renderText("1:"+UPDATE_SUCCESS+":"+returnRule.getId());
			}
		} catch (Exception e) {
			if(isSave){
				renderText("0:"+SAVE_FAIL);
			}else{
				renderText("0:"+UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}
	public void deleteById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				returnRule = vixntBaseService.findEntityById(ReturnRule.class, id);
				if(returnRule != null){
					vixntBaseService.deleteByEntity(returnRule);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}
	public void goSingleListReturnRuleItem(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(id)){
				params.put("returnRule.id,"+SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, ReturnRuleItem.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdateReturnRuleItem(){
		try {
			if(StringUtils.isNotEmpty(id)){
				returnRuleItem = vixntBaseService.findEntityById(ReturnRuleItem.class, id);
			}else{
				returnRuleItem = new ReturnRuleItem();
			}
			currencyTypeList = vixntBaseService.findAllByEntityClass(CurrencyType.class);
			measureUnitList = vixntBaseService.findAllByEntityClass(MeasureUnit.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateReturnRuleItem";
	}
	public void saveOrUpdateReturnRuleItem(){
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(returnRuleItem.getId())){
				isSave = false;
			}else{
				returnRuleItem.setCode(VixUUID.createCode(12));
				returnRuleItem.setCreateTime(new Date());
				returnRuleItem.setUpdateTime(new Date());
			}
			initEntityBaseController.initEntityBaseAttribute(returnRuleItem);
			returnRuleItem = vixntBaseService.merge(returnRuleItem);
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}
	public void deleteReturnRuleItemById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				returnRuleItem = vixntBaseService.findEntityById(ReturnRuleItem.class, id);
				if(returnRuleItem != null){
					vixntBaseService.deleteByEntity(returnRuleItem);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ReturnRule getReturnRule() {
		return returnRule;
	}
	public void setReturnRule(ReturnRule returnRule) {
		this.returnRule = returnRule;
	}
	public ReturnRuleItem getReturnRuleItem() {
		return returnRuleItem;
	}
	public void setReturnRuleItem(ReturnRuleItem returnRuleItem) {
		this.returnRuleItem = returnRuleItem;
	}
	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}
	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}
	public List<MeasureUnit> getMeasureUnitList() {
		return measureUnitList;
	}
	public void setMeasureUnitList(List<MeasureUnit> measureUnitList) {
		this.measureUnitList = measureUnitList;
	}
}
