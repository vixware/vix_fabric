package com.vix.sales.credit.action;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.sales.credit.entity.CreditControl;
import com.vix.sales.credit.entity.CreditControlItem;

@Controller
@Scope("prototype")
public class CreditControlItemAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	
	private String id;
	private CreditControlItem creditControlItem;
	private String pageNo;
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(null != id && !"".equals(id)){
				creditControlItem = baseHibernateService.findEntityById(CreditControlItem.class,id);
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
			if(null != creditControlItem.getId()){
				isSave = false;
			}else{
				creditControlItem.setCreateTime(new Date());
				loadCommonData(creditControlItem);
			}
			baseHibernateService.merge(creditControlItem);
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
			CreditControlItem pb = baseHibernateService.findEntityById(CreditControlItem.class,id);
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
	
	public void getCreditControlItemJson(){
		try {
			String json = "";
			String id = getRequestParameter("id");
			if(null != id && !"".equals(id)){
				CreditControl cc = baseHibernateService.findEntityById(CreditControl.class,id);
				if(null != cc){
					json = convertListToJson(new ArrayList<CreditControlItem>(cc.getCreditControlItems()),cc.getCreditControlItems().size(),"creditControl");
				}
			}
			if(!"".equals(json)){
				renderJson(json);
			}else{
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public CreditControlItem getCreditControlItem() {
		return creditControlItem;
	}

	public void setCreditControlItem(CreditControlItem creditControlItem) {
		this.creditControlItem = creditControlItem;
	}
}