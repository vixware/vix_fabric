package com.vix.crm.member.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.member.dao.IMemberLevelDao;

@Repository("memberLevelDao")
public class MemberLevelDaoImpl extends BaseHibernateDaoImpl implements IMemberLevelDao {

	private static final long serialVersionUID = 1L;

}
