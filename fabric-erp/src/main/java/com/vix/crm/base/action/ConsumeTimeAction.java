package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.ConsumeTime;

@Controller
@Scope("prototype")
public class ConsumeTimeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<ConsumeTime> consumeTimeList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			consumeTimeList = baseHibernateService.findAllByEntityClass(ConsumeTime.class);
			if(null != consumeTimeList && consumeTimeList.size()<8){
				int stepCount = 8-consumeTimeList.size();
				for(int i=0;i<stepCount;i++){
					consumeTimeList.add(new ConsumeTime());
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
					ConsumeTime consumeTime = new ConsumeTime();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						consumeTime.setId(csItem[0].toString());
						isSave = false;
					}
					consumeTime.setIsDefault(csItem[1]);
					consumeTime.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						consumeTime.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						consumeTime.setMemo(csItem[4]);
					}
					loadCommonData(consumeTime);
					baseHibernateService.merge(consumeTime);
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

	public List<ConsumeTime> getConsumeTimeList() {
		return consumeTimeList;
	}

	public void setConsumeTimeList(List<ConsumeTime> consumeTimeList) {
		this.consumeTimeList = consumeTimeList;
	}
}
