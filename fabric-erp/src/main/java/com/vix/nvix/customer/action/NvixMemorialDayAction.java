package com.vix.nvix.customer.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.MemorialDayType;
import com.vix.crm.customer.entity.MemorialDay;
import com.vix.mdm.crm.entity.ContactPerson;

@Controller
@Scope("prototype")
public class NvixMemorialDayAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private MemorialDay memorialDay;
	private String entityName;
	private Integer memorialDayNum;
	
	@Override
	public String goList(){
		try{
			Map<String, Object> params = getParams();
			params.put("nextDateStr," + SearchCondition.EQUAL, DateUtil.format(new Date()));
			List<MemorialDay> memorialDays = baseHibernateService.findAllByConditions(MemorialDay.class, params);
			memorialDayNum = memorialDays.size();
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_LIST;
	}
	
	public String goMemorialDayList() {
		return "goMemorialDayList";
	}
	
	public void goMemorialDayListContent(){
		try{
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			params.put("nextDateStr," + SearchCondition.EQUAL, DateUtil.format(new Date()));
			pager = baseHibernateService.findPagerByHqlConditions(getPager(), MemorialDay.class, params);
			renderDataTable(pager, null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void goListContent(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			String customerName = getRequestParameter("customerName");
			if(StrUtils.objectIsNotNull(customerName)){
				customerName = decode(customerName, "UTF-8");
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, customerName);
			}
			pager = baseHibernateService.findPagerByHqlConditions(getPager(), MemorialDay.class, params);
			renderDataTable(pager, null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	private List<MemorialDayType> memorialDayTypeList;
	private List<ContactPerson> contactPersonList;
	public String goSaveOrUpdate() {
		try {
			memorialDayTypeList = baseHibernateService.findAllByEntityClassAndAttribute(MemorialDayType.class, "enable", "1");
			if (null != id && !"".equals(id)) {
				memorialDay = baseHibernateService.findEntityById(MemorialDay.class, id);
				if(memorialDay.getCustomerAccount() != null && StrUtils.isNotEmpty(memorialDay.getCustomerAccount().getId())){
					contactPersonList = baseHibernateService.findAllByEntityClassAndAttribute(ContactPerson.class, "customerAccount.id", memorialDay.getCustomerAccount().getId());
				}
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
				memorialDay.setUpdateTime(new Date());
			}else{
				memorialDay.setCreateTime(new Date());
				memorialDay.setUpdateTime(new Date());
				loadCommonData(memorialDay);
			}
			if(null != memorialDay && memorialDay.getDate() != null){
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
				int year = DateUtil.getYear();
				String time = year + "-" + sdf.format(memorialDay.getDate());
				long start = DateUtil.praseSqlDate(time).getTime();
				long end = DateUtil.praseSqlDate(DateUtil.format(new Date())).getTime();
				if(start < end){
					memorialDay.setNextDateStr((year + 1) + "-" + sdf.format(memorialDay.getDate()));
				}else{
					memorialDay.setNextDateStr(time);
				}
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
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	
	/** 处理删除操作 */
	public void deleteById() {
		try {
			MemorialDay pb = baseHibernateService.findEntityById(MemorialDay.class,id);
			if(null != pb){
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

	public MemorialDay getMemorialDay() {
		return memorialDay;
	}

	public void setMemorialDay(MemorialDay memorialDay) {
		this.memorialDay = memorialDay;
	}

	public List<MemorialDayType> getMemorialDayTypeList() {
		return memorialDayTypeList;
	}

	public void setMemorialDayTypeList(List<MemorialDayType> memorialDayTypeList) {
		this.memorialDayTypeList = memorialDayTypeList;
	}

	public List<ContactPerson> getContactPersonList() {
		return contactPersonList;
	}

	public void setContactPersonList(List<ContactPerson> contactPersonList) {
		this.contactPersonList = contactPersonList;
	}

	public Integer getMemorialDayNum() {
		return memorialDayNum;
	}

	public void setMemorialDayNum(Integer memorialDayNum) {
		this.memorialDayNum = memorialDayNum;
	}

}