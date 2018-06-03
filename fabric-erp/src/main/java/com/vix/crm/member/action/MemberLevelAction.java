package com.vix.crm.member.action;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.CrmMember;
import com.vix.crm.member.entity.MemberLevel;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class MemberLevelAction extends BaseAction {

	private static final long serialVersionUID = 1L;


	private String id;
	private MemberLevel memberLevel;
	private List<MemberLevel> memberLevelList;
	private String pageNo;

	@Override
	public String goList() {
		try {
			memberLevelList = baseHibernateService.findAllByEntityClass(MemberLevel.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			String name = getDecodeRequestParameter("name");
			if (name != null & !"".equals(name) && !"undefined".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name.trim());
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), MemberLevel.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				memberLevel = baseHibernateService.findEntityById(MemberLevel.class, id);
			} else {
				memberLevel = new MemberLevel();
				memberLevel.setCode(VixUUID.createCode(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != memberLevel.getId()) {
				isSave = false;
			} else {
				memberLevel.setCreateTime(new Date());
				loadCommonData(memberLevel);
			}
			memberLevel = baseHibernateService.merge(memberLevel);
			if (memberLevel != null && "1".equals(memberLevel.getLevelType())) {
				//将符合条件的会员等级管理
				Map<String, Object> params = getParams(memberLevel.getFromAmount(), memberLevel.getToAmount());
				List<CrmMember> crmMemberList = baseHibernateService.findAllByConditions(CrmMember.class, params);
				if (crmMemberList != null && crmMemberList.size() > 0) {
					for (CrmMember crmMember : crmMemberList) {
						if (crmMember != null) {
							CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, crmMember.getCustomerAccount().getId());
							if (customerAccount != null) {
								if (memberLevel != null) {
									customerAccount.setMemberLevel(memberLevel);
								}
								baseHibernateService.merge(customerAccount);
							}
						}
					}
				}
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

	private Map<String, Object> getParams(Double fromAmount, Double toAmount) throws ParseException {
		Map<String, Object> params = getParams();
		if (fromAmount != null && !"".equals(fromAmount) && toAmount != null && !"".equals(toAmount)) {
			params.put("tradeAmount," + SearchCondition.BETWEENAND, fromAmount + "!" + toAmount);
		}
		return params;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			MemberLevel pb = baseHibernateService.findEntityById(MemberLevel.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("CRM_M_MEMBERLEVEL NotExist"));
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

	public MemberLevel getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(MemberLevel memberLevel) {
		this.memberLevel = memberLevel;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public List<MemberLevel> getMemberLevelList() {
		return memberLevelList;
	}

	public void setMemberLevelList(List<MemberLevel> memberLevelList) {
		this.memberLevelList = memberLevelList;
	}

}
