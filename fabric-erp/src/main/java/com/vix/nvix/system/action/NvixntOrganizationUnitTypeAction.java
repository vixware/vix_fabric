package com.vix.nvix.system.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.entity.OrganizationUnitType;

/**
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.system.action.NvixntOrganizationUnitTypeAction
 *
 * @date 2018年2月26日
 */
@Controller
@Scope("prototype")
public class NvixntOrganizationUnitTypeAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private OrganizationUnitType organizationUnitType;

	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, searchName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, OrganizationUnitType.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				organizationUnitType = vixntBaseService.findEntityById(OrganizationUnitType.class, id);
			} else {
				organizationUnitType = new OrganizationUnitType();
				organizationUnitType.setCode(VixUUID.getUUID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (organizationUnitType != null && StringUtils.isNotEmpty(organizationUnitType.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(organizationUnitType);
			organizationUnitType = vixntBaseService.merge(organizationUnitType);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText("更新失败");
			}
		}
	}
	public void deleteOrganizationUnitTypeById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				organizationUnitType = vixntBaseService.findEntityById(OrganizationUnitType.class, id);
				if (null != organizationUnitType) {
					vixntBaseService.deleteByEntity(organizationUnitType);
					renderText(DELETE_SUCCESS);
				}
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

	/**
	 * @return the organizationUnitType
	 */
	public OrganizationUnitType getOrganizationUnitType() {
		return organizationUnitType;
	}

	/**
	 * @param organizationUnitType
	 *            the organizationUnitType to set
	 */
	public void setOrganizationUnitType(OrganizationUnitType organizationUnitType) {
		this.organizationUnitType = organizationUnitType;
	}

}
