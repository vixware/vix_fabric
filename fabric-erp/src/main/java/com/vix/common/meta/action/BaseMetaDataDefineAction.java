package com.vix.common.meta.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseSecAction;
import com.vix.common.meta.entity.BaseMetaDataDefine;
import com.vix.common.meta.entity.BaseMetaDataExtend;
import com.vix.common.meta.service.IBaseMetaDataDefineService;
import com.vix.core.constant.BizConstant;
import com.vix.core.utils.JSonUtils;

/**
 * 元数据基本属性
 * @author Administrator
 *
 */
@Controller
@Scope("request")
public class BaseMetaDataDefineAction extends BaseSecAction {
	
	@Autowired
	private IBaseMetaDataDefineService baseMetaDataDefineService;
	
	/** 元数据对象id */
	private String metaDataId;
	
	
	private String id;
	
	private String metaDataName;
	
	private BaseMetaDataDefine entity;
	
	private BaseMetaDataDefine entityForm;
	
	/** 扩展属性的保存接收 */
	private List<String> jsonParam;
	
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
			List<BaseMetaDataDefine> baseMetaDataDefineList = baseMetaDataDefineService.findBaseMetaDataDefineList(params);
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
				entity = baseMetaDataDefineService.findEntityById(BaseMetaDataDefine.class, id);
			}else{
				entity = new BaseMetaDataDefine();
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
			
			baseMetaDataDefineService.saveOrUpdateBaseMetaDataDefine(entityForm,metaDataId);
			
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
			baseMetaDataDefineService.deleteById(BaseMetaDataDefine.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	
	//属性编辑页面属性对应的元数据对象的可编辑列表
	public void goPropertyObjList(){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			//params.put("roleCode,"+SearchCondition.NOEQUAL, BizConstant.ROLE_SUPERADMIN);
			//roleName
			List<BaseMetaDataDefine> baseMetaDataDefineList = null;
			if(StringUtils.isNotEmpty(metaDataName)){
				params.put("metaDataName", metaDataName);
				
				baseMetaDataDefineList = baseMetaDataDefineService.findBaseMetaDataDefineListByProName(params);
			}
			if(baseMetaDataDefineList==null){
				baseMetaDataDefineList= new ArrayList<BaseMetaDataDefine>();
			}
			
			Long dataSize = baseMetaDataDefineList==null?0L:baseMetaDataDefineList.size();
			
			renderHtml(convertListToJsonNoPage(baseMetaDataDefineList, dataSize));
		}catch(Exception e){
			e.printStackTrace();
		}
		//return GO_SINGLE_LIST;
	}
	
	//属性编辑页面属性对应的元数据对象的可编辑列表
	public void goExtPropertyObjList(){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			//params.put("roleCode,"+SearchCondition.NOEQUAL, BizConstant.ROLE_SUPERADMIN);
			//roleName
			List<BaseMetaDataExtend> baseMetaDataExtList = null;
			if(StringUtils.isNotEmpty(metaDataName)){
				params.put("metaDataName", metaDataName);
				
				baseMetaDataExtList = baseMetaDataDefineService.findBaseMetaDataExtByMetaNameList(params);
			}
			if(baseMetaDataExtList==null){
				baseMetaDataExtList = new ArrayList<BaseMetaDataExtend>();
			}
			
			Long dataSize = baseMetaDataExtList==null?0L:baseMetaDataExtList.size();
			renderHtml(convertListToJsonNoPage(baseMetaDataExtList, dataSize));
		}catch(Exception e){
			e.printStackTrace();
		}
		//return GO_SINGLE_LIST;
	}
	/**
	 * 保存扩展属性
	 * @return
	 */
	public String saveOrUpdateExt() {
		boolean isSave = true;
		try {
			
			//baseMetaDataDefineService.saveOrUpdateBaseMetaDataDefine(entityForm,metaDataId);
			List<BaseMetaDataExtend> allExtGridList = new LinkedList<BaseMetaDataExtend>();
			if (jsonParam != null && jsonParam.size() > 0) {
				for (String crvStr : jsonParam) {
					//String str1 = crvStr.trim().replace("\n", "").replace("\\", "\\\\").replace("\"", "\\\"");
					//String str = str1.replace("'", "\"");
					
					//final JSONParser parser = new JSONParser();
					//parser.parse(new StringReader(str));
					//BaseMetaDataExtend cr = (BaseMetaDataExtend) JSONMapper.toJava(parser.parse(new StringReader(str)), BaseMetaDataExtend.class);
					//allCrvList.add(cr);
					BaseMetaDataExtend ext = JSonUtils.readValue(crvStr, BaseMetaDataExtend.class);
					allExtGridList.add(ext);
				}
			}
			if(!allExtGridList.isEmpty() && metaDataId!=null){
				baseMetaDataDefineService.saveOrUpdateBaseMetaDataExt(allExtGridList, metaDataId);
			}
			
			setMessage(OPER_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(OPER_FAIL);
		}
		return UPDATE;
	}
	
	/**
	 * 删除扩展属性
	 * @return
	 */
	public String deleteExtById() {
		try {
			baseMetaDataDefineService.deleteById(BaseMetaDataExtend.class, id);
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

	public BaseMetaDataDefine getEntity() {
		return entity;
	}

	public void setEntity(BaseMetaDataDefine entity) {
		this.entity = entity;
	}

	public BaseMetaDataDefine getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(BaseMetaDataDefine entityForm) {
		this.entityForm = entityForm;
	}

	public String getMetaDataId() {
		return metaDataId;
	}

	public void setMetaDataId(String metaDataId) {
		this.metaDataId = metaDataId;
	}

	public List<String> getJsonParam() {
		return jsonParam;
	}

	public void setJsonParam(List<String> jsonParam) {
		this.jsonParam = jsonParam;
	}

}
