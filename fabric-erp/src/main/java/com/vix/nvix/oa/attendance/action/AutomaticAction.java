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
import com.vix.nvix.oa.attendance.entity.AutomaticRule;

/**
 * @author Bluesnow 2016年7月18日
 */
@Controller
@Scope("prototype")
public class AutomaticAction extends BaseAction {

	private static final long serialVersionUID = 1L;


	private String id;
	private AutomaticRule automaticRule;

	@Override
	public String goList() {
		return GO_LIST;
	}

	@SuppressWarnings("unchecked")
	public void getAutomaticsJson() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();

			String searchAutomaticRule = getDecodeRequestParameter("searchAutomaticRule");
			if (StringUtils.isNotEmpty(searchAutomaticRule)) {
				params.put("name," + SearchCondition.ANYLIKE, searchAutomaticRule);
			}

			baseHibernateService.findPagerByHqlConditions(pager, AutomaticRule.class,params);

			if (pager.getResultList().size() < pager.getPageSize()) {
				int count = pager.getPageSize() - pager.getResultList().size();
				for (int i = 0; i < count; i++) {
					pager.getResultList().add(new AutomaticRule());
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
				automaticRule = baseHibernateService.findEntityById(AutomaticRule.class, id);
			} else {
				automaticRule = new AutomaticRule();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(automaticRule.getId())) {
				isSave = false;
				automaticRule.setIsTemp("0");
				automaticRule.setUpdateTime(new Date());
			} else {
				automaticRule.setIsTemp("0");
				automaticRule.setCreateTime(new Date());
				automaticRule.setCreator(SecurityUtil.getCurrentUserName());
			}
			loadCommonData(automaticRule);
			/** 检查空值对象 */
			/*String[] attrArray = { "workShiftRule", "basicRule", "org" };
			checkEntityNullValue(automaticRule, attrArray);*/

			baseHibernateService.merge(automaticRule);

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

	public AutomaticRule getAutomaticRule() {
		return automaticRule;
	}

	public void setAutomaticRule(AutomaticRule automaticRule) {
		this.automaticRule = automaticRule;
	}

}
