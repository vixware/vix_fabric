/**
 * 
 */
package com.vix.nvix.oa.attendance.action;

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
import com.vix.nvix.oa.attendance.entity.AttendanceRuleSet;
import com.vix.nvix.oa.attendance.entity.AutomaticRule;
import com.vix.nvix.oa.attendance.entity.BasicRule;
import com.vix.nvix.oa.attendance.entity.CalculationRule;
import com.vix.nvix.oa.attendance.entity.OtherRule;
import com.vix.nvix.oa.attendance.entity.OverTimeRule;
import com.vix.nvix.oa.attendance.entity.PeriodRule;
import com.vix.nvix.oa.entity.AttendanceRule;

/**
 * @author Bluesnow 2016年7月12日
 * 
 *         基本规则设置
 */

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class AttendanceRuleSetAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String userCode;

	private BasicRule basicRule;
	private OtherRule otherRule;
	private AttendanceRuleSet attRuleSet;
	private List<AttendanceRule> attendanceRuleList;
	private List<PeriodRule> periodRuleList;
	private List<BasicRule> basicRuleList;
	private List<OtherRule> otherRuleList;
	private List<AutomaticRule> automaticRuleList;
	private List<OverTimeRule> overTimeRuleList;
	private List<CalculationRule> calculationRuleList;

	public String goRuleSet() {
		return "ruleSetList";
	}

	public void getRuleSetsJson() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();

			String name = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}

			baseHibernateService.findPagerByHqlConditions(pager, AttendanceRuleSet.class, params);

			if (pager.getResultList().size() < pager.getPageSize()) {
				int count = pager.getPageSize() - pager.getResultList().size();
				for (int i = 0; i < count; i++) {
					pager.getResultList().add(new AttendanceRuleSet());
				}
			}

			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdateRuleSet() {
		try {
			basicRuleList = baseHibernateService.findAllByEntityClass(BasicRule.class);
			otherRuleList = baseHibernateService.findAllByEntityClass(OtherRule.class);
			automaticRuleList = baseHibernateService.findAllByEntityClass(AutomaticRule.class);
			overTimeRuleList = baseHibernateService.findAllByEntityClass(OverTimeRule.class);
			calculationRuleList = baseHibernateService.findAllByEntityClass(CalculationRule.class);
			periodRuleList = baseHibernateService.findAllByEntityClass(PeriodRule.class);
			if (StringUtils.isNotEmpty(id)) {
				attRuleSet = baseHibernateService.findEntityById(AttendanceRuleSet.class, id);
			} else {
				attRuleSet = new AttendanceRuleSet();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ruleSet";
	}

	public void saveOrUpdateRuleSet() {
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(attRuleSet.getId())) {
				isSave = false;
				attRuleSet.setCreateTime(new Date());
				attRuleSet.setIsTemp("0");
			} else {
				loadCommonData(attRuleSet);
				attRuleSet.setUpdateTime(new Date());
				attRuleSet.setIsTemp("0");
			}
			if (StrUtils.isNotEmpty(attRuleSet.getExerciseDate())) {
				attRuleSet.setExerciseDate("," + attRuleSet.getExerciseDate().replaceAll(" ", "") + ",");
			}
			loadCommonData(attRuleSet);
			/** 检查空值对象 */

			String[] attrArray = { "periodRule", "basicRule", "org", "overTimeRule", "automaticRule", "calculationRule", "otherRule" };
			checkEntityNullValue(attRuleSet, attrArray);

			baseHibernateService.merge(attRuleSet);

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

	/**
	 * 基本规则
	 */

	public void getRuleDetailsJson() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();

			String searchDetail = getDecodeRequestParameter("searchDetail");
			if (StringUtils.isNotEmpty(searchDetail)) {
				params.put("name," + SearchCondition.ANYLIKE, searchDetail);
			}
			baseHibernateService.findPagerByHqlConditions(pager, BasicRule.class, params);
			if (pager.getResultList().size() < pager.getPageSize()) {
				int count = pager.getPageSize() - pager.getResultList().size();
				for (int i = 0; i < count; i++) {
					pager.getResultList().add(new BasicRule());
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goRuleDetail() {
		return "ruleDetailList";
	}

	public String goSaveOrUpdateRuleDetail() {
		try {
			if (StrUtils.isNotEmpty(id)) {
				basicRule = baseHibernateService.findEntityById(BasicRule.class, id);
			} else {
				basicRule = new BasicRule();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ruleDetail";
	}

	public String saveOrUpdateRuleDetail() {
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(basicRule.getId())) {
				isSave = false;
				basicRule.setCreateTime(new Date());
				basicRule.setIsTemp("0");
			} else {
				basicRule.setUpdateTime(new Date());
				basicRule.setIsTemp("0");
			}
			loadCommonData(basicRule);
			/** 检查空值对象 */
			String[] attrArray = { "aaRecord" };
			checkEntityNullValue(basicRule, attrArray);

			baseHibernateService.merge(basicRule);

			if (isSave) {
				setMessage(basicRule.getId() + ":" + SAVE_SUCCESS);
			} else {
				setMessage(basicRule.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage("0:" + SAVE_FAIL);
			} else {
				this.setMessage("0:" + UPDATE_FAIL);
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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public List<AttendanceRule> getAttendanceRuleList() {
		return attendanceRuleList;
	}

	public void setAttendanceRuleList(List<AttendanceRule> attendanceRuleList) {
		this.attendanceRuleList = attendanceRuleList;
	}

	public BasicRule getBasicRule() {
		return basicRule;
	}

	public void setBasicRule(BasicRule basicRule) {
		this.basicRule = basicRule;
	}

	public AttendanceRuleSet getAttRuleSet() {
		return attRuleSet;
	}

	public void setAttRuleSet(AttendanceRuleSet attRuleSet) {
		this.attRuleSet = attRuleSet;
	}

	public OtherRule getOtherRule() {
		return otherRule;
	}

	public void setOtherRule(OtherRule otherRule) {
		this.otherRule = otherRule;
	}

	public List<PeriodRule> getPeriodRuleList() {
		return periodRuleList;
	}

	public void setPeriodRuleList(List<PeriodRule> periodRuleList) {
		this.periodRuleList = periodRuleList;
	}

	public List<OtherRule> getOtherRuleList() {
		return otherRuleList;
	}

	public void setOtherRuleList(List<OtherRule> otherRuleList) {
		this.otherRuleList = otherRuleList;
	}

	public List<AutomaticRule> getAutomaticRuleList() {
		return automaticRuleList;
	}

	public void setAutomaticRuleList(List<AutomaticRule> automaticRuleList) {
		this.automaticRuleList = automaticRuleList;
	}

	public List<OverTimeRule> getOverTimeRuleList() {
		return overTimeRuleList;
	}

	public void setOverTimeRuleList(List<OverTimeRule> overTimeRuleList) {
		this.overTimeRuleList = overTimeRuleList;
	}

	public List<CalculationRule> getCalculationRuleList() {
		return calculationRuleList;
	}

	public void setCalculationRuleList(List<CalculationRule> calculationRuleList) {
		this.calculationRuleList = calculationRuleList;
	}

	public List<BasicRule> getBasicRuleList() {
		return basicRuleList;
	}

	public void setBasicRuleList(List<BasicRule> basicRuleList) {
		this.basicRuleList = basicRuleList;
	}

}
