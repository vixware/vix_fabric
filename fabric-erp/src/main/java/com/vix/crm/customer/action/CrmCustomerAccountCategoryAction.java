package com.vix.crm.customer.action;

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
import com.vix.crm.customer.entity.CustomerAccountCategory;

@Controller
@Scope("prototype")
public class CrmCustomerAccountCategoryAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	
	/** 分类 */
	private CustomerAccountCategory customerAccountCategory;
	private String id;
	private String name;
	private String parentId;
	
	/** 获取列表数据  */
	@SuppressWarnings("unchecked")
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != parentId && !"".equals(parentId)){
				params.put("parentCustomerAccountCategory.id,"+SearchCondition.EQUAL, parentId);
			}
			if(null != name && !"".equals(name)){
				name = decode(name,"UTF-8");
				params.put("name,"+SearchCondition.ANYLIKE,name);
			}
			getPager().setPageSize(12);
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), CustomerAccountCategory.class, params);
			if(pager.getResultList().size() < 12){
				int listSize = pager.getResultList().size();
				for(int i=0; i<12-listSize; i++){
					pager.getResultList().add(new CustomerAccountCategory());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(null != id && !"".equals(id)){
				customerAccountCategory = baseHibernateService.findEntityById(CustomerAccountCategory.class, id);
			}else{
				customerAccountCategory = new CustomerAccountCategory();
				if(null != parentId && !parentId.equals("0")){
					CustomerAccountCategory pc = baseHibernateService.findEntityById(CustomerAccountCategory.class,parentId);
					if(null != pc){
						customerAccountCategory.setParentCustomerAccountCategory(pc);
					}
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
			if(StrUtils.objectIsNotNull(customerAccountCategory.getId())){
				isSave = false;
				customerAccountCategory.setUpdateTime(new Date());
			}else{
				customerAccountCategory.setCreateTime(new Date());
				loadCommonData(customerAccountCategory);
			}
			if(null == customerAccountCategory.getParentCustomerAccountCategory() || StrUtils.objectIsNull(customerAccountCategory.getParentCustomerAccountCategory().getId())){
				customerAccountCategory.setParentCustomerAccountCategory(null);
			}
			customerAccountCategory = baseHibernateService.merge(customerAccountCategory);
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
			CustomerAccountCategory pc = baseHibernateService.findEntityById(CustomerAccountCategory.class, id);
			if(null != pc){
				if(pc.getSubCustomerAccountCategorys().size()>0){
					setMessage("客户分类存在子分类不允许删除!");
				}else{
					baseHibernateService.deleteByEntity(pc);
					renderText(DELETE_SUCCESS);
				}
			}else{
				setMessage("客户分类不存在!");
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
			List<CustomerAccountCategory> listCustomerAccountCategory = new ArrayList<CustomerAccountCategory>();
			/** 获取查询参数 */
			Map<String,Object> params = getParams();
			if(null!=id && !"".equals(id)){
				listCustomerAccountCategory = baseHibernateService.findAllSubEntity(CustomerAccountCategory.class, "parentCustomerAccountCategory.id", id, params);
			}else{
				listCustomerAccountCategory = baseHibernateService.findAllSubEntity(CustomerAccountCategory.class, "parentCustomerAccountCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllCustomerAccountCategory(strSb,listCustomerAccountCategory);
			strSb.append("]");
			renderHtml(strSb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private StringBuilder loadAllCustomerAccountCategory(StringBuilder strSb,List<CustomerAccountCategory> listCustomerAccountCategory) throws Exception{
		for(int i =0;i<listCustomerAccountCategory.size();i++){
			CustomerAccountCategory pc = listCustomerAccountCategory.get(i);
			if(pc.getSubCustomerAccountCategorys().size() > 0){
				strSb.append("{id:\"");
				strSb.append(pc.getId());
				strSb.append("\",name:\"");
				strSb.append(pc.getName());
				strSb.append("\",open:true,isParent:true,children:[");
				loadAllCustomerAccountCategory(strSb,new ArrayList<CustomerAccountCategory>(pc.getSubCustomerAccountCategorys()));
				strSb.append("]}");
			}else{
				strSb.append("{id:\"");
				strSb.append(pc.getId());
				strSb.append("\",name:\"");
				strSb.append(pc.getName());
				strSb.append("\",open:false,isParent:false}");
			}
			if(i < listCustomerAccountCategory.size() -1){
				strSb.append(",");
			}
		}
		return strSb;
	}
	
	public String goChooseCustomerAccountCategory(){
		return "goChooseCustomerAccountCategory";
	}

	public CustomerAccountCategory getCustomerAccountCategory() {
		return customerAccountCategory;
	}

	public void setCustomerAccountCategory(
			CustomerAccountCategory customerAccountCategory) {
		this.customerAccountCategory = customerAccountCategory;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}