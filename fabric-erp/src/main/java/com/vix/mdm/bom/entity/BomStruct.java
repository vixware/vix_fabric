package com.vix.mdm.bom.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.mdm.item.entity.Item;

/** Bom结构(离散类) */
public class BomStruct extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 配置Bom名称 */
	private String configItemBomName;
	/** Bom类型 */
	private String bomType;
	/** Bom类别 */
	private String bomClass;
	/** 母件损耗率 */
	private Double parentPartAttritionRate;
	/** 规格型号 */
	private String specification;
	/** 版本 */
	private String version;
	/** 版本日期 */
	private Date versionDate;
	/** 版本说明 */
	private String versionDescription;
	/** 默认基础数量 */
	private Double defaultBaseAmount;
	/** 产品配置限定模型 */
	private String productConfigureModel;
	/** 物料 */
	private Item item;
	/** Bom配置节点 */
	private Set<BomNode> bomNodes = new HashSet<BomNode>();
	
	public BomStruct(){}

	public String getConfigItemBomName() {
		return configItemBomName;
	}

	public void setConfigItemBomName(String configItemBomName) {
		this.configItemBomName = configItemBomName;
	}

	public String getBomType() {
		return bomType;
	}

	public void setBomType(String bomType) {
		this.bomType = bomType;
	}

	public String getBomClass() {
		return bomClass;
	}

	public void setBomClass(String bomClass) {
		this.bomClass = bomClass;
	}

	public Set<BomNode> getBomNodes() {
		return bomNodes;
	}

	public void setBomNodes(Set<BomNode> bomNodes) {
		this.bomNodes = bomNodes;
	}

	public Double getParentPartAttritionRate() {
		return parentPartAttritionRate;
	}

	public void setParentPartAttritionRate(Double parentPartAttritionRate) {
		this.parentPartAttritionRate = parentPartAttritionRate;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

	public Date getVersionDate() {
		return versionDate;
	}
	
	public String getVersionDateStr() {
		if(versionDate != null){
			return DateUtil.format(versionDate);
		}
		return "";
	}

	public void setVersionDate(Date versionDate) {
		this.versionDate = versionDate;
	}

	public String getVersionDescription() {
		return versionDescription;
	}

	public void setVersionDescription(String versionDescription) {
		this.versionDescription = versionDescription;
	}

	public Double getDefaultBaseAmount() {
		return defaultBaseAmount;
	}

	public void setDefaultBaseAmount(Double defaultBaseAmount) {
		this.defaultBaseAmount = defaultBaseAmount;
	}
 
	public String getProductConfigureModel() {
		return productConfigureModel;
	}

	public void setProductConfigureModel(String productConfigureModel) {
		this.productConfigureModel = productConfigureModel;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Set<BomNode> getbomNodes() {
		return bomNodes;
	}

	public void setbomNodes(Set<BomNode> bomNodes) {
		this.bomNodes = bomNodes;
	}
}
