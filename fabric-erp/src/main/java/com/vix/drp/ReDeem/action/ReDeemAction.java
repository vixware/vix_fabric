package com.vix.drp.ReDeem.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.MembershipCardmanagement.entity.MemberShipCard;
import com.vix.drp.MembershipCardmanagement.entity.ZoVipCardLog;
import com.vix.drp.ReDeem.controller.ReDeemController;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

@Controller
@Scope("prototype")
public class ReDeemAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ReDeemController reDeemController;

	private String id;
	private String ids;
	private String pageNo;
	private MemberShipCard memberShipCard;

	private List<MemberShipCard> memberShipCardList;

	private SalesOrder salesOrder;
	private SaleOrderItem saleOrderItem;
	/**
	 * 所需积分
	 */
	private Long requiredPoint;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			memberShipCardList = reDeemController.doListMemberShipCardList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			params.put("isdestruct," + SearchCondition.NOEQUAL, "1");
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
			pager = reDeemController.doListMemberShipCard(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				memberShipCard = reDeemController.doListMemberShipCardById(id);
			} else {
				memberShipCard = new MemberShipCard();
				memberShipCard.setCode(VixUUID.getUUID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * TODO
	 * 
	 * 处理积分兑换
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != memberShipCard.getId()) {
				isSave = false;
			}

			/* 保存销售订单 */
			salesOrder = new SalesOrder();
			salesOrder.setCreateTime(new Date());
			salesOrder = reDeemController.doSaveSalesOrder(salesOrder);
			saleOrderItem.setSalesOrder(salesOrder);

			/* 生成积分记录 */
			ZoVipCardLog zoVipCardLog = new ZoVipCardLog();
			zoVipCardLog.setMemberShipCard(memberShipCard);

			/* 会员卡积分扣除 */
			memberShipCard = reDeemController.doListMemberShipCardById(memberShipCard.getId());
			Long requiredPoint = Long.parseLong(getRequestParameter("requiredPoint"));
			if (requiredPoint != null) {
				memberShipCard.setPoint(memberShipCard.getPoint() - requiredPoint);
			}
			//处理修改留痕
			billMarkProcessController.processMark(memberShipCard, updateField);
			reDeemController.doSaveMemberShipCard(memberShipCard);
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
			MemberShipCard memberShipCard = reDeemController.doListMemberShipCardById(id);
			if (null != memberShipCard) {
				reDeemController.doDeleteMemberShipCard(memberShipCard);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
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
				reDeemController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	// 跳转到选择会员页面
	public String goChooseInventoryCurrentStock() {
		return "goChooseInventoryCurrentStock";
	}

	public String goInventoryCurrentStockList() {
		try {
			Map<String, Object> params = getParams();
			// 按最近使用
			Pager pager = reDeemController.doListSetRedeemGoods(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goInventoryCurrentStockList";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public SaleOrderItem getSaleOrderItem() {
		return saleOrderItem;
	}

	public void setSaleOrderItem(SaleOrderItem saleOrderItem) {
		this.saleOrderItem = saleOrderItem;
	}

	public Long getRequiredPoint() {
		return requiredPoint;
	}

	public void setRequiredPoint(Long requiredPoint) {
		this.requiredPoint = requiredPoint;
	}

}
