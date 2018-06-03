package com.vix.hr.job.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrPostSys;
import com.vix.hr.job.service.IPostSysService;

@Transactional
@Component("postsysdomain")
public class PostSysDomain {

	@Autowired
	private IPostSysService iPostSysService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iPostSysService.findPagerByHqlConditions(pager, HrPostSys.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iPostSysService.findPagerByOrHqlConditions(pager, HrPostSys.class, params);
		return p;
	}

	public HrPostSys findEntityById(String id) throws Exception {
		return iPostSysService.findEntityById(HrPostSys.class, id);
	}

	public HrPostSys merge(HrPostSys hrPostSys) throws Exception {
		return iPostSysService.merge(hrPostSys);
	}

	public void deleteByEntity(HrPostSys hrPostSys) throws Exception {
		iPostSysService.deleteByEntity(hrPostSys);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iPostSysService.batchDelete(HrPostSys.class, ids);
	}

	/** 索引对象列表 */
	public List<HrPostSys> findpostsysIndex() throws Exception {
		return iPostSysService.findAllByConditions(HrPostSys.class, null);
	}

}
