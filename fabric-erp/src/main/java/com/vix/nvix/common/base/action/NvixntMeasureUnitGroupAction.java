/**
 * 
 */
package com.vix.nvix.common.base.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.entity.Organization;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;

/**
 * @author Bluesnow
 * 2016年8月13日
 * 
 * 计量单位
 */
@Controller
@Scope("prototype")
public class NvixntMeasureUnitGroupAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	

	private String id;
	private String name;
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
	@SuppressWarnings("unchecked")
	public void getMeasureUnitJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();

			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			pager = baseHibernateService.findPagerByHqlConditions(getPager(), MeasureUnitGroup.class, params);
			
			if (pager.getResultList().size() < 10) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 10 - listSize; i++) {
					pager.getResultList().add(new MeasureUnitGroup());
				}
			}
			
			String[] excludes = {""};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
				measureUnitGroup.setStatus("1");
				Employee baseEmp = getEmployee();
				if(null != baseEmp){
					measureUnitGroup.setOrganization(baseEmp.getOrganizationUnit().getOrganization());
				}
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
			measureUnitGroup.setIsTemp("0");
			measureUnitGroup = baseHibernateService.merge(measureUnitGroup);
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
			/** 设置分页信息 */
			Pager pager = getPager();
			if(null == pager.getOrderField() || "".equals(pager.getOrderField())){
				pager.setOrderBy("desc");
			}
			Map<String,Object> params = getParams();
			if(null != parentId && !"".equals(parentId)){
				params.put("measureUnitGroup.id,"+SearchCondition.EQUAL, parentId);
				baseHibernateService.findPagerByHqlConditions(pager, MeasureUnit.class, params);
			}
			String [] excludes = {};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdateMeasureUnitGroupDetail() {
		try {
			measureUnitList = baseHibernateService.findAllByEntityClass(MeasureUnit.class);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
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
			measureUnitGroup = baseHibernateService.findEntityById(MeasureUnitGroup.class, parentId);
			measureUnit.setMeasureUnitGroup(measureUnitGroup);
			measureUnit = baseHibernateService.merge(measureUnit);
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
	
	/**
	 * 删除计量单位明细
	 * 
	 * */
	
	public String deleteDetailById() {
		try {
			MeasureUnit measureUnit = baseHibernateService.findEntityById(MeasureUnit.class, id);
			if (null != measureUnit) {
				baseHibernateService.deleteByEntity(measureUnit);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("没有找到该单位,请联系管理员!"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL + "请确认该单位是否已经被引用!");
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}