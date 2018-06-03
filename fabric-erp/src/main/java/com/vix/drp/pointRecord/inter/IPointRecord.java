package com.vix.drp.pointRecord.inter;

import com.vix.drp.pointRecord.entity.PointRecord;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * 积分记录写入接口
 * 
 * com.vix.drp.pointRecord.inter.IPointRecord
 *
 * @author zhanghaibing
 *
 * @date 2015年1月29日
 */
public interface IPointRecord {
	/**
	 * 
	 * @param pointSource
	 *            积分来源
	 * @param operation
	 *            操作
	 * @param pointType
	 *            积分类型
	 * @param pointValue
	 *            积分值
	 * @param customerAccount
	 *            会员
	 * @return
	 * @throws Exception
	 */
	public PointRecord updatePointRecord(String pointSource, String operation, String pointType, Double pointValue, CustomerAccount customerAccount) throws Exception;
}
