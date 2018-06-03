package com.rest.app.contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rest.core.base.BaseRestController;
import com.rest.core.exception.RestException;
import com.vix.contract.contractInquiry.entity.ContractInquiry;
import com.vix.core.utils.StrUtils;
import com.vix.core.validation.ValidateException;
import com.vix.core.validation.ValidatonUtil;
import com.vix.core.validation.Validator;
import com.vix.core.web.Pager;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.nvix.common.base.service.IVixntBaseService;

/**
 * 
* @ClassName: SalesContractAppController
* @Description: 销售合同
* @author bobchen
* @date 2016年1月11日 下午1:29:39
*
 */

@Controller
@RequestMapping(value = "restService/app/contract/salesContractApp")
public class SalesContractAppController extends BaseRestController{
	
	
	@Autowired
	private IVixntBaseService vixntBaseService;
	
	/**
	 * 获取销售合同列表
	 * @param request
	 * @param response
	 * @param contractInquiry
	 * @throws Exception
	 */
	@RequestMapping(value="findContractInquiryList.rs",method = RequestMethod.POST)
	//@ResponseBody
	public void findContractInquiry(HttpServletRequest request, HttpServletResponse response, ContractInquiry contractInquiry) throws Exception {
		List<ContractInquiry> cilist = vixntBaseService.findAllByEntityClass(ContractInquiry.class);
		List<ContractInquiry> resList = new ArrayList<ContractInquiry>();
		if(null != cilist && cilist.size() > 0){
			for(ContractInquiry c : cilist){
				ContractInquiry ci = new ContractInquiry();
				BeanUtils.copyProperties(c, ci,new String[]{"hrAttachments","contractChildItem",
						"contractSubject","contractWarning","applicationForm",
						"priceConditions","contractPricingConditions","contractMarket",
						"contractGroupType","contractTypeCombine","contractNatureType",
						"contractStageGroupType","currencyType","exchangeRate",
						"viewIndustryType","modeType","enableStageType"});
				resList.add(ci);
			}
		}
		renderResult(response,resList);
	}
	
	
	/**
	 * 获取销售合同分页列表
	 * @param request
	 * @param response
	 * @param pager
	 * @param contractInquiry
	 * @throws Exception
	 */
	@RequestMapping(value = "findContractInquiryPager.rs", method = RequestMethod.POST)
	public void findContractInquiryPager(HttpServletRequest request, HttpServletResponse response, Pager pager, ContractInquiry contractInquiry) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StrUtils.isNotEmpty(contractInquiry.getContractName())) {
			params.put("meetingTheme", contractInquiry.getContractName());
		}
		Pager pagerRes = vixntBaseService.findPagerByHqlConditions(pager, ContractInquiry.class, params);
		renderResultPager(response, pagerRes);
	}
	
	/**
	 * 根据ID获取销售合同
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findContractInquiry.rs", method = RequestMethod.POST)
	public void querys(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		ContractInquiry t = null;
		if (StringUtils.isNotEmpty(id)) {
			ContractInquiry contractInquiry = vixntBaseService.findEntityByAttributeNoTenantId(ContractInquiry.class, "id", id);
			String message = "";
			if (contractInquiry == null) {
				message = "对象不存在(id:" + id + ")";
				throw new RestException(HttpStatus.NOT_FOUND, message);
			} else {
				t = new ContractInquiry();
				BeanUtils.copyProperties(contractInquiry, t, new String[]
				  {"hrAttachments","contractChildItem",
					"contractSubject","contractWarning","applicationForm",
					"priceConditions","contractPricingConditions","contractMarket",
					"contractGroupType","contractTypeCombine","contractNatureType",
					"contractStageGroupType","currencyType","exchangeRate",
					"viewIndustryType","modeType","enableStageType"});
			}
		}
		renderResult(response, t);
	}
	
	/**
	 * 获取乙方客户信息列表
	 * @param request
	 * @param response
	 * @param customerAccount
	 * @throws Exception
	 */
	@RequestMapping(value="findCustomerAccountList.rs",method = RequestMethod.POST)
	//@ResponseBody
	public void findCustomerAccount(HttpServletRequest request, HttpServletResponse response, CustomerAccount customerAccount) throws Exception {
		List<CustomerAccount> calist = vixntBaseService.findAllByEntityClass(CustomerAccount.class);
		List<CustomerAccount> resList = new ArrayList<CustomerAccount>();
		if(null != calist && calist.size() > 0){
			for(CustomerAccount ca : calist){
				CustomerAccount cat = new CustomerAccount();
				BeanUtils.copyProperties(ca, cat,new String[]{"customerCreditInfo","customerAccountCategory",
						"customerAccountGroup","contactPerson","saleChance","saleLead","memberLevel",
						"customerComplaint","customerService","memberTag","couponDetail"});
				resList.add(cat);
			}
		}
		renderResult(response,resList);
	}
	
	/**
	 * 保存销售合同
	 * @param request
	 * @param response
	 * @param contractInquiry
	 * @throws Exception
	 */
	@RequestMapping(value = "saveContractInquiry.rs", method = RequestMethod.POST)
	public void saveContractInquiry(HttpServletRequest request, HttpServletResponse response, ContractInquiry contractInquiry) throws Exception {
		//方式2 使用简单验证器
		Map<String, String> valMsgMap = ValidatonUtil.validator(new Validator() {
			@Override
			protected void validate() {
				validateRequiredString("contractCode", "contractCodeMsg", "请输合同编码!");
				validateRequiredString("contractName", "contractNameMsg", "请输合同名称!");
				validateRequiredString("projectCode", "projectCodeMsg", "请输项目代码!");
				validateRequiredString("projectName", "projectNameMsg", "请输项目名称!");
				validateRequiredString("firstParty", "firstPartyMsg", "请输甲方信息!");
				validateRequiredString("secondParty", "secondPartyMsg", "请输乙方信息!");
			}
		}, contractInquiry, false);
		if (!valMsgMap.isEmpty()) {
			renderResultNotValid(response, valMsgMap);
			return;
		}
		contractInquiry = vixntBaseService.mergeOriginal(contractInquiry);
		renderResult(response, contractInquiry);
	}
	
	/**
	 * 删除销售合同
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteContractInquiry.rs", method = RequestMethod.DELETE)
	public void deleteContractInquiry(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			ContractInquiry contractInquiry = vixntBaseService.findEntityByAttributeNoTenantId(ContractInquiry.class, "id", id);
			if (contractInquiry == null) {
				throw new ValidateException("没有查询到待删除的销售合同id！");
			} else {
				vixntBaseService.deleteByEntity(contractInquiry);
			}
		}
		renderResult(response, null);
	}
}
