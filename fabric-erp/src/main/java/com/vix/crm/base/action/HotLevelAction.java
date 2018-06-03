package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.HotLevel;

@Controller
@Scope("prototype")
public class HotLevelAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<HotLevel> hotLevelList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			hotLevelList = baseHibernateService.findAllByEntityClass(HotLevel.class);
			if(null != hotLevelList && hotLevelList.size()<8){
				int stepCount = 8-hotLevelList.size();
				for(int i=0;i<stepCount;i++){
					hotLevelList.add(new HotLevel());
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
					HotLevel hotLevel = new HotLevel();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						hotLevel.setId(csItem[0].toString());
						isSave = false;
					}
					hotLevel.setIsDefault(csItem[1]);
					hotLevel.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						hotLevel.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						hotLevel.setMemo(csItem[4]);
					}
					loadCommonData(hotLevel);
					baseHibernateService.merge(hotLevel);
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

	public List<HotLevel> getHotLevelList() {
		return hotLevelList;
	}

	public void setHotLevelList(List<HotLevel> hotLevelList) {
		this.hotLevelList = hotLevelList;
	}
}
