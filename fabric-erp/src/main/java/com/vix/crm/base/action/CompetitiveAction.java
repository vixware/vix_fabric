package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.Competitive;

@Controller
@Scope("prototype")
public class CompetitiveAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<Competitive> competitiveList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			competitiveList = baseHibernateService.findAllByEntityClass(Competitive.class);
			if(null != competitiveList && competitiveList.size()<8){
				int stepCount = 8-competitiveList.size();
				for(int i=0;i<stepCount;i++){
					competitiveList.add(new Competitive());
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
					Competitive competitive = new Competitive();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						competitive.setId(csItem[0].toString());
						isSave = false;
					}
					competitive.setIsDefault(csItem[1]);
					competitive.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						competitive.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						competitive.setMemo(csItem[4]);
					}
					loadCommonData(competitive);
					baseHibernateService.merge(competitive);
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

	public List<Competitive> getCompetitiveList() {
		return competitiveList;
	}

	public void setCompetitiveList(List<Competitive> competitiveList) {
		this.competitiveList = competitiveList;
	}
}
