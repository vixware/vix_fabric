/**
 * 
 */
package com.vix.common.id.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.id.ICodeGenerator;
import com.vix.common.id.IDGenerator;
import com.vix.common.id.exception.CreateIDException;
import com.vix.common.id.fomater.DefaultSequenceFormater;
import com.vix.common.id.prefix.DefaultPrefixGenerator;
import com.vix.common.id.sequence.DefaultSequenceGenerator;
import com.vix.common.id.sequence.TimeRollingSequenceGenerator;

/**
 * @author zhanghaibing
 * 
 * @date 2013-7-9
 */
public class CodeGenerator implements ICodeGenerator {

	@Override
	public String getCodeByRule(String scale, String boType, String datePattern, Long start, Long end, Integer step, Integer length, Boolean hasTime) throws CreateIDException {

		// 构造生成器
		IDGenerator generator = new DefaultIDGenerator();

		// 前缀生成器，用于产生ID前缀
		DefaultPrefixGenerator prefixGenerator = new DefaultPrefixGenerator();
		prefixGenerator.setWithDate(hasTime);
		prefixGenerator.setPattern(datePattern);
		generator.setPrefixGenerator(prefixGenerator);
		StringBuffer prefix = new StringBuffer();
		if (scale != null && !"".equals(scale)) {
			prefix.append(scale);
		}
		if (boType != null && !"".equals(boType)) {
			prefix.append(boType);
		}
		// 前缀
		prefixGenerator.setPrefix(prefix.toString());

		// 序号生成器
		DefaultSequenceGenerator sequenceGenerator = TimeRollingSequenceGenerator.getDayRollingSequenceGenerator();
		sequenceGenerator.setMinValue(start);
		sequenceGenerator.setMaxValue(end);
		sequenceGenerator.setCycle(true);
		sequenceGenerator.setCache(step);
		sequenceGenerator.setId(prefix.toString());
		generator.setSequenceGenerator(sequenceGenerator);

		// 格式化序号
		DefaultSequenceFormater sequenceFormater = new DefaultSequenceFormater();
		StringBuffer pattern = new StringBuffer();
		int size = 0;
		if (prefix != null && !"".equals(prefix)) {
			size = prefix.length();
		}
		int patternlength = 0;
		if (length > size) {
			patternlength = length - prefix.length();
		} else
			return null;
		for (int i = 0; i < patternlength; i++) {
			pattern.append("0");
		}
		sequenceFormater.setPattern(pattern.toString());
		generator.setSequenceFormater(sequenceFormater);

		return generator.create();
	}

	@Override
	public List<String> createCodesByRule(String scale, String boType, String datePattern, Long start, Long end, Integer step, Integer length, Boolean hasTime, Integer number) throws CreateIDException {
		List<String> codes = new ArrayList<String>();
		for (int j = 0; j < number; j++) {

			// 构造生成器
			IDGenerator generator = new DefaultIDGenerator();

			// 前缀生成器，用于产生ID前缀
			DefaultPrefixGenerator prefixGenerator = new DefaultPrefixGenerator();
			prefixGenerator.setWithDate(hasTime);
			prefixGenerator.setPattern(datePattern);
			generator.setPrefixGenerator(prefixGenerator);
			StringBuffer prefix = new StringBuffer();
			if (scale != null && !"".equals(scale)) {
				prefix.append(scale);
			}
			if (boType != null && !"".equals(boType)) {
				prefix.append(boType);
			}
			// 前缀
			prefixGenerator.setPrefix(prefix.toString());

			// 序号生成器
			DefaultSequenceGenerator sequenceGenerator = TimeRollingSequenceGenerator.getDayRollingSequenceGenerator();
			sequenceGenerator.setMinValue(start);
			sequenceGenerator.setMaxValue(end);
			sequenceGenerator.setCycle(true);
			sequenceGenerator.setCache(step);
			sequenceGenerator.setId(prefix.toString());
			generator.setSequenceGenerator(sequenceGenerator);

			// 格式化序号
			DefaultSequenceFormater sequenceFormater = new DefaultSequenceFormater();
			StringBuffer pattern = new StringBuffer();
			int size = 0;
			if (prefix != null && !"".equals(prefix)) {
				size = prefix.length();
			}
			int patternlength = 0;
			if (length > size) {
				patternlength = length - prefix.length();
			} else
				return null;
			for (int i = 0; i < patternlength; i++) {
				pattern.append("0");
			}
			sequenceFormater.setPattern(pattern.toString());
			generator.setSequenceFormater(sequenceFormater);
			codes.add(generator.create());

		}
		return codes;
	}

	@Override
	public String createRandomCode() throws CreateIDException {
		UUIDGenerator a = new UUIDGenerator();
		return a.create();
	}

	@Override
	public String getCode(String prefixCode, String datePattern, Long start, Long end, Integer step, Integer length, Boolean hasTime) throws CreateIDException {

		if (StringUtils.isEmpty(prefixCode)) {
			return null;
		}

		String s1 = "";
		if (StringUtils.isNotEmpty(prefixCode)) {
			if (prefixCode.contains("YYYYMMDD")) {
				s1 = prefixCode.replaceAll("YYYYMMDD", getFormatedDate("YYYYMMDD"));
			} else if (prefixCode.contains("YYYYMM")) {
				s1 = prefixCode.replaceAll("YYYYMM", getFormatedDate("YYYYMM"));
			} else if (prefixCode.contains("YYYY")) {
				s1 = prefixCode.replaceAll("YYYY", getFormatedDate("YYYY"));
			} else {
				s1 = prefixCode;
			}
		}

		if (start == null) {
			return null;
		}

		if (end == null) {
			return null;
		}

		if (step == null) {
			return null;
		}

		if (length == null) {
			return null;
		}

		if (hasTime == null) {
			return null;
		}

		// 构造生成器
		IDGenerator generator = new DefaultIDGenerator();

		// 前缀生成器，用于产生ID前缀
		DefaultPrefixGenerator prefixGenerator = new DefaultPrefixGenerator();
		prefixGenerator.setWithDate(hasTime);
		prefixGenerator.setPattern(datePattern);
		generator.setPrefixGenerator(prefixGenerator);

		// 前缀
		prefixGenerator.setPrefix(s1);

		// 序号生成器
		DefaultSequenceGenerator sequenceGenerator = TimeRollingSequenceGenerator.getDayRollingSequenceGenerator();
		sequenceGenerator.setMinValue(start);
		sequenceGenerator.setMaxValue(end);
		sequenceGenerator.setCycle(true);
		sequenceGenerator.setCache(step);
		sequenceGenerator.setId(prefixCode);
		generator.setSequenceGenerator(sequenceGenerator);

		// 格式化序号
		DefaultSequenceFormater sequenceFormater = new DefaultSequenceFormater();
		StringBuffer pattern = new StringBuffer();
		int size = 0;
		if (prefixCode != null && !"".equals(prefixCode)) {
			size = prefixCode.length();
		}
		if (hasTime) {
			if (datePattern != null && datePattern.length() > 0) {
				size += datePattern.length();
			}
		}
		int patternlength = 0;
		if (length > size) {
			patternlength = length - size;
		}
		for (int i = 0; i < patternlength; i++) {
			pattern.append("0");
		}
		sequenceFormater.setPattern(pattern.toString());
		generator.setSequenceFormater(sequenceFormater);

		return generator.create();
	}

	public String getFormatedDate(String pattern) {
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
