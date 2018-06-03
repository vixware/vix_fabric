package com.vix.common.share.entity;

/**
 * 系统全局业务对象回收站，待实现
 * @author Administrator
 *
 */
public class BussinessObjectRecycle extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**通过静态接口设定模块与业务对象值，通过接口实现来组织一二级数据，回收站支持查看  */
	/** 业务模块类型 */
	private String type;
	/** 对象名称描述 */
	private String name;
}
