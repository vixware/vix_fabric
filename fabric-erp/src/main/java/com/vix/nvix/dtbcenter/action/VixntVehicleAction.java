package com.vix.nvix.dtbcenter.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.vehiclemanagement.entity.Vehicle;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class VixntVehicleAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	/**
	 * 车辆
	 */
	private Vehicle vehicle;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String searchvehicleno = getRequestParameter("searchvehicleno");
			if (StringUtils.isNotEmpty(searchvehicleno)) {
				params.put("vehicleNO," + SearchCondition.ANYLIKE, searchvehicleno);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), Vehicle.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vehicle = vixntBaseService.findEntityById(Vehicle.class, id);
			} else {
				vehicle = new Vehicle();
				vehicle.setVehicleCode(VixUUID.createCode(8));
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
			if (StringUtils.isNotEmpty(vehicle.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(vehicle);
			vehicle = vixntBaseService.merge(vehicle);
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
			Vehicle pb = vixntBaseService.findEntityById(Vehicle.class, id);
			if (null != pb) {
				vixntBaseService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
