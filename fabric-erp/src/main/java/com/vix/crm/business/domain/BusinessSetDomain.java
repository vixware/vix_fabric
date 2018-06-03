/**
 * 
 */
package com.vix.crm.business.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.EmailTemplate;
import com.vix.crm.business.entity.MessageTemplate;
import com.vix.crm.business.entity.MessageTemplateType;
import com.vix.crm.business.service.IOrderManagementService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("businessSetDomain")
@Transactional
public class BusinessSetDomain extends BaseDomain{

	@Autowired
	private IOrderManagementService orderManagementService;

	/** 获取列表数据 */
	public Pager findMessageTemplatePager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderManagementService.findPagerByHqlConditions(pager, MessageTemplate.class, params);
		return p;
	}

	public Pager findEmailTemplatePager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderManagementService.findPagerByHqlConditions(pager, EmailTemplate.class, params);
		return p;
	}

	public MessageTemplate findMessageTemplateById(String id) throws Exception {
		return orderManagementService.findEntityById(MessageTemplate.class, id);
	}

	public List<MessageTemplateType> findMessageTemplateTypeList(Map<String, Object> params) throws Exception {
		return orderManagementService.findAllByConditions(MessageTemplateType.class, params);
	}

	public EmailTemplate findEmailTemplateById(String id) throws Exception {
		return orderManagementService.findEntityById(EmailTemplate.class, id);
	}

	public void deleteMessageTemplateByEntity(MessageTemplate messageTemplate) throws Exception {
		orderManagementService.deleteByEntity(messageTemplate);
	}

	public void deleteEmailTemplateByEntity(EmailTemplate emailTemplate) throws Exception {
		orderManagementService.deleteByEntity(emailTemplate);
	}

	public MessageTemplate saveMessageTemplate(MessageTemplate messageTemplate) throws Exception {
		return orderManagementService.merge(messageTemplate);
	}

	public EmailTemplate saveEmailTemplate(EmailTemplate emailTemplate) throws Exception {
		return orderManagementService.merge(emailTemplate);
	}

}
