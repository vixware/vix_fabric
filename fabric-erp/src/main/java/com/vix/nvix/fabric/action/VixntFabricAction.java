package com.vix.nvix.fabric.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.fabric.entity.MockupUsers;

import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class VixntFabricAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String username;
	private String orgName;
	private String password;
	private final static String url = "http://192.168.0.156:4000/login";
	public void login() {
		JSONObject json = new JSONObject();
		json.put("username", username);
		json.put("orgName", orgName);
		json.put("password", password);
		System.out.println(json);
		String resp = postToPos(url, json.toString());
		if (StringUtils.isNotEmpty(resp)) {
			System.out.println(resp);
			JSONObject returnjson = JSONObject.fromObject(resp);
			String returnValue = returnjson.getString("success");
			if (returnjson.containsKey("success") && "true".equals(returnValue)) {
				if (returnjson.containsKey("token")) {
					System.out.println(returnjson.getString("token"));
					try {
						MockupUsers mockupUsers = vixntBaseService.findEntityByAttribute(MockupUsers.class, "userName", username);
						if (mockupUsers != null) {
							getSession().setAttribute("cmId", mockupUsers.getCmId());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					getSession().setAttribute("token", returnjson.getString("token"));
				}
				renderText("登录成功!");
			}
		}
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * @param orgName
	 *            the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}