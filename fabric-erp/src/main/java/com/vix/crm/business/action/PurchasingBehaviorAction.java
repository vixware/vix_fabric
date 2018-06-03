package com.vix.crm.business.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.vixdata.dataappinterface.IDataAccuracy;
import com.vix.core.constant.SearchCondition;
import com.vix.crm.business.entity.StoreSalesInformation;
import com.vix.crm.business.hql.PurchasingBehaviorHqlProvider;
import com.vix.crm.business.service.IPurchasingBehaviorService;
import com.vix.drp.channel.entity.ChannelDistributor;

@Controller
@Scope("prototype")
public class PurchasingBehaviorAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Resource(name = "purchasingBehaviorHqlProvider")
	private PurchasingBehaviorHqlProvider purchasingBehaviorHqlProvider;
	@Autowired
	private IPurchasingBehaviorService purchasingBehaviorService;
	@Autowired
	private IDataAccuracy dataAccuracy;
	private List<StoreSalesInformation> orderSalesInformationList;
	// 总成交额
	private Double totalAmount = 0d;
	// 首次订单购买金额
	private Double firstOrderAmount = 0d;
	// 回头订单购买金额
	private Double buyBackOrderAmount = 0d;
	/**
	 * 首次购买订单数
	 */
	private Double firstOrderNum = 0d;
	/**
	 * 订单均价
	 */
	private Double firstOrderPrice = 0d;
	/**
	 * 回头购买订单数
	 */
	private Double buyBackOrderNum = 0d;
	/**
	 * 回头订单均价
	 */
	private Double buyBackOrderPrice = 0d;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
	SimpleDateFormat msdf = new SimpleDateFormat("MM");
	/**
	 * 店铺
	 */
	private List<ChannelDistributor> channelDistributorList;
	/**
	 * 查询条件
	 */
	private StringBuilder searchCriteria;

	@Override
	public String goList() {
		try {
			// 获取店铺
			Map<String, Object> p = getParams();
			p.put("type," + SearchCondition.ANYLIKE, "5");
			channelDistributorList = purchasingBehaviorService.findAllByConditions(ChannelDistributor.class, p);
			// 获取店铺
			Map<String, Object> params = new HashMap<String, Object>();
			// 获取某一天的数据
			String channelDistributorId = getRequestParameter("channelDistributorId");
			if (channelDistributorId != null && !"".equals(channelDistributorId) && !"-1".equals(channelDistributorId)) {
				params.put("channelDistributorId", channelDistributorId);
			}
			String date = getRequestParameter("date");
			String weekday = getRequestParameter("weekday");
			String monthdate = getRequestParameter("monthdate");
			String startdate = getRequestParameter("startdate");
			String enddate = getRequestParameter("enddate");
			String dateType = getRequestParameter("dateType");
			searchCriteria = new StringBuilder();
			if (dateType != null && !"".equals(dateType)) {
				if ("d".equals(dateType)) {
					// 天
					if (date != null && !"".equals(date)) {
						params.putAll(getDate(date));
						searchCriteria.append(getDate(date));
					}
				} else if ("w".equals(dateType)) {
					// 周
					if (weekday != null && !"".equals(weekday)) {
						String weekdays[] = weekday.split("-");
						params = getWeekDay(weekdays[0], weekdays[1]);
						searchCriteria.append(weekdays[0] + "/" + weekdays[1]);
					}
				} else if ("m".equals(dateType)) {
					// 月
					if (monthdate != null && !"".equals(monthdate)) {
						String monthdates[] = monthdate.split("-");
						params = getMonthDate(monthdates[0], monthdates[1]);
						searchCriteria.append(monthdates[0] + "/" + monthdates[1]);
					}
				} else if ("b".equals(dateType)) {
					// 时间段
					if (startdate != null && !"".equals(startdate) && enddate != null && !"".equals(enddate)) {
						params = getBetweenDate(startdate, enddate);
						searchCriteria.append(startdate + "/" + enddate);
					}
				}
			} else {
				// 默认获取本月的数据
				params.putAll(getMonthDate(ysdf.format(new Date()), msdf.format(new Date())));
				searchCriteria.append(ysdf.format(new Date()) + "/" + msdf.format(new Date()));
			}
			StringBuilder hql = purchasingBehaviorHqlProvider.findStoreSalesInformationList(params);
			orderSalesInformationList = purchasingBehaviorService.findStoreSalesInformationList(hql.toString());
			if (orderSalesInformationList != null && !orderSalesInformationList.isEmpty()) {
				for (StoreSalesInformation storeSalesInformation : orderSalesInformationList) {
					totalAmount += storeSalesInformation.getTotalAmount();
					firstOrderAmount += storeSalesInformation.getFirstOrderAmount();
					buyBackOrderAmount += storeSalesInformation.getBuyBackOrderAmount();
					firstOrderNum += storeSalesInformation.getFirstOrderNum();
					buyBackOrderNum += storeSalesInformation.getBuyBackOrderNum();
				}
				totalAmount = dataAccuracy.getAmountDecimal(totalAmount);
				firstOrderAmount = dataAccuracy.getAmountDecimal(firstOrderAmount);
				buyBackOrderAmount = dataAccuracy.getAmountDecimal(buyBackOrderAmount);
				firstOrderPrice = dataAccuracy.getAmountDecimal(firstOrderAmount / firstOrderNum);
				buyBackOrderPrice = dataAccuracy.getAmountDecimal(buyBackOrderAmount / buyBackOrderNum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goChooseChannelDistributor() {
		return "goChooseChannelDistributor";
	}

	public String goSearch() {
		return "goSearch";
	}

	public Double add(Double firstOrderNum2, BigDecimal bigDecimal) {
		BigDecimal b1 = new BigDecimal(firstOrderNum2);
		return b1.add(bigDecimal).doubleValue();
	}

	public String goSingleList() {
		return GO_SINGLE_LIST;
	}

	public List<StoreSalesInformation> getOrderSalesInformationList() {
		return orderSalesInformationList;
	}

	public void setOrderSalesInformationList(List<StoreSalesInformation> orderSalesInformationList) {
		this.orderSalesInformationList = orderSalesInformationList;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getFirstOrderAmount() {
		return firstOrderAmount;
	}

	public void setFirstOrderAmount(Double firstOrderAmount) {
		this.firstOrderAmount = firstOrderAmount;
	}

	public Double getBuyBackOrderAmount() {
		return buyBackOrderAmount;
	}

	public void setBuyBackOrderAmount(Double buyBackOrderAmount) {
		this.buyBackOrderAmount = buyBackOrderAmount;
	}

	public Double getFirstOrderPrice() {
		return firstOrderPrice;
	}

	public void setFirstOrderPrice(Double firstOrderPrice) {
		this.firstOrderPrice = firstOrderPrice;
	}

	public Double getBuyBackOrderPrice() {
		return buyBackOrderPrice;
	}

	public void setBuyBackOrderPrice(Double buyBackOrderPrice) {
		this.buyBackOrderPrice = buyBackOrderPrice;
	}

	public Double getFirstOrderNum() {
		return firstOrderNum;
	}

	public void setFirstOrderNum(Double firstOrderNum) {
		this.firstOrderNum = firstOrderNum;
	}

	public Double getBuyBackOrderNum() {
		return buyBackOrderNum;
	}

	public void setBuyBackOrderNum(Double buyBackOrderNum) {
		this.buyBackOrderNum = buyBackOrderNum;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

	public Map<String, Object> getDate(String date) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (date != null) {
		} else {
			date = sdf.format(new Date());
		}
		map.put("startTime", date + " 00:00:00");
		map.put("endTime", date + " 23:59:59");
		return map;
	}

	public Map<String, Object> getWeekDay(String yeardate, String weekday) {

		Map<String, Object> map = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(yeardate));
		cal.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(weekday));
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		map.put("startTime", sdf.format(cal.getTime()) + " 00:00:00");
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		map.put("endTime", sdf.format(cal.getTime()) + " 23:59:59");
		return map;
	}

	public Map<String, Object> getMonthDate(String yeardate, String monthdate) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取Calendar
		Calendar calendar = Calendar.getInstance();
		// 设置时间,当前时间不用设置
		calendar.set(Calendar.YEAR, Integer.parseInt(yeardate));
		calendar.set(Calendar.MONTH, Integer.parseInt(monthdate) - 1);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		map.put("startTime", sdf.format(calendar.getTime()) + " 00:00:00");
		// 设置日期为本月最大日期
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		map.put("endTime", sdf.format(calendar.getTime()) + " 23:59:59");
		return map;
	}

	/**
	 * 获取指定时间段的开始,结束日期
	 * 
	 * @param startdate
	 * @param enddate
	 * @return
	 */
	public Map<String, Object> getBetweenDate(String startdate, String enddate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startdate + " 00:00:00");
		map.put("endTime", enddate + " 23:59:59");
		return map;
	}

	public StringBuilder getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(StringBuilder searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

}
