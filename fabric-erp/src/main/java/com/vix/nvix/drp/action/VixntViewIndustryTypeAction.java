package com.vix.nvix.drp.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.contract.config.entity.ViewIndustryType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 框架合作协议管理
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class VixntViewIndustryTypeAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private ViewIndustryType viewIndustryType;
	
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String searchName = getDecodeRequestParameter("searchName");
			if(StringUtils.isNotEmpty(searchName)){
				params.put("name,"+SearchCondition.ANYLIKE, searchName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, ViewIndustryType.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(id)){
				viewIndustryType = vixntBaseService.findEntityById(ViewIndustryType.class, id);
			}else{
				viewIndustryType = new ViewIndustryType();
				viewIndustryType.setCode(VixUUID.createCode(12));
				viewIndustryType.setDisabled("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	public void saveOrUpdate(){
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(viewIndustryType.getId())){
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(viewIndustryType);
			viewIndustryType = vixntBaseService.merge(viewIndustryType);
			if (isSave) {
				renderText("1:"+SAVE_SUCCESS);
			} else {
				renderText("1:"+UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:"+SAVE_FAIL);
			} else {
				renderText("0:"+UPDATE_FAIL);
			}
		}
	}
	
	public void deleteById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				viewIndustryType = vixntBaseService.findEntityById(ViewIndustryType.class, id);
				if(viewIndustryType != null){
					vixntBaseService.deleteByEntity(viewIndustryType);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
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

	public ViewIndustryType getViewIndustryType() {
		return viewIndustryType;
	}

	public void setViewIndustryType(ViewIndustryType viewIndustryType) {
		this.viewIndustryType = viewIndustryType;
	}
}
