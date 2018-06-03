package com.vix.common.share.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.orginialMeta.entity.OrginialMeasureUnit;
import com.vix.common.orginialMeta.entity.OrginialMeasureUnitGroup;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class OrginialMeasureUnitAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	/**
	 * 计量单位组 OrginialMeasureUnitGroup
	 */
	private OrginialMeasureUnitGroup orginialMeasureUnitGroup;
	/**
	 * 计量单位 OrginialMeasureUnit
	 */
	private OrginialMeasureUnit orginialMeasureUnit;

	private String treeType;

	private String parentId;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), OrginialMeasureUnitGroup.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				orginialMeasureUnitGroup = baseHibernateService.findEntityById(OrginialMeasureUnitGroup.class, id);
			} else {
				orginialMeasureUnitGroup = new OrginialMeasureUnitGroup();
				orginialMeasureUnitGroup.setIsTemp("1");
				orginialMeasureUnitGroup = baseHibernateService.mergeOriginal(orginialMeasureUnitGroup);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != orginialMeasureUnitGroup.getId()) {
				isSave = false;
			}
			orginialMeasureUnitGroup.setIsTemp("");
			orginialMeasureUnitGroup = baseHibernateService.mergeOriginal(orginialMeasureUnitGroup);
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
			OrginialMeasureUnitGroup orginialMeasureUnitGroup = baseHibernateService.findEntityById(OrginialMeasureUnitGroup.class, id);
			if (null != orginialMeasureUnitGroup) {
				baseHibernateService.deleteByEntity(orginialMeasureUnitGroup);
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

	public String deleteOrginialMeasureUnitById() {
		try {
			OrginialMeasureUnit orginialMeasureUnit = baseHibernateService.findEntityById(OrginialMeasureUnit.class, id);
			if (null != orginialMeasureUnit) {
				baseHibernateService.deleteByEntity(orginialMeasureUnit);
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

	public void getOrginialMeasureUnitJson() {
		try {

			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				OrginialMeasureUnitGroup orginialMeasureUnitGroup = baseHibernateService.findEntityById(OrginialMeasureUnitGroup.class, id);
				if (orginialMeasureUnitGroup != null) {
					json = convertListToJson(new ArrayList<OrginialMeasureUnit>(orginialMeasureUnitGroup.getOrginialMeasureUnits()), orginialMeasureUnitGroup.getOrginialMeasureUnits().size());
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

	public String goSaveOrUpdateOrginialMeasureUnit() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				orginialMeasureUnit = baseHibernateService.findEntityById(OrginialMeasureUnit.class, id);
			} else {
				orginialMeasureUnit = new OrginialMeasureUnit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateOrginialMeasureUnit";
	}

	/**
	 * 保存计量单位组明细
	 * 
	 * @return
	 */
	public String saveOrUpdateOrginialMeasureUnit() {
		boolean isSave = true;
		try {
			if (null != orginialMeasureUnit.getId()) {
				isSave = false;
			}
			OrginialMeasureUnitGroup orginialMeasureUnitGroup = baseHibernateService.findEntityById(OrginialMeasureUnitGroup.class, id);
			orginialMeasureUnit.setOrginialMeasureUnitGroup(orginialMeasureUnitGroup);
			orginialMeasureUnit = baseHibernateService.mergeOriginal(orginialMeasureUnit);
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

	public OrginialMeasureUnitGroup getOrginialMeasureUnitGroup() {
		return orginialMeasureUnitGroup;
	}

	public void setOrginialMeasureUnitGroup(OrginialMeasureUnitGroup orginialMeasureUnitGroup) {
		this.orginialMeasureUnitGroup = orginialMeasureUnitGroup;
	}

	public OrginialMeasureUnit getOrginialMeasureUnit() {
		return orginialMeasureUnit;
	}

	public void setOrginialMeasureUnit(OrginialMeasureUnit orginialMeasureUnit) {
		this.orginialMeasureUnit = orginialMeasureUnit;
	}

}
