package com.vix.crm.member.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.member.controller.MemberTagManagementController;
import com.vix.crm.member.entity.MemberTag;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class MemberTagManagementAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MemberTagManagementController memberTagManagementController;
	private String id;
	private String pageNo;

	private CustomerAccount customerAccount;
	private List<MemberTag> memberTagList;
	private String customerAccountId;
	private Long memberTagId;
	private MemberTag memberTag;
	private String tag = "";

	@Override
	public String goList() {
		try {
			Map<String, Object> mtmap = getParams();
			// 过滤临时数据
			mtmap.put("isTemp," + SearchCondition.NOEQUAL, "1");
			memberTagList = memberTagManagementController.doListMemberTagList(mtmap);
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
			pager = memberTagManagementController.doListCustomerAccountPager(params, pager);
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
		String tags = getRequestParameter("tags");
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			memberTagList = memberTagManagementController.doListMemberTagList(params);
			if (id != null && !"".equals(id)) {
				customerAccount = memberTagManagementController.doListCustomerAccountById(id);
				if (customerAccount != null) {
					if (customerAccount.getMemberTags() != null && customerAccount.getMemberTags().size() > 0) {
						List<String> memberTagNameList = new ArrayList<String>();
						for (MemberTag memberTag : customerAccount.getMemberTags()) {
							if(null != memberTag.getName() && !"".equals(memberTag.getName().trim())){
								memberTagNameList.add(memberTag.getName());
							}
						}
						tag = memberTagNameList.toString().replace("[", "").replace("]", "");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("edit".equals(tags)){
			return "editMembertags";
		}else {
			return GO_SAVE_OR_UPDATE;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		if (null != memberTag.getId()) {
			isSave = false;
		}
		try {
			if (null != customerAccountId) {
				customerAccount = memberTagManagementController.doListCustomerAccountById(customerAccountId);
				memberTag.setIsTemp("");
				memberTag = memberTagManagementController.doSaveMemberTag(memberTag);
				if (memberTag != null && memberTag.getId() != null) {
					Set<MemberTag> memberTags = customerAccount.getMemberTags();
					memberTags.add(memberTag);
					customerAccount.setMemberTags(memberTags);
				}
				if (customerAccount != null) {
					if (customerAccount.getMemberTags() != null && customerAccount.getMemberTags().size() > 0) {
						tag = getRequestParameter("tag");
						/**
						 * 修改     待处理
						 * */
						List<String> memberTagNameList = new ArrayList<String>();
						for (MemberTag memberTag : customerAccount.getMemberTags()) {
							if(null != memberTag.getName() && !"".equals(memberTag.getName().trim())){
								memberTagNameList.add(memberTag.getName());
							}
						}
						tag = memberTagNameList.toString().replace("[", "").replace("]", "");
						customerAccount.setMemberTag(tag);
					}
				}
				customerAccount = memberTagManagementController.doSaveCustomerAccount(customerAccount);
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Long getMemberTagId() {
		return memberTagId;
	}

	public void setMemberTagId(Long memberTagId) {
		this.memberTagId = memberTagId;
	}

	public List<MemberTag> getMemberTagList() {
		return memberTagList;
	}

	public void setMemberTagList(List<MemberTag> memberTagList) {
		this.memberTagList = memberTagList;
	}

	public String getCustomerAccountId() {
		return customerAccountId;
	}

	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public MemberTag getMemberTag() {
		return memberTag;
	}

	public void setMemberTag(MemberTag memberTag) {
		this.memberTag = memberTag;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

}
