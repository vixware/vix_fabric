package com.vix.common.org.dao;

import java.util.List;

import com.vix.common.org.entity.BusinessOrg;
import com.vix.common.org.entity.BusinessOrgDetail;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

/**
 * 业务组织DAO
 * @author Administrator
 *
 */
public interface IBusinessOrgDao extends IBaseHibernateDao {

	/**
	 * 此接口不可使用，由于更改了业务组织
	 * 
	 * 
	 * 查询某项目的业务组织结构   为异步加载  每次只查询一个层级
	 * 
	 * nodeId==null    则查询一级节点
	 * nodeId = -1L,则不再以层级方式查询，而是返回该项目下的所有业务组织列表 
	 * 
	 * 
	 * @param pmCode 项目编码
	 * @param nodeId 节点id，第一次查询时传递null，其它情况请传递点击节点的id(也就是BusinessOrg的 id)
	 * @return 
	 * 
	 * 返回的BusinessOrg的bizOrgType取值有三种 并分别对应该业务组织对应的实体
	 * BizConstant.COMMON_ORG_O   部门			OrganizationUnit organizationUnit
	 * BizConstant.COMMON_ORG_R   角色			Role role
	 * BizConstant.COMMON_ORG_E   职员  			Employee employee
	 * 
	 * 举例，如果BusinessOrg的bizOrgType为BizConstant.COMMON_ORG_O，则上述三个实体中只能从organizationUnit属性取得该部门的相关属性值
	 * 
	 * @throws Exception
	 */
	@Deprecated
	public List<BusinessOrg> findBusinessOrgTreeList(String pmCode,String nodeId) throws Exception;
	
	/**
	 * 根据部门id得到所有的业务组织
	 * @param orgUnitId
	 * @return
	 * @throws Exception
	 */
	//Set<BusinessOrg> findBusinessOrgByOrgUnitId(Long orgUnitId)throws Exception;
	
	
	/**
	 * @Title: findBusinessOrgByDetailBoIdType
	 * @Description: 查询业务对象标识 的  所属业务组织
	 * @param @param businessOrgDetailBoId
	 * @param @param businessOrgDetailBoType
	 * @param @param businessViewId
	 */
	public List<BusinessOrg> findBusinessOrgByDetailBoIdType(String businessOrgDetailBoId,String businessOrgDetailBoType,
			String businessViewId) throws Exception;
	
	/**
	 * @Title: findSubBusinessOrgByCode
	 * @Description: 根据业务对象编码 查询所有子业务对象
	 * @param @param businessOrgCode 当前业务组织编码
	 * @param @param businessViewId 业务试图id
	 * @param @param isQueryAllSub True 查询所有的下级节点，  False 只查询下一级的子节点
	 * @param @return
	 */
	public List<BusinessOrg> findSubBusinessOrgByCode(String businessOrgCode,String businessViewId,Boolean isQueryAllSub) throws Exception;
	
	/**
	 * 
	 * @Title: findBusinessOrg4ReportLine
	 * @Description: 汇报关系接口
	 * @param @param businessViewId   业务视图id  必填
	 * @param @param tenantId 承租户标识 必填
	 * @param @param isAboveLevel 是否查询上级汇报关系，   false:则查询夏季
	 * @param @param isQueryAllLevel  是否查询所有，   false： 只查询上一级
	 * @param @param businessOrgDetailBoId  被查询的业务组织id（可能是职员 角色  组织）
	 * @param @return
	 * @param @throws Exception    
	 * @return List<BusinessOrgDetail>   
	 * @throws
	 */
	public List<BusinessOrg> findBusinessOrg4ReportLine(String businessViewId,String tenantId,
			Boolean isAboveLevel,Boolean isQueryAllLevel,
			String queryBusinessOrgDetailBoId,String queryBusinessOrgDetailBoIdType//String resultBusinessOrgDetailType
			) throws Exception;
	 
	 
	/**
	 * @Title: findBusinessOrgDetail4ReportLine
	 * @Description: 同findBusinessOrg4ReportLine，区别是返回的是业务组织的明细
	 * @param @param businessViewId
	 * @param @param tenantId
	 * @param @param isAboveLevel
	 * @param @param isQueryAllLevel
	 * @param @param businessOrgDetailBoId
	 * @param @param resultBusinessOrgDetailType  返回查询结果的类型，如果为空 则查询所有类型，类型标识同上 BizConstant.COMMON_ORG_C E R
	 */
	public List<BusinessOrgDetail> findBusinessOrgDetail4ReportLine(String businessViewId,String tenantId,
			Boolean isAboveLevel,Boolean isQueryAllLevel,
			String businessOrgDetailBoId,
			String resultBusinessOrgDetailType) throws Exception;
	
	
	/**
	 * @Title: findBusinessOrgDetailBo4ReportLine
	 * @Description: 汇报关系
	 * @param @param businessViewCode  业务视图编码，  如果为空，则查询默认承租户的上下级汇报关系
	 * @param @param tenantId 承租户标识 必填
	 * @param @param isAboveLevel 是否查询上级汇报关系，   false:则查询下级
	 * @param @param isQueryAllLevel  是否查询所有，   false： 只查询一级 ，  例如 ： 如果 isAboveLevel = true , isQueryAllLevel = true, 则 之查询上一级的汇报关系对象
	 * @param @param queryBusinessOrgDetailBoId  查询的 具体业务对象id，如职员id  部门id
	 * @param @param queryBusinessOrgDetailBoIdType  查询的businessOrgDetailBoId 的类型，如职员为    BizConstant.COMMON_ORG_E
	 * @param @param resultBusinessOrgDetailType  返回结果的类型  参数，如  职员为  BizConstant.COMMON_ORG_E
	 * @param @return
	 * @param @throws Exception    
	 * @return List<T>
	 * @throws
	 */
	public <T> List<T> findBusinessOrgDetailBo4ReportLine(String businessViewCode,String tenantId,
			Boolean isAboveLevel,Boolean isQueryAllLevel,
			String queryBusinessOrgDetailBoId,String queryBusinessOrgDetailBoIdType,
			String resultBusinessOrgDetailType) throws Exception;
}
