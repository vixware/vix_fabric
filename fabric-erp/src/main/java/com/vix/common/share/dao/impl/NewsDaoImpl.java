package com.vix.common.share.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.common.share.dao.INewsDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("newsDao")
public class NewsDaoImpl extends BaseHibernateDaoImpl implements INewsDao {


}
