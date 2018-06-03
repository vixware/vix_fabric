package com.vix.oa.personaloffice.phoneSms.domain;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.phoneSms.entity.PhoneSms;


/**
 * 
 * @ClassName: PhoneSmsDomain
 * @Description: 短信 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2015-5-27 下午17:16:33
 */
@Component("phoneSmsDomain")
@Transactional
public class PhoneSmsDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PhoneSms.class, params);
		return p;
	}
	
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, PhoneSms.class, params);
		return p;
	}
	
	/** 索引对象列表 */
	public List<PhoneSms> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(PhoneSms.class, null);
	}
	
	
	public PhoneSms findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(PhoneSms.class, id);
	}
	
	public void deleteByEntity(PhoneSms phoneSms) throws Exception {
		baseHibernateService.deleteByEntity(phoneSms);
	}
	
	public PhoneSms merge(PhoneSms phoneSms) throws Exception {
		return baseHibernateService.merge(phoneSms);
	}
	
}
