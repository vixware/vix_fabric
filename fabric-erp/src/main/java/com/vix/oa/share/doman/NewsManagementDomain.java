package com.vix.oa.share.doman;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.share.entity.Comments;
import com.vix.oa.share.entity.Trends;

/**
 * 
 * @ClassName: NewsManagementDomain
 * @Description: 个人办公——新闻管理 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-3-31 下午4:25:58
 */
@Component("newsManagementDomain")
@Transactional
public class NewsManagementDomain extends BaseDomain{

	
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,Trends.class, params);
		return p;
	}
	
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, Trends.class, params);
		return p;
	}
	
	
	/** 获取列表数据 */
	public Pager findPagerByHqlComments(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,Comments.class, params);
		return p;
	}
	
	/** 索引对象列表 */
	public List<Trends> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(Trends.class, null);
	}
	
	public Trends findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(Trends.class, id);
	}
	public Comments findEntityById1(String id) throws Exception {
		return baseHibernateService.findEntityById(Comments.class, id);
	}
	
	public void deleteByEntity(Trends trends) throws Exception {
		baseHibernateService.deleteByEntity(trends);
	}
	
	public Trends merge(Trends trends) throws Exception {
		return baseHibernateService.merge(trends);
	}
	public Comments merge(Comments comments) throws Exception {
		return baseHibernateService.merge(comments);
	}
		
}
