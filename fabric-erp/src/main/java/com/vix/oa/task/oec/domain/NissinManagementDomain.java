package com.vix.oa.task.oec.domain;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.task.taskDefinition.entity.VixTask;

/**
 * 
 * @ClassName: NissinManagementDomain
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-2-28 下午2:02:11
 */
@Component("nissinManagementDomain")
@Transactional
public class NissinManagementDomain extends BaseDomain{

	
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,VixTask.class, params);
		return p;
	}
	
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, VixTask.class, params);
		return p;
	}
	
	/** 索引对象列表 */
	public List<VixTask> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(VixTask.class, null);
	}
	
	public VixTask findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(VixTask.class, id);
	}
	
	public void deleteByEntity(VixTask taskDefinition) throws Exception {
		baseHibernateService.deleteByEntity(taskDefinition);
	}
	
	public VixTask merge(VixTask taskDefinition) throws Exception {
		return baseHibernateService.merge(taskDefinition);
	}

}
