package com.vix.common.orginialMeta.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.VixSecAction;
import com.vix.common.orginialMeta.entity.OrginialVar;
import com.vix.common.orginialMeta.service.IOrginialVarService;
import com.vix.common.orginialMeta.util.VixVarUtil;
import com.vix.core.web.Pager;

@Controller
@Scope("request")
public class OrginialVarAction extends VixSecAction{

	private static final long serialVersionUID = 1L;

	@Autowired
	private IOrginialVarService orginialVarService;
	
	private String varCode;
	
	private String id;
	
	private OrginialVar entity;
	
	private OrginialVar entityForm;
	
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			
			Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(varCode)){
				params.put("varCode", "%"+varCode+"%");
			}
			Pager pager = orginialVarService.findOrginialVarPager(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
 
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
				entity = orginialVarService.findEntityById(OrginialVar.class, id);
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
			if (orginialVarService.isEntityExist(OrginialVar.class,entityForm.getId(), "varCode", entityForm.getVarCode())) {
				setMessage("变量名已经存在！");
				return "update";
			}
			if(null != entityForm.getId()){
				isSave = false;
			}
			
			orginialVarService.saveOrginialVar(entityForm);
			//保存
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(SAVE_SUCCESS);
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
			orginialVarService.deleteById(OrginialVar.class, id);
			VixVarUtil.refreshVixTenantVar("");
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public IOrginialVarService getOrginialVarService() {
		return orginialVarService;
	}

	public void setOrginialVarService(IOrginialVarService orginialVarService) {
		this.orginialVarService = orginialVarService;
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

	public OrginialVar getEntity() {
		return entity;
	}

	public void setEntity(OrginialVar entity) {
		this.entity = entity;
	}

	public OrginialVar getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(OrginialVar entityForm) {
		this.entityForm = entityForm;
	}
	
}
