
package com.vix.oa.personaloffice.service;

import java.util.ArrayList;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.nvix.purchase.action.vo.StockHasDistributionTableVo;
import com.vix.nvix.purchase.action.vo.StockInOutDepositSummaryVo;
/**库存模块统计数据 sql查询**/
public interface IStockQueryStatisticsService extends IBaseHibernateService {
	/** 库存管理>库存报表>现存物料价值分布表 列表查询 **/
	public Map<String, Object> searchStockHasMoneyDistribution(Map<String, Object> hsMap) throws Exception;
	/** 慢加载：库存管理 > 库存报表 > 库存仪表盘 > 最近30日商品 入/出 库趋势图  数据查询   **/
	public Map<String, Object> queryDataViewStockInOut(Map<String, Object> hsMap)throws Exception;
	 /**  查询sql的返回一个数值(门店库存报表 > 现存物料SKU数,本月过期物料量,库存不足物料SKU数,库存积压物料SKU数(库存积压:现存量>=200;库存不足:现存量<20;))  **/  
	public Map<String, Object> queryStockGoodsNum(Map<String, Object> hsMap) throws Exception;
	/** 导出:库存管理>库存报表>收发存汇总表  **/ 
	public ArrayList<StockInOutDepositSummaryVo> outExcelToStockInOutDepositSummary(Map<String, Object> hsMap) throws Exception;
	/** 导出:库存管理>库存报表>存货分布表 **/
	public ArrayList<StockHasDistributionTableVo> outExcelToStockHasDistributionTable(Map<String, Object> hsMap) throws Exception;
	/** 这里计算的是所有仓库的 库龄结构:计算'库龄结构'(自定义查询'30天以下,30天-60天,60天到180天,180天以上'的入库数量)   **/
	public String calculationStockAge(Map<String, Object> hsMap)throws Exception;
	/** 查询:收货商品数量,收货SKU数,收货订单数 **/
	public Map<String, Object> queryBlockAbc(Map<String, Object> hsMap)throws Exception;
	/**查询'进销存概览'的收货商品总数折线图和收货SKU数折线图 **/
	public Map<String, Object> queryViewBrokenLineData(Map<String, Object> hsMap)throws Exception;
	/** 查询金额比例top柱图和top饼图 **/
	public String queryViewMoneyTopData(Map<String, Object> hsMap)throws Exception;
	/**对传入的sql语句执行查询，示例： select name,'1' from inv_shelf where id='"+objectMap.get("ckid")+"'  and name is not null and name <> '' 
	 * 所以注意：此方法智能查询返回一个结果，必须类似示例sql语句一样写
	  * @param sqlQueryOneResult 必须是只能查询一个结果的完整sql语句，且需要的数据在‘1’前面， 例如示例sql语句
	  * 特别注意：如果sql查询结果会出现 null,'1'的结果是不能用此方法！！！     一般查询name时，进行where条件限制，and name is not null and name <> '' ；就可以用此方法
	  * 当然：SELECT IFNULL(sum((many.amount * many.price)),0)  AS sqltotalNum, '1' FROM pur_orderlineitem many where 1=4 此方法也能处理，因为 IFNULL做出了sql判断
	 *  **/
	public String queryOneDataBySql(String sqlQueryOneResult);
}
