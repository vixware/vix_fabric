package com.vix.common.message.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 短信模板参数
 * 
 * @类全名 com.vix.common.message.constant.MessageTemplateConstant
 *
 * @author yhl
 *
 * @date 2017年3月8日
 */
public class MessageTemplateConstant {
	/** 姓名  */
	public final static String username = "username";
	/** 性别  */
	public final static String sex = "sex";
	/** 卡号  */
	public final static String cardNo = "cardNo";
	/** 密码  */
	public final static String password = "password";
	/** 可用积分  */
	public final static String integral = "integral";
	/** 可用储值  */
	public final static String amount = "amount";
	/** 公司名称  */
	public final static String companyName = "companyName";
	/** 联系电话 */
	public final static String servicephone = "servicephone";
	/** 主题 */
	public final static String title = "title";
	/** 内容 */
	public final static String commentcontent = "commentcontent";

	private static Map<String, String> orderStatusMap = new LinkedHashMap<String, String>();

	public static Map<String, String> getTypeMap() {
		if (orderStatusMap.size() <= 0) {
			orderStatusMap.put(MessageTemplateConstant.username, "username");
			orderStatusMap.put(MessageTemplateConstant.sex, "sex");
			orderStatusMap.put(MessageTemplateConstant.cardNo, "cardNo");
			orderStatusMap.put(MessageTemplateConstant.password, "password");
			orderStatusMap.put(MessageTemplateConstant.integral, "integral");
			orderStatusMap.put(MessageTemplateConstant.amount, "amount");
			orderStatusMap.put(MessageTemplateConstant.companyName, "companyName");
			orderStatusMap.put(MessageTemplateConstant.servicephone, "servicephone");
			orderStatusMap.put(MessageTemplateConstant.title, "title");
			orderStatusMap.put(MessageTemplateConstant.commentcontent, "commentcontent");
		}
		return orderStatusMap;
	}
}
