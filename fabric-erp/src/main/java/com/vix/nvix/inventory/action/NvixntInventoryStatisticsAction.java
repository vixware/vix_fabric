package com.vix.nvix.inventory.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 库存统计
 * 
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixntInventoryStatisticsAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 收发存汇总
	 * 
	 * @return
	 */
	public String sendingAndReceivingDeposit() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sendingAndReceivingDeposit";
	}
	/**
	 * 存货分布
	 * 
	 * @return
	 */
	public String inventoryDistribution() {

		return "inventoryDistribution";
	}
	/**
	 * 业务类型汇总
	 * 
	 * @return
	 */
	public String businessType() {

		return "businessType";
	}
	/**
	 * 限额领料汇总
	 * 
	 * @return
	 */
	public String pickingTheLimit() {

		return "pickingTheLimit";
	}
}
