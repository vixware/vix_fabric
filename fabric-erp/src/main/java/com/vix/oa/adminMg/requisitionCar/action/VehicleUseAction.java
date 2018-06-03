package com.vix.oa.adminMg.requisitionCar.action;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.requisitionCar.controller.VehicleUseController;
import com.vix.oa.adminMg.requisitionCar.entity.CarUse;

/**
 * 车辆使用管理
 * @author Thinkpad
 *
 */
@Controller
@Scope("prototype")
public class VehicleUseAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(VehicleUseController.class);
	
	@Autowired
	private VehicleUseController vehicleUseController;
	

	private String id;
	
	private String pageNo;
	
	public Integer bookingSituation;
	
	private List<CarUse> carUseList;
	
	private CarUse carUse;
	
	private CarUse entity;
	
	
	/**加载顶端工具栏*/
	public String goTopDynamicMenuContent() {
		
		return "goTopDynamicMenuContent";
	}
	
	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			carUseList = vehicleUseController.doListCarUseIndex();
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
			//状态
			String bookingSituation = getRequestParameter("bookingSituation");
			if (null != bookingSituation && !"".equals(bookingSituation)) {
				params.put("bookingSituation," + SearchCondition.EQUAL, Integer.parseInt(bookingSituation));
			}		
			// 按最近使用
			String startTime = getRequestParameter("startTime");
			if (startTime != null && !"".equals(startTime)) {
				params.put("startTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(startTime));
			}
			Pager pager = vehicleUseController.doCarUseSingleList(params,getPager());
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
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("carName," + SearchCondition.ANYLIKE, carName);
				pager = vehicleUseController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != carName && !"".equals(carName)) {
					params.put("carName," + SearchCondition.ANYLIKE, carName);
				}
				if (null != reasons && !"".equals(reasons)) {
					params.put("reasons," + SearchCondition.ANYLIKE, reasons);
				}
				pager = vehicleUseController.goSingleList(params, getPager());
			}
			logger.info("获取车辆使用搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	
	public void updateBookingSituation(){
		try{
			CarUse pb = vehicleUseController.findEntityById(id);
			if(pb.getBookingSituation()== 1){
				pb.setBookingSituation(3);
			}else
				pb.setBookingSituation(bookingSituation);
			
			carUse = vehicleUseController.doSaveCarUse(pb);
		}catch(Exception e){
			e.printStackTrace();
		}
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

	public CarUse getEntity() {
		return entity;
	}

	public void setEntity(CarUse entity) {
		this.entity = entity;
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

}
