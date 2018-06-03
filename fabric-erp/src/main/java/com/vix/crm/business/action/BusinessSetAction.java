package com.vix.crm.business.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.BusinessSetController;
import com.vix.crm.business.entity.EmailTemplate;
import com.vix.crm.business.entity.MessageTemplate;
import com.vix.crm.business.entity.MessageTemplateType;

@Controller
@Scope("prototype")
public class BusinessSetAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String id;

	private MessageTemplate messageTemplate;

	private EmailTemplate emailTemplate;
	private List<MessageTemplateType> messageTemplateTypeList;
	@Autowired
	private BusinessSetController businessSetController;

	/**
	 * 短信
	 * 
	 * @return
	 */
	public String goMessageList() {
		return "goMessageList";
	}

	/**
	 * 短信
	 * 
	 * @return
	 */
	public String goMessageListContent() {

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = businessSetController.doListMessageTemplate(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "goMessageListContent";
	}

	/**
	 * 短信模板
	 * 
	 * @return
	 */
	public String goMessageTemplate() {
		try {
			Map<String, Object> params = getParams();
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			messageTemplateTypeList = businessSetController.doListMessageTemplateTypeList(params);
			if (id != null && !"".equals(id)) {
				messageTemplate = businessSetController.doListMessageTemplateById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMessageTemplate";
	}

	/**
	 * 保存短信模板
	 * 
	 * @return
	 */
	public String saveOrUpdateMessageTemplate() {
		boolean isSave = true;
		try {
			if (null != messageTemplate.getId() && !"".equals(messageTemplate.getId())) {
				isSave = false;
			}
			String messageContent = "";
			if (messageTemplate.getMessageContent() != null) {
				messageContent = messageTemplate.getMessageContent();
				messageContent = messageContent.replace("{$isOrderCode$}", "订单编码");
				messageContent = messageContent.replace("{$isBuyerNick$}", "买家昵称");
				messageContent = messageContent.replace("{$isGoodsName$}", "商品名称");
				messageTemplate.setMessageContent(messageContent);
			}
			//处理修改留痕
			billMarkProcessController.processMark(messageTemplate, updateField);
			messageTemplate = businessSetController.doSaveMessageTemplate(messageTemplate);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	/**
	 * 删除短信模板
	 * 
	 * @return
	 */
	public String deleteMessageTemplateById() {
		try {
			MessageTemplate messageTemplate = businessSetController.doListMessageTemplateById(id);
			if (null != messageTemplate) {
				businessSetController.doDeleteMessageTemplateByEntity(messageTemplate);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 邮件
	 * 
	 * @return
	 */
	public String goEmailList() {
		return "goEmailList";
	}

	/**
	 * 邮件
	 * 
	 * @return
	 */
	public String goEmailListContent() {

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = businessSetController.doListEmailTemplate(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "goEmailListContent";
	}

	/**
	 * 邮件模板
	 * 
	 * @return
	 */
	public String goEmailTemplate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				emailTemplate = businessSetController.doListEmailTemplateById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goEmailTemplate";
	}

	/**
	 * 保存邮件模板
	 * 
	 * @return
	 */
	public String saveOrUpdateEmailTemplate() {
		boolean isSave = true;
		try {
			if (null != emailTemplate.getId() && !"".equals(emailTemplate.getId())) {
				isSave = false;
			}
			//处理修改留痕
			billMarkProcessController.processMark(emailTemplate, updateField);
			emailTemplate = businessSetController.doSaveEmailTemplate(emailTemplate);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	/**
	 * 删除邮件模板
	 * 
	 * @return
	 */
	public String deleteEmailTemplateById() {
		try {
			EmailTemplate emailTemplate = businessSetController.doListEmailTemplateById(id);
			if (null != emailTemplate) {
				businessSetController.doDeleteEmailTemplateByEntity(emailTemplate);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EmailTemplate getEmailTemplate() {
		return emailTemplate;
	}

	public void setEmailTemplate(EmailTemplate emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	public MessageTemplate getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(MessageTemplate messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	public List<MessageTemplateType> getMessageTemplateTypeList() {
		return messageTemplateTypeList;
	}

	public void setMessageTemplateTypeList(List<MessageTemplateType> messageTemplateTypeList) {
		this.messageTemplateTypeList = messageTemplateTypeList;
	}

}
