package com.vix.nvix.coupon;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.PromotionRule;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.wx.util.StrUtils;

/**
 * 促销规则
 * 
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixntPromotionRuleAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private PromotionRule promotionRule;
	private Map<String, String> promotionTypeMap = PromotionTypeConstant.getPromotionType();

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String name = getDecodeRequestParameter("name");
			if (StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, PromotionRule.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				promotionRule = vixntBaseService.findEntityById(PromotionRule.class, id);
			} else {
				promotionRule = new PromotionRule();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				promotionRule.setStartTime(new Date());
				promotionRule.setEndTime(sdf.parse("9999-12-31 23:59:59"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(promotionRule.getId())) {
				isSave = false;
				promotionRule.setUpdateTime(new Date());
			} else {
				promotionRule.setCreateTime(new Date());
			}
			promotionRule = vixntBaseService.merge(promotionRule);
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

	/** 处理修改操作 */
	public String saveOrUpdateInner() {
		try {
			if (StrUtils.objectIsNotNull(promotionRule.getId())) {
				promotionRule.setUpdateTime(new Date());
			} else {
				promotionRule.setCreateTime(new Date());
			}
			promotionRule = vixntBaseService.merge(promotionRule);
			setMessage(promotionRule.getId() + "," + SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("0," + SAVE_FAIL);
		}
		return UPDATE;
	}
	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				promotionRule = vixntBaseService.findEntityById(PromotionRule.class, id);
				if (null != promotionRule) {
					vixntBaseService.deleteByEntity(promotionRule);
				}
			}
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

	public PromotionRule getPromotionRule() {
		return promotionRule;
	}

	public void setPromotionRule(PromotionRule promotionRule) {
		this.promotionRule = promotionRule;
	}

	public Map<String, String> getPromotionTypeMap() {
		return promotionTypeMap;
	}

	public void setPromotionTypeMap(Map<String, String> promotionTypeMap) {
		this.promotionTypeMap = promotionTypeMap;
	}
}
