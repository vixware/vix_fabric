package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.SaleChanceStatus;

@Controller
@Scope("prototype")
public class SaleChanceStatusAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<SaleChanceStatus> saleChanceStatusList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			saleChanceStatusList = baseHibernateService.findAllByEntityClass(SaleChanceStatus.class);
			if(null != saleChanceStatusList && saleChanceStatusList.size()<8){
				int stepCount = 8-saleChanceStatusList.size();
				for(int i=0;i<stepCount;i++){
					saleChanceStatusList.add(new SaleChanceStatus());
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
					SaleChanceStatus saleChanceStatus = new SaleChanceStatus();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						saleChanceStatus.setId(csItem[0]);
						isSave = false;
					}
					saleChanceStatus.setIsDefault(csItem[1]);
					saleChanceStatus.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						saleChanceStatus.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						saleChanceStatus.setMemo(csItem[4]);
					}
					loadCommonData(saleChanceStatus);
					baseHibernateService.merge(saleChanceStatus);
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

	public List<SaleChanceStatus> getSaleChanceStatusList() {
		return saleChanceStatusList;
	}

	public void setSaleChanceStatusList(List<SaleChanceStatus> saleChanceStatusList) {
		this.saleChanceStatusList = saleChanceStatusList;
	}
}
