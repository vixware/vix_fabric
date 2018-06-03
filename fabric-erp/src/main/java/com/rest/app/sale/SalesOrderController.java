package com.rest.app.sale;

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
import com.vix.sales.order.entity.SalesOrder;
import com.vix.sales.order.service.ISalesOrderService;

/**
 * @ClassName: salesOrderModuleAppRestController
 * @Description: App salesOrder 销售订单   服务 
 * @author Bluesnow
 * 
 */
@Controller
@RequestMapping(value = "restService/app/sale/salesOrder")
public class SalesOrderController extends BaseRestController{

	private static Logger logger = LoggerFactory.getLogger(SalesOrderController.class);

	@Resource(name="salesOrderService")
	private ISalesOrderService salesOrderService;
	
	/**
	 * 数据列表
	 */
	@RequestMapping(value="findAll.rs",method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	//@ResponseBody
	public void findAll(HttpServletRequest request, HttpServletResponse response, SalesOrder salesOrder) throws Exception {
		List<SalesOrder> solist = salesOrderService.findAllByEntityClass(SalesOrder.class);
		List<SalesOrder> resList = new ArrayList<SalesOrder>();
		if(null != solist && solist.size() > 0){
			for(SalesOrder so : solist){
				SalesOrder soTemp = new SalesOrder();
				BeanUtils.copyProperties(so, soTemp,new String[]{"crmProject","customerAccount","regional","contactPerson","saleOrg","salePerson","currencyType","saleOrderItems","salesAttachFiles","salesDeliveryPlans","salesTickets","channelDistributor","employee","orderPrintData","distributionCenter","invWarehouse","logistics","orderBatch","subStockRecords","channelDistributor"});
				resList.add(soTemp);
			}
		}
		renderResult(response,resList);
	}
	
	/**
	 * 分页参数 :    pageNo  第几页    
	 * 			   pageSize  每页的数量
	 */
	@RequestMapping(value="findSalesOrderPager.rs",method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	public void findSalesOrderPager(HttpServletRequest request, HttpServletResponse response, Pager pager,SalesOrder salesOrder) throws Exception {
		Map<String,Object> searchParam = new HashMap<String,Object>();
		if(StrUtils.isNotEmpty(salesOrder.getTitle())){
			searchParam.put("title", salesOrder.getTitle());
		}
		
		if(null != salesOrder.getBillDate()){
			searchParam.put("billDate", salesOrder.getBillDate());
		}
		
		if(null != salesOrder.getCustomerAccount()){
			if(StrUtils.isNotEmpty(salesOrder.getCustomerAccount().getName())){
				searchParam.put("customerAccount.name", salesOrder.getCustomerAccount().getName());
			}
		}
		
		//如下的数据查询需自行实现
		Pager pagerRes = salesOrderService.findPagerByHqlConditions(pager, SalesOrder.class, searchParam);
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
		SalesOrder soTemp = null;
		if(StrUtils.isNotEmpty(id)){
			SalesOrder salesOrder = salesOrderService.findEntityByAttributeNoTenantId(SalesOrder.class,"id", id);
			if (salesOrder == null) {
				throw new ValidateException("没有查询到id="+id+"的销售单！");
			}else{
				soTemp = new SalesOrder();
				BeanUtils.copyProperties(salesOrder, soTemp,new String[]{"crmProject","customerAccount","regional","contactPerson","saleOrg","salePerson","currencyType","saleOrderItems","salesAttachFiles","salesDeliveryPlans","salesTickets","channelDistributor","employee","orderPrintData","distributionCenter","invWarehouse","logistics","orderBatch","subStockRecords","channelDistributor"});
			}
		}
		renderResult(response,soTemp);
	}
	
	/**
	 * @Title: saveOrUpdate
	 * @Description: 保存  和  更新
	 */
	@RequestMapping(value="saveOrUpdate.rs",method = RequestMethod.POST)//, consumes = MediaTypes.JSON
	public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response, SalesOrder salesOrder) throws Exception {//@RequestBody 
		logger.info("保存.");
		//方式2 使用简单验证器
		Map<String,String> valMsgMap = ValidatonUtil.validator(new Validator(){
				@Override
				protected void validate() {
					//String field, String errorKey, String errorMessage
					//field 对应的实体的属性， errorKey 是自定义的唯一标识，errorMessage 是错误提示
					validateRequiredString("title", "titleMsg", "请输入主题!");
					validateRequiredString("billDate", "billDateMsg", "请输入销售时间!");
					validateRequiredString("customerAccount.name", "nameMsg", "请选择客户!");
					validateRequiredString("customerAccount.id", "idMsg", "请选择客户!");
				}
			}, salesOrder, false);
		if(!valMsgMap.isEmpty()){
			renderResultNotValid(response, valMsgMap);
			return;
		}
		//保存和更新代码待实现
		salesOrder = salesOrderService.mergeOriginal(salesOrder);
		SalesOrder soTemp = new SalesOrder();
		BeanUtils.copyProperties(salesOrder, soTemp,new String[]{"crmProject","customerAccount","regional","contactPerson","saleOrg","salePerson","currencyType","saleOrderItems","salesAttachFiles","salesDeliveryPlans","salesTickets","channelDistributor","employee","orderPrintData","distributionCenter","invWarehouse","logistics","orderBatch","subStockRecords","channelDistributor"});
		renderResult(response,soTemp);
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
			SalesOrder salesOrder = salesOrderService.findEntityByAttributeNoTenantId(SalesOrder.class,"id", id);
			if(null == salesOrder){
				throw new ValidateException("没有查询到id="+id+"的销售单！");
			}else{
				salesOrderService.deleteById(SalesOrder.class, id);
			}
		}
		renderResult(response,null);
	}
	
}
