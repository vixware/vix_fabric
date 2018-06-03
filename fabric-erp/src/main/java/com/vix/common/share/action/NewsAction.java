package com.vix.common.share.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.share.entity.News;
import com.vix.common.share.service.INewsService;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class NewsAction extends BaseAction {
	
	@Autowired
	private INewsService newsService;
	
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	
	private String id;
	
	private String title;
	
	private News entity;
	
	private News entityForm;
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			//params.put("roleCode,"+SearchCondition.NOEQUAL, BizConstant.ROLE_SUPERADMIN);
			//roleName
			if(StringUtils.isNotEmpty(title)){
				params.put("title", "%"+title+"%");
			}
			
			Pager pager = newsService.findNewsPager(getPager(), params);
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
				entity = newsService.findEntityById(News.class, id);
			}else{
				entity = new News();
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
			initEntityBaseController.initEntityBaseAttribute(entityForm);
			newsService.saveOrUpdateNews(entityForm);
			
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
			newsService.deleteById(News.class, id);
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public News getEntity() {
		return entity;
	}

	public void setEntity(News entity) {
		this.entity = entity;
	}

	public News getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(News entityForm) {
		this.entityForm = entityForm;
	}

	
}
