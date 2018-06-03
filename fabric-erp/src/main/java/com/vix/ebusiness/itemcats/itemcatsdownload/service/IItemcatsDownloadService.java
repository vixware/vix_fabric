/**
 * 
 */
package com.vix.ebusiness.itemcats.itemcatsdownload.service;

import java.util.List;

import com.vix.common.org.vo.OrgUnit;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface IItemcatsDownloadService extends IBaseHibernateService {
	/**
	 * 
	 * @param treeType
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<OrgUnit> findOrgAndUnitTreeList(String treeType, Long id) throws Exception;

}
