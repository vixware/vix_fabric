package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.ComplaintType;

@Controller
@Scope("prototype")
public class ComplaintTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<ComplaintType> complaintTypeList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			complaintTypeList = baseHibernateService.findAllByEntityClass(ComplaintType.class);
			if(null != complaintTypeList && complaintTypeList.size()<8){
				int stepCount = 8-complaintTypeList.size();
				for(int i=0;i<stepCount;i++){
					complaintTypeList.add(new ComplaintType());
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
					ComplaintType complaintType = new ComplaintType();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						complaintType.setId(csItem[0].toString());
						isSave = false;
					}
					complaintType.setIsDefault(csItem[1]);
					complaintType.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						complaintType.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						complaintType.setMemo(csItem[4]);
					}
					loadCommonData(complaintType);
					baseHibernateService.merge(complaintType);
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

	public List<ComplaintType> getComplaintTypeList() {
		return complaintTypeList;
	}

	public void setComplaintTypeList(List<ComplaintType> complaintTypeList) {
		this.complaintTypeList = complaintTypeList;
	}
}
