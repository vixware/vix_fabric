/**
 * 
 */
package com.vix.nvix.oa.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.nvix.oa.entity.AttendanceAndRecord;
import com.vix.nvix.oa.entity.AttendanceRule;

/**
 * @author Bluesnow 2016年6月22日
 */
@Controller
@Scope("prototype")
public class AttendanceRuleAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private AttendanceRule attendanceRule;
	private List<AttendanceRule> attendanceRuleList;
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
					pager.getResultList().add(new AttendanceAndRecord());
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
			if (StringUtils.isNotEmpty(id)) {
				attendanceRule = baseHibernateService.findEntityById(AttendanceRule.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(attendanceRule.getId())) {
				isSave = false;
				attendanceRule.setCreateTime(new Date());
				attendanceRule.setIsTemp("0");
			} else {
				loadCommonData(attendanceRule);
				attendanceRule.setUpdateTime(new Date());
				attendanceRule.setIsTemp("0");
			}
			loadCommonData(attendanceRule);
			/** 检查空值对象 */
			// String[] attrArray =
			// {"customerAccountCategory","hotLevel","customerType","relationshipClass","industry"};
			// checkEntityNullValue(customerAccount, attrArray);

			attendanceRule.setName("1111");
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

	/**
	 * 基本规则
	 */
	public String addRuleDetail() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ruleDetail";
	}

	/**
	 * 加班规则
	 */
	public String addOverTime() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "overTime";
	}

	/**
	 * 工作日设定
	 */
	public String addWorkDay() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "workDay";
	}

	/**
	 * 自动套班
	 */
	public String addAutomatic() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "automatic";
	}

	/**
	 * 计算方式
	 * 
	 */
	public String addCalculation() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "calculation";
	}

	/**
	 * 其他设定
	 * 
	 */
	public String addOther() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "other";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AttendanceRule getAttendanceRule() {
		return attendanceRule;
	}

	public void setAttendanceRule(AttendanceRule attendanceRule) {
		this.attendanceRule = attendanceRule;
	}

	public List<AttendanceRule> getAttendanceRuleList() {
		return attendanceRuleList;
	}

	public void setAttendanceRuleList(List<AttendanceRule> attendanceRuleList) {
		this.attendanceRuleList = attendanceRuleList;
	}
}
