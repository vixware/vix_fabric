/**
 * 
 */
package com.vix.common.cache;

import java.util.List;

import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.entity.UserAccount;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * 提供应用层cache值获取接口
 * 
 * @author zhanghaibing
 * 
 * @date 2013-7-1
 */
public interface IUseCache {
	/**
	 * 获取系统全局变量
	 * 
	 * @param key
	 * @return String value值
	 * @throws Exception
	 */
	public String getVixGlobalValue(String key) throws Exception;

	/**
	 * 获取各模块配置项变量
	 * 
	 * @param key
	 * @return String value值
	 * @throws Exception
	 */
	public String getMdlPurchase(String key) throws Exception;

	/**
	 * 获取组织架构
	 * 
	 * @param key
	 * @return List<Object>对象集合
	 * @throws Exception
	 */
	public List<Object> getVixOrg(String key) throws Exception;

	/**
	 * 批量获取物料信息
	 * 
	 * @param key
	 * @return 对象集合
	 * @throws Exception
	 */
	public List<Object> getVixItems(String key) throws Exception;

	/**
	 * 获取单个物料信息
	 * 
	 * @param key
	 * @return Object 单个对象
	 * @throws Exception
	 */
	public Object getVixItem(String key) throws Exception;

	/**
	 * 获取权限信息
	 * 
	 * @param key
	 * @return List<String>字符串集合
	 * @throws Exception
	 */
	public List<String> getVixGlobalRoleValue(String key) throws Exception;

	/**
	 * 获取登录用户账号信息
	 * 
	 * @throws Exception
	 */
	public UserAccount getUserAccount() throws Exception;

	/**
	 * 获取登录用户账号所属员工信息
	 * 
	 * @throws Exception
	 */
	public Employee getEmployee() throws Exception;

	/**
	 * 获取驻户ID
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTenantId() throws Exception;

	/**
	 * 获取公司信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public Organization getOrganization() throws Exception;

	/**
	 * 获取部门信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public OrganizationUnit getOrganizationUnit() throws Exception;

	/**
	 * 获取国家编码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getCountryCode() throws Exception;

	/**
	 * 获取语言编码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getLangCode() throws Exception;

	/**
	 * 获取供应商信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public Supplier getSupplier() throws Exception;

	/**
	 * 获取物料信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Item> getItem() throws Exception;

}
