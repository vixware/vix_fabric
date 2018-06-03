package com.vix.common.id.sequence;

import com.vix.common.id.exception.CreateSequnceException;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-21
 */
public abstract class AbstractRollingSequenceGenerator extends DefaultSequenceGenerator {

	@Override
	public long next() throws CreateSequnceException {
		if (isResetCount()) {
			this.currCount = this.minValue;
			maxCount = this.currCount;
			sequenceStorer.updateMaxValueByFieldName(maxCount, this.getId());
		}
		return super.next();
	}

	abstract protected boolean isResetCount();

}
