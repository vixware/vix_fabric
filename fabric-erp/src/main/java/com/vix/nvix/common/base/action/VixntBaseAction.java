package com.vix.nvix.common.base.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.drools.util.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.process.vreport.service.FlowTaskService;
import com.vix.common.base.action.BaseAction;
import com.vix.common.message.entity.MessageSendRecord;
import com.vix.common.message.entity.MessageSendTemplate;
import com.vix.common.message.factory.MessageTemplateFactory;
import com.vix.common.org.entity.Organization;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.EcMessageConfig;
import com.vix.common.vixdata.dataappinterface.IDataAccuracy;
import com.vix.core.constant.SearchCondition;
import com.vix.core.flow.activiti.service.IStandardActivitiService;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.kit.HttpKit;
import com.vix.core.utils.kit.PropKit;
import com.vix.core.web.Pager;
import com.vix.drp.accountmanagement.service.IAccountManagementService;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.nvix.common.base.controller.VixntBaseController;
import com.vix.nvix.common.base.service.IVixntBaseService;
import com.vix.nvix.common.base.util.DESUtil;
import com.vix.nvix.common.message.constant.MessageCompanyConstant;
import com.vix.nvix.project.util.JSONFlexUtils;
import com.vix.nvix.schedule.adapter.CalendarsAdapter;
import com.vix.nvix.wx.service.IWxpMenuService;
import com.vix.system.billTypeManagement.entity.BillsProperty;
import com.vix.system.billTypeManagement.service.IBillTypeManagementService;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;
import com.vix.wechat.base.entity.WxpQyPicture;
import com.vix.wechat.base.entity.WxpQySuiteTicket;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.util.WxQyUtil;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassFullName com.vix.nvix.common.base.action.VixntBaseAction
 *
 * @author bjitzhang
 *
 * @date 2016年4月7日
 *
 */
