package com.rest.app.crm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rest.core.base.BaseRestController;
import com.vix.core.utils.StrUtils;
import com.vix.core.validation.ValidateException;
import com.vix.core.validation.ValidatonUtil;
import com.vix.core.validation.Validator;
import com.vix.core.web.Pager;
import com.vix.crm.lead.entity.SaleLead;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.nvix.common.base.service.IVixntBaseService;

/**
 * @ClassName: saleLeadModuleAppRestController
 * @Description: App saleLead 销售线索
 * @author Bluesnow
 * 
 */

@Controller
@RequestMapping(value = "restService/app/crm/saleLead")
public class CrmSaleLeadController extends BaseRestController{

	private static Logger logger = LoggerFactory.getLogger(CrmSaleLeadController.class);

	
	@Autowired
	private IVixntBaseService vixntBaseService;
	
	List<CustomerAccount> calist = new ArrayList<CustomerAccount>();
	List<ContactPerson> cpList = new ArrayList<ContactPerson>();
	/**
	 *	数据列表
	 */
	@RequestMapping(value="findAll.rs",method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	//@ResponseBody
	public void findAll(HttpServletRequest request, HttpServletResponse response, SaleLead saleLead) throws Exception {
		List<SaleLead> saleLeadlist = vixntBaseService.findAllByEntityClass(SaleLead.class);
		List<SaleLead> resList = new ArrayList<SaleLead>();
		if(null != saleLeadlist && saleLeadlist.size() > 0){
			for(SaleLead sl : saleLeadlist){
				SaleLead slTemp = new SaleLead();
				BeanUtils.copyProperties(sl, slTemp,new String[]{"customerAccount","contactPerson"});
				resList.add(slTemp);
			}
		}
		renderResult(response,resList);
	}
	
	/**
	 * 分页参数 :    pageNo  第几页    
	 * 			  pageSize  每页的数量
	 */
	@RequestMapping(value="findSaleLeadPager.rs",method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	public void findCustomerAccountPager(HttpServletRequest request, HttpServletResponse response, Pager pager,SaleLead saleLead) throws Exception {
		Map<String,Object> searchParam = new HashMap<String,Object>();
		if(StrUtils.isNotEmpty(saleLead.getSubject())){
			searchParam.put("subject", saleLead.getSubject());
		}
		if(StrUtils.isNotEmpty(saleLead.getCustomerAccount().getName())){
			searchParam.put("customerAccount.name", saleLead.getCustomerAccount().getName());
		}
		//如下的数据查询需自行实现
		Pager pagerRes = vixntBaseService.findPagerByHqlConditions(pager, SaleLead.class, searchParam);
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
		SaleLead slTemp = null;
		Map<String,Object> params = new HashMap<String,Object>(); 
		if(StrUtils.isNotEmpty(id)){
			SaleLead saleLead = vixntBaseService.findEntityByAttributeNoTenantId(SaleLead.class, "id",id);
			if (saleLead == null) {
				throw new ValidateException("没有查询到id="+id+"的销售线索！");
			}else{
				slTemp = new SaleLead();
				BeanUtils.copyProperties(saleLead, slTemp, new String[]{"customerAccount","contactPerson"});
			}
			params.put("slTemp", slTemp);
		}else{
			throw new ValidateException("没有查询到id="+id+"的销售线索！");
		}
		renderResult(response,params);
	}
	
	/**
	 * @Title: saveOrUpdate
	 * @Description: 保存  和  更新
	 */
	@RequestMapping(value="saveOrUpdate.rs",method = RequestMethod.POST)//, consumes = MediaTypes.JSON
	public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response,SaleLead saleLead) throws Exception {//@RequestBody 
		logger.info("保存.");
		//客户List
		calist = vixntBaseService.findAllByEntityClass(CustomerAccount.class);
		//方式2 使用简单验证器
		Map<String,String> valMsgMap = ValidatonUtil.validator(new Validator(){
				@Override
				protected void validate() {
					//String field, String errorKey, String errorMessage
					//field 对应的实体的属性， errorKey 是自定义的唯一标识，errorMessage 是错误提示
					validateRequiredString("subject", "subjectMsg", "请输入销售线索主题!");
					validateRequiredString("lastName", "nameMsg", "请输入客户名称!");
					validateRequiredString("customerAccount.id", "customerAccountMsg", "请选择客户!");
				}
			}, saleLead, false);
		if(!valMsgMap.isEmpty()){
			renderResultNotValid(response, valMsgMap);
			return;
		}
		//保存和更新代码待实现
		saleLead = vixntBaseService.mergeOriginal(saleLead);
		SaleLead slTemp = new SaleLead();
		BeanUtils.copyProperties(saleLead, slTemp,new String[]{"customerAccount","contactPerson"});
		renderResult(response,slTemp);
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
			SaleLead saleLead = vixntBaseService.findEntityByAttributeNoTenantId(SaleLead.class,"id", id);
			if(null == saleLead){
				throw new ValidateException("没有查询到id="+id+"的客户！");
			}else{
				vixntBaseService.deleteById(SaleLead.class, id);
			}
		}
		renderResult(response,null);
	}
	
	/**
	 *  获取联系人List
	 * */
	
	@RequestMapping(value = "queryContentPersons.rs", method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	public void queryContentPersons(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("customerAccountId");
		logger.info("单一查询:{}.",id);
		Map<String, Object> params = new HashMap<String,Object>();
		if(StrUtils.isNotEmpty(id)){
			params.put("customerAccount.id", id);
			cpList = vixntBaseService.findAllByConditions(ContactPerson.class, params);
			if (cpList.size() == 0) {
				throw new ValidateException("该客户没有添加联系人！");
			}
		}
	}

	public List<CustomerAccount> getCalist() {
		return calist;
	}

	public void setCalist(List<CustomerAccount> calist) {
		this.calist = calist;
	}

	public List<ContactPerson> getCpList() {
		return cpList;
	}

	public void setCpList(List<ContactPerson> cpList) {
		this.cpList = cpList;
	}
}
