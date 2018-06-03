package com.vix.common.base;


/**
 * 
 * BizObjectProcessFactory 只是在核心业务对象的管理上采用抽象工厂, 对于系统设置、参数配置等业务不采用
 * @author Jackie
 *
 */
public abstract class BizObjectProcessFactory {

	// 定义关于差异化的核心业务对象的工厂模型;
	abstract public IBizObjectProcess getPurchaseOderAction();
	abstract public IBizObjectProcess getSaleOderAction();

	

}
