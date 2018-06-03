package com.vix.crm.member.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.member.dao.IMemberTagDao;

@Repository("memberTagDao")
public class MemberTagDaoImpl extends BaseHibernateDaoImpl implements IMemberTagDao {

	private static final long serialVersionUID = 1L;

}
