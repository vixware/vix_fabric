package com.vix.common.security.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.VixSecAction;
import com.vix.common.security.entity.Resource;
import com.vix.common.security.service.IResourceService;
import com.vix.core.web.Pager;

@Controller
@Scope("request")
public class ResourceAction extends VixSecAction{

	private static final long serialVersionUID = 1L;

	@Autowired
	private IResourceService resourceService;
	
	private String searchResourceName;
	
	private String id;

	private Resource entity;
	
	private Resource entityForm;
	
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(searchResourceName)){
				searchResourceName = decode(searchResourceName, "UTF-8");
				params.put("searchResourceName", "%"+searchResourceName+"%");
			}
			
			//Pager pager = resourceService.findPagerByHqlConditions(getPager(), Resource.class, params);
			
			//Pager pager = resourceService.findPagerByHqlConditions(getPager(), Resource.class, params);
			Pager pager = resourceService.findResourcePager(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goSubSingleList(){
		try {
			Map<String,Object> params = getParams();
			//getPager().setPageSize(6);
			//Pager pager = resourceService.findPagerByHqlConditions(getPager(), Resource.class, params);
			Pager pager = resourceService.findResourcePager(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}
 
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
				entity = resourceService.findEntityById(Resource.class, id);
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
			resourceService.merge(entityForm);
			//resourceService.mergeOriginal(entityForm);
			
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
			resourceService.deleteById(Resource.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	public String goChooseResource(){
		return "goChooseResource";
	}
	
	/**
	 * 
	 * @return
	 */
	public String goChooseResourceList(){
		try {
			Map<String,Object> params =getParams();
			//Pager pager = resourceService.findPagerByHqlConditions(getPager(), Resource.class, params); setPager(pager);
			Pager pager = resourceService.findResourceForSelect(getPager(),params);
			setPager(pager);
			
			/*List rList = pager.getResultList();
			for(Object rr:rList){
				if(rr instanceof Resource){
					Resource rrr = (Resource)rr;
					System.out.println(rrr.getId());
				}
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}
	    return "goChooseResourceList";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Resource getEntity() {
		return entity;
	}

	public void setEntity(Resource entity) {
		this.entity = entity;
	}

	public Resource getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(Resource entityForm) {
		this.entityForm = entityForm;
	}

	public String getSearchResourceName() {
		return searchResourceName;
	}

	public void setSearchResourceName(String searchResourceName) {
		this.searchResourceName = searchResourceName;
	}
	
}
