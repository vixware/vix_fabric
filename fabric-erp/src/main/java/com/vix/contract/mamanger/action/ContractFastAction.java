package com.vix.contract.mamanger.action;


import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.contract.mamanger.controller.ContractController;
import com.vix.contract.mamanger.entity.Contract;
import com.vix.core.web.Pager;

/**
 * 快速新增合同
 */
@Controller
@Scope("prototype")
public class ContractFastAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ContractController.class);
	/** 注入service */
	public Integer contractType;
	private String pageNo;
	private Contract contract;
	private String id;
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	/**
	 * 跳转到快速新增合同
	 */
	public String goSaveOrUpdateContractFast() {
		try {
			if(null != id && !"".equals(id)){
				contract = baseHibernateService.findEntityById(Contract.class,id);
			}else{
				contract = new Contract();
				contract.setContractType(contractType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateContractFast";
	}
	
	/**
	 * 保存合同
	 * 
	 * @return
	 */
	public String saveOrUpdateContractFast() {
		boolean isSave = true;
		try {
			if(null != contract.getId()){
				isSave = false;
			}
			contract = baseHibernateService.merge(contract);
			initEntityBaseController.initEntityBaseAttribute(contract);
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
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
	
	
	/**
	 * 跳转到快速新增合同
	 */
	public String goSaveOrUpdatePurchase() {
		try {
			if(null != id && !"".equals(id)){
				contract = baseHibernateService.findEntityById(Contract.class,id);
			}else{
				contract = new Contract();
				contract.setContractType(contractType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdatePurchase";
	}
	
	/**
	 * 保存合同
	 * 
	 * @return
	 */
	public String saveOrUpdatePurchase() {
		boolean isSave = true;
		try {
			if(null != contract.getId()){
				isSave = false;
			}
			contract = baseHibernateService.merge(contract);
			initEntityBaseController.initEntityBaseAttribute(contract);
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
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
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), Contract.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	

	public String addAttachFile() {
		return "addAttachFile";
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(
			InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

	public Integer getContractType() {
		return contractType;
	}

	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}

}
