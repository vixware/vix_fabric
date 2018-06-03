
package com.vix.oa.adminMg.requisitionCar.action;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.oa.adminMg.requisitionCar.controller.VehicleUseController;

/**
 * 车辆查询管理
 * @author Thinkpad
 *
 */
@Controller
@Scope("prototype")
public class VehicleApplicationQueryAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(VehicleUseController.class);
	
	/**加载顶端工具栏*/
	public String goTopDynamicMenuContent() {
		
		return "goTopDynamicMenuContent";
	}
	
	/** 跳转到列表页面 */
	@Override
	public String goList() {
		return GO_LIST;
	}
	
	/** 获取列表数据 */
	public String goSingleList() {
		return GO_SINGLE_LIST;
	}
}
