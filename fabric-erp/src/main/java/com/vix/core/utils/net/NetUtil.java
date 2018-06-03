package com.vix.core.utils.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class NetUtil {

	public static String getMyServerName(){
		String serverName = null;
		InetAddress ip;
		try {

			ip = InetAddress.getLocalHost();
			serverName = ip.getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return serverName;
	}

	public static String getMyServerIp(){
		String serverIp = null;
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			serverIp = ip.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return serverIp;
	}
	
	
	public static String getUserIp(HttpServletRequest request){
		if(null != request){
			return request.getRemoteAddr();
		}
		return null;
	}
}
