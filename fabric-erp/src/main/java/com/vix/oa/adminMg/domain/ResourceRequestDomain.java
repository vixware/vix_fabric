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
import com.vix.oa.adminMg.entity.ResourceRequest;

/**
 * @Description:资源申请管理
 * @author ivan
 * @date 2013-12-5
 */
@Component("resourceRequestDomain")
@Transactional
public class ResourceRequestDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ResourceRequest.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, ResourceRequest.class, params);
		return p;
	}

	public ResourceRequest findResourceRequestById(String id) throws Exception {
		return baseHibernateService.findEntityById(ResourceRequest.class, id);
	}

	public ResourceRequest merge(ResourceRequest resourceRequest) throws Exception {
		return baseHibernateService.merge(resourceRequest);
	}

	public void deleteByResourceRequest(ResourceRequest resourceRequest) throws Exception {
		baseHibernateService.deleteByEntity(resourceRequest);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ResourceRequest.class, ids);
	}

	/** 索引对象列表 */
	public List<ResourceRequest> findResourceRequestIndex() throws Exception {
		return baseHibernateService.findAllByConditions(ResourceRequest.class, null);
	}

}
