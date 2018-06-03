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
import com.vix.crm.activity.entity.Activity;
import com.vix.crm.lead.entity.SaleLead;

/**
 * @ClassName: activityModuleAppRestController
 * @Description: App activity 销售活动
 * @author Bluesnow
 * 
 */

@Controller
@RequestMapping(value = "restService/app/crm/activity")
public class CrmActivityController extends BaseRestController{

	private static Logger logger = LoggerFactory.getLogger(CrmActivityController.class);

	
	@Autowired
	public IBaseHibernateService baseHibernateService;
	
	private List<String> startTimeList = new ArrayList<String>();// 开始时间
	private List<String> endTimeList = new ArrayList<String>();// 结束时间

	public CrmActivityController() {
		StringBuilder t = new StringBuilder();
		for (int i = 0; i < 24; i++) {
			if (i < 10) {
				t.append("0");
				t.append(i);
			} else {
				t.append(i);
			}
			t.append(":");
			t.append("00");
			startTimeList.add(t.toString());
			endTimeList.add(t.toString());
			t.delete(0, t.length());
			if (i < 10) {
				t.append("0");
				t.append(i);
			} else {
				t.append(i);
			}
			t.append(":");
			t.append("30");
			startTimeList.add(t.toString());
			endTimeList.add(t.toString());
			t.delete(0, t.length());
		}
	}
	
	/**
	 *	数据列表
	 */
	@RequestMapping(value="findAll.rs",method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	//@ResponseBody
	public void findAll(HttpServletRequest request, HttpServletResponse response, Activity activity) throws Exception {
		List<Activity> activitylist = baseHibernateService.findAllByEntityClass(Activity.class);
		List<Activity> resList = new ArrayList<Activity>();
		if(null != activitylist && activitylist.size() > 0){
			for(Activity a : activitylist){
				Activity aTemp = new Activity();
				BeanUtils.copyProperties(a, aTemp,new String[]{"customerAccount","currencyType","crmProject","created_by","personInCharge","saleActivity"});
				resList.add(aTemp);
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
		Pager pagerRes = baseHibernateService.findPagerByHqlConditions(pager, SaleLead.class, searchParam);
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
			SaleLead saleLead = baseHibernateService.findEntityByAttributeNoTenantId(SaleLead.class, "id",id);
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
		saleLead = baseHibernateService.mergeOriginal(saleLead);
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
			SaleLead saleLead = baseHibernateService.findEntityByAttributeNoTenantId(SaleLead.class,"id", id);
			if(null == saleLead){
				throw new ValidateException("没有查询到id="+id+"的客户！");
			}else{
				baseHibernateService.deleteById(SaleLead.class, id);
			}
		}
		renderResult(response,null);
	}
	
}
