/**
 * 
 */
package com.vix.system.remindsCenter.base.task;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vix.common.scheduling.entity.Calendars;
import com.vix.common.share.entity.AlertNotice;
import com.vix.common.share.entity.AlertTask;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.kit.PropKit;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.order.orderProcess.processor.OrderDownloadProcessor;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.service.IVixntBaseService;
import com.vix.oa.adminMg.conference.entity.ApplicationMg;
import com.vix.oa.adminMg.conference.entity.MeetingRequest;
import com.vix.system.remindsCenter.base.adapter.VixAdapter;
import com.vix.system.remindsCenter.base.service.IRemindsCenterService;
import com.vix.system.warningSource.entity.WarningType;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.util.WxQyUtil;

/**
 * 定时任务
 * 
 * @author zhanghaibing
 * 
 * @date 2014-5-22
 */
@Component
public class VixTaskProcess {
	@Autowired
	public IVixntBaseService vixntBaseService;
	@Autowired
	private IRemindsCenterService remindsCenterService;
	@Autowired
	private VixAdapter defaultVixAdapter;
	@Autowired
	private OrderDownloadProcessor orderDownloadProcessor;

	/**
	 * 获取所有待执行的任务列表 根据vix.inventory 具体任务执行类唯一标识 ,任务类型 (type :1,只执行一次,2
	 * 重复执行,==)循环执行任务0 05 10 * * ?*
	 */
	@Scheduled(cron = "0 50 16 * * ?*")
	void doSomethingWith() {
		try {
			List<AlertTask> alertTaskList = remindsCenterService.findRemindsList(AlertTask.class);
			if (alertTaskList != null && alertTaskList.size() > 0) {
				for (AlertTask alertTask : alertTaskList) {
					if (alertTask != null) {
						WarningType warningType = remindsCenterService.findEntityByAttributeNoTenantId(WarningType.class, "id", alertTask.getWarningType().getId());
						if (warningType != null) {
							defaultVixAdapter.toDoTask(warningType.getClassCode());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Scheduled(cron = "0 0/5 * * * ?")
	void downloadOrder() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type," + SearchCondition.ANYLIKE, "5");
		try {
			List<ChannelDistributor> channelDistributorList = remindsCenterService.findRemindsList(ChannelDistributor.class, params);
			if (channelDistributorList != null) {
				for (ChannelDistributor channelDistributor : channelDistributorList) {
					if (channelDistributor != null) {
						orderDownloadProcessor.process(channelDistributor.getId());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 会议室状态定时任务
	 */
	//@Scheduled(cron = "0 0/5 * * * ?")
	void deletes() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("meetingRoomStatus" + SearchCondition.NOEQUAL, 1);
			List<MeetingRequest> meetingRequestList = remindsCenterService.findRemindsList(MeetingRequest.class, params);
			if (meetingRequestList != null) {
				for (MeetingRequest meetingRequest : meetingRequestList) {
					Date date = new Date();
					if (meetingRequest != null && meetingRequest.getAllowedEndTime() != null) {
						if (meetingRequest.getAllowedEndTime().getTime() < date.getTime()) {
						}
						meetingRequest.setMeetingRoomStatus(0);
						remindsCenterService.mergeOriginal(meetingRequest);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 改变任务状态
	 */
	//@Scheduled(cron = "0 0/5 * * * ?")
	void setMessage() {
		try {
			String userIdStr = "";
			Map<String, Object> params = new HashMap<String, Object>();
			List<com.vix.oa.task.taskDefinition.entity.VixTask> vixTaskList = vixntBaseService.findAllDataByConditions(com.vix.oa.task.taskDefinition.entity.VixTask.class, params);
			if (vixTaskList != null) {
				for (com.vix.oa.task.taskDefinition.entity.VixTask vixTask : vixTaskList) {
					vixTask = remindsCenterService.findEntityById(com.vix.oa.task.taskDefinition.entity.VixTask.class, vixTask.getId());
					Date date = new Date();
					if (vixTask != null && vixTask.getTaskEndTime() != null) {
						if (vixTask.getTaskStartTime().getTime() < date.getTime()) {
							if (vixTask.getEmployees() != null && vixTask.getEmployees().size() > 0) {
								for (Employee employee : vixTask.getEmployees()) {
									userIdStr += "|" + employee.getUserId();
								}
							}
							String redirect_ip = PropKit.use("path_config.properties").get("redirect_ip");
							WxpQyWeixinSite site = remindsCenterService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "tenantId", vixTask.getTenantId());
							if (StringUtils.isNotEmpty(userIdStr)) { // 更新企业号token
								saveOrUpdateWxpWeixinSite(site);
								WxQyUtil.messageSendNews("3", userIdStr, vixTask.getQuestName(), site.getQiyeAccessToken(), vixTask.getTaskDescription(), redirect_ip + "/wechat/weChatTaskPlanAction!goMyTask.action?id=" + vixTask.getId() + "&corpid=" + site.getCorpId(), "");
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WxpQyWeixinSite saveOrUpdateWxpWeixinSite(WxpQyWeixinSite wxpWeixinSite) throws Exception {
		if (wxpWeixinSite != null && wxpWeixinSite.needReloadQiyeAccessToken()) {
			if (StringUtils.isNotEmpty(wxpWeixinSite.getQiyeCorpId()) && StringUtils.isNotEmpty(wxpWeixinSite.getQiyeSecret())) {
				Map<?, ?> dataMap = WxQyUtil.reloadAccessToken(wxpWeixinSite.getQiyeCorpId(), wxpWeixinSite.getQiyeSecret());
				if (dataMap != null) {
					if (dataMap.containsKey("access_token")) {
						String newAccessToken = (String) dataMap.get("access_token");
						wxpWeixinSite.setQiyeAccessToken(newAccessToken);
					}
					if (dataMap.containsKey("expires_in")) {
						Integer expireSec = (Integer) dataMap.get("expires_in");
						long expireTime = System.currentTimeMillis() + 1000L * expireSec;
						wxpWeixinSite.setQiyeTokenExpireTime(new Date(expireTime));
					}
					wxpWeixinSite = remindsCenterService.merge(wxpWeixinSite);
				}
			}
		}
		return wxpWeixinSite;
	}

	/**
	 * 会议申请状态定时任务
	 */
	//@Scheduled(cron = "0 0/5 * * * ?")
	void deletess() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("bookingSituation" + SearchCondition.NOEQUAL, 1);
			List<ApplicationMg> applicationMgList = remindsCenterService.findRemindsList(ApplicationMg.class, params);
			if (applicationMgList != null) {
				for (ApplicationMg applicationMg : applicationMgList) {
					Date date = new Date();
					if (applicationMg != null && applicationMg.getMeetingEndTime() != null) {
						if (applicationMg.getMeetingEndTime().getTime() < date.getTime()) {
							applicationMg.setBookingSituation(1);
							remindsCenterService.mergeOriginal(applicationMg);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建提醒数据
	 */
	@Scheduled(cron = "0 0/5 * * * ?")
	void calendarsNotice() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isHasRemind," + SearchCondition.NOEQUAL, "2");
			List<Calendars> calendarsList = remindsCenterService.findRemindsList(Calendars.class, params);
			if (calendarsList != null) {
				for (Calendars calendars : calendarsList) {
					if (calendars != null && "2".equals(calendars.getIsRemind())) {
						AlertNotice alertNotice = new AlertNotice();
						alertNotice.setSubject("日程安排");
						alertNotice.setBoCode(calendars.getScheduleName());
						alertNotice.setContent(calendars.getCalendarsContent());
						alertNotice.setRemindTime(new Date());
						alertNotice.setTime(new Date());
						alertNotice.setType("schedule");
						alertNotice.setTenantId(calendars.getTenantId());
						alertNotice.setCompanyCode(calendars.getCompanyCode());
						alertNotice.setCompanyInnerCode(calendars.getCompanyInnerCode());
						alertNotice.setCreator(calendars.getCreator());
						alertNotice.setCreateTime(new Date());
						alertNotice.setStatus("1");
						remindsCenterService.mergeOriginal(alertNotice);
						// 如果设置了不重复,提醒完后改日程安排的状态为已提醒
						if ("0".equals(calendars.getIsRepeat())) {
							calendars.setIsHasRemind("2");
							remindsCenterService.mergeOriginal(calendars);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Scheduled(cron = "0 0/5 * * * ?")
	void delete() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status," + SearchCondition.NOEQUAL, "2");
			List<AlertNotice> alertNoticeList = remindsCenterService.findRemindsList(AlertNotice.class, params);
			if (alertNoticeList != null) {
				for (AlertNotice alertNotice : alertNoticeList) {
					if (daysBetween(alertNotice.getStartTime(), new Date()) >= 24) {
						alertNotice.setStatus("2");
						remindsCenterService.mergeOriginal(alertNotice);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int daysBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600);
		return Integer.parseInt(String.valueOf(between_days));
	}
}
