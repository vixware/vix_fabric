package com.vix.common.securityDra.service;

import java.util.List;

import com.vix.common.security.entity.DataResRowPredata;
import com.vix.common.security.entity.DataResRowSystemParams;
import com.vix.common.security.entity.Role;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

public interface IDataResRowSysParamService extends IBaseHibernateService {

	
	/**
	 * 保存更新配置
	 * @param sysParam
	 * @return
	 * @throws Exception
	 */
	DataResRowSystemParams saveOrUpdateParam(DataResRowSystemParams sysParam)throws Exception;
	
	/**
	 * 根据系统配置id 得到其所有角色列表
	 * @param id SysParams的 id
	 * @return
	 * @throws Exception
	 */
	List<Role> findSysParamRoleList(String id)throws Exception;
	
	/**
	 * 根据系统配置id 得到预处理数据列表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<DataResRowPredata> findSysParamPreDataList(String id)throws Exception;
	
	/**
	 * 为系统常量配置  设定角色分配信息
	 * @param sysParamId
	 * @param roleId
	 * @throws Exception
	 */
	void saveOrUpdateParamRole(String sysParamId,String roleId)throws Exception;
	
	/**
	 * 为系统常量配置 删除角色分配信息
	 * @param sysParamId
	 * @param roleId
	 * @throws Exception
	 */
	void deleteRoleForParam(String sysParamId,String roleId)throws Exception;
	
	/**
	 * 保存预处理数据
	 * @param predata
	 * @throws Exception
	 
	DataResRowPredata saveOrUpdateParamPredata(DataResRowPredata predata)throws Exception;
	*/
}
