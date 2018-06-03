package com.vix.sales.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.sales.common.dao.ISalePersonCategoryDao;

@Repository("salePersonCategoryDao")
public class SalePersonCategoryDaoImpl extends BaseHibernateDaoImpl implements ISalePersonCategoryDao {

	private static final long serialVersionUID = 1L;

}
