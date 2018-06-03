package com.vix.common.org.dao;

import java.util.List;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.system.base.compOperation.vo.OrganizationTenantShopVO;

/**
 * @ClassName: IChannelDistributorShopDao
 * @Description: 企业门店 数据
 * @author wangmingchen
 * @date 2016年8月18日 下午4:13:22
 */
public interface IChannelDistributorShopDao extends IBaseHibernateDao {

	/**
	 * @Title: save4OrgShop
	 * @Description: OC承租户注册调用OC接口 进行门店数据同步
	 * @param @param tenantId
	 * @param @param companyInnerCode
	 * @param @param fromShopList
	 */
	public void save4OrgShop(String tenantId,String companyInnerCode, List<OrganizationTenantShopVO> fromShopList)throws Exception;
	
	
}
