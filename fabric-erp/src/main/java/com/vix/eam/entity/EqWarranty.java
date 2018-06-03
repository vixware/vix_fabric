package com.vix.eam.entity;

import java.util.Date;

import com.vix.eam.common.entity.EamBaseEntity;

public class EqWarranty extends EamBaseEntity
{
	private String installObj;		//保修项目
	private String serialNo;		//设备序列号
	private String contractSn;		//保修合同编号
	private String responsiPerson;		//负责人
	private String phone;		//联系电话
	private String content;		//保修故障信息：描述了哪种工作类型、现象、特征属于保修的范围
	private String priority;		//优先级
	private Integer warrantyMode;		//保修类型,制造保修、附加保修、外延的损坏保修
	private String feeMode;		//免费、部分收费
	private String warrantyBy;		//保修负责单位
	private Integer warrantyType;		//保修有效性类型，有效期、周期（计数器）
	private Date warrantyExpire;		//保修有效期截止日期
	private Integer countValue;		//保修周期值，如保修4000公里中的4000
	private String countUnit;		//保修周期单位，如保修4000公里中的单位公里

	//private String eqId;		//id设备id
	private Equipment equipment;

	public String getInstallObj()
	{
		return installObj;
	}

	public void setInstallObj(String installObj)
	{
		this.installObj = installObj;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getContractSn()
	{
		return contractSn;
	}

	public void setContractSn(String contractSn)
	{
		this.contractSn = contractSn;
	}

	public String getResponsiPerson()
	{
		return responsiPerson;
	}

	public void setResponsiPerson(String responsiPerson)
	{
		this.responsiPerson = responsiPerson;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getPriority()
	{
		return priority;
	}

	public void setPriority(String priority)
	{
		this.priority = priority;
	}

	public Integer getWarrantyMode()
	{
		return warrantyMode;
	}

	public void setWarrantyMode(Integer warrantyMode)
	{
		this.warrantyMode = warrantyMode;
	}

	public String getFeeMode()
	{
		return feeMode;
	}

	public void setFeeMode(String feeMode)
	{
		this.feeMode = feeMode;
	}

	public String getWarrantyBy()
	{
		return warrantyBy;
	}

	public void setWarrantyBy(String warrantyBy)
	{
		this.warrantyBy = warrantyBy;
	}

	public Integer getWarrantyType()
	{
		return warrantyType;
	}

	public void setWarrantyType(Integer warrantyType)
	{
		this.warrantyType = warrantyType;
	}

	public Date getWarrantyExpire()
	{
		return warrantyExpire;
	}

	public void setWarrantyExpire(Date warrantyExpire)
	{
		this.warrantyExpire = warrantyExpire;
	}

	public Integer getCountValue()
	{
		return countValue;
	}

	public void setCountValue(Integer countValue)
	{
		this.countValue = countValue;
	}

	public String getCountUnit()
	{
		return countUnit;
	}

	public void setCountUnit(String countUnit)
	{
		this.countUnit = countUnit;
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
