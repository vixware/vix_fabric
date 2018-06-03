package com.vix.nvix.srm.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.share.entity.EcMessageConfig;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.MessageTemplate;
import com.vix.ebusiness.entity.BusinessCustomer;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class MessageSendAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private MessageTemplate messageTemplate;

	private String id;

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String messageName = getDecodeRequestParameter("messageName");
			if (StringUtils.isNotEmpty(messageName)) {
				params.put("name," + SearchCondition.ANYLIKE, messageName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, MessageTemplate.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				messageTemplate = vixntBaseService.findEntityById(MessageTemplate.class, id);
			}
			if (messageTemplate != null) {
			} else {
				messageTemplate = new MessageTemplate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	public void saveOrUpdate() {

		boolean isSave = true;
		try {
			// initEntityBaseController.initEntityBaseAttribute(messageTemplate);
			// messageTemplate = vixntBaseService.merge(messageTemplate);
			EcMessageConfig ecMessageConfig = new EcMessageConfig();
			ecMessageConfig.setMsgUrl("http://101.200.228.238/NewSmsPort/default.asmx/SendSms");
			ecMessageConfig.setMsgAccount("zsyc01");
			ecMessageConfig.setMsgPassword("zsyc123456");
			ecMessageConfig.setMsgType("AO_YI_HU_TONG");
			Map<String, Object> params = getParams();
			List<BusinessCustomer> businessCustomerList = this.vixntBaseService.findAllByConditions(BusinessCustomer.class, params);
			String phoneList = "";
			if (businessCustomerList != null && businessCustomerList.size() > 0) {
				for (BusinessCustomer businessCustomer : businessCustomerList) {
					if (StringUtils.isNotEmpty(businessCustomer.getMobile())) {
						phoneList += businessCustomer.getMobile() + ",";
					}
				}
			}
			System.out.println(phoneList);
			//MessageSender.sendMessage(ecMessageConfig, phoneList, "【食来运转】会员专享：早市/预订1元/斤绿色蔬菜！密云农场，每天直送，新鲜健康。保证长期供应，会员就是任性！近期组织农场活动敬请关注。详询010-57268138");
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
	}

	public void deleteById() {
		try {
			MessageTemplate messageTemplate = vixntBaseService.findEntityById(MessageTemplate.class, id);
			if (null != messageTemplate) {
				vixntBaseService.deleteByEntity(messageTemplate);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public MessageTemplate getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(MessageTemplate messageTemplate) {
		this.messageTemplate = messageTemplate;
	}
}