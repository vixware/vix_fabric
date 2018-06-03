package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.CredentialType;

@Controller
@Scope("prototype")
public class CredentialTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private List<CredentialType> credentialTypeList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			credentialTypeList = baseHibernateService.findAllByEntityClass(CredentialType.class);
			if(null != credentialTypeList && credentialTypeList.size()<8){
				int stepCount = 8-credentialTypeList.size();
				for(int i=0;i<stepCount;i++){
					credentialTypeList.add(new CredentialType());
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
					CredentialType credentialType = new CredentialType();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						credentialType.setId(csItem[0].toString());
						isSave = false;
					}
					credentialType.setIsDefault(csItem[1]);
					credentialType.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						credentialType.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						credentialType.setMemo(csItem[4]);
					}
					loadCommonData(credentialType);
					baseHibernateService.merge(credentialType);
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

	public List<CredentialType> getCredentialTypeList() {
		return credentialTypeList;
	}

	public void setCredentialTypeList(List<CredentialType> credentialTypeList) {
		this.credentialTypeList = credentialTypeList;
	}
}
