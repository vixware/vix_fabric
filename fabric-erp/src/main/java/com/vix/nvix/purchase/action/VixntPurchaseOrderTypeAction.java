package com.vix.nvix.purchase.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.order.entity.OrderType;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 采购单据类型
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class VixntPurchaseOrderTypeAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String status;
	
	private OrderType orderType;
	
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String name = getDecodeRequestParameter("name");
			if(StringUtils.isNotEmpty(name)) {
				params.put("name,"+SearchCondition.ANYLIKE, name);
			}
			if(StringUtils.isNotEmpty(status)) {
				params.put("status,"+SearchCondition.EQUAL, status);
				pager = vixntBaseService.findPagerByHqlConditions(pager, OrderType.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				orderType = vixntBaseService.findEntityById(OrderType.class, id);
			}else {
				orderType = new OrderType();
				orderType.setCode(VixUUID.createCode(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(orderType.getId())) {
				isSave = false;
				orderType.setUpdateTime(new Date());
			}else {
				orderType.setCreateTime(new Date());
			}
			orderType.setStatus("PUR");
			initEntityBaseController.initEntityBaseAttribute(orderType);
			orderType = vixntBaseService.merge(orderType);
			if(isSave) {
				renderText(SAVE_SUCCESS);
			}else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if(isSave) {
				renderText(SAVE_FAIL);
			}else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}
	public void deleteById() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				orderType = vixntBaseService.findEntityById(OrderType.class, id);
				if(orderType != null) {
					vixntBaseService.deleteByEntity(orderType);
					renderText(DELETE_SUCCESS);
				}else {
					renderText("未找到该单据类型!");
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	
}
