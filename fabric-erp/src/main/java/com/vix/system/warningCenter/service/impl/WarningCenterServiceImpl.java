/**
 * 
 */
package com.vix.system.warningCenter.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.vix.common.org.vo.OrgUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.system.warningCenter.service.IWarningCenterService;
import com.vix.system.warningSource.entity.WarningType;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("warningCenterService")
public class WarningCenterServiceImpl extends BaseHibernateServiceImpl implements IWarningCenterService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8910089932784964013L;

	@Override
	public List<OrgUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception {
		List<OrgUnit> orgUnitList = new LinkedList<OrgUnit>();
		if (treeType.equals("M")) {
			List<WarningType> warningTypeList = findWarningTypeList(id);
			orgUnitList = makeTree(warningTypeList);
		}
		return orgUnitList;
	}

	public List<WarningType> findWarningTypeList(String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(id) || !id.equals("0")){
			params.put("moduleCategory.id," + SearchCondition.IS, "NULL");
		} else {
			params.put("moduleCategory.id," + SearchCondition.EQUAL, id);
		}
		List<WarningType> warningTypeList = this.findAllByConditions(WarningType.class, params);
		return warningTypeList;
	}

	private List<OrgUnit> makeTree(List<WarningType> warningTypeList) {
		List<OrgUnit> orgUnitList = new LinkedList<OrgUnit>();

		if (warningTypeList != null) {
			for (WarningType warningType : warningTypeList) {
				OrgUnit orgUnit = new OrgUnit(warningType.getId(), "W", warningType.getTypeName());
				orgUnitList.add(orgUnit);
			}
		}
		return orgUnitList;
	}
}
