package com.rest.app.crm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rest.core.base.BaseRestController;
import com.vix.core.utils.StrUtils;
import com.vix.core.validation.ValidateException;
import com.vix.core.validation.ValidatonUtil;
import com.vix.core.validation.Validator;
import com.vix.core.web.Pager;
import com.vix.crm.project.entity.CrmProject;
import com.vix.crm.salechance.entity.SaleChance;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.service.ICustomerAccountService;

/**
 * @ClassName: customerAccountModuleAppRestController
 * @Description: App customerAccount 客户    服务 
 * @author Bluesnow
 * 
 */

@Controller
@RequestMapping(value = "restService/app/crm/customerAccount")
public class CrmCustomerAccountController extends BaseRestController{

	private static Logger logger = LoggerFactory.getLogger(CrmCustomerAccountController.class);

	@Resource(name="customerAccountService")
	private ICustomerAccountService customerAccountService;
	
	/**
	 *	数据列表
	 */
	@RequestMapping(value="findAll.rs",method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	//@ResponseBody
	public void findAll(HttpServletRequest request, HttpServletResponse response, CustomerAccount customerAccount) throws Exception {
		List<CustomerAccount> calist = customerAccountService.findAllByEntityClass(CustomerAccount.class);
		List<CustomerAccount> resList = new ArrayList<CustomerAccount>();
		if(null != calist && calist.size() > 0){
			for(CustomerAccount c : calist){
				CustomerAccount cTemp = new CustomerAccount();
				BeanUtils.copyProperties(c, cTemp,new String[]{"hotLevel","customerAccountCategory","customerType","customerSource",
						"customerStage","industry","relationshipClass","staffScale","memberLevel","channelDistributor",
						"customerCreditInfos","customerAccountCategory","customerAccountGroup","contactPersons","saleChances",
						"saleLeads","customerComplaints","customerServices","memberTags","subCouponDetails","memberLevel"});
				resList.add(cTemp);
			}
		}
		renderResult(response,resList);
	}
	
	/**
	 * 分页参数 :    pageNo  第几页    
	 * 			  pageSize  每页的数量
	 */
	@RequestMapping(value="findCustomerAccountPager.rs",method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	public void findCustomerAccountPager(HttpServletRequest request, HttpServletResponse response, Pager pager,CustomerAccount customerAccount) throws Exception {
		Map<String,Object> searchParam = new HashMap<String,Object>();
		if(StrUtils.isNotEmpty(customerAccount.getName())){
			searchParam.put("name", customerAccount.getName());
		}
		if(StrUtils.isNotEmpty(customerAccount.getShorterName())){
			searchParam.put("shorterName", customerAccount.getShorterName());
		}
		//如下的数据查询需自行实现
		Pager pagerRes = customerAccountService.findPagerByHqlConditions(pager, CustomerAccount.class, searchParam);
		renderResultPager(response,pagerRes);
	}
	

	/**
	 * @Title: post
	 * @Description: 单一对象查询
	 */
	@RequestMapping(value = "query.rs", method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		logger.info("单一查询:{}.",id);
		CustomerAccount cTemp = null;
		Map<String,Object> params = new HashMap<String,Object>(); 
		Integer contactPersons = 0;//联系人数量
		Integer crmProject = 0;//项目数量
		Integer saleChance = 0;//销售机会数量
//		Integer aa = 0;//合同数量（没有绑定客户关系  当前无法提供合同数量）
		Map<String,Object> param = new HashMap<String,Object>();
		if(StrUtils.isNotEmpty(id)){
			CustomerAccount customerAccount = customerAccountService.findEntityByAttributeNoTenantId(CustomerAccount.class, "id",id);
			if(null != customerAccount){
				contactPersons = customerAccount.getContactPersons().size();
				params.put("contactPersons", contactPersons);
				String cId = customerAccount.getId();
				param.put("customerAccount.id", cId);
				List<CrmProject> cList = customerAccountService.findAllByConditions(CrmProject.class, param);
				List<SaleChance> sList = customerAccountService.findAllByConditions(SaleChance.class, param);
				if(null != cList && cList.size() > 0){
					crmProject = cList.size();
				}else{
					crmProject = 0;
				}
				if(null != sList && sList.size() > 0){
					saleChance = sList.size();
				}else{
					saleChance = 0;
				}
				params.put("crmProject", crmProject);
				params.put("saleChance", saleChance);
			}
			if (customerAccount == null) {
				throw new ValidateException("没有查询到id="+id+"的客户！");
			}else{
				cTemp = new CustomerAccount();
				BeanUtils.copyProperties(customerAccount, cTemp, new String[]{"hotLevel","customerAccountCategory","customerType",
						"customerSource","customerStage","industry","relationshipClass","staffScale","memberLevel","channelDistributor",
						"customerCreditInfos","customerAccountCategory","customerAccountGroup","contactPersons","saleChances",
						"saleLeads","customerComplaints","customerServices","memberTags","subCouponDetails","memberLevel"});
			}
			params.put("cTemp", cTemp);
		}else{
			throw new ValidateException("没有查询到id="+id+"的客户！");
		}
		renderResult(response,params);
	}
	
	/**
	 * @Title: saveOrUpdate
	 * @Description: 保存  和  更新
	 */
	@RequestMapping(value="saveOrUpdate.rs",method = RequestMethod.POST)//, consumes = MediaTypes.JSON
	public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response, CustomerAccount customerAccount) throws Exception {//@RequestBody 
		logger.info("保存.");
		//方式2 使用简单验证器
		Map<String,String> valMsgMap = ValidatonUtil.validator(new Validator(){
				@Override
				protected void validate() {
					//String field, String errorKey, String errorMessage
					//field 对应的实体的属性， errorKey 是自定义的唯一标识，errorMessage 是错误提示
					validateRequiredString("shorterName", "shorterNameMsg", "请输入客户简称!");
					validateRequiredString("name", "nameMsg", "请输入客户名称!");
					validateRequiredString("code", "codeMsg", "请输入客户编码编码!");
					/** 客户类型： enterPrise 企业 ，personal 个人 ,internal 内部,member 会员 */
					validateRequiredString("type", "typeMsg", "请输指定客户类型!");
				}
			}, customerAccount, false);
		if(!valMsgMap.isEmpty()){
			renderResultNotValid(response, valMsgMap);
			return;
		}
		//保存和更新代码待实现
		customerAccount = customerAccountService.mergeOriginal(customerAccount);
		CustomerAccount cTemp = new CustomerAccount();
		BeanUtils.copyProperties(customerAccount, cTemp,new String[]{"hotLevel","customerAccountCategory","customerType","customerSource",
				"customerStage","industry","relationshipClass","staffScale","memberLevel","channelDistributor","customerCreditInfos",
				"customerAccountCategory","customerAccountGroup","contactPersons","saleChances","saleLeads","customerComplaints",
				"customerServices","memberTags","subCouponDetails","memberLevel"});
		renderResult(response,cTemp);
	}
	
	/**
	 *   删除
	 */
	@RequestMapping(value = "delete.rs", method = RequestMethod.DELETE)//或者不使用  DELETE 二是 添加value进行单独映射
	public void delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		logger.info("删除:{}.",id);
		// 删除代码待具体实现
		if(StrUtils.isNotEmpty(id)){
			CustomerAccount customerAccount = customerAccountService.findEntityByAttributeNoTenantId(CustomerAccount.class,"id", id);
			if(null == customerAccount){
				throw new ValidateException("没有查询到id="+id+"的客户！");
			}else{
				customerAccountService.deleteById(CustomerAccount.class, id);
			}
		}
		renderResult(response,null);
	}
	
