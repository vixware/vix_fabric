package com.vix.nvix.wx.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WxConfigProperty{
	
	/** 加载properties文件，读取用户常量 */
	static {
		Properties properties = new Properties();
		ClassLoader clsLoader = WxConfigProperty.class.getClassLoader();
		InputStream in = clsLoader.getResourceAsStream("/pad_config.properties");
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		wxp_key_upload_base_path = properties.getProperty("wxp_upload_path_base");
		wxp_key_upload_folder_article_image = properties.getProperty("wxp_upload_folder_article_image");
		wxp_key_upload_folder_source_image = properties.getProperty("wxp_upload_folder_source_image");
		wxp_key_upload_folder_goods_image = properties.getProperty("wxp_upload_folder_goods_image");
		wxp_key_upload_folder_member_image = properties.getProperty("wxp_upload_folder_member_image");
		wxp_key_upload_folder_hudong_image = properties.getProperty("wxp_upload_folder_hudong_image");
		wxp_upload_ms_page_image = properties.getProperty("wxp_upload_ms_page_image");
		wxp_upload_ms_page_head_image = properties.getProperty("wxp_upload_ms_page_head_image");
		wxp_upload_ms_coach_head_image = properties.getProperty("wxp_upload_ms_coach_head_image");
		wxp_upload_ms_class_head_image = properties.getProperty("wxp_upload_ms_class_head_image");
		wxp_upload_ms_message_head_image = properties.getProperty("wxp_upload_ms_message_head_image");
		wxp_upload_mge_head_image = properties.getProperty("wxp_upload_mge_head_image");
		wxp_upload_mge_share_image = properties.getProperty("wxp_upload_mge_share_image");
		wxp_upload_mge_teacher_head_image = properties.getProperty("wxp_upload_mge_teacher_head_image");
		wxp_upload_mge_webfreeinfo_head_image = properties.getProperty("wxp_upload_mge_webfreeinfo_head_image");
		wxp_upload_ms_member_check_image = properties.getProperty("wxp_upload_ms_member_check_image");
		
		wxp_upload_red_config_cer = properties.getProperty("wxp_upload_red_config_cer");
		
		wx_odb_member_bar_code = properties.getProperty("wx_odb_member_bar_code");
		wx_odb_member_qr_code = properties.getProperty("wx_odb_member_qr_code");
		wx_odb_member_card_qr_code = properties.getProperty("wx_odb_member_card_qr_code");
		wx_odb_member_card_bar_code = properties.getProperty("wx_odb_member_card_bar_code");
		wx_member_card = properties.getProperty("wx_member_card");
		wx_member_head_image = properties.getProperty("wx_member_head_image");
		art_club_code_image = properties.getProperty("art_club_code_image");
		
		load_server = properties.getProperty("load_server");
		load_member_info_by_mobile = properties.getProperty("load_member_info_by_mobile");
		load_member_info_by_cardcode = properties.getProperty("load_member_info_by_cardcode");
		vix_data_transfer_id = properties.getProperty("vix_data_transfer_id");
		vix_data_transfer_key = properties.getProperty("vix_data_transfer_key");
		wxp_msg_media_file_save_folder = properties.getProperty("wxp_msg_media_file_save_folder");
		wxp_reply_media_file_save_folder = properties.getProperty("wxp_reply_media_file_save_folder");
		Tep_key_Upload_Path_Base = properties.getProperty("Tep_key_Upload_Path_Base");
		Tep_key_Upload_Folder_RiBao = properties.getProperty("Tep_key_Upload_Folder_RiBao");
		Tep_key_Upload_Folder_FanKui = properties.getProperty("Tep_key_Upload_Folder_FanKui");
		Tep_key_Namespace_DiQu = properties.getProperty("Tep_key_Namespace_DiQu");
		Tep_key_Namespace_PianQu = properties.getProperty("Tep_key_Namespace_PianQu");
		Tep_key_Namespace_XueShuZuZhi = properties.getProperty("Tep_key_Namespace_XueShuZuZhi");
		Tep_key_Namespace_ZhuanYe = properties.getProperty("Tep_key_Namespace_ZhuanYe");
		Tep_key_Namespace_ZhuanYeFangXiang = properties.getProperty("Tep_key_Namespace_ZhuanYeFangXiang");
		Tep_key_Namespace_Teacher_Leibie1 = properties.getProperty("Tep_key_Namespace_Teacher_Leibie1");
		
		Tep_key_Namespace_Teacher_Leibie2 = properties.getProperty("Tep_key_Namespace_Teacher_Leibie2");
		Tep_key_Namespace_Book_Leibie1 = properties.getProperty("Tep_key_Namespace_Book_Leibie1");
		Tep_key_Namespace_Book_Leibie2 = properties.getProperty("Tep_key_Namespace_Book_Leibie2");
		Tep_key_Namespace_Book_Leibie3 = properties.getProperty("Tep_key_Namespace_Book_Leibie3");
		
		errorLogPath = properties.getProperty("errorLogPath");
		system_tenantId = properties.getProperty("system_tenantId");
		system_companyCode = properties.getProperty("system_companyCode");
		system_compaylogo = properties.getProperty("system_compaylogo");
		//system_companyName = properties.getProperty("system_companyName");
		system_companyName = "中盛盈创平台";
		system_account = properties.getProperty("system_account");
		system_password = properties.getProperty("system_password");
		defaultRoleCode = properties.getProperty("defaultRoleCode");
		dbType = properties.getProperty("dbType");
		key_qq_map_api = properties.getProperty("key_qq_map_api");
	}
	
	/**
	 * 日志保存路径
	 */
	public static String errorLogPath;
	/**
	 * 中盛公司承租户标识
	 */
	public static String system_tenantId;
	/**
	 * 平台承租户标识
	 */
	public static String system_companyCode;
	/**
	 * 平台承租户名称
	 */
	public static String system_companyName;
	public static String system_compaylogo;
	/**
	 * 平台承租户账号
	 */
	public static String system_account;
	/**
	 * 平台承租户密码
	 */
	public static String system_password;
	/**
	 * 平台承租户角色编码
	 */
	public static String defaultRoleCode;
	/**
	 * 数据库类型
	 */
	public static String dbType;
	
	
	/**
	 * 文件保存路径
	 */
	public static String wxp_key_upload_base_path;
	/**
	 *  文章、图片相关文件夹
	 */
	public static String wxp_key_upload_folder_article_image;
	/**
	 * 素材管理相关文件夹
	 */
	public static String wxp_key_upload_folder_source_image;
	/**
	 * 商品相关文件夹
	 */
	public static String wxp_key_upload_folder_goods_image;
	
	/**
	 * 微现场--签到图片
	 */
	public static String wxp_key_upload_folder_microsite_sign_image;
	/**
	 * 微现场--微信墙图片
	 */
	public static String wxp_key_upload_folder_microsite_appwall_image;
	/**
	 * 微现场--摇一摇图片
	 */
	public static String wxp_key_upload_folder_microsite_shake_image;
	/**
	 * 微现场--现场活动图片
	 */
	public static String wxp_key_upload_folder_microsite_livevent_image;
	/**
	 * 粉丝头像
	 */
	public static String wxp_key_upload_folder_microsite_fan_image;
	/**
	 * 微现场--现场活动图片
	 */
	public static String wxp_key_upload_folder_hudong_sesssage_image;
	/**
	 * 会员相关文件文件夹
	 */
	public static String wxp_key_upload_folder_member_image;
	/**
	 * 互动相关文件夹
	 */
	public static String wxp_key_upload_folder_hudong_image;
	/**
	 * 微团教相关文件夹
	 */
	public static String wxp_upload_mge_head_image;
	public static String wxp_upload_mge_share_image;
	public static String wxp_upload_mge_teacher_head_image;
	public static String wxp_upload_mge_webfreeinfo_head_image;
	/**
	 * 微健身相关文件夹
	 */
	public static String wxp_upload_ms_page_image;
	public static String wxp_upload_ms_page_head_image;
	public static String wxp_upload_ms_coach_head_image;
	public static String wxp_upload_ms_class_head_image;
	public static String wxp_upload_ms_message_head_image;
	public static String wxp_upload_ms_base_shop_head_image;
	public static String wxp_upload_ms_classroom_head_image;
	public static String wxp_upload_ms_consultant_head_image;
	public static String wxp_upload_ms_member_card_head_image;
	public static String wxp_upload_ms_member_check_image;
	/**
	 * 微信红包配置证书
	 */
	public static String wxp_upload_red_config_cer;
	
	/** 会员一维码*/
	public static String wx_odb_member_bar_code;
	/** 会员二维码*/
	public static String wx_odb_member_qr_code;
	public static String wx_odb_member_card_qr_code;
	public static String wx_odb_member_card_bar_code;
	/** 会员卡图片*/
	public static String wx_member_card;
	public static String wx_member_head_image;
	
	public static String art_club_code_image;
	
	public static String wxp_pay_weixin_notify_url;

	public static String wxp_key_namespace_mendian = "ns.wxp.mendian";
	public static String wxp_key_namespace_user_remark_type = "ns.wxp.user.remark.type";

	public static String load_server;
	public static String load_member_info_by_mobile;
	public static String load_member_info_by_cardcode;

	public static String vix_data_transfer_id = "vix_data_transfer_id";
	public static String vix_data_transfer_key = "vix_data_transfer_key";
	
	public static String wxp_msg_media_file_save_folder;
	public static String wxp_reply_media_file_save_folder;
	

	public static String Tep_key_Upload_Path_Base;
	public static String Tep_key_Upload_Folder_RiBao;
	public static String Tep_key_Upload_Folder_FanKui;
	
	public static String Tep_key_Namespace_DiQu;
	public static String Tep_key_Namespace_PianQu;
	public static String Tep_key_Namespace_XueShuZuZhi;

	public static String Tep_key_Namespace_ZhuanYe;
	public static String Tep_key_Namespace_ZhuanYeFangXiang;
	
	public static String Tep_key_Namespace_Teacher_Leibie1;
	public static String Tep_key_Namespace_Teacher_Leibie2;
	
	public static String Tep_key_Namespace_Book_Leibie1;
	public static String Tep_key_Namespace_Book_Leibie2;
	public static String Tep_key_Namespace_Book_Leibie3;
	
	/**
	 * 腾讯地图key
	 */
	public static String key_qq_map_api;
}
