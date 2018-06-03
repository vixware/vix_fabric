package com.vix.crm.market.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.market.entity.PrintedMatterUnit;

@Controller
@Scope("prototype")
public class PrintedMatterUnitAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<PrintedMatterUnit> printedMatterUnitList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			printedMatterUnitList = baseHibernateService.findAllByEntityClass(PrintedMatterUnit.class);
			if(null != printedMatterUnitList && printedMatterUnitList.size()<8){
				int stepCount = 8-printedMatterUnitList.size();
				for(int i=0;i<stepCount;i++){
					printedMatterUnitList.add(new PrintedMatterUnit());
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
					PrintedMatterUnit printedMatterUnit = new PrintedMatterUnit();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						printedMatterUnit.setId(csItem[0]);
						isSave = false;
					}
					printedMatterUnit.setIsDefault(csItem[1]);
					printedMatterUnit.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						printedMatterUnit.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						printedMatterUnit.setMemo(csItem[4]);
					}
					loadCommonData(printedMatterUnit);
					baseHibernateService.merge(printedMatterUnit);
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

	public List<PrintedMatterUnit> getPrintedMatterUnitList() {
		return printedMatterUnitList;
	}

	public void setPrintedMatterUnitList(List<PrintedMatterUnit> printedMatterUnitList) {
		this.printedMatterUnitList = printedMatterUnitList;
	}
}
