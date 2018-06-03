package com.vix.crm.business.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.MembershipSubdivisionController;
import com.vix.crm.business.entity.CrmMember;
import com.vix.crm.business.entity.CustomerAccountGroupListView;
import com.vix.crm.business.entity.MembershipSubdivision;
import com.vix.crm.business.entity.MembershipSubdivisionDetail;
import com.vix.crm.business.param.IMemberSubdivisionRules;
import com.vix.crm.business.param.OperationalCharacter;
import com.vix.crm.member.entity.MemberTag;
import com.vix.crm.member.entity.MemberTagDetail;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.StoreType;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class MembershipSubdivisionAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MembershipSubdivisionController membershipSubdivisionController;
	@Autowired
	private IBaseHibernateService baseHibernateService;
	private String id;
	private String membershipSubdivisionName;
	private Integer memberNum;
	private List<CustomerAccountGroupListView> customerAccountGroupListViewList;
	private String pageNo;
	private String memberTagId;
	private MembershipSubdivision membershipSubdivision;
	private MembershipSubdivisionDetail membershipSubdivisionDetail;
	private String treeType;
	private String parentId;
	private List<MemberTag> memberTagList;
	/**
	 * 店铺
	 */
	private List<ChannelDistributor> channelDistributorList;
	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}

			// 处理查询条件
			String name = getDecodeRequestParameter("name");
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name.trim());
			}
			if (treeType != null && !"".equals(treeType)) {
				if ("S".equals(treeType)) {
					if (StringUtils.isNotEmpty(parentId)) {
						StoreType storeType = membershipSubdivisionController.doListStoreTypeById(parentId);
						if (storeType != null) {
							Map<String, Object> p = getParams();
							p.put("type," + SearchCondition.ANYLIKE, "5");
							p.put("storeType.id," + SearchCondition.EQUAL, storeType.getId());
							List<ChannelDistributor> channelDistributorList = membershipSubdivisionController.doListChannelDistributorList(p);
							String channelDistributorIds = "";
							if (channelDistributorList != null && channelDistributorList.size() > 0) {
								for (ChannelDistributor channelDistributor : channelDistributorList) {
									channelDistributorIds += "," + channelDistributor.getId();
								}
							}
							params.put("channelDistributorId," + SearchCondition.IN, channelDistributorIds);
						}
					}
				} else if ("CH".equals(treeType)) {
					if (StringUtils.isNotEmpty(parentId)) {
						params.put("channelDistributorId," + SearchCondition.EQUAL, parentId);
					}
				}
			}
			pager = membershipSubdivisionController.doListMembershipSubdivisionPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = getParams();
			params.put("type," + SearchCondition.ANYLIKE, "5");
			channelDistributorList = membershipSubdivisionController.doListChannelDistributorList(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				membershipSubdivision = membershipSubdivisionController.doListMembershipSubdivisionById(id);
			} else {
				membershipSubdivision = new MembershipSubdivision();
				membershipSubdivision.setIsTemp("1");
				if (SecurityUtil.getCurrentUserName() != null) {
					membershipSubdivision.setCreator(SecurityUtil.getCurrentUserName());
				}
				membershipSubdivision = membershipSubdivisionController.doSaveMembershipSubdivision(membershipSubdivision);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 保存会员分组 同时产生会员分组列表
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != membershipSubdivision.getId() && !"".equals(membershipSubdivision.getId())) {
				isSave = false;
			}

			membershipSubdivision.setIsTemp("");
			initEntityBaseController.initEntityBaseAttribute(membershipSubdivision);
			if (StringUtils.isNotEmpty(memberTagId) && !"0".equals(memberTagId)) {
				// 处理标签开始
				Map<String, Object> params = getParams();
				params.put("id," + SearchCondition.IN, memberTagId);
				List<MemberTag> memberTagList = membershipSubdivisionController.doMemberTagList(params);
				if (memberTagList != null && memberTagList.size() > 0) {
					Set<MemberTag> subMemberTags = new HashSet<MemberTag>();
					for (MemberTag memberTag : memberTagList) {
						subMemberTags.add(memberTag);
					}
					membershipSubdivision.setSubMemberTags(subMemberTags);
				}
				// 处理标签结束
			}
			// 处理修改留痕
			billMarkProcessController.processMark(membershipSubdivision, updateField);
			membershipSubdivision = membershipSubdivisionController.doSaveMembershipSubdivision(membershipSubdivision);

			if (membershipSubdivision != null) {
				List<MembershipSubdivisionDetail> membershipSubdivisionDetailList = new ArrayList<MembershipSubdivisionDetail>();
				if (membershipSubdivision.getSubMembershipSubdivisionDetails() != null && membershipSubdivision.getSubMembershipSubdivisionDetails().size() > 0) {
					for (MembershipSubdivisionDetail membershipSubdivisionDetail : membershipSubdivision.getSubMembershipSubdivisionDetails()) {
						membershipSubdivisionDetailList.add(membershipSubdivisionDetail);
					}

				}

				List<MemberTagDetail> memberTagDetailList = new ArrayList<MemberTagDetail>();
				if (membershipSubdivision.getSubMemberTags() != null && membershipSubdivision.getSubMemberTags().size() > 0) {
					for (MemberTag memberTag : membershipSubdivision.getSubMemberTags()) {
						if (memberTag != null && memberTag.getSubMemberTagDetails() != null && memberTag.getSubMemberTagDetails().size() > 0) {
							for (MemberTagDetail memberTagDetail : memberTag.getSubMemberTagDetails()) {
								memberTagDetailList.add(memberTagDetail);
							}
						}
					}
				}

				// 通过会员细分规则条件组合会员查询条件
				Map<String, Object> membershipSubdivisionDetailParams = getMembershipSubdivisionDetailParams(memberTagDetailList, membershipSubdivisionDetailList, membershipSubdivision.getChannelDistributor().getId());

				//
				List<CrmMember> crmMemberList = baseHibernateService.findAllByConditions(CrmMember.class, membershipSubdivisionDetailParams);
				if (crmMemberList != null && crmMemberList.size() > 0) {
					for (CrmMember crmMember : crmMemberList) {
						if (crmMember != null) {
							CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, crmMember.getCustomerAccount().getId());
							if (customerAccount != null) {
								CustomerAccountGroupListView customerAccountGroupListView = baseHibernateService.findEntityByAttribute(CustomerAccountGroupListView.class, "customerIdAndMembershipSubdivisionId", customerAccount.getId() + "|" + membershipSubdivision.getId());
								if (customerAccountGroupListView != null) {

								} else {
									customerAccountGroupListView = new CustomerAccountGroupListView();
									customerAccountGroupListView.setCustomerName(customerAccount.getName());
									customerAccountGroupListView.setCustomerAccount(customerAccount);
									customerAccountGroupListView.setCustomerAccountId(customerAccount.getId());
									customerAccountGroupListView.setMobilePhone(customerAccount.getMobilePhone());
									customerAccountGroupListView.setTelephone(customerAccount.getTelephone());
									customerAccountGroupListView.setAddress(customerAccount.getAddress());
									customerAccountGroupListView.setEmail(customerAccount.getEmail());
									customerAccountGroupListView.setMembershipSubdivision(membershipSubdivision);
									customerAccountGroupListView.setMembershipSubdivisionId(membershipSubdivision.getId());
									customerAccountGroupListView.setCustomerIdAndMembershipSubdivisionId(customerAccount.getId() + "|" + membershipSubdivision.getId());
									initEntityBaseController.initEntityBaseAttribute(customerAccountGroupListView);
									baseHibernateService.merge(customerAccountGroupListView);
								}
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
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	private Map<String, Object> getMembershipSubdivisionDetailParams(List<MemberTagDetail> memberTagDetailList, List<MembershipSubdivisionDetail> membershipSubdivisionDetailList, String channelDistributorId) throws ParseException {
		Map<String, Object> params = getParams();
		if (StringUtils.isNotEmpty(channelDistributorId)) {
			params.put("channelDistributorId," + SearchCondition.EQUAL, channelDistributorId);
		}
		if (membershipSubdivisionDetailList != null && membershipSubdivisionDetailList.size() > 0) {
			for (MembershipSubdivisionDetail membershipSubdivisionDetail : membershipSubdivisionDetailList) {
				if (membershipSubdivisionDetail != null) {
					if (membershipSubdivisionDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_BUY_NUM)) {
						params.put("tradeCount," + getOperationalCharacter(membershipSubdivisionDetail.getOperationalCharacter()), Long.parseLong(membershipSubdivisionDetail.getCategoryValue()));
					} else if (membershipSubdivisionDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_BUY_AMOUNT)) {
						params.put("tradeAmount," + getOperationalCharacter(membershipSubdivisionDetail.getOperationalCharacter()), Double.parseDouble(membershipSubdivisionDetail.getCategoryValue()));
					} else if (membershipSubdivisionDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_ITEM_NUM)) {
						params.put("itemNum," + getOperationalCharacter(membershipSubdivisionDetail.getOperationalCharacter()), Long.parseLong(membershipSubdivisionDetail.getCategoryValue()));
					} else if (membershipSubdivisionDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_CLOSE_TRADE_COUNT)) {
						params.put("closeTradeCount," + getOperationalCharacter(membershipSubdivisionDetail.getOperationalCharacter()), Long.parseLong(membershipSubdivisionDetail.getCategoryValue()));
					} else if (membershipSubdivisionDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_CLOSE_TRADE_AMOUNT)) {
						params.put("closeTradeAmount," + getOperationalCharacter(membershipSubdivisionDetail.getOperationalCharacter()), Double.parseDouble(membershipSubdivisionDetail.getCategoryValue()));
					} else if (membershipSubdivisionDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_LAST_TRADE_TIME)) {
						params.put("lastTradeTime," + getOperationalCharacter(membershipSubdivisionDetail.getOperationalCharacter()), ft.parse(membershipSubdivisionDetail.getCategoryValue()));
					} else if (membershipSubdivisionDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_FIRST_PURCHASE_TIME)) {
						params.put("firstTradeTime," + getOperationalCharacter(membershipSubdivisionDetail.getOperationalCharacter()), ft.parse(membershipSubdivisionDetail.getCategoryValue()));
					} else if (membershipSubdivisionDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_CUSTOMER_LEVEL)) {
						params.put("grade," + getOperationalCharacter(membershipSubdivisionDetail.getOperationalCharacter()), membershipSubdivisionDetail.getCategoryValue());
					} else if (membershipSubdivisionDetail.getStandardCategory().equals(IMemberSubdivisionRules.CTM_AVG_PRICE)) {
						params.put("avgPrice," + getOperationalCharacter(membershipSubdivisionDetail.getOperationalCharacter()), Double.parseDouble(membershipSubdivisionDetail.getCategoryValue()));
					}
				}
			}
		}
		if (memberTagDetailList != null && memberTagDetailList.size() > 0) {
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
		}

		return params;
	}

	/**
	 * 根据条件规则明细中的运算符返回 SearchCondition 的相关常量
	 * 
	 * @param operationalCharacter
	 * @return
	 */
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

	public String saveOrUpdateMembershipSubdivisionDetail() {
		boolean isSave = true;
		try {
			if (null != membershipSubdivisionDetail.getId() && !"".equals(membershipSubdivisionDetail.getId())) {
				isSave = false;
			}
			if (membershipSubdivisionDetail.getStandardCategory() != null) {
				membershipSubdivisionDetail.setName(getStandardCategoryName(membershipSubdivisionDetail.getStandardCategory()));
			}
			if (membershipSubdivisionDetail.getOperationalCharacter() != null) {
				membershipSubdivisionDetail.setOperationalCharacterName(getOperationalCharacterName(membershipSubdivisionDetail.getOperationalCharacter()));
			}
			membershipSubdivisionDetail = membershipSubdivisionController.doSaveMembershipSubdivisionDetail(membershipSubdivisionDetail);
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

	public String goCustomerList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("membershipSubdivision.id," + SearchCondition.EQUAL, id);
			membershipSubdivision = membershipSubdivisionController.doListMembershipSubdivisionById(id);
			if (membershipSubdivision != null) {
				membershipSubdivisionName = membershipSubdivision.getName();
			}
			customerAccountGroupListViewList = membershipSubdivisionController.doListCustomerAccountGroupListViewList(params);
			if (customerAccountGroupListViewList != null && customerAccountGroupListViewList.size() > 0) {
				memberNum = customerAccountGroupListViewList.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCustomerList";
	}

	/** 获取明细的json数据 */
	public void getMembershipSubdivisionDetailJson() {
		try {
			String json = "";
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				MembershipSubdivision membershipSubdivision = membershipSubdivisionController.doListMembershipSubdivisionById(id);
				if (membershipSubdivision != null) {
					json = convertListToJson(new ArrayList<MembershipSubdivisionDetail>(membershipSubdivision.getSubMembershipSubdivisionDetails()), membershipSubdivision.getSubMembershipSubdivisionDetails().size());
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

	public String goSaveOrUpdateMembershipSubdivision() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				membershipSubdivisionDetail = membershipSubdivisionController.doListMembershipSubdivisionDetailById(id);
			} else {
				membershipSubdivisionDetail = new MembershipSubdivisionDetail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateMembershipSubdivision";
	}

	public String deleteMembershipSubdivisionDetail() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				membershipSubdivisionDetail = membershipSubdivisionController.doListMembershipSubdivisionDetailById(id);
				if (membershipSubdivisionDetail != null) {
					membershipSubdivisionController.doDeleteMembershipSubdivisionDetail(membershipSubdivisionDetail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}

	public String goCustomerListContent() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("membershipSubdivision.id," + SearchCondition.EQUAL, id);
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			membershipSubdivision = membershipSubdivisionController.doListMembershipSubdivisionById(id);
			if (membershipSubdivision != null) {
				membershipSubdivisionName = membershipSubdivision.getName();
			}
			// 处理查询条件
			String name = getDecodeRequestParameter("name");
			if (null != name && !"".equals(name)) {
				params.put("customerName," + SearchCondition.ANYLIKE, name.trim());
			}
			pager = membershipSubdivisionController.doListCustomerAccountGroupListViewPager(params, pager);
			setPager(pager);
			customerAccountGroupListViewList = membershipSubdivisionController.doListCustomerAccountGroupListViewList(params);
			if (customerAccountGroupListViewList != null && customerAccountGroupListViewList.size() > 0) {
				memberNum = customerAccountGroupListViewList.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCustomerListContent";
	}

	/**
	 * 
	 * @return
	 */
	public String goChooseMemberTag() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			memberTagList = membershipSubdivisionController.doListMemberTagList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseMemberTag";
	}

	public String goMemberTagList() {
		try {
			Map<String, Object> params = getParams();
			// 倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager = membershipSubdivisionController.doListMemberTagPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMemberTagList";
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

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getMembershipSubdivisionName() {
		return membershipSubdivisionName;
	}

	public void setMembershipSubdivisionName(String membershipSubdivisionName) {
		this.membershipSubdivisionName = membershipSubdivisionName;
	}

	public Integer getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}

	public List<CustomerAccountGroupListView> getCustomerAccountGroupListViewList() {
		return customerAccountGroupListViewList;
	}

	public void setCustomerAccountGroupListViewList(List<CustomerAccountGroupListView> customerAccountGroupListViewList) {
		this.customerAccountGroupListViewList = customerAccountGroupListViewList;
	}

	public String getMemberTagId() {
		return memberTagId;
	}

	public void setMemberTagId(String memberTagId) {
		this.memberTagId = memberTagId;
	}

	public List<MemberTag> getMemberTagList() {
		return memberTagList;
	}

	public void setMemberTagList(List<MemberTag> memberTagList) {
		this.memberTagList = memberTagList;
	}

	public MembershipSubdivision getMembershipSubdivision() {
		return membershipSubdivision;
	}

	public void setMembershipSubdivision(MembershipSubdivision membershipSubdivision) {
		this.membershipSubdivision = membershipSubdivision;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

	public MembershipSubdivisionDetail getMembershipSubdivisionDetail() {
		return membershipSubdivisionDetail;
	}

	public void setMembershipSubdivisionDetail(MembershipSubdivisionDetail membershipSubdivisionDetail) {
		this.membershipSubdivisionDetail = membershipSubdivisionDetail;
	}
}
