/**
 * 
 */
package com.vix.ebusiness.item.itemdownload.service;

import java.util.List;
import java.util.Map;

import com.vix.common.org.vo.OrgUnit;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface IItemDownLoadService extends IBaseHibernateService {
	/**
	 * 
	 * @param treeType
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<OrgUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception;

	public <T> T findItemByHql(String hql, Map<String, Object> params) throws Exception;

}
