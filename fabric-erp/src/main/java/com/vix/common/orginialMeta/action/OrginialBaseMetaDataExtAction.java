package com.vix.common.orginialMeta.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseSecAction;
import com.vix.common.orginialMeta.entity.OrginialBaseMetaDataExtend;
import com.vix.common.orginialMeta.service.IOrginialBaseMetaDataDefineService;
import com.vix.core.constant.BizConstant;

/**
 * 元数据扩展属性Action
 * @author Administrator
 *
 */
@Controller
@Scope("request")
public class OrginialBaseMetaDataExtAction extends BaseSecAction {
	
	@Autowired
	private IOrginialBaseMetaDataDefineService baseMetaDataDefineService;
	
	/** 元数据对象id */
	private String metaDataId;
	
	
	private String id;
	
	private String metaDataName;
	
	private OrginialBaseMetaDataExtend entity;
	
	private OrginialBaseMetaDataExtend entityForm;
	
	/** 获取列表数据  */
	public void goSingleList(){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			//params.put("roleCode,"+SearchCondition.NOEQUAL, BizConstant.ROLE_SUPERADMIN);
			//roleName
			if(StringUtils.isNotEmpty(metaDataName)){
				params.put("metaDataName", "%"+metaDataName+"%");
			}
			
			metaDataId= (StringUtils.isEmpty(metaDataId)?"-1":metaDataId); 
			params.put("metaDataId", metaDataId);
			
			/*Pager pager = baseMetaDataDefineService.findBaseMetaDataDefinePager(getPager(), params);
			setPager(pager);*/
			List<OrginialBaseMetaDataExtend> baseMetaDataDefineList = baseMetaDataDefineService.findBaseMetaDataExtendList(params);
			Long dataSize = baseMetaDataDefineList==null?0L:baseMetaDataDefineList.size();
			
			renderHtml(convertListToJsonNoPage(baseMetaDataDefineList, dataSize));
		}catch(Exception e){
			e.printStackTrace();
		}
		//return GO_SINGLE_LIST;
	}
 
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			//元数据分类
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
				entity = baseMetaDataDefineService.findEntityById(OrginialBaseMetaDataExtend.class, id);
			}else{
				entity = new OrginialBaseMetaDataExtend();
				entity.setIsCollectionType("0");
				entity.setIsSelectView("0");
			}
			getRequest().setAttribute("sjlx", BizConstant.COMMON_METADATA_DATATYPE);
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
			
			baseMetaDataDefineService.saveOrUpdateBaseMetaDataExt(entityForm, metaDataId);
			
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
			baseMetaDataDefineService.deleteById(OrginialBaseMetaDataExtend.class, id);
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

	public String getMetaDataName() {
		return metaDataName;
	}

	public void setMetaDataName(String metaDataName) {
		this.metaDataName = metaDataName;
	}

	public String getMetaDataId() {
		return metaDataId;
	}

	public void setMetaDataId(String metaDataId) {
		this.metaDataId = metaDataId;
	}

	public OrginialBaseMetaDataExtend getEntity() {
		return entity;
	}

	public void setEntity(OrginialBaseMetaDataExtend entity) {
		this.entity = entity;
	}

	public OrginialBaseMetaDataExtend getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(OrginialBaseMetaDataExtend entityForm) {
		this.entityForm = entityForm;
	}

}
