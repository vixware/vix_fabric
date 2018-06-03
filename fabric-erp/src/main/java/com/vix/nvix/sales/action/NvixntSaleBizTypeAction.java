package com.vix.nvix.sales.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.order.entity.BizType;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 销售业务类型
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixntSaleBizTypeAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private BizType bizType;
	
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String name = getDecodeRequestParameter("name");
			if(StringUtils.isNotEmpty(name)) {
				params.put("name,"+SearchCondition.ANYLIKE, name);
			}
			params.put("status,"+SearchCondition.EQUAL, "SAL");
			pager = vixntBaseService.findPagerByHqlConditions(pager, BizType.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				bizType = vixntBaseService.findEntityById(BizType.class, id);
			}else {
				bizType = new BizType();
				bizType.setCode(VixUUID.createCode(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(bizType.getId())) {
				isSave = false;
				bizType.setUpdateTime(new Date());
			}else {
				bizType.setCreateTime(new Date());
			}
			bizType.setStatus("SAL");//采购的业务类型
			initEntityBaseController.initEntityBaseAttribute(bizType);
			bizType = vixntBaseService.merge(bizType);
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
				bizType = vixntBaseService.findEntityById(BizType.class, id);
				if(bizType != null) {
					vixntBaseService.deleteByEntity(bizType);
					renderText(DELETE_SUCCESS);
				}else {
					renderText("业务类型不存在!");
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
	public BizType getBizType() {
		return bizType;
	}
	public void setBizType(BizType bizType) {
		this.bizType = bizType;
	}
}
