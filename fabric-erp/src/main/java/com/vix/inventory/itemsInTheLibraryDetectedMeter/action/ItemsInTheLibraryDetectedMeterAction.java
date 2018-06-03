package com.vix.inventory.itemsInTheLibraryDetectedMeter.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

/**
 * 在库品待检表
 * 
 * @author zhanghaibing
 * 
 * @date 2013-12-11
 */
@Controller
@Scope("prototype")
public class ItemsInTheLibraryDetectedMeterAction extends BaseAction {

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

	/** 跳转至新增修改调拨单 */
	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		return UPDATE;
	}

}
