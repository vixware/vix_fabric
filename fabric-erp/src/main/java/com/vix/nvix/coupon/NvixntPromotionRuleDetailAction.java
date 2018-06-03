package com.vix.nvix.coupon;

import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.PromotionRule;
import com.vix.crm.business.entity.PromotionRuleDetail;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.wx.util.StrUtils;
/**
 * 促销规则明细管理
 * 
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixntPromotionRuleDetailAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String promotionRuleId;
	private String promotionType;
	private PromotionRuleDetail promotionRuleDetail;
	private PromotionRule promotionRule;

	/** 获取列表数据 */
	public void goSingleListJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (StrUtils.objectIsNotNull(promotionRuleId)) {
				params.put("promotionRule.id," + SearchCondition.EQUAL, promotionRuleId);
				if (null != name && !"".equals(name)) {
					name = decode(name, "UTF-8");
					params.put("ecProduct.name," + SearchCondition.ANYLIKE, name.trim());
				}
				pager = vixntBaseService.findPagerByHqlConditions(pager, PromotionRuleDetail.class, params);
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
				promotionRuleDetail = vixntBaseService.findEntityById(PromotionRuleDetail.class, id);
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
			if (StrUtils.objectIsNotNull(promotionRuleDetail.getId())) {
				isSave = false;
				promotionRuleDetail.setUpdateTime(new Date());
			} else {
				promotionRuleDetail.setCreateTime(new Date());
			}
			if (null != promotionRuleDetail.getPromotionRule().getId() && !"".equals(promotionRuleDetail.getPromotionRule().getId())) {
				/** 检查空值对象 */
				String[] attrArray = {"item", "promotionRule"};
				checkEntityNullValue(promotionRuleDetail, attrArray);

				promotionRuleDetail = vixntBaseService.merge(promotionRuleDetail);
				if (isSave) {
					renderText(SAVE_SUCCESS);
				} else {
					renderText(UPDATE_SUCCESS);
				}
			} else {
				if (isSave) {
					renderText(SAVE_FAIL);
				} else {
					renderText(UPDATE_FAIL);
				}
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

	/** 处理修改操作 */
	public String saveOrUpdateInner() {
		try {
			if (StrUtils.objectIsNotNull(promotionRuleDetail.getId())) {
				promotionRuleDetail.setUpdateTime(new Date());
			} else {
				promotionRuleDetail.setCreateTime(new Date());
			}

			/** 检查空值对象 */
			String[] attrArray = {"promotionRule", "ecProduct", "ecProductGifts"};
			checkEntityNullValue(promotionRuleDetail, attrArray);

			promotionRuleDetail = vixntBaseService.merge(promotionRuleDetail);
			setMessage(promotionRuleDetail.getId() + "," + SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("0," + SAVE_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			PromotionRuleDetail p = vixntBaseService.findEntityById(PromotionRuleDetail.class, id);
			if (null != p) {
				vixntBaseService.deleteByEntity(p);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_promotionRuleDetailNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
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

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}

	public PromotionRuleDetail getPromotionRuleDetail() {
		return promotionRuleDetail;
	}

	public void setPromotionRuleDetail(PromotionRuleDetail promotionRuleDetail) {
		this.promotionRuleDetail = promotionRuleDetail;
	}

	public PromotionRule getPromotionRule() {
		return promotionRule;
	}

	public void setPromotionRule(PromotionRule promotionRule) {
		this.promotionRule = promotionRule;
	}

	/** 根据商品id载入商品的sku列表 */
	/*
	 * public String loadEcProductSkuByEcProductId(){ try {
	 *//** 默认sku数量为0 */
	/*
	 * getRequest().setAttribute("skuCount", 0); String prdId =
	 * getRequestParameter("prdId"); if(StrUtils.objectIsNotNull(prdId)){
	 * PromotionRuleDetail prd =
	 * vixntBaseService.findEntityById(PromotionRuleDetail.class, prdId);
	 * getRequest().setAttribute("sku", prd.getSku());
	 * getRequest().setAttribute("skuPrice", prd.getSkuPrice()); }
	 * if(StrUtils.objectIsNotNull(id)){ Map<String,String> skuMap = new
	 * HashMap<String,String>(); Item ep =
	 * vixntBaseService.findEntityById(Item.class, id); if(null != ep && null !=
	 * ep.getItemCatalog()&& null != ep.getItemCatalog().getId()){
	 * List<Map<String,Object>> list =
	 * vixntBaseService.findEcProductSkuDetailListById(ep.getId()); if(null !=
	 * list && list.size() > 0){
	 *//** 获取多规格sku */
	/*
	 * for(Map<String,Object> map : list){ if(null != map && null !=
	 * map.get("sku") && null != map.get("price")){
	 * skuMap.put(map.get("sku").toString(),
	 * map.get("price").toString().trim()); } } } } if(null != ep && null !=
	 * ep.getSku() && ep.getPrice() > 0 && skuMap.keySet().size() <= 0){
	 *//** 获取商品本身sku *//*
						 * if(null != ep.getSku() && !"".equals(ep.getSku()) &&
						 * ep.getPrice() > 0){ skuMap.put(ep.getSku(),
						 * ep.getPrice().toString()); } }
						 * getRequest().setAttribute("skuMap", skuMap);
						 * getRequest().setAttribute("skuCount",
						 * skuMap.keySet().size()); } } catch (Exception e) {
						 * e.printStackTrace(); renderText(DELETE_FAIL); }
						 * return "ecProductSkuList"; }
						 */

}
