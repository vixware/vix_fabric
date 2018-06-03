package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.Industry;

@Controller
@Scope("prototype")
public class IndustryAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<Industry> industryList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			industryList = baseHibernateService.findAllByEntityClass(Industry.class);
			if(null != industryList && industryList.size()<8){
				int stepCount = 24-industryList.size();
				for(int i=0;i<stepCount;i++){
					industryList.add(new Industry());
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
					Industry industry = new Industry();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						industry.setId(csItem[0].toString());
						isSave = false;
					}
					industry.setIsDefault(csItem[1]);
					industry.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						industry.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						industry.setMemo(csItem[4]);
					}
					loadCommonData(industry);
					baseHibernateService.merge(industry);
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

	public List<Industry> getIndustryList() {
		return industryList;
	}

	public void setIndustryList(List<Industry> industryList) {
		this.industryList = industryList;
	}
}
