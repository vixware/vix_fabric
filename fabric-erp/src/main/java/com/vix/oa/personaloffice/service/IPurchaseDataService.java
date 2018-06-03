
package com.vix.oa.personaloffice.service;

import java.util.ArrayList;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.nvix.purchase.action.vo.PurchaseArrivalDetailsVo;
import com.vix.nvix.purchase.action.vo.PurchaseDetailedVo;
import com.vix.nvix.purchase.action.vo.PurchaseMaterielCostVo;
import com.vix.nvix.purchase.action.vo.PurchaseOrderAnalysisVo;
import com.vix.nvix.purchase.action.vo.PurchaseStrorageDetailsVo;
import com.vix.nvix.purchase.action.vo.PurchaseSupplierCostVo;
import com.vix.nvix.purchase.action.vo.StockInOutWaterAccountTableVo;
/**采购模块统计数据 sql查询**/
public interface IPurchaseDataService extends IBaseHibernateService {
	/** 查询  '采购统计仪表盘'的数据块的相关数据 **/  
	public Map<String, Object> blockNumPurchaseDashboard(Map<String, Object> hsMap) throws Exception;
	/** 查询  '采购统计仪表盘'的  视图   的相关数据 **/  
	public Map<String, Object> slowQueryPurchaseView(Map<String, Object> hsMap) throws Exception;
	/** 查询 '采购统计仪表盘'的 视图 的相关数据‘最近30日已完成采购订单金额趋势图 查询’ **/  
	public Map<String, Object> slowQueryPurchaseMoneyView(Map<String, Object> hsMap) throws Exception;
	/** 查询 '采购统计仪表盘'的 视图 的相关数据‘采购订单中:采购产品数量TOP10 查询’ **/
	public Map<String, Object> slowQueryPurchaseItemQuantityTop(Map<String, Object> hsMap) throws Exception;
	/** 查询 '采购统计仪表盘'的 视图 的相关数据‘采购订单中:采购产品金额TOP10 查询 查询’ **/
	public Map<String, Object> slowQueryPurchaseItemMoneyTop(Map<String, Object> hsMap) throws Exception;
	/** 慢加载：采购订单中:供应商采购金额排行TOP10 查询json数据 **/
	public Map<String, Object> slowQueryPurOrderSupplierMoneyTop(Map<String, Object> hsMap)throws Exception;
	/** 采购智能分析>采购成本分析>供应商采购排行Top10**/
	public Map<String, Object> querySupplierOrderMoneyTop(Map<String, Object> hsMap)throws Exception;
	/** 采购智能分析>资金比重分析>物料采购排行Top10(饼图Top形式带其他占比)**/
	public Map<String, Object> queryMaterielOrderMoneyPieChartTop(Map<String, Object> hsMap)throws Exception;
	/** 采购智能分析>资金比重分析>供应商采购排行Top10(饼图Top形式)**/
	public Map<String, Object> querySupplierOrderMoneyPieChartTop(Map<String, Object> hsMap)throws Exception;
	/** 慢加载：采购订单中:供应商采购订单数排行TOP10 查询json **/
	public Map<String, Object> slowQueryPurOrderSupplierQuantityTop(Map<String, Object> hsMap)throws Exception;
	/** 采购走势图 查询**/
	public Map<String, Object> queryPurchaseMoneyView(Map<String, Object> hsMap)throws Exception;
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
	/** 采购智能分析>类型结构分析>产品类别排行柱图和饼图一起查询  TOP10  树形结构排名  **/
	public Double queryStructurePurchaseTopView(Map<String, Object> hsMap)throws Exception;
	/** 采购明细:采购明细表的导出 **/
	public ArrayList<PurchaseDetailedVo> outExcelToPurchaseDetailed(Map<String, Object> hsMap)throws Exception;
	/** 采购智能分析 > 到货明细>到货明细表的导出 **/
	public ArrayList<PurchaseArrivalDetailsVo> outExcelToArrivalTable(Map<String, Object> hsMap)throws Exception;
	/** 采购智能分析 > 入库明细>入库明细表的导出 **/
	public ArrayList<PurchaseStrorageDetailsVo> outExcelToStorageTable(Map<String, Object> hsMap)throws Exception;
	/** 采购智能分析 >  采购成本分析 >供应商采购成本分析列表 导出 **/
	public ArrayList<PurchaseSupplierCostVo> outExcelToSupplierCostTable(Map<String, Object> hsMap)throws Exception;
	/** 采购智能分析 >  采购成本分析 > 物料采购成本分析列表 导出 **/
	public ArrayList<PurchaseMaterielCostVo> outExcelToMaterielCostTable(Map<String, Object> hsMap)throws Exception;
	/** 导出采购智能分析 > 类型结构分析 > 产品类别采购分析列表  **/
	public ArrayList<PurchaseSupplierCostVo> outExcelToStructurePurchaseTopTable(Map<String, Object> hsMap)throws Exception;
	/**导出: 采购统计仪表盘>采购订单分析列表 **/
	public ArrayList<PurchaseOrderAnalysisVo> outExcelToPurchaseStatisticsTable(Map<String, Object> hsMap)throws Exception;
	/** 库存管理>库存报表>出入库流水账的导出 **/
	public ArrayList<StockInOutWaterAccountTableVo> outExcelToStockInOutWaterAccountTable(Map<String, Object> hsMap)throws Exception;
}
