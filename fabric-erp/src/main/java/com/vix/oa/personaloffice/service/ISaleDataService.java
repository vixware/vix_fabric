
package com.vix.oa.personaloffice.service;

import java.util.ArrayList;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.nvix.purchase.action.vo.PurchaseSupplierCostVo;
import com.vix.nvix.sales.action.vo.SalesDeliverGoodsVo;
import com.vix.nvix.sales.action.vo.SalesDetailedBookVo;
import com.vix.nvix.sales.action.vo.SalesDetailedVo;
import com.vix.nvix.sales.action.vo.SalesOrderAnalysisVo;
import com.vix.nvix.sales.action.vo.SalesReturnGoodsDetailedVo;
import com.vix.nvix.sales.action.vo.SalesmanAchieveVo;
/**销售模块统计数据 sql查询**/
public interface ISaleDataService extends IBaseHibernateService {
	/** 查询  '销售统计仪表盘'的数据块的相关数据 **/  
	public Map<String, Object> blockNumSaleDashboard(Map<String, Object> hsMap) throws Exception;
	/** 慢加载：销售智能分析 > 销售统计仪表盘>最近30日销售订单趋势**/
	public Map<String, Object> slowQuerySalesOrderTrend(Map<String, Object> hsMap)throws Exception;
	/** 销售智能分析>销售增长分析>销售订单数趋势图   & ...   **/
	public Map<String, Object> querySalesOrderTrend(Map<String, Object> hsMap)throws Exception;
	/** 慢加载：销售智能分析 > 销售统计仪表盘>产品销售数量TOP10  & ...    **/
	public Map<String, Object> slowQuerySalesTopView(Map<String, Object> hsMap)throws Exception;
	/** 销售智能分析>产品销量排行>产品销售金额排行Top10  & ... **/
	public Map<String, Object> queryProductSalesTopView(Map<String, Object> hsMap)throws Exception;
	/** 销售智能分析>销售结构分析>产品类别销售排行柱图和饼图一起查询  TOP10  树形结构排名   **/
	public Double queryStructureSalesTopView(Map<String, Object> hsMap)throws Exception;
	/** 销售智能分析>客户购买排行>客户购买排行金额Top10  &  客户购买排行数量Top10 **/
	public Map<String, Object> queryCustomerBuyTopView(Map<String, Object> hsMap)throws Exception;
	/** 销售智能分析>货物流向分析>  销售订单数发货地图排行TOP10 & 销售订单金额发货地图排行TOP10  **/
	public Map<String, Object> querySendMapTopView(Map<String, Object> hsMap)throws Exception;
	/** 销售智能分析>销售人员业绩排行>销售人员业绩订单金额Top10  &  销售人员业绩订单数量Top10  &... **/
	public Map<String, Object> querySalesmanSellTopView(Map<String, Object> hsMap)throws Exception;
	/** 销售智能分析>退货订单走势图查询   **/
	public Map<String, Object> querySaleReturnView(Map<String, Object> hsMap)throws Exception;
	/**对传入的sql语句执行查询，示例： select name,'1' from inv_shelf where id='"+objectMap.get("ckid")+"'  and name is not null and name <> '' 
	 * 所以注意：此方法智能查询返回一个结果，必须类似示例sql语句一样写
	 *  **/
	public String queryOneDataBySql(String sql);
	/**对传入的sql语句执行查询，示例： select sum((many.amount * many.price)) as sqltotalnum, '1' from pur_orderlineitem many where 1=4 会返回第一列是nul，第二列是1，的结果集，此方法能处理
	 *  select sum((many.amount * many.price)) as sqltotalnum, '1' from pur_orderlineitem many where 1=1  此方法也能处理
	  * @param sqlQueryOneResult 必须是只能查询一个结果的完整sql语句，且需要的数据在‘1’前面， 例如示例sql语句
	  * queryOneDataBySql不能处理时，根据需求用此方法！！
	 *  **/
	public Double queryOneDoubleNumDataBySql(String sqlQueryOneResult);
	/**导出: 销售智能分析 > 销售统计仪表盘> 销售订单分析列表 **/
	public ArrayList<SalesOrderAnalysisVo> outExcelToSaleStatisticsTable(Map<String, Object> hsMap)throws Exception;
	/**导出: 销销售智能分析>销售明细表  **/
	public ArrayList<SalesDetailedVo> outExcelToSaleDetailedTable(Map<String, Object> hsMap)throws Exception;
	/**导出 销售智能分析>发货明细表 查询**/
	public ArrayList<SalesDeliverGoodsVo> outExcelToSaleDeliverTable(Map<String, Object> hsMap)throws Exception;
	/**导出: 销销售智能分析>销售明细账  **/
	public ArrayList<SalesDetailedBookVo> outExcelToSaleDetailedTableBook(Map<String, Object> hsMap)throws Exception;
	/**导出 销售智能分析>退货明细表 **/
	public ArrayList<SalesReturnGoodsDetailedVo> outExcelToSaleReturnTable(Map<String, Object> hsMap)throws Exception;
	/** 导出采购智能分析 > 类型结构分析 > 产品类别采购分析列表  **/
	public ArrayList<SalesmanAchieveVo> outExcelToStaffTable(Map<String, Object> hsMap)throws Exception;
	/** 导出:销售智能分析 > 销售结构分析 > 产品类别销售分析列表 **/
	public ArrayList<PurchaseSupplierCostVo> outExcelToStructureSalesTopTable(Map<String, Object> hsMap)throws Exception;
}
