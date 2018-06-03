package com.vix.hr.trainning.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.DemandNotice;
import com.vix.hr.trainning.service.IDemandNoticeService;

@Transactional
@Component("demandNoticeDomain")
public class DemandNoticeDomain {
	@Autowired
	private IDemandNoticeService iDemandNoticeService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandNoticeService.findPagerByHqlConditions(pager, DemandNotice.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandNoticeService.findPagerByOrHqlConditions(pager, DemandNotice.class, params);
		return p;
	}

	public DemandNotice findEntityById(String id) throws Exception {
		return iDemandNoticeService.findEntityById(DemandNotice.class, id);
	}

	public DemandNotice merge(DemandNotice demandNotice) throws Exception {
		return iDemandNoticeService.merge(demandNotice);
	}

	public void deleteByEntity(DemandNotice demandNotice) throws Exception {
		iDemandNoticeService.deleteByEntity(demandNotice);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iDemandNoticeService.batchDelete(DemandNotice.class, ids);
	}

	/** 索引对象列表 */
	public List<DemandNotice> findDemandNoticeIndex() throws Exception {
		return iDemandNoticeService.findAllByConditions(DemandNotice.class, null);
	}

}
