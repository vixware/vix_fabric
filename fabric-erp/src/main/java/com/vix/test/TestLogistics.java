/**
 * 
 */
package com.vix.test;

import java.util.List;

import com.vix.logistics.LogisticsCompany;
import com.vix.logistics.numbers.LogisticsNumbers;
import com.vix.logistics.numbers.LogisticsNumbersException;
import com.vix.logistics.numbers.LogisticsNumbersGenerator;

/**
 * com.vix.ebusiness.logistics.Test
 *
 * @author zhanghaibing
 *
 * @date 2014年10月13日
 */
public class TestLogistics {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String firstcode = "114750081239";
		try {
			LogisticsNumbers logisticsNumbers = LogisticsNumbersGenerator.getGenerator(LogisticsCompany.SF);
			List<String> logisticsCodeList = logisticsNumbers.createNumners(firstcode, 10, true);
			if (logisticsCodeList != null && logisticsCodeList.size() > 0) {
				for (String logisticsCode : logisticsCodeList) {
					System.out.println(logisticsCode);
				}
			}
		} catch (LogisticsNumbersException e) {
			e.printStackTrace();
		}
	}

}
