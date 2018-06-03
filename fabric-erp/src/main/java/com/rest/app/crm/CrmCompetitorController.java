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
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.utils.StrUtils;
import com.vix.core.validation.ValidateException;
import com.vix.core.validation.ValidatonUtil;
import com.vix.core.validation.Validator;
import com.vix.core.web.Pager;
import com.vix.crm.project.entity.Competitor;
import com.vix.crm.project.service.ICompetitorService;

/**
 * @ClassName: competitorModuleAppRestController
 * @Description: App competitor 竞争对手
 * @author Bluesnow
 * 
 */

@Controller
@RequestMapping(value = "restService/app/crm/competitor")
public class CrmCompetitorController extends BaseRestController{

	private static Logger logger = LoggerFactory.getLogger(CrmCompetitorController.class);

	@Resource(name="competitorService")
	private ICompetitorService competitorService;
	
	private List<CurrencyType> currencyTypeList;
	
	/**
	 *	数据列表
	 */
	@RequestMapping(value="findAll.rs",method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	//@ResponseBody
	public void findAll(HttpServletRequest request, HttpServletResponse response, Competitor competitor) throws Exception {
		List<Competitor> competitorlist = competitorService.findAllByEntityClass(Competitor.class);
		List<Competitor> resList = new ArrayList<Competitor>();
		if(null != competitorlist && competitorlist.size() > 0){
			for(Competitor c : competitorlist){
				Competitor cTemp = new Competitor();
				BeanUtils.copyProperties(c, cTemp,new String[]{"crmProject","competitorProducts","saleChance","customerAccount"});
				resList.add(cTemp);
			}
		}
		renderResult(response,resList);
	}
	
	/**
	 * 分页参数 :    pageNo  第几页    
	 * 			  pageSize  每页的数量
	 */
	@RequestMapping(value="findCompetitorPager.rs",method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	public void findCustomerAccountPager(HttpServletRequest request, HttpServletResponse response, Pager pager,Competitor competitor) throws Exception {
		Map<String,Object> searchParam = new HashMap<String,Object>();
		if(StrUtils.isNotEmpty(competitor.getCompeteProduct())){
			searchParam.put("competeProduct", competitor.getCompeteProduct());
		}
		if(StrUtils.isNotEmpty(competitor.getCustomerAccount().getName())){
			searchParam.put("customerAccount.name", competitor.getCustomerAccount().getName());
		}
		//如下的数据查询需自行实现
		Pager pagerRes = competitorService.findPagerByHqlConditions(pager, Competitor.class, searchParam);
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
		Competitor cTemp = null;
		Map<String,Object> params = new HashMap<String,Object>(); 
		if(StrUtils.isNotEmpty(id)){
			Competitor competitor = competitorService.findEntityByAttributeNoTenantId(Competitor.class, "id",id);
			if (competitor == null) {
				throw new ValidateException("没有查询到id="+id+"的竞争对手！");
			}else{
				cTemp = new Competitor();
				BeanUtils.copyProperties(competitor, cTemp, new String[]{"crmProject","competitorProducts","saleChance","customerAccount"});
			}
			params.put("cTemp", cTemp);
		}else{
			throw new ValidateException("没有查询到id="+id+"的竞争对手！");
		}
		renderResult(response,params);
	}
	
	/**
	 * @Title: saveOrUpdate
	 * @Description: 保存  和  更新
	 */
	@RequestMapping(value="saveOrUpdate.rs",method = RequestMethod.POST)//, consumes = MediaTypes.JSON
	public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response,Competitor competitor) throws Exception {//@RequestBody 
		logger.info("保存.");
		//币种
		currencyTypeList = competitorService.findAllByEntityClass(CurrencyType.class);
		//方式2 使用简单验证器
		Map<String,String> valMsgMap = ValidatonUtil.validator(new Validator(){
				@Override
				protected void validate() {
					//String field, String errorKey, String errorMessage
					//field 对应的实体的属性， errorKey 是自定义的唯一标识，errorMessage 是错误提示
					validateRequiredString("companyName", "companyNameMsg", "请输入对手公司名称!");
					validateRequiredString("competeProduct", "competeProductMsg", "请输入竞争产品!");
					validateRequiredString("customerAccount.id", "customerAccountMsg", "请选择客户!");
				}
			}, competitor, false);
		if(!valMsgMap.isEmpty()){
			renderResultNotValid(response, valMsgMap);
			return;
		}
		//保存和更新代码待实现
		competitor = competitorService.mergeOriginal(competitor);
		Competitor cTemp = new Competitor();
		BeanUtils.copyProperties(competitor, cTemp,new String[]{"crmProject","competitorProducts","saleChance","customerAccount"});
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
			Competitor competitor = competitorService.findEntityByAttributeNoTenantId(Competitor.class,"id", id);
			if(null == competitor){
				throw new ValidateException("没有查询到id="+id+"的竞争对手！");
			}else{
				competitorService.deleteById(Competitor.class, id);
			}
		}
		renderResult(response,null);
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}
}
