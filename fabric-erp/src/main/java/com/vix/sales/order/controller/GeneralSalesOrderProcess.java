package com.vix.sales.order.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vix.common.base.IBizObjectProcess;
import com.vix.common.base.MySpringApplicationContextUtil;
import com.vix.common.share.entity.CMNObjectModifyRecord;
import com.vix.sales.order.domain.SalesOrderDomain;
import com.vix.sales.order.entity.SalesOrder;

/**
 * GeneralPurchaseOrderFactory中的方法是结合Domain层进行实际的业务处理的实现类
 * 
 * @author Jackie
 *
 */
@Component("generalSalesOrderProcess")
public class GeneralSalesOrderProcess implements IBizObjectProcess {

	@Autowired
	private SalesOrderDomain salesOrderDomain;
	
	@Override
	public void doSave(Map<String,Object> parameters) throws Exception {
		salesOrderDomain = (SalesOrderDomain)MySpringApplicationContextUtil.getContext().getBean("salesOrderDomain");
		Object soObj = parameters.get("saveEntity");
		Object uf = parameters.get("updateField");
		if(null != soObj && soObj instanceof SalesOrder){
			SalesOrder so = (SalesOrder)soObj;
			if(null != so.getId() && null != uf){
				dealTrace(so,uf.toString());
			}
			if(null == so.getCrmProject() || null == so.getCrmProject().getId() || !so.getCrmProject().getId().equals("") || !so.getCrmProject().getId().equals("0")){
				so.setCrmProject(null);
			}
			so = salesOrderDomain.merge(so);
			parameters.put("savedEntity", so);
//			Object dpListObj = parameters.get("dpList");
//			if(null != dpListObj && dpListObj instanceof List){
//				List<SalesDeliveryPlan> dpList = (List<SalesDeliveryPlan>)dpListObj;
//				for(SalesDeliveryPlan dp : dpList){
//					if(null != dp){
//						dp.setSalesOrder(so);
//						salesOrderDomain.merge(dp);
//					}
//				}
//			}
		}
		// TODO
		// 在此实现关于通用行业的订单保存的业务逻辑,并结合领域模型层进行处理.
		// 每个模块下面都有一个Domian的目录,与当前模块有的领域对象都在此目录下.

		//  save1(parameters); 保存了采购订单
		//  save2();

		// 观察者模式代码
		
		// 
	}
	
	/** 处理留痕 */
	private void dealTrace(SalesOrder so,String updateField) throws Exception{
		SalesOrder oldSo = salesOrderDomain.findEntityById(so.getId());
		String[] ufArray = updateField.split(",");
		List<String> list = new ArrayList<String>();
		for(String uf : ufArray){
			if(null != uf && !"".equals(uf)){
				list.add(uf);
			}
		}
		Field[] fds = Class.forName("com.vix.sales.order.entity.SalesOrder").getDeclaredFields();   
		  for(int i=0;i<fds.length;i++){
			  if(list.contains(fds[i].getName())){
				  fds[i].setAccessible(true);  
				  Object oldObj = fds[i].get(oldSo);
				  Object newObj = fds[i].get(so);
				  CMNObjectModifyRecord cmr = new CMNObjectModifyRecord();
				  cmr.setKey(fds[i].getName());
				  if(null != oldObj){
					  cmr.setOldValue(oldObj.toString());
				  }
				  if(null != newObj){
					  cmr.setNewValue(newObj.toString());
				  }
				  cmr.setCreateTime(new Date());
				  salesOrderDomain.merge(cmr);
			  }
		  }   
	}

	@Override
	public void doApproval(Map<String,Object> parameters) throws Exception {
		 

	}

	@Override
	public void doEdit(Map<String,Object> parameters) throws Exception {
		 

	}
	
	
	

}
