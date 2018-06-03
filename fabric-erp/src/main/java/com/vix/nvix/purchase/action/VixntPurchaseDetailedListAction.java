package com.vix.nvix.purchase.action;
import java.io.InputStream;
import java.net.URLEncoder;
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

import com.google.gson.Gson;
import com.vix.common.properties.util.MyTool;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.vixlog.IOperateLog;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;
import com.vix.nvix.purchase.action.vo.PurchaseArrivalDetailsVo;
import com.vix.nvix.purchase.action.vo.PurchaseDetailedVo;
import com.vix.nvix.purchase.action.vo.PurchaseMaterielCostVo;
import com.vix.nvix.purchase.action.vo.PurchaseStrorageDetailsVo;
import com.vix.nvix.purchase.action.vo.PurchaseSupplierCostVo;
import com.vix.oa.personaloffice.service.IPurchaseDataService;
/**
 * @类全名 com.vix.nvix.purchase.action.VixntPurchaseDetailedListAction
 */
@Controller
@Scope("prototype")
public class VixntPurchaseDetailedListAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	private List<Supplier> supplierList;  
	private List<ItemCatalog> itemCatalogList;
	private List<InvWarehouse> invWarehouseList;
	@Autowired
	private IPurchaseDataService purchaseDataService;
	@Autowired
	public IOperateLog vixOperateLog;
	private String queryTime;
	private String topNum;
	private String supplierID;//供应商id
	private String invWarehouseID;//仓库id
	/** 这里的分类，只按二级分类进行查询TOP数据；如飞行设备包括飞机ID1，火箭ID2；则这里的itemCatalogID一定是飞机ID1，火箭ID2中的一个； **/
	private String itemCatalogID;
	/** 这里的分类twolevelCatalogID：按所有二级分类混排排序   **/
	private String twolevelCatalogID;  
	/** 采购智能分析>采购明细 **/
	public String goDetailedView() {
		try {
			Map<String, Object> params = getParams();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				params.put("status," + SearchCondition.EQUAL, "3");
				supplierList = vixntBaseService.findAllDataByConditions(Supplier.class, params);
			}
			StringBuffer jsonObj =  new StringBuffer();
			jsonObj.append("{");
			jsonObj.append("\"value\":");
			jsonObj.append("[");
				jsonObj.append("{");
				jsonObj.append("\"id\": \""+"all"+"\",");
				jsonObj.append("\"word\": \""+"全部供应商"+"\"");
				jsonObj.append("}");
			if(supplierList!=null && supplierList.size()>0){ 
				for(int x=0;x<supplierList.size();x++) {
						jsonObj.append(",{");
						jsonObj.append("\"id\": \""+supplierList.get(x).getId()+"\",");
						jsonObj.append("\"word\": \""+supplierList.get(x).getName()+"\"");
						jsonObj.append("}");
				}
			}
			jsonObj.append("]");
			jsonObj.append("}");
			getRequest().setAttribute("jsonObj",jsonObj.toString());
			getRequest().setAttribute("todayStr",MyTool.getTodayBy_yyyyMMdd());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDetailedView";
	}
	/** 根据supplierID查询返回供应商的id  用于sql条件的in使用 
	 * @throws Exception **/
	//例如select TOTALAMOUNT from pur_order where TOTALAMOUNT IN (NULL) 回查询出空数据
	public String getSqlInAllSupplierID(){
		try {
			Employee employee = getEmployee();
			StringBuffer sb = new StringBuffer();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				Map<String, Object> params = getParams();
				params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				params.put("status," + SearchCondition.EQUAL, "3");
				List<Supplier> lst = vixntBaseService.findAllDataByConditions(Supplier.class, params);
				if(lst !=null && lst.size()>0){
					for(int x=0;x<lst.size();x++) {
						if(x==0){
							sb.append("'"+lst.get(x).getId()+"'");
						}
						if(x!=0){
							sb.append(",'"+lst.get(x).getId()+"'");
						}
					}
				}
			}
			String sbStr = sb.toString();
			return (StringUtils.isNotEmpty(sbStr) ? sbStr:"NULL");
		} catch (Exception e) {
			e.printStackTrace();
			return "NULL";
		}
	}
	/** 根据invWarehouseID查询返回仓库的id  用于sql条件的in使用 
	 * @throws Exception **/
	//例如select TOTALAMOUNT from pur_order where TOTALAMOUNT IN (NULL) 回查询出空数据
	public String getSqlInAllInvWarehouseID(){
		try {
			Employee employee = getEmployee();
			StringBuffer sb = new StringBuffer();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				Map<String, Object> params = getParams();
				params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				params.put("type," + SearchCondition.EQUAL, "1");
				List<InvWarehouse> lst = vixntBaseService.findAllDataByConditions(InvWarehouse.class, params);
				if(lst !=null && lst.size()>0){
					for(int x=0;x<lst.size();x++) {
						if(x==0){
							sb.append("'"+lst.get(x).getId()+"'");
						}
						if(x!=0){
							sb.append(",'"+lst.get(x).getId()+"'");
						}
					}
				}
			}
			String sbStr = sb.toString();
			return (StringUtils.isNotEmpty(sbStr) ? sbStr:"NULL");
		} catch (Exception e) {
			e.printStackTrace();
			return "NULL";
		}
	}
	/**采购明细:采购走势图 查询**/
	@SuppressWarnings("unchecked")
	public void queryPurchaseMoneyView() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
				if (StringUtils.isNotEmpty(supplierID)) {
					hsMap.put("supplierID", "'"+supplierID+"'" );
				}else {
					hsMap.put("supplierID", getSqlInAllSupplierID());
				}
			}
			hsMap.put("timeArr",timeArr);
			ArrayList<String> valueArr = (ArrayList<String>) purchaseDataService.queryPurchaseMoneyView(hsMap).get("valueArr");
			ArrayList<String> timeStr = MyTool.substringDateStrTwo_Array(timeArr);
			Gson gosn = new Gson();
			StringBuffer fastAssemblingJson = new StringBuffer();
			fastAssemblingJson.append("{"+"\"timeStr\":" + gosn.toJson(timeStr));
			fastAssemblingJson.append(",\"valueArr\":" + valueArr.toString() + "}" );
			renderJson(fastAssemblingJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 采购明细:采购明细表 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void goOrderQueryTable() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				String sqlinsupplierID = (StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() );
				tableSql.append(" select toone.id as orid,many.itemcode,many.itemname,many.amount,many.price,many.unit, ");
				tableSql.append(" toone.purchaseperson,DATE_FORMAT(toone.createtime, '%Y-%m-%d %H:%i:%s' ) as mcreatetime,toone.code,toone.status ");
				tableSql.append(" from pur_orderlineitem many inner join pur_order toone on many.purchaseorder_id=toone.id "); 
				tableSql.append(" and toone.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and toone.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" and many.itemname is not null and many.itemname <> '' ");
				tableSql.append(" and many.itemcode is not null and many.itemcode <> '' ");
				tableSql.append(" and many.price is not null and many.price <> '' ");
				tableSql.append(" and many.amount is not null and many.amount <> '' ");
				tableSql.append(" and toone.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and toone.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" and toone.supplier_id in("+sqlinsupplierID+") ");
				tableSql.append(" order by toone.createtime desc ");
				Pager pager = this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					/** 查询供应商名 queryName  **/
					StringBuffer queryName = new StringBuffer();
					queryName.append(" select toone.name as suppliername,'1' from pur_order many inner join srm_supplier toone on many.supplier_id=toone.id ");
					queryName.append(" and many.id='"+objectMap.get("orid")+"'");
					queryName.append(" and toone.name is not null and toone.name <> '' ");
					objectMap.put("suppliername", purchaseDataService.queryOneDataBySql(queryName.toString())   );
				}
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	////////<outExcel>//////
	/** 采购明细:采购明细表的导出 **/
	public void outExcelToPurchaseDetailed() {
		try {
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				String sqlinsupplierID = (StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() );
				tableSql.append(" select many.itemname,many.unit,toone.id as orid,many.itemcode,many.amount,many.price, ");
				tableSql.append(" toone.purchaseperson,DATE_FORMAT(toone.createtime, '%Y-%m-%d %H:%i:%s' ) as mcreatetime,toone.code,toone.status ");
				tableSql.append(" from pur_orderlineitem many inner join pur_order toone on many.purchaseorder_id=toone.id "); 
				tableSql.append(" and toone.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and toone.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" and many.itemname is not null and many.itemname <> '' ");
				tableSql.append(" and many.itemcode is not null and many.itemcode <> '' ");
				tableSql.append(" and many.price is not null and many.price <> '' ");
				tableSql.append(" and many.amount is not null and many.amount <> '' ");
				tableSql.append(" and toone.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and toone.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" and toone.supplier_id in("+sqlinsupplierID+") ");
				tableSql.append(" order by toone.createtime desc ");
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("outExcelSqlPurchaseDetailedVo", tableSql.toString());
				ArrayList<PurchaseDetailedVo> purchaseDetailedVoList = purchaseDataService.outExcelToPurchaseDetailed(hsMap);
				exportPurchaseDetailExcel(purchaseDetailedVoList);
				vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'采购智能分析>采购明细>采购明细表'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//导出
	public void exportPurchaseDetailExcel(ArrayList<PurchaseDetailedVo> purchaseDetailedVoList) {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");

			String ua = getRequest().getHeader("user-agent");
			String fileName = "采购明细表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

			try (InputStream is = ExcelTemplate.class.getResourceAsStream("purchaseDetail_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("purchaseDetail_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("purchaseDetailedVoList", purchaseDetailedVoList);
					xlsArea.applyAt(new CellRef("purchaseDetailedVo!A1"), context);
					transformer.write();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	////////</outExcel>//////
	/** 采购智能分析>到货明细 **/
	public String goArrivalDetailedView() {
		try {
			Map<String, Object> params = getParams();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				params.put("status," + SearchCondition.EQUAL, "3");
				supplierList = vixntBaseService.findAllDataByConditions(Supplier.class, params);
			}
			StringBuffer jsonObj =  new StringBuffer();
			jsonObj.append("{");
			jsonObj.append("\"value\":");
			jsonObj.append("[");
				jsonObj.append("{");
				jsonObj.append("\"id\": \""+"all"+"\",");
				jsonObj.append("\"word\": \""+"全部供应商"+"\"");
				jsonObj.append("}");
			if(supplierList!=null && supplierList.size()>0){ 
				for(int x=0;x<supplierList.size();x++) {
						jsonObj.append(",{");
						jsonObj.append("\"id\": \""+supplierList.get(x).getId()+"\",");
						jsonObj.append("\"word\": \""+supplierList.get(x).getName()+"\"");
						jsonObj.append("}");
				}
			}
			jsonObj.append("]");
			jsonObj.append("}");
			getRequest().setAttribute("jsonObj",jsonObj.toString());
			getRequest().setAttribute("todayStr",MyTool.getTodayBy_yyyyMMdd());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goArrivalDetailedView";
	}
	/** 采购智能分析>到货明细 列表 **/
	public void searchArrivalTable() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				String sqlinsupplierID = (StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() );
				tableSql.append(" select many.itemcode,many.itemname,many.amount,many.price,many.unit, ");
				tableSql.append(" date_format(toone.createtime, '%Y-%m-%d %h:%i:%s' ) as mcreatetime, ");
				tableSql.append(" toone.suppliername,toone.purchaseperson,toone.code,date_format(toone.deliverydate, '%Y-%m-%d %h:%i:%s' ) as jiaohuotime ");  
				tableSql.append(" from pur_arrivalitems many inner join pur_arrival toone on many.purchasearrival_id=toone.id "); 
				tableSql.append(" and toone.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and toone.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" and many.itemname is not null and many.itemname <> '' ");
				tableSql.append(" and many.itemcode is not null and many.itemcode <> '' ");
				tableSql.append(" and many.price is not null and many.price <> '' ");
				tableSql.append(" and many.amount is not null and many.amount <> '' ");
				tableSql.append(" and toone.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and toone.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" and toone.supplierid in("+sqlinsupplierID+") ");
				tableSql.append(" order by toone.createtime desc ");
				tablePager.setOrderField(null);
				this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 采购智能分析 > 到货明细>到货明细表的导出 **/
	public void outExcelToArrivalTable() {
		try {
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				String sqlinsupplierID = (StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() );
				tableSql.append(" select many.itemcode,many.itemname,many.amount,many.price,many.unit, ");
				tableSql.append(" date_format(toone.createtime, '%Y-%m-%d %h:%i:%s' ) as mcreatetime, ");
				tableSql.append(" toone.suppliername,toone.purchaseperson,toone.code,date_format(toone.deliverydate, '%Y-%m-%d %h:%i:%s' ) as jiaohuotime ");  
				tableSql.append(" from pur_arrivalitems many inner join pur_arrival toone on many.purchasearrival_id=toone.id "); 
				tableSql.append(" and toone.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and toone.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" and many.itemname is not null and many.itemname <> '' ");
				tableSql.append(" and many.itemcode is not null and many.itemcode <> '' ");
				tableSql.append(" and many.price is not null and many.price <> '' ");
				tableSql.append(" and many.amount is not null and many.amount <> '' ");
				tableSql.append(" and toone.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and toone.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" and toone.supplierid in("+sqlinsupplierID+") ");
				tableSql.append(" order by toone.createtime desc ");
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("outExcelSqlPurchaseArrivalDetailsVo", tableSql.toString());
				ArrayList<PurchaseArrivalDetailsVo> purchaseArrivalDetailsVoList = purchaseDataService.outExcelToArrivalTable(hsMap);
				exportPurchaseArrivalExcel(purchaseArrivalDetailsVoList);
				vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'采购智能分析>到货明细>到货明细表'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 采购智能分析 > 到货明细>服务  到货明细表的导出 **/
	public void exportPurchaseArrivalExcel(ArrayList<PurchaseArrivalDetailsVo> purchaseArrivalDetailsVoList) {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");
			String ua = getRequest().getHeader("user-agent");
			String fileName = "到货明细表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("purchaseArrival_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("purchaseArrival_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("purchaseArrivalDetailsVoList", purchaseArrivalDetailsVoList);
					xlsArea.applyAt(new CellRef("purchaseArrivalDetailsVo!A1"), context);
					transformer.write();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	///////////////////////////////////////////////////////////////////
	/** 采购智能分析>入库明细 **/
	public String goStorageDetailedView() {
		try {
			Map<String, Object> params = getParams();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				params.put("status," + SearchCondition.EQUAL, "3");
				supplierList = vixntBaseService.findAllDataByConditions(Supplier.class, params);
				Map<String, Object> paramsWarehouse = new HashMap<String, Object>();
				paramsWarehouse.put("type," + SearchCondition.EQUAL, "1");
				paramsWarehouse.put("tenantId,"+ SearchCondition.EQUAL, params.get("tenantId,"+ SearchCondition.EQUAL)  );
				paramsWarehouse.put("companyInnerCode,"+ SearchCondition.EQUAL, params.get("companyInnerCode,"+ SearchCondition.EQUAL)  );
				invWarehouseList = vixntBaseService.findAllDataByConditions(InvWarehouse.class, paramsWarehouse);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			getRequest().setAttribute("todayStr",MyTool.getTodayBy_yyyyMMdd());
			StringBuffer jsonObj =  new StringBuffer();
			jsonObj.append("{");
			jsonObj.append("\"value\":");
			jsonObj.append("[");
				jsonObj.append("{");
				jsonObj.append("\"id\": \""+"all"+"\",");
				jsonObj.append("\"word\": \""+"全部供应商"+"\"");
				jsonObj.append("}");
			if(supplierList!=null && supplierList.size()>0){ 
				for(int x=0;x<supplierList.size();x++) {
						jsonObj.append(",{");
						jsonObj.append("\"id\": \""+supplierList.get(x).getId()+"\",");
						jsonObj.append("\"word\": \""+supplierList.get(x).getName()+"\"");
						jsonObj.append("}");
				}
			}
			jsonObj.append("]");
			jsonObj.append("}");
			getRequest().setAttribute("jsonObj",jsonObj.toString());
			/////
			StringBuffer jsonObjHouse =  new StringBuffer();
			jsonObjHouse.append("{");
			jsonObjHouse.append("\"value\":");
			jsonObjHouse.append("[");
				jsonObjHouse.append("{");
				jsonObjHouse.append("\"id\": \""+"all"+"\",");
				jsonObjHouse.append("\"word\": \""+"全部仓库"+"\"");
				jsonObjHouse.append("}");
			if(invWarehouseList!=null && invWarehouseList.size()>0){ 
				for(int x=0;x<invWarehouseList.size();x++) {
						jsonObjHouse.append(",{");
						jsonObjHouse.append("\"id\": \""+invWarehouseList.get(x).getId()+"\",");
						jsonObjHouse.append("\"word\": \""+invWarehouseList.get(x).getName()+"\"");
						jsonObjHouse.append("}");
				}
			}
			jsonObjHouse.append("]");
			jsonObjHouse.append("}");
			getRequest().setAttribute("jsonObjHouse",jsonObjHouse.toString());
		return "goStorageDetailedView";
	}
	/** 采购智能分析>入库明细 列表 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void searchStrorageTable() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				String sqlinsupplierID = (StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() );
				String sqlinInvWarehouseID = (StringUtils.isNotEmpty(invWarehouseID) ? "'"+invWarehouseID+"'" : getSqlInAllInvWarehouseID() );
				tableSql.append(" select toone.warehousePerson as jbperson,toone.code,many.supplier_id as supplierid,date_format(toone.createtime, '%Y-%m-%d %h:%i:%s' ) as mcreatetime ");
				tableSql.append(" ,toone.invWarehouse_id as ckid,many.invShelf_id as hwid ");
				tableSql.append(" ,many.itemcode,many.itemname,many.quantity,many.price,many.unit,many.unitcost ");
				tableSql.append(" from INV_STOCKRECORDLINES many inner join INV_STOCKRECORDS toone on many.INVSTOCKRECORDS_ID=toone.id "); 
				tableSql.append(" and toone.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and toone.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" and many.supplier_id in("+sqlinsupplierID+") ");
				tableSql.append(" and toone.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and toone.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" and toone.invWarehouse_id in("+sqlinInvWarehouseID+") ");
				tableSql.append(" order by toone.createtime desc ");
				tablePager.setOrderField(null);
				Pager pager = this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					/** 查询货位名称  **/
					String querySqlhw = " select name,'1' from inv_shelf where id='"+objectMap.get("hwid")+"' and name is not null and name <> '' ";
					objectMap.put("hwname", purchaseDataService.queryOneDataBySql(querySqlhw) );
					/** 查询仓库名称  **/
					String querySqlck = " select name,'1' from inv_warehouse where id='"+objectMap.get("ckid")+"' and name is not null and name <> '' ";
					objectMap.put("ckname", purchaseDataService.queryOneDataBySql(querySqlck) );
					/** 查询供应商名称  **/
					String querySqlgys = " select name,'1' from srm_supplier where id='"+objectMap.get("supplierid")+"' and name is not null and name <> '' ";
					objectMap.put("gysname", purchaseDataService.queryOneDataBySql(querySqlgys) );
				}
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 采购智能分析 > 入库明细>入库明细表的导出 **/
	public void outExcelToStorageTable() {
		try {
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				String sqlinsupplierID = (StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() );
				String sqlinInvWarehouseID = (StringUtils.isNotEmpty(invWarehouseID) ? "'"+invWarehouseID+"'" : getSqlInAllInvWarehouseID() );
				tableSql.append(" select toone.warehousePerson as jbperson,toone.code,many.supplier_id as supplierid,date_format(toone.createtime, '%Y-%m-%d %h:%i:%s' ) as mcreatetime ");
				tableSql.append(" ,toone.invWarehouse_id as ckid,many.invShelf_id as hwid ");
				tableSql.append(" ,many.itemcode,many.itemname,many.quantity,many.price,many.unit,many.unitcost ");
				tableSql.append(" from INV_STOCKRECORDLINES many inner join INV_STOCKRECORDS toone on many.INVSTOCKRECORDS_ID=toone.id "); 
				tableSql.append(" and toone.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and toone.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" and many.supplier_id in("+sqlinsupplierID+") ");
				tableSql.append(" and toone.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and toone.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" and toone.invWarehouse_id in("+sqlinInvWarehouseID+") ");
				tableSql.append(" order by toone.createtime desc ");
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("outExcelSqlPurchaseStorageDetailsVo", tableSql.toString());
				ArrayList<PurchaseStrorageDetailsVo> purchaseStrorageDetailsVoList = purchaseDataService.outExcelToStorageTable(hsMap);
				exportPurchaseStrorageExcel(purchaseStrorageDetailsVoList);
				vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'采购智能分析>入库明细>入库明细表'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 服务  入库明细表的导出 **/
	public void exportPurchaseStrorageExcel(ArrayList<PurchaseStrorageDetailsVo> purchaseStrorageDetailsVoList) {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");
			String ua = getRequest().getHeader("user-agent");
			String fileName = "入库明细表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("purchaseStrorage_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("purchaseStrorage_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("purchaseStrorageDetailsVoList", purchaseStrorageDetailsVoList);
					xlsArea.applyAt(new CellRef("purchaseStrorageDetailsVo!A1"), context);
					transformer.write();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	/** 采购智能分析>采购成本分析| 和 |采购智能分析>资金比重分析**/
	public String goCostAnalysisView() {
		try {
			Map<String, Object> params = getParams();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				params.put("status," + SearchCondition.EQUAL, "3");
				supplierList = vixntBaseService.findAllDataByConditions(Supplier.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		StringBuffer jsonObj =  new StringBuffer();
		jsonObj.append("{");
		jsonObj.append("\"value\":");
		jsonObj.append("[");
			jsonObj.append("{");
			jsonObj.append("\"id\": \""+"all"+"\",");
			jsonObj.append("\"word\": \""+"全部供应商"+"\"");
			jsonObj.append("}");
		if(supplierList!=null && supplierList.size()>0){ 
			for(int x=0;x<supplierList.size();x++) {
					jsonObj.append(",{");
					jsonObj.append("\"id\": \""+supplierList.get(x).getId()+"\",");
					jsonObj.append("\"word\": \""+supplierList.get(x).getName()+"\"");
					jsonObj.append("}");
			}
		}
		jsonObj.append("]");
		jsonObj.append("}");
		getRequest().setAttribute("jsonObj",jsonObj.toString());
		String[] objMonth = MyTool.getThisMonth_SOnlyAndW();
		getRequest().setAttribute("todayStrFront",objMonth[0]);
		getRequest().setAttribute("todayStrAfter",objMonth[1]);
		String page = getRequestParameter("page");
		if(StringUtils.isNotEmpty(page) && (page.equals("2")) ){//去资金比重分析
			return "goProportionAnalysisView";
		}	
		return "goCostAnalysisView";//去采购成本分析
	}
	/** 通过‘queryTime’参数获得对应的环比上期的时间段   **/
	public ArrayList<String> getTimeArrByQueryTimeMom(String queryTime) {
		ArrayList<String> timeArr =  new ArrayList<String>();
		if(StringUtils.isNotEmpty(queryTime)){
			if(queryTime.equals("ThisMonthOT")){
				timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString("LastMonthOT");
			}else if(queryTime.equals("ThisQuarterOT")){
				timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString("LastQuarterOT");
			}
		}
		return timeArr;
	}
	/** 采购智能分析>采购成本分析>慢加载环比同比仪表盘（供应商采购订单总金额环比 和  供应商采购订单总金额同比）**/
	public String slowLoadSupplierMoneyMomAndAn() {
		try {
			Employee employee = getEmployee();
			Double thisPeriodMoney = 0.00;//本期的钱
			Double lastPeriodMoneyMom = 0.00;//环比上期的钱
			Double lastPeriodMoneyAn = 0.00;//同比上期的钱
			for(int x=0;x<3;x++){//0时计算:本期的钱,1时计算:环比上期的钱,2时计算:同比上期的钱
				if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
					ArrayList<String> timeArr =  new ArrayList<String>();
					if(x==0){
						timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
					}else if(x==1){
						if(queryTime.equals("ThisMonthOT") || queryTime.equals("ThisQuarterOT") ){
							timeArr = this.getTimeArrByQueryTimeMom(queryTime);
						}else {
							timeArr = MyTool.getMomTimeArrByArr((ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime), 1095);
						}
					}else if(x==2){
						timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
						/** 对当前获得的‘本期本月时间集合’进行按顺序减少一年...获得‘上期本月时间集合’ **/
						for(int y=0;y<timeArr.size();y++){
							String dateReduceOneYear = MyTool.dateReduceOneYear(timeArr.get(y));
							timeArr.set(y, dateReduceOneYear);
						}
					}
					String sqlinsupplierID = (StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() );
					String sqla=" from pur_order many inner join srm_supplier toone on many.supplier_id=toone.id ";
					String sqlc=" and toone.name is not null and toone.name <> '' AND many.totalamount is not null and many.totalamount <> '' ";
					String sqld=" and many.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0));
					String sqle=" and many.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1)));
					String sqlf=" and many.supplier_id in("+sqlinsupplierID+") ";
					String sqlg=" and many.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'";
					String sqlh=" and many.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'";
					/** 开始拼装查询总金额的sql语句 **/	
					StringBuffer totalNumSql = new StringBuffer();
					totalNumSql.append(" select sum(many.totalamount) as sqltotalNum,'1' ");
					totalNumSql.append(sqla+sqlc+sqld+sqle+sqlf+sqlg+sqlh); 
					if(x==0){
						thisPeriodMoney = purchaseDataService.queryOneDoubleNumDataBySql(totalNumSql.toString());
					}else if(x==1){
						lastPeriodMoneyMom = purchaseDataService.queryOneDoubleNumDataBySql(totalNumSql.toString());
					}else if(x==2){
						lastPeriodMoneyAn = purchaseDataService.queryOneDoubleNumDataBySql(totalNumSql.toString());
					}
				}
			}
			/* 采用‘圆环形式显示’不用展示具体数值了
			 * getRequest().setAttribute("thisPeriodMoney",MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(thisPeriodMoney)));
			getRequest().setAttribute("lastPeriodMoneyMom",MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(lastPeriodMoneyMom)));
			getRequest().setAttribute("lastPeriodMoneyAn",MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(lastPeriodMoneyAn)));*/
			/** 环比操作  **/
			String moneyNumMom = MyTool.getMomStr(thisPeriodMoney, lastPeriodMoneyMom);
			getRequest().setAttribute("moneyNumMom",moneyNumMom);
			getRequest().setAttribute("moneyNumMomClass",(moneyNumMom.startsWith("-") ? "mygreen":"red" ));//补充完善class="easy-pie-chart txt-color-red easyPieChart" 和 class="easy-pie-chart txt-color-mygreen easyPieChart"
			getRequest().setAttribute("moneyNumMomfa",   (moneyNumMom.startsWith("-") ? "fa fa-arrow-down":"fa fa-arrow-up" ));	
			getRequest().setAttribute("moneyNumMomColor",(moneyNumMom.startsWith("-") ? "#00FF00":"#A90329" ));
			/** 同比操作  **/
			String moneyNumAn = MyTool.getMomStr(thisPeriodMoney, lastPeriodMoneyAn);
			getRequest().setAttribute("moneyNumAn",moneyNumAn);
			getRequest().setAttribute("moneyNumAnClass",(moneyNumAn.startsWith("-") ? "mygreen":"red" ));
			getRequest().setAttribute("moneyNumAnfa",   (moneyNumAn.startsWith("-") ? "fa fa-arrow-down":"fa fa-arrow-up" ));	
			getRequest().setAttribute("moneyNumAnColor",(moneyNumAn.startsWith("-") ? "#00FF00":"#A90329" ));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "slowLoadSupplierMoneyMomAndAn";
	}
	/** 采购智能分析>采购成本分析>供应商采购排行Top10(柱图Top形式)**/
	public void querySupplierOrderMoneyTop() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("controlSQL","SupplierOrderMoneyTop");//SupplierOrderMoneyTop 查询  采购智能分析>采购成本分析>供应商采购排行Top10
			hsMap.put("timeArr",timeArr);
			hsMap.put("topNum",topNum);
			hsMap.put("sqlinsupplierID",(StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() ) );
			String sBufferJsonToString =(String)purchaseDataService.querySupplierOrderMoneyTop(hsMap).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 采购智能分析>资金比重分析>供应商采购排行Top10(饼图Top形式)**/
	public void querySupplierOrderMoneyPieChartTop() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("controlSQL","SupplierOrderMoneyTop");//SupplierOrderMoneyTop 查询  采购智能分析>采购成本分析>供应商采购排行Top10
			hsMap.put("timeArr",timeArr);
			hsMap.put("sqlinsupplierID",(StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() ) );
			hsMap.put("PieChartTop","PieChartTop");//如果有PieChartTop这个键值对，就查询饼图形式TOP，否则查询柱图形式Top
			String sBufferJsonToString =(String)purchaseDataService.querySupplierOrderMoneyPieChartTop(hsMap).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 采购智能分析>采购成本分析>物料采购排行Top10**/
	public void queryMaterielOrderMoneyTop() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("controlSQL","MaterielOrderMoneyTop");//MaterielOrderMoneyTop 查询  采购智能分析>采购成本分析>物料采购排行Top10
			hsMap.put("timeArr",timeArr);
			hsMap.put("topNum",topNum);
			hsMap.put("sqlinsupplierID",(StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() ) );
			String sBufferJsonToString =(String)purchaseDataService.querySupplierOrderMoneyTop(hsMap).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 采购智能分析>资金比重分析>物料采购排行Top10(饼图Top形式带其他占比)**/
	public void queryMaterielOrderMoneyPieChartTop() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("controlSQL","MaterielOrderMoneyTop");//MaterielOrderMoneyTop 查询  采购智能分析>采购成本分析>物料采购排行Top10
			hsMap.put("timeArr",timeArr);
			hsMap.put("PieChartTop","PieChartTop");//如果有PieChartTop这个键值对，就查询饼图形式TOP，否则查询柱图形式Top
			hsMap.put("sqlinsupplierID",(StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() ) );
			String sBufferJsonToString =(String)purchaseDataService.queryMaterielOrderMoneyPieChartTop(hsMap).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 采购智能分析>采购成本分析>物料采购成本分析列表 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void searchMaterielCostTable() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				String sqlinsupplierID = (StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() );
				tableSql.append(" select many.itemcode,many.itemname,many.amount,many.price,many.unit,(many.amount *  many.price) as sqlnum ");
				String sqla=" from pur_orderlineitem many inner join pur_order toone on many.purchaseorder_id=toone.id ";
				String sqlb=" and many.itemname is not null and many.itemname <> '' and many.itemcode is not null and many.itemcode <> '' ";
				String sqlc=" and many.price is not null and many.price <> '' and many.amount is not null and many.amount <> '' ";
				String sqld=" and toone.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0));
				String sqle=" and toone.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1)));
				String sqlf=" and toone.supplier_id in("+sqlinsupplierID+") ";
				String sqlg=" and toone.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'";
				String sqlh=" and toone.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'";
				/** sqla,sqlb,sqlc,sqld,sqle,sqlf...是两句sql语句的共用部分 tableSql 和 totalNumSql**/
				tableSql.append(sqla+sqlb+sqlc+sqld+sqle+sqlf+sqlg+sqlh); 
				tableSql.append(" group by many.itemCode ");
				tableSql.append(" order by (many.amount *  many.price) desc ");
				/** 开始拼装查询总金额的sql语句 **/	
				StringBuffer totalNumSql = new StringBuffer();
				totalNumSql.append(" select sum((many.amount *  many.price)) as sqltotalNum,'1' ");
				totalNumSql.append(sqla+sqlb+sqlc+sqld+sqle+sqlf+sqlg+sqlh); 
				Double totalNum = purchaseDataService.queryOneDoubleNumDataBySql(totalNumSql.toString());
				tablePager.setOrderField(null);
				Pager pager = this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					Double doubleTemp = 0.0;
					Double fzDou = Double.parseDouble((objectMap.get("sqlnum")+""));
					if(totalNum != 0.0){
						doubleTemp =MyTool.roundingDoubleAppointDecimal( ((fzDou/totalNum)+0.00),4);
					}
					/** 已经计算好占比，已经保留小数  **/
					objectMap.put("doubleTemp",(doubleTemp*100));//因为要加百分号，所以乘100
				}
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 采购智能分析 >  采购成本分析 > 物料采购成本分析列表 导出 **/
	public void outExcelToMaterielCostTable() {
		try {
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				String sqlinsupplierID = (StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() );
				tableSql.append(" select many.itemcode,many.itemname,many.amount,many.price,many.unit,(many.amount *  many.price) as sqlnum ");
				String sqla=" from pur_orderlineitem many inner join pur_order toone on many.purchaseorder_id=toone.id ";
				String sqlb=" and many.itemname is not null and many.itemname <> '' and many.itemcode is not null and many.itemcode <> '' ";
				String sqlc=" and many.price is not null and many.price <> '' and many.amount is not null and many.amount <> '' ";
				String sqld=" and toone.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0));
				String sqle=" and toone.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1)));
				String sqlf=" and toone.supplier_id in("+sqlinsupplierID+") ";
				String sqlg=" and toone.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'";
				String sqlh=" and toone.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'";
				/** sqla,sqlb,sqlc,sqld,sqle,sqlf...是两句sql语句的共用部分 tableSql 和 totalNumSql**/
				tableSql.append(sqla+sqlb+sqlc+sqld+sqle+sqlf+sqlg+sqlh); 
				tableSql.append(" group by many.itemCode ");
				tableSql.append(" order by (many.amount *  many.price) desc ");
				/** 开始拼装查询总金额的sql语句 **/	
				StringBuffer totalNumSql = new StringBuffer();
				totalNumSql.append(" select sum((many.amount *  many.price)) as sqltotalNum,'1' ");
				totalNumSql.append(sqla+sqlb+sqlc+sqld+sqle+sqlf+sqlg+sqlh); 
				Double totalNum = purchaseDataService.queryOneDoubleNumDataBySql(totalNumSql.toString());
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("outExcelSqlMaterielCostTable", tableSql.toString());
				hsMap.put("totalNum", totalNum);
				ArrayList<PurchaseMaterielCostVo> purchaseMaterielCostVoList = purchaseDataService.outExcelToMaterielCostTable(hsMap);
				exportPurchaseMaterielCostExcel(purchaseMaterielCostVoList);
				vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'采购智能分析>采购成本分析>物料采购成本分析列表'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 服务  导出'物料采购成本分析列表' **/
	public void exportPurchaseMaterielCostExcel(ArrayList<PurchaseMaterielCostVo> purchaseMaterielCostVoList) {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");
			String ua = getRequest().getHeader("user-agent");
			String fileName = "物料采购成本分析列表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("purchaseMaterielCost_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("purchaseMaterielCost_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("purchaseMaterielCostVoList", purchaseMaterielCostVoList);
					xlsArea.applyAt(new CellRef("purchaseMaterielCostVo!A1"), context);
					transformer.write();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 采购智能分析>采购成本分析>供应商采购成本分析列表 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void searchSupplierCostTable() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				String sqlinsupplierID = (StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() );
				tableSql.append(" select  toone.name as sqlname , sum(many.totalamount) as sqlnum  ");
				String sqla=" from pur_order many inner join srm_supplier toone on many.supplier_id=toone.id ";
				String sqlc=" and toone.name is not null and toone.name <> '' AND many.totalamount is not null and many.totalamount <> '' ";
				String sqld=" and many.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0));
				String sqle=" and many.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1)));
				String sqlf=" and many.supplier_id in("+sqlinsupplierID+") ";
				String sqlg=" and many.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'";
				String sqlh=" and many.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'";
				/** sqla,sqlb,sqlc,sqld,sqle,sqlf是两句sql语句的共用部分 tableSql 和 totalNumSql**/
				tableSql.append(sqla+sqlc+sqld+sqle+sqlf+sqlg+sqlh); 
				tableSql.append(" group by many.supplier_id ");
				tableSql.append(" order by (sum(many.totalamount)) desc ");
				/** 开始拼装查询总金额的sql语句 **/	
				StringBuffer totalNumSql = new StringBuffer();
				totalNumSql.append(" select sum(many.totalamount) as sqltotalNum,'1' ");
				totalNumSql.append(sqla+sqlc+sqld+sqle+sqlf+sqlg+sqlh); 
				Double totalNum = purchaseDataService.queryOneDoubleNumDataBySql(totalNumSql.toString());
				tablePager.setOrderField(null);
				Pager pager = this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					Double doubleTemp = 0.0;
					Double fzDou = Double.parseDouble((objectMap.get("sqlnum")+""));
					if(totalNum != 0.0){
						doubleTemp =MyTool.roundingDoubleAppointDecimal( ((fzDou/totalNum)+0.00),4);
					}
					/** 已经计算好占比，已经保留小数  **/
					objectMap.put("doubleTemp",(doubleTemp*100));//因为要加百分号，所以乘100
				}
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 采购智能分析 >  采购成本分析 >供应商采购成本分析列表 导出 **/
	public void outExcelToSupplierCostTable() {
		try {
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				String sqlinsupplierID = (StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() );
				tableSql.append(" select  toone.name as sqlname , sum(many.totalamount) as sqlnum  ");
				String sqla=" from pur_order many inner join srm_supplier toone on many.supplier_id=toone.id ";
				String sqlc=" and toone.name is not null and toone.name <> '' AND many.totalamount is not null and many.totalamount <> '' ";
				String sqld=" and many.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0));
				String sqle=" and many.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1)));
				String sqlf=" and many.supplier_id in("+sqlinsupplierID+") ";
				String sqlg=" and many.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'";
				String sqlh=" and many.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'";
				/** sqla,sqlb,sqlc,sqld,sqle,sqlf是两句sql语句的共用部分 tableSql 和 totalNumSql**/
				tableSql.append(sqla+sqlc+sqld+sqle+sqlf+sqlg+sqlh); 
				tableSql.append(" group by many.supplier_id ");
				tableSql.append(" order by (sum(many.totalamount)) desc ");
				/** 开始拼装查询总金额的sql语句 **/	
				StringBuffer totalNumSql = new StringBuffer();
				totalNumSql.append(" select sum(many.totalamount) as sqltotalNum,'1' ");
				totalNumSql.append(sqla+sqlc+sqld+sqle+sqlf+sqlg+sqlh); 
				Double totalNum = purchaseDataService.queryOneDoubleNumDataBySql(totalNumSql.toString());
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("outExcelSqlSupplierCostTable", tableSql.toString());
				hsMap.put("totalNum", totalNum);
				ArrayList<PurchaseSupplierCostVo>  purchaseSupplierCostVoList = purchaseDataService.outExcelToSupplierCostTable(hsMap);
				exportPurchaseSupplierCostExcel(purchaseSupplierCostVoList);
				vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'采购智能分析>采购成本分析>供应商采购成本分析列表'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 服务  导出'供应商成本分析列表' **/
	public void exportPurchaseSupplierCostExcel(ArrayList<PurchaseSupplierCostVo> purchaseSupplierCostVoList) {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");
			String ua = getRequest().getHeader("user-agent");
			String fileName = "供应商采购成本分析列表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("purchaseSupplierCost_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("purchaseSupplierCost_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("purchaseSupplierCostVoList", purchaseSupplierCostVoList);
					xlsArea.applyAt(new CellRef("purchaseSupplierCostVo!A1"), context);
					transformer.write();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	/** 采购智能分析>类型结构分析>物料类别采购排行Top10**/
	public void queryItemCatalogMoneyTop() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("controlSQL","ItemCatalogMoneyTop");//ItemCatalogMoneyTop 查询  采购智能分析>类型结构分析>物料类别采购排行Top10(方式1指定一级分类:和方式2混排所有二级分类:)
			hsMap.put("timeArr",timeArr);
			hsMap.put("topNum",topNum);
			hsMap.put("sqlinsupplierID",(StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() ) );
			/**  说明：如果itemCatalogID不为空，就方式1指定一级分类Top;否则如果twolevelCatalogID不为空，就方式2混排所有二级分类;否则errorNoquery错误，不查询了 **/
			if(StringUtils.isNotEmpty(itemCatalogID)){
				hsMap.put("itemCatalogID","'"+itemCatalogID+"'" );
			}else if(StringUtils.isNotEmpty(twolevelCatalogID)){
				hsMap.put("twolevelCatalogID","ShuffleAllTwolevelCatalogID" );
			}else{
				hsMap.put("errorNoquery","errorNoquery" );
			}
			String sBufferJsonToString =(String)purchaseDataService.querySupplierOrderMoneyTop(hsMap).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 采购智能分析>类型结构分析>物料类别采购分析列表|查询  采购智能分析>类型结构分析>物料类别采购排行Top10(方式1指定一级分类:和方式2混排所有二级分类:)**/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void searchMaterielCategoryTable() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				ArrayList<String> objTimeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				String sqlinsupplierID = (StringUtils.isNotEmpty(supplierID) ? "'"+supplierID+"'" : getSqlInAllSupplierID() );
				StringBuffer hqlone = new StringBuffer();
				hqlone.append(" select b.leibeiname,b.jine from ( ");
				hqlone.append(" select toonemulu.name as leibeiname,a.sqlnum as jine from( ");
				StringBuffer hql = new StringBuffer();
				String strSql = " select toonea.itemcatalog_id as sqlitemcatalog_id ,sum(many.amount * many.price) as sqlnum ";
				hql.append(" from pur_orderlineitem many inner join pur_order toone on many.purchaseorder_id = toone.id "); 
				hql.append(" inner join mdm_item toonea on many.itemcode = toonea.code ");
				/**  说明：如果itemCatalogID不为空，就方式1指定一级分类Top;否则如果twolevelCatalogID不为空，就方式2混排所有二级分类;否则errorNoquery错误，不查询了 **/
				if(StringUtils.isNotEmpty(itemCatalogID)){
					/** 开始分类sql处理 方式1指定一级分类  **/
					hql.append(" and many.itemcode in( ");
					hql.append(" 	select code from mdm_item where itemCatalog_ID in( ");
					hql.append(" 		select id from MDM_ITEMCATALOG where parent_id ="+"'"+itemCatalogID+"'"+" ");
					hql.append(" 	 ) ");
					hql.append("  ) ");
					/** 结果分类sql处理 **/
				}else if(StringUtils.isNotEmpty(twolevelCatalogID)){
					hql.append(" ");//接着查询
				}else{
					hql.append(" and 1=3 ");//错误，不查询了；
				}
				hql.append(" and many.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				hql.append(" and many.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				hql.append(" and many.itemname is not null and many.itemname <> '' and many.itemcode is not null and many.itemcode <> '' "); 
				hql.append(" and many.price is not null and many.price <> '' and many.amount is not null and many.amount <> '' "); 
				hql.append(" and toone.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and toone.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
				hql.append(" and toone.supplier_id in("+sqlinsupplierID+") ");
				StringBuffer hqltwo = new StringBuffer();
				hqltwo.append(" group by tooneA.itemCatalog_ID ");
				hqltwo.append(" order by sqlnum desc ");
				hqltwo.append(" )a inner join mdm_itemcatalog toonemulu on a.sqlitemcatalog_id = toonemulu.id  and toonemulu.name is not null and toonemulu.name  <> '' ");
				hqltwo.append(" )b");
				/**  因为要拼接分页sql和占比sql，他们之间存在很多共用部分sql语句片段，所以这么处理 findPagerSql：是分页sql;**/
				String findPagerSql = hqlone.toString()+strSql+hql.toString()+hqltwo.toString();
				/** 开始拼装查询总金额的sql语句,也就是占比sql **/	
				StringBuffer totalNumSql = new StringBuffer();
				totalNumSql.append(" select sum(many.amount * many.price)  as sqltotalNum,'1' ");
				totalNumSql.append(hql.toString()); 
				Double totalNum = purchaseDataService.queryOneDoubleNumDataBySql(totalNumSql.toString());
				tablePager.setOrderField(null);
				Pager pager = this.baseHibernateService.findPagerBySqlFull(tablePager, findPagerSql, new HashMap<String, Object>());
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					Double doubleTemp = 0.0;
					Double fzDou = Double.parseDouble((objectMap.get("jine")+""));
					if(totalNum != 0.0){
						doubleTemp =MyTool.roundingDoubleAppointDecimal( ((fzDou/totalNum)+0.00),4);
					}
					/** 已经计算好占比，已经保留小数  **/
					objectMap.put("doubleTemp",(doubleTemp*100));//因为要加百分号，所以乘100
				}
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<Supplier> getSupplierList() {
		return supplierList;
	}
	public void setSupplierList(List<Supplier> supplierList) {
		this.supplierList = supplierList;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	public String getSupplierID() {
		return supplierID;
	}
	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}
	public List<InvWarehouse> getInvWarehouseList() {
		return invWarehouseList;
	}
	public void setInvWarehouseList(List<InvWarehouse> invWarehouseList) {
		this.invWarehouseList = invWarehouseList;
	}
	public String getInvWarehouseID() {
		return invWarehouseID;
	}
	public void setInvWarehouseID(String invWarehouseID) {
		this.invWarehouseID = invWarehouseID;
	}
	public String getTopNum() {
		return topNum;
	}
	public void setTopNum(String topNum) {
		this.topNum = topNum;
	}
	public List<ItemCatalog> getItemCatalogList() {
		return itemCatalogList;
	}
	public void setItemCatalogList(List<ItemCatalog> itemCatalogList) {
		this.itemCatalogList = itemCatalogList;
	}
	public String getItemCatalogID() {
		return itemCatalogID;
	}
	public void setItemCatalogID(String itemCatalogID) {
		this.itemCatalogID = itemCatalogID;
	}
	public String getTwolevelCatalogID() {
		return twolevelCatalogID;
	}
	public void setTwolevelCatalogID(String twolevelCatalogID) {
		this.twolevelCatalogID = twolevelCatalogID;
	}
}