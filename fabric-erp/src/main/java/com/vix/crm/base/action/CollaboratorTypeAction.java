package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.CollaboratorType;

@Controller
@Scope("prototype")
public class CollaboratorTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<CollaboratorType> collaboratorTypeList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			collaboratorTypeList = baseHibernateService.findAllByEntityClass(CollaboratorType.class);
			if(null != collaboratorTypeList && collaboratorTypeList.size()<8){
				int stepCount = 8-collaboratorTypeList.size();
				for(int i=0;i<stepCount;i++){
					collaboratorTypeList.add(new CollaboratorType());
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
					CollaboratorType collaboratorType = new CollaboratorType();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						collaboratorType.setId(csItem[0].toString());
						isSave = false;
					}
					collaboratorType.setIsDefault(csItem[1]);
					collaboratorType.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						collaboratorType.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						collaboratorType.setMemo(csItem[4]);
					}
					loadCommonData(collaboratorType);
					baseHibernateService.merge(collaboratorType);
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

	public List<CollaboratorType> getCollaboratorTypeList() {
		return collaboratorTypeList;
	}

	public void setCollaboratorTypeList(List<CollaboratorType> collaboratorTypeList) {
		this.collaboratorTypeList = collaboratorTypeList;
	}
}
