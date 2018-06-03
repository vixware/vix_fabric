package com.vix.common.org.domain;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.entity.LanguageAbility;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;

@Transactional
@Component("languageabilitydomain")
public class LanguageAbilityDomain {

	@Autowired
	private IEmployeeHrService iEmployeeHrService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iEmployeeHrService.findPagerByHqlConditions(pager, Employee.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iEmployeeHrService.findPagerByOrHqlConditions(pager, Employee.class, params);
		return p;
	}

	/**
	 * 根据id获取语言能力明细数据
	 */
	public LanguageAbility findLanguageAbilityByIdId(String id) throws Exception {
		return iEmployeeHrService.findEntityById(LanguageAbility.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return iEmployeeHrService.findEntityById(Employee.class, id);
	}

	// 语言能力明细
	public LanguageAbility merge(LanguageAbility languageAbility) throws Exception {
		return iEmployeeHrService.merge(languageAbility);
	}

	// 删除语言能力明细
	public void deleteLanguageAbilityEntity(LanguageAbility languageAbility) throws Exception {
		iEmployeeHrService.deleteByEntity(languageAbility);
	}
}
