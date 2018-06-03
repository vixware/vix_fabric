package com.vix.common.security.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.VixSecAction;
import com.vix.common.security.entity.IndexPageDataConfig;
import com.vix.common.security.service.IIndexPageDataConfigService;
import com.vix.core.web.Pager;

@Controller
@Scope("request")
public class IndexPdcAction extends VixSecAction{

	private static final long serialVersionUID = 1L;

	@Autowired
	private IIndexPageDataConfigService indexPageDataConfigService;
	
	private String name;
	private String divId;
	
	private String id;
	
	private IndexPageDataConfig entity;
	
	private IndexPageDataConfig entityForm;
	
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			
			Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(name)){
				name = decode(name, "UTF-8");
				params.put("name", "%"+name+"%");
			}
			if(StringUtils.isNotEmpty(divId)){
				params.put("divId", "%"+name+"%");
			}
			Pager pager = indexPageDataConfigService.findIndexPageDataConfigPager(getPager(), params);
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
				entity = indexPageDataConfigService.findEntityById(IndexPageDataConfig.class, id);
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
			if (indexPageDataConfigService.isEntityExist(IndexPageDataConfig.class,entityForm.getId(), "divId", entityForm.getDivId())) {
				setMessage("DIV的ID已经存在！");
				return "update";
			}
			if(null != entityForm.getId()){
				isSave = false;
			}
			
			indexPageDataConfigService.saveIndexPageDataConfig(entityForm);
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
			indexPageDataConfigService.deleteById(IndexPageDataConfig.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	public IIndexPageDataConfigService getIndexPageDataConfigService() {
		return indexPageDataConfigService;
	}

	public void setIndexPageDataConfigService(
			IIndexPageDataConfigService indexPageDataConfigService) {
		this.indexPageDataConfigService = indexPageDataConfigService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IndexPageDataConfig getEntity() {
		return entity;
	}

	public void setEntity(IndexPageDataConfig entity) {
		this.entity = entity;
	}

	public IndexPageDataConfig getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(IndexPageDataConfig entityForm) {
		this.entityForm = entityForm;
	}
	
}
