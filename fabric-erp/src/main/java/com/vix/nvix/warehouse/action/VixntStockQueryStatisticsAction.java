package com.vix.nvix.warehouse.action;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xml.XmlAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.TransformerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.properties.util.MyTool;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.item.entity.Item;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;
import com.vix.nvix.purchase.action.vo.StockHasDistributionTableVo;
import com.vix.nvix.purchase.action.vo.StockHasMoneyDistributionVo;
import com.vix.nvix.purchase.action.vo.StockInOutDepositSummaryVo;
import com.vix.nvix.purchase.action.vo.StockInOutWaterAccountTableVo;
import com.vix.oa.personaloffice.service.IPurchaseDataService;
import com.vix.oa.personaloffice.service.IStockQueryStatisticsService;
/**
 *   为库存管理>库存报表 的统计服务
 */
@Controller
@Scope("prototype")
public class VixntStockQueryStatisticsAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	private String queryTime;
	private String inOrOutStock;//出库或入库
	private String invWarehouseID;//仓库id
	private String itemname;//检索:物料名称
	private String itemcode;//检索:物料编号
	private String itemID;//检索:物料id
	private String excessOrsave;//超额还是节约(限额领料)
	@Autowired
	private IPurchaseDataService purchaseDataService;
	@Autowired
	private IStockQueryStatisticsService stockQueryStatisticsService;
	/** 显示已经选择的时段 **/
	public void lookAlreadySelectTime() {
			try {
			    ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				StringBuffer fastAssemblingJson = new StringBuffer();
				fastAssemblingJson.append("{"+"\"startTimeLook\":" + ("\""+timeArr.get(0)+"\""));
				fastAssemblingJson.append(",\"endTimeLook\":" + ("\""+timeArr.get((timeArr.size()-1))+"\"") + "}" );
				renderJson(fastAssemblingJson.toString());
			} catch (Exception e) {
			}
	}
	/** 服务查询:拼装json串,用于帅选条件,选择仓库 **/
	public void serveToPageSetJson() {
		try {
			Employee employee = getEmployee();
			List<InvWarehouse> objList = null;
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				Map<String, Object> params = new HashMap<String, Object>();
				/*params.put("type," + SearchCondition.EQUAL, "1");*/
				params.put("tenantId," + SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				objList = baseHibernateService.findAllByConditions(InvWarehouse.class, params);
			}
			StringBuilder jsonSuggestObj =  new StringBuilder();
			jsonSuggestObj.append("{");
			jsonSuggestObj.append("\"value\":");
			jsonSuggestObj.append("[");
				jsonSuggestObj.append("{");
				jsonSuggestObj.append("\"id\": \"all"+"\",");
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
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	/** 库存管理>库存报表>出入库流水账   **/
	public String goStockInOutWaterAccount() {
		try {
			serveToPageSetJson();
			getRequest().setAttribute("todayStr",MyTool.getTodayBy_yyyyMMdd());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goStockInOutWaterAccount";
	}
	/** 库存管理>库存报表>出入库流水账 列表查询 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void searchStockInOutWaterAccountTable() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select t.flag,m.unit,m.itemcode,m.itemname,m.specification,m.quantity,m.unitcost,m.price,date_format(m.createtime, '%Y-%m-%d %h:%i:%s' ) as createtimetimestr");
				tableSql.append(" ,t.invwarehouse_id,t.code ");
				tableSql.append(" from inv_stockrecordlines m inner join inv_stockrecords t on m.invstockrecords_id=t.id ");   
				tableSql.append(" and t.type='2'  ");
				tableSql.append(" and m.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" and t.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and t.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				if(StringUtils.isNotEmpty(inOrOutStock) && "in".equals(inOrOutStock) ) {
					tableSql.append(" and t.flag = '1' ");  
				}else if(StringUtils.isNotEmpty(inOrOutStock) && "out".equals(inOrOutStock) ){
					tableSql.append(" and t.flag = '2' ");  
				}else {
					tableSql.append(" and t.flag in ('1','2') ");  
				}
				if(StringUtils.isNotEmpty(invWarehouseID)) {
					tableSql.append(" and t.invWarehouse_id = '"+invWarehouseID+"' ");  
				}else {
					tableSql.append(" and t.invWarehouse_id is not null ");  
				}
				tableSql.append(" order by t.createtime desc ");
				tablePager.setOrderField(null);
				Pager pager = this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					/** 查询仓库名称  **/
					String querySqlck = " select name,'1' from inv_warehouse where id='"+objectMap.get("invwarehouse_id")+"' and name is not null and name <> '' ";
					objectMap.put("ckname", purchaseDataService.queryOneDataBySql(querySqlck) );
				}
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////导出开始/////////////
	/** 库存管理>库存报表>出入库流水账的导出 **/
	public void outExcelToStorageTable() {
		try {
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select t.flag,m.unit,m.itemcode,m.itemname,m.specification,m.quantity,m.unitcost,m.price,date_format(m.createtime, '%Y-%m-%d %h:%i:%s' ) as createtimetimestr");
				tableSql.append(" ,t.invwarehouse_id,t.code ");
				tableSql.append(" from inv_stockrecordlines m inner join inv_stockrecords t on m.invstockrecords_id=t.id ");   
				tableSql.append(" and t.type='2'  ");
				tableSql.append(" and m.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" and t.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and t.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				if(StringUtils.isNotEmpty(inOrOutStock) && "in".equals(inOrOutStock) ) {
					tableSql.append(" and t.flag = '1' ");  
				}else if(StringUtils.isNotEmpty(inOrOutStock) && "out".equals(inOrOutStock) ){
					tableSql.append(" and t.flag = '2' ");  
				}else {
					tableSql.append(" and t.flag in ('1','2') ");  
				}
				if(StringUtils.isNotEmpty(invWarehouseID)) {
					tableSql.append(" and t.invWarehouse_id = '"+invWarehouseID+"' ");  
				}else {
					tableSql.append(" and t.invWarehouse_id is not null ");  
				}
				tableSql.append(" order by t.createtime desc ");
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("outExcelStockInOutWaterAccountTableVoList", tableSql.toString());
				ArrayList<StockInOutWaterAccountTableVo> stockInOutWaterAccountTableVoList = purchaseDataService.outExcelToStockInOutWaterAccountTable(hsMap);
				exportPurchaseStrorageExcel(stockInOutWaterAccountTableVoList);
				vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'库存管理>库存报表>出入库流水账'");  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 服务 库存管理>库存报表>出入库流水账的导出 **/
	public void exportPurchaseStrorageExcel(ArrayList<StockInOutWaterAccountTableVo> stockInOutWaterAccountTableVoList) {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");
			String ua = getRequest().getHeader("user-agent");
			String fileName = "出入库流水账.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("inOutWaterTable_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("inOutWaterTable_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("stockInOutWaterAccountTableVoList", stockInOutWaterAccountTableVoList);
					xlsArea.applyAt(new CellRef("stockInOutWaterAccountTableVo!A1"), context);
					transformer.write();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	///////////////////////////////////////////////////////导出结束/////////////////////////////////////////////////
	/////  库存管理>库存报表>收发存汇总表    导出开始
	/** 导出:库存管理>库存报表>收发存汇总表 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void outExcelToStockInOutDepositSummary() {
			try {
				Employee employee = getEmployee();
				if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
					ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
					StringBuffer tableSql = new StringBuffer();
					tableSql.append(" select tb.name as ckname,t.invwarehouse_id as tinvwarehouseid,m.itemcode, ");
					tableSql.append(" m.unit, m.itemname, m.specification, m.unitcost ");
					tableSql.append(" from inv_stockrecordlines m inner join inv_stockrecords t on m.invstockrecords_id=t.id inner join inv_warehouse tb on t.invWarehouse_id = tb.id ");   
					tableSql.append(" and t.type='2' and tb.name is not null and tb.name <> '' ");  
					tableSql.append(" and t.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
					tableSql.append(" and t.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
					tableSql.append(" and t.flag in ('1','2') ");
					if(StringUtils.isNotEmpty(invWarehouseID)) {
						tableSql.append(" and t.invWarehouse_id = '"+invWarehouseID+"' ");  
					}else {
						tableSql.append(" and t.invWarehouse_id is not null ");  
					}
					if(StringUtils.isNotEmpty(itemcode)) {
						tableSql.append(" and m.itemcode = '"+itemcode+"' ");  
					}
					if(StringUtils.isNotEmpty(itemname)) {
						tableSql.append(" and m.itemname like '%"+(decodeStr(itemname))+"%' ");  
					}
					tableSql.append(" GROUP BY m.itemcode,t.invWarehouse_id order by tb.id,t.id desc ");
					Map hsMap = new HashMap<String, Object>();
					hsMap.put("outExcelToStockInOutDepositSummary", tableSql);
					ArrayList<StockInOutDepositSummaryVo> stockInOutDepositSummaryVoList = stockQueryStatisticsService.outExcelToStockInOutDepositSummary(hsMap);
					if(stockInOutDepositSummaryVoList != null && stockInOutDepositSummaryVoList.size()>0) {
						for (int x = 0; x < stockInOutDepositSummaryVoList.size(); x++) {
							StockInOutDepositSummaryVo stockInOutDepositSummaryVo = stockInOutDepositSummaryVoList.get(x);
							String tinvwarehouseid = stockInOutDepositSummaryVo.getTinvwarehouseid();//仓库ID  
							String titemcode = stockInOutDepositSummaryVo.getItemcode();//商品编码    
							/** 查询期初数量  **/
							String termStartNumSql = assemblingSqlToNumB(tinvwarehouseid,titemcode,timeArr);
							stockInOutDepositSummaryVo.setTermStartNum(purchaseDataService.queryOneDataBySql(termStartNumSql));
							/** 查询入库数量  **/
							String inNumSql = assemblingSqlToNumIn(tinvwarehouseid,titemcode,timeArr);
							stockInOutDepositSummaryVo.setInNum(purchaseDataService.queryOneDataBySql(inNumSql));
							/** 查询出库数量  **/
							String outNumSql = assemblingSqlToNumOut(tinvwarehouseid,titemcode,timeArr);
							stockInOutDepositSummaryVo.setOutNum(purchaseDataService.queryOneDataBySql(outNumSql));
								String unitcost = stockInOutDepositSummaryVo.getUnitcost();
								String termStartNum = stockInOutDepositSummaryVo.getTermStartNum();
								String inNum = stockInOutDepositSummaryVo.getInNum();
								String outNum = stockInOutDepositSummaryVo.getOutNum();
	if(StringUtils.isNotEmpty(unitcost) && StringUtils.isNotEmpty(termStartNum) ) {
		stockInOutDepositSummaryVo.setTermStartMoney(  MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(   (Double.parseDouble(unitcost) * Double.parseDouble(termStartNum))  ))  );
	}
	if(StringUtils.isNotEmpty(unitcost) && StringUtils.isNotEmpty(inNum) ) {
		stockInOutDepositSummaryVo.setInMoney(  MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(   (Double.parseDouble(unitcost) * Double.parseDouble(inNum))  ))  );
	}
	if(StringUtils.isNotEmpty(unitcost) && StringUtils.isNotEmpty(outNum) ) {
		stockInOutDepositSummaryVo.setOutMoney(  MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(   (Double.parseDouble(unitcost) * Double.parseDouble(outNum))  ))  );
	}
	if(StringUtils.isNotEmpty(termStartNum) && StringUtils.isNotEmpty(inNum) && StringUtils.isNotEmpty(outNum) ) {
		String str = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(   (  Double.parseDouble(termStartNum)  + Double.parseDouble(inNum)   -   Double.parseDouble(outNum)  )    ));
		stockInOutDepositSummaryVo.setThisResultNum(str);
	}
	String thisResultNum = stockInOutDepositSummaryVo.getThisResultNum();
	if(StringUtils.isNotEmpty(thisResultNum) && StringUtils.isNotEmpty(unitcost) ) {
		stockInOutDepositSummaryVo.setThisResultMoney(  MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(   (Double.parseDouble(thisResultNum) * Double.parseDouble(unitcost))  ))  );
	}
							stockInOutDepositSummaryVoList.set(x, stockInOutDepositSummaryVo);
						}
					}
					exportStockInOutDepositSummaryExcel(stockInOutDepositSummaryVoList);
					vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'收发存汇总表'");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/** 服务导出:导出:库存管理>库存报表>收发存汇总表 **/
		public void exportStockInOutDepositSummaryExcel(ArrayList<StockInOutDepositSummaryVo> stockInOutDepositSummaryVoList) {
			try {
				HttpServletResponse excelResponse = getResponse();
				excelResponse.setContentType("application/octet-stream");
				excelResponse.setHeader("Charset", "UTF-8");
				excelResponse.setCharacterEncoding("UTF-8");
				String ua = getRequest().getHeader("user-agent");
				String fileName = "收发存汇总表.xls";
				if (ua != null && ua.indexOf("Firefox") >= 0)
					excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
				else
					excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
				try (InputStream is = ExcelTemplate.class.getResourceAsStream("stockInOutDepositSummaryTable_template.xls")) {
					Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
					try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("stockInOutDepositSummaryTable_template.xml")) {
						AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
						List<Area> xlsAreaList = areaBuilder.build();
						Area xlsArea = xlsAreaList.get(0);
						Context context = new Context();
						context.putVar("stockInOutDepositSummaryVoList", stockInOutDepositSummaryVoList);
						xlsArea.applyAt(new CellRef("stockInOutDepositSummaryVo!A1"), context);
						transformer.write();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	/////  库存管理>库存报表>收发存汇总表    导出结束
	/** 库存管理>库存报表>收发存汇总表   **/
	public String goStockInOutDepositSummary() {
		try {
			serveToPageSetJson();
			getRequest().setAttribute("todayStr",MyTool.getTodayBy_yyyyMMdd());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goStockInOutDepositSummary";
	}
	/** 解码 **/
	public String decodeStr(String str) throws Exception {
		if ( StringUtils.isNotEmpty(str) ) {
			return decode(str, "UTF-8");
		}
		return str;
	}
	/** 库存管理>库存报表>收发存汇总表 列表查询 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void searchStockInOutDepositSummaryTable() {
			try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select tb.name as ckname,t.invwarehouse_id as tinvwarehouseid,m.itemcode, ");
				tableSql.append(" m.unit, m.itemname, m.specification, m.unitcost ");
				tableSql.append(" from inv_stockrecordlines m inner join inv_stockrecords t on m.invstockrecords_id=t.id inner join inv_warehouse tb on t.invWarehouse_id = tb.id ");   
				tableSql.append(" and t.type='2' and tb.name is not null and tb.name <> '' ");  
				tableSql.append(" and t.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and t.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" and t.flag in ('1','2') ");
				if(StringUtils.isNotEmpty(invWarehouseID)) {
					tableSql.append(" and t.invWarehouse_id = '"+invWarehouseID+"' ");  
				}else {
					tableSql.append(" and t.invWarehouse_id is not null ");  
				}
				if(StringUtils.isNotEmpty(itemcode)) {
					tableSql.append(" and m.itemcode = '"+itemcode+"' ");  
				}
				if(StringUtils.isNotEmpty(itemname)) {
					tableSql.append(" and m.itemname like '%"+(decodeStr(itemname))+"%' ");  
				}
				tableSql.append(" GROUP BY m.itemcode,t.invWarehouse_id order by tb.id,t.id desc ");
				tablePager.setOrderField(null);
				Pager pager = this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					String tinvwarehouseid = (String)objectMap.get("tinvwarehouseid");//仓库ID  
					String titemcode = (String)objectMap.get("itemcode");//商品编码  
					/** 查询期初数量  **/
					String termStartNumSql = assemblingSqlToNumB(tinvwarehouseid,titemcode,timeArr);
					objectMap.put("termStartNum", purchaseDataService.queryOneDataBySql(termStartNumSql) );
					/** 查询入库数量  **/
					String inNumSql = assemblingSqlToNumIn(tinvwarehouseid,titemcode,timeArr);
					objectMap.put("inNum", purchaseDataService.queryOneDataBySql(inNumSql) );
					/** 查询出库数量  **/
					String outNumSql = assemblingSqlToNumOut(tinvwarehouseid,titemcode,timeArr);
					objectMap.put("outNum", purchaseDataService.queryOneDataBySql(outNumSql) );
				}
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 拼装sql语句用于查询'期初数量'做准备   @throws ParseException **/  
	public String assemblingSqlToNumA(String tinvwarehouseid /*仓库ID*/,String titemcode /*商品编码*/,ArrayList<String> timeArr /*时间段*/,String flagStr /*入库1,出库2*/ ) throws ParseException {
		StringBuffer sqlA = new StringBuffer();
		sqlA.append(" select IFNULL(sum(IFNULL(m.quantity,0)),0) ");
		sqlA.append(" from inv_stockrecordlines m inner join inv_stockrecords t on m.invstockrecords_id=t.id ");   
		sqlA.append(" and t.type='2' ");
		sqlA.append(" and t.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
		sqlA.append(" and t.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
		sqlA.append(" and t.flag ='"+flagStr+"' and m.quantity >= 0 and t.invWarehouse_id is not null ");
		sqlA.append(" and m.createtime < "+ MyTool.StringUseToSql(timeArr.get(0)));
		sqlA.append(" and t.invWarehouse_id = '"+tinvwarehouseid+"' ");
		sqlA.append(" and m.itemcode = '"+titemcode+"' ");  
		return sqlA.toString();
	}
	/** 拼装sql语句用于查询'期初数量'   @throws ParseException **/  
	public String assemblingSqlToNumB(String tinvwarehouseid /*仓库ID*/,String titemcode /*商品编码*/,ArrayList<String> timeArr /*时间段*/) throws ParseException {
		String sqlB = (" select (("+assemblingSqlToNumA(tinvwarehouseid,titemcode,timeArr,"1")+") - ("+assemblingSqlToNumA(tinvwarehouseid,titemcode,timeArr,"2")+")),'1' ");
		return sqlB;
	}
	/** 拼装sql语句用于查询'入库数量''出库数量'做准备   @throws ParseException **/  
	public String assemblingSqlToNumInOut(String tinvwarehouseid /*仓库ID*/,String titemcode /*商品编码*/,ArrayList<String> timeArr /*时间段*/,String flagStr /*入库1,出库2*/ ) throws ParseException {
		StringBuffer sqlA = new StringBuffer();
		sqlA.append(" select IFNULL(sum(IFNULL(m.quantity,0)),0) ");
		sqlA.append(" from inv_stockrecordlines m inner join inv_stockrecords t on m.invstockrecords_id=t.id ");   
		sqlA.append(" and t.type='2' ");
		sqlA.append(" and t.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
		sqlA.append(" and t.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
		sqlA.append(" and t.flag ='"+flagStr+"' and m.quantity >= 0 and t.invWarehouse_id is not null ");
		sqlA.append(" and m.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
		sqlA.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
		sqlA.append(" and t.invWarehouse_id = '"+tinvwarehouseid+"' ");
		sqlA.append(" and m.itemcode = '"+titemcode+"' ");  
		return sqlA.toString();
	}
	/** 拼装sql语句用于查询'入库数量'   @throws ParseException **/  
	public String assemblingSqlToNumIn(String tinvwarehouseid /*仓库ID*/,String titemcode /*商品编码*/,ArrayList<String> timeArr /*时间段*/) throws ParseException {
		String sqlstr = (" select ("+assemblingSqlToNumInOut(tinvwarehouseid,titemcode,timeArr,"1")+"),'1' ");
		return sqlstr;
	}
	/** 拼装sql语句用于查询'出库数量'   @throws ParseException **/  
	public String assemblingSqlToNumOut(String tinvwarehouseid /*仓库ID*/,String titemcode /*商品编码*/,ArrayList<String> timeArr /*时间段*/) throws ParseException {
		String sqlstr = (" select ("+assemblingSqlToNumInOut(tinvwarehouseid,titemcode,timeArr,"2")+"),'1' ");
		return sqlstr;
	}
	/** 库存管理>库存报表>存货分布表   **/
	public String goStockHasDistribution() {
		try {
			serveToPageSetJson();
			getRequest().setAttribute("todayStr",MyTool.getTodayBy_yyyyMMdd());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goStockHasDistribution";
	}
			/////       存货分布表 :导出开始
	/** 导出:库存管理>库存报表>存货分布表 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void outExcelToStockHasDistributionTable() {
			try {
				Employee employee = getEmployee();
				if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
					ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
					StringBuffer tableSql = new StringBuffer();
					tableSql.append(" select tb.name as ckname,t.invwarehouse_id as tinvwarehouseid,tb.code ");
					tableSql.append(" from inv_stockrecordlines m inner join inv_stockrecords t on m.invstockrecords_id=t.id inner join inv_warehouse tb on t.invWarehouse_id = tb.id ");   
					tableSql.append(" and t.type='2' and tb.name is not null and tb.name <> '' ");  
					tableSql.append(" and t.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
					tableSql.append(" and t.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
					tableSql.append(" and t.flag in ('1','2') ");
					if(StringUtils.isNotEmpty(invWarehouseID)) {
						tableSql.append(" and t.invWarehouse_id = '"+invWarehouseID+"' ");  
					}else {
						tableSql.append(" and t.invWarehouse_id is not null ");  
					}
					if(StringUtils.isNotEmpty(itemID)) {
						tableSql.append(" and m.itemcode = '"+itemID+"' ");  
					}
					tableSql.append(" group by t.invWarehouse_id order by tb.id,t.id desc ");
					Map hsMap = new HashMap<String, Object>();
					hsMap.put("outExcelToStockHasDistributionTable", tableSql);
					ArrayList<StockHasDistributionTableVo> stockHasDistributionTableVoList = stockQueryStatisticsService.outExcelToStockHasDistributionTable(hsMap);
					if(stockHasDistributionTableVoList != null && stockHasDistributionTableVoList.size()>0) {
						for (int x = 0; x < stockHasDistributionTableVoList.size(); x++) {
							StockHasDistributionTableVo stockHasDistributionTableVo = stockHasDistributionTableVoList.get(x);
							String tinvwarehouseid = stockHasDistributionTableVo.getTinvwarehouseid();//仓库ID
							String titemcode = itemID;//商品编码  
							/** 查询期初数量  **/
							String termStartNumSql = assemblingSqlToDistributionNumD(tinvwarehouseid,titemcode,timeArr);
							stockHasDistributionTableVo.setTermStartNum(purchaseDataService.queryOneDataBySql(termStartNumSql));
							/** 查询入库数量  **/
							String inNumSql = assemblingSqlToDistributionNumIn(tinvwarehouseid,titemcode,timeArr);
							stockHasDistributionTableVo.setInNum(purchaseDataService.queryOneDataBySql(inNumSql));
							/** 查询出库数量  **/
							String outNumSql = assemblingSqlToDistributionNumOut(tinvwarehouseid,titemcode,timeArr);
							stockHasDistributionTableVo.setOutNum(purchaseDataService.queryOneDataBySql(outNumSql));
								String termStartNum = stockHasDistributionTableVo.getTermStartNum();
								String inNum = stockHasDistributionTableVo.getInNum();
								String outNum = stockHasDistributionTableVo.getOutNum();
	if(StringUtils.isNotEmpty(termStartNum) && StringUtils.isNotEmpty(inNum) && StringUtils.isNotEmpty(outNum) ) {
		String str = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(   (  Double.parseDouble(termStartNum)  + Double.parseDouble(inNum)   -   Double.parseDouble(outNum)  )    ));
		stockHasDistributionTableVo.setThisResultNum(str);
	}
							stockHasDistributionTableVoList.set(x, stockHasDistributionTableVo);
						}
					}
					exportStockHasDistributionTableExcel(stockHasDistributionTableVoList);
					vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'存货分布表'");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/** 服务导出:  库存管理>库存报表>存货分布表 **/
		public void exportStockHasDistributionTableExcel(ArrayList<StockHasDistributionTableVo> stockHasDistributionTableVoList) {
			try {
				HttpServletResponse excelResponse = getResponse();
				excelResponse.setContentType("application/octet-stream");
				excelResponse.setHeader("Charset", "UTF-8");
				excelResponse.setCharacterEncoding("UTF-8");
				String ua = getRequest().getHeader("user-agent");
				String fileName = "存货分布表.xls";
				if (ua != null && ua.indexOf("Firefox") >= 0)
					excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
				else
					excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
				try (InputStream is = ExcelTemplate.class.getResourceAsStream("stockHasDistributionTable_template.xls")) {
					Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
					try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("stockHasDistributionTable_template.xml")) {
						AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
						List<Area> xlsAreaList = areaBuilder.build();
						Area xlsArea = xlsAreaList.get(0);
						Context context = new Context();
						context.putVar("stockHasDistributionTableVoList", stockHasDistributionTableVoList);
						xlsArea.applyAt(new CellRef("stockHasDistributionTableVo!A1"), context);
						transformer.write();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			/////       存货分布表 :导出结束
	/** 库存管理>库存报表>存货分布表 列表查询 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void searchStockHasDistributionTable() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select tb.name as ckname,t.invwarehouse_id as tinvwarehouseid,tb.code ");
				tableSql.append(" from inv_stockrecordlines m inner join inv_stockrecords t on m.invstockrecords_id=t.id inner join inv_warehouse tb on t.invWarehouse_id = tb.id ");   
				tableSql.append(" and t.type='2' and tb.name is not null and tb.name <> '' ");  
				tableSql.append(" and t.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and t.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" and t.flag in ('1','2') ");
				if(StringUtils.isNotEmpty(invWarehouseID)) {
					tableSql.append(" and t.invWarehouse_id = '"+invWarehouseID+"' ");  
				}else {
					tableSql.append(" and t.invWarehouse_id is not null ");  
				}
				if(StringUtils.isNotEmpty(itemID)) {
					tableSql.append(" and m.itemcode = '"+itemID+"' ");  
				}
				tableSql.append(" group by t.invWarehouse_id order by tb.id,t.id desc ");
				tablePager.setOrderField(null);
				Pager pager = this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					String tinvwarehouseid = (String)objectMap.get("tinvwarehouseid");//仓库ID  
					String titemcode = itemID;//商品编码  
					/** 查询期初数量  **/
					String termStartNumSql = assemblingSqlToDistributionNumD(tinvwarehouseid,titemcode,timeArr);
					objectMap.put("termStartNum", purchaseDataService.queryOneDataBySql(termStartNumSql) );
					/** 查询入库数量  **/
					String inNumSql = assemblingSqlToDistributionNumIn(tinvwarehouseid,titemcode,timeArr);
					objectMap.put("inNum", purchaseDataService.queryOneDataBySql(inNumSql) );
					/** 查询出库数量  **/
					String outNumSql = assemblingSqlToDistributionNumOut(tinvwarehouseid,titemcode,timeArr);
					objectMap.put("outNum", purchaseDataService.queryOneDataBySql(outNumSql) );
				}
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 拼装sql语句用于查询'存货分布表:期初数量'做准备   @throws ParseException **/  
	public String assemblingSqlToDistributionNumC(String tinvwarehouseid /*仓库ID*/,String titemcode /*商品编码*/,ArrayList<String> timeArr /*时间段*/,String flagStr /*入库1,出库2*/ ) throws ParseException {
		StringBuffer sqlA = new StringBuffer();
		sqlA.append(" select IFNULL(sum(IFNULL(m.quantity,0)),0) ");
		sqlA.append(" from inv_stockrecordlines m inner join inv_stockrecords t on m.invstockrecords_id=t.id ");   
		sqlA.append(" and t.type='2' ");
		sqlA.append(" and t.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
		sqlA.append(" and t.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
		sqlA.append(" and t.flag ='"+flagStr+"' and m.quantity >= 0 and t.invWarehouse_id is not null ");
		sqlA.append(" and m.createtime < "+ MyTool.StringUseToSql(timeArr.get(0)));
		sqlA.append(" and t.invWarehouse_id = '"+tinvwarehouseid+"' ");
		if(StringUtils.isNotEmpty(titemcode)) {
			sqlA.append(" and m.itemcode = '"+titemcode+"' ");  
		}
		return sqlA.toString();
	}
	/** 拼装sql语句用于查询'存货分布表:期初数量'   @throws ParseException **/  
	public String assemblingSqlToDistributionNumD(String tinvwarehouseid /*仓库ID*/,String titemcode /*商品编码*/,ArrayList<String> timeArr /*时间段*/) throws ParseException {
		String sqlB = (" select (("+assemblingSqlToDistributionNumC(tinvwarehouseid,titemcode,timeArr,"1")+") - ("+assemblingSqlToDistributionNumC(tinvwarehouseid,titemcode,timeArr,"2")+")),'1' ");
		return sqlB;
	}
	/** 拼装sql语句用于查询'存货分布表:入库数量''存货分布表:出库数量'做准备   @throws ParseException **/  
	public String assemblingSqlToDistributionNumInOut(String tinvwarehouseid /*仓库ID*/,String titemcode /*商品编码*/,ArrayList<String> timeArr /*时间段*/,String flagStr /*入库1,出库2*/ ) throws ParseException {
		StringBuffer sqlA = new StringBuffer();
		sqlA.append(" select IFNULL(sum(IFNULL(m.quantity,0)),0) ");
		sqlA.append(" from inv_stockrecordlines m inner join inv_stockrecords t on m.invstockrecords_id=t.id ");   
		sqlA.append(" and t.type='2' ");
		sqlA.append(" and t.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
		sqlA.append(" and t.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
		sqlA.append(" and t.flag ='"+flagStr+"' and m.quantity >= 0 and t.invWarehouse_id is not null ");
		sqlA.append(" and m.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
		sqlA.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
		sqlA.append(" and t.invWarehouse_id = '"+tinvwarehouseid+"' ");
		if(StringUtils.isNotEmpty(titemcode)) {
			sqlA.append(" and m.itemcode = '"+titemcode+"' ");  
		}  
		return sqlA.toString();
	}
	/** 拼装sql语句用于查询'存货分布表:入库数量'   @throws ParseException **/  
	public String assemblingSqlToDistributionNumIn(String tinvwarehouseid /*仓库ID*/,String titemcode /*商品编码*/,ArrayList<String> timeArr /*时间段*/) throws ParseException {
		String sqlstr = (" select ("+assemblingSqlToDistributionNumInOut(tinvwarehouseid,titemcode,timeArr,"1")+"),'1' ");
		return sqlstr;
	}
	/** 拼装sql语句用于查询'存货分布表:出库数量'   @throws ParseException **/  
	public String assemblingSqlToDistributionNumOut(String tinvwarehouseid /*仓库ID*/,String titemcode /*商品编码*/,ArrayList<String> timeArr /*时间段*/) throws ParseException {
		String sqlstr = (" select ("+assemblingSqlToDistributionNumInOut(tinvwarehouseid,titemcode,timeArr,"2")+"),'1' ");
		return sqlstr;
	}
	/** 库存管理>库存报表> layer弹窗检索物料 **/
	public String playBoxSearchItems() {
		return "playBoxSearchItems";
	}
	/** 库存管理>库存报表> 查物料列表 **/
	public void goDetailSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			params.put("itemClass," + SearchCondition.IN, "goods,finishedgoods");
			if(StringUtils.isNotEmpty(itemcode)) {
				params.put("code,"+SearchCondition.EQUAL, itemcode); 
			}
			if(StringUtils.isNotEmpty(itemname)) {
				params.put("name,"+SearchCondition.ANYLIKE, (decodeStr(itemname))); 
			}
			baseHibernateService.findPagerByHqlConditions(getPager(),Item.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 服务查询:拼装json串,用于帅选条件,选择仓库,传递仓库Code而不是ID **/
	public void serveToPageSetJsonCode() {
		try {
			Employee employee = getEmployee();
			List<InvWarehouse> objList = null;
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				Map<String, Object> params = new HashMap<String, Object>();
				/*params.put("type," + SearchCondition.EQUAL, "1");*/
				params.put("tenantId," + SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				objList = baseHibernateService.findAllByConditions(InvWarehouse.class, params);
			}
			StringBuilder jsonSuggestObj =  new StringBuilder();
			jsonSuggestObj.append("{");
			jsonSuggestObj.append("\"value\":");
			jsonSuggestObj.append("[");
				jsonSuggestObj.append("{");
				jsonSuggestObj.append("\"id\": \"all"+"\",");
				jsonSuggestObj.append("\"word\": \""+"全部仓库"+"\"");
				jsonSuggestObj.append("}");
			if(objList!=null && objList.size()>0){ 
				for(int x=0;x<objList.size();x++) {
						jsonSuggestObj.append(",{");
						jsonSuggestObj.append("\"id\": \""+objList.get(x).getCode()+"\",");
						jsonSuggestObj.append("\"word\": \""+objList.get(x).getName()+"\"");
						jsonSuggestObj.append("}");
				}
			}
			jsonSuggestObj.append("]");
			jsonSuggestObj.append("}");
			getRequest().setAttribute("jsonBsSuggestJava",jsonSuggestObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	/** 库存管理>库存报表>现存物料价值分布表   **/
	public String goStockHasMoneyDistribution() {
		try {
			serveToPageSetJsonCode();
			getRequest().setAttribute("todayStr",MyTool.getTodayBy_yyyyMMdd());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goStockHasMoneyDistribution";
	}
	/** 库存管理>库存报表>现存物料价值分布表 列表查询 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void searchStockHasMoneyDistribution() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				Map hsMap = new HashMap<String, Object>();
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
				hsMap.put("itemID", itemID);
				hsMap.put("invWarehouseID", invWarehouseID);
				Map<String, Object> searchMap = stockQueryStatisticsService.searchStockHasMoneyDistribution(hsMap);
				ArrayList<StockHasMoneyDistributionVo> objList = (ArrayList<StockHasMoneyDistributionVo>)searchMap.get("stockHasMoneyDistributionVoList");
				String totalDoubleStr = (String)searchMap.get("totalDoubleStr");
				StringBuilder sbsql = new StringBuilder();
				if(objList !=null && objList.size()>=1) {
					for(int x=0;x<objList.size();x++) {
						if(x==0) {
							sbsql.append(" select "+x+" as a ");
						}else {
							sbsql.append(" union all select "+x+" as a ");
						}
					}
				}
				if(sbsql !=null && !sbsql.toString().equals("")) {
					tablePager.setOrderField(null);
					Pager pager = this.baseHibernateService.findPagerBySqlFull(tablePager, sbsql.toString(), new HashMap<String, Object>());
					List resultList = pager.getResultList();
					for (int x = 0; x < resultList.size(); x++) {
						HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
						/** objectMap put查询到的   ArrayList<StockHasMoneyDistributionVo> objList 每列数据集合   **/
						objectMap.put("topstr", objList.get(x).getTopStr() );
						objectMap.put("itemname", objList.get(x).getItemname() );
						objectMap.put("itemcode", objList.get(x).getItemcode() );
						objectMap.put("price", objList.get(x).getPrice() );
						objectMap.put("avaquantity", objList.get(x).getAvaquantity() );
						objectMap.put("value", objList.get(x).getValue() );
						objectMap.put("proportion", totalDoubleStr );
					}
				}
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    /////////导出:库存管理>库存报表>现存物料价值分布表  START
	/** 导出:库存管理>库存报表>现存物料价值分布表 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void outExcelToStockHasMoneyDistribution() {
			try {
				Employee employee = getEmployee();
				if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
					Map hsMap = new HashMap<String, Object>();
					hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
					hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
					hsMap.put("itemID", itemID);
					hsMap.put("invWarehouseID", invWarehouseID);
					Map<String, Object> searchMap = stockQueryStatisticsService.searchStockHasMoneyDistribution(hsMap);
					ArrayList<StockHasMoneyDistributionVo> stockHasMoneyDistributionVoList = (ArrayList<StockHasMoneyDistributionVo>)searchMap.get("stockHasMoneyDistributionVoList");
					String totalDoubleStr = (String)searchMap.get("totalDoubleStr");
					if(stockHasMoneyDistributionVoList != null && stockHasMoneyDistributionVoList.size()>0 && StringUtils.isNotEmpty(totalDoubleStr)) {
						for(int x=0;x<stockHasMoneyDistributionVoList.size();x++) {
							StockHasMoneyDistributionVo stockHasMoneyDistributionVo = stockHasMoneyDistributionVoList.get(x);
							String value = stockHasMoneyDistributionVo.getValue();
							if(StringUtils.isNotEmpty(value)) {
								Double numDou =( (Double.parseDouble(value)/Double.parseDouble(totalDoubleStr)) * 100);
								String numDouStr = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(numDou)) + "%" ;
								stockHasMoneyDistributionVo.setProportion(numDouStr);
								stockHasMoneyDistributionVoList.set(x, stockHasMoneyDistributionVo);
							}
						}
					}
 					exportStockHasMoneyDistributionExcel(stockHasMoneyDistributionVoList);
					vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'现存物料价值分布表Top10'");  
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/** 服务导出:库存管理>库存报表>现存物料价值分布表 **/
		public void exportStockHasMoneyDistributionExcel(ArrayList<StockHasMoneyDistributionVo> stockHasMoneyDistributionVoList) {
			try {
				HttpServletResponse excelResponse = getResponse();
				excelResponse.setContentType("application/octet-stream");
				excelResponse.setHeader("Charset", "UTF-8");
				excelResponse.setCharacterEncoding("UTF-8");
				String ua = getRequest().getHeader("user-agent");
				String fileName = "现存物料价值分布表Top10.xls";
				if (ua != null && ua.indexOf("Firefox") >= 0)
					excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
				else
					excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
				try (InputStream is = ExcelTemplate.class.getResourceAsStream("stockHasMoneyDistributionTable_template.xls")) {
					Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
					try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("stockHasMoneyDistributionTable_template.xml")) {
						AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
						List<Area> xlsAreaList = areaBuilder.build();
						Area xlsArea = xlsAreaList.get(0);
						Context context = new Context();
						context.putVar("stockHasMoneyDistributionVoList", stockHasMoneyDistributionVoList);
						xlsArea.applyAt(new CellRef("stockHasMoneyDistributionVo!A1"), context);
						transformer.write();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	/////////导出:库存管理>库存报表>现存物料价值分布表  END
		/** 库存管理>库存报表>限额领料汇总表   **/
		public String goStockQuotaReceiveSummary() {
			try {
				serveToPageSetJsonCode();
				getRequest().setAttribute("todayStr",MyTool.getTodayBy_yyyyMMdd());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "goStockQuotaReceiveSummary";
		}
		/**  库存管理>库存报表>限额领料汇总表  列表查询  的  主sql **/
		public StringBuilder tableSqlStockQuotaReceiveSummaryTable(String isHasTenantIdAndInnerCode) throws Exception {
			StringBuilder tableSql = new StringBuilder();
			if(StringUtils.isNotEmpty(isHasTenantIdAndInnerCode)  && "yes".equals(isHasTenantIdAndInnerCode)) {
			    ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime); 
 tableSql.append(" select DATE_FORMAT(m.createtime, '%Y-%m-%d %H:%i:%s' ) as createtime ,m.itemcode,m.itemname,m.specification,m.unit,m.price,m.limitcount,m.requisitioncount,m.actualquantity ");
				tableSql.append(" ,t.warehousecode as tinvwarehouseid ,t.departmentmanager,t.pickingpeople,t.sendingpeople ");
				tableSql.append(" from inv_stocklimitedtakingitem m inner join inv_stocklimitedtaking t on m.stocklimitedtaking_id = t.id  ");
				tableSql.append(" and m.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and m.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				if(StringUtils.isNotEmpty(itemcode)) {
					tableSql.append(" and m.itemcode = '"+itemcode+"' ");  
				}
				if(StringUtils.isNotEmpty(itemname)) {
					tableSql.append(" and m.itemname like '%"+(decodeStr(itemname))+"%' ");  
				}
				tableSql.append(" and m.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				if(StringUtils.isNotEmpty(invWarehouseID)) {
					tableSql.append(" and t.warehousecode = '"+invWarehouseID+"' ");  
				}
				tableSql.append(" and t.warehousecode <> '' and t.warehousecode is not null ");
				tableSql.append(" and m.actualQuantity > 0 ");//实发数量 大于 0
				if(StringUtils.isNotEmpty(excessOrsave) && "excess".equals(excessOrsave) ) {//超额领料 时
					tableSql.append(" and m.limitCount >0 ");
					tableSql.append(" and m.actualQuantity >0 ");
					tableSql.append(" and m.actualQuantity > m.limitCount ");
				}else if(StringUtils.isNotEmpty(excessOrsave) && "save".equals(excessOrsave) ) {//限额以内 时
					tableSql.append(" and m.limitCount >0 ");
					tableSql.append(" and m.actualQuantity >0 ");
					tableSql.append(" and m.actualQuantity <= m.limitCount ");
				}
			}
			tableSql.append(" order by m.createtime,m.id,t.id desc ");
			return tableSql;
		}
		/**  库存管理>库存报表>限额领料汇总表  列表查询 **/
		@SuppressWarnings({"rawtypes", "unchecked"})
		public void searchStockQuotaReceiveSummaryTable() {
				try {
				Employee employee = getEmployee();
				Pager tablePager = getPager();
				if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
					StringBuilder tableSql = tableSqlStockQuotaReceiveSummaryTable("yes");
					tablePager.setOrderField(null);
					Pager pager = this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
					List resultList = pager.getResultList();
					for (int x = 0; x < resultList.size(); x++) {
						HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
						/** 根据仓库编码 查询仓库名称 **/
						String tinvwarehouseid = (String)objectMap.get("tinvwarehouseid");//仓库编码 
						String invwarehouseNameSql = "select name,'1' from inv_warehouse where code ='"+tinvwarehouseid+"' and name <> '' and name is not null ";
						objectMap.put("warehousenamestr", purchaseDataService.queryOneDataBySql(invwarehouseNameSql) );
					}
				}
				renderDataTable(tablePager);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 /** 为做原型页面时,原型'空列表'服务   **/
		public void emptyList() {
			renderDataTable(getPager());
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
	public String getInvWarehouseID() {
		return invWarehouseID;
	}
	public void setInvWarehouseID(String invWarehouseID) {
		this.invWarehouseID = invWarehouseID;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getExcessOrsave() {
		return excessOrsave;
	}
	public void setExcessOrsave(String excessOrsave) {
		this.excessOrsave = excessOrsave;
	}
	
}