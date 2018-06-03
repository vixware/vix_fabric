package com.vix.common.share.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class MeasureUnitAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	/**
	 * 计量单位
	 */
	private MeasureUnit measureUnit;
	/**
	 * 单位类型
	 */
	private MeasureUnitType measureUnitType;

	private List<MeasureUnitType> measureUnitTypeList;

	private List<Organization> organizationList;

	private String treeType;
	private String parentId;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			params.put("tenantId," + SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null == id || id == 0) {
				
			} else {
				if ("C".equals(treeType)) {
					params.put("organization.id," + SearchCondition.EQUAL, id);
					// 点击的树节点是公司
				} else if ("CH".equals(treeType)) {
				}
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), MeasureUnit.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId," + SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
			organizationList = baseHibernateService.findAllByConditions(Organization.class, params);
			measureUnitTypeList = baseHibernateService.findAllByEntityClass(MeasureUnitType.class);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				measureUnit = baseHibernateService.findEntityById(MeasureUnit.class, id);
			} else {
				measureUnit = new MeasureUnit();

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
			if (null != measureUnit.getId()) {
				isSave = false;
			}
			measureUnit = baseHibernateService.merge(measureUnit);
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

	/**
	 * 设置计量单位类型
	 * 
	 * @return
	 */
	public String goSaveOrUpdateUnitType() {
		return "goSaveOrUpdateUnitType";
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

	public MeasureUnit getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(MeasureUnit measureUnit) {
		this.measureUnit = measureUnit;
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

	public List<Organization> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(List<Organization> organizationList) {
		this.organizationList = organizationList;
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
