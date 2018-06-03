package com.vix.nvix.wx.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 省市地址信息
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpAddressInfo
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */
public class WxpAddressInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 顺序 */
	private Long orderBy;
	/** 备注 */
	private String memo;
	/** 上级地址信息 */
	private WxpAddressInfo parentAddressInfo;
	/** 下级市区地址信息 */
	private Set<WxpAddressInfo> subAddressInfos = new HashSet<WxpAddressInfo>();

	public WxpAddressInfo() {
	}

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

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public WxpAddressInfo getParentAddressInfo() {
		return parentAddressInfo;
	}

	public void setParentAddressInfo(WxpAddressInfo parentAddressInfo) {
		this.parentAddressInfo = parentAddressInfo;
	}

	public Set<WxpAddressInfo> getSubAddressInfos() {
		return subAddressInfos;
	}

	public void setSubAddressInfos(Set<WxpAddressInfo> subAddressInfos) {
		this.subAddressInfos = subAddressInfos;
	}

	public String getShowName() {
		String showName = this.getName();
		if (null != this.parentAddressInfo) {
			showName = parentAddressInfo.getName() + " " + showName;
			if (null != parentAddressInfo.getParentAddressInfo()) {
				showName = parentAddressInfo.getParentAddressInfo().getName() + " " + showName;
			}
		}
		return showName;
	}

	public String getShowIds() {
		String showIds = this.getId().toString();
		if (null != this.parentAddressInfo) {
			showIds = parentAddressInfo.getId() + " " + showIds;
			if (null != parentAddressInfo.getParentAddressInfo()) {
				showIds = parentAddressInfo.getParentAddressInfo().getId() + " " + showIds;
			}
		}
		return showIds;
	}

	public String getTopAddressInfoId() {
		String id = "";
		if (null != id) {
			id = this.getId();
			if (null != parentAddressInfo && null != parentAddressInfo.getId()) {
				id = parentAddressInfo.getId();
				if (null != parentAddressInfo.getParentAddressInfo() && null != parentAddressInfo.getParentAddressInfo().getId()) {
					id = parentAddressInfo.getParentAddressInfo().getId();
				}
			}
		}
		return id;
	}
}
