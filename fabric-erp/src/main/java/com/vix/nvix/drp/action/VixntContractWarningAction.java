package com.vix.nvix.drp.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.contract.mamanger.entity.ContractWarning;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
@Controller
@Scope("prototype")
public class VixntContractWarningAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchName;
	private String id;
	private ContractWarning contractWarning;
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("warnningTime");
			if(StringUtils.isNotEmpty(searchName)){
				params.put("contractName,"+SearchCondition.ANYLIKE, searchName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, ContractWarning.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				contractWarning = vixntBaseService.findEntityById(ContractWarning.class, id);
				if(contractWarning != null){
					vixntBaseService.deleteByEntity(contractWarning);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public ContractWarning getContractWarning() {
		return contractWarning;
	}

	public void setContractWarning(ContractWarning contractWarning) {
		this.contractWarning = contractWarning;
	}
}
