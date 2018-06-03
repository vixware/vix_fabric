package com.vix.drp.printMediaManagement.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.printMediaManagement.entity.PrintMedia;

@Component("printMediaManagementDomain")
@Transactional
public class PrintMediaManagementDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PrintMedia.class, params);
		return p;
	}

	public PrintMedia findPrintMediaById(String id) throws Exception {
		return baseHibernateService.findEntityById(PrintMedia.class, id);
	}

	public PrintMedia saveOrUpdatePrintMedia(PrintMedia printMedia) throws Exception {
		return baseHibernateService.merge(printMedia);
	}

	public void deleteByEntity(PrintMedia printMedia) throws Exception {
		baseHibernateService.deleteByEntity(printMedia);
	}

}
