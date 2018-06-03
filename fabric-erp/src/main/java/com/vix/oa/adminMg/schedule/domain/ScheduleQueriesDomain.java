
package com.vix.oa.adminMg.schedule.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.scheduling.entity.Calendars;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.schedule.entity.PeriodicTransaction;
import com.vix.oa.adminMg.schedule.entity.ScheduleQueries;
import com.vix.oa.task.taskDefinition.entity.VixTask;
@Component("scheduleQueriesDomain")
@Transactional
public class ScheduleQueriesDomain extends BaseDomain{
	
	/** 日程安排索引对象列表 */
	public List<Calendars> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(Calendars.class, null);
	}
	
	/** 获取日程安排列表数据 */
	public Pager findPagerByCalendarsConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,Calendars.class, params);
		return p;
	}
	
	/** 获取日常事物搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, Calendars.class, params);
		return p;
	}
	

	/** 获取日常事物列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,Calendars.class, params);
		return p;
	}
	
	
	
	/** 获取周期事物搜索列表数据  */
	public Pager findPagerByOrHqlConditions1(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, PeriodicTransaction.class, params);
		return p;
	}
	
	/** 获取任务搜索列表数据  */
	public Pager findPagerByOrHqlConditions2(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, VixTask.class, params);
		return p;
	}
	
	/** 获取周期事物列表数据 */
	public Pager findPagerByHqlConditions1(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,PeriodicTransaction.class, params);
		return p;
	}
	
	/** 获取任务物列表数据 */
	public Pager findPagerByHqlConditions2(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,VixTask.class, params);
		return p;
	}

	/**
	 * 日常事物
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ScheduleQueries findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(ScheduleQueries.class, id);
	}
	
	/**
	 * 周期事物
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PeriodicTransaction findEntityById1(String id) throws Exception {
		return baseHibernateService.findEntityById(PeriodicTransaction.class, id);
	}
	
	/**
	 * 任务
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public VixTask findEntityById2(String id) throws Exception {
		return baseHibernateService.findEntityById(VixTask.class, id);
	}
	
	/**
	 * 日常事物
	 * @param scheduleQueries
	 * @return
	 * @throws Exception
	 */
	public ScheduleQueries merge(ScheduleQueries scheduleQueries) throws Exception {
		return baseHibernateService.merge(scheduleQueries);
	}
	
	/**
	 * 周期事物
	 * @param periodicTransaction
	 * @return
	 * @throws Exception
	 */
	public PeriodicTransaction merge(PeriodicTransaction periodicTransaction) throws Exception {
		return baseHibernateService.merge(periodicTransaction);
	}
	/**
	 * 任务
	 * @param taskDefinition
	 * @return
	 * @throws Exception
	 */
	public VixTask merge(VixTask taskDefinition) throws Exception {
		return baseHibernateService.merge(taskDefinition);
	}
}	
	