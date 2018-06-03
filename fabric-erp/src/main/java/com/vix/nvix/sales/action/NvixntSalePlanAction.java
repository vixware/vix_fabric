package com.vix.nvix.sales.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.sales.plan.entity.SalePlan;
import com.vix.sales.plan.entity.SalePlanCollect;
import com.vix.sales.plan.entity.SalePlanDetails;

import net.sf.json.JSONObject;
/**
 * 销售计划
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixntSalePlanAction extends VixntBaseAction{
	protected SimpleDateFormat dft = new SimpleDateFormat("yyyy");
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private OrderProcessController orderProcessController;
	private String id;
	private String parentId;
	private String treeType;
	private String name;
	private SalePlan salePlan;
	private String isAllowAudit;
	private List<MeasureUnitGroup> measureUnitGroupList;
	private List<MeasureUnit> measureUnitList;
	private List<BaseEntity> baseEntityList;
	private String str;
	private List<FlowApprovalOpinion> flowApprovalOpinionList;
	public String goCompList() {
		return "goCompList";
	}
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			Employee salesMan = getEmployee();
			if(salesMan != null) {
				params.put("salesMan.id,"+SearchCondition.EQUAL, salesMan.getId());
			}
			if(StringUtils.isNotEmpty(parentId)&&StringUtils.isNotEmpty(treeType)) {
				if("E".equalsIgnoreCase(treeType)) {
					params.put("salesMan.id,"+SearchCondition.EQUAL, parentId);
				}else if("O".equalsIgnoreCase(treeType)) {
					params.put("salesMan.organizationUnit.id,"+SearchCondition.EQUAL, parentId);
				}else if("C".equalsIgnoreCase(treeType)) {
					params.put("salesMan.organizationUnit.organization.id,"+SearchCondition.EQUAL, parentId);
				}
			}
			String planDate = getDecodeRequestParameter("planDate");
			if(StringUtils.isNotEmpty(planDate)) {
				params.put("planDate,"+SearchCondition.ANYLIKE, planDate);
			}
			if(StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, SalePlan.class, params);
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void goSingleListCollect() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			if(StringUtils.isNotEmpty(parentId)&&StringUtils.isNotEmpty(treeType)) {
				if("O".equalsIgnoreCase(treeType)) {
					params.put("departmet.id,"+SearchCondition.EQUAL, parentId);
				}else if("C".equalsIgnoreCase(treeType)) {
					params.put("departmet.organization.id,"+SearchCondition.EQUAL, parentId);
				}
			}
			String planDate = getDecodeRequestParameter("planDate");
			if(StringUtils.isNotEmpty(planDate)) {
				params.put("cycle,"+SearchCondition.ANYLIKE, planDate);
			}
			if(StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, SalePlanCollect.class, params);
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = getParams();
			params.put("name," + SearchCondition.NOEQUAL, "");
			measureUnitGroupList = vixntBaseService.findAllByConditions(MeasureUnitGroup.class, params);
			measureUnitList = vixntBaseService.findAllByConditions(MeasureUnit.class, params);
			isAllowAudit = isAllowAudit(BillType.SAL_PLAN);
			if(StringUtils.isNotEmpty(id)) {
				salePlan = vixntBaseService.findEntityById(SalePlan.class, id);
			}else {
				salePlan = new SalePlan();
				Employee salesMan = getEmployee();
				if(salesMan != null) {
					salePlan.setSalesMan(salesMan);
					salePlan.setDepartmet(salesMan.getOrganizationUnit());
				}
				salePlan.setAcount(0d);
				salePlan.setAmount(0l);
				salePlan.setCode(autoCreateCode.getBillNO(BillType.SAL_PLAN));
				salePlan.setPlanDate(dft.format(new Date()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(salePlan.getId())) {
				isSave = false;
				salePlan.setUpdateTime(new Date());
			}else {
				salePlan.setCreateTime(new Date());
			}
			if(salePlan.getParentSalePlan() != null) {
				if(StringUtils.isNotEmpty(salePlan.getParentSalePlan().getId())) {
				}else {
					salePlan.setParentSalePlan(null);
				}
			}
			salePlan.setIsCollect("0");
			initEntityBaseController.initEntityBaseAttribute(salePlan);
			salePlan = vixntBaseService.merge(salePlan);
			if(isSave) {
				renderText("1:"+SAVE_SUCCESS+":"+salePlan.getId());
			}else {
				renderText("1:"+UPDATE_SUCCESS+":"+salePlan.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:"+OPER_FAIL);
		}
	}
	public void audit() {
		try {
			if(StringUtils.isNotEmpty(salePlan.getId())) {
				salePlan.setUpdateTime(new Date());
			}else {
				salePlan.setCreateTime(new Date());
			}
			if(salePlan.getParentSalePlan() != null) {
				if(StringUtils.isNotEmpty(salePlan.getParentSalePlan().getId())) {
				}else {
					salePlan.setParentSalePlan(null);
				}
			}
			initEntityBaseController.initEntityBaseAttribute(salePlan);
			salePlan = vixntBaseService.merge(salePlan);
				if("1".equals(isAllowAudit(BillType.SAL_PLAN))) {
					String response = dealStartAndSubmitByBillsCode(BillType.SAL_PLAN, salePlan);
					if(StringUtils.isNotEmpty(response)) {
						JSONObject json = JSONObject.fromObject(response);
						if(json.has("status")) {
							if("1".equals(json.getString("status"))) {
								salePlan.setStatus("1");
								salePlan = vixntBaseService.merge(salePlan);
								renderText("提交成功!");
							}
						}
					}
			}else {
				renderText("提交失败!未绑定审批流程!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("提交失败!");
		}
	}
	
	public String goAudit() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				salePlan = vixntBaseService.findEntityById(SalePlan.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAudit";
	}
	public void deleteById() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				salePlan = vixntBaseService.findEntityById(SalePlan.class, id);
				if(null != salePlan) {
					vixntBaseService.deleteByEntity(salePlan);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public String loadMeasureUnit() {
		try {
			if(StringUtils.isNotEmpty(parentId)) {
				measureUnitList = baseHibernateService.findAllByEntityClassAndAttribute(MeasureUnit.class, "measureUnitGroup.id",parentId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loadMeasureUnit";
	}
	public String show() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				salePlan = vixntBaseService.findEntityById(SalePlan.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	public String printSalePlan() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				salePlan = vixntBaseService.findEntityById(SalePlan.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "printSalePlan";
	}
	public String goSourceList() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				salePlan = vixntBaseService.findEntityById(SalePlan.class, id);
				if(salePlan != null) {
					baseEntityList = new ArrayList<>();
					baseEntityList.add(salePlan);
					if(StringUtils.isNotEmpty(salePlan.getSourceBillCode())&&StringUtils.isNotEmpty(salePlan.getSourceClassName())) {
						getSourceBaseEntity(baseEntityList, salePlan);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSourceList";
	}
	public String goShowBeforeAndAfter() {
		try {
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				salePlan = baseHibernateService.findEntityById(SalePlan.class,id);
				if (salePlan != null && salePlan.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(salePlan.getCreateTime()));
					params.put("isTemp", "0");
					params.put("isDelete", "0");
					if (StrUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							salePlan = (SalePlan) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(salePlan.getCreateTime()), params, salePlan, "before");
						} else if ("after".equals(str)) {
							salePlan = (SalePlan) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(salePlan.getCreateTime()), params, salePlan, "after");
						}
					}
					if (salePlan == null || StrUtils.isEmpty(salePlan.getId())) {
						salePlan = baseHibernateService.findEntityById(SalePlan.class,id);
						Map<String, Object> params1 = new HashMap<String, Object>();
						params.put("sourceClassPk," + SearchCondition.EQUAL, salePlan.getId());
						flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	/** 树形结构JSON */
	public void findTreeToJson(){
		try{
			List<SalePlan> listSalePlan = new ArrayList<SalePlan>();
			/** 获取查询参数 */
			Map<String,Object> params = getParams();
			if(null!=id && !"".equals(id)){
				listSalePlan = baseHibernateService.findAllSubEntity(SalePlan.class,"parentSalePlan.id", id, params);
			}else{
				listSalePlan = baseHibernateService.findAllSubEntity(SalePlan.class,"parentSalePlan.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllSalePlan(strSb,listSalePlan);
			strSb.append("]");
			renderHtml(strSb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private StringBuilder loadAllSalePlan(StringBuilder strSb,List<SalePlan> listSalePlan) throws Exception{
		for(int i =0;i<listSalePlan.size();i++){
			SalePlan ic = listSalePlan.get(i);
			if(ic.getSubSalePlans().size() > 0){
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:true,isParent:true,children:[");
				loadAllSalePlan(strSb,new ArrayList<SalePlan>(ic.getSubSalePlans()));
				strSb.append("]}");
			}else{
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:false,isParent:false}");
			}
			if(i < listSalePlan.size() -1){
				strSb.append(",");
			}
		}
		return strSb;
	}
	
	public void collectSalePlan() {
		try {
			Map<String, Object> params = getParams();
			params.put("isCollect,"+SearchCondition.NOEQUAL, "1");
			if(StringUtils.isNotEmpty(parentId)&&StringUtils.isNotEmpty(treeType)) {
				if("E".equalsIgnoreCase(treeType)) {
					params.put("salesMan.id,"+SearchCondition.EQUAL, parentId);
				}else if("O".equalsIgnoreCase(treeType)) {
					params.put("salesMan.organizationUnit.id,"+SearchCondition.EQUAL, parentId);
				}else if("C".equalsIgnoreCase(treeType)) {
					params.put("salesMan.organizationUnit.organization.id,"+SearchCondition.EQUAL, parentId);
				}
			}
			String planDate = getDecodeRequestParameter("planDate");
			if(StringUtils.isNotEmpty(planDate)) {
				params.put("planDate,"+SearchCondition.ANYLIKE, planDate);
			}
			List<SalePlan> salePlanList = baseHibernateService.findAllByConditions(SalePlan.class, params);
			if(salePlanList != null && salePlanList.size() > 0) {
				for (SalePlan salePlan : salePlanList) {
					if(salePlan != null) {
						List<SalePlanDetails> salePlanDetailList = baseHibernateService.findAllByEntityClassAndAttribute(SalePlanDetails.class, "salePlan.id", salePlan.getId());
						if(salePlanDetailList != null && salePlanDetailList.size() > 0) {
							for (SalePlanDetails salePlanDetails : salePlanDetailList) {
								Map<String, Object> params1 = getParams();
								params1.put("departmet.id,"+SearchCondition.EQUAL, salePlan.getSalesMan().getOrganizationUnit().getId());
								params1.put("cycle,"+SearchCondition.EQUAL, salePlan.getPlanDate());
								params1.put("item.id,"+SearchCondition.EQUAL, salePlanDetails.getItem().getId());
								List<SalePlanCollect> collectList = baseHibernateService.findAllByConditions(SalePlanCollect.class, params1);
								if(collectList != null && collectList.size() > 0) {
									SalePlanCollect salePlanCollect = collectList.get(0);
									salePlanCollect.setJan(salePlanCollect.getJan() + salePlanDetails.getJan());
									salePlanCollect.setFeb(salePlanCollect.getFeb() + salePlanDetails.getFeb());
									salePlanCollect.setMar(salePlanCollect.getMar() + salePlanDetails.getMar());
									salePlanCollect.setApr(salePlanCollect.getApr() + salePlanDetails.getApr());
									salePlanCollect.setMay(salePlanCollect.getMay() + salePlanDetails.getMay());
									salePlanCollect.setJun(salePlanCollect.getJun() + salePlanDetails.getJun());
									salePlanCollect.setJul(salePlanCollect.getJul() + salePlanDetails.getJul());
									salePlanCollect.setAug(salePlanCollect.getAug() + salePlanDetails.getAug());
									salePlanCollect.setSep(salePlanCollect.getSep() + salePlanDetails.getSep());
									salePlanCollect.setOct(salePlanCollect.getOct() + salePlanDetails.getOct());
									salePlanCollect.setNov(salePlanCollect.getNov() + salePlanDetails.getNov());
									salePlanCollect.setDecember(salePlanCollect.getDecember() + salePlanDetails.getDecember());
									salePlanCollect.setUpdateTime(new Date());
									salePlanCollect = baseHibernateService.merge(salePlanCollect);
								}else {
									SalePlanCollect salePlanCollect = new SalePlanCollect();
									salePlanCollect.setCycle(salePlan.getPlanDate());
									salePlanCollect.setDepartmet(salePlan.getSalesMan().getOrganizationUnit());
									salePlanCollect.setItem(salePlanDetails.getItem());
									salePlanCollect.setJan(salePlanDetails.getJan());
									salePlanCollect.setFeb(salePlanDetails.getFeb());
									salePlanCollect.setMar(salePlanDetails.getMar());
									salePlanCollect.setApr(salePlanDetails.getApr());
									salePlanCollect.setMay(salePlanDetails.getMay());
									salePlanCollect.setJun(salePlanDetails.getJun());
									salePlanCollect.setJul(salePlanDetails.getJul());
									salePlanCollect.setAug(salePlanDetails.getAug());
									salePlanCollect.setSep(salePlanDetails.getSep());
									salePlanCollect.setOct(salePlanDetails.getOct());
									salePlanCollect.setNov(salePlanDetails.getNov());
									salePlanCollect.setDecember(salePlanDetails.getDecember());
									salePlanCollect.setUpdateTime(new Date());
									salePlanCollect.setCreateTime(new Date());
									salePlanCollect = baseHibernateService.merge(salePlanCollect);
								}
							}
						}else {
							continue;
						}
						salePlan.setIsCollect("1");
						salePlan = baseHibernateService.merge(salePlan);
					}
				}
				renderText("汇总成功!");
			}else {
				renderText("没有待汇总的计划!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("汇总失败!");
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
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SalePlan getSalePlan() {
		return salePlan;
	}
	public void setSalePlan(SalePlan salePlan) {
		this.salePlan = salePlan;
	}
	public String getIsAllowAudit() {
		return isAllowAudit;
	}
	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}
	public List<MeasureUnitGroup> getMeasureUnitGroupList() {
		return measureUnitGroupList;
	}
	public void setMeasureUnitGroupList(List<MeasureUnitGroup> measureUnitGroupList) {
		this.measureUnitGroupList = measureUnitGroupList;
	}
	public List<MeasureUnit> getMeasureUnitList() {
		return measureUnitList;
	}
	public void setMeasureUnitList(List<MeasureUnit> measureUnitList) {
		this.measureUnitList = measureUnitList;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public List<BaseEntity> getBaseEntityList() {
		return baseEntityList;
	}
	public void setBaseEntityList(List<BaseEntity> baseEntityList) {
		this.baseEntityList = baseEntityList;
	}
	public List<FlowApprovalOpinion> getFlowApprovalOpinionList() {
		return flowApprovalOpinionList;
	}
	public void setFlowApprovalOpinionList(List<FlowApprovalOpinion> flowApprovalOpinionList) {
		this.flowApprovalOpinionList = flowApprovalOpinionList;
	}
	public String getTreeType() {
		return treeType;
	}
	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}
}
