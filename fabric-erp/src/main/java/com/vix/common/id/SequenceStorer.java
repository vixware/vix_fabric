/**
 * 
 */
package com.vix.common.id;

import com.vix.common.id.exception.StoreSequenceException;



/**
 * @author zhanghaibing
 * 
 * @date 2013-6-20
 */
public interface SequenceStorer {

	/**
	 * 保存序号
	 * 
	 * @param pSequence
	 *            序号
	 * @param pSequenceID
	 *            序号ID
	 * @throws StoreSequenceException
	 */
	public void updateMaxValueByFieldName(long pSequence, final String pSequenceID) throws StoreSequenceException;

	/**
	 * 
	 * @param pSequenceID
	 *            序号ID
	 * @return
	 * @throws StoreSequenceException
	 */
	public long load(final String pSequenceID) throws StoreSequenceException;

}
