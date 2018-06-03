package com.vix.nvix.common.base.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;


/** 
 * 
 * 省市地址信息
 * @author yhl
 * @date 2018-1-9 13:44:02
 *
 */
public class AddressInfo extends BaseEntity {
 
	private static final long serialVersionUID = 1L;
	
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 顺序 */
	private Long orderBy;
	/** 首字母 */
	private String firstLetter;
	/** 备注 */
	private String memo;
	/** 上级地址信息 */
	private AddressInfo parentAddressInfo;
	/** 下级市区地址信息 */
	private Set<AddressInfo> subAddressInfos = new HashSet<AddressInfo>();

	public AddressInfo(){}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Long getOrderBy() {
		return orderBy;
	}

	@Override
	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public AddressInfo getParentAddressInfo() {
		return parentAddressInfo;
	}

	public void setParentAddressInfo(AddressInfo parentAddressInfo) {
		this.parentAddressInfo = parentAddressInfo;
	}

	public Set<AddressInfo> getSubAddressInfos() {
		return subAddressInfos;
	}

	public void setSubAddressInfos(Set<AddressInfo> subAddressInfos) {
		this.subAddressInfos = subAddressInfos;
	}
	
	public String getShowName(){
		String showName = this.getName();
		if(null != this.parentAddressInfo){
			showName = parentAddressInfo.getName() + " " + showName;
			if(null != parentAddressInfo.getParentAddressInfo()){
				showName = parentAddressInfo.getParentAddressInfo().getName() + " " + showName;
			}
		}
		return showName;
	}
	
	public String getShowIds(){
		String showIds = this.getId().toString();
		if(null != this.parentAddressInfo){
			showIds = parentAddressInfo.getId() + " " + showIds;
			if(null != parentAddressInfo.getParentAddressInfo()){
				showIds = parentAddressInfo.getParentAddressInfo().getId() + " " + showIds;
			}
		}
		return showIds;
	}
	
	public String getTopAddressInfoId(){
		String id = "";
		if(null != id){
			id = this.getId();
			if(null != parentAddressInfo && null != parentAddressInfo.getId()){
				id = parentAddressInfo.getId();
				if(null != parentAddressInfo.getParentAddressInfo() && null != parentAddressInfo.getParentAddressInfo().getId()){
					id = parentAddressInfo.getParentAddressInfo().getId();
				}
			}
		}
		return id;
	}
}
