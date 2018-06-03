package com.vix.common.id.generator;

import com.vix.common.id.IDGenerator;
import com.vix.common.id.PrefixGenerator;
import com.vix.common.id.SequenceFormater;
import com.vix.common.id.SequenceGenerator;
import com.vix.common.id.exception.CreateIDException;
import com.vix.common.id.sequence.DefaultSequenceGenerator;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-20
 */
public class DefaultIDGenerator implements IDGenerator {

	private PrefixGenerator prefixGenerator;
	private SequenceGenerator sequenceGenerator = new DefaultSequenceGenerator();
	private SequenceFormater sequenceFormater;

	@Override
	public synchronized String create() throws CreateIDException {
		final String prefix = prefixGenerator == null ? "" : prefixGenerator.create();
		long sequence = sequenceGenerator.next();
		final String strSequence = sequenceFormater == null ? new Long(sequence).toString() : sequenceFormater.format(sequence);
		return prefix + strSequence;
	}

	@Override
	public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
		this.prefixGenerator = prefixGenerator;
	}

	@Override
	public void setSequenceGenerator(SequenceGenerator sequenceGenerator) {
		this.sequenceGenerator = sequenceGenerator;
	}

	@Override
	public void setSequenceFormater(SequenceFormater sequenceFormater) {
		this.sequenceFormater = sequenceFormater;
	}

}
