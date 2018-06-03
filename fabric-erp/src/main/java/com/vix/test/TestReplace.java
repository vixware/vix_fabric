package com.vix.test;

public class TestReplace {

	public static void main(String[] args) {
		String s = "INV_INBOUNDYYYYMM";
		String s1 = "";
		if (s.contains("YYYYMMDD")) {
			s1 = s.replaceAll("YYYYMMDD", getFormatedDate("YYYYMMDD"));
		} else if (s.contains("YYYYMM")) {
			s1 = s.replaceAll("YYYYMM", getFormatedDate("YYYYMM"));
		} else if (s.contains("YYYY")) {
			s1 = s.replaceAll("YYYY", getFormatedDate("YYYY"));
		}
		System.out.println(s1);
	}

	public static String getFormatedDate(String pattern) {
		java.text.SimpleDateFormat sdf = null;
		if ("YYYYMMDD".equals(pattern)) {
			sdf = new java.text.SimpleDateFormat("yyyyMMdd");
		} else if ("YYYYMM".equals(pattern)) {
			sdf = new java.text.SimpleDateFormat("yyyyMM");
		} else if ("YYYY".equals(pattern)) {
			sdf = new java.text.SimpleDateFormat("yyyy");
		}
		java.util.Date now = new java.util.Date();
		return sdf.format(now);
	}
}
