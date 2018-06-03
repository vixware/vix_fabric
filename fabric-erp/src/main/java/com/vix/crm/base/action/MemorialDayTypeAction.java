package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.MemorialDayType;

@Controller
@Scope("prototype")
public class MemorialDayTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<MemorialDayType> memorialDayTypeList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			memorialDayTypeList = baseHibernateService.findAllByEntityClass(MemorialDayType.class);
			if(null != memorialDayTypeList && memorialDayTypeList.size()<8){
				int stepCount = 8-memorialDayTypeList.size();
				for(int i=0;i<stepCount;i++){
					memorialDayTypeList.add(new MemorialDayType());
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
					MemorialDayType memorialDayType = new MemorialDayType();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						memorialDayType.setId(csItem[0].toString());
						isSave = false;
					}
					memorialDayType.setIsDefault(csItem[1]);
					memorialDayType.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						memorialDayType.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						memorialDayType.setMemo(csItem[4]);
					}
					loadCommonData(memorialDayType);
					baseHibernateService.merge(memorialDayType);
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

	public List<MemorialDayType> getMemorialDayTypeList() {
		return memorialDayTypeList;
	}

	public void setMemorialDayTypeList(List<MemorialDayType> memorialDayTypeList) {
		this.memorialDayTypeList = memorialDayTypeList;
	}
}
