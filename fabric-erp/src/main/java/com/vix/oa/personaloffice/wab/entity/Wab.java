package com.vix.oa.personaloffice.wab.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;
import com.vix.wechat.base.entity.WxpQyPicture;

/**
 * 
 * @ClassName: Wab
 * @Description: 通讯簿 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-5-22 下午5:01:37
 */
public class Wab extends BaseEntity {

	private static final long serialVersionUID = 87031862813442337L;
	
	/**公司*/
	public String company;
	
	/**职位*/
	public String position;
	
	/**手机号码*/
	public String mobileno;
	
	/**手机号码*/
	public String mobileno1;
	
	/**手机号码*/
	public String mobileno2;
	
	/**手机号码*/
	public String mobileno3;
	
	/**手机号码*/
	public String mobileno4;
	
	/**手机号码*/
	public String mobileno5;
	
	/**公司电话*/
	public String calls;
	
	/**公司电话*/
	public String calls1;
	
	/**公司电话*/
	public String calls2;
	
	/**公司电话*/
	public String calls3;
	
	/**公司电话*/
	public String calls4;
	
	/**公司电话*/
	public String calls5;
	
	/**个人邮箱*/
	public String email;
	
	/**个人邮箱*/
	public String email1;
	
	/**个人邮箱*/
	public String email2;
	
	/**个人邮箱*/
	public String email3;
	
	/**个人邮箱*/
	public String email4;
	
	/**个人邮箱*/
	public String email5;
	
	/**QQ*/
	public String qq;
	
	/**QQ*/
	public String qq1;
	
	/**QQ*/
	public String qq2;
	
	/**QQ*/
	public String qq3;
	
	/**QQ*/
	public String qq4;
	
	/**QQ*/
	public String qq5;
	
	/**备注*/
	public String memo;
	
	/**类型 0男1女*/
	public String wabtype;
	
	/** 状态 0私有1公共*/
	private String statuss;

	/** 子分类集合 */
	private Set<Wab> subCategorys = new HashSet<Wab>();
	
	/** 父分类 */
	private Wab parentCategory;
	
	/** 手机号*/
	private Set<PhoneNumber> phoneNumber = new HashSet<PhoneNumber>();
	
	/** 发布人id */
	private String uploadPersonId;	
	/** id发布人 */
	private String uploadPerson;
	
	private String uploadPersonName;
	
	/** 自定义 */
	private String custom;	
	
	/** 自定义 */
	private String custom1;	
	
	/** 自定义 */
	private String custom2;	
	
	/** 自定义 */
	private String custom3;	
	
	/** 自定义 */
	private String custom4;	
	
	/** 自定义 */
	private String custom5;	
	
	/** 自定义 */
	private String custom6;	
	
	/** 自定义 */
	private String custom7;	
	
	/** 自定义 */
	private String custom8;	
	
	/** 自定义 */
	private String custom9;	
	
	
	 /**
     * 发布人员类型
     * "O\":\"部门\",\"R\":\"角色\",\"E\":\"人员
     */
    private String pubType;
    
    /** 发布对象的id集合 */
    private String pubIds;
    
	/**
	 * 图片
	 */
	private Set<WxpQyPicture> subWxpQyPictures = new HashSet<WxpQyPicture>();
	
	/**
	 * 照片路径
	 */
	private String pictureurl;
	
	private Employee employee;

	public Wab() {
		super();
	}

	public Wab(String id) {
		super();
		setId(id);
	}

	public Set<Wab> getSubCategorys() {
		return subCategorys;
	}

	public void setSubCategorys(Set<Wab> subCategorys) {
		this.subCategorys = subCategorys;
	}

	public Wab getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Wab parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Set<PhoneNumber> getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Set<PhoneNumber> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWabtype() {
		return wabtype;
	}

