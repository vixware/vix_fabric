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
import com.vix.crm.activity.entity.Activity;
import com.vix.crm.base.entity.SaleActivity;
import com.vix.crm.project.entity.ActionHistory;
import com.vix.crm.project.entity.CrmProject;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class NvixSaleActivityAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private Activity activity;
	private String entityName;
	
	public void goListContent(){
		try {
			Pager pager = getPager();
			
			Map<String,Object> params = getParams();
			params.put("isDeleted,"+SearchCondition.NOEQUAL, "1");
			
			String title = getDecodeRequestParameter("title");
			if (null != title && !"".equals(title)) {
				params.put("title," + SearchCondition.ANYLIKE, title);
			}
			String customerName = getRequestParameter("customerName");
			if(StrUtils.objectIsNotNull(customerName)){
				customerName = decode(customerName, "UTF-8");
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, customerName);
			}
			baseHibernateService.findPagerByHqlConditions(pager, Activity.class, params);
			renderDataTable(pager,null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private List<CurrencyType> currencyTypeList;
	private List<SaleActivity> saleActivityList;
	private List<CrmProject> crmProjectList;
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			saleActivityList = baseHibernateService.findAllByEntityClass(SaleActivity.class);
			if(null != id && StrUtils.isNotEmpty(id)){
				activity = baseHibernateService.findEntityById(Activity.class,id);
				if(null != activity.getCustomerAccount() && StrUtils.isNotEmpty(activity.getCustomerAccount().getId())){
					crmProjectList = baseHibernateService.findAllByEntityClassAndAttribute(CrmProject.class, "customerAccount.id", activity.getCustomerAccount().getId());
				}
			}else{
				activity = new Activity();
				activity.setDate(new Date());
				Employee e = getEmployee();
				if(e != null){
					activity.setPersonInCharge(e);
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
			if (StrUtils.isNotEmpty(activity.getId())) {
				isSave = false;
			} else {
				activity.setCreateTime(new Date());
				activity.setIsDeleted("0");
				loadCommonData(activity);
			}
			
			if (null == activity.getCreated_by() || null == activity.getCreated_by().getId() || !"".equals(activity.getCreated_by().getId()) || !activity.getCreated_by().getId().equals("0")) {
				Employee bemp = getEmployee();
				if (null != bemp) {
					Employee emp = baseHibernateService.findEntityById(
							Employee.class, bemp.getId());
					if (null != emp) {
						activity.setCreated_by(emp);
					}
				}
			}
			if(null == activity.getCrmProject() || StrUtils.isEmpty(activity.getCrmProject().getId())){
				activity.setCrmProject(null);
			}
			String[] attrArray = { "customerAccount", "currencyType", "personInCharge" , "saleActivity" ,"crmProject"};
			checkEntityNullValue(activity, attrArray);

			String name = activity.getTitle();
			String py = ChnToPinYin.getPYString(name);
			activity.setChineseFirstLetter(py.toUpperCase());
			activity = baseHibernateService.merge(activity);
			// 增加行动历史和客户更新
			if(activity.getCustomerAccount() != null && StrUtils.isNotEmpty(activity.getCustomerAccount().getId())){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, activity.getCustomerAccount().getId());
				ActionHistory actionHistory = new ActionHistory();
				actionHistory.setSubject("销售活动");
				actionHistory.setActionDate(new Date());
				actionHistory.setCustomerAccount(customerAccount);
				actionHistory.setCrmProject(activity.getCrmProject());
				actionHistory.setDescription("销售活动编辑");
				actionHistory = baseHibernateService.merge(actionHistory);
				customerAccount.setUpdateTime(new Date());
				customerAccount.setStagnateDay(0);
				customerAccount = baseHibernateService.merge(customerAccount);
			}
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
	}
	
	/** 处理删除操作 */
	public void deleteById() {
		try {
			Activity pb = baseHibernateService.findEntityById(Activity.class,id);
			if(null != pb){
				//pb.setIsDeleted("1");
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public List<SaleActivity> getSaleActivityList() {
		return saleActivityList;
	}

	public void setSaleActivityList(List<SaleActivity> saleActivityList) {
		this.saleActivityList = saleActivityList;
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

	public List<CrmProject> getCrmProjectList() {
		return crmProjectList;
	}

	public void setCrmProjectList(List<CrmProject> crmProjectList) {
		this.crmProjectList = crmProjectList;
	}
}