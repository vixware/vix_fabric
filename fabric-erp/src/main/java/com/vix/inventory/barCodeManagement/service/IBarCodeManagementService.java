/**
 * 
 */
package com.vix.inventory.barCodeManagement.service;

import java.util.List;

import com.vix.common.org.vo.OrgUnit;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface IBarCodeManagementService extends IBaseHibernateService {
	List<OrgUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception;
}
