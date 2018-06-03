package com.vix.nvix.system.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.share.entity.AlertNotice;
import com.vix.nvix.common.base.action.VixntBaseAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class VixntRemindsCenterAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private AlertNotice alertNotice;
	private List<AlertNotice> alertNoticeList;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			alertNoticeList = vixntBaseService.findAllByConditions(AlertNotice.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goList";
	}

	/** 跳转至用户修改页面 */
	public String showAlertNotice() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				alertNotice = vixntBaseService.findEntityById(AlertNotice.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showCanlendar";
	}

	/** 处理删除操作 */
	public void deleteAlertNoticeById() {
		try {
			AlertNotice AlertNotice = vixntBaseService.findEntityById(AlertNotice.class, id);
			if (null != AlertNotice) {
				vixntBaseService.deleteByEntity(AlertNotice);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public void alertNoticeEvents() {
		try {
			String json = "";
			Map<String, Object> params = new HashMap<String, Object>();
			alertNoticeList = vixntBaseService.findAllByConditions(AlertNotice.class, params);
			if (alertNoticeList != null && alertNoticeList.size() > 0) {
				JSONArray array = new JSONArray();
				for (AlertNotice alertNotice : alertNoticeList) {
					JSONObject object = new JSONObject();
					object.put("id", alertNotice.getId());
					object.put("title", alertNotice.getSubject());
					if (alertNotice.getStartDay() != null) {
						object.put("start", dateFormat.format(alertNotice.getStartDay()));
					}
					if (alertNotice.getEndDay() != null) {
						object.put("end", dateFormat.format(alertNotice.getEndDay()));
					}
					array.add(object);
				}
				json = array.toString();
			}
			if (!"".equals(json)) {
				renderJson(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the alertNotice
	 */
	public AlertNotice getAlertNotice() {
		return alertNotice;
	}

	/**
	 * @param alertNotice
	 *            the alertNotice to set
	 */
	public void setAlertNotice(AlertNotice alertNotice) {
		this.alertNotice = alertNotice;
	}

	/**
	 * @return the alertNoticeList
	 */
	public List<AlertNotice> getAlertNoticeList() {
		return alertNoticeList;
	}

	/**
	 * @param alertNoticeList
	 *            the alertNoticeList to set
	 */
	public void setAlertNoticeList(List<AlertNotice> alertNoticeList) {
		this.alertNoticeList = alertNoticeList;
	}

}