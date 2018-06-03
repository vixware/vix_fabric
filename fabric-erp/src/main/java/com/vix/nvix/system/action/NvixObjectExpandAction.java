/**
 * 
 */
package com.vix.nvix.system.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.entity.Organization;
import com.vix.common.org.service.IOrganizationService;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpand;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpandField;
import com.vix.core.utils.PropertyConfigLoader;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.system.expand.constant.ExpandConstantInitialization;
import com.vix.system.expand.service.IObjectExpandService;

/**
 * @author Bluesnow 2016年8月26日
 * 
 *         扩展类型管理
 */
@Controller
@Scope("prototype")
public class NvixObjectExpandAction extends VixntBaseAction {

	private static final long serialVersionUID = 3672642140958967510L;

	@Autowired
	private IObjectExpandService objectExpandService;

	@Resource(name = "organizationService")
	private IOrganizationService organizationService;

	private String id;
	private String ids;
	private String treeId;
	private String parentId;
	private String detailName;
	private String searchName;
	private ObjectExpand objectExpand;
	private ObjectExpandField objectExpandField;
	private Map<String, String> expandTypeConstantMap = ExpandConstantInitialization.getExpandTypeConstantMap();
	private Map<String, String> columnTypeConstantMap = ExpandConstantInitialization.getExpandColumnTypeConstantMap();

