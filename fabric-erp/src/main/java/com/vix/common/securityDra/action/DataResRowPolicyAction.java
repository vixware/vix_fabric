package com.vix.common.securityDra.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseSecAction;
import com.vix.common.security.entity.DataResRowPolicy;
import com.vix.common.security.entity.DataResRowPolicyObj;
import com.vix.common.securityDra.service.IDataResRowPolicyObjService;
import com.vix.common.securityDra.service.IDataResRowPolicyService;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

/**
 * 数据权限主体action
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class DataResRowPolicyAction extends BaseSecAction {

	@Autowired
	private IDataResRowPolicyService dataResRowPolicyService;
	
	@Autowired
	private IDataResRowPolicyObjService dataResRowPolicyObjService;
	
	private List<DataResRowPolicyObj> dataResRowPolicyObjList;
	
	private String policyName;
	
	private DataResRowPolicy entity;
	
	private DataResRowPolicy entityForm;
	
	private String id;
	
	/**
	 * 策略对象名称
	 */
	private String rowPolicyObjName;
	
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
			/*if(StringUtils.isNotEmpty(policyName)){
				params.put("policyName,"+SearchCondition.ANYLIKE, policyName);
			}*/
			if(StringUtils.isNotEmpty(policyName)){
				policyName = decode(policyName, "UTF-8");
				params.put("policyName", policyName);
			}
			//Pager pager = dataResRowPolicyService.findPagerByHqlConditions(getPager(), DataResRowPolicy.class, params);
			Pager pager = dataResRowPolicyService.findDataResRowPolicyPager(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
 
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			
			//dataResRowPolicyObjList =  dataResRowOwnerService.findAllByEntityClass(DataResRowPolicy.class);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
				entity = dataResRowPolicyService.findEntityById(DataResRowPolicy.class, id);
				
				/*Organization orgTmp = organizationService.findOrganizationByCompCode(entity.getCompanyCode());
				parentId = orgTmp.getId();
				parentTreeName=orgTmp.getOrgName();*/
				
			}else{
				entity=new DataResRowPolicy();
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
			if(null != entityForm.getId()){
                isSave = false;
            }
            String addPlyObjIds = getRequest().getParameter("addDataRowPolicyObjIds");
            String deletePlyObjIds = getRequest().getParameter("deleteDataRowPolicyObjIds");
            dataResRowPolicyService.saveOrUpdate(addPlyObjIds,deletePlyObjIds,entityForm);
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
			//dataColSecService.deleteById(DataResColConfig.class, id);
			dataResRowPolicyService.deleteById(DataResRowPolicy.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	
	public String goChooseRowPolicy(){
		chkStyle = "checkbox";
		return "goChooseRowPolicy";
	}
	
	public String goChooseRowPolicyList(){
	    	try {
	    		/*
	    		Map<String,Object> params =getParams();
	    		Pager pager = dataResRowPolicyObjService.findPagerByHqlConditions(getPager(), DataResRowPolicyObj.class, params); 
	    		setPager(pager);
	    		*/
	    		Map<String,Object> params = new HashMap<String, Object>();
	    		if(StrUtils.isNotEmpty(rowPolicyObjName)){
	    			params.put("metaDataViewName", rowPolicyObjName);
	    		}
	    		//Pager pager = dataResRowPolicyObjService.findPagerByHqlConditions(getPager(), DataResRowPolicyObj.class, params); 
	    		Pager pager = dataResRowPolicyObjService.findPolicyObjPager(getPager(), params);
	    		setPager(pager);
			}catch(Exception e){
				e.printStackTrace();
			}
	        return "goChooseRowPolicyList";
	}
	 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IDataResRowPolicyService getDataResRowPolicyService() {
		return dataResRowPolicyService;
	}

	public void setDataResRowPolicyService(
			IDataResRowPolicyService dataResRowPolicyService) {
		this.dataResRowPolicyService = dataResRowPolicyService;
	}

	public List<DataResRowPolicyObj> getDataResRowPolicyObjList() {
		return dataResRowPolicyObjList;
	}

	public void setDataResRowPolicyObjList(
			List<DataResRowPolicyObj> dataResRowPolicyObjList) {
		this.dataResRowPolicyObjList = dataResRowPolicyObjList;
	}

	public DataResRowPolicy getEntity() {
		return entity;
	}

	public void setEntity(DataResRowPolicy entity) {
		this.entity = entity;
	}

	public DataResRowPolicy getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(DataResRowPolicy entityForm) {
		this.entityForm = entityForm;
	}

	public IDataResRowPolicyObjService getDataResRowPolicyObjService() {
		return dataResRowPolicyObjService;
	}

	public void setDataResRowPolicyObjService(
			IDataResRowPolicyObjService dataResRowPolicyObjService) {
		this.dataResRowPolicyObjService = dataResRowPolicyObjService;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getRowPolicyObjName() {
		return rowPolicyObjName;
	}

	public void setRowPolicyObjName(String rowPolicyObjName) {
		this.rowPolicyObjName = rowPolicyObjName;
	}

}
