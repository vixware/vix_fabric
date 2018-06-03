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
import com.vix.oa.adminMg.typeSettings.entity.DisplacementType;

@Controller
@Scope("prototype")
public class DisplacementTypeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(VehicleRequestController.class);

	@Autowired
	private VehicleRequestController vehicleRequestController;
	private List<DisplacementType> displacementTypeList;
	private DisplacementType displacementType;
	private String id;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			displacementTypeList = baseHibernateService.findAllByEntityClass(DisplacementType.class);
			Map<String, Object> params = getParams();
			Pager pager = vehicleRequestController.doSubDisplacementTypeList(params, getPager());
			logger.info("获取排量类型列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				displacementType = vehicleRequestController.doListDisplacementTypeById(id);
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
			if (displacementType != null && null != displacementType.getId()) {
				isSave = false;
			}

			displacementType = vehicleRequestController.doSaveDisplacementType(displacementType);
			logger.info("对排量类型进行修改！");
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
			DisplacementType displacementType = vehicleRequestController.doListDisplacementTypeById(id);
			if (null != displacementType) {
				vehicleRequestController.doDeleteByDisplacementType(displacementType);
				logger.info("");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除排量类型设置信息");
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

	public List<DisplacementType> getDisplacementTypeList() {
		return displacementTypeList;
	}

	public void setDisplacementTypeList(List<DisplacementType> displacementTypeList) {
		this.displacementTypeList = displacementTypeList;
	}

	public DisplacementType getDisplacementType() {
		return displacementType;
	}

	public void setDisplacementType(DisplacementType displacementType) {
		this.displacementType = displacementType;
	}

}
