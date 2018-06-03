/**
 * 
 */
package com.vix.nvix.common.base.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;

/**
 * @author Bluesnow
 * 2016年8月13日
 * 
 * 币种管理
 */
@Controller
@Scope("prototype")
public class NvixntCurrencyTypeAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String defaultValue;
	private CurrencyType currencyType;
	private String companyId;
	
	/** 获取列表数据  */
	@SuppressWarnings("unchecked")
	public void getCurrencyTypeJson(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = getPager();
			if (null != defaultValue && !"".equals(defaultValue)) {
				defaultValue = decode(defaultValue, "UTF-8");
				params.put("code," + SearchCondition.ANYLIKE, defaultValue);
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, CurrencyType.class, params);
			if (pager.getResultList().size() < 10) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 10 - listSize; i++) {
					pager.getResultList().add(new CurrencyType());
				}
			}
			String[] excludes = {""};
			renderDataTable(pager, excludes);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("")){
				currencyType = baseHibernateService.findEntityById(CurrencyType.class,id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != currencyType.getId()){
				isSave = false;
			}
			if(StringUtils.isEmpty(id) || id.equals("")){
				Employee emp = getEmployee();
				if(null != emp && null != emp.getCompanyCode() && !"".equals(emp.getCompanyCode())){
					currencyType.setCompanyCode(emp.getCompanyCode());
				}
			}else{
				OrganizationUnit ou = baseHibernateService.findEntityById(OrganizationUnit.class, companyId);
				if(null != ou && null != ou.getCompanyCode() && !"".equals(ou.getCompanyCode())){
					currencyType.setCompanyCode(ou.getCompanyCode());
				}
			}
			currencyType = baseHibernateService.merge(currencyType);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			CurrencyType pb = baseHibernateService.findEntityById(CurrencyType.class,id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}