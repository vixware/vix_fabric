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
import com.vix.common.orginialMeta.entity.OrginialBaseMetaDataCategory;
import com.vix.common.orginialMeta.service.IOrginialBaseMetaDataCategoryService;
import com.vix.common.orginialMeta.vo.OrginialBaseMetaDataCategoryImpVo;
import com.vix.core.utils.JSonUtils;
import com.vix.core.utils.excel.ExcelUtil;
import com.vix.core.web.Pager;

@Controller
@Scope("request")
public class OrginialBaseMetaDataCategoryAction extends BaseSecAction {
	
	@Autowired
	private IOrginialBaseMetaDataCategoryService baseMetaDataCategoryService;
	
	private String id;
	
	private String categoryName;
	
	private OrginialBaseMetaDataCategory entity;
	
	private OrginialBaseMetaDataCategory entityForm;
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			//params.put("roleCode,"+SearchCondition.NOEQUAL, BizConstant.ROLE_SUPERADMIN);
			//roleName
			if(StringUtils.isNotEmpty(categoryName)){
				categoryName = decode(categoryName, "UTF-8");
				params.put("categoryName", "%"+categoryName+"%");
			}
			
			Pager pager = baseMetaDataCategoryService.findBaseMetaDataCategoryPager(getPager(), params);
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
				entity = baseMetaDataCategoryService.findEntityById(OrginialBaseMetaDataCategory.class, id);
			}else{
				entity = new OrginialBaseMetaDataCategory();
			}
			//getRequest().setAttribute("ggtz", BizConstant.COMMON_BULLETIN);
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
			
			baseMetaDataCategoryService.saveOrUpdateBaseMetaDataCategory(entityForm);
			
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
			baseMetaDataCategoryService.deleteById(OrginialBaseMetaDataCategory.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 导入元数据分类
	 */
	public void importFile() {
		Map<String,String> msgMap = new HashMap<String, String>();
		FileInputStream fis = null;
		
		try{	
			//System.out.println(taskFileToUpload);
			 //getResponse().setStatus( getResponse().SC_OK);
			//returnJson(true,"",RESULT_MESSAGE_SUCCESS,"");
			/*String fileName = taskFileToUpload.getName();
			System.out.println(fileName+"--"+taskFileToUploadFileName);
			*/
			if(fileToUpload==null){
				msgMap.put("success", "0");
				msgMap.put("error", "没有选择文件!");
			}else{
				//String fileName = fileAtt.getName();
				//String attType = BizConstant.COMMON_ATT_EMAIL_ATT;
				//isTmp = (isTmp==null)?true:isTmp;
				//String bizPath = FileUtil.FILE_STORE_MAP.get(fileAttType);
				
				//attachmentService.saveForUploadAtt(bizPath, fileAttFileName, fileAtt,businessTag, fileAttType, dataId, isTmp);
				
				fis = new FileInputStream(fileToUpload);
				ExcelUtil util = new ExcelUtil();
				List<OrginialBaseMetaDataCategoryImpVo> voList = util.importExcel(OrginialBaseMetaDataCategoryImpVo.class,"元数据对象分类", fis);
					
				//authorityService.saveForImportAuthority(voList);
				baseMetaDataCategoryService.saveForImportBaseMetaDataCategory(voList);
				
				msgMap.put("success", "1");
				msgMap.put("msg", "导入成功!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			msgMap.put("success", "0");
			msgMap.put("error", e.getMessage());
		}  finally{
			if(fis!=null){
				IOUtils.closeQuietly(fis);
			}
		}
		String reMsg = JSonUtils.makeMapToJson(msgMap);
		//renderText(reMsg.toString());
		//render("text/html", reMsg);
		renderHtml(reMsg);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public OrginialBaseMetaDataCategory getEntity() {
		return entity;
	}

	public void setEntity(OrginialBaseMetaDataCategory entity) {
		this.entity = entity;
	}

	public OrginialBaseMetaDataCategory getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(OrginialBaseMetaDataCategory entityForm) {
		this.entityForm = entityForm;
	}

}
