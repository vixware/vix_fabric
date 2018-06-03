package com.vix.drp.IntegralManagementsubsidiary.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.pointRecord.entity.PointRecord;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class IntegralManagementsubsidiaryAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	
	private String customerAccountId;
	@Override
	public String goList() {
		return GO_LIST;
	}

	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("createTime");
			String searchName = getDecodeRequestParameter("searchName");
			if(StringUtils.isNotEmpty(searchName)){
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, searchName);
			}
			String phone = getDecodeRequestParameter("phone");
			if(StringUtils.isNotEmpty(phone)){
				params.put("customerAccount.mobilePhone,"+SearchCondition.ANYLIKE, phone);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, PointRecord.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据用户ID查询积分记录
	 */
	public void goSingleListByCustomerById(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("createTime");
			if(StringUtils.isNotEmpty(customerAccountId)){
				params.put("customerAccount.id,"+SearchCondition.EQUAL, customerAccountId);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, PointRecord.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		return UPDATE;
	}

	public String goSearch() {
		return "goSearch";
	}

	public String getCustomerAccountId() {
		return customerAccountId;
	}

	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}
}
