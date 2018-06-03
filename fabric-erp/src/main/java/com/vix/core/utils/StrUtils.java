package com.vix.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.ParserException;

/**
 * 字符串的帮助类，提供静态方法，不可以实例化。
 * 
 * @author arron
 * 
 */
public class StrUtils {
	

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
        "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
        "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
        "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
        "W", "X", "Y", "Z" };  

	public static List<String> splitStr(String oriStr, String septChar)
	{
		if(StrUtils.isEmpty(oriStr))
			return null;
		if(StrUtils.isEmpty(septChar)){
			List list = new ArrayList<String>();
			list.add(oriStr);
			return list;
		}
		
		List<String> list = new ArrayList<String>();
		int idx = oriStr.indexOf(septChar);
		while(idx !=-1)
		{
			list.add(oriStr.substring(0,idx));
			oriStr = oriStr.substring(idx + septChar.length());
			idx = oriStr.indexOf(septChar);
		}
		//最后一个参数
		list.add(oriStr);
		
		return list;
	}

	/**
	 * 将查询条件的键转换下作为hql中的插叙条件
	 * 如aa.bb转为aa_bb
	 *   aa.bb.cc转为aa_bb_cc
	 * @param paramKey
	 * @return
	 */
	public static String fixParamForHql(String paramKey){
		String newStr = StringUtils.replace(paramKey, ".", "_");
		return newStr;
	}
	
	/** 禁止实例化 */
	//注释原因：其他util无法extends
	//private StrUtils() {}
	
	/**
	 * 检查对象是否为空　
	 * @param obj
	 * @return
	 */
	public static boolean objectIsNull(Object obj){
		if(null != obj && !"".equals(obj)){
			return false;
		}else{
			return true;
		}
	}
	
	public static boolean isNotEmpty(String str){
		if(StringUtils.isEmpty(str) || str.equalsIgnoreCase("null")){
			return false;
		}
		return true;
	}
	
	public static boolean isEmpty(String str){
		if(StringUtils.isEmpty(str) || str.equalsIgnoreCase("null")){
			return true;
		}
		return false;
	}

	/**
	 * 字符串为 null 或者为  "" 时返回 true
	 */
	public static boolean isBlank(String str) {
		return str == null || "".equals(str.trim()) ? true : false;
	}
	
	
	/**　
	 * 检查对象是否为空
	 * @param obj
	 * @return
	 */
	public static boolean objectIsNotNull(Object obj){
		if(obj==null){
			return false;
		}
		if("".equals(obj.toString().trim())){
			return false;
		}
		return true;
	}
	
	/**
	 * 将字符串数字转换成int数字
	 * @param val 字符串数字
	 * @param defaultVal 无转换失败后使用的默认值
	 * @return 转换后的数字
	 */
	public static int fixStrToInt(String val, int defaultVal){
		if(StrUtils.objectIsNull(val))
			return defaultVal;
		try{
			return Integer.parseInt(val);
		}catch(Exception unReport){
			return defaultVal;
		}
	}

	/**
	 * 处理url
	 * 
	 * url为null返回null，url为空串或以http://或https://开头，则加上http://，其他情况返回原参数。
	 * 
	 * @param url
	 * @return
	 */
	public static String handelUrl(String url) {
		if (url == null) {
			return null;
		}
		url = url.trim();
		if (url.equals("") || url.startsWith("http://")
				|| url.startsWith("https://")) {
			return url;
		} else {
			return "http://" + url.trim();
		}
	}

	/**
	 * 分割并且去除空格
	 * 
	 * @param str
	 *            待分割字符串
	 * @param sep
	 *            分割符
	 * @param sep2
	 *            第二个分隔符
	 * @return 如果str为空，则返回null。
	 */
	public static String[] splitAndTrim(String str, String sep, String sep2) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		if (!StringUtils.isBlank(sep2)) {
			str = StringUtils.replace(str, sep2, sep);
		}
		String[] arr = StringUtils.split(str, sep);
		// trim
		for (int i = 0, len = arr.length; i < len; i++) {
			arr[i] = arr[i].trim();
		}
		return arr;
	}

	/**
	 * 文本转html
	 * 
	 * @param txt
	 * @return
	 */
	public static String txt2htm(String txt) {
		if (StringUtils.isBlank(txt)) {
			return txt;
		}
		StringBuilder sb = new StringBuilder((int) (txt.length() * 1.2));
		char c;
		boolean doub = false;
		for (int i = 0; i < txt.length(); i++) {
			c = txt.charAt(i);
			if (c == ' ') {
				if (doub) {
					sb.append(' ');
					doub = false;
				} else {
					sb.append("&nbsp;");
					doub = true;
				}
			} else {
				doub = false;
				switch (c) {
				case '&':
					sb.append("&amp;");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '"':
					sb.append("&quot;");
					break;
				case '\n':
					sb.append("<br/>");
					break;
				default:
					sb.append(c);
					break;
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 剪切文本。如果进行了剪切，则在文本后加上"..."
	 * 
	 * @param s
	 *            剪切对象。
	 * @param len
	 *            编码小于256的作为一个字符，大于256的作为两个字符。
	 * @return
	 */
	public static String textCut(String s, int len, String append) {
		if (s == null) {
			return null;
		}
		int slen = s.length();
		if (slen <= len) {
			return s;
		}
		// 最大计数（如果全是英文）
		int maxCount = len * 2;
		int count = 0;
		int i = 0;
		for (; count < maxCount && i < slen; i++) {
			if (s.codePointAt(i) < 256) {
				count++;
			} else {
				count += 2;
			}
		}
		if (i < slen) {
			if (count > maxCount) {
				i--;
			}
			if (!StringUtils.isBlank(append)) {
				if (s.codePointAt(i - 1) < 256) {
					i -= 2;
				} else {
					i--;
				}
				return s.substring(0, i) + append;
			} else {
				return s.substring(0, i);
			}
		} else {
			return s;
		}
	}

	public static String htmlCut(String s, int len, String append) {
		String text = html2Text(s, len * 2);
		return textCut(text, len, append);
	}

	public static String html2Text(String html, int len) {
		try {
			Lexer lexer = new Lexer(html);
			Node node;
			StringBuilder sb = new StringBuilder(html.length());
			while ((node = lexer.nextNode()) != null) {
				if (node instanceof TextNode) {
					sb.append(node.toHtml());
				}
				if (sb.length() > len) {
					break;
				}
			}
			return sb.toString();
		} catch (ParserException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 检查字符串中是否包含被搜索的字符串。被搜索的字符串可以使用通配符'*'。
	 * 
	 * @param str
	 * @param search
	 * @return
	 */
	public static boolean contains(String str, String search) {
		if (StringUtils.isBlank(str) || StringUtils.isBlank(search)) {
			return false;
		}
		String reg = StringUtils.replace(search, "*", ".*");
		Pattern p = Pattern.compile(reg);
		return p.matcher(str).matches();
	}
	
	/**
	 * 将字符串中的全角字符转换为半角
	 * @param fullWidthStr
	 * @return
	 */
	public static final String convertFullWithToHalfWidth(String fullWidthStr) {  
		char[] tempChars = fullWidthStr.toCharArray();   
		for (int i = 0; i < tempChars.length; i++) {   
			if (tempChars[i] == 12288) {   
				tempChars[i] = (char) 32;   
				continue;   
			}   
		    if (tempChars[i] > 65280 && tempChars[i] < 65375){  
		    	tempChars[i] = (char) (tempChars[i] - 65248); 
		    }
		}   
		return new String(tempChars);  
	}  
	
	/**
	 * StingBuilder的 trim方法
	 * @param sb
	 * @return
	 */
	 public static String trimSubstring(StringBuilder sb) {
        int first, last;

        for (first = 0; first < sb.length(); first++)
            if (!Character.isWhitespace(sb.charAt(first)))
                break;

        for (last = sb.length(); last > first; last--)
            if (!Character.isWhitespace(sb.charAt(last - 1)))
                break;

        return sb.substring(first, last);
    }
	 
	 
	 public static String trimLastSubstring(StringBuilder sb) {
        int first=0, last;

     /*   for (first = 0; first < sb.length(); first++)
            if (!Character.isWhitespace(sb.charAt(first)))
                break;*/

        for (last = sb.length(); last > first; last--)
            if (!Character.isWhitespace(sb.charAt(last - 1)))
                break;

        return sb.substring(first, last);
    }
	 
	
	 public static String toLowerCaseInitial(String srcString,boolean isDaxie){
		 String resStr ;
		 if(isDaxie){
			 resStr = StringUtils.capitalize(srcString);
		 }else{
			 resStr = StringUtils.uncapitalize(srcString);
		 }
		 return resStr;
	 }
	 
	 /**
	  * 去掉首尾字母
	  * @param str
	  * @param oldChar
	  * @return
	  */
	 public static String relaceFirstAndLastChar(String str,char searchChar){
		char first = str.charAt(0);
		if(first==searchChar){
			str = str.substring(1);
		}

		char last = str.charAt(str.length()-1);
		if(last==searchChar){
			str = str.substring(0,str.length()-1);
		}
		
		return str;
		 
	 }
	 
	 
	 public static String removeLastChar(String str){
		 return str.substring(0, str.length()-1);
	 }
	 
	 public static String getRemoteIpAddress(final HttpServletRequest request){
		/* String ip = request.getHeader("x-forwarded-for");
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			 ip = request.getHeader("Proxy-Client-IP");
		 }
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			 ip = request.getHeader("WL-Proxy-Client-IP");
		 }
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getRemoteAddr();
		 }
		 return ip;*/
			if (request == null) {
				return null;
			}
			String ipString = request.getHeader("x-forwarded-for");
			if (StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
				ipString = request.getHeader("Proxy-Client-IP");
			}
			if (StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
				ipString = request.getHeader("WL-Proxy-Client-IP");
			}
			if (StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
				ipString = request.getRemoteAddr();
			}

			// 多个路由时，取第一个非unknown的ip
			final String[] arr = ipString.split(",");
			for (final String str : arr) {
				if (!"unknown".equalsIgnoreCase(str)) {
					ipString = str;
					break;
				}
			}

			return ipString;
	 }
	
	 public static void main(String[] args) throws Exception {
		 //System.out.println(toLowerCaseInitial("Metata", false));
		/* String em="com.org.UserAccount";
		 int index = StringUtils.lastIndexOf(em, ".");
		 System.out.println(index);
		 System.out.println(StringUtils.substring(em, index+1));*/
		 
		 String str = ",1,2,3,4a,";
		 str = relaceFirstAndLastChar(str, ',');
		 System.out.println(str);
		 
		 System.out.println(removeLastChar("abdcd23,"));
	 }

	/**
	 * 生成8位UUID，重复率也相当的低
	 * @return
	 */
	public static String genShortUUID() {  
		StringBuffer shortBuffer = new StringBuffer();  
		String uuid = UUID.randomUUID().toString().replace("-", "");  
		for (int i = 0; i < 8; i++) {  
		    String str = uuid.substring(i * 4, i * 4 + 4);  
		    int x = Integer.parseInt(str, 16);  
		    shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();  
	}
}
