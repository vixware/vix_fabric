package com.vix.hr.job.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrRecruitactivitity;
import com.vix.hr.job.entity.HrRecruitactivitityDetails;
import com.vix.hr.job.service.IRecruitactivitityService;

@Transactional
@Component("recruitactivititydomain")
public class RecruitactivitityDomain {

	@Autowired
	private IRecruitactivitityService iRecruitactivitityService;

	/**
	 * 获取招聘活动列表数据
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iRecruitactivitityService.findPagerByHqlConditions(pager, HrRecruitactivitity.class, params);
		return p;
	}

	/**
	 * 获取招聘活动搜索列表数据
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iRecruitactivitityService.findPagerByOrHqlConditions(pager, HrRecruitactivitity.class, params);
		return p;
	}

	/**
	 * 根据Id获取招聘活动明细数据
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HrRecruitactivitityDetails findHrRecruitactivitityDetails(String id) throws Exception {
		return iRecruitactivitityService.findEntityById(HrRecruitactivitityDetails.class, id);
	}

	/**
	 * 根据对象获取招聘活动明细数据
	 * 
	 * @param hrRecruitactivitityDetails
	 * @return
	 * @throws Exception
	 */
	public HrRecruitactivitityDetails merge(HrRecruitactivitityDetails hrRecruitactivitityDetails) throws Exception {
		return iRecruitactivitityService.merge(hrRecruitactivitityDetails);
	}

	/**
	 * 根据id获取招聘计划明细数据
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HrRecruitactivitityDetails findHrRecruitactivitityDetailsById(String id) throws Exception {
		return iRecruitactivitityService.findEntityById(HrRecruitactivitityDetails.class, id);
	}

	/**
	 * 根据实体对象删除招聘活动明细
	 * 
	 * @param hrRecruitactivitityDetails
	 * @throws Exception
	 */
	public void deleteHrRecruitactivitityDetailsEntity(HrRecruitactivitityDetails hrRecruitactivitityDetails) throws Exception {
		iRecruitactivitityService.deleteByEntity(hrRecruitactivitityDetails);
	}

	public HrRecruitactivitity findEntityById(String id) throws Exception {
		return iRecruitactivitityService.findEntityById(HrRecruitactivitity.class, id);
	}

	public HrRecruitactivitity merge(HrRecruitactivitity hrRecruitactivitity) throws Exception {
		return iRecruitactivitityService.merge(hrRecruitactivitity);
	}

	public void deleteByEntity(HrRecruitactivitity hrRecruitactivitity) throws Exception {
		iRecruitactivitityService.deleteByEntity(hrRecruitactivitity);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		iRecruitactivitityService.batchDelete(HrRecruitactivitity.class, ids);
	}

	/**
	 * 招聘活动列表页索引对象
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HrRecruitactivitity> findRecruitactivitityIndex() throws Exception {
		return iRecruitactivitityService.findAllByConditions(HrRecruitactivitity.class, null);
	}
}
