package com.vix.nvix.customer.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.RelationshipClass;
import com.vix.nvix.common.base.service.IVixntBaseService;

@Controller
@Scope("prototype")
public class NvixRelationshipClassAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IVixntBaseService vixntBaseService;

	private String id;
	private String name;
	private RelationshipClass relationshipClass;
	
	/** 获取列表数据  */
	public void getRelationshipClassJson(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = getPager();
			if(null != name && !"".equals(name)){
				name = decode(name, "UTF-8");
				params.put("name,"+SearchCondition.ANYLIKE, name);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, RelationshipClass.class, params);
			renderDataTable(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StrUtils.isNotEmpty(id) && !id.equals("0")){
				relationshipClass = vixntBaseService.findEntityById(RelationshipClass.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StrUtils.isNotEmpty(relationshipClass.getId())){
				isSave = false;
				relationshipClass.setUpdateTime(new Date());
			}else{
				relationshipClass.setCreateTime(new Date());
				relationshipClass.setUpdateTime(new Date());
			}
			relationshipClass = vixntBaseService.merge(relationshipClass);
			if("1".equals(relationshipClass.getIsDefault())){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("isDefault," + SearchCondition.EQUAL, "1");
				params.put("id," + SearchCondition.NOEQUAL, relationshipClass.getId());
				List<RelationshipClass> relationshipClasses = vixntBaseService.findAllDataByConditions(RelationshipClass.class, params);
				if(relationshipClasses != null && relationshipClasses.size() > 0){
					for (RelationshipClass rc : relationshipClasses) {
						rc.setIsDefault("0");
						rc = vixntBaseService.merge(rc);
					}
				}
			}
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			relationshipClass = vixntBaseService.findEntityById(RelationshipClass.class, id);
			if(null != relationshipClass){
				vixntBaseService.deleteByEntity(relationshipClass);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			renderText("关系等级已使用,不可删除");
		}
		return UPDATE;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RelationshipClass getRelationshipClass() {
		return relationshipClass;
	}

	public void setRelationshipClass(RelationshipClass relationshipClass) {
		this.relationshipClass = relationshipClass;
	}

}
