package com.vix.contract.config.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.contract.config.dao.IContractCatalogDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

/**
 * @ClassName: ContractCatalogDaoImpl
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-7-29 上午8:53:36
 */
@Repository("contractCatalogDao")
public class ContractCatalogDaoImpl extends BaseHibernateDaoImpl implements IContractCatalogDao {

	private static final long serialVersionUID = 1L;

}
