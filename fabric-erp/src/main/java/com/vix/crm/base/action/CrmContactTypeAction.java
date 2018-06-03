package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.CrmContactType;

@Controller
@Scope("prototype")
public class CrmContactTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private List<CrmContactType> crmContactTypeList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			crmContactTypeList = baseHibernateService.findAllByEntityClass(CrmContactType.class);
			if(null != crmContactTypeList && crmContactTypeList.size()<8){
				int stepCount = 8-crmContactTypeList.size();
				for(int i=0;i<stepCount;i++){
					crmContactTypeList.add(new CrmContactType());
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
					CrmContactType crmContactType = new CrmContactType();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						crmContactType.setId(csItem[0]);
						isSave = false;
					}
					crmContactType.setIsDefault(csItem[1]);
					crmContactType.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						crmContactType.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						crmContactType.setMemo(csItem[4]);
					}
					loadCommonData(crmContactType);
					baseHibernateService.merge(crmContactType);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CrmContactType> getCrmContactTypeList() {
		return crmContactTypeList;
	}

	public void setCrmContactTypeList(List<CrmContactType> crmContactTypeList) {
		this.crmContactTypeList = crmContactTypeList;
	}

}
