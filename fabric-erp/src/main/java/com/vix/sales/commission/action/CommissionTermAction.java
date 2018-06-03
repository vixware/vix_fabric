package com.vix.sales.commission.action;

import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.sales.commission.entity.CommissionTerm;

@Controller
@Scope("prototype")
public class CommissionTermAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	
	private String id;
	private CommissionTerm commissionTerm;
	private String pageNo;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), CommissionTerm.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(null != id && !"".equals(id)){
				commissionTerm = baseHibernateService.findEntityById(CommissionTerm.class,id);
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
			if(null != commissionTerm.getId()){
				isSave = false;
				commissionTerm.setUpdateTime(new Date());
			}else{
				commissionTerm.setCreateTime(new Date());
				loadCommonData(commissionTerm);
			}
			commissionTerm = baseHibernateService.merge(commissionTerm);
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
			CommissionTerm pb = baseHibernateService.findEntityById(CommissionTerm.class,id);
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

	public void getCommissionTermItemJson(){
		try {
			String json = "";
			String id = getRequestParameter("id");
			if(null != id && !"".equals(id)){
				CommissionTerm cp = baseHibernateService.findEntityById(CommissionTerm.class, id);
				if(null != cp){
					//json = convertListToJson(new ArrayList<CommissionTermItem>(cp.getCommissionTermItems()),cp.getCommissionTermItems().size(),"commissionTerm");
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

	public CommissionTerm getCommissionTerm() {
		return commissionTerm;
	}

	public void setCommissionTerm(CommissionTerm commissionTerm) {
		this.commissionTerm = commissionTerm;
	}
}