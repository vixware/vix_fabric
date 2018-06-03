/**
 * 
 */
package com.vix.nvix.common.base.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.ExchangeRate;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;

/**
 * @author Bluesnow
 * 2016年8月13日
 * 
 * 汇率
 */
@Controller
@Scope("prototype")
public class NvixntExchangeRateAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	
	
	private String id;
	private String defaultValue;
	private ExchangeRate exchangeRate;
	private String companyId;
	
	/** 获取列表数据  */
	@SuppressWarnings("unchecked")
	public void getExchangeRateJson(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = getPager();
			
			if (null != defaultValue && !"".equals(defaultValue)) {
				params.put("currentCurrencyType.name,"+SearchCondition.ANYLIKE, decode(defaultValue, "UTF-8"));
			}
			
			vixntBaseService.findPagerByHqlConditions(pager, ExchangeRate.class, params);
			if (pager.getResultList().size() < 10) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 10 - listSize; i++) {
					pager.getResultList().add(new ExchangeRate());
				}
			}
			String[] excludes = {""};
			renderDataTable(pager, excludes);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	private List<CurrencyType> listCurrencyType;
	public String goSaveOrUpdate() {
		try {
			listCurrencyType = vixntBaseService.findAllByEntityClass(CurrencyType.class);
			if(StringUtils.isNotEmpty(id) && !id.equals("")){
				exchangeRate = vixntBaseService.findEntityById(ExchangeRate.class,id);
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
			/**if(null == exchangeRate || null == exchangeRate.getCurrentCurrencyType() || exchangeRate.getCurrentCurrencyType().getId().equals("")){
				exchangeRate.setCurrentCurrencyType(null);
			}
			if(null == exchangeRate || null == exchangeRate.getForeignCurrencyType() || exchangeRate.getForeignCurrencyType().getId().equals("")){
				exchangeRate.setForeignCurrencyType(null);
			}
			*/
			if(StringUtils.isEmpty(companyId) || companyId.equals("")){
				Employee emp = getEmployee();
				if(null != emp && null != emp.getCompanyCode() && !"".equals(emp.getCompanyCode())){
					exchangeRate.setCompanyCode(emp.getCompanyCode());
				}
			}else{
				OrganizationUnit ou = vixntBaseService.findEntityById(OrganizationUnit.class, companyId);
				if(null != ou && null != ou.getCompanyCode() && !"".equals(ou.getCompanyCode())){
					exchangeRate.setCompanyCode(ou.getCompanyCode());
				}
			}
			
			String[] attrArrays ={"currentCurrencyType","foreignCurrencyType"};
			checkEntityNullValue(exchangeRate,attrArrays);
			
			exchangeRate = vixntBaseService.merge(exchangeRate);
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
			ExchangeRate pb = vixntBaseService.findEntityById(ExchangeRate.class,id);
			if(null != pb){
				vixntBaseService.deleteByEntity(pb);
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

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}