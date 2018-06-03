/**
 * 
 */
package com.vix.common.id;

import java.util.List;

import com.vix.common.id.exception.CreateIDException;

/**
 * 应用层编码
 * 
 * @author zhanghaibing
 * 
 * @date 2013-7-9
 */
public interface ICodeGenerator {
	static final String TIMEPATTERN = "yyyyMMdd";

	/**
	 * 通过规则生成编码
	 * 
	 * @param prefixCode
	 *            编码前缀
	 * @param datePattern
	 *            日期格式yyyyMMdd
	 * @param start
	 *            序列号起始1
	 * @param end
	 *            序列号结束9999999
	 * @param step
	 *            序列号步长1
	 * @param length
	 *            编码长度20
	 * @param hasTime
	 *            是否包含时间20130717
	 * @return
	 * @throws CreateIDException
	 */
	public String getCode(String prefixCode, String datePattern, Long start, Long end, Integer step, Integer length, Boolean hasTime) throws CreateIDException;

	/**
	 * 通过规则生成编码
	 * 
	 * @param scale
	 *            企业范围Vix
	 * @param boType
	 *            业务对象类型qq
	 * @param datePattern
	 *            日期格式yyyyMMdd
	 * @param start
	 *            序列号起始1
	 * @param end
	 *            序列号结束9999999
	 * @param step
	 *            序列号步长1
	 * @param length
	 *            编码长度20
	 * @param hasTime
	 *            是否包含时间20130717
	 * @return
	 * @throws CreateIDException
	 */
	public String getCodeByRule(String scale, String boType, String datePattern, Long start, Long end, Integer step, Integer length, Boolean hasTime) throws CreateIDException;

	/**
	 * 通过规则批量生成编码
	 * 
	 * @param scale
	 *            企业范围Vix
	 * @param boType
	 *            对象类型qq
	 * @param datePattern
	 *            日期格式yyyyMMdd
	 * @param start
	 *            序列号起始1
	 * @param end
	 *            序列号结束9999999
	 * @param step
	 *            序列号步长1
	 * @param length
	 *            序列号长度20
	 * @param hasTime
	 *            是否包含时间20130717
	 * @param number
	 *            生成编码的个数100
	 * @return Vixqq201307170000000281 Vixqq201307170000000282
	 *         Vixqq201307170000000283 Vixqq201307170000000284
	 * @throws CreateIDException
	 */
	public List<String> createCodesByRule(String scale, String boType, String datePattern, Long start, Long end, Integer step, Integer length, Boolean hasTime, Integer number) throws CreateIDException;

	/**
	 * 生成随机编码 402881903feceaf8013feceaf8c40000
	 * 
	 * @return
	 * @throws CreateIDException
	 */
	public String createRandomCode() throws CreateIDException;
}
