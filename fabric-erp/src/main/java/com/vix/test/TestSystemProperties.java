package com.vix.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestSystemProperties {

	public static void main(String[] args) {
		System.out.println("host ip:" + getHostIp());
	}

	public static InetAddress getInetAddress() {

		try {
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.out.println("unknown host!");
		}
		return null;

	}

	public static String getHostIp() {
		InetAddress netAddress = getInetAddress();
		if (null == netAddress) {
			return null;
		}
		String ip = netAddress.getHostAddress(); // get the ip address
		return ip;
	}

}
