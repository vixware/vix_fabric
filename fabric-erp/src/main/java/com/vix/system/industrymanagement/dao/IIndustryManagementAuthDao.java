package com.vix.system.industrymanagement.dao;

import java.util.List;
import java.util.Set;

import com.vix.common.orginialMeta.entity.OrginialAuthority;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IIndustryManagementAuthDao extends IBaseHibernateDao {

	/**
	 * 查询所有的菜单  根据类型，并且需要带选中信息
	 * @Title: findAllAuthorityMWithIndustryMgtModule
	 * @Description: TODO
	 * @param @param industryMgtModuleId
	 * @param @param bizType
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Set<Authority>    返回类型
	 * @throws
	 */
	public Set<OrginialAuthority> findAllOrginialAuthorityMWithIndustryMgtModule(String industryMgtModuleId,String bizType)throws Exception;

	public List<OrginialAuthority> findSubOrginialAuthorityFByIndustryMgtModule(String industryMgtModuleId,String orginialAuthCode,String bizType)throws Exception;
}
