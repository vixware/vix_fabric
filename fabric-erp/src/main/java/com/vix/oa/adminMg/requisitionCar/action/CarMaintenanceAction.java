package com.vix.oa.adminMg.requisitionCar.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.requisitionCar.controller.VehicleRequestController;
import com.vix.oa.adminMg.requisitionCar.entity.CarMaintenance;
import com.vix.oa.adminMg.requisitionCar.entity.VehicleRequest;
import com.vix.oa.adminMg.typeSettings.entity.MaintenanceType;

/**
 * 
 * @ClassName: CarMaintenanceAction
 * @Description: 车辆维护管理
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-6-3 下午5:00:52
 */
@Controller
@Scope("prototype")
public class CarMaintenanceAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(VehicleRequestController.class);

	@Autowired
	private VehicleRequestController vehicleRequestController;

	@Autowired
	private InitEntityBaseController initEntityBaseController;


	private List<CarMaintenance> carMaintenanceList;

	/** 车牌号 */
	private List<VehicleRequest> vehicleRequestList;
	/** 维护类型 */
	private List<MaintenanceType> maintenanceTypeList;

	private String id;
	private String vehicleRequestID;
	private String pageNo;
	private CarMaintenance carMaintenance;
	private VehicleRequest vehicleRequest;
	public Integer carSituation;

	/** 加载顶端工具栏 */
	public String goTopDynamicMenuContent() {

		return "goTopDynamicMenuContent";
	}

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			carMaintenanceList = vehicleRequestController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goCarMaintenance() {
		try {
			Map<String, Object> params = getParams();
			/* params.put("votingType.code," + SearchCondition.ANYLIKE, "1"); */
			/*
			 * String code = getRequestParameter("code"); if (null != code &&
			 * !"".equals(code)) { params.put("code," + SearchCondition.ANYLIKE, code); }
			 */

			// 按最近使用
			String maintenanceDate = getRequestParameter("maintenanceDate");
			if (maintenanceDate != null && !"".equals(maintenanceDate)) {
				params.put("maintenanceDate," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(maintenanceDate));
			}
			Pager pager = vehicleRequestController.doCarMaintenanceList(params, getPager());
			logger.info("获取车辆维护管理列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCarMaintenance";
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取车辆维护搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 维护原因
			String maintenanceReason = getRequestParameter("maintenanceReason");
			if (null != maintenanceReason && !"".equals(maintenanceReason)) {
				maintenanceReason = URLDecoder.decode(maintenanceReason, "utf-8");
			}
			// 经办人
			String uploadPersonName = getRequestParameter("uploadPersonName");
			if (null != uploadPersonName && !"".equals(uploadPersonName)) {
				uploadPersonName = URLDecoder.decode(uploadPersonName, "utf-8");
			}
			// 维护费用
			String maintenanceCost = getRequestParameter("maintenanceCost");
			if (null != maintenanceCost && !"".equals(maintenanceCost)) {
				maintenanceCost = URLDecoder.decode(maintenanceCost, "utf-8");
			}
			// 维护情况
			String maintenance = getRequestParameter("maintenance");
			if (null != maintenance && !"".equals(maintenance)) {
				maintenance = URLDecoder.decode(maintenance, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("maintenanceReason," + SearchCondition.ANYLIKE, maintenanceReason);
				pager = vehicleRequestController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != maintenanceReason && !"".equals(maintenanceReason)) {
					params.put("maintenanceReason," + SearchCondition.ANYLIKE, maintenanceReason);
				}
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				if (null != maintenanceCost && !"".equals(maintenanceCost)) {
					params.put("maintenanceCost," + SearchCondition.ANYLIKE, maintenanceCost);
				}
				if (null != maintenance && !"".equals(maintenance)) {
					params.put("maintenance," + SearchCondition.ANYLIKE, maintenance);
				}
				pager = vehicleRequestController.goCarMaintenance(params, getPager());
			}
			logger.info("获取维护车辆搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCarMaintenance";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("carSituation," + SearchCondition.NOEQUAL, 3);
			vehicleRequestList = baseHibernateService.findAllByConditions(VehicleRequest.class, params);
			maintenanceTypeList = baseHibernateService.findAllByEntityClass(MaintenanceType.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				carMaintenance = vehicleRequestController.doListCarMaintenanceById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			/** 索引 */
			String maintenanceReason = carMaintenance.getMaintenanceReason();
			String py = ChnToPinYin.getPYString(maintenanceReason);

			carMaintenance.setChineseFirstLetter(py.toUpperCase());
			if (StringUtils.isNotEmpty(carMaintenance.getId()) && !"".equals(carMaintenance.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(carMaintenance);
			carMaintenance = vehicleRequestController.doSaveCarMaintenance(carMaintenance);
			/** 根据vehicleRequest的id对vehicleRequest里的CarSituation进行set */
			VehicleRequest vehicleRequest = baseHibernateService.findEntityById(VehicleRequest.class, carMaintenance.getVehicleRequest().getId());
			vehicleRequest.setCarSituation(3);
			baseHibernateService.merge(vehicleRequest);

			this.carMaintenance.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.carMaintenance.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			carMaintenance.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.carMaintenance);
			logger.info("新增！");
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
		}
		return UPDATE;
	}

	public String goSaveOrUpdateReturn() {
		try {
			vehicleRequestList = baseHibernateService.findAllByEntityClass(VehicleRequest.class);
			maintenanceTypeList = baseHibernateService.findAllByEntityClass(MaintenanceType.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				carMaintenance = vehicleRequestController.doListCarMaintenanceById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "goSaveOrUpdateReturn";
	}

	public String saveOrUpdateReturn() {
		boolean isSave = true;
		try {
			/** 索引 */
			String maintenanceReason = carMaintenance.getMaintenanceReason();
			String py = ChnToPinYin.getPYString(maintenanceReason);

			carMaintenance.setChineseFirstLetter(py.toUpperCase());
			if (StringUtils.isNotEmpty(carMaintenance.getId()) && !"".equals(carMaintenance.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(carMaintenance);
			carMaintenance = vehicleRequestController.doSaveCarMaintenance(carMaintenance);
			/** 根据vehicleRequest的id对vehicleRequest里的CarSituation进行set */
			VehicleRequest vehicleRequest = baseHibernateService.findEntityById(VehicleRequest.class, carMaintenance.getVehicleRequest().getId());
			vehicleRequest.setCarSituation(0);
			baseHibernateService.merge(vehicleRequest);
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
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			CarMaintenance pb = vehicleRequestController.findCarMaintenanceById(id);
			if (null != pb) {
				vehicleRequestController.doDeleteByCarMaintenance(pb);
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public List<VehicleRequest> getVehicleRequestList() {
		return vehicleRequestList;
	}

	public void setVehicleRequestList(List<VehicleRequest> vehicleRequestList) {
		this.vehicleRequestList = vehicleRequestList;
	}

	public List<CarMaintenance> getCarMaintenanceList() {
		return carMaintenanceList;
	}

	public void setCarMaintenanceList(List<CarMaintenance> carMaintenanceList) {
		this.carMaintenanceList = carMaintenanceList;
	}

	public List<MaintenanceType> getMaintenanceTypeList() {
		return maintenanceTypeList;
	}

	public void setMaintenanceTypeList(List<MaintenanceType> maintenanceTypeList) {
		this.maintenanceTypeList = maintenanceTypeList;
	}

	public CarMaintenance getCarMaintenance() {
		return carMaintenance;
	}

	public void setCarMaintenance(CarMaintenance carMaintenance) {
		this.carMaintenance = carMaintenance;
	}

	public VehicleRequestController getVehicleRequestController() {
		return vehicleRequestController;
	}

	public void setVehicleRequestController(VehicleRequestController vehicleRequestController) {
		this.vehicleRequestController = vehicleRequestController;
	}

	public VehicleRequest getVehicleRequest() {
		return vehicleRequest;
	}

	public void setVehicleRequest(VehicleRequest vehicleRequest) {
		this.vehicleRequest = vehicleRequest;
	}

	public Integer getCarSituation() {
		return carSituation;
	}

	public void setCarSituation(Integer carSituation) {
		this.carSituation = carSituation;
	}

	public String getVehicleRequestID() {
		return vehicleRequestID;
	}

	public void setVehicleRequestID(String vehicleRequestID) {
		this.vehicleRequestID = vehicleRequestID;
	}

}
