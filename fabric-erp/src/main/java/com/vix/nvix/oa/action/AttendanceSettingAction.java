/**
 * 
 */
package com.vix.nvix.oa.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.nvix.oa.entity.AttendanceRule;

/**
 * @author Bluesnow 2016年5月28日
 */
@Controller
@Scope("prototype")
public class AttendanceSettingAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private String searchRuleType;
	private AttendanceRule attendanceRule;
	@Override
	public String goList() {
		return GO_LIST;
	}

	@SuppressWarnings("unchecked")
	public void getRulesJson() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();

			String searchRuleType = getDecodeRequestParameter("searchRuleType");
			if (StringUtils.isNotEmpty(searchRuleType)) {
				params.put("ruleType," + SearchCondition.ANYLIKE, searchRuleType);
			}

			baseHibernateService.findPagerByHqlConditions(pager, AttendanceRule.class, params);

			if (pager.getResultList().size() < pager.getPageSize()) {
				int count = pager.getPageSize() - pager.getResultList().size();
				for (int i = 0; i < count; i++) {
					pager.getResultList().add(new AttendanceRule());
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
			if (StrUtils.objectIsNotNull(id) && !"0".equals(id)) {
				attendanceRule = baseHibernateService.findEntityById(AttendanceRule.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(attendanceRule.getId())) {
				isSave = false;
				attendanceRule.setUpdateTime(new Date());
				attendanceRule.setCreateTime(attendanceRule.getCreateTime());
			} else {
				attendanceRule.setCreateTime(new Date());
			}

			loadCommonData(attendanceRule);
			baseHibernateService.merge(attendanceRule);

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

	public void deleteById() {
		try {
			attendanceRule = baseHibernateService.findEntityById(AttendanceRule.class, id);
			if (null != attendanceRule) {
				baseHibernateService.deleteById(AttendanceRule.class, id);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage("删除失败,请联系管理员！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSearchRuleType() {
		return searchRuleType;
	}

	public void setSearchRuleType(String searchRuleType) {
		this.searchRuleType = searchRuleType;
	}

	public AttendanceRule getAttendanceRule() {
		return attendanceRule;
	}

	public void setAttendanceRule(AttendanceRule attendanceRule) {
		this.attendanceRule = attendanceRule;
	}
}
