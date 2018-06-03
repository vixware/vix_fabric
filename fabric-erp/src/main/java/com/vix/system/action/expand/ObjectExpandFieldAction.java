package com.vix.system.action.expand;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpand;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpandField;
import com.vix.core.utils.PropertyConfigLoader;
import com.vix.system.expand.constant.ExpandConstantInitialization;
import com.vix.system.expand.service.IObjectExpandService;

/**
 * 商品属性扩展
 */
@Controller
@Scope("prototype")
public class ObjectExpandFieldAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IObjectExpandService objectExpandService;
	/** 扩展属性 */
	private ObjectExpandField objectExpandField;
	private String id;
	private List<ObjectExpandField> objectExpandFieldList;
	private Map<String, String> columnTypeConstantMap = ExpandConstantInitialization.getExpandColumnTypeConstantMap();

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				objectExpandFieldList = baseHibernateService.findAllByEntityClassAndAttribute(ObjectExpandField.class, "objectExpand.id", id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				objectExpandField = baseHibernateService.findEntityById(ObjectExpandField.class, id);
			} else {
				String ptId = getRequestParameter("ptId");
				if (null != ptId && !"".equals(ptId)) {
					ObjectExpand et = baseHibernateService.findEntityById(ObjectExpand.class, ptId);
					if (null != et) {
						objectExpandField = new ObjectExpandField();
						objectExpandField.setObjectExpand(et);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != objectExpandField.getId() && !"".equals(objectExpandField.getId())) {
				isSave = false;
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSS");
				objectExpandField.setFieldName("fn_" + sdf.format(new Date()));
			}
			if (null == objectExpandField.getObjectExpand() || null == objectExpandField.getObjectExpand().getId()) {
				setMessage("未绑定类型信息!");
				return UPDATE;
			} else {
				ObjectExpand et = baseHibernateService.findEntityById(ObjectExpand.class, objectExpandField.getObjectExpand().getId());
				objectExpandField.setExpandTableName(et.getExpandTableName());
			}

			objectExpandField = baseHibernateService.merge(objectExpandField);
			if (null != objectExpandField.getExpandTableName() && !"".equals(objectExpandField.getExpandTableName()) && objectExpandService.checkTableExist(PropertyConfigLoader.dbType, objectExpandField.getExpandTableName())) {
				if (!objectExpandService.checkColumnExist(PropertyConfigLoader.dbType, objectExpandField.getExpandTableName(), objectExpandField.getFieldName())) {
					objectExpandService.addExpandTableField(PropertyConfigLoader.dbType, objectExpandField);
				}
			}
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
			ObjectExpandField ef = baseHibernateService.findEntityById(ObjectExpandField.class, id);
			if (null != ef) {
				String tableName = ef.getExpandTableName();
				baseHibernateService.deleteByEntity(ef);
				if (null != tableName && !"".equals(tableName) && objectExpandService.checkTableExist(PropertyConfigLoader.dbType, ef.getExpandTableName())) {
					if (null != ef.getExpandTableName() && !"".equals(ef.getExpandTableName())) {
						objectExpandService.deleteExpandTableField(PropertyConfigLoader.dbType, ef);
					}
				}
				renderText(DELETE_SUCCESS);
			} else {
				setMessage("扩展属性信息不存在!");
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

	public ObjectExpandField getObjectExpandField() {
		return objectExpandField;
	}

	public void setObjectExpandField(ObjectExpandField objectExpandField) {
		this.objectExpandField = objectExpandField;
	}

	public List<ObjectExpandField> getObjectExpandFieldList() {
		return objectExpandFieldList;
	}

	public void setObjectExpandFieldList(List<ObjectExpandField> objectExpandFieldList) {
		this.objectExpandFieldList = objectExpandFieldList;
	}

	public Map<String, String> getColumnTypeConstantMap() {
		return columnTypeConstantMap;
	}

	public void setColumnTypeConstantMap(Map<String, String> columnTypeConstantMap) {
		this.columnTypeConstantMap = columnTypeConstantMap;
	}
}