package com.vix.crm.customer.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.MemorialDayType;
import com.vix.crm.customer.entity.MemorialDay;
import com.vix.mdm.crm.entity.ContactPerson;

@Controller
@Scope("prototype")
public class MemorialDayAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String ids;
	private MemorialDay memorialDay;
	private String customerAccountId;
	private String pageNo;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			String name = getRequest().getParameter("name");
			if(null != name && !"".equals(name)){
				name = decode(name, "UTF-8");
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, name);
				params.put("tenantId,"+SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), MemorialDay.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	private List<MemorialDayType> memorialDayTypeList;
	public String goSaveOrUpdate() {
		try {
			memorialDayTypeList = baseHibernateService.findAllByEntityClassAndAttribute(MemorialDayType.class, "enable", "1");
			if(null != id && !"".equals(id)){
				memorialDay = baseHibernateService.findEntityById(MemorialDay.class,id);
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
			if(StrUtils.objectIsNotNull(memorialDay.getId())){
				isSave = false;
			}else{
				memorialDay.setCreateTime(new Date());
				loadCommonData(memorialDay);
			}
			
			String[] attrArray ={"customerAccount","contactPerson","memorialDayType"};
			checkEntityNullValue(memorialDay,attrArray);
			
			memorialDay = baseHibernateService.merge(memorialDay);
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
			MemorialDay pb = baseHibernateService.findEntityById(MemorialDay.class,id);
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
				baseHibernateService.batchDelete(MemorialDay.class, delIds);
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
	
	private List<ContactPerson> contactPersonList;
	public String loadCustomerContactPerson(){
		try {
			if(null != customerAccountId && !"".equals(customerAccountId)){
				contactPersonList = baseHibernateService.findAllByEntityClassAndAttribute(ContactPerson.class, "customerAccount.id", customerAccountId);
			}
			if(null != id && !"".equals(id)){
				memorialDay = baseHibernateService.findEntityById(MemorialDay.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return "loadCustomerContactPerson";
	}
	
	public String goFastList(){
		return "goFastList";
	}
	
	public String goChooseType(){
		return "goChooseType";
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

	public MemorialDay getMemorialDay() {
		return memorialDay;
	}

	public void setMemorialDay(MemorialDay memorialDay) {
		this.memorialDay = memorialDay;
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

	public List<MemorialDayType> getMemorialDayTypeList() {
		return memorialDayTypeList;
	}

	public void setMemorialDayTypeList(List<MemorialDayType> memorialDayTypeList) {
		this.memorialDayTypeList = memorialDayTypeList;
	}
}
