package com.vix.oa.personaloffice.domain;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.entity.LogComment;
import com.vix.oa.personaloffice.entity.WorkLog;

/**
 * 
 * @ClassName: WorkLogDomain
 * @Description: 工作日志 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-4-17 下午3:29:33
 */
@Component("workLogDomain")
@Transactional
public class WorkLogDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, WorkLog.class, params);
		return p;
	}
	
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, WorkLog.class, params);
		return p;
	}
	
	/** 索引对象列表 */
	public List<WorkLog> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(WorkLog.class, null);
	}
	/** 索引对象列表 */
	public List<LogComment> findSalesOrderIndex1() throws Exception {
		return baseHibernateService.findAllByConditions(LogComment.class, null);
	}
	
	public WorkLog findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(WorkLog.class, id);
	}
	
	public LogComment findEntityById1(String id) throws Exception {
		return baseHibernateService.findEntityById(LogComment.class, id);
	}
	
	public void deleteByEntity(WorkLog workLog) throws Exception {
		baseHibernateService.deleteByEntity(workLog);
	}
	
	public WorkLog merge(WorkLog workLog) throws Exception {
		return baseHibernateService.merge(workLog);
	}
	public LogComment merge(LogComment logComment) throws Exception {
		return baseHibernateService.merge(logComment);
	}
	
	
}
