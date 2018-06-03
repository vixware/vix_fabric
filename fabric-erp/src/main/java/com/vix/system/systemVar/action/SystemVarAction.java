package com.vix.system.systemVar.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.tag.util.VixVarTenantUtil;
import com.vix.core.web.Pager;
import com.vix.system.systemVar.entity.SystemVar;
import com.vix.system.systemVar.service.ISystemVarService;

@Controller
@Scope("request")
public class SystemVarAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ISystemVarService systemVarService;
	
	private String varCode;
	
	private String id;
	
	private SystemVar entity;
	
	private SystemVar entityForm;
	
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			
			Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(varCode)){
				params.put("varCode", "%"+varCode+"%");
			}
			Pager pager = systemVarService.findSystemVarPager(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
 
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				entity = systemVarService.findEntityById(SystemVar.class, id);
			}
			//getRequest().setAttribute("moduleTypeMap", BizConstant.COMMON_SECURITY_RESTYPE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (systemVarService.isEntityExist(SystemVar.class,entityForm.getId(), "varCode", entityForm.getVarCode())) {
				setMessage("变量名已经存在！");
				return "update";
			}
			if(null != entityForm.getId()){
				isSave = false;
			}
			
			systemVarService.saveSystemVar(entityForm);
			//保存
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
			systemVarService.deleteById(SystemVar.class, id);
			VixVarTenantUtil.refreshVixTenantVar(SecurityUtil.getCurrentUserTenantId());
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public ISystemVarService getSystemVarService() {
		return systemVarService;
	}

	public void setSystemVarService(ISystemVarService systemVarService) {
		this.systemVarService = systemVarService;
	}

	public String getVarCode() {
		return varCode;
	}

	public void setVarCode(String varCode) {
		this.varCode = varCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SystemVar getEntity() {
		return entity;
	}

	public void setEntity(SystemVar entity) {
		this.entity = entity;
	}

	public SystemVar getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(SystemVar entityForm) {
		this.entityForm = entityForm;
	}
	
}
