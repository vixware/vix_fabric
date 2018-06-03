package com.vix.common.org.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.vix.common.org.dao.IOrgDrpViewDao;
import com.vix.common.org.entity.OrgDrpView;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("orgDrpViewDao")
public class OrgDrpViewDaoImpl extends BaseHibernateDaoImpl implements IOrgDrpViewDao {

	private static final long serialVersionUID = -8868584160810745209L;

	
	/* (non-Javadoc)
	 * @see com.vix.common.org.dao.IOrgDrpViewDao#findOrgByInnerCode(java.lang.String)
	 */
	@Override
	public OrgDrpView findOrgByInnerCode(String innerCode)throws Exception{
		String ename = "comp";
		StringBuilder hql = new StringBuilder();
		Map<String,Object> param = new HashMap<String,Object>();
		
		hql.append("select ").append(ename).append(" from ").append(OrgDrpView.class.getName());
		hql.append(" ").append(ename).append(" where ");
		hql.append(ename).append(".companyInnerCode = :companyInnerCode ");
		hql.append(" order by ").append(ename).append(".realId asc");
		param.put("companyInnerCode", innerCode);

		//HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);//不处理CompanyInnerCode;
		List<OrgDrpView> orgList = findAllByHql2NoTenantId(hql.toString(), param);
		if(orgList!=null && orgList.size()>0){
			return orgList.get(0);
		}
		
		return null;
	}
}
