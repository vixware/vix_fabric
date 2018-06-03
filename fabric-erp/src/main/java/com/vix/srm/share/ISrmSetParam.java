package com.vix.srm.share;

/**
 * 供应商设置参数主键定义对照
 * 
 * @author Ivan 2013-06-24
 */
public interface ISrmSetParam {
	/** 注册资金 */
	public static final String SRM_REGISTERED_CAPITAL = "SRM_REGISTERED_CAPITAL";
	/** 是否允许在线投标 */
	public static final String SRM_WHETHER_TOALLOW_ONLINE_BIDDING = "SRM_WHETHER_TOALLOW_ONLINE_BIDDING";
	/** 是否允许在线注册 */
	public static final String SRM_WHETHER_TO_ALLOW_ONLINE_REGISTRATION = "SRM_WHETHER_TO_ALLOW_ONLINE_REGISTRATION";
	/** 是否需要流程审批 */
	public static final String SRM_NEED_APPROVAL_PROCESS = "SRM_NEED_APPROVAL_PROCESS";
	/** 是否允许供应商管理库存VMI */
	public static final String SRM_ALLOWS_VENDOR_MANAGED_INVENTORY = "SRM_ALLOWS_VENDOR_MANAGED_INVENTORY";
	/** 是否要求ISO认证 */
	public static final String SRM_ISO_CERTIFICATION = "SRM_ISO_CERTIFICATION";
	/** 是否要求CMMI认证 */
	public static final String SRM_CMMI_CERTIFICATION = "SRM_CMMI_CERTIFICATION";
	/** 资质到期提醒 */
	public static final String SRM_QUALIFICATION_EXPIRATION_REMINDER = "SRM_QUALIFICATION_EXPIRATION_REMINDER";
	/** 流程审批提醒 */
	public static final String SRM_APPROVAL_PROCESS_TO_REMIND = "SRM_APPROVAL_PROCESS_TO_REMIND";
	/** 参与投标公司数量 */
	public static final String SRM_NUMBER_OF_BIDDERS = "SRM_NUMBER_OF_BIDDERS";
	/** 招标项目时间 */
	public static final String SRM_PROJECT_TIME = "SRM_PROJECT_TIME";
	/** 监控刷新时间间隔 */
	public static final String SRM_MONITOR_REFRESH_INTERVAL = "SRM_MONITOR_REFRESH_INTERVAL";
	/** 是否发送通知邮件 */
	public static final String SRM_WHETHER_TO_SEND_NOTIFICATION_EMAIL = "SRM_WHETHER_TO_SEND_NOTIFICATION_EMAIL";
	/** 是否发送通知短信 */
	public static final String SRM_WHETHER_TO_SEND_NOTIFICATION_MESSAGES = "SRM_WHETHER_TO_SEND_NOTIFICATION_MESSAGES";

}
