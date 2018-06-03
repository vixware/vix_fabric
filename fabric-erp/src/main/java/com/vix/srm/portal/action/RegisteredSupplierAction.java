package com.vix.srm.portal.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.mdm.srm.share.entity.SupplierAptitudeInfo;
import com.vix.srm.portal.controller.RegisteredSupplierController;

/**
 * @Description: 供应商网上注册
 * @author ivan 
 * @date 2013-08-20
 */
@Controller
@Scope("prototype")
public class RegisteredSupplierAction extends BaseAction {

	private static final long serialVersionUID = -8275845612901424728L;
	Logger loggerSupplier = Logger.getLogger(Supplier.class);
	/** 注入service */
	@Autowired
	private RegisteredSupplierController registeredSupplierController;
	
	private Supplier supplier;
	private SupplierAptitudeInfo supplierAptitudeInfo;
	private Item item;

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public SupplierAptitudeInfo getSupplierAptitudeInfo() {
		return supplierAptitudeInfo;
	}

	public void setSupplierAptitudeInfo(SupplierAptitudeInfo supplierAptitudeInfo) {
		this.supplierAptitudeInfo = supplierAptitudeInfo;
	}

	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 处理修改供应商操作 */
	public String saveOrUpdateSupplier() {
		boolean isSave = true;
		try {
			if (null != supplier.getId()) {
				isSave = false;
			}
			// 设置供应商状态为为审批
			supplier.setStatus("S1");
			supplier = registeredSupplierController.doSaveSupplier(supplier);
			loggerSupplier.info("对供应商进行了修改！");
			if (isSave) {
				setMessage(SAVE_SUCCESS + "," + supplier.getId() + ",");
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	/** 处理修改供应商资质操作 */
	public String saveOrUpdateSupplierAptitudeInfo() {
		boolean isSave = true;
		try {
			if (null != supplierAptitudeInfo.getId()) {
				isSave = false;
			}
			// 设置供应商状态为为审批
			supplierAptitudeInfo.setStatus("S1");
			supplierAptitudeInfo = registeredSupplierController.doSaveSupplierAptitudeInfo(supplierAptitudeInfo);
			loggerSupplier.info("对供应商资质进行了修改！");
			if (isSave) {
				setMessage(SAVE_SUCCESS + "," + supplierAptitudeInfo.getId() + ",");
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	/** 处理修改物料操作 */
	public String saveOrUpdateItem() {
		boolean isSave = true;
		try {
			if (null != item.getId()) {
				isSave = false;
			}
			// 设置供应商状态为为审批
			item.setStatus("S1");
			item = registeredSupplierController.doSaveItem(item);
			loggerSupplier.info("对物料进行了修改！");
			if (isSave) {
				setMessage(SAVE_SUCCESS + "," + item.getId() + ",");
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
}
