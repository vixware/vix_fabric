package com.vix.common.id;

import com.vix.common.id.exception.FormatSequenceExcepiton;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-21
 */
public interface SequenceFormater {
	public String format(long pSequence) throws FormatSequenceExcepiton;
}
