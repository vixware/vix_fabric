package com.vix.common.securityDra.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseSecAction;
import com.vix.common.security.entity.DataResRowPredata;
import com.vix.common.security.entity.DataResRowSystemParams;
import com.vix.common.security.entity.Role;
import com.vix.common.securityDra.service.IDataResRowSysParamService;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.utils.JSonUtils;
import com.vix.core.web.Pager;

/**
 * 行集数据权限的预处理配置
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class DataResRowSysParamAction extends BaseSecAction {

	@Autowired
	private IDataResRowSysParamService dataResRowSysParamService;
	
	private DataResRowSystemParams entity;
	
	private DataResRowSystemParams entityForm;
	
	private DataResRowPredata predataEntity;
	
	private DataResRowPredata predataEntityForm;
	
	private String id;
	
	
	private String sysParamId;
	
	private String roleId;
	
	private String predataId;
	
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
			
			HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
			HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
			
			Pager pager = dataResRowSysParamService.findPagerByHqlConditions(getPager(), DataResRowSystemParams.class, params);
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
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
				entity = dataResRowSysParamService.findEntityById(DataResRowSystemParams.class, id);
				/*Organization orgTmp = organizationService.findOrganizationByCompCode(entity.getCompanyCode());
				parentId = orgTmp.getId();
				parentTreeName=orgTmp.getOrgName();*/
			}else{
				entity=new DataResRowSystemParams();
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
			
			DataResRowSystemParams entity = dataResRowSysParamService.saveOrUpdateParam(entityForm);
			Map<String,Object> msgObj = new HashMap<String, Object>();
			if(isSave){
            	msgObj.put("msg", SAVE_SUCCESS);
            }else{
            	msgObj.put("msg", UPDATE_SUCCESS);
            }
            msgObj.put("objId", entity.getId());
            
            String resMsg = JSonUtils.toJSon(msgObj);
            setMessage(resMsg);
			/*if(isSave){
                renderText(SAVE_SUCCESS);
            }else{
                renderText(SAVE_SUCCESS);
            }*/
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
			dataResRowSysParamService.deleteById(DataResRowSystemParams.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	
	
	
	

	/**
	 * 加载所选角色信息
	 * @return
	 */
	public String findDataParamRoleList() {
		try {
			List<Role> dataResParamRoleList = null;
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (id != null && id > 0) {
				dataResParamRoleList = dataResRowSysParamService.findSysParamRoleList(id);
			} else {
				dataResParamRoleList = new ArrayList<Role>();
			}

			renderHtml(convertListToJsonNoPage(dataResParamRoleList,dataResParamRoleList.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 增加角色
	 * @return
	 */
	public String saveRoleForParam() {
		boolean isSave = true;
		try {
			if (sysParamId != null && StringUtils.isNotEmpty(roleId)) {
				dataResRowSysParamService.saveOrUpdateParamRole(sysParamId, roleId);
			}

			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/**
	 * 删除配置的角色
	 * @return
	 */
	public String deleteRoleForParam() {
		try {
			if (sysParamId != null && StringUtils.isNotEmpty(roleId)) {
				dataResRowSysParamService.deleteRoleForParam(sysParamId, roleId);
	       	}
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	
	
	
	
	
	
	/**
	 * 加载预处理数据信息 dataResRowPredatas
	 * @return
	 */
	public String findDataParamPreDataList() {
		try {
			List<DataResRowPredata> preDataList = null;
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (id != null && id > 0) {
				preDataList = dataResRowSysParamService.findSysParamPreDataList(id);
			} else {
				preDataList = new ArrayList<DataResRowPredata>();
			}

			renderHtml(convertListToJsonNoPage(preDataList,preDataList.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/** 跳转至预处理数据的新建或者修改页面 
	public String goSaveUpdatePredata() {
		try {
			
			//dataResRowPolicyList =  dataResRowOwnerService.findAllByEntityClass(DataResRowPolicy.class);
			if(predataId!=null && predataId>0l){
				predataEntity = dataResRowSysParamService.findEntityById(DataResRowPredata.class, predataId);
			}else{
				predataEntity = new DataResRowPredata();
				if(sysParamId!=null && sysParamId>0l){
					DataResRowSystemParams dataResRowSystemParams = dataResRowSysParamService.findEntityById(DataResRowSystemParams.class, sysParamId);
					predataEntity.setDataResRowSystemParams(dataResRowSystemParams);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveUpdatePredata";
	}
	*/
	/**
	 * 预处理数据的保存
	 * @return
	 
	public String savePredataForParam() {
		boolean isSave = true;
		try {
			//if (sysParamId != null && predataId!=null) {
				//dataResRowSysParamService.saveOrUpdateParamRole(sysParamId, roleId);
				
			//}
			dataResRowSysParamService.saveOrUpdateParamPredata(predataEntityForm);
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	*/
	/**
	 * 删除配置的预处理数据 
	 * @return
	 */
	public String deletePredataForParam() {
		try {
			if (predataId != null ) {
				//dataResRowSysParamService.deletePredataForParam(predataId);
				dataResRowSysParamService.deleteById(DataResRowPredata.class, predataId);
	       	}
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	
	public IDataResRowSysParamService getDataResRowSysParamService() {
		return dataResRowSysParamService;
	}

	public void setDataResRowSysParamService(
			IDataResRowSysParamService dataResRowSysParamService) {
		this.dataResRowSysParamService = dataResRowSysParamService;
	}

	public DataResRowSystemParams getEntity() {
		return entity;
	}

	public void setEntity(DataResRowSystemParams entity) {
		this.entity = entity;
	}

	public DataResRowSystemParams getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(DataResRowSystemParams entityForm) {
		this.entityForm = entityForm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getSysParamId() {
		return sysParamId;
	}

	public void setSysParamId(String sysParamId) {
		this.sysParamId = sysParamId;
	}

	public String getPredataId() {
		return predataId;
	}

	public void setPredataId(String predataId) {
		this.predataId = predataId;
	}

	public DataResRowPredata getPredataEntity() {
		return predataEntity;
	}

	public void setPredataEntity(DataResRowPredata predataEntity) {
		this.predataEntity = predataEntity;
	}

	public DataResRowPredata getPredataEntityForm() {
		return predataEntityForm;
	}

	public void setPredataEntityForm(DataResRowPredata predataEntityForm) {
		this.predataEntityForm = predataEntityForm;
	}

}
