package com.vix.ebusiness.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 商品类目结构
 * 
 * com.vix.ebusiness.entity.ItemCat
 *
 * @author zhanghaibing
 *
 * @date 2014年9月21日
 */
public class ItemCat extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 商品所属类目ID
	 */
	private Long typeId;
	/**
	 * 渠道标识
	 */
	private Long channelTypeId;
	/**
	 * 类目名称
	 */
	private String typeName;
	/**
	 * 是否实体商品
	 */
	private Integer isPhysical;
	/**
	 * SCHEMA标识
	 */
	private Integer schemaId;
	/**
	 * 类型属性
	 */
	private String typeProps;
	/**
	 * 类型规格
	 */
	private String type_spec;
	/**
	 * 类型参数
	 */
	private String typeParams;
	/**
	 * 父类型标识
	 */
	private Long parentTypeId;
	/**
	 * 该类目是否为父类目
	 */
	private Integer isParent;
	/**
	 * 显示属性
	 */
	private Integer sortOrder;
	/**
	 * 设置
	 */
	private String settings;
	/**
	 * 渠道
	 */
	private ChannelDistributor channelDistributor;

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

	public String getType_spec() {
		return type_spec;
	}

	public void setType_spec(String typeSpec) {
		type_spec = typeSpec;
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

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
