/**
 * 
 */
package com.vix.wechat.oa.signPunch.action;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.util.DaysUtils;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.core.utils.StrUtils;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.oa.attendance.entity.AttendanceRuleSet;
import com.vix.nvix.oa.attendance.entity.PeriodRule;
import com.vix.nvix.oa.attendance.entity.PunchCardRecord;
import com.vix.nvix.oa.attendance.entity.Scheduling;
import com.vix.nvix.oa.constant.PunchCardRecordConstant;
import com.vix.nvix.oa.entity.AttendanceAndClock;
import com.vix.nvix.oa.entity.AttendanceRecordDetail;
import com.vix.nvix.oa.entity.AttendanceRule;
import com.vix.oa.personaloffice.service.IPersonalAttendanceService;
import com.vix.wechat.base.action.WechatBaseAction;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.entity.WxpQyWeixinTicket;

/**
 * @author Bluesnow
 * 2016年6月2日
 * 
 * 签到打卡
 */
@Controller
@Scope("prototype")
public class SignPunchAction extends WechatBaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IPersonalAttendanceService personalAttendanceService;
	private String timestamp; // 必填，生成签名的时间戳
	private String nonceStr; // 必填，生成签名的随机串
	private String signature;// 必填，签名，见附录1
	private String qiyeCorpId;//企业号id
	private String jsapiTicket;
	private String wxId; //微信号
	private String headImg;//头像URL
	private String wxCode;
	private Double times;
	/** 签到时间 */
	private String startTimes;
	/** 签退时间 */
	private String endTimes;
	private String nowTimes;
	private String userId;
	private String attTimes;
	private String id;
	private String longitude;
	private String latitude;
	private String ipAddress;
	private String doAddress;
	private String doOutAddress;
	private Integer isLate;
	private Integer isEarly;
	private AttendanceRecordDetail recordDetail;
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
	private SimpleDateFormat weekDay = new SimpleDateFormat("EEEE");
	//考勤类型
	private List<AttendanceRule> ruleList;
	//考勤
	private PunchCardRecord punchCard;
	private List<PunchCardRecord> punList;
	private AttendanceAndClock attClock;
	private List<AttendanceAndClock> attClockList;
	private List<Scheduling> schedulingList;
	//考勤明细
	private Set<AttendanceRecordDetail> ardSet;
	private List<AttendanceRecordDetail> recordDetailList;
	private List<AttendanceRecordDetail> doAttendanceList;
	private List<AttendanceRecordDetail> doAttendanceOutList;
	@Override
	public String goList(){
		try {
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", api_qiye_corpid);
			//注意这里参数名必须全部小写，且必须有序
			if (site != null) {
				site = saveOrUpdateWxpWeixinSite(site);
				qiyeCorpId = site.getQiyeCorpId();
				WxpQyWeixinTicket wxpWeixinTicket = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinTicket.class, "qiyeCorpId", qiyeCorpId);
				wxpWeixinTicket = saveOrUpdateWxpWeixinTicket(wxpWeixinTicket, site);
				jsapiTicket = wxpWeixinTicket.getQiyeTicket();
				nonceStr = UUID.randomUUID().toString();
				timestamp = Long.toString(System.currentTimeMillis() / 1000);
				StringBuffer url = getRequest().getRequestURL();
				signature = getSign(jsapiTicket, nonceStr, timestamp, url.toString());
				if (StringUtils.isNotEmpty(jsapiTicket) && StringUtils.isNotEmpty(nonceStr) && StringUtils.isNotEmpty(timestamp)) {
					try {
						getSession().setAttribute("jsapi_ticket", jsapiTicket);
						String sign = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
						MessageDigest crypt = MessageDigest.getInstance("SHA-1");
						crypt.reset();
						crypt.update(sign.getBytes("UTF-8"));
						signature = byteToHex(crypt.digest());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			//获取职员编码
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			} else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site,state, code, redirect_ip + "/wechat/signPunchAction!goList.action");
				}
			}
			
			/** 系统当前日期*/
			nowTimes = DateUtil.getCurrentChinaDate() + "-" + weekDay.format(new Date());
			
			/** 是否签到 */
			punList = loadPunchCardRecord();
			if (null != punList && punList.size() >= 1) {
				PunchCardRecord pcr = punList.get(0);
				/** 签到时间 */
				startTimes = (pcr.getPunchCardDate().split(" "))[1];
				isLate = loadLateOrEarly();
				if(StrUtils.objectIsNotNull(pcr.getIpAddress())){
					doAddress = pcr.getIpAddress();
				}
				/** 获取工作时长*/
				if (null != pcr && pcr.getPunchCardDate().length() >= 11) {
					times = Double.parseDouble(String.valueOf((format.parse(format.format(new Date())).getTime() - format.parse(pcr.getPunchCardDate()).getTime())/(60*60*1000)));
				}
			}else {
				times = 0.0;
			}
			
			/** 是否签退 */
			if (null != punList && punList.size() >= 2) {
				PunchCardRecord pcr = punList.get(1);
				/** 签退时间 */
				endTimes = (pcr.getPunchCardDate().split(" "))[1];
				isLate = loadLateOrEarly();
				if(StrUtils.objectIsNotNull(pcr.getIpAddress())){
					doOutAddress = pcr.getIpAddress();
				}
				/** 获取工作时长*/
				if (null != pcr && pcr.getPunchCardDate().length() >= 11) {
					times = Double.parseDouble(String.valueOf((format.parse(punList.get(1).getPunchCardDate()).getTime() - format.parse(punList.get(0).getPunchCardDate()).getTime())/(60*60*1000)));
				}
			}else {
				if(StrUtils.isNotEmpty(startTimes)){
					times = Double.parseDouble(String.valueOf((format.parse(format.format(new Date())).getTime() - format.parse(punList.get(0).getPunchCardDate()).getTime())/(60*60*1000)));
				}else {
					times = 0.0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}
	
	public String getMyAttendance(){
		try {
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", api_qiye_corpid);
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			}else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site,state, code, redirect_ip + "/wechat/signPunchAction!goList.action");
				}
			}
			if(null != userId){
				punList = loadPunchCardRecord();
			}else {
				punList = new ArrayList<PunchCardRecord>();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showMyAttendance";
	}
	
	@SuppressWarnings("static-access")
	public String goMyAttendance(){
		try {
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", api_qiye_corpid);
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			}else {
				String state = getRequestParameter("state");
				String code = getRequestParameter("code");
				if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
					userId = checkWxQyVisitUser(site,state, code, redirect_ip + "/wechat/signPunchAction!goMyAttendance.action");
				}
			}
			
		
			nowTimes = DateUtil.getCurrentChinaDate() + "-" + weekDay.format(new Date());
			
			if(null != userId){
				Employee emp = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				Map <String,Object> params = getParams();
				if(null != emp){
					wxId = emp.getWeixinid();
					headImg = emp.getHeadImgUrl();
					params.put("userCode," + SearchCondition.EQUAL, emp.getCode());
					params.put("recordDate,"+SearchCondition.BETWEENAND, attTimes + " 00:00:00" + "!" + attTimes + " 23:59:59");
					punList = wechatBaseService.findAllDataByConditions(PunchCardRecord.class, params);
					Calendar c = new GregorianCalendar();
					Calendar ca = new GregorianCalendar();
					if(null != punList && punList.size() > 0){
						for(PunchCardRecord pcr : punList){
							if(null != pcr){
								wxCode = wxId;
								//计算工作时长
								if(null != pcr.getRecordDate()){
									c.setTime(pcr.getRecordDate());
									nowTimes = format.format(pcr.getRecordDate()) + "-" + weekDay.format(pcr.getRecordDate());
								}
								
								//查询签到 设置签到时间
								Map<String ,Object> paramsIn = getParams();
								paramsIn.put("attClock.id," + SearchCondition.EQUAL, pcr.getId());
								paramsIn.put("sign," + SearchCondition.EQUAL, "sign");
								doAttendanceList = wechatBaseService.findAllDataByConditions(AttendanceRecordDetail.class, paramsIn);
								if(null != doAttendanceList && doAttendanceList.size() > 0){
									for(AttendanceRecordDetail ard : doAttendanceList){
										ca.setTime(ard.getAttendanceTime());//给Calendar对象设置时间
										SimpleDateFormat s = new SimpleDateFormat("HH:mm");
										startTimes = s.format(ard.getAttendanceTime());
										times = (double) (ca.get(Calendar.HOUR_OF_DAY) - c.get(Calendar.HOUR_OF_DAY));
									}
								}
								
								//查询签退 设置签退时间
								Map<String ,Object> paramsOut = getParams();
								paramsOut.put("attClock.id," + SearchCondition.EQUAL, pcr.getId());
								paramsOut.put("sign," + SearchCondition.EQUAL, "signOut");
								doAttendanceOutList = wechatBaseService.findAllDataByConditions(AttendanceRecordDetail.class, paramsOut);
								if(null != doAttendanceOutList && doAttendanceOutList.size() > 0){
									for(AttendanceRecordDetail ard : doAttendanceOutList){
										ca.setTime(ard.getAttendanceTime());//给Calendar对象设置时间
										SimpleDateFormat s = new SimpleDateFormat("HH:mm");
										endTimes = s.format(ard.getAttendanceTime());
										times = (double) (ca.get(Calendar.HOUR_OF_DAY) - c.get(Calendar.HOUR_OF_DAY));
									}
								}
							}
						}
					}else {
						times = 0.0;
						nowTimes = attTimes;
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myAttendance";
	}
	
	public String doAttendance(){
		try {
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			}
			if(StrUtils.isNotEmpty(userId)){
				Employee emp = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				//设置签到时间
				startTimes = time.format(new Date());
				/** 判断迟到*/
				isLate = loadLateOrEarly();
				/** 判断迟到*/
				//生成签到记录
				if(null != emp){
					PunchCardRecord pcr = new PunchCardRecord();
					pcr.setSource("1");
					if(StrUtils.isNotEmpty(emp.getCode())){
						pcr.setUserCode(emp.getCode());
					}
					pcr.setRecordDate(new Date());
					pcr.setPunchCardDate(format.format(new Date()));
					
					if(StrUtils.isNotEmpty(longitude)){
						pcr.setLongitude(longitude);
					}
					if(StrUtils.isNotEmpty(latitude)){
						pcr.setLatitude(latitude);
					}
					if(StrUtils.isNotEmpty(ipAddress)){
						doAddress = ipAddress;
						pcr.setIpAddress(ipAddress);
					}
					loadCommonData(pcr);
					punchCard = wechatBaseService.mergeOriginal(pcr);
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "doAttendance";
	}
	
	public String doAttendanceOut(){
		try {
			if (getSession().getAttribute("userId") != null && !"".equals(getSession().getAttribute("userId"))) {
				userId = String.valueOf(getSession().getAttribute("userId"));
			}
			if(StrUtils.isNotEmpty(userId)){
				Employee emp = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
				//设置签到时间
				endTimes = time.format(new Date());
				/** 判断早退*/
				isEarly = loadLateOrEarly();
				/** 判断早退*/
				//生成签到记录
				if(null != emp){
					PunchCardRecord pcr = new PunchCardRecord();
					pcr.setSource("1");
					if(StrUtils.isNotEmpty(emp.getCode())){
						pcr.setUserCode(emp.getCode());
					}
					pcr.setRecordDate(new Date());
					pcr.setPunchCardDate(format.format(new Date()));
					if(StrUtils.isNotEmpty(longitude)){
						pcr.setLongitude(longitude);
					}
					if(StrUtils.isNotEmpty(latitude)){
						pcr.setLatitude(latitude);
					}
					if(StrUtils.isNotEmpty(ipAddress)){
						doOutAddress = ipAddress;
						pcr.setIpAddress(ipAddress);
					}
					loadCommonData(pcr);
					punchCard = wechatBaseService.mergeOriginal(pcr);
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "doAttendanceOut";
	}
	
	private List<PunchCardRecord> loadPunchCardRecord() throws Exception {
		Map<String, Object> params = getParams();
		Employee emp = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
		if (null != emp) {
			params.put("userCode," + SearchCondition.EQUAL, emp.getCode());
		}
		params.put("recordDate," + SearchCondition.BETWEENAND,DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
		return personalAttendanceService.findAllTopEntityByCountAndConditions(PunchCardRecord.class, "recordDate", "asc", 2, params);
	}
	
	private Integer loadLateOrEarly() throws Exception{
		Employee emp = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userId);
		if(null != emp && StrUtils.isNotEmpty(emp.getId())){
			//通过职员ID和部门ID拿到对应班次
			Map<String,Object> params = getParams();
			//根据职员查询
			params.put("emp.id," + SearchCondition.EQUAL,emp.getId());
			schedulingList = wechatBaseService.findAllDataByConditions(Scheduling.class, params);
			if(null != schedulingList && schedulingList.size() > 0){
				numeration();
			}else {
				params.clear();
				//根据部门查询
				if(null != emp.getOrganizationUnit() && StrUtils.isNotEmpty(emp.getOrganizationUnit().getId())){
					params.put("unit.id," + SearchCondition.EQUAL, emp.getOrganizationUnit().getId());
				}
				schedulingList = wechatBaseService.findAllDataByConditions(Scheduling.class, params);
				if(null != schedulingList && schedulingList.size() > 0){
					numeration();
				}
			}
		}
		if(StrUtils.objectIsNotNull(isLate)){
			return isLate;
		}else {
			return isEarly;
		}
	}
	
	private Integer numeration() throws Exception{
		Long sTime = 0L;
		Long eTime = 0L;
		for(Scheduling scheduling : schedulingList){
			if(null != scheduling && null != scheduling.getAttRuleSet()){
				//通过班次拿到时间段
				AttendanceRuleSet attRuleSet = scheduling.getAttRuleSet();
				if(null != attRuleSet && null != attRuleSet.getPeriodRule()){
					//工作时间段
					PeriodRule pr = attRuleSet.getPeriodRule();
					//判断工作时间段
					if(StrUtils.isNotEmpty(startTimes)){
						//签到时间
						sTime = format.parse(DateUtil.format(new Date()) + " " + startTimes).getTime();
						if(null != pr && null != pr.getWorkOnTime()){
							//判断允许迟到时长
							if(null != pr.getAllowTheLateTime() && pr.getAllowTheLateTime() > 0){
								//考勤时间
								Long w = format.parse(DateUtil.format(new Date()) + " " + pr.getWorkOnTime()).getTime() + pr.getAllowTheLateTime() * 60 * 1000;
								if(sTime.compareTo(w) >= 0){
									isLate = 1;
								}else {
									isLate = 0;
								}
							}else {
								//考勤时间
								Long w = format.parse(DateUtil.format(new Date()) + " " + pr.getWorkOnTime()).getTime();
								if(sTime.compareTo(w) > 0){
									isLate = 1;
								}else {
									isLate = 0;
								}
							}
							getSession().setAttribute(PunchCardRecordConstant.PCR_ISLATE, isLate);
						}
					}
					if(StrUtils.isNotEmpty(endTimes)){
						//签退时间
						eTime = format.parse(DateUtil.format(new Date()) + " " + endTimes).getTime();
						if(null != pr && null != pr.getWorkOffTime()){
							//判断允许早退时长
							if(null != pr.getAllowedEarlyTime() && pr.getAllowedEarlyTime() > 0){
								//考勤时间
								Long w = format.parse(DateUtil.format(new Date()) + " " + pr.getWorkOffTime()).getTime() - pr.getAllowedEarlyTime() * 60 * 1000;
								if(eTime.compareTo(w) < 0){
									isEarly = 1;
								}else {
									isEarly = 0;
								}
							}else {
								//考勤时间
								Long w = format.parse(DateUtil.format(new Date()) + " " + pr.getWorkOffTime()).getTime();
								if(eTime.compareTo(w) < 0){
									isEarly = 1;
								}else {
									isEarly = 0;
								}
							}
						}
						getSession().setAttribute(PunchCardRecordConstant.PCR_ISEARLY, isEarly);
					}
				}
			}
		}
		if(StrUtils.objectIsNotNull(isLate)){
			return isLate;
		}else {
			return isEarly;
		}
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getQiyeCorpId() {
		return qiyeCorpId;
	}

	public void setQiyeCorpId(String qiyeCorpId) {
		this.qiyeCorpId = qiyeCorpId;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public List<AttendanceRule> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<AttendanceRule> ruleList) {
		this.ruleList = ruleList;
	}

	public List<AttendanceAndClock> getAttClockList() {
		return attClockList;
	}

	public void setAttClockList(List<AttendanceAndClock> attClockList) {
		this.attClockList = attClockList;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Double getTimes() {
		return times;
	}

	public void setTimes(Double times) {
		this.times = times;
	}

	public String getNowTimes() {
		return nowTimes;
	}

	public void setNowTimes(String nowTimes) {
		this.nowTimes = nowTimes;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<AttendanceRecordDetail> getRecordDetailList() {
		return recordDetailList;
	}

	public void setRecordDetailList(List<AttendanceRecordDetail> recordDetailList) {
		this.recordDetailList = recordDetailList;
	}

	public String getStartTimes() {
		return startTimes;
	}

	public void setStartTimes(String startTimes) {
		this.startTimes = startTimes;
	}

	public String getEndTimes() {
		return endTimes;
	}

	public void setEndTimes(String endTimes) {
		this.endTimes = endTimes;
	}

	public AttendanceRecordDetail getRecordDetail() {
		return recordDetail;
	}

	public void setRecordDetail(AttendanceRecordDetail recordDetail) {
		this.recordDetail = recordDetail;
	}

	public AttendanceAndClock getAttClock() {
		return attClock;
	}

	public void setAttClock(AttendanceAndClock attClock) {
		this.attClock = attClock;
	}

	public Set<AttendanceRecordDetail> getArdSet() {
		return ardSet;
	}

	public void setArdSet(Set<AttendanceRecordDetail> ardSet) {
		this.ardSet = ardSet;
	}

	public List<AttendanceRecordDetail> getDoAttendanceList() {
		return doAttendanceList;
	}

	public void setDoAttendanceList(List<AttendanceRecordDetail> doAttendanceList) {
		this.doAttendanceList = doAttendanceList;
	}

	public List<AttendanceRecordDetail> getDoAttendanceOutList() {
		return doAttendanceOutList;
	}

	public void setDoAttendanceOutList(
			List<AttendanceRecordDetail> doAttendanceOutList) {
		this.doAttendanceOutList = doAttendanceOutList;
	}

	public String getAttTimes() {
		return attTimes;
	}

	public void setAttTimes(String attTimes) {
		this.attTimes = attTimes;
	}

	public String getWxCode() {
		return wxCode;
	}

	public void setWxCode(String wxCode) {
		this.wxCode = wxCode;
	}
	
	public int getDoAttendanceListSize() {
		if(null != doAttendanceList){
			return doAttendanceList.size();
		}
		return 0;
	}
	
	public int getDoAttendanceOutListSize() {
		if(null != doAttendanceOutList){
			return doAttendanceOutList.size();
		}
		return 0;
	}

	public PunchCardRecord getPunchCard() {
		return punchCard;
	}

	public void setPunchCard(PunchCardRecord punchCard) {
		this.punchCard = punchCard;
	}

	public List<PunchCardRecord> getPunList() {
		return punList;
	}

	public void setPunList(List<PunchCardRecord> punList) {
		this.punList = punList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getDoAddress() {
		return doAddress;
	}

	public void setDoAddress(String doAddress) {
		this.doAddress = doAddress;
	}

	public String getDoOutAddress() {
		return doOutAddress;
	}

	public void setDoOutAddress(String doOutAddress) {
		this.doOutAddress = doOutAddress;
	}

	public List<Scheduling> getSchedulingList() {
		return schedulingList;
	}

	public void setSchedulingList(List<Scheduling> schedulingList) {
		this.schedulingList = schedulingList;
	}

	public Integer getIsLate() {
		return isLate;
	}

	public void setIsLate(Integer isLate) {
		this.isLate = isLate;
	}

	public Integer getIsEarly() {
		return isEarly;
	}

	public void setIsEarly(Integer isEarly) {
		this.isEarly = isEarly;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
