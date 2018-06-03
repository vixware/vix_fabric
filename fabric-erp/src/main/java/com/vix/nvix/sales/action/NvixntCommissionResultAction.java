package com.vix.nvix.sales.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.vo.OrgUnit;
import com.vix.common.security.util.DaysUtils;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.sales.commission.entity.CommissionPlan;
import com.vix.sales.commission.entity.CommissionPlanItem;
import com.vix.sales.commission.entity.CommissionResult;
import com.vix.sales.delivery.entity.SaleReturnForm;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;
@Controller
@Scope("prototype")
public class NvixntCommissionResultAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CommissionResult commissionResult;
	
	private String id;
	
	private String treeType;
	private String selectId;
	
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
	
	
	private String salePersonId;
	private String startTime;
	private String endTime;
	private String customerAccountId;
	
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(treeType)&&StringUtils.isNotEmpty(selectId)) {
				if("E".equalsIgnoreCase(treeType)) {
					params.put("saleMan.id,"+SearchCondition.EQUAL, selectId);
				}else if("O".equalsIgnoreCase(treeType)) {
					params.put("saleOrg.id,"+SearchCondition.EQUAL, selectId);
				}else if("C".equalsIgnoreCase(treeType)) {
					params.put("saleOrg.organization.id,"+SearchCondition.EQUAL, selectId);
				}
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, CommissionResult.class, params);
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void chooseSaleOrderItem() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(startTime)&&StringUtils.isNotEmpty(endTime)) {
				params.put("salesOrder.createTime,"+SearchCondition.BETWEENAND,DaysUtils.getBeginDay(sdf.parse(startTime))+"!"+DaysUtils.getEndDay(sdf.parse(endTime)));
			}
			if(StringUtils.isNotEmpty(treeType)&&StringUtils.isNotEmpty(selectId)) {
				if("E".equalsIgnoreCase(treeType)) {
					params.put("salesOrder.salePerson.id,"+SearchCondition.EQUAL, selectId);
				}else if("O".equalsIgnoreCase(treeType)) {
					params.put("salesOrder.saleOrg.id,"+SearchCondition.EQUAL, selectId);
				}else if("C".equalsIgnoreCase(treeType)) {
					params.put("salesOrder.saleOrg.organization.id,"+SearchCondition.EQUAL, selectId);
				}
			}
			params.put("salesOrder.isFreeze,"+SearchCondition.NOEQUAL, "1");//订单未冻结
			params.put("isCommissionCalculation,"+SearchCondition.NOEQUAL, "1");//未参与佣金计算
			params.put("salesOrder.isComplate,"+SearchCondition.EQUAL, "1");//订单已完成
			
			pager = baseHibernateService.findPagerByHqlConditions(pager, SaleOrderItem.class, params);
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goChooseSalesOrderItem() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseSalesOrderItem";
	}
	public void calculateCommissionResult() {
		//初始化总金额
		Double count = 0.00;
		//临时变量
		Double temp = 0.00;
		try {
			String ids = getDecodeRequestParameter("ids");
			if(StringUtils.isNotEmpty(ids)) {
				String [] arr = ids.split(",");
				List<SaleOrderItem> saleOrderItemList = new ArrayList<SaleOrderItem>();
				if(arr.length > 0) {
					for (String id : arr) {
						if(StringUtils.isNotEmpty(id)) {
							SaleOrderItem saleOrderItem = baseHibernateService.findEntityById(SaleOrderItem.class, id);
							saleOrderItemList.add(saleOrderItem);
						}
					}
				}
				if(StringUtils.isNotEmpty(selectId)&&StringUtils.isNotBlank(treeType)) {
					if("C".equalsIgnoreCase(treeType)) {
						
					}
					if("O".equalsIgnoreCase(treeType)) {
						commissionPlan = vixntBaseService.findEntityByAttribute(CommissionPlan.class, "saleOrg.id", selectId);
						if(commissionPlan != null) {
							if(commissionPlan.getCommissionPlanItems() != null && commissionPlan.getCommissionPlanItems().size() > 0) {
								for (CommissionPlanItem commissionPlanItem : commissionPlan.getCommissionPlanItems()) {
									if(commissionPlanItem != null) {
										if(commissionPlanItem.getCommissionTerms() != null && commissionPlanItem.getCommissionTerms().size() > 0) {
											for (SaleOrderItem saleOrderItem : saleOrderItemList) {
												if(saleOrderItem != null) {
												}
											}
										}
									}
								}
							}
						}else {
							renderText("计算失败!");
							return;
						}
					}
				}
			}
			if(StringUtils.isNotEmpty(salePersonId)) {
				salePerson = vixntBaseService.findEntityById(Employee.class, salePersonId);
				if(salePerson != null && "1".equals(salePerson.getEmployeeType())) {
					//获取销售组织
					saleOrg = salePerson.getOrganizationUnit();
					
					//获取该组织下的佣金方案
					commissionPlan = vixntBaseService.findEntityByAttribute(CommissionPlan.class, "saleOrg", saleOrg);
					if(commissionPlan != null && commissionPlan.getCommissionPlanItems().size() <= 0) {
						
					}else {
						for (CommissionPlanItem commissionPlanItem : commissionPlan.getCommissionPlanItems()) {
							
						}
					}
					List<SalesOrder> salesOrders = vixntBaseService.findAllByConditions(SalesOrder.class, params);
					if(salesOrders != null && salesOrders.size() > 0) {
						for (SalesOrder salesOrder : salesOrders) {
							if(salesOrder != null) {
								//判断订单是否被冻结,是否参与过佣金计算,订单是否已完成
								if("1".equals(salesOrder.getIsFreeze()) || "1".equals(salesOrder.getIsCommissionCalculation()) ||"2".equals(salesOrder.getIsComplate())) {
									continue;
								}
								String fromType = null;
								if(salesOrder.getOrderType() != null) {
									fromType = salesOrder.getOrderType().getName();
								}
								if(salesOrder.getPayAmount() == salesOrder.getAmountTotal()) {
									count  = salesOrder.getAmountTotal();
								}
								List<SaleOrderItem> saleOrderItems = vixntBaseService.findAllByEntityClassAndAttribute(SaleOrderItem.class, "salesOrder.id", salesOrder.getId());
								if(saleOrderItems != null && saleOrderItems.size() > 0) {
									for (SaleOrderItem saleOrderItem : saleOrderItems) {
										
									}
								}
							}
						}
					}
				}else {
					renderText("该员工不是在职员工,不能参与佣金计算!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public void findTreeToJson() {
		try {
			loadEmployee(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void loadEmployee(String nodeId, String nodeTreeType) {
		try {
			List<OrgUnit> orgUnitList = null;
			List<Organization> orgList = null;
			if (null != nodeId && !"".equals(nodeId) && !"undefined".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					// 没有根结点 需要加载公司信息 其实id不为空 则treetype肯定也不为空
					if (nodeTreeType.equals("C") || nodeTreeType.equals("O") || nodeTreeType.equals("P")||nodeTreeType.equals("E")) {
						// 加载公司信息和部门信息
						orgUnitList = vixntBaseService.findOrgAndUnitTreeList(nodeTreeType, nodeId, "O");
					}
				}
			} else {
				// id为空 则类型也为空,加载公司信息
				Map<String, Object> params = getParams();
				orgList = vixntBaseService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}

			if (orgUnitList == null) {
				orgUnitList = new LinkedList<OrgUnit>();
			}

			if (orgList != null) {
				for (Organization orgTmp : orgList) {
					OrgUnit ou1 = new OrgUnit(orgTmp.getId(), "C", orgTmp.getOrgName());
					if (orgTmp.getSubOrganizations().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (Organization childOrg : orgTmp.getSubOrganizations()) {
							ou2List.add(new OrgUnit(childOrg.getId(), "C", childOrg.getOrgName()));
						}
						ou1.setSubOrgUnits(ou2List);
					}
					if (orgTmp.getOrganizationUnits().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (OrganizationUnit organizationUnit : orgTmp.getOrganizationUnits()) {
							OrgUnit ou2 = new OrgUnit(organizationUnit.getId(), "O", organizationUnit.getFs());
							List<Employee> employees = vixntBaseService.findAllByEntityClassAndAttribute(Employee.class, "organizationUnit.id", organizationUnit.getId());
							if(employees.size() > 0) {
								List<OrgUnit> ou3List = new LinkedList<OrgUnit>();
								for (Employee employee : employees) {
									ou3List.add(new OrgUnit(employee.getId(), "E", employee.getName()));
								}
								ou2.setSubOrgUnits(ou3List);
							}
							ou2List.add(ou2);
						}
						ou1.setSubOrgUnits(ou2List);
					}
					orgUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			strSb = loadEmp(strSb,orgUnitList);
			/** 递归方式 **/
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private StringBuilder loadEmp(StringBuilder strSb,List<OrgUnit> orgUnits) {
		int count = orgUnits.size();
		for (int i = 0; i < count; i++) {
			OrgUnit org = orgUnits.get(i);
			if (org.getSubOrgUnits() != null && org.getSubOrgUnits().size() > 0) {
				strSb.append("{id:\"");
				strSb.append(org.getId());
				strSb.append("\",treeType:\"");
				strSb.append(org.getTreeType());
				strSb.append("\",name:\"");
				strSb.append(org.getOrgName());
				strSb.append("\",open:false,isParent:true,children:[");
				loadEmp(strSb,new ArrayList<OrgUnit>(org.getSubOrgUnits()));
				strSb.append("]}");
			} else {
				strSb.append("{id:\"");
				strSb.append(org.getId());
				strSb.append("\",treeType:\"");
				strSb.append(org.getTreeType());
				strSb.append("\",name:\"");
				strSb.append(org.getOrgName());
				strSb.append("\",open:false,isParent:false}");
			}
			if (i < count - 1) {
				strSb.append(",");
			}
		}
		return strSb;
	}
	public CommissionResult getCommissionResult() {
		return commissionResult;
	}

	public void setCommissionResult(CommissionResult commissionResult) {
		this.commissionResult = commissionResult;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
	public String getTreeType() {
		return treeType;
	}
	public void setTreeType(String treeType) {
		this.treeType = treeType;
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
	public String getSalePersonId() {
		return salePersonId;
	}
	public void setSalePersonId(String salePersonId) {
		this.salePersonId = salePersonId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCustomerAccountId() {
		return customerAccountId;
	}
	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}
	@Override
	public String getSelectId() {
		return selectId;
	}
	@Override
	public void setSelectId(String selectId) {
		this.selectId = selectId;
	}
}
