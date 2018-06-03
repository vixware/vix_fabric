package com.vix.oa.personaloffice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.common.properties.util.MyTool;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.nvix.common.base.dao.IVixntBaseDao;
import com.vix.nvix.common.base.hql.PurchaseDataHqlProvider;
import com.vix.nvix.common.base.hql.SqlReturnDataHandle;
import com.vix.nvix.purchase.action.vo.PurchaseArrivalDetailsVo;
import com.vix.nvix.purchase.action.vo.PurchaseDetailedVo;
import com.vix.nvix.purchase.action.vo.PurchaseMaterielCostVo;
import com.vix.nvix.purchase.action.vo.PurchaseOrderAnalysisVo;
import com.vix.nvix.purchase.action.vo.PurchaseStrorageDetailsVo;
import com.vix.nvix.purchase.action.vo.PurchaseSupplierCostVo;
import com.vix.nvix.purchase.action.vo.StockInOutWaterAccountTableVo;
import com.vix.oa.personaloffice.service.IPurchaseDataService;
/**采购模块统计数据 sql查询**/
@Service("purchaseDataService")
public class PurchaseDataServiceImpl extends BaseHibernateServiceImpl implements IPurchaseDataService {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IVixntBaseDao vixntBaseDao;
	@Resource(name = "purchaseDataHqlProvider")
	private PurchaseDataHqlProvider purchaseDataHqlProvider;//hql语句提供者
	@Resource(name = "sqlReturnDataHandle")
	private SqlReturnDataHandle purchaseDataHandle;//List<Object[]>结果处理者
	
