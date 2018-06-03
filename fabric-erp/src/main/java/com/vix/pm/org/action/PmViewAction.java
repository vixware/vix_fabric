package com.vix.pm.org.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.BizConstant;
import com.vix.core.web.Pager;
import com.vix.pm.org.entity.PmView;
import com.vix.pm.org.service.IPmViewService;

@Controller
@Scope("prototype")
public class PmViewAction extends BaseAction {
	
	@Autowired
	private IPmViewService pmViewService;
	
	private String id;
	
	private String businessViewName;
	
	private PmView entity;
	
	private PmView entityForm;
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			//params.put("roleCode,"+SearchCondition.NOEQUAL, BizConstant.ROLE_SUPERADMIN);
			//roleName
			if(StringUtils.isNotEmpty(businessViewName)){
				params.put("name", "%"+businessViewName+"%");
			}
			
			Pager pager = pmViewService.findBusinessViewPager(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
 
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(null != id ){
				entity = pmViewService.findEntityById(PmView.class, id);
			}else{
				entity = new PmView();
			}
			getRequest().setAttribute("bvType", BizConstant.COMMON_BIZORG_V_TYPE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != entityForm.getId()){
				isSave = false;
			}
			
			pmViewService.saveOrUpdateBusinessView(entityForm);
			
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
			pmViewService.deleteById(PmView.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public String getBusinessViewName() {
		return businessViewName;
	}

	public void setBusinessViewName(String businessViewName) {
		this.businessViewName = businessViewName;
	}


	public IPmViewService getPmViewService() {
		return pmViewService;
	}

	public void setPmViewService(IPmViewService pmViewService) {
		this.pmViewService = pmViewService;
	}

	public PmView getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(PmView entityForm) {
		this.entityForm = entityForm;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PmView getEntity() {
		return entity;
	}

	public void setEntity(PmView entity) {
		this.entity = entity;
	}

	
}
