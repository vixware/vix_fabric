package com.vix.common.org.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.dao.IOrgDrpViewDao;
import com.vix.common.org.entity.OrgDrpView;
import com.vix.common.org.service.IOrgDrpViewService;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.utils.StrUtils;

@Service("orgDrpViewService")
@Transactional
public class OrgDrpViewServiceImpl extends BaseHibernateServiceImpl implements	IOrgDrpViewService {

	private static final long serialVersionUID = 7866819391700843060L;
	
	@Resource(name="orgDrpViewDao")
	private IOrgDrpViewDao orgDrpViewDao;

	@Override
	public List<OrgDrpView> findOrgDrpViewList(String id) throws Exception{
		if(StrUtils.isEmpty(id)){
			String innerCode = SecurityUtil.getCurrentEmpOrgInnerCode();
			if(StrUtils.isNotEmpty(innerCode)){
				OrgDrpView orgViewTmp = orgDrpViewDao.findOrgByInnerCode(innerCode);
				List<OrgDrpView> orgViewList = new LinkedList<OrgDrpView>();
				orgViewList.add(orgViewTmp);
				return orgViewList;
			}
		}else{
			List<OrgDrpView> orgViewList = findOrgDrpViewListByParentId(id);
			return orgViewList;
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrgDrpViewService#findOrgDrpViewListByParentId(java.lang.String)
	 */
	@Override
	public List<OrgDrpView> findOrgDrpViewListByParentId(String id)throws Exception{
		String entityAsName = "orgDrpView";
		Map<String,Object> params = new HashMap<String, Object>();
		
		//StringBuilder hqlBuilder =new StringBuilder();
        StringBuilder hqlBuilder =new StringBuilder();
        hqlBuilder.append("select ").append(entityAsName);
        hqlBuilder.append(" from ");
        
        hqlBuilder.append(OrgDrpView.class.getName()).append(" ").append(entityAsName);
        hqlBuilder.append(" where ");
       
        if(StringUtils.isNotEmpty(id)){
        	hqlBuilder.append(entityAsName).append(".parentId = :parentId");
        	params.put("parentId", id);
        }else{
        	hqlBuilder.append(entityAsName).append(".parentId is null ");
        }
        hqlBuilder.append(" order by ").append(entityAsName).append(".id ");
        
        HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
        
        List<OrgDrpView> orgDrpList = orgDrpViewDao.findAllByHql2(hqlBuilder.toString(), params);
        return orgDrpList;
	}
}
