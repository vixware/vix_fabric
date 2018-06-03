package com.vix.crm.workbench.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.workbench.entity.InnerBulletinCategory;

@Controller
@Scope("prototype")
public class InnerBulletinCategoryAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<InnerBulletinCategory> innerBulletinCategoryList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			innerBulletinCategoryList = baseHibernateService.findAllByEntityClass(InnerBulletinCategory.class);
			if(null != innerBulletinCategoryList && innerBulletinCategoryList.size()<8){
				int stepCount = 8-innerBulletinCategoryList.size();
				for(int i=0;i<stepCount;i++){
					innerBulletinCategoryList.add(new InnerBulletinCategory());
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
					InnerBulletinCategory innerBulletinCategory = new InnerBulletinCategory();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						innerBulletinCategory.setId(csItem[0]);
						isSave = false;
					}
					innerBulletinCategory.setIsDefault(csItem[1]);
					innerBulletinCategory.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						innerBulletinCategory.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						innerBulletinCategory.setMemo(csItem[4]);
					}
					loadCommonData(innerBulletinCategory);
					baseHibernateService.merge(innerBulletinCategory);
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

	public List<InnerBulletinCategory> getInnerBulletinCategoryList() {
		return innerBulletinCategoryList;
	}

	public void setInnerBulletinCategoryList(List<InnerBulletinCategory> innerBulletinCategoryList) {
		this.innerBulletinCategoryList = innerBulletinCategoryList;
	}
}
