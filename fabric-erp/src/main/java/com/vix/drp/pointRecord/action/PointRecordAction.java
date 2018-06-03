package com.vix.drp.pointRecord.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.drp.pointRecord.controller.PointRecordController;
import com.vix.drp.pointRecord.entity.PointRecord;

@Controller
@Scope("prototype")
public class PointRecordAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PointRecordController pointRecordController;
	private String id;
	private String ids;
	private PointRecord pointRecord;
	private List<PointRecord> pointRecordList;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			pager = pointRecordController.findPointRecordPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				pointRecord = pointRecordController.doListPointRecordById(id);
			} else {
				pointRecord = new PointRecord();
				pointRecord = pointRecordController.doSavePointRecord(pointRecord);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != pointRecord.getId() && !"".equals(pointRecord.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(pointRecord);
			pointRecord = pointRecordController.doSavePointRecord(pointRecord);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			PointRecord pointRecord = pointRecordController.doListPointRecordById(id);
			if (null != pointRecord) {
				pointRecordController.doDeletePointRecord(pointRecord);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				pointRecordController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
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

	public PointRecord getPointRecord() {
		return pointRecord;
	}

	public void setPointRecord(PointRecord pointRecord) {
		this.pointRecord = pointRecord;
	}

	public List<PointRecord> getPointRecordList() {
		return pointRecordList;
	}

	public void setPointRecordList(List<PointRecord> pointRecordList) {
		this.pointRecordList = pointRecordList;
	}

}
