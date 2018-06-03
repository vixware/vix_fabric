package com.vix.common.securityDra.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseSecAction;
import com.vix.common.security.entity.DataResRowPolicyObj;
import com.vix.common.securityDca.vo.MetaDataProperty;
import com.vix.common.securityDra.service.IDataResRowPolicyObjService;
import com.vix.common.securityDra.vo.rule.RowDataGroup;
import com.vix.common.securityDra.vo.rule.RowDataRule;
import com.vix.core.constant.DataRowOperator;
import com.vix.core.web.Pager;

import flexjson.JSONDeserializer;

/**
 * 数据权限配置action
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class DataResRowPolicyObjAction extends BaseSecAction {

	@Autowired
	private IDataResRowPolicyObjService dataResRowPolicyObjService;
	
	private List<DataResRowPolicyObj> dataResRowPolicyObjList;
	
	private String policyName;
	
	private DataResRowPolicyObj entity;
	
	private DataResRowPolicyObj entityForm;
	
	private String id;
	
	private List<MetaDataProperty> mdpList;
	
	
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
			if(StringUtils.isNotEmpty(policyName)){
				policyName = decode(policyName, "UTF-8");
				params.put("metaDataViewName", policyName);
			}
			Pager pager = dataResRowPolicyObjService.findPolicyObjPager(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
 
	/** 跳转至修改页面 */
	public String goSaveOrUpdate() {
		try {
			
			//dataResRowPolicyObjList =  dataResRowOwnerService.findAllByEntityClass(DataResRowPolicy.class);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
				entity = dataResRowPolicyObjService.findEntityById(DataResRowPolicyObj.class, id);
				
				/*Organization orgTmp = organizationService.findOrganizationByCompCode(entity.getCompanyCode());
				parentId = orgTmp.getId();
				parentTreeName=orgTmp.getOrgName();*/
				
			}else{
				entity=new DataResRowPolicyObj();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 业务对象属性 */
	private List<String> propertyName;
	/** 业务对象满足条件的操作符 */
	private List<String> propertyOperator;
	/** 业务对象的值 */
	private List<String> mdpValue;
	/**  业务对象的值 隐藏域 */
	private List<String> mdpRealValue;
	/**  业务对象属性的类型*/
	private List<String> propertyType;
	/**  业务对象的规则间的操作符*/
	private List<String> ruleOpertator;
	
	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != entityForm.getId()){
                isSave = false;
            }
            String addDataRowMetaDataIds = getRequest().getParameter("addDataRowMetaDataIds");
            dataResRowPolicyObjService.saveOrUpdate(addDataRowMetaDataIds,entityForm);
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
	
	public String goPolicyObj() {
		try {
			//配置信息
			entity = dataResRowPolicyObjService.findEntityById(DataResRowPolicyObj.class, id);
			
			// 元数据信息
			mdpList = dataResRowPolicyObjService.findMdbPropertyList(entity.getBaseMetaData().getId());
			
			//操作符号等
			getRequest().setAttribute("operMap", DataRowOperator.operateMap);
			getRequest().setAttribute("conditionMap", DataRowOperator.conditionMap);
			getRequest().setAttribute("typeMap", DataRowOperator.typeMap);
			
			//系统常量
			//mdpList.add(new MetaDataProperty("S","系统角色","{SysRole}"));
			//mdpList.add(new MetaDataProperty("S","系统用户","{SysUserAccount}"));
			
			List<MetaDataProperty> mdList = new LinkedList<MetaDataProperty>();
			/*
			
			mdpList.add(new MetaDataProperty("S","当前角色",DataRowConstant.SYS_CUR_ROLE));
			mdpList.add(new MetaDataProperty("S","当前账号",DataRowConstant.SYS_CUR_USERACCOUNT));
			mdpList.add(new MetaDataProperty("S","当前员工",DataRowConstant.SYS_CUR_EMPLOYEE));
			*/
			
			/*
			 * {SysUserAccount}
			是否从数据库加载？
			List<MetaDataProperty> mdList = dataResRowPolicyObjService.findMetaDataProperty();
			mdpList.addAll(mdList);
			*/
			
			//系统常量的选择事件
			Map<String,String> rowPolicyObjMap = new HashMap<String, String>();
			/*rowPolicyObjMap.put("{SysRole}", "/common/security/userAccountAction!goChooseRole.action?tag=choose");
			rowPolicyObjMap.put("{SysUserAccount}", "/common/security/userAccountAction!goChooseUserAccount.action?tag=choose");
			getRequest().setAttribute("rowPolicyObjMap", rowPolicyObjMap);
			*/
			for(MetaDataProperty mp:mdList){
				rowPolicyObjMap.put(mp.getProperty(), mp.getSysParamUrl());
			}
			getRequest().setAttribute("rowPolicyObjMap", rowPolicyObjMap);
			
			
			//反序列化为对象
			List<RowDataRule> rdrList = null;
			if(StringUtils.isNotEmpty(entity.getDataConfigRule())){
				RowDataGroup rdgTest = new JSONDeserializer<RowDataGroup>().deserialize(entity.getDataConfigRule(),RowDataGroup.class);
				rdrList = rdgTest.getRules();
			}else{
				rdrList = new LinkedList<RowDataRule>();
				for(MetaDataProperty mp:mdpList){
					//rdrList.add(new RowDataRule(mp.getProperty()));
					RowDataRule rdrTmp = new RowDataRule();
					rdrTmp.setFields(mp.getProperty());
					//rdrTmp.setIsCollectionType(mp.getIsCollectionType());
					//System.out.println(rdrTmp.getIsCollectionType());
				}
			}
			
			//System.out.println(rdrList.size());
			
			getRequest().setAttribute("metadataId", entity.getBaseMetaData().getId());
			getRequest().setAttribute("dataList", rdrList);
			//goChooseUserAccount
			
			//1 加载系统常量 
			
			//2 加载元数据权限配置信息   元数据属性和系统配置常量
			
			//3反序列化json对象
			/*
			if(null != id && id.longValue() > 0){
				entity = dataResRowPolicyObjService.findEntityById(DataResRowPolicyObj.class, id);
			}else{
				entity=new DataResRowPolicyObj();
			}*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dataRowConfig";
	}
	
	
	public String saveOrUpdateObjConfig() {
		boolean isSave = true;
		try {
			if(null != id){
				dataResRowPolicyObjService.saveOrUpdateObjConfig(id, propertyName, propertyOperator, mdpValue, mdpRealValue, propertyType, ruleOpertator);
            }
            renderText(SAVE_SUCCESS);
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
			dataResRowPolicyObjService.deleteById(DataResRowPolicyObj.class, id);
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

	public void setId(String id) {
		this.id = id;
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

	public List<DataResRowPolicyObj> getDataResRowPolicyObjList() {
		return dataResRowPolicyObjList;
	}

	public void setDataResRowPolicyObjList(
			List<DataResRowPolicyObj> dataResRowPolicyObjList) {
		this.dataResRowPolicyObjList = dataResRowPolicyObjList;
	}

	public DataResRowPolicyObj getEntity() {
		return entity;
	}

	public void setEntity(DataResRowPolicyObj entity) {
		this.entity = entity;
	}

	public DataResRowPolicyObj getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(DataResRowPolicyObj entityForm) {
		this.entityForm = entityForm;
	}

	public List<MetaDataProperty> getMdpList() {
		return mdpList;
	}

	public void setMdpList(List<MetaDataProperty> mdpList) {
		this.mdpList = mdpList;
	}

	public List<String> getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(List<String> propertyName) {
		this.propertyName = propertyName;
	}

	public List<String> getPropertyOperator() {
		return propertyOperator;
	}

	public void setPropertyOperator(List<String> propertyOperator) {
		this.propertyOperator = propertyOperator;
	}

	public List<String> getMdpValue() {
		return mdpValue;
	}

	public void setMdpValue(List<String> mdpValue) {
		this.mdpValue = mdpValue;
	}

	public List<String> getMdpRealValue() {
		return mdpRealValue;
	}

	public void setMdpRealValue(List<String> mdpRealValue) {
		this.mdpRealValue = mdpRealValue;
	}

	public List<String> getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(List<String> propertyType) {
		this.propertyType = propertyType;
	}

	public List<String> getRuleOpertator() {
		return ruleOpertator;
	}

	public void setRuleOpertator(List<String> ruleOpertator) {
		this.ruleOpertator = ruleOpertator;
	}

}
