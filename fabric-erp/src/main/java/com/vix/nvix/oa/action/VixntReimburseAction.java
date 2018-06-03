package com.vix.nvix.oa.action;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.billtype.BillType;
import com.vix.common.job.entity.FlowApprovalOpinion;
import com.vix.common.org.entity.OrgPosition;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.areaExpensesStandards.entity.AreaExpensesStandards;
import com.vix.oa.claimsmanagement.areaLevel.entity.AreaLevel;
import com.vix.oa.claimsmanagement.vehicle.entity.Transport;
import com.vix.oa.currentexpense.entity.CurrentExpenseDetail;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.oa.travelclaims.entity.AccommodationFee;
import com.vix.oa.travelclaims.entity.TransportCosts;
import com.vix.oa.travelclaims.entity.TravelExpense;

import net.sf.json.JSONObject;

/**
 * 报销管理
 * 
 * @类全名 com.vix.nvix.oa.action.VixntReimburseAction
 *
 * @author zhanghaibing
 *
 * @date 2016年8月13日
 */
@Controller
@Scope("prototype")
public class VixntReimburseAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private TravelExpense travelExpense;
	private List<TravelExpense> travelExpenseList;
	/**
	 * 交通费用
	 */
	private TransportCosts transportCosts;
	private AccommodationFee accommodationFee;
	private CurrentExpenseDetail currentExpenseDetail;
	// 岗位
	private List<OrgPosition> orgPositionList;
	// 区域
	private List<AreaLevel> areaLevelList;
	// 交通
	private List<Transport> transportList;

	private String travelExpenseId;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	@Autowired
	private OrderProcessController orderProcessController;
	private String str;
	private List<FlowApprovalOpinion> flowApprovalOpinionList;

	private String transportId;
	private String orgPositionId;
	private String areaLevelId;
	private String type;
	@Override
	public String goList() {
		return GO_LIST;
	}

	// 我的报销单
	public void goMyTripRecord() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			Employee e = getEmployee();
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("name," + SearchCondition.ANYLIKE, title);
			}
			if (e != null) {
				params.put("employee.id," + SearchCondition.EQUAL, e.getId());
				pager = vixntBaseService.findPagerByHqlConditions(pager, TravelExpense.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goAccommodationFeePager() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("name," + SearchCondition.ANYLIKE, title);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("travelExpense.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, AccommodationFee.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goCurrentExpenseDetailPager() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("name," + SearchCondition.ANYLIKE, title);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("travelExpense.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, CurrentExpenseDetail.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goTransportCostsPager() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("name," + SearchCondition.ANYLIKE, title);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("travelExpense.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, TransportCosts.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 跳转工作计划页面
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			isAllowAudit = isAllowAudit(BillType.OA_EXPENSE_ACCOUNT);
			if (StringUtils.isNotEmpty(id)) {
				travelExpense = vixntBaseService.findEntityById(TravelExpense.class, id);
			} else {
				travelExpense = new TravelExpense();
				travelExpense.setCreateTime(new Date());
				Employee e = getEmployee();
				if (e != null) {
					travelExpense.setEmployee(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateTransportCosts() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			orgPositionList = vixntBaseService.findAllByConditions(OrgPosition.class, params);
			areaLevelList = vixntBaseService.findAllByConditions(AreaLevel.class, params);
			transportList = vixntBaseService.findAllByConditions(Transport.class, params);
			if (StringUtils.isNotEmpty(id)) {
				transportCosts = vixntBaseService.findEntityById(TransportCosts.class, id);
			} else {
				transportCosts = new TransportCosts();
				transportCosts.setCreateTime(new Date());
				if (StringUtils.isNotEmpty(travelExpenseId)) {
					travelExpense = vixntBaseService.findEntityById(TravelExpense.class, travelExpenseId);
					if (travelExpense != null) {
						transportCosts.setTravelExpense(travelExpense);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateTransportCosts";
	}

	public void saveOrUpdateTransportCosts() {
		boolean isSave = true;
		try {
			if (transportCosts != null && StringUtils.isNotEmpty(transportCosts.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(transportCosts);
			transportCosts = vixntBaseService.merge(transportCosts);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdateAccommodationFee() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			orgPositionList = vixntBaseService.findAllByConditions(OrgPosition.class, params);
			areaLevelList = vixntBaseService.findAllByConditions(AreaLevel.class, params);
			transportList = vixntBaseService.findAllByConditions(Transport.class, params);
			if (StringUtils.isNotEmpty(id)) {
				accommodationFee = vixntBaseService.findEntityById(AccommodationFee.class, id);
			} else {
				accommodationFee = new AccommodationFee();
				accommodationFee.setCreateTime(new Date());
				if (StringUtils.isNotEmpty(travelExpenseId)) {
					travelExpense = vixntBaseService.findEntityById(TravelExpense.class, travelExpenseId);
					if (travelExpense != null) {
						accommodationFee.setTravelExpense(travelExpense);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateAccommodationFee";
	}

	public void saveOrUpdateAccommodationFee() {
		boolean isSave = true;
		try {
			if (accommodationFee != null && StringUtils.isNotEmpty(accommodationFee.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(accommodationFee);
			accommodationFee = vixntBaseService.merge(accommodationFee);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdateCurrentExpenseDetail() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			orgPositionList = vixntBaseService.findAllByConditions(OrgPosition.class, params);
			areaLevelList = vixntBaseService.findAllByConditions(AreaLevel.class, params);
			transportList = vixntBaseService.findAllByConditions(Transport.class, params);
			if (StringUtils.isNotEmpty(id)) {
				currentExpenseDetail = vixntBaseService.findEntityById(CurrentExpenseDetail.class, id);
			} else {
				currentExpenseDetail = new CurrentExpenseDetail();
				currentExpenseDetail.setCreateTime(new Date());
				if (StringUtils.isNotEmpty(travelExpenseId)) {
					travelExpense = vixntBaseService.findEntityById(TravelExpense.class, travelExpenseId);
					if (travelExpense != null) {
						currentExpenseDetail.setTravelExpense(travelExpense);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateCurrentExpenseDetail";
	}

	public void saveOrUpdateCurrentExpenseDetail() {
		boolean isSave = true;
		try {
			if (currentExpenseDetail != null && StringUtils.isNotEmpty(currentExpenseDetail.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(currentExpenseDetail);
			currentExpenseDetail = vixntBaseService.merge(currentExpenseDetail);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}

	/** 处理修改工作计划操作 */
	public void saveOrUpdate() {

		boolean isSave = true;
		try {
			if (travelExpense != null && StringUtils.isNotEmpty(travelExpense.getId())) {
				isSave = false;
				travelExpense.setUpdateTime(new Date());
			} else {
				travelExpense.setCreateTime(new Date());
			}
			if ("1".equals(isAllowAudit(BillType.OA_EXPENSE_ACCOUNT))) {
				travelExpense.setStatus("0");
			}
			initEntityBaseController.initEntityBaseAttribute(travelExpense);
			travelExpense = vixntBaseService.merge(travelExpense);
			if (isSave) {
				renderText(travelExpense.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText(travelExpense.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}
	/** 处理修改工作计划操作 */
	public void audit() {
		try {
			if (travelExpense != null && StringUtils.isNotEmpty(travelExpense.getId())) {
				travelExpense.setUpdateTime(new Date());
			} else {
				travelExpense.setCreateTime(new Date());
			}
			if ("1".equals(isAllowAudit(BillType.OA_EXPENSE_ACCOUNT))) {
				travelExpense.setStatus("0");
			}
			initEntityBaseController.initEntityBaseAttribute(travelExpense);
			travelExpense = vixntBaseService.merge(travelExpense);
			if ("1".equals(isAllowAudit(BillType.OA_EXPENSE_ACCOUNT))) {
				String response = dealStartAndSubmitByBillsCode(BillType.OA_EXPENSE_ACCOUNT, travelExpense);
				if (StringUtils.isNotEmpty(response)) {
					JSONObject json = JSONObject.fromObject(response);
					if (json.has("status")) {
						if ("1".equals(json.getString("status"))) {
							travelExpense.setStatus("1");
							travelExpense = vixntBaseService.merge(travelExpense);
							renderText("提交成功!");
						}
					}
				}
			} else {
				renderText("提交失败!未绑定审批流程!");
			}
		} catch (Exception e) {
			renderText("提交失败!");
			e.printStackTrace();
		}
	}
	public String goAudit() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				travelExpense = vixntBaseService.findEntityById(TravelExpense.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAudit";
	}
	/** 处理删除操作 */
	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				TravelExpense pb = vixntBaseService.findEntityById(TravelExpense.class, id);
				if (null != pb) {
					vixntBaseService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public void deleteEvaluationReviewById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				EvaluationReview pb = vixntBaseService.findEntityById(EvaluationReview.class, id);
				if (null != pb) {
					vixntBaseService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/** 子项价格汇总 */
	public void getTravelExpenseCost() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				TravelExpense travelExpense = vixntBaseService.findEntityById(TravelExpense.class, id);
				if (null != travelExpense) {
					Double totalAmount = 0d;
					for (TransportCosts transportCosts : travelExpense.getTransportCostss()) {
						if (transportCosts.getCost() != null) {
							totalAmount += transportCosts.getCost();
						}
					}
					for (AccommodationFee accommodationFee : travelExpense.getAccommodationFees()) {
						if (accommodationFee.getCost() != null) {
							totalAmount += accommodationFee.getCost();
						}
					}
					for (CurrentExpenseDetail currentExpenseDetail : travelExpense.getSubCurrentExpenseDetails()) {
						if (currentExpenseDetail.getExpensesAmountDetail() != null) {
							totalAmount += currentExpenseDetail.getExpensesAmountDetail();
						}
					}
					travelExpense.setCost(totalAmount);
					travelExpense = vixntBaseService.merge(travelExpense);
					DecimalFormat df = new DecimalFormat(".##");
					String st = df.format(totalAmount);
					renderJson(st);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goShowBeforeAndAfter() {
		try {
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				travelExpense = baseHibernateService.findEntityById(TravelExpense.class, id);
				if (travelExpense != null && travelExpense.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(travelExpense.getCreateTime()));
					params.put("isTemp", "0");
					params.put("isDelete", "0");
					params.put("tenantId", travelExpense.getTenantId());
					params.put("companyInnerCode", travelExpense.getCompanyInnerCode());
					if (StrUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							travelExpense = (TravelExpense) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(travelExpense.getCreateTime()), params, travelExpense, "before");
						} else if ("after".equals(str)) {
							travelExpense = (TravelExpense) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(travelExpense.getCreateTime()), params, travelExpense, "after");
						}
					}
					if (travelExpense == null || StrUtils.isEmpty(travelExpense.getId())) {
						travelExpense = baseHibernateService.findEntityById(TravelExpense.class, id);
						Map<String, Object> params1 = new HashMap<String, Object>();
						params.put("sourceClassPk," + SearchCondition.EQUAL, travelExpense.getId());
						flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	public String show() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				travelExpense = vixntBaseService.findEntityById(TravelExpense.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	public String printTravelExpense() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				travelExpense = vixntBaseService.findEntityById(TravelExpense.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "printTravelExpense";
	}

	public void checkTransportCosts() {
		try {
			if (StringUtils.isNotEmpty(type)) {
				Map<String, Object> params = getParams();
				if ("1".equals(type)) {
					if (StringUtils.isNotEmpty(areaLevelId) && StringUtils.isNotEmpty(orgPositionId) && StringUtils.isNotEmpty(transportId)) {
						params.put("areaLevel.id," + SearchCondition.EQUAL, areaLevelId);
						params.put("transport.id," + SearchCondition.EQUAL, transportId);
						params.put("orgPosition.id," + SearchCondition.EQUAL, orgPositionId);
						params.put("type," + SearchCondition.EQUAL, type);
						List<AreaExpensesStandards> areaExpensesStandardsList = vixntBaseService.findAllByConditions(AreaExpensesStandards.class, params);
						if (areaExpensesStandardsList != null && areaExpensesStandardsList.size() == 1) {
							AreaExpensesStandards expensesStandards = areaExpensesStandardsList.get(0);
							if (expensesStandards != null) {
								renderJson(expensesStandards.getReimbursementAmount().toString());
							}
						}
					}
				} else if ("2".equals(type)) {
					if (StringUtils.isNotEmpty(areaLevelId) && StringUtils.isNotEmpty(orgPositionId)) {
						params.put("areaLevel.id," + SearchCondition.EQUAL, areaLevelId);
						params.put("orgPosition.id," + SearchCondition.EQUAL, orgPositionId);
						params.put("type," + SearchCondition.EQUAL, type);
						List<AreaExpensesStandards> areaExpensesStandardsList = vixntBaseService.findAllByConditions(AreaExpensesStandards.class, params);
						if (areaExpensesStandardsList != null && areaExpensesStandardsList.size() == 1) {
							AreaExpensesStandards expensesStandards = areaExpensesStandardsList.get(0);
							if (expensesStandards != null) {
								renderJson(expensesStandards.getReimbursementAmount().toString());
							}
						}
					}
				} else if ("3".equals(type)) {
					if (StringUtils.isNotEmpty(areaLevelId) && StringUtils.isNotEmpty(orgPositionId)) {
						params.put("areaLevel.id," + SearchCondition.EQUAL, areaLevelId);
						params.put("orgPosition.id," + SearchCondition.EQUAL, orgPositionId);
						params.put("type," + SearchCondition.EQUAL, type);
						List<AreaExpensesStandards> areaExpensesStandardsList = vixntBaseService.findAllByConditions(AreaExpensesStandards.class, params);
						if (areaExpensesStandardsList != null && areaExpensesStandardsList.size() == 1) {
							AreaExpensesStandards expensesStandards = areaExpensesStandardsList.get(0);
							if (expensesStandards != null) {
								renderJson(expensesStandards.getReimbursementAmount().toString());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public TravelExpense getTravelExpense() {
		return travelExpense;
	}

	public void setTravelExpense(TravelExpense travelExpense) {
		this.travelExpense = travelExpense;
	}

	public String getTravelExpenseId() {
		return travelExpenseId;
	}

	public void setTravelExpenseId(String travelExpenseId) {
		this.travelExpenseId = travelExpenseId;
	}

	public List<OrgPosition> getOrgPositionList() {
		return orgPositionList;
	}

	public void setOrgPositionList(List<OrgPosition> orgPositionList) {
		this.orgPositionList = orgPositionList;
	}

	public List<AreaLevel> getAreaLevelList() {
		return areaLevelList;
	}

	public void setAreaLevelList(List<AreaLevel> areaLevelList) {
		this.areaLevelList = areaLevelList;
	}

	public List<Transport> getTransportList() {
		return transportList;
	}

	public void setTransportList(List<Transport> transportList) {
		this.transportList = transportList;
	}

	public TransportCosts getTransportCosts() {
		return transportCosts;
	}

	public void setTransportCosts(TransportCosts transportCosts) {
		this.transportCosts = transportCosts;
	}

	public AccommodationFee getAccommodationFee() {
		return accommodationFee;
	}

	public void setAccommodationFee(AccommodationFee accommodationFee) {
		this.accommodationFee = accommodationFee;
	}

	public CurrentExpenseDetail getCurrentExpenseDetail() {
		return currentExpenseDetail;
	}

	public void setCurrentExpenseDetail(CurrentExpenseDetail currentExpenseDetail) {
		this.currentExpenseDetail = currentExpenseDetail;
	}

	public List<TravelExpense> getTravelExpenseList() {
		return travelExpenseList;
	}

	public void setTravelExpenseList(List<TravelExpense> travelExpenseList) {
		this.travelExpenseList = travelExpenseList;
	}

	public String getIsAllowAudit() {
		return isAllowAudit;
	}

	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public List<FlowApprovalOpinion> getFlowApprovalOpinionList() {
		return flowApprovalOpinionList;
	}

	public void setFlowApprovalOpinionList(List<FlowApprovalOpinion> flowApprovalOpinionList) {
		this.flowApprovalOpinionList = flowApprovalOpinionList;
	}

	public String getTransportId() {
		return transportId;
	}

	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}

	public String getOrgPositionId() {
		return orgPositionId;
	}

	public void setOrgPositionId(String orgPositionId) {
		this.orgPositionId = orgPositionId;
	}

	public String getAreaLevelId() {
		return areaLevelId;
	}

	public void setAreaLevelId(String areaLevelId) {
		this.areaLevelId = areaLevelId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
