package com.vix.crm.salechance.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.salechance.entity.BackSectionPlan;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class BackSectionPlanAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String ids;
	private BackSectionPlan backSectionPlan;
	private String pageNo;
	
	private List<BackSectionPlan> indexList;

	@Override
	@SuppressWarnings("unchecked")
	public String goList(){
		try{
			Map<String,Object> params = getParams();
			getPager().setPageSize(20);
			getPager().setOrderField("id");
			getPager().setOrderBy("desc");
			params.put("customerAccount.id,"+SearchCondition.MORETHAN, 0l);
			baseHibernateService.findPagerByHqlConditions(getPager(), BackSectionPlan.class, params);
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
			String name = getRequestParameter("name");
			if(StrUtils.objectIsNotNull(name)){
				name = decode(name,"UTF-8");
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, name);
			}
			String owner = getRequestParameter("owner");
			if(StrUtils.objectIsNotNull(owner)){
				owner = decode(owner,"UTF-8");
				params.put("owner.name,"+ SearchCondition.ANYLIKE, owner);
			}
			String customerName = getRequestParameter("customerName");
			if(StrUtils.objectIsNotNull(customerName)){
				customerName = decode(customerName, "UTF-8");
				params.put("customerAccount.name," +SearchCondition.ANYLIKE, customerName);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), BackSectionPlan.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	// 高级搜索
	public String goSearch() {
		return "goSearch";
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(null != id && !"".equals(id)){
				backSectionPlan = baseHibernateService.findEntityById(BackSectionPlan.class,id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			}else{
				backSectionPlan = new BackSectionPlan();
				backSectionPlan.setBackSectionPlanDate(new Date());
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
			if(StrUtils.objectIsNotNull(backSectionPlan.getId())){
				isSave = false;
			}else{
				backSectionPlan.setCreateTime(new Date());
				loadCommonData(backSectionPlan);
			}
			
			String[] attrArray ={"customerAccount","crmProject","charger","owner"};
			checkEntityNullValue(backSectionPlan,attrArray);
			
			if(null != backSectionPlan.getCustomerAccount() && null != backSectionPlan.getCustomerAccount().getId() &&
					!backSectionPlan.getCustomerAccount().getId().equals("") && !backSectionPlan.getCustomerAccount().getId().equals("0")){
				CustomerAccount ca = baseHibernateService.findEntityById(CustomerAccount.class, backSectionPlan.getCustomerAccount().getId());
				String name = ca.getShorterName();
				String py = ChnToPinYin.getPYString(name);
				backSectionPlan.setChineseFirstLetter(py.toUpperCase());
			}
			backSectionPlan = baseHibernateService.merge(backSectionPlan);
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
			BackSectionPlan pb = baseHibernateService.findEntityById(BackSectionPlan.class,id);
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
				baseHibernateService.batchDelete(BackSectionPlan.class,delIds);
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
	
	public String goSaveOrUpdateForCrmProject(){
		goSaveOrUpdate();
		return "goSaveOrUpdateForCrmProject";
	}

	public String saveOrUpdateForCustomerAccount(){
		saveOrUpdate();
		return UPDATE;
	}
	
	public String saveOrUpdateForCrmProject(){
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
	
	public String goListContentForCrmProject(){
		Map<String,Object> params = getParams();
		if(null != id && !"".equals(id)){
			params.put("crmProject.id,"+SearchCondition.EQUAL, id);
		}
		goListContent();
		return "goSingleListForCrmProject";
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

	public BackSectionPlan getBackSectionPlan() {
		return backSectionPlan;
	}

	public void setBackSectionPlan(BackSectionPlan backSectionPlan) {
		this.backSectionPlan = backSectionPlan;
	}

	public List<BackSectionPlan> getIndexList() {
		return indexList;
	}

	public void setIndexList(List<BackSectionPlan> indexList) {
		this.indexList = indexList;
	}
}
