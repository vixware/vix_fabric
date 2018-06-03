package com.vix.nvix.customer.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.member.entity.MemberTag;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 会员标签视图
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class VixntMemberTagManagementAction extends VixntBaseAction{

	private static final long serialVersionUID = 1L;
	private String id;
	private MemberTag memberTag;
	public String goSaveOrUpdate(){
		return GO_SAVE_OR_UPDATE;
	}
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String searchName = getDecodeRequestParameter("searchName");
			if(StringUtils.isNotEmpty(searchName)){
				params.put("name,"+SearchCondition.ANYLIKE, searchName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, MemberTag.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public MemberTag getMemberTag() {
		return memberTag;
	}
	public void setMemberTag(MemberTag memberTag) {
		this.memberTag = memberTag;
	}
}
