/**
 * 
 */
package com.vix.inventory.batch.service;

import java.util.List;

import com.vix.common.org.vo.OrgUnit;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface IBatchService extends IBaseHibernateService {
	List<OrgUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception;
}
