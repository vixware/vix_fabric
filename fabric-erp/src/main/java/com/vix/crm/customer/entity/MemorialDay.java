package com.vix.crm.customer.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.crm.base.entity.MemorialDayType;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

public class MemorialDay extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 客户 */
	private CustomerAccount customerAccount;
	/** 联系人 */
	private ContactPerson contactPerson ;
	/** 纪念日类型 */
	private MemorialDayType memorialDayType;
	/** 0：公历 1：农历 */
	private String dateType;
	/** 下一个纪念日  */
	private String nextDateStr;
	/** 纪念日 */
	private Date date ;
	/** 备注 */
	private String memo;
	
	public MemorialDay(){}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public MemorialDayType getMemorialDayType() {
		return memorialDayType;
	}

	public void setMemorialDayType(MemorialDayType memorialDayType) {
		this.memorialDayType = memorialDayType;
	}

	public String getDateType() {
		return dateType;
	}
	
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public Date getDate() {
		return date;
	}

	public String getDateStr(){
		if(null != date){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);  
		}else {
			return "";
		}
			
	}
	
	/*public String getNextDateStr(){
		if(null != date){
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
			int year = DateUtil.getYear();
			String time = year + "-" + sdf.format(date);
			long start = DateUtil.praseSqlDate(time).getTime();
			long end = DateUtil.praseSqlDate(DateUtil.format(new Date())).getTime();
			if(start < end){
				return (year + 1) + "-" + sdf.format(date);
			}else{
				return time;
			}
		}else {
			return "";
		}
		
	}*/
	
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getNextDateStr() {
		return nextDateStr;
	}

	public void setNextDateStr(String nextDateStr) {
		this.nextDateStr = nextDateStr;
	}
}
