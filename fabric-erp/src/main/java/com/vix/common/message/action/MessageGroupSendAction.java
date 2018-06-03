package com.vix.common.message.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.VixAction;
import com.vix.common.id.VixUUID;
import com.vix.common.message.entity.MessageGroupSend;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.service.IVixntBaseService;

/**
 * 短信群发设置
 * 
 * @类全名 com.vix.common.message.action.MessageGroupSendAction
 *
 * @author yhl
 *
 * @date 2017年9月29日
 */
@Controller
@Scope("prototype")
public class MessageGroupSendAction extends VixAction {
	private static final long serialVersionUID = 1L;
	private String id;
	private MessageGroupSend messageGroupSend;
	
	@Autowired
	private IVixntBaseService vixntBaseService;

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != messageGroupSend.getId()) {
				isSave = false;
			}
			messageGroupSend = vixntBaseService.merge(messageGroupSend);
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

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				messageGroupSend = vixntBaseService.findEntityByAttributeNoTenantId(MessageGroupSend.class, "id", id);
			} else {
				messageGroupSend = new MessageGroupSend();
				messageGroupSend.setCode(VixUUID.createCode(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	public void goSingleList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			String messageName = getDecodeRequestParameter("messageName");
			if(StrUtils.isNotEmpty(messageName)){
				params.put("title," + SearchCondition.EQUAL, messageName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, MessageGroupSend.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				MessageGroupSend messageGroupSend = vixntBaseService.findEntityById(MessageGroupSend.class, id);
				if (null != messageGroupSend) {
					vixntBaseService.deleteByEntity(messageGroupSend);
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

	public MessageGroupSend getMessageGroupSend() {
		return messageGroupSend;
	}

	public void setMessageGroupSend(MessageGroupSend messageGroupSend) {
		this.messageGroupSend = messageGroupSend;
	}

}