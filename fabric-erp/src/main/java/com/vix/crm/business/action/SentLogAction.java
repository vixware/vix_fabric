package com.vix.crm.business.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.SentLogController;
import com.vix.crm.business.entity.MessageLog;

/**
 * 发送记录 com.vix.crm.business.action.SentLogAction
 *
 * @author zhanghaibing
 *
 * @date 2014年12月30日
 */
@Controller
@Scope("prototype")
public class SentLogAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private String pageNo;
	@Autowired
	private SentLogController sentLogController;
	/**
	 * 发送记录
	 */
	private MessageLog messageLog;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}

			if (null != pageNo && !"".equals(pageNo) && !"undefined".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
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
			pager = sentLogController.doListMessageLogPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				messageLog = sentLogController.doListMessageLogById(id);
			} else {
				messageLog = new MessageLog();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public MessageLog getMessageLog() {
		return messageLog;
	}

	public void setMessageLog(MessageLog messageLog) {
		this.messageLog = messageLog;
	}

}
