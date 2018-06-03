package com.vix.hr.personnelmgr.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrAttachments;
import com.vix.hr.personnelmgr.entity.HrApprovalOpinion;
import com.vix.hr.personnelmgr.entity.HrBecomeRegular;
import com.vix.hr.personnelmgr.service.IBecomeRegularService;

@Transactional
@Component("becomeregulardomain")
public class BecomeRegularDomain {
	@Autowired
	private IBecomeRegularService iBecomeRegularService;
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iBecomeRegularService.findPagerByHqlConditions(pager, HrBecomeRegular.class, params);
		return p;
	}
	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iBecomeRegularService.findPagerByOrHqlConditions(pager, HrBecomeRegular.class, params);
		return p;
	}
	public HrBecomeRegular findEntityById(String id) throws Exception {
		return iBecomeRegularService.findEntityById(HrBecomeRegular.class, id);
	}
	public HrApprovalOpinion merge(HrApprovalOpinion hrApprovalOpinion) throws Exception {
		return iBecomeRegularService.merge(hrApprovalOpinion);
	}

	public HrBecomeRegular merge(HrBecomeRegular hrBecomeRegular) throws Exception {
		return iBecomeRegularService.merge(hrBecomeRegular);
	}

	public void deleteByEntity(HrBecomeRegular hrBecomeRegular) throws Exception {
		iBecomeRegularService.deleteByEntity(hrBecomeRegular);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iBecomeRegularService.batchDelete(HrBecomeRegular.class, ids);
	}

	/** 索引对象列表 */
	public List<HrBecomeRegular> findHrBecomeRegularIndex() throws Exception {
		return iBecomeRegularService.findAllByConditions(HrBecomeRegular.class, null);
	}
	/** 附件 */
	public HrAttachments merge(HrAttachments attachments) throws Exception {
		return iBecomeRegularService.merge(attachments);
	}

	public HrAttachments findHrAttachmentsEntityById(String id) throws Exception {
		return iBecomeRegularService.findEntityById(HrAttachments.class, id);
	}
	public void deleteHrAttachmentsEntity(HrAttachments attachments) throws Exception {
		iBecomeRegularService.deleteByEntity(attachments);
	}
}
