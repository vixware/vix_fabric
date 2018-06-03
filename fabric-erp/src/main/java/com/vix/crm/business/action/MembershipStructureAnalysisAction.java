package com.vix.crm.business.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.vix.crm.business.hql.PurchasingBehaviorHqlProvider;
import com.vix.crm.business.service.IPurchasingBehaviorService;
import com.vix.crm.business.vo.CombinedSalesGoods;
import com.vix.crm.business.vo.CustomerAnalysis;
import com.vix.crm.business.vo.IdAndTitleVo;
import com.vix.sales.order.entity.SaleOrderItem;

@Controller
@Scope("prototype")
public class MembershipStructureAnalysisAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Resource(name = "purchasingBehaviorHqlProvider")
	private PurchasingBehaviorHqlProvider purchasingBehaviorHqlProvider;
	@Autowired
	private IPurchasingBehaviorService purchasingBehaviorService;
	@Autowired
	private IDataAccuracy dataAccuracy;
	private List<CombinedSalesGoods> combinedSalesGoodsList;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	@Autowired
	public String goList() {
		try {
			Map<String, Object> p = new HashMap<String, Object>();
			//通过条件获取相应数据
			String dateType = getRequestParameter("dateType");
			//获取某一天的数据
			String date = getRequestParameter("date");
			if (dateType != null && !"".equals(dateType)) {
				p.putAll(getDateMap(dateType, date));
			} else if (date != null && !"".equals(date)) {
				p.putAll(getDate(date));
			} else {
				//默认获取本周的数据
				p.putAll(getWeekDay());
			}
			//通过条件获取相应数据

			StringBuilder hql = purchasingBehaviorHqlProvider.findIdAndTitleVoList(p);
			List<IdAndTitleVo> idAndTitleVoList = purchasingBehaviorService.findIdAndTitleVoList(hql.toString());
			if (idAndTitleVoList != null && idAndTitleVoList.size() > 0) {
				combinedSalesGoodsList = new ArrayList<CombinedSalesGoods>();
				for (IdAndTitleVo idAndTitleVo : idAndTitleVoList) {
					if (idAndTitleVo != null) {
						String[] titles = idAndTitleVo.getTitles().split(",");
						if (titles.length == 2) {
							CombinedSalesGoods combinedSalesGoods = new CombinedSalesGoods();
							if (titles[0] != null) {
								combinedSalesGoods.setAgoodsName(titles[0]);
								List<SaleOrderItem> saleOrderItemList = purchasingBehaviorService.findAllByEntityClassAndAttribute(SaleOrderItem.class, "title", titles[0]);
								combinedSalesGoods.setApeoplenumber(Double.valueOf(saleOrderItemList.size()));
							}
							String[] ids = idAndTitleVo.getIds().split(",");
							combinedSalesGoods.setBgoodsName(titles[1]);
							combinedSalesGoods.setBpeoplenumber(Double.valueOf(ids.length));
							combinedSalesGoods.setPurchaseJointRate(Double.valueOf(dataAccuracy.getAmountDecimal(combinedSalesGoods.getBpeoplenumber() / combinedSalesGoods.getApeoplenumber() * 100)));
							combinedSalesGoodsList.add(combinedSalesGoods);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	// 活跃客户
	public void goShowData() {
		try {
			String str = "";
			Map<String, Object> p = new HashMap<String, Object>();
			//通过条件获取相应数据
			String dateType = getRequestParameter("dateType");
			//获取某一天的数据
			String date = getRequestParameter("date");
			if (dateType != null && !"".equals(dateType)) {
				p.putAll(getDateMap(dateType, date));
			} else if (date != null && !"".equals(date)) {
				p.putAll(getDate(date));
			} else {
				//默认获取本周的数据
				p.putAll(getThreeMonths());
			}
			//通过条件获取相应数据
			StringBuilder hql = purchasingBehaviorHqlProvider.findCustomerAnalysis(p);
			CustomerAnalysis customerAnalysis = purchasingBehaviorService.findCustomerAnalysis(hql.toString());
			if (customerAnalysis != null) {
				if (customerAnalysis.getCustomerNumber() != null) {
					str += "客户数:" + customerAnalysis.getCustomerNumber() + "\n";
				} else {
					str += "客户数:0" + "\n";
				}

				if (customerAnalysis.getAmount() != null) {
					str += "成交额:" + customerAnalysis.getAmount() + "\n";
				} else {
					str += "成交额:0" + "\n";
				}

				if (customerAnalysis.getAverageAmount() != null) {
					str += "人均成交额:" + customerAnalysis.getAverageAmount();
				} else {
					str += "人均成交额:0";
				}
			}
			if (!"".equals(str)) {
				renderText(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//购买多次
	public void goBuyMore() {
		try {
			String str = "";
			Map<String, Object> p = new HashMap<String, Object>();
			//通过条件获取相应数据
			String dateType = getRequestParameter("dateType");
			//获取某一天的数据
			String date = getRequestParameter("date");
			if (dateType != null && !"".equals(dateType)) {
				p.putAll(getDateMap(dateType, date));
			} else if (date != null && !"".equals(date)) {
				p.putAll(getDate(date));
			} else {
				//默认获取本周的数据
				p.putAll(getThreeMonths());
			}
			//通过条件获取相应数据
			StringBuilder hql = purchasingBehaviorHqlProvider.findBuyMore(p);
			CustomerAnalysis customerAnalysis = purchasingBehaviorService.findCustomerAnalysis(hql.toString());
			if (customerAnalysis != null) {
				if (customerAnalysis.getCustomerNumber() != null) {
					str += "客户数:" + customerAnalysis.getCustomerNumber() + "\n";
				} else {
					str += "客户数:0" + "\n";
				}

				if (customerAnalysis.getAmount() != null) {
					str += "成交额:" + customerAnalysis.getAmount() + "\n";
				} else {
					str += "成交额:0" + "\n";
				}

				if (customerAnalysis.getAverageAmount() != null) {
					str += "人均成交额:" + customerAnalysis.getAverageAmount();
				} else {
					str += "人均成交额:0";
				}
			}
			if (!"".equals(str)) {
				renderText(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//购买一次
	public void goBuyOne() {
		try {
			String str = "";
			Map<String, Object> p = new HashMap<String, Object>();
			//通过条件获取相应数据
			String dateType = getRequestParameter("dateType");
			//获取某一天的数据
			String date = getRequestParameter("date");
			if (dateType != null && !"".equals(dateType)) {
				p.putAll(getDateMap(dateType, date));
			} else if (date != null && !"".equals(date)) {
				p.putAll(getDate(date));
			} else {
				//默认获取本周的数据
				p.putAll(getThreeMonths());
			}
			//通过条件获取相应数据
			StringBuilder hql = purchasingBehaviorHqlProvider.findBuyOne(p);
			CustomerAnalysis customerAnalysis = purchasingBehaviorService.findCustomerAnalysis(hql.toString());
			if (customerAnalysis != null) {
				if (customerAnalysis.getCustomerNumber() != null) {
					str += "客户数:" + customerAnalysis.getCustomerNumber() + "\n";
				} else {
					str += "客户数:0" + "\n";
				}

				if (customerAnalysis.getAmount() != null) {
					str += "成交额:" + customerAnalysis.getAmount() + "\n";
				} else {
					str += "成交额:0" + "\n";
				}

				if (customerAnalysis.getAverageAmount() != null) {
					str += "人均成交额:" + customerAnalysis.getAverageAmount();
				} else {
					str += "人均成交额:0";
				}
			}
			if (!"".equals(str)) {
				renderText(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//新客户
	public void goNewCustomerData() {
		try {
			String str = "";
			Map<String, Object> p = new HashMap<String, Object>();
			//通过条件获取相应数据
			String dateType = getRequestParameter("dateType");
			//获取某一天的数据
			String date = getRequestParameter("date");
			if (dateType != null && !"".equals(dateType)) {
				p.putAll(getDateMap(dateType, date));
			} else if (date != null && !"".equals(date)) {
				p.putAll(getDate(date));
			} else {
				p.putAll(getOneMonths());
			}
			//通过条件获取相应数据
			StringBuilder hql = purchasingBehaviorHqlProvider.findNewCustomerAnalysis(p);
			CustomerAnalysis customerAnalysis = purchasingBehaviorService.findCustomerAnalysis(hql.toString());
			if (customerAnalysis != null) {
				if (customerAnalysis.getCustomerNumber() != null) {
					str += "客户数:" + customerAnalysis.getCustomerNumber() + "\n";
				} else {
					str += "客户数:0" + "\n";
				}

				if (customerAnalysis.getAmount() != null) {
					str += "成交额:" + customerAnalysis.getAmount() + "\n";
				} else {
					str += "成交额:0" + "\n";
				}

				if (customerAnalysis.getAverageAmount() != null) {
					str += "人均成交额:" + customerAnalysis.getAverageAmount();
				} else {
					str += "人均成交额:0";
				}
			}
			if (!"".equals(str)) {
				renderText(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//老客户
	public void goOldCustomerData() {
		try {
			String str = "";
			Map<String, Object> p = new HashMap<String, Object>();
			//通过条件获取相应数据
			String dateType = getRequestParameter("dateType");
			//获取某一天的数据
			String date = getRequestParameter("date");
			if (dateType != null && !"".equals(dateType)) {
				p.putAll(getDateMap(dateType, date));
			} else if (date != null && !"".equals(date)) {
				p.putAll(getDate(date));
			} else {
				p.putAll(getThreeMonths());
			}
			//通过条件获取相应数据
			StringBuilder hql = purchasingBehaviorHqlProvider.findOldCustomerAnalysis(p);
			CustomerAnalysis customerAnalysis = purchasingBehaviorService.findCustomerAnalysis(hql.toString());
			if (customerAnalysis != null) {
				if (customerAnalysis.getCustomerNumber() != null) {
					str += "客户数:" + customerAnalysis.getCustomerNumber() + "\n";
				} else {
					str += "客户数:0" + "\n";
				}

				if (customerAnalysis.getAmount() != null) {
					str += "成交额:" + customerAnalysis.getAmount() + "\n";
				} else {
					str += "成交额:0" + "\n";
				}

				if (customerAnalysis.getAverageAmount() != null) {
					str += "人均成交额:" + customerAnalysis.getAverageAmount();
				} else {
					str += "人均成交额:0";
				}
			}
			if (!"".equals(str)) {
				renderText(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> getDateMap(String dateType, String date) {
		Map<String, Object> params = new HashMap<String, Object>();
		if ("d".equals(dateType)) {
			params = getDate(date);
		} else if ("w".equals(dateType)) {
			params = getWeekDay();
		} else if ("m".equals(dateType)) {
			params = getMonthDate();
		}
		return params;
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
	}

	/**
	 * 获取3个月的时间段
	 * 
	 * @return
	 */
	public Map<String, Object> getThreeMonths() {
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -3);
		map.put("startTime", sdf.format(cal.getTime()) + " 00:00:00");
		map.put("endTime", sdf.format(new Date()) + " 23:59:59");
		return map;
	}

	public Map<String, Object> getOneMonths() {
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		map.put("startTime", sdf.format(cal.getTime()) + " 00:00:00");
		map.put("endTime", sdf.format(new Date()) + " 23:59:59");
		return map;
	}

	public Map<String, Object> getTwoMonths() {
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -3);
		map.put("startTime", sdf.format(cal.getTime()) + " 00:00:00");

		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.MONTH, -1);
		map.put("endTime", sdf.format(cal1.getTime()) + " 23:59:59");
		return map;
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

	public String goSearch() {
		return "goSearch";
	}

	public List<CombinedSalesGoods> getCombinedSalesGoodsList() {
		return combinedSalesGoodsList;
	}

	public void setCombinedSalesGoodsList(List<CombinedSalesGoods> combinedSalesGoodsList) {
		this.combinedSalesGoodsList = combinedSalesGoodsList;
	}

}
