/**
 * 
 */
package com.vix.drp.drpwarehouse.service;

import java.util.List;

import com.vix.common.org.vo.OrgUnit;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface IDrpWarehouseService extends IBaseHibernateService {


	/**
	 * @Title: findOrgAndUnitTreeList
	 * @Description: 加载公司和组织机构数据
	 * @param @param treeType 公司 C 部门 O
	 * @param @param id 公司或者部门id
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<OrgUnit> 返回类型
	 * @throws
	 */
	List<OrgUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception;

}
