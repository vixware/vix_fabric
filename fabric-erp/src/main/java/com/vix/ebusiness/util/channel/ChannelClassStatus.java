package com.vix.ebusiness.util.channel;

import java.io.Serializable;

/**
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-21
 */
public class ChannelClassStatus implements Serializable {

	private static final long serialVersionUID = -3682176851458810164L;
	/**
	 * 0 未分类
	 */
	public static final Integer UNFILED = 0;
	/**
	 * 1 商城
	 */
	public static final Integer MALL = 1;
	/**
	 * 2 团购
	 */
	public static final Integer GROUP = 2;
	/**
	 * 3 微博、SNS
	 */
	public static final Integer MICROBLOG = 3;
}
