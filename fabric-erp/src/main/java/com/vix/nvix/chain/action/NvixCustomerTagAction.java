package com.vix.nvix.chain.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.member.entity.MemberTag;
import com.vix.nvix.common.base.action.VixntBaseAction;

import net.sf.json.JSONObject;
/**
 * 会员标签
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixCustomerTagAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private MemberTag memberTag;

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String searchName = getDecodeRequestParameter("searchName");
			if(StringUtils.isNotEmpty(searchName)){
				params.put("name," +SearchCondition.ANYLIKE, searchName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), MemberTag.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				memberTag = vixntBaseService.findEntityById(MemberTag.class, id);
			} else {
				memberTag = new MemberTag();
				memberTag.setCode(VixUUID.createCodeByNumber(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(memberTag.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(memberTag);
			memberTag = vixntBaseService.merge(memberTag);
			//upLoadMemberTag(memberTag);
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
	}

	@SuppressWarnings("unused")
	private void upLoadMemberTag(MemberTag memberTag) throws Exception {
		JSONObject json = new JSONObject();
		json.put("tenantid", memberTag.getTenantId());
		json.put("code", memberTag.getCode());
		json.put("name", memberTag.getName());
		System.out.println(json);
		String resp = postToPos("", json.toString(), "", "");
		if (StringUtils.isNotEmpty(resp)) {
			System.out.println(resp);
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			MemberTag memberTag = vixntBaseService.findEntityById(MemberTag.class, id);
			if (null != memberTag) {
				vixntBaseService.deleteByEntity(memberTag);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public String goChooseTag(){
		return "goChooseTag";
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
