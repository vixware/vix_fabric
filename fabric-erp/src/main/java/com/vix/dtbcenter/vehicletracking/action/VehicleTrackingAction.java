package com.vix.dtbcenter.vehicletracking.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.driver.action.DriverAction;
import com.vix.dtbcenter.vehiclemanagement.controller.VehicleController;
import com.vix.dtbcenter.vehiclemanagement.entity.InsuranceRegister;
import com.vix.dtbcenter.vehiclemanagement.entity.Vehicle;

@Controller
@Scope("prototype")
public class VehicleTrackingAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(DriverAction.class);
	@Autowired
	private VehicleController vehicleController;

	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 司机
	 */
	private Vehicle vehicle;
	private List<Vehicle> vehicleList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			vehicleList = vehicleController.doListVehicleList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (null != pageNo && !"".equals(pageNo) && !"undefined".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			Pager pager = vehicleController.doListVehicle(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				vehicle = vehicleController.doListVehicleById(id);
				logger.info("");
			} else {
				vehicle = new Vehicle();
				vehicle.setIsTemp("1");
				vehicle = vehicleController.doSaveOrUpdateVehicle(vehicle);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != vehicle.getId()) {
				isSave = false;
			}
			vehicle.setIsTemp("");
			vehicle = vehicleController.doSaveOrUpdateVehicle(vehicle);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			Vehicle pb = vehicleController.doListVehicleById(id);
			if (null != pb) {
				vehicleController.doDeleteVehicle(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * InsuranceRegister
	 */
	public void getInsuranceRegisterJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Vehicle vehicle = vehicleController.doListVehicleById(id);
				if (vehicle != null) {
					json = convertListToJson(new ArrayList<InsuranceRegister>(vehicle.getInsuranceRegisters()), vehicle.getInsuranceRegisters().size(), "vehicle");
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSearch() {
		return "goSearch";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

}
