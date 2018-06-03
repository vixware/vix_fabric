package com.vix.eam.entity;

import java.util.Date;

import com.vix.eam.common.entity.EamBaseEntity;

public class Equipment extends EamBaseEntity{
	private String eqCode;		// '设备编码',
	private String eqSerialNo;		// '+设备序号',
	private String eqPartSn;		// '-设备零件号',
	private String codeType;		// '+编码类型',
	private String eqName;		// '设备名称',
	private String capitalAssetCode;		// '+固定资产编码',
	private String eqLabel;		// '+设备标签信息',
	private String eqRoomCode;		// '-安装房间号',
	private String eqPlaceCode;		// '安装位置编号',
	private String modelnum;		// '型号规格',
	private String eqType;		// '设备类型',
	private String eqCategory;		// '设备类别',
	private String eqGroupCode;		// '设备组号',
	private String eqGroupName;		// '-设备组名称',
	private String eqLevel;		// '+设备层',
	private String parentEquipment;		// '父设备编码',
	private String parentEqname;		// '父设备名称',
	private String isVirtualEquipment;		// '+是否为虚拟设备',
	private String eqSatus;		// '设备状态',
	private String address;		// '+地址',
	private Date purchDate;		// '购买日期',
	private Date putinDate;		// '启用日期',
	private Date installDate;		// '-安装日期',
	private Date warrantyDate;		// '保修期',
	private String eqAccount;		// '-设备账号',
	private String totalCost;		// '-设备成本',
	private Double weight;		// '设备重量',
	private String wunit;		// '重量单位',
	private String measureUnit;		// '-度量单位',
	private String eqSize;		// '-设备尺寸',
	private Double upLimit;		// '-上限值',
	private Double downLimit;		// '-下限值',
	private String kehuMingcheng;		// '-客户名称',
	private String caigouDanhao;		// '-采购单号',
	private String caigouDanwei;		// '-采购单位(计量单位)',
	private Double caigouJiage;		// '-采购价格',
	private Date caigouRiqi;		// '-采购日期',
	private String kucunDanwei	;		// '-库存单位',
	private Date outFactoryDate;		// '出厂日期',
	private String responsiblePerson;		// '+负责人',
	private String subType;		// '+设备子类',
	private String chuchangBianhao;		// '-出厂编号',
	private String zichanBianhao;		// '-资产编号',
	private String madeCode;		// '制造商编号',
	private String madeName;		// '制造商名称',
	private String supplyCode;		// '+供应商编号',
	private String supplyName;		// '供应商名称',
	private String sparesCode;		// '设备备件包',
	private String contractCode;		// '+服务合同编码',
	private String costCenter;		// '+成本中心',
	private Integer priority;		// '+优先级',
	private Integer criticality;		// '重要程度',
	private Integer hasSub;		// '设备下是否有其他设备',
	private Integer isConnected;		// '是否与其他设备有连接',
	private Integer connectionType;		// '+链接类型',
	private String techClass;		// '-技术等级',
	private String techParam;		// '+技术参数',
	private String maintainOrg;		// '+维护单位',
	private Integer isMove;		// '+设备专业（动设备或静设备）',
	private Integer intactStatus;		// '+完好状态',
	private String belongSite;		// '+上级域名',
	private String belongEquipment;		// '+所属装置',
	private String description;		// '-设备描述'
	
	
	public String getEqCode() {
		return eqCode;
	}
	public void setEqCode(String eqCode) {
		this.eqCode = eqCode;
	}
	public String getEqSerialNo() {
		return eqSerialNo;
	}
	public void setEqSerialNo(String eqSerialNo) {
		this.eqSerialNo = eqSerialNo;
	}
	public String getEqPartSn() {
		return eqPartSn;
	}
	public void setEqPartSn(String eqPartSn) {
		this.eqPartSn = eqPartSn;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getEqName() {
		return eqName;
	}
	public void setEqName(String eqName) {
		this.eqName = eqName;
	}
	public String getCapitalAssetCode() {
		return capitalAssetCode;
	}
	public void setCapitalAssetCode(String capitalAssetCode) {
		this.capitalAssetCode = capitalAssetCode;
	}
	public String getEqLabel() {
		return eqLabel;
	}
	public void setEqLabel(String eqLabel) {
		this.eqLabel = eqLabel;
	}
	public String getEqRoomCode() {
		return eqRoomCode;
	}
	public void setEqRoomCode(String eqRoomCode) {
		this.eqRoomCode = eqRoomCode;
	}
	public String getEqPlaceCode() {
		return eqPlaceCode;
	}
	public void setEqPlaceCode(String eqPlaceCode) {
		this.eqPlaceCode = eqPlaceCode;
	}
	public String getModelnum() {
		return modelnum;
	}
	public void setModelnum(String modelnum) {
		this.modelnum = modelnum;
	}
	public String getEqType() {
		return eqType;
	}
	public void setEqType(String eqType) {
		this.eqType = eqType;
	}
	public String getEqCategory() {
		return eqCategory;
	}
	public void setEqCategory(String eqCategory) {
		this.eqCategory = eqCategory;
	}
	public String getEqGroupCode() {
		return eqGroupCode;
	}
	public void setEqGroupCode(String eqGroupCode) {
		this.eqGroupCode = eqGroupCode;
	}
	public String getEqGroupName() {
		return eqGroupName;
	}
	public void setEqGroupName(String eqGroupName) {
		this.eqGroupName = eqGroupName;
	}
	public String getEqLevel() {
		return eqLevel;
	}
	public void setEqLevel(String eqLevel) {
		this.eqLevel = eqLevel;
	}
	public String getParentEquipment() {
		return parentEquipment;
	}
	public void setParentEquipment(String parentEquipment) {
		this.parentEquipment = parentEquipment;
	}
	public String getParentEqname() {
		return parentEqname;
	}
	public void setParentEqname(String parentEqname) {
		this.parentEqname = parentEqname;
	}
	public String getIsVirtualEquipment() {
		return isVirtualEquipment;
	}
	public void setIsVirtualEquipment(String isVirtualEquipment) {
		this.isVirtualEquipment = isVirtualEquipment;
	}
	public String getEqSatus() {
		return eqSatus;
	}
	public void setEqSatus(String eqSatus) {
		this.eqSatus = eqSatus;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getPurchDate() {
		return purchDate;
	}
	public void setPurchDate(Date purchDate) {
		this.purchDate = purchDate;
	}
	public Date getPutinDate() {
		return putinDate;
	}
	public void setPutinDate(Date putinDate) {
		this.putinDate = putinDate;
	}
	public Date getInstallDate() {
		return installDate;
	}
	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}
	public Date getWarrantyDate() {
		return warrantyDate;
	}
	public void setWarrantyDate(Date warrantyDate) {
		this.warrantyDate = warrantyDate;
	}
	public String getEqAccount() {
		return eqAccount;
	}
	public void setEqAccount(String eqAccount) {
		this.eqAccount = eqAccount;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getWunit() {
		return wunit;
	}
	public void setWunit(String wunit) {
		this.wunit = wunit;
	}
	public String getMeasureUnit() {
		return measureUnit;
	}
	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}
	public String getEqSize() {
		return eqSize;
	}
	public void setEqSize(String eqSize) {
		this.eqSize = eqSize;
	}
	public Double getUpLimit() {
		return upLimit;
	}
	public void setUpLimit(Double upLimit) {
		this.upLimit = upLimit;
	}
	public Double getDownLimit() {
		return downLimit;
	}
	public void setDownLimit(Double downLimit) {
		this.downLimit = downLimit;
	}
	public String getKehuMingcheng() {
		return kehuMingcheng;
	}
	public void setKehuMingcheng(String kehuMingcheng) {
		this.kehuMingcheng = kehuMingcheng;
	}
	public String getCaigouDanhao() {
		return caigouDanhao;
	}
	public void setCaigouDanhao(String caigouDanhao) {
		this.caigouDanhao = caigouDanhao;
	}
	public String getCaigouDanwei() {
		return caigouDanwei;
	}
	public void setCaigouDanwei(String caigouDanwei) {
		this.caigouDanwei = caigouDanwei;
	}
	public Double getCaigouJiage() {
		return caigouJiage;
	}
	public void setCaigouJiage(Double caigouJiage) {
		this.caigouJiage = caigouJiage;
	}
	public Date getCaigouRiqi() {
		return caigouRiqi;
	}
	public void setCaigouRiqi(Date caigouRiqi) {
		this.caigouRiqi = caigouRiqi;
	}
	public String getKucunDanwei() {
		return kucunDanwei;
	}
	public void setKucunDanwei(String kucunDanwei) {
		this.kucunDanwei = kucunDanwei;
	}
	public Date getOutFactoryDate() {
		return outFactoryDate;
	}
	public void setOutFactoryDate(Date outFactoryDate) {
		this.outFactoryDate = outFactoryDate;
	}
	public String getResponsiblePerson() {
		return responsiblePerson;
	}
	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getChuchangBianhao() {
		return chuchangBianhao;
	}
	public void setChuchangBianhao(String chuchangBianhao) {
		this.chuchangBianhao = chuchangBianhao;
	}
	public String getZichanBianhao() {
		return zichanBianhao;
	}
	public void setZichanBianhao(String zichanBianhao) {
		this.zichanBianhao = zichanBianhao;
	}
	public String getMadeCode() {
		return madeCode;
	}
	public void setMadeCode(String madeCode) {
		this.madeCode = madeCode;
	}
	public String getMadeName() {
		return madeName;
	}
	public void setMadeName(String madeName) {
		this.madeName = madeName;
	}
	public String getSupplyCode() {
		return supplyCode;
	}
	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}
	public String getSupplyName() {
		return supplyName;
	}
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	public String getSparesCode() {
		return sparesCode;
	}
	public void setSparesCode(String sparesCode) {
		this.sparesCode = sparesCode;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getCriticality() {
		return criticality;
	}
	public void setCriticality(Integer criticality) {
		this.criticality = criticality;
	}
	public Integer getHasSub() {
		return hasSub;
	}
	public void setHasSub(Integer hasSub) {
		this.hasSub = hasSub;
	}
	public Integer getIsConnected() {
		return isConnected;
	}
	public void setIsConnected(Integer isConnected) {
		this.isConnected = isConnected;
	}
	public String getTechClass() {
		return techClass;
	}
	public void setTechClass(String techClass) {
		this.techClass = techClass;
	}
	public String getTechParam() {
		return techParam;
	}
	public void setTechParam(String techParam) {
		this.techParam = techParam;
	}
	public String getMaintainOrg() {
		return maintainOrg;
	}
	public void setMaintainOrg(String maintainOrg) {
		this.maintainOrg = maintainOrg;
	}
	public Integer getIsMove() {
		return isMove;
	}
	public void setIsMove(Integer isMove) {
		this.isMove = isMove;
	}
	public Integer getIntactStatus() {
		return intactStatus;
	}
	public void setIntactStatus(Integer intactStatus) {
		this.intactStatus = intactStatus;
	}
	public String getBelongSite() {
		return belongSite;
	}
	public void setBelongSite(String belongSite) {
		this.belongSite = belongSite;
	}
	public String getBelongEquipment() {
		return belongEquipment;
	}
	public void setBelongEquipment(String belongEquipment) {
		this.belongEquipment = belongEquipment;
	}
	@Override
	public String getDescription() {
		return description;
	}
	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getConnectionType() {
		return connectionType;
	}
	public void setConnectionType(Integer connectionType) {
		this.connectionType = connectionType;
	}

	
	/**子设备*/
	/**设备分类*/
	
	
}
