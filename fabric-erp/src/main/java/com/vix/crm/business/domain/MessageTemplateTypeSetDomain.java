/**
 * 
 */
package com.vix.crm.business.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.MessageTemplateType;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("messageTemplateTypeSetDomain")
@Transactional
public class MessageTemplateTypeSetDomain extends BaseDomain{


	public Pager findMessageTemplateTypePager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, MessageTemplateType.class, params);
		return p;
	}

	public MessageTemplateType findMessageTemplateTypeById(String id) throws Exception {
		return baseHibernateService.findEntityById(MessageTemplateType.class, id);
	}

	public void deleteMessageTemplateType(MessageTemplateType messageTemplateType) throws Exception {
		baseHibernateService.deleteByEntity(messageTemplateType);
	}

	public MessageTemplateType saveMessageTemplateType(MessageTemplateType messageTemplateType) throws Exception {
		return baseHibernateService.merge(messageTemplateType);
	}

	public List<MessageTemplateType> findMessageTemplateTypeList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(MessageTemplateType.class, params);
	}

}
