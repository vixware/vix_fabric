package com.vix.hr.job.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.job.entity.WelfareArchives;
import com.vix.hr.job.service.IWelfareArchivesService;

@Transactional
@Component("welfarearchivesdomain")
public class WelfareArchivesDomain {

	@Autowired
	private IWelfareArchivesService iWelfareArchivesService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iWelfareArchivesService.findPagerByHqlConditions(pager, WelfareArchives.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iWelfareArchivesService.findPagerByOrHqlConditions(pager, WelfareArchives.class, params);
		return p;
	}

	public WelfareArchives findEntityById(String id) throws Exception {
		return iWelfareArchivesService.findEntityById(WelfareArchives.class, id);
	}

	public WelfareArchives merge(WelfareArchives welfareArchives) throws Exception {
		return iWelfareArchivesService.merge(welfareArchives);
	}

	public void deleteByEntity(WelfareArchives welfareArchives) throws Exception {
		iWelfareArchivesService.deleteByEntity(welfareArchives);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iWelfareArchivesService.batchDelete(WelfareArchives.class, ids);
	}

	/** 索引对象列表 */
	public List<WelfareArchives> findWelfareArchivesIndex() throws Exception {
		return iWelfareArchivesService.findAllByConditions(WelfareArchives.class, null);
	}

}
