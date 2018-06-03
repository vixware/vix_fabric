package com.vix.crm.customer.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.core.utils.StrUtils;
import com.vix.crm.base.entity.ContactPersonType;
import com.vix.crm.base.entity.CredentialType;
import com.vix.crm.base.entity.CustomerSource;
import com.vix.crm.base.entity.CustomerStage;
import com.vix.crm.base.entity.CustomerType;
import com.vix.crm.base.entity.HotLevel;
import com.vix.crm.base.entity.Industry;
import com.vix.crm.base.entity.RelationshipClass;
import com.vix.crm.base.entity.StaffScale;
import com.vix.crm.customer.entity.CustomerAccountCategory;
import com.vix.crm.salechance.entity.BackSectionRecord;
import com.vix.crm.salechance.entity.BackSectionRecordTotal;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.service.ICustomerAccountService;

@Controller
@Scope("prototype")
public class CrmCustomerAccountAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICustomerAccountService customerAccountService;
	
	private String id;
	private String ids;
	private CustomerAccount customerAccount;
	private ContactPerson contactPerson;
	private String pageNo;
	private List<HotLevel> hotLevelList;
	private List<CustomerType> customerTypeList;
	private List<CustomerSource> customerSourceList;
	private List<CustomerStage> customerStageList;
	private List<Industry> industryList;
	private List<RelationshipClass> relationshipClassList;
	private List<StaffScale> staffScaleList;
	private List<CredentialType> credentialTypeList;
	private List<ContactPersonType> contactPersonTypeList;
	private String customerAccountType;//客户类型
	
	private List<CustomerAccount> caList;

	@Override
	public String goList(){
		try{
			caList = customerAccountService.findAllByEntityClass(CustomerAccount.class);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "goList";
	}
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			StringBuilder hqlBuilder = new StringBuilder("select ca from CustomerAccount ca where ca.isTemp = :isTemp and ca.isDeleted = :isDeleted ");
			Map<String,Object> params = getParams();
			params.put("isTemp,"+SearchCondition.EQUAL, "0");
			params.put("isDeleted,"+SearchCondition.EQUAL, "0");
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			String name = getRequestParameter("name");
			if(null != name && !"".equals(name)){
				name = decode(name, "UTF-8");
				params.put("name,"+SearchCondition.ANYLIKE, name);
				hqlBuilder.append("and ca.name like :name ");
			}
			Employee emp = getEmployee();
			if (null != emp && StrUtils.objectIsNotNull(emp.getId())) {
				params.put("creator," + SearchCondition.EQUAL, emp.getId());
				hqlBuilder.append("and ca.creator = :creator ");
			}
			if(null != id && !"".equals(id)){
				params.put("customerAccountCategory.id,"+SearchCondition.EQUAL, id);
				hqlBuilder.append(" and ca.customerAccountCategory.id = :customerAccountCategory_id ");
			}
			params.put("isHighSea,"+SearchCondition.EQUAL, "1");
			hqlBuilder.append(" or ca.isHighSea = :isHighSea ");
			if(null != getPager().getOrderField()){
				hqlBuilder.append(" order by ca.");
				hqlBuilder.append(getPager().getOrderField());
				if(null != getPager().getOrderBy()){
					hqlBuilder.append(" ");
					hqlBuilder.append(getPager().getOrderBy());
				}
			}
			customerAccountService.findPagerByHql(getPager(), "ca", hqlBuilder.toString(), params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goSubSingleList(){
		try {
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			StringBuilder hqlBuilder = new StringBuilder("select ca from CustomerAccount ca where ca.isTemp = :isTemp and ca.isDeleted = :isDeleted ");
			Map<String,Object> params = getParams();
			params.put("isTemp,"+SearchCondition.EQUAL, "0");
			params.put("isDeleted,"+SearchCondition.EQUAL, "0");
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Employee emp = getEmployee();
			if (null != emp && StrUtils.objectIsNotNull(emp.getId())) {
				params.put("creator," + SearchCondition.EQUAL, emp.getId());
				hqlBuilder.append("and ca.creator = :creator ");
			}
			params.put("isHighSea,"+SearchCondition.EQUAL, "1");
			hqlBuilder.append(" or ca.isHighSea = :isHighSea ");
			if(null != getPager().getOrderField()){
				hqlBuilder.append(" order by ca.");
				hqlBuilder.append(getPager().getOrderField());
				if(null != getPager().getOrderBy()){
					hqlBuilder.append(" ");
					hqlBuilder.append(getPager().getOrderBy());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			customerTypeList = customerAccountService.findAllByEntityClassAndAttribute(CustomerType.class, "enable", "1");
			relationshipClassList = customerAccountService.findAllByEntityClassAndAttribute(RelationshipClass.class, "enable", "1");
			customerSourceList = customerAccountService.findAllByEntityClassAndAttribute(CustomerSource.class, "enable", "1");
			customerStageList = customerAccountService.findAllByEntityClassAndAttribute(CustomerStage.class, "enable", "1");
			hotLevelList = customerAccountService.findAllByEntityClassAndAttribute(HotLevel.class, "enable", "1");
			if("enterPrise".equals(customerAccountType)){
				industryList = customerAccountService.findAllByEntityClassAndAttribute(Industry.class, "enable", "1");
				staffScaleList = customerAccountService.findAllByEntityClassAndAttribute(StaffScale.class, "enable", "1");
			}else{
				credentialTypeList = customerAccountService.findAllByEntityClassAndAttribute(CredentialType.class, "enable", "1");
				contactPersonTypeList = customerAccountService.findAllByEntityClassAndAttribute(ContactPersonType.class, "enable", "1");
			}
			if(null != id && !"".equals(id)){
				customerAccount = customerAccountService.findEntityById(CustomerAccount.class,id);
			}else{
				customerAccount = new CustomerAccount();
				String categoryId = getRequestParameter("categoryId");
				if(null != categoryId && !"".equals(categoryId)){
					CustomerAccountCategory cac = customerAccountService.findEntityById(CustomerAccountCategory.class, categoryId);
					customerAccount.setCustomerAccountCategory(cac);
				}
				customerAccount.setIsTemp("1");
				customerAccount = customerAccountService.merge(customerAccount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerAccountType;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != customerAccount.getId()){
				isSave = false;
			}else{
				customerAccount.setCreateTime(new Date());
				loadCommonData(customerAccount);
			}
			if(null == customerAccount.getHotLevel() || null == customerAccount.getHotLevel().getId() || !customerAccount.getHotLevel().getId().equals("") || !customerAccount.getHotLevel().getId().equals("0")){
				customerAccount.setHotLevel(null);
			}
			if(null == customerAccount.getCustomerType() || null == customerAccount.getCustomerType().getId() || !customerAccount.getCustomerType().equals("") || !customerAccount.getCustomerType().getId().equals("0")){
				customerAccount.setCustomerType(null);
			}
			if(null == customerAccount.getCustomerSource() || null == customerAccount.getCustomerSource().getId() || !customerAccount.getCustomerSource().equals("") || !customerAccount.getCustomerSource().getId().equals("0")){
				customerAccount.setCustomerSource(null);
			}
			if(null == customerAccount.getCustomerStage() || null == customerAccount.getCustomerStage().getId() || !customerAccount.getCustomerStage().equals("") || !customerAccount.getCustomerStage().getId().equals("0")){
				customerAccount.setCustomerStage(null);
			}
			if(null == customerAccount.getIndustry() || null == customerAccount.getIndustry().getId() || !customerAccount.getIndustry().equals("") || !customerAccount.getIndustry().getId().equals("0")){
				customerAccount.setIndustry(null);
			}
			if(null == customerAccount.getRelationshipClass() || null == customerAccount.getRelationshipClass().getId() || !customerAccount.getRelationshipClass().equals("") || !customerAccount.getRelationshipClass().getId().equals("0")){
				customerAccount.setRelationshipClass(null);
			}
			if(null == customerAccount.getStaffScale() || null == customerAccount.getStaffScale().getId() || !customerAccount.getStaffScale().equals("") || !customerAccount.getStaffScale().getId().equals("0")){
				customerAccount.setStaffScale(null);
			}
			customerAccount = customerAccountService.merge(customerAccount);
			if("personal".equals(customerAccount.getType())){
				if(null == contactPerson.getContactPersonType() || null == contactPerson.getContactPersonType().getId() || !contactPerson.getContactPersonType().getId().equals("") || !contactPerson.getContactPersonType().getId().equals("0")){
					contactPerson.setContactPersonType(null);
				}
				if(null == contactPerson.getCredentialType() || null == contactPerson.getCredentialType().getId() || !contactPerson.getCredentialType().getId().equals("") || !contactPerson.getCredentialType().getId().equals("0")){
					contactPerson.setCredentialType(null);
				}
				contactPerson.setCustomerAccount(customerAccount);
				contactPerson = customerAccountService.merge(contactPerson);
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
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			CustomerAccount pb = customerAccountService.findEntityById(CustomerAccount.class,id);
			if(null != pb){
				customerAccountService.deleteByEntity(pb);
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
				customerAccountService.batchDelete(CustomerAccount.class,delIds);
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
	
	public String goChooseCustomerAccount(){
		return "goChooseCustomerAccount";
	}

	public void getContactPersonJson(){
		try {
			String json = "";
			String id = getRequestParameter("id");
			if(null != id && !"".equals(id)){
				CustomerAccount ca = customerAccountService.findEntityById(CustomerAccount.class,id);
				if(null != ca){
					json = convertListToJson(new ArrayList<ContactPerson>(ca.getContactPersons()),ca.getContactPersons().size(),"customerAccount");
				}
			}
			if(!"".equals(json)){
				renderJson(json);
			}else{
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goCustomerView(){
		try{
			customerTypeList = customerAccountService.findAllByEntityClassAndAttribute(CustomerType.class, "enable", "1");
			relationshipClassList = customerAccountService.findAllByEntityClassAndAttribute(RelationshipClass.class, "enable", "1");
			customerSourceList = customerAccountService.findAllByEntityClassAndAttribute(CustomerSource.class, "enable", "1");
			customerStageList = customerAccountService.findAllByEntityClassAndAttribute(CustomerStage.class, "enable", "1");
			hotLevelList = customerAccountService.findAllByEntityClassAndAttribute(HotLevel.class, "enable", "1");
			if(null != id && !"".equals(id)){
				customerAccount = customerAccountService.findEntityById(CustomerAccount.class,id);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		if(null !=customerAccountType && "enterPrise".equals(customerAccountType)){
			return "enterPriseView";
		}else{
			return "personalView";
		}
	}
	
	private Long year;
	private Long preYear;
	private Long nextYear;
	private List<BackSectionRecordTotal> bsrtList;
	public String goReconciliation(){
		try{
			int month = 12;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			if(!StrUtils.objectIsNotNull(year)){
				String y = sdf.format(new Date());
				year = Long.parseLong(y);
				preYear = year - 1;
				nextYear = 0l;
				Calendar cal = new GregorianCalendar();
				month = cal.get(Calendar.MONTH)+1;
				if(month == 13){
					month = 12;
				}
			}else{
				String y = sdf.format(new Date());
				Long currentYear = Long.parseLong(y);
				preYear = year - 1;
				if(currentYear.longValue() > year.longValue()){
					nextYear = year +1;
				}else{
					Calendar cal = new GregorianCalendar();
					month = cal.get(Calendar.MONTH)+1;
					if(month == 13){
						month = 12;
					}
				}
			}
			bsrtList = new ArrayList<BackSectionRecordTotal>();
			for(int i=1;i<=month;i++){
				bsrtList.add(backSectionRecordTotalByMonth(i));
			}
			for(int i=0;i<month;i++){
				if(i > 0){
					BackSectionRecordTotal bsrt1 = bsrtList.get(i-1);
					BackSectionRecordTotal bsrt2 = bsrtList.get(i);
					bsrt2.setPreviousMonthAmount(bsrt1.getCurrentMonthAmount()+bsrt1.getPreviousMonthAmount());
				}
			}
			year--;
			BackSectionRecordTotal preYearLastMonth = backSectionRecordTotalByMonth(12);
			bsrtList.get(0).setPreviousMonthAmount(preYearLastMonth.getCurrentMonthAmount());
			year++;
			String caId = getRequestParameter("customerAccountId");
			if(null != caId && !"".equals(caId)){
				customerAccount = customerAccountService.findEntityById(CustomerAccount.class, caId);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "goReconciliation";
	}

	public BackSectionRecordTotal backSectionRecordTotalByMonth(long month) throws Exception {
		BackSectionRecordTotal bsrt = new BackSectionRecordTotal();
		bsrt.setMonth(month);
		Map<String,Object> params = new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sDateStr = year + "-" + month + "-01";
		String eDateStr = year + "-" + month + "-01";
		String sDate = DateUtil.format(DateUtil.getFirstDayOfMonth(sdf.parse(sDateStr)), "yyyy-MM-dd");
		String eDate = DateUtil.format(DateUtil.getLastDayOfMonth(sdf.parse(eDateStr)), "yyyy-MM-dd");
		params.put("backSectionDate,"+SearchCondition.BETWEENAND, sDate + " 00:00:01!" + eDate+" 23:59:59");
		String caId = getRequestParameter("customerAccountId");
		if(null != caId && !"".equals(caId)){
			params.put("customerAccount.id,"+SearchCondition.EQUAL,Long.parseLong(caId));
		}
		List<BackSectionRecord> list = customerAccountService.findAllByConditions(BackSectionRecord.class, params);
		if(null != list){
			double amountTotal = 0l;
			for(BackSectionRecord bsr : list){
				if(null != bsr){
					amountTotal += bsr.getAmount();
				}
			}
			bsrt.setCurrentMonthAmount(amountTotal);
		}
		return bsrt;
	}
	
	public String showReconciliationDetail(){
		try{
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			String caId = getRequestParameter("customerAccountId");
			if(null != caId && !"".equals(caId)){
				params.put("customerAccount.id,"+SearchCondition.EQUAL,Long.parseLong(caId));
				customerAccount = customerAccountService.findEntityById(CustomerAccount.class, caId);
			}
			String y = getRequestParameter("year");
			String m = getRequestParameter("month");
			if(null != y && !"".equals(y) && null != m && !"".equals(m)){
				if(m.length() == 1){
					m = "0" + m;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String sDateStr = y + "-" + m + "-01";
				String eDateStr = y + "-" + m + "-01";
				String sDate = DateUtil.format(DateUtil.getFirstDayOfMonth(sdf.parse(sDateStr)), "yyyy-MM-dd");
				String eDate = DateUtil.format(DateUtil.getLastDayOfMonth(sdf.parse(eDateStr)), "yyyy-MM-dd");
				params.put("backSectionDate,"+SearchCondition.BETWEENAND, sDate + " 00:00:01!" + eDate+" 23:59:59");
				customerAccountService.findPagerByHqlConditions(getPager(), BackSectionRecord.class, params);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "showReconciliationDetail";
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

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getPreYear() {
		return preYear;
	}

	public void setPreYear(Long preYear) {
		this.preYear = preYear;
	}

	public Long getNextYear() {
		return nextYear;
	}

	public void setNextYear(Long nextYear) {
		this.nextYear = nextYear;
	}

	public List<HotLevel> getHotLevelList() {
		return hotLevelList;
	}

	public void setHotLevelList(List<HotLevel> hotLevelList) {
		this.hotLevelList = hotLevelList;
	}

	public List<CustomerType> getCustomerTypeList() {
		return customerTypeList;
	}

	public void setCustomerTypeList(List<CustomerType> customerTypeList) {
		this.customerTypeList = customerTypeList;
	}

	public List<CustomerSource> getCustomerSourceList() {
		return customerSourceList;
	}

	public void setCustomerSourceList(List<CustomerSource> customerSourceList) {
		this.customerSourceList = customerSourceList;
	}

	public List<CustomerStage> getCustomerStageList() {
		return customerStageList;
	}

	public void setCustomerStageList(List<CustomerStage> customerStageList) {
		this.customerStageList = customerStageList;
	}

	public List<Industry> getIndustryList() {
		return industryList;
	}

	public void setIndustryList(List<Industry> industryList) {
		this.industryList = industryList;
	}

	public List<RelationshipClass> getRelationshipClassList() {
		return relationshipClassList;
	}

	public void setRelationshipClassList(
			List<RelationshipClass> relationshipClassList) {
		this.relationshipClassList = relationshipClassList;
	}

	public List<StaffScale> getStaffScaleList() {
		return staffScaleList;
	}

	public void setStaffScaleList(List<StaffScale> staffScaleList) {
		this.staffScaleList = staffScaleList;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public List<CredentialType> getCredentialTypeList() {
		return credentialTypeList;
	}

	public void setCredentialTypeList(List<CredentialType> credentialTypeList) {
		this.credentialTypeList = credentialTypeList;
	}

	public List<ContactPersonType> getContactPersonTypeList() {
		return contactPersonTypeList;
	}

	public void setContactPersonTypeList(
			List<ContactPersonType> contactPersonTypeList) {
		this.contactPersonTypeList = contactPersonTypeList;
	}

	public String getCustomerAccountType() {
		return customerAccountType;
	}

	public void setCustomerAccountType(String customerAccountType) {
		this.customerAccountType = customerAccountType;
	}

	public List<CustomerAccount> getCaList() {
		return caList;
	}

	public void setCaList(List<CustomerAccount> caList) {
		this.caList = caList;
	}

	public List<BackSectionRecordTotal> getBsrtList() {
		return bsrtList;
	}

	public void setBsrtList(List<BackSectionRecordTotal> bsrtList) {
		this.bsrtList = bsrtList;
	}
}
