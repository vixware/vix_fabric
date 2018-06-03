package com.rest.core.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.common.bo.MessageObject;
import com.vix.common.security.service.IUserAccountService;
import com.vix.core.utils.JSONFlexUtils;
import com.vix.core.utils.JSonUtils;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

/**
 * @ClassName: BaseRestController
 * @Description: Spring MVC REST BASE SERVICE
 * @author wangmingchen
 * @date 2015年2月19日 下午2:36:56
 */
public abstract class BaseRestController {

	@Resource(name = "userAccountService")
	protected IUserAccountService userAccountService;

	protected static final String OPER_SUCCESS = "操作成功!";
	protected static final String OPER_FAIL = "操作失败!";

	protected String NAV_DEFAULT_BATHPATH = "/page/view";

	/**
	 * 
	 * @param htmlPath
	 * @return
	 */
	protected String navViewDefault(String htmlPath) {
		return NAV_DEFAULT_BATHPATH + htmlPath + ".html";
	}

	/**
	 * 
	 * @param response
	 * @param isSuccess
	 * @param message
	 */
	public static void renderResult(HttpServletResponse response, Boolean isSuccess, String message) {
		write2ClientMsg(response, handlerMsg(isSuccess, message));
	}

	public static void renderResult(HttpServletResponse response, Boolean isSuccess, String message, Map<String, String> msgMap) {
		write2ClientMsg(response, handlerMsg(isSuccess, message, msgMap));
	}

	public static void renderResult(HttpServletResponse response, Boolean isSuccess, String message, Object bo) {
		write2ClientMsg(response, handlerMsg(isSuccess, message, bo, null));
	}

	public static void renderResult(HttpServletResponse response, Boolean isSuccess, String message, Object bo, String... includeBoProp) {
		write2ClientMsg(response, handlerMsg(isSuccess, message, bo, null, includeBoProp));
	}

	/**
	 * 
	 * @Title: renderResult
	 * @Description: Rest通用返回数据接口
	 */
	public static void renderResult(HttpServletResponse response, Object bo) {
		write2ClientMsg(response, handlerMsg(true, OPER_SUCCESS, bo, null));
	}

	public static void renderResultStatus(HttpServletResponse response, Boolean isSuccess, Object bo) {
		String msg = isSuccess ? OPER_SUCCESS : OPER_FAIL;
		write2ClientMsg(response, handlerMsg(true, msg, bo, null));
	}

	/**
	 * @Title: renderResult
	 * @Description: 表单验证错误时 使用
	 */
	public static void renderResultNotValid(HttpServletResponse response, Map<String, String> msgMap) {
		write2ClientMsg(response, handlerMsg(false, OPER_FAIL, msgMap));
	}

	/**
	 * 
	 * @Title: renderResult
	 * @Description: Rest通用分页返回数据接口
	 */
	public static void renderResultPager(HttpServletResponse response, Object bo) {
		write2ClientMsg(response, handlerMsg(true, OPER_SUCCESS, bo, null, "resultList"));
	}

	/**
	 * 
	 * @Title: renderResult
	 * @Description: Rest通用返回数据接口
	 */
	public static void renderResult4ListByJackson(HttpServletResponse response, Object bo, Collection<?> boList) {
		write2ClientMsg(response, handlerMsg4ListByJackson(true, OPER_SUCCESS, bo, boList, null));
	}

	public static void renderResultList(HttpServletResponse response, Map<String, String> msgMap, String defaultMsg) {
		MessageObject ms = new MessageObject();
		if (msgMap == null || msgMap.isEmpty()) {
			ms.setSuccess(true);
			if (StringUtils.isEmpty(defaultMsg)) {
				ms.setMessage("操作成功！");
			}
		} else {
			ms.setSuccess(true);
			if (StringUtils.isEmpty(defaultMsg)) {
				ms.setMessage("操作失败！");
			}
		}
		ms.setMsgMap(msgMap);
		write2ClientMsg(response, JSONFlexUtils.toJson(ms));
	}

	private static MessageObject handlerMessageObject(Boolean isSuccess, String bizType, String msg, Object bo, Map<String, String> msgMap) {
		MessageObject ms = new MessageObject();
		ms.setSuccess(isSuccess);
		if (StringUtils.isNotEmpty(bizType)) {
			ms.setBizType(bizType);
		}
		if (isSuccess) {
			if (StringUtils.isEmpty(msg)) {
				msg = "操作成功！";
			}
		} else {
			if (StringUtils.isEmpty(msg)) {
				msg = "操作失败！";
			}
		}
		ms.setMessage(msg);
		ms.setBo(bo);
		if (msgMap != null && !msgMap.isEmpty()) {
			ms.setMsgMap(msgMap);
		}
		return ms;
	}

	private static MessageObject handlerMessageObject4List(Boolean isSuccess, String bizType, String msg, Object bo, Collection<?> boList, Map<String, String> msgMap) {
		MessageObject ms = new MessageObject();
		ms.setBoList(boList);
		ms.setSuccess(isSuccess);
		if (StringUtils.isNotEmpty(bizType)) {
			ms.setBizType(bizType);
		}
		if (isSuccess) {
			if (StringUtils.isEmpty(msg)) {
				msg = "操作成功！";
			}
		} else {
			if (StringUtils.isEmpty(msg)) {
				msg = "操作失败！";
			}
		}
		ms.setMessage(msg);
		ms.setBo(bo);
		if (msgMap != null && !msgMap.isEmpty()) {
			ms.setMsgMap(msgMap);
		}
		return ms;
	}

