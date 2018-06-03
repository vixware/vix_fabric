package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.SaleActivity;

@Controller
@Scope("prototype")
public class SaleActivityAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private List<SaleActivity> saleActivityList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			saleActivityList = baseHibernateService.findAllByEntityClass(SaleActivity.class);
			if(null != saleActivityList && saleActivityList.size()<8){
				int stepCount = 8-saleActivityList.size();
				for(int i=0;i<stepCount;i++){
					saleActivityList.add(new SaleActivity());
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
					SaleActivity saleActivity = new SaleActivity();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						saleActivity.setId(csItem[0]);
						isSave = false;
					}
					saleActivity.setIsDefault(csItem[1]);
					saleActivity.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						saleActivity.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						saleActivity.setMemo(csItem[4]);
					}
					loadCommonData(saleActivity);
					baseHibernateService.merge(saleActivity);
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

	public List<SaleActivity> getSaleActivityList() {
		return saleActivityList;
	}

	public void setSaleActivityList(List<SaleActivity> saleActivityList) {
		this.saleActivityList = saleActivityList;
	}

	
}
