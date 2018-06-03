package com.vix.eam.entity;

import com.vix.eam.common.entity.EamBaseEntity;

public class EqMonitorPoint extends EamBaseEntity
{
	private String pointCode;		//监测点编号
	private String pointName;		//监测点名称
	private String pointPlaceCode;		//监测点位置编码
	private String monitorProjectCode;		//监测项目代码
	private String monitorProjectName;		//监测项目名称
	private Integer monitorType;		//监测值类型，范围，累计
	private String measureUnit;		//度量单位
	private Double limitMinValue;		//最小值
	private Double limitMaxValue;		//最大值
	private Double countValue;		//累计值

	//private String eqId;		//id设备id
	private Equipment equipment;

	public String getPointCode()
	{
		return pointCode;
	}

	public void setPointCode(String pointCode)
	{
		this.pointCode = pointCode;
	}

	public String getPointName()
	{
		return pointName;
	}

	public void setPointName(String pointName)
	{
		this.pointName = pointName;
	}

	public String getPointPlaceCode()
	{
		return pointPlaceCode;
	}

	public void setPointPlaceCode(String pointPlaceCode)
	{
		this.pointPlaceCode = pointPlaceCode;
	}

	public String getMonitorProjectCode()
	{
		return monitorProjectCode;
	}

	public void setMonitorProjectCode(String monitorProjectCode)
	{
		this.monitorProjectCode = monitorProjectCode;
	}

	public String getMonitorProjectName()
	{
		return monitorProjectName;
	}

	public void setMonitorProjectName(String monitorProjectName)
	{
		this.monitorProjectName = monitorProjectName;
	}

	public Integer getMonitorType()
	{
		return monitorType;
	}

	public void setMonitorType(Integer monitorType)
	{
		this.monitorType = monitorType;
	}

	public String getMeasureUnit()
	{
		return measureUnit;
	}

	public void setMeasureUnit(String measureUnit)
	{
		this.measureUnit = measureUnit;
	}

	public Double getLimitMinValue()
	{
		return limitMinValue;
	}

	public void setLimitMinValue(Double limitMinValue)
	{
		this.limitMinValue = limitMinValue;
	}

	public Double getLimitMaxValue()
	{
		return limitMaxValue;
	}

	public void setLimitMaxValue(Double limitMaxValue)
	{
		this.limitMaxValue = limitMaxValue;
	}

	public Double getCountValue()
	{
		return countValue;
	}

	public void setCountValue(Double countValue)
	{
		this.countValue = countValue;
	}

	public Equipment getEquipment()
	{
		return equipment;
	}

	public void setEquipment(Equipment equipment)
	{
		this.equipment = equipment;
	}
	
	
}
