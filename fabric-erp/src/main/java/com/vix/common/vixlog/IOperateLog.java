package com.vix.common.vixlog;

/**
 * 处理日志,方便以后多种方式存放数据扩展
 * 
 * @author Think
 *
 */
public interface IOperateLog {
	/**
	 * 在执行操作的同时添加最近访问
	 * 
	 * @param operateType
	 *            对象类型
	 * @param billCode
	 *            单据编码
	 * @param url
	 *            访问url
	 * @param operate
	 *            操作内容
	 * @throws Exception
	 */
	public void saveOperateLog(String objectType, String billCode, String url, String operate) throws Exception;
}
