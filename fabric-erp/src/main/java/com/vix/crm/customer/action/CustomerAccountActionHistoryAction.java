package com.vix.crm.customer.action;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.crm.project.entity.ActionHistory;

@Controller
@Scope("prototype")
public class CustomerAccountActionHistoryAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String ids;
	private ActionHistory actionHistory;
	private String pageNo;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Pager pager = getPager();
			if(null != id && !"".equals(id)){
				List<ActionHistory> pcList = baseHibernateService.findAllByEntityClassAndAttribute(ActionHistory.class, "customerAccount.id", id);
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
				actionHistory = baseHibernateService.findEntityById(ActionHistory.class,id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
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
			if(null != actionHistory.getId()){
				isSave = false;
			}else{
				actionHistory.setCreateTime(new Date());
				loadCommonData(actionHistory);
			}
			if(null == actionHistory.getCustomerAccount() || null == actionHistory.getCustomerAccount().getId() || !actionHistory.getCustomerAccount().getId().equals("") || !actionHistory.getCustomerAccount().getId().equals("0")){
				actionHistory.setCustomerAccount(null);
			}
			actionHistory = baseHibernateService.merge(actionHistory);
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
			ActionHistory pb = baseHibernateService.findEntityById(ActionHistory.class,id);
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public ActionHistory getActionHistory() {
		return actionHistory;
	}

	public void setActionHistory(ActionHistory actionHistory) {
		this.actionHistory = actionHistory;
	}
}
