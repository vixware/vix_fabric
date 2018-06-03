package com.vix.nvix.system.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.Dimension;
import com.vix.mdm.item.entity.DimensionDetail;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.nvix.common.base.action.VixntBaseAction;
@Controller
@Scope("prototype")
public class NvixDimensionAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private Dimension dimension;
	private DimensionDetail dimensionDetail;
	private String itemCatalogId;
	private String dimensionId;

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (StrUtils.objectIsNotNull(id)) {
				params.put("itemCatalog.id," + SearchCondition.EQUAL, id);
			}
			if (StringUtils.isNotEmpty(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name.trim());
			}
			vixntBaseService.findPagerByHqlConditions(pager, Dimension.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				dimension = vixntBaseService.findEntityById(Dimension.class, id);
			} else {
				if (StringUtils.isNotEmpty(itemCatalogId)) {
					ItemCatalog itemCatalog = vixntBaseService.findEntityById(ItemCatalog.class, itemCatalogId);
					if (null != itemCatalog) {
						dimension = new Dimension();
						dimension.setItemCatalog(itemCatalog);
					}
				}
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
			if (StrUtils.objectIsNotNull(dimension.getId())) {
				isSave = false;
				dimension.setUpdateTime(new Date());
			} else {
				dimension.setCreateTime(new Date());
			}

			/** 检查空值对象 */
			String[] attrArray = {"itemCatalog"};
			checkEntityNullValue(dimension, attrArray);

			if (null == dimension.getItemCatalog()) {
				renderText("未绑定商品分类!");
				return;
			}

			dimension = vixntBaseService.merge(dimension);

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

	public void saveOrUpdateInner() {
		try {
			if (null == dimension.getItemCatalog()) {
				renderText("0:未绑定商品分类!");
				return;
			}
			dimension.setCreateTime(new Date());
			dimension = vixntBaseService.merge(dimension);
			renderText(dimension.getId() + ":" + SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:" + SAVE_FAIL);
		}
	}
	/** 处理删除操作 */
	public void deleteById() {
		try {
			Dimension emp = vixntBaseService.findEntityById(Dimension.class, id);
			if (null != emp) {
				vixntBaseService.deleteByAttribute(DimensionDetail.class, "dimension.id", emp.getId());
				vixntBaseService.deleteByEntity(emp);
				renderText(DELETE_SUCCESS);
			} else {
				renderText("维度不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/**
	 * 维度明细列表
	 */
	public void goSingleListJson() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name.trim());
			}
			if (StringUtils.isNotEmpty(dimensionId)) {
				params.put("dimension.id," + SearchCondition.EQUAL, dimensionId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, DimensionDetail.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdateDimensionDetail() {
		try {
			if (StrUtils.objectIsNotNull(id)) {
				dimensionDetail = vixntBaseService.findEntityById(DimensionDetail.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateDimensionDetail";
	}

	/** 处理修改操作 */
	public void saveOrUpdateDimensionDetail() {
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(dimensionDetail.getId())) {
				isSave = false;
				dimensionDetail.setUpdateTime(new Date());
			} else {
				dimensionDetail.setCreateTime(new Date());
			}

			/** 检查空值对象 */
			String[] attrArray = {"dimension"};
			checkEntityNullValue(dimensionDetail, attrArray);

			/** 持久化数据 */
			dimensionDetail = vixntBaseService.merge(dimensionDetail);
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
	public void deleteDimensionDetailById() {
		try {
			DimensionDetail pb = vixntBaseService.findEntityById(DimensionDetail.class, id);
			if (null != pb) {
				vixntBaseService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				renderText("维度明细不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Dimension getDimension() {
		return dimension;
	}
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	public DimensionDetail getDimensionDetail() {
		return dimensionDetail;
	}
	public void setDimensionDetail(DimensionDetail dimensionDetail) {
		this.dimensionDetail = dimensionDetail;
	}
	public String getItemCatalogId() {
		return itemCatalogId;
	}
	public void setItemCatalogId(String itemCatalogId) {
		this.itemCatalogId = itemCatalogId;
	}

	public String getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(String dimensionId) {
		this.dimensionId = dimensionId;
	}
}
