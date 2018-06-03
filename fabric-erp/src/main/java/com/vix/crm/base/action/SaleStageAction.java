package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.SaleStage;
import com.vix.crm.base.entity.SaleType;

@Controller
@Scope("prototype")
public class SaleStageAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private List<SaleStage> saleStageList;
	private List<SaleType> saleTypeList;
	/** 获取列表数据  */
	public String goListContent(){
		try {
			saleStageList = baseHibernateService.findAllByEntityClass(SaleStage.class);
			if(null != saleStageList && saleStageList.size()<8){
				int stepCount = 8-saleStageList.size();
				for(int i=0;i<stepCount;i++){
					saleStageList.add(new SaleStage());
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
					SaleStage saleStage = new SaleStage();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						saleStage.setId(csItem[0]);
						isSave = false;
					}
					saleStage.setIsDefault(csItem[1]);
					saleStage.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						saleStage.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						saleStage.setMemo(csItem[4]);
					}
					loadCommonData(saleStage);
					baseHibernateService.merge(saleStage);
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
	/**  销售机会类型*/
	
	public String goSaleTypeList(){
		return "goSaleTypeList";
	}
	/** 获取列表数据  */
	public String goSaleTypeListContent(){
		try {
			saleTypeList = baseHibernateService.findAllByEntityClass(SaleType.class);
			if(null != saleTypeList && saleTypeList.size()<8){
				int stepCount = 8-saleTypeList.size();
				for(int i=0;i<stepCount;i++){
					saleTypeList.add(new SaleType());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goSaleTypeListContent";
	}

	/** 处理修改操作  */
	public String saveOrUpdateSaleType() {
		boolean isSave = true;
		try {
			String data = getRequestParameter("data");
			if(null != data && !"".equals(data)){
				String[] cs = data.split(",");
				for(String s :cs){
					String[] csItem = s.split(":");
					SaleType saleType = new SaleType();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						saleType.setId(csItem[0]);
						isSave = false;
					}
					saleType.setIsDefault(csItem[1]);
					saleType.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						saleType.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						saleType.setMemo(csItem[4]);
					}
					loadCommonData(saleType);
					baseHibernateService.merge(saleType);
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

	public List<SaleStage> getSaleStageList() {
		return saleStageList;
	}

	public void setSaleStageList(List<SaleStage> saleStageList) {
		this.saleStageList = saleStageList;
	}

	public List<SaleType> getSaleTypeList() {
		return saleTypeList;
	}

	public void setSaleTypeList(List<SaleType> saleTypeList) {
		this.saleTypeList = saleTypeList;
	}
	
}