@Controller
@Scope("prototype")
public class VixntBaseAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	public IVixntBaseService vixntBaseService;
	@Autowired
	public IWxpMenuService menuService;
	@Autowired
	public FlowTaskService flowTaskService;
	@Autowired
	public CalendarsAdapter defaultCalendarsAdapter;
	@Autowired
	public IAccountManagementService accountManagementService;
	@Autowired
	public VixntBaseController vixntBaseController;
	@Autowired
	public IStandardActivitiService standardActivitiService;
	@Autowired
	public IBillTypeManagementService billTypeManagementService;
	@Autowired
	public IDataAccuracy dataAccuracy;
	protected File docToUpload;
	protected String docToUploadFileName;
	/** content-type 常量定义 */
	private static final String JSON_TYPE = "application/json";
	public static String redirect_ip;
	public static String ec_url;
	public static String ec_useraccount;
	public static String ec_password;
	public static String ec_tenantid;
	public static String api_qiye_corpid;
	public static String flow_ip;
	/** 查询参数 */
	protected Map<String, Object> params;
	protected SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
	// 页面跳转来源
	protected String source;
	/**
	 * 选择按钮类型
	 */
	private String chooseType;
	private String entityName;
	public Integer pageNo;
	public String id;
	protected Boolean isCheck = false;
	/** 渲染json是否包含rowid */
	private String containRowId;
	public String taskId;
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;

	static {
		redirect_ip = PropKit.use("path_config.properties").get("redirect_ip");
		flow_ip = PropKit.use("application.properties").get("flow_ip");
		api_qiye_corpid = PropKit.use("path_config.properties").get("api_qiye_qiyecorpid");
		ec_url = PropKit.use("path_config.properties").get("ec_url");
		ec_useraccount = PropKit.use("path_config.properties").get("ec_useraccount");
		ec_password = PropKit.use("path_config.properties").get("ec_password");
		ec_tenantid = PropKit.use("path_config.properties").get("ec_tenantid");
	}

	/**
	 * 
	 * @param agentid
	 *            企业应用的id，整型。可在应用的设置页面查看
	 * @param userIds
	 *            成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，
	 *            则向关注该企业应用的全部成员发送
	 * @param title
	 *            标题，不超过128个字节，超过会自动截断
	 * @param description
	 *            描述，不超过512个字节，超过会自动截断
	 * @param id
	 *            新增数据的ID
	 * @param url
	 *            图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。如不填，在客户端不显示图片
	 * @param tenantId
	 *            承租户ID
	 */
	public void sendMessage(String agentid, String userIds, String title, String description, String id, String url, String tenantId) {
		try {
			if (StringUtils.isNotEmpty(redirect_ip)) {
				String surl = "";
				if (StringUtils.isNotEmpty(agentid)) {
					if ("7".equals(agentid)) {
						surl = redirect_ip + "/wechat/conferenceAssistantAction!goApplicationMg.action?id=" + id;
					} else if ("15".equals(agentid)) {
						surl = redirect_ip + "/wechat/identityVerificationAction!goShowNews.action?id=" + id;
					} else if ("2".equals(agentid)) {
						surl = redirect_ip + "/wechat/weChatDynListAction!goShowAnnouncementNotification.action?id=" + id;
					}
				}
				WxpQyWeixinSite site = vixntBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "tenantId", tenantId);
				// 更新企业号token
				if (StringUtils.isNotEmpty(url)) {
					url = redirect_ip + url;
				}
				if (site != null) {
					saveOrUpdateWxpWeixinSite(site);
					WxQyUtil.messageSendNews(agentid, userIds, title, site.getQiyeAccessToken(), description, surl, url);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 发送短信方法
	public String sendMessage(String typeCode, String mobilePhone, Map<String, String> orderStatusMap) {
		String resp = "";
		try {
			EcMessageConfig ecMessageConfig = vixntBaseService.findEntityByAttributeNoTenantId(EcMessageConfig.class, "msgType", MessageCompanyConstant.AO_YI_HU_TONG);
			MessageSendTemplate messageSendTemplate = vixntBaseService.findEntityByAttributeNoTenantId(MessageSendTemplate.class, "messageTemplateType", typeCode);
			if (null != ecMessageConfig && null != messageSendTemplate) {
				resp = MessageTemplateFactory.sendMessage(orderStatusMap, messageSendTemplate, ecMessageConfig, mobilePhone);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	public void saveMessageSendRecord(Integer sendType, String typeCode, String title, String mobilePhone, ChannelDistributor channelDistributor, Employee employee) {
		try {
			if (StringUtils.isNotBlank(typeCode) && StringUtils.isNotBlank(mobilePhone)) {
				MessageSendTemplate messageSendTemplate = vixntBaseService.findEntityByAttributeNoTenantId(MessageSendTemplate.class, "messageTemplateType", typeCode);
				if (messageSendTemplate != null) {
					MessageSendRecord messageSendRecord = new MessageSendRecord();
					messageSendRecord.setChannelDistributor(channelDistributor);
					messageSendRecord.setContent(messageSendTemplate.getMsgContent());
					messageSendRecord.setOperator(employee.getName());
					messageSendRecord.setPostTime(new Date());
					messageSendRecord.setMobilePhone(mobilePhone);
					messageSendRecord.setTitle(title);
					messageSendRecord.setSendType(sendType);
					initEntityBaseController.initEntityBaseAttribute(messageSendRecord);
					messageSendRecord = vixntBaseService.merge(messageSendRecord);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getSourceBaseEntity(List<BaseEntity> baseEntityList, BaseEntity entity) {
		BaseEntity baseEntity = getBaseEntity(entity.getSourceClassName(), entity.getSourceBillCode());
		if (baseEntity != null) {
			baseEntityList.add(baseEntity);
			if (StringUtils.isNotEmpty(baseEntity.getSourceClassName()) && StringUtils.isNotEmpty(baseEntity.getSourceBillCode())) {
				getSourceBaseEntity(baseEntityList, baseEntity);
			}
		}
	}
	public BaseEntity getBaseEntity(String sourceClassName, String sourceBillCode) {
		try {
			return (BaseEntity) vixntBaseService.findEntityByAttributeNoTenantId(Class.forName(sourceClassName), "code", sourceBillCode);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 */
	public void updateWxpWeixinSite() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<WxpQyWeixinSite> wxpWeixinSiteList = vixntBaseService.findAllByConditions(WxpQyWeixinSite.class, params);
			if (wxpWeixinSiteList != null && wxpWeixinSiteList.size() > 0) {
				for (WxpQyWeixinSite wxpWeixinSite : wxpWeixinSiteList) {
					saveOrUpdateWxpWeixinSite(wxpWeixinSite);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getTime(Date s1, Date s2) {
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		java.util.Calendar c2 = java.util.Calendar.getInstance();
		c1.setTime(s1);
		c2.setTime(s2);
		int result = c1.compareTo(c2);
		return result;
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
					initEntityBaseController.initEntityBaseAttribute(wxpWeixinSite);
					wxpWeixinSite = vixntBaseService.merge(wxpWeixinSite);
				}
			}
		}
		return wxpWeixinSite;
	}

	public void updateAccessToken(WxpQyWeixinSite wxpWeixinSite) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		List<WxpQySuiteTicket> wxpSuiteTicketList = vixntBaseService.findAllByConditions(WxpQySuiteTicket.class, params);
		// 倒序
		Collections.sort(wxpSuiteTicketList, new Comparator<WxpQySuiteTicket>() {
			@Override
			public int compare(WxpQySuiteTicket o1, WxpQySuiteTicket o2) {
				return getTime(o2.getCreateTime(), o1.getCreateTime());
			}
		});

		if (wxpSuiteTicketList != null && wxpSuiteTicketList.size() > 0) {
			for (WxpQySuiteTicket wxpSuiteTicket : wxpSuiteTicketList) {
				String suiteAccessToken = null;
				if (wxpSuiteTicket != null) {
					// 通过suite_access_token获取预授权码
					if (wxpSuiteTicket.needReloadSuiteAccessToken()) {
						String request = WxQyUtil.getSuiteToken(wxpSuiteTicket.getSuiteId(), wxpSuiteTicket.getSuiteSecret(), wxpSuiteTicket.getSuiteTicket());
						if (StringUtils.isNotEmpty(request)) {
							JSONObject json = JSONObject.fromObject(request);
							if (json != null) {
								String suite_access_token = "";
								if (json.has("suite_access_token")) {
									suite_access_token = json.getString("suite_access_token");
								}
								Integer expires_in = null;
								if (json.has("expires_in")) {
									expires_in = Integer.parseInt(json.getString("expires_in"));
									long expireTime = System.currentTimeMillis() + 1000L * expires_in;
									wxpSuiteTicket.setSuiteAccessTokenExpireTime(new Date(expireTime));
								}
								wxpSuiteTicket.setSuiteAccessToken(suite_access_token);
								wxpSuiteTicket = vixntBaseService.merge(wxpSuiteTicket);
								suiteAccessToken = wxpSuiteTicket.getSuiteAccessToken();
							}
						}
					} else {
						suiteAccessToken = wxpSuiteTicket.getSuiteAccessToken();
					}
					System.out.println(wxpWeixinSite.getSuiteId() + wxpWeixinSite.getQiyeCorpId() + wxpWeixinSite.getPermanentCode() + suiteAccessToken);
					String request1 = WxQyUtil.getCorpToken(wxpWeixinSite.getSuiteId(), wxpWeixinSite.getQiyeCorpId(), wxpWeixinSite.getPermanentCode(), suiteAccessToken);
					if (StringUtils.isNotEmpty(request1)) {
						System.out.println("getCorpToken:" + request1);
						JSONObject json = JSONObject.fromObject(request1);
						if (json != null) {
							String access_token = "";
							if (json.has("access_token")) {
								access_token = json.getString("access_token");
								wxpWeixinSite.setQiyeAccessToken(access_token);
							}
							Integer expires_in = null;
							if (json.has("expires_in")) {
								expires_in = Integer.parseInt(json.getString("expires_in"));
								long expireTime = System.currentTimeMillis() + 1000L * expires_in;
								wxpWeixinSite.setQiyeTokenExpireTime(new Date(expireTime));
							}
							wxpWeixinSite = vixntBaseService.merge(wxpWeixinSite);
						}
					}
					break;
				}
			}
		}
	}

	/** 将pager对象转换为json */
	@Override
	public void renderDataTable(Pager pager, String[] excludes) throws Exception {
		if (!"1".equals(getContainRowId())) {
			List<String> list = new ArrayList<String>();
			list.add("*.DT_RowId");
			for (String exclude : excludes) {
				if (StrUtils.objectIsNotNull(exclude)) {
					list.add(exclude);
				}
			}
			excludes = list.toArray(new String[list.size()]);
		}

		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("recordsTotal", pager.getTotalCount());
		pageMap.put("recordsFiltered", pager.getTotalCount());
		pageMap.put("data", pager.getResultList());
		String json = JSONFlexUtils.toJsonInclude(pageMap, excludes, "data");
		render(JSON_TYPE, json);
	}

	/** 将pager对象转换为json */
	public void vixRenderDataTable(Pager pager) throws Exception {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("recordsTotal", pager.getTotalCount());
		pageMap.put("recordsFiltered", pager.getTotalCount());
		pageMap.put("data", pager.getResultList());
		String json = JSONFlexUtils.toJsonInclude(pageMap, null, "data");
		render(JSON_TYPE, json);
	}

	/** 将list对象转换为json */
	public void renderNoPagerDataTable(List<? extends Object> list, String[] excludes) throws Exception {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("recordsTotal", list.size());
		pageMap.put("recordsFiltered", list.size());
		pageMap.put("data", list);
		String json = JSONFlexUtils.toJsonInclude(pageMap, excludes, "data");
		render(JSON_TYPE, json);
	}

	/** 将list对象转换为json */
	public void renderNoPagerDataTable(List<? extends Object> list) throws Exception {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("recordsTotal", list.size());
		pageMap.put("recordsFiltered", list.size());
		pageMap.put("data", list);
		String json = JSONFlexUtils.toJsonInclude(pageMap, null, "data");
		render(JSON_TYPE, json);
	}

	/** 将list对象转换为json */
	public void renderDataToJson(List<List<Object>> list, String[] excludes) throws Exception {
		if (null == list) {
			list = new ArrayList<List<Object>>();
		}
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("recordsTotal", list.size());
		pageMap.put("data", list);
		String json = JSONFlexUtils.toJsonInclude(pageMap, excludes, "data");
		render(JSON_TYPE, json);
	}

	/** 将对象转换为json */
	public void renderObjectToJson(Object object, String... excludes) throws Exception {
		String json = JSONFlexUtils.toJsonInclude(object, excludes);
		render(JSON_TYPE, json);
	}

	public String[] saveUploadPic() {
		String[] pathAndName = null;
		try {
			if (null != fileToUpload) {
				/** 上传目录 */
				String saveFolder = this.getUploadFileSavePic();
				@SuppressWarnings("resource")
				BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(fileToUpload));
				String[] fileNames = fileToUploadFileName.split("\\.");
				String extFileName = fileNames[fileNames.length - 1];
				String fileName = fileToUploadFileName.substring(0, fileToUploadFileName.length() - extFileName.length() - 1);

				long newFileMark = System.currentTimeMillis() / 1000;
				String saveFileName = fileName + "_" + newFileMark + "." + extFileName;

				String saveFilePath = saveFolder + "/" + saveFileName;

				BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(saveFilePath));
				byte[] buf = new byte[1024 * 100];
				int len = -1;
				while ((len = bufIn.read(buf)) != -1) {
					bufOut.write(buf, 0, len);
				}
				bufOut.close();

				pathAndName = new String[2];
				pathAndName[0] = saveFolder;
				pathAndName[1] = saveFileName;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return pathAndName;
	}

	@Override
	public String getUploadFileSavePic() {

		String baseFolder = "c:/img";

		String newFilePath = "";

		newFilePath = baseFolder;

		File dir = new File(newFilePath);
		if (!dir.exists())
			dir.mkdirs();

		return newFilePath;
	}

	@Override
	public String[] saveDocUploadPic() {
		String[] pathAndName = null;
		try {
			if (null != docToUpload) {
				/** 上传目录 */
				String saveFolder = this.getUploadFileSavePic();
				@SuppressWarnings("resource")
				BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(docToUpload));
				int size = bufIn.available();
				String[] fileNames = docToUploadFileName.split("\\.");
				String extFileName = fileNames[fileNames.length - 1];
				String fileName = docToUploadFileName.substring(0, docToUploadFileName.length() - extFileName.length() - 1);

				long newFileMark = System.currentTimeMillis() / 1000;
				String saveFileName = fileName + "_" + newFileMark + "." + extFileName;

				String saveFilePath = saveFolder + "/" + saveFileName;

				BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(saveFilePath));
				byte[] buf = new byte[1024 * 100];
				int len = -1;
				while ((len = bufIn.read(buf)) != -1) {
					bufOut.write(buf, 0, len);
				}
				bufOut.close();

				pathAndName = new String[4];
				pathAndName[0] = saveFolder;
				pathAndName[1] = saveFileName;
				if (size > 1024) {
					pathAndName[2] = String.valueOf(Math.ceil(size / 1024) + "K");
				} else {
					pathAndName[2] = String.valueOf(size + " Bytes");
				}
				pathAndName[3] = extFileName;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return pathAndName;
	}

	/**
	 * 判断某一时间是否在一个区间内
	 * 
	 * @param sourceTime
	 *            时间区间,半闭合,如[10:00-20:00) 全闭合,,如[10:00-20:00]
	 * @param curTime
	 *            需要判断的时间 如10:00
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static boolean isInTime(String sourceTime, String curTime) {
		if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
			throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
		}
		if (curTime == null || !curTime.contains(":")) {
			throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
		}
		String[] args = sourceTime.split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			long now = sdf.parse(curTime).getTime();
			long start = sdf.parse(args[0]).getTime();

			long end = sdf.parse(args[1]).getTime();
			if (args[1].equals("00:00:00")) {
				args[1] = "24:00:00";
			}
			if (end < start) {
				if (now > end && now < start) {
					return false;
				} else {
					return true;
				}
			} else {
				if (now >= start && now <= end) {
					return true;
				} else {
					return false;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
		}
	}

	/**
	 * 调用接口
	 * 
	 * @param json
	 * @return
	 */
	public String postToPos(String url, String json, String userAccount, String password) {
		Map<String, String> map = new HashMap<String, String>();
		String auth = userAccount + ":" + password;
		String authHeader = "Basic " + Base64.encodeBase64String(auth.getBytes()).replaceAll("[\\s*\t\n\r]", "");
		map.put("Authorization", authHeader);
		String resp = HttpKit.postJson(url, null, json, map);
		return resp;
	}
	/**
	 * 不需要登录
	 * 
	 * @param url
	 * @param json
	 * @return
	 */
	public String postToPos(String url, String json) {
		String resp = HttpKit.postJson(url, null, json, null);
		return resp;
	}
	/**
	 * 创建自动编码
	 * 
	 * @param billType
	 * @param tenantId
	 * @throws Exception
	 */
	public void createEncodingRulesTableInTheMiddle(String billType, String tenantId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("billType", billType);
		params.put("tenantId", tenantId);
		StringBuilder itemhql = standingBookHqlProvider.findEncodingRulesTableInTheMiddleBillType(params);
		EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle = vixntBaseService.findObjectByHql(itemhql.toString(), params);
		if (encodingRulesTableInTheMiddle == null) {
			encodingRulesTableInTheMiddle = new EncodingRulesTableInTheMiddle();
			initEntityBaseController.initEntityBaseAttribute(encodingRulesTableInTheMiddle);
			encodingRulesTableInTheMiddle.setBillType(billType);
			encodingRulesTableInTheMiddle.setCodeLength(10);
			encodingRulesTableInTheMiddle.setEnableSeries(2);
			encodingRulesTableInTheMiddle.setCodeType("C");
			encodingRulesTableInTheMiddle.setSerialNumberBegin(1l);
			encodingRulesTableInTheMiddle.setSequenceID(billType);
			encodingRulesTableInTheMiddle.setSerialNumberEnd(999999999L);
			encodingRulesTableInTheMiddle.setSerialNumberStep(1);
			vixntBaseService.merge(encodingRulesTableInTheMiddle);
		}
	}

	public static String getBeginDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Date monday = c.getTime();
		String preMonday = sdf.format(monday);
		return preMonday + " " + "00:00:00";
	}

	public static String getEndDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Date monday = c.getTime();
		String preMonday = sdf.format(monday);
		return preMonday + " " + "23:59:59";
	}

	public static Date getLastMonthDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date strDateTo = calendar.getTime();
		return strDateTo;
	}

	public List<Date> getAllTheDateOftheMonth(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);

		int month = cal.get(Calendar.MONTH);
		while (cal.get(Calendar.MONTH) == month) {
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}

	public static Integer getWeek(Date date) {
		// String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return week_index;
	}

	public static String getWeekOfDate(Date date) {

		String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekOfDays[w];

	}

	/**
	 */
	public void saveOrUpdatePicture() {
		try {
			// 保存图片
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				WxpQyPicture wxpQyPicture = new WxpQyPicture();
				wxpQyPicture.setPictureUrl("/img/wechat/" + savePathAndName[1].toString());
				wxpQyPicture.setCreateTime(new Date());
				wxpQyPicture = vixntBaseService.mergeOriginal(wxpQyPicture);
				if (wxpQyPicture != null) {
					renderText(wxpQyPicture.getId() + "," + "/img/wechat/" + savePathAndName[1].toString());
				} else {
					renderText("0:保存失败!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 调用工作流提交表单
	 * 
	 * @param propertyCode
	 * @param entity
	 * @return
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 */
	public String dealStartAndSubmitByBillsCode(String propertyCode, BaseEntity entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("propertyCode," + SearchCondition.EQUAL, propertyCode);
		params.put("tenantId," + SearchCondition.EQUAL, entity.getTenantId());
		List<BillsProperty> billsPropertyList = null;
		try {
			billsPropertyList = vixntBaseService.findAllByConditions(BillsProperty.class, params);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (billsPropertyList != null && billsPropertyList.size() > 0) {
			for (BillsProperty billsProperty : billsPropertyList) {

				String billsPropertyId = billsProperty.getId();
				String billTitle = entity.getName();
				String billId = entity.getId();

				MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
				formData.add("billsPropertyId", billsPropertyId);
				formData.add("billTypeCode", propertyCode);
				try {
					formData.add("billTitle", URLEncoder.encode(billTitle, "utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				formData.add("billId", billId);
				Object objUser = getSession().getAttribute("userInfo");
				UserAccount user = null;
				if (null != objUser && objUser instanceof UserAccount) {
					user = (UserAccount) objUser;
					formData.add("assignee", user.getId());
				}
				String url = flow_ip + "/activiti/deployment/startAndSubmitByBillsCode";
				try {
					return postData(formData, url);
				} catch (Exception e) {
					// e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}

	public void uploadItem(String erpShopCode, String erpShopName, String account, String password) throws Exception {
		JSONObject itemJson = new JSONObject();
		itemJson.put("erpShopCode", erpShopCode);
		itemJson.put("erpShopName", erpShopName);
		itemJson.put("account", account);
		itemJson.put("password", password);
		System.out.println(itemJson);
		String resp = postToPos("http://60.205.152.102/SnowX/rest/erpEcCustomer/erpEcCustomerRegister.rs", itemJson, "bjlhsd", "123");
		if (StringUtils.isNotEmpty(resp)) {
			System.out.println(resp);
		}
	}

	public String postToPos(String url, JSONObject json, String ZF_POS_USERACCOUNT, String ZF_POS_PASSWORD) {
		Map<String, String> map = new HashMap<String, String>();
		String auth = ZF_POS_USERACCOUNT + ":" + ZF_POS_PASSWORD;
		String authHeader = "Basic " + Base64.encodeBase64String(auth.getBytes()).replaceAll("[\\s*\t\n\r]", "");
		map.put("Authorization", authHeader);
		String resp = HttpKit.postJson(url, null, json.toString(), map);
		return resp;
	}
	public static void buildAll() {
		try {
			String url = flow_ip + "/form/businessformtemplate/buildAll";
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.put(url, null);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}

	private String postData(MultiValueMap<String, Object> formData, String url) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Accept-Charset", "utf-8");
		requestHeaders.setContentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(formData, requestHeaders);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class, formData);
		return response.getBody();
	}
	public static Date getYesterDay(Date date) {
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(date);// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
		date = calendar.getTime(); // 得到前一天的时间
		return date;
	}

	public Date getDay(Date date) {
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(date);// 把当前时间赋给日历
		date = calendar.getTime(); // 得到前一天的时间
		return date;
	}

	public String getTenantId() {
		if (SecurityUtil.getCurrentUserTenantId() != null) {
			return SecurityUtil.getCurrentUserTenantId();
		}
		return "";
	}

	public String getCompanyInnerCode() {
		if (SecurityUtil.getCurrentUserAccountCompanyInnerCode() != null) {
			return SecurityUtil.getCurrentUserAccountCompanyInnerCode();
		}
		return "";
	}
	// 拼接时间字符串
	public String jointTimeData(String datetemp, String hourandminutestr) {
		return datetemp + " " + hourandminutestr;
	}

	/**
	 * @return the docToUpload
	 */
	@Override
	public File getDocToUpload() {
		return docToUpload;
	}

	/**
	 * @param docToUpload
	 *            the docToUpload to set
	 */
	@Override
	public void setDocToUpload(File docToUpload) {
		this.docToUpload = docToUpload;
	}

	/**
	 * @return the docToUploadFileName
	 */
	@Override
	public String getDocToUploadFileName() {
		return docToUploadFileName;
	}

	/**
	 * @param docToUploadFileName
	 *            the docToUploadFileName to set
	 */
	@Override
	public void setDocToUploadFileName(String docToUploadFileName) {
		this.docToUploadFileName = docToUploadFileName;
	}

	/**
	 * @return the containRowId
	 */
	public String getContainRowId() {
		return containRowId;
	}

	/**
	 * @param containRowId
	 *            the containRowId to set
	 */
	public void setContainRowId(String containRowId) {
		this.containRowId = containRowId;
	}

	public String getChooseType() {
		return chooseType;
	}

	public void setChooseType(String chooseType) {
		this.chooseType = chooseType;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Boolean getIsCheck() {
		Organization organization = getOrganization();
		if (organization != null) {
			if ("FW".equals(organization.getMatProp())) {
				isCheck = true;
			}
		}
		return isCheck;
	}

	public void setIsCheck(Boolean isCheck) {
		this.isCheck = isCheck;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	public enum ItemType {
		// 利用构造函数传参
		GOODS("goods"), FIXEDASSETS("fixedassets"), MATERIAL("material"), PURCHASEPART("purchasepart"), PURCHASEBACKUPPART("purchasebackuppart"), MACHININGPART("machiningpart"), SEMIFINISHED("semifinished"), ASSEMBLYPART("assemblypart"), FINISHEDGOODS("finishedgoods"), EQUIPMENT("equipment"), OFFICESUPPLY("officesupply"), AFTERSERVICE("afterservice"), MAINTENANCESERVICE("maintenanceservice"), OUTSIDESERVICE("outsideservice");
		// 定义私有变量
		private String nCode;
		// 构造函数，枚举类型只能为私有
		private ItemType(String _nCode) {
			this.nCode = _nCode;
		}
		@Override
		public String toString() {
			return String.valueOf(this.nCode);
		}
	}

	/**
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		// 1.遍历枚举类型
		System.out.println("枚举类型的遍历 ......");
		testTraversalEnum();
		// 2.EnumMap对象的使用
		System.out.println("EnmuMap对象的使用和遍历.....");
		getItemTypeMap();
		// 3.EnmuSet的使用
		System.out.println("EnmuSet对象的使用和遍历.....");
		testEnumSet();
	}

	/**
	 * 
	 * 枚举类型的遍历
	 */

	public static void testTraversalEnum() {
		ItemType[] allItemType = ItemType.values();
		for (ItemType itemType : allItemType) {
			System.out.println("当前类型name：" + itemType.name());
			System.out.println("当前类型ordinal：" + itemType.ordinal());
			System.out.println("当前类型：" + itemType);
		}
	}

	/**
	 * 
	 * 演示EnumMap的使用，EnumMap跟HashMap的使用差不多，只不过key要是枚举类型
	 */

	public static EnumMap<ItemType, String> getItemTypeMap() {
		// 定义EnumMap对象，EnumMap对象的构造函数需要参数传入,默认是key的类的类型
		EnumMap<ItemType, String> currEnumMap = new EnumMap<ItemType, String>(ItemType.class);

		currEnumMap.put(ItemType.GOODS, "流通商品");
		currEnumMap.put(ItemType.FIXEDASSETS, "固定资产");
		currEnumMap.put(ItemType.MATERIAL, "原材料");
		currEnumMap.put(ItemType.PURCHASEPART, "外购零件");
		currEnumMap.put(ItemType.PURCHASEBACKUPPART, "外购备件");
		currEnumMap.put(ItemType.MACHININGPART, "加工部件");
		currEnumMap.put(ItemType.SEMIFINISHED, "半成品");
		currEnumMap.put(ItemType.ASSEMBLYPART, "组装件");
		currEnumMap.put(ItemType.FINISHEDGOODS, "产成品");
		currEnumMap.put(ItemType.EQUIPMENT, "设备 ");
		currEnumMap.put(ItemType.OFFICESUPPLY, "办公用品");
		currEnumMap.put(ItemType.AFTERSERVICE, "售后服务");
		currEnumMap.put(ItemType.MAINTENANCESERVICE, "维修服务");
		currEnumMap.put(ItemType.OUTSIDESERVICE, "外协服务 ");
		// 2.遍历对象
		for (ItemType itemType : ItemType.values()) {
			System.out.println("[key=" + itemType.name() + ",value=" + currEnumMap.get(itemType) + "]");
		}
		return currEnumMap;
	}

	/**
	 * 
	 * EnumSet如何使用，EnumSet是一个抽象类，获取一个类型的枚举类型内容<BR/>
	 * 
	 * 可以使用allOf方法
	 */

	public static void testEnumSet() {
		EnumSet<ItemType> currEnumSet = EnumSet.allOf(ItemType.class);
		for (ItemType aLightSetElement : currEnumSet) {
			System.out.println("当前EnumSet中数据为：" + aLightSetElement);
		}
	}
	/**
	 * 讲数字类型的数据加密
	 * 
	 * @param baseEntity
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws UnsupportedEncodingException
	 */
	public void encryptData(BaseEntity baseEntity) throws InvocationTargetException, IllegalAccessException, UnsupportedEncodingException {
		if (null != baseEntity) {
			Field[] fs1 = baseEntity.getClass().getDeclaredFields();
			for (Field f : fs1) {
				System.out.println(f.getType().getName().toString());
				if (f.getType().getName().toString().equals("java.lang.Double")) {
					if (getFieldValueByName(f.getName(), baseEntity) != null) {
						BeanUtils.setProperty(baseEntity, f.getName(), DESUtil.encrypt(getFieldValueByName(f.getName(), baseEntity)));
					}
				}
			}
		}
	}

	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 *            the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * 根据属性名获取属性值
	 */
	public String getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[]{});
			Object value = method.invoke(o, new Object[]{});
			return value.toString();
		} catch (Exception e) {
			return null;
		}
	}
	
	public String uploadFile(){
		//文件保存目录路径    img_upload是服务器存储上传图片的目录名
        String savePath = getServletContext().getRealPath("/") + "/resources/attached/kindeditor/";
        //文件保存目录URL
        String saveUrl = getRequest().getContextPath() + "/resources/attached/kindeditor/";
        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
        //允许最大上传文件大小
        long maxSize = 5120000;
        //Struts2 请求 包装过滤器
        MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) getRequest();
        //获得上传的文件名
        String fileName = wrapper.getFileNames("imgFile")[0];
        //获得文件过滤器
        File file = wrapper.getFiles("imgFile")[0];
        //得到上传文件的扩展名
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        //获得文件上传的类型
        String dirName = getRequest().getParameter("dir");
        if (dirName == null) {
                dirName = "image";
        }
        if(!extMap.containsKey(dirName)){
                setMessage("目录名不正确。");
        		return UPDATE;
        }
        //创建文件夹
        savePath += dirName + "/";
        saveUrl += dirName + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
        }
        //检查扩展名
        if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                setMessage("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
        		return UPDATE;
        }
        //检查文件大小
        if (file.length() > maxSize) {
                setMessage("上传文件大小超过限制，上传的文件不能超过5M。");
        		return UPDATE;
        } 
        //检查目录
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    String dir = sdf.format(new Date());
        File uploadDir = new File(savePath + dir);
        uploadDir.mkdirs();
        if (!uploadDir.isDirectory()) {
                setMessage("上传目录不存在 。");
        		return UPDATE;
        }
        //检查目录写入权限
        if (!uploadDir.canWrite()) {
                setMessage("上传目录没有写入权限。");
        		return UPDATE;
        }
        //重构上传图片的名称 
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String newImgName = df.format(new Date()) + "_"
                        + new Random().nextInt(1000) + "." + fileExt;
        
        byte[] buffer = new byte[1024];
        //获取文件输出流
        FileOutputStream fos = null;
        //获取内存中当前文件输入流
        InputStream in = null;
        try {
        	 fos = new FileOutputStream(savePath + dir + "/" + newImgName);
        	 in = new FileInputStream(file);
            int num = 0;
            while ((num = in.read(buffer)) > 0) {
                    fos.write(buffer, 0, num);
            }
        } catch (Exception e) {
                e.printStackTrace(System.err);
        } finally {
        	try{
                in.close();
                fos.close();
        	}catch(Exception ex){
        		ex.printStackTrace();
        	}
        }

        //发送给 KE 
        JSONObject obj = new JSONObject();
        obj.put("error", 0);
        obj.put("url", saveUrl + dir + "/" + newImgName);
		setMessage(obj.toString());
		return UPDATE;
	}
	/**
	 * 获取下级人员的id
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getBusinessOrgDetailBo4ReportLineEmployeeIds() throws Exception {
		String employeeids = "";
		Employee e = getEmployee();
		if (e != null) {
			employeeids = e.getId();
			List<Employee> employeeList = vixntBaseService.findBusinessOrgDetailBo4ReportLine(e.getTenantId(), e.getId());
			if (employeeList != null && employeeList.size() > 0) {
				for (Employee employee : employeeList) {
					if (employee != null) {
						if (!e.getId().equals(employee.getId())) {
							employeeids += "," + employee.getId();
						}
					}
				}
			}
		}
		return employeeids;
	}

	/**
	 * 获取上级人员ID
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getHigherLevelBusinessOrgDetailBo4ReportLineEmployeeIds() throws Exception {
		String employeeids = "";
		Employee e = getEmployee();
		if (e != null) {
			employeeids = e.getId();
			List<Employee> employeeList = vixntBaseService.findHigherLevelBusinessOrgDetailBo4ReportLine(e.getTenantId(), e.getId());
			if (employeeList != null && employeeList.size() > 0) {
				for (Employee employee : employeeList) {
					if (employee != null) {
						if (!e.getId().equals(employee.getId())) {
							employeeids += "," + employee.getId();
						}
					}
				}
			}
		}
		return employeeids;
	}
}