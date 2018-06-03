package com.vix.crm.business.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.LogisticsFollowUpController;
import com.vix.crm.business.entity.MessageLog;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.Shipping;

@Controller
@Scope("prototype")
public class LogisticsFollowUpAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String pageNo;
	private List<ChannelDistributor> channelDistributorList;
	@Autowired
	private LogisticsFollowUpController logisticsFollowUpController;
	private MessageLog messageLog;

	private String shippingId;

	private Integer prolongDays;

	private Shipping shipping;

	@Override
	public String goList() {
		Map<String, Object> params = getParams();
		//获取网店
		params.put("type," + SearchCondition.ANYLIKE, "5");
		try {
			channelDistributorList = logisticsFollowUpController.doListChannelDistributor(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = logisticsFollowUpController.doListShippingPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 短信催付
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 事务跟进
	 * 
	 * @return
	 */
	public String goSaveOrUpdateFollowing() {
		if (StringUtils.isNotEmpty(shippingId)) {
			try {
				shipping = logisticsFollowUpController.doListShipping(shippingId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "goSaveOrUpdateFollowing";
	}

	/**
	 * 保存关怀信息
	 * 
	 * @return
	 */
	public String saveOrUpdateMessage() {
		boolean isSave = true;
		try {
			if (null != messageLog.getId() && !"".equals(messageLog.getId())) {
				isSave = false;
			}
			if (StringUtils.isNotEmpty(shippingId)) {
				shipping = logisticsFollowUpController.doListShipping(shippingId);
				if (shipping != null) {
					messageLog.setOrderCode(shipping.getTradeNo());
					messageLog.setBuyerNick(shipping.getBuyerNick());
				}
			}
			initEntityBaseController.initEntityBaseAttribute(messageLog);
			messageLog = logisticsFollowUpController.doSaveMessageLog(messageLog);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	//延时
	public String saveOrUpdateShipping() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(shippingId)) {
				shipping = logisticsFollowUpController.doListShipping(shippingId);
				if (shipping != null) {
					shipping.setAutomaticValidationTime(getBeforeAfterDate(shipping.getAutomaticValidationTime(), prolongDays));
					shipping = logisticsFollowUpController.doSaveShipping(shipping);
				}
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public MessageLog getMessageLog() {
		return messageLog;
	}

	public void setMessageLog(MessageLog messageLog) {
		this.messageLog = messageLog;
	}

	public String getShippingId() {
		return shippingId;
	}

	public void setShippingId(String shippingId) {
		this.shippingId = shippingId;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

	public Integer getProlongDays() {
		return prolongDays;
	}

	public void setProlongDays(Integer prolongDays) {
		this.prolongDays = prolongDays;
	}

	/**
	 * 
	 * @param datestr
	 *            日期字符串
	 * @param day
	 *            相对天数，为正数表示之后，为负数表示之前
	 * @return 指定日期字符串n天之前或者之后的日期
	 */
	public Date getBeforeAfterDate(Date datestr, Integer day) {
		Calendar cal = new GregorianCalendar();
		if (datestr != null) {

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date olddate = null;
			df.setLenient(false);
			olddate = new Date(datestr.getTime());
			cal.setTime(olddate);

			int Year = cal.get(Calendar.YEAR);
			int Month = cal.get(Calendar.MONTH);
			int Day = cal.get(Calendar.DAY_OF_MONTH);

			int NewDay = Day + day;

			cal.set(Calendar.YEAR, Year);
			cal.set(Calendar.MONTH, Month);
			cal.set(Calendar.DAY_OF_MONTH, NewDay);
		}

		return new java.sql.Date(cal.getTimeInMillis());
	}

}
