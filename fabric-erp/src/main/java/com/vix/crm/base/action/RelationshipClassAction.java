package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.RelationshipClass;

@Controller
@Scope("prototype")
public class RelationshipClassAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<RelationshipClass> relationshipClassList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			relationshipClassList = baseHibernateService.findAllByEntityClass(RelationshipClass.class);
			if(null != relationshipClassList && relationshipClassList.size()<8){
				int stepCount = 8-relationshipClassList.size();
				for(int i=0;i<stepCount;i++){
					relationshipClassList.add(new RelationshipClass());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			String data = getRequestParameter("data");
			if(null != data && !"".equals(data)){
				String[] cs = data.split(",");
				for(String s :cs){
					String[] csItem = s.split(":");
					RelationshipClass relationshipClass = new RelationshipClass();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						relationshipClass.setId(csItem[0]);
						isSave = false;
					}
					relationshipClass.setIsDefault(csItem[1]);
					relationshipClass.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						relationshipClass.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						relationshipClass.setMemo(csItem[4]);
					}
					loadCommonData(relationshipClass);
					baseHibernateService.merge(relationshipClass);
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<RelationshipClass> getRelationshipClassList() {
		return relationshipClassList;
	}

	public void setRelationshipClassList(List<RelationshipClass> relationshipClassList) {
		this.relationshipClassList = relationshipClassList;
	}
}
