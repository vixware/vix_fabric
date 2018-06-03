package com.vix.sales.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.BizObjectProcessFactory;
import com.vix.common.base.IBizObjectProcess;
import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.sales.order.domain.SalesOrderDomain;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesAttachFile;
import com.vix.sales.order.entity.SalesDeliveryPlan;
import com.vix.sales.order.entity.SalesOrder;

/**
 * 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 * @author Administrator
 *
 */
@Controller("salesOrderController")
@Scope("prototype")
public class SalesOrderController extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	
	Logger logger = LoggerFactory.getLogger("PurchaseOrderController");
	
	public BizObjectProcessFactory getProcessFactory(String industryIdentify){
		
		if(industryIdentify.equals("GeneralIndustry"))
			return  new GeneralIndustryProcessFactory();
		
		if(industryIdentify.equals("Food"))
			return  new FoodIndustryProcessFactory();
		
		if(industryIdentify.equals("GEO"))
			return  new FoodIndustryProcessFactory();
		
		return null;
	}
	
	@Autowired
	private SalesOrderDomain salesOrderDomain;
	
	/** 获取列表数据  */
	public Pager goSingleList(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = salesOrderDomain.findPagerByHqlConditions(params,pager);
		return p;
	}
	
	public Pager goSubSingleList(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = salesOrderDomain.findPagerByHqlConditions(params,pager);
		return p;
	}
	
	public SalesOrder findEntityById(String id) throws Exception{
		return  salesOrderDomain.findEntityById(id);
	}
	
	public SalesAttachFile findSalesAttachFileEntityById(String id) throws Exception{
		return  salesOrderDomain.findSalesAttachFileEntityById(id);
	}
	
	public SaleOrderItem merge(SaleOrderItem soi) throws Exception{
		return salesOrderDomain.merge(soi);
	}
	
	public SalesOrder merge(SalesOrder salesOrder,String updateField,List<SalesDeliveryPlan> dpList) throws Exception{
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("saveEntity", salesOrder);
		paramsMap.put("updateField", updateField);
		paramsMap.put("dpList", dpList);
		// 3. 业务处理      采用抽象工厂模式来处理在大行业中的二次行业差异的处理.
		BizObjectProcessFactory boProcessFactory = getProcessFactory(industryIdentify);
		IBizObjectProcess poProcess = boProcessFactory.getSaleOderAction();
		poProcess.doSave(paramsMap);
		Object obj = paramsMap.get("savedEntity");
		if(null != obj && obj instanceof SalesOrder){
			SalesOrder so = (SalesOrder)obj;
			executeLogger(logger,"保存订单数据,订单id:"+so.getId());
			return so;
		}
		return  null;
	}
	
	public SalesAttachFile mergeSalesAttachFile(SalesAttachFile salesAttachFile) throws Exception{
		return salesOrderDomain.merge(salesAttachFile);
	}
	
	public void deleteByEntity(SalesOrder salesOrder) throws Exception{
		salesOrderDomain.deleteByEntity(salesOrder);
	}
	
	public void deleteSalesAttachFileEntity(SalesAttachFile salesAttachFile) throws Exception{
		salesOrderDomain.deleteSalesAttachFileEntity(salesAttachFile);
	}
	
	public void deleteByIds(List<String> ids) throws Exception{
		salesOrderDomain.deleteByIds(ids);
	}
	
	public List<SalesOrder> findSalesOrderIndex() throws Exception{
		return salesOrderDomain.findSalesOrderIndex();
	}
}
