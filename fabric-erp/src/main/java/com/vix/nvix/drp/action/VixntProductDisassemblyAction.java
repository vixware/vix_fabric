package com.vix.nvix.drp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.service.IItemService;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 商品拆装
 * 
 * @author zhanghaibing
 * 
 * @date 2013-12-11
 */
@Controller
@Scope("prototype")
public class VixntProductDisassemblyAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String itemId;
	private String parentItemId;
	private Double num;
	private String categoryId;
	private Item item;
	private List<MeasureUnitGroup> measureUnitGroupList;
	private List<MeasureUnit> measureUnitList;
	private List<CurrencyType> currencyTypeList;
	@Autowired
	private IItemService itemService;

	@Override
	public String goList() {
		return GO_LIST;
	}

	/**
	 * 
	 * @return
	 */
	public void goSingleList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			String itemname = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(itemname)) {
				params.put("name," + SearchCondition.ANYLIKE, itemname.trim());
			}
			params.put("isBinding," + SearchCondition.EQUAL, "1");
			if (StringUtils.isNotEmpty(categoryId)) {
				pager = itemService.findPagerByItemCatalogId(pager, categoryId, params);
			} else {
				pager = itemService.findPagerByHqlConditions(pager, Item.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			measureUnitList = vixntBaseService.findAllByConditions(MeasureUnit.class, params);
			measureUnitGroupList = itemService.findAllByEntityClass(MeasureUnitGroup.class);
			currencyTypeList = itemService.findAllByEntityClass(CurrencyType.class);
			if (StringUtils.isNotEmpty(id)) {
				item = vixntBaseService.findEntityById(Item.class, id);
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
			if (null != item.getId()) {
				isSave = false;
			}
			item.setIsBinding("1");
			item = vixntBaseService.merge(item);
			if (isSave) {
				renderText("1:拆装成功:"+item.getId());
			} else {
				renderText("1:拆装成功:"+item.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:拆装失败");
			} else {
				renderText("0:拆装失败");
			}
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			Item item = vixntBaseService.findEntityById(Item.class, id);
			if (null != item) {
				vixntBaseService.deleteByEntity(item);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public void goItemList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String searchItemName = getDecodeRequestParameter("searchItemName");
			if (StringUtils.isNotEmpty(searchItemName)) {
				params.put("name," + SearchCondition.ANYLIKE, searchItemName.trim());
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("parentItem.id," + SearchCondition.EQUAL, id);
				pager = itemService.findPagerByHqlConditions(pager, Item.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdateItem() {
		return "goSaveOrUpdateItem";
	}

	/** 获取列表数据 */
	public void goChooseItemListContent() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			String itemname = getDecodeRequestParameter("name");
			if (StringUtils.isNotEmpty(itemname)) {
				params.put("name," + SearchCondition.ANYLIKE, itemname.trim());
			}
			if (StringUtils.isNotEmpty(categoryId)) {
				pager = itemService.findPagerByItemCatalogId(pager, categoryId, params);
			} else {
				pager = itemService.findPagerByHqlConditions(pager, Item.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 拆装商品
	public void bingItem() {
		try {
			Item parentItem = vixntBaseService.findEntityById(Item.class, parentItemId);
			Item item = vixntBaseService.findEntityById(Item.class, itemId);
			if (parentItem != null && item != null) {
				item.setParentItem(parentItem);
				item.setBindingNum(num);
				item = vixntBaseService.merge(item);
				renderText("保存成功!");
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getParentItemId() {
		return parentItemId;
	}

	public void setParentItemId(String parentItemId) {
		this.parentItemId = parentItemId;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public List<MeasureUnit> getMeasureUnitList() {
		return measureUnitList;
	}

	public void setMeasureUnitList(List<MeasureUnit> measureUnitList) {
		this.measureUnitList = measureUnitList;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public List<MeasureUnitGroup> getMeasureUnitGroupList() {
		return measureUnitGroupList;
	}

	public void setMeasureUnitGroupList(List<MeasureUnitGroup> measureUnitGroupList) {
		this.measureUnitGroupList = measureUnitGroupList;
	}

}
