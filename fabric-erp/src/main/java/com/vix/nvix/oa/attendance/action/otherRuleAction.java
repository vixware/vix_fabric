/**
 * 
 */
package com.vix.nvix.oa.attendance.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.nvix.oa.attendance.entity.OtherRule;

/**
 * @author Bluesnow 2016年7月14日
 */
@Controller
@Scope("prototype")
public class otherRuleAction extends BaseAction {

	private static final long serialVersionUID = 1L;


	private String id;
	private OtherRule otherRule;

	@Override
	public String goList() {
		return GO_LIST;
	}

	@SuppressWarnings("unchecked")
	public void getOtherRulesJson() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();

			String searchotherRule = getDecodeRequestParameter("searchotherRule");
			if (StringUtils.isNotEmpty(searchotherRule)) {
				params.put("name," + SearchCondition.ANYLIKE, searchotherRule);
			}

			baseHibernateService.findPagerByHqlConditions(pager, OtherRule.class,
					params);

			if (pager.getResultList().size() < pager.getPageSize()) {
				int count = pager.getPageSize() - pager.getResultList().size();
				for (int i = 0; i < count; i++) {
					pager.getResultList().add(new OtherRule());
				}
			}

			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				otherRule = baseHibernateService.findEntityById(OtherRule.class,id);
			} else {
				otherRule = new OtherRule();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(otherRule.getId())) {
				isSave = false;
				otherRule.setUpdateTime(new Date());
				otherRule.setIsTemp("0");
			} else {
				otherRule.setCreator(SecurityUtil.getCurrentUserName());
				otherRule.setCreateTime(new Date());
				otherRule.setIsTemp("0");
			}
			loadCommonData(otherRule);
			/** 检查空值对象 */
			/*String[] attrArray = { "workShiftRule", "basicRule", "org" };
			checkEntityNullValue(otherRule, attrArray);*/
			baseHibernateService.merge(otherRule);

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OtherRule getOtherRule() {
		return otherRule;
	}

	public void setOtherRule(OtherRule otherRule) {
		this.otherRule = otherRule;
	}

}
