package com.vix.common.securityDra.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseSecAction;
import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.security.entity.DataResRowMethodConfig;
import com.vix.common.securityDra.service.IDataResRowMethodConfigService;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;

/**
 * 元数据和拦截方法配置action
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class DataResRowMethodConfigAction extends BaseSecAction {

	@Autowired
	private IDataResRowMethodConfigService dataResRowMethodConfigService;
	
	private String methodName;
	
	private DataResRowMethodConfig entity;
	
	private DataResRowMethodConfig entityForm;
	
	private String id;
	
	/**
	 * 覆盖父类方法  加载选择列表数据
	 
	@Override
	public String goList(){
		try {
			dataColConfigList = dataColSecService.findAllByEntityClass(DataResColConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}*/
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(methodName)){
				params.put("ownerName,"+SearchCondition.ANYLIKE, methodName);
			}
			
			Pager pager = dataResRowMethodConfigService.findPagerByHqlConditions(getPager(), DataResRowMethodConfig.class, params);
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
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
				entity = dataResRowMethodConfigService.findEntityById(DataResRowMethodConfig.class, id);
				
				/*Organization orgTmp = organizationService.findOrganizationByCompCode(entity.getCompanyCode());
				parentId = orgTmp.getId();
				parentTreeName=orgTmp.getOrgName();*/
				
			}else{
				entity=new DataResRowMethodConfig();
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
            //String addDataRowMetaDataIds = getRequest().getParameter("addDataRowMetaDataIds");
            //dataResRowMethodConfigService.saveOrUpdate(addDataRowMetaDataIds,entityForm);
			dataResRowMethodConfigService.saveOrUpdate(entityForm);
            
            //ApplicationSecurityCode.hqlMethodMetadataMap =null;
			
            if(isSave){
                renderText(SAVE_SUCCESS);
            }else{
                renderText(SAVE_SUCCESS);
            }
		} catch (Exception e) {
			e.printStackTrace();
			this.setMessage(e.getMessage());
			/*
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}*/
		}
		return UPDATE;
	}
	
	/** 处理删除操作 */
	public String deleteById() {
		try {
			dataResRowMethodConfigService.deleteById(DataResRowMethodConfig.class, id);
			
			//ApplicationSecurityCode.hqlMethodMetadataMap =null;
			 
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	
	/**
     * @Title: goChooseRole
     * @Description: 跳转到选择角色界面 
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public String goChooseMetaData(){
    	if(StringUtils.isEmpty(chkStyle)){
			chkStyle = "checkbox";
		}
        return "goChooseMetaData";
    }
    
   /**
    * 
    * @return
    */
    public String goChooseMetaDataList(){
    	try {
    		Map<String,Object> params = new HashMap<String, Object>();
    		Pager pager = dataResRowMethodConfigService.findPagerByHqlConditions(getPager(), BaseMetaData.class, params);
    		setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
        return "goChooseMetaDataList";
    }
    
    
	public IDataResRowMethodConfigService getDataResRowMethodConfigService() {
		return dataResRowMethodConfigService;
	}

	public void setDataResRowMethodConfigService(
			IDataResRowMethodConfigService dataResRowMethodConfigService) {
		this.dataResRowMethodConfigService = dataResRowMethodConfigService;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public DataResRowMethodConfig getEntity() {
		return entity;
	}

	public void setEntity(DataResRowMethodConfig entity) {
		this.entity = entity;
	}

	public DataResRowMethodConfig getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(DataResRowMethodConfig entityForm) {
		this.entityForm = entityForm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
