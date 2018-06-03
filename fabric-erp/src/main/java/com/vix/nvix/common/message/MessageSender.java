package com.vix.nvix.common.message;

import com.vix.common.share.entity.EcMessageConfig;
import com.vix.core.utils.StrUtils;
import com.vix.nvix.common.message.constant.MessageCompanyConstant;

/**
 * 短信发送工具类
 * 
 * @author Administrator
 *
 */
public class MessageSender {

	public static String sendMessage(EcMessageConfig ecMessageConfig, String phoneList, String msg) throws Exception {
		/** 校验参数 */
		if (null == ecMessageConfig || StrUtils.objectIsNull(ecMessageConfig.getMsgType()) || StrUtils.objectIsNull(phoneList) || StrUtils.objectIsNull(msg)) {
			return null;
		}

		/** 接口数据校验 */
		String url = ecMessageConfig.getMsgUrl();
		String uName = ecMessageConfig.getMsgAccount();
		String pwd = ecMessageConfig.getMsgPassword();
		if (null == url || StrUtils.objectIsNull(url) || null == uName || StrUtils.objectIsNull(uName) || null == pwd || StrUtils.objectIsNull(pwd)) {
			return null;
		}

		StringBuilder phoneBuilder = new StringBuilder();
		for (String phone : phoneList.split(",")) {
			if (StrUtils.objectIsNotNull(phone) && NumberUtil.isNumeric(phone)) {
				if (MessageCompanyConstant.MEI_LIAN_RUAN_TONG.equals(ecMessageConfig.getMsgType())) {
					phoneBuilder.append("86" + phone);
					phoneBuilder.append(",");
				} else {
					phoneBuilder.append(phone);
					phoneBuilder.append(",");
				}
			}
		}

		/** 奥易互通 */
		if (MessageCompanyConstant.AO_YI_HU_TONG.equals(ecMessageConfig.getMsgType())) {
			return AoYiHuTongMessage.sendSms(url, uName, pwd, phoneBuilder.toString(), msg, null);
		}

		/** 创蓝文化 */
		if (MessageCompanyConstant.CHUANG_LAN_WEN_HUA.equals(ecMessageConfig.getMsgType())) {
			return ChuangLanWenHuaMessage.sendSms(url, uName, pwd, phoneBuilder.toString(), msg);
		}

		/** 华信科技 */
		if (MessageCompanyConstant.HUA_XIN_KE_JI.equals(ecMessageConfig.getMsgType())) {
			return HuaXinKeJiMessage.sendSms(url, uName, pwd, phoneBuilder.toString(), msg);
		}
		/** 美联软通 */
		if (MessageCompanyConstant.MEI_LIAN_RUAN_TONG.equals(ecMessageConfig.getMsgType())) {
			return MeiLianRuanTongMessage.sendSms(url, uName, pwd, phoneBuilder.toString(), ecMessageConfig.getApikey(), msg);
		}
		return null;
	}
}