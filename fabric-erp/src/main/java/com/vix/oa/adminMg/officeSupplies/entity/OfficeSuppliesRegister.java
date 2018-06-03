package com.vix.oa.adminMg.officeSupplies.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: OfficeSupplies
 * @Description: 办公用品领用、借用、归还
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-5-13 上午10:08:33
 */
public class OfficeSuppliesRegister extends BaseEntity {

	private static final long serialVersionUID = 87031862813442337L;

	/** 领用、借用、归还标志 */
	private String flag;
	/** 借用领用人*/
	private String recipientsWho;
	/** 编码*/
	private String encoding;
	/** 主题*/
	private String theme;
	/** 经办人*/
	private String  operator;
	/** 经办人id */
	private String uploadPersonId;	
	/** id经办人 */
	private String uploadPerson;
	/**经办人*/
	private String uploadPersonName;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/**办公用品领用明细*/
	private Set<OfficeSuppliesRegisterItem> officeSuppliesRegisterItem = new HashSet<OfficeSuppliesRegisterItem>();
	/** 办公用品借用明细*/
	private Set<OfficeSuppliesBorrow> officeSuppliesBorrow = new HashSet<OfficeSuppliesBorrow>();
	/** 办公用品日志明细*/
	private Set<OfficeList> officeList = new HashSet<OfficeList>();

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}


	public Set<OfficeSuppliesRegisterItem> getOfficeSuppliesRegisterItem() {
		return officeSuppliesRegisterItem;
	}

	public void setOfficeSuppliesRegisterItem(Set<OfficeSuppliesRegisterItem> officeSuppliesRegisterItem) {
		this.officeSuppliesRegisterItem = officeSuppliesRegisterItem;
	}

	public Set<OfficeSuppliesBorrow> getOfficeSuppliesBorrow() {
		return officeSuppliesBorrow;
	}

	public void setOfficeSuppliesBorrow(
			Set<OfficeSuppliesBorrow> officeSuppliesBorrow) {
		this.officeSuppliesBorrow = officeSuppliesBorrow;
	}

	public String getRecipientsWho() {
		return recipientsWho;
	}

	public void setRecipientsWho(String recipientsWho) {
		this.recipientsWho = recipientsWho;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getUploadPersonId() {
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId) {
		this.uploadPersonId = uploadPersonId;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public Set<OfficeList> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(Set<OfficeList> officeList) {
		this.officeList = officeList;
	}

}