	/** 查询  '采购统计仪表盘'的数据块的相关数据'本年采购询价' **/
	@Override
	public Map<String, Object> blockNumPurchaseDashboard(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = purchaseDataHqlProvider.blockNumPurchaseDashboard(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		if((hsMap.get("controlSQL")+"").toString().equals("numInquireSanbc")){//numInquireSanbc 查询 ‘采购询价单数’
			Integer num = purchaseDataHandle.queryNumToInteger(dataList);
			hsMapReturn.put("num",num);
		}else if((hsMap.get("controlSQL")+"").toString().equals("numPurOrderMoneySanec")){//numPurOrderMoneySanec 查询 ‘本年采购订单总金额’
			Double num = purchaseDataHandle.queryNumToDouble(dataList);
			hsMapReturn.put("num",num);
		}else if((hsMap.get("controlSQL")+"").toString().equals("completedOrder&Money")){//completedOrder&Money 查询 ‘本年已完成采购订单金额’和‘本年已完成采购订单数’
			Object[] integerAndDouble = purchaseDataHandle.queryNumToIntegerAndDouble(dataList);
			Integer numAmount = (Integer) integerAndDouble[0];
			Double numMoney = (Double) integerAndDouble[1];
			hsMapReturn.put("numMoney",numMoney);
			hsMapReturn.put("numAmount",numAmount);
		}else if((hsMap.get("controlSQL")+"").toString().equals("OnPassageOrder")){//OnPassageOrder 查询 ‘本年在途采购订单数’
			Integer num = purchaseDataHandle.queryNumToInteger(dataList);
			hsMapReturn.put("num",num);
		}
		return hsMapReturn;
	}
	/** 查询 '采购统计仪表盘'的 视图 的相关数据‘最近30日采购趋势图’ **/
	@SuppressWarnings({ "unchecked" })
	@Override
	public Map<String, Object> slowQueryPurchaseView(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = purchaseDataHqlProvider.slowQueryPurchaseView(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		if((hsMap.get("controlSQL")+"").toString().equals("qPurchasePlanView")){//qPurchasePlanView 查询 ‘30天采购计划订单数’
			ArrayList<String> valueArr = purchaseDataHandle.queryViewByYmdTimeAndInteger(dataList,(ArrayList<String>)hsMap.get("timeArr") );
			hsMapReturn.put("valueArr",valueArr);
		}else if((hsMap.get("controlSQL")+"").toString().equals("qCompletedOrderView")){//qCompletedOrderView 查询 ‘30天已完成采购订单’
			ArrayList<String> valueArrCompleted = purchaseDataHandle.queryViewByYmdTimeAndInteger(dataList,(ArrayList<String>)hsMap.get("timeArr") );
			hsMapReturn.put("valueArrCompleted",valueArrCompleted);
		}else if((hsMap.get("controlSQL")+"").toString().equals("qOnPassageOrderView")){//qOnPassageOrderView 查询 ‘30天在途采购订单
			ArrayList<String> valueArrOnPassage = purchaseDataHandle.queryViewByYmdTimeAndInteger(dataList,(ArrayList<String>)hsMap.get("timeArr") );
			hsMapReturn.put("valueArrOnPassage",valueArrOnPassage);
		}
		return hsMapReturn;
	}
	/** 查询 '采购统计仪表盘'的 视图 的相关数据‘最近30日已完成采购订单金额趋势图 查询’ **/
	@SuppressWarnings({ "unchecked" })
	@Override
	public Map<String, Object> slowQueryPurchaseMoneyView(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = purchaseDataHqlProvider.slowQueryPurchaseMoneyView(hsMap);//最近30日已完成采购订单金额柱图
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		ArrayList<String> valueArr = purchaseDataHandle.queryViewByYmdTimeAndDouble(dataList,(ArrayList<String>)hsMap.get("timeArr") );
		hsMapReturn.put("valueArr",valueArr);
		return hsMapReturn;
	}
	/** 查询 '采购统计仪表盘'的 视图 的相关数据‘采购订单中:采购产品数量TOP10 查询’ **/
	@Override
	public Map<String, Object> slowQueryPurchaseItemQuantityTop(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = purchaseDataHqlProvider.slowQueryPurchaseItemQuantityTop(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		Map<String, Object> topMap = purchaseDataHandle.topSqlStrnameAndsqlDoublenumReturnMap(dataList,(String)hsMap.get("topNum"));
		hsMapReturn.put("nameArr",topMap.get("nameArr"));
		hsMapReturn.put("valueArr",topMap.get("valueArr"));
		return hsMapReturn;
	}
	/** 查询 '采购统计仪表盘'的 视图 的相关数据‘采购订单中:采购产品金额TOP10 查询 查询’ **/
	@Override
	public Map<String, Object> slowQueryPurchaseItemMoneyTop(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = purchaseDataHqlProvider.slowQueryPurchaseItemMoneyTop(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		Map<String, Object> topMap = purchaseDataHandle.topSqlStrnameAndsqlDoublenumReturnMap(dataList,(String)hsMap.get("topNum"));
		hsMapReturn.put("nameArr",topMap.get("nameArr"));
		hsMapReturn.put("valueArr",topMap.get("valueArr"));
		return hsMapReturn;
	}
	/** 慢加载：采购订单中:供应商采购金额排行TOP10 查询json数据 **/
	@Override
	public Map<String, Object> slowQueryPurOrderSupplierMoneyTop(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = purchaseDataHqlProvider.slowQueryPurOrderSupplierMoneyTop(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		Map<String, Object> topMap = purchaseDataHandle.topSqlStrnameAndsqlDoublenumReturnMap(dataList,(String)hsMap.get("topNum"));
		hsMapReturn.put("nameArr",topMap.get("nameArr"));
		hsMapReturn.put("valueArr",topMap.get("valueArr"));
		return hsMapReturn;
	}
	/** 采购智能分析>采购成本分析>供应商采购排行Top10**/
	@Override
	public Map<String, Object> querySupplierOrderMoneyTop(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = purchaseDataHqlProvider.querySupplierOrderMoneyTop(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		StringBuffer sBufferJson = purchaseDataHandle.queryTopViewBysqlStrnameAndsqlDoublenum(dataList,(String)hsMap.get("topNum"));
		hsMapReturn.put("sBufferJsonToString",sBufferJson.toString());
		return hsMapReturn;
	}
	/** 采购智能分析>资金比重分析>物料采购排行Top10(饼图Top形式带其他占比)**/
	@Override
	public Map<String, Object> queryMaterielOrderMoneyPieChartTop(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = purchaseDataHqlProvider.querySupplierOrderMoneyTop(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		StringBuffer sBufferJson = purchaseDataHandle.queryPieChartTopBySqlStrnameAndSqlDoublenum(dataList,"10");
		hsMapReturn.put("sBufferJsonToString",sBufferJson.toString());
		return hsMapReturn;
	}
	/** 采购智能分析>资金比重分析>供应商采购排行Top10(饼图Top形式)**/
	@Override
	public Map<String, Object> querySupplierOrderMoneyPieChartTop(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = purchaseDataHqlProvider.querySupplierOrderMoneyTop(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		StringBuffer sBufferJson = purchaseDataHandle.queryPieChartTopBySqlStrnameAndSqlDoublenum(dataList,"10");
		hsMapReturn.put("sBufferJsonToString",sBufferJson.toString());
		return hsMapReturn;
	}
	/** 慢加载：采购订单中:供应商采购订单数排行TOP10 查询json **/
	@Override
	public Map<String, Object> slowQueryPurOrderSupplierQuantityTop(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = purchaseDataHqlProvider.slowQueryPurOrderSupplierQuantityTop(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		Map<String, Object> topMap = purchaseDataHandle.topSqlStrnameAndsqlIntnumReturnMap(dataList,(String)hsMap.get("topNum"));
		hsMapReturn.put("nameArr",topMap.get("nameArr"));
		hsMapReturn.put("valueArr",topMap.get("valueArr"));
		return hsMapReturn;
	}
	/**采购明细 采购走势图 查询**/
	@SuppressWarnings({ "unchecked" })
	@Override
	public Map<String, Object> queryPurchaseMoneyView(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = purchaseDataHqlProvider.queryPurchaseMoneyView(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		ArrayList<String> valueArr = purchaseDataHandle.queryViewByYmdTimeAndDouble(dataList,(ArrayList<String>)hsMap.get("timeArr") );
		hsMapReturn.put("valueArr",valueArr);
		return hsMapReturn;
	}
	/**对传入的sql语句执行查询，示例： select name,'1' from inv_shelf where id='"+objectMap.get("ckid")+"'  and name is not null and name <> '' 
	 * 所以注意：此方法智能查询返回一个结果，必须类似示例sql语句一样写
	  * @param sqlQueryOneResult 必须是只能查询一个结果的完整sql语句，且需要的数据在‘1’前面， 例如示例sql语句
	  * 特别注意：如果sql查询结果会出现 null,'1'的结果是不能用此方法！！！     一般查询name时，进行where条件限制，and name is not null and name <> '' ；就可以用此方法
	  * 当然：SELECT IFNULL(sum((many.amount * many.price)),0)  AS sqltotalNum, '1' FROM pur_orderlineitem many where 1=4 此方法也能处理，因为 IFNULL做出了sql判断
	 *  **/
	@Override
	public String queryOneDataBySql(String sqlQueryOneResult){
		try {
			List<Object[]> dataList = vixntBaseDao.findAllBySql(sqlQueryOneResult.toString(),new HashMap<String,Object>());
			String str = ((dataList !=null && dataList.size()>0) ? dataList.get(0)[0].toString() : "") ;
			return str;
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**对传入的sql语句执行查询，示例： select sum((many.amount * many.price)) as sqltotalnum, '1' from pur_orderlineitem many where 1=4 会返回第一列是nul，第二列是1，的结果集，此方法能处理
	 *  select sum((many.amount * many.price)) as sqltotalnum, '1' from pur_orderlineitem many where 1=1  此方法也能处理
	  * @param sqlQueryOneResult 必须是只能查询一个结果的完整sql语句，且需要的数据在‘1’前面， 例如示例sql语句
	  * queryOneDataBySql不能处理时，根据需求用此方法！！
	 *  **/
	@Override
	public Double queryOneDoubleNumDataBySql(String sqlQueryOneResult){
		try {
			Double dreturn = 0.0;
			List<Object[]> dataList = vixntBaseDao.findAllBySql(sqlQueryOneResult.toString(),new HashMap<String,Object>());
			if(dataList !=null && dataList.size()>0){
				Object[] objarr = dataList.get(0);
				if(objarr[0] != null ){
					dreturn = Double.parseDouble(objarr[0].toString());
				}
			}
			return dreturn;
		}catch (Exception e) {
			e.printStackTrace();
			return 0.0;
		}
	}
	
	/** 采购智能分析>类型结构分析>产品类别排行柱图和饼图一起查询  TOP10  树形结构排名  **/ 
	@Override
	public Double queryStructurePurchaseTopView(Map<String, Object> hsMap)throws Exception {
		StringBuilder hql = purchaseDataHqlProvider.queryStructurePurchaseTopView(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		Double num = purchaseDataHandle.queryNumToDouble(dataList);
		return num;
	}
	/** 采购明细:采购明细表的导出 **/
	@Override
	public ArrayList<PurchaseDetailedVo> outExcelToPurchaseDetailed(Map<String, Object> hsMap)throws Exception {
		PurchaseDetailedVo purchaseDetailedVo = new PurchaseDetailedVo();
		ArrayList<PurchaseDetailedVo> purchaseDetailedVoList = new ArrayList<PurchaseDetailedVo>();
		//每列数据sql样式
		/*SELECT
		many.itemname,
		many.unit,
		toone.id AS orid,
		many.itemcode,
		many.amount,
		many.price,
		toone.purchaseperson,
		DATE_FORMAT(
			toone.createtime,
			'%Y-%m-%d %H:%i:%s'
		) AS mcreatetime,
		toone. CODE,
		toone. STATUS FROM pur_orderlineitem many INNER JOIN pur_order toone ON many.purchaseorder_id = toone.id AND toone.tenantid = '111222' ..ORDER BY toone.createtime DESC*/ 
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("outExcelSqlPurchaseDetailedVo").toString(),new HashMap<String,Object>());
		if(dataList !=null && dataList.size()>0){
			Object[] objects = dataList.get(0);
			if(objects.length==10){//如果是10列数据
				for(Object[] objarr:dataList){
					purchaseDetailedVo.setItemName(((objarr[0] != null ) ? objarr[0].toString() : ""));
					purchaseDetailedVo.setUnit(((objarr[1] != null ) ? objarr[1].toString() : ""));
					/** 查询供应商名 queryName  **/
					StringBuffer queryName = new StringBuffer();
					queryName.append(" select toone.name as suppliername,'1' from pur_order many inner join srm_supplier toone on many.supplier_id=toone.id ");
					queryName.append(" and many.id='"+((objarr[2] != null ) ? objarr[2].toString() : "")+"'");
					queryName.append(" and toone.name is not null and toone.name <> '' ");
					purchaseDetailedVo.setSupplierName(queryOneDataBySql(queryName.toString()));
					purchaseDetailedVo.setItemCode(((objarr[3] != null ) ? objarr[3].toString() : ""));
					purchaseDetailedVo.setAmount(((objarr[4] != null ) ? objarr[4].toString() : ""));
					purchaseDetailedVo.setPrice( ((objarr[5] != null ) ? objarr[5].toString() : "")  );
					purchaseDetailedVo.setPurchasePerson(((objarr[6] != null ) ? objarr[6].toString() : ""));
					purchaseDetailedVo.setDate(((objarr[7] != null ) ? objarr[7].toString() : ""));
					purchaseDetailedVo.setOrderCode(((objarr[8] != null ) ? objarr[8].toString() : ""));
					String status = ((objarr[9] != null ) ? objarr[9].toString() : "");
					if (StringUtils.isNotEmpty(status)){
						if(status.equals("0")){
							status = "待配货";
						}else if(status.equals("1")){
							status = "代发货";
						}else if(status.equals("2")){
							status = "配送中";
						}else if(status.equals("4")){
							status = "待分拣";
						}else if(status.equals("3")){
							status = "已完成";
						}else{
							status = "";
						}
					}
					purchaseDetailedVo.setStatus(status);
					purchaseDetailedVoList.add(purchaseDetailedVo);       
					purchaseDetailedVo = new PurchaseDetailedVo();
				}
			}
		}
		return purchaseDetailedVoList;
	}
	/** 采购智能分析 > 到货明细>到货明细表的导出 **/
	@Override
	public ArrayList<PurchaseArrivalDetailsVo> outExcelToArrivalTable(Map<String, Object> hsMap)throws Exception {
		PurchaseArrivalDetailsVo purchaseArrivalDetailsVo = new PurchaseArrivalDetailsVo();
		ArrayList<PurchaseArrivalDetailsVo> purchaseArrivalDetailsVoList = new ArrayList<PurchaseArrivalDetailsVo>();
		//每列数据sql样式
			/*SELECT
			many.itemcode,
			many.itemname,
			many.amount,
			many.price,
			many.unit,
			date_format(
				toone.createtime,
				'%Y-%m-%d %h:%i:%s'
			) AS mcreatetime,
			toone.suppliername,
			toone.purchaseperson,
			toone. CODE,
			date_format(
				toone.deliverydate,
				'%Y-%m-%d %h:%i:%s'
			) AS jiaohuotime
		FROM pur_arrivalitems many INNER JOIN pur_arrival toone ON many.purchasearrival_id = toone.id AND toone.tenantid = '111222' ... */
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("outExcelSqlPurchaseArrivalDetailsVo").toString(),new HashMap<String,Object>());
		if(dataList !=null && dataList.size()>0){
			Object[] objects = dataList.get(0);
			if(objects.length==10){//如果是10列数据
				for(Object[] objarr:dataList){
					purchaseArrivalDetailsVo.setItemCode(((objarr[0] != null ) ? objarr[0].toString() : ""));
					purchaseArrivalDetailsVo.setItemName(((objarr[1] != null ) ? objarr[1].toString() : ""));
					purchaseArrivalDetailsVo.setAmount(((objarr[2] != null ) ? objarr[2].toString() : ""));
					purchaseArrivalDetailsVo.setPrice( ((objarr[3] != null ) ? objarr[3].toString() : "")  );
					purchaseArrivalDetailsVo.setUnit(((objarr[4] != null ) ? objarr[4].toString() : ""));
					purchaseArrivalDetailsVo.setEstablishDate(((objarr[5] != null ) ? objarr[5].toString() : ""));
					purchaseArrivalDetailsVo.setSupplierName(((objarr[6] != null ) ? objarr[6].toString() : ""));
					purchaseArrivalDetailsVo.setPurchasePerson(((objarr[7] != null ) ? objarr[7].toString() : ""));
					purchaseArrivalDetailsVo.setOrderCode(((objarr[8] != null ) ? objarr[8].toString() : ""));
					purchaseArrivalDetailsVo.setDeliveryDate(((objarr[9] != null ) ? objarr[9].toString() : ""));
					purchaseArrivalDetailsVoList.add(purchaseArrivalDetailsVo);       
					purchaseArrivalDetailsVo = new PurchaseArrivalDetailsVo();
				}
			}
		}
		return purchaseArrivalDetailsVoList;
	}
	/** 采购智能分析 > 入库明细>入库明细表的导出 **/
	@Override
	public ArrayList<PurchaseStrorageDetailsVo> outExcelToStorageTable(Map<String, Object> hsMap)throws Exception {
		PurchaseStrorageDetailsVo purchaseStrorageDetailsVo = new PurchaseStrorageDetailsVo();
		ArrayList<PurchaseStrorageDetailsVo> purchaseStrorageDetailsVoList = new ArrayList<PurchaseStrorageDetailsVo>();
		//每列数据sql样式
			/*SELECT
				toone.warehousePerson AS jbperson,
				toone. CODE,
				many.supplier_id AS supplierid,
				date_format(
					toone.createtime,
					'%Y-%m-%d %h:%i:%s'
				) AS mcreatetime,
				toone.invWarehouse_id AS ckid,
				many.invShelf_id AS hwid,
				many.itemcode,
				many.itemname,
				many.quantity,
				many.price,
				many.unit,
				many.unitcost
			FROM INV_STOCKRECORDLINES many INNER JOIN INV_STOCKRECORDS toone ON many.INVSTOCKRECORDS_ID = toone.id ORDER BY toone.createtime DESC */
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("outExcelSqlPurchaseStorageDetailsVo").toString(),new HashMap<String,Object>());
		if(dataList !=null && dataList.size()>0){
			Object[] objects = dataList.get(0);
			if(objects.length==12){//如果是12列数据
				for(Object[] objarr:dataList){
					purchaseStrorageDetailsVo.setAgentPerson(((objarr[0] != null ) ? objarr[0].toString() : ""));
					purchaseStrorageDetailsVo.setOrderCode(((objarr[1] != null ) ? objarr[1].toString() : ""));
					/** 查询供应商名称  **/
					String querySqlgys = " select name,'1' from srm_supplier where id='"+((objarr[2] != null ) ? objarr[2].toString() : "")+"' and name is not null and name <> '' ";
					purchaseStrorageDetailsVo.setSupplierName(queryOneDataBySql( querySqlgys ));
					purchaseStrorageDetailsVo.setDate(((objarr[3] != null ) ? objarr[3].toString() : ""));
					/** 查询仓库名称  **/
					String querySqlck = " select name,'1' from inv_warehouse where id='"+((objarr[4] != null ) ? objarr[4].toString() : "")+"' and name is not null and name <> '' ";
					purchaseStrorageDetailsVo.setWarehouse(queryOneDataBySql( querySqlck ));
					/** 查询货位名称  **/
					String querySqlhw = " select name,'1' from inv_shelf where id='"+((objarr[5] != null ) ? objarr[5].toString() : "")+"' and name is not null and name <> '' ";
					purchaseStrorageDetailsVo.setLocation(queryOneDataBySql( querySqlhw ));
					purchaseStrorageDetailsVo.setItemCode(((objarr[6] != null ) ? objarr[6].toString() : ""));
					purchaseStrorageDetailsVo.setItemName(((objarr[7] != null ) ? objarr[7].toString() : ""));
					purchaseStrorageDetailsVo.setAmount(((objarr[8] != null ) ? objarr[8].toString() : ""));
					purchaseStrorageDetailsVo.setTotalPrice( ((objarr[9] != null ) ? objarr[9].toString() : "")  );
					purchaseStrorageDetailsVo.setUnit(((objarr[10] != null ) ? objarr[10].toString() : ""));
					purchaseStrorageDetailsVo.setPrice( ((objarr[11] != null ) ? objarr[11].toString() : "")  );
					purchaseStrorageDetailsVoList.add(purchaseStrorageDetailsVo);       
					purchaseStrorageDetailsVo = new PurchaseStrorageDetailsVo();
				}
			}
		}
		return purchaseStrorageDetailsVoList;
	}
	/** 采购智能分析 >  采购成本分析 >供应商采购成本分析列表 导出 **/
	@Override
	public ArrayList<PurchaseSupplierCostVo> outExcelToSupplierCostTable(Map<String, Object> hsMap)throws Exception {
		PurchaseSupplierCostVo purchaseSupplierCostVo = new PurchaseSupplierCostVo();
		ArrayList<PurchaseSupplierCostVo> purchaseSupplierCostVoList = new ArrayList<PurchaseSupplierCostVo>();
		//每列数据sql样式
			/* select
				toone. name as sqlname,
				sum(many.totalamount) as sqlnum from pur_order many inner join srm_supplier toone on many.supplier_id = toone.id....*/
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("outExcelSqlSupplierCostTable").toString(),new HashMap<String,Object>());
		Double totalNum = (Double)hsMap.get("totalNum");
		if(dataList !=null && dataList.size()>0){
			Object[] objects = dataList.get(0);
			if(objects.length==2){//如果是2列数据
				for(int x=0;x<dataList.size();x++){
					Object[] objarr = dataList.get(x);
					purchaseSupplierCostVo.setLineNumber((x+1)+"");
					purchaseSupplierCostVo.setSupplierName(((objarr[0] != null ) ? objarr[0].toString() : ""));
					Double fzDou = Double.parseDouble(    ((objarr[1] != null ) ? objarr[1].toString() : "0.0")      );
					purchaseSupplierCostVo.setMoney(   fzDou+""  );
					Double doubleTemp = 0.0;
					if(totalNum != 0.0){
						doubleTemp =MyTool.roundingDoubleAppointDecimal( ((fzDou/totalNum)+0.00),4);
					}
					purchaseSupplierCostVo.setProportion( MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal( (doubleTemp*100) )) +"%");
					purchaseSupplierCostVoList.add(purchaseSupplierCostVo);       
					purchaseSupplierCostVo = new PurchaseSupplierCostVo();
				}
			}
		}
		return purchaseSupplierCostVoList;
	}
	/** 采购智能分析 >  采购成本分析 > 物料采购成本分析列表 导出 **/
	@Override
	public ArrayList<PurchaseMaterielCostVo> outExcelToMaterielCostTable(Map<String, Object> hsMap)throws Exception {
		PurchaseMaterielCostVo purchaseMaterielCostVo = new PurchaseMaterielCostVo();
		ArrayList<PurchaseMaterielCostVo> purchaseMaterielCostVoList = new ArrayList<PurchaseMaterielCostVo>();
		//每列数据sql样式
			/*  select
				many.itemcode,
				many.itemname,
				many.amount,
				many.price,
				many.unit,
				(many.amount * many.price) as sqlnum  from  pur_orderlineitem many  inner join pur_order toone on many.purchaseorder_id = toone.id
			 */
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("outExcelSqlMaterielCostTable").toString(),new HashMap<String,Object>());
		Double totalNum = (Double)hsMap.get("totalNum");
		if(dataList !=null && dataList.size()>0){
			Object[] objects = dataList.get(0);
			if(objects.length==6){//如果是6列数据
				for(int x=0;x<dataList.size();x++){
					Object[] objarr = dataList.get(x);
					purchaseMaterielCostVo.setLineNumber((x+1)+"");
					purchaseMaterielCostVo.setItemCode(((objarr[0] != null ) ? objarr[0].toString() : ""));
					purchaseMaterielCostVo.setItemName(((objarr[1] != null ) ? objarr[1].toString() : ""));
					purchaseMaterielCostVo.setAmount(((objarr[2] != null ) ? objarr[2].toString() : ""));
					purchaseMaterielCostVo.setPrice(((objarr[3] != null ) ? objarr[3].toString() : ""));
					purchaseMaterielCostVo.setUnit(((objarr[4] != null ) ? objarr[4].toString() : ""));
					Double fzDou = Double.parseDouble(    ((objarr[5] != null ) ? objarr[5].toString() : "0.0")      );
					purchaseMaterielCostVo.setMoney(   fzDou+""  );
					Double doubleTemp = 0.0;
					if(totalNum != 0.0){
						doubleTemp =MyTool.roundingDoubleAppointDecimal( ((fzDou/totalNum)+0.00),4);
					}
					purchaseMaterielCostVo.setProportion( MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal( (doubleTemp*100) )) +"%");
					purchaseMaterielCostVoList.add(purchaseMaterielCostVo);       
					purchaseMaterielCostVo = new PurchaseMaterielCostVo();
				}
			}
		}
		return purchaseMaterielCostVoList;
	}
	/** 导出采购智能分析 > 类型结构分析 > 产品类别采购分析列表  **/
	@Override
	public ArrayList<PurchaseSupplierCostVo> outExcelToStructurePurchaseTopTable(Map<String, Object> hsMap)throws Exception {
		PurchaseSupplierCostVo purchaseSupplierCostVo = new PurchaseSupplierCostVo();
		ArrayList<PurchaseSupplierCostVo> purchaseSupplierCostVoList = new ArrayList<PurchaseSupplierCostVo>();
		//每列数据sql样式
			/* 
			 * SELECT
					t. NAME,
					t.money,
					t.pro
				FROM
					(
						SELECT
							'按飞行平台构型分类' AS NAME,
							0 AS numsign,
							'1056606.65' AS money,
							'97.48' AS pro
						UNION ALL
							SELECT
								'按汽车平台构型分类' AS NAME,
								1 AS numsign,
								'27302.31' AS money,
								'2.52' AS pro
					) t
				ORDER BY
					t.numsign ASC 
			 **/
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("outExcelSqlStructurePurchaseTop").toString(),new HashMap<String,Object>());
		if(dataList !=null && dataList.size()>0){
			Object[] objects = dataList.get(0);
			if(objects.length==3){//如果是3列数据
				for(int x=0;x<dataList.size();x++){
					Object[] objarr = dataList.get(x);
					if((objarr[0] == null ) && (objarr[1] == null )  && (objarr[2] == null )  ){
						break;
					}
					purchaseSupplierCostVo.setLineNumber((x+1)+"");
					purchaseSupplierCostVo.setSupplierName(((objarr[0] != null ) ? objarr[0].toString() : ""));
					Double money = Double.parseDouble(    ((objarr[1] != null ) ? objarr[1].toString() : "0.0")      );
					purchaseSupplierCostVo.setMoney(   money+""  );
					Double pro = Double.parseDouble(    ((objarr[2] != null ) ? objarr[2].toString() : "0.0")      );
					purchaseSupplierCostVo.setProportion( pro +"%");
					purchaseSupplierCostVoList.add(purchaseSupplierCostVo);       
					purchaseSupplierCostVo = new PurchaseSupplierCostVo();
				}
			}
		}
		return purchaseSupplierCostVoList;
	}
	/**导出: 采购统计仪表盘>采购订单分析列表 **/
	@Override
	public ArrayList<PurchaseOrderAnalysisVo> outExcelToPurchaseStatisticsTable(Map<String, Object> hsMap)throws Exception {
		PurchaseOrderAnalysisVo purchaseOrderAnalysisVo = new PurchaseOrderAnalysisVo();
		ArrayList<PurchaseOrderAnalysisVo> purchaseOrderAnalysisVoList = new ArrayList<PurchaseOrderAnalysisVo>();
		//每列数据sql样式
			/* 
			 * select
				toone. name as suppliername,
				count(*) as ordernum,
				round(
					ifnull(sum(many.totalamount), 0),
					2
				) as ordermoney,
				many.purchaseperson,
				date_format(
					many.createtime,
					'%y-%m-%d %h:%i:%s'
				) as mcreatetime
			from pur_order many inner join srm_supplier toone on many.supplier_id = toone.id
			 **/
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("outExcelSqlPurchaseStatisticsTable").toString(),new HashMap<String,Object>());
		if(dataList !=null && dataList.size()>0){
			Object[] objects = dataList.get(0);
			if(objects.length==5){//如果是5列数据
				for(int x=0;x<dataList.size();x++){
					Object[] objarr = dataList.get(x);
					purchaseOrderAnalysisVo.setSupplierName(((objarr[0] != null ) ? objarr[0].toString() : ""));
					purchaseOrderAnalysisVo.setOrderNum(((objarr[1] != null ) ? objarr[1].toString() : ""));
					purchaseOrderAnalysisVo.setOrderMoney(((objarr[2] != null ) ? objarr[2].toString() : ""));
					purchaseOrderAnalysisVo.setPurchaser(((objarr[3] != null ) ? objarr[3].toString() : ""));
					purchaseOrderAnalysisVo.setPurchaserDate(((objarr[4] != null ) ? objarr[4].toString() : ""));
					purchaseOrderAnalysisVoList.add(purchaseOrderAnalysisVo);       
					purchaseOrderAnalysisVo = new PurchaseOrderAnalysisVo();
				}
			}
		}
		return purchaseOrderAnalysisVoList;
	}
	/** 库存管理>库存报表>出入库流水账的导出 **/
	@Override
	public ArrayList<StockInOutWaterAccountTableVo> outExcelToStockInOutWaterAccountTable(Map<String, Object> hsMap)throws Exception {
		StockInOutWaterAccountTableVo stockInOutWaterAccountTableVo = new StockInOutWaterAccountTableVo();
		ArrayList<StockInOutWaterAccountTableVo> stockInOutWaterAccountTableVoList = new ArrayList<StockInOutWaterAccountTableVo>();
		//每列数据sql样式
			/*SELECT
				t.flag,
				m.unit,
				m.itemcode, 
				m.itemname,  
				m.specification, 
				m.quantity,   
				m.unitcost,    
				m.price,    
				date_format(
					m.createtime,
					'%Y-%m-%d %h:%i:%s'
				) AS createtimetimestr,    
				t.invwarehouse_id,
				t. CODE  
			FROM
				inv_stockrecordlines m ... */
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("outExcelStockInOutWaterAccountTableVoList").toString(),new HashMap<String,Object>());
		if(dataList !=null && dataList.size()>0){
			Object[] objects = dataList.get(0);
			if(objects.length==11){//如果是11列数据
				for(Object[] objarr:dataList){
					stockInOutWaterAccountTableVo.setFlag(((objarr[0] != null ) ? objarr[0].toString() : ""));
					stockInOutWaterAccountTableVo.setUnit(((objarr[1] != null ) ? objarr[1].toString() : ""));
					stockInOutWaterAccountTableVo.setItemcode(((objarr[2] != null ) ? objarr[2].toString() : ""));
					stockInOutWaterAccountTableVo.setItemname(((objarr[3] != null ) ? objarr[3].toString() : ""));
					stockInOutWaterAccountTableVo.setSpecification(((objarr[4] != null ) ? objarr[4].toString() : ""));
					stockInOutWaterAccountTableVo.setQuantity(((objarr[5] != null ) ? objarr[5].toString() : ""));
					stockInOutWaterAccountTableVo.setUnitcost(((objarr[6] != null ) ? objarr[6].toString() : ""));
					stockInOutWaterAccountTableVo.setPrice(((objarr[7] != null ) ? objarr[7].toString() : ""));
					stockInOutWaterAccountTableVo.setCreatetimetimestr(((objarr[8] != null ) ? objarr[8].toString() : ""));
					/** 查询仓库名称  **/
					String querySqlck = " select name,'1' from inv_warehouse where id='"+((objarr[9] != null ) ? objarr[9].toString() : "")+"' and name is not null and name <> '' ";
					stockInOutWaterAccountTableVo.setCkname(queryOneDataBySql( querySqlck ));
					stockInOutWaterAccountTableVo.setCode(((objarr[10] != null ) ? objarr[10].toString() : ""));
					stockInOutWaterAccountTableVoList.add(stockInOutWaterAccountTableVo);       
					stockInOutWaterAccountTableVo = new StockInOutWaterAccountTableVo();
				}
			}
		}
		return stockInOutWaterAccountTableVoList;
	}
}
