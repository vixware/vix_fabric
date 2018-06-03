package com.vix.oa.adminMg.vote.domain;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.vote.entity.VotingManagement;

/**
 * 
 * @ClassName: VotingManagementDomain
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-2-25 下午3:41:22
 */
@Component("votingManagementDomain")
@Transactional
public class VotingManagementDomain extends BaseDomain{

	
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,VotingManagement.class, params);
		return p;
	}
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, VotingManagement.class, params);
		return p;
	}
	
	/** 索引对象列表 */
	public List<VotingManagement> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(VotingManagement.class, null);
	}
	
	public VotingManagement findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(VotingManagement.class, id);
	}
	
	public void deleteByEntity(VotingManagement votingManagement) throws Exception {
		baseHibernateService.deleteByEntity(votingManagement);
	}
	
	public VotingManagement merge(VotingManagement votingManagement) throws Exception {
		return baseHibernateService.merge(votingManagement);
	}

}
