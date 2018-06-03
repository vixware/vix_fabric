package com.vix.common.securityDca.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseSecAction;
import com.vix.common.security.entity.DataResColConfig;
import com.vix.common.securityDca.service.IDataColSecService;
import com.vix.core.web.Pager;

/**
 * 列级权限  配置  action
 * @author Administrator
 *
 */
@Controller
@Scope("request")
public class DataColSecAction extends BaseSecAction {

	@Autowired
	private IDataColSecService dataColSecService;
	
//    private IOrganizationService organizationService;
	
	private DataResColConfig entity;
	
	private DataResColConfig entityForm;
	
	private String id;
	
	private String parentId;
	
	private String parentTreeName;
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = dataColSecService.findPagerByHqlConditions(getPager(), DataResColConfig.class, params);
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
				entity = dataColSecService.findEntityById(DataResColConfig.class, id);
				//!!!!!!!!!!!!!  2014-05-04 需要根据具体逻辑使用dataColSecService获取BaseOrganization对象
				//BaseOrganization orgTmp = null;//organizationService.findOrganizationByCompCode(entity.getCompanyCode());
				//parentId = orgTmp.getId();
				//parentTreeName=orgTmp.getOrgName();
				
			}else{
				entity=new DataResColConfig();
				entity.setFlag(1);
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
			/*if (dataColSecService.isEntityExist(DataResColConfig.class,entityForm.getId(), "companyCode", entityForm.getCompanyCode())) {
				setMessage("公司编码已经存在");
				return "update";
			}*/
			if (dataColSecService.isEntityExist(DataResColConfig.class,entityForm.getId(), "configName", entityForm.getConfigName())) {
				setMessage("分类名称已经存在！");
				return "update";
			}
			/*if(parentId==null){
				setMessage("没有选择公司！");
				return "update";
			}*/
			
			if(null != entityForm.getId()){
				isSave = false;
			}
			//BaseOrganization org = dataColSecService.findEntityById(BaseOrganization.class, parentId);
			//entityForm.setCompanyCode(org.getCompanyCode());
			dataColSecService.merge(entityForm);
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
			dataColSecService.deleteById(DataResColConfig.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public DataResColConfig getEntity() {
		return entity;
	}

	public void setEntity(DataResColConfig entity) {
		this.entity = entity;
	}

	public DataResColConfig getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(DataResColConfig entityForm) {
		this.entityForm = entityForm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentTreeName() {
		return parentTreeName;
	}

	public void setParentTreeName(String parentTreeName) {
		this.parentTreeName = parentTreeName;
	}
	
}
