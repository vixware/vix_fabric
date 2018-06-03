package com.vix.hr.hrmgr.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.hrmgr.entity.PersonnelContract;
import com.vix.hr.hrmgr.service.IPersonnelContractService;
import com.vix.hr.job.entity.HrAttachments;

@Transactional
@Component("personnelcontractdomain")
public class PersonnelContractDomain {
	@Autowired
	private IPersonnelContractService iPersonnelContractService;
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iPersonnelContractService.findPagerByHqlConditions(pager, PersonnelContract.class, params);
		return p;
	}
	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iPersonnelContractService.findPagerByOrHqlConditions(pager, PersonnelContract.class, params);
		return p;
	}
	public PersonnelContract findEntityById(String id) throws Exception {
		return iPersonnelContractService.findEntityById(PersonnelContract.class, id);
	}

	public PersonnelContract merge(PersonnelContract personnelContract) throws Exception {
		return iPersonnelContractService.merge(personnelContract);
	}

	public void deleteByEntity(PersonnelContract personnelContract) throws Exception {
		iPersonnelContractService.deleteByEntity(personnelContract);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iPersonnelContractService.batchDelete(PersonnelContract.class, ids);
	}

	/** 索引对象列表 */
	public List<PersonnelContract> findPersonnelContractIndex() throws Exception {
		return iPersonnelContractService.findAllByConditions(PersonnelContract.class, null);
	}
	/** 附件 */
	public HrAttachments merge(HrAttachments attachments) throws Exception {
		return iPersonnelContractService.merge(attachments);
	}

	public HrAttachments findHrAttachmentsEntityById(String id) throws Exception {
		return iPersonnelContractService.findEntityById(HrAttachments.class, id);
	}

	public void deleteHrAttachmentsEntity(HrAttachments attachments) throws Exception {
		iPersonnelContractService.deleteByEntity(attachments);
	}
}
