package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.ContactPersonType;

@Controller
@Scope("prototype")
public class ContactPersonTypeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<ContactPersonType> contactPersonTypeList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			contactPersonTypeList = baseHibernateService.findAllByEntityClass(ContactPersonType.class);
			if(null != contactPersonTypeList && contactPersonTypeList.size()<8){
				int stepCount = 8-contactPersonTypeList.size();
				for(int i=0;i<stepCount;i++){
					contactPersonTypeList.add(new ContactPersonType());
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
					ContactPersonType contactPersonType = new ContactPersonType();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						contactPersonType.setId(csItem[0].toString());
						isSave = false;
					}
					contactPersonType.setIsDefault(csItem[1]);
					contactPersonType.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						contactPersonType.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						contactPersonType.setMemo(csItem[4]);
					}
					loadCommonData(contactPersonType);
					baseHibernateService.merge(contactPersonType);
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

	public List<ContactPersonType> getContactPersonTypeList() {
		return contactPersonTypeList;
	}

	public void setContactPersonTypeList(List<ContactPersonType> contactPersonTypeList) {
		this.contactPersonTypeList = contactPersonTypeList;
	}
}