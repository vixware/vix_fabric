package com.vix.oa.adminMg.requisitionCar.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.requisitionCar.controller.VehicleRequestController;
import com.vix.oa.adminMg.typeSettings.entity.MaintenanceType;

@Controller
@Scope("prototype")
public class MaintenanceTypeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(VehicleRequestController.class);

	@Autowired
	private VehicleRequestController vehicleRequestController;
	private List<MaintenanceType> maintenanceTypeList;
	private MaintenanceType maintenanceType;
	private String id;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			maintenanceTypeList = baseHibernateService.findAllByEntityClass(MaintenanceType.class);
			Map<String, Object> params = getParams();
			Pager pager = vehicleRequestController.doSubMaintenanceTypeList(params, getPager());
			logger.info("获取维护类型列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				maintenanceType = vehicleRequestController.doListMaintenanceTypeById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "goSaveOrUpdate";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (maintenanceType != null && null != maintenanceType.getId()) {
				isSave = false;
			}

			maintenanceType = vehicleRequestController.doSaveMaintenanceType(maintenanceType);
			logger.info("对维护类型进行修改！");
			if (isSave) {
				renderText(SAVE_SUCCESS);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			MaintenanceType maintenanceType = vehicleRequestController.doListMaintenanceTypeById(id);
			if (null != maintenanceType) {
				vehicleRequestController.doDeleteByMaintenanceType(maintenanceType);
				logger.info("");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除维护类型设置信息");
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<MaintenanceType> getMaintenanceTypeList() {
		return maintenanceTypeList;
	}

	public void setMaintenanceTypeList(List<MaintenanceType> maintenanceTypeList) {
		this.maintenanceTypeList = maintenanceTypeList;
	}

	public MaintenanceType getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(MaintenanceType maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

}
