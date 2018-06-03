/**
 * 
 */
package com.vix.drp.basicInformation.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.basicInformation.entity.BasicInformation;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("basicInformationDomain")
@Transactional
public class BasicInformationDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findBasicInformationPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, BasicInformation.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findBasicInformationPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, BasicInformation.class, params);
		return p;
	}

	public BasicInformation findBasicInformationById(String id) throws Exception {
		return baseHibernateService.findEntityById(BasicInformation.class, id);
	}

	/**
	 * 保存
	 */
	public BasicInformation mergeBasicInformation(BasicInformation basicInformation) throws Exception {
		return baseHibernateService.merge(basicInformation);
	}

	public void deleteByEntity(BasicInformation basicInformation) throws Exception {
		baseHibernateService.deleteByEntity(basicInformation);
	}

	/** 索引对象列表 */
	public List<BasicInformation> findBasicInformationList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(BasicInformation.class, params);
	}

}
