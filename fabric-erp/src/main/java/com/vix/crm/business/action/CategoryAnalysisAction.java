package com.vix.crm.business.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.crm.business.controller.CategoryAnalysisController;
import com.vix.crm.business.hql.PurchasingBehaviorHqlProvider;
import com.vix.crm.business.service.IPurchasingBehaviorService;
import com.vix.crm.business.vo.GoodsSaleInformation;
import com.vix.drp.channel.entity.ChannelDistributor;

@Controller
@Scope("prototype")
public class CategoryAnalysisAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CategoryAnalysisController categoryAnalysisController;
	@Resource(name = "purchasingBehaviorHqlProvider")
	private PurchasingBehaviorHqlProvider purchasingBehaviorHqlProvider;
	@Autowired
	private IPurchasingBehaviorService purchasingBehaviorService;
	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy年-MM月");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
	static SimpleDateFormat msdf = new SimpleDateFormat("MM");
	/**
	 * 店铺
	 */
	private List<ChannelDistributor> channelDistributorList;
	private List<GoodsSaleInformation> goodsSaleInformationList;

	@Override
	public String goList() {
		try {
			Map<String, Object> p = getParams();
			p.put("type," + SearchCondition.ANYLIKE, "5");
			channelDistributorList = categoryAnalysisController.doListChannelDistributorList(p);

			//获取店铺
			Map<String, Object> params = new HashMap<String, Object>();
			//获取某一天的数据
			String channelDistributorId = getRequestParameter("channelDistributorId");
			if (StringUtils.isNotEmpty(channelDistributorId) && !"-1".equals(channelDistributorId)) {
				params.put("channelDistributorId", channelDistributorId);
			}
			String date = getRequestParameter("date");
			String weekday = getRequestParameter("weekday");
			String monthdate = getRequestParameter("monthdate");
			String startdate = getRequestParameter("startdate");
			String enddate = getRequestParameter("enddate");
			String dateType = getRequestParameter("dateType");
			if (dateType != null && !"".equals(dateType)) {
				if ("d".equals(dateType)) {
					//天
					if (date != null && !"".equals(date)) {
						params.putAll(getDate(date));
					}
				} else if ("w".equals(dateType)) {
					//周
					if (weekday != null && !"".equals(weekday)) {
						String weekdays[] = weekday.split("-");
						params = getWeekDay(weekdays[0], weekdays[1]);
					}
				} else if ("m".equals(dateType)) {
					//月
					if (monthdate != null && !"".equals(monthdate)) {
						String monthdates[] = monthdate.split("-");
						params = getMonthDate(monthdates[0], monthdates[1]);
					}
				} else if ("b".equals(dateType)) {
					//时间段
					if (startdate != null && !"".equals(startdate) && enddate != null && !"".equals(enddate)) {
						params = getBetweenDate(startdate, enddate);
					}
				}
			} else {
				//默认获取本月的数据
				params.putAll(getMonthDate(ysdf.format(new Date()), msdf.format(new Date())));
			}
			StringBuilder hql = purchasingBehaviorHqlProvider.findGoodsSaleInformationList(params);
			goodsSaleInformationList = purchasingBehaviorService.findGoodsSaleInformationList(hql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
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
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
		map.put("startTime", sdf.format(cal.getTime()) + " 00:00:00");
		//这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		//增加一个星期，才是我们中国人理解的本周日的日期
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

	/*public Map<String, Object> getDate(String date) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (date != null) {
		} else {
			date = sdf.format(new Date());
		}
		map.put("startTime", date + " 00:00:00");
		map.put("endTime", date + " 23:59:59");
		return map;
	}

	public Map<String, Object> getWeekDay() {
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
		map.put("startTime", sdf.format(cal.getTime()) + " 00:00:00");
		//这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		//增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		map.put("endTime", sdf.format(cal.getTime()) + " 23:59:59");
		return map;
	}

	public Map<String, Object> getMonthDate() {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取Calendar 
		Calendar calendar = Calendar.getInstance();
		// 设置时间,当前时间不用设置 
		// calendar.setTime(new Date()); 
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		map.put("startTime", sdf.format(calendar.getTime()) + " 00:00:00");
		// 设置日期为本月最大日期 
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		map.put("endTime", sdf.format(calendar.getTime()) + " 23:59:59");
		return map;
	}*/
	public String goSearch() {
		return "goSearch";
	}

	public String goSingleList() {
		return GO_SINGLE_LIST;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

	public List<GoodsSaleInformation> getGoodsSaleInformationList() {
		return goodsSaleInformationList;
	}

	public void setGoodsSaleInformationList(List<GoodsSaleInformation> goodsSaleInformationList) {
		this.goodsSaleInformationList = goodsSaleInformationList;
	}

}
