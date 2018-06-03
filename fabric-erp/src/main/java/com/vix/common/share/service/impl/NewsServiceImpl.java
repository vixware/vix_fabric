package com.vix.common.share.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.share.dao.INewsDao;
import com.vix.common.share.entity.News;
import com.vix.common.share.hql.NewsHqlProvider;
import com.vix.common.share.service.INewsService;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

@Service("newsService")
@Transactional
public class NewsServiceImpl  extends BaseHibernateServiceImpl implements INewsService{

	@Resource(name="newsDao")
	private INewsDao newsDao;
	
	@Resource(name="newsHqlProvider")
	private NewsHqlProvider newsHqlProvider;

	
	/**
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public Pager findNewsPager(Pager pager, Map<String, Object> params) throws Exception{
		StringBuilder hql = newsHqlProvider.findNewsList(params, pager);
		pager = newsDao.findPager2ByHql(pager, newsHqlProvider.entityAsName(), hql.toString(), params);
		return pager;
	}
	
	@Override
	public News saveOrUpdateNews(News entityForm)throws Exception{
		News entity = null;
		if(entityForm!=null && StrUtils.isNotEmpty(entityForm.getId())){
			entity = newsDao.findEntityById(News.class, entityForm.getId());
		}
		if(entity == null){
			entity = new News();
		}
		
		Date now = new Date();
		//String currentUserAccountId = SecurityUtil.getCurrentUserId();
		
		if(entity.getId()==null){
			entity.setCreateTime(now);
			entity.setIsAudit(0);
			entity.setIsPublish(0);
		}
		entity.setUpdateTime(now);
		//entity.setCreateUser(new UserAccount(currentUserAccountId));
		
		Long rdTimes = entity.getReadTimes();
		if(rdTimes==null){
			rdTimes=0L;
		}else{
			rdTimes = rdTimes+1;
		}
		entity.setReadTimes(rdTimes);
		
	/*	entity.setActiveStartDate(entityForm.getActiveStartDate());
		entity.setActiveEndDate(entityForm.getActiveEndDate());
		entity.setSendMsg(entity.getSendMsg());
		entity.setOnTop(entityForm.getOnTop());
		entity.setOnTopDay(entityForm.getOnTopDay());*/
		
		
		entity.setTitle(entityForm.getTitle());
		entity.setSubtitle(entityForm.getSubtitle());
		entity.setNewsType(entityForm.getNewsType());
		entity.setKeywords(entityForm.getKeywords());
		entity.setSourceFrom(entityForm.getSourceFrom());
		entity.setSourceFromUrl(entityForm.getSourceFromUrl());
		entity.setContent(entityForm.getContent());
		
		/*pubIds = StrUtils.relaceFirstAndLastChar(pubIds, ',');
		pubNames = StrUtils.relaceFirstAndLastChar(pubNames, ',');
		String pubType = entityForm.getPubType();
		
		entity.setPubIds(pubIds);
		entity.setPubNames(pubNames);
		entity.setPubType(pubType);*/
		
		/*if(pubType.equals(BizConstant.COMMON_ORG_O)){
			//部门
			entity.setRoles(null);
			entity.setEmployees(null);
			
			String[] pubIdArray = pubIds.split("\\,");
			Set<OrganizationUnit> orgUnitSet = new HashSet<OrganizationUnit>()	;
			for(String pubId:pubIdArray){
				orgUnitSet.add(new OrganizationUnit(Long.parseLong(StrUtils.removeLastChar(pubId))));
			}
			entity.setOrganizationUnits(orgUnitSet);
			
		}else if(pubType.equals(BizConstant.COMMON_ORG_R)){
			//角色
		
			entity.setOrganizationUnits(null);
			entity.setEmployees(null);
			
			String[] pubIdArray = pubIds.split("\\,");
			Set<Role> roleSet = new HashSet<Role>()	;
			for(String pubId:pubIdArray){
				roleSet.add(new Role(Long.parseLong(pubId)));
			}
			entity.setRoles(roleSet);
			
		}else if(pubType.equals(BizConstant.COMMON_ORG_E)){
			//职员
			entity.setOrganizationUnits(null);
			entity.setRoles(null);
			
			String[] pubIdArray = pubIds.split("\\,");
			Set<Employee> empSet = new HashSet<Employee>()	;
			for(String pubId:pubIdArray){
				empSet.add(new Employee(Long.parseLong(pubId)));
			}
			entity.setEmployees(empSet);
		}
		*/
		
		newsDao.saveOrUpdate(entity);
		return entity;
	}
}