	/**
	 * 
	 * @param isSuccess
	 * @param msg
	 * @return
	 */
	public static String handlerMsg(Boolean isSuccess, String msg) {
		return handlerMsg4Obj(isSuccess, null, msg, null, null);
	}

	public static String handlerMsg(Boolean isSuccess, String bizType, String msg) {
		return handlerMsg4Obj(isSuccess, bizType, msg, null, null);
	}

	public static String handlerMsg(Boolean isSuccess, String msg, Map<String, String> msgMap) {
		return handlerMsg4Obj(isSuccess, null, msg, null, msgMap);
	}

	public static String handlerMsg(Boolean isSuccess, String msg, Object bo, Map<String, String> msgMap) {
		return handlerMsg4Obj(isSuccess, null, msg, bo, msgMap);
	}

	public static String handlerMsg(Boolean isSuccess, String msg, Object bo, Map<String, String> msgMap, String... includeBoProp) {
		return handlerMsg4Obj(isSuccess, null, msg, bo, msgMap, includeBoProp);
	}

	public static String handlerMsg4ListByJackson(Boolean isSuccess, String msg, Object bo, Collection<?> boList, Map<String, String> msgMap) {
		return handlerMsg4SetByJackson(isSuccess, null, msg, bo, boList, msgMap);
	}

	public static String handlerMsg4Obj(Boolean isSuccess, String bizType, String msg, Object bo, Map<String, String> msgMap) {
		MessageObject ms = handlerMessageObject(isSuccess, bizType, msg, bo, msgMap);
		String json = JSONFlexUtils.toJson(ms);
		return json;
	}

	public static String handlerMsg4SetByJackson(Boolean isSuccess, String bizType, String msg, Object bo, Collection<?> boList, Map<String, String> msgMap) {
		MessageObject ms = handlerMessageObject4List(isSuccess, bizType, msg, bo, boList, msgMap);
		String json = JSonUtils.makeBeanToJson(ms);
		return json;
	}

	public static String handlerMsg4Obj(Boolean isSuccess, String bizType, String msg, Object bo, Map<String, String> msgMap, String... includeBoProp) {
		MessageObject ms = handlerMessageObject(isSuccess, bizType, msg, bo, msgMap);
		if (includeBoProp != null && includeBoProp.length > 0) {
			for (int i = 0; i < includeBoProp.length; i++) {
				includeBoProp[i] = "bo." + includeBoProp[i];
			}
		}
		String json = JSONFlexUtils.toJsonInclude(ms, includeBoProp);
		return json;
	}

	public static void write2ClientMsg(HttpServletResponse response, final String content) {
		// 设置headers参数
		String fullContentType = "text/plain;charset=UTF-8";
		response.setContentType(fullContentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		// 防止servlet输出内容被缓存
		response.setDateHeader("Expires", 0);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.write(content);
				out.flush();
				out.close();
			}
		}
	}

	public <T> String convertListToJson(List<T> dataList, long total, String... exludeExpression) {
		return convertListToJsonEscape(dataList, total, exludeExpression);
	}

	// 带转义字符
	public <T> String convertListToJsonEscape(List<T> dataList, long total, String... exludeExpression) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(total).append(",");
		sb.append("\"rows\":");
		// sb.append(new
		// JSONSerializer().exclude("class").exclude(exludeExpression).serialize(dataList));
		if (dataList != null && !dataList.isEmpty()) {
			sb.append(new JSONSerializer().transform(new DateTransformer("yyyy-MM-dd"), Date.class).exclude("class").exclude("*.class").exclude("*.handler").exclude("*.hibernateLazyInitializer").exclude(exludeExpression).serialize(dataList));
		} else {
			sb.append("[]");
		}
		sb.append("}");
		return sb.toString();
	}

	public <T> String convertListToJsonNoEscape(List<T> dataList, long total, String... exludeExpression) {
		if (dataList == null) {
			dataList = new ArrayList<T>();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", total);
		// jsonMap.put("rows", new
		// JSONSerializer().exclude("class").exclude(exludeExpression).serialize(dataList));
		jsonMap.put("rows", dataList);
		String json = new JSONSerializer().include("rows").transform(new DateTransformer("yyyy-MM-dd"), Date.class).exclude("*.class").exclude(exludeExpression).serialize(jsonMap);
		return json;
	}

	public <T> String convertListToJsonNoEscape(String code, String msg) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("code", code);
		// jsonMap.put("rows", new
		// JSONSerializer().exclude("class").exclude(exludeExpression).serialize(dataList));
		jsonMap.put("msg", msg);
		String json = new JSONSerializer().serialize(jsonMap);
		return json;
	}

	public void writeClientMsg(HttpServletResponse response, final String content) {
		String fullContentType = "application/json;charset=UTF-8";
		response.setContentType(fullContentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				if (StringUtils.isNotEmpty(content)) {
					out.write(content);
				}
				out.flush();
				out.close();
			}
		}
	}
	public String readStreamParameter(ServletInputStream in) {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return buffer.toString();
	}

	/** 解码 */
	public String decode(String str, String enc) throws Exception {
		str = str.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
		str = URLDecoder.decode(str, enc);
		return str;
	}

}
