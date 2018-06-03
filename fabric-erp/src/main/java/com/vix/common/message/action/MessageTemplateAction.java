package com.vix.common.message.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.VixAction;
import com.vix.common.message.entity.MessageSendTemplate;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.service.IVixntBaseService;

@Controller
@Scope("prototype")
public class MessageTemplateAction extends VixAction {

	private static final long serialVersionUID = 1L;

	private String id;

	private MessageSendTemplate messageSendTemplate;
	
	@Autowired
	private IVixntBaseService vixntBaseService;

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				messageSendTemplate = vixntBaseService.findEntityById(MessageSendTemplate.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	public void saveOrUpdate() {

		boolean isSave = true;
		try {
			if (null != messageSendTemplate.getId()) {
				isSave = false;
			}
			messageSendTemplate = vixntBaseService.merge(messageSendTemplate);
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

	public void goSingleList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			String messageName = getDecodeRequestParameter("messageName");
			if(StrUtils.isNotEmpty(messageName)){
				params.put("name," + SearchCondition.ANYLIKE, messageName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, MessageSendTemplate.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				MessageSendTemplate messageSendTemplate = vixntBaseService.findEntityById(MessageSendTemplate.class, id);
				if (null != messageSendTemplate) {
					vixntBaseService.deleteByEntity(messageSendTemplate);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MessageSendTemplate getMessageSendTemplate() {
		return messageSendTemplate;
	}

	public void setMessageSendTemplate(MessageSendTemplate messageSendTemplate) {
		this.messageSendTemplate = messageSendTemplate;
	}

}