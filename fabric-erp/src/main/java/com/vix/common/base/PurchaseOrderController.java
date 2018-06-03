package com.vix.common.base;

import java.util.Map;

import org.apache.log4j.Logger;



/**
 * PurchaseOrderController根据不同的行业返回针对行业的处理对象
 * PurchaseOrderController类是否可以单态的?
 * @author Jackie
 *
 */
public class PurchaseOrderController {
	
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("PurchaseOrderController");
	
	/**
	 * 实现业务处理的后端过程框架,平台中的业务处理实现需要调用此方法进行处理。
	 * 针对页面不同的功能,此方法要定义多次.
	 * 
	 */
	
	public BizObjectProcessFactory getProcessFactory(String industryIdentify){
		
		if(industryIdentify.equals("GeneralIndustry"))
			return  new GeneralIndustryProcessFactory();
		
		if(industryIdentify.equals("Food"))
			return  new FoodIndustryProcessFactory();
		
		if(industryIdentify.equals("GEO"))
			return  new FoodIndustryProcessFactory();
		
		return null;
	}
	
	/**
	 * 
	 * @param parameters
	 * @return
	 */
	public Map doSaveExecute(Map parameters){
		
		try{
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			//Map parameters = baExecutor.executeParameterProcess();
		
			// 2. 触发事件        beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 业务处理      采用抽象工厂模式来处理在大行业中的二次行业差异的处理.
			BizObjectProcessFactory boProcessFactory = getProcessFactory(industryIdentify);
			IBizObjectProcess poProcess = boProcessFactory.getPurchaseOderAction();
			poProcess.doSave(parameters);
			
			// 4. 触发事件        afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作  visitBORecord(parameter);
			
			// 6. 日志处理        log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger("");
		}
		catch(Exception ex){
			
		}
		
		return null;
	}
		

	/**
	 * 输入信息到日志文件中,可以考虑是用Helper类提供服务.
	 */
	public void executeLogger(String info){
		logger.info(info);
	}
	
	/**
	 * bizFlowExecute  需要对流程进行操作时调用
	 * @param flowName
	 * @param flowParameter
	 */
	protected void bizFlowExecute(String flowName, Map<String,Object>flowParameter){
		
	}
	
	/**
	 * bizRuleExecute 的定义还没有考虑清楚,涉及业务时重构.
	 * @param ruleName
	 * @param flowParameter
	 */
	protected void bizRuleExecute(String ruleName,Map<String,Object>flowParameter){
		
	}
		
	
	/**
	 * beforeEventTrigger 用于触发在执行业务逻辑前的事件产生, 将产生一个业务对象执行动作的before事件.
	 * 使用Esper引擎处理,事件体执行过程为异步.afterEventTrigger采用相同的处理方式.
	 * @param eventName 根据业务对象来定义
	 */
	private void beforeEventTrigger(String eventName){
		
	}
	
	
	/**
	 * afterEventTrigger 用于触发在执行业务逻辑后的事件产生, 将产生一个业务对象执行动作的after事件.
	 * @param eventName 根据业务对象来定义
	 */
	private void afterEventTrigger(String eventName){
		
	}
		
	
	
	public void doProcessEvent(){
		
	}
	
	public void doExecute(){
		
	}
		
	
	
	
	/**
	 * 
	 * @return
	 */
	public Object getIndustryOrderAction(){
		
		
		
		return null;
	}
	
	public void doPrint(){
		
	}
	
	public void doDrools(){
		
	}
	
	
	
}
