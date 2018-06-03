
package com.vix.oa.personaloffice.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.nvix.chain.action.RequireGoodsOrderVo;
/**
 * 统计查询数据
 * **/
public interface IQueryDataService extends IBaseHibernateService {
	 /** ‘工作台’查询返回相关json   
	 * @throws Exception **/  
	public Map<String, Object> findnvixContentJsonA(Map<String, Object> hsMap) throws Exception;
	
	 /** ‘工作台’查询返回相关json B   
		 * @throws Exception **/  
	public Map<String, Object> findnvixContentJsonB(Map<String, Object> hsMap) throws Exception;
		
	 /** 查询 工作台  '今日客流量排行' Top  **/  
	public Map<String, Object> findnvixContentJsonC(Map<String, Object> hsMap) throws Exception;
	/** ‘工作台’客户消费排行TOP10 返回html  **/  
	public Map<String, Object> findnvixContentHtmlA(Map<String, Object> hsMap) throws Exception;
	/** ‘工作台’商品销量排行 TOP10 返回html  **/  
	public Map<String, Object> findnvixContentHtmlB(Map<String, Object> hsMap) throws Exception;
	/**  会员消费分析  '数据块'返回jsonA   **/  
	public Map<String, Object> findConsumptionAnalysisJsonA(Map<String, Object> hsMap) throws Exception;
	/**   会员消费分析  '视图A'返回json 会员消费方式分析   **/  
	public Map<String, Object> findConsumptionAnalysisViewA(Map<String, Object> hsMap) throws Exception;
	/**   会员消费排行视图 '会员消费分析' **/  
	public Map<String, Object> findConsumptionAnalysisViewB(Map<String, Object> hsMap) throws Exception;
	/** 单个会员购买商品数量top5 */
	public List<RequireGoodsOrderVo> getCustomerExpense(Map<String, Object> params) throws Exception;
	/** 单个会员购买商品数量 --饼图*/
	public List<RequireGoodsOrderVo> getCustomerAllExpense(Map<String, Object> params)throws Exception;
	/** 会员画像分析>顾客消费次数分析视图  */ 
	public Map<String, Object> findCategoryAnalysisViewA(Map<String, Object> hsMap) throws Exception;
	 /** 会员画像分析>会员消费次数排行  */ 
	public Map<String, Object> findCategoryAnalysisViewB(Map<String, Object> hsMap) throws Exception;
	 /**   会员消费排行 '视图C'返回json 最近30日商品销量排行Top10 **/  
    public Map<String, Object> findConsumptionAnalysisViewC(Map<String, Object> hsMap) throws Exception;
    /**   会员量分析 > 新增会员量视图    **/  
	public Map<String, Object> findPurchasingBehaviorActionViewA(Map<String, Object> hsMap) throws Exception;
	/**   会员量分析 > 会员总量视图    **/  
	public Map<String, Object> findPurchasingBehaviorActionViewB(Map<String, Object> hsMap) throws Exception;
	/**  客户消费明细CustomerConsumptionDetails   A  **/  
	public Map<String, Object> findCustomerConsumptionDetailsA(Map<String, Object> hsMap) throws Exception;
	/** 获得’客户分析 ’分析页面的 会员总数+会员卡总数+会员卡总积分+会员卡平均积分   **/  
	public Map<String, Object> findFsycfaaMemberAnalysisJsonA(Map<String, Object> hsMap) throws Exception;
	/** 获得’客户分析 ’分析页面的 饼图数据   **/  
	public Map<String, Object> findFsycfaaMemberAnalysisJsonB(Map<String, Object> hsMap) throws Exception;
	/**获得’客户分析 ’客流量视图  **/  
	public Map<String, Object> findFsycfaaMemberAnalysisJsonC(Map<String, Object> hsMap) throws Exception;
	/**获得’会员消费分析 ’>客单价视图(每日订单总金额/对应客户人数)  **/  
	public Map<String, Object> findFsyqfraConsumptionAnalysisViewD(Map<String, Object> hsMap) throws Exception;
	/** 会员画像分析> 顾客流失风险A主力客户 |查询主力客户    */
	public Map<String, Object> findFsycftaMainCustomer(Map<String, Object> hsMap) throws Exception;
	/** 会员画像分析> 顾客流失风险  | B 新客户 | frequency 频率，次数*/
	public Map<String, Object> findFsmcftaNewCustomer(Map<String, Object> hsMap) throws Exception;
	 /** 会员画像分析> 顾客流失风险  | C 已经流失顾客 | frequency 频率，次数 AlreadyLost*/
	public Map<String, Object> findFsmcftaAlreadyLostCustomer(Map<String, Object> hsMap) throws Exception;
	/** 会员画像分析> 顾客流失风险  | D 将要流失顾客 | frequency 频率，次数WillLoss 将要流失*/
	public Map<String, Object> findFsmcftaWillLossCustomer(Map<String, Object> hsMap) throws Exception;
	/** 会员画像分析> highValueMember 高价值客户分析 |A 忠诚客户   |Loyal 忠诚  **/
	public Map<String, Object> findFvmqftaLoyalCustomer(Map<String, Object> hsMap) throws Exception;
	 /** 会员画像分析> highValueMember 高价值客户分析 |B 瞌睡客户   |Doze 瞌睡  **/
	public Map<String, Object> findFvmqftaDozeCustomer(Map<String, Object> hsMap) throws Exception;
	/** 会员画像分析> highValueMember 高价值客户分析 |C 半睡客户   |HalfAsleep半睡  **/
	public Map<String, Object> findFvmmftaHalfAsleepCustomer(Map<String, Object> hsMap) throws Exception; 
	/**  客户会员相关数据或其他...Souli是个随机数  **/  
	public Map<String, Object> findCustomerDataSouliA(Map<String, Object> hsMap) throws Exception;
	 /** 门店库存报表>最近30日商品入库数量Top10  */ 
	public Map<String, Object> findStockAnalysisViewASabc(Map<String, Object> hsMap) throws Exception;
	/**  查询sql的返回一个数值  **/  
	public Map<String, Object> findGoodsDataSujrop(Map<String, Object> hsMap) throws Exception;
	/** 最近30日销售商品数量按客户排行Top10  */ 
	public Map<String, Object> findAnalysisTopViewAScdsvb(Map<String, Object> hsMap) throws Exception;
	/**  根据参数获得不同的店铺id，或者供应商id,或者....id **/
	public String queryDifferentIDsAbcd(String setSupplierID) throws Exception;
	/**  查询sql的返回一个数值  **/  
	public Map<String, Object> findDataRtOneUajoop(Map<String, Object> hsMap) throws Exception;
	/**   最近30日采购订单视图 等    **/  
	public Map<String, Object> lookupDateBrokenlineViewUcbf(Map<String, Object> hsMap) throws Exception;
}
