package com.vix.crm.business.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.MessageTemplateTypeSetController;
import com.vix.crm.business.entity.MessageTemplateType;

@Controller
@Scope("prototype")
public class MessageTemplateTypeSetAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String id;

	private MessageTemplateType messageTemplateType;
	private List<MessageTemplateType> messageTemplateTypeList;
	@Autowired
	private MessageTemplateTypeSetController messageTemplateTypeSetController;

	public String goMessageTemplateTypeList() {
		try {
			Map<String, Object> params = getParams();// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			messageTemplateTypeList = messageTemplateTypeSetController.doListMessageTemplateTypeList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMessageTemplateTypeList";
	}

	public String goMessageTemplateTypeListContent() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			//倒序排序
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = messageTemplateTypeSetController.doListMessageTemplateType(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMessageTemplateTypeListContent";
	}

	public String goSaveOrUpdateMessageTemplateType() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				messageTemplateType = messageTemplateTypeSetController.doListMessageTemplateTypeById(id);
			} else {
				messageTemplateType = new MessageTemplateType();
				messageTemplateType.setCode(VixUUID.createCode(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateMessageTemplateType";
	}

	/**
	 * 保存短信模板
	 * 
	 * @return
	 */
	public String saveOrUpdateMessageTemplateType() {
		boolean isSave = true;
		try {
			if (null != messageTemplateType.getId() && !"".equals(messageTemplateType.getId())) {
				isSave = false;
			}
			//处理修改留痕
			billMarkProcessController.processMark(messageTemplateType, updateField);
			messageTemplateType = messageTemplateTypeSetController.doSaveMessageTemplateType(messageTemplateType);
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

	public String deleteMessageTemplateTypeById() {
		try {
			MessageTemplateType messageTemplateType = messageTemplateTypeSetController.doListMessageTemplateTypeById(id);
			if (null != messageTemplateType) {
				messageTemplateTypeSetController.doDeleteMessageTemplateType(messageTemplateType);
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

	public MessageTemplateType getMessageTemplateType() {
		return messageTemplateType;
	}

	public void setMessageTemplateType(MessageTemplateType messageTemplateType) {
		this.messageTemplateType = messageTemplateType;
	}

	public List<MessageTemplateType> getMessageTemplateTypeList() {
		return messageTemplateTypeList;
	}

	public void setMessageTemplateTypeList(List<MessageTemplateType> messageTemplateTypeList) {
		this.messageTemplateTypeList = messageTemplateTypeList;
	}

}
