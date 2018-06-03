package com.vix.hr.job.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrRecruitnoficaition;
import com.vix.hr.job.service.IRecruitnoficaitionService;

@Transactional
@Component("recruitnoficaitiondomain")
public class RecruitnoficaitionDomain {

	@Autowired
	private IRecruitnoficaitionService iRecruitnoficaitionService;

	/**
	 * 获取招聘征集列表数据
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iRecruitnoficaitionService.findPagerByHqlConditions(pager, HrRecruitnoficaition.class, params);
		return p;
	}

	/** 获取招聘征集列表页搜索数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iRecruitnoficaitionService.findPagerByOrHqlConditions(pager, HrRecruitnoficaition.class, params);
		return p;
	}

	/**
	 * 根据Id获取招聘征集信息数据
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HrRecruitnoficaition findEntityById(String id) throws Exception {
		return iRecruitnoficaitionService.findEntityById(HrRecruitnoficaition.class, id);
	}
	/**
	 * 根据实体对象获取招聘征集信息
	 * 
	 * @param hrRecruitnoficaition
	 * @return
	 * @throws Exception
	 */
	public HrRecruitnoficaition merge(HrRecruitnoficaition hrRecruitnoficaition) throws Exception {
		return iRecruitnoficaitionService.merge(hrRecruitnoficaition);
	}

	/**
	 * 根据实体对象删除招聘征集信息
	 * 
	 * @param HrRecruitnoficaition
	 * @throws Exception
	 */
	public void deleteByEntity(HrRecruitnoficaition HrRecruitnoficaition) throws Exception {
		iRecruitnoficaitionService.deleteByEntity(HrRecruitnoficaition);
	}

	/**
	 * 
	 * 
	 * @param ids
	 */
	public void deleteByIds(List<String> ids) throws Exception {
		iRecruitnoficaitionService.batchDelete(HrRecruitnoficaition.class, ids);
	}

	/**
	 * 招聘征集列表页索引对象
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HrRecruitnoficaition> findRecruitnoficaitionIndex() throws Exception {
		return iRecruitnoficaitionService.findAllByConditions(HrRecruitnoficaition.class, null);
	}
}
