package com.vix.oa.personaloffice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.vix.common.properties.util.CalculationDataVo;
import com.vix.common.properties.util.MapBeanDouble;
import com.vix.common.properties.util.MyTool;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.nvix.common.base.dao.IVixntBaseDao;
import com.vix.nvix.common.base.hql.PurchaseDataHqlProvider;
import com.vix.nvix.common.base.hql.SqlReturnDataHandle;
import com.vix.nvix.purchase.action.vo.StockHasDistributionTableVo;
import com.vix.nvix.purchase.action.vo.StockHasMoneyDistributionVo;
import com.vix.nvix.purchase.action.vo.StockInOutDepositSummaryVo;
import com.vix.oa.personaloffice.service.IStockQueryStatisticsService;
/**库存模块统计数据 sql查询**/
@Service("stockQueryStatisticsService")
public class StockQueryStatisticsServiceImpl extends BaseHibernateServiceImpl implements IStockQueryStatisticsService {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IVixntBaseDao vixntBaseDao;
	@Resource(name = "purchaseDataHqlProvider")
	private PurchaseDataHqlProvider purchaseDataHqlProvider;//hql语句提供者
	@Resource(name = "sqlReturnDataHandle")
	private SqlReturnDataHandle purchaseDataHandle;//List<Object[]>结果处理者
	
