package com.vix.common.message.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.VixAction;
import com.vix.common.message.entity.MessageSendRecord;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.service.IVixntBaseService;

/**
 * 短信发送记录
 * 
 * @类全名 com.vix.common.message.action.MessageSendRecordAction
 *
 * @author yhl
 *
 * @date 2017年9月29日
 */
@Controller
@Scope("prototype")
public class MessageSendRecordAction extends VixAction {
	private static final long serialVersionUID = 1L;
	private String id;
	private MessageSendRecord messageSendRecord;
	
	@Autowired
	private IVixntBaseService vixntBaseService;

	public void goSingleList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			String messageName = getDecodeRequestParameter("messageName");
			if(StrUtils.isNotEmpty(messageName)){
				params.put("title," + SearchCondition.EQUAL, messageName);
			}
			String mobilePhone = getRequestParameter("mobilePhone");
			if(StrUtils.isNotEmpty(mobilePhone)){
				params.put("mobilePhone," + SearchCondition.ENDLIKE, mobilePhone);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, MessageSendRecord.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				MessageSendRecord messageSendRecord = vixntBaseService.findEntityById(MessageSendRecord.class, id);
				if (null != messageSendRecord) {
					vixntBaseService.deleteByEntity(messageSendRecord);
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

	public MessageSendRecord getMessageSendRecord() {
		return messageSendRecord;
	}

	public void setMessageSendRecord(MessageSendRecord messageSendRecord) {
		this.messageSendRecord = messageSendRecord;
	}

}