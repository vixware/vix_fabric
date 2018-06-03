
package com.vix.oa.personaloffice.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.drp.channel.entity.ChannelDistributor;
/**
 * 统计查询数据  SeekData
 * **/
public interface ISeekDataService extends IBaseHibernateService {
	/**  查讯返回一个值  **/  
	public Map<String, Object> seekReturnOneDataSoulLiA(Map<String, Object> hsMap) throws Exception;
	/**  根据传入参数的不同，控制查询门店的id不同 ；用于拼接sql ；  ***/
	public String seekDifferentIDsAbcd(String setChannelDistributorID) throws Exception;
	/**  根据用户账号及其角色     查询符合条件符合权限的的 门店集合  ***/
	public List<ChannelDistributor> seekChannelDistributorList(Map<String, Object> hsMap) throws Exception;
}
