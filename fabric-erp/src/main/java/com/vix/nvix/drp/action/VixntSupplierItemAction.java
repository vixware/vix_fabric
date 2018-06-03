package com.vix.nvix.drp.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.mdm.item.constant.ItemConstant;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.item.entity.StoreItem;
import com.vix.mdm.item.service.IItemService;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.wechat.base.entity.WxpQyPicture;

@Controller
@Scope("prototype")
public class VixntSupplierItemAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IItemService itemService;
	private String id;
	private String categoryId;
	private String itemIds;
	private String imagesId;
	private Double price;
	/** 产品 */
	private Item item;

	private List<MeasureUnitGroup> measureUnitGroupList;
	private List<MeasureUnit> measureUnitList;
	private List<CurrencyType> currencyTypeList;
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;

	public void goSingleList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			String selectName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			String itemcode = getRequestParameter("itemcode");
			if (StringUtils.isNotEmpty(itemcode)) {
				params.put("code," + SearchCondition.ANYLIKE, itemcode);
			}
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getSupplier() != null && StringUtils.isNotEmpty(employee.getSupplier().getId())) {
					params.put("supplier.id," + SearchCondition.EQUAL, employee.getSupplier().getId());
				} else {
					Supplier supplier = vixntBaseService.findEntityByAttribute(Supplier.class, "employee.id", employee.getId());
					if (supplier != null) {
						params.put("supplier.id," + SearchCondition.EQUAL, supplier.getId());
					}
				}
				if (StringUtils.isNotEmpty(categoryId)) {
					params.put("itemCatalog.id," + SearchCondition.EQUAL, categoryId);
				}
				pager = itemService.findPagerByHqlConditions(pager, Item.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void isUsed() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				item = vixntBaseService.findEntityById(Item.class, id);
				if (item != null) {
					item.setStatus("T");
					item = vixntBaseService.merge(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			measureUnitGroupList = itemService.findAllByEntityClass(MeasureUnitGroup.class);
			measureUnitList = itemService.findAllByEntityClass(MeasureUnit.class);
			currencyTypeList = itemService.findAllByEntityClass(CurrencyType.class);
			if (StrUtils.isNotEmpty(id)) {
				item = itemService.findEntityById(Item.class, id);
			} else {
				item = new Item();
				if (StringUtils.isNotEmpty(categoryId)) {
					ItemCatalog ic = itemService.findEntityById(ItemCatalog.class, categoryId);
					if (ic != null) {
						item.setItemCatalog(ic);
						item.setCode(autoCreateCode.getBillNO(ic.getCodeRule()));
					}
				}
				item.setStatus("F");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	public void updatePrice() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(id)) {
				Item item = vixntBaseService.findEntityById(Item.class, id);
				if (item != null) {
					if (price != null) {
						item.setPurchasePrice(price);
					}
					item = vixntBaseService.merge(item);
					Map<String, Object> itemparams = new HashMap<String, Object>();
					itemparams.put("code", item.getCode());
					itemparams.put("supplierId", item.getSupplier().getId());
					StringBuilder itemhql = standingBookHqlProvider.findStoreItemByItemCode(itemparams);
					StoreItem t = vixntBaseService.findObjectByHql(itemhql.toString(), itemparams);
					if (t != null) {
						t.setPurchasePrice(price);
						t = vixntBaseService.merge(t);
					}
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

	/** 处理删除操作 */
	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				item = vixntBaseService.findEntityById(Item.class, id);
				if (null != item) {
					vixntBaseService.deleteByEntity(item);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StrUtils.isNotEmpty(item.getId())) {
				isSave = false;
			}
			item.setCreateTime(new Date());
			item.setIsTemp("0");
			item.setIsDeleted("0");
			item.setItemClass(ItemConstant.GOODS);
			item.setType("circulationIndustry");
			item.setUpdateTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(item);
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getSupplier() != null) {
					item.setSupplier(employee.getSupplier());
				} else {
					Supplier supplier = vixntBaseService.findEntityByAttribute(Supplier.class, "employee.id", employee.getId());
					if (supplier != null) {
						item.setSupplier(supplier);
					}
				}
			}
			item = itemService.merge(item);
			if (StrUtils.isNotEmpty(imagesId)) {
				WxpQyPicture wxpQyPicture = this.vixntBaseService.findEntityById(WxpQyPicture.class, imagesId);
				if (wxpQyPicture != null && item != null) {
					wxpQyPicture.setItem(item);
					wxpQyPicture = this.vixntBaseService.merge(wxpQyPicture);
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

	public String goUpdatePrice() {
		return "goUpdatePrice";
	}

	public String goChooseItem() {
		return "goChooseItem";
	}

	public void goChooseListContent() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// 处理搜索条件
			String selectName = getDecodeRequestParameter("selectName");
			if (selectName != null && !"".equals(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			if (StringUtils.isNotEmpty(categoryId)) {
				params.put("itemCatalog.id," + SearchCondition.EQUAL, categoryId);
			}
			pager = itemService.findPagerByHqlConditions(pager, Item.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void itemToSupplier() {
		try {
			Employee employee = getEmployee();
			Supplier supplier = null;
			if (employee != null) {
				if (employee.getSupplier() != null) {
					supplier = employee.getSupplier();
				} else {
					supplier = vixntBaseService.findEntityByAttribute(Supplier.class, "employee.id", employee.getId());
				}
			}
			if (supplier != null) {
				if (StringUtils.isNotEmpty(itemIds)) {
					Map<String, Object> params = getParams();
					params.put("id," + SearchCondition.IN, itemIds);
					List<Item> itemList = itemService.findAllByConditions(Item.class, params);
					if (itemList != null && itemList.size() > 0) {
						for (Item item : itemList) {
							if (item != null) {
								item.setSupplier(supplier);
								item = vixntBaseService.merge(item);
							}
						}
					}
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getItemIds() {
		return itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public String getImagesId() {
		return imagesId;
	}

	public void setImagesId(String imagesId) {
		this.imagesId = imagesId;
	}

	public List<MeasureUnitGroup> getMeasureUnitGroupList() {
		return measureUnitGroupList;
	}

	public void setMeasureUnitGroupList(List<MeasureUnitGroup> measureUnitGroupList) {
		this.measureUnitGroupList = measureUnitGroupList;
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

}
