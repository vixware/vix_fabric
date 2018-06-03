package com.vix.nvix.system.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemPackage;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.wx.constant.SearchCondition;
@Controller
@Scope("prototype")
public class NvixEcproductPackageAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private ItemPackage itemPackage;
	private String name;

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name.trim());
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, ItemPackage.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取列表数据 */
	public void goEcProductSingleListJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (null != id && !"".equals(id)) {
				params.put("itemPackage.id," + SearchCondition.EQUAL, id.trim());
				if (null != name && !"".equals(name)) {
					name = decode(name, "UTF-8");
					params.put("name," + SearchCondition.ANYLIKE, name.trim());
				}
				vixntBaseService.findPagerByHqlConditions(pager, Item.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				itemPackage = vixntBaseService.findEntityById(ItemPackage.class, id);
			} else {
				itemPackage = new ItemPackage();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
				itemPackage.setCode("EPP_" + sdf.format(new Date()));
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
			if (StringUtils.isNotEmpty(itemPackage.getId())) {
				isSave = false;
				itemPackage.setUpdateTime(new Date());
			} else {
				itemPackage.setCreateTime(new Date());
			}
			itemPackage = vixntBaseService.merge(itemPackage);
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

	public String saveOrUpdateInner() {
		try {
			itemPackage.setCreateTime(new Date());
			itemPackage = vixntBaseService.merge(itemPackage);
			renderText(itemPackage.getId() + ":" + SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:" + SAVE_FAIL);
		}
		return UPDATE;
	}
	/** 处理删除操作 */
	public String deleteById() {
		try {
			ItemPackage p = vixntBaseService.findEntityById(ItemPackage.class, id);
			if (null != p) {
				for (Item ep : p.getSubItems()) {
					ep.setItemPackage(null);
					vixntBaseService.merge(ep);
				}
				p.getSubItems().clear();
				vixntBaseService.deleteByEntity(p);
				renderText(DELETE_SUCCESS);
			} else {
				renderText("商品包不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void addEcProductToPackage() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				ItemPackage epp = vixntBaseService.findEntityById(ItemPackage.class, id);
				String ecProductIds = getRequestParameter("ecProductIds");
				if (null != epp && StringUtils.isNotEmpty(ecProductIds)) {
					for (String epId : ecProductIds.split(",")) {
						if (StringUtils.isNotEmpty(epId)) {
							Item ep = vixntBaseService.findEntityById(Item.class, epId);
							if (null != ep) {
								ep.setItemPackage(epp);
								vixntBaseService.merge(ep);
							}
						}
					}
				}
				renderText(SAVE_SUCCESS);
			} else {
				renderText("商品包不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
	}
	public String removeEcProductFromPackage() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				Item ep = vixntBaseService.findEntityById(Item.class, id);
				if (null != ep) {
					ep.setItemPackage(null);
					vixntBaseService.merge(ep);
				}
				renderText(DELETE_SUCCESS);
			} else {
				renderText("商品不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goChooseEcProduct() {
		return "goChooseEcProduct";
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

	public ItemPackage getItemPackage() {
		return itemPackage;
	}

	public void setItemPackage(ItemPackage itemPackage) {
		this.itemPackage = itemPackage;
	}
}
