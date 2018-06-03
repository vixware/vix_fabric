package com.vix.common.org.service;

import java.util.List;
import java.util.Map;

import com.vix.common.org.entity.OrgView;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.vo.OrgUnit;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.hr.position.entity.OrgPositionView;

public interface IOrganizationUnitService extends IBaseHibernateService{
    
	 /**
     * 查询树视图信息
     * @param id
     * @return
     */
    List<OrgView> findOrgViewList(String id) throws Exception;
    List<OrgPositionView> findOrgPositionViewList(String id) throws Exception;
    
    /**
     * 查询树  根据id 和  类型过滤
     * @param params
     * @return
     * @throws Exception
     */
    List<OrgView> findOrgViewList(String id,String unitType) throws Exception;
    
    /**
     * @Title: findOrgViewList4CmnSelect
     * @Description: 组织机构部门的选择
     * @param @param id
     * @param @param unitType
     * @param @return
     * @param @throws Exception    设定文件
     * @return List<OrgView>    返回类型
     * @throws
     */
    List<OrgView> findOrgViewList4CmnSelect(String id,String unitType) throws Exception;
    
    /**
     * 根节点为自己所在的公司
     * @param id
     * @return
     * @throws Exception
     */
    List<OrgView> findOrgView4OwnCompList(String id,String unitType) throws Exception;
    
    void saveOrUpdateOrgUnit(OrganizationUnit formUnit,String boIds) throws Exception;
	
	
    
	
   /**
    * @Title: findOrgAndUnitTreeList
    * @Description: 加载公司和组织机构数据
    * @param @param treeType 公司 C  部门 O
    * @param @param id 公司或者部门id
    * @param @return
    * @param @throws Exception    设定文件
    * @return List<OrgUnit>    返回类型
    * @throws
    */
    List<OrgUnit> findOrgAndUnitTreeList(String treeType,String id)throws Exception;
    
    /**
     * @Title: findSubOrganizationUnitList
     * @Description: 根据公司id加载部门信息
     * @param @param id
     * @param @return
     * @param @throws Exception    设定文件
     * @return List<OrganizationUnit>    返回类型
     * @throws
     */
    List<OrganizationUnit> findSubOrganizationUnitListByOrgId(String orgId) throws Exception;
    
    /**
     * @Title: findSubOrganizationUnitList
     * @Description: 根据部门id得到部门列表
     * @param @param orgUnitId
     * @param @return
     * @param @throws Exception    设定文件
     * @return List<OrganizationUnit>    返回类型
     * @throws
     */
    List<OrganizationUnit> findSubOrganizationUnitList(String orgUnitId) throws Exception;
    
    /**
     * 得到部门分页信息
     * @param pager
     * @param params
     * @return
     * @throws Exception
     */
    Pager findOrganizationUnitListByOrgId(Pager pager,Map<String,Object> params) throws Exception;
    
    /**
     * 根据id得到所有相关信息
     * @param id
     * @return
     * @throws Exception
     */
    OrganizationUnit findOrganizationUnitAll(String id)throws Exception;
    
    /**
     * 递归查询公司
     * @param oranizationUnitId
     * @return
     * @throws Exception
     */
    Organization getOrganizationByUnitId(String oranizationUnitId) throws Exception;
}
