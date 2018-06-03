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
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.utils.StrUtils;
import com.vix.core.validation.ValidateException;
import com.vix.core.validation.ValidatonUtil;
import com.vix.core.validation.Validator;
import com.vix.core.web.Pager;
import com.vix.mdm.crm.entity.ContactPerson;

/**
 * @ClassName: ContactPersonModuleAppRestController
 * @Description: App ContactPerson 联系人   服务 
 * @author Bluesnow
 * 
 */

@Controller
@RequestMapping(value = "restService/app/crm/contactPerson")
public class CrmContactPersonController extends BaseRestController{

	private static Logger logger = LoggerFactory.getLogger(CrmContactPersonController.class);

	
	@Autowired
	public IBaseHibernateService baseHibernateService;
	
	/**
	 *	数据列表
	 */
	@RequestMapping(value="findAll.rs",method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	//@ResponseBody
	public void findAll(HttpServletRequest request, HttpServletResponse response, ContactPerson contactPerson) throws Exception {
		List<ContactPerson> cplist = baseHibernateService.findAllByEntityClass(ContactPerson.class);
		List<ContactPerson> resList = new ArrayList<ContactPerson>();
		if(null != cplist && cplist.size() > 0){
			for(ContactPerson cp : cplist){
				ContactPerson cpTemp = new ContactPerson();
				BeanUtils.copyProperties(cp, cpTemp, new String[]{"customerAccount","credentialType","contactPersonType","crmContactType"});
				resList.add(cpTemp);
			}
		}
		renderResult(response,resList);
	}
	
	/**
	 * 分页参数 :    pageNo  第几页    
	 * 			  pageSize  每页的数量
	 */
	@RequestMapping(value="findContactPersonPager.rs",method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	public void findContactPersonPager(HttpServletRequest request, HttpServletResponse response, Pager pager,ContactPerson contactPerson) throws Exception {
		Map<String,Object> searchParam = new HashMap<String,Object>();
		if(StrUtils.isNotEmpty(contactPerson.getLastName())){
			searchParam.put("lastName", contactPerson.getLastName());
		}
		if(StrUtils.isNotEmpty(contactPerson.getFirstName())){
			searchParam.put("firstName", contactPerson.getFirstName());
		}
		
		//如下的数据查询需自行实现
		Pager pagerRes = baseHibernateService.findPagerByHqlConditions(pager, ContactPerson.class, searchParam);
		renderResultPager(response,pagerRes);
	}
	

	/**
	 * @Title: get
	 * @Description: 单一对象查询
	 */
	@RequestMapping(value = "query.rs", method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		logger.info("单一查询:{}.",id);
		ContactPerson cpTemp = null;
		if(StrUtils.isNotEmpty(id)){
			ContactPerson contactPerson = baseHibernateService.findEntityByAttributeNoTenantId(ContactPerson.class, "id", id);
			if (contactPerson == null) {
				throw new ValidateException("没有查询到id="+id+"的待删除的联系人！");
			}else{
				cpTemp = new ContactPerson();
				BeanUtils.copyProperties(contactPerson, cpTemp, new String[]{"customerAccount","credentialType","contactPersonType","crmContactType"});
			}
		}
		
		renderResult(response,cpTemp);
	}
	
	/**
	 * @Title: saveOrUpdate
	 * @Description: 保存  和  更新
	 */
	@RequestMapping(value="saveOrUpdate.rs",method = RequestMethod.POST)//, consumes = MediaTypes.JSON
	public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response, ContactPerson contactPerson) throws Exception {//@RequestBody 
		logger.info("保存.");
		//方式2 使用简单验证器
		Map<String,String> valMsgMap = ValidatonUtil.validator(new Validator(){
				@Override
				protected void validate() {
					//String field, String errorKey, String errorMessage
					//field 对应的实体的属性， errorKey 是自定义的唯一标识，errorMessage 是错误提示
					validateRequiredString("name", "nameMsg", "请输入联系人姓名!");
					validateRequiredString("customerAccount", "customerAccountMsg", "请指定该联系人所属客户!");
				}
			}, contactPerson, false);
		if(!valMsgMap.isEmpty()){
			renderResultNotValid(response, valMsgMap);
			return;
		}
		//保存和更新代码待实现
		contactPerson = baseHibernateService.mergeOriginal(contactPerson);
		ContactPerson cpTemp = new ContactPerson();
		BeanUtils.copyProperties(contactPerson, cpTemp, new String[]{"customerAccount","credentialType","contactPersonType","crmContactType"});
		renderResult(response,cpTemp);
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
			ContactPerson contactPerson = baseHibernateService.findEntityByAttributeNoTenantId(ContactPerson.class,"id", id);
			if(null == contactPerson){
				throw new ValidateException("没有查询到id="+id+"的待删除的联系人！");
			}else{
				baseHibernateService.deleteById(ContactPerson.class, id);
			}
		}
		renderResult(response,null);
	}
	
}
