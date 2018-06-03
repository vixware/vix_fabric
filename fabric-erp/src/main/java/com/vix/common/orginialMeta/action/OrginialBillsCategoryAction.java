package com.vix.common.orginialMeta.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.VixSecAction;
import com.vix.common.orginialMeta.entity.OrginialBillsCategory;
import com.vix.common.orginialMeta.service.IOrginialBillsCategoryService;
import com.vix.core.web.Pager;

@Controller
@Scope("request")
public class OrginialBillsCategoryAction extends VixSecAction{

	private static final long serialVersionUID = 1L;

	@Autowired
	private IOrginialBillsCategoryService orginialBillsCategoryService;
	
	private String categoryName;
	
	private String id;
	
	private OrginialBillsCategory entity;
	
	private OrginialBillsCategory entityForm;
	
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			
			Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(categoryName)){
				categoryName = decode(categoryName, "UTF-8");
				params.put("categoryName", "%"+categoryName+"%");
			}
			Pager pager = orginialBillsCategoryService.findOrginialBillsCategoryPager(getPager(), params);
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
				entity = orginialBillsCategoryService.findEntityById(OrginialBillsCategory.class, id);
			}
			//getRequest().setAttribute("moduleTypeMap", BizConstant.COMMON_SECURITY_RESTYPE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (orginialBillsCategoryService.isEntityExist(OrginialBillsCategory.class,entityForm.getId(), "categoryCode", entityForm.getCategoryCode())) {
				setMessage("模块编码已经存在！");
				return "update";
			}
			if(null != entityForm.getId()){
				isSave = false;
			}
			
			orginialBillsCategoryService.saveOrginialBillsCategory(entityForm);
			//保存
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
			orginialBillsCategoryService.deleteById(OrginialBillsCategory.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OrginialBillsCategory getEntity() {
		return entity;
	}

	public void setEntity(OrginialBillsCategory entity) {
		this.entity = entity;
	}

	public OrginialBillsCategory getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(OrginialBillsCategory entityForm) {
		this.entityForm = entityForm;
	}
	
}
