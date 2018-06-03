package com.vix.common.orginialMeta.action;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseSecAction;
import com.vix.common.orginialMeta.entity.OrginialBaseMetaData;
import com.vix.common.orginialMeta.entity.OrginialBaseMetaDataCategory;
import com.vix.common.orginialMeta.service.IOrginialBaseMetaDataCategoryService;
import com.vix.common.orginialMeta.service.IOrginialBaseMetaDataService;
import com.vix.common.orginialMeta.vo.OrginialBaseMetaDataDefineImpVo;
import com.vix.common.orginialMeta.vo.OrginialBaseMetaDataImpVo;
import com.vix.core.utils.JSonUtils;
import com.vix.core.utils.excel.ExcelUtil;
import com.vix.core.web.Pager;

@Controller
@Scope("request")
public class OrginialBaseMetaDataAction extends BaseSecAction {
	
	@Autowired
	private IOrginialBaseMetaDataService baseMetaDataService;
	
	@Autowired
	private IOrginialBaseMetaDataCategoryService baseMetaDataCategoryService;
	
	/** 元数据分类列表 */
	private List<OrginialBaseMetaDataCategory> baseMetaDataCategoryList;
	
	private String id;
	
	private String metaDataName;
	
	private String categoryId= "-1";
	
	private OrginialBaseMetaData entity;
	
	private OrginialBaseMetaData entityForm;
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			//params.put("roleCode,"+SearchCondition.NOEQUAL, BizConstant.ROLE_SUPERADMIN);
			//roleName
			if(StringUtils.isNotEmpty(metaDataName)){
				metaDataName = decode(metaDataName, "UTF-8");
				params.put("metaDataName", "%"+metaDataName+"%");
			}
			
			if(StringUtils.isNotEmpty(categoryId) && !categoryId.equals("0") && !categoryId.equals("-1")){//if(categoryId!=null && categoryId>0){
				params.put("categoryId", categoryId);
			}
			
