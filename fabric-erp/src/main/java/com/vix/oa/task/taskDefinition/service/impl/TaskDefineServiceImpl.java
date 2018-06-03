package com.vix.oa.task.taskDefinition.service.impl;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vix.common.org.dao.IBusinessOrgDao;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.oa.task.taskDefinition.entity.VixTask;
import com.vix.oa.task.taskDefinition.service.ITaskDefineService;

/**
 * 
 * @ClassName: TaskDefineServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-2-25 下午3:08:48
 */
@Service("taskDefineService")
public class TaskDefineServiceImpl extends BaseHibernateServiceImpl implements ITaskDefineService {

	private static final long serialVersionUID = 1L;
	@Resource(name="businessOrgDao")
	private IBusinessOrgDao businessOrgDao;

	/* (non-Javadoc)
	 * @see com.vix.oa.task.taskDefinition.service.ITaskDefineService#findPublishedTaskDefinition()
	 */
	@Override
	public List<VixTask> findPublishedTaskDefinition() throws Exception {
		List<VixTask> all = businessOrgDao.findAllByHql2("select * from OA_TASK_DEFINITION left join OA_TASKTYPE on OA_TASK_DEFINITION.ID=OA_TASKTYPE.ID where OA_TASKTYPE.code='3'", new HashMap<String, Object>());
		return all;
	}

	/* (non-Javadoc)
	 * @see com.vix.oa.task.taskDefinition.service.ITaskDefineService#findTaskDefinition()
	 */
	@Override
	public List<VixTask> findTaskDefinition() throws Exception {
		List<VixTask> all = businessOrgDao.findAllByHql2("select * from OA_TASK_DEFINITION where  IS_PUBLISH ='0'", new HashMap<String, Object>());
		return all;
	}

}
