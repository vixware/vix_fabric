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
import com.vix.nvix.oa.attendance.entity.PeriodRule;

/**
 * @author Bluesnow 2016年7月14日
 * 
 *         时间段设置
 */
@Controller
@Scope("prototype")
public class PeriodAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;

	private PeriodRule periodRule;
	
	@Override
	public String goList(){
		return GO_LIST;
	}
	
	@SuppressWarnings("unchecked")
	public void getPeriodRulesJson(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();

			String searchPeriodRule = getDecodeRequestParameter("searchPeriodRule");
			if (StringUtils.isNotEmpty(searchPeriodRule)) {
				params.put("name," + SearchCondition.ANYLIKE, searchPeriodRule);
			}

			baseHibernateService.findPagerByHqlConditions(pager,PeriodRule.class, params);

			if (pager.getResultList().size() < pager.getPageSize()) {
				int count = pager.getPageSize() - pager.getResultList().size();
				for (int i = 0; i < count; i++) {
					pager.getResultList().add(new PeriodRule());
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
				periodRule = baseHibernateService.findEntityById(PeriodRule.class, id);
				periodRule.setIsTemp("0");
			}else {
				periodRule = new PeriodRule();
				periodRule.setCreator(SecurityUtil.getCurrentUserName());
				periodRule.setIsTemp("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	public String saveOrUpdate(){
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(periodRule.getId())) {
				isSave = false;
				periodRule.setUpdateTime(new Date());
				periodRule.setIsTemp("0");
			} else {
				periodRule.setCreateTime(new Date());
				periodRule.setIsTemp("0");
			}
			loadCommonData(periodRule);
			/** 检查空值对象 */
			/*String[] attrArray = {"periodRule","basicRule","org"};
			checkEntityNullValue(periodRule, attrArray);*/

			baseHibernateService.merge(periodRule);

			if (isSave) {
				setMessage(periodRule.getId() + ":" + SAVE_SUCCESS);
			} else {
				setMessage(periodRule.getId() + ":" + UPDATE_SUCCESS);
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

	public PeriodRule getPeriodRule() {
		return periodRule;
	}

	public void setPeriodRule(PeriodRule periodRule) {
		this.periodRule = periodRule;
	}

}
