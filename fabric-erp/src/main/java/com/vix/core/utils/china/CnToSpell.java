package com.vix.core.utils.china;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * 
 * 汉字转化为全拼,码表利用输入法的码表导出，汉字信息非常全，平时见到的汉字
 * 转拼音的API只能处理几千个一级汉字，很多字都没有法查到，尤其是我自己的姓
 * 都查不到所以只好自己做一个汉字转拼音的API，该API除可以处理99％以上的汉字，
 * 而且可以返回多音字的读音
 */
public class CnToSpell extends CnToSpellBase{

	static {
		if (spellMap == null) {
			spellMap = new LinkedHashMap<String,String>(20901);
		}
		initialize();
	}

	private CnToSpell() {
	}
	

	/**
	 * 获得单个汉字的Ascii，并用"-"连接成一个字符串
	 * 
	 * @param cn char 汉字字符
	 * @return string 错误返回 空字符串,否则返回ascii
	 */
	public static String getCnAscii(char cn) {
		byte[] bytes = (String.valueOf(cn)).getBytes();
//		System.out.println(bytes.length);
		if (bytes == null || bytes.length > 2 || bytes.length <= 0) { // 错误
			return "";
		}
		if (bytes.length == 1) { // 英文字符
			return new String(bytes);
		}
		if (bytes.length == 2) { // 中文字符
			int hightByte = 256 + bytes[0];
			int lowByte = 256 + bytes[1];

			String ascii = hightByte + "-" + lowByte;

//			System.out.println("ASCII=" + ascii);

			return ascii;
		}

		return ""; // 错误
	}

	/**
	 * 根据ASCII码连接成的字符串到SpellMap中查找对应的拼音
	 * 
	 * @param ascii 字符对应的ASCII连接的字符串
	 * @return String 拼音,首先判断是否是中文如果是英文直接返回字符，如果是中文返回拼音,
	 * 
	 * 否则到SpellMap中查找,如果没有找到拼音,则返回null,如果找到则返回拼音.
	 */
	public static String getSpellByAscii(String ascii) {
		if (ascii.indexOf("-") > -1)
		{
			return spellMap.get(ascii);
		} else {
			return ascii;
		}
	}

	/**
	 * 返回字符串的全拼,是汉字转化为全拼,其它字符不进行转换
	 * 
	 * @param cnStr String字符串
	 * @return String 转换成全拼后的字符串
	 */
	public static String getFullSpell(String cnStr)
	{
		if (null == cnStr || "".equals(cnStr.trim())) {
			return cnStr;
		}

		char[] chars = cnStr.toCharArray();
		StringBuffer retuBuf = new StringBuffer();
		for (int i = 0, Len = chars.length; i < Len; i++) {
			String ascii = getCnAscii(chars[i]);
			if (ascii.length() == 0) { // 取ascii时出错
				retuBuf.append(chars[i]);
			} else {
				String spell = getSpellByAscii(ascii);
				if (spell == null) {
					retuBuf.append(chars[i]);
				} else {
					//去除多音字，只显示第一个
					int idx = spell.indexOf(",");
					if(idx!=-1)
						spell = spell.substring(0,idx);
					
					retuBuf.append(spell);
				} // end of if spell == null
			} // end of if ascii <= -20400
		} // end of for
			
		return retuBuf.toString();
	}
	
	//所有拼音可能性,暂时不用就没写，跟getFirstSpellAll逻辑详细，以getFullSpell为基础改就成
	public static String getFullSpellAll(String cnStr)
	{
		return "";
	}

	/**
	 * 获取汉语字符串的声母组合，每个汉字取拼音的第一个字符组成的一个字符串
	 * @param cnStr 汉字的字符串
	 * @return 每个汉字拼音的第一个字母所组成的汉字
	 */
	public static String getFirstSpell(String cnStr)
	{
		if (null == cnStr || "".equals(cnStr.trim())) {
			return cnStr;
		}

		char[] chars = cnStr.toCharArray();
		StringBuffer retuBuf = new StringBuffer();
		for (int i = 0, Len = chars.length; i < Len; i++) {
			String ascii = getCnAscii(chars[i]);
//			System.out.println(ascii);
			if (ascii.length() == 1) { // 取ascii时出错
				retuBuf.append(chars[i]);
			} else {
				
				String spell = getSpellByAscii(ascii).substring(0, 1);
//				System.out.println(spell);
				if (spell == null) {
					retuBuf.append(chars[i]);
				} else {
					retuBuf.append(spell);
				} // end of if spell == null
			} // end of if ascii <= -20400
		} // end of for
		return retuBuf.toString();
	}

	//所有可能性，用逗号分割，多个字有多音字时就排列组合
	public static String getFirstSpellAll(String cnStr){
		if (null == cnStr || "".equals(cnStr.trim())) {
			return cnStr;
		}
		
		StringBuilder retuBuf = new StringBuilder();
		try{
			char[] chars = cnStr.toCharArray();
			int charLength = chars.length;
			int[] everyCharIdx = new int[charLength];//all 0
			int[] everyCharLength = new int[charLength];
			String[][] everyChars = new String[charLength][];
			
			for(int i=0;i<charLength;i++){
				String ascii = getCnAscii(chars[i]);
				String spell = getSpellByAscii(ascii);
				everyChars[i] = spell.split(",");
				everyCharLength[i] = everyChars[i].length;
				for(int t=0;t<everyChars[i].length;t++){
					everyChars[i][t] = everyChars[i][t].substring(0,1);
				}
			}
			
			int firstLength = everyChars[0].length;
			
			while(everyCharIdx[0]<firstLength){
				for(int ins=0;ins<charLength;ins++){
					retuBuf.append(everyChars[ins][everyCharIdx[ins]]);
				}
				retuBuf.append(",");
				
				everyCharIdx = setNewIndexOfEveryChar(charLength, everyCharIdx, everyCharLength);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return getFirstSpell(cnStr);
		}
		
		String ret = retuBuf.toString();
		return ret.substring(0,ret.length()-1);
	}
	
	static int[] setNewIndexOfEveryChar(int charLength, int[] everyCharIdx, int[] everyCharLength){
		
		int setIdx = charLength-1;

		while(setIdx>=0){
			if(++everyCharIdx[setIdx] == everyCharLength[setIdx]){
				if(setIdx!=0)
					everyCharIdx[setIdx] = 0;
				setIdx--;					
			}else{
				break;
			}
		}
		return everyCharIdx;
	}

	public static void main(String[] args) {
		Date start = new Date();
		System.out.println(start.getTime());
		String str = null;
//		str = "逯 闫 乐";
		//"仇，,;$#@&avc"
		str = "校仇系";
		System.out.println("Spell=" + CnToSpell.getFullSpell(str));
		System.out.println("Spell=" + CnToSpell.getFirstSpell(str));
		System.out.println(CnToSpell.getFirstSpellAll(str));
		Date end = new Date();
		System.out.println(end.getTime());
	}
}