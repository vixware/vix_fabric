package com.vix.common.securityDra.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseSecAction;
import com.vix.common.security.entity.DataResRowMethod;
import com.vix.common.securityDra.service.IDataResRowMethodService;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.web.Pager;

/**
 * 元数据和拦截方法配置action
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class DataResRowMethodAction extends BaseSecAction {

	@Autowired
	private IDataResRowMethodService dataResRowMethodService;
	
	private String hqlProvider;
	
	private DataResRowMethod entity;
	
	private DataResRowMethod entityForm;
	
	private String id;
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(hqlProvider)){
				params.put("hqlProvider", hqlProvider);
			}
			
			HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
			HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
			
			Pager pager = dataResRowMethodService.findDataResRowMethodPager(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
 
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			
			//dataResRowPolicyList =  dataResRowMethodConfigService.findAllByEntityClass(DataResRowPolicy.class);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				entity = dataResRowMethodService.findEntityById(DataResRowMethod.class, id);
				
				/*Organization orgTmp = organizationService.findOrganizationByCompCode(entity.getCompanyCode());
				parentId = orgTmp.getId();
				parentTreeName=orgTmp.getOrgName();*/
				
			}else{
				entity=new DataResRowMethod();
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
			
			//addDataRowMetaDataIds
			if(null != entityForm.getId()){
                isSave = false;
            }
            
			dataResRowMethodService.mergeOriginal(entityForm);
            //ApplicationSecurityCode.hqlMethodMetadataMap =null;
			
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
			dataResRowMethodService.deleteById(DataResRowMethod.class, id);
			
			//ApplicationSecurityCode.hqlMethodMetadataMap =null;
			 
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goChooseDataResRowMethod(){
	    	if(StringUtils.isEmpty(chkStyle)){
				chkStyle = "checkbox";
			}
	        return "goChooseDataResRowMethod";
    }
	    
	/** 获取列表数据  */
    public String goChooseDataResRowMethodList(){
    	try {
    		Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(hqlProvider)){
				params.put("hqlProvider", hqlProvider);
			}
			
			HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
			HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
			
			Pager pager = dataResRowMethodService.findDataResRowMethodPager(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
        return "goChooseDataResRowMethodList";
    }
	    
	public IDataResRowMethodService getDataResRowMethodService() {
		return dataResRowMethodService;
	}

	public void setDataResRowMethodService(
			IDataResRowMethodService dataResRowMethodService) {
		this.dataResRowMethodService = dataResRowMethodService;
	}

	public String getHqlProvider() {
		return hqlProvider;
	}

	public void setHqlProvider(String hqlProvider) {
		this.hqlProvider = hqlProvider;
	}

	public DataResRowMethod getEntity() {
		return entity;
	}

	public void setEntity(DataResRowMethod entity) {
		this.entity = entity;
	}

	public DataResRowMethod getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(DataResRowMethod entityForm) {
		this.entityForm = entityForm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
