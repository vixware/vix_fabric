package com.vix.nvix.sales.action;

import java.io.InputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
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
import com.vix.common.properties.util.ComparatorMapBeanDoubleDX;
import com.vix.common.properties.util.MapBeanDouble;
import com.vix.common.properties.util.MyTool;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.Regional;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;
import com.vix.nvix.purchase.action.vo.PurchaseSupplierCostVo;
import com.vix.nvix.sales.action.vo.SalesDeliverGoodsVo;
import com.vix.nvix.sales.action.vo.SalesReturnGoodsDetailedVo;
import com.vix.nvix.sales.action.vo.SalesmanAchieveVo;
import com.vix.oa.personaloffice.service.ISaleDataService;
/**
 * 销售统计分析
 */
@Controller
@Scope("prototype")
public class NvixntSalesAnalysisAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ISaleDataService saleDataService;
	private List<Regional> regionalList;//地域
	private List<CustomerAccount> customerAccountList;//客户
	private String regionalID;//地域id     
	private String customerAccountID;//客户id   
	private List<Employee> employeeList;//业务员
	private String employeeID;//业务员id
	private String queryTime;
	private String topNum;
	private String controlSQL;
	private String categoryId;//树中：选中的 商品分类id
	/** 销售智能分析> 页面中‘竖行检索区’通过操作...地域&客户&业务员   **/
	public void verticalRegionalCustomerEmployee() {
		try {
			Map<String, Object> params = getParams();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				regionalList = vixntBaseService.findAllByConditions(Regional.class, params);
				Map<String, Object> paramsCustomer = new HashMap<String, Object>();
				paramsCustomer.put("isTemp," + SearchCondition.EQUAL, "0");
				paramsCustomer.put("isDeleted," + SearchCondition.EQUAL, "0");
				paramsCustomer.put("tenantId,"+ SearchCondition.EQUAL, params.get("tenantId,"+ SearchCondition.EQUAL)  );
				paramsCustomer.put("companyInnerCode,"+ SearchCondition.EQUAL, params.get("companyInnerCode,"+ SearchCondition.EQUAL)  );
				customerAccountList = vixntBaseService.findAllByConditions(CustomerAccount.class, paramsCustomer);
				Map<String, Object> paramsEmployee = new HashMap<String, Object>();
				paramsEmployee.put("empType," + SearchCondition.EQUAL, "S");
				paramsEmployee.put("tenantId,"+ SearchCondition.EQUAL, params.get("tenantId,"+ SearchCondition.EQUAL)  );
				paramsEmployee.put("companyInnerCode,"+ SearchCondition.EQUAL, params.get("companyInnerCode,"+ SearchCondition.EQUAL)  );
				employeeList = vixntBaseService.findAllByConditions(Employee.class, paramsEmployee);
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
			jsonObj.append("\"word\": \""+"全部地区"+"\"");
			jsonObj.append("}");
		if(regionalList!=null && regionalList.size()>0){ 
			for(int x=0;x<regionalList.size();x++) {
					jsonObj.append(",{");
					jsonObj.append("\"id\": \""+regionalList.get(x).getId()+"\",");
					jsonObj.append("\"word\": \""+regionalList.get(x).getName()+"\"");
					jsonObj.append("}");
			}
		}
		jsonObj.append("]");
		jsonObj.append("}");
		getRequest().setAttribute("jsonObj",jsonObj.toString());
		/////
		StringBuffer jsonObjTwo =  new StringBuffer();
		jsonObjTwo.append("{");
		jsonObjTwo.append("\"value\":");
		jsonObjTwo.append("[");
			jsonObjTwo.append("{");
			jsonObjTwo.append("\"id\": \""+"all"+"\",");
			jsonObjTwo.append("\"word\": \""+"全部客户"+"\"");
			jsonObjTwo.append("}");
		if(customerAccountList!=null && customerAccountList.size()>0){ 
			for(int x=0;x<customerAccountList.size();x++) {
					jsonObjTwo.append(",{");
					jsonObjTwo.append("\"id\": \""+customerAccountList.get(x).getId()+"\",");
					jsonObjTwo.append("\"word\": \""+customerAccountList.get(x).getName()+"\"");
					jsonObjTwo.append("}");
			}
		}
		jsonObjTwo.append("]");
		jsonObjTwo.append("}");
		getRequest().setAttribute("jsonObjTwo",jsonObjTwo.toString());
		/////
		StringBuffer jsonObjThree =  new StringBuffer();
		jsonObjThree.append("{");
		jsonObjThree.append("\"value\":");
		jsonObjThree.append("[");
			jsonObjThree.append("{");
			jsonObjThree.append("\"id\": \""+"all"+"\",");
			jsonObjThree.append("\"word\": \""+"全部业务员"+"\"");
			jsonObjThree.append("}");
		if(employeeList!=null && employeeList.size()>0){    
			for(int x=0;x<employeeList.size();x++) {
					jsonObjThree.append(",{");
					jsonObjThree.append("\"id\": \""+employeeList.get(x).getId()+"\",");
					jsonObjThree.append("\"word\": \""+employeeList.get(x).getName()+"\"");
					jsonObjThree.append("}");
			}
		}
		jsonObjThree.append("]");
		jsonObjThree.append("}");
		getRequest().setAttribute("jsonObjThree",jsonObjThree.toString());
		getRequest().setAttribute("todayStr",MyTool.getTodayBy_yyyyMMdd());
	}
	//////////////////////////////////////////////////
	/** 销售智能分析>销售增长分析**/
	public String goSaleGrowView() {
		verticalRegionalCustomerEmployee();
		return "goSaleGrowView";
	}
	/** 销售智能分析>销售增长分析>销售订单数趋势图   & 查询‘销售金额趋势图’  **/
	@SuppressWarnings("unchecked")
	public void querySalesOrderTrend() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("regionalID",regionalID);
			hsMap.put("customerAccountID",customerAccountID);
			hsMap.put("employeeID",employeeID);
			hsMap.put("timeArr",timeArr);
			hsMap.put("controlSQL", controlSQL);//querSalesOrderTrendNum  查询‘销售订单数趋势图’   和      querSalesOrderTrendMoney   查询‘销售金额趋势图’ 
			ArrayList<String> valueArr = (ArrayList<String>) saleDataService.querySalesOrderTrend(hsMap).get("valueArr");
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
	/** 销售智能分析>产品销量排行**/
	public String goSaleGoodsTopView() {
		verticalRegionalCustomerEmployee();
		return "goSaleGoodsTopView";
	}
	/** 销售智能分析>产品销量排行>产品销售金额排行Top10  &  产品销售数量排行Top10 **/
	public void queryProductSalesTopView() {  
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("regionalID",regionalID);
			hsMap.put("customerAccountID",customerAccountID);
			hsMap.put("employeeID",employeeID);
			hsMap.put("timeArr",timeArr);
			hsMap.put("topNum",topNum);
			hsMap.put("controlSQL", controlSQL);//ProductSaleTopmoney 产品销售金额排行Top10  &  ProductSaleTopnum  产品销售数量排行Top10
			String sBufferJsonToString =(String)saleDataService.queryProductSalesTopView(hsMap).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 销售智能分析>客户购买排行**/
	public String goCustomerBuyGoodsTopView() {
		verticalRegionalCustomerEmployee();
		return "goCustomerBuyGoodsTopView";
	}
	/** 销售智能分析>客户购买排行>客户购买排行金额Top10  &  客户购买排行数量Top10 **/
	public void queryCustomerBuyTopView() {  
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("regionalID",regionalID);
			hsMap.put("employeeID",employeeID);
			hsMap.put("timeArr",timeArr);
			hsMap.put("topNum",topNum);
			hsMap.put("controlSQL", controlSQL);//CustomerBuyTopmoney 客户购买排行金额Top10  &  CustomerBuyTopnum  客户购买排行数量Top10也就是客户购买产品总数量
			String sBufferJsonToString =(String)saleDataService.queryCustomerBuyTopView(hsMap).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 销售智能分析>销售人员业绩排行**/
	public String goSalesmanSellTopView() {
		verticalRegionalCustomerEmployee();
		return "goSalesmanSellTopView";
	}
	/** 销售智能分析>销售人员业绩排行>销售人员业绩订单金额Top10  &  销售人员业绩订单数量Top10  & 销售订单数量Top10 **/
	public void querySalesmanSellTopView() {  
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("regionalID",regionalID);
			hsMap.put("customerAccountID",customerAccountID);
			hsMap.put("timeArr",timeArr);
			hsMap.put("topNum",topNum);
			hsMap.put("controlSQL", controlSQL);//SalesmanSellTopmoney 销售人员业绩:销售产品总金额排行  & SalesmanSellTopnum 销售人员业绩:销售产品总数量排行  & SalesmanSellTopOrdersnum 销售订单数量Top10
			String sBufferJsonToString =(String)saleDataService.querySalesmanSellTopView(hsMap).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 销售智能分析>销售人员业绩排行>销售人员业绩:销售订单金额Top10 相关销售人员信息  & 产品数量Top10 & 订单数量Top10 3个列表 **/
	public void queryStaffTable() {
		try {
			String urlEncodeStr = "";
			String groupArrOrderMoney = getDecodeRequestParameter("groupArrOrderMoney");
			String groupArrProductNum = getDecodeRequestParameter("groupArrProductNum");   
			String groupArrOrderNum = getDecodeRequestParameter("groupArrOrderNum");
			if(StringUtils.isNotEmpty(groupArrOrderMoney)){
				urlEncodeStr = groupArrOrderMoney; 
			}else if(StringUtils.isNotEmpty(groupArrProductNum)){
				urlEncodeStr = groupArrProductNum; 
			}else if(StringUtils.isNotEmpty(groupArrOrderNum)){
				urlEncodeStr = groupArrOrderNum; 
			}
			
			StringBuffer sqlBuffer = new StringBuffer();
			int intState = 0;
			if(StringUtils.isNotEmpty(urlEncodeStr) && urlEncodeStr.startsWith("[") && urlEncodeStr.endsWith("]") ){
				JSONArray arr = new JSONArray(urlEncodeStr);
				if(arr !=null && arr.length()>0){
				  for(int x=(arr.length()-1);x>=0;x--) {
					intState++;
				    String id = arr.get(x)+"";
					if(x==(arr.length()-1)){
						sqlBuffer.append(" select '"+id+"' as id,"+x+" as numsign ");
					}else{
						sqlBuffer.append(" union all  select '"+id+"' as id,"+x+" as numsign ");
					}
				  }
				}
			}
			/** intState==0时，必须拼装一个可执行的空子查询sql **/
			if(intState == 0){
				sqlBuffer = new StringBuffer(" select null as id,null as numsign ");
			}
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select m.name,m.staffJobNumber,m.gender,m.telephone ");
				tableSql.append(" from hr_org_employee m inner join ("+sqlBuffer.toString()+")t on m.id = t.id order by t.numsign desc ");
				this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 导出  销售智能分析>销售人员业绩排行>销售人员业绩:销售订单金额Top10 相关销售人员信息  & 产品数量Top10 & 订单数量Top10 3个列表  **/
	public void outExcelToStaffTable() {
		try {
			String urlEncodeStr = getDecodeRequestParameter("queryWhat");
			StringBuffer sqlBuffer = new StringBuffer();
			int intState = 0;
			if(StringUtils.isNotEmpty(urlEncodeStr) && urlEncodeStr.startsWith("[") && urlEncodeStr.endsWith("]") ){
				JSONArray arr = new JSONArray(urlEncodeStr);
				if(arr !=null && arr.length()>0){
				  for(int x=(arr.length()-1);x>=0;x--) {
					intState++;
				    String id = arr.get(x)+"";
					if(x==(arr.length()-1)){
						sqlBuffer.append(" select '"+id+"' as id,"+x+" as numsign ");
					}else{
						sqlBuffer.append(" union all  select '"+id+"' as id,"+x+" as numsign ");
					}
				  }
				}
			}
			/** intState==0时，必须拼装一个可执行的空子查询sql **/
			if(intState == 0){
				sqlBuffer = new StringBuffer(" select null as id,null as numsign ");
			}
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select m.name,m.staffJobNumber,m.gender,m.telephone ");
				tableSql.append(" from hr_org_employee m inner join ("+sqlBuffer.toString()+")t on m.id = t.id order by t.numsign desc ");
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("outExcelSqlStaffTableTop", tableSql.toString());
				ArrayList<SalesmanAchieveVo>  salesmanAchieveVoList = saleDataService.outExcelToStaffTable(hsMap);
				exportStaffTableExcel(salesmanAchieveVoList);
				vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'销售智能分析>销售人员业绩排行>销售人员业绩排行列表'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 服务 导出  销售智能分析>销售人员业绩排行>销售人员业绩:销售订单金额Top10 相关销售人员信息  & 产品数量Top10 & 订单数量Top10 3个列表  **/
	public void exportStaffTableExcel(ArrayList<SalesmanAchieveVo> salesmanAchieveVoList) {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");
			String ua = getRequest().getHeader("user-agent");
			String fileName = "销售人员业绩排行列表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("salesmanAchieveTable_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("salesmanAchieveTable_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("salesmanAchieveVoList", salesmanAchieveVoList);
					xlsArea.applyAt(new CellRef("salesmanAchieveVo!A1"), context);
					transformer.write();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/////////
	/** 销售智能分析>销售结构分析   树形排名处理页面   **/
	public String goSaleStructureAnalysisView() {
		verticalRegionalCustomerEmployee();
		return "goSaleStructureAnalysisView";
	}
	/** 销售智能分析>销售结构分析>产品类别销售排行柱图和饼图一起查询  TOP10  树形结构排名  **/
	public void queryStructureSalesTopView() {  
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("regionalID",regionalID);
			hsMap.put("customerAccountID",customerAccountID);
			hsMap.put("employeeID",employeeID);
			hsMap.put("timeArr",timeArr);
			hsMap.put("controlSQL", controlSQL);//SalesStructureBarAndPieTop 产品类别销售排行柱图和饼图一起查询 
			String jsonStr = queryTopByTreeToJson(hsMap,categoryId);  
			renderJson(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 销售智能分析>销售结构分析>产品类别销售排行柱图和饼图一起查询  TOP10  树形结构排名   table的查询  **/
	public void queryStructureSalesTopTable() {
		try {
			String categoryArrMoney = getDecodeRequestParameter("categoryArrMoney");
			String categoryArrName = getDecodeRequestParameter("categoryArrName");   
			String categoryTotalDouble = getDecodeRequestParameter("categoryTotalDouble");
			StringBuffer sqlBuffer = new StringBuffer();
			int intState = 0;
			if( (StringUtils.isNotEmpty(categoryArrMoney) && categoryArrMoney.startsWith("[") && categoryArrMoney.endsWith("]")) 
			 && (StringUtils.isNotEmpty(categoryArrName) && categoryArrName.startsWith("[") && categoryArrName.endsWith("]")) 
			  ){
				JSONArray arrMoney = new JSONArray(categoryArrMoney);
				JSONArray arrName = new JSONArray(categoryArrName);
				if(arrMoney !=null && arrMoney.length()>0){
				  for(int x=0;x<arrMoney.length();x++) {  
					intState++;
				    String money = arrMoney.get(x)+"";
				    String name = arrName.get(x)+"";
				    Double upper = Double.valueOf((StringUtils.isNotEmpty(money) ? money : "0.0"  ));
				    Double down = Double.valueOf(  ( (StringUtils.isNotEmpty(categoryTotalDouble) && Double.valueOf(categoryTotalDouble)>0.0 )  ? categoryTotalDouble : "0.000000000000001"  )    );
				    Double pro = MyTool.roundingDoubleAppointDecimal((upper/down),4);//pro 占比
					if(x==0){
						sqlBuffer.append(" select '"+name+"' as name,"+x+" as numsign,'"+money+"' as money,'"+(pro*100)+"' as pro");
					}else{
						sqlBuffer.append("  union all  select '"+name+"' as name,"+x+" as numsign,'"+money+"' as money,'"+(pro*100)+"' as pro");
					}
				  }
				}
			}
			/** intState==0时，必须拼装一个可执行的空子查询sql **/
			if(intState == 0){
				sqlBuffer = new StringBuffer(" select null as name,null as numsign,null as money,null as pro ");
			}
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select t.name,t.money,t.pro from  ("+sqlBuffer.toString()+")t order by t.numsign asc ");
				this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
				if(intState == 0){
					tablePager = new Pager();
				}
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 导出:销售智能分析 > 销售结构分析 > 产品类别销售分析列表 **/
	public void outExcelToStructureSalesTopTable() {
		try {
			String categoryArrMoney = getDecodeRequestParameter("categoryArrMoney");
			String categoryArrName = getDecodeRequestParameter("categoryArrName");   
			String categoryTotalDouble = getDecodeRequestParameter("categoryTotalDouble");
			StringBuffer sqlBuffer = new StringBuffer();
			int intState = 0;
			if( (StringUtils.isNotEmpty(categoryArrMoney) && categoryArrMoney.startsWith("[") && categoryArrMoney.endsWith("]")) 
			 && (StringUtils.isNotEmpty(categoryArrName) && categoryArrName.startsWith("[") && categoryArrName.endsWith("]")) 
			  ){
				JSONArray arrMoney = new JSONArray(categoryArrMoney);
				JSONArray arrName = new JSONArray(categoryArrName);
				if(arrMoney !=null && arrMoney.length()>0){
				  for(int x=0;x<arrMoney.length();x++) {  
					intState++;
				    String money = arrMoney.get(x)+"";
				    String name = arrName.get(x)+"";
				    Double upper = Double.valueOf((StringUtils.isNotEmpty(money) ? money : "0.0"  ));
				    Double down = Double.valueOf(  ( (StringUtils.isNotEmpty(categoryTotalDouble) && Double.valueOf(categoryTotalDouble)>0.0 )  ? categoryTotalDouble : "0.000000000000001"  )    );
				    Double pro = MyTool.roundingDoubleAppointDecimal((upper/down),4);//pro 占比
					if(x==0){
						sqlBuffer.append(" select '"+name+"' as name,"+x+" as numsign,'"+money+"' as money,'"+(pro*100)+"' as pro");
					}else{
						sqlBuffer.append("  union all  select '"+name+"' as name,"+x+" as numsign,'"+money+"' as money,'"+(pro*100)+"' as pro");
					}
				  }
				}
			}
			/** intState==0时，必须拼装一个可执行的空子查询sql **/
			if(intState == 0){
				sqlBuffer = new StringBuffer(" select null as name,null as numsign,null as money,null as pro ");
			}
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select t.name,t.money,t.pro from  ("+sqlBuffer.toString()+")t order by t.numsign asc ");
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("outExcelSqlStructureSalesTop", tableSql.toString());
				ArrayList<PurchaseSupplierCostVo>  purchaseSupplierCostVoList = saleDataService.outExcelToStructureSalesTopTable(hsMap);
				exportStructureSaleTopExcel(purchaseSupplierCostVoList);
				vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'销售智能分析>销售结构分析>产品类别销售分析列表'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 服务  导出'产品类别销售分析列表' **/
	public void exportStructureSaleTopExcel(ArrayList<PurchaseSupplierCostVo> purchaseSupplierCostVoList) {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");
			String ua = getRequest().getHeader("user-agent");
			String fileName = "产品类别销售分析列表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("saleStructureTop_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("saleStructureTop_template.xml")) {
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
	///////////
	/** 销售智能分析>退货订单统计表**/
	public String goSaleReturnGoodsView() {
		getRequest().setAttribute("todayStr",MyTool.getTodayBy_yyyyMMdd());
		return "goSaleReturnGoodsView";
	}
	/** 销售智能分析>退货订单走势图查询   **/
	@SuppressWarnings("unchecked")
	public void querySaleReturnView() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("timeArr",timeArr);
			ArrayList<String> valueArr = (ArrayList<String>) saleDataService.querySaleReturnView(hsMap).get("valueArr");
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
	/** 销售智能分析>退货订单明细表查询   **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void queryReturnTable() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				tableSql.append(" select many.itemCode,many.itemName,many.count,many.unit,many.price,many.netTotal,toone.customerAccount_id as khid, ");  
				tableSql.append(" DATE_FORMAT(toone.returnAppTime, '%Y-%m-%d %H:%i:%s' ) as sqtime,toone.returnOrderCode, toone.returnAppTime,DATE_FORMAT(toone.returnTime, '%Y-%m-%d %H:%i:%s' ) as thtime,toone.approvalStatus ");
				tableSql.append(" from sale_salereturnformitem many inner join sale_salereturnform toone on many.salereturnform_id=toone.id "); 
				tableSql.append(" and toone.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and toone.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" and toone.returnAppTime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and toone.returnAppTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" order by toone.returnAppTime desc ");
				Pager pager = this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					/** 查询客户名称  **/
					String querySqlkh = " select name,'1' from crm_customeraccount where id='"+objectMap.get("khid")+"' and name is not null and name <> '' ";
					objectMap.put("khname", saleDataService.queryOneDataBySql(querySqlkh) );
				}
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**导出 销售智能分析>退货明细表 **/
	public void outExcelToSaleReturnTable() {
		try {
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				tableSql.append(" select many.itemCode,many.itemName,many.count,many.unit,many.price,many.netTotal,toone.customerAccount_id as khid, ");  
				tableSql.append(" DATE_FORMAT(toone.returnAppTime, '%Y-%m-%d %H:%i:%s' ) as sqtime,toone.returnOrderCode, toone.returnAppTime,DATE_FORMAT(toone.returnTime, '%Y-%m-%d %H:%i:%s' ) as thtime,toone.approvalStatus ");
				tableSql.append(" from sale_salereturnformitem many inner join sale_salereturnform toone on many.salereturnform_id=toone.id "); 
				tableSql.append(" and toone.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and toone.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" and toone.returnAppTime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and toone.returnAppTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" order by toone.returnAppTime desc ");
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("excelSqlSaleReturnTable", tableSql.toString());
				ArrayList<SalesReturnGoodsDetailedVo> salesReturnGoodsDetailedVoList = saleDataService.outExcelToSaleReturnTable(hsMap); 
				exportSaleReturnTableExcel(salesReturnGoodsDetailedVoList);
				vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'销售智能分析>退货订单统计表>退货明细表'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 服务  导出 销售智能分析>退货明细  **/
	public void exportSaleReturnTableExcel(ArrayList<SalesReturnGoodsDetailedVo> salesReturnGoodsDetailedVoList) {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");
			String ua = getRequest().getHeader("user-agent");
			String fileName = "退货明细表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("saleReturnTable_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("saleReturnTable_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("salesReturnGoodsDetailedVoList", salesReturnGoodsDetailedVoList);
					xlsArea.applyAt(new CellRef("salesReturnGoodsDetailedVo!A1"), context);
					transformer.write();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	////////
	/** 销售智能分析>发货明细表**/
	public String goDeliverDetailedView() {
		getRequest().setAttribute("todayStr",MyTool.getTodayBy_yyyyMMdd());
		return "goDeliverDetailedView";
	}
	/** 销售智能分析>发货明细表 查询**/
	public void queryDeliverTable() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				tableSql.append(" select many.goodsName,many.amount,many.price,many.goodsCode, ");  
				tableSql.append(" DATE_FORMAT(toone.invoiceTime, '%Y-%m-%d %H:%i:%s' ) as fhtime,"
						+ " toone.orderCode,toone.receiverName,toone.receiverCity,toone.receiverMobile,toone.receiverState ");   
				tableSql.append(" from e_invoicelistdetail many inner join e_invoicelist toone on many.invoiceList_id=toone.id "); 
				tableSql.append(" and toone.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and toone.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" and toone.invoiceTime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and toone.invoiceTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" order by toone.invoiceTime desc ");
				this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**导出 销售智能分析>发货明细表 **/
	public void outExcelToSaleDeliverTable() {
		try {
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				tableSql.append(" select many.goodsName,many.amount,many.price,many.goodsCode, ");  
				tableSql.append(" DATE_FORMAT(toone.invoiceTime, '%Y-%m-%d %H:%i:%s' ) as fhtime,"
						+ " toone.orderCode,toone.receiverName,toone.receiverCity,toone.receiverMobile,toone.receiverState ");   
				tableSql.append(" from e_invoicelistdetail many inner join e_invoicelist toone on many.invoiceList_id=toone.id "); 
				tableSql.append(" and toone.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and toone.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" and toone.invoiceTime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and toone.invoiceTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" order by toone.invoiceTime desc ");
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("excelSqlSaleDeliverTable", tableSql.toString());
				ArrayList<SalesDeliverGoodsVo>  salesDeliverGoodsVoList = saleDataService.outExcelToSaleDeliverTable(hsMap); 
				exportSaleDeliverTableExcel(salesDeliverGoodsVoList);
				vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'销售智能分析>发货明细表>发货明细表'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 服务  导出 销售智能分析>发货明细表  **/
	public void exportSaleDeliverTableExcel(ArrayList<SalesDeliverGoodsVo> salesDeliverGoodsVoList) {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");
			String ua = getRequest().getHeader("user-agent");
			String fileName = "发货明细表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("salesSaleDeliverTable_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("salesSaleDeliverTable_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("salesDeliverGoodsVoList", salesDeliverGoodsVoList);
					xlsArea.applyAt(new CellRef("salesDeliverGoodsVo!A1"), context);
					transformer.write();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//////////
	/** 销售智能分析>货物流向分析**/
	public String goSaleSendMapView() {
		getRequest().setAttribute("todayStr",MyTool.getTodayBy_yyyyMMdd());
		return "goSaleSendMapView";
	}
	/** 销售智能分析>货物流向分析>  销售订单数发货地图排行TOP10 & 销售订单金额发货地图排行TOP10  **/
	public void querySendMapTopView() {  
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("timeArr",timeArr);
			hsMap.put("topNum",topNum);
			hsMap.put("controlSQL", controlSQL);//OrderMapNumBar 销售订单数发货地图排行TOP10 &  OrderMapMoneyBar 销售订单金额发货地图排行TOP10
			String sBufferJsonToString =(String)saleDataService.querySendMapTopView(hsMap).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 销售智能分析>货物流向分析> 发货订单数在echarts地图中显示  **/
	public void sendOrderNumInEChartsMap() {  
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("timeArr",timeArr);
			hsMap.put("controlSQL", controlSQL);//sendOrderNumInEChartsMap 销售智能分析>货物流向分析> 发货订单数在echarts地图中显示
			String sBufferJsonToString =(String)saleDataService.querySendMapTopView(hsMap).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
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
	public String getTopNum() {
		return topNum;
	}
	public void setTopNum(String topNum) {
		this.topNum = topNum;
	}
	public String getControlSQL() {
		return controlSQL;
	}
	public void setControlSQL(String controlSQL) {
		this.controlSQL = controlSQL;
	}
	public String getRegionalID() {
		return regionalID;
	}
	public void setRegionalID(String regionalID) {
		this.regionalID = regionalID;
	}
	public String getCustomerAccountID() {
		return customerAccountID;
	}
	public void setCustomerAccountID(String customerAccountID) {
		this.customerAccountID = customerAccountID;
	}
	public List<CustomerAccount> getCustomerAccountList() {
		return customerAccountList;
	}
	public void setCustomerAccountList(List<CustomerAccount> customerAccountList) {
		this.customerAccountList = customerAccountList;
	}
	public List<Regional> getRegionalList() {
		return regionalList;
	}
	public void setRegionalList(List<Regional> regionalList) {
		this.regionalList = regionalList;
	}
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	////////   下面是处理 树形排行的方法    ////////////////////////////////////////////////////////////////////////////////
	/** 通过循环结合递归查询出不同级别的商品分类下的‘产品类别销售排行’数据，用于'销售智能分析 > 销售结构分析>...排行'显示  */
	public String queryTopByTreeToJson(Map<String, Object> hsMap,String categoryId) {
		String returnStr ="";
		try {
			List<ItemCatalog> listItemCatalog = new ArrayList<ItemCatalog>();
			/** 获取查询参数 */
			MapBeanDouble mapDou = new MapBeanDouble();
			List<MapBeanDouble> listMapDou = new ArrayList<MapBeanDouble>();
			Map<String, Object> params = getParams();
			if (null != categoryId && !"".equals(categoryId)) {
				listItemCatalog = vixntBaseService.findAllSubEntity(ItemCatalog.class, "parentItemCatalog.id", categoryId, params);
				/** 如果 listItemCatalog == null || listItemCatalog.size()==0 说明：到分类的底层级别了，当前无子分类就，那也应该查询下当前类别数据  **/
				if(listItemCatalog == null || listItemCatalog.size()==0) {  
					ItemCatalog logByID = vixntBaseService.findEntityById(ItemCatalog.class,categoryId);
					if(logByID !=null ){
						listItemCatalog.add(logByID);
					}
				}
				if(listItemCatalog!=null && listItemCatalog.size()>0) {
					for(int x=0;x<listItemCatalog.size();x++){
						mapDou = new MapBeanDouble( (listItemCatalog.get(x).getName()) , (queryDoubleMoney(0.0, new ArrayList<ItemCatalog>(listItemCatalog.get(x).getSubItemCatalogs()),hsMap,listItemCatalog.get(x).getId())) );						
						listMapDou.add(mapDou);
						mapDou = new MapBeanDouble();
					}
				}
			}else {
				listItemCatalog = vixntBaseService.findAllSubEntity(ItemCatalog.class, "parentItemCatalog.id", null, params);
				if(listItemCatalog!=null && listItemCatalog.size()>0) {
					for(int x=0;x<listItemCatalog.size();x++){
						mapDou = new MapBeanDouble( (listItemCatalog.get(x).getName()) , (queryDoubleMoney(0.0, new ArrayList<ItemCatalog>(listItemCatalog.get(x).getSubItemCatalogs()),hsMap,listItemCatalog.get(x).getId())) );						
						listMapDou.add(mapDou);
						mapDou = new MapBeanDouble();						
					}
				}
			}
			if(listMapDou!=null && listMapDou.size()>0){
				/** 先去0：把数据中的0.0去掉 **/
				List<MapBeanDouble> listMapDouNew = new ArrayList<MapBeanDouble>();
				for(MapBeanDouble mBean:listMapDou){
					if(mBean !=null && (mBean.getValue()>0.0) ){
						listMapDouNew.add(mBean);
					}
				}
				listMapDou = listMapDouNew;
				/** 后排序：从大到小，为取数据 **/
				ComparatorMapBeanDoubleDX mySort = new ComparatorMapBeanDoubleDX() ;
				Collections.sort(listMapDou, mySort) ;
			}
			StringBuffer barJsonTopTen = sortAfterTakeOutBarJsonTopTen(listMapDou);
			StringBuffer pieJsonTopTen = sortAfterTakeOutPieJsonTopTen(listMapDou,"10");
			StringBuffer tableJsonForQuery = sortAfterTakeOutTableJsonForQuery(listMapDou);  
			returnStr = MyTool.mergeJsonStringTwo((barJsonTopTen.toString()),(pieJsonTopTen.toString()),(tableJsonForQuery.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnStr;
	}
	/** 排序之后传递查询到的数据封装成几个数组，用于查询列表,为拼装 sql语句使用  **/
	public StringBuffer sortAfterTakeOutTableJsonForQuery(List<MapBeanDouble> dataList) throws ParseException {
		ArrayList<String> nameArrTable = new ArrayList<String>();
		ArrayList<String> valueArrTable = new ArrayList<String>();
		Double totalDouble = 0.0;//totalNum 记录‘和值’
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dataList.size();x++){
				nameArrTable.add(dataList.get(x).getKey());
				/** 因为是钱或者数量有可能是小数 ， 所有要四舍五入保留2位小数**/
				Double dTemp = dataList.get(x).getValue();
				valueArrTable.add(MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(dTemp)));
				totalDouble += dTemp;
			}
		}
		/*for(int x=0;x<nameArrTable.size();x++){ //数值倒序
			伞翼无人机             ..........           45796000.00
			旋翼无人机             ..........           49231.00
			扑翼无人机             ..........           2697.00
		}*/
		Gson gosn = new Gson();
		StringBuffer returnBufferJson = new StringBuffer();
		returnBufferJson.append("{"+"\"nameArrTable\":" + gosn.toJson(nameArrTable));
		returnBufferJson.append(",\"totalDouble\":" + "\""+MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(totalDouble))+"\"" );   
		returnBufferJson.append(",\"valueArrTable\":" + valueArrTable.toString() + "}" );
		return returnBufferJson;
	}
	/** 排序之后取出用于柱图展示的top10**/
	public StringBuffer sortAfterTakeOutBarJsonTopTen(List<MapBeanDouble> dataList) throws ParseException {
		ArrayList<String> nameArrBarTen = new ArrayList<String>();
		ArrayList<String> valueArrBarTen = new ArrayList<String>();
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dataList.size();x++){
				if(x==10){//top10，就跳出
					break;
				}
				nameArrBarTen.add(dataList.get(x).getKey());
				/** 因为是钱或者数量有可能是小数 ， 所有要四舍五入保留2位小数**/
				valueArrBarTen.add(MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(dataList.get(x).getValue())));
			}
		}
		 for (int start = 0, end = nameArrBarTen.size() - 1; start < end; start++, end--) {
			 String temp = nameArrBarTen.get(end);
			 nameArrBarTen.set(end, nameArrBarTen.get(start));
			 nameArrBarTen.set(start, temp);
		 }
		 for (int start = 0, end = valueArrBarTen.size() - 1; start < end; start++, end--) {
			 String temp = valueArrBarTen.get(end);
			 valueArrBarTen.set(end, valueArrBarTen.get(start));
			 valueArrBarTen.set(start, temp);
		 }
		Gson gosn = new Gson();
		StringBuffer returnBufferJson = new StringBuffer();
		returnBufferJson.append("{"+"\"nameArrBarTen\":" + gosn.toJson(nameArrBarTen));
		returnBufferJson.append(",\"valueArrBarTen\":" + valueArrBarTen.toString() + "}" );
		return returnBufferJson;
	}
	/** 排序之后取出用于饼图展示的top10   注意：超过top10，top11,top12....合并为top10处理**/
	public StringBuffer sortAfterTakeOutPieJsonTopTen(List<MapBeanDouble> dataList,String otherTopNum) throws ParseException {
		ArrayList<String> nameArrPieTen = new ArrayList<String>();
		ArrayList<String> valueArrPieTen = new ArrayList<String>();
		Integer otherTopNumInt = Integer.parseInt(otherTopNum);
		Integer otherTopNumIntSmall = otherTopNumInt-1;
		if(dataList !=null && dataList.size()>0){
			if( dataList.size() <=otherTopNumIntSmall ){
				for(int x=0;x<dataList.size();x++){
					String money = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(dataList.get(x).getValue()));//钱
					String name = dataList.get(x).getKey();//类目名
					nameArrPieTen.add(name);
					valueArrPieTen.add(money);
				}
			}else{
				Double temp = 0.0;
				 for(int x=0;x<otherTopNumIntSmall;x++){
					String money = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(dataList.get(x).getValue()));//钱
					String name = dataList.get(x).getKey();//类目名
					nameArrPieTen.add(name);
					valueArrPieTen.add(money);
				 }
				 if(dataList.size() == otherTopNumInt ){
					String money = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(dataList.get(otherTopNumIntSmall).getValue()));//钱
					String name = dataList.get(otherTopNumIntSmall).getKey();//类目名
					nameArrPieTen.add(name);
					valueArrPieTen.add(money);
				 }else{
					 for(int x=otherTopNumIntSmall;x<dataList.size();x++){
						 Double money = dataList.get(x).getValue();//钱
						 temp += money;
					 }
					 valueArrPieTen.add(MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(temp))+"");//钱
					 nameArrPieTen.add("其他");
				 }
			}
	}
		Gson gosn = new Gson();
		StringBuffer returnBufferJson = new StringBuffer();
		returnBufferJson.append("{"+"\"nameArrPieTen\":" + gosn.toJson(nameArrPieTen));
		returnBufferJson.append(",\"valueArrPieTen\":" + valueArrPieTen.toString() + "}" );
		return returnBufferJson;
	}
	/** 递归方式查询‘产品类别销售金额’用于'销售智能分析 > 销售结构分析>...排行'显示 */
	private Double queryDoubleMoney(Double doubleTotal, List<ItemCatalog> listItemCatalog,Map<String, Object> hsMap,String logID) throws Exception {
		if(listItemCatalog != null && listItemCatalog.size()>0){
			for (int i = 0; i < listItemCatalog.size(); i++) {
				ItemCatalog ic = listItemCatalog.get(i);
				if (ic.getSubItemCatalogs().size() > 0) {
					hsMap.put("itemcatalogID", ic.getId());
					Double num = saleDataService.queryStructureSalesTopView(hsMap);
					doubleTotal = doubleTotal+num;
					queryDoubleMoney(doubleTotal, new ArrayList<ItemCatalog>(ic.getSubItemCatalogs()) ,hsMap,ic.getId());
				} else {
					hsMap.put("itemcatalogID", ic.getId());
					Double num = saleDataService.queryStructureSalesTopView(hsMap);
					doubleTotal = doubleTotal+num;
				}
			}
		}else{
			hsMap.put("itemcatalogID", logID);
			Double num = saleDataService.queryStructureSalesTopView(hsMap);
			doubleTotal = doubleTotal+num;
		}
		return doubleTotal;
	}
	////////上面是处理 树形排行的方法    ////////////////////////////////////////////////////////////////////////////////
}
