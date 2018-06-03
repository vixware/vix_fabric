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
import com.vix.oa.adminMg.typeSettings.entity.TransmissionType;

@Controller
@Scope("prototype")
public class TransmissionTypeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(VehicleRequestController.class);

	@Autowired
	private VehicleRequestController vehicleRequestController;
	private List<TransmissionType> transmissionTypeList;
	private TransmissionType transmissionType;
	private String id;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			transmissionTypeList = baseHibernateService.findAllByEntityClass(TransmissionType.class);
			Map<String, Object> params = getParams();
			Pager pager = vehicleRequestController.doSubTransmissionTypeList(params, getPager());
			logger.info("获取变速器类型列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				transmissionType = vehicleRequestController.doListTransmissionTypeById(id);
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
			if (transmissionType != null && null != transmissionType.getId()) {
				isSave = false;
			}

			transmissionType = vehicleRequestController.doSaveTransmissionType(transmissionType);
			logger.info("对变速器类型进行修改！");
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
			TransmissionType transmissionType = vehicleRequestController.doListTransmissionTypeById(id);
			if (null != transmissionType) {
				vehicleRequestController.doDeleteByTransmissionType(transmissionType);
				logger.info("");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除变速器类型设置信息");
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

	public List<TransmissionType> getTransmissionTypeList() {
		return transmissionTypeList;
	}

	public void setTransmissionTypeList(List<TransmissionType> transmissionTypeList) {
		this.transmissionTypeList = transmissionTypeList;
	}

	public TransmissionType getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(TransmissionType transmissionType) {
		this.transmissionType = transmissionType;
	}

}
