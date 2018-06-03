package com.vix.common.id.storer;

import java.util.HashMap;
import java.util.Map;

import com.vix.common.id.SequenceStorer;
import com.vix.common.id.exception.StoreSequenceException;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-20
 */
public class MemorySequenceStorer implements SequenceStorer {

	@SuppressWarnings("rawtypes")
	private Map cache = new HashMap();

	public void init() {
	}

	@Override
	public long load(String sequenceID) throws StoreSequenceException {
		if (cache.containsKey(sequenceID) == false) {
			updateMaxValueByFieldName(0, sequenceID);
		}
		Long result = (Long) cache.get(sequenceID);
		return result.longValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void updateMaxValueByFieldName(long sequence, String sequenceID) throws StoreSequenceException {
		cache.put(sequenceID, new Long(sequence));
	}

}
