package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.DealResult;

@Controller
@Scope("prototype")
public class DealResultAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<DealResult> dealResultList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			dealResultList = baseHibernateService.findAllByEntityClass(DealResult.class);
			if(null != dealResultList && dealResultList.size()<8){
				int stepCount = 8-dealResultList.size();
				for(int i=0;i<stepCount;i++){
					dealResultList.add(new DealResult());
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
					DealResult dealResult = new DealResult();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						dealResult.setId(csItem[0].toString());
						isSave = false;
					}
					dealResult.setIsDefault(csItem[1]);
					dealResult.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						dealResult.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						dealResult.setMemo(csItem[4]);
					}
					loadCommonData(dealResult);
					baseHibernateService.merge(dealResult);
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

	public List<DealResult> getDealResultList() {
		return dealResultList;
	}

	public void setDealResultList(List<DealResult> dealResultList) {
		this.dealResultList = dealResultList;
	}
}
