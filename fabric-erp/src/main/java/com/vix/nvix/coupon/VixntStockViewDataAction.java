package com.vix.nvix.coupon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.properties.util.MyTool;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.personaloffice.service.IQueryDataService;

@Controller
@Scope("prototype")
public class VixntStockViewDataAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IQueryDataService queryDataService;
	private String startTime;
	private String endTime;
	private String topNum;
	private String queryTime;

	/**
	 * 门店管理>门店数据统计>门店库存报表
	 */
	public String goStockView() {
		try {
			String titleDate = "今日";
			int inState = 0;
			if (StringUtils.isNotEmpty(queryTime)) {
				titleDate = "当日";
			} else {
				queryTime = MyTool.getTodayBy_yyyyMMdd();
				inState = 6;
			}
			warehouseGoodsIN(queryTime);
			warehouseGoodsOUT(queryTime);
			nowStockGoodsNum();
			invalidGoodsNum();
			notEnoughGoodsNum();
			tooMuchGoodsNum();
			getRequest().setAttribute("titleDate", titleDate);
			if (inState == 6) {
				queryTime = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goStockView";
	}
	/** 门店库存报表 > 商品入库列表 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void goGoodsQueryList() {
		try {
			Map<String, Object> params = getParams();
			Pager myPager = this.getPager();
			String myflag = getDecodeRequestParameter("myflag");
			String flagSQL = "";
			if (StringUtils.isNotEmpty(myflag)) {
				if (myflag.equals("In")) {// 说明是查询‘入库’
					flagSQL = " AND toone.flag = '1' "; // flag 出入库标志 1:入库， 2:出库
				}
			}
			String mytitle = getDecodeRequestParameter("mytitle");
			String startTime = getDecodeRequestParameter("startTime");
			String endTime = getDecodeRequestParameter("endTime");
			String mynum = getDecodeRequestParameter("mynum");
			String timeSQL = "";
			if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
				List<String> obTimeArr = MyTool.getTimeArrByHtmlParameterString(startTime + endTime);
				StringBuffer forSql = new StringBuffer();
				forSql.append(" and many.CREATETIME >= " + MyTool.StringUseToSql(obTimeArr.get(0)));
				forSql.append(" and many.CREATETIME <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(obTimeArr.get(obTimeArr.size() - 1))));
				timeSQL = forSql.toString();
			} else {
				List<String> obTimeArr = MyTool.getTimeArrByHtmlParameterString("Today");
				StringBuffer forSql = new StringBuffer();
				forSql.append(" and many.CREATETIME <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(obTimeArr.get(obTimeArr.size() - 1))));
				timeSQL = forSql.toString();
			}
			StringBuffer querySql = new StringBuffer();
			querySql.append(" select A.asid AS sid,A.amid as mid from ( ");
			querySql.append(" SELECT many.itemcode AS asid,many.id as amid,many.CREATETIME as atime ");
			querySql.append(" FROM ");
			querySql.append(" inv_stockrecordlines many ");
			querySql.append(" INNER JOIN inv_stockrecords toone ON many.invstockrecords_id = toone.id ");
			querySql.append(" AND ( toone.istemp != '1') ");
			querySql.append(flagSQL);
			querySql.append(" AND many.itemcode IS NOT NULL AND many.itemcode <> '' ");
			querySql.append(" AND many.quantity > 0 ");
			querySql.append(timeSQL);
			String mytitleSQL = "  ";
			if (StringUtils.isNotEmpty(mytitle)) {
				mytitleSQL = " and many.itemname like '%" + mytitle.trim() + "%' ";
				querySql.append(mytitleSQL);
			}
			String mynumSQL = "  ";
			if (StringUtils.isNotEmpty(mynum)) {
				mynumSQL = " and many.itemcode like '%" + mynum.trim() + "%' ";
				querySql.append(mynumSQL);
			}
			querySql.append(" ORDER BY many.CREATETIME desc ");
			querySql.append(" )A GROUP BY A.asid ");
			querySql.append(" ORDER BY A.atime desc ");

			Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, querySql.toString(), params);
			List resultList = pager.getResultList();
			for (int x = 0; x < resultList.size(); x++) {
				HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
				String itemcode = objectMap.get("sid").toString();
				String manyID = objectMap.get("mid").toString();
				StockRecordLines stockRecordLines = this.baseHibernateService.findEntityById(StockRecordLines.class, manyID);
				objectMap.put("itemcode", (stockRecordLines.getItemcode() == null ? "" : stockRecordLines.getItemcode() + ""));
				objectMap.put("itemname", (stockRecordLines.getItemname() == null ? "" : stockRecordLines.getItemname() + ""));
				objectMap.put("specification", (stockRecordLines.getSpecification() == null ? "" : stockRecordLines.getSpecification() + ""));
				objectMap.put("unitcost", (stockRecordLines.getUnitcost() == null ? "" : stockRecordLines.getUnitcost() + ""));
				if (stockRecordLines.getMeasureUnit() != null) {
					if (stockRecordLines.getMeasureUnit().getName() != null) {
						objectMap.put("measureUnitName", stockRecordLines.getMeasureUnit().getName());
					}
				} else {
					objectMap.put("measureUnitName", "");
				}
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("controlSQL", "listGoodsNumSfgklmopz");// listGoodsNumSfgklmopz
																	// 查询列表中的‘商品数量’
				hsMap.put("flagSQL", flagSQL);
				hsMap.put("timeSQL", timeSQL);
				hsMap.put("mytitleSQL", mytitleSQL);
				hsMap.put("mynumSQL", mynumSQL);
				hsMap.put("itemcode", itemcode);
				Double quantityNum = (Double) queryDataService.findGoodsDataSujrop(hsMap).get("totalDouble");
				objectMap.put("quantityNum", "" + quantityNum);
				hsMap.put("controlSQL", "listGoodsMoneySfgklmapz");// listGoodsMoneySfgklmapz
																	// 查询列表中的‘总价’
				Double totalPrice = (Double) queryDataService.findGoodsDataSujrop(hsMap).get("totalDouble");
				objectMap.put("totalPrice", "" + totalPrice);

				hsMap.put("controlSQL", "listGoodsLatelyTimeSfgklmakz");// listGoodsLatelyTimeSfgklmakz
																		// 查询列表中的‘最近入库时间’或‘最近出库时间’【注意这里两个通用listGoodsLatelyTimeSfgklmakz】
				String totime = (String) queryDataService.findGoodsDataSujrop(hsMap).get("stringRTinner");
				objectMap.put("totime", totime);
				resultList.set(x, objectMap);
			}
			pager.setResultList(resultList);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店库存报表 > 商品出库列表 【根据goGoodsQueryList()复制过来，只需改变一个参数myflag为Out即可】 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void goGoodsQueryListOut() {
		try {
			Map<String, Object> params = getParams();
			Pager myPager = this.getPager();
			String myflag = getDecodeRequestParameter("myflag");
			String flagSQL = "";
			if (StringUtils.isNotEmpty(myflag)) {
				if (myflag.equals("Out")) {// 说明是查询‘入库’
					flagSQL = " AND toone.flag = '2' "; // flag 出入库标志 1:入库， 2:出库
				}
			}
			String mytitle = getDecodeRequestParameter("mytitle");
			String startTime = getDecodeRequestParameter("startTime");
			String endTime = getDecodeRequestParameter("endTime");
			String mynum = getDecodeRequestParameter("mynum");
			String timeSQL = "";
			if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
				List<String> obTimeArr = MyTool.getTimeArrByHtmlParameterString(startTime + endTime);
				StringBuffer forSql = new StringBuffer();
				forSql.append(" and many.CREATETIME >= " + MyTool.StringUseToSql(obTimeArr.get(0)));
				forSql.append(" and many.CREATETIME <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(obTimeArr.get(obTimeArr.size() - 1))));
				timeSQL = forSql.toString();
			} else {
				List<String> obTimeArr = MyTool.getTimeArrByHtmlParameterString("Today");
				StringBuffer forSql = new StringBuffer();
				forSql.append(" and many.CREATETIME <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(obTimeArr.get(obTimeArr.size() - 1))));
				timeSQL = forSql.toString();
			}
			StringBuffer querySql = new StringBuffer();
			querySql.append(" select A.asid AS sid,A.amid as mid from ( ");
			querySql.append(" SELECT many.itemcode AS asid,many.id as amid,many.CREATETIME as atime ");
			querySql.append(" FROM ");
			querySql.append(" inv_stockrecordlines many ");
			querySql.append(" INNER JOIN inv_stockrecords toone ON many.invstockrecords_id = toone.id ");
			querySql.append(" AND ( toone.istemp != '1') ");
			querySql.append(flagSQL);
			querySql.append(" AND many.itemcode IS NOT NULL AND many.itemcode <> '' ");
			querySql.append(" AND many.quantity > 0 ");
			querySql.append(timeSQL);
			String mytitleSQL = "  ";
			if (StringUtils.isNotEmpty(mytitle)) {
				mytitleSQL = " and many.itemname like '%" + mytitle.trim() + "%' ";
				querySql.append(mytitleSQL);
			}
			String mynumSQL = "  ";
			if (StringUtils.isNotEmpty(mynum)) {
				mynumSQL = " and many.itemcode like '%" + mynum.trim() + "%' ";
				querySql.append(mynumSQL);
			}
			querySql.append(" ORDER BY many.CREATETIME desc ");
			querySql.append(" )A GROUP BY A.asid ");
			querySql.append(" ORDER BY A.atime desc ");

			Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, querySql.toString(), params);
			List resultList = pager.getResultList();
			for (int x = 0; x < resultList.size(); x++) {
				HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
				String itemcode = objectMap.get("sid").toString();
				String manyID = objectMap.get("mid").toString();
				StockRecordLines stockRecordLines = this.baseHibernateService.findEntityById(StockRecordLines.class, manyID);
				objectMap.put("itemcode", (stockRecordLines.getItemcode() == null ? "" : stockRecordLines.getItemcode() + ""));
				objectMap.put("itemname", (stockRecordLines.getItemname() == null ? "" : stockRecordLines.getItemname() + ""));
				objectMap.put("specification", (stockRecordLines.getSpecification() == null ? "" : stockRecordLines.getSpecification() + ""));
				objectMap.put("unitcost", (stockRecordLines.getUnitcost() == null ? "" : stockRecordLines.getUnitcost() + ""));
				if (stockRecordLines.getMeasureUnit() != null) {
					if (stockRecordLines.getMeasureUnit().getName() != null) {
						objectMap.put("measureUnitName", stockRecordLines.getMeasureUnit().getName());
					}
				} else {
					objectMap.put("measureUnitName", "");
				}
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("controlSQL", "listGoodsNumSfgklmopz");// listGoodsNumSfgklmopz
																	// 查询列表中的‘商品数量’
				hsMap.put("flagSQL", flagSQL);
				hsMap.put("timeSQL", timeSQL);
				hsMap.put("mytitleSQL", mytitleSQL);
				hsMap.put("mynumSQL", mynumSQL);
				hsMap.put("itemcode", itemcode);
				Double quantityNum = (Double) queryDataService.findGoodsDataSujrop(hsMap).get("totalDouble");
				objectMap.put("quantityNum", "" + quantityNum);
				hsMap.put("controlSQL", "listGoodsMoneySfgklmapz");// listGoodsMoneySfgklmapz
																	// 查询列表中的‘总价’
				Double totalPrice = (Double) queryDataService.findGoodsDataSujrop(hsMap).get("totalDouble");
				objectMap.put("totalPrice", "" + totalPrice);

				hsMap.put("controlSQL", "listGoodsLatelyTimeSfgklmakz");// listGoodsLatelyTimeSfgklmakz
																		// 查询列表中的‘最近入库时间’或‘最近出库时间’【注意这里两个通用listGoodsLatelyTimeSfgklmakz】
				String totime = (String) queryDataService.findGoodsDataSujrop(hsMap).get("stringRTinner");
				objectMap.put("totime", totime);
				resultList.set(x, objectMap);
			}
			pager.setResultList(resultList);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店库存报表 > 现存商品列表 **/ // Exist 存在的
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void goGoodsQueryListExist() {
		try {
			Map<String, Object> params = getParams();
			Pager myPager = this.getPager();

			String mytitle = getDecodeRequestParameter("mytitle");
			String mynum = getDecodeRequestParameter("mynum");
			StringBuffer hql = new StringBuffer();// nowStockGoodsNumSzphg
													// 现存商品量(多少种)
			String timeSQL = "  "; // 【注意timeSQL在后面的查询最近入库，出库时间有用，many不可缺少】
			List<String> objTimeArr = MyTool.getTimeArrByHtmlParameterString("Today");
			timeSQL = " AND many.CREATETIME <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size() - 1)));
			String mytitleSQL = "  ";// 【注意mytitleSQL在后面的查询最近入库，出库时间有用，many不可缺少】
			if (StringUtils.isNotEmpty(mytitle)) {
				mytitleSQL = " and many.itemname like '%" + mytitle.trim() + "%' ";
			}
			String mynumSQL = "  ";// 【注意mynumSQL在后面的查询最近入库，出库时间有用，many不可缺少】
			if (StringUtils.isNotEmpty(mynum)) {
				mynumSQL = " and many.itemcode like '%" + mynum.trim() + "%' ";
			}
			Map<String, Object> hsMap = new HashMap<String, Object>();
			Map<String, Object> manyIDMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			manyIDMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			manyIDMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
				manyIDMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			hql.append(" select many.itemcode as sid,many.avaquantity as sys from inv_inventory many ");
			hql.append(" where many.avaquantity > 0  and many.itemcode is not null  and many.itemcode <> '' ");
			hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
			hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");
			}
			hql.append(mytitleSQL);
			hql.append(mynumSQL);
			Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, hql.toString(), params);
			List resultList = pager.getResultList();

			for (int x = 0; x < resultList.size(); x++) {
				HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
				String itemcode = objectMap.get("sid").toString();// AS sid
																	// 商品编码。
				String spExistNum = objectMap.get("sys").toString();// AS sys
																	// 商品数量余数
																	// spExistNum
				manyIDMap.put("controlSQL", "byManyItemcodeSijodvn");// byManyItemcodeSijodvn  根据‘商品编码’查询最近的StockRecordLines出现的id
				manyIDMap.put("itemcode", itemcode);
				String manyID = (String) queryDataService.findGoodsDataSujrop(manyIDMap).get("stringRTinner");
				StockRecordLines stockRecordLines = new StockRecordLines();
					if(StringUtils.isNotEmpty(manyID) && !manyID.equals("null") ) {
						stockRecordLines = this.baseHibernateService.findEntityById(StockRecordLines.class, manyID);
					}
				objectMap.put("itemcode", (stockRecordLines.getItemcode() == null ? "" : stockRecordLines.getItemcode() + ""));
				objectMap.put("itemname", (stockRecordLines.getItemname() == null ? "" : stockRecordLines.getItemname() + ""));
				objectMap.put("specification", (stockRecordLines.getSpecification() == null ? "" : stockRecordLines.getSpecification() + ""));
				Double unitcost = (stockRecordLines.getUnitcost() == null ? 0.0 : stockRecordLines.getUnitcost());
				objectMap.put("unitcost", unitcost);// 单价
				if (stockRecordLines.getMeasureUnit() != null) {
					if (stockRecordLines.getMeasureUnit().getName() != null) {
						objectMap.put("measureUnitName", stockRecordLines.getMeasureUnit().getName());
					}
				} else {
					objectMap.put("measureUnitName", "");
				}
				
				objectMap.put("quantityNum", spExistNum);// 查询列表中的‘商品现存数量’
				objectMap.put("totalPrice", (unitcost * (Double.parseDouble(spExistNum))));// 商品现存总价=单价*商品现存数量
																							// 再四舍五入

				hsMap.put("controlSQL", "listGoodsLatelyTimeSfgklmakz");// listGoodsLatelyTimeSfgklmakz
																		// 查询列表中的‘最近入库时间’或‘最近出库时间’【注意这里两个通用listGoodsLatelyTimeSfgklmakz】
				hsMap.put("flagSQL", " AND toone.flag = '1' ");// 这里查询 ‘最近入库时间’
																// //flag 出入库标志
																// 1:入库， 2:出库
				hsMap.put("timeSQL", timeSQL);
				hsMap.put("mytitleSQL", mytitleSQL);
				hsMap.put("mynumSQL", mynumSQL);
				hsMap.put("itemcode", itemcode);
				String totimeIn = (String) queryDataService.findGoodsDataSujrop(hsMap).get("stringRTinner");
				objectMap.put("totimeIn", totimeIn);
				hsMap.put("flagSQL", " AND toone.flag = '2' ");// 这里查询 ‘最近入库时间’
																// //flag 出入库标志
																// 1:入库， 2:出库
				String totimeOut = (String) queryDataService.findGoodsDataSujrop(hsMap).get("stringRTinner");
				objectMap.put("totimeOut", totimeOut);
				resultList.set(x, objectMap);
			}
			pager.setResultList(resultList);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店库存报表 > 库存积压预警商品(多少种) Too much 太多 tooMuch **/
	public void tooMuchGoodsNum() {
		try {
			// <!-- 库存积压预警商品:进货-出货=余数，余数>=200 (暂且这么定义) -->
			String qTime = "Today";
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("controlSQL", "tooMuchGoodsNumSzpvg");// tooMuchGoodsNumSzpvg
															// 库存积压预警商品(多少种)
			hsMap.put("timeStr", qTime);
			hsMap.put("sentenceSQL", " and avaquantity >= 200 ");// sentence句子
																	// sentenceSQL代表
																	// sql语句条件
			Double tooMuchGoodsNum = (Double) queryDataService.findGoodsDataSujrop(hsMap).get("totalDouble");
			getRequest().setAttribute("tooMuchGoodsNum", MyTool.getIntFromDouble(tooMuchGoodsNum));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店库存报表 > 库存不足预警商品(多少种) **/
	public void notEnoughGoodsNum() {
		try {
			// <!-- 库存不足预警商品:进货-出货=余数， 余数<20 (暂且这么定义) -->
			String qTime = "Today";
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("controlSQL", "notEnoughGoodsNumSzpxg");// notEnoughGoodsNumSzpxg
																// 库存不足预警商品(多少种)
			hsMap.put("timeStr", qTime);
			hsMap.put("sentenceSQL", " and avaquantity < 20 ");// sentence句子
																// sentenceSQL代表
																// sql语句条件
			Double notEnoughGoodsNum = (Double) queryDataService.findGoodsDataSujrop(hsMap).get("totalDouble");
			getRequest().setAttribute("notEnoughGoodsNum", MyTool.getIntFromDouble(notEnoughGoodsNum));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店库存报表 > 现存商品量(多少种) **/
	public void nowStockGoodsNum() {
		try {
			String qTime = "Today";
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			hsMap.put("controlSQL", "nowStockGoodsNumSzphg");// nowStockGoodsNumSzphg  现存商品量(多少种)
			hsMap.put("timeStr", qTime);
			Double nowStockGoodsNum = (Double) queryDataService.findGoodsDataSujrop(hsMap).get("totalDouble");
			getRequest().setAttribute("nowStockGoodsNum", MyTool.getIntFromDouble(nowStockGoodsNum));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店库存报表 > 本月过期现存商品(多少种) invalid失效的 **/
	public void invalidGoodsNum() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			hsMap.put("controlSQL", "invalidGoodsNumSzhhg");// invalidGoodsNumSzhhg 本月过期现存商品(多少种)
			hsMap.put("timeStrA", "Today");// 需要两个时间 所以 timeStrA 今天
			hsMap.put("timeStrB", "ThisMonthOT");// 需要两个时间 所以 timeStrB 本月
			Double invalidGoodsNum = (Double) queryDataService.findGoodsDataSujrop(hsMap).get("totalDouble");
			getRequest().setAttribute("invalidGoodsNum", MyTool.getIntFromDouble(invalidGoodsNum));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店库存报表 > 今日出库商品总数 (种类) **/
	public void warehouseGoodsOUT(String qTime) {
		try {
			String timeA = qTime;// 当日
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("controlSQL", "warehouseGoodsOUTSzyhg");// warehouseGoodsOUTSzyhg
																// 今日出库商品总数(种类)
			hsMap.put("timeStr", timeA + timeA);
			Double whGOUTtodayNum = (Double) queryDataService.findGoodsDataSujrop(hsMap).get("totalDouble");
			String timeB = MyTool.dateReduceOrAddByInt(qTime, -1);// 昨日
			hsMap.put("timeStr", timeB + timeB);
			Double whGOUTyesteNum = (Double) queryDataService.findGoodsDataSujrop(hsMap).get("totalDouble");
			getRequest().setAttribute("whGOUTtodayNum", MyTool.getIntFromDouble(whGOUTtodayNum));
			getRequest().setAttribute("whGOUTyesteNum", MyTool.getIntFromDouble(whGOUTyesteNum));
			String divClass = "whGOUTtodayIclass";// ${whGOUTtodayIclass}
			String divMomNum = "whGOUTtodayImNum";
			String momNum = MyTool.getMomOne(whGOUTtodayNum, whGOUTyesteNum);
			if (momNum.startsWith("-")) { // 页面标签中的class是不完整的，这里通过计算，补充完整class样式
				String num = momNum.replace("-", "");
				getRequest().setAttribute(divClass, "down tmyColorA");
				getRequest().setAttribute(divMomNum, num);
			} else if (momNum.startsWith("+")) {
				String num = momNum.replace("+", "");
				getRequest().setAttribute(divClass, "up tmyColorB");
				getRequest().setAttribute(divMomNum, num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店库存报表 > 今日入库商品总数 (种类) **/
	public void warehouseGoodsIN(String qTime) {
		try {
			String timeA = qTime;// 当日
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("controlSQL", "warehouseGoodsINSvyhg");// warehouseGoodsINSvyhg
																// 今日入库商品总数(种类)
			hsMap.put("timeStr", timeA + timeA);
			Double whGINtodayNum = (Double) queryDataService.findGoodsDataSujrop(hsMap).get("totalDouble");
			String timeB = MyTool.dateReduceOrAddByInt(qTime, -1);// 昨日
			hsMap.put("timeStr", timeB + timeB);
			Double whGINyesteNum = (Double) queryDataService.findGoodsDataSujrop(hsMap).get("totalDouble");
			getRequest().setAttribute("whGINtodayNum", MyTool.getIntFromDouble(whGINtodayNum));
			getRequest().setAttribute("whGINyesteNum", MyTool.getIntFromDouble(whGINyesteNum));
			String divClass = "whGINtodayIclass";
			String divMomNum = "whGINtodayImNum";
			String momNum = MyTool.getMomOne(whGINtodayNum, whGINyesteNum);
			if (momNum.startsWith("-")) { // 页面标签中的class是不完整的，这里通过计算，补充完整class样式
				String num = momNum.replace("-", "");
				getRequest().setAttribute(divClass, "down tmyColorA");
				getRequest().setAttribute(divMomNum, num);
			} else if (momNum.startsWith("+")) { // .tmyColorB{color:#D0000D} /*
													// 蓝色箭头 用于环比符号显示 */
				String num = momNum.replace("+", ""); // .tmyColorA{color:#05CD15}
														// /* 粉红色箭头 用于环比符号显示 */
				getRequest().setAttribute(divClass, "up tmyColorB");
				getRequest().setAttribute(divMomNum, num); // class="fa
															// fa-arrow-down
															// tmyColorA"//class="fa
															// fa-arrow-up
															// tmyColorB"
			}
			// 页面应该是这样的
			// <i class="fa fa-arrow-${whGINtodayIclass }"></i>${whGINtodayImNum
			// }%</strong>
			// <style type="text/css">
			// .tmyColorA{color:#05CD15} /* 粉红色下箭头 用于环比符号显示 */
			// .tmyColorB{color:#D0000D} /* 蓝色上箭头 用于环比符号显示 */
			// </style>
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店管理 > 门店数据统计 > 门店库存报表 最近30日商品入库数量Top10 **/
	public void stockAnalysisViewA() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			List<String> timeArr = new ArrayList<String>();
			if (endTime.startsWith("-Lately-Day{")) {
				timeArr = MyTool.getTimeArrByHtmlParameterString(endTime);// 查询最近几天
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
			}
			hsMap.put("timeArr", timeArr);
			hsMap.put("topNum", topNum);
			hsMap.put("controlSQL", "goodsStorageNumSabe");// goodsStorageNumSabe商品入库数量
			String jsonStrReturn = (String) queryDataService.findStockAnalysisViewASabc(hsMap).get("jsonStrReturn");
			renderJson(jsonStrReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店管理 > 门店数据统计 > 门店库存报表 最近30日商品入库金额Top10 **/
	public void stockAnalysisViewB() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			List<String> timeArr = new ArrayList<String>();
			if (endTime.startsWith("-Lately-Day{")) {
				timeArr = MyTool.getTimeArrByHtmlParameterString(endTime);// 查询最近几天
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
			}
			hsMap.put("timeArr", timeArr);
			hsMap.put("topNum", topNum);
			hsMap.put("controlSQL", "goodsStorageMoneySrbe");// goodsStorageMoneySrbe
																// 商品入库金额 Money
			String jsonStrReturn = (String) queryDataService.findStockAnalysisViewASabc(hsMap).get("jsonStrReturn");
			renderJson(jsonStrReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店管理 > 门店数据统计 > 门店库存报表 最近30日商品出库数量Top10 **/
	public void stockAnalysisViewE() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			List<String> timeArr = new ArrayList<String>();
			if (endTime.startsWith("-Lately-Day{")) {
				timeArr = MyTool.getTimeArrByHtmlParameterString(endTime);// 查询最近几天
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
			}
			hsMap.put("timeArr", timeArr);
			hsMap.put("topNum", topNum);
			hsMap.put("controlSQL", "goodsOutStorageNumSame");// goodsOutStorageNumSame商品出库数量
			String jsonStrReturn = (String) queryDataService.findStockAnalysisViewASabc(hsMap).get("jsonStrReturn");
			renderJson(jsonStrReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店管理 > 门店数据统计 > 门店库存报表 最近30日商品出库金额Top10 **/
	public void stockAnalysisViewF() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			List<String> timeArr = new ArrayList<String>();
			if (endTime.startsWith("-Lately-Day{")) {
				timeArr = MyTool.getTimeArrByHtmlParameterString(endTime);// 查询最近几天
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
			}
			hsMap.put("timeArr", timeArr);
			hsMap.put("topNum", topNum);
			hsMap.put("controlSQL", "goodsOutStorageMoneySrbb");// goodsOutStorageMoneySrbb
																// 商品出库金额
			String jsonStrReturn = (String) queryDataService.findStockAnalysisViewASabc(hsMap).get("jsonStrReturn");
			renderJson(jsonStrReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 门店管理>门店数据统计>门店销售统计报表
	 */
	public String goSaleView() {
		return "goSaleView";
	}
	/** 门店管理 > 门店数据统计 > 门店销售统计 最近30日商品销售数量Top10 **/
	public void saleAnalysisViewC() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			List<String> timeArr = new ArrayList<String>();
			if (endTime.startsWith("-Lately-Day{")) {
				timeArr = MyTool.getTimeArrByHtmlParameterString(endTime);// 查询最近几天
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
			}
			hsMap.put("timeArr", timeArr);
			hsMap.put("topNum", topNum);
			hsMap.put("controlSQL", "goodsSaleNumSaveh");// goodsSaleNumSaveh商品销售数量
			String jsonStrReturn = (String) queryDataService.findStockAnalysisViewASabc(hsMap).get("jsonStrReturn");
			renderJson(jsonStrReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店管理 > 门店数据统计 > 门店销售统计 最近30日商品销售金额Top10 **/
	public void saleAnalysisViewD() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			List<String> timeArr = new ArrayList<String>();
			if (endTime.startsWith("-Lately-Day{")) {
				timeArr = MyTool.getTimeArrByHtmlParameterString(endTime);// 查询最近几天
			} else {
				timeArr = MyTool.get_Date_StartTOEnd_ByDays_C(startTime, endTime, 366);// 【这里的60表示开始时间和结束时间的差最多366天，否则返回366天的集合】以结束时间倒退366天正序
			}
			hsMap.put("timeArr", timeArr);
			hsMap.put("topNum", topNum);
			hsMap.put("controlSQL", "goodsSaleMoneySkvkh");// goodsSaleMoneySkvkh商品销售金额
			String jsonStrReturn = (String) queryDataService.findStockAnalysisViewASabc(hsMap).get("jsonStrReturn");
			renderJson(jsonStrReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTopNum() {
		return topNum;
	}
	public void setTopNum(String topNum) {
		this.topNum = topNum;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
}
