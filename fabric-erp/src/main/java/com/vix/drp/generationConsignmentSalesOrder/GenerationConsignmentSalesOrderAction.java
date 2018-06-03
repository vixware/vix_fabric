/**
 * 
 */
package com.vix.drp.generationConsignmentSalesOrder;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

/**
 * @author zhanghaibing
 * 
 * @date 2014-5-6
 */
@Controller
@Scope("prototype")
public class GenerationConsignmentSalesOrderAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Override
	public String goList() {
		return GO_LIST;
	}

	/**
	 * 根据条件查询入库单信息
	 */
	public String goSingleList() {
		return GO_SINGLE_LIST;
	}

	/** 跳转至新增修改盘点单 */
	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateStockTakingItem() {
		return "goSaveOrUpdateStockTakingItem";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		return UPDATE;
	}

	public void getStockTakingItemJson() {
	}

	public String saveOrUpdateStockTakingItem() {
		return UPDATE;
	}

	public String deleteStockTakingItemById() {
		return UPDATE;
	}

}