	/** 获取列表数据 */
	@SuppressWarnings("unchecked")
	public void getObjectExpandJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			params.put("isDelete," + SearchCondition.NOEQUAL, "1");
			if (StrUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, decode(searchName, "UTF-8"));
			}
			objectExpandService.findPagerByHqlConditions(pager, ObjectExpand.class, params);
			if (pager.getResultList().size() < 10) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 10 - listSize; i++) {
					pager.getResultList().add(new ObjectExpand());
				}
			}
			String[] excludes = {""};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取列表数据 */
	public void getObjectExpandDetailJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();

			if (null != parentId && !"".equals(parentId)) {
				params.put("objectExpand.id," + SearchCondition.EQUAL, parentId);

				if (StrUtils.isNotEmpty(detailName)) {
					params.put("displayName," + SearchCondition.ANYLIKE, detailName);
				}

				objectExpandService.findPagerByHqlConditions(pager, ObjectExpandField.class, params);
			}
			String[] excludes = {""};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				objectExpand = objectExpandService.findEntityById(ObjectExpand.class, id);
			} else {
				objectExpand = new ObjectExpand();
				objectExpand.setIsGenerateTable("0");
				objectExpand.setIsReference("0");
				objectExpand.setCode(UUID.randomUUID().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateDetail() {
		try {
			if (StrUtils.isNotEmpty(id)) {
				objectExpandField = objectExpandService.findEntityById(ObjectExpandField.class, id);
			} else {
				objectExpandField = new ObjectExpandField();
				SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSS");
				objectExpandField.setFieldName("fn_" + sdf.format(new Date()));
				if (StrUtils.isNotEmpty(parentId)) {
					objectExpandField.setObjectExpand(objectExpandService.findEntityById(ObjectExpand.class, parentId));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateDetail";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != objectExpand.getId()) {
				isSave = false;
			}
			loadCommonData(objectExpand);
			objectExpand.setIsDelete("0");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			objectExpand.setExpandTableName("EXPANDTABLE_" + sdf.format(new Date()));
			objectExpand = objectExpandService.merge(objectExpand);
			if (isSave) {
				setMessage(objectExpand.getId() + ":" + SAVE_SUCCESS);
			} else {
				setMessage(objectExpand.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage("0:" + SAVE_FAIL);
			} else {
				this.setMessage("0:" + UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public String saveOrUpdateDetail() {
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
				ObjectExpand et = objectExpandService.findEntityById(ObjectExpand.class, objectExpandField.getObjectExpand().getId());
				objectExpandField.setExpandTableName(et.getExpandTableName());
			}

			objectExpandField = objectExpandService.merge(objectExpandField);
			if (null != objectExpandField.getExpandTableName() && !"".equals(objectExpandField.getExpandTableName()) && objectExpandService.checkTableExist(PropertyConfigLoader.dbType, objectExpandField.getExpandTableName())) {
				if (!objectExpandService.checkColumnExist(PropertyConfigLoader.dbType, objectExpandField.getExpandTableName(), objectExpandField.getFieldName())) {
					objectExpandService.addExpandTableField(PropertyConfigLoader.dbType, objectExpandField);
				}
			}
			if (isSave) {
				setMessage(objectExpandField.getId() + ":" + SAVE_SUCCESS);
			} else {
				setMessage(objectExpandField.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage("0:" + SAVE_FAIL);
			} else {
				this.setMessage("0:" + UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 创建数据表 */
	public String createOrUpdateTable() {
		try {
			if (null != id) {
				ObjectExpand objectExpand = objectExpandService.findEntityById(ObjectExpand.class, id);
				if (null == objectExpand) {
					setMessage("类型获取失败!");
					return UPDATE;
				}
				if (null == objectExpand.getObjectExpandFields() || objectExpand.getObjectExpandFields().size() <= 0) {
					setMessage("分类的扩展属性为空,不能生成扩展数据表!");
					return UPDATE;
				}
				if (!objectExpandService.checkTableExist(PropertyConfigLoader.dbType, objectExpand.getExpandTableName())) {
					boolean tag = objectExpandService.createTable(PropertyConfigLoader.dbType, objectExpand);
					if (tag) {
						String hql = "update ObjectExpandField ef set ef.expandTableName = :expandTableName where ef.objectExpand.id = :objectExpandId";
						Map<String, Object> p = new HashMap<String, Object>();
						p.put("expandTableName", objectExpand.getExpandTableName());
						p.put("objectExpandId", objectExpand.getId());
						objectExpandService.batchUpdateByHql(hql, p);
						objectExpand.setIsGenerateTable("1");
						objectExpandService.merge(objectExpand);
						setMessage("数据表创建成功!");
					} else {
						setMessage("数据表创建失败!");
					}
				} else {
					setMessage("数据表已存在!");
				}
			} else {
				setMessage("数据表创建失败!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage(ex.getMessage());
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			ObjectExpand et = objectExpandService.findEntityById(ObjectExpand.class, id);
			if (null != et) {
				if (null != et.getSpecifications() && et.getSpecifications().size() > 0) {
					setMessage(getText("system_objectExpandHasSpec"));
					return UPDATE;
				}
				et.setIsDelete("1");
				objectExpandService.merge(et);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("system_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String deleteDetailById() {
		try {
			ObjectExpandField ef = objectExpandService.findEntityById(ObjectExpandField.class, id);
			if (null != ef) {
				String tableName = ef.getExpandTableName();
				objectExpandService.deleteByEntity(ef);
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

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			List<ObjectExpand> listObjectExpand = objectExpandService.findAllByConditions(ObjectExpand.class, params);
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listObjectExpand.size(); i++) {
				ObjectExpand oe = listObjectExpand.get(i);
				strSb.append("{id:\"");
				strSb.append(oe.getId());
				strSb.append("\",name:\"");
				strSb.append(oe.getName());
				strSb.append("\",open:true,isParent:false}");
				if (i < listObjectExpand.size() - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: findOrgAndUnitTreeToJson @Description: 加载公司和部门的混合树 @param
	 * 设定文件 @return void 返回类型 @throws
	 */
	public void findOrgAndUnitTreeToJson() {
		try {
			String treeType = getRequestParameter("treeType");
			loadOrg(treeId, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadOrg(String nodeId, String nodeTreeType) {
		try {
			List<Organization> orgUnitList = organizationService.findSubOrganizationList(nodeId);
			// orgUnitList =
			// organizationService.findSubOrganizationListNoTenantId(nodeId);
			if (orgUnitList == null) {
				orgUnitList = new LinkedList<Organization>();
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				Organization org = orgUnitList.get(i);

				List<Organization> subList = organizationService.findSubOrganizationList(org.getId());
				// subList =
				// organizationService.findSubOrganizationListNoTenantId(org.getId());
				if (subList != null && subList.size() > 0) {
					strSb.append("{treeId:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getOrgType());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true");

					/*
					 * if(canCheckComp==0){ if(org.getOrgType().equals("C")){
					 * strSb.append(",nocheck:true"); } } if(
					 * org.getOrgType().equals("O") &&
					 * StringUtils.isNotEmpty(unitType)){
					 * if(unitType.equals("XS")){
					 * if(BizConstant.COMMON_ORGUNIT_TYPE_XS_SET.contains(org.
					 * getOrgUnitType())){ strSb.append(",nocheck:false");
					 * }else{ strSb.append(",nocheck:true"); } }else{
					 * if(org.getOrgUnitType().equalsIgnoreCase(unitType)){
					 * strSb.append(",nocheck:false"); }else{
					 * strSb.append(",nocheck:true"); } }
					 * 
					 * }
					 */
				} else {
					strSb.append("{treeId:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getOrgType());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:false");

					/*
					 * if(canCheckComp==0){ if(org.getOrgType().equals("C")){
					 * strSb.append(",nocheck:true"); } } if(
					 * org.getOrgType().equals("O") &&
					 * StringUtils.isNotEmpty(unitType)){
					 * if(unitType.equals("XS")){
					 * if(BizConstant.COMMON_ORGUNIT_TYPE_XS_SET.contains(org.
					 * getOrgUnitType())){ strSb.append(",nocheck:false");
					 * }else{ strSb.append(",nocheck:true"); } }else{
					 * if(org.getOrgUnitType().equalsIgnoreCase(unitType)){
					 * strSb.append(",nocheck:false"); }else{
					 * strSb.append(",nocheck:true"); } }
					 * 
					 * }
					 */
				}

				/*
				 * if(checkedIdSet.contains(treeId)){
				 * strSb.append(",checked:true"); }
				 */

				strSb.append("}");// strSb.append("}");

				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String goChooseObjectExpand() {
		return "goChooseObjectExpand";
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public ObjectExpand getObjectExpand() {
		return objectExpand;
	}

	public void setObjectExpand(ObjectExpand objectExpand) {
		this.objectExpand = objectExpand;
	}

	public Map<String, String> getExpandTypeConstantMap() {
		return expandTypeConstantMap;
	}

	public void setExpandTypeConstantMap(Map<String, String> expandTypeConstantMap) {
		this.expandTypeConstantMap = expandTypeConstantMap;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public ObjectExpandField getObjectExpandField() {
		return objectExpandField;
	}

	public void setObjectExpandField(ObjectExpandField objectExpandField) {
		this.objectExpandField = objectExpandField;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public Map<String, String> getColumnTypeConstantMap() {
		return columnTypeConstantMap;
	}

	public void setColumnTypeConstantMap(Map<String, String> columnTypeConstantMap) {
		this.columnTypeConstantMap = columnTypeConstantMap;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}
}