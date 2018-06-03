package com.vix.common.securityDra.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseSecAction;
import com.vix.common.security.entity.DataResRowOwner;
import com.vix.common.security.entity.DataResRowPolicy;
import com.vix.common.securityDra.service.IDataResRowOwnerService;
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
public class DataResRowOwnerAction extends BaseSecAction {

	@Autowired
	private IDataResRowPolicyService dataResRowPolicyService;
	
	@Autowired
	private IDataResRowOwnerService dataResRowOwnerService;
	
	private List<DataResRowPolicy> dataResRowPolicyList;
	
	private String ownerName;
	
	private DataResRowOwner entity;
	
	private DataResRowOwner entityForm;
	
	private String id;
	
	/** 策略名称 */
	private String rowPlyName;
	
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
			/*if(StringUtils.isNotEmpty(ownerName)){
				params.put("ownerName,"+SearchCondition.ANYLIKE, ownerName);
			}*/
			//Pager pager = dataResRowOwnerService.findPagerByHqlConditions(getPager(), DataResRowOwner.class, params);
			
			if(StringUtils.isNotEmpty(ownerName)){
				ownerName = decode(ownerName, "UTF-8");
				params.put("ownerName", ownerName);
			}
			
			//Pager pager = dataResRowOwnerService.findPagerByHqlConditions(getPager(), DataResRowOwner.class, params);
			Pager pager = dataResRowOwnerService.findDataResRowOwnerPager(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
 
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			//dataResRowPolicyList =  dataResRowOwnerService.findAllByEntityClass(DataResRowPolicy.class);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id &&StringlongValue() > 0){
				entity = dataResRowOwnerService.findEntityById(DataResRowOwner.class, id);
				
				/*Organization orgTmp = organizationService.findOrganizationByCompCode(entity.getCompanyCode());
				parentId = orgTmp.getId();
				parentTreeName=orgTmp.getOrgName();*/
				
			}else{
				entity=new DataResRowOwner();
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
			
			String addRoleIds = getRequest().getParameter("addDataRowOwnerRoleIds");
	        String deleteRoleIds = getRequest().getParameter("deleteDataRowOwnerRoleIds");
	       
	        dataResRowOwnerService.saveOrUpdate(addRoleIds,deleteRoleIds,entityForm);
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
			dataResRowOwnerService.deleteById(DataResRowOwner.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 策略选择
	 * @return
	 */
	public String goChooseRowPolicy(){
		return "goChooseRowPolicy";
	}
	
	public String goChooseRowPolicyList(){
	    	try {
	    		Map<String,Object> params = new HashMap<String, Object>();
	    		if(StrUtils.isNotEmpty(rowPlyName)){
	    			params.put("policyName", rowPlyName);
	    		}
	    		//Pager pager = dataResRowOwnerService.findPagerByHqlConditions(getPager(), DataResRowPolicy.class, params); 
	    		//Pager pager = dataResRowOwnerService.findDataResRowOwnerPager(getPager(), params);
	    		Pager pager = dataResRowPolicyService.findDataResRowPolicyPager(getPager(), params);
	    		setPager(pager);
			}catch(Exception e){
				e.printStackTrace();
			}
	        return "goChooseRowPolicyList";
	}
	
	public IDataResRowOwnerService getDataResRowOwnerService() {
		return dataResRowOwnerService;
	}

	public void setDataResRowOwnerService(
			IDataResRowOwnerService dataResRowOwnerService) {
		this.dataResRowOwnerService = dataResRowOwnerService;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public DataResRowOwner getEntity() {
		return entity;
	}

	public void setEntity(DataResRowOwner entity) {
		this.entity = entity;
	}

	public DataResRowOwner getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(DataResRowOwner entityForm) {
		this.entityForm = entityForm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<DataResRowPolicy> getDataResRowPolicyList() {
		return dataResRowPolicyList;
	}

	public void setDataResRowPolicyList(List<DataResRowPolicy> dataResRowPolicyList) {
		this.dataResRowPolicyList = dataResRowPolicyList;
	}

	public String getRowPlyName() {
		return rowPlyName;
	}

	public void setRowPlyName(String rowPlyName) {
		this.rowPlyName = rowPlyName;
	}


}
