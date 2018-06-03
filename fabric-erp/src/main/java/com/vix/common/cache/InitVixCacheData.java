package com.vix.common.cache;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEmployee;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 初始化系统缓存数据
 * 
 * @author zhanghaibing
 * 
 * @date 2013-6-23
 */
@Controller("initVixCacheData")
@Scope("prototype")
public class InitVixCacheData {
	@Resource
	private CacheManager cacheManager;
	
	@Resource
	private IUseCache useCache;

	/**
	 * 将同一个驻户的所有数据存放到以tenantId 为key的map中
	 * 
	 * @param cache
	 * @param tenantId
	 * @throws Exception
	 */
	private void putTenantIdData(Cache cache, String tenantId) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		/**
		 * 当前登录员工信息
		 */
		BaseEmployee employee = SecurityUtil.getCurrentRealUser();
		dataMap.put("employee", employee);
		/**
		 * 用户账号信息
		 */
		UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
		dataMap.put("userAccount", userAccount);
		/**
		 * 当前登陆用户的组织/部门
		 */
		OrganizationUnit organizationUnit = useCache.getOrganizationUnit();
		dataMap.put("organizationUnit", organizationUnit);

		/**
		 * 物料信息
		 */
		/*
		 * List<Item> itemList = cacheService.findAllByEntityClass(Item.class);
		 * dataMap.put("ITEM", itemList);
		 */

		cache.put(new Element(tenantId, dataMap));
	}

	@Cacheable("baseCache")
	public void initData(String tenantId) throws Exception {
		Cache baseCache = cacheManager.getCache("baseCache");// 得到cache
		Cache vixCache = cacheManager.getCache("vixCache");// 得到cache
		putTenantIdData(baseCache, tenantId);
		putTenantIdData(vixCache, tenantId);
	}
}
