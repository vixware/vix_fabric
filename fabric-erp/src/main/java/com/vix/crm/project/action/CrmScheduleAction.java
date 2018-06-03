package com.vix.crm.project.action;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.Schedule;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class CrmScheduleAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private Schedule schedule;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Pager pager = getPager();
			if(null != id && !"".equals(id)){
				List<Schedule> pcList = baseHibernateService.findAllByEntityClassAndAttribute(Schedule.class, "crmProject.id", id);
				pager.setResultList(pcList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(null != id && !"".equals(id)){
				schedule = baseHibernateService.findEntityById(Schedule.class,id);
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
			if(null != schedule.getId()){
				isSave = false;
			}else{
				schedule.setCreateTime(new Date());
				loadCommonData(schedule);
			}
			if(null == schedule.getCustomerAccount() || null == schedule.getCustomerAccount().getId() || !schedule.getCustomerAccount().getId().equals("") || !schedule.getCustomerAccount().getId().equals("0")){
				schedule.setCustomerAccount(null);
			}
			if(null == schedule.getExecutor() || null == schedule.getExecutor().getId() || !schedule.getExecutor().getId().equals("") || !schedule.getExecutor().getId().equals("0")){
				schedule.setExecutor(null);
			}
			schedule = baseHibernateService.merge(schedule);
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
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			Schedule pb = baseHibernateService.findEntityById(Schedule.class,id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("ec_brandNotExist"));
			}
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

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
}
