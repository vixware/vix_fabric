package com.vix.contract.config.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.contract.config.entity.ContractNatureType;
import com.vix.contract.config.entity.ViewIndustryType;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class ViewIndustryTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private ViewIndustryType viewIndustryType;
	
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	
	@Override
	public String goList() {
		return GO_LIST;
	}
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), ViewIndustryType.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(null != id && !"".equals(id)){
				viewIndustryType = baseHibernateService.findEntityById(ViewIndustryType.class,id);
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
			if(null != viewIndustryType.getId()){
				isSave = false;
			}
			viewIndustryType = baseHibernateService.merge(viewIndustryType);
			initEntityBaseController.initEntityBaseAttribute(viewIndustryType);
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
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
			ContractNatureType pb = baseHibernateService.findEntityById(ContractNatureType.class,id);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ViewIndustryType getViewIndustryType() {
		return viewIndustryType;
	}

	public void setViewIndustryType(ViewIndustryType viewIndustryType) {
		this.viewIndustryType = viewIndustryType;
	}


	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(
			InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

}
