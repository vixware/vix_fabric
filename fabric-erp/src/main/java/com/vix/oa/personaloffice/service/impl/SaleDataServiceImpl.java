package com.vix.oa.personaloffice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.vix.common.properties.util.MapBeanInt;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.nvix.common.base.dao.IVixntBaseDao;
import com.vix.nvix.common.base.hql.SaleDataHqlProvider;
import com.vix.nvix.common.base.hql.SqlReturnDataHandle;
import com.vix.nvix.purchase.action.vo.PurchaseSupplierCostVo;
import com.vix.nvix.sales.action.vo.SalesDeliverGoodsVo;
import com.vix.nvix.sales.action.vo.SalesDetailedBookVo;
import com.vix.nvix.sales.action.vo.SalesDetailedVo;
import com.vix.nvix.sales.action.vo.SalesOrderAnalysisVo;
import com.vix.nvix.sales.action.vo.SalesReturnGoodsDetailedVo;
import com.vix.nvix.sales.action.vo.SalesmanAchieveVo;
import com.vix.oa.personaloffice.service.ISaleDataService;
/**销售模块统计数据 sql查询**/
@Service("saleDataService")
public class SaleDataServiceImpl extends BaseHibernateServiceImpl implements ISaleDataService {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IVixntBaseDao vixntBaseDao;
	@Resource(name = "saleDataHqlProvider")    
	private SaleDataHqlProvider saleDataHqlProvider;//hql语句提供者
	@Resource(name = "sqlReturnDataHandle")
	private SqlReturnDataHandle sqlReturnDataHandle;//List<Object[]>结果处理者
	
