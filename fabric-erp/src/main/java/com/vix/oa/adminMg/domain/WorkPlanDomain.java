
package com.vix.oa.adminMg.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.entity.Postil;
import com.vix.oa.adminMg.entity.ProgressLog;
import com.vix.oa.adminMg.entity.ProjectManagement;
import com.vix.oa.adminMg.entity.WorkPlanType;
@Component("workPlanDomain")
@Transactional
public class WorkPlanDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		   Pager p = baseHibernateService.findPagerByHqlConditions(pager, ProjectManagement.class, params);
		return p;
	}
	
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, ProjectManagement.class, params);
		return p;
	}
	
	public Pager findPagerByHqlConditions4(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,Postil.class, params);
		return p;
	}
	public Pager findPagerByHqlConditions3(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,ProgressLog.class, params);
		return p;
	}
	
	/** 索引对象列表 */
	public List<ProgressLog> findMoneyTypeIndex() throws Exception{
		return baseHibernateService.findAllByConditions(ProgressLog.class, null);
	}
	/** 索引对象列表 */
	public List<ProjectManagement> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(ProjectManagement.class, null);
	}
		
	public Pager findPagerByHqlConditions1(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,ProjectManagement.class, params);
		return p;
	}
	
	public Pager findPagerByHqlConditions2(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,WorkPlanType.class, params);
		return p;
	}
	
	public ProgressLog findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(ProgressLog.class, id);
	}
	
	
	public WorkPlanType findEntityById2(String id) throws Exception {
		return baseHibernateService.findEntityById(WorkPlanType.class, id);
	}
	
	public ProjectManagement findEntityById1(String id) throws Exception {
		return baseHibernateService.findEntityById(ProjectManagement.class, id);
	}
	public ProgressLog findEntityById3(String id) throws Exception {
		return baseHibernateService.findEntityById(ProgressLog.class, id);
	}
	public Postil findPostilOrPlan(String id) throws Exception {
		return baseHibernateService.findEntityById(Postil.class, id);
	}
	
	public void deleteByEntity(WorkPlanType workPlanType) throws Exception {
		baseHibernateService.deleteByEntity(workPlanType);
	}
	public void deleteByEntity(ProgressLog progressLog) throws Exception {
		baseHibernateService.deleteByEntity(progressLog);
	}
	public void deleteByEntity(ProjectManagement projectManagement) throws Exception {
		baseHibernateService.deleteByEntity(projectManagement);
	}
	
	public ProgressLog merge(ProgressLog workPlan) throws Exception {
		return baseHibernateService.merge(workPlan);
	}
	
	public Postil merge(Postil postil) throws Exception {
		return baseHibernateService.merge(postil);
	}
	
	public ProjectManagement merge(ProjectManagement projectManagement) throws Exception {
		return baseHibernateService.merge(projectManagement);
	}
	
	public WorkPlanType merge(WorkPlanType workPlanType) throws Exception {
		return baseHibernateService.merge(workPlanType);
	}
}	
	