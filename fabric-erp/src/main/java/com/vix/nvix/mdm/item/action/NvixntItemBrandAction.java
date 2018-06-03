package com.vix.nvix.mdm.item.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.ItemBrand;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 商品品牌
 */
@Controller
@Scope("prototype")
public class NvixntItemBrandAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private ItemBrand itemBrand;
	private String itemBrandName;

	/** 获取列表数据 */
	@SuppressWarnings("unchecked")
	public void getItemBrandJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(itemBrandName)) {
				params.put("name," + SearchCondition.ANYLIKE, decode(itemBrandName, "UTF-8"));
			}
			vixntBaseService.findPagerByHqlConditions(pager, ItemBrand.class, params);
			if (pager.getResultList().size() < 10) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 10 - listSize; i++) {
					pager.getResultList().add(new ItemBrand());
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void chooseItemBrandJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			itemBrandName = getRequestParameter("itemBrandName");
			if (StringUtils.isNotEmpty(itemBrandName)) {
				params.put("name," + SearchCondition.ANYLIKE, decode(itemBrandName, "UTF-8"));
			}
			vixntBaseService.findPagerByHqlConditions(pager, ItemBrand.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				itemBrand = vixntBaseService.findEntityById(ItemBrand.class, id);
			}
			if (itemBrand == null) {
				itemBrand = new ItemBrand();
				itemBrand.setCode(VixUUID.createCode(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(itemBrand.getId())) {
				isSave = false;
				itemBrand.setUpdateTime(new Date());
			} else {
				itemBrand.setCreateTime(new Date());
				loadCommonData(itemBrand);// 载入数据公司码
			}
			itemBrand = vixntBaseService.merge(itemBrand);
			if (isSave) {
				renderText(itemBrand.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText(itemBrand.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			ItemBrand pb = vixntBaseService.findEntityById(ItemBrand.class, id);
			if (null != pb) {
				vixntBaseService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/** 选择品牌 */
	public String goChooseItemBrand() {
		return "goChooseItemBrand";
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public ItemBrand getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(ItemBrand itemBrand) {
		this.itemBrand = itemBrand;
	}

	public String getItemBrandName() {
		return itemBrandName;
	}

	public void setItemBrandName(String itemBrandName) {
		this.itemBrandName = itemBrandName;
	}
}