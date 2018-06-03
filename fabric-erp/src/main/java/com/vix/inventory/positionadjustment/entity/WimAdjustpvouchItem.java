package com.vix.inventory.positionadjustment.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.inventory.warehouse.entity.InvShelf;

/**
 * 货位调整单子表
 * 
 * @author zhanghaibing
 * 
 * @date 2013-7-29
 */
public class WimAdjustpvouchItem extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 货位调整单号
	 */
	private String cvouchcode;
	/**
	 * 物料编码
	 */
	private String itemcode;
	/**
	 * 物料名称
	 */
	private String itemname;
	/**
	 * 存货编码
	 */
	private String cinvcode;
	/**
	 * 调整前货位编码
	 */
	private String cbposcode;
	/**
	 * 调整后货位编码
	 */
	private String caposcode;
	/**
	 * 收发记录子表标识
	 */
	private Integer rdsid;
	/**
	 * 辅计量数量
	 */
	private Float inum;
	/**
	 * 数量
	 */
	private Float iquantity;
	/**
	 * 批号
	 */
	private String cbatch;
	/**
	 * 失效日期
	 */
	private Date ddisdate;
	/**
	 * 货位调整单子表标识
	 */
	private Integer autoid;
	/**
	 * 对应条形码编码
	 */
	private String cbarcode;
	/**
	 * 辅计量单位编码
	 */
	private String cassunit;
	/**
	 * 供应商编码
	 */
	private String cbvencode;
	/**
	 * 对应入库单号
	 */
	private String cinvouchcode;
	/**
	 * 保质期天数
	 */
	private Integer imassdate;
	/**
	 * 生产日期
	 */
	private String dmadedate;
	/**
	 * 保质期单位
	 */
	private Integer cmassunit;
	/**
	 * 存货自由项1
	 */
	private String cfree1;
	/**
	 * 存货自由项2
	 */
	private String cfree2;
	/**
	 * 表体自定义项1
	 */
	private String cdefine1;
	/**
	 * 表体自定义项2
	 */
	private String cdefine2;
	/**
	 * 货位调整单
	 */
	private WimAdjustpvouch wimAdjustpvouch;
	/**
	 * 调整前货位
	 */
	private InvShelf oldInvShelf;
	/**
	 * 调整后货位
	 */
	private InvShelf newInvShelf;

	public WimAdjustpvouchItem() {
	}

	public String getCvouchcode() {
		return cvouchcode;
	}

	public void setCvouchcode(String cvouchcode) {
		this.cvouchcode = cvouchcode;
	}

	public String getCinvcode() {
		return cinvcode;
	}

	public void setCinvcode(String cinvcode) {
		this.cinvcode = cinvcode;
	}

	public String getCbposcode() {
		return cbposcode;
	}

	public void setCbposcode(String cbposcode) {
		this.cbposcode = cbposcode;
	}

	public String getCaposcode() {
		return caposcode;
	}

	public void setCaposcode(String caposcode) {
		this.caposcode = caposcode;
	}

	public Integer getRdsid() {
		return rdsid;
	}

	public void setRdsid(Integer rdsid) {
		this.rdsid = rdsid;
	}

	public Float getInum() {
		return inum;
	}

	public void setInum(Float inum) {
		this.inum = inum;
	}

	public Float getIquantity() {
		return iquantity;
	}

	public void setIquantity(Float iquantity) {
		this.iquantity = iquantity;
	}

	public String getCbatch() {
		return cbatch;
	}

	public void setCbatch(String cbatch) {
		this.cbatch = cbatch;
	}

	public Date getDdisdate() {
		return ddisdate;
	}

	public void setDdisdate(Date ddisdate) {
		this.ddisdate = ddisdate;
	}

	public Integer getAutoid() {
		return autoid;
	}

	public void setAutoid(Integer autoid) {
		this.autoid = autoid;
	}

	public String getCbarcode() {
		return cbarcode;
	}

	public void setCbarcode(String cbarcode) {
		this.cbarcode = cbarcode;
	}

	public String getCassunit() {
		return cassunit;
	}

	public void setCassunit(String cassunit) {
		this.cassunit = cassunit;
	}

	public String getCbvencode() {
		return cbvencode;
	}

	public void setCbvencode(String cbvencode) {
		this.cbvencode = cbvencode;
	}

	public String getCinvouchcode() {
		return cinvouchcode;
	}

	public void setCinvouchcode(String cinvouchcode) {
		this.cinvouchcode = cinvouchcode;
	}

	public Integer getImassdate() {
		return imassdate;
	}

	public void setImassdate(Integer imassdate) {
		this.imassdate = imassdate;
	}

	public String getDmadedate() {
		return dmadedate;
	}

	public void setDmadedate(String dmadedate) {
		this.dmadedate = dmadedate;
	}

	public Integer getCmassunit() {
		return cmassunit;
	}

	public void setCmassunit(Integer cmassunit) {
		this.cmassunit = cmassunit;
	}

	public String getCfree1() {
		return cfree1;
	}

	public void setCfree1(String cfree1) {
		this.cfree1 = cfree1;
	}

	public String getCfree2() {
		return cfree2;
	}

	public void setCfree2(String cfree2) {
		this.cfree2 = cfree2;
	}

	public String getCdefine1() {
		return cdefine1;
	}

	public void setCdefine1(String cdefine1) {
		this.cdefine1 = cdefine1;
	}

	public String getCdefine2() {
		return cdefine2;
	}

	public void setCdefine2(String cdefine2) {
		this.cdefine2 = cdefine2;
	}

	public WimAdjustpvouch getWimAdjustpvouch() {
		return wimAdjustpvouch;
	}

	public void setWimAdjustpvouch(WimAdjustpvouch wimAdjustpvouch) {
		this.wimAdjustpvouch = wimAdjustpvouch;
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public InvShelf getOldInvShelf() {
		return oldInvShelf;
	}

	public void setOldInvShelf(InvShelf oldInvShelf) {
		this.oldInvShelf = oldInvShelf;
	}

	public InvShelf getNewInvShelf() {
		return newInvShelf;
	}

	public void setNewInvShelf(InvShelf newInvShelf) {
		this.newInvShelf = newInvShelf;
	}

}