	/** 库存管理>库存报表>现存物料价值分布表 列表查询 **/
	@Override
	public Map<String, Object> searchStockHasMoneyDistribution(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = purchaseDataHqlProvider.searchStockHasMoneyDistribution(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		StockHasMoneyDistributionVo stockHasMoneyDistributionVo = new StockHasMoneyDistributionVo();
		ArrayList<StockHasMoneyDistributionVo> stockHasMoneyDistributionVoList = new ArrayList<StockHasMoneyDistributionVo>(); 
		/** 超过top10.top11...合并为其他(top10显示) **/
		Integer otherTopNumInt = 10;
		Integer otherTopNumIntSmall = otherTopNumInt-1;
		String totalDoubleStr = "0.0";//储存和值,str数字类型
		/** 对原生sql查询的集合dataList进行数据封装到vo中,因为要显示top10,当返回小于等于10时直接封装到voList,大于10时需要取出top9剩下的数值合并数值计算在页面显示为其他;
		 * 其中 totalDouble 用于取出不论返回几个数据的和值计算用于计算占比, temp用于储存其他的和值 ***/
		if(dataList !=null && dataList.size()>0){
			Double totalDouble = 0.0;//储存和值,double类型
			if( dataList.size() <=otherTopNumIntSmall ){
				for(int x=0;x<dataList.size();x++){
					String money = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo( dataList.get(x)[4].toString() ));//钱
					totalDouble += Double.parseDouble(money);
					String itemname =  (dataList.get(x)[0] !=null) ? dataList.get(x)[0].toString() : ""; 
					String itemcode =  (dataList.get(x)[1] !=null) ? dataList.get(x)[1].toString() : "";
					String price =     (dataList.get(x)[2] !=null) ? dataList.get(x)[2].toString() : "";
					String avaquantity =  (dataList.get(x)[3] !=null) ? dataList.get(x)[3].toString() : "";
					stockHasMoneyDistributionVo.setItemname(itemname); 
					stockHasMoneyDistributionVo.setItemcode(itemcode); 
					stockHasMoneyDistributionVo.setPrice(price); 
					stockHasMoneyDistributionVo.setAvaquantity(avaquantity);
					stockHasMoneyDistributionVo.setTopStr("top"+(x+1));
					stockHasMoneyDistributionVo.setValue(money);
					stockHasMoneyDistributionVoList.add(stockHasMoneyDistributionVo);
					stockHasMoneyDistributionVo = new StockHasMoneyDistributionVo();
				}
			}else{
				Double temp = 0.0;
				 for(int x=0;x<otherTopNumIntSmall;x++){
					String money = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo( dataList.get(x)[4].toString() ));//钱
					totalDouble += Double.parseDouble(money);
					String itemname =  (dataList.get(x)[0] !=null) ? dataList.get(x)[0].toString() : ""; 
					String itemcode =  (dataList.get(x)[1] !=null) ? dataList.get(x)[1].toString() : "";
					String price =     (dataList.get(x)[2] !=null) ? dataList.get(x)[2].toString() : "";
					String avaquantity =  (dataList.get(x)[3] !=null) ? dataList.get(x)[3].toString() : "";
					stockHasMoneyDistributionVo.setItemname(itemname); 
					stockHasMoneyDistributionVo.setItemcode(itemcode); 
					stockHasMoneyDistributionVo.setPrice(price); 
					stockHasMoneyDistributionVo.setAvaquantity(avaquantity);
					stockHasMoneyDistributionVo.setTopStr("top"+(x+1));
					stockHasMoneyDistributionVo.setValue(money);
					stockHasMoneyDistributionVoList.add(stockHasMoneyDistributionVo);
					stockHasMoneyDistributionVo = new StockHasMoneyDistributionVo();
				 }
				 if(dataList.size() == otherTopNumInt ){
					String money = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo( dataList.get(otherTopNumIntSmall)[4].toString() ));//钱
					totalDouble += Double.parseDouble(money);
					String itemname =  (dataList.get(otherTopNumIntSmall)[0] !=null) ? dataList.get(otherTopNumIntSmall)[0].toString() : ""; 
					String itemcode =  (dataList.get(otherTopNumIntSmall)[1] !=null) ? dataList.get(otherTopNumIntSmall)[1].toString() : "";
					String price =     (dataList.get(otherTopNumIntSmall)[2] !=null) ? dataList.get(otherTopNumIntSmall)[2].toString() : "";
					String avaquantity =  (dataList.get(otherTopNumIntSmall)[3] !=null) ? dataList.get(otherTopNumIntSmall)[3].toString() : "";
					stockHasMoneyDistributionVo.setItemname(itemname); 
					stockHasMoneyDistributionVo.setItemcode(itemcode); 
					stockHasMoneyDistributionVo.setPrice(price); 
					stockHasMoneyDistributionVo.setAvaquantity(avaquantity);
					stockHasMoneyDistributionVo.setTopStr("top"+(otherTopNumIntSmall+1));
					stockHasMoneyDistributionVo.setValue(money);
					stockHasMoneyDistributionVoList.add(stockHasMoneyDistributionVo);
					stockHasMoneyDistributionVo = new StockHasMoneyDistributionVo();
				 }else{
					 for(int x=otherTopNumIntSmall;x<dataList.size();x++){
						 Double money = Double.parseDouble(dataList.get(x)[4].toString());//钱
						 temp += money;
						 totalDouble += money;
					 }
					 stockHasMoneyDistributionVo.setValue(MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(temp+"")));
					 stockHasMoneyDistributionVo.setTopStr("其他");
					 stockHasMoneyDistributionVo.setItemname("---"); 
					 stockHasMoneyDistributionVo.setItemcode("---"); 
					 stockHasMoneyDistributionVo.setPrice("---"); 
					 stockHasMoneyDistributionVo.setAvaquantity("---");
					 stockHasMoneyDistributionVoList.add(stockHasMoneyDistributionVo);
					 stockHasMoneyDistributionVo = new StockHasMoneyDistributionVo();
				 }
			}
			totalDoubleStr = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(totalDouble+""));//保留2位小数,如果是科学计数法,就转化为数字型str
		}
		hsMapReturn.put("stockHasMoneyDistributionVoList", stockHasMoneyDistributionVoList);
		hsMapReturn.put("totalDoubleStr", totalDoubleStr);
		return hsMapReturn;
	}
	/** 慢加载：库存管理 > 库存报表 > 库存仪表盘 > 最近30日商品 入/出 库趋势图  数据查询   **/
	@SuppressWarnings({ "unchecked" })
	@Override
	public Map<String, Object> queryDataViewStockInOut(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = purchaseDataHqlProvider.queryDataViewStockInOut(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		ArrayList<String> valueArr = purchaseDataHandle.queryViewByYmdTimeAndDouble(dataList,(ArrayList<String>)hsMap.get("timeArr") );
		hsMapReturn.put("valueArr",valueArr);
		return hsMapReturn;
	}
	@Override    /**  查询sql的返回一个数值(门店库存报表 > 现存物料SKU数,本月过期物料量,库存不足物料SKU数,库存积压物料SKU数(库存积压:现存量>=200;库存不足:现存量<20;))  **/  
	public Map<String, Object> queryStockGoodsNum(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = purchaseDataHqlProvider.queryStockGoodsNum(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		Double totalDouble = purchaseDataHandle.queryNumToDouble(dataList);
		hsMapReturn.put("totalDouble", totalDouble);
		return hsMapReturn;
	}
	/** 导出:库存管理>库存报表>收发存汇总表 **/
	@Override
	public ArrayList<StockInOutDepositSummaryVo> outExcelToStockInOutDepositSummary(Map<String, Object> hsMap)throws Exception {
	    StockInOutDepositSummaryVo stockInOutDepositSummaryVo = new StockInOutDepositSummaryVo();
		ArrayList<StockInOutDepositSummaryVo> stockInOutDepositSummaryVoList = new ArrayList<StockInOutDepositSummaryVo>();
		//每列数据sql样式  stockInOutDepositSummaryVo
			/*SELECT        tb. NAME AS ckname, 0
							t.invwarehouse_id AS tinvwarehouseid, 1
							m.itemcode, 2
							m.unit, 3
							m.itemname, 4
							m.specification, 5
							m.unitcost   6
			FROM inv_stockrecordlines m ... */
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("outExcelToStockInOutDepositSummary").toString(),new HashMap<String,Object>());
		if(dataList !=null && dataList.size()>0){
			Object[] objects = dataList.get(0);
			if(objects.length>=7){//如果是7列数据
				for(Object[] objarr:dataList){
					stockInOutDepositSummaryVo.setCkname(((objarr[0] != null ) ? objarr[0].toString() : ""));
					stockInOutDepositSummaryVo.setTinvwarehouseid(((objarr[1] != null ) ? objarr[1].toString() : ""));
					stockInOutDepositSummaryVo.setItemcode(((objarr[2] != null ) ? objarr[2].toString() : ""));
					stockInOutDepositSummaryVo.setUnit(((objarr[3] != null ) ? objarr[3].toString() : ""));
					stockInOutDepositSummaryVo.setItemname(((objarr[4] != null ) ? objarr[4].toString() : ""));
					stockInOutDepositSummaryVo.setSpecification(((objarr[5] != null ) ? objarr[5].toString() : ""));
					stockInOutDepositSummaryVo.setUnitcost(((objarr[6] != null ) ? objarr[6].toString() : ""));
					stockInOutDepositSummaryVoList.add(stockInOutDepositSummaryVo);       
					stockInOutDepositSummaryVo = new StockInOutDepositSummaryVo();
				}
			}
		}
		return stockInOutDepositSummaryVoList;
	}
	/** 导出:库存管理>库存报表>存货分布表 **/
	@Override
	public ArrayList<StockHasDistributionTableVo> outExcelToStockHasDistributionTable(Map<String, Object> hsMap)throws Exception {
		StockHasDistributionTableVo stockHasDistributionTableVo = new StockHasDistributionTableVo();
		ArrayList<StockHasDistributionTableVo> stockHasDistributionTableVoList = new ArrayList<StockHasDistributionTableVo>();
		//每列数据sql样式  stockInOutDepositSummaryVo
			/*SELECT        tb. NAME AS ckname,   0
							t.invwarehouse_id AS tinvwarehouseid,   1
							tb. CODE   2
			FROM inv_stockrecordlines m ... */
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hsMap.get("outExcelToStockHasDistributionTable").toString(),new HashMap<String,Object>());
		if(dataList !=null && dataList.size()>0){
			Object[] objects = dataList.get(0);
			if(objects.length>=3){//如果是3列数据
				for(Object[] objarr:dataList){
					stockHasDistributionTableVo.setCkname(((objarr[0] != null ) ? objarr[0].toString() : ""));
					stockHasDistributionTableVo.setTinvwarehouseid(((objarr[1] != null ) ? objarr[1].toString() : ""));
					stockHasDistributionTableVo.setCode(((objarr[2] != null ) ? objarr[2].toString() : ""));
					stockHasDistributionTableVoList.add(stockHasDistributionTableVo);       
					stockHasDistributionTableVo = new StockHasDistributionTableVo();
				}
			}
		}
		return stockHasDistributionTableVoList;
	}
	/** 这里计算的是所有仓库的 库龄结构:计算'库龄结构'(自定义查询'30天以下,30天-60天,60天到180天,180天以上'的入库数量)   **/
	@Override
	public String calculationStockAge(Map<String, Object> hsMap)throws Exception {
		hsMap.put("controlSQL", "queryGroupByCustomTime");//queryGroupByCustomTime构造自定义groupby时间问题
		Map<String, Object> hplMap = purchaseDataHqlProvider.calculationStockAge(hsMap);
		String[] hqlArr = (String[])hplMap.get("hqlArr");
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hplMap.get("hqlStringBuilder").toString(),new HashMap<String,Object>());
		MapBeanDouble mbdou = new MapBeanDouble();
		ArrayList<MapBeanDouble>  arrData = new ArrayList<MapBeanDouble>(); 
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dataList.size();x++){
				mbdou = new MapBeanDouble(hqlArr[x],MyTool.roundingTwoDecimalTwo(  dataList.get(x)[0].toString() )   );
				arrData.add(mbdou);   
				mbdou = new MapBeanDouble();
			}
		}
		Double total = 0.0;//这是得到的现存商品的数量
		if(hsMap.containsKey("companyInnerCode") && hsMap.containsKey("tenantId") ){// 查询现存物料总数量
			StringBuffer sbsql = new StringBuffer();
			sbsql.append(" select IFNULL(sum(quantity),0),'1' from inv_inventory m where 1=1 ");
			sbsql.append(" and m.avaquantity > 0 and m.skucode is not null and m.skucode <> '' and m.createtime is not null ");
			sbsql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			sbsql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			List<Object[]> innerList = vixntBaseDao.findAllBySql(sbsql.toString(),new HashMap<String,Object>());
			if(innerList !=null && innerList.size()>0){
				Object[] objarr = innerList.get(0);
				if(objarr[0] != null ){
					total = Double.parseDouble(objarr[0].toString());
				}
			}
		}
		total = MyTool.roundingTwoDecimal(total);
		ArrayList<CalculationDataVo>  arrNew = new ArrayList<CalculationDataVo>();
		for(int x=0;x<arrData.size();x++) {
			arrNew.add(getCalculationDataVoList((x+1),arrData));
		}
		for(int x=0;x<arrNew.size();x++) {
			CalculationDataVo v = arrNew.get(x);
			if(v.getValueb() > total) {
				v.setValuec(-1.0);
				arrNew.set(x, v);
			}
		}
		int index = 0;
		for(int x=0;x<arrNew.size();x++) {
			CalculationDataVo v = arrNew.get(x);
			if(v.getValuec() != null && v.getValuec() == -1.0) {
				try {
				v.setValuea(MyTool.roundingTwoDecimal(total-arrNew.get(x-1).getValueb()));
				arrNew.set(x, v);
				}catch (Exception e) {
					v.setValuea(total);
					arrNew.set(x, v);
				}
				index++;
			}
			if(index > 1 ) {
				v.setValuea(0.0);
				arrNew.set(x, v);
			}
		}
		//开始返回json串
		ArrayList<String> nameArr = new ArrayList<String>();
		ArrayList<Double> valueArr = new ArrayList<Double>();
		for(int x=0;x<arrNew.size();x++) {
			CalculationDataVo calculationDataVo = arrNew.get(x);
			nameArr.add( calculationDataVo.getKey() );
			valueArr.add( MyTool.roundingTwoDecimal(calculationDataVo.getValuea()) );
		}
		Gson gosn = new Gson();
		StringBuffer returnBufferJson = new StringBuffer();
		returnBufferJson.append("{"+"\"nameArr\":" + gosn.toJson(nameArr));
		returnBufferJson.append(",\"valueArr\":" + valueArr.toString() + "}" );
		return returnBufferJson.toString();
	}
	/** 这里计算的是所有仓库的 库龄结构:计算'库龄结构'(自定义查询'30天以下,30天-60天,60天到180天,180天以上'的入库数量)  而准备的方法   **/
	public static CalculationDataVo getCalculationDataVoList(int in,ArrayList<MapBeanDouble>  arrData){
		Double tp=0.0;
		String key = "";
		Double ysz=0.0;
		for(int x=0;x<in;x++) {
			tp += arrData.get(x).getValue();
			key = arrData.get(x).getKey();
			ysz = arrData.get(x).getValue();
		}
		return new CalculationDataVo(key,ysz,tp,null);
	}
	/** 查询:收货商品数量,收货SKU数,收货订单数,收货平均时长 **/
	@Override
	public Map<String, Object> queryBlockAbc(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = purchaseDataHqlProvider.queryBlockAbc(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		int inTimeLengthHour = 0,inTimeLengthMinute = 0;//收货平均时长(小时和分钟)
		if((hsMap.get("controlSQL")+"").toString().equals("inTimeLength")){//收货平均时长 inTimeLength
			if(dataList !=null && dataList.size()==2 ) {
				Object[] ar = dataList.get(0);
				Object[] br = dataList.get(1);
				if(ar[0] !=null && br[0] !=null && !ar[0].toString().equals("") && !br[0].toString().equals("") ) {
					long endUnixTimeStamp = Long.parseLong(ar[0].toString());
					long startUnixTimeStamp = Long.parseLong(br[0].toString());
					if(endUnixTimeStamp>startUnixTimeStamp) {
						long answer = (endUnixTimeStamp-startUnixTimeStamp)/60;
						inTimeLengthMinute = Integer.parseInt(String.valueOf(answer%60)); 
						inTimeLengthHour = Integer.parseInt(String.valueOf(answer/60)); 
					}
				}
			}
		}
		Double[] totalDoubleArr = purchaseDataHandle.queryNumToDoubleTwoValues(dataList);
		hsMapReturn.put("totalDouble", totalDoubleArr[0]);
		hsMapReturn.put("totalDoubleB", totalDoubleArr[1]);
		hsMapReturn.put("inTimeLengthMinute", inTimeLengthMinute);
		hsMapReturn.put("inTimeLengthHour", inTimeLengthHour);
		return hsMapReturn;
	}
	/**查询'进销存概览'的收货商品总数折线图和收货SKU数折线图 **/
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryViewBrokenLineData(Map<String, Object> hsMap)throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = purchaseDataHqlProvider.queryViewBrokenLineData(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		ArrayList<String> valueArr = purchaseDataHandle.queryViewByYmdTimeAndDouble(dataList,(ArrayList<String>)hsMap.get("timeArr") );
		hsMapReturn.put("valueArr",valueArr);
		return hsMapReturn;
	}
	/** 查询金额比例top柱图和top饼图 **/
	@SuppressWarnings("unchecked")
	@Override
	public String queryViewMoneyTopData(Map<String, Object> hsMap)throws Exception {
		StringBuilder hql = purchaseDataHqlProvider.queryViewMoneyTopData(hsMap);
		List<Object[]> dataList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
		ArrayList<String> nameArrStr = new ArrayList<String>();
		Map<String, Object> objMap = purchaseDataHandle.queryTopBarAndTopPieBySqlStrnameAndSqlDoublenum(dataList,"10");
		ArrayList<String> nameArr = (ArrayList<String>)objMap.get("nameArr");
		for(String strsku:nameArr) {
			String queryName = " select name,'1' from mdm_item where skuCode='"+strsku+"' and name is not null and name <> '' ";
			nameArrStr.add(this.queryOneDataBySql(queryName));
		}
		ArrayList<String> valueArr = (ArrayList<String>)objMap.get("valueArr");
		String doubleA = (String)objMap.get("doubleA");
		String doubleB = (String)objMap.get("doubleB");
		Double douSumAB = Double.parseDouble(doubleA) + Double.parseDouble(doubleB);
		String pieProportionTopTen = "0.00";//饼图中top10金额和的占比
		String pieProportionOther = "0.00";//饼图中top10之后其他金额和的占比
		if(douSumAB !=null && douSumAB > 0.0) {
			 Double pieProportionTopTenDou = MyTool.roundingDoubleAppointDecimal((Double.parseDouble(doubleA)/douSumAB),4);
			 Double pieProportionOtherDou = MyTool.roundingDoubleAppointDecimal((Double.parseDouble(doubleB)/douSumAB),4);
			 pieProportionTopTen = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo( (pieProportionTopTenDou*100)+"" )); 
			 pieProportionOther = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo( (pieProportionOtherDou*100)+"" )); 
		}
		nameArrStr = completeTenArr(nameArrStr,"");
		nameArr = completeTenArr(nameArr,"");
		valueArr = completeTenArr(valueArr,"0.00");
		ArrayList<String> valueArrProportion = calculationProportion(valueArr,douSumAB);
		/** 开始返回查询到的结果**/
     	Gson gson = new Gson();
		StringBuilder returnBufferJson = new StringBuilder();
		returnBufferJson.append("{"+"\"pieProportionTopTen\":" + MyTool.StringJsonReturn(pieProportionTopTen) );//计算出的:饼图中top10金额和的占比,加%即可  
		returnBufferJson.append(",\"pieProportionOther\":" + MyTool.StringJsonReturn(pieProportionOther) );//计算出的:饼图中top10之后其他金额和的占比,加%即可   
		returnBufferJson.append(",\"doubleA\":" + doubleA );//计算出的:饼图中top10 的金额之和 (元) 
		returnBufferJson.append(",\"doubleB\":" + doubleB );//计算出的:饼图中top10之后其他金额和 (元) 
	  returnBufferJson.append(",\"nameArr\":" + gson.toJson( reverseArrayListString(nameArr)) );//计算出的:柱图中top前10(含top10)的SKU数组    
	  returnBufferJson.append(",\"nameArrStr\":" + gson.toJson( reverseArrayListString(nameArrStr)) );//计算出的:柱图中top前10(含top10)的SKU相对应的 物料名称数组     
	  returnBufferJson.append(",\"valueArrProportion\":" + gson.toJson(reverseArrayListString(valueArrProportion)) );//计算出的:柱图中top前10(含top10)的 物料金额占比,加%即可   
	  returnBufferJson.append(",\"valueArr\":" + gson.toJson(reverseArrayListString(valueArr)) + "}" );//计算出的:柱图中top前10(含top10)的 物料金额(元)   
		return returnBufferJson.toString();
	}
	/** 为'queryViewMoneyTopData'方法服务的:倒排 ArrayList<String> 返回  **/
	public static ArrayList<String> reverseArrayListString(ArrayList<String> arr){
		if(arr != null && arr.size()>0) {
			for(int start=0,end=arr.size()-1;start<end;start++,end--) {
				String temp = arr.get(end);
				arr.set(end, arr.get(start));
				arr.set(start, temp);
			}
		}
		return arr;
	}
	/** 为'queryViewMoneyTopData'方法服务的:计算占比处理 **/
	public static ArrayList<String> calculationProportion(ArrayList<String> valueArr,Double douSumAB){
		ArrayList<String> valueArrProportion = new ArrayList<String>();
		for(String s:valueArr) {
			Double pro = 0.0;
			if(douSumAB !=null && douSumAB > 0.0) {
				pro = MyTool.roundingDoubleAppointDecimal((Double.parseDouble(s)/douSumAB),4);//pro 占比
			}  
			valueArrProportion.add(  MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo( (pro*100)+"" ))  );  
		}
		return valueArrProportion;
	}
	/** 为'queryViewMoneyTopData'方法服务的:补齐10个 元素集合 **/
	public static ArrayList<String> completeTenArr(ArrayList<String> arr,String str){
		for(int x=0;x<10;x++) {
			if(arr !=null && arr.size()>=10) {
				break;
			}
			arr.add(str);
		}
		return arr;
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
}
