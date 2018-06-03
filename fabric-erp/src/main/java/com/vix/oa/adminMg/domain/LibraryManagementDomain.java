/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.oa.adminMg.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.entity.LibraryManagement;

/**
 * @Description:图书管理
 * @author ivan
 * @date 2013-12-5
 */
@Component("libraryManagementDomain")
@Transactional
public class LibraryManagementDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, LibraryManagement.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, LibraryManagement.class, params);
		return p;
	}

	public LibraryManagement findLibraryManagementById(String id) throws Exception {
		return baseHibernateService.findEntityById(LibraryManagement.class, id);
	}

	public LibraryManagement merge(LibraryManagement libraryManagement) throws Exception {
		return baseHibernateService.merge(libraryManagement);
	}

	public void deleteByLibraryManagement(LibraryManagement libraryManagement) throws Exception {
		baseHibernateService.deleteByEntity(libraryManagement);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(LibraryManagement.class, ids);
	}

	/** 索引对象列表 */
	public List<LibraryManagement> findLibraryManagementIndex() throws Exception {
		return baseHibernateService.findAllByConditions(LibraryManagement.class, null);
	}

}
