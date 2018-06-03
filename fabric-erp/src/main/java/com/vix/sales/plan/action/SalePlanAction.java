package com.vix.sales.plan.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.billtype.BillType;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.sales.plan.entity.SalePlan;

@Controller
@Scope("prototype")
public class SalePlanAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private OrderProcessController orderProcessController;
	private String id;
	private String ids;
	private SalePlan salePlan;
	private String pageNo;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	private String str;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			String name = getRequestParameter("name");
			if(StrUtils.objectIsNotNull(name)){
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			String fs = getRequestParameter("fs");
			if(StrUtils.objectIsNotNull(fs)){
				fs = decode(fs, "UTF-8");
				params.put("saleOrg.fs," + SearchCondition.ANYLIKE, fs);
			}
			String salesMan = getRequestParameter("salesMan");
			if(StrUtils.objectIsNotNull(salesMan)){
				salesMan = decode(salesMan, "UTF-8");
				params.put("salesMan.name," + SearchCondition.ANYLIKE, salesMan);
			}
			String subject = getRequestParameter("subject");
			if(StrUtils.objectIsNotNull(subject)){
				subject = decode(subject, "UTF-8");
				params.put("subject,"+SearchCondition.ANYLIKE, subject);
			}
			String parentId = getRequestParameter("parentId");
			if(StrUtils.objectIsNotNull(parentId)){
				params.put("parentSalePlan.id,"+SearchCondition.EQUAL, parentId);
			}else{
				params.put("parentSalePlan.id,"+SearchCondition.IS, null);
			}
			 Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), SalePlan.class, params);
			 setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	private List<MeasureUnitGroup> measureUnitGroupList;
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name," + SearchCondition.NOEQUAL, "");
			measureUnitGroupList = baseHibernateService.findAllByConditions(MeasureUnitGroup.class, params);
			if(null != id && !"".equals(id) && !"0".equals(id)){
				salePlan = baseHibernateService.findEntityById(SalePlan.class,id);
				isAllowAudit = isAllowAudit(BillType.SAL_PLAN);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
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
			if(null != salePlan.getId()){
				isSave = false;
			}else{
				salePlan.setCreateTime(new Date());
				salePlan.setStatus("1");
				loadCommonData(salePlan);
			}
			String[] attrArray ={"item","saleOrg","salesMan","parentSalePlan","departmet"};
			checkEntityNullValue(salePlan,attrArray);
			salePlan.setStatus("1");
			salePlan = baseHibernateService.merge(salePlan);
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
	
	private String measureUnitGroupId;
	private String type;
	private List<MeasureUnit> measureUnitList;
	public String loadMeasureUnit() {
		try {
			if(StringUtils.isNotEmpty(measureUnitGroupId)){
				measureUnitList = baseHibernateService.findAllByEntityClassAndAttribute(MeasureUnit.class, "measureUnitGroup.id",measureUnitGroupId);
			}
			if (null != id && !id.equals("") && !id.equals("0")) {
				salePlan = baseHibernateService.findEntityById(SalePlan.class, id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return type;
	}

	
	public String showSalesPlan() {
		try {
			if (null != id && !"".equals(id)) {
				salePlan = baseHibernateService.findEntityById(SalePlan.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showSalesPlan";
	}
	
	//打印
	public String goPrintSalesPlan() {
		try {
			salePlan = baseHibernateService.findEntityById(SalePlan.class,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintSalesPlan";
	}
	
	//上一条   下一条
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
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showSalesPlan";
	}
	
	
//	高级搜索
	public String goSearch() {
		return "goSearch";
	}
	
	/** 处理删除操作 */
	public String deleteById() {
		try {
			SalePlan pb = baseHibernateService.findEntityById(SalePlan.class,id);
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
	
	/** 处理删除操作 */
	public String deleteByIds() {
		try {
			if(null != ids && !"".equals(ids)){
				List<String> delIds = new ArrayList<String>();
				for(String idStr : ids.split(",")){
					if(null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)){
						delIds.add(idStr);
					}
				}
				baseHibernateService.batchDelete(SalePlan.class,delIds);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("ec_brandNotChoose"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
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
				strSb.append("{id:");
				strSb.append(ic.getId());
				strSb.append(",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:true,isParent:true,children:[");
				loadAllSalePlan(strSb,new ArrayList<SalePlan>(ic.getSubSalePlans()));
				strSb.append("]}");
			}else{
				strSb.append("{id:");
				strSb.append(ic.getId());
				strSb.append(",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:false,isParent:false}");
			}
			if(i < listSalePlan.size() -1){
				strSb.append(",");
			}
		}
		return strSb;
	}
	
	public String goChooseSalePlan(){
		return "goChooseSalePlan";
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
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

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public List<MeasureUnitGroup> getMeasureUnitGroupList() {
		return measureUnitGroupList;
	}

	public void setMeasureUnitGroupList(List<MeasureUnitGroup> measureUnitGroupList) {
		this.measureUnitGroupList = measureUnitGroupList;
	}

	public String getMeasureUnitGroupId() {
		return measureUnitGroupId;
	}

	public void setMeasureUnitGroupId(String measureUnitGroupId) {
		this.measureUnitGroupId = measureUnitGroupId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<MeasureUnit> getMeasureUnitList() {
		return measureUnitList;
	}

	public void setMeasureUnitList(List<MeasureUnit> measureUnitList) {
		this.measureUnitList = measureUnitList;
	}
	
	
}
