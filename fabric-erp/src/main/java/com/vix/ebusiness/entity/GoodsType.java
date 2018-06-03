package com.vix.ebusiness.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 商品类目结构 com.vix.ebusiness.entity.GoodsType
 *
 * @author zhanghaibing
 *
 * @date 2014年9月21日
 */
public class GoodsType extends BaseEntity {
	private static final long serialVersionUID = 1L;
	public Long typeId;
	public Long channelTypeId;
	public String typeName;
	public Integer isPhysical;
	public Integer schemaId;
	public String typeProps;
	public String typeSpec;
	public String typeParams;
	public Long parentTypeId;
	public Integer isParent;
	public Integer sortOrder;
	public String settings;

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getChannelTypeId() {
		return channelTypeId;
	}

	public void setChannelTypeId(Long channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getIsPhysical() {
		return isPhysical;
	}

	public void setIsPhysical(Integer isPhysical) {
		this.isPhysical = isPhysical;
	}

	public Integer getSchemaId() {
		return schemaId;
	}

	public void setSchemaId(Integer schemaId) {
		this.schemaId = schemaId;
	}

	public String getTypeProps() {
		return typeProps;
	}

	public void setTypeProps(String typeProps) {
		this.typeProps = typeProps;
	}

	public String getTypeSpec() {
		return typeSpec;
	}

	public void setTypeSpec(String typeSpec) {
		this.typeSpec = typeSpec;
	}

	public String getTypeParams() {
		return typeParams;
	}

	public void setTypeParams(String typeParams) {
		this.typeParams = typeParams;
	}

	public Long getParentTypeId() {
		return parentTypeId;
	}

	public void setParentTypeId(Long parentTypeId) {
		this.parentTypeId = parentTypeId;
	}

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSettings() {
		return settings;
	}

	public void setSettings(String settings) {
		this.settings = settings;
	}

}
