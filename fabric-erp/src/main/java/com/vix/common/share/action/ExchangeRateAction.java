package com.vix.common.share.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.ExchangeRate;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;

@Controller
@Scope("prototype")
public class ExchangeRateAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private ExchangeRate exchangeRate;
	private String companyId;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), ExchangeRate.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	private List<CurrencyType> listCurrencyType;
	public String goSaveOrUpdate() {
		try {
			listCurrencyType = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
				exchangeRate = baseHibernateService.findEntityById(ExchangeRate.class,id);
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
			if(null != exchangeRate.getId()){
				isSave = false;
			}
			if(null == exchangeRate || null == exchangeRate.getCurrentCurrencyType()
					|| exchangeRate.getCurrentCurrencyType().getId().equals("0")){//|| exchangeRate.getCurrentCurrencyType().getId() <= 0){
				exchangeRate.setCurrentCurrencyType(null);
			}
			if(null == exchangeRate || null == exchangeRate.getForeignCurrencyType()
					|| exchangeRate.getForeignCurrencyType().getId().equals("0")){//|| exchangeRate.getForeignCurrencyType().getId() <= 0){
				exchangeRate.setForeignCurrencyType(null);
			}
			if(StringUtils.isEmpty(companyId) || companyId.equals("0")){//if(null == companyId || companyId.longValue() <= 0){
				Employee emp = getEmployee();
				if(null != emp && null != emp.getCompanyCode() && !"".equals(emp.getCompanyCode())){
					exchangeRate.setCompanyCode(emp.getCompanyCode());
				}
			}else{
				OrganizationUnit ou = baseHibernateService.findEntityById(OrganizationUnit.class, companyId);
				if(null != ou && null != ou.getCompanyCode() && !"".equals(ou.getCompanyCode())){
					exchangeRate.setCompanyCode(ou.getCompanyCode());
				}
			}
			exchangeRate = baseHibernateService.merge(exchangeRate);
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(SAVE_SUCCESS);
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
			ExchangeRate pb = baseHibernateService.findEntityById(ExchangeRate.class,id);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CurrencyType> getListCurrencyType() {
		return listCurrencyType;
	}

	public void setListCurrencyType(List<CurrencyType> listCurrencyType) {
		this.listCurrencyType = listCurrencyType;
	}

	public ExchangeRate getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(ExchangeRate exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
