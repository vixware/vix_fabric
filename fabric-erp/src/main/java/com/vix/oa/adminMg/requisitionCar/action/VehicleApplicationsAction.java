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
import com.vix.oa.adminMg.requisitionCar.controller.VehicleApplicationsController;
import com.vix.oa.adminMg.requisitionCar.entity.CarUse;
import com.vix.oa.adminMg.requisitionCar.entity.VehicleRequest;

/**
 * 车辆使用申请管理
 * 
 * @author Thinkpad
 *
 */
@Controller
@Scope("prototype")
public class VehicleApplicationsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(VehicleApplicationsController.class);

	@Autowired
	private VehicleApplicationsController vehicleApplicationsController;

	@Autowired
	private InitEntityBaseController initEntityBaseController;

	private String id;

	public String bookingSituation;

	private String pageNo;

	private List<CarUse> carUseList;

	private CarUse carUse;

	/** 车牌号 */
	private List<VehicleRequest> vehicleRequestList;

	/** 加载顶端工具栏 */
	public String goTopDynamicMenuContent() {

		return "goTopDynamicMenuContent";
	}

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			carUseList = vehicleApplicationsController.doListCarUseIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			// 状态
			String bookingSituation = getRequestParameter("bookingSituation");
			if (null != bookingSituation && !"".equals(bookingSituation)) {
				params.put("bookingSituation," + SearchCondition.EQUAL, Integer.parseInt(bookingSituation));
			}
			// 按最近使用
			String startTime = getRequestParameter("startTime");
			if (startTime != null && !"".equals(startTime)) {
				params.put("startTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(startTime));
			}
			// uploadPersonId包含当前登录人id
			/*
			 * String employeeId = SecurityUtil.getCurrentUserId();
			 * params.put("uploadPersonId," + SearchCondition.EQUAL,employeeId);
			 */
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("startTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = vehicleApplicationsController.doCarUseSingleList(params, getPager());
			logger.info("获取车辆使用列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取车辆使用搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 主题
			String theme = getRequestParameter("theme");
			if (null != theme && !"".equals(theme)) {
				theme = URLDecoder.decode(theme, "utf-8");
			}
			// 用车人
			String carName = getRequestParameter("carName");
			if (null != carName && !"".equals(carName)) {
				carName = URLDecoder.decode(carName, "utf-8");
			}
			// 事由
			String reasons = getRequestParameter("reasons");
			if (null != reasons && !"".equals(reasons)) {
				reasons = URLDecoder.decode(reasons, "utf-8");
			}
			// 开始时间
			String startTime = getRequestParameter("startTime");
			if (startTime != null && !"".equals(startTime)) {
				startTime = URLDecoder.decode(startTime, "utf-8");
			}
			// 结束时间
			String endTime = getRequestParameter("endTime");
			if (endTime != null && !"".equals(endTime)) {
				endTime = URLDecoder.decode(endTime, "utf-8");
			}
			// 参与人
			String pubNames = getRequestParameter("pubNames");
			if (null != pubNames && !"".equals(pubNames)) {
				pubNames = URLDecoder.decode(pubNames, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("carName," + SearchCondition.ANYLIKE, carName);
				pager = vehicleApplicationsController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != theme && !"".equals(theme)) {
					params.put("theme," + SearchCondition.ANYLIKE, theme);
				}
				if (null != carName && !"".equals(carName)) {
					params.put("carName," + SearchCondition.ANYLIKE, carName);
				}
				if (null != reasons && !"".equals(reasons)) {
					params.put("reasons," + SearchCondition.ANYLIKE, reasons);
				}
				if (null != pubNames && !"".equals(pubNames)) {
					params.put("pubNames," + SearchCondition.ANYLIKE, pubNames);
				}
				if (null != startTime && !"".equals(startTime)) {
					params.put("startTime," + SearchCondition.ANYLIKE, startTime);
				}
				if (null != endTime && !"".equals(endTime)) {
					params.put("endTime," + SearchCondition.ANYLIKE, endTime);
				}
				pager = vehicleApplicationsController.goSingleList(params, getPager());
			}
			logger.info("获取车辆使用搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("carSituation," + SearchCondition.NOEQUAL, 3);
			vehicleRequestList = baseHibernateService.findAllByConditions(VehicleRequest.class, params);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				carUse = vehicleApplicationsController.doListCarUseById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(carUse.getId()) && !"".equals(carUse.getId())) {
				isSave = false;
			}
			/** 索引carSituation */
			String reasons = carUse.getReasons();
			String py = ChnToPinYin.getPYString(reasons);
			carUse.setChineseFirstLetter(py.toUpperCase());
			initEntityBaseController.initEntityBaseAttribute(carUse);
			carUse = vehicleApplicationsController.doSaveCarUse(carUse);
			/** 根据vehicleRequest的id对vehicleRequest里的CarSituation进行set */
			VehicleRequest vehicleRequest = baseHibernateService.findEntityById(VehicleRequest.class, carUse.getVehicleRequest().getId());
			vehicleRequest.setCarSituation(2);
			baseHibernateService.merge(vehicleRequest);

			this.carUse.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.carUse.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			carUse.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.carUse);
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
			CarUse pb = vehicleApplicationsController.findEntityById(id);
			if (null != pb) {
				vehicleApplicationsController.doDeleteByCarUse(pb);
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

	// 跳转到选择设备页面
	public String goChooseCar() {
		return "goChooseCar";
	}

	/** 获取设备列表数据 */
	public String goCarList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按状态
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			Pager pager = vehicleApplicationsController.goSingleCarList(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCarList";
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

	public String getBookingSituation() {
		return bookingSituation;
	}

	public void setBookingSituation(String bookingSituation) {
		this.bookingSituation = bookingSituation;
	}

	public List<CarUse> getCarUseList() {
		return carUseList;
	}

	public void setCarUseList(List<CarUse> carUseList) {
		this.carUseList = carUseList;
	}

	public CarUse getCarUse() {
		return carUse;
	}

	public void setCarUse(CarUse carUse) {
		this.carUse = carUse;
	}

	public List<VehicleRequest> getVehicleRequestList() {
		return vehicleRequestList;
	}

	public void setVehicleRequestList(List<VehicleRequest> vehicleRequestList) {
		this.vehicleRequestList = vehicleRequestList;
	}

}
