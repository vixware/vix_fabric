/**
 * 
 */
package com.vix.common.id;

import com.vix.common.id.exception.CreateIDException;

/**
 * IDGenerator
 * 
 * @author zhanghaibing
 * 
 * @date 2013-6-20
 */
public interface IDGenerator {
	public String create() throws CreateIDException;

	public void setPrefixGenerator(PrefixGenerator prefixGenerator);

	public void setSequenceGenerator(SequenceGenerator sequenceGenerator);

	public void setSequenceFormater(SequenceFormater sequenceFormater);
}
