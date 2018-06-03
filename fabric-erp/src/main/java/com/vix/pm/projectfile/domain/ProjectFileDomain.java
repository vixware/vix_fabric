package com.vix.pm.projectfile.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.pm.projectfile.entity.ProjectFile;

@Component("projectFileDomain")
@Transactional
public class ProjectFileDomain extends BaseDomain {

	/** 索引对象列表 */
	public List<ProjectFile> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(ProjectFile.class, null);
	}

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ProjectFile.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, ProjectFile.class, params);
		return p;
	}

	public ProjectFile findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(ProjectFile.class, id);
	}

	public ProjectFile merge(ProjectFile checkListtemplate) throws Exception {
		return baseHibernateService.merge(checkListtemplate);
	}

}