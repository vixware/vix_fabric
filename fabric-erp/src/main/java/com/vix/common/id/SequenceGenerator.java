/**
 * 
 */
package com.vix.common.id;

import com.vix.common.id.exception.CreateSequnceException;


/**
 * SequenceGenerator
 * 
 * @author zhanghaibing
 * 
 * @date 2013-6-20
 */
public interface SequenceGenerator {

	public long next() throws CreateSequnceException;

}
