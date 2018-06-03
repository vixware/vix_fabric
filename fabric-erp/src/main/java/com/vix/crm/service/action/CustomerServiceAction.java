package com.vix.crm.service.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.ConsumeTime;
import com.vix.crm.base.entity.CustomerServiceStatus;
import com.vix.crm.base.entity.ServiceMode;
import com.vix.crm.base.entity.ServiceType;
import com.vix.crm.service.entity.CustomerServices;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class CustomerServiceAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String customerAccountId;
	private String ids;
	private CustomerServices customerService = new CustomerServices();
	private String pageNo;
	private String from;//来自于 project：项目 self：本对象
	
	private List<String> startTimeList = new ArrayList<String>();//开始时间
	
	public CustomerServiceAction(){
		StringBuilder t = new StringBuilder();
		for(int i = 0;i<24; i++){
			if(i<10){
				t.append("0");
				t.append(i);
			}else{
				t.append(i);
			}
			t.append(":");
			t.append("00");
			startTimeList.add(t.toString());
			t.delete(0, t.length());
			if(i<10){
				t.append("0");
				t.append(i);
			}else{
				t.append(i);
			}
			t.append(":");
			t.append("30");
			startTimeList.add(t.toString());
			t.delete(0, t.length());
		}
	}
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			params.put("isDeleted,"+SearchCondition.NOEQUAL, "1");
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), CustomerServices.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 获取列表数据  */
	public String goListContentForCrmProject(){
		try {
			Pager pager = getPager();
			if(null != id && !"".equals(id)){
				List<CustomerServices> pcList = baseHibernateService.findAllByEntityClassAndAttribute(CustomerServices.class, "crmProject.id", id);
				pager.setResultList(pcList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goSingleListForCrmProject";
	}
	
	/** 跳转至用户修改页面 */
	private List<ServiceType> serviceTypeList;
	private List<ServiceMode> serviceModeList;
	private List<ConsumeTime> consumeTimeList;
	private List<CustomerServiceStatus> customerServiceStatusList;
	public String goSaveOrUpdate() {
		try {
			serviceTypeList = baseHibernateService.findAllByEntityClass(ServiceType.class);
			serviceModeList = baseHibernateService.findAllByEntityClass(ServiceMode.class);
			consumeTimeList = baseHibernateService.findAllByEntityClass(ConsumeTime.class);
			customerServiceStatusList = baseHibernateService.findAllByEntityClass(CustomerServiceStatus.class);
			if(null != id && !"".equals(id)){
				customerService = baseHibernateService.findEntityById(CustomerServices.class,id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			}else{
				customerService = new CustomerServices();
				customerService.setStartDate(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null != from && "project".equals(from)){
			return "goSaveOrUpdateForProject";
		}else{
			return GO_SAVE_OR_UPDATE;
		}
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StrUtils.objectIsNotNull(customerService.getId())){
				isSave = false;
			}else{
				customerService.setCreateTime(new Date());
				customerService.setIsDeleted("0");
				loadCommonData(customerService);
			}
			
			String[] attrArray ={"serviceMode","serviceType","consumeTime","employee","crmProject","customerAccount","customerServiceStatus"};
			checkEntityNullValue(customerService,attrArray);
			
			customerService = baseHibernateService.merge(customerService);
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
			CustomerServices pb = baseHibernateService.findEntityById(CustomerServices.class,id);
			if(null != pb){
				pb.setIsDeleted("1");
				baseHibernateService.merge(pb);
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
				baseHibernateService.batchDelete(CustomerServices.class,delIds);
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
	
	public String goSaveOrUpdateForCustomerAccount(){
		goSaveOrUpdate();
		return "goSaveOrUpdateForCustomerAccount";
	}
	
	public String saveOrUpdateForCustomerAccount(){
		saveOrUpdate();
		return UPDATE;
	}

	public String goListContentForCustomerAccount(){
		Map<String,Object> params = getParams();
		if(null != id && !"".equals(id)){
			params.put("customerAccount.id,"+SearchCondition.EQUAL, id);
		}
		goListContent();
		return "goSingleListForCustomerAccount";
	}
	
	public String goChooseType(){
		return "goChooseType";
	}
	
	private List<ContactPerson> contactPersonList = new ArrayList<ContactPerson>();
	public String loadCustomerContactPerson(){
		try{
			if(null != id && !"".equals(id)){
				customerService = baseHibernateService.findEntityById(CustomerServices.class, id);
			}
			if(null != customerAccountId && !customerAccountId.equals("")){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, customerAccountId);
				if(null != customerAccount && null != customerAccount.getContactPersons() && customerAccount.getContactPersons().size() > 0){
					contactPersonList.addAll(customerAccount.getContactPersons());
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "loadCustomerContactPerson";
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

	public CustomerServices getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerServices customerService) {
		this.customerService = customerService;
	}

	public List<ServiceType> getServiceTypeList() {
		return serviceTypeList;
	}

	public void setServiceTypeList(List<ServiceType> serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}

	public List<ServiceMode> getServiceModeList() {
		return serviceModeList;
	}

	public void setServiceModeList(List<ServiceMode> serviceModeList) {
		this.serviceModeList = serviceModeList;
	}

	public List<ConsumeTime> getConsumeTimeList() {
		return consumeTimeList;
	}

	public void setConsumeTimeList(List<ConsumeTime> consumeTimeList) {
		this.consumeTimeList = consumeTimeList;
	}

	public List<CustomerServiceStatus> getCustomerServiceStatusList() {
		return customerServiceStatusList;
	}

	public void setCustomerServiceStatusList(
			List<CustomerServiceStatus> customerServiceStatusList) {
		this.customerServiceStatusList = customerServiceStatusList;
	}

	public List<String> getStartTimeList() {
		return startTimeList;
	}

	public void setStartTimeList(List<String> startTimeList) {
		this.startTimeList = startTimeList;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getCustomerAccountId() {
		return customerAccountId;
	}

	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public List<ContactPerson> getContactPersonList() {
		return contactPersonList;
	}

	public void setContactPersonList(List<ContactPerson> contactPersonList) {
		this.contactPersonList = contactPersonList;
	}
}
