package com.vix.system.action.expand;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpand;
import com.vix.core.utils.PropertyConfigLoader;
import com.vix.core.web.Pager;
import com.vix.system.expand.constant.ExpandConstantInitialization;
import com.vix.system.expand.service.IObjectExpandService;

/**
 * 扩展表类型
 */
@Controller
@Scope("prototype")
public class ObjectExpandAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IObjectExpandService objectExpandService;

	private String id;
	private String ids;
	private ObjectExpand objectExpand;
	private Map<String,String> expandTypeConstantMap = ExpandConstantInitialization.getExpandTypeConstantMap();
	
	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			params.put("isDelete,"+SearchCondition.NOEQUAL, "1");
			Pager pager = objectExpandService.findPagerByHqlConditions(getPager(), ObjectExpand.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 获取列表数据 */
	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			params.put("isDelete,"+SearchCondition.NOEQUAL, "1");
			getPager().setPageSize(10);
			Pager pager = objectExpandService.findPagerByHqlConditions(getPager(), ObjectExpand.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				objectExpand = objectExpandService.findEntityById(ObjectExpand.class, id);
			}else{
				objectExpand = new ObjectExpand();
				loadCommonData(objectExpand);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
				objectExpand.setExpandTableName("EXPANDTABLE_"+sdf.format(new Date()));
				objectExpand.setIsTemp("1");
				objectExpand.setCode(UUID.randomUUID().toString());
				objectExpand = objectExpandService.merge(objectExpand);
//				if(!objectExpandService.checkTableExist(PropertyConfigLoader.dbType, objectExpand.getExpandTableName())){
//					boolean tag = objectExpandService.createTable(PropertyConfigLoader.dbType, objectExpand);
//					if(tag){
//						objectExpand.setIsGenerateTable("1");
//						objectExpandService.merge(objectExpand);
//					}
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdateInner() {
		try {
			objectExpand.setIsTemp("0");
			objectExpand.setIsDelete("0");
			objectExpand = objectExpandService.merge(objectExpand);
			setMessage(objectExpand.getId().toString().trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}
	
	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != objectExpand.getId()) {
				isSave = false;
			}
			objectExpand.setIsTemp("0");
			objectExpand.setIsDelete("0");
			objectExpand = objectExpandService.merge(objectExpand);
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
	
	/** 创建数据表  */
	public String createOrUpdateTable(){
		try {
			if(null != id ){
				ObjectExpand objectExpand = objectExpandService.findEntityById(ObjectExpand.class, id);
				if(null == objectExpand ){
					setMessage("类型获取失败!");
					return UPDATE;
				}
				if(null == objectExpand.getObjectExpandFields() || objectExpand.getObjectExpandFields().size()<=0){
					setMessage("分类的扩展属性为空,不能生成扩展数据表!");
					return UPDATE;
				}
				if(!objectExpandService.checkTableExist(PropertyConfigLoader.dbType, objectExpand.getExpandTableName())){
					boolean tag = objectExpandService.createTable(PropertyConfigLoader.dbType, objectExpand);
					if(tag){
						String hql = "update ObjectExpandField ef set ef.expandTableName = :expandTableName where ef.objectExpand.id = :objectExpandId";
						Map<String,Object> p = new HashMap<String,Object>();
						p.put("expandTableName", objectExpand.getExpandTableName());
						p.put("objectExpandId",objectExpand.getId());
						objectExpandService.batchUpdateByHql(hql,p);
						objectExpand.setIsGenerateTable("1");
						objectExpandService.merge(objectExpand);
						setMessage("数据表创建成功!");
					}else{
						setMessage("数据表创建失败!");
					}
				}else{
					setMessage("数据表已存在!");
				}
			}else{
				setMessage("数据表创建失败!");
			}
		}catch(Exception ex){
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
				if(null != et.getSpecifications() && et.getSpecifications().size() > 0){
					setMessage(getText("system_objectExpandHasSpec"));
					return UPDATE;
				}
				et.setIsDelete("1");
//				objectExpandService.deleteByAttribute(ObjectExpandField.class, "objectExpand.id", id);
//				if(objectExpandService.checkTableExist(PropertyConfigLoader.dbType, et.getExpandTableName())){
//					objectExpandService.dropExpandTable(PropertyConfigLoader.dbType, et.getExpandTableName());
//				}
//				objectExpandService.deleteByEntity(et);
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
	
	/** 树形结构JSON */
	public void findTreeToJson(){
		try{
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			List<ObjectExpand> listObjectExpand = objectExpandService.findAllByConditions(ObjectExpand.class, params);
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for(int i =0;i<listObjectExpand.size();i++){
				ObjectExpand oe = listObjectExpand.get(i);
				strSb.append("{id:\"");
				strSb.append(oe.getId());
				strSb.append("\",name:\"");
				strSb.append(oe.getName());
				strSb.append("\",open:true,isParent:false}");
				if(i < listObjectExpand.size()-1){
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public String goChooseObjectExpand() {
		return "goChooseObjectExpand";
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
 
}