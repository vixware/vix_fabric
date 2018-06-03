package com.vix.system.changeManagement.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.system.changeManagement.entity.ChangeRecord;

/**
 * 
 * @author zhanghaibing
 * 
 * @date 2014-2-27
 */
@Controller
@Scope("prototype")
public class ChangeManagementAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String id;
	private ChangeRecord changeRecord;
	private List<ChangeRecord> changeRecordList;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			Pager pager = baseHibernateService.findPagerByOrHqlConditions(getPager(), ChangeRecord.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				changeRecord = baseHibernateService.findEntityById(ChangeRecord.class, id);
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
			if (null != changeRecord.getId()) {
				isSave = false;
			}
			changeRecord = baseHibernateService.merge(changeRecord);
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
			ChangeRecord changeRecord = baseHibernateService.findEntityById(ChangeRecord.class, id);
			if (null != changeRecord) {
				baseHibernateService.deleteByEntity(changeRecord);
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

	public ChangeRecord getChangeRecord() {
		return changeRecord;
	}

	public void setChangeRecord(ChangeRecord changeRecord) {
		this.changeRecord = changeRecord;
	}

	public List<ChangeRecord> getChangeRecordList() {
		return changeRecordList;
	}

	public void setChangeRecordList(List<ChangeRecord> changeRecordList) {
		this.changeRecordList = changeRecordList;
	}

}