package com.vix.eam.entity;

import java.util.Date;

import com.vix.eam.common.entity.EamBaseEntity;

public class EqSparePart extends EamBaseEntity
{
	private String partCode;		//备件编码
	private String partName;		//备件名称
	private String specType;		//规格型号
	private Date buyDate;		//购买日期
	private Date instoreDate;		//入库日期
	private Double price;		//价格
	private String depotCode;		//所在仓库
	private String placeCode;		//位置编码
	private String unit;		//单位
	private Integer account;		//数量
	private String madeCode;		//制造商编码
	private String supplyCode;		//供应商编码

	//private String eqId;		//id设备id
	private Equipment equipment;

	public String getPartCode()
	{
		return partCode;
	}

	public void setPartCode(String partCode)
	{
		this.partCode = partCode;
	}

	public String getPartName()
	{
		return partName;
	}

	public void setPartName(String partName)
	{
		this.partName = partName;
	}

	public String getSpecType()
	{
		return specType;
	}

	public void setSpecType(String specType)
	{
		this.specType = specType;
	}

	public Date getBuyDate()
	{
		return buyDate;
	}

	public void setBuyDate(Date buyDate)
	{
		this.buyDate = buyDate;
	}

	public Date getInstoreDate()
	{
		return instoreDate;
	}

	public void setInstoreDate(Date instoreDate)
	{
		this.instoreDate = instoreDate;
	}

	public Double getPrice()
	{
		return price;
	}

	public void setPrice(Double price)
	{
		this.price = price;
	}

	public String getDepotCode()
	{
		return depotCode;
	}

	public void setDepotCode(String depotCode)
	{
		this.depotCode = depotCode;
	}

	public String getPlaceCode()
	{
		return placeCode;
	}

	public void setPlaceCode(String placeCode)
	{
		this.placeCode = placeCode;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public Integer getAccount()
	{
		return account;
	}

	public void setAccount(Integer account)
	{
		this.account = account;
	}

	public String getMadeCode()
	{
		return madeCode;
	}

	public void setMadeCode(String madeCode)
	{
		this.madeCode = madeCode;
	}

	public String getSupplyCode()
	{
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode)
	{
		this.supplyCode = supplyCode;
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
