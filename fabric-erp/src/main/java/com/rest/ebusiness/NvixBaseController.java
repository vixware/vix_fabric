package com.rest.ebusiness;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.rest.core.base.BaseRestController;
import com.vix.restService.test.submodule.service.ISubmoduleRestService;

/**
 * 
 * @类全名 com.rest.ebusiness.NvixBaseController
 *
 * @author zhanghaibing
 *
 * @date 2016年9月23日
 */

public class NvixBaseController extends BaseRestController {
	@Autowired
	public ISubmoduleRestService submoduleRestService;

	@Override
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
	@Override
	public String decode(String str, String enc) throws Exception {
		str = URLDecoder.decode(str, enc);
		return str;
	}

	@Override
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
}
