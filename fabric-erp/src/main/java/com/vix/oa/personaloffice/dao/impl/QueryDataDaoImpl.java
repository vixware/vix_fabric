package com.vix.oa.personaloffice.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.oa.personaloffice.dao.IQueryDataDao;
/** 请不要删除此类，此类做统计用的，虽然没有显示方法，但用到了  继承过来的方法了 guopeng **/
@Repository("queryDataDao")
public class QueryDataDaoImpl extends BaseHibernateDaoImpl implements IQueryDataDao {

	private static final long serialVersionUID = 1L;

}
