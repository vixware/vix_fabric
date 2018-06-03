package com.vix.drp.retailPriceSurvey.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.retailPriceSurvey.entity.RetailPriceSurvey;
import com.vix.drp.retailPriceSurvey.entity.RetailPriceSurveyDetail;

@Component("retailPriceSurveyDomain")
@Transactional
public class RetailPriceSurveyDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, RetailPriceSurvey.class, params);
		return p;
	}

	public RetailPriceSurvey findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(RetailPriceSurvey.class, id);
	}

	public RetailPriceSurveyDetail findRetailPriceSurveyDetailById(String id) throws Exception {
		return baseHibernateService.findEntityById(RetailPriceSurveyDetail.class, id);
	}

	public RetailPriceSurvey saveOrUpdateRetailPriceSurvey(RetailPriceSurvey retailPriceSurvey) throws Exception {
		return baseHibernateService.merge(retailPriceSurvey);
	}

	public RetailPriceSurveyDetail saveOrUpdateRetailPriceSurveyDetail(RetailPriceSurveyDetail retailPriceSurveyDetail) throws Exception {
		return baseHibernateService.merge(retailPriceSurveyDetail);
	}

	public void deleteByEntity(RetailPriceSurvey retailPriceSurvey) throws Exception {
		baseHibernateService.deleteByEntity(retailPriceSurvey);
	}

	public void deleteRetailPriceSurveyDetailByEntity(RetailPriceSurveyDetail retailPriceSurveyDetail) throws Exception {
		baseHibernateService.deleteByEntity(retailPriceSurveyDetail);
	}

}
