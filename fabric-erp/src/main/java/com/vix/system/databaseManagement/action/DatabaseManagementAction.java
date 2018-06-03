package com.vix.system.databaseManagement.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.system.databaseManagement.entity.DatabaseRecord;

/**
 * 扩展表类型
 */
@Controller
@Scope("prototype")
public class DatabaseManagementAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private DatabaseRecord databaseRecord;
	private List<DatabaseRecord> databaseRecordList;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			Pager pager = baseHibernateService.findPagerByOrHqlConditions(getPager(), DatabaseRecord.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				databaseRecord = baseHibernateService.findEntityById(DatabaseRecord.class, id);
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
			if (databaseRecord != null && StringUtils.isNotEmpty(databaseRecord.getId())) {
				isSave = false;
			}
			databaseRecord = baseHibernateService.merge(databaseRecord);
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
			DatabaseRecord databaseRecord = baseHibernateService.findEntityById(DatabaseRecord.class, id);
			if (null != databaseRecord) {
				baseHibernateService.deleteByEntity(databaseRecord);
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

	public DatabaseRecord getDatabaseRecord() {
		return databaseRecord;
	}

	public void setDatabaseRecord(DatabaseRecord databaseRecord) {
		this.databaseRecord = databaseRecord;
	}

	public List<DatabaseRecord> getDatabaseRecordList() {
		return databaseRecordList;
	}

	public void setDatabaseRecordList(List<DatabaseRecord> databaseRecordList) {
		this.databaseRecordList = databaseRecordList;
	}

}