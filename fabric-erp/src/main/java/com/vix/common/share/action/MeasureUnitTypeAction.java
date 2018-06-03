package com.vix.common.share.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitType;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class MeasureUnitTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	/**
	 * 单位类型
	 */
	private MeasureUnitType measureUnitType;

	private List<MeasureUnitType> measureUnitTypeList;

	private String treeType;
	private String parentId;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), MeasureUnitType.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 设置计量单位类型
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				measureUnitType = baseHibernateService.findEntityById(MeasureUnitType.class, id);
			} else {
				measureUnitType = new MeasureUnitType();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	/**
	 * 保存单位类型
	 * 
	 * @return
	 */
	public String saveOrUpdateUnitType() {
		boolean isSave = true;
		try {
			if (null != measureUnitType.getId()) {
				isSave = false;
			}
			measureUnitType = baseHibernateService.merge(measureUnitType);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(SAVE_SUCCESS);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			MeasureUnit measureUnit = baseHibernateService.findEntityById(MeasureUnit.class, id);
			if (null != measureUnit) {

				baseHibernateService.deleteByEntity(measureUnit);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MeasureUnitType getMeasureUnitType() {
		return measureUnitType;
	}

	public void setMeasureUnitType(MeasureUnitType measureUnitType) {
		this.measureUnitType = measureUnitType;
	}

	public List<MeasureUnitType> getMeasureUnitTypeList() {
		return measureUnitTypeList;
	}

	public void setMeasureUnitTypeList(List<MeasureUnitType> measureUnitTypeList) {
		this.measureUnitTypeList = measureUnitTypeList;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
