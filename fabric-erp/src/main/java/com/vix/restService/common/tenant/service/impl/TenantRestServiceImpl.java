package com.vix.restService.common.tenant.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.dao.IChannelDistributorShopDao;
import com.vix.common.org.dao.IOrganizationDao;
import com.vix.common.org.entity.Organization;
import com.vix.common.security.dao.IUserAccountDao;
import com.vix.common.security.entity.UserAccount;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.restService.common.tenant.service.ITenantRestService;
import com.vix.system.base.compOperation.vo.CompanyOperationVO;
import com.vix.system.industrymanagement.entity.IndustryManagementModule;

/**
 * @ClassName: TenantRestServiceImpl
 * @Description: 承租户初始化的业务服务
 * @author wangmingchen
 * @date 2015年3月10日 下午5:18:15
 */
@Service("tenantRestService")
@Transactional
public class TenantRestServiceImpl extends BaseHibernateServiceImpl implements ITenantRestService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="userAccountDao")
	private IUserAccountDao userAccountDao;
	@Resource(name="organizationDao")
    private IOrganizationDao organizationDao;
	@Resource(name="channelDistributorShopDao")
    private IChannelDistributorShopDao channelDistributorShopDao;
	
	
	@Override
	public Organization save4InitTenant(CompanyOperationVO vo)throws Exception{
		//IndustryManagementModuleIds
		//IndustryManagementModuleNames
		List<String> industryManagementModuleIdsList = new LinkedList<String>();
		//String industryManagementModuleUUIDs = vo.getIndustryManagementModuleUUIDs();//行业模块id
		String industryManagementModuleIds = vo.getIndustryManagementModuleIds();//行业模块id
		logger.debug("VIX服务器初始化行业模块标识: {}.",industryManagementModuleIds);
		
		if(StringUtils.isNotEmpty(industryManagementModuleIds)){
			String[] industryManagementModuleIdsArray = StringUtils.split(industryManagementModuleIds, ",");
			for(String id : industryManagementModuleIdsArray){
				//IndustryManagementModule imm = organizationDao.findObjectByUUID(IndustryManagementModule.class, uuid);
				//System.out.println("UUID:"+uuid+";ID:"+imm.getId());
				IndustryManagementModule imm = organizationDao.findEntityById(IndustryManagementModule.class, id);
				industryManagementModuleIdsList.add(imm.getId());
			}
		}
		vo.setIndustryManagementModuleIds(StringUtils.join(industryManagementModuleIdsList,","));
		
		vo.setId(null);
		//根据uuid 设置本服务器的id值
		String requestOrgUUID = vo.getUuid();
		//UserAccount tempCompAdmin = vo.getCompSuperAdmin();
		String compAccount = vo.getCompAccount();
		String compPassword = vo.getCompPassword();
		//String requestAccountId = tempCompAdmin.getUuid();
		//vo.setUserAccountUUID(requestAccountUUID);
		
		Organization uuidOrg = organizationDao.findObjectByUUID(Organization.class, requestOrgUUID);
		UserAccount userAccount = userAccountDao.findUserByAccount(compAccount);// organizationDao.findObjectByUUID(UserAccount.class, requestAccountUUID);
		
		logger.debug("VIX服务器接收数据，承租户 UUID:{}, 管理员账号:compAccount：{},compPassword:{}.",new Object[]{requestOrgUUID,compAccount,compPassword});
		
		if(uuidOrg!=null){
			organizationDao.evict(uuidOrg);
			vo.setId(uuidOrg.getId());
		}
		if(userAccount!=null){
			organizationDao.evict(userAccount);
			vo.setUserAccountId(userAccount.getId());
		}else{
			vo.setUserAccountId(null);
		}
		
		vo.setUuid(requestOrgUUID);
		//设置账号密码
		vo.setAccount(compAccount);
		vo.setPassword(compPassword);
		
		vo.setIsResetAuth("Y");//重置权限
		Organization org = organizationDao.saveCompanyOperation(vo,true);
		
		//门店信息设置
		if(vo.getShopList()!=null && !vo.getShopList().isEmpty()){
			channelDistributorShopDao.save4OrgShop(org.getTenantId(), org.getCompanyInnerCode(), vo.getShopList());
		}
		
		return org;
	}
	
	/* 
	 * @see com.vix.restService.common.tenant.service.ITenantRestService#findOrgTreeByTenantId(java.lang.String)
	 */ 
	@Override
	public List<CompanyOperationVO> findOrgTreeByTenantId(String tenantId)throws Exception{
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("SELECT mo.ID,mo.Parent_id,mo.TENANTID,mo.CompanyInnerCode,mo.OrgName,mo.BriefName,mo.uuid  ");//.append(ename);
		sql.append("FROM MDM_ORG_ORGANIZATION mo ");
		sql.append("WHERE 1=1 ");
		if(StringUtils.isNotEmpty(tenantId)
				&& !tenantId.equals("0")){
			sql.append(" and mo.TENANTID=? ");
			params.add(tenantId);
		}
		sql.append(" ORDER BY mo.CompanyInnerCode ");
		
		List<CompanyOperationVO> compList = organizationDao.queryObjectListBySql(sql.toString(), CompanyOperationVO.class, params.toArray());
		if(compList == null){
			compList = new ArrayList<CompanyOperationVO>();
		}
		return compList;
	}
}
