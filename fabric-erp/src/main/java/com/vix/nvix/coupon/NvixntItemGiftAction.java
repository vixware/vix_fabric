package com.vix.nvix.coupon;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.PromotionRule;
import com.vix.crm.business.entity.PromotionRuleDetail;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemGift;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.wx.util.StrUtils;
/**
 * 赠品管理
 * 
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixntItemGiftAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String type;
	private String name;
	private String promotionRuleId;
	private String promotionRuleDetailId;
	private ItemGift itemGift;
	private PromotionRule promotionRule;
	private PromotionRuleDetail promotionRuleDetail;

	/** 获取列表数据 */
	public void goSingleListJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (StrUtils.objectIsNotNull(type)) {
				if ("pr".equals(type)) {
					params.put("promotionRule.id," + SearchCondition.EQUAL, promotionRuleId);
				}
				if ("prd".equals(type)) {
					params.put("promotionRuleDetail.id," + SearchCondition.EQUAL, promotionRuleDetailId);
				}
				if (null != name && !"".equals(name)) {
					name = decode(name, "UTF-8");
					params.put("item.name," + SearchCondition.ANYLIKE, name.trim());
				}
				pager = vixntBaseService.findPagerByHqlConditions(pager, ItemGift.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StrUtils.objectIsNotNull(id)) {
				itemGift = vixntBaseService.findEntityById(ItemGift.class, id);
			}
			if (StrUtils.objectIsNotNull(promotionRuleDetailId)) {
				promotionRuleDetail = vixntBaseService.findEntityById(PromotionRuleDetail.class, promotionRuleDetailId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(itemGift.getId())) {
				isSave = false;
				itemGift.setUpdateTime(new Date());
			} else {
				itemGift.setCreateTime(new Date());
			}
			/** 检查空值对象 */
			String[] attrArray = {"promotionRule", "promotionRuleDetail", "item"};
			checkEntityNullValue(itemGift, attrArray);
			itemGift = vixntBaseService.merge(itemGift);
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
	public String goChooseItem() {
		return "goChooseItem";
	}
	public void getItemSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isServiceItem," + SearchCondition.NOEQUAL, "Y");
			String selectName = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, Item.class, params);
			renderDataTable(pager);
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPromotionRuleId() {
		return promotionRuleId;
	}

	public void setPromotionRuleId(String promotionRuleId) {
		this.promotionRuleId = promotionRuleId;
	}

	public String getPromotionRuleDetailId() {
		return promotionRuleDetailId;
	}

	public void setPromotionRuleDetailId(String promotionRuleDetailId) {
		this.promotionRuleDetailId = promotionRuleDetailId;
	}

	public ItemGift getItemGift() {
		return itemGift;
	}

	public void setItemGift(ItemGift itemGift) {
		this.itemGift = itemGift;
	}

	public PromotionRule getPromotionRule() {
		return promotionRule;
	}

	public void setPromotionRule(PromotionRule promotionRule) {
		this.promotionRule = promotionRule;
	}

	public PromotionRuleDetail getPromotionRuleDetail() {
		return promotionRuleDetail;
	}

	public void setPromotionRuleDetail(PromotionRuleDetail promotionRuleDetail) {
		this.promotionRuleDetail = promotionRuleDetail;
	}
}
