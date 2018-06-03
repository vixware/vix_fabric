package com.vix.common.id.generator;

import java.net.InetAddress;

import com.vix.common.id.IDGenerator;
import com.vix.common.id.PrefixGenerator;
import com.vix.common.id.SequenceFormater;
import com.vix.common.id.SequenceGenerator;

/**
 * AbstractUUIDGenerator
 * 
 * @author zhanghaibing
 * 
 * @date 2013-6-20
 */
public abstract class AbstractUUIDGenerator implements IDGenerator {

	private static final int IP;
	static {
		int ipadd;
		try {
			ipadd = toInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}
	private static short counter = (short) 0;
	private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

	public static int toInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + bytes[i];
		}
		return result;
	}

	public AbstractUUIDGenerator() {
	}

	/**
	 * Unique across JVMs on this machine (unless they load this class in the
	 * same quater second - very unlikely)
	 */
	protected int getJVM() {
		return JVM;
	}

	/**
	 * Unique in a millisecond for this JVM instance (unless there are >
	 * Short.MAX_VALUE instances created in a millisecond)
	 */
	protected short getCount() {
		synchronized (AbstractUUIDGenerator.class) {
			if (counter < 0)
				counter = 0;
			return counter++;
		}
	}

	/**
	 * Unique in a local network
	 */
	protected int getIP() {
		return IP;
	}

	/**
	 * Unique down to millisecond
	 */
	protected short getHiTime() {
		return (short) (System.currentTimeMillis() >>> 32);
	}

	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}

	@Override
	public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
	}

	@Override
	public void setSequenceGenerator(SequenceGenerator sequenceGenerator) {
	}

	@Override
	public void setSequenceFormater(SequenceFormater sequenceFormater) {
	}
}
