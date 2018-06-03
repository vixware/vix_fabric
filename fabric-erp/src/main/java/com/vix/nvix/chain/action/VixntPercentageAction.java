package com.vix.nvix.chain.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.EmployeeType;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 
 * @类全名 com.vix.nvix.chain.action.VixntPercentageAction
 * 
 * @author zhanghaibing
 *
 * @date 2017年11月16日
 */
@Controller
@Scope("prototype")
public class VixntPercentageAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private EmployeeType employeeType;

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
			pager = vixntBaseService.findPagerByHqlConditions(pager, EmployeeType.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				employeeType = vixntBaseService.findEntityById(EmployeeType.class, id);
			} else {
				employeeType = new EmployeeType();
				employeeType.setCode(VixUUID.getUUID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (employeeType != null && StringUtils.isNotEmpty(employeeType.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(employeeType);
			employeeType = vixntBaseService.merge(employeeType);
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

	public void deleteEmployeeTypeById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				employeeType = vixntBaseService.findEntityById(EmployeeType.class, id);
				if (null != employeeType) {
					vixntBaseService.deleteByEntity(employeeType);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
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

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

}