package com.vix.nvix.customer.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.customer.entity.CustomerAccountCategory;
import com.vix.nvix.common.base.service.IVixntBaseService;

@Controller
@Scope("prototype")
public class NvixCustomerCategoryAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IVixntBaseService vixntBaseService;

	private String id;
	private String parentId;
	private String name;
	private CustomerAccountCategory customerAccountCategory;
	private String pageNo;
	
	/** 获取列表数据  */
	public void getCustomerAccountCategoryJson(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = getPager();
			if(null != pageNo && !"".equals(pageNo)){
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			String code = getRequestParameter("code");
			if(null != code && !"".equals(code)){
				params.put("code,"+SearchCondition.ANYLIKE, code);
			}
			if(null != name && !"".equals(name)){
				name = decode(name, "UTF-8");
				params.put("name,"+SearchCondition.ANYLIKE, name);
			}
			if(StringUtils.isNotEmpty(parentId) && !parentId.equals("0")){
				params.put("parentCustomerAccountCategory.id,"+SearchCondition.EQUAL, parentId);
			}else{
				params.put("parentCustomerAccountCategory.id,"+SearchCondition.IS, "NULL");
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, CustomerAccountCategory.class, params);
			renderDataTable(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String goSubSingleList(){
		try {
			Map<String,Object> params = getParams();
			getPager().setPageSize(6);
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(),CustomerAccountCategory.class,params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				customerAccountCategory = vixntBaseService.findEntityById(CustomerAccountCategory.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			}else{
				if(StringUtils.isNotEmpty(parentId) && !parentId.equals("0")){
					customerAccountCategory = new CustomerAccountCategory();
					CustomerAccountCategory cac = vixntBaseService.findEntityById(CustomerAccountCategory.class, parentId);
					customerAccountCategory.setParentCustomerAccountCategory(cac);
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
			if(null != customerAccountCategory.getId()){
				isSave = false;
			}
			if(null == customerAccountCategory.getParentCustomerAccountCategory() || StrUtils.isEmpty(customerAccountCategory.getParentCustomerAccountCategory().getId())){
				customerAccountCategory.setParentCustomerAccountCategory(null);
			}
			customerAccountCategory = vixntBaseService.merge(customerAccountCategory);
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
			customerAccountCategory = vixntBaseService.findEntityById(CustomerAccountCategory.class, id);
			if(null != customerAccountCategory){
				vixntBaseService.deleteByEntity(customerAccountCategory);
				renderText(DELETE_SUCCESS);
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
			List<CustomerAccountCategory> customerAccountCategoryList = new ArrayList<CustomerAccountCategory>();
			/** 获取查询参数 */
			Map<String,Object> params = getParams();
			if(null!=id && !"".equals(id)){
				customerAccountCategoryList = vixntBaseService.findAllSubEntity(CustomerAccountCategory.class,"parentCustomerAccountCategory.id", id, params);
			}else{
				customerAccountCategoryList = vixntBaseService.findAllSubEntity(CustomerAccountCategory.class,"parentCustomerAccountCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllCustomerAccountCategory(strSb,customerAccountCategoryList);
			strSb.append("]");
			renderHtml(strSb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private StringBuilder loadAllCustomerAccountCategory(StringBuilder strSb,List<CustomerAccountCategory> customerAccountCategoryList) throws Exception{
		for(int i =0;i<customerAccountCategoryList.size();i++){
			CustomerAccountCategory ic = customerAccountCategoryList.get(i);
			if(ic.getSubCustomerAccountCategorys().size() > 0){
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:true,isParent:true,children:[");
				loadAllCustomerAccountCategory(strSb,new ArrayList<CustomerAccountCategory>(ic.getSubCustomerAccountCategorys()));
				strSb.append("]}");
			}else{
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:false,isParent:false}");
			}
			if(i < customerAccountCategoryList.size() -1){
				strSb.append(",");
			}
		}
		return strSb;
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public CustomerAccountCategory getCustomerAccountCategory() {
		return customerAccountCategory;
	}

	public void setCustomerAccountCategory(CustomerAccountCategory customerAccountCategory) {
		this.customerAccountCategory = customerAccountCategory;
	}
}
