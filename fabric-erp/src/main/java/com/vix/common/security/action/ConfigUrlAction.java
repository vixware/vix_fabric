package com.vix.common.security.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.VixSecAction;
import com.vix.common.security.entity.ConfigUrlAdd;
import com.vix.common.security.service.IConfigUrlService;
import com.vix.core.web.Pager;

@Controller
@Scope("request")
public class ConfigUrlAction extends VixSecAction{

	private static final long serialVersionUID = 1L;

	@Autowired
	private IConfigUrlService configUrlService;
	
	private String configUrlName;
	
	private String id;
	
	private ConfigUrlAdd entity;
	
	private ConfigUrlAdd entityForm;
	
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			
			Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(configUrlName)){
				configUrlName = decode(configUrlName, "UTF-8");
				params.put("name", "%"+configUrlName+"%");
			}
			Pager pager = configUrlService.findConfigUrlPager(getPager(), params);
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
				entity = configUrlService.findEntityById(ConfigUrlAdd.class, id);
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
			if (configUrlService.isEntityExist(ConfigUrlAdd.class,entityForm.getId(), "code", entityForm.getCode())) {
				setMessage("编码已经存在！");
				return "update";
			}
			if(null != entityForm.getId()){
				isSave = false;
			}
			
			configUrlService.saveConfigUrl(entityForm);
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
			configUrlService.deleteById(ConfigUrlAdd.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getConfigUrlName() {
		return configUrlName;
	}

	public void setConfigUrlName(String configUrlName) {
		this.configUrlName = configUrlName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ConfigUrlAdd getEntity() {
		return entity;
	}

	public void setEntity(ConfigUrlAdd entity) {
		this.entity = entity;
	}

	public ConfigUrlAdd getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(ConfigUrlAdd entityForm) {
		this.entityForm = entityForm;
	}
	
	
}
