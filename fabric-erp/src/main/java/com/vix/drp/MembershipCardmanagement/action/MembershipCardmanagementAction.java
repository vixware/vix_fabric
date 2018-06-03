package com.vix.drp.MembershipCardmanagement.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.MembershipCardmanagement.controller.MembershipCardmanagementController;
import com.vix.drp.MembershipCardmanagement.entity.MemberShipCard;
import com.vix.drp.MembershipCardmanagement.entity.MemberShipCardType;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;

import net.sf.cglib.beans.BeanCopier;

@Controller
@Scope("prototype")
public class MembershipCardmanagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private MembershipCardmanagementController membershipCardmanagementController;

	private String id;
	private String ids;
	private String pageNo;
	private String parentId;
	private String treeType;
	private MemberShipCard memberShipCard;

	private List<MemberShipCard> memberShipCardList;

	private List<MemberShipCardType> cardTypeList;
	/**
	 * 续费金额
	 */
	private Double balanceAmount;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			/* 高级查询 */
			params.put("isdestruct," + SearchCondition.NOEQUAL, "1");
			// 获取当前登录用户所在的公司或供应商
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = membershipCardmanagementController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					if (employee.getChannelDistributor() != null) {
						// 如果登录的员工属于经销商或门店
						ChannelDistributor channelDistributor = employee.getChannelDistributor();
						params.put("channelDistributor.id," + SearchCondition.ANYLIKE, channelDistributor.getId());
					}
				}
			}
			if (treeType != null && "CH".equals(treeType)) {
				params.put("channelDistributor.id," + SearchCondition.EQUAL, id);
			}
			String vipcardid = getRequestParameter("name");
			if (vipcardid != null && !"".equals(vipcardid)) {
				params.put("vipcardid," + SearchCondition.EQUAL, vipcardid.trim());
			}
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			memberShipCardList = membershipCardmanagementController.doListMemberShipCardList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			//倒序排序
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			/* 高级查询 */
			params.put("isdestruct," + SearchCondition.NOEQUAL, "1");
			// 获取当前登录用户所在的公司或供应商
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = membershipCardmanagementController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					if (employee.getChannelDistributor() != null) {
						// 如果登录的员工属于经销商或门店
						ChannelDistributor channelDistributor = employee.getChannelDistributor();
						params.put("channelDistributor.id," + SearchCondition.ANYLIKE, channelDistributor.getId());
					}
				}
			}
			if (treeType != null && "CH".equals(treeType)) {
				params.put("channelDistributor.id," + SearchCondition.EQUAL, id);
			}
			String vipcardid = getRequestParameter("name");
			if (vipcardid != null && !"".equals(vipcardid)) {
				params.put("vipcardid," + SearchCondition.EQUAL, vipcardid.trim());
			}
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = membershipCardmanagementController.doListMemberShipCard(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> ctParams = new HashMap<String, Object>();
			this.cardTypeList = this.baseHibernateService.findAllByConditions(MemberShipCardType.class, ctParams);

			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				memberShipCard = membershipCardmanagementController.doListMemberShipCardById(id);
			} else {
				memberShipCard = new MemberShipCard();
				memberShipCard.setCode(VixUUID.getUUID());
				if (null != parentId && !"".equals(parentId)) {
					if ("CH".equals(treeType)) {
						ChannelDistributor c = membershipCardmanagementController.doListChannelDistributorById(parentId);
						memberShipCard.setChannelDistributor(c);
						//生成会员卡号
						memberShipCard.setVipcardid(autoCreateCode.getBillNO(c.getCode()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 批量开卡
	 * 
	 * @return
	 */
	public String goBatchSaveOrUpdate() {
		try {
			Map<String, Object> ctParams = new HashMap<String, Object>();
			this.cardTypeList = this.baseHibernateService.findAllByConditions(MemberShipCardType.class, ctParams);

			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				memberShipCard = membershipCardmanagementController.doListMemberShipCardById(id);
			} else {
				memberShipCard = new MemberShipCard();
				if (null != parentId && !"".equals(parentId)) {
					if ("C".equals(treeType)) {
					} else if ("CH".equals(treeType)) {
						ChannelDistributor c = membershipCardmanagementController.doListChannelDistributorById(parentId);
						memberShipCard.setChannelDistributor(c);

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goBatchSaveOrUpdate";
	}

	/**
	 * @return
	 */
	public String goChangeCard() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				memberShipCard = membershipCardmanagementController.doListMemberShipCardById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChangeCard";
	}

	/**
	 * 换卡 新增一条会员卡数据 将以前的会员卡数据注销
	 * 
	 * @return
	 */
	public String saveOrUpdateChangeCard() {
		boolean isSave = true;
		try {
			if (null != memberShipCard.getId() && !"".equals(memberShipCard.getId())) {
				isSave = false;
			}
			memberShipCard = membershipCardmanagementController.doListMemberShipCardById(memberShipCard.getId());
			// 创建新卡
			MemberShipCard mscard = new MemberShipCard();
			BeanCopier copier = BeanCopier.create(MemberShipCard.class, MemberShipCard.class, false);
			copier.copy(memberShipCard, mscard, null);
			mscard.setId(null);
			mscard.setCode(memberShipCard.getChannelDistributor().getCode() + VixUUID.createCode(9));
			String newvipcardid = getRequestParameter("newvipcardid");
			mscard.setVipcardid(newvipcardid);
			membershipCardmanagementController.doSaveMemberShipCard(mscard);

			// 注销原卡
			memberShipCard.setIsdestruct("1");
			initEntityBaseController.initEntityBaseAttribute(memberShipCard);
			membershipCardmanagementController.doSaveMemberShipCard(memberShipCard);
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

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != memberShipCard.getId() && !"".equals(memberShipCard.getId())) {
				isSave = false;
			}
			memberShipCard.setIsstop("2");
			memberShipCard.setOpentype("S");
			memberShipCard.setIsDownLoad("1");
			memberShipCard.setIsOpenCard("2");
			if (memberShipCard.getCustomerAccount() == null || memberShipCard.getCustomerAccount().getId() == null) {
				memberShipCard.setCustomerAccount(null);
			} else {
				CustomerAccount customerAccount = membershipCardmanagementController.doListCustomerAccountById(memberShipCard.getCustomerAccount().getId());
				if (customerAccount != null) {
					memberShipCard.setVipid(customerAccount.getCode());
				}
			}
			initEntityBaseController.initEntityBaseAttribute(memberShipCard);
			//处理修改留痕
			billMarkProcessController.processMark(memberShipCard, updateField);
			membershipCardmanagementController.doSaveMemberShipCard(memberShipCard);
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

	public String batchSaveOrUpdate() {
		boolean isSave = true;
		try {
			String batchNo = VixUUID.getUUID();
			Integer cardCount = Integer.parseInt(getRequestParameter("cardCount"));
			for (int i = 0; i < cardCount; i++) {
				MemberShipCard m = new MemberShipCard();
				m = memberShipCard;
				m.setIsstop("2");
				m.setBatchNo(batchNo);
				ChannelDistributor c = membershipCardmanagementController.doListChannelDistributorById(m.getChannelDistributor().getId());
				//生成会员卡号
				m.setVipcardid(autoCreateCode.getBillNO(c.getCode()));
				m.setOpentype("B");
				m.setIsDownLoad("1");
				m.setIsOpenCard("2");
				if (m.getCustomerAccount() == null || m.getCustomerAccount().getId() == null) {
					m.setCustomerAccount(null);
				} else {
					CustomerAccount customerAccount = membershipCardmanagementController.doListCustomerAccountById(memberShipCard.getCustomerAccount().getId());
					if (customerAccount != null) {
						m.setVipid(customerAccount.getCode());
					}
				}
				initEntityBaseController.initEntityBaseAttribute(m);
				//处理修改留痕
				billMarkProcessController.processMark(m, updateField);
				membershipCardmanagementController.doSaveMemberShipCard(m);
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

	/** 跳转至用户修改页面 */
	public String goMemberShipCardFeesForRenewal() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				memberShipCard = membershipCardmanagementController.doListMemberShipCardById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMemberShipCardFeesForRenewal";
	}

	/**
	 * 处理续费逻辑
	 * 
	 * @return
	 */
	public String memberShipCardFeesForRenewal() {
		boolean isSave = true;
		try {
			if (null != memberShipCard.getId() && !"".equals(memberShipCard.getId())) {
				isSave = false;
			}
			MemberShipCard msCard = membershipCardmanagementController.doListMemberShipCardById(memberShipCard.getId());
			msCard.setBalance_amount(msCard.getBalance_amount() + balanceAmount);
			membershipCardmanagementController.doSaveMemberShipCard(msCard);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				setMessage("续费成功");
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
			MemberShipCard memberShipCard = membershipCardmanagementController.doListMemberShipCardById(id);
			if (null != memberShipCard) {
				membershipCardmanagementController.doDeleteMemberShipCard(memberShipCard);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 禁用
	 * 
	 * @return
	 */
	public String memberShipCardDisable() {
		try {
			MemberShipCard memberShipCard = membershipCardmanagementController.doListMemberShipCardById(id);
			if (null != memberShipCard) {
				memberShipCard.setIsstop("1");
				membershipCardmanagementController.doSaveMemberShipCard(memberShipCard);
				setMessage("禁用成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("禁用失败" + e.getMessage());
		}
		return UPDATE;
	}

	/**
	 * 解禁
	 * 
	 * @return
	 */
	public String memberShipCardEnable() {
		try {
			MemberShipCard memberShipCard = membershipCardmanagementController.doListMemberShipCardById(id);
			if (null != memberShipCard) {
				memberShipCard.setIsstop("2");
				membershipCardmanagementController.doSaveMemberShipCard(memberShipCard);
				setMessage("启用成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("启用失败" + e.getMessage());
		}
		return UPDATE;
	}

	// 跳转到选择会员页面
	public String goChooseCustomerAccount() {
		return "goChooseCustomerAccount";
	}

	public String goCustomerAccountList() {
		try {
			Map<String, Object> params = getParams();
			//倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(5);
			/* 高级查询 */
			params.put("type," + SearchCondition.ANYLIKE, "personal");

			String searchContent = getDecodeRequestParameter("searchContent");
			if (searchContent != null && !"".equals(searchContent)) {
				params.put("name," + SearchCondition.ANYLIKE, searchContent);
			}
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = membershipCardmanagementController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					// 获取登录员工所在的部门信息
					if (employee.getCompanyCode() != null) {
						params.put("companyCode," + SearchCondition.ANYLIKE, employee.getCompanyCode());
					}
					if (employee.getChannelDistributor() != null) {
						// 如果登录的员工属于经销商或门店
						ChannelDistributor channelDistributor = employee.getChannelDistributor();
						params.put("channelDistributor.code," + SearchCondition.ANYLIKE, channelDistributor.getCode());
					}
				}
			}
			pager = membershipCardmanagementController.doListCustomerAccount(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCustomerAccountList";
	}

	/**
	 * 会员卡转账
	 * 
	 * @return
	 */
	public String goMemberShipCardTransfers() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				memberShipCard = membershipCardmanagementController.doListMemberShipCardById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMemberShipCardTransfers";
	}

	/**
	 * 保存会员卡转账
	 * 
	 * @return
	 */
	public String saveOrUpdateMemberShipCardTransfers() {
		boolean isSave = true;
		try {
			if (null != memberShipCard.getId() && !"".equals(memberShipCard.getId())) {
				isSave = false;
			}
			String otherMemberShipCardId = getRequestParameter("otherMemberShipCardId");
			Double transfersOutAmount = Double.parseDouble(getRequestParameter("transfersOutAmount"));
			MemberShipCard msCard = membershipCardmanagementController.doListMemberShipCardById(memberShipCard.getId());
			msCard.setBalance_amount(msCard.getBalance_amount() - transfersOutAmount);
			membershipCardmanagementController.doSaveMemberShipCard(msCard);
			MemberShipCard otherCard = membershipCardmanagementController.doListMemberShipCardById(otherMemberShipCardId);
			otherCard.setBalance_amount(otherCard.getBalance_amount() + transfersOutAmount);
			membershipCardmanagementController.doSaveMemberShipCard(otherCard);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				setMessage("转账成功");
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

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				membershipCardmanagementController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public MemberShipCard getMemberShipCard() {
		return memberShipCard;
	}

	public void setMemberShipCard(MemberShipCard memberShipCard) {
		this.memberShipCard = memberShipCard;
	}

	public List<MemberShipCard> getMemberShipCardList() {
		return memberShipCardList;
	}

	public void setMemberShipCardList(List<MemberShipCard> memberShipCardList) {
		this.memberShipCardList = memberShipCardList;
	}

	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public List<MemberShipCardType> getCardTypeList() {
		return cardTypeList;
	}

	public void setCardTypeList(List<MemberShipCardType> cardTypeList) {
		this.cardTypeList = cardTypeList;
	}
}
