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
 * 商品组装
 * 
 * @类全名 com.vix.nvix.drp.action.VixntProductAssemblyAction
 *
 * @author zhanghaibing
 *
 * @date 2016年11月17日
 */
@Controller
@Scope("prototype")
public class VixntProductAssemblyAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IItemService itemService;
	private String id;
	private Double num;
	private String itemIds;
	private String categoryId;
	private Item item;
	private List<MeasureUnitGroup> measureUnitGroupList;
	private List<MeasureUnit> measureUnitList;
	private List<CurrencyType> currencyTypeList;

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
			Map<String, Object> params = getParams();
			params.put("isBinding," + SearchCondition.EQUAL, "1");
			Pager pager = getPager();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			String title = getDecodeRequestParameter("title");
			if(StringUtils.isNotEmpty(title)){
				params.put("name,"+SearchCondition.ANYLIKE, title);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, Item.class, params);
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
			} else {
				item = new Item();
				item = vixntBaseService.merge(item);
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
			if (StringUtils.isNotEmpty(item.getId())) {
				isSave = false;
			}
			item = vixntBaseService.merge(item);
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

	/** 处理删除操作 */
	public void deleteById() {
		try {
			Item item = vixntBaseService.findEntityById(Item.class, id);
			if (null != item) {
				List<Item> list = vixntBaseService.findAllByEntityClassAndAttribute(Item.class, "parentItem.id", item.getId());
				if(null != list && list.size() > 0){
					for (Item item2 : list) {
						if(item2 != null){
							item2.setParentItem(null);
							item2.setBindingNum(null);
						}
					}
				}
				vixntBaseService.deleteByEntity(item);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public void deleteItemById(){
		try {
			Item item = vixntBaseService.findEntityById(Item.class, id);
			if(null != item){
				item.setParentItem(null);
				item.setBindingNum(null);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public void bingItem() {
		try {
			item = new Item();
			item.setIsBinding("1");
			item = vixntBaseService.merge(item);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id," + SearchCondition.IN, itemIds);
			List<Item> itemList = vixntBaseService.findAllByConditions(Item.class, params);
			if (itemList != null && itemList.size() > 0) {
				for (Item i : itemList) {
					if (i != null) {
						i.setParentItem(item);
						i = vixntBaseService.merge(i);
					}
				}
			}
			if (null != item) {
				renderText(item.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goChooseItem() {
		return "goChooseItem";
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

	public void goItemList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
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

	public String goUpdateItemNum() {
		return "goUpdateItemNum";
	}

	public void updateItemNum() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(id)) {
				Item item = vixntBaseService.findEntityById(Item.class, id);
				if (item != null) {
					if (num != null) {
						item.setBindingNum(num);
					}
					item = vixntBaseService.merge(item);
				}
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
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getItemIds() {
		return itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<MeasureUnit> getMeasureUnitList() {
		return measureUnitList;
	}

	public void setMeasureUnitList(List<MeasureUnit> measureUnitList) {
		this.measureUnitList = measureUnitList;
	}

	public List<MeasureUnitGroup> getMeasureUnitGroupList() {
		return measureUnitGroupList;
	}

	public void setMeasureUnitGroupList(List<MeasureUnitGroup> measureUnitGroupList) {
		this.measureUnitGroupList = measureUnitGroupList;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}
}