/**
 * 
 */
package com.vix.system.processdefinition.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.org.entity.Organization;
import com.vix.core.web.Pager;
import com.vix.system.billTypeManagement.entity.BillsCategory;
import com.vix.system.billTypeManagement.entity.BillsType;
import com.vix.system.processdefinition.entity.Procedure;
import com.vix.system.processdefinition.service.IProcessDefinitionService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("processDefinitionDomain")
@Transactional
public class ProcessDefinitionDomain extends BaseDomain{

	@Autowired
	private IProcessDefinitionService processDefinitionService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = processDefinitionService.findBillsTypePager(pager, params);
		return p;
	}

	public Procedure findEntityById(String id) throws Exception {
		return processDefinitionService.findEntityById(Procedure.class, id);
	}

	public BillsCategory findBillsCategoryById(String id) throws Exception {
		return processDefinitionService.findEntityById(BillsCategory.class, id);
	}

	public Organization findOrganizationById(String id) throws Exception {
		return processDefinitionService.findEntityById(Organization.class, id);
	}

	public void saveOrUpdateProcedure(Procedure procedure) throws Exception {
		processDefinitionService.saveOrUpdate(procedure);
	}

	public void saveOrUpdateBillsCategory(BillsCategory billsCategory) throws Exception {
		processDefinitionService.saveOrUpdate(billsCategory);
	}

	public void deleteByEntity(BillsType billsType) throws Exception {
		processDefinitionService.deleteByEntity(billsType);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		processDefinitionService.batchDelete(BillsType.class, ids);
	}

	/** 索引对象列表 */
	public List<Procedure> findProcedureList() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		return processDefinitionService.findAllByConditions(Procedure.class, params);
	}
}
