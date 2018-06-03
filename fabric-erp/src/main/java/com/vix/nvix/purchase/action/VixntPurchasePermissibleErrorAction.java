package com.vix.nvix.purchase.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.share.entity.PurchasePermissibleError;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.order.entity.BizType;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 误差规则
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class VixntPurchasePermissibleErrorAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private PurchasePermissibleError purchasePermissibleError;
	private List<BizType> bizTypeList;
	public void goSingleList() {
		try {
			Map<String,Object> params = getParams();
			Pager pager = getPager();
			String name = getDecodeRequestParameter("name");
			if(StringUtils.isNotEmpty(name)) {
				params.put("name,"+SearchCondition.EQUAL, name);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, PurchasePermissibleError.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = getParams();
			params.put("status,"+SearchCondition.EQUAL, "PUR");
			bizTypeList = vixntBaseService.findAllByConditions(BizType.class, params);
			if(StringUtils.isNotEmpty(id)) {
				purchasePermissibleError = vixntBaseService.findEntityById(PurchasePermissibleError.class, id);
			}else {
				purchasePermissibleError = new PurchasePermissibleError();
				purchasePermissibleError.setCode(VixUUID.createCode(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(purchasePermissibleError.getId())) {
				isSave = false;
				purchasePermissibleError.setUpdateTime(new Date());
			}else {
				purchasePermissibleError.setCreateTime(new Date());
			}
			initEntityBaseController.initEntityBaseAttribute(purchasePermissibleError);
			purchasePermissibleError = vixntBaseService.merge(purchasePermissibleError);	
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
				purchasePermissibleError = vixntBaseService.findEntityById(PurchasePermissibleError.class, id);
				if(purchasePermissibleError != null) {
					vixntBaseService.deleteByEntity(purchasePermissibleError);
					renderText(DELETE_SUCCESS);
				}else {
					renderText("误差规则未找到!");
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

	public PurchasePermissibleError getPurchasePermissibleError() {
		return purchasePermissibleError;
	}

	public void setPurchasePermissibleError(PurchasePermissibleError purchasePermissibleError) {
		this.purchasePermissibleError = purchasePermissibleError;
	}
	public List<BizType> getBizTypeList() {
		return bizTypeList;
	}
	public void setBizTypeList(List<BizType> bizTypeList) {
		this.bizTypeList = bizTypeList;
	}
}
