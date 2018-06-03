package com.vix.nvix.warehouse.action;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.vix.common.properties.util.MyTool;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.personaloffice.service.IStockQueryStatisticsService;
/**
 *   为库存管理>库存报表>促销推荐等 的统计服务
 */
@Controller
@Scope("prototype")
public class VixntStockSerachDataAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	private String queryTime;
	private String inOrOutStock;//出库或入库
	private String channelDistributorID; //仓库所属门店id
	private String warehouseID; //指定仓库id
	@Autowired
	private IStockQueryStatisticsService stockQueryStatisticsService;
	/** 库存管理 > 库存报表 > 进销存概览   **/
	public String goinOutStoreOverviewPage() {
		getRequest().setAttribute("todayStr",MyTool.getTodayBy_yyyyMMdd());
		serveToPageSetJsonHouse();
		return "goinOutStoreOverviewPage";
	}
	/** 查询:收货商品数量,收货SKU数,收货订单数,收货平均时长 **/
	public void queryBlockAbc() {
		try {
			Double inStoreQuantity = 0.0,inSKUquantity = 0.0;
			Double orderQuantity = 0.0;//收货订单数
			int inTimeLengthHour = 0,inTimeLengthMinute = 0;//收货平均时长(小时和分钟)
			Map<String, Object> hsMap = new HashMap<String, Object>();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
				hsMap.put("controlSQL", "inStoreQuantity");//收货商品数量 inStoreQuantity
				hsMap.put("queryTime", queryTime);
				hsMap.put("channelDistributorID", channelDistributorID);
				hsMap.put("warehouseID", warehouseID);
				inStoreQuantity = (Double)stockQueryStatisticsService.queryBlockAbc(hsMap).get("totalDouble");
				hsMap.put("controlSQL", "inSKUquantity");//收货SKU数&收货订单数 inSKUquantity
				Map<String, Object> objDoubleArr = stockQueryStatisticsService.queryBlockAbc(hsMap);
				inSKUquantity = (Double)objDoubleArr.get("totalDouble");
				orderQuantity = (Double)objDoubleArr.get("totalDoubleB");
				hsMap.put("controlSQL", "inTimeLength");//收货平均时长 inTimeLength
				Map<String, Object> objTimeLengthArr = stockQueryStatisticsService.queryBlockAbc(hsMap);
				inTimeLengthHour = (int)objTimeLengthArr.get("inTimeLengthHour");
				inTimeLengthMinute = (int)objTimeLengthArr.get("inTimeLengthMinute");
			}
			StringBuilder returnBufferJson = new StringBuilder();
			returnBufferJson.append("{"+"\"inStoreQuantity\":" + inStoreQuantity );
			returnBufferJson.append(",\"orderQuantity\":" + orderQuantity );   
			returnBufferJson.append(",\"inTimeLengthHour\":" + inTimeLengthHour );
			returnBufferJson.append(",\"inTimeLengthMinute\":" + inTimeLengthMinute );
			returnBufferJson.append(",\"inSKUquantity\":" + inSKUquantity + "}" );
			renderJson(returnBufferJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 根据给定日期'2018-04-25'获得以'2018-04-25'为终点的倒推y天时间数组arr(时间正序);  
	 * 例如:ArrayList<String> timeArr = givenTimeBackYday("2018-01-07",7);打印timeArr得到[2018-01-01, 2018-01-02, 2018-01-03, 2018-01-04, 2018-01-05, 2018-01-06, 2018-01-07]
	 * @throws ParseException **/
	public ArrayList<String> givenTimeBackYday(String timeyyyyMMdd,int y) throws ParseException {
		ArrayList<String>  timeArr = new ArrayList<String>();
		for(int x=-(y-1);x<=0;x++) {
			String timeStr = MyTool.dateReduceOrAddByInt(timeyyyyMMdd,x);
			timeArr.add(timeStr);
		}
		return timeArr;
	}
	/** 查询:收货商品数量,收货SKU数,收货订单数 **/
	@SuppressWarnings("unchecked")
	public void queryViewBrokenLineData() {
		try {
			ArrayList<String> inStoreQuantityValueArr = new ArrayList<String>();
			for(int x=0;x<7;x++) {
				inStoreQuantityValueArr.add("0.0");
			}
			ArrayList<String> inSKUquantityValueArr = new ArrayList<String>();
			inSKUquantityValueArr.addAll(inStoreQuantityValueArr);
			ArrayList<String>  timeArr = new ArrayList<String>();
			if(StringUtils.isNotEmpty(queryTime)) {
				timeArr = givenTimeBackYday(queryTime,7);
			}else {
				timeArr = givenTimeBackYday(MyTool.getTodayBy_yyyyMMdd(),7);
			}
			Map<String, Object> hsMap = new HashMap<String, Object>();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
				hsMap.put("controlSQL", "inStoreQuantityBrokenLineData");//inStoreQuantityBrokenLineData 收货商品数量 折线图数据 
				hsMap.put("timeArr",timeArr);
				hsMap.put("channelDistributorID", channelDistributorID);
				hsMap.put("warehouseID", warehouseID);
				inStoreQuantityValueArr = (ArrayList<String>)stockQueryStatisticsService.queryViewBrokenLineData(hsMap).get("valueArr");
				hsMap.put("controlSQL", "inSKUquantityBrokenLineData");//inSKUquantityBrokenLineData 收货SKU数量 折线图数据 
				inSKUquantityValueArr = (ArrayList<String>)stockQueryStatisticsService.queryViewBrokenLineData(hsMap).get("valueArr");
			}
			Gson gson = new Gson();
			StringBuilder returnBufferJson = new StringBuilder();
			returnBufferJson.append("{"+"\"timeArr\":" + gson.toJson(MyTool.substringDateStrTwo_Array(timeArr)) );
			returnBufferJson.append(",\"inStoreQuantityValueArr\":" + inStoreQuantityValueArr.toString() );   
			returnBufferJson.append(",\"inSKUquantityValueArr\":" + inSKUquantityValueArr.toString() + "}" );
			renderJson(returnBufferJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 查询金额比例top柱图和top饼图 **/
	public void queryViewMoneyTopData() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("controlSQL", "GoodsReceiptMoneyTop");//GoodsReceiptMoneyTop 查询金额比例top柱图和top饼图
			hsMap.put("queryTime",queryTime);
			hsMap.put("channelDistributorID", channelDistributorID);
			hsMap.put("warehouseID", warehouseID);
			String jsonStr = stockQueryStatisticsService.queryViewMoneyTopData(hsMap);
			renderJson(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 库存管理 > 库存报表 > 促销推荐    **/
	public String goRecommendPage() {
		return "goRecommendPage";
	}
	///////////////////////////////////////////////////////////////
	/** 服务查询:拼装json串,用于帅选条件,选择仓库, (注意:如果有门店id就查询指定门店id的所以仓库,否则查询指定TenantId的仓库) **/
	public void serveToPageSetJsonHouse() {
		try {
			Employee employee = getEmployee();
			List<InvWarehouse> objList = null;
			String qChannelDistributorID = "";
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("tenantId," + SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				if(employee.getChannelDistributor() != null && employee.getChannelDistributor().getId() != null ){
					qChannelDistributorID = employee.getChannelDistributor().getId();
					params.put("channelDistributor.id," + SearchCondition.EQUAL, qChannelDistributorID);
				}
				objList = baseHibernateService.findAllByConditions(InvWarehouse.class, params);
			}
			StringBuilder jsonSuggestObj =  new StringBuilder();
			jsonSuggestObj.append("{");
			jsonSuggestObj.append("\"value\":");
			jsonSuggestObj.append("[");
				jsonSuggestObj.append("{");
				jsonSuggestObj.append("\"id\": \"all!"+qChannelDistributorID+""+"\",");
				jsonSuggestObj.append("\"word\": \""+"全部仓库"+"\"");
				jsonSuggestObj.append("}");
			if(objList!=null && objList.size()>0){ 
				for(int x=0;x<objList.size();x++) {
						jsonSuggestObj.append(",{");
						jsonSuggestObj.append("\"id\": \""+objList.get(x).getId()+"\",");
						jsonSuggestObj.append("\"word\": \""+objList.get(x).getName()+"\"");
						jsonSuggestObj.append("}");
				}
			}
			jsonSuggestObj.append("]");
			jsonSuggestObj.append("}");
			getRequest().setAttribute("jsonBsSuggestJava",jsonSuggestObj.toString());
			getRequest().setAttribute("qChannelDistributorID",   ("\""+qChannelDistributorID+"\"")   );
			getRequest().setAttribute("qbChannelDistributorID",   qChannelDistributorID  );
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	public String getInOrOutStock() {
		return inOrOutStock;
	}
	public void setInOrOutStock(String inOrOutStock) {
		this.inOrOutStock = inOrOutStock;
	}
	public String getChannelDistributorID() {
		return channelDistributorID;
	}
	public void setChannelDistributorID(String channelDistributorID) {
		this.channelDistributorID = channelDistributorID;
	}
	public String getWarehouseID() {
		return warehouseID;
	}
	public void setWarehouseID(String warehouseID) {
		this.warehouseID = warehouseID;
	}
	
}