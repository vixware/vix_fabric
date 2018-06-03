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
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.personaloffice.service.ISeekDataService;

@Controller
@Scope("prototype")
public class VixntDoBusinessDataAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ISeekDataService seekDataService;
	private String setChannelDistributorID = "";// 控制查询哪些 门店 的id集合，限制查询条件 //
												// setChannelDistributorID 设置
												// 门店id
	private List<ChannelDistributor> channelDistributorList;
	private String startTime;
	private String endTime;
	private String topNum;
	private String queryTime = "Initialization";// queryTime 包含startTime和endTime
												// 代表查询 什么时间段的内容
	private String queryWhat;// queryWhat
								// 是指页面要查询的内容，注意命名别重复，用不同的字母进行限制，防止重复，比如查询‘营业收入’，查询‘储值收入’
	private String liabcd;// 控制ul下的li的按钮样式显示
	/**
	 * 门店管理>门店数据统计>门店营业统计
	 */
	public String goDoBusinessSurveyView() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDoBusinessSurveyView";
	}
	/**
	 * 门店管理>门店数据统计>门店营业概况
	 */
	public String goDoBusinessView() {
		try {
			if (StringUtils.isNotEmpty(setChannelDistributorID)) {// 说明不是初始化时
				getRequest().setAttribute("selectDistributorID", setChannelDistributorID);
				if ("查询平台所有的门店相关数据".equals("查询平台所有的门店相关数据")) {// 模拟根据账号角色，分配查询门店的相关id集合，将来需要替换代码
					channelDistributorList = seekDataService.seekChannelDistributorList(new HashMap<String, Object>());
					if (channelDistributorList != null) {
						if (channelDistributorList.size() >= 2) {
							ChannelDistributor ChannelDistributorAll = new ChannelDistributor();
							ChannelDistributorAll.setId("all-C---lD---r-ID");
							ChannelDistributorAll.setName("全部门店");
							channelDistributorList.add(ChannelDistributorAll);
							for (int start = 0, end = channelDistributorList.size() - 1; start < end; start++, end--) {
								ChannelDistributor temp = channelDistributorList.get(end);
								channelDistributorList.set(end, channelDistributorList.get(start));
								channelDistributorList.set(start, temp);
							}
						}
					}
				} else if ("查询具体某个门店的相关数据".equals("查")) {// 控制查询哪些门店的id集合，限制查询条件
															// // setSupplierID
															// 设置门店id
					setChannelDistributorID = "{a9fe3cad-5fb4-1a6c-815f-b8751afd0151}";
					ChannelDistributor channelDistributor = seekDataService.findEntityById(ChannelDistributor.class, "a9fe3cad-5fb4-1a6c-815f-b8751afd0151");
					List<ChannelDistributor> lst = new ArrayList<ChannelDistributor>();
					lst.add(channelDistributor);
					channelDistributorList = lst;
				}
			} else {// 说明是初始化页面时
				if ("查询平台所有的门店相关数据".equals("查询平台所有的门店相关数据")) {// 模拟根据账号角色，分配查询门店的相关id集合，将来需要替换代码
					setChannelDistributorID = "all-C---lD---r-ID";
					channelDistributorList = seekDataService.seekChannelDistributorList(new HashMap<String, Object>());
					if (channelDistributorList != null) {
						if (channelDistributorList.size() >= 2) {
							ChannelDistributor ChannelDistributorAll = new ChannelDistributor();
							ChannelDistributorAll.setId(setChannelDistributorID);
							ChannelDistributorAll.setName("全部门店");
							channelDistributorList.add(ChannelDistributorAll);
							for (int start = 0, end = channelDistributorList.size() - 1; start < end; start++, end--) {
								ChannelDistributor temp = channelDistributorList.get(end);
								channelDistributorList.set(end, channelDistributorList.get(start));
								channelDistributorList.set(start, temp);
							}
						}
					}

				} else if ("查询具体某个门店的相关数据".equals("查")) {// 控制查询哪些门店的id集合，限制查询条件
															// // setSupplierID
															// 设置门店id
					setChannelDistributorID = "{a9fe3cad-5fb4-1a6c-815f-b8751afd0151}";
					ChannelDistributor channelDistributor = seekDataService.findEntityById(ChannelDistributor.class, "a9fe3cad-5fb4-1a6c-815f-b8751afd0151");
					List<ChannelDistributor> lst = new ArrayList<ChannelDistributor>();
					lst.add(channelDistributor);
					channelDistributorList = lst;
				}
				setChannelDistributorID = new String("{S---tH-d}" + setChannelDistributorID + "{E---dT-l}");
			}
			String liabcd = getRequestParameter("liabcd");
			String lia = "lia";// 操作lia，操作 liabcd的目的是给哪一个li下的a标签按钮加颜色样式
			if (StringUtils.isNotEmpty(liabcd)) {
				lia = liabcd;
			}
			getRequest().setAttribute(lia, "mytxt-color-wathet");
			if (StringUtils.isNotEmpty(queryTime)) {
				if (queryTime.equals("Initialization")) {
					String todayStr = MyTool.getTodayBy_yyyyMMdd();// 用于页面初始化时，输出(2017-12-08星期五)
					String weekNumStr = MyTool.getWeekDayByDate(todayStr);
					String todayStrweekNumStr = "(" + todayStr + weekNumStr + ")";
					getRequest().setAttribute("todayStrweekNumStr", todayStrweekNumStr);
					queryTime = todayStr + todayStr;
				} else {
					List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(queryTime);
					if (MyTool.isNotEmpty(timeArr)) {
						if (timeArr.size() == 1) {// (2017-12-07星期四)
							String dateStr = timeArr.get(0);
							String weekNumStr = MyTool.getWeekDayByDate(dateStr);
							String dateStrweekNumStr = "(" + dateStr + weekNumStr + ")";
							getRequest().setAttribute("todayStrweekNumStr", dateStrweekNumStr);
							queryTime = dateStr + dateStr;
						} else if (timeArr.size() >= 2) {// (2017-12-08 至
															// 2017-12-09 2天)
							String dateStrStart = timeArr.get(0);
							String dateStrEnd = timeArr.get(timeArr.size() - 1);
							Integer timeDifference = (MyTool.getTimeDifference(dateStrStart, dateStrEnd)) + 1;
							ArrayList<String> timeArrNineTy = new ArrayList<String>();
							int maxSize = 90;// 只取最多以结束时间倒退90天的时间段
							if (timeDifference >= (maxSize + 1)) {
								int startsIndex = timeArr.size() - maxSize;
								for (int x = startsIndex; x < timeArr.size(); x++) {
									timeArrNineTy.add(timeArr.get(x));
								}
							}
							if (MyTool.isNotEmpty(timeArrNineTy)) {
								timeArr = new ArrayList<String>();
								timeArr = timeArrNineTy;
								dateStrStart = timeArr.get(0);
								dateStrEnd = timeArr.get(timeArr.size() - 1);
								timeDifference = (MyTool.getTimeDifference(dateStrStart, dateStrEnd)) + 1;
							}
							String dateStrweekNumStr = "(" + dateStrStart + "至" + dateStrEnd + " " + timeDifference + "天)";
							getRequest().setAttribute("todayStrweekNumStr", dateStrweekNumStr);
							queryTime = dateStrStart + dateStrEnd;
						}
					}
				}
			}
			// .println("要查询的相关门店 "+setChannelDistributorID);
			// .println("要查询的相关时间段 "+queryTime); //已证明传递相关门店参数正确，传递相关时间段参数正确

			String setDistributorIDBrackets = seekDataService.seekDifferentIDsAbcd(setChannelDistributorID);
			doBusinessIncomeFigure(setChannelDistributorID, queryTime, setDistributorIDBrackets);// 已经查询到相关id
			rechargeIncomeFigure(setChannelDistributorID, queryTime, setDistributorIDBrackets);
			discountFigure(setChannelDistributorID, queryTime, setDistributorIDBrackets);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDoBusinessView";
	}
	/** 门店营业概况 营业收入 **/
	public void doBusinessIncomeFigure(String setChannelDistributorID, String queryTime, String setDistributorIDBrackets) {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("controlSQL", "doBusinessIncomeFigureAvBn");// doBusinessIncomeFigureAvBn
																	// 计算‘营业收入’，其中AvBn是随机字母后缀，防止重复
			hsMap.put("timeStr", queryTime);
			hsMap.put("conditionSQL", "sumMoney");// sumMoney是多少 钱的合计
			hsMap.put("setDistributorIDBrackets", setDistributorIDBrackets);
			Double doBusinessNA = (Double) seekDataService.seekReturnOneDataSoulLiA(hsMap).get("totalDouble");
			hsMap.put("conditionSQL", "countOrdersNum");// countOrdersNum是多少
														// 笔的合计
			Double doBusinessNB = (Double) seekDataService.seekReturnOneDataSoulLiA(hsMap).get("totalDouble");
			List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(queryTime);
			List<String> momTimeArr = MyTool.getMomTimeArrByArr((ArrayList<String>) timeArr, 100);
			hsMap.put("timeStr", (momTimeArr.get(0) + momTimeArr.get(momTimeArr.size() - 1)));
			hsMap.put("conditionSQL", "sumMoney");
			Double doBusinessNC = (Double) seekDataService.seekReturnOneDataSoulLiA(hsMap).get("totalDouble");// 环比时间段金额合计

			getRequest().setAttribute("doBusinessNB", MyTool.getIntFromDouble(doBusinessNB));// 本期营业收入
																								// 多少笔
			getRequest().setAttribute("doBusinessNA", MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(doBusinessNA)));// 本期营业收入
																															// 多少钱

			String divClass = "doBusinessColor";
			String divMomNum = "doBusinessNum";
			String momNum = MyTool.getMomOne(doBusinessNA, doBusinessNC);
			if (momNum.startsWith("-")) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 门店营业概况 储值收入 **/
	public void rechargeIncomeFigure(String setChannelDistributorID, String queryTime, String setDistributorIDBrackets) {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("controlSQL", "rechargeIncomeFigureAvBn");// rechargeIncomeFigureAvBn
																// 计算 储值收入
			hsMap.put("timeStr", queryTime);
			hsMap.put("conditionSQL", "sumMoney");// sumMoney是多少 钱的合计
			hsMap.put("setDistributorIDBrackets", setDistributorIDBrackets);
			Double rechargeNA = (Double) seekDataService.seekReturnOneDataSoulLiA(hsMap).get("totalDouble");
			hsMap.put("conditionSQL", "countOrdersNum");// countOrdersNum是多少
														// 笔的合计
			Double rechargeNB = (Double) seekDataService.seekReturnOneDataSoulLiA(hsMap).get("totalDouble");
			List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(queryTime);
			ArrayList<String> momTimeArr = MyTool.getMomTimeArrByArr((ArrayList<String>) timeArr, 100);
			hsMap.put("timeStr", (momTimeArr.get(0) + momTimeArr.get(momTimeArr.size() - 1)));
			hsMap.put("conditionSQL", "sumMoney");
			Double rechargeNC = (Double) seekDataService.seekReturnOneDataSoulLiA(hsMap).get("totalDouble");// 环比时间段金额合计

			getRequest().setAttribute("rechargeNB", MyTool.getIntFromDouble(rechargeNB));// 本期充值收入
																							// 多少笔
			getRequest().setAttribute("rechargeNA", MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(rechargeNA)));// 本期充值收入
																														// 多少钱

			String divClass = "rechargeColor";
			String divMomNum = "rechargeNum";
			String momNum = MyTool.getMomOne(rechargeNA, rechargeNC);
			if (momNum.startsWith("-")) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店营业概况 折扣计算 **/
	public void discountFigure(String setChannelDistributorID, String queryTime, String setDistributorIDBrackets) {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("controlSQL", "discountFigureAviBn");// discountFigureAviBn
															// 计算折扣
			hsMap.put("timeStr", queryTime);
			hsMap.put("conditionSQL", "sumMoney");// sumMoney是多少 钱的合计
			hsMap.put("setDistributorIDBrackets", setDistributorIDBrackets);
			Double discountNA = (Double) seekDataService.seekReturnOneDataSoulLiA(hsMap).get("totalDouble");
			hsMap.put("conditionSQL", "countOrdersNum");// countOrdersNum是多少
														// 笔的合计
			Double discountNB = (Double) seekDataService.seekReturnOneDataSoulLiA(hsMap).get("totalDouble");
			List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(queryTime);
			ArrayList<String> momTimeArr = MyTool.getMomTimeArrByArr((ArrayList<String>) timeArr, 100);
			hsMap.put("timeStr", (momTimeArr.get(0) + momTimeArr.get(momTimeArr.size() - 1)));
			hsMap.put("conditionSQL", "sumMoney");
			Double discountNC = (Double) seekDataService.seekReturnOneDataSoulLiA(hsMap).get("totalDouble");// 环比时间段金额合计

			getRequest().setAttribute("discountNB", MyTool.getIntFromDouble(discountNB));// 折扣金额>0时的订单
																							// 多少笔
			getRequest().setAttribute("discountNA", MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(discountNA)));// 本期折扣金额>0时的订单
																														// 共计多少钱

			String divClass = "discountColor";
			String divMomNum = "discountNum";
			String momNum = MyTool.getMomOne(discountNA, discountNC);
			if (momNum.startsWith("-")) {
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

	public String getSetChannelDistributorID() {
		return setChannelDistributorID;
	}

	public void setSetChannelDistributorID(String setChannelDistributorID) {
		this.setChannelDistributorID = setChannelDistributorID;
	}
	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}
	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}
	public String getQueryWhat() {
		return queryWhat;
	}
	public void setQueryWhat(String queryWhat) {
		this.queryWhat = queryWhat;
	}
	public String getLiabcd() {
		return liabcd;
	}
	public void setLiabcd(String liabcd) {
		this.liabcd = liabcd;
	}

}
