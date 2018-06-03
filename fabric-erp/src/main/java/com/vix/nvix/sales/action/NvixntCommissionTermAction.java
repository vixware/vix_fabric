package com.vix.nvix.sales.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.sales.commission.entity.CommissionTerm;
@Controller
@Scope("prototype")
public class NvixntCommissionTermAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommissionTerm commissionTerm;
	private String id;
	
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String name = getDecodeRequestParameter("name");
			if(StringUtils.isNotEmpty(name)) {
				params.put("name,"+SearchCondition.ANYLIKE, name);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, CommissionTerm.class, params);
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id)){
				commissionTerm = baseHibernateService.findEntityById(CommissionTerm.class,id);
			}else {
				commissionTerm = new CommissionTerm();
				commissionTerm.setCode(VixUUID.createCode(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(commissionTerm.getId())){
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
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			CommissionTerm pb = baseHibernateService.findEntityById(CommissionTerm.class,id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	public String goChooseCommissionPlanItem() {
		return "goChooseCommissionPlanItem";
	}
	public CommissionTerm getCommissionTerm() {
		return commissionTerm;
	}
	public void setCommissionTerm(CommissionTerm commissionTerm) {
		this.commissionTerm = commissionTerm;
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
}