			Pager pager = baseMetaDataService.findBaseMetaDataPager(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
 
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			//元数据分类
			//baseMetaDataCategoryList=baseMetaDataService.findAllByEntityClassNoTenantId(BaseMetaDataCategory.class);
			//baseMetaDataCategoryList=baseMetaDataService.findAllByConditions(BaseMetaDataCategory.class, new HashMap<String,Object>());
			baseMetaDataCategoryList=baseMetaDataCategoryService.findAllBaseMetaDataCategory();
			
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
				entity = baseMetaDataService.findEntityById(OrginialBaseMetaData.class, id);
			}else{
				entity = new OrginialBaseMetaData();
			}
			//getRequest().setAttribute("ggtz", BizConstant.COMMON_BULLETIN);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != entityForm.getId()){
				isSave = false;
			}
			
			OrginialBaseMetaData baseMetaData = baseMetaDataService.saveOrUpdateBaseMetaData(entityForm);
			Map<String,Object> msgObj = new HashMap<String, Object>();
            if(isSave){
            	msgObj.put("msg", SAVE_SUCCESS);
            }else{
            	msgObj.put("msg", UPDATE_SUCCESS);
            }
            msgObj.put("objId", baseMetaData.getId());
            
           String resMsg = JSonUtils.toJSon(msgObj);
           //renderJson(resMsg);
           renderText(resMsg);
            
		} catch (Exception e) {
			e.printStackTrace();
			/*if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}*/
		}
		//return UPDATE;
	}

	public void findMetaDataByName() {
		try {
			Map<String,Object> msgObj = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(metaDataName)){
				//BaseMetaData baseMetaData = baseMetaDataService.findEntityByAttributeNoTenantId(BaseMetaData.class, "metaDataName", metaDataName);
				OrginialBaseMetaData baseMetaData = baseMetaDataService.findEntityByAttribute(OrginialBaseMetaData.class, "metaDataName", metaDataName);
				msgObj.put("objId", baseMetaData.getId());
			}else{
				msgObj.put("objId", "");
			}
		
			String resMsg = JSonUtils.toJSon(msgObj);
			renderText(resMsg);
		} catch (Exception e) {
			e.printStackTrace();
			/*if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}*/
		}
	}
	
	/** 处理删除操作 */
	public String deleteById() {
		try {
			baseMetaDataService.deleteById(OrginialBaseMetaData.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	
	public void findTreeToJson() {
		try {
			//List<BaseMetaDataCategory> listMetaDataCate = baseMetaDataService.findAllByEntityClassNoTenantId(BaseMetaDataCategory.class);
			//List<BaseMetaDataCategory> listMetaDataCate = baseMetaDataService.findAllByEntityClass(BaseMetaDataCategory.class);
			
			List<OrginialBaseMetaDataCategory> listMetaDataCate = baseMetaDataCategoryService.findAllBaseMetaDataCategory();
			
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listMetaDataCate.size();
			for (int i = 0; i < count; i++) {
				OrginialBaseMetaDataCategory metaCate = listMetaDataCate.get(i);
				strSb.append("{id:\"");
				strSb.append(metaCate.getId());
				strSb.append("\",name:\"");
				strSb.append(metaCate.getCategoryName());
				strSb.append("\",isParent:false}");
					
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * 导入元数据对象和属性
	 */
	public void importFile() {
		List<OrginialBaseMetaDataImpVo> metadataList = null ;
		List<OrginialBaseMetaDataDefineImpVo> metadataDefineList =null;
		
		Map<String,String> msgMap = new HashMap<String, String>();
		FileInputStream fis = null;
		
		
		if(fileToUpload==null){
			msgMap.put("success", "0");
			msgMap.put("error", "没有选择文件!");
			renderHtml(JSonUtils.makeMapToJson(msgMap));
		}
		
		try{	
			fis = new FileInputStream(fileToUpload);
			
			ExcelUtil metadataUtil = new ExcelUtil();
			metadataList = metadataUtil.importExcel(OrginialBaseMetaDataImpVo.class,"元数据对象", fis);
			
		} catch (Exception e) {
			e.printStackTrace();
			msgMap.put("success", "0");
			msgMap.put("error", e.getMessage());
			renderHtml(JSonUtils.makeMapToJson(msgMap));
		}  finally{
			if(fis!=null){
				IOUtils.closeQuietly(fis);
			}
		}
		
		
		try{	
			fis = new FileInputStream(fileToUpload);
			
			ExcelUtil<OrginialBaseMetaDataDefineImpVo> metadataDefineUtil = new ExcelUtil<OrginialBaseMetaDataDefineImpVo>();
			//metadataDefineUtil = new ExcelUtil<BaseMetaDataDefineImpVo>(BaseMetaDataDefineImpVo.class);
			metadataDefineList = metadataDefineUtil.importExcel(OrginialBaseMetaDataDefineImpVo.class,"元数据对象基本属性", fis);
			
		} catch (Exception e) {
			e.printStackTrace();
			msgMap.put("success", "0");
			msgMap.put("error", e.getMessage());
			renderHtml(JSonUtils.makeMapToJson(msgMap));
		}  finally{
			if(fis!=null){
				IOUtils.closeQuietly(fis);
			}
		}
		
		try{	
			baseMetaDataService.saveForImportBaseMetaData(metadataList, metadataDefineList, null);
		} catch (Exception e) {
			e.printStackTrace();
			msgMap.put("success", "0");
			msgMap.put("error", e.getMessage());
			renderHtml(JSonUtils.makeMapToJson(msgMap));
		}  
		
		msgMap.put("success", "1");
		msgMap.put("msg", "导入成功!");
		String reMsg = JSonUtils.makeMapToJson(msgMap);
		renderHtml(reMsg);
	}
	
	
	/**
	 * 元数据初始化
	 * @return
	 */
	public String initSystemMetadata() {
		try {

			baseMetaDataService.saveForInitHbmMetadata();
			setMessage(OPER_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(OPER_FAIL);
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


	public List<OrginialBaseMetaDataCategory> getBaseMetaDataCategoryList() {
		return baseMetaDataCategoryList;
	}

	public void setBaseMetaDataCategoryList(
			List<OrginialBaseMetaDataCategory> baseMetaDataCategoryList) {
		this.baseMetaDataCategoryList = baseMetaDataCategoryList;
	}

	public OrginialBaseMetaData getEntity() {
		return entity;
	}

	public void setEntity(OrginialBaseMetaData entity) {
		this.entity = entity;
	}

	public OrginialBaseMetaData getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(OrginialBaseMetaData entityForm) {
		this.entityForm = entityForm;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

}
