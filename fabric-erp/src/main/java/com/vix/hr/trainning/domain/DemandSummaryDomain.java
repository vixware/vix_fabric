package com.vix.hr.trainning.domain;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.DemandApply;
import com.vix.hr.trainning.entity.DemandSummary;
import com.vix.hr.trainning.service.IDemandApplyService;

@Transactional
@Component("demandSummaryDomain")
public class DemandSummaryDomain {
	@Autowired
	private IDemandApplyService iDemandApplyService;

	/** 索引对象列表 */
	public List<DemandSummary> findDemandSummaryIndex() throws Exception {
		return iDemandApplyService.findAllByConditions(DemandSummary.class, null);
	}

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByHqlConditions(pager, DemandSummary.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByOrHqlConditions(pager, DemandApply.class, params);
		return p;
	}

	public DemandSummary findEntityById(String id) throws Exception {
		return iDemandApplyService.findEntityById(DemandSummary.class, id);
	}

	public DemandSummary merge(DemandSummary demandSummary) throws Exception {
		return iDemandApplyService.merge(demandSummary);
	}

	public void deleteByEntity(DemandSummary demandSummary) throws Exception {
		iDemandApplyService.deleteByEntity(demandSummary);
	}
	/*******************************************
	 * 培训需求申请
	 ********************************************************************************/

	/** 根据id获取培训申请明细数据 */
	public DemandApply findDemandApplyById(String id) throws Exception {
		return iDemandApplyService.findEntityById(DemandApply.class, id);
	}

	/** 培训申请明细 */
	public DemandApply merge(DemandApply demandApply) throws Exception {
		return iDemandApplyService.merge(demandApply);
	}

	/** 根据对象删除培训申请 */
	public void deleteDemandApplyEntity(DemandApply demandApply) throws Exception {
		iDemandApplyService.deleteByEntity(demandApply);
	}

	/** 获取培训申请列表数据 */
	public Pager findPagerByHqlDemandApply(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iDemandApplyService.findPagerByHqlConditions(pager, DemandApply.class, params);
		return p;
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iDemandApplyService.batchDelete(DemandApply.class, ids);
	}

}
