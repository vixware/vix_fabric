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
import com.vix.nvix.oa.attendance.entity.OverTimeRule;

/**
 * @author Bluesnow 2016年7月16日
 */
@Controller
@Scope("prototype")
public class OverTimeAction extends BaseAction {

	private static final long serialVersionUID = 1L;


	private String id;
	private OverTimeRule overTimeRule;
	
	@Override
	public String goList(){
		return GO_LIST;
	}
	
	@SuppressWarnings("unchecked")
	public void getOverTimeRulesJson(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			String searchOverTimeRule = getDecodeRequestParameter("searchOverTimeRule");
			if (StringUtils.isNotEmpty(searchOverTimeRule)) {
				params.put("name," + SearchCondition.ANYLIKE, searchOverTimeRule);
			}

			baseHibernateService.findPagerByHqlConditions(pager,OverTimeRule.class, params);

			if (pager.getResultList().size() < pager.getPageSize()) {
				int count = pager.getPageSize() - pager.getResultList().size();
				for (int i = 0; i < count; i++) {
					pager.getResultList().add(new OverTimeRule());
				}
			}

			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdate(){
		try {
			if (StringUtils.isNotEmpty(id)) {
				overTimeRule = baseHibernateService.findEntityById(OverTimeRule.class, id);
				overTimeRule.setIsTemp("0");
			}else {
				overTimeRule = new OverTimeRule();
				overTimeRule.setIsTemp("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	public String saveOrUpdate(){
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(overTimeRule.getId())) {
				isSave = false;
				overTimeRule.setUpdateTime(new Date());
				overTimeRule.setIsTemp("0");
			} else {
				overTimeRule.setCreator(SecurityUtil.getCurrentUserName());
				overTimeRule.setCreateTime(new Date());
				overTimeRule.setIsTemp("0");
			}
			loadCommonData(overTimeRule);
			/** 检查空值对象 */
			/*String[] attrArray = {"periodRule","basicRule","org"};
			checkEntityNullValue(periodRule, attrArray);*/

			baseHibernateService.merge(overTimeRule);

			if (isSave) {
				setMessage(overTimeRule.getId() + ":" + SAVE_SUCCESS);
			} else {
				setMessage(overTimeRule.getId() + ":" + UPDATE_SUCCESS);
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

	public OverTimeRule getOverTimeRule() {
		return overTimeRule;
	}

	public void setOverTimeRule(OverTimeRule overTimeRule) {
		this.overTimeRule = overTimeRule;
	}

}
