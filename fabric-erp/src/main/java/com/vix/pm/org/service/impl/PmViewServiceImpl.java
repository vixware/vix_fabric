package com.vix.pm.org.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.pm.org.dao.IPmViewDao;
import com.vix.pm.org.entity.PmOrgView;
import com.vix.pm.org.entity.PmView;
import com.vix.pm.org.hql.PmViewHqlProvider;
import com.vix.pm.org.service.IPmViewService;

@Service("pmViewService")
@Transactional
public class PmViewServiceImpl  extends BaseHibernateServiceImpl implements IPmViewService{

	@Resource(name="pmViewDao")
	private IPmViewDao pmViewDao;
	
	@Resource(name="pmViewHqlProvider")
	private PmViewHqlProvider pmViewHqlProvider;

	
	/**
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public Pager findBusinessViewPager(Pager pager, Map<String, Object> params) throws Exception{
		StringBuilder hql = pmViewHqlProvider.findBusinessViewList(params, pager);
		pager = pmViewDao.findPager2ByHql(pager, pmViewHqlProvider.entityAsName(), hql.toString(), params);
		return pager;
	}
	
	@Override
	public PmView saveOrUpdateBusinessView(PmView entityForm)throws Exception{
		PmView entity = null;
		if(entityForm!=null && StrUtils.isNotEmpty(entityForm.getId())){
			entity = pmViewDao.findEntityById(PmView.class, entityForm.getId());
		}
		if(entity == null){
			entity = new PmView();
		}
		
		Date now = new Date();
		//Long currentUserAccountId = SecurityUtil.getCurrentUserId();
		if(entity.getId()==null){
			entity.setCreateTime(now);
		}
		entity.setUpdateTime(now);
		
		entity.setName(entityForm.getName());
		entity.setBizViewType(entityForm.getBizViewType());
		entity.setIsMatrixManagement(entityForm.getIsMatrixManagement());
		entity.setPmCode(entityForm.getPmCode());
		entity.setMetaDataCode(entityForm.getMetaDataCode());
		
		entity.setBizViewCreateDate(entityForm.getBizViewCreateDate());
		entity.setStartTime(entityForm.getStartTime());
		entity.setEndTime(entityForm.getEndTime());
		
		pmViewDao.saveOrUpdate(entity);
		return entity;
	}
	
	@Override
	public List<PmOrgView> findOrgViewList(String id) throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("parentId", id);
		StringBuilder hql = pmViewHqlProvider.findBusinessOrgViewList(params);
		List<PmOrgView> bizOrgViewList =  pmViewDao.findAllByHql2(hql.toString(), params);
		return bizOrgViewList;
	}
}
