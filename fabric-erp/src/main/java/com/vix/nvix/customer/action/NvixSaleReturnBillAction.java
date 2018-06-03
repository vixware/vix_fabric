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
import com.vix.core.web.Pager;
import com.vix.crm.project.entity.ActionHistory;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.sales.refund.entity.SaleReturnBill;

@Controller
@Scope("prototype")
public class NvixSaleReturnBillAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private SaleReturnBill saleReturnBill;
	private String entityName;
	
	public void goListContent(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			String customerName = getRequestParameter("customerName");
			if(StrUtils.objectIsNotNull(customerName)){
				customerName = decode(customerName, "UTF-8");
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, customerName);
			}
			if(StrUtils.objectIsNotNull(name)){
				name = decode(name, "UTF-8");
				params.put("name,"+SearchCondition.ANYLIKE, name);
			}
			pager = baseHibernateService.findPagerByHqlConditions(getPager(), SaleReturnBill.class, params);
			renderDataTable(pager,null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	private List<CurrencyType> currencyTypeList;
	public String goSaveOrUpdate() {
		try {
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			if (null != id && !"".equals(id)) {
				saleReturnBill = baseHibernateService.findEntityById(SaleReturnBill.class, id);
			} else {
				saleReturnBill = new SaleReturnBill();
				saleReturnBill.setCreateTime(new Date());
				saleReturnBill.setRbDate(new Date());
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
			if (null != saleReturnBill.getId()) {
				isSave = false;
			} else {
				saleReturnBill.setCreateTime(new Date());
				loadCommonData(saleReturnBill);
			}
			saleReturnBill = baseHibernateService.merge(saleReturnBill);
			// 增加行动历史和客户更新
			if(saleReturnBill.getCustomerAccount() != null && StrUtils.isNotEmpty(saleReturnBill.getCustomerAccount().getId())){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, saleReturnBill.getCustomerAccount().getId());
				ActionHistory actionHistory = new ActionHistory();
				actionHistory.setSubject("退款记录");
				actionHistory.setActionDate(new Date());
				actionHistory.setCustomerAccount(customerAccount);
				//actionHistory.setCrmProject(saleReturnBill.getCrmProject());
				actionHistory.setDescription("退款记录编辑");
				actionHistory = baseHibernateService.merge(actionHistory);
				customerAccount.setUpdateTime(new Date());
				customerAccount.setStagnateDay(0);
				customerAccount = baseHibernateService.merge(customerAccount);
			}
			if (isSave) {
				renderText(saleReturnBill.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText(saleReturnBill.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0" + SAVE_FAIL);
			} else {
				renderText("0" + UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	
	/** 处理删除操作 */
	public void deleteById() {
		try {
			SaleReturnBill pb = baseHibernateService.findEntityById(SaleReturnBill.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
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

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public SaleReturnBill getSaleReturnBill() {
		return saleReturnBill;
	}

	public void setSaleReturnBill(SaleReturnBill saleReturnBill) {
		this.saleReturnBill = saleReturnBill;
	}
}