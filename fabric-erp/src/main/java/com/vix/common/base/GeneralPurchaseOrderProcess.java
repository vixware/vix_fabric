package com.vix.common.base;

import java.util.Map;



/**
 * GeneralPurchaseOrderFactory中的方法是结合Domain层进行实际的业务处理的实现类
 * 
 * @author Jackie
 *
 */
public class GeneralPurchaseOrderProcess implements IBizObjectProcess {

	@Override
	public void doSave(Map parameters) {
		// TODO
		// 在此实现关于通用行业的订单保存的业务逻辑,并结合领域模型层进行处理.
		// 每个模块下面都有一个Domian的目录,与当前模块有的领域对象都在此目录下.

		//  save1(parameters); 保存了采购订单
		//  save2();

		// 观察者模式代码
		
		
		
		// 
	}

	@Override
	public void doApproval(Map parameters) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doEdit(Map parameters) {
		// TODO Auto-generated method stub

	}
	
	
	

}
