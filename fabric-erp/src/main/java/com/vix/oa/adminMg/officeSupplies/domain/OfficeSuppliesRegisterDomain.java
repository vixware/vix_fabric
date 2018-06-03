package com.vix.oa.adminMg.officeSupplies.domain;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.officeSupplies.entity.OfficeSupplies;
import com.vix.oa.adminMg.officeSupplies.entity.OfficeSuppliesBorrow;
import com.vix.oa.adminMg.officeSupplies.entity.OfficeSuppliesRegister;

/**
 * 
 * @ClassName: OfficeSuppliesRegisterDomain
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-5-13 下午4:40:35
 */
@Component("officeSuppliesRegisterDomain")
@Transactional
public class OfficeSuppliesRegisterDomain extends BaseDomain{

	
	/** 获取列表数据 */
	public Pager findWimStockrecordsPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, OfficeSuppliesRegister.class, params);
		return p;
	}
	
	/** 获取办公用品搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, OfficeSuppliesRegister.class, params);
		return p;
	}
	
	/** 获取借用列表数据 */
	public Pager findBorrowListConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, OfficeSuppliesBorrow.class, params);
		return p;
	}
		
	/** 索引对象列表 */
	public List<OfficeSuppliesRegister> findWimStockrecordsIndex() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		// 过滤归还单
		/*params.put("flag," + SearchCondition.ANYLIKE, "2");
		params.put("isTemp," + SearchCondition.NOEQUAL, "1");*/
		return baseHibernateService.findAllByConditions(OfficeSuppliesRegister.class, params);
	}
	
	public OfficeSuppliesRegister findWimStockrecordsById(String id) throws Exception {
		return baseHibernateService.findEntityById(OfficeSuppliesRegister.class, id);
	}
	
	public OfficeSupplies findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(OfficeSupplies.class, id);
	}

}
