package com.vix.wechat.util;

import com.vix.core.utils.StrUtils;
import com.vix.wechat.entity.WxpTemplateMsgTemplate;
import com.vix.wechat.entity.WxpTemplateMsgTemplateValue;

/**
 * 
 * @类全名 com.vix.diandoc.wechat.util.WxpTemplateMessageUtil
 *
 * @author zhanghaibing
 *
 * @date 2017年4月6日
 */
public class WxpTemplateMessageUtil {
	/**
	 * { "touser":"OPENID",
	 * "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
	 * "url":"http://weixin.qq.com/download", "data":{ "first": {
	 * "value":"恭喜你购买成功！", "color":"#173177" }, "keynote1":{ "value":"巧克力",
	 * "color":"#173177" }, "keynote2": { "value":"39.8元", "color":"#173177" },
	 * "keynote3": { "value":"2014年9月22日", "color":"#173177" }, "remark":{
	 * "value":"欢迎再次购买！", "color":"#173177" } } }
	 * 
	 * @param openId
	 * @param wxpTemplateMsgTemplateValue
	 * @param wxpTemplateMsgTemplate
	 * @return
	 */
	public static String sendTemplateMessageJson(String openId, WxpTemplateMsgTemplateValue wxpTemplateMsgTemplateValue, WxpTemplateMsgTemplate wxpTemplateMsgTemplate) {
		if (StrUtils.objectIsNotNull(openId) && wxpTemplateMsgTemplateValue != null && wxpTemplateMsgTemplate != null) {

			StringBuilder sb = new StringBuilder("");
			sb.append("{\"touser\":\"").append(openId).append("\",");
			sb.append("\"template_id\":\"").append(wxpTemplateMsgTemplate.getTemplateId()).append("\",");
			sb.append("\"url\":\"").append("").append("\",");
			sb.append("\"data\":{");
			if (wxpTemplateMsgTemplate.getCount() > 0) {
				sb.append("\"").append(wxpTemplateMsgTemplate.getContentData1()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueI()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorI()).append("\"");
				sb.append("}");
			}
			if (wxpTemplateMsgTemplate.getCount() > 1) {
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData2()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueII()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorII()).append("\"");
				sb.append("}");
			}
			if (wxpTemplateMsgTemplate.getCount() > 2) {
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData3()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueIII()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorIII()).append("\"");
				sb.append("}");
			}
			if (wxpTemplateMsgTemplate.getCount() > 3) {
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData4()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueIV()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorIV()).append("\"");
				sb.append("}");
			}
			if (wxpTemplateMsgTemplate.getCount() > 4) {
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData5()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueV()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorV()).append("\"");
				sb.append("}");
			}
			if (wxpTemplateMsgTemplate.getCount() > 5) {
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData6()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueVI()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorVI()).append("\"");
				sb.append("}");
			}
			if (wxpTemplateMsgTemplate.getCount() > 6) {
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData7()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueVII()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorVII()).append("\"");
				sb.append("}");
			}
			if (wxpTemplateMsgTemplate.getCount() > 7) {
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData8()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueVIII()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorVIII()).append("\"");
				sb.append("}");
			}
			if (wxpTemplateMsgTemplate.getCount() > 8) {
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData9()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueIX()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorIX()).append("\"");
				sb.append("}");
			}
			if (wxpTemplateMsgTemplate.getCount() > 9) {
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData10()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueX()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorX()).append("\"");
				sb.append("}");
			}
			if (wxpTemplateMsgTemplate.getCount() > 10) {
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData11()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueXI()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorXI()).append("\"");
				sb.append("}");
			}
			if (wxpTemplateMsgTemplate.getCount() > 11) {
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData12()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueXII()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorXII()).append("\"");
				sb.append("}");
			}
			if (wxpTemplateMsgTemplate.getCount() > 12) {
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData13()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueXIII()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorXIII()).append("\"");
				sb.append("}");
			}
			if (wxpTemplateMsgTemplate.getCount() > 13) {
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData13()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueXIV()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorXIV()).append("\"");
				sb.append("}");
			}
			if (wxpTemplateMsgTemplate.getCount() > 14) {
				sb.append(",\"").append(wxpTemplateMsgTemplate.getContentData15()).append("\":{");
				sb.append("\"value\":\"").append(wxpTemplateMsgTemplateValue.getContentDataValueXV()).append("\",");
				sb.append("\"color\":\"").append(wxpTemplateMsgTemplateValue.getContentDataColorXV()).append("\"");
				sb.append("}");
			}
			sb.append("}}");
			return sb.toString();
		}
		return null;
	}
}