	/**
	 *  获取当前联系人列表
	 * */
	
	@RequestMapping(value = "queryContentPersons.rs", method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	public void queryContentPersons(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		logger.info("单一查询:{}.",id);
		Map<String, Object> params = new HashMap<String,Object>();
		if(StrUtils.isNotEmpty(id)){
			params.put("customerAccount.id", id);
			List<ContactPerson> cList = customerAccountService.findAllByConditions(ContactPerson.class, params);
			if (cList.size() == 0) {
				throw new ValidateException("该客户没有添加联系人！");
			}else{
				List<ContactPerson> resList = new ArrayList<ContactPerson>();
				if(null != cList && cList.size() > 0){
					for(ContactPerson c : cList){
						ContactPerson cpTemp = new ContactPerson();
						BeanUtils.copyProperties(c, cpTemp, new String[]{"customerAccount","credentialType","contactPersonType","crmContactType"});
						resList.add(cpTemp);
					}
				}
				renderResult(response,resList);
			}
		}
	}
	
	/**
	 *  获取销售机会列表
	 * */
	
	@RequestMapping(value = "querySaleChances.rs", method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	public void querySaleChances(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		logger.info("单一查询:{}.",id);
		Map<String, Object> params = new HashMap<String,Object>();
		if(StrUtils.isNotEmpty(id)){
			params.put("customerAccount.id", id);
			List<SaleChance> scList = customerAccountService.findAllByConditions(SaleChance.class, params);
			if (scList.size() == 0) {
				throw new ValidateException("该客户没有销售机会线索！");
			}else{
				List<SaleChance> resList = new ArrayList<SaleChance>();
				if(null != scList && scList.size() > 0){
					for(SaleChance sc : scList){
						SaleChance scTemp = new SaleChance();
						BeanUtils.copyProperties(sc, scTemp, new String[]{"customerAccount","currencyType","saleChanceStatus","crmProject","contactPerson","saleType"});
						resList.add(scTemp);
					}
				}
				renderResult(response,resList);
			}
		}
	}
}
