/**
 * 
 */
package com.vix.crm.business.domain;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.MessageLog;
import com.vix.crm.business.service.IOrderManagementService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("sentLogDomain")
@Transactional
public class SentLogDomain extends BaseDomain{

	@Autowired
	private IOrderManagementService orderManagementService;

	public Pager findMessageLogPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderManagementService.findPagerByHqlConditions(pager, MessageLog.class, params);
		return p;
	}

	public MessageLog findMessageLogById(String id) throws Exception {
		return orderManagementService.findEntityById(MessageLog.class, id);
	}

}
