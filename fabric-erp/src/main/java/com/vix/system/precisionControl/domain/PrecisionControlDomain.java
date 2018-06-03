/**
 * 
 */
package com.vix.system.precisionControl.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.org.entity.Organization;
import com.vix.core.web.Pager;
import com.vix.system.precisionControl.entity.PrecisionControl;
import com.vix.system.precisionControl.service.IPrecisionControlService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("precisionControlDomain")
@Transactional
public class PrecisionControlDomain extends BaseDomain{

	@Autowired
	private IPrecisionControlService precisionControlService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = precisionControlService.findBillsTypePager(pager, params);
		return p;
	}

	public PrecisionControl findPrecisionControlById(String id) throws Exception {
		return precisionControlService.findEntityById(PrecisionControl.class, id);
	}

	public Organization findOrganizationById(String id) throws Exception {
		return precisionControlService.findEntityById(Organization.class, id);
	}

	public PrecisionControl findPrecisionControl(String organizationId) throws Exception {
		return precisionControlService.findEntityByAttribute(PrecisionControl.class, "organization.id", organizationId);
	}

	public void saveOrUpdatePrecisionControl(PrecisionControl precisionControl) throws Exception {
		precisionControlService.saveOrUpdate(precisionControl);
	}

	/** 索引对象列表 */
	public List<PrecisionControl> findPrecisionControlIndex() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		return precisionControlService.findAllByConditions(PrecisionControl.class, params);
	}

	public List<Organization> findOrganization(String attributeName, String parentId, Map<String, Object> params) throws Exception {
		return precisionControlService.findAllSubEntity(Organization.class, attributeName, parentId, params);
	}
}
