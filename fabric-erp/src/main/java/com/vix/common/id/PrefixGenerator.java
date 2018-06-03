/**
 * 
 */
package com.vix.common.id;

import com.vix.common.id.exception.CreatePrefixException;


/**
 * @author zhanghaibing
 * 
 * @date 2013-6-20
 */
public interface PrefixGenerator {

	public String create() throws CreatePrefixException;

}
