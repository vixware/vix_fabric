
package com.vix.oa.adminMg.requisitionCar.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.requisitionCar.controller.VehicleRequestController;
import com.vix.oa.adminMg.requisitionCar.entity.VehicleRequest;
import com.vix.oa.adminMg.typeSettings.entity.DisplacementType;
import com.vix.oa.adminMg.typeSettings.entity.EngineType;
import com.vix.oa.adminMg.typeSettings.entity.TransmissionType;
import com.vix.oa.adminMg.typeSettings.entity.VehicleColor;
import com.vix.oa.adminMg.typeSettings.entity.VehicleType;

/**
 * 
 * @ClassName: RequisitionCarAction
 * @Description: 新增车辆
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-3-5 上午10:32:29
 */
@Controller
@Scope("prototype")
public class RequisitionCarAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(VehicleRequestController.class);

	@Autowired
	private VehicleRequestController vehicleRequestController;

	private List<VehicleRequest> vehicleRequestList;

	/** 车辆类型 */
	private List<VehicleType> vehicleTypeList;
	/** 车辆颜色 */
	private List<VehicleColor> vehicleColorList;
	/** 变速器类型 */
	private List<TransmissionType> transmissionTypeList;
	/** 引擎类型 */
	private List<EngineType> engineTypeList;
	/** 排量类型 */
	private List<DisplacementType> displacementTypeList;

	private String id;
	private String parentId;
	private String pageNo;
	private VehicleRequest vehicleRequest;

	/** 加载顶端工具栏 */
	/*
	 * public String goTopDynamicMenuContent() {
	 * 
	 * return "goTopDynamicMenuContent"; }
	 */

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			vehicleRequestList = baseHibernateService.findAllByEntityClass(VehicleRequest.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 状态
			String status = getRequestParameter("status");
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			if (StringUtils.isNotEmpty(parentId) && !parentId.equals("0")) {
				params.put("parentCategory.id," + SearchCondition.EQUAL, parentId);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), VehicleRequest.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取车辆信息搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 型号
			String model = getRequestParameter("model");
			if (null != model && !"".equals(model)) {
				model = URLDecoder.decode(model, "utf-8");
			}
			// 车牌号
			String plateNumber = getRequestParameter("plateNumber");
			if (null != plateNumber && !"".equals(plateNumber)) {
				plateNumber = URLDecoder.decode(plateNumber, "utf-8");
			}
			// 引擎号
			String engineNumber = getRequestParameter("engineNumber");
			if (null != engineNumber && !"".equals(engineNumber)) {
				engineNumber = URLDecoder.decode(engineNumber, "utf-8");
			}
			// 购买价
			String price = getRequestParameter("price");
			if (null != price && !"".equals(price)) {
				price = URLDecoder.decode(price, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("model," + SearchCondition.ANYLIKE, model);
				pager = vehicleRequestController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != model && !"".equals(model)) {
					params.put("model," + SearchCondition.ANYLIKE, model);
				}
				if (null != plateNumber && !"".equals(plateNumber)) {
					params.put("plateNumber," + SearchCondition.ANYLIKE, plateNumber);
				}
				if (null != engineNumber && !"".equals(engineNumber)) {
					params.put("engineNumber," + SearchCondition.ANYLIKE, engineNumber);
				}
				if (null != price && !"".equals(price)) {
					params.put("price," + SearchCondition.ANYLIKE, price);
				}
				pager = vehicleRequestController.goSingleList(params, getPager());
			}
			logger.info("获取车辆信息列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			vehicleTypeList = baseHibernateService.findAllByEntityClass(VehicleType.class);
			vehicleColorList = baseHibernateService.findAllByEntityClass(VehicleColor.class);
			transmissionTypeList = baseHibernateService.findAllByEntityClass(TransmissionType.class);
			engineTypeList = baseHibernateService.findAllByEntityClass(EngineType.class);
			displacementTypeList = baseHibernateService.findAllByEntityClass(DisplacementType.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				vehicleRequest = baseHibernateService.findEntityById(VehicleRequest.class, id);
			} else {
				if (StringUtils.isNotEmpty(parentId) && !parentId.equals("0") && !"undefined".equals(parentId)) {
					// if(null != parentId &&!"undefined".equals(parentId)&& parentId > 0){
					VehicleRequest c = baseHibernateService.findEntityById(VehicleRequest.class, parentId);
					if (null != c) {
						vehicleRequest = new VehicleRequest();
						vehicleRequest.setParentCategory(c);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(vehicleRequest.getId()) && !"".equals(vehicleRequest.getId())) {
				isSave = false;
			}
			/** 索引carSituation */
			String model = vehicleRequest.getModel();
			String py = ChnToPinYin.getPYString(model);
			vehicleRequest.setChineseFirstLetter(py.toUpperCase());
			initEntityBaseController.initEntityBaseAttribute(vehicleRequest);
			vehicleRequest = vehicleRequestController.doSaveVehicleRequest(vehicleRequest);
			vehicleRequest.setCarSituation(0);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			baseHibernateService.deleteById(VehicleRequest.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				listOrganization = baseHibernateService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = baseHibernateService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listOrganization.size();
			for (int i = 0; i < count; i++) {
				Organization org = listOrganization.get(i);
				if (org.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goChooseCategory() {
		return "goChooseCategory";
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public VehicleRequest getVehicleRequest() {
		return vehicleRequest;
	}

	public void setVehicleRequest(VehicleRequest vehicleRequest) {
		this.vehicleRequest = vehicleRequest;
	}

	public List<VehicleType> getVehicleTypeList() {
		return vehicleTypeList;
	}

	public void setVehicleTypeList(List<VehicleType> vehicleTypeList) {
		this.vehicleTypeList = vehicleTypeList;
	}

	public VehicleRequestController getVehicleRequestController() {
		return vehicleRequestController;
	}

	public void setVehicleRequestController(VehicleRequestController vehicleRequestController) {
		this.vehicleRequestController = vehicleRequestController;
	}

	public List<VehicleColor> getVehicleColorList() {
		return vehicleColorList;
	}

	public void setVehicleColorList(List<VehicleColor> vehicleColorList) {
		this.vehicleColorList = vehicleColorList;
	}

	public List<TransmissionType> getTransmissionTypeList() {
		return transmissionTypeList;
	}

	public void setTransmissionTypeList(List<TransmissionType> transmissionTypeList) {
		this.transmissionTypeList = transmissionTypeList;
	}

	public List<EngineType> getEngineTypeList() {
		return engineTypeList;
	}

	public void setEngineTypeList(List<EngineType> engineTypeList) {
		this.engineTypeList = engineTypeList;
	}

	public List<DisplacementType> getDisplacementTypeList() {
		return displacementTypeList;
	}

	public void setDisplacementTypeList(List<DisplacementType> displacementTypeList) {
		this.displacementTypeList = displacementTypeList;
	}

}
