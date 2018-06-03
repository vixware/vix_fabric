package com.vix.common.share.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class MeasureUnitGroupAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	/**
	 * 计量单位组
	 */
	private MeasureUnitGroup measureUnitGroup;
	/**
	 * 计量单位组列表
	 */
	private List<MeasureUnitGroup> measureUnitGroupList;

	private MeasureUnit measureUnit;

	private List<MeasureUnit> measureUnitList;

	private List<Organization> organizationList;

	private String treeType;

	private String parentId;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (id != null && id.longValue() > 0) {
				if ("C".equals(treeType)) {
					params.put("organization.id," + SearchCondition.EQUAL, id);
					// 点击的树节点是公司
				} else if ("CH".equals(treeType)) {
				}
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), MeasureUnitGroup.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			organizationList = baseHibernateService.findAllByConditions(Organization.class, params);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				measureUnitGroup = baseHibernateService.findEntityById(MeasureUnitGroup.class, id);
			} else {
				measureUnitGroup = new MeasureUnitGroup();
				measureUnitGroup.setIsTemp("1");
				if(StringUtils.isNotEmpty(parentId) && !parentId.equals("0")){//if (parentId != null && parentId.longValue() > 0) {
					Organization organization = baseHibernateService.findEntityById(Organization.class, parentId);
					measureUnitGroup.setOrganization(organization);
				}
				measureUnitGroup = baseHibernateService.merge(measureUnitGroup);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != measureUnitGroup.getId()) {
				isSave = false;
			}
			measureUnitGroup.setIsTemp("");
			measureUnitGroup = baseHibernateService.merge(measureUnitGroup);
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
			MeasureUnitGroup measureUnitGroup = baseHibernateService.findEntityById(MeasureUnitGroup.class, id);
			if (null != measureUnitGroup) {
				baseHibernateService.deleteByEntity(measureUnitGroup);
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

	public void getMeasureUnitGroupDetailJson() {
		try {

			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				MeasureUnitGroup measureUnitGroup = baseHibernateService.findEntityById(MeasureUnitGroup.class, id);
				if (measureUnitGroup != null) {
					json = convertListToJson(new ArrayList<MeasureUnit>(measureUnitGroup.getMeasureUnits()), measureUnitGroup.getMeasureUnits().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdateMeasureUnitGroupDetail() {
		try {
			measureUnitList = baseHibernateService.findAllByEntityClass(MeasureUnit.class);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				measureUnit = baseHibernateService.findEntityById(MeasureUnit.class, id);
			} else {
				measureUnit = new MeasureUnit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateMeasureUnitGroupDetail";
	}

	/**
	 * 保存计量单位组明细
	 * 
	 * @return
	 */
	public String saveOrUpdateMeasureUnitGroupDetail() {
		boolean isSave = true;
		try {
			if (null != measureUnit.getId()) {
				isSave = false;
			}
			measureUnitGroup = baseHibernateService.findEntityById(MeasureUnitGroup.class, id);
			measureUnit.setMeasureUnitGroup(measureUnitGroup);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MeasureUnitGroup getMeasureUnitGroup() {
		return measureUnitGroup;
	}

	public void setMeasureUnitGroup(MeasureUnitGroup measureUnitGroup) {
		this.measureUnitGroup = measureUnitGroup;
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

	public MeasureUnit getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(MeasureUnit measureUnit) {
		this.measureUnit = measureUnit;
	}

}
