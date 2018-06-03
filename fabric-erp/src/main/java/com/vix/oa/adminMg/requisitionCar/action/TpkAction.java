
package com.vix.oa.adminMg.requisitionCar.action;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.requisitionCar.controller.VehicleRequestController;
import com.vix.oa.adminMg.requisitionCar.entity.Tpk;
import com.vix.oa.adminMg.requisitionCar.entity.VehicleRequest;

/**
 * 
 * @ClassName: TpkAction
 * @Description: 油耗统计
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-6-5 上午10:14:49
 */
@Controller
@Scope("prototype")
public class TpkAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(VehicleRequestController.class);

	@Autowired
	private VehicleRequestController vehicleRequestController;

	@Autowired
	private InitEntityBaseController initEntityBaseController;

	private List<Tpk> tpkList;

	/** 车牌号 */
	private List<VehicleRequest> vehicleRequestList;

	private String id;
	private String pageNo;
	private Tpk tpk;

	/** 加载顶端工具栏 */
	public String goTopDynamicMenuContent() {

		return "goTopDynamicMenuContent";
	}

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			tpkList = vehicleRequestController.doLisTpkIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goTpkList() {
		try {
			Map<String, Object> params = getParams();
			/* params.put("votingType.code," + SearchCondition.ANYLIKE, "1"); */
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			Pager pager = vehicleRequestController.doTpkList(params, getPager());
			logger.info("获取车辆油耗管理列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTpkList";
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取车辆油耗搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 司机姓名
			String carname = getRequestParameter("carname");
			if (null != carname && !"".equals(carname)) {
				carname = URLDecoder.decode(carname, "utf-8");
			}
			// 车牌号
			String plateNumber = getRequestParameter("plateNumber");
			if (null != plateNumber && !"".equals(plateNumber)) {
				plateNumber = URLDecoder.decode(plateNumber, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("carname," + SearchCondition.ANYLIKE, carname);
				pager = vehicleRequestController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != carname && !"".equals(carname)) {
					params.put("maintenanceReason," + SearchCondition.ANYLIKE, carname);
				}
				if (null != plateNumber && !"".equals(plateNumber)) {
					params.put("plateNumber," + SearchCondition.ANYLIKE, plateNumber);
				}
				pager = vehicleRequestController.goTpkList(params, getPager());
			}
			logger.info("获取车辆油耗搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTpkList";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			vehicleRequestList = baseHibernateService.findAllByEntityClass(VehicleRequest.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				tpk = vehicleRequestController.doListTpkById(id);
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
			String carname = tpk.getCarname();
			String py = ChnToPinYin.getPYString(carname);
			tpk.setChineseFirstLetter(py.toUpperCase());
			vehicleRequestList = baseHibernateService.findAllByEntityClass(VehicleRequest.class);
			if (StringUtils.isNotEmpty(tpk.getId()) && !"".equals(tpk.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(tpk);
			tpk = vehicleRequestController.doSaveTpk(tpk);
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
			Tpk pb = vehicleRequestController.findTpkById(id);
			if (null != pb) {
				vehicleRequestController.doDeleteByTpk(pb);
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

	public List<Tpk> getTpkList() {
		return tpkList;
	}

	public void setTpkList(List<Tpk> tpkList) {
		this.tpkList = tpkList;
	}

	public Tpk getTpk() {
		return tpk;
	}

	public void setTpk(Tpk tpk) {
		this.tpk = tpk;
	}

	public VehicleRequestController getVehicleRequestController() {
		return vehicleRequestController;
	}

	public void setVehicleRequestController(VehicleRequestController vehicleRequestController) {
		this.vehicleRequestController = vehicleRequestController;
	}

}
