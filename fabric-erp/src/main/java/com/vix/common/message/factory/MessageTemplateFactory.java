/**
 * 
 */
package com.vix.common.message.factory;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.message.constant.MessageTemplateConstant;
import com.vix.common.message.entity.MessageSendTemplate;
import com.vix.common.share.entity.EcMessageConfig;
import com.vix.nvix.common.message.MessageSender;

/**
 * 
 * @author zhanghaibing
 * 
 * @date 2014-5-23
 */
public class MessageTemplateFactory {

	public static String sendMessage(Map<String, String> orderStatusMap, MessageSendTemplate messageSendTemplate, EcMessageConfig ecMessageConfig, String mobilePhone) throws Exception {
		String msg = "";
		if (messageSendTemplate != null && StringUtils.isNotEmpty(messageSendTemplate.getMsgContent())) {
			msg = messageSendTemplate.getMsgContent();
		}
		if (orderStatusMap.containsKey(MessageTemplateConstant.username)) {
			msg = msg.replaceAll(MessageTemplateConstant.getTypeMap().get(MessageTemplateConstant.username), orderStatusMap.get(MessageTemplateConstant.username));
		}
		if (orderStatusMap.containsKey(MessageTemplateConstant.sex)) {
			msg = msg.replaceAll(MessageTemplateConstant.getTypeMap().get(MessageTemplateConstant.sex), orderStatusMap.get(MessageTemplateConstant.sex));
		}
		if (orderStatusMap.containsKey(MessageTemplateConstant.cardNo)) {
			msg = msg.replaceAll(MessageTemplateConstant.getTypeMap().get(MessageTemplateConstant.cardNo), orderStatusMap.get(MessageTemplateConstant.cardNo));
		}
		if (orderStatusMap.containsKey(MessageTemplateConstant.password)) {
			msg = msg.replaceAll(MessageTemplateConstant.getTypeMap().get(MessageTemplateConstant.password), orderStatusMap.get(MessageTemplateConstant.password));
		}
		if (orderStatusMap.containsKey(MessageTemplateConstant.integral)) {
			msg = msg.replaceAll(MessageTemplateConstant.getTypeMap().get(MessageTemplateConstant.integral), orderStatusMap.get(MessageTemplateConstant.integral));
		}
		if (orderStatusMap.containsKey(MessageTemplateConstant.amount)) {
			msg = msg.replaceAll(MessageTemplateConstant.getTypeMap().get(MessageTemplateConstant.amount), orderStatusMap.get(MessageTemplateConstant.amount));
		}
		if (orderStatusMap.containsKey(MessageTemplateConstant.companyName)) {
			msg = msg.replaceAll(MessageTemplateConstant.getTypeMap().get(MessageTemplateConstant.companyName), orderStatusMap.get(MessageTemplateConstant.companyName));
		}
		if (orderStatusMap.containsKey(MessageTemplateConstant.servicephone)) {
			msg = msg.replaceAll(MessageTemplateConstant.getTypeMap().get(MessageTemplateConstant.servicephone), orderStatusMap.get(MessageTemplateConstant.servicephone));
		}
		if (orderStatusMap.containsKey(MessageTemplateConstant.title)) {
			msg = msg.replaceAll(MessageTemplateConstant.getTypeMap().get(MessageTemplateConstant.title), orderStatusMap.get(MessageTemplateConstant.title));
		}
		if (orderStatusMap.containsKey(MessageTemplateConstant.commentcontent)) {
			msg = msg.replaceAll(MessageTemplateConstant.getTypeMap().get(MessageTemplateConstant.commentcontent), orderStatusMap.get(MessageTemplateConstant.commentcontent));
		}
		return MessageSender.sendMessage(ecMessageConfig, mobilePhone, msg);
	}

	public static String getMessageText(String message) {
		if (message.contains(MessageTemplateConstant.username)) {
			message = message.replaceAll(MessageTemplateConstant.username, "XXX");
		}
		if (message.contains(MessageTemplateConstant.sex)) {
			message = message.replaceAll(MessageTemplateConstant.sex, "XXX");
		}
		if (message.contains(MessageTemplateConstant.cardNo)) {
			message = message.replaceAll(MessageTemplateConstant.cardNo, "XXX");
		}
		if (message.contains(MessageTemplateConstant.password)) {
			message = message.replaceAll(MessageTemplateConstant.password, "XXX");
		}
		if (message.contains(MessageTemplateConstant.integral)) {
			message = message.replaceAll(MessageTemplateConstant.integral, "XXX");
		}
		if (message.contains(MessageTemplateConstant.amount)) {
			message = message.replaceAll(MessageTemplateConstant.amount, "XXX");
		}
		if (message.contains(MessageTemplateConstant.companyName)) {
			message = message.replaceAll(MessageTemplateConstant.companyName, "XXX");
		}
		if (message.contains(MessageTemplateConstant.servicephone)) {
			message = message.replaceAll(MessageTemplateConstant.servicephone, "XXX");
		}
		if (message.contains(MessageTemplateConstant.title)) {
			message = message.replaceAll(MessageTemplateConstant.title, "XXX");
		}
		if (message.contains(MessageTemplateConstant.commentcontent)) {
			message = message.replaceAll(MessageTemplateConstant.commentcontent, "XXX");
		}
		return message;
	}
}
