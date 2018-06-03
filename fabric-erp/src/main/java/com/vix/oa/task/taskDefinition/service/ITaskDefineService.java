package com.vix.oa.task.taskDefinition.service;
import java.util.List;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.oa.task.taskDefinition.entity.VixTask;

/**
 * 
 * @ClassName: ITaskDefineService
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-2-25 下午3:08:26
 */
public interface ITaskDefineService extends IBaseHibernateService {
	//日清任务
	public List<VixTask> findPublishedTaskDefinition()throws Exception;
	//生效任务
	public List<VixTask> findTaskDefinition()throws Exception;
}