	public void setWabtype(String wabtype) {
		this.wabtype = wabtype;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getCalls() {
		return calls;
	}

	public void setCalls(String calls) {
		this.calls = calls;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
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

	public String getPubType() {
		return pubType;
	}

	public void setPubType(String pubType) {
		this.pubType = pubType;
	}

	public String getPubIds() {
		return pubIds;
	}

	public void setPubIds(String pubIds) {
		this.pubIds = pubIds;
	}

	public String getMobileno1() {
		return mobileno1;
	}

	public void setMobileno1(String mobileno1) {
		this.mobileno1 = mobileno1;
	}

	public String getMobileno2() {
		return mobileno2;
	}

	public void setMobileno2(String mobileno2) {
		this.mobileno2 = mobileno2;
	}

	public String getMobileno3() {
		return mobileno3;
	}

	public void setMobileno3(String mobileno3) {
		this.mobileno3 = mobileno3;
	}

	public String getMobileno4() {
		return mobileno4;
	}

	public void setMobileno4(String mobileno4) {
		this.mobileno4 = mobileno4;
	}

	public String getMobileno5() {
		return mobileno5;
	}

	public void setMobileno5(String mobileno5) {
		this.mobileno5 = mobileno5;
	}

	public String getCalls1() {
		return calls1;
	}

	public void setCalls1(String calls1) {
		this.calls1 = calls1;
	}

	public String getCalls2() {
		return calls2;
	}

	public void setCalls2(String calls2) {
		this.calls2 = calls2;
	}

	public String getCalls3() {
		return calls3;
	}

	public void setCalls3(String calls3) {
		this.calls3 = calls3;
	}

	public String getCalls4() {
		return calls4;
	}

	public void setCalls4(String calls4) {
		this.calls4 = calls4;
	}

	public String getCalls5() {
		return calls5;
	}

	public void setCalls5(String calls5) {
		this.calls5 = calls5;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getEmail3() {
		return email3;
	}

	public void setEmail3(String email3) {
		this.email3 = email3;
	}

	public String getEmail4() {
		return email4;
	}

	public void setEmail4(String email4) {
		this.email4 = email4;
	}

	public String getEmail5() {
		return email5;
	}

	public void setEmail5(String email5) {
		this.email5 = email5;
	}

	public String getQq1() {
		return qq1;
	}

	public void setQq1(String qq1) {
		this.qq1 = qq1;
	}

	public String getQq2() {
		return qq2;
	}

	public void setQq2(String qq2) {
		this.qq2 = qq2;
	}

	public String getQq3() {
		return qq3;
	}

	public void setQq3(String qq3) {
		this.qq3 = qq3;
	}

	public String getQq4() {
		return qq4;
	}

	public void setQq4(String qq4) {
		this.qq4 = qq4;
	}

	public String getQq5() {
		return qq5;
	}

	public void setQq5(String qq5) {
		this.qq5 = qq5;
	}

	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	public String getCustom1() {
		return custom1;
	}

	public void setCustom1(String custom1) {
		this.custom1 = custom1;
	}

	public String getCustom2() {
		return custom2;
	}

	public void setCustom2(String custom2) {
		this.custom2 = custom2;
	}

	public String getCustom3() {
		return custom3;
	}

	public void setCustom3(String custom3) {
		this.custom3 = custom3;
	}

	public String getCustom4() {
		return custom4;
	}

	public void setCustom4(String custom4) {
		this.custom4 = custom4;
	}

	public String getCustom5() {
		return custom5;
	}

	public void setCustom5(String custom5) {
		this.custom5 = custom5;
	}

	public String getCustom6() {
		return custom6;
	}

	public void setCustom6(String custom6) {
		this.custom6 = custom6;
	}

	public String getCustom7() {
		return custom7;
	}

	public void setCustom7(String custom7) {
		this.custom7 = custom7;
	}

	public String getCustom8() {
		return custom8;
	}

	public void setCustom8(String custom8) {
		this.custom8 = custom8;
	}

	public String getCustom9() {
		return custom9;
	}

	public void setCustom9(String custom9) {
		this.custom9 = custom9;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set<WxpQyPicture> getSubWxpQyPictures() {
		return subWxpQyPictures;
	}

	public void setSubWxpQyPictures(Set<WxpQyPicture> subWxpQyPictures) {
		this.subWxpQyPictures = subWxpQyPictures;
	}

	public String getPictureurl() {
		return pictureurl;
	}

	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}

	public String getStatuss() {
		return statuss;
	}

	public void setStatuss(String statuss) {
		this.statuss = statuss;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
