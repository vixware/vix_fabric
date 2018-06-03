package com.vix.oa.treegridEuTreegrid.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.BizConstant;
import com.vix.oa.bulletin.entity.AnnouncementNotification;
import com.vix.oa.bulletin.service.IAnnouncementNotificationService;

@Controller
@Scope("prototype")
public class TreegridEuTreegridAction extends BaseAction {

	private static final long serialVersionUID = 2702948395608591987L;

	@Autowired
	private IAnnouncementNotificationService announcementNotificationService;
	
	private String id;
	
	private String title;
	
	private AnnouncementNotification entity;
	
	private AnnouncementNotification entityForm;
	
	/** 获取列表数据  */
	public String goSingleList(){
		/*try {
			Map<String,Object> params = new HashMap<String, Object>();
			//params.put("roleCode,"+SearchCondition.NOEQUAL, BizConstant.ROLE_SUPERADMIN);
			//roleName
			if(StringUtils.isNotEmpty(title)){
				params.put("title", "%"+title+"%");
			}
			
			Pager pager = announcementNotificationService.findAnnouncementNotificationPager(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}*/
		return GO_SINGLE_LIST;
	}
 
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				entity = announcementNotificationService.findEntityById(AnnouncementNotification.class, id);
			}else{
				entity = new AnnouncementNotification();
			}
			getRequest().setAttribute("ggtz", BizConstant.COMMON_BULLETIN);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		/*boolean isSave = true;
		try {
			if(null != entityForm.getId()){
				isSave = false;
			}
			
			announcementNotificationService.saveOrUpdateBulletin(entityForm);
			
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}*/
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			announcementNotificationService.deleteById(AnnouncementNotification.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	public String goChooseOrganization(){
	return "goChooseOrganization";
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

	public AnnouncementNotification getEntity() {
		return entity;
	}

	public void setEntity(AnnouncementNotification entity) {
		this.entity = entity;
	}

	public AnnouncementNotification getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(AnnouncementNotification entityForm) {
		this.entityForm = entityForm;
	}

	

	
	
}