	/**销售智能分析 > 销售统计仪表盘>数据块>本月销售金额，今日销售金额 等**/ 
	@Override
	public Map<String, Object> blockNumSaleDashboard(Map<String, Object> hsMap) throws Exception{
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = saleDataHqlProvider.blockNumPurchaseDashboard(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		if((hsMap.get("controlSQL")+"").toString().equals("salesAmount&orderNum")){//salesAmount&orderNum 销售统计仪表盘查询销售订单数&查询销售金额
			Object[] integerAndDouble = sqlReturnDataHandle.queryNumToIntegerAndDouble(dataList);
			Integer numAmount = (Integer) integerAndDouble[0];
			Double numMoney = (Double) integerAndDouble[1];
			hsMapReturn.put("numMoney",numMoney);
			hsMapReturn.put("numAmount",numAmount);
		}else if((hsMap.get("controlSQL")+"").toString().equals("salesOrderCustomerNum")) {//salesOrderCustomerNum  销售仪表盘查：今日客户数
			Integer num = sqlReturnDataHandle.queryNumToInteger(dataList);
			hsMapReturn.put("num",num);
		}else if((hsMap.get("controlSQL")+"").toString().equals("salesOrderNewCustomerNum")) {//salesOrderNewCustomerNum  本月新客户数(去重)
			Integer num = sqlReturnDataHandle.queryNumToInteger(dataList);
			hsMapReturn.put("num",num);
		}else if((hsMap.get("controlSQL")+"").toString().equals("salesProductTypes")) {//salesProductTypes 销售产品种类
			Integer num = sqlReturnDataHandle.queryNumToInteger(dataList);
			hsMapReturn.put("num",num);
		}else if((hsMap.get("controlSQL")+"").toString().equals("salesReturnOrederNum")) {//salesReturnOrederNum  本月退货订单数(已完成)
			Integer num = sqlReturnDataHandle.queryNumToInteger(dataList);
			hsMapReturn.put("num",num);
		}
		return hsMapReturn;
	}
	/** 慢加载：销售智能分析 > 销售统计仪表盘>最近30日销售订单趋势  &  最近30日销售金额趋势图**/
	@SuppressWarnings({ "unchecked" })
	@Override
	public Map<String, Object> slowQuerySalesOrderTrend(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = saleDataHqlProvider.slowQuerySalesOrderTrend(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		if((hsMap.get("controlSQL")+"").toString().equals("qvSalesOrderTrendNum")){//qvSalesOrderTrendNum 查询 ‘最近30日销售订单趋势（订单数）’
			ArrayList<String> valueArr = sqlReturnDataHandle.queryViewByYmdTimeAndInteger(dataList,(ArrayList<String>)hsMap.get("timeArr") );
			hsMapReturn.put("valueArr",valueArr);
		}else if((hsMap.get("controlSQL")+"").toString().equals("qvSalesOrderTrendMoney")){// qvSalesOrderTrendMoney 最近30日销售金额趋势图
			ArrayList<String> valueArr = sqlReturnDataHandle.queryViewByYmdTimeAndDouble(dataList,(ArrayList<String>)hsMap.get("timeArr") );
			hsMapReturn.put("valueArr",valueArr);
		}
		return hsMapReturn;
	}
	/** 销售智能分析>销售增长分析>销售订单数趋势图   & 查询‘销售金额趋势图’   **/
	@SuppressWarnings({ "unchecked" })
	@Override
	public Map<String, Object> querySalesOrderTrend(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = saleDataHqlProvider.querySalesOrderTrend(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		if((hsMap.get("controlSQL")+"").toString().equals("querSalesOrderTrendNum")){//querSalesOrderTrendNum  查询‘销售订单数趋势图’
			ArrayList<String> valueArr = sqlReturnDataHandle.queryViewByYmdTimeAndInteger(dataList,(ArrayList<String>)hsMap.get("timeArr") );
			hsMapReturn.put("valueArr",valueArr);
		}else if((hsMap.get("controlSQL")+"").toString().equals("querSalesOrderTrendMoney")){// querSalesOrderTrendMoney   查询‘销售金额趋势图’ 
			ArrayList<String> valueArr = sqlReturnDataHandle.queryViewByYmdTimeAndDouble(dataList,(ArrayList<String>)hsMap.get("timeArr") );
			hsMapReturn.put("valueArr",valueArr);
		}
		return hsMapReturn;
	}
	/** 慢加载：销售智能分析 > 销售统计仪表盘>产品销售数量TOP10  & ...    **/
	@Override
	public Map<String, Object> slowQuerySalesTopView(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = saleDataHqlProvider.slowQuerySalesTopView(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		StringBuffer sBufferJson = sqlReturnDataHandle.queryTopViewBysqlStrnameAndsqlDoublenum(dataList,(String)hsMap.get("topNum"));
		hsMapReturn.put("sBufferJsonToString",sBufferJson.toString());
		return hsMapReturn;
	}
	/** 销售智能分析>产品销量排行>产品销售金额排行Top10  & ... **/
	@Override
	public Map<String, Object> queryProductSalesTopView(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = saleDataHqlProvider.queryProductSalesTopView(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		StringBuffer sBufferJson = sqlReturnDataHandle.queryTopViewBysqlStrnameAndsqlDoublenum(dataList,(String)hsMap.get("topNum"));
		hsMapReturn.put("sBufferJsonToString",sBufferJson.toString());
		return hsMapReturn;
	}
	/** 销售智能分析>销售结构分析>产品类别销售排行柱图和饼图一起查询  TOP10  树形结构排名   **/  
	@Override
	public Double queryStructureSalesTopView(Map<String, Object> hsMap)throws Exception {
		StringBuilder hql = saleDataHqlProvider.queryStructureSalesTopView(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		Double num = sqlReturnDataHandle.queryNumToDouble(dataList);
		return num;
	}
	/** 销售智能分析>客户购买排行>客户购买排行金额Top10  &  客户购买排行数量Top10 **/
	@Override
	public Map<String, Object> queryCustomerBuyTopView(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = saleDataHqlProvider.queryCustomerBuyTopView(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		StringBuffer sBufferJson = sqlReturnDataHandle.queryTopViewBysqlStrnameAndsqlDoublenum(dataList,(String)hsMap.get("topNum"));
		hsMapReturn.put("sBufferJsonToString",sBufferJson.toString());
		return hsMapReturn;
	}
	/** 销售智能分析>货物流向分析>  销售订单数发货地图排行TOP10 & 销售订单金额发货地图排行TOP10  **/
	@Override
	public Map<String, Object> querySendMapTopView(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = saleDataHqlProvider.querySendMapTopView(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		StringBuffer sBufferJson = new StringBuffer();
		if((hsMap.get("controlSQL")+"").toString().equals("sendOrderNumInEChartsMap")){//sendOrderNumInEChartsMap 销售智能分析>货物流向分析> 发货订单数在echarts地图中显示
			sBufferJson.append(getEchartsMapJsonDataToPage(dataList));
		}else{
			sBufferJson = sqlReturnDataHandle.queryTopViewBysqlStrnameAndsqlDoublenum(dataList,(String)hsMap.get("topNum"));
		}
		hsMapReturn.put("sBufferJsonToString",sBufferJson.toString());
		return hsMapReturn;
	}
	/** 销售智能分析>销售人员业绩排行>销售人员业绩订单金额Top10  &  销售人员业绩订单数量Top10  &... **/
	@Override
	public Map<String, Object> querySalesmanSellTopView(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = saleDataHqlProvider.querySalesmanSellTopView(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		StringBuffer sBufferJson = sqlReturnDataHandle.topBysqlstrnameAndsqldounumAndgroupbyid(dataList,(String)hsMap.get("topNum"));
		hsMapReturn.put("sBufferJsonToString",sBufferJson.toString());
		return hsMapReturn;
	}
	/** 销售智能分析>退货订单走势图查询   **/
	@SuppressWarnings({ "unchecked" })
	@Override
	public Map<String, Object> querySaleReturnView(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = saleDataHqlProvider.querySaleReturnView(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		ArrayList<String> valueArr = sqlReturnDataHandle.queryViewByYmdTimeAndDouble(dataList,(ArrayList<String>)hsMap.get("timeArr") );
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
		////////////////////<echarts地图数据处理方法  开始>////////////////////////////
		/** 此方法服务于 'echarts地图'（上方代码），省份数据的处理；<br>说明：1，echarts地图如果缺少那个省，会出现一些bug；<br>2，echarts地图传递省份相关数据时，没有省份顺序，但还是按下列顺序好。**/
		public static ArrayList<MapBeanInt> getEchartsMapProvinceData(){
		/*series : [ {
			name : '省份订单数',
			type : 'map',
			mapType : 'china',
			roam : false,
			data : [ {
				name : '北京',
				value : randomData()
			},.........等等{
				name : '澳门',
				value : randomData()
			} ]
		} ]
		};
		var myChart = echarts.init(document.getElementById('bar-chart4'));
		myChart.setOption(option);*/
		//此方法服务于 'echarts地图'（上方代码），省份数据的处理；
			ArrayList<MapBeanInt> strArr = new ArrayList<MapBeanInt>();
			//add开始
			strArr.add(new MapBeanInt("北京",0));
			strArr.add(new MapBeanInt("天津",0));
			strArr.add(new MapBeanInt("上海",0));
			strArr.add(new MapBeanInt("重庆",0));
			strArr.add(new MapBeanInt("河北",0));
			strArr.add(new MapBeanInt("河南",0));
			strArr.add(new MapBeanInt("云南",0));
			strArr.add(new MapBeanInt("辽宁",0));
			strArr.add(new MapBeanInt("黑龙江",0));
			strArr.add(new MapBeanInt("湖南",0));
			strArr.add(new MapBeanInt("安徽",0));
			strArr.add(new MapBeanInt("山东",0));
			strArr.add(new MapBeanInt("新疆",0));
			strArr.add(new MapBeanInt("江苏",0));
			strArr.add(new MapBeanInt("浙江",0));
			strArr.add(new MapBeanInt("江西",0));
			strArr.add(new MapBeanInt("湖北",0));
			strArr.add(new MapBeanInt("广西",0));
			strArr.add(new MapBeanInt("甘肃",0));
			strArr.add(new MapBeanInt("山西",0));
			strArr.add(new MapBeanInt("内蒙古",0));
			strArr.add(new MapBeanInt("陕西",0));
			strArr.add(new MapBeanInt("吉林",0));
			strArr.add(new MapBeanInt("福建",0));
			strArr.add(new MapBeanInt("贵州",0));
			strArr.add(new MapBeanInt("广东",0));
			strArr.add(new MapBeanInt("青海",0));
			strArr.add(new MapBeanInt("西藏",0));
			strArr.add(new MapBeanInt("四川",0));
			strArr.add(new MapBeanInt("宁夏",0));
			strArr.add(new MapBeanInt("海南",0));
			strArr.add(new MapBeanInt("台湾",0));
			strArr.add(new MapBeanInt("香港",0));
			strArr.add(new MapBeanInt("澳门",0));
			//add结束
			return strArr;
		}
		/** 为了控制EchartsMap地图颜色显示,让数据之间的颜色充分区分开来进行了数值运算，<br>使‘数据颜色均匀显示’<br>
		 *  <br>从而控制返回的json中 myMax对应的值用于echarts地图中数据 visualMap的最大值控制，<br>默认为10，根据查询数据的最大值再变化
		 *   **/
			public static Integer getEchartsMapMaxInteger(Integer myMax) {
				int result = 10;
				if(myMax == null ){
					myMax = 0;
				} 
				ArrayList<Integer> arr = new ArrayList<Integer>();
				if(myMax<=5000){
					int loop = 10;
					if(0<=myMax && myMax<=100){
						loop=10;
					}else if(100<myMax && myMax<=200){
						loop=20;
					}else if(200<myMax && myMax<=300){
						loop=30;
					}else if(300<myMax && myMax<=400){
						loop=40;
					}else if(400<myMax && myMax<=500){
						loop=50;
					}else if(500<myMax && myMax<=600){
						loop=60;
					}else if(600<myMax && myMax<=700){
						loop=70;
					}else if(700<myMax && myMax<=800){
						loop=80;
					}else if(800<myMax && myMax<=900){
						loop=90;
					}else if(900<myMax && myMax<=1000){
						loop=100;
					}else if(1000<myMax && myMax<=2000){
						loop=200;
					}else if(2000<myMax && myMax<=3000){
						loop=300;
					}else if(3000<myMax && myMax<=4000){
						loop=400;
					}else if(4000<myMax && myMax<=5000){
						loop=500;
					}
					for(int x=0;x<loop;x++) {
						arr.add(((x+1)*10));
					}
					for(int x=0;x<(arr.size()-1);x++) {
						Integer small = arr.get(x);  
						Integer big = arr.get(x+1);
						if(0<=myMax && myMax<=10){
							result = 10;
							break;
						}
						if(small<=myMax && myMax<=big){
							result = big;
							break;
						}
					}
				}else if(5000<myMax && myMax<=10000){
					result = 100000;
				}
				else if(10000<myMax && myMax<=50000){
					result = 500000;
				}
				else if(50000<myMax && myMax<=100000){
					result = 100000;
				}else if(100000<myMax && myMax<=1000000){
					result = 1000000;
				}else{
					result = myMax;//最后myMax比1000000还大，就取值myMax
				}
				return result;
			}
		/** 此方法用于从sql查询出来的地图数据，拼装出用于页面展示的json字符串json中<br>
		 * @param dataList 必须是 List(Object[])类型它有两个参数，前面是strName,后面是intNum<br> 
		 * @return 返回的json中 myMax对应的值用于echarts地图中数据 visualMap的最大值控制，默认为10，根据查询数据的值再变化
		 * **/
		public static String getEchartsMapJsonDataToPage(List<Object[]> ttList){
			ArrayList<MapBeanInt> provinceArr = getEchartsMapProvinceData();
			Integer myMax = 10;
			if(ttList !=null && ttList.size()>0){
				myMax = Integer.parseInt(ttList.get(0)[1].toString());//这里必须前面的sql进行的 order By num desc 使得myMax获得其get(0)[1]数值最大值
				for(int x=0;x<provinceArr.size();x++){
					String hkey = provinceArr.get(x).getKey();
					for(int y=0;y<ttList.size();y++){
						String province = ttList.get(y)[0].toString();//省
						String number = ttList.get(y)[1].toString();//多少个
						if(province.contains(hkey)){
							provinceArr.set(x, new MapBeanInt(hkey,Integer.parseInt(number)));
						}
					}
				}
			}
			/*下面myMax = getEchartsMapMaxInteger(myMax);是为了控制地图颜色显示*/
			myMax = getEchartsMapMaxInteger(myMax);
			ArrayList<String> dataArrStr = new ArrayList<String>();
			ArrayList<Integer> dataArrOriInt = new ArrayList<Integer>();
			for(int x=0;x<provinceArr.size();x++){
				dataArrOriInt.add(provinceArr.get(x).getValue());
				dataArrStr.add(provinceArr.get(x).getKey());
			}
			Gson gson = new Gson();
			String jsonReturnStr ="{\"numberResult\":"+dataArrOriInt.toString()+",\"stringResult\":"+gson.toJson(dataArrStr)+",\"myMax\":"+myMax+"}";
			return jsonReturnStr;
		}
		////////////////////</echarts地图数据处理方法  结束>////////////////////////////
		/**导出: 销售智能分析 > 销售统计仪表盘> 销售订单分析列表 **/
		@Override
		public ArrayList<SalesOrderAnalysisVo> outExcelToSaleStatisticsTable(Map<String, Object> hsMap)throws Exception {
			SalesOrderAnalysisVo salesOrderAnalysisVo = new SalesOrderAnalysisVo();
			ArrayList<SalesOrderAnalysisVo> salesOrderAnalysisVoList = new ArrayList<SalesOrderAnalysisVo>();
				/* //每列数据sql样式
				 SELECT
				t. NAME AS khname,
				sum(m.amounttotal) AS tmoney,
				DATE_FORMAT(
					m.billdate,
					'%Y-%m-%d %H:%i:%s'
				) AS mbilldate FROM sale_salesorder m INNER JOIN crm_customeraccount t ON m.customeraccount_id = t.id **/
			List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("excelSqlSaleStatisticsTable").toString(),new HashMap<String,Object>());
			if(dataList !=null && dataList.size()>0){
				Object[] objects = dataList.get(0);
				if(objects.length==3){//如果是3列数据
					for(int x=0;x<dataList.size();x++){
						Object[] objarr = dataList.get(x);
						salesOrderAnalysisVo.setLineNumber((x+1)+"");
						salesOrderAnalysisVo.setCustomerName(((objarr[0] != null ) ? objarr[0].toString() : ""));
						salesOrderAnalysisVo.setMoney(((objarr[1] != null ) ? objarr[1].toString() : ""));
						salesOrderAnalysisVo.setLatelyBuyTime(((objarr[2] != null ) ? objarr[2].toString() : ""));
						salesOrderAnalysisVoList.add(salesOrderAnalysisVo);       
						salesOrderAnalysisVo = new SalesOrderAnalysisVo();
					}
				}
			}
			return salesOrderAnalysisVoList;
		}
		/**导出: 销销售智能分析>销售明细表  **/
		@Override
		public ArrayList<SalesDetailedVo> outExcelToSaleDetailedTable(Map<String, Object> hsMap)throws Exception {
			SalesDetailedVo salesDetailedVo = new SalesDetailedVo();
			ArrayList<SalesDetailedVo> salesDetailedVoList = new ArrayList<SalesDetailedVo>();
				/* //每列数据sql样式
				 * SELECT
					date_format(
						t.billdate,
						'%Y-%m-%d %h:%i:%s'
					) AS mcreatetime,
					m.amount AS sl,
					m.price AS dj,
					tb. NAME AS cpmc,
					t. CODE AS djbm,
					t.customerAccount_id AS khid,
					t.regional_id AS dyid,
					tb. CODE AS cpbh
				FROM
					sale_saleorderitem m
				INNER JOIN sale_salesorder t ON m.salesorder_id = t.id
				INNER JOIN mdm_item tb ON m.item_id = tb.id
				  **/
			List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("excelSqlSaleDetailedTable").toString(),new HashMap<String,Object>());
			if(dataList !=null && dataList.size()>0){
				Object[] objects = dataList.get(0);
				if(objects.length==8){//如果是8列数据
					for(int x=0;x<dataList.size();x++){
						Object[] objarr = dataList.get(x);
						salesDetailedVo.setOrderDate(((objarr[0] != null ) ? objarr[0].toString() : ""));
						salesDetailedVo.setProductNum(((objarr[1] != null ) ? objarr[1].toString() : ""));
						salesDetailedVo.setProductPrice(((objarr[2] != null ) ? objarr[2].toString() : ""));
						salesDetailedVo.setProductName(((objarr[3] != null ) ? objarr[3].toString() : ""));
						salesDetailedVo.setOrderCode(((objarr[4] != null ) ? objarr[4].toString() : ""));
						salesDetailedVo.setProductCode(((objarr[7] != null ) ? objarr[7].toString() : ""));
						/** 查询地域名称  **/
						String querySqldy = " select name,'1' from common_regional where id='"+((objarr[6] != null ) ? objarr[6].toString() : "")+"' and name is not null and name <> '' ";
						salesDetailedVo.setRegionalName(queryOneDataBySql( querySqldy ));
						/** 查询客户名称  **/
						String querySqlkh = " select name,'1' from crm_customeraccount where id='"+((objarr[5] != null ) ? objarr[5].toString() : "")+"' and name is not null and name <> '' ";
						salesDetailedVo.setCustomer(queryOneDataBySql( querySqlkh ));
						salesDetailedVoList.add(salesDetailedVo);       
						salesDetailedVo = new SalesDetailedVo();
					}
				}
			}
			return salesDetailedVoList;
		}
		/**导出 销售智能分析>发货明细表 查询**/
		@Override
		public ArrayList<SalesDeliverGoodsVo> outExcelToSaleDeliverTable(Map<String, Object> hsMap)throws Exception {
			SalesDeliverGoodsVo salesDeliverGoodsVo = new SalesDeliverGoodsVo();  
			ArrayList<SalesDeliverGoodsVo> salesDeliverGoodsVoList = new ArrayList<SalesDeliverGoodsVo>();
				/* //每列数据sql样式
				 * SELECT
					many.goodsName,
					many.amount,
					many.price,
					many.goodsCode,
					DATE_FORMAT(
						toone.invoiceTime,
						'%Y-%m-%d %H:%i:%s'
					) AS fhtime,
					toone.orderCode,
					toone.receiverName,
					toone.receiverCity,
					toone.receiverMobile,
					toone.receiverState
				FROM
					e_invoicelistdetail many
				INNER JOIN e_invoicelist toone ON many.invoiceList_id = toone.id
				  **/
			List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("excelSqlSaleDeliverTable").toString(),new HashMap<String,Object>());
			if(dataList !=null && dataList.size()>0){
				Object[] objects = dataList.get(0);
				if(objects.length>=10){//如果是10列数据
					for(int x=0;x<dataList.size();x++){
						Object[] objarr = dataList.get(x);
						salesDeliverGoodsVo.setProductName(((objarr[0] != null ) ? objarr[0].toString() : ""));
						salesDeliverGoodsVo.setProductNum(((objarr[1] != null ) ? objarr[1].toString() : ""));
						salesDeliverGoodsVo.setProductPrice(((objarr[2] != null ) ? objarr[2].toString() : ""));
						salesDeliverGoodsVo.setProductCode(((objarr[3] != null ) ? objarr[3].toString() : ""));
						salesDeliverGoodsVo.setDate(((objarr[4] != null ) ? objarr[4].toString() : ""));
						salesDeliverGoodsVo.setOrderCode(((objarr[5] != null ) ? objarr[5].toString() : ""));
						salesDeliverGoodsVo.setConsigneeName(((objarr[6] != null ) ? objarr[6].toString() : ""));
						salesDeliverGoodsVo.setConsigneeCity(((objarr[7] != null ) ? objarr[7].toString() : ""));
						salesDeliverGoodsVo.setConsigneeTel(((objarr[8] != null ) ? objarr[8].toString() : ""));
						salesDeliverGoodsVo.setReceiverState(((objarr[9] != null ) ? objarr[9].toString() : ""));
						salesDeliverGoodsVoList.add(salesDeliverGoodsVo);       
						salesDeliverGoodsVo = new SalesDeliverGoodsVo();
					}
				}
			}
			return salesDeliverGoodsVoList;
		}
		/**导出: 销销售智能分析>销售明细账  **/
		@Override
		public ArrayList<SalesDetailedBookVo> outExcelToSaleDetailedTableBook(Map<String, Object> hsMap)throws Exception {
			SalesDetailedBookVo salesDetailedBookVo = new SalesDetailedBookVo();
			ArrayList<SalesDetailedBookVo> salesDetailedBookVoList = new ArrayList<SalesDetailedBookVo>();
				/* //每列数据sql样式
				 * SELECT
					date_format(
						t.billdate,
						'%Y-%m-%d %h:%i:%s'
					) AS mcreatetime,
					m.amount AS sl,
					m.price AS dj,
					tb. NAME AS cpmc,
					t. CODE AS djbm,
					t.customeraccount_id AS khid,
					t.regional_id AS dyid,
					tb. CODE AS cpbh,
					m.specification,8 规格型号
					m.taxprice,9含税单价
					m.netprice,10无税单价
					m.tax,11税率
					m.discount,12折扣率
					tb.measureUnit_id AS zjldwid
				FROM
					sale_saleorderitem m
				INNER JOIN sale_salesorder t ON m.salesorder_id = t.id
				INNER JOIN mdm_item tb ON m.item_id = tb.id
				  **/
			List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("excelSqlSaleDetailedTableBook").toString(),new HashMap<String,Object>());
			if(dataList !=null && dataList.size()>0){
				Object[] objects = dataList.get(0);
				if(objects.length==14){//如果是14列数据
					for(int x=0;x<dataList.size();x++){
						Object[] objarr = dataList.get(x);
						salesDetailedBookVo.setOrderDate(((objarr[0] != null ) ? objarr[0].toString() : ""));
						salesDetailedBookVo.setProductNum(((objarr[1] != null ) ? objarr[1].toString() : ""));
						salesDetailedBookVo.setProductPrice(((objarr[2] != null ) ? objarr[2].toString() : ""));
						salesDetailedBookVo.setProductName(((objarr[3] != null ) ? objarr[3].toString() : ""));
						salesDetailedBookVo.setOrderCode(((objarr[4] != null ) ? objarr[4].toString() : ""));
						salesDetailedBookVo.setProductCode(((objarr[7] != null ) ? objarr[7].toString() : ""));
						/** 查询地域名称  **/
						String querySqldy = " select name,'1' from common_regional where id='"+((objarr[6] != null ) ? objarr[6].toString() : "")+"' and name is not null and name <> '' ";
						salesDetailedBookVo.setRegionalName(queryOneDataBySql( querySqldy ));
						/** 查询客户名称  **/
						String querySqlkh = " select name,'1' from crm_customeraccount where id='"+((objarr[5] != null ) ? objarr[5].toString() : "")+"' and name is not null and name <> '' ";
						salesDetailedBookVo.setCustomer(queryOneDataBySql( querySqlkh ));
						salesDetailedBookVo.setProductStandard(((objarr[8] != null ) ? objarr[8].toString() : ""));
						salesDetailedBookVo.setTaxprice(((objarr[9] != null ) ? objarr[9].toString() : ""));
						salesDetailedBookVo.setNetprice(((objarr[10] != null ) ? objarr[10].toString() : ""));
						salesDetailedBookVo.setTax(((objarr[11] != null ) ? (objarr[11].toString()+"%") : ""));
						salesDetailedBookVo.setDiscount(((objarr[12] != null ) ? (objarr[12].toString()+"%") : ""));
						/** 查询主计量单位名称  **/
						String querySqldw = " select name,'1' from common_measureunit where id='"+((objarr[13] != null ) ? objarr[13].toString() : "")+"' and name is not null and name <> '' ";
						salesDetailedBookVo.setProductUnit(queryOneDataBySql( querySqldw ));
						salesDetailedBookVoList.add(salesDetailedBookVo);       
						salesDetailedBookVo = new SalesDetailedBookVo();
					}
				}
			}
			return salesDetailedBookVoList;
		}
		/**导出 销售智能分析>退货明细表 **/
		@Override
		public ArrayList<SalesReturnGoodsDetailedVo> outExcelToSaleReturnTable(Map<String, Object> hsMap)throws Exception {
			SalesReturnGoodsDetailedVo salesReturnGoodsDetailedVo = new SalesReturnGoodsDetailedVo();  
			ArrayList<SalesReturnGoodsDetailedVo> salesReturnGoodsDetailedVoList = new ArrayList<SalesReturnGoodsDetailedVo>();
				/* //每列数据sql样式
				 * SELECT
						many.itemCode,0
						many.itemName,1
						many.count,2
						many.unit,3
						many.price,4
						many.netTotal,5
						toone.customerAccount_id AS khid,6
						DATE_FORMAT(
							toone.returnAppTime,
							'%Y-%m-%d %H:%i:%s'
						) AS sqtime,7
						toone.returnOrderCode,8
						toone.returnAppTime,9
						DATE_FORMAT(
							toone.returnTime,
							'%Y-%m-%d %H:%i:%s'
						) AS thtime,10
						toone.approvalStatus11
					FROM
						sale_salereturnformitem many
					INNER JOIN sale_salereturnform toone ON many.salereturnform_id = toone.id
				  **/
			List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("excelSqlSaleReturnTable").toString(),new HashMap<String,Object>());
			if(dataList !=null && dataList.size()>0){
				Object[] objects = dataList.get(0);
				if(objects.length>=12){//如果是12列数据
					for(int x=0;x<dataList.size();x++){
						Object[] objarr = dataList.get(x);
						salesReturnGoodsDetailedVo.setProductCode(((objarr[0] != null ) ? objarr[0].toString() : ""));
						salesReturnGoodsDetailedVo.setProductName(((objarr[1] != null ) ? objarr[1].toString() : ""));
						salesReturnGoodsDetailedVo.setProductNum(((objarr[2] != null ) ? objarr[2].toString() : ""));
						salesReturnGoodsDetailedVo.setProductUnit(((objarr[3] != null ) ? objarr[3].toString() : ""));
						salesReturnGoodsDetailedVo.setProductPrice(((objarr[4] != null ) ? objarr[4].toString() : ""));
						/** 查询客户名称  **/
						String querySqlkh = " select name,'1' from crm_customeraccount where id='"+((objarr[6] != null ) ? objarr[6].toString() : "")+"' and name is not null and name <> '' ";
						salesReturnGoodsDetailedVo.setCustomer(queryOneDataBySql( querySqlkh ));
						salesReturnGoodsDetailedVo.setReturnAppTime(((objarr[7] != null ) ? objarr[7].toString() : ""));
						salesReturnGoodsDetailedVo.setReturnOrderCode(((objarr[8] != null ) ? objarr[8].toString() : ""));
						salesReturnGoodsDetailedVo.setReturnTime(((objarr[10] != null ) ? objarr[10].toString() : ""));
						salesReturnGoodsDetailedVo.setState(((objarr[11] != null ) ? objarr[11].toString() : ""));
						salesReturnGoodsDetailedVoList.add(salesReturnGoodsDetailedVo);       
						salesReturnGoodsDetailedVo = new SalesReturnGoodsDetailedVo();
					}
				}
			}
			return salesReturnGoodsDetailedVoList;
		}
		/** 导出采购智能分析 > 类型结构分析 > 产品类别采购分析列表  **/
		@Override
		public ArrayList<SalesmanAchieveVo> outExcelToStaffTable(Map<String, Object> hsMap)throws Exception {
			SalesmanAchieveVo salesmanAchieveVo = new SalesmanAchieveVo();
			ArrayList<SalesmanAchieveVo> salesmanAchieveVoList = new ArrayList<SalesmanAchieveVo>();
			//每列数据sql样式
				/* SELECT
						m. NAME,
						m.staffJobNumber,
						m.gender,
						m.telephone
					FROM
						hr_org_employee m ....
				 **/
			List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("outExcelSqlStaffTableTop").toString(),new HashMap<String,Object>());
			if(dataList !=null && dataList.size()>0){
				Object[] objects = dataList.get(0);
				if(objects.length==4){//如果是4列数据
					for(int x=0;x<dataList.size();x++){
						Object[] objarr = dataList.get(x);
						if((objarr[0] == null ) && (objarr[1] == null )  && (objarr[2] == null ) && (objarr[3] == null )  ){
							break;
						}
						salesmanAchieveVo.setLineNumber((x+1)+"");
						salesmanAchieveVo.setName(((objarr[0] != null ) ? objarr[0].toString() : ""));
						salesmanAchieveVo.setJobNum(((objarr[1] != null ) ? objarr[1].toString() : ""));
						String gender = ((objarr[2] != null ) ? objarr[2].toString() : "");
						if (StringUtils.isNotEmpty(gender)  ){
							if (gender.equals("1")) {
								gender = "男";
							} else if (gender.equals("0")) {  
								gender = "女";
							}
						}
						salesmanAchieveVo.setTel(((objarr[3] != null ) ? objarr[3].toString() : ""));
						salesmanAchieveVo.setSex(gender);
						salesmanAchieveVoList.add(salesmanAchieveVo);       
						salesmanAchieveVo = new SalesmanAchieveVo();
					}
				}
			}
			return salesmanAchieveVoList;
		}
		/** 导出:销售智能分析 > 销售结构分析 > 产品类别销售分析列表 **/
		@Override
		public ArrayList<PurchaseSupplierCostVo> outExcelToStructureSalesTopTable(Map<String, Object> hsMap)throws Exception {
			PurchaseSupplierCostVo purchaseSupplierCostVo = new PurchaseSupplierCostVo();
			ArrayList<PurchaseSupplierCostVo> purchaseSupplierCostVoList = new ArrayList<PurchaseSupplierCostVo>();
			//每列数据sql样式
				/* 
				 * SELECT
						t. NAME,
						t.money,
						t.pro
					FROM...
				 **/
			List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("outExcelSqlStructureSalesTop").toString(),new HashMap<String,Object>());
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
}
