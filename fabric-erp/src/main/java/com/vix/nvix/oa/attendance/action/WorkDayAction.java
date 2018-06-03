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
import com.vix.nvix.oa.attendance.entity.WorkDayRule;

/**
 * @author Bluesnow 2016年7月14日
 */
@Controller
@Scope("prototype")
public class WorkDayAction extends BaseAction {

	private static final long serialVersionUID = 1L;


	private String id;
	private WorkDayRule workDay;

	@Override
	public String goList() {
		return GO_LIST;
	}

	@SuppressWarnings("unchecked")
	public void getWorkDaysJson() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();

			String name = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}

			baseHibernateService.findPagerByHqlConditions(pager,WorkDayRule.class, params);

			if (pager.getResultList().size() < pager.getPageSize()) {
				int count = pager.getPageSize() - pager.getResultList().size();
				for (int i = 0; i < count; i++) {
					pager.getResultList().add(new WorkDayRule());
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
				workDay = baseHibernateService.findEntityById(WorkDayRule.class, id);
			}else {
				workDay = new WorkDayRule();
				workDay.setCreator(SecurityUtil.getCurrentUserName());
				workDay.setCreateTime(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	public String saveOrUpdate(){
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(workDay.getId())) {
				isSave = false;
				workDay.setCreateTime(new Date());
				workDay.setIsTemp("0");
			} else {
				loadCommonData(workDay);
				workDay.setUpdateTime(new Date());
				workDay.setIsTemp("0");
			}
			loadCommonData(workDay);
			/** 检查空值对象 */
			String[] attrArray = {"workShiftRule","basicRule","org"};
			checkEntityNullValue(workDay, attrArray);

			baseHibernateService.merge(workDay);

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

	public WorkDayRule getWorkDay() {
		return workDay;
	}

	public void setWorkDay(WorkDayRule workDay) {
		this.workDay = workDay;
	}

}
