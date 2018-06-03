/**
 * 
 */
package com.vix.system.code.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.system.billTypeManagement.entity.BillsType;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("codeDomain")
@Transactional
public class CodeDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, EncodingRulesTableInTheMiddle.class, params);
		return p;
	}

	public EncodingRulesTableInTheMiddle findEncodingRulesTableInTheMiddleByAttribute(String billTypeCode) throws Exception {
		return baseHibernateService.findEntityByAttribute(EncodingRulesTableInTheMiddle.class, "billType", billTypeCode);
	}

	public void deleteByEntity(EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle) throws Exception {
		baseHibernateService.deleteByEntity(encodingRulesTableInTheMiddle);
	}

	public BillsType findBillsType(String id) throws Exception {
		return baseHibernateService.findEntityById(BillsType.class, id);
	}

	public EncodingRulesTableInTheMiddle findEncodingRulesTableInTheMiddle(String id) throws Exception {
		return baseHibernateService.findEntityById(EncodingRulesTableInTheMiddle.class, id);
	}

	public EncodingRulesTableInTheMiddle mergeEncodingRulesTableInTheMiddle(EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle) throws Exception {
		return baseHibernateService.merge(encodingRulesTableInTheMiddle);
	}

	/** 索引对象列表 */
	public List<EncodingRulesTableInTheMiddle> findEncodingRulesTableInTheMiddleIndex() throws Exception {
		return baseHibernateService.findAllByConditions(EncodingRulesTableInTheMiddle.class, null);
	}

	public List<BillsType> findBillsTypeList() throws Exception {
		return baseHibernateService.findAllByConditions(BillsType.class, null);
	}

}
