package com.vix.crm.member.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.CrmMember;
import com.vix.crm.business.param.IMemberSubdivisionRules;
import com.vix.crm.business.param.OperationalCharacter;
import com.vix.crm.member.entity.MemberTag;
import com.vix.crm.member.entity.MemberTagDetail;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class MemberTagAction extends BaseAction {

	private static final long serialVersionUID = 1L;


	private String id;
	private String tag = "";
	private String pageNo;
	private MemberTag memberTag;
	private MemberTagDetail memberTagDetail;
	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");

			String name = getRequestParameter("name");
			if (name != null & !"".equals(name) && !"undefined".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name.trim());
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), MemberTag.class, params);
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
				memberTag = baseHibernateService.findEntityById(MemberTag.class, id);
			} else {
				memberTag = new MemberTag();
				memberTag.setIsTemp("1");
				memberTag = baseHibernateService.merge(memberTag);
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
			if (null != memberTag.getId()) {
				isSave = false;
			} else {
				memberTag.setCreateTime(new Date());
				loadCommonData(memberTag);
			}
			memberTag.setIsTemp("");
			memberTag = baseHibernateService.merge(memberTag);
			List<MemberTagDetail> memberTagDetailList = new ArrayList<MemberTagDetail>();
			for (MemberTagDetail memberTagDetail : memberTag.getSubMemberTagDetails()) {
				memberTagDetailList.add(memberTagDetail);
			}
			//将符合条件的会员打标签
			if (memberTagDetailList != null && memberTagDetailList.size() > 0) {
				Map<String, Object> membershipSubdivisionDetailParams = getMemberTagDetailParams(memberTagDetailList);
				List<CrmMember> crmMemberList = baseHibernateService.findAllByConditions(CrmMember.class, membershipSubdivisionDetailParams);
				if (crmMemberList != null && crmMemberList.size() > 0) {
					for (CrmMember crmMember : crmMemberList) {
						if (crmMember != null) {
							CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, crmMember.getCustomerAccount().getId());
							if (customerAccount != null) {
								/** 
								 * 添加会员标签   （卢岩） 2016-02-03
								 * 
								 * */
								
								
								if (memberTag != null) {
									Set<MemberTag> memberTags = customerAccount.getMemberTags();
									memberTags.add(memberTag);
									customerAccount.setMemberTags(memberTags);
								}
								if (customerAccount.getMemberTags() != null && customerAccount.getMemberTags().size() > 0) {
									List<String> memberTagNameList = new ArrayList<String>();
									for (MemberTag memberTag : customerAccount.getMemberTags()) {
										memberTagNameList.add(memberTag.getName());
									}
									tag = memberTagNameList.toString().replace("[", "").replace("]", "");
									customerAccount.setMemberTag(tag);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			MemberTag pb = baseHibernateService.findEntityById(MemberTag.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	public String goSaveOrUpdateMemberTagDetail() {
		try {
			if (id != null && !"".equals(id)) {
				memberTagDetail = baseHibernateService.findEntityById(MemberTagDetail.class, id);
			} else {
				memberTagDetail = new MemberTagDetail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateMemberTagDetail";
	}

	/**
	 * 保存会员标签明细
	 * 
	 * @return
	 */
	public String saveOrUpdateMemberTagDetail() {
		boolean isSave = true;
		try {
			if (null != memberTagDetail.getId()) {
				isSave = false;
			}
			if (memberTagDetail.getStandardCategory() != null) {
				memberTagDetail.setName(getStandardCategoryName(memberTagDetail.getStandardCategory()));
			}
			if (memberTagDetail.getOperationalCharacter() != null) {
				memberTagDetail.setOperationalCharacterName(getOperationalCharacterName(memberTagDetail.getOperationalCharacter()));
			}
			memberTagDetail = baseHibernateService.merge(memberTagDetail);
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

	/**
	 * 
	 * @param standardCategory
	 * @return
	 */
	private String getStandardCategoryName(String standardCategory) {
		String standardCategoryName = "";
		if (standardCategory.equals(IMemberSubdivisionRules.CTM_BUY_NUM)) {
			standardCategoryName = "购买成功订单数";
		} else if (standardCategory.equals(IMemberSubdivisionRules.CTM_BUY_AMOUNT)) {
			standardCategoryName = "购买金额";
		} else if (standardCategory.equals(IMemberSubdivisionRules.CTM_ITEM_NUM)) {
			standardCategoryName = "购买件数";
		} else if (standardCategory.equals(IMemberSubdivisionRules.CTM_CLOSE_TRADE_COUNT)) {
			standardCategoryName = "退款订单数";
		} else if (standardCategory.equals(IMemberSubdivisionRules.CTM_CLOSE_TRADE_AMOUNT)) {
			standardCategoryName = "退款金额";
		} else if (standardCategory.equals(IMemberSubdivisionRules.CTM_LAST_TRADE_TIME)) {
			standardCategoryName = "最后一次购买时间";
		} else if (standardCategory.equals(IMemberSubdivisionRules.CTM_FIRST_PURCHASE_TIME)) {
			standardCategoryName = "第一次够买时间";
		} else if (standardCategory.equals(IMemberSubdivisionRules.CTM_CUSTOMER_LEVEL)) {
			standardCategoryName = "会员等级";
		} else if (standardCategory.equals(IMemberSubdivisionRules.CTM_AVG_PRICE)) {
			standardCategoryName = "平均客单价";
		}
		return standardCategoryName;
	}

	private String getOperationalCharacterName(String operationalCharacter) {
		String operationalCharacterName = "";
		if (operationalCharacter.equals(OperationalCharacter.EQUAL)) {
			operationalCharacterName = "等于";
		} else if (operationalCharacter.equals(OperationalCharacter.NOEQUAL)) {
			operationalCharacterName = "不等于";
		} else if (operationalCharacter.equals(OperationalCharacter.MORETHAN)) {
			operationalCharacterName = "大于";
		} else if (operationalCharacter.equals(OperationalCharacter.MORETHANANDEQUAL)) {
			operationalCharacterName = "大于等于";
		} else if (operationalCharacter.equals(OperationalCharacter.LESSTHAN)) {
			operationalCharacterName = "小于";
		} else if (operationalCharacter.equals(OperationalCharacter.LESSTHANANDEQUAL)) {
			operationalCharacterName = "小于等于";
		}
		return operationalCharacterName;
	}

	/** 获取明细的json数据 */
	public void getMemberTagDetailJson() {
		try {
			String json = "";
			if (null != id && !"".equals(id)) {
				MemberTag memberTag = baseHibernateService.findEntityById(MemberTag.class, id);
				if (memberTag != null) {
					json = convertListToJson(new ArrayList<MemberTagDetail>(memberTag.getSubMemberTagDetails()), memberTag.getSubMemberTagDetails().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String deleteMemberTagDetail() {
		try {
			if (id != null && !"".equals(id)) {
				memberTagDetail = baseHibernateService.findEntityById(MemberTagDetail.class, id);
				if (memberTagDetail != null) {
					baseHibernateService.deleteByEntity(memberTagDetail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}

	private Map<String, Object> getMemberTagDetailParams(List<MemberTagDetail> memberTagDetailList) throws ParseException {
		Map<String, Object> params = getParams();
		for (MemberTagDetail memberTagDetail : memberTagDetailList) {
			if (memberTagDetail != null) {
				if (memberTagDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_BUY_NUM)) {
					params.put("tradeCount," + getOperationalCharacter(memberTagDetail.getOperationalCharacter()), Long.parseLong(memberTagDetail.getCategoryValue()));
				} else if (memberTagDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_BUY_AMOUNT)) {
					params.put("tradeAmount," + getOperationalCharacter(memberTagDetail.getOperationalCharacter()), Double.parseDouble(memberTagDetail.getCategoryValue()));
				} else if (memberTagDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_ITEM_NUM)) {
					params.put("itemNum," + getOperationalCharacter(memberTagDetail.getOperationalCharacter()), Long.parseLong(memberTagDetail.getCategoryValue()));
				} else if (memberTagDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_CLOSE_TRADE_COUNT)) {
					params.put("closeTradeCount," + getOperationalCharacter(memberTagDetail.getOperationalCharacter()), Long.parseLong(memberTagDetail.getCategoryValue()));
				} else if (memberTagDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_CLOSE_TRADE_AMOUNT)) {
					params.put("closeTradeAmount," + getOperationalCharacter(memberTagDetail.getOperationalCharacter()), Double.parseDouble(memberTagDetail.getCategoryValue()));
				} else if (memberTagDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_LAST_TRADE_TIME)) {
					params.put("lastTradeTime," + getOperationalCharacter(memberTagDetail.getOperationalCharacter()), ft.parse(memberTagDetail.getCategoryValue()));
				} else if (memberTagDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_FIRST_PURCHASE_TIME)) {
					params.put("firstTradeTime," + getOperationalCharacter(memberTagDetail.getOperationalCharacter()), ft.parse(memberTagDetail.getCategoryValue()));
				} else if (memberTagDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_CUSTOMER_LEVEL)) {
					params.put("grade," + getOperationalCharacter(memberTagDetail.getOperationalCharacter()), memberTagDetail.getCategoryValue());
				} else if (memberTagDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_AVG_PRICE)) {
					params.put("avgPrice," + getOperationalCharacter(memberTagDetail.getOperationalCharacter()), Double.parseDouble(memberTagDetail.getCategoryValue()));
				}
			}
		}

		return params;
	}

	private String getOperationalCharacter(String operationalCharacter) {
		String oc = "";
		if (operationalCharacter.equals(OperationalCharacter.EQUAL)) {
			oc = SearchCondition.EQUAL;
		} else if (operationalCharacter.equals(OperationalCharacter.NOEQUAL)) {
			oc = SearchCondition.NOEQUAL;
		} else if (operationalCharacter.equals(OperationalCharacter.MORETHAN)) {
			oc = SearchCondition.MORETHAN;
		} else if (operationalCharacter.equals(OperationalCharacter.MORETHANANDEQUAL)) {
			oc = SearchCondition.MORETHANANDEQUAL;
		} else if (operationalCharacter.equals(OperationalCharacter.LESSTHAN)) {
			oc = SearchCondition.LESSTHAN;
		} else if (operationalCharacter.equals(OperationalCharacter.LESSTHANANDEQUAL)) {
			oc = SearchCondition.LESSTHANANDEQUAL;
		} else if (operationalCharacter.equals(OperationalCharacter.BETWEENAND)) {
			oc = SearchCondition.BETWEENAND;
		}
		return oc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MemberTag getMemberTag() {
		return memberTag;
	}

	public void setMemberTag(MemberTag memberTag) {
		this.memberTag = memberTag;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public MemberTagDetail getMemberTagDetail() {
		return memberTagDetail;
	}

	public void setMemberTagDetail(MemberTagDetail memberTagDetail) {
		this.memberTagDetail = memberTagDetail;
	}

}
