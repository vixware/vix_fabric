package com.vix.crm.member.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.member.controller.MemberLevelManagementController;
import com.vix.crm.member.entity.MemberLevel;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class MemberLevelManagementAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MemberLevelManagementController memberLevelManagementController;
	private String id;
	private String pageNo;

	private CustomerAccount customerAccount;
	private List<MemberLevel> memberLevelList;
	private String memberLevelId;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			memberLevelList = memberLevelManagementController.doListMemberLevelList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			if (memberLevelId != null && !memberLevelId.equals("-1") && !"".equals(memberLevelId)) {
				params.put("memberLevel.id," + SearchCondition.EQUAL, memberLevelId);
			}

			//处理查询条件
			String buyerNick = getDecodeRequestParameter("buyerNick");
			if (null != buyerNick && !"".equals(buyerNick)) {
				params.put("name," + SearchCondition.ANYLIKE, buyerNick.trim());
			}
			String receiverMobile = getRequestParameter("receiverMobile");
			if (null != receiverMobile && !"".equals(receiverMobile)) {
				params.put("mobilePhone," + SearchCondition.EQUAL, receiverMobile.trim());
			}
			//处理查询条件
			pager = memberLevelManagementController.doListCustomerAccountPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			memberLevelList = memberLevelManagementController.doListMemberLevelList(params);
			if (id != null && !"".equals(id) && !"0".equals(id)) {
				customerAccount = memberLevelManagementController.doListCustomerAccountById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != customerAccount.getId() && !"".equals(customerAccount.getId())) {
				isSave = false;
			}
			customerAccount = memberLevelManagementController.doListCustomerAccountById(customerAccount.getId());
			if (memberLevelId != null && !"".equals(memberLevelId)) {
				MemberLevel memberLevel = memberLevelManagementController.doListMemberLevelById(memberLevelId);
				if (memberLevel != null) {
					customerAccount.setMemberLevel(memberLevel);
				}
			}
			customerAccount = memberLevelManagementController.doSaveCustomerAccount(customerAccount);

			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
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

	public String getMemberLevelId() {
		return memberLevelId;
	}

	public void setMemberLevelId(String memberLevelId) {
		this.memberLevelId = memberLevelId;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public List<MemberLevel> getMemberLevelList() {
		return memberLevelList;
	}

	public void setMemberLevelList(List<MemberLevel> memberLevelList) {
		this.memberLevelList = memberLevelList;
	}

}
