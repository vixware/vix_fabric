package com.vix.nvix.drp.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.srm.share.entity.SupplierLevel;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 供应商等级管理 
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class VixntSupplierLevelAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private SupplierLevel supplierLevel;
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String searchName = getDecodeRequestParameter("searchName");
			if(StringUtils.isNotEmpty(searchName)){
				params.put("name,"+SearchCondition.EQUAL, searchName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, SupplierLevel.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(id)){
				supplierLevel = vixntBaseService.findEntityById(SupplierLevel.class, id);
			}else{
				supplierLevel = new SupplierLevel();
				supplierLevel.setIsDefault("1");
				supplierLevel.setIsUse("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	public void saveOrUpdate(){
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(supplierLevel.getId())){
				isSave = false;
			}
			supplierLevel.setCreateTime(new Date());
			supplierLevel.setUpdateTime(new Date());
			supplierLevel.setCode(VixUUID.createCode(12));
			initEntityBaseController.initEntityBaseAttribute(supplierLevel);
			supplierLevel = vixntBaseService.merge(supplierLevel);
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
	public void deleteById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				SupplierLevel supplierLevel = vixntBaseService.findEntityById(SupplierLevel.class, id);
				if(supplierLevel != null){
					vixntBaseService.deleteByEntity(supplierLevel);
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
	public SupplierLevel getSupplierLevel() {
		return supplierLevel;
	}
	public void setSupplierLevel(SupplierLevel supplierLevel) {
		this.supplierLevel = supplierLevel;
	}
}
