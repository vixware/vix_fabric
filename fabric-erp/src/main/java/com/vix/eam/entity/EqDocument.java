package com.vix.eam.entity;

import java.util.Date;

import com.vix.eam.common.entity.EamBaseEntity;


public class EqDocument extends EamBaseEntity
{
	private String fileType;		//id文档类型,设计图、手册等等，应可以定制
	private String title;		//文档名称
	private String fileName;		//文件名称
	private String filePath;		//文件路径
	private Date uploadTime;		//上传时间
	private String uploadPersonId;	//上传人id
	private String uploadPerson;		//id上传人
	private String note;		//id备注

	//private String eqId;		//id设备id
	private Equipment equipment;

	public String getFileType()
	{
		return fileType;
	}

	public void setFileType(String fileType)
	{
		this.fileType = fileType;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public Date getUploadTime()
	{
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime)
	{
		this.uploadTime = uploadTime;
	}

	public String getUploadPerson()
	{
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson)
	{
		this.uploadPerson = uploadPerson;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public Equipment getEquipment()
	{
		return equipment;
	}

	public void setEquipment(Equipment equipment)
	{
		this.equipment = equipment;
	}

	public String getUploadPersonId()
	{
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId)
	{
		this.uploadPersonId = uploadPersonId;
	}
	
	
}
