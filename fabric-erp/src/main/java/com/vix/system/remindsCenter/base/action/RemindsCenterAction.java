package com.vix.system.remindsCenter.base.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.AlertNotice;
import com.vix.core.web.Pager;
import com.vix.system.remindsCenter.base.service.IRemindsCenterService;

@Controller
@Scope("prototype")
public class RemindsCenterAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IRemindsCenterService remindsCenterService;

	private String id;
	private String ids;
	private String pageNo;
	private AlertNotice alertNotice;
	private List<AlertNotice> alertNoticeList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			alertNoticeList = remindsCenterService.findRemindsList(AlertNotice.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (null == id || id.equals("0")) {
			} else {
			}
			Pager pager = remindsCenterService.findPagerByHqlConditions(getPager(), AlertNotice.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String deleteAlertNoticeById() {
		try {
			AlertNotice alertNotice = remindsCenterService.findEntityById(AlertNotice.class, id);
			if (null != alertNotice) {
				remindsCenterService.deleteByEntity(alertNotice);
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

	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				alertNotice = remindsCenterService.findEntityById(AlertNotice.class, id);
			} else {
				alertNotice = new AlertNotice();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != alertNotice.getId()) {
				isSave = false;
			}
			remindsCenterService.merge(alertNotice);
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
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public AlertNotice getAlertNotice() {
		return alertNotice;
	}

	public void setAlertNotice(AlertNotice alertNotice) {
		this.alertNotice = alertNotice;
	}

	public List<AlertNotice> getAlertNoticeList() {
		return alertNoticeList;
	}

	public void setAlertNoticeList(List<AlertNotice> alertNoticeList) {
		this.alertNoticeList = alertNoticeList;
	}

}
