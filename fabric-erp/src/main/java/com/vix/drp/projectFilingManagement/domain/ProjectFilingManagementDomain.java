package com.vix.drp.projectFilingManagement.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.projectFilingManagement.entity.ProjectFilingManagement;

@Component("projectFilingManagementDomain")
@Transactional
public class ProjectFilingManagementDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ProjectFilingManagement.class, params);
		return p;
	}

	public ProjectFilingManagement findProjectFilingManagementById(String id) throws Exception {
		return baseHibernateService.findEntityById(ProjectFilingManagement.class, id);
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	public ProjectFilingManagement saveOrUpdateProjectFilingManagement(ProjectFilingManagement projectFilingManagement) throws Exception {
		return baseHibernateService.merge(projectFilingManagement);
	}

	/** 索引对象列表 */
	public List<ProjectFilingManagement> findProjectFilingManagementList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(ProjectFilingManagement.class, params);
	}

	public void deleteByEntity(ProjectFilingManagement projectFilingManagement) throws Exception {
		baseHibernateService.deleteByEntity(projectFilingManagement);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ProjectFilingManagement.class, ids);
	}
}
