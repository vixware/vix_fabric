package com.vix.sales.commission.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.sales.commission.entity.CommissionPlan;
import com.vix.sales.commission.entity.CommissionResult;
import com.vix.sales.delivery.entity.SaleReturnForm;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

@Controller
@Scope("prototype")
public class CommissionResultAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private CommissionResult commissionResult;
	private String pageNo;


	//销售退货单
	private SaleReturnForm saleReturnForm;
	//销售单
	private SalesOrder salesOrder;
	//销售员
	private Employee salePerson;
	//销售组织
	private OrganizationUnit saleOrg;
	//佣金计划
	private CommissionPlan commissionPlan;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			baseHibernateService.findPagerByHqlConditions(getPager(),
					CommissionResult.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 选择计算条件 */
	public String goChooseCondition() {
		return "chooseCondition";
	}

	public String calculateSaleReturnBill() {
		//初始化总金额
		Double count = 0.00;
		//临时变量
		Double temp = 0.00;
		try {
			//封装查询条件
			Map<String, Object> param = getParams();
			String startTime = getRequestParameter("startTime");
			String endTime = getRequestParameter("endTime");
			if(null != startTime && !"".equals(startTime) && null != endTime && !"".equals(endTime)){
				param.put("startTime", sdf.parse(startTime));
				param.put("endTime", sdf.parse(endTime));
			}
			param.put("isTemp," + SearchCondition.NOEQUAL, "1");
			param.put("isDelete," + SearchCondition.NOEQUAL, "1");
			String salePersonId = getRequestParameter("salePersonId");
			if(null != salePersonId && !"".equals(salePersonId)){
				//查询销售员
				salePerson = baseHibernateService.findEntityById(Employee.class, salePersonId);
				if(null != salePerson && !"1".equals(salePerson.getEmployeeType())){
					//获取销售组织
					saleOrg = salePerson.getOrganizationUnit();
					//获取销售员级别
					
					//获取该级别下的佣金计划
					commissionPlan = baseHibernateService.findEntityByAttribute(CommissionPlan.class, "saleOrg", saleOrg);
					//佣金计算明细
					if(null != commissionPlan || commissionPlan.getCommissionPlanItems().size() <= 0){
						
					}else {
						
					}
					//获取该销售员的销售单
					List<SalesOrder> salesOrderList = baseHibernateService.findAllByConditions(SalesOrder.class, param);
					for(SalesOrder so : salesOrderList){
						//判断该销售单是否冻结,是否退货，是否审批通过，
						if("1".equals(so.getIsFreeze()) || "1".equals(salesOrder.getIsSaleReturnBillCalculation()) || "2".equals(salesOrder.getIsComplate())){
							continue;
						}
						//获取该销售单的类型
						String type = so.getType();
						String fromType = so.getFormType();
						//该销售单的金额总计 （目前先按单据总金额，后期增加按实际回款金额计算）
						//根据佣金计划计算条件，区分参与计算的金额。0 总金额  1 实际回款金额
						
						if(so.getPayAmount() == so.getAmountTotal()){
							//按总价格计算
							Double amountTotal = so.getAmountTotal();
						}
						//获取订单明细
						Set<SaleOrderItem> saleOrderItems = new HashSet<SaleOrderItem>();
						for(SaleOrderItem soi : saleOrderItems){
							//获取物料编码
							String ic = soi.getItemCode();
							//判断物料类型，获取佣金方案
						}
					}
				}else {
					setMessage(salePerson.getName() + "不是在职员工，不能参与佣金结算！");
					return UPDATE;
				}
			}
			String customerAccountId = getRequestParameter("customerAccountId");
			if(null != customerAccountId && !"".equals(customerAccountId)){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, customerAccountId);
			}
			
			if (StrUtils.objectIsNotNull(customerAccountId) && StrUtils.objectIsNotNull(startTime) && StrUtils.objectIsNotNull(endTime)) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("isTemp," + SearchCondition.NOEQUAL, "1");
				params.put("tenantId," + SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("isCommissionCalculation," + SearchCondition.NOEQUAL, "1");// 未参与计算
				params.put("isComplate," + SearchCondition.EQUAL, "1");// 已完成
				params.put("customerAccount.id," + SearchCondition.EQUAL,customerAccountId);
				params.put("orderCreateDate," + SearchCondition.BETWEENAND, startTime + ":01!" + endTime + ":01");
				List<SalesOrder> soList = baseHibernateService.findAllByConditions(SalesOrder.class, params);
			}
			setMessage("计算成功!");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("计算失败!");
		}
		return UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != commissionResult.getId()) {
				isSave = false;
			} else {
				commissionResult.setCreateTime(new Date());
				loadCommonData(commissionResult);
			}
			commissionResult = baseHibernateService.merge(commissionResult);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public CommissionResult getCommissionResult() {
		return commissionResult;
	}

	public void setCommissionResult(CommissionResult commissionResult) {
		this.commissionResult = commissionResult;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public SaleReturnForm getSaleReturnForm() {
		return saleReturnForm;
	}

	public void setSaleReturnForm(SaleReturnForm saleReturnForm) {
		this.saleReturnForm = saleReturnForm;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public Employee getSalePerson() {
		return salePerson;
	}

	public void setSalePerson(Employee salePerson) {
		this.salePerson = salePerson;
	}

	public OrganizationUnit getSaleOrg() {
		return saleOrg;
	}

	public void setSaleOrg(OrganizationUnit saleOrg) {
		this.saleOrg = saleOrg;
	}

	public CommissionPlan getCommissionPlan() {
		return commissionPlan;
	}

	public void setCommissionPlan(CommissionPlan commissionPlan) {
		this.commissionPlan = commissionPlan;
	}

}