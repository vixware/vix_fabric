package com.vix.contract.mamanger.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.contract.mamanger.dao.IContractDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

/**
 * @ClassName: ApplicationFormDaoImpl
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-24 下午1:43:44
 */
@Repository("contractDao")
public class ContractDaoImpl extends BaseHibernateDaoImpl implements IContractDao {

	private static final long serialVersionUID = 1L;

}
