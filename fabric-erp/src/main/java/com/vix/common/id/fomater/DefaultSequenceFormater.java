package com.vix.common.id.fomater;

import java.text.DecimalFormat;

import com.vix.common.id.SequenceFormater;
import com.vix.common.id.exception.FormatSequenceExcepiton;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-20
 */
public class DefaultSequenceFormater implements SequenceFormater {

	private String pattern;

	@Override
	public String format(long pSequence) throws FormatSequenceExcepiton {
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(pSequence);
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
