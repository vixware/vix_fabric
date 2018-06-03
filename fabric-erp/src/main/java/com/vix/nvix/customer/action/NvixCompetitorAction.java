package com.vix.nvix.customer.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.Competitive;
import com.vix.crm.project.entity.ActionHistory;
import com.vix.crm.project.entity.Competitor;
import com.vix.crm.salechance.entity.SaleChance;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class NvixCompetitorAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private String companyCode;
	private Competitor competitor;
	private String entityName;
	
	public void goListContent(){
		try {
			Pager pager = getPager();
			
			Map<String,Object> params = getParams();
			String companyName = getRequestParameter("companyName");
			if(StrUtils.objectIsNotNull(companyName)){
				companyName = decode(companyName, "UTF-8");
				params.put("companyName,"+SearchCondition.ANYLIKE, companyName);
			}
			String product = getRequestParameter("product");
			if(StrUtils.objectIsNotNull(product)){
				product = decode(product, "UTF-8");
				params.put("competeProduct,"+SearchCondition.ANYLIKE, product);
			}
			
			String customerName = getRequestParameter("customerName");
			if(StrUtils.objectIsNotNull(customerName)){
				customerName = decode(customerName, "UTF-8");
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, customerName);
			}
			baseHibernateService.findPagerByHqlConditions(pager, Competitor.class, params);
			renderDataTable(pager,null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private List<CurrencyType> currencyTypeList;
	private List<Competitive> competitiveList;
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			competitiveList = baseHibernateService.findAllByEntityClassAndAttribute(Competitive.class, "enable", "1");
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				competitor = baseHibernateService.findEntityById(Competitor.class, id);
				if(null != competitor.getCustomerAccount() && StrUtils.isNotEmpty(competitor.getCustomerAccount().getId())){
					saleChanceList = baseHibernateService.findAllByEntityClassAndAttribute(SaleChance.class, "customerAccount.id", competitor.getCustomerAccount().getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 处理修改操作  */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != competitor.getId()){
				isSave = false;
			}else{
				competitor.setCreateTime(new Date());
				loadCommonData(competitor);
			}
			if(null == competitor.getCustomerAccount() || StrUtils.isEmpty(competitor.getCustomerAccount().getId())){
				competitor.setCustomerAccount(null);
			}
			if(null == competitor.getSaleChance() || StrUtils.isEmpty(competitor.getSaleChance().getId())){
				competitor.setSaleChance(null);
			}
			String name = competitor.getCompanyName();
			String py = ChnToPinYin.getPYString(name);
			competitor.setChineseFirstLetter(py.toUpperCase());
			competitor = baseHibernateService.merge(competitor);
			// 增加行动历史和客户更新
			if(competitor.getCustomerAccount() != null && StrUtils.isNotEmpty(competitor.getCustomerAccount().getId())){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, competitor.getCustomerAccount().getId());
				ActionHistory actionHistory = new ActionHistory();
				actionHistory.setSubject("竞争对手");
				actionHistory.setActionDate(new Date());
				actionHistory.setCustomerAccount(customerAccount);
				actionHistory.setCrmProject(competitor.getCrmProject());
				actionHistory.setDescription("竞争对手编辑");
				actionHistory = baseHibernateService.merge(actionHistory);
				customerAccount.setUpdateTime(new Date());
				customerAccount.setStagnateDay(0);
				customerAccount = baseHibernateService.merge(customerAccount);
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
	}
	
	/** 处理删除操作 */
	public void deleteById() {
		try {
			if(StrUtils.isNotEmpty(id)){
				Competitor pb = baseHibernateService.findEntityById(Competitor.class,id);
				if (null != pb) {
					baseHibernateService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				}else{
					renderText(DELETE_FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	private List<SaleChance> saleChanceList;
	public String loadSaleChance(){
		try {
			Map<String,Object> params = getParams();
			if(null != id){
				params.put("customerAccount.id", id);
				saleChanceList = baseHibernateService.findAllByConditions(SaleChance.class,params);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "loadSaleChance";
	}
	

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Competitor getCompetitor() {
		return competitor;
	}

	public void setCompetitor(Competitor competitor) {
		this.competitor = competitor;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<Competitive> getCompetitiveList() {
		return competitiveList;
	}

	public void setCompetitiveList(List<Competitive> competitiveList) {
		this.competitiveList = competitiveList;
	}

	public List<SaleChance> getSaleChanceList() {
		return saleChanceList;
	}

	public void setSaleChanceList(List<SaleChance> saleChanceList) {
		this.saleChanceList = saleChanceList;
	}

}