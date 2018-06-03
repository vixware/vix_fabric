package com.vix.crm.lead.action;

import java.util.ArrayList;
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
import com.vix.crm.lead.entity.SaleLead;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class SaleLeadAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String ids;
	private SaleLead saleLead;
	private String pageNo;
	
	private List<SaleLead> indexList;

	@Override
	@SuppressWarnings("unchecked")
	public String goList(){
		try{
			Map<String,Object> params = getParams();
			getPager().setPageSize(20);
			getPager().setOrderField("id");
			getPager().setOrderBy("desc");
			baseHibernateService.findPagerByHqlConditions(getPager(), SaleLead.class, params);
			indexList = getPager().getResultList();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "goList";
	}
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			String subject = getRequestParameter("subject");
			if(StrUtils.objectIsNotNull(subject)){
				subject = decode(subject, "UTF-8");
				params.put("subject,"+SearchCondition.ANYLIKE, subject);
			}
			
			String customerName = getRequestParameter("customerName");
			if(null != customerName && !"".trim().equals(customerName)){
				customerName = decode(customerName,"UTF-8");
				params.put("customerName," + SearchCondition.ANYLIKE, customerName);
			}
			
			String title = getRequestParameter("title");
			if(null != title && !"".trim().equals(title)){
				title = decode(title,"UTF-8");
				params.put("type," + SearchCondition.ANYLIKE, title);
			}
			
			params.put("isDeleted,"+SearchCondition.NOEQUAL, "1");
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), SaleLead.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	private List<CurrencyType> currencyTypeList;
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			List<ContactPerson> contactPersonList = new ArrayList<ContactPerson>();
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			if(null != id && !"".equals(id) && !"0".equals(id)){
				saleLead = baseHibernateService.findEntityById(SaleLead.class,id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			}else{
				saleLead = new SaleLead();
				saleLead.setFindOutTime(new Date());
			}
			ContactPerson c = saleLead.getContactPerson();
			if(null != c){
				contactPersonList.add(c);
			}else{
				contactPersonList.add(new ContactPerson());
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
			if(StrUtils.objectIsNotNull(saleLead.getId())){
				isSave = false;
			}else{
				saleLead.setCreateTime(new Date());
				saleLead.setIsDeleted("0");
				loadCommonData(saleLead);
			}
			
			String[] attrArray ={"customerAccount","currencyType"};
			checkEntityNullValue(saleLead,attrArray);
			
			String name = saleLead.getSubject();
			String py = ChnToPinYin.getPYString(name);
			saleLead.setChineseFirstLetter(py.toUpperCase());
			saleLead = baseHibernateService.merge(saleLead);
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
			SaleLead pb = baseHibernateService.findEntityById(SaleLead.class,id);
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
				baseHibernateService.batchDelete(SaleLead.class,delIds);
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
	
	// 高级搜索
	public String goSearch() {
		return "goSearch";
	}
	
	private List<ContactPerson> contactPersonList = new ArrayList<ContactPerson>();
	public String loadCustomerContactPerson(){
		try{
			if(null != id && !"".equals(id)){
				saleLead = baseHibernateService.findEntityById(SaleLead.class, id);
			}
			String customerAccountId = getRequestParameter("customerAccountId");
			if(null != customerAccountId && !"".equals(customerAccountId)){
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
	
	public String goChooseType(){
		return "goChooseType";
	}
	
	public String goTopDynamicMenuContent(){
		return "goTopDynamicMenuContent";
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

	public SaleLead getSaleLead() {
		return saleLead;
	}

	public void setSaleLead(SaleLead saleLead) {
		this.saleLead = saleLead;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public List<ContactPerson> getContactPersonList() {
		return contactPersonList;
	}

	public void setContactPersonList(List<ContactPerson> contactPersonList) {
		this.contactPersonList = contactPersonList;
	}

	public List<SaleLead> getIndexList() {
		return indexList;
	}

	public void setIndexList(List<SaleLead> indexList) {
		this.indexList = indexList;
	}
}
