package com.vix.contract.config.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.contract.config.dao.IContractClauseDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

/**
 * @ClassName: ContractClauseDaoImpl
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-7-21 下午5:13:57
 */
@Repository("contractClauseDao")
public class ContractClauseDaoImpl extends BaseHibernateDaoImpl implements IContractClauseDao {

	private static final long serialVersionUID = 1L;

}
