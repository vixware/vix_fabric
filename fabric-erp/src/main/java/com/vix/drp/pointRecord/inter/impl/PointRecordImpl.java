package com.vix.drp.pointRecord.inter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.drp.pointRecord.controller.PointRecordController;
import com.vix.drp.pointRecord.entity.PointRecord;
import com.vix.drp.pointRecord.inter.IPointRecord;
import com.vix.mdm.crm.entity.CustomerAccount;

@Service("pointRecord")
public class PointRecordImpl implements IPointRecord {
	@Autowired
	private PointRecordController pointRecordController;
	@Autowired
	public InitEntityBaseController initEntityBaseController;

	@Override
	public PointRecord updatePointRecord(String pointSource, String operation, String pointType, Double pointValue, CustomerAccount customerAccount) throws Exception {
		PointRecord pointRecord = new PointRecord();
		pointRecord.setPointSource(pointSource);
		pointRecord.setOperation(operation);
		pointRecord.setPointType(pointType);
		pointRecord.setPointValue(pointValue);
		pointRecord.setCustomerAccount(customerAccount);
		initEntityBaseController.initEntityBaseAttribute(pointRecord);
		return pointRecordController.doSavePointRecord(pointRecord);
	}

}